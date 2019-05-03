<template>
    <div class="fillcontain">
        <el-form :inline='true'>
            <el-form-item label="预约日期" required>
                <el-date-picker
                    v-model="selectedDate"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit()">申请预约</el-button>
            </el-form-item>
        </el-form> 
        <el-table :data="tableData" class="table">
            <el-table-column prop="id" label="申请序号"></el-table-column>
            <el-table-column prop="name" label="申请人"></el-table-column>
            <el-table-column prop="sendDate" label="申请日期"></el-table-column>
            <el-table-column prop="appointDate" label="预约日期"></el-table-column>
            <el-table-column prop="result" label="申请状态"></el-table-column>
            <el-table-column fixed="right" label="操作">
                <template scope="scope">
                    <el-button size="mini" type="danger" @click="handleCancel(scope.$index, scope.row)">撤销</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                selectedDate: null,
                tableData: []
            }
        },
        mounted() {
            this.getAppointsById();
        },
        methods: {
            onSubmit() {
                this.selectedDate.setHours(16);
                console.log(this.selectedDate);
                this.addAppoint();
            },
            addAppoint() {
                this.axios.post('http://127.0.0.1:8081/addAppoint', {
                    id: this.$store.state.id,
                    date: this.selectedDate
                })
                .then((response) => {
                    let affectedRow = response.data;
                    if (affectedRow > 0) {
                        this.$message({
                            type: 'success',
                            message: '申请发送成功'
                        });
                        this.getAppointsById();
                    } else {
                        this.$message({
                            type: 'error',
                            message: '申请发送失败'
                        });
                    }
                })
                .catch((error) => {
                    console.log(error);
                });	
            },
            getAppointsById() {
                this.axios.post('http://127.0.0.1:8081/getAppointsById', {
                    id: this.$store.state.id
                })
                .then((response) => {
                    //this.tableData = response.data;
                    let dataList = response.data;
                    for (let key in dataList) {
                        this.format(dataList[key]);
                    }
                    this.tableData = dataList;
                })
                .catch((error) => {
                    console.log(error);
                });	
            },
            getAppointsById() {
                this.axios.post('http://127.0.0.1:8081/getAppointsById', {
                    id: this.$store.state.id
                })
                .then((response) => {
                    let dataList = response.data;
                    for (let key in dataList) {
                        this.format(dataList[key]);
                    }
                    this.tableData = dataList;
                })
                .catch((error) => {
                    console.log(error);
                });	
            },
            format(data) {
                data.sendDate = data.sendDate.substr(0, 10);
                data.appointDate = data.appointDate.substr(0, 10);
            },
            handleCancel(index, row) {
                this.axios.post('http://127.0.0.1:8081/deleteAppoint', {
                    id: row.id
                }).then((response) => {
                    if (response.data > 0) {
                        this.$message({
                            type: 'success',
                            message: '撤销成功'
                        });
                        this.getAppointsById();
                    } else {
                        this.$message({
                            type: 'error',
                            message: '撤销失败'
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
    .table {
        margin-top: 50px;
    }
</style>

