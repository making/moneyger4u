package am.ik.moneyger4u.domain.service.outcome;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.MonthlyOutcomeCategory;
import am.ik.moneyger4u.domain.repository.outcome.MonthlyOutcomeCategoryRepository;

@Service
@Transactional
public class MonthlyOutcomeCategorySeviceImpl implements
                                             MonthlyOutcomeCategoryService {
    @Inject
    protected MonthlyOutcomeCategoryRepository monthlyOutcomeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MonthlyOutcomeCategory> findAll() {
        return monthlyOutcomeCategoryRepository.findAll();
    }

}
