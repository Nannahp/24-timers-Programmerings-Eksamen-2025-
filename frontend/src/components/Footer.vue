<script setup lang="ts">


</script>

<template>


  <footer class="absolute bottom-0 w-screen flex flex-col justify-center">

    <!-- line  -->
    <span class="w-11/12 h-[2px] bg-white mt-2 block flex self-center"></span>

    <div class="flex flex-row justify-between p-8 px-20">

      <p>
        © {{ new Date().getFullYear() }} Nanna Hofgård
      </p>

      <!--links -->
      <div>
        <a
            href="https://github.com/Nannahp"
            target="_blank"
            rel="noopener noreferrer"
            class="logo-link flex items-center space-x-2  "
        >
          <img
              src="../assets/github-mark.png"
              alt="GitHub Logo"
              class="logo-img invert w-4 h-4 sm:w-8 sm:h-8"
          />
          <p class="logo-text">Nannahp</p>
        </a>
      </div>
    </div>
  </footer>
</template>

<style scoped>

</style>