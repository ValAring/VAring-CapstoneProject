export type Project = {
    id: string,
    author: string,
    description: string,
    imageURL: string,
    timeCreated: string,
    lastEdited: string
}

export type Wip = {
    id: string,
    wipText: string,
    images: string[],
    timeCreated: string,
    lastEdited: string
}