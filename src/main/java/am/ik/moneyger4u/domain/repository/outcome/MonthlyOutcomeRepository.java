package am.ik.moneyger4u.domain.repository.outcome;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import am.ik.moneyger4u.domain.model.Family;
import am.ik.moneyger4u.domain.model.MonthlyOutcome;

public interface MonthlyOutcomeRepository extends
                                         JpaRepository<MonthlyOutcome, Integer> {
    @Query("SELECT x FROM MonthlyOutcome x WHERE x.familyId = :family AND x.outcomeDate BETWEEN :start AND :end")
    List<MonthlyOutcome> findFamilyMonthlyOutcomeByFamilyAndOutcomeDate(
            @Param("family") Family family, @Param("start") Date start, @Param("end") Date end);
}
