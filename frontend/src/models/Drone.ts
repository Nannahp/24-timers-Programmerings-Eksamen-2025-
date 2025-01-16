import { Delivery } from "./Delivery";
import { Station } from "./Station";
import { Status } from "./Status";

export interface Drone {
    id?: number;
    serialUuid: string;
    deliveries: Delivery[];
    station: Station;
    status: Status;
}
