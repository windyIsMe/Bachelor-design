<template>
    <div id="app" class="fillcontain">
		<router-view></router-view>
    </div>
</template>

<script>
    export default {
    	mounted() {
            let id = window.sessionStorage.getItem('id');
            this.$store.commit('setId', id);
            let password = window.sessionStorage.getItem('password');
            this.$store.commit('setPassword', password);
            let name = window.sessionStorage.getItem('name');
            this.$store.commit('setName', name);
        },
    }
</script>

<style lang="less">
	@import './style/common';
</style>
