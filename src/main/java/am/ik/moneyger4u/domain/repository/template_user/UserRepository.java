package am.ik.moneyger4u.domain.repository.template_user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import am.ik.moneyger4u.domain.model.TemplateUser;

public interface UserRepository extends JpaRepository<TemplateUser, Integer> {
    @Query(value = "SELECT x FROM User x WHERE x.name LIKE :name ORDER BY x.id", 
            countQuery = "SELECT COUNT(x) FROM User x WHERE x.name LIKE :name")
    Page<TemplateUser> findByNameLike(@Param("name") String name, Pageable page);
}

