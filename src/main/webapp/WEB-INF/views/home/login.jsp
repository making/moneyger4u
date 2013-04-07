
<form class="form-horizontal"
  action='${pageContext.request.contextPath}/j_spring_security_check'
  method="POST">
  <c:if test="${not empty param.error}">
    <div class="alert alert-error span6">
      <a class="close" href="#" onclick="$(this).parent().hide()">x</a>
      <h4 class="alert-heading">Login error!</h4>
      ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
  </c:if>
  <fieldset>
    <div id="legend">
      <legend class="">ログイン</legend>
    </div>
    <div class="control-group">
      <!-- Username -->
      <label class="control-label" for="j_username">ユーザ名</label>
      <div class="controls">
        <input type="text" id="j_username" name="j_username"
          placeholder="Your E-mail address" class="input-xlarge">
      </div>
    </div>

    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="j_password">パスワード</label>
      <div class="controls">
        <input type="password" id="j_password" name="j_password"
          placeholder="Your password" class="input-xlarge">
      </div>
    </div>

    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="_spring_security_remember_me">ログインしたままにする</label>
      <div class="controls">
        <input type="checkbox" id="_spring_security_remember_me"
          name="_spring_security_remember_me" class="input-xlarge">
      </div>
    </div>
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success">Login</button>
      </div>
    </div>
  </fieldset>
</form>