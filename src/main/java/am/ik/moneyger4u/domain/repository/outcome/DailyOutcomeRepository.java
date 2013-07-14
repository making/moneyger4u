package am.ik.moneyger4u.domain.repository.outcome;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import am.ik.moneyger4u.domain.model.DailyOutcome;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import am.ik.moneyger4u.domain.model.Family;

public interface DailyOutcomeRepository extends
		JpaRepository<DailyOutcome, Integer>, DailyOutcomeRepositoryCustom {
	@Query("SELECT x FROM DailyOutcome x WHERE x.userId.familyId = :family AND x.outcomeDate = :date ORDER BY x.dailyOutcomeCategoryId.dailyOutcomeCategoryId ASC, x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC")
	List<DailyOutcome> findFamilyDailyOutcomeByUserAndDate(
			@Param("family") Family family, @Param("date") Date date);

	@Query("SELECT x FROM DailyOutcome x WHERE x.userId.familyId = :family AND UPPER(x.outcomeName) LIKE UPPER(:outcomeName) ORDER BY x.dailyOutcomeCategoryId.dailyOutcomeCategoryId ASC, x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC, x.outcomeDate DESC")
	List<DailyOutcome> findFamilyDailyOutcomeLikeOutcomeName(
			@Param("outcomeName") String outcomeName,
			@Param("family") Family family);

	@Query("SELECT NEW am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate(x.outcomeDate, SUM(x.amount * x.quantity)) FROM DailyOutcome x WHERE x.userId.familyId = :family AND x.outcomeDate BETWEEN :start AND :end GROUP BY x.outcomeDate ORDER BY x.outcomeDate ASC")
	List<DailyOutcomeReportGroupByOutcomeDate> findFamilyReportGroupByOutcomeDate(
			@Param("family") Family family, @Param("start") Date start,
			@Param("end") Date end);

	@Query("SELECT NEW am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId(x.dailyOutcomeCategoryId.parentOutcomeCategoryId, SUM(x.amount * x.quantity)) FROM DailyOutcome x WHERE x.userId.familyId = :family AND x.outcomeDate BETWEEN :start AND :end GROUP BY x.dailyOutcomeCategoryId.parentOutcomeCategoryId ORDER BY x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC")
	List<DailyOutcomeReportGroupByParentOutcomeCategoryId> findFamilyReportGroupByParentOutcomeCategoryId(
			@Param("family") Family family, @Param("start") Date start,
			@Param("end") Date end);

	@Query("SELECT SUM(x.amount * x.quantity) FROM DailyOutcome x WHERE x.userId.familyId = :family AND (x.outcomeDate BETWEEN :start AND :end) AND x.isWaste = true")
	Number findFamilyWasteTotal(@Param("family") Family family,
			@Param("start") Date start, @Param("end") Date end);
}
