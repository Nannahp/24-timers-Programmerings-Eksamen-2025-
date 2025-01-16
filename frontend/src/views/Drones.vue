<script setup lang="ts">
import Button from "../components/BasicButton.vue";
import Drones from"../components/Drones.vue"
import CreateDrone from "../components/CreateDrone.vue";
//TODO - BUTTON FOR NEW DRONES
import {ref} from "vue";



</script>


<template>
  <div class="flex flex-row justify-between px-20">

    <div class="create-drones">
      <CreateDrone></CreateDrone>
    </div>
    <div class="drones">
      <Drones></Drones>
    </div>

  </div>
</template>

<style scoped>

</style>
