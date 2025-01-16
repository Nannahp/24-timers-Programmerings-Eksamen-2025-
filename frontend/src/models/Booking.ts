import {Team} from "./Team";
import {SwimmingLane} from "./SwimmingLane";

export interface Booking {
    id?: number;
    date: string;
    team: Team;
    swimmingLane: SwimmingLane;
}
