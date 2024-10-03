import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import React from 'react';
import Button from '../components/Button';

function About() {
  const queryClient = useQueryClient();
  const { isLoading, error, data } = useQuery({
    queryKey: ['canvases'],
    queryFn: () =>
      axios.get('http://localhost:8000/canvases/').then(res => res.data),
  });

  const { mutate: createNewCanvas, isLoading: isLoadingCreate } = useMutation({
    mutationFn: newCanvas =>
      axios.post('http://localhost:8000/canvases/', newCanvas),
    onSuccess: () => {
      queryClient.invalidateQueries(['canvases']);
    },
  });

  const handleCreate = () => {
    createNewCanvas({ title: 'new Canvas' });
  };

  return (
    <div>
      <h2 className="text-3xl">useQuery</h2>
      {isLoading && <p>...Loading</p>}
      {error && <p className="text-red-700">{error.message}</p>}
      {data && data.map(item => <li key={item.id}>{item.title}</li>)}

      <h2 className="text-3xl">useMutation</h2>
      {isLoadingCreate && <p>...Loading</p>}
      <Button onClick={handleCreate}>등록</Button>
    </div>
  );
}

export default About;
