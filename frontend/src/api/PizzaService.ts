import axios from "axios";
import { Pizza } from "../models/Pizza.ts";

const BASE_URL = "http://127.0.0.1:8080/pizzas";

export const fetchPizzas = async (): Promise<Pizza[]> => {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
};