import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";
import router from "./router";


// Create the Vue app and mount it with the router
createApp(App)
  .use(router)
  .mount("#app");
