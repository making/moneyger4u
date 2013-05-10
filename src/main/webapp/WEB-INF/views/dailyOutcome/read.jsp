<script>
	$(function() {
		$('#command').on('submit', function() {
			return confirm('削除してよいですか？');
		});
	});
</script>
<c:if test="${not empty updated}">
  <script type="text/javascript">
		<!--
			$(function() {
				$('.top-right').notify({
					message : {
						text : '${f:h(updated)}を更新しました!'
					},
					fadeOut : {
						enabled : false
					}
				}).show();
			});
		//-->
		</script>
</c:if>
<div class="row">
  <div class="span12">
    <h2>支出詳細</h2>
    <div class='notifications top-right'></div>

    <table class="table table-bordered table-striped table-condensed">
      <tr>
        <th>支出日</th>
        <td>${date.toString('yyyy-MM-dd')}</td>
      </tr>
      <tr>
        <th>支出名</th>
        <td>${f:h(dailyOutcome.outcomeName)}</td>
      </tr>
      <tr>
        <th>カテゴリ</th>
        <td>${f:h(dailyOutcome.dailyOutcomeCategoryId.parentOutcomeCategoryId.categoryName)}(${f:h(dailyOutcome.dailyOutcomeCategoryId.categoryName)})</td>
      </tr>
      <tr>
        <th>単価</th>
        <td><fmt:formatNumber value="${f:h(dailyOutcome.amount)}"
            pattern="###,###" />円</td>
      </tr>
      <tr>
        <th>数量</th>
        <td><fmt:formatNumber value="${f:h(dailyOutcome.quantity)}"
            pattern="###,###" />点</td>
      </tr>
      <tr>
        <th>支払い方法</th>
        <td>${f:h(dailyOutcome.payment)}</td>
      </tr>
      <tr>
        <th>無駄</th>
        <td>${f:h(dailyOutcome.isWaste)}</td>
      </tr>
      <tr>
        <th>備考</th>
        <td>${f:h(dailyOutcome.remarks)}</td>
      </tr>
      <tr>
        <th>作成時刻</th>
        <td>${f:h(dailyOutcome.createdAt)}</td>
      </tr>
      <tr>
        <th>更新時刻</th>
        <td>${f:h(dailyOutcome.updatedAt)}</td>
      </tr>
      <tr>
        <th>作成ユーザ</th>
        <td>${f:h(dailyOutcome.userId.lastName)}
          ${f:h(dailyOutcome.userId.firstName)}</td>
      </tr>
      <tr>
        <th>更新ユーザ</th>
        <td>${f:h(dailyOutcome.updatedBy.lastName)}
          ${f:h(dailyOutcome.updatedBy.firstName)}</td>
      </tr>
      <tr>
        <th>変更回数</th>
        <td>${f:h(dailyOutcome.version)}</td>
      </tr>
    </table>
    <p>
      <form:form method="delete"
        action="${pageContext.request.contextPath}/dailyOutcome/${f:h(dailyOutcome.dailyOutcomeId)}">
        <a
          href="${pageContext.request.contextPath}/calendar/${date.toString('yyyy/MM/dd')}"
          class="btn btn-success"><span
          class="glyphicon glyphicon-list"></span>
          ${date.toString('yyyy-MM-dd')}の支出</a>
        <a
          href="${pageContext.request.contextPath}/dailyOutcome/${f:h(dailyOutcome.dailyOutcomeId)}?form"
          class="btn btn-info"><span
          class="glyphicon glyphicon-edit"></span>
          修正</a>
        <button type="submit" class="btn btn-danger">
          <span
            class="glyphicon glyphicon-remove"></span>
          削除
        </button>
        <a href="${pageContext.request.contextPath}/"
          class="btn btn-default"><span
          class="glyphicon glyphicon-home"></span> HOME</a>
      </form:form>
    </p>
  </div>
</div>
