import {Project} from "../assets/Types.tsx";
import Card from "./Card.tsx";

type Props = {
    project: Project[]
}

export default function MyProjectsList( props: Props ) {

    return (
        <div className="projectsList">
            {
                props.project.map(project =>
                    <Card key={project.id} project={project}/>
                )
            }
        </div>
    )
}