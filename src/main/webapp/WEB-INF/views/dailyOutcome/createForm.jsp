<script type="text/javascript">
<!--
	$(function() {
		$('#outcomeDate').datepicker({
			dateFormat : 'yy-mm-dd',
			autoSize : true
		});
		$('#dailyOutcomeForm').validate(
				{
					errorClass : 'text-error',
					rules : {
						outcomeDate : {
							required : true,
							dateISO : true
						},
						outcomeName : {
							required : true,
							rangelength : [ 1, 100 ]
						},
						amount : {
							required : true
						},
						quantity : {
							required : true,
							max : 1000,
							min : 1
						},
						payment : {
							required : true
						},
						dailyOutcomeCategoryId : {
							required : true
						}
					},
					highlight : function(element) {
						$(element).closest('.control-group').removeClass(
								'success').addClass('error');
					}
				});
	});
//-->
</script>
<c:if test="${not empty created}">
  <script type="text/javascript">
		<!--
			$(function() {
				$('.top-right').notify({
					message : {
						text : '${f:h(created)}を登録しました!'
					},
					fadeOut : {
						enabled : true,
						delay : 1000
					}
				}).show();
			});
		//-->
		</script>
</c:if>
<div class="row">
  <div class="span12">
    <h2>支出登録</h2>
    <div class='notifications top-right'></div>
    <form:form action="${pageContext.request.contextPath}/dailyOutcome"
      method="POST" modelAttribute="dailyOutcomeForm"
      class="form-horizontal">
      <div class="control-group">
        <form:label path="outcomeDate" class="control-label">日付</form:label>
        <div class="controls">
          <form:input path="outcomeDate" type="text"
            placeholder="yyyy-MM-dd" />
          <form:errors path="outcomeDate" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="outcomeName" class="control-label">支出名</form:label>
        <div class="controls">
          <form:input path="outcomeName" type="text" placeholder="にんじん等" />
          <form:errors path="outcomeName" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="amount" class="control-label">単価</form:label>
        <div class="controls">
          <form:input path="amount" type="text" />
          円
          <form:errors path="amount" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="quantity" class="control-label">数量</form:label>
        <div class="controls">
          <form:input path="quantity" type="number" />
          点
          <form:errors path="quantity" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="dailyOutcomeCategoryId" class="control-label">カテゴリ</form:label>
        <div class="controls">
          <form:select path="dailyOutcomeCategoryId">
            <form:option value="">--</form:option>
            <c:forEach var="categoryGroup"
              items="${dailyOutcomeCategoryMap}">
              <optgroup label="${f:h(categoryGroup.key)}">
                <form:options items="${categoryGroup.value}" />
              </optgroup>
            </c:forEach>
          </form:select>
          <form:errors path="dailyOutcomeCategoryId"
            cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="payment" class="control-label">支払い方法</form:label>
        <div class="controls">
          <form:select path="payment">
            <form:options items="${payments}" />
          </form:select>
          <form:errors path="payment" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="waste" class="control-label">無駄</form:label>
        <div class="controls">
          <form:checkbox path="waste" id="waste" />
          <form:errors path="waste" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="remarks" class="control-label">備考</form:label>
        <div class="controls">
          <form:textarea path="remarks" />
          <form:errors path="remarks" cssClass="text-error" />
        </div>
      </div>
      <div class="form-actions">
        <input type="submit" class="btn btn-primary" value="登録" />
        <c:if test="${date != null}">
          <a
            href="${pageContext.request.contextPath}/calendar/${date.toString('yyyy/MM/dd')}"
            class="btn btn-success"><i class="icon-list icon-white"></i>
            ${date.toString('yyyy-MM-dd')}の支出</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/" class="btn"><i
          class="icon-home"></i> HOME</a>
      </div>
    </form:form>
  </div>
</div>
