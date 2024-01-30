// Importuj obraz
import luggageImage from "./luggage1.png";
import luggageImage2 from "./luggage2.png";
import logo from "./logo.svg";
import "./App.css";
import React, { useEffect, useState } from "react";
import TodoItem from "./components/todoitem";
import Button from "@mui/material/Button";
import Footer from "./components/Footer/Footer";

function App() {
  const [todoItems, setTodoItems] = useState(null);

  //Date
  const current = new Date();
  const weekday = [
    "Niedziela",
    "Poniedziałek",
    "Wtorek",
    "Środa",
    "Czwartek",
    "Piątek",
    "Sob",
  ];
  let day = weekday[current.getDay()];
  let longMonth = current.toLocaleString("pl-pl", { month: "long" });
  let date = `${longMonth} ${current.getDate()}`;

  useEffect(() => {
    console.log("useEffect Loaded.");

    if (!todoItems) {
      fetch("http://localhost:8080/api/walizka")
        .then((response) => response.json())
        .then((data) => {
          console.log("Todo Items List:", data);
          setTodoItems(data);
        });
    }
  }, [todoItems]);

  function addNewTodoItem() {
    fetch("http://localhost:8080/api/walizka", {
      headers: {
        "content-type": "application/json",
      },
      method: "POST",
    })
      .then((response) => response.json())
      .then((data) => {
        setTodoItems([...todoItems, data]);
        console.log(data);
      });
  }

  function handleDeleteTodoItem(item) {
    const updatedTodoItems = todoItems.filter((data) => data.id !== item.id);
    setTodoItems([...updatedTodoItems]);
  }

  return (
    <>
      <div className="main-body">
        <div className="todo-container">
          <div className="above-label">
            <h2 style={{ textTransform: "uppercase" }}>Twoja walizka</h2>
          </div>

          <div className="date">
            <h3>
              Dzisiaj jest {day}, {date}
            </h3>
          </div>

          <div className="addbtn">
            <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
              {/* Dodaj przycisk */}
              <Button variant="contained" onClick={addNewTodoItem}>
                Dodaj rzecz
              </Button>

              {/* Dodaj obraz luggage1 pod przyciskiem */}
              <img src={luggageImage} alt="Luggage" style={{ maxWidth: "100%", marginTop: "10px", marginBottom: "-20px", marginRight: "10px" }} />
            </div>
          </div>

          <div className="todoitems">
            {todoItems
              ? todoItems.map((todoItem) => {
                  return (
                    <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                      <TodoItem
                        data={todoItem}
                        key={todoItem.id}
                        emitDeleteTodoItem={handleDeleteTodoItem}
                      />
                      
                    </div>  
                  );
                })
              : "loading data..."}
              
          </div>
          <img src={luggageImage2} alt="Luggage" style={{ maxWidth: "100%", marginRight: "10px" }} />
        </div>
        
      </div>
      <Footer />
    </>
  );
}

export default App;
