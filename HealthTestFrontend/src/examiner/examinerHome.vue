<template>
	<div class="wrapper">
        <db-header></db-header>
		<el-row class="container">
	  		<el-col :span="4" class="menu">
				<el-menu :default-active="defaultActive" style="height: 100%;" theme="dark" router>
                    <el-menu-item index="personData">个人体检信息</el-menu-item>
                    <el-menu-item index="sendAppointment">预约体检</el-menu-item>
                    <el-menu-item index="getAnnouncement">公告栏</el-menu-item>
                    <el-menu-item index="changeExaminerPassword">修改密码</el-menu-item>		
				</el-menu>
			</el-col>
			<el-col :span="20" class="content">
				<router-view></router-view>
			</el-col>
		</el-row>
  	</div>
</template>

<script>
    import DbHeader from '../components/DbHeader.vue'
    export default {
        components: {
            DbHeader
        },
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
        },
        data() {
            return {
                id: -1,
            }
        }
    }
</script>

<style>
	.wrapper {
		height: 100%;
        position: relative;
    }
	.container {
		height: 100%;
        padding-top: 70px;
        flex: 1;
    }
	.menu {
        height: 100%;
        background-color: #324057;
    }
	.content {
		padding-top: 25px;
        padding-right: 50px;
        padding-bottom: 125px;
        padding-left: 50px;
	}

</style>


