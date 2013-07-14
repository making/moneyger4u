package am.ik.moneyger4u.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QParentOutcomeCategory is a Querydsl query type for ParentOutcomeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParentOutcomeCategory extends EntityPathBase<ParentOutcomeCategory> {

    private static final long serialVersionUID = 1221933830;

    public static final QParentOutcomeCategory parentOutcomeCategory = new QParentOutcomeCategory("parentOutcomeCategory");

    public final StringPath categoryName = createString("categoryName");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final ListPath<DailyOutcomeCategory, QDailyOutcomeCategory> dailyOutcomeCategoryList = this.<DailyOutcomeCategory, QDailyOutcomeCategory>createList("dailyOutcomeCategoryList", DailyOutcomeCategory.class, QDailyOutcomeCategory.class, PathInits.DIRECT2);

    public final NumberPath<Integer> parentOutcomeCategoryId = createNumber("parentOutcomeCategoryId", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QParentOutcomeCategory(String variable) {
        super(ParentOutcomeCategory.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QParentOutcomeCategory(Path<? extends ParentOutcomeCategory> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QParentOutcomeCategory(PathMetadata<?> metadata) {
        super(ParentOutcomeCategory.class, metadata);
    }

}

