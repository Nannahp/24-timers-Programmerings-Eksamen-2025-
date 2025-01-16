<script setup lang="ts">
import Button from "../components/BasicButton.vue";
import Drones from"../components/Drones.vue"
//TODO - BUTTON FOR NEW DRONES
import {ref} from "vue";



</script>


<template>
  <div class="flex flex-row justify-between px-20">

    <div class="create-drones">
      <!--
      <Button @click="toggleForm" type="button" text="Create new pizza"/>
      <CreateProfile
          v-if="showForm"
          @close="toggleForm"
      />-->
    </div>
    <div class="profiles">
      <Drones></Drones>
    </div>

  </div>
</template>

<style scoped>

</style>
