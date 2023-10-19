import {useEffect, useState} from 'react'
import './App.css'
import axios from "axios";
import {Project} from "./assets/Types.tsx";
import MyProjectsList from "./components/MyProjectsList.tsx";

export default function App() {
    const [myProjects, setMyProjects] = useState<Project[]>([]);

    useEffect(loadAllProjects, []);
    function loadAllProjects(){
        axios.get("/api")
            .then((response) => {
                if(response.status !== 200)
                    throw "Wrong response Status when loading: "+response.status;
                setMyProjects(response.data);
            })
            .catch((error)=>{
                console.error(error);
            })
    }

    return (
        <>
            <h1>WIP Wizard</h1>
            <MyProjectsList project={myProjects}/>
        </>
    )
}
