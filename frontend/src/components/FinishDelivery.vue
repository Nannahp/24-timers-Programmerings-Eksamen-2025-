<script setup lang="ts">

import { ref, onMounted, onUnmounted, watch } from "vue";
import type { Delivery } from "../models/Delivery";
import { finishDeliverySim } from "../api/DeliveryService";

const props = defineProps<{
  delivery: Delivery | null;
}>();


const emit = defineEmits(['delivery-finished']);


const removalTimeout = ref<number | null>(null);


const finishDelivery = async (deliveryId: number) => {
  try {
    const updatedDelivery = await finishDeliverySim(deliveryId);
    props.delivery.actualArrival = updatedDelivery.actualArrival;

    removalTimeout.value = setTimeout(() => {
      emit('delivery-finished', deliveryId);
    }, 5000);
  } catch (error) {
    console.error("Error finishing delivery:", error);
  }
};


onUnmounted(() => {
  if (removalTimeout.value) {
    clearTimeout(removalTimeout.value);
  }
});

</script>

<template>
  <div class="delivery-item border-2 p-4 bg-amber-500 rounded-md" v-if="props.delivery">
    <p>Id: {{ props.delivery.id }}</p>
    <p>Est. Arrival: {{ props.delivery.expectedArrival }}</p>
    <p v-if="props.delivery.actualArrival">
      Actual Arrival: {{ props.delivery.actualArrival }}
    </p>
    <p>Pizza: {{props.delivery.pizza.title}}</p>


    <!-- Finish Delivery Button -->
    <button
        v-if="delivery.drone && !delivery.actualArrival"
        @click="finishDelivery(delivery.id)"
        class="mt-2 text-white bg-green-500 hover:bg-green-700 px-4 py-2 rounded"
    >
      Delivery Received
    </button>
  </div>
</template>

<style scoped>
.delivery-item {
  transition: opacity 0.5s ease;
}
</style>
