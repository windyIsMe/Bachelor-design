<template>
    <div class="fillcontain">
        <el-table :data="tableData" class="table">
            <el-table-column prop="id" label="申请序号"></el-table-column>
            <el-table-column prop="name" label="申请人姓名"></el-table-column>
            <el-table-column prop="sendDate" label="申请日期"></el-table-column>
            <el-table-column prop="appointDate" label="预约日期"></el-table-column>
            <el-table-column prop="result" label="申请状态"></el-table-column>
            <el-table-column fixed="right" label="操作">
                <template scope="scope">
                    <el-button size="mini" type="success" @click="handlePass(scope.$index, scope.row)">通过</el-button>
                    <el-button size="mini" type="danger" @click="handleFail(scope.$index, scope.row)">拒绝</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination 
            class="pagination" 
            layout="prev, pager, next" 
            :total="total" 
            :page-size="pageSize"
            :current-page="currentPage" 
            @current-change="handleCurrentChange">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [],
                total: 32,
                pageSize: 8,
                currentPage: 1,
            }
        },
        mounted() {
            this.getAppoints();
        },
        methods: {
            getAppoints() {
                this.axios.post('http://127.0.0.1:8081/getAppoints', {
                    pageSize: this.pageSize,
                    currentPage: this.currentPage
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
            updateAppoint(id, result) {
                this.axios.post('http://127.0.0.1:8081/updateAppoint', {
                    id: id,
                    result: result
                }).then((response) => {
                    console.log(response.data);
                    if (response.data > 0) {
                        console.log('通过');
                    } else {
                        this.$message({
                            type: 'error',
                            message: '操作失败'
                        });
                    }
                }).catch((error) => {
                    console.log(error);
                }) 
            },
            format(data) {
                data.sendDate = data.sendDate.substr(0, 10);
                data.appointDate = data.appointDate.substr(0, 10);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getAppoints();
            },
            handlePass(index, row) {
                console.log(row.id);
                this.updateAppoint(row.id, '已通过');
                this.tableData[index].result = '已通过';
            },
            handleFail(index, row) {
                console.log(row.id);
                this.updateAppoint(row.id, '未通过');
                this.tableData[index].result = '未通过';
            }
        },
    }
</script>

<style>
    .table {
        margin-top: 50px;
    }
</style>

