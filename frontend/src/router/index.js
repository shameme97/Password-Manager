import Vue from 'vue'
import VueRouter from 'vue-router';
import VueAxios from 'vue-axios';
import axios from 'axios';
import NProgress from 'nprogress';
import VueCryptojs from 'vue-cryptojs'

import LoginComponent from "../components/login.vue"
import SecureComponent from "../components/secure.vue"

Vue.use(VueRouter)
Vue.use(VueAxios, axios);
Vue.use(VueCryptojs)


export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: {
                name: "login"
            }
        },
        {
            path: "/login",
            name: "login",
            component: LoginComponent
        },
        {
            path: "/secure",
            name: "secure",
            component: SecureComponent
        }
    ]
})
