<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <a class="btn btn-navbar" data-toggle="collapse"
        data-target=".nav-collapse"> <span class="icon-bar"></span>
        <span class="icon-bar"></span> <span class="icon-bar"></span>
      </a> <a class="brand" href="${pageContext.request.contextPath}/">家計簿</a>
      <div class="nav-collapse collapse">
        <sec:authorize access="isAuthenticated()">
          <ul class="nav">
            <li class="active"><a
              href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a
              href="${pageContext.request.contextPath}/dailyOutcome?form">支出登録</a></li>
          </ul>
          <div class="pull-right">
            <ul class="nav pull-right">
              <li class="divider-vertical"></li>
              <li class="dropdown"><a href="#"
                class="dropdown-toggle" data-toggle="dropdown"><i
                  class="icon-user"></i> <sec:authentication
                    property="principal.displayName" /> <b
                  class="caret"></b> </a>
                <ul class="dropdown-menu">
                  <li><a
                    href="${pageContext.request.contextPath}/user/update?form&userId=<sec:authentication
                  property="principal.user.userId" />"><i
                      class="icon-edit"></i> Edit</a></li>
                  <li class="divider"></li>
                  <li><a
                    href="${pageContext.request.contextPath}/logout"><i
                      class="icon-off"></i> Logout</a></li>
                </ul></li>
            </ul>
          </div>
        </sec:authorize>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>
</div>
