import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from 'axios'
Vue.config.productionTip = false;
Vue.prototype.$axios = axios    //全局注册，使用方法为:this.$axios

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
