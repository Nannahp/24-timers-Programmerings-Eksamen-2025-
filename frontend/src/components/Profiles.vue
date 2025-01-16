<script setup lang="ts">
import type { Profile } from "../models/Profile";
import {deleteProfile, fetchProfiles, updateProfile} from "../api/ProfileService";
import {onMounted, ref} from "vue";
import DeleteConfirmation from "./BasicDeleteConfirmation.vue";
import EditProfile from "./EditProfile.vue";

const profiles = ref<Profile[]>([]);
const showEditForm = ref(false);
const showDeleteForm = ref(false);
const selectedProfile = ref<Profile | null>(null);


const handleEditClick = (pizza: Profile) => {
  selectedProfile.value = pizza;
  showEditForm.value = true;
};
const closeEditForm = ()=> {
  showEditForm.value = false;
}

const handleDeleteClick = (pizza: Profile) => {
  selectedProfile.value = pizza;
  showDeleteForm.value = true;
};


const submitEditForm = async (updatedProfile: Profile) => {
  try {
    const response = await updateProfile(updatedProfile);
    console.log("Profile updated successfully:", response);

    // Update the local list of profiles
    const index = profiles.value.findIndex(pizza => pizza.id === updatedProfile.id);
    if (index !== -1) profiles.value[index] = { ...updatedProfile };

    closeEditForm();
  } catch (error) {
    console.error("Error updating pizza:", error);
  }
};

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

onMounted(() => {
  const fetchData = async () => {
    try {
      profiles.value = await fetchProfiles();
    } catch (error) {
      console.error("Error fetching profiles:", error);
    }
  };
  fetchData();
});
</script>

<template>

  <!-- Edit Profile Form -->
  <EditProfile
      v-if="showEditForm"
      :profileToEdit="selectedProfile"
      @confirm-edit="submitEditForm"
      @close="closeEditForm"
      class="absolute"
  />
  <!-- Delete Confirmation Modal -->
  <DeleteConfirmation
      v-if="showDeleteForm"
      :toDelete="{ toDeleteId: selectedProfile?.id, title: selectedProfile?.firstName + ' ' + selectedProfile?.lastName }"
      @close="showDeleteForm = false"
      @confirm-delete="confirmDelete"
  />
  <div>
    <ul class="pizza-list grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
      <li v-for="pizza in profiles" :key="pizza.id" class="pizza-item border-2 p-4 bg-amber-500 rounded-md">
        <p>{{ pizza.firstName }}</p>
        <p>{{ pizza.lastName }}</p>
        <p>{{ pizza.age }}</p>
        <p>{{ pizza.status }}</p>

        <!-- Edit and Delete Icons -->
        <button @click="handleEditClick(pizza)" class="edit-icon mt-2 text-blue-500 hover:text-blue-700">
          <i class="fas fa-edit"></i> Edit
        </button>
        <button @click="handleDeleteClick(pizza)" class="delete-icon mt-2 text-red-500 hover:text-red-700">
          <i class="fas fa-trash-alt"></i> Delete
        </button>
      </li>
    </ul>
  </div>



</template>

<style scoped>

</style>