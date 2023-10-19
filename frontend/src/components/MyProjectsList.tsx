import {Project} from "../assets/Types.tsx";
import Card from "./Card.tsx";

type Props = {
    project: Project[]
}

export default function MyProjectsList( props: Props ) {

    return (
        <div className="BookList">
            {
                props.project.map(project =>
                    <Card project={project}/>
                )
            }
        </div>
    )
}