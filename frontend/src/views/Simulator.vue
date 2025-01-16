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
  <div class="flex flex-row ">
    <div class="justify-center items-center ">
    <CreateDelivery @delivery-created="handleDeliveryCreated" />
    </div>
    <div>
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