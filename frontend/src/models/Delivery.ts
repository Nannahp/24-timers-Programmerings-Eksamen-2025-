import { Pizza } from "./Pizza";
import { Drone } from "./Drone";

export interface Delivery {
    id?: number;
    expectedArrival: string;
    actualArrival?: string;
    formattedExpectedArrival: string;
    formattedActualArrival: string;
    pizza: Pizza;
    drone?: Drone;
    address: string;
}
