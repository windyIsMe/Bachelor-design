<template>
    <div>
        <div class="healthTitle">
            <h2>体检数据报告</h2>
        </div>
        <div class="healthTable">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column prop="sex" label="性别"></el-table-column>
                <el-table-column prop="age" label="年龄"></el-table-column>
                <el-table-column prop="birthdate" label="出生日期"></el-table-column>
                <el-table-column prop="department" label="部门"></el-table-column>
            </el-table>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="place" label="出生地"></el-table-column>
                <el-table-column prop="colorVision" label="色觉"></el-table-column>
                <el-table-column prop="rightSight" label="右眼视力"></el-table-column>
                <el-table-column prop="leftSight" label="左眼视力"></el-table-column>
                <el-table-column prop="nose" label="鼻咽"></el-table-column>            
            </el-table>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="throat" label="喉咙"></el-table-column>
                <el-table-column prop="height" label="身高"></el-table-column>
                <el-table-column prop="weight" label="体重"></el-table-column>
                <el-table-column prop="highPressure" label="舒张压"></el-table-column>
                <el-table-column prop="lowPressure" label="收缩压"></el-table-column>            
            </el-table>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="bone" label="骨密度平均值"></el-table-column>
                <el-table-column prop="eb" label="EB病毒VCA-IgA"></el-table-column>
                <el-table-column prop="alb" label="白蛋白ALB"></el-table-column>
                <el-table-column prop="whiteCell" label="白细胞 (WBC)"></el-table-column>
                <el-table-column prop="redCell" label="红细胞 (RBC)"></el-table-column>            
            </el-table>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="liver" label="肝胆胰脾"></el-table-column>
                <el-table-column prop="thyroid" label="甲状腺"></el-table-column>
                <el-table-column prop="bladder" label="肾脏"></el-table-column>
                <el-table-column prop="dr" label="心肺"></el-table-column>
                <el-table-column prop="electrocardiogram" label="心电图"></el-table-column>            
            </el-table>
        </div>
    </div>
    
</template>

<script>
    export default {
        data() {
            return {
                tableData: []
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
                    this.tableData.push(response.data);   
                }).catch((error) => {
                    console.log(error);
                });
            }
        }
    }
</script>

<style>
    .healthTitle {
        text-align: center;
        margin-bottom: 20px;
    }
    .healthTable {
        margin-left: 60px;
        margin-right: 30px;
    }
    .healthButton {
        margin-top: 30px;
        text-align: center;
    }
</style>
