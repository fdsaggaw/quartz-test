<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>商品列表</title>
    <link rel="stylesheet" type="text/css" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/search.css">
    <script src="../../js/jquery-2.1.0.min.js"></script>
    <script>
        function addToCart(id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/client/addToCart",
                dataType:"json",
                type:"get",
                data:"productId="+id,
                success:function(data){
                    //处理服务器响应的数据  data
                    if(data.flag){
                        //发送成功
                        alert("添加成功");
                    }
                },
                error:function (data) {
                    alert("请先登录");
                    window.parent.location.href = "/user";
                }
            });
        }
    </script>

</head>
<body>
<!--引入头部-->
<div class="page_one">
    <div class="contant">
        <p style="text-align: right;font-weight: bold;font-size: 23px"><a href="${pageContext.request.contextPath}/client/findAll">购物车</a></p>
            <div class="menu">
                <a href="${pageContext.request.contextPath}/user/logOff"  class="item">注销</a>
            </div>
        <div class="crumbs">
            <p style="font-weight:bold;color: orangered">猜你喜欢</p>
        </div>
        <div class="xinxi clearfix">
            <div class="left">
                <div class="header">
                    <span>商品信息</span>
                    <span class="jg">价格</span>
                </div>
                <div>${message}</div>
                <ul>
                    <c:forEach items="${pageProduct.list}" var="product" begin="0" varStatus="stusts">
                    <li>
                        <div class="img"><img src="../../image/${product.image}" alt="" style="width:250px;height:200px;"></div>
                        <div class="text1">
                            <p>${product.name}</p>
                            <br/>
                            <p style="font-weight:bold;color: red">${product.description}</p>
                        </div>
                        <div class="price">
                            <p class="price_num">
                                <span>&yen;</span>
                                <span>${product.price}</span>
                                <span>起</span>
                            </p>
                            <p><button type="button"  id="add" onclick="addToCart(${product.id})">加入购物车</button></p>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
                <div class="page_num_inf">
                    <i></i> 共
                    <span>${pageProduct.pages}</span>页<span>${pageProduct.total}</span>条
                </div>
                <div style="text-align: center;font-size: 15px;" id="p">
                    <a href=${pageContext.request.contextPath}/client/browse?pageNum=1>首页</a>
                    <c:choose>
                        <c:when test="${pageProduct.hasPreviousPage}">
                            <a href=${pageContext.request.contextPath}/client/browse?pageNum=${pageProduct.prePage}>上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a href="#">上一页</a>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach var="p" begin="1" end="${pageProduct.pages }">
                        <c:choose>
                            <c:when test="${pageProduct.pageNum==p}">
                                <a href=${pageContext.request.contextPath}/client/browse?pageNum=${p} style="text-decoration: underline">${p}</a>
                            </c:when>
                            <c:otherwise>
                                <a href=${pageContext.request.contextPath}/client/browse?pageNum=${p}>${p}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${pageProduct.hasNextPage}">
                            <a href=${pageContext.request.contextPath}/client/browse?pageNum=${pageProduct.nextPage}>下一页</a>
                        </c:when>
                        <c:otherwise>
                            <a href="#">下一页</a>
                        </c:otherwise>
                    </c:choose>
                    <a href=${pageContext.request.contextPath}/client/browse?pageNum=${pageProduct.pages}>末页</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>