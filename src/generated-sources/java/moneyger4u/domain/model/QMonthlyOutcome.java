package moneyger4u.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonthlyOutcome is a Querydsl query type for MonthlyOutcome
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonthlyOutcome extends EntityPathBase<MonthlyOutcome> {

    private static final long serialVersionUID = -1438470949;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonthlyOutcome monthlyOutcome = new QMonthlyOutcome("monthlyOutcome");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final QFamily familyId;

    public final QMonthlyOutcomeCategory monthlyOutcomeCategoryId;

    public final NumberPath<Integer> monthlyOutcomeId = createNumber("monthlyOutcomeId", Integer.class);

    public final DatePath<java.util.Date> outcomeDate = createDate("outcomeDate", java.util.Date.class);

    public final StringPath outcomeName = createString("outcomeName");

    public final NumberPath<java.math.BigDecimal> quantity = createNumber("quantity", java.math.BigDecimal.class);

    public final StringPath remarks = createString("remarks");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final QUser updatedBy;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QMonthlyOutcome(String variable) {
        this(MonthlyOutcome.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMonthlyOutcome(Path<? extends MonthlyOutcome> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonthlyOutcome(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMonthlyOutcome(PathMetadata<?> metadata, PathInits inits) {
        this(MonthlyOutcome.class, metadata, inits);
    }

    public QMonthlyOutcome(Class<? extends MonthlyOutcome> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.familyId = inits.isInitialized("familyId") ? new QFamily(forProperty("familyId")) : null;
        this.monthlyOutcomeCategoryId = inits.isInitialized("monthlyOutcomeCategoryId") ? new QMonthlyOutcomeCategory(forProperty("monthlyOutcomeCategoryId")) : null;
        this.updatedBy = inits.isInitialized("updatedBy") ? new QUser(forProperty("updatedBy"), inits.get("updatedBy")) : null;
    }

}

