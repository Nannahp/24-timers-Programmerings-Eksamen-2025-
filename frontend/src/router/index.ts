import { createRouter, createWebHistory } from "vue-router";
import HomePage from '../views/HomePage.vue'
import Deliveries from '../views/Deliveries.vue'
import Drones from '../views/Drones.vue'
import Simulator from '../views/Simulator.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "homepage",
      component: HomePage,
    },
    {
      path: "/deliveries",
      name: "/deliveries",
      component: Deliveries,
    },
    {
      path: "/drones",
      name: "/drones",
      component: Drones,
    },
    {
      path: "/simulator",
      name: "/simulator",
      component: Simulator,
    }
  ],
});


export default router;
