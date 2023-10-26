import {Project} from "./Types.tsx";
import Card from "./Card.tsx";
import {useNavigate} from "react-router-dom";

type Props = {
    readonly project: Project[]
}

export default function MyDashboard( props: Props ) {
    const navigate = useNavigate();

    return (
        <div className="projectsList">
            <button className="addButton" onClick={()=>navigate("/addProject")}>+ <br/>Add New Project</button>
            {
                props.project.map(project =>
                    <Card key={project.id} project={project}/>
                )
            }
        </div>
    )
}