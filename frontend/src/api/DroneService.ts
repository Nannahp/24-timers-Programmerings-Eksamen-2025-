import axios from "axios";
import { Drone } from "../models/Drone.ts";

const BASE_URL = "http://127.0.0.1:8080/drones";

export const fetchDrones = async (): Promise<Drone[]> => {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
};



