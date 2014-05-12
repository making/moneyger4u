package moneyger4u.infra.converter;

import moneyger4u.domain.model.DailyOutcomeCategory;
import org.dozer.DozerConverter;

public class DailyOutcomeCateogryIdToDailyOutcomeCateogryConverter extends DozerConverter<Integer, DailyOutcomeCategory> {
    public DailyOutcomeCateogryIdToDailyOutcomeCateogryConverter() {
        super(Integer.class, DailyOutcomeCategory.class);
    }

    @Override
    public DailyOutcomeCategory convertTo(Integer source, DailyOutcomeCategory destination) {
        if (source == null) {
            return null;
        }
        return new DailyOutcomeCategory(source);
    }

    @Override
    public Integer convertFrom(DailyOutcomeCategory source, Integer destination) {
        if (source == null) {
            return null;
        }
        return source.getDailyOutcomeCategoryId();
    }
}
