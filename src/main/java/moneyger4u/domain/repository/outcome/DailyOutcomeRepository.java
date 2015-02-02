package moneyger4u.domain.repository.outcome;

import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import moneyger4u.domain.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DailyOutcomeRepository extends
        JpaRepository<DailyOutcome, Integer>, DailyOutcomeRepositoryCustom {
    @Query("SELECT x FROM DailyOutcome x WHERE x.userId.familyId = :family AND x.outcomeDate = :date ORDER BY x.dailyOutcomeCategoryId.dailyOutcomeCategoryId ASC, x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC")
    List<DailyOutcome> findFamilyDailyOutcomeByUserAndDate(
            @Param("family") Family family, @Param("date") Date date);

    @Query("SELECT x FROM DailyOutcome x WHERE x.userId.familyId = :family AND UPPER(x.outcomeName) LIKE UPPER(:outcomeName) ORDER BY x.dailyOutcomeCategoryId.dailyOutcomeCategoryId ASC, x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC, x.outcomeDate DESC")
    List<DailyOutcome> findFamilyDailyOutcomeLikeOutcomeName(
            @Param("outcomeName") String outcomeName,
            @Param("family") Family family);

    @Query("SELECT x FROM DailyOutcome x " +
            "WHERE x.userId.familyId = :family  AND " +
            " x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId = :parentOutcomeCategoryId AND " +
            " (x.outcomeDate BETWEEN :start AND :end) " +
            "ORDER BY " +
            " x.dailyOutcomeCategoryId.dailyOutcomeCategoryId ASC," +
            " x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC," +
            " x.outcomeDate DESC")
    List<DailyOutcome> findFamilyDailyOutcomeByParentCategory(
            @Param("parentOutcomeCategoryId") Integer parentOutcomeCategoryId,
            @Param("family") Family family,
            @Param("start") Date start, @Param("end") Date end);

    @Query("SELECT NEW moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate(x.outcomeDate, SUM(x.amount * x.quantity)) FROM DailyOutcome x WHERE x.userId.familyId = :family AND x.outcomeDate BETWEEN :start AND :end GROUP BY x.outcomeDate ORDER BY x.outcomeDate ASC")
    List<DailyOutcomeReportGroupByOutcomeDate> findFamilyReportGroupByOutcomeDate(
            @Param("family") Family family, @Param("start") Date start,
            @Param("end") Date end);

    @Query("SELECT NEW moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId(x.dailyOutcomeCategoryId.parentOutcomeCategoryId, SUM(x.amount * x.quantity)) FROM DailyOutcome x WHERE x.userId.familyId = :family AND x.outcomeDate BETWEEN :start AND :end GROUP BY x.dailyOutcomeCategoryId.parentOutcomeCategoryId ORDER BY x.dailyOutcomeCategoryId.parentOutcomeCategoryId.parentOutcomeCategoryId ASC")
    List<DailyOutcomeReportGroupByParentOutcomeCategoryId> findFamilyReportGroupByParentOutcomeCategoryId(
            @Param("family") Family family, @Param("start") Date start,
            @Param("end") Date end);

    @Query("SELECT SUM(x.amount * x.quantity) FROM DailyOutcome x WHERE x.userId.familyId = :family AND (x.outcomeDate BETWEEN :start AND :end) AND x.isWaste = true")
    Number findFamilyWasteTotal(@Param("family") Family family,
                                @Param("start") Date start, @Param("end") Date end);
}
