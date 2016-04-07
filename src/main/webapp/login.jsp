<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<jsp:include page="WEB-INF/views/header/header.jsp" />
<link href="styles/student.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <h3>嵌入式实训平台</h3>
            <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">用户名：</label>
                    <input type="email" class="form-control" id="exampleInputEmail1">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码：</label>
                    <input type="password" class="form-control" id="exampleInputPassword1">
                </div>
                <div class="form-group">
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> 记住密码
                    </label>
                </div>
                <a href="index" class="btn btn-default">登录</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/views/footer/footer.jsp" />
</body>
</html>