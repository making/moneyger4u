package am.ik.moneyger4u.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMonthlyOutcomeCategory is a Querydsl query type for MonthlyOutcomeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMonthlyOutcomeCategory extends EntityPathBase<MonthlyOutcomeCategory> {

    private static final long serialVersionUID = -1055033341;

    public static final QMonthlyOutcomeCategory monthlyOutcomeCategory = new QMonthlyOutcomeCategory("monthlyOutcomeCategory");

    public final StringPath categoryName = createString("categoryName");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Integer> monthlyOutcomeCategoryId = createNumber("monthlyOutcomeCategoryId", Integer.class);

    public final ListPath<MonthlyOutcome, QMonthlyOutcome> monthlyOutcomeList = this.<MonthlyOutcome, QMonthlyOutcome>createList("monthlyOutcomeList", MonthlyOutcome.class, QMonthlyOutcome.class, PathInits.DIRECT2);

    public final StringPath unitName = createString("unitName");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QMonthlyOutcomeCategory(String variable) {
        super(MonthlyOutcomeCategory.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QMonthlyOutcomeCategory(Path<? extends MonthlyOutcomeCategory> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QMonthlyOutcomeCategory(PathMetadata<?> metadata) {
        super(MonthlyOutcomeCategory.class, metadata);
    }

}

