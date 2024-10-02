import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from './Header';
import Main from './components/Main';

function App() {
  return (
    <>
      <Header />
      <Main>
        <Outlet />
      </Main>
    </>
  );
}

export default App;
