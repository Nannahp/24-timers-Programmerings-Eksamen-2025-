<script setup lang="ts">
import BasicForm from "./BasicForm.vue";
import BasicInput from "./BasicInput.vue";
import BasicButton from "./BasicButton.vue";
import type  {Profile} from "../models/Profile";
import  { Role } from "../models/Profile";
import  { createProfile } from "../api/ProfileService";
import {ref} from "vue";

//pizza that is being created
const pizza = ref<Profile>({
  firstName: "",
  lastName: "",
  age: 0,
  status: Role.SWIMMER,
});

// List of roles for the dropdown
const roles = Object.values(Role);

const submitProfile = async () => {
  try {
    const createdProfile = await createProfile(pizza.value);
    console.log("Profile created successfully:", createdProfile);

    pizza.value = { firstName: "", lastName: "", age: 0 , status: Role.SWIMMER,};
  } catch (error) {
    console.error("Error creating pizza:", error);
  }
};
</script>

<template>
<BasicForm width="w-11/12" title="Create Profile " @submit.prevent="submitProfile">
  <div class=" flex flex-col">
    <BasicInput
      v-model="pizza.firstName"
      label="First name"
      name="firstName"
      type="text"
      placeholder="first name"
      errorMessage="This field is required"
      :isError="pizza.firstName.length === 0"
    />
    <BasicInput
        v-model="pizza.lastName"
        label="Last name"
        name="lastName"
        type="text"
        placeholder="last name"
        errorMessage="This field is required"
        :isError="pizza.lastName.length === 0"
    />
    <BasicInput
        v-model="pizza.age"
        label="age"
        name="age"
        type="number"
        placeholder="fx: 26"
        errorMessage="This field is required"
        :isError="pizza.age === 0"
    />

    <div>
      <label for="status" class="block text-sm font-medium text-gray-700">Role</label>
      <select
          id="status"
          v-model="pizza.status"
          class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
      >
        <option v-for="status in roles" :key="status" :value="status">
          {{ status }}
        </option>
      </select>
    </div>
  </div>
  <BasicButton type="button" text="submit" @click="submitProfile" >

  </BasicButton>
</BasicForm>
</template>

<style scoped>

</style>