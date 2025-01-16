import axios from "axios";
import { Team } from "../models/Team";

const BASE_URL = "http://127.0.0.1:8080/api";

export const fetchTeams= async (): Promise<Team[]> => {
    const response = await axios.get(`${BASE_URL}/teams`);
    return response.data;
};


