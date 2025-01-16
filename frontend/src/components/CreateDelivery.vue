<script setup lang="ts">
import BasicForm from "./BasicForm.vue";
import BasicInput from "./BasicInput.vue";
import BasicButton from "./BasicButton.vue";
import {onMounted, ref} from "vue";
import {addDroneToDelivery, createDeliverySim} from "../api/DeliveryService";
import {fetchPizzas} from "../api/PizzaService";
import {Delivery} from "../models/Delivery.ts";

const delivery = ref({
  address: "",
  pizza: null,
});

const pizzas = ref([]);


const getPizzas = async () => {
  try {
    pizzas.value = await fetchPizzas();
  } catch (error) {
    console.error("Error fetching pizzas:", error);
  }
};

const emit = defineEmits<{
  (event: "delivery-created", newDelivery: Delivery): void;
}>();

const addDrone = async (deliveryId: number) => {
    const updatedDelivery = await addDroneToDelivery(deliveryId);
  emit("delivery-created", updatedDelivery);
}
const createDelivery = async () => {
  if (!delivery.value.address || !delivery.value.pizza) {
    console.error("Address and Pizza must be selected.");
    return;
  }

  try {
    const payload = { pizzaId: delivery.value.pizza.id, address: delivery.value.address };
    console.log("Payload being sent to backend:", payload);

    const newDelivery = await createDeliverySim(payload);
    await addDrone(newDelivery.id);

    delivery.value = {
      address: "",
      pizza: null,
    };
  } catch (error) {
    console.error("Error creating delivery:", error);
  }
};

onMounted(() => {
  getPizzas();
});
</script>

<template >
  <BasicForm width="w-full" title="Create Delivery" @submit.prevent="createDelivery" >
    <div class="flex flex-col space-y-4">

      <BasicInput
          v-model="delivery.address"
          label="Delivery Address:"
          name="address"
          type="text"
          placeholder="Enter delivery address"
          errorMessage="This field is required"
          :isError="!delivery.address"


      />


      <div class="p-6">
        <label for="pizza" class="block text-sm font- text-gray-700">Pizza:</label>
        <select
            id="pizza"
            v-model="delivery.pizza"
            class="mt-2 text-gray-900 block bg-gray-50 w-full rounded-md shadow-sm
border-2 w-48 h-8"
        >
          <option  disabled value=""></option>
          <option class="text-gray-900" v-for="pizza in pizzas" :key="pizza.id" :value="pizza">
            {{ pizza.title }} - {{ pizza.price }} DKK
          </option>
        </select>
      </div>
    </div>


    <BasicButton type="button" text="Submit" @click="createDelivery" />
  </BasicForm>
</template>

<style scoped>

</style>
