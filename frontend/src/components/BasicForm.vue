<script setup lang="ts">
import Button from "./BasicButton.vue";
import {computed, ref} from "vue";
import BasicButton from "./BasicButton.vue";

const props = defineProps<{
  title: string;
  width?: string;
  action?: string;
  method?: string;
}>();

const computedWidth = computed(() => props.width || '100%');
const computedAction = computed(() => props.action || '/');
const computedMethod = computed(() => props.method || 'POST');

const emit = defineEmits<{
  (event: "submit"): void;
  (event: "close"): void;
}>();

const handleFormSubmit = (event: Event) => {
  event.preventDefault();
  if (!props.action) {
    emit('submit');
  }
}
</script>

<template>

  <div class="flex flex-col items-center bg-white p-12 rounded-md "
       :style="{ width: computedWidth }">

    <h2 class="text-gray-900 font-bold text-2xl p-4">{{ props.title }}</h2>

    <form
        :action="computedAction"
        :method="computedMethod"
        @submit="handleFormSubmit"
    >

      <slot></slot>

    </form>
  </div>
</template>

<style scoped>

</style>