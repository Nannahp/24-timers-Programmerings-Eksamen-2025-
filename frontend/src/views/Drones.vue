<script setup lang="ts">
import Button from "../components/BasicButton.vue";
import Drones from"../components/Drones.vue"
import CreateDrone from "../components/CreateDrone.vue";

import {ref} from "vue";



</script>

<template>
  <h1 class="mt-8 text-center">DRONES</h1>
  <!-- Wrapper takes up full viewport width -->
  <div class="flex justify-center items-center px-4 py-12 w-full">
    <div class="flex flex-row w-full">
      <div class="create-drones w-1/2">
        <h2 class="italic"> click for drone!</h2>
        <i class="fa fa-share"></i>
        <CreateDrone></CreateDrone>
      </div>
      <div class="drones w-1/2">
        <Drones></Drones>
      </div>
    </div>
  </div>
</template>
<style scoped>

</style>
