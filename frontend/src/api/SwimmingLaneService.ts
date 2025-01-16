import axios from "axios";
import { SwimmingLane } from "../models/SwimmingLane";

const BASE_URL = "http://127.0.0.1:8080/api";

export const fetchSwimmingLanes= async (): Promise<SwimmingLane[]> => {
    const response = await axios.get(`${BASE_URL}/swimming-lanes`);
    return response.data;
};


