<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="apple-touch-icon"
          href="${pageContext.request.contextPath}/apple-touch-icon.png"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/vendor/jquery-ui-1.8.21/css/redmond/jquery-ui-1.8.21.custom.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/vendor/jqplot/jquery.jqplot.min.css"/>
    <style type="text/css">
        body {
            padding-top: 60px;
            /* 60px to make the container go all the way to the bottom of the topbar */
        }

        input[type=radio] {
            margin-left: 5px;
        }

        .my-inline.form-inline input[type="text"], .my-inline.form-inline input[type="password"] {
            width: 240px;
        }
    </style>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/js/jquery.validate-1.9.0.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/bootstrap-notify/js/bootstrap-notify.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/jquery-ui-1.8.21/js/jquery-ui-1.8.21.custom.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/js/underscore-1.4.4.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/jqplot/jquery.jqplot.min.js"></script>
    <script class="include" type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/jqplot/plugins/jqplot.pieRenderer.min.js"></script>
    <script class="include" type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/jqplot/plugins/jqplot.json2.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/vendor/js/button-disabler.js"></script>
    <script type="text/javascript">
        var TaxAdder = function (tax) {
            this.tax = tax; // n %
        };
        TaxAdder.prototype.add = function (value) {
            return Math.floor((this.tax + 100) * Number(value) / 100);
        };

        var TaxAddView = function ($amount, $add8percent, $minus8percent, $add5percent, $minus5percent, $revert) {
            var fivePercentAdder = new TaxAdder(5);
            var eightPercentAdder = new TaxAdder(8);
            var minusFivePercentAdder = new TaxAdder(-5);
            var minusEightPercentAdder = new TaxAdder(-8);
            var currentAmount = null;

            var setCurrentAmount = function () {
                if (!currentAmount) {
                    currentAmount = $amount.val();
                }
            };
            $amount.on('keydown', function (e) {
                currentAmount = null;
            });
            $add8percent.on('click', function (e) {
                e.preventDefault();
                setCurrentAmount();
                $amount.val(eightPercentAdder.add(currentAmount));
            });
            $add5percent.on('click', function (e) {
                e.preventDefault();
                setCurrentAmount();
                $amount.val(fivePercentAdder.add(currentAmount));
            });
            $minus5percent.on('click', function (e) {
                e.preventDefault();
                setCurrentAmount();
                $amount.val(minusFivePercentAdder.add(currentAmount));
            });
            $minus8percent.on('click', function (e) {
                e.preventDefault();
                setCurrentAmount();
                $amount.val(minusEightPercentAdder.add(currentAmount));
            });
            $revert.on('click', function (e) {
                e.preventDefault();
                if (currentAmount) {
                    $amount.val(currentAmount);
                    currentAmount = null;
                }
            });
        }
    </script>
    <c:set var="titleKey">
        <tiles:insertAttribute name="title" ignore="true"/>
    </c:set>
    <title><spring:message code="${titleKey}" text="moneyger4u"/></title>
</head>
<body>
<div class="container">
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>
    <hr>
    <p style="text-align: center; background: #e5eCf9;">Copyright
        &copy; 2013 moneyger4u</p>
</div>
</body>
</html>
