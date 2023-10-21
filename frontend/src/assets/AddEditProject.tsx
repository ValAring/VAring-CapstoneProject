import '../App.css'
import {useNavigate} from "react-router-dom";

export default function AddEditProject(){
    const navigate = useNavigate();


    return(
        <>
            <button className="backButton" onClick={()=>navigate("/")}>My Dashboard</button>
            <h2>Add a new Project</h2>

        </>
    )
}