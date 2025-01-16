<script setup lang="ts">
import type { Drone } from "../models/Drone.ts";
import {fetchDrones} from "../api/DroneService.ts";
import {onMounted, ref} from "vue";



const drones = ref<Drone[]>([]);

onMounted(() => {
  const fetchData = async () => {
    try {
      drones.value = await fetchDrones();
    } catch (error) {
      console.error("Error fetching drones:", error);
    }
  };
  fetchData();
});
</script>

<template>

  <div>
    <ul class="drone-list grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
      <li v-for="drone in drones" :key="drone.id" class="drone-item border-2 p-4 bg-emerald-800 rounded-md">
        <p>Id: {{ drone.id }}</p>
        <p>UUID: {{ drone.serialUuid }}</p>
        <p>Station: {{ drone.station ? drone.station.id : 'No Station' }}</p>
        <p>Status: {{ drone.status }}</p>

      </li>
    </ul>
  </div>



</template>

<style scoped>

</style>