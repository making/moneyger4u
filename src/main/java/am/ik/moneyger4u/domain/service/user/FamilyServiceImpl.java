package am.ik.moneyger4u.domain.service.user;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.Family;
import am.ik.moneyger4u.domain.repository.user.FamilyRepository;

@Service
@Transactional
public class FamilyServiceImpl implements FamilyService {
    @Inject
    protected FamilyRepository familyRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "family")
    public List<Family> findAll() {
        return familyRepository.findAll();
    }
}
