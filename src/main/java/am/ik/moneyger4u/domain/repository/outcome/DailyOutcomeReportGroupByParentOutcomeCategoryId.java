package am.ik.moneyger4u.domain.repository.outcome;

import am.ik.moneyger4u.domain.model.ParentOutcomeCategory;

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
