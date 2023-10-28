export type Project = {
    id: string,
    author: string,
    description: string,
    wips: Wip[],
    timeCreated: string,
    lastEdited: string
}

export type Wip = {
    id: string,
    wipText: string,
    images: string[]
}