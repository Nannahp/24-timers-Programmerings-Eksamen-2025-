<script setup lang="ts">
import Navigation from "./Navigation.vue";
import Logo from "./Logo.vue"


</script>

<template>
  <header class=" flex flex-col p-4
                  absolute top-0 w-screen">

    <div class="flex flex-row justify-between items-center px-6 ">
      <Logo></Logo>

      <div>
        <Navigation></Navigation>
      </div>
    </div>

    <!-- line  -->
    <div class=" flex flex-row justify-between px-6">
    <span class="w-4/12 h-[2px] bg-white mt-2 block flex self-center"></span>
    <span class="w-4/12 h-[2px] bg-white mt-2 block flex self-center"></span>
    </div>
  </header>
</template>