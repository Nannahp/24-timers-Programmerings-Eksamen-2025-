<script setup lang="ts">
import { ref } from "vue";
import BasicButton from "./BasicButton.vue";

const props = defineProps<{
  toDelete: {
    toDeleteId: number;
    title: string;
  };
}>();

const emit = defineEmits(["close", "confirm-delete"]);
const isLoading = ref(false);

const confirmDelete = async () => {
  isLoading.value = true;

  try {
    emit("confirm-delete", props.toDelete.toDeleteId);
  } catch (error) {
    console.error("Error during deletion:", error);
  } finally {
    isLoading.value = false;
  }
};
</script>

<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
    <div class="bg-black bg-opacity-80 p-6 shadow-lg text-center w-96 h-auto mx-auto">
      <h3 class="text-[#EAEAEA] text-4xl font-bold text-center mb-8">Confirm Delete</h3>
      <p class="text-[#EAEAEA] text-l text-center mb-2">Are you sure you want to delete:</p>
      <p class="text-[#EAEAEA] text-xl text-center font-bold mb-7">"{{ props.toDelete.title }}" with id: "{{ props.toDelete.toDeleteId }}"?</p>



      <div class="mt-6 flex justify-center gap-4">
        <!-- Cancel Button -->
        <BasicButton
            text="Cancel"
            @click="$emit('close')"
            class="bg-gray-400 opacity-90 text-white rounded-md py-2 px-4 w-full"
        >
        </BasicButton>

        <!-- Delete Button -->
        <BasicButton
            :loading="isLoading"
            @click="confirmDelete"
            text="Delete"
            class="bg-red-700 opacity-90 text-white rounded-md py-2 px-4 w-full"
        >
        </BasicButton>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Add additional styles if necessary */
</style>
