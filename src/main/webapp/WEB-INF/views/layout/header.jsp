<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle"
                data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span> <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
                href="${pageContext.request.contextPath}">家計簿</a>
        </div>

        <sec:authorize access="isAuthenticated()">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav pull-right">
                    <li class="active"><a
                        href="${pageContext.request.contextPath}/">Home</a></li>
                    <li><a
                        href="${pageContext.request.contextPath}/dailyOutcome?form">支出登録</a></li>
                    <li><a
                        href="${pageContext.request.contextPath}/monthlyOutcome?form">固定支出登録</a></li>
                    <li class="nav-divider"></li>
                    <li class="dropdown"><a href="#"
                        class="dropdown-toggle" data-toggle="dropdown"><span
                            class="glyphicon glyphicon-user"></span> <sec:authentication
                                property="principal.displayName" /> <b
                            class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a
                                href="${pageContext.request.contextPath}/user/update?form&userId=<sec:authentication
                  property="principal.user.userId" />"><span
                                    class="glyphicon glyphicon-edit"></span>
                                    Edit</a></li>
                            <li class="divider"></li>
                            <li><a
                                href="${pageContext.request.contextPath}/logout"><span
                                    class="glyphicon glyphicon-off"></span>
                                    Logout</a></li>
                        </ul></li>
                </ul>
            </div>
        </sec:authorize>
        <!--/.nav-collapse -->
    </div>
</div>

