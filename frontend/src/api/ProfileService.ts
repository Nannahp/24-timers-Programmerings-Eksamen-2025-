import axios from "axios";
import { Profile } from "../models/Profile";

const BASE_URL = "http://127.0.0.1:8080/api";

export const fetchProfiles = async (): Promise<Profile[]> => {
    const response = await axios.get(`${BASE_URL}/profiles`);
    return response.data;
};

export const createProfile = async (profile: Profile): Promise<Profile> => {
    const response = await axios.post(`${BASE_URL}/profiles`, profile);
    return response.data;
};

export const deleteProfile = async (id: number) => {
        const response = await axios.delete(`${BASE_URL}/delete-profile/${id}`);
        return response.data;
};
export const updateProfile = async (profile: Profile) => {
    const response = await axios.post(`${BASE_URL}/edit-profile/${profile.id}`, profile);
    return response.data;
};
