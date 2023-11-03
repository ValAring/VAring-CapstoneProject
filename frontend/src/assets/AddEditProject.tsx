import '../App.css';
import {useNavigate} from "react-router-dom";
import {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import homeBTN from "/house.svg";

type Props = {
    readonly onItemChange: ()=> void
}
export default function AddEditProject(props: Props){
    const navigate = useNavigate();

    const [author, setAuthor] = useState<string>("");
    const [description, setDescription] = useState<string>("");
    const [imageUrl, setImageUrl] = useState<string>('');
    const [imageFile, setImageFile] = useState<File | null>(null)

    const newProject = {
        author: author,
        description: description
    }

    function addAuthor(author: ChangeEvent<HTMLInputElement>){
        setAuthor(author.target.value);
    }
    function addDescription(description: ChangeEvent<HTMLTextAreaElement>){
        setDescription(description.target.value);
    }

    function saveNew(event: FormEvent){
        event.preventDefault();

        const formData = new FormData();

        if (imageFile) {
            formData.append("file", imageFile);
        }
        formData.append("data", new Blob([JSON.stringify(newProject)], {type: 'application/json'}));

        axios.post("/api", formData,{
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then((response)=>{
                console.log(response.data);
                props.onItemChange();
            })
        navigate("/");
    }

    function handleFileUpload(event: ChangeEvent<HTMLInputElement>) {
        if (event.target.files && event.target.files.length > 0) {
            const selectedFile = event.target.files[0];

            if (selectedFile.type.startsWith("image/")) {

                // Resize the image before setting it in the state
                resizeImage(selectedFile, (resizedFile) => {
                    setImageFile(resizedFile);

                    const reader = new FileReader();
                    reader.onload = (e) => {
                        if (e.target) {
                            setImageUrl(e.target.result as string);
                        }
                    };
                    reader.readAsDataURL(resizedFile);
                });

                /*setImageFile(selectedFile);

                const reader = new FileReader();
                reader.onload = (e) => {
                    if (e.target) {
                        setImageUrl(e.target.result as string);
                    }
                };
                reader.readAsDataURL(selectedFile);*/
            } else {
                console.error("Selected file is not an image.");
            }
        } else {
            setImageUrl("");
        }
    }


    return(
        <>
            <button className="iconBTN" onClick={() => navigate("/")}><img src={homeBTN} alt="back home button" width="20px" height="20px"/></button>
            <h2>Add a new Project</h2>

            <form onSubmit={saveNew}>
                <input name="author" value={author} onChange={addAuthor}/>
                <textarea name="description" value={description} onChange={addDescription}/>

                <input type="file" onChange={handleFileUpload}/>
                {imageUrl && <img src={imageUrl} alt={author} width="150px" height="auto"/>}

                <button>Save</button>
            </form>
        </>
    )
}

function resizeImage(file, callback) {
    const reader = new FileReader();
    reader.onload = (e) => {
        const img = new Image();
        img.src = e.target.result as string;

        img.onload = () => {
            const canvas = document.createElement("canvas");
            const ctx = canvas.getContext("2d");

            // Calculate the desired dimensions (e.g., 200x200)
            const maxWidth = 800;
            const maxHeight = 800;
            let width = img.width;
            let height = img.height;

            if (width > height) {
                if (width > maxWidth) {
                    height *= maxWidth / width;
                    width = maxWidth;
                }
            } else {
                if (height > maxHeight) {
                    width *= maxHeight / height;
                    height = maxHeight;
                }
            }
            canvas.width = width;
            canvas.height = height;

            ctx.drawImage(img, 0, 0, width, height);

            // Convert the canvas back to a blob (JPEG format in this example)
            canvas.toBlob((blob) => {
                const resizedFile = new File([blob], file.name, { type: "image/jpeg" });
                callback(resizedFile);
            }, "image/jpeg");
        };
    };
    reader.readAsDataURL(file);
}
