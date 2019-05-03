<template>
    <div class="fillcontain">
        <div class="editTitle">
            <h2>编辑体检数据</h2>
        </div>
        <div>
            <el-row style="width: 100%">
                <el-col span="6">
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="姓名">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-input v-model="form.sex"></el-input>
                        </el-form-item>
                        <el-form-item label="年龄">
                            <el-input v-model="form.age"></el-input>
                        </el-form-item>
                        <el-form-item label="出生日期">
                            <el-input v-model="form.birthdate"></el-input>
                        </el-form-item>
                        <el-form-item label="部门">
                            <el-input v-model="form.department"></el-input>
                        </el-form-item>
                        <el-form-item label="肝胆胰脾">
                            <el-input type="textarea" v-model="form.liver"></el-input>
                        </el-form-item>
                        <el-form-item label="心电图">
                            <el-input type="textarea" v-model="form.electrocardiogram"></el-input>
                        </el-form-item>
                    </el-form>
                </el-col>
                <el-col span="6"> 
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="出生地">
                            <el-input v-model="form.place"></el-input>
                        </el-form-item>
                        <el-form-item label="色觉">
                            <el-input v-model="form.colorVision"></el-input>
                        </el-form-item>
                        <el-form-item label="右眼视力">
                            <el-input v-model="form.rightSight"></el-input>
                        </el-form-item>
                        <el-form-item label="左眼视力">
                            <el-input v-model="form.leftSight"></el-input>
                        </el-form-item>
                        <el-form-item label="鼻咽">
                            <el-input v-model="form.nose"></el-input>
                        </el-form-item>
                        <el-form-item label="甲状腺">
                            <el-input type="textarea" v-model="form.thyroid"></el-input>
                        </el-form-item>
                    </el-form>
                </el-col>
                <el-col span="6">
                    <el-form ref="form" :model="form" label-width="80px">
                        <el-form-item label="喉咙">
                            <el-input v-model="form.throat"></el-input>
                        </el-form-item>
                        <el-form-item label="身高">
                            <el-input v-model="form.height"></el-input>
                        </el-form-item>
                        <el-form-item label="体重">
                            <el-input v-model="form.weight"></el-input>
                        </el-form-item>
                        <el-form-item label="舒张压">
                            <el-input v-model="form.highPressure"></el-input>
                        </el-form-item>
                        <el-form-item label="收缩压">
                            <el-input v-model="form.lowPressure"></el-input>
                        </el-form-item>
                        <el-form-item label="肾脏">
                            <el-input type="textarea" v-model="form.bladder"></el-input>
                        </el-form-item>
                    </el-form>
                </el-col>
                <el-col span="6">
                    <el-form ref="form" :model="form" label-width="120px">
                        <el-form-item label="EB病毒VCA-IgA">
                            <el-input v-model="form.eb"></el-input>
                        </el-form-item>
                        <el-form-item label="骨密度平均值">
                            <el-input v-model="form.bone"></el-input>
                        </el-form-item>                      
                        <el-form-item label="白蛋白ALB">
                            <el-input v-model="form.alb"></el-input>
                        </el-form-item>
                        <el-form-item label="白细胞 (WBC)">
                            <el-input v-model="form.whiteCell"></el-input>
                        </el-form-item>
                        <el-form-item label="红细胞 (RBC)">
                            <el-input v-model="form.redCell"></el-input>
                        </el-form-item>
                        <el-form-item label="心肺">
                            <el-input type="textarea" v-model="form.dr"></el-input>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
        </div>
        <div class="editSubmit">
            <el-button @click="cancel">取消</el-button>
            <el-button type="primary" @click="submit">提交</el-button>
        </div>
    </div>
    
</template>

<script>
    import Bus from '../eventBus'

    export default {
        data() {
            return {
                form: {}
            }
        },
        created() {
            this.getDataById(this.$store.state.id);
        },
        methods: {
            getDataById(id) {
                this.axios.post('http://127.0.0.1:8081/getDataById', {
                    id: id
                }).then((response) => {     
                    console.log(response.data);        
                    this.form = response.data;   
                }).catch((error) => {
                    console.log(error);
                });
            },
            cancel() {
                this.$router.push('dataList');
            },
            submit() {
                this.axios.post('http://127.0.0.1:8081/updateDataById', {
                    form: this.form
                }).then((response) => {
                    console.log(response.data);
                    if (response.data > 0) {
                        this.$message({
                            type: 'success',
                            message: '修改成功'
                        });
                        this.$router.push('dataList');
                    } else {
                        this.$message({
                            type: 'error',
                            message: '编辑数据失败'
                        });
                    }
                }).catch((error) => {
                    console.log(error);
                }) 
            }
        }
    }
</script>

<style>
    .editTitle {
        text-align: center;
        margin-bottom: 40px;
    }
    .editSubmit {
        text-align: center;
    }
</style>