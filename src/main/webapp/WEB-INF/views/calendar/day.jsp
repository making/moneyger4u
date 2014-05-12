<h2>${today.toString('yyyy-MM-dd')}の支出</h2>
<ul class="pager">
    <li class="previous"><a
            href="${pageContext.request.contextPath}/calendar/${today.minusDays(1).toString('yyyy/MM/dd')}">前日</a></li>
    <li class="next"><a
            href="${pageContext.request.contextPath}/calendar/${today.plusDays(1).toString('yyyy/MM/dd')}">翌日</a></li>
</ul>
<p>
    <a
            href="${pageContext.request.contextPath}/dailyOutcome?form&date=${today.toString('yyyy-MM-dd')}"
            class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> 支出登録する</a> <a
        href="${pageContext.request.contextPath}/calendar/${today.toString('yyyy/MM')}"
        class="btn btn-success"><span class="glyphicon glyphicon-list"></span>
    ${today.toString('yyyy-MM')}の支出</a>
</p>
<table class="table table-bordered table-striped table-condensed">
    <tr>
        <th>カテゴリ</th>
        <th>支出名</th>
        <th>金額</th>
        <th>登録ユーザ</th>
    </tr>
    <c:forEach items="${outcomes}" var="outcome">
        <tr>
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
                    value="${outcome.amount * outcome.quantity}" pattern="###,###"/>
                円
            </td>
            <td>${f:h(outcome.userId.lastName)}
                    ${f:h(outcome.userId.firstName)}</td>
        </tr>
    </c:forEach>
</table>
<table class="table table-bordered table-condensed">
    <tr>
        <th>支出合計</th>
        <td><fmt:formatNumber value="${f:h(total)}" pattern="###,###"/>
            円
        </td>
    </tr>
    <tr>
        <th>内、カード</th>
        <td><fmt:formatNumber value="${f:h(cardTotal)}"
                              pattern="###,###"/> 円
        </td>
    </tr>
    <tr>
        <th>内、無駄</th>
        <td <c:if test="${wasteTotal > 0}">class="text-error"</c:if>><fmt:formatNumber
                value="${f:h(wasteTotal)}" pattern="###,###"/> 円
        </td>
    </tr>
</table>
