package moneyger4u.domain.repository.outcome;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.mysema.query.jpa.impl.JPAQuery;
import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.DailyOutcomeCategory;
import moneyger4u.domain.model.QDailyOutcome;
import moneyger4u.domain.model.User;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

public class DailyOutcomeRepositoryImpl implements DailyOutcomeRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public DailyOutcomeSearchResult search(DailyOutcomeSearchCriteria criteria,
                                           Pageable pageable) {
        QDailyOutcome dailyOutcome = QDailyOutcome.dailyOutcome;
        JPAQuery query = new JPAQuery(entityManager).from(dailyOutcome);

        if (criteria.getOutcomeDateFrom() != null) {
            query = query.where(dailyOutcome.outcomeDate.goe(criteria
                    .getOutcomeDateFrom()));
        }
        if (criteria.getOutcomeDateTo() != null) {
            query = query.where(dailyOutcome.outcomeDate.loe(criteria
                    .getOutcomeDateTo()));
        }
        if (criteria.getOutcomeName() != null
                && !criteria.getOutcomeName().isEmpty()) {
            query = query.where(dailyOutcome.outcomeName.like(criteria
                    .getOutcomeName() + "%"));
        }
        if (criteria.getDailyOutcomeCategoryIdList() != null
                && !criteria.getDailyOutcomeCategoryIdList().isEmpty()) {
            Collection<DailyOutcomeCategory> candidates = Collections2
                    .transform(criteria.getDailyOutcomeCategoryIdList(),
                            new Function<Integer, DailyOutcomeCategory>() {
                                @Override
                                public DailyOutcomeCategory apply(
                                        Integer dailyOutcomeCategoryId) {
                                    return new DailyOutcomeCategory(
                                            dailyOutcomeCategoryId);
                                }
                            }
                    );
            query = query.where(dailyOutcome.dailyOutcomeCategoryId
                    .in(candidates));
        }
        if (criteria.getAmountFrom() != null) {
            query = query.where(dailyOutcome.amount.goe(criteria
                    .getAmountFrom()));
        }
        if (criteria.getAmountTo() != null) {
            query = query
                    .where(dailyOutcome.amount.loe(criteria.getAmountTo()));
        }
        if (criteria.getWasteList() != null
                && !criteria.getWasteList().isEmpty()) {
            query = query.where(dailyOutcome.isWaste.in(criteria
                    .getWasteList()));
        }
        if (criteria.getPaymentList() != null
                && !criteria.getPaymentList().isEmpty()) {
            query = query.where(dailyOutcome.payment.in(criteria
                    .getPaymentList()));
        }
        if (criteria.getUserIdList() != null
                && !criteria.getUserIdList().isEmpty()) {
            Collection<User> candidates = Collections2.transform(
                    criteria.getUserIdList(), new Function<Integer, User>() {
                        @Override
                        public User apply(Integer userId) {
                            return new User(userId);
                        }
                    }
            );
            query = query.where(dailyOutcome.userId.in(candidates));
        }

        Integer sum = query.singleResult(dailyOutcome.amount.multiply(
                dailyOutcome.quantity).sum());
        if (sum == null) {
            sum = 0;
        }

        long count = query.count();
        query = query
                .innerJoin(dailyOutcome.dailyOutcomeCategoryId)
                .fetch()
                .innerJoin(dailyOutcome.userId)
                .fetch()
                .orderBy(
                        dailyOutcome.dailyOutcomeCategoryId.dailyOutcomeCategoryId
                                .asc(), dailyOutcome.outcomeDate.asc()
                )
                .offset(pageable.getOffset()).limit(pageable.getPageSize());

        List<DailyOutcome> content = query.list(dailyOutcome);

        PageImpl<DailyOutcome> page = new PageImpl<DailyOutcome>(content,
                pageable, count);
        return new DailyOutcomeSearchResult(page, sum);
    }
}
