<script setup lang="ts">
import Navigation from "./Navigation.vue";
import Logo from "./Logo.vue"


</script>

<template>
  <header class=" flex flex-col p-8
                  absolute top-0 w-screen">

    <Navigation class="-mt-8 px-12">

    </Navigation>


    <!-- line  -->
    <div class=" flex flex-row justify-between px-6 -mt-8">
    <span class="w-4/12 h-[2px] bg-white mt-2 block flex self-center"></span>
    <span class="w-4/12 h-[2px] bg-white mt-2 block flex self-center"></span>
    </div>
  </header>
</template>