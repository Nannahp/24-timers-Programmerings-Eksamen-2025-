<script setup lang="ts">
import {useRouter} from 'vue-router'

const router = useRouter();

//helper-function to highlight what nav-menu is active
// hash is used if there is a sections or a form within a view
const isActive = (path: string, hash?: string) => {
  const currentRoute = router.currentRoute.value;
  if (hash) {
    return currentRoute.path === path && currentRoute.hash === hash;
  }
  return currentRoute.path === path && ! currentRoute.hash;
};


</script>

<template>
<nav>
  <!-- Nav Links -->
  <div>
    <router-link to="/simulator"
                 :class="{
                 'text-white': isActive('/simulator'),
                 'hover:text-gray-700': true,
                 'px-20': true
               }">
      SIMULATOR
    </router-link>
    <router-link to="/deliveries"
                 :class="{
                 'text-white': isActive('/deliveries'),
                 'hover:text-gray-700': true,
                 'px-20': true
               }">
      DELIVERIES
    </router-link>
    <router-link to="/drones"
                 :class="{
                 'text-white': isActive('/drones'),
                 'hover:text-gray-700': true,
                 'px-20': false
               }">
      DRONES
    </router-link>
  </div>
</nav>
</template>

<style scoped>

</style>