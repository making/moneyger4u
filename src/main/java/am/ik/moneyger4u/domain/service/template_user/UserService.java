package am.ik.moneyger4u.domain.service.template_user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import am.ik.moneyger4u.domain.model.TemplateUser;

public interface UserService {
	void save(TemplateUser user, String rawPassword);

	TemplateUser findOne(Integer id);

	Page<TemplateUser> findAll(Pageable pageable);

	Page<TemplateUser> findByNameLike(String name, Pageable pageable);

	void delete(TemplateUser user);
}
