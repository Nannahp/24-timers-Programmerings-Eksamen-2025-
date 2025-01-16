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

const exitForm = () => {
  emit("close");
};

const handleFormSubmit = (event: Event) => {
  event.preventDefault();
  if (!props.action) {
    emit('submit');
  }
}
</script>

<template>
  <!-- Form layout -->
  <div class="flex flex-col items-center "
       :style="{ width: computedWidth }">
    <Button
        @click="$emit('close')"
        text="X"/>
    <h2>{{ props.title }}</h2>
    <!-- form functions -->
    <form
        :action="computedAction"
        :method="computedMethod"
        @submit="handleFormSubmit"
    >
      <!-- Insert context from parent component -->
      <slot></slot>

    </form>
  </div>
</template>

<style scoped>

</style>