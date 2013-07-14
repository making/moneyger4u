<script type="text/javascript">
<!--
	$(function() {
		$('#outcomeDate').datepicker({
			dateFormat : 'yy-mm-dd',
			autoSize : true
		});
		$('#dailyOutcomeForm').validate(
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
								'has-success').addClass('has-error');
					}
				});
	});
//-->
</script>
<h2>支出修正</h2>
<div class='notifications top-right'></div>
<form:form
    action="${pageContext.request.contextPath}/dailyOutcome/${f:h(dailyOutcomeForm.dailyOutcomeId)}"
    method="PUT" modelAttribute="dailyOutcomeForm"
    class="form-horizontal">
    <div class="row control-group">
        <form:label path="outcomeDate"
            class="col col-lg-2 row-label control-label">日付</form:label>
        <div class="col col-lg-6 controls">
            <form:input path="outcomeDate" type="text"
                class="input-with-feedback" placeholder="yyyy-MM-dd" />
            <form:errors path="outcomeDate" cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="outcomeName"
            class="col col-lg-2 row-label control-label">支出名</form:label>
        <div class="col col-lg-6 controls">
            <form:input path="outcomeName" type="text"
                class="input-with-feedback" placeholder="にんじん等" />
            <form:errors path="outcomeName" cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="amount"
            class="col col-lg-2 row-label control-label">単価</form:label>
        <div class="col col-lg-6 controls">
            <form:input path="amount" type="text"
                class="input-with-feedback" />
            円
            <form:errors path="amount" cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="quantity"
            class="col col-lg-2 row-label control-label">数量</form:label>
        <div class="col col-lg-6 controls">
            <form:input path="quantity" type="number"
                class="input-with-feedback" />
            点
            <form:errors path="quantity" cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="dailyOutcomeCategoryId"
            class="col col-lg-2 row-label control-label">カテゴリ</form:label>
        <div class="col col-lg-6 controls">
            <form:select path="dailyOutcomeCategoryId"
                class="input-with-feedback">
                <form:option value="">--</form:option>
                <c:forEach var="categoryGroup"
                    items="${dailyOutcomeCategoryMap}">
                    <optgroup label="${f:h(categoryGroup.key)}">
                        <form:options items="${categoryGroup.value}" />
                    </optgroup>
                </c:forEach>
            </form:select>
            <form:errors path="dailyOutcomeCategoryId"
                cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="payment"
            class="col col-lg-2 row-label control-label">支払い方法</form:label>
        <div class="col col-lg-6 controls">
            <form:select path="payment" class="input-with-feedback">
                <form:options items="${payments}" />
            </form:select>
            <form:errors path="payment" cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="waste"
            class="col col-lg-2 row-label control-label">無駄</form:label>
        <div class="col col-lg-6 controls">
            <form:checkbox path="waste" id="waste"
                class="input-with-feedback" />
            <form:errors path="waste" cssClass="text-danger" />
        </div>
    </div>
    <div class="row control-group">
        <form:label path="remarks"
            class="col col-lg-2 row-label control-label">備考</form:label>
        <div class="col col-lg-6 controls">
            <form:textarea path="remarks" class="input-with-feedback" />
            <form:errors path="remarks" cssClass="text-danger" />
        </div>
    </div>
    <div class="row form-actions">
        <div class="col col-lg-6 col-offset-2">
            <form:hidden path="version" />
            <form:errors path="version" cssClass="text-danger" />
            <input type="submit"
                class="btn btn-primary disable-double-submit" value="修正" />
            <a
                href="${pageContext.request.contextPath}/dailyOutcome/${f:h(dailyOutcomeForm.dailyOutcomeId)}"
                class="btn btn-success"><span
                class="glyphicon glyphicon-th-list"></span> 詳細</a> <a
                href="${pageContext.request.contextPath}/"
                class="btn btn-default disable-double-submit"><span
                class="glyphicon glyphicon-home"></span> HOME</a>
        </div>
    </div>
</form:form>
