package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.ParentOutcomeCategory;
import moneyger4u.domain.repository.outcome.ParentOutcomeCategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class ParentOutcomeCategoryServiceImpl implements
        ParentOutcomeCategoryService {

    @Inject
    ParentOutcomeCategoryRepository parentOutcomeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "outcomeCategory")
    public List<ParentOutcomeCategory> findAll() {
        return parentOutcomeCategoryRepository.findAll();
    }

}
