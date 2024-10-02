import { useEffect, useState } from 'react';
import CanvasList from '../components/CanvasList';
import SearchBar from '../components/SearchBar';
import ViewToggle from '../components/ViewToggle';

function Home() {
  const [searchText, setSearchText] = useState('');
  const [isGridView, setIsGridView] = useState(true);
  const [data, setData] = useState([]);

  async function fetchData() {
    const data = await fetch('http://localhost:8000/canvases')
      .then(response => response.json())
      .catch(error => console.log(error));
    setData(data);
  }

  useEffect(() => {
    fetchData();
  }, []);

  const handleDeleteItem = id => {
    setData(data.filter(item => item.id !== id));
  };

  const filteredData = data.filter(data =>
    data.title.toLowerCase().includes(searchText.toLowerCase()),
  );

  return (
    <div className="container mx-auto px-4 py-16">
      <div className="mb-6 flex flex-col sm:flex-row items-center justify-between">
        <SearchBar searchText={searchText} setSearchText={setSearchText} />
        <ViewToggle isGridView={isGridView} setIsGridView={setIsGridView} />
      </div>
      <CanvasList
        filteredData={filteredData}
        isGridView={isGridView}
        searchText={searchText}
        onDeleteItem={handleDeleteItem}
      />
    </div>
  );
}

export default Home;
