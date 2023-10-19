import {ChangeEvent, FormEvent, useState} from "react";
import {Project} from "../assets/Types.tsx";
import axios from "axios";

export default function AddProject() {

    const [author, setAuthor] = useState<string>();
    const [text, setText] = useState<string>();


    function addTitle(title: ChangeEvent<HTMLInputElement>){
        setText(title.target.value);
    }
    function addAuthor(author: ChangeEvent<HTMLInputElement>){
        setAuthor(author.target.value);
    }
    function saveNewPoject(event: FormEvent<HTMLFormElement>){
        event.preventDefault();

        axios.post("/api/books",{
            author: author,
            description: text
        } as Project)
    }

    return (
        <>
            <h2>My new Project</h2>
            <form onSubmit={saveNewPoject}>
                <input value={author} onChange={addAuthor}/>
                <input value={text} onChange={addTitle}/>
                <button>Add New Project</button>
            </form>
        </>
    )
}