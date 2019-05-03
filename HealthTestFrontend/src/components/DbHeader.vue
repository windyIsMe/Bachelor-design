<template>
    <header style="background-color: rgb(10, 47, 88);">
        <div class="text-logo">{{msg}}</div>
        <div class="quit">
            <el-link @click="handleQuit()">退出登录</el-link>
        </div>
        <div class="meta"></div>
    </header>
</template>

<script>
    export default {
        name: 'db-header',
        data () {
            return {
                msg: '体检数据管理系统'
            }
        },
        methods: {
            handleQuit() {
                this.$router.push('/');
            }
        },
    }
</script>

<style>
    header {
        height: 70px;
        position: absolute;
        width: 100%;
        top: 0;
        left: 0;
        padding: 0 20px;
        z-index: 1;
        box-sizing: border-box;
    }

    .text-logo {
        float: left;
        margin-top: 20px;
        font-size: 25px;
        color: white;
    }

    .quit {
        float: right;
        margin-top: 25px;
        font-size: 15px;
        color: white;
    }

    .meta {
        color: #7e95c5;
        width: 200px;
        display: block;
        margin: -5px 0 0 225px;
        font-weight: 700;
        font-size: 0.3rem;
    }
</style>