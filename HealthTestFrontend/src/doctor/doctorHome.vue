<template>
	<div class="wrapper">
        <db-header></db-header>
		<el-row class="container">
	  		<el-col :span="4" class="menu">
				<el-menu :default-active="defaultActive" style="height: 100%;" theme="dark" router>
                    <el-menu-item index="dataList">体检数据信息</el-menu-item>
                    <el-menu-item index="getAppointment">体检预约审查</el-menu-item>
                    <el-menu-item index="sendAnnouncement">编辑公告</el-menu-item>
                    <el-menu-item index="changeDoctorPassword">修改密码</el-menu-item>		
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
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
        },
        components: {
            DbHeader
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
        background-color: #324057
    }
	.content {
		padding-top: 25px;
        padding-right: 25px;
        padding-bottom: 125px;
        padding-left: 25px;
	}

</style>


