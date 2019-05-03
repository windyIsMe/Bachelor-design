<template>
    <div class="fillcontain">
        <el-form :inline="true" :model="formInline">
            <el-form-item label="部门">
                <el-select v-model="formInline.department" clearable>
                    <el-option
                        v-for="item in type_options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="formInline.name"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="search">查询</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="tableData" class="table">
            <el-table-column prop="id" label="序号"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="sex" label="性别"></el-table-column>
            <el-table-column prop="age" label="年龄"></el-table-column>
            <el-table-column prop="department" label="部门"></el-table-column>
            <el-table-column prop="birthdate" label="出生年月"></el-table-column>
            <el-table-column fixed="right" label="操作" width="250">
                <template scope="scope">
                    <el-button size="mini" @click="handleCheck(scope.$index, scope.row)">查看</el-button>
                    <el-button size="mini" type="Success" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
        data(){
            return {
                tableData: [],
                apiUrl: 'http://127.0.0.1:8081/users/findAllUsers',
                total: 24,
                pageSize: 7,
                currentPage: 1,
                type_options: [{
                    value: 1,
                    label: '人力资源部'
                }, {
                    value: 2,
                    label: '调度部'
                }, {
                    value: 3,
                    label: '办公室'
                }, {
                    value: 4,
                    label: '运行方式部'
                }, {
                    value: 5,
                    label: '通信管理部'
                }, {
                    value: 6,
                    label: '继电保护部'
                }],
                formInline: {
                    department: '',
                    name: ''
                }
            }
        },
        mounted () {
            this.getDatas();
        },
        methods: {
            getDatas() {
                this.axios.post('http://127.0.0.1:8081/getDatasByDepartAndName', {
                    department: this.formInline.department,
                    name: this.formInline.name,
                    pageSize: this.pageSize,
                    currentPage: this.currentPage
                }).then((response) => {
                    console.log(response.data);
                    this.tableData = response.data;           
                }).catch((error) => {
                    console.log(error);
                });
            },
            search() {
                this.currentPage = 1;
                this.getDatas();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getDatas();
            },
            handleCheck(index, row) {
                this.$store.commit('setId', row.id);
                this.$router.push('healthData');
            },
            handleEdit(index, row) {
                this.$store.commit('setId', row.id);
                this.$router.push('editData');
            },
            handleDelete(index, row) {
                this.axios.post('http://127.0.0.1:8081/deleteDataById', {
                    id: row.id
                }).then((response) => {
                    if (response.data > 0) {
                        this.$message({
                            type: 'success',
                            message: '删除成功'
                        });
                        this.getDatas();
                    } else {
                        this.$message({
                            type: 'error',
                            message: '删除失败'
                        });
                    }
                }).catch((error) => {
                    console.log(error);
                }) 
            },
        }
    }
</script>

<style>
    .table {
        width: 100%;
        margin-top: 30px;
    }

    .pagination {
        margin-top: 10px;
        float: right;
    }

</style>