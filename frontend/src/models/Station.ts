import { Drone } from "./Drone";

export interface Station {
    id?: number;
    longitude: number;
    latitude: number;
    drones: Drone[];
}
