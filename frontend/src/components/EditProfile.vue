<script setup lang="ts">
import { ref, watch } from "vue";
import type { Profile } from "../models/Profile";
import { Role } from "../models/Profile";
import BasicForm from "./BasicForm.vue";
import BasicInput from "./BasicInput.vue";
import BasicButton from "./BasicButton.vue";
import {updateProfile} from "../api/ProfileService";



const props = defineProps<{
  profileToEdit: Profile | null;
}>();
const pizza = ref<Profile>(null);
const emit = defineEmits(["close", "confirm-edit"]);
// Watch for changes in props.profileToEdit and update the local pizza object
watch(
    () => props.profileToEdit,
    (newProfile) => {
      pizza.value = newProfile ? { ...newProfile } : null;
    },
    { immediate: true }
);

const confirmEdit = async () => {
  try {
    emit("confirm-edit", pizza.value);
  } catch (error) {
    console.error("Error updating pizza:", error);
  }
};
</script>

<template>
  <div class="fixed inset-0 flex items-center justify-center bg-opacity-50 bg-gray-900 z-50">
    <BasicForm  title="Edit Profile"  @close="emit('close')" class="w-96">
      <div class="flex flex-col">
        <!-- First Name -->
        <BasicInput
            v-model="pizza.firstName"
            label="First Name"
            name="firstName"
            type="text"
            placeholder="First Name"
        />
        <!-- Last Name -->
        <BasicInput
            v-model="pizza.lastName"
            label="Last Name"
            name="lastName"
            type="text"
            placeholder="Last Name"
        />
        <!-- Age -->
        <BasicInput
            v-model="pizza.age"
            label="Age"
            name="age"
            type="number"
            placeholder="Age"
        />
        <!-- Role -->
        <div>
          <label for="status" class="block text-sm font-medium text-gray-700">Role</label>
          <select
              id="status"
              v-model="pizza.status"
              class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          >
            <option v-for="status in Object.values(Role)" :key="status" :value="status">
              {{ status }}
            </option>
          </select>
        </div>
      </div>
      <BasicButton type="button"  text="Save Changes" @click="confirmEdit" />
    </BasicForm>
  </div>
</template>

<style scoped>
/* Center the modal with a background overlay */
.fixed {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.bg-opacity-50 {
  background-color: rgba(0, 0, 0, 0.5);
}

.z-50 {
  z-index: 50; /* Make sure it sits above other content */
}

.w-96 {
  width: 24rem; /* You can adjust the width as needed */
}
</style>
