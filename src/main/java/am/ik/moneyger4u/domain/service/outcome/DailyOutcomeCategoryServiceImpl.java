package am.ik.moneyger4u.domain.service.outcome;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.DailyOutcomeCategory;
import am.ik.moneyger4u.domain.repository.outcome.DailyOutcomeCategoryRepository;

@Service
@Transactional
public class DailyOutcomeCategoryServiceImpl implements
                                            DailyOutcomeCategoryService {
    @Inject
    protected DailyOutcomeCategoryRepository dailyOutcomeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcomeCategory> findAll() {
        return dailyOutcomeCategoryRepository.findAll();
    }

}
