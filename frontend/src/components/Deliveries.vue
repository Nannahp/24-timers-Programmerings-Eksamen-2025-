<script setup lang="ts">

import {onMounted, onUnmounted, ref} from "vue";
import type {Delivery} from "../models/Delivery.ts";
import {fetchDeliveries} from "../api/DeliveryService";
import {addDroneToDelivery} from "../api/DeliveryService.ts"


const deliveries = ref<Delivery[]>([]);

const addDrone = async (deliveryId: number) => {
  try {
    const updatedDelivery = await addDroneToDelivery(deliveryId);
    const delivery = deliveries.value.find(d => d.id === deliveryId);
    if (delivery) {
      delivery.drone = updatedDelivery.drone;
    }
  }
  catch (error) {
      console.error("Error adding drone to delivery:", error);
    }
  }

let intervalId: number | undefined;

onMounted(() => {
  const fetchData = async () => {
    try {
      deliveries.value = await fetchDeliveries();
    } catch (error) {
      console.error("Error fetching deliveries:", error);
    }
  };
  fetchData();
  intervalId = setInterval(() => {
    fetchData();
  }, 60000);
});
onUnmounted(() => {
  if (intervalId) {
    clearInterval(intervalId);
  }
});
</script>

<template>

  <div>
    <ul class="delivery-list grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
      <li v-for="delivery in deliveries" :key="delivery.id" class="drone-item border-2 p-4 bg-amber-500 rounded-md">
        <p>Id: {{ delivery.id }}</p>
        <p>Pizza: {{ delivery.pizza.title }}</p>
        <p>Est. Arrival: {{ delivery.formattedExpectedArrival  }}</p>
        <p>Drone: {{ delivery.drone ? delivery.drone.id : 'No Drone Assigned' }}</p>

        <!-- Add Drone -->
      <button v-if="!delivery.drone"  @click="addDrone(delivery.id)" class="delete-icon mt-2 text-white hover:text-red-700">
         Add Drone
      </button>

      </li>
    </ul>
  </div>
</template>

<style scoped>

</style>