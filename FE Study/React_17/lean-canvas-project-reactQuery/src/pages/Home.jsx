import { useEffect, useState } from 'react';
import axios from 'axios';
import CanvasList from '../components/CanvasList';
import SearchBar from '../components/SearchBar';
import ViewToggle from '../components/ViewToggle';
import { getCanvases, createCanvases, deleteCanvas } from '../api/canvas';
import Loading from '../components/Loading';
import Error from '../components/Error';
import Button from '../components/Button';
import useApiRequest from '../hooks/useApiRequest';

function Home() {
  const [searchText, setSearchText] = useState('');
  const [isGridView, setIsGridView] = useState(true);
  const [data, setData] = useState([]);

  //API call
  const { isLoading, error, execute: fetchData } = useApiRequest(getCanvases);
  const { isLoading: isLoadingCreate, execute: createNewCanvas } =
    useApiRequest(createCanvases);

  useEffect(() => {
    fetchData(
      { title_like: searchText },
      { onSuccess: response => setData(response.data) },
    );
  }, [searchText, fetchData]);

  const handleDeleteItem = async id => {
    try {
      if (confirm('정말 포스트를 삭제하시겠습니까?') === false) {
        return;
      }

      await deleteCanvas(id);
      fetchData({ title_like: searchText });
    } catch (error) {
      alert(error.message);
    }
  };

  const handleCreateCanvas = async () => {
    createNewCanvas(null, {
      onSuccess: () => {
        fetchData(
          { title_like: searchText },
          { onSuccess: response => setData(response.data) },
        );
      },
      onError: error => alert(error.message),
    });

    // try {
    //   setIsLoadingCreate(true);
    //   await new Promise(resolver => setTimeout(resolver, 1000));
    //   await createCanvases();
    //   fetchData({ title_like: searchText });
    // } catch (error) {
    //   alert(error.message);
    // } finally {
    //   setIsLoadingCreate(false);
    // }
  };

  return (
    <>
      <div className="mb-6 flex flex-col sm:flex-row items-center justify-between">
        <SearchBar searchText={searchText} setSearchText={setSearchText} />
        <ViewToggle isGridView={isGridView} setIsGridView={setIsGridView} />
      </div>
      <div className="flex justify-end mb-6">
        <Button onClick={handleCreateCanvas} loading={isLoadingCreate}>
          등록하기
        </Button>
      </div>
      {isLoading && <Loading />}
      {error && (
        <Error
          message={error.message}
          onRetry={() => fetchData({ title_like: searchText })}
        />
      )}
      {!isLoading && !error && (
        <CanvasList
          filteredData={data}
          isGridView={isGridView}
          searchText={searchText}
          onDeleteItem={handleDeleteItem}
        />
      )}
    </>
  );
}

export default Home;
