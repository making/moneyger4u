<script type="text/javascript">
    <!--
    $(function () {
        $('#outcomeDateTo').datepicker({
            dateFormat: 'yy-mm-dd',
            autoSize: true
        });
        $('#outcomeDateFrom').datepicker({
            dateFormat: 'yy-mm-dd',
            autoSize: true
        });
    });
    //-->
</script>
<h2>支出詳細検索</h2>

<div class='notifications top-right'></div>
<form:form
        action="${pageContext.request.contextPath}/dailyOutcome/search"
        method="GET" modelAttribute="dailyOutcomeSearchCriteria"
        class="form-horizontal">
    <div class="row control-group">
        <form:label path="outcomeDateFrom"
                    class="col col-sm-2 control-label">日付</form:label>
        <div class="col col-sm-2 controls">
            <form:input path="outcomeDateFrom" type="text"
                        class="form-control" placeholder="yyyy-MM-dd"/>
            <form:errors path="outcomeDateFrom" cssClass="text-danger"/>
        </div>
        <div class="col col-sm-1 controls">~</div>
        <div class="col col-sm-2 controls">
            <form:input path="outcomeDateTo" type="text"
                        class="form-control" placeholder="yyyy-MM-dd"/>
            <form:errors path="outcomeDateTo" cssClass="text-danger"/>
        </div>
    </div>

    <div class="row control-group">
        <form:label path="outcomeName"
                    class="col col-sm-2 control-label">支出名</form:label>
        <div class="col col-sm-10 controls">
            <form:input path="outcomeName" type="text"
                        class="form-control" placeholder="前方一致検索"/>
            <form:errors path="outcomeName" cssClass="text-danger"/>
        </div>
    </div>
    <div class="row control-group">
        <form:label path="amountFrom" class="col col-sm-2 control-label">単価</form:label>
        <div class="col col-sm-2 controls">
            <form:input path="amountFrom" type="text"
                        class="form-control"/>
            円
            <form:errors path="amountFrom" cssClass="text-danger"/>
        </div>
        <div class="col col-sm-1 controls">~</div>
        <div class="col col-sm-2 controls">
            <form:input path="amountTo" type="text" class="form-control"/>
            円
            <form:errors path="amountTo" cssClass="text-danger"/>
        </div>
    </div>
    <div class="row control-group">
        <form:label path="dailyOutcomeCategoryIdList"
                    class="col col-sm-2 control-label">カテゴリ</form:label>
        <div class="col col-sm-10 controls">
            <form:select path="dailyOutcomeCategoryIdList"
                         class="form-control">
                <c:forEach var="categoryGroup"
                           items="${dailyOutcomeCategoryMap}">
                    <optgroup label="${f:h(categoryGroup.key)}">
                        <form:options items="${categoryGroup.value}"/>
                    </optgroup>
                </c:forEach>
            </form:select>
            <form:errors path="dailyOutcomeCategoryIdList"
                         cssClass="text-danger"/>
        </div>
    </div>

    <div class="row control-group">
        <form:label path="paymentList"
                    class="col col-sm-2 control-label">支払い方法</form:label>
        <div class="col col-sm-10 controls">
            <form:select path="paymentList" cssStyle="height:50px"
                         cssClass="form-control">
                <form:options items="${payments}" class="form-control"/>
            </form:select>
            <form:errors path="paymentList" cssClass="text-danger"/>
        </div>
    </div>
    <div class="row control-group">
        <form:label path="wasteList" class="col col-sm-2 control-label">無駄</form:label>
        <div class="col col-sm-10 controls">
            <form:select path="wasteList" class="form-control"
                         cssStyle="height:50px">
                <form:option value="false">NO</form:option>
                <form:option value="true">YES</form:option>
            </form:select>
            <form:errors path="wasteList" cssClass="text-danger"/>
        </div>
    </div>
    <div class="row control-group">
        <form:label path="userIdList" class="col col-sm-2 control-label">登録ユーザ</form:label>
        <div class="col col-sm-10 controls">
            <form:select path="userIdList" class="form-control"
                         cssStyle="height:50px">
                <form:options items="${userIdMap}"/>
            </form:select>
            <form:errors path="userIdList" cssClass="text-danger"/>
        </div>
    </div>
    <div class="row form-actions">
        <div class="col col-sm-10 col-sm-offset-2">
            <input type="submit"
                   class="btn btn-primary disable-double-submit" value="検索"/>
        </div>
    </div>
</form:form>

<hr>

<table class="table table-bordered table-striped table-condensed">
    <tr>
        <th>購入日</th>
        <th>カテゴリ</th>
        <th>支出名</th>
        <th>金額</th>
        <th>登録ユーザ</th>
        <th>コピーして登録</th>
    </tr>
    <c:forEach items="${result.page.content}" var="outcome">
        <tr>
            <td>${outcome.outcomeDate}</td>
            <td>${f:h(outcome.dailyOutcomeCategoryId.parentOutcomeCategoryId.categoryName)}(${f:h(outcome.dailyOutcomeCategoryId.categoryName)})</td>
            <td><a
                    href="${pageContext.request.contextPath}/dailyOutcome/${f:h(outcome.dailyOutcomeId)}">${f:h(outcome.outcomeName)}</a>
                <c:if test="${outcome.payment == 'CREDITCARD'}">
                    <span class="label label-info"><i
                            class="glyphicon glyphicon-check"></i> クレジットカード</span>
                </c:if> <c:if test="${outcome.isWaste}">
                    <span class="label label-warning"><i
                            class="glyphicon glyphicon-warning-sign"></i> 無駄</span>
                </c:if></td>
            <td><fmt:formatNumber
                    value="${outcome.amount * outcome.quantity}"
                    pattern="###,###"/> 円
            </td>
            <td>${f:h(outcome.userId.lastName)}
                    ${f:h(outcome.userId.firstName)}</td>
            <td><a class="btn btn-info"
                   href="${pageContext.request.contextPath}/dailyOutcome/?form&copyFrom=${f:h(outcome.dailyOutcomeId)}">コピー</a>
            </td>
        </tr>
    </c:forEach>
</table>
<table class="table table-bordered table-condensed">
    <tr>
        <th>合計</th>
        <td><fmt:formatNumber value="${f:h(result.sum)}"
                              pattern="###,###"/> 円
        </td>
    </tr>
</table>
<t:pagination page="${result.page}"
              criteriaQuery="${f:query(dailyOutcomeSearchCriteria)}"
              outerElementClass="pagination"/>
