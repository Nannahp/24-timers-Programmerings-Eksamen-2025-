import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: '/',  // Ensure the base URL is correctly set for production
  proxy: {
    '/api': 'http://localhost:8080', // Adjust the backend URL accordingly
  }

})
