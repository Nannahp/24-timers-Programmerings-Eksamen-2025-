<script setup lang="ts">
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";

import { useRoute } from "vue-router";
const route = useRoute();

</script>

<template>
  <Header></Header>
  <div class="pt-[calc(93px+2rem)]">
    <RouterView :key="$route.fullPath" />
  </div>
  <Footer></Footer>
</template>


