package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.DailyOutcomeCategory;
import moneyger4u.domain.repository.outcome.DailyOutcomeCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class DailyOutcomeCategoryServiceImpl implements
        DailyOutcomeCategoryService {
    @Inject
    DailyOutcomeCategoryRepository dailyOutcomeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcomeCategory> findAll() {
        return dailyOutcomeCategoryRepository.findAll();
    }

}
