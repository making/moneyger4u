package am.ik.moneyger4u.domain.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.Family;
import am.ik.moneyger4u.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	List<User> findByFamilyId(Family familyId);
}
