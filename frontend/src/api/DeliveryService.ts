import axios from "axios";
import { Delivery } from "../models/Delivery.ts";
import {Drone} from "../models/Drone.ts";
import {toRaw} from "vue";

const BASE_URL = "http://127.0.0.1:8080/deliveries";

export const fetchDeliveries= async (): Promise<Delivery[]> => {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
};
export const addDroneToDelivery = async (deliveryId: number) => {
    const response = await axios.post(`${BASE_URL}/schedule?deliveryId=${deliveryId}`);
    return response.data;
};

export const finishDeliverySim = async (deliveryId: number) => {
    const response = await axios.post(`${BASE_URL}/finish?deliveryId=${deliveryId}`);
    return response.data;
};

export const createDeliverySim = async (payload: { pizzaId: number; address: string }) => {
    console.log(payload);
    const response = await axios.post(`${BASE_URL}/add`, payload, {
        headers: { "Content-Type": "application/json" },
    });
    return response.data;
};




