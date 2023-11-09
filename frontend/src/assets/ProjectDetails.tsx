import {Project} from "./Types.tsx";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

import homeBTN from "/house.svg";
import editBTN from "/pen.svg";
import deleteBTN from "/delete-cross.svg";
import defaultCanvas from '/default-canvas.png';

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
            <div className="projectDetails">
                <button className="iconBTN" onClick={() => navigate("/")}>
                <img src={homeBTN} alt="back home button" width="20px" height="20px"/>
                </button>
                <button className="editBTN" onClick={() => navigate("/"+project?.id+"/edit")}>
                    <img src={editBTN} alt="edit button" width="20px" height="20px"/>
                </button>
                <button className="editBTN" onClick={deleteCard}>
                    <img src={deleteBTN} alt="delete button" width="20px" height="20px"/>
                </button>

                <h2>{project?.author}</h2>
                {project
                    ? <>
                        <img src={(project.imageURL === null) ? defaultCanvas : project.imageURL} alt={project.author} width="100%" height="auto"/>
                        <p>
                            {project.description}
                        </p>
                    </>
                    : <>
                        <p>Project not found</p>
                    </>
                }
            </div>
    );
}

