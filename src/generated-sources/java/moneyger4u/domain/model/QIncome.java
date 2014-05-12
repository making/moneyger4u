package moneyger4u.domain.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QIncome is a Querydsl query type for Income
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIncome extends EntityPathBase<Income> {

    private static final long serialVersionUID = 1121889791;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIncome income = new QIncome("income");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final QFamily familyId;

    public final QIncomeCategory incomeCategoryId;

    public final DatePath<java.util.Date> incomeDate = createDate("incomeDate", java.util.Date.class);

    public final NumberPath<Integer> incomeId = createNumber("incomeId", Integer.class);

    public final StringPath incomeName = createString("incomeName");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QIncome(String variable) {
        this(Income.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QIncome(Path<? extends Income> path) {
        this((Class) path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIncome(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIncome(PathMetadata<?> metadata, PathInits inits) {
        this(Income.class, metadata, inits);
    }

    public QIncome(Class<? extends Income> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.familyId = inits.isInitialized("familyId") ? new QFamily(forProperty("familyId")) : null;
        this.incomeCategoryId = inits.isInitialized("incomeCategoryId") ? new QIncomeCategory(forProperty("incomeCategoryId")) : null;
    }

}

