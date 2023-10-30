import {Project} from "./Types.tsx";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

type Props = {
    readonly onItemChange: ()=> void
}
export default function ProjectDetails(props: Props) {
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

    function deleteCard() {
        axios.delete("/api/project/" + urlParams.id)
            .then(response => {
                navigate("/");
                props.onItemChange();
            })
            .catch(error => {
                console.error("Error on delete book", error)
            })
    }

    return (
        <>

            <button className="backButton" onClick={()=>navigate("/")}>My Dashboard</button>
            <button onClick={deleteCard}>
                <svg width="15px" height="15px" viewBox="5 2 15 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fillRule="evenodd" clipRule="evenodd" d="M14.5 3L15.5 4H19V6H5V4H8.5L9.5 3H14.5ZM12 12.59L14.12 10.47L15.53 11.88L13.41 14L15.53 16.12L14.12 17.53L12 15.41L9.88 17.53L8.47 16.12L10.59 14L8.46 11.88L9.87 10.47L12 12.59ZM6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM16 9H8V19H16V9Z" fill="#fff"/>
                </svg>
            </button>

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

