// App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/header/Header";
import "./App.css";
import Home from "./Home/Home";
import Table from "./components/table/Table";

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/users" element={<Table />} />
      </Routes>
    </Router>
  );
}

export default App;
