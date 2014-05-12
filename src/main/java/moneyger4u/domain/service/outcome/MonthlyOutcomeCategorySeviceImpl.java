package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.MonthlyOutcomeCategory;
import moneyger4u.domain.repository.outcome.MonthlyOutcomeCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class MonthlyOutcomeCategorySeviceImpl implements
        MonthlyOutcomeCategoryService {
    @Inject
    MonthlyOutcomeCategoryRepository monthlyOutcomeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MonthlyOutcomeCategory> findAll() {
        return monthlyOutcomeCategoryRepository.findAll();
    }

}
