package moneyger4u.domain.repository.user;

import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query("SELECT x FROM User x JOIN FETCH x.familyId JOIN FETCH x.roleList WHERE x.email = :email")
    User findDetailByEmail(@Param("email") String email);

    List<User> findByFamilyId(Family familyId);
}
