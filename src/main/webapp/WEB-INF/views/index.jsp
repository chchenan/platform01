<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<jsp:include page="header/header.jsp" />
<link href="styles/index.css" rel="stylesheet">
<body>
<jsp:include page="nav/nav.jsp" />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <s:if test="#request.session.userType == 2">
                <jsp:include page="sidebar/sidebar-teacher.jsp" />
            </s:if>
            <s:elseif test="#request.session.userType == 3">
                <jsp:include page="sidebar/sidebar-student.jsp" />
            </s:elseif>
            <s:else>
                <jsp:include page="sidebar/sidebar-admin.jsp" />
            </s:else>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="images/index/banner-1.png" alt="...">
                        <div class="carousel-caption">
                            <h3>First slide label</h3>
                            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                        </div>
                    </div>
                    <s:iterator value="#request.carouselFigures" var="cf">
                    <div class="item">
                        <img src="upload/carouselFigure/<s:property value="#cf.pictureName"/>" alt="...">
                        <div class="carousel-caption">
                            <h3><s:property value="cf.title" /></h3>
                            <p><s:property value="cf.content" /></p>
                        </div>
                    </div>
                    </s:iterator>
                    
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-notice">
                        <div class="panel-heading">
                            <h3 class="panel-title">公告</h3>
                            <a href="#" class="panel-title panel-title-more fr">更多>></a>
                        </div>
                        <div class="list-group">
                        	<s:iterator value="#request.noticeBoards" var="nb">
                        	<a href="#" class="list-group-item"><s:property value="#nb.title" /></a>
                        	</s:iterator>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-job">
                        <div class="panel-heading">
                            <h3 class="panel-title">招聘信息</h3>
                            <a href="#" class="panel-title panel-title-more fr">更多>></a>
                        </div>
                        <div class="list-group">
                            <s:iterator value="#request.recruitInfos" var="ri">
                        	<a href="#" class="list-group-item"><s:property value="#ri.title" /></a>
                        	</s:iterator>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer/footer.jsp" />
</body>
</html>