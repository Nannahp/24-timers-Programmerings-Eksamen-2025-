<script setup lang="ts">
import Button from "../components/BasicButton.vue";
import CreateProfile from "../components/CreateProfile.vue";
import Profiles from "../components/Profiles.vue";
import {ref} from "vue";

const showForm = ref(false);

const toggleForm = () => {
  showForm.value = !showForm.value;
};

</script>


<template>
  <div class="flex flex-row justify-between px-20">

    <div class="create-profiles">
      <Button @click="toggleForm" type="button" text="Create new pizza"/>
      <CreateProfile
          v-if="showForm"
          @close="toggleForm"
      />
    </div>
    <div class="profiles">
      <Profiles></Profiles>
    </div>

  </div>
</template>

<style scoped>

</style>
