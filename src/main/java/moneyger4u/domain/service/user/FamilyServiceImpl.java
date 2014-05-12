package moneyger4u.domain.service.user;

import moneyger4u.domain.model.Family;
import moneyger4u.domain.repository.user.FamilyRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class FamilyServiceImpl implements FamilyService {
    @Inject
    FamilyRepository familyRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "family")
    public List<Family> findAll() {
        return familyRepository.findAll();
    }
}
