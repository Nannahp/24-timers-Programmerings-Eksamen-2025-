<script setup lang="ts">

import {onMounted, ref} from "vue";
import type {Booking} from "../models/Booking";
import {fetchBookings} from "../api/BookingService";

const deliveries = ref<Booking[]>([]);
onMounted(() => {
  const fetchData = async () => {
    try {
      deliveries.value = await fetchBookings();
    } catch (error) {
      console.error("Error fetching deliveries:", error);
    }
  };
  fetchData();
});
</script>

<template>
  <div>
    <ul class="deliveries-list flex flex-col">
      <li v-for="delivery in deliveries" :key="delivery.id" class="border-2">
        <p> {{ delivery.team }}</p>
        <p> {{ delivery.drone }}</p>
        <p> {{ delivery.date }}</p>

      </li>
    </ul>
  </div>
</template>

<style scoped>

</style>