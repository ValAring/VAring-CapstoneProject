import '../App.css';
import { useNavigate, useParams } from "react-router-dom";
import { ChangeEvent, FormEvent, useState } from "react";
import axios from "axios";
import homeBTN from "/house.svg";
import { Project } from './Types';

type Props = {
    project: Project[]
    readonly onItemChange: ()=> void
}
export default function EditProject(props: Props) {
    const { id } = useParams();
    const navigate = useNavigate();

    const filteredProject:Project[] = props.project.filter(e => e.id === id);

    const [author, setAuthor] = useState<string>(filteredProject[0].author);
    const [description, setDescription] = useState<string>(filteredProject[0].description);
    const [imageUrl, setImageUrl] = useState<string>(filteredProject[0].imageURL);
    const [imageFile, setImageFile] = useState<File | null>(null);

    const newProject = {
        author: author,
        description: description
    };

    function addAuthor(author: ChangeEvent<HTMLInputElement>) {
        setAuthor(author.target.value);
    }

    function addDescription(description: ChangeEvent<HTMLTextAreaElement>) {
        setDescription(description.target.value);
    }

    function updateProject(event: FormEvent) {
        event.preventDefault();

        const formData = new FormData();

        if (imageFile) {
            formData.append("file", imageFile);
        }
        formData.append("data", new Blob([JSON.stringify(newProject)], { type: 'application/json' }));

        axios.put(`/api/${id}`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then((response) => {
                console.log(response.data);
                props.onItemChange();
            });

        navigate("/"+id);
    }

    function handleFileUpload(event: ChangeEvent<HTMLInputElement>) {
        if (event.target.files && event.target.files.length > 0) {
            const selectedFile = event.target.files[0];

            if (selectedFile.type.startsWith("image/")) {
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

            } else {
                console.error("Selected file is not an image.");
            }
        } else {
            setImageUrl("");
        }
    }

    function resizeImage(file: File, callback: (resizedFile: File) => void) {
        const reader = new FileReader();

        reader.onload = (e: ProgressEvent<FileReader>) => {
            if (e.target) {
                const img = new Image();
                img.src = e.target.result as string;

                img.onload = () => {
                    const canvas = document.createElement("canvas");
                    const ctx = canvas.getContext("2d");

                    if (ctx) {
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

                        canvas.toBlob((blob) => {
                            if (blob) {
                                const resizedFile = new File([blob], file.name, { type: "image/jpeg" });
                                callback(resizedFile);
                            } else {
                                console.error("Error converting canvas to blob");
                            }
                        }, "image/jpeg");
                    } else {
                        console.error("Canvas context is null");
                    }
                };
            } else {
                console.error("FileReader's target is null");
            }
        };
        reader.readAsDataURL(file);
    }

    return (
        <>
            <button className="iconBTN" onClick={() => navigate("/")}><img src={homeBTN} alt="back home button" width="20px" height="20px" /></button>
            <h2>Edit Project</h2>

            <form onSubmit={updateProject}>
                <input name="author" value={author} onChange={addAuthor} />
                <textarea name="description" value={description} onChange={addDescription} />

                <input type="file" onChange={handleFileUpload} />
                {imageUrl && <img src={imageUrl} alt={author} width="150px" height="auto" />}

                <button className="saveBTN">Save</button>
            </form>
        </>
    );
}


