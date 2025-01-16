export enum Role {
    SWIMMER = "SWIMMER",
    COACH = "COACH"
}

export interface Profile {
    id?: number;
    firstName: string;
    lastName: string;
    age: number;
    role: Role; 
}
