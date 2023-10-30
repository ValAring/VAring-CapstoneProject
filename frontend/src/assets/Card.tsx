import {Project} from "./Types.tsx";
import {useNavigate} from "react-router-dom";

type Props = {
    readonly project: Project
}

export default function Card( props: Props ) {

    const navigate = useNavigate();

    return (
        <button className="card" onClick={()=>navigate("/"+props.project.id)}>
            {/*{props.project.wips[0].images[0].length === 0 && ()}*/}
            <img src="/src/images/default-canvas.png" alt="default empty canvas" width="150px" height="150px"/>
        </button>
    )
}

