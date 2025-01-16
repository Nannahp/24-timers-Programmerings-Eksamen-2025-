<script setup lang="ts">
import BasicForm from "./BasicForm.vue";
import BasicInput from "./BasicInput.vue";
import BasicButton from "./BasicButton.vue";
import {addDrone} from "../api/DroneService.ts";

const emit = defineEmits<{
  (event: "drone-created"): void;
}>();

const createDrone = async () => {
    await addDrone();
  emit("drone-created");
}

</script>

<template >
    <BasicButton type="button" text="Add Drone" @click="createDrone" />
</template>

<style scoped>

</style>
