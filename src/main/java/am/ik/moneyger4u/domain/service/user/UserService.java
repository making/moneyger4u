package am.ik.moneyger4u.domain.service.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import am.ik.moneyger4u.domain.model.Family;
import am.ik.moneyger4u.domain.model.User;

public interface UserService {

	void save(User user, String password);

	Page<User> findAll(Pageable pageable);

	User findOne(Integer id);

	void delete(User user);

	Page<User> findByNameLike(String query, Pageable pageable);

	List<User> findByFamilyId(Family familyId);

}
