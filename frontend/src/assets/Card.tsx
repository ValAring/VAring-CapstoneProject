import {Project} from "./Types.tsx";
import {useNavigate} from "react-router-dom";
import defaultCanvas from '/default-canvas.png';

type Props = {
    readonly project: Project
}

export default function Card( props: Props ) {

    const navigate = useNavigate();

    return (
        <button className="card" onClick={()=>navigate("/"+props.project.id)}>
            <img src={(props.project.imageURL === null) ? defaultCanvas : props.project.imageURL} alt="default empty canvas" width="150px" height="150px"/>
        </button>
    )
}

