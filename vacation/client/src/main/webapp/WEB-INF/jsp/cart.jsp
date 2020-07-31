<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>商城系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteOneProduct(obj,id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/client/deleteOneProduct",
                dataType:"json",
                type:"get",
                data:"cartId="+id,
                success:function(data){
                    //处理服务器响应的数据  data
                    if(data.flag){
                        var td_content = $(obj).parents("tr").children("td");
                        td_content.eq(2).text(data.quantity);
                        td_content.eq(3).text(data.amount)
                    }else {
                        alert("不能再减少了！")
                    }
                },
                error:function () {
                    alert("请先登录");
                    window.parent.location.href = "/user";
                }
            });
        }
        function addOneProduct(obj,id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/client/addOneProduct",
                dataType:"json",
                type:"get",
                data:"cartId="+id,
                success:function(data){
                    //处理服务器响应的数据  data
                    if(data.flag){
                        var td_content = $(obj).parents("tr").children("td");
                        td_content.eq(2).text(data.quantity);
                        td_content.eq(3).text(data.amount)
                    }else {
                        alert("库存不足")
                    }
                },
                error:function () {
                    alert("请先登录");
                    window.parent.location.href = "/user";
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">购物车列表</h3>
    <div class="menu">
        <a href="${pageContext.request.contextPath}/user/logOff"  class="item">注销</a>
    </div>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>品名 </th>
            <th>数量</th>
            <th>总价</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${carts}" var="cart" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${cart.productName}</td>
                <td>${cart.quantity}</td>
                <td>${cart.amount}</td>
                <td>
                    <button type="button"  id="deleteOne" onclick="deleteOneProduct(this,${cart.id})">-1</button>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/client/deleteProduct?cartId=${cart.id}">删除</a>&nbsp;
                    <button type="button"  id="addOne" onclick="addOneProduct(this,${cart.id})">+1</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/client/browse">返回</a></td>
        </tr>
    </table>
</div>
</body>
</html>
