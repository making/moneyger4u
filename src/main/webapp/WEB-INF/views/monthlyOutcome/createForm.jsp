<script type="text/javascript">
<!--
	$(function() {
		$('#outcomeDate').datepicker({
			dateFormat : 'yy-mm-dd',
			autoSize : true
		});
		$('#monthlyOutcomeForm').validate(
				{
					errorClass : 'text-danger',
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
						monthlyOutcomeCategoryId : {
							required : true
						}
					},
					highlight : function(element) {
						$(element).closest('.form-group').removeClass(
								'has-success').addClass('has-error');
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
								enabled : false
							}
						}).show();
					});
				//-->
				</script>
</c:if>
<h2>固定支出登録</h2>
<div class='notifications top-right'></div>
<form:form action="${pageContext.request.contextPath}/monthlyOutcome"
    method="POST" modelAttribute="monthlyOutcomeForm"
    class="form-horizontal">
    <div class="form-group">
        <form:label path="outcomeDate"
            class="col col-sm-2 control-label">日付</form:label>
        <div class="col col-sm-10 controls">
            <form:input path="outcomeDate" type="text"
                class="form-control" placeholder="yyyy-MM-dd" />
            <form:errors path="outcomeDate" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="outcomeName"
            class="col col-sm-2 control-label">固定支出名</form:label>
        <div class="col col-sm-10 controls">
            <form:input path="outcomeName" type="text"
                class="form-control" placeholder="電気代 6月分等" />
            <form:errors path="outcomeName" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="amount"
            class="col col-sm-2 control-label">料金</form:label>
        <div class="col col-sm-10 controls">
            <form:input path="amount" type="text"
                class="form-control" />
            円
            <form:errors path="amount" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="quantity"
            class="col col-sm-2 control-label">使用量</form:label>
        <div class="col col-sm-10 controls">
            <form:input path="quantity" class="form-control" />
            <form:errors path="quantity" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="monthlyOutcomeCategoryId"
            class="col col-sm-2 control-label">カテゴリ</form:label>
        <div class="col col-sm-10 controls">
            <form:select path="monthlyOutcomeCategoryId"
                class="form-control">
                <form:option value="">--</form:option>
                <form:options items="${monthlyOutcomeCategoryMap}" />
            </form:select>
            <form:errors path="monthlyOutcomeCategoryId"
                cssClass="text-danger" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="remarks"
            class="col col-sm-2 control-label">備考</form:label>
        <div class="col col-sm-10 controls">
            <form:textarea path="remarks" class="form-control" />
            <form:errors path="remarks" cssClass="text-danger" />
        </div>
    </div>
    <div class="form-actions">
        <div class="col col-sm-10 col-sm-offset-2">
            <input type="submit"
                class="btn btn-primary disable-double-submit" value="登録" />
            <a href="${pageContext.request.contextPath}/"
                class="btn btn-default disable-double-submit"><span
                class="glyphicon glyphicon-home"></span> HOME</a>
        </div>
    </div>
</form:form>