package moneyger4u.domain.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QDailyOutcomeCategory is a Querydsl query type for DailyOutcomeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDailyOutcomeCategory extends EntityPathBase<DailyOutcomeCategory> {

    private static final long serialVersionUID = -1710343955;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDailyOutcomeCategory dailyOutcomeCategory = new QDailyOutcomeCategory("dailyOutcomeCategory");

    public final StringPath categoryName = createString("categoryName");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Integer> dailyOutcomeCategoryId = createNumber("dailyOutcomeCategoryId", Integer.class);

    public final ListPath<DailyOutcome, QDailyOutcome> dailyOutcomeList = this.<DailyOutcome, QDailyOutcome>createList("dailyOutcomeList", DailyOutcome.class, QDailyOutcome.class, PathInits.DIRECT2);

    public final QParentOutcomeCategory parentOutcomeCategoryId;

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDailyOutcomeCategory(String variable) {
        this(DailyOutcomeCategory.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QDailyOutcomeCategory(Path<? extends DailyOutcomeCategory> path) {
        this((Class) path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDailyOutcomeCategory(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDailyOutcomeCategory(PathMetadata<?> metadata, PathInits inits) {
        this(DailyOutcomeCategory.class, metadata, inits);
    }

    public QDailyOutcomeCategory(Class<? extends DailyOutcomeCategory> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentOutcomeCategoryId = inits.isInitialized("parentOutcomeCategoryId") ? new QParentOutcomeCategory(forProperty("parentOutcomeCategoryId")) : null;
    }

}

