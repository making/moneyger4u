package am.ik.moneyger4u.domain.service.outcome;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.ParentOutcomeCategory;
import am.ik.moneyger4u.domain.repository.outcome.ParentOutcomeCategoryRepository;

@Service
@Transactional
public class ParentOutcomeCategoryServiceImpl implements
                                             ParentOutcomeCategoryService {

    @Inject
    protected ParentOutcomeCategoryRepository parentOutcomeCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "outcomeCategory")
    public List<ParentOutcomeCategory> findAll() {
        return parentOutcomeCategoryRepository.findAll();
    }

}
