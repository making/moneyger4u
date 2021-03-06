package moneyger4u.domain.repository.outcome;

import lombok.Data;
import moneyger4u.domain.model.Payment;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class DailyOutcomeSearchCriteria {
    // 支出名
    private String outcomeName;
    // カテゴリ
    private List<Integer> dailyOutcomeCategoryIdList;
    // 支出日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outcomeDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outcomeDateTo;
    // 金額
    private Integer amountFrom;
    private Integer amountTo;
    // 支払い方法
    private List<Payment> paymentList;
    // 無駄
    private List<Boolean> wasteList;
    // ユーザー
    private List<Integer> userIdList;
}
