<script setup  lang="ts">

import {ref} from "vue";
import Button from "../components/BasicButton.vue";
import CreateBooking from "../components/CreateBooking.vue";
import Bookings from "../components/Bookings.vue";

const showForm = ref(false);

const toggleForm = () => {
  showForm.value = !showForm.value;
};

</script>

<template>
  <div class="flex flex-row justify-between px-20">

    <div class="create-delivery">
      <Button @click="toggleForm" type="button" text="Create Booking"/>
      <CreateBooking
          v-if="showForm"
          @close="toggleForm"
      />
    </div>
    <div class="deliveries">
      <Bookings></Bookings>
    </div>

  </div>
</template>

<style scoped>

</style>