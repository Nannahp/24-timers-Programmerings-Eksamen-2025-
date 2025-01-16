<script setup lang="ts">
import BasicForm from "./BasicForm.vue";
import BasicInput from "./BasicInput.vue";
import BasicButton from "./BasicButton.vue";
import { ref, onMounted } from "vue";
import {checkIfBookingExist, createBooking, fetchBookings} from "../api/BookingService";
import { Team } from "../models/Team";
import { SwimmingLane } from "../models/SwimmingLane";
import type { Booking } from "../models/Booking";
import {fetchTeams} from "../api/TeamService";
import {fetchSwimmingLanes} from "../api/SwimmingLaneService";

// Profile and Booking details
const delivery = ref<Booking>({
  date: "",
  team: null,
  drone: null,
});

// Lists for dropdowns
const teams = ref<Team[]>([]);
const swimmingLanes = ref<SwimmingLane[]>([]);

// Function to fetch the list of teams and swimming lanes
const getTeamsAndLanes = async () => {
  try {
    teams.value = await fetchTeams();
    swimmingLanes.value = await fetchSwimmingLanes();
  } catch (error) {
    console.error("Error fetching teams:", error);
  }
};

onMounted(()=> {
  getTeamsAndLanes();
});

const checkLaneAvailability = async () => {
  if (!delivery.value.date || !delivery.value.drone) return false;

  // Tjek tilgængeligheden af svømmebanen for den valgte dato og lane
  const booked = await checkIfBookingExist(delivery.value.date, delivery.value.drone.id);

  if (!booked) {
    console.log("Lane is available for delivery.");
  } else {
    console.log("Lane is already booked for this date.");
  }
  return booked;
};

const submitBooking = async () => {
  const isBooked = await checkLaneAvailability();

  if (isBooked) {
    return;
  }

  try {
    const createdBooking = await createBooking(delivery.value);
    console.log("Booking created successfully:", createdBooking);
    delivery.value = { date: "", team: null, drone: null }; // Clear the form
  } catch (error) {
    console.error("Error creating Booking:", error);
  }
};

</script>

<template>
      <BasicForm width="w-11/12" title="Create Booking " @submit.prevent="submitBooking">
        <div class="flex flex-col">
          <BasicInput
              v-model="delivery.date"
              label="Date"
              name="date"
              type="date"
              placeholder="Select date"
              errorMessage="This field is required"
              :isError="!delivery.date"
          />

          <div>
            <label for="team" class="block text-sm font-medium text-gray-700">Team</label>
            <select
                id="team"
                v-model="delivery.team"
                class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            >
              <option v-for="team in teams" :key="team.id" :value="team">{{ team.name }}</option>
            </select>
          </div>

          <div>
            <label for="drone" class="block text-sm font-medium text-gray-700">Swimming Lane</label>
            <select
                id="drone"
                v-model="delivery.drone"
                class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            >
              <option v-for="drone in swimmingLanes" :key="drone.id" :value="drone">{{ drone.laneNumber }}</option>
            </select>
          </div>
        </div>
    <BasicButton type="button" text="submit" @click="submitBooking" >
    </BasicButton>
  </BasicForm>
</template>

<style scoped>

</style>