<template>
    <div>
        <el-table :data="tableData" height="1000" border style="width: 100%" class="table">
            <el-table-column prop="content" label="公告内容"  header-align="center" width="800"></el-table-column>
            <el-table-column prop="name" label="发布者" align="center"></el-table-column>
            <el-table-column prop="date" label="发布时间" align="center"></el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [],
            }
        },
        mounted() {
            this.getAnnounces();
        },
        methods: {
            getAnnounces() {
                this.axios.post('http://127.0.0.1:8081/getAnnounces'
                ).then((response) => {
                    console.log(response.data);
                    let dataList = response.data;
                    for (let key in dataList) {
                        dataList[key].content = dataList[key].content;
                        this.format(dataList[key]);
                    }
                    this.tableData = dataList;
                }).catch((error) => {
                    console.log(error);
                })  
            },
            format(data) {
                data.date = data.date.substr(0, 10);
            },
        },
    }
</script>

<style>
    .table {

    }
</style>