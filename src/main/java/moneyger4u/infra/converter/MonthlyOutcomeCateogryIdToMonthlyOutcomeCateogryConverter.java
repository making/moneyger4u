package moneyger4u.infra.converter;

import moneyger4u.domain.model.MonthlyOutcomeCategory;
import org.dozer.DozerConverter;

public class MonthlyOutcomeCateogryIdToMonthlyOutcomeCateogryConverter extends DozerConverter<Integer, MonthlyOutcomeCategory> {
    public MonthlyOutcomeCateogryIdToMonthlyOutcomeCateogryConverter() {
        super(Integer.class, MonthlyOutcomeCategory.class);
    }

    @Override
    public MonthlyOutcomeCategory convertTo(Integer source, MonthlyOutcomeCategory destination) {
        if (source == null) {
            return null;
        }
        return new MonthlyOutcomeCategory(source);
    }

    @Override
    public Integer convertFrom(MonthlyOutcomeCategory source, Integer destination) {
        if (source == null) {
            return null;
        }
        return source.getMonthlyOutcomeCategoryId();
    }
}
