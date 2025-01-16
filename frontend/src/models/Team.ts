import {Profile} from "./Profile";

export interface Team {
    id?: number;
    name: string;
    coach?: Profile;
    members: Profile[];
}
