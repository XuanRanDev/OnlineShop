<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>后台管理</title>

        <!-- Bootstrap -->
        <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>

        <div>
            <div style="text-align:center;margin:0 auto;"><span style="font-size: 50px;">服装信息</span></div>
            <div style="margin-left: 690px;">
                <form method="get" action="<c:url value="/admin/seleClothes"/>">
                    <input type="text" name="key" value="<c:out value="${param.key}" />"/>
                    <input type="submit" value="查询"/>
                </form>
            </div>
            <table class="table table-hover table-bordered">
                <tr>
                    <td style="text-align: center;font-weight: bold">服装名</td>
                    <td style="text-align: center;font-weight: bold">服装介绍</td>
                    <td style="text-align: center;font-weight: bold">价格</td>
                    <td style="text-align: center;font-weight: bold">图片路径</td>
                    <td style="text-align: center;font-weight: bold">操作</td>
                </tr>

                <c:forEach items="${clothesList}" var="clothes">
                    <tr>
                        <td style="text-align: center;"><c:out value="${clothes.f_name}"/></td>
                        <td style="text-align: center;"><c:out value="${clothes.f_content}"/></td>
                        <td style="text-align: center;"><c:out value="${clothes.price}"/></td>
                        <td style="text-align: center;">img/<c:out value="${clothes.f_image}"/>.jpg</td>
                        <td style="text-align: center;">
                            <a href="<c:url value="/admin/delClothes?id=${clothes.f_id}"/>"
                               onclick="return confirm('是否要删除?')">
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>
                            <a href="<c:url value="/admin/clothesEdit?id=${clothes.f_id}"/>"
                               onclick="return confirm('是否要修改?')">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="jquery/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    </body>
</html>
