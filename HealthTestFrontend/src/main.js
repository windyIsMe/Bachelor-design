/**
 * Created by gubaoer on 17/4/18.
 */

import Vue from 'vue'
import ElementUI from 'element-ui'
import App from './App.vue'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from './store/index';

Vue.use(ElementUI); 
Vue.use(VueAxios, axios)

// eslint-disable-next-line no-new
new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})
