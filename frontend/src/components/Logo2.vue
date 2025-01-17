<script setup lang="ts">

//if logo is clicked, redirect to homepage
function handleClick() {
  window.location.href = "/";
}

const props = defineProps<{
  width?: string;
  height?: string;
  fill?: string;
  opacity?: number;
}>();

// Compute the opacity of the logo
const computedOpacity = !props.opacity  ? props.opacity : 1;
</script>

<template>
  <button @click="handleClick" class="bg-transparent">
    <img src="../assets/pizza-removebg-preview.png">
  </button>
</template>