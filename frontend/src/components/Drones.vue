<script setup lang="ts">
import type { Drone } from "../models/Drone.ts";
import {deleteProfile, fetchDrones} from "../api/DroneService.ts";
import {onMounted, ref} from "vue";
import DeleteConfirmation from "./BasicDeleteConfirmation.vue";


const drones = ref<Drone[]>([]);

//const showDeleteForm = ref(false);
//const selectedProfile = ref<Drone | null>(null);
/*
const handleDeleteClick = (drone: Drone) => {
  selectedProfile.value = drone;
  showDeleteForm.value = true;
};
*/

/*
const confirmDelete = async (profileId: number) => {
  try {

    await deleteProfile(profileId);
    // Remove the deleted pizza from the list
    profiles.value = profiles.value.filter(pizza => pizza.id !== profileId);
    showDeleteForm.value = false;
  } catch (error) {
    console.error("Error during deletion:", error);
  }
};
*/
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

  <!-- Delete Confirmation Modal -->
  <!--
  <DeleteConfirmation
      v-if="showDeleteForm"
      :toDelete="{ toDeleteId: selectedProfile?.id, title: selectedProfile?.firstName + ' ' + selectedProfile?.lastName }"
      @close="showDeleteForm = false"
      @confirm-delete="confirmDelete"
  />
  -->
  <div>
    <ul class="drone-list grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
      <li v-for="drone in drones" :key="drone.id" class="drone-item border-2 p-4 bg-emerald-800 rounded-md">
        <p>Id: {{ drone.id }}</p>
        <p>UUID: {{ drone.serialUuid }}</p>
        <p>Station: {{ drone.station ? drone.station.id : 'No Station' }}</p>
        <p>Status: {{ drone.status }}</p>
        <!--
        <li v-for="delivery in drone.deliveries" :key="delivery.id" class="drone-item border-2 p-4 bg-amber-500 rounded-md">
          <p>Delivery Id: {{ delivery.id }}</p>
        </li>
-->
        <!-- Edit and Delete Icons -->
          <!--
        <button @click="handleDeleteClick(drone)" class="delete-icon mt-2 text-red-500 hover:text-red-700">
          <i class="fas fa-trash-alt"></i> Delete
        </button>
          -->
      </li>
    </ul>
  </div>



</template>

<style scoped>

</style>