<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!--
* INSPINIA - Responsive Admin Theme
* Version 2.5
*
-->

<!DOCTYPE html>
<html ng-app="inspinia">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title set in pageTitle directive -->
    <title page-title></title>

    <!-- Font awesome -->
    <link href="${contextPath}/resources/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Bootstrap and Fonts -->
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Main Inspinia CSS files -->
    <link href="${contextPath}/resources/css/animate.css" rel="stylesheet">
    <link id="loadBefore" href="${contextPath}/resources/css/style.css" rel="stylesheet">
	<link id="loadBefore" href="${contextPath}/resources/css/scrummit.css" rel="stylesheet">

</head>

<!-- ControllerAs syntax -->
<!-- Main controller with serveral data used in Inspinia theme on diferent view -->
<body ng-controller="MainCtrl as main" class="{{$state.current.data.specialClass}}">

<!-- Main view  -->
<div ui-view></div>

<!-- jQuery and Bootstrap -->
<script src="${contextPath}/resources/js/jquery/jquery-2.1.1.min.js"></script>
<script src="${contextPath}/resources/js/plugins/jquery-ui/jquery-ui.js"></script>

<script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>

<!-- MetsiMenu -->
<script src="${contextPath}/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>

<!-- SlimScroll -->
<script src="${contextPath}/resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Peace JS -->
<script src="${contextPath}/resources/js/plugins/pace/pace.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${contextPath}/resources/js/inspinia.js"></script>

<!-- Main Angular scripts-->
<script src="${contextPath}/resources/js/angular/angular.min.js"></script>
<script src="${contextPath}/resources/js/angular/angular-cookies.min.js"></script>
<script src="${contextPath}/resources/js/plugins/oclazyload/dist/ocLazyLoad.min.js"></script>
<script src="${contextPath}/resources/js/ui-router/angular-ui-router.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap/ui-bootstrap-tpls-1.1.2.min.js"></script>
<script src="${contextPath}/resources/js/angular/angular-resource.min.js"></script>


<!-- Anglar App Script -->
<script src="${contextPath}/resources/js/app.js"></script>
<script src="${contextPath}/resources/js/config.js"></script>
<script src="${contextPath}/resources/js/directives.js"></script>
<script src="${contextPath}/resources/js/controllers.js"></script>
<script src="${contextPath}/resources/js/services.js"></script>

</body>
</html>
