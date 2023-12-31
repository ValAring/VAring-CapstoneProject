import {Project} from "./Types.tsx";
import Card from "./Card.tsx";
import {useNavigate} from "react-router-dom";
import plusBTN from "/plus.svg";

type Props = {
    readonly project: Project[]
}

export default function MyDashboard( props: Props ) {
    const navigate = useNavigate();

    return (
        <>
            <h2>My Projects</h2>
            <div className="projectsList">
                <button className="addButton" onClick={()=>navigate("/addProject")}>
                    <img src={plusBTN} alt="add Project button" width="40px" height="40px"/>
                    <br/>Add New Project
                </button>
                {
                    props.project.map(project =>
                        <Card key={project.id} project={project}/>
                    )
                }
            </div>
        </>
    )
}