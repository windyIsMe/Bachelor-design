<template>
    <div>
        <el-col :span="7">
            <div style="color:white">1</div>
        </el-col>
        <el-col :span="8">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">
                <el-form-item label="旧密码" prop="oldPassword">
                    <el-input type="password" v-model="ruleForm.oldPassword" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                    <el-input type="password" v-model="ruleForm.newPassword" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPassword">
                    <el-input type="password" v-model="ruleForm.checkPassword" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>      
            </el-form>
        </el-col>
    </div>
</template>


<script>
  export default {
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入旧密码'));
        } else if (value !== this.$store.state.password) {
          console.log(this.$store.state.password);
          callback(new Error('密码不正确'));
        } else {
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入新密码'));
        } else if (value !== this.ruleForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
            oldPassword: '',
            newPassword: '',
            checkPassword: '',
        },
        rules: {
          oldPassword: [
            { required: true, validator: validatePass, trigger: 'blur' },
          ],
          newPassword: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
          ],
          checkPassword: [
            { required: true, validator: validatePass2, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('http://127.0.0.1:8081/updateDoctorPassword', {
                id: this.$store.state.id,
                password: this.ruleForm.newPassword
            }).then((response) => {
                console.log(response.data);
                if (response.data > 0) {
                    this.$message({
                        type: 'success',
                        message: '修改成功'
                    });
                    this.$store.commit('setPassword', this.ruleForm.newPassword);
                } else {
                    this.$message({
                        type: 'error',
                        message: '修改失败'
                    });
                }
            }).catch((error) => {
                console.log(error);
            }) 
          } else {
            this.$notify.error({
                title: '错误',
                message: '提交失败！',
                offset: 100
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<Style>
    h1 {
        color: white;
    }
    .form {
        margin-left: 100px;
    }
</Style>