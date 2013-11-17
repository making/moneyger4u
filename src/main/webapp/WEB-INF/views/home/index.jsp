<h3>メニュー</h3>
<ul>
    <li><a href="${pageContext.request.contextPath}/calendar">カレンダー</a></li>
    <li><a
        href="${pageContext.request.contextPath}/dailyOutcome/search?form">支出詳細検索</a></li>
    <li><a
        href="${pageContext.request.contextPath}/dailyOutcome?form">支出登録</a></li>
    <li><a
        href="${pageContext.request.contextPath}/monthlyOutcome?form">固定支出登録</a></li>
    <li><a href="${pageContext.request.contextPath}/user/list">ユーザー管理</a></li>
</ul>
<h3>支出簡易検索</h3>
<form class="form-inline"
    action="${pageContext.request.contextPath}/dailyOutcome"
    method="get">
    <input name="outcomeName" type="text" placeholder="支出名" class="form-control"
        style="width: 180px;">
    <button type="submit" class="btn btn-primary disable-double-submit">検索</button>
</form>