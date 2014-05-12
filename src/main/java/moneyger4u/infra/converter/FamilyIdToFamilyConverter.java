package moneyger4u.infra.converter;

import moneyger4u.domain.model.Family;
import org.dozer.DozerConverter;

public class FamilyIdToFamilyConverter extends DozerConverter<Integer, Family> {
    public FamilyIdToFamilyConverter() {
        super(Integer.class, Family.class);
    }

    @Override
    public Family convertTo(Integer source, Family destination) {
        if (source == null) {
            return null;
        }
        return new Family(source);
    }

    @Override
    public Integer convertFrom(Family source, Integer destination) {
        if (source == null) {
            return null;
        }
        return source.getFamilyId();
    }
}
