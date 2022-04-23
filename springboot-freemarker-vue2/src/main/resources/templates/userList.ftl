<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>
<div id="app">
    <el-table
            :data="userList"
            :row-class-name="tableRowClassName"
            style="width: 100%">
        <el-table-column
                type="index"
                width="50">
        </el-table-column>
        <el-table-column
                prop="username"
                label="用户名">
        </el-table-column>
        <el-table-column
                prop="password"
                label="密码">
        </el-table-column>
        <el-table-column
                prop="phone"
                label="手机号码">
        </el-table-column>
    </el-table>
</div>
</body>
<#include "base.ftl">
<script>
    new Vue({
        el: '#app',
        data: {
            userList: []
        },
        created() {
            this.getList()
        },
        methods: {
            getList() {
                axios.get("/user/selectList").then(res => {
                    const {data} = res.data
                    this.userList = data
                })
            },
            tableRowClassName({row, rowIndex}) {
                if (rowIndex === 1) {
                    return 'warning-row';
                } else if (rowIndex === 3) {
                    return 'success-row';
                }
                return '';
            }
        }
    });
</script>
<style>
    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
</style>
</html>