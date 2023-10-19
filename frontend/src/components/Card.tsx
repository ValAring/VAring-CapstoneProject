import {Project} from "../assets/Types.tsx";

type Props = {
    project: Project
}

export default function BookCard( props: Props ) {

    return (
        <div>
            <div>author : {props.project.author }</div>
            <div>description : {props.project.description }</div>
        </div>
    )
}