package moneyger4u.domain.model;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DailyOutcomeReportGroupByParentOutcomeCategoryId {
    private final ParentOutcomeCategory parentOutcomeCategory;

    private final Number amount;

    public DailyOutcomeReportGroupByParentOutcomeCategoryId(
            ParentOutcomeCategory parentOutcomeCategory, Number amount) {
        this.parentOutcomeCategory = parentOutcomeCategory;
        this.amount = amount;
    }
}
