<h2>${f:h(outcomeName)}の検索結果</h2>

<table class="table table-bordered table-striped table-condensed">
  <tr>
    <th>購入日</th>
    <th>カテゴリ</th>
    <th>支出名</th>
    <th>金額</th>
    <th>登録ユーザ</th>
  </tr>
  <c:forEach items="${outcomes}" var="outcome">
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
          value="${outcome.amount * outcome.quantity}" pattern="###,###" />
        円</td>
      <td>${f:h(outcome.userId.lastName)}
        ${f:h(outcome.userId.firstName)}</td>
    </tr>
  </c:forEach>
</table>

<div>
  <a href="${pageContext.request.contextPath}/" class="btn btn-default"><span
    class="glyphicon glyphicon-home"></span> HOME</a>
</div>
