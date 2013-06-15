<style type="text/css">
@media ( min-width : 500px) {
  #chart1 {
    height: 300px;
    width: 500px;
  }
}

@media ( max-width : 499px) {
  #chart1 {
    height: 200px;
    width: 280px;
  }
}
</style>
<h2>${today.toString('yyyy-MM')}の家計簿</h2>

<ul class="pager">
  <li class="previous"><a
    href="${pageContext.request.contextPath}/calendar/${today.minusMonths(1).toString('yyyy/MM')}">前月</a></li>
  <li class="next"><a
    href="${pageContext.request.contextPath}/calendar/${today.plusMonths(1).toString('yyyy/MM')}">翌月</a></li>
</ul>
<ul class="nav nav-tabs" id="monthTab">
  <li class="active"><a href="#list">一覧</a></li>
  <li><a href="#monthly">固定支出</a></li>
  <li><a href="#income">収入</a></li>
  <li><a href="#graph">グラフ</a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="list">
    <table class="table table-bordered table-striped table-condensed">
      <tr>
        <th>日付</th>
        <th>金額</th>
      </tr>
      <c:forEach items="${reportsByOutcomeDate}" var="report">
        <tr>
          <td><a
            href="${pageContext.request.contextPath}/calendar/${report.date.toString('yyyy/MM/dd')}">${report.date.toString('yyyy-MM-dd')}
              (${f:h(weekArray[report.date.dayOfWeek])})</a></td>
          <td><fmt:formatNumber value="${f:h(report.amount)}"
              pattern="###,###" />円</td>
        </tr>
      </c:forEach>
    </table>
    <div id="chart1"></div>
    <table class="table table-bordered table-striped table-condensed">
      <tr>
        <th>カテゴリ</th>
        <th>金額</th>
      </tr>
      <c:forEach items="${reportsByParentOutcomeCategoryId}"
        var="report">
        <tr>
          <td>${f:h(report.parentOutcomeCategory.categoryName)}</td>
          <td><fmt:formatNumber value="${f:h(report.amount)}"
              pattern="###,###" />円</td>
        </tr>
      </c:forEach>
    </table>
    <span id="graph-data"
      data-category='<c:forEach items="${reportsByParentOutcomeCategoryId}"
        var="report">["${f:h(report.parentOutcomeCategory.categoryName)}", ${f:h(report.amount)}],</c:forEach>'></span>
    <table class="table table-bordered table-condensed">
      <tr>
        <th>支出合計</th>
        <td><fmt:formatNumber value="${f:h(total)}"
            pattern="###,###" /> 円</td>
      </tr>
      <tr>
        <th>内、無駄</th>
        <td <c:if test="${wasteTotal > 0}">class="text-error"</c:if>><fmt:formatNumber
            value="${f:h(wasteTotal)}" pattern="###,###" /> 円</td>
      </tr>
    </table>
  </div>
  <div class="tab-pane" id="monthly">
    <p>固定</p>
    <table class="table table-bordered table-striped table-condensed">
      <tr>
        <th>支出日</th>
        <th>カテゴリ</th>
        <th>固定支出名</th>
        <th>金額</th>
        <th>使用量</th>
      </tr>
      <c:forEach items="${monthlyOutcomes}" var="monthlyOutcome">
        <tr>
          <td><fmt:formatDate value="${monthlyOutcome.outcomeDate}" pattern="yyyy/MM/dd"/></td>
          <td>${f:h(monthlyOutcome.monthlyOutcomeCategoryId.categoryName)}</td>
          <td>${f:h(monthlyOutcome.outcomeName)}</td>
          <td><fmt:formatNumber
              value="${f:h(monthlyOutcome.amount)}" pattern="###,###" />円</td>
          <td>${f:h(monthlyOutcome.quantity)} ${f:h(monthlyOutcome.monthlyOutcomeCategoryId.unitName)}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <div class="tab-pane" id="income">
    <p>収入</p>
  </div>
  <div class="tab-pane" id="graph">
    <p>グラフ</p>
  </div>
</div>

<script>
	$(function() {
		$('#monthTab a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});

		// pie chart
		var ajaxDataRenderer = function(url, plot, options) {
			var ret = null;
			$.ajax({
				async : false,
				url : url,
				dataType : 'json',
				success : function(data) {
					console.log(data);
					ret = [ _.map(data.reportsByParentOutcomeCategoryId,
							function(x) {
								return [ x.parentOutcomeCategory.categoryName,
										x.amount ];
							}) ];
				}
			});
			console.log(ret);
			return ret;
		};

		var jsonurl = location.href + '.json';
		try {
			$.jqplot('chart1', jsonurl, {
				dataRenderer : ajaxDataRenderer,
				seriesDefaults : {
					renderer : $.jqplot.PieRenderer,
					rendererOptions : {
						showDataLabels : true
					}
				},
				legend : {
					show : true,
					location : 'e'
				}
			});
		} catch (e) {
			// no data
		}
	});
</script>