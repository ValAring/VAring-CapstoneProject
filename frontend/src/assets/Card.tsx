import {Project} from "./Types.tsx";

type Props = {
    readonly project: Project
}

export default function Card( props: Props ) {

    return (
        <div className="card">
            <div>{props.project.author}</div>
            <div>{props.project.description}</div>
        </div>
    )
}