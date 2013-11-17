<form class="form-horizontal"
    action='${pageContext.request.contextPath}/j_spring_security_check'
    method="POST">
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger">
            <a class="close" href="#" onclick="$(this).parent().hide()">x</a>
            <h4 class="alert-heading">Login error!</h4>
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <fieldset>
        <legend>ログイン</legend>
        <div class="form-group">
            <!-- Username -->
            <label class="col col-sm-2 control-label" for="j_username">ユーザ名</label>
            <div class="col col-sm-10">
                <input type="text" id="j_username" name="j_username"
                    placeholder="Your E-mail address"
                    class="form-control">
            </div>
        </div>

        <div class="form-group">
            <!-- Password-->
            <label class="col col-sm-2 control-label" for="j_password">パスワード</label>
            <div class="col col-sm-10">
                <input type="password" id="j_password" name="j_password"
                    placeholder="Your password" class="form-control">
                <div class="checkbox">
                    <label> <input type="checkbox"
                        id="_spring_security_remember_me"
                        name="_spring_security_remember_me">ログインしたままにする</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <!-- Button -->
            <div class="col col-sm-10 col-sm-offset-2">
                <button class="btn btn-success">Login</button>
            </div>
        </div>
    </fieldset>
</form>