import './App.css'
import axios from "axios";
import {useEffect, useState} from "react";
import {Project} from "./assets/Types.tsx";
import MyDashboard from "./assets/MyDashboard.tsx";

export default function App() {
    const [myProjects, setMyProjects] = useState<Project[]>([]);

    useEffect(loadAllProjects, []);
    function loadAllProjects(){
        axios.get("/api")
            .then((response) => {
                if(response.status !== 200)
                    throw new Error("Wrong response Status when loading: "+response.status);
                setMyProjects(response.data);
            })
            .catch((error)=>{
                console.error(error);
            })
    }

  return (
    <>
      <h1>Work in Progress - Tracker</h1>

        <MyDashboard project={myProjects}/>
    </>
  )
}