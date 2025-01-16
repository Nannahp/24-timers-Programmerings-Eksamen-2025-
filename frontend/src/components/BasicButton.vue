<script setup lang="ts">

const props = defineProps<{
  type?: string;
  text: string
}>();
</script>

<template>
  <button :type="props.type || 'button'"
  class="bg-green-800 opacity-90 rounded-md">
    {{ props.text }}
  </button>
</template>

<style scoped>

</style>