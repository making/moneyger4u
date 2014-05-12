<div class="row">
    <div class="span12">
        <h2>New User</h2>
        <form:form action="${pageContext.request.contextPath}/user/create"
                   modelAttribute="userForm" class="form-horizontal">
            <div class="control-group">
                <form:label path="firstName" class="control-label">First Name</form:label>
                <div class="form-group">
                    <form:input path="firstName" type="text"/>
                    <form:errors path="firstName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="firstName" class="control-label">Last Name</form:label>
                <div class="form-group">
                    <form:input path="lastName" type="text"/>
                    <form:errors path="lastName" cssClass="text-danger"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="familyId" class="control-label">Family</form:label>
                <div class="form-group">
                    <form:select path="familyId" items="${familyMap}"/>
                    <form:errors path="familyId" cssClass="text-danger"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="email" class="control-label">E-mail</form:label>
                <div class="form-group">
                    <form:input path="email" type="text"/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="password" class="control-label">User Password</form:label>
                <div class="form-group">
                    <form:input path="password" type="password"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="confirmPassword" class="control-label">User Password (Confirm)</form:label>
                <div class="form-group">
                    <form:input path="confirmPassword" type="password"/>
                    <form:errors path="confirmPassword" cssClass="text-danger"/>
                </div>
            </div>
            <div class="form-actions">
                <input type="submit" class="btn btn-primary" name="confirm"
                       value="Confirm"/> <input type="submit" class="btn btn-default"
                                                name="redirectToList" value="Back to List"/>
            </div>
        </form:form>
    </div>
</div>
