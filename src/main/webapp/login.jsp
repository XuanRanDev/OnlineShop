<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>登录</title>
        <!-- Bootstrap -->
        <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="layui/css/layui.css">


    </head>
    <body>


        <div class="layui-bg-gray"
             style="margin-top: 50px;margin-left: 500px;margin-right: 300px;height: 400px;width: 500px">

            <div class="layui-panel" style="height: 400px">
                <div style="padding: 50px 30px;">
                    <form action="<c:url value="/loginReq" /> " method="post">
                        <h2 style="text-align: center">网上购物系统-专注于服装</h2>
                        <label>账号：</label>
                        <input type="text" class="form-control" name="loginId" placeholder="账号" required autofocus>
                        <label>密码：</label>
                        <input type="password" class="form-control" name="password" placeholder="密码" required>
                        <div>
                            　
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
                        <div style="text-align: right;">
                            <a href="<c:url value="/selePwd"/>" style="text-align: right;">忘记密码</a>
                            <span>　　　　　还没有账号？</span><a href="<c:url value="/register"/> ">立即注册</a>
                        </div>

                    </form>


                </div>
            </div>
        </div>


        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="jquery/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    </body>
</html>
