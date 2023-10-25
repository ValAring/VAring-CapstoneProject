import '../App.css'
import {useNavigate} from "react-router-dom";
import {ChangeEvent, FormEvent, useState} from "react";
import {Project} from "./Types.tsx";
import axios from "axios";

type Props = {
    onItemChange: ()=> void
}
export default function AddEditProject(props: Props){
    const navigate = useNavigate();

    const [author, setAuthor] = useState<string>("");
    const [description, setDescription] = useState<string>("");

    function addAuthor(author: ChangeEvent<HTMLInputElement>){
        setAuthor(author.target.value);
    }
    function addDescription(description: ChangeEvent<HTMLTextAreaElement>){
        setDescription(description.target.value);
    }

    function saveNew(event: FormEvent<HTMLFormElement>){
        event.preventDefault();
        axios.post("/api",{
            author: author,
            description: description
        } as Project)
            .then(()=>{
                setAuthor("");
                setDescription("");
                props.onItemChange();
            })
        navigate("/");
    }

    return(
        <>
            <button className="backButton" onClick={()=>navigate("/")}>My Dashboard</button>
            <h2>Add a new Project</h2>

            <form onSubmit={saveNew}>
                <input name="author" value={author} onChange={addAuthor}/>
                <textarea name="description" value={description} onChange={addDescription}/>
                <button>Save</button>
            </form>
        </>
    )
}