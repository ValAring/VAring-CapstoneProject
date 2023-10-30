import {Project} from "./Types.tsx";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

type Props = {
    readonly onItemChange: () => void
}
export default function ProjectDetails(props: Props) {
    const urlParams = useParams();

    const [project, setProject] = useState<Project>();
    const navigate = useNavigate();

    useEffect(loadBook, [urlParams.id]);

    function loadBook() {
        axios.get("/api/project/" + urlParams.id)
            .then((response) => {
                if (response.status !== 200)
                    throw new Error("Get wrong response status: " + response.status);
                setProject(response.data)
            })
            .catch((error) => {
                console.error(error);
            })
    }

    function deleteCard() {
        axios.delete("/api/project/" + urlParams.id)
            .then(() => {
                navigate("/");
                props.onItemChange();
            })
            .catch(error => {
                console.error("Error on delete book", error)
            })
    }

    return (
        <>

            <button className="iconBTN" onClick={() => navigate("/")}><img src="/src/images/house.svg" alt="back home button" width="20px" height="20px"/></button>
            <button className="iconBTN" onClick={deleteCard}><img src="/src/images/delete-cross.svg" alt="Delete Project trash icon"  width="20px" height="20px"/></button>

            <div className="projectDetails">
                {project
                    ? <>
                        {project.author && <p>Author : <br/>{project.author}</p>}
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

