<script setup  lang="ts">


import {ref} from "vue";
import Button from "../components/BasicButton.vue";
import Deliveries from "../components/Deliveries.vue";



</script>

<template>
  <h1 class="mt-8">DELIVERIES</h1>
  <div class="flex flex-row justify-center px-20 py-12">

    <div class="deliveries">
      <Deliveries></Deliveries>
    </div>

  </div>
</template>

<style scoped>

</style>