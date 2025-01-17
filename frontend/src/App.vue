<script setup lang="ts">
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";
import Logo2 from"./components/Logo2.vue"
import { useRoute } from "vue-router";

const route = useRoute();

</script>

<template>
  <Logo2 class=" fixed w-56 h-48 right-[45em] -top-6"></Logo2>
  <Header></Header>
  <div class="pt-[calc(93px+2rem)]">
    <RouterView :key="$route.fullPath" />
  </div>
  <Footer></Footer>
</template>


