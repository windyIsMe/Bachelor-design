import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
    id: 0, 
    password: '',
    name: '刘流'
}

const mutations = {
    setId(state, id) {
        state.id = id;
        window.sessionStorage.setItem('id', id);
    },
    setPassword(state, password) {
        state.password = password;
        window.sessionStorage.setItem('password', password);
    },
    setName(state, name) {
        state.name = name;
        window.sessionStorage.setItem('name', name);
    }
 };

export default new Vuex.Store({
	state,
	mutations
})