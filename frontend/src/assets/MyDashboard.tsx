import {Project} from "./Types.tsx";
import Card from "./Card.tsx";

type Props = {
    readonly project: Project[]
}

export default function MyDashboard( props: Props ) {

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