<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>
<div id="app">
    <a-table :columns="columns" :data-source="userList"/>
</div>
</body>
<#include "base.ftl">
<script>
    Vue.createApp({
        data() {
            return {
                columns: [
                    {
                        title: '序号',
                        dataIndex: 'index',
                    },
                    {
                        title: '用户名',
                        dataIndex: 'username',
                    },
                    {
                        title: '密码',
                        dataIndex: 'password',
                    },
                    {
                        title: '手机号码',
                        dataIndex: 'phone',
                    }
                ],
                userList: []
            }
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
            }
        }
    }).mount('#app')
</script>
</html>