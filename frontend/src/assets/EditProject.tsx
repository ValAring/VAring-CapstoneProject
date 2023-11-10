import '../App.css';
import { useNavigate, useParams } from "react-router-dom";
import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import axios from "axios";
import homeBTN from "/house.svg";
import { Project } from './Types';

type Props = {
    readonly project: Project[]
    readonly onItemChange: ()=> void
}
export default function EditProject(props: Props) {
    const { id } = useParams();
    const filteredProject:Project[] = props.project.filter(e => e.id === id);
    const navigate = useNavigate();

    if (filteredProject.length < 1)
        return <>
            Can't find book with id "{id}"<br/>
            <button type="button" onClick={() => navigate("/")}>Back</button>
        </>

    return <EditProjectForm project={filteredProject[0]} reload={props.onItemChange}/>
}

type FormProps = {
    readonly project: Project
    readonly reload: ()=>void
}

function EditProjectForm( props: FormProps ) {
    const [project, updateProject] = useState<Project>(props.project);
    useEffect(
        ()=> updateProject(props.project),
        [ props.project ]
    );
    const navigate = useNavigate();
    console.debug(`Rendering EditProjectForm { id:"${props.project.id}" }`);

    function updateProjectValue( name:string, value:string ) {
        updateProject( {
            ...project,
            [name]: value
        } );
    }

    function onChangeFcnI( event: ChangeEvent<HTMLInputElement> ) {
        updateProjectValue( event.target.name, event.target.value );
    }

    function saveChanges( event: FormEvent<HTMLFormElement> ) {
        event.preventDefault();
        update(project);
        navigate("/"+project.id);
    }

    function update( project: Project ) {
        axios
            .put('/api/project/'+project.id, project )
            .then(response => {
                if (response.status != 200)
                    throw {error: "Got wrong status on update book: " + response.status}
                props.reload()
            })
            .catch(reason => {
                console.error(reason)
            });
    }

    return (
        <>
            <button className="iconBTN" onClick={() => navigate("/")}><img src={homeBTN} alt="back home button" width="20px" height="20px" /></button>
            <h2>Edit Project</h2>

            <form onSubmit={saveChanges}>
                <input name="author" value={project.author} onChange={onChangeFcnI} />
                <input name="description" value={project.description} onChange={onChangeFcnI} />

                {project.imageURL && <img src={project.imageURL} alt={project.author} width="150px" height="auto" />}

                <button className="saveBTN">Save</button>
            </form>
        </>
    );
}


