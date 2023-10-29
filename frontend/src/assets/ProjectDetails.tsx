import {Project} from "./Types.tsx";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

export default function ProjectDetails() {
    const urlParams = useParams();

    const [project, setProject] = useState<Project>();
    const navigate = useNavigate();

    useEffect(loadBook, [urlParams.id]);
    function loadBook (){
        axios.get("/api/project/"+ urlParams.id)
            .then((response) => {
                if (response.status!==200)
                    throw new Error("Get wrong response status: "+response.status);
                setProject(response.data)
            })
            .catch((error)=>{
                console.error(error);
            })
    }
    return (
        <>

            <button className="backButton" onClick={()=>navigate("/")}>My Dashboard</button>

            <div className="projectDetails">
                {project
                    ? <>
                        {project.author      && <p>Author      : <br/>{project.author     }</p>}
                        {project.description && <p>Description : <br/>{project.description}</p>}
                    </>
                    : <>
                        <p>Project not found</p>
                    </>
                }

            </div>
        </>
    );
}

