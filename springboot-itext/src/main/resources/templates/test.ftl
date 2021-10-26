<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>测试模板</title>
    <style>
        body {
            font-family: SimHei, serif;
        }
    </style>
</head>
<body>
<div>
    <h3>1. 字符串渲染</h3>
    title：${title}
</div>
<div>
    <h3>2. if判断</h3>
    num: ${num}  <#if num?number gt 0>大于0<#else >小于0</#if>
</div>
<div>
    <h3>3. 集合遍历</h3>
    <#if list??>
        <#list list as l>
            <li>${l}</li>
        </#list>
    </#if>
</div>
</body>
</html>