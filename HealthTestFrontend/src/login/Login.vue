<template>
  	<div class="login_page fillcontain">
		<section class="form_contianer">
			<div class="manage_tip">
				<p>体检信息管理系统</p>
			</div>
			<el-form :model="loginForm" :rules="rules" ref="loginForm">
				<el-form-item prop="username" label="用户名">
					<el-input v-model="loginForm.username"></el-input>
				</el-form-item>
				<el-form-item prop="password" label="密码">
					<el-input type="password" v-model="loginForm.password"></el-input>
				</el-form-item>
				<el-form-item>
					<el-radio v-model="role" label="1">医生</el-radio>
  					<el-radio v-model="role" label="2">体检者</el-radio>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm('loginForm')" class="submit_btn">登陆</el-button>
				</el-form-item>
			</el-form>
		</section>
  	</div>
</template>

<script>
	export default {
	    data(){
			return {
				loginForm: {
					username: '',
					password: ''
				},
				rules: {
					username: [
			            { required: true, message: '请输入用户名', trigger: 'blur' },
			        ],
					password: [
						{ required: true, message: '请输入密码', trigger: 'blur' }
					],
				},
				role: 0
			}
		},
		methods: {
			async submitForm(formName) {
				console.log(this.role);
				this.$refs[formName].validate(async (valid) => {
					if (valid && this.role == 1) {
						this.axios.post('http://127.0.0.1:8081/checkLogin', {
                            actor: 'doctor',
							username: this.loginForm.username,
							password: this.loginForm.password
						})
						.then((response) => {
                            console.log(response.data);
							if (response.data !== '') {
								this.$message({
									type: 'success',
									message: '登录成功'
								});
                                this.$store.commit('setPassword', this.loginForm.password);
                                this.$store.commit('setId', response.data.id);
                                this.$store.commit('setName', response.data.name);
								this.$router.push('dataList');
							} else {
								this.$message({
									type: 'error',
									message: '用户名或密码输入错误'
								});
							}
						})
						.catch((error) => {
							console.log(error);
						});				
					} else if (valid && this.role == 2) {
						this.axios.post('http://127.0.0.1:8081/checkLogin', {
                            actor: 'examiner',
							username: this.loginForm.username,
							password: this.loginForm.password
						})
						.then((response) => {
							if (response.data !== '') {
								this.$message({
									type: 'success',
									message: '登录成功'
								});
								this.$store.commit('setId', response.data.id);
                                this.$store.commit('setPassword', this.loginForm.password);
								this.$router.push('personData');
							} else {
								this.$message({
									type: 'error',
									message: '用户名或密码输入错误'
								});
							}
						})
						.catch((error) => {
							console.log(error);
						});	
					} else {
						this.$notify.error({
							title: '错误',
							message: '用户名，密码和角色选择不能为空！',
							offset: 100
						});
						return false;
					}
				}); 
			},
		},
	}
</script>

<style lang="less" scoped>
	@import '../style/mixin';
	.login_page{
		background-color: #324057;
	}
	.manage_tip{
		position: absolute;
		width: 100%;
		top: -100px;
		left: 0;
		p{
			font-size: 34px;
			color: #fff;
		}
	}
	.form_contianer{
		.wh(350px, 300px);
		.ctp(350px, 300px);
		padding: 25px;
		border-radius: 5px;
		text-align: center;
		background-color: #fff;
		.submit_btn{
			width: 100%;
			font-size: 16px;
		}
	}

</style>