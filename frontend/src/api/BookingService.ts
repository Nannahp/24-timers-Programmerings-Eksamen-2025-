import axios from "axios";
import { Booking } from "../models/Booking";
import {Profile} from "../models/Profile.ts";
import {toRaw} from "vue";

const BASE_URL = "http://127.0.0.1:8080/api";

export const fetchBookings= async (): Promise<Booking[]> => {
    const response = await axios.get(`${BASE_URL}/bookings`);
    return response.data;
};


export const createBooking = async (booking: Booking): Promise<Booking> => {
    try {
        console.log(booking)
        const rawBooking = toRaw(booking);

        const response = await axios.post(
            `${BASE_URL}/bookings`,
            rawBooking,
            { headers: { 'Content-Type': 'application/json' } }
        );
        return response.data;
    } catch (error) {
        console.error("Error creating booking:", error);
        throw error;  // Sørg for at fejlen bliver kastet videre
    }
};
/*
export const createBooking = async (booking: Booking) => {
    const response = await fetch("/api/bookings", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(booking),
    });

    if (!response.ok) {
        throw new Error("Failed to create booking");
    }
    return response.json();
};

*/
export const checkIfBookingExist = async (date: string, laneId: number): Promise<boolean> => {
    try {
        const response = await axios.get(`${BASE_URL}/check-booking-date`, {
            params: { date, laneId }
        });
        console.log(response.data)
        return response.data;
    } catch (error) {
        console.error("Error checking booking:", error);
        return false;
    }
};

