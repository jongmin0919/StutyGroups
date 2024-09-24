import { useState } from "react";
import "./App.css";
import Banner from "./components/Banner";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="container">
      <div className="itemGroup">
        <h2>강의 목록</h2>
        <Banner />
        <Banner />
        <Banner />
      </div>
    </div>
  );
}

export default App;
