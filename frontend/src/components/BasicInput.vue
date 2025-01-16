<script  setup lang="ts">

import {toRef, toRefs} from "vue";

const props = defineProps<{
  label: string;
  name: string;
  modelValue: string | number;
  type?: string;
  placeholder?: string;
  width?: string;
  errorMessage?: string;
  isError?: boolean;
}>();


const emit = defineEmits<{
  (event:'update:modelValue', value: string | number): void;
}>();

const handleInput = (event: Event) => {
  emit('update:modelValue', (event.target as HTMLInputElement).value);
}
</script>

<template>
<div :style="{width: width || '100%'}"
class="flex flex-col ">
  <label class="text-gray-900" :for="props.name">{{ props.label }}</label>
<input
  :id="props.name"
  :name="props.name"
  :type="props.type || 'text'"
  :value="props.modelValue"
  :placeholder="props.placeholder"
  @input="handleInput"
  :class="{'border-red-500': isError}"
  class="p-2 border rounded-md text-gray-900"
  />
  <p v-if="props.isError" class="text-red-500 text-sm">{{ errorMessage }}</p>

</div>
</template>

<style scoped>
/* For Webkit browsers (Chrome, Safari, Edge, etc.) */
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>