<script setup  lang="ts">

import {ref} from "vue";
import CreateDelivery from "../components/CreateDelivery.vue"
import FinishDelivery from "../components/FinishDelivery.vue"
import {Delivery} from "../models/Delivery.ts";

const delivery = ref<Delivery | null>(null);

const handleDeliveryCreated = (newDelivery: Delivery) => {
  delivery.value =newDelivery;
};
const handleDeliveryFinished = (deliveryId: number) => {
  delivery.value = null;
};

</script>

<template>
  <h1 class="mt-8">CUSTOMER SIMULATOR</h1>
  <div class="flex flex-row justify-between px-32 mt-6 ">
    <div class="justify-center items-center ">
    <CreateDelivery @delivery-created="handleDeliveryCreated" />
    </div>
    <div class="mr-28">
      <h3 class="font-bold text-xl py-6">Delivery:</h3>
      <FinishDelivery
          v-if="delivery"
          :delivery="delivery"
          @delivery-finished="handleDeliveryFinished"
      />
    </div>

  </div>
</template>

<!--template>
  <div class="grid grid-cols-2 h-screen">
<div class="flex justify-center items-center">
<CreateDelivery @delivery-created="handleDeliveryCreated" />
</div>

<div class="flex justify-center items-center">
<FinishDelivery :delivery="delivery" />
</div>
</div>
</template> -->

<style scoped>

</style>