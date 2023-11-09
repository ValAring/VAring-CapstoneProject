import './App.css'
import axios from "axios";
import {useEffect, useState} from "react";
import {Project} from "./assets/Types.tsx";
import MyDashboard from "./assets/MyDashboard.tsx";
import ProjectDetails from "./assets/ProjectDetails.tsx";
import {Route, Routes} from "react-router-dom";
import AddProject from "./assets/AddProject.tsx";
import EditProject from "./assets/EditProject.tsx";

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
      <h1>WIP-Tracker</h1>
        <Routes>
            <Route path={"/"}           element={<MyDashboard project={myProjects}/>}/>
            <Route path={"/addProject"} element={<AddProject onItemChange={loadAllProjects}/>}/>
            <Route path={"/:id/edit"}   element={<EditProject project={myProjects} onItemChange={loadAllProjects}/>} />
            <Route path={"/:id"}        element={<ProjectDetails onItemChange={loadAllProjects}/>} />
        </Routes>
    </>
  )
}