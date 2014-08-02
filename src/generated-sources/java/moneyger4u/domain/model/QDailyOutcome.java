package moneyger4u.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDailyOutcome is a Querydsl query type for DailyOutcome
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDailyOutcome extends EntityPathBase<DailyOutcome> {

    private static final long serialVersionUID = -745449777;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDailyOutcome dailyOutcome = new QDailyOutcome("dailyOutcome");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final QDailyOutcomeCategory dailyOutcomeCategoryId;

    public final NumberPath<Integer> dailyOutcomeId = createNumber("dailyOutcomeId", Integer.class);

    public final BooleanPath isWaste = createBoolean("isWaste");

    public final DatePath<java.util.Date> outcomeDate = createDate("outcomeDate", java.util.Date.class);

    public final StringPath outcomeName = createString("outcomeName");

    public final EnumPath<Payment> payment = createEnum("payment", Payment.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath remarks = createString("remarks");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final QUser updatedBy;

    public final QUser userId;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDailyOutcome(String variable) {
        this(DailyOutcome.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QDailyOutcome(Path<? extends DailyOutcome> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDailyOutcome(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDailyOutcome(PathMetadata<?> metadata, PathInits inits) {
        this(DailyOutcome.class, metadata, inits);
    }

    public QDailyOutcome(Class<? extends DailyOutcome> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dailyOutcomeCategoryId = inits.isInitialized("dailyOutcomeCategoryId") ? new QDailyOutcomeCategory(forProperty("dailyOutcomeCategoryId"), inits.get("dailyOutcomeCategoryId")) : null;
        this.updatedBy = inits.isInitialized("updatedBy") ? new QUser(forProperty("updatedBy"), inits.get("updatedBy")) : null;
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId"), inits.get("userId")) : null;
    }

}

