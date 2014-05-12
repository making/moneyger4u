package moneyger4u.domain.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QFamily is a Querydsl query type for Family
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFamily extends EntityPathBase<Family> {

    private static final long serialVersionUID = 1024288698;

    public static final QFamily family = new QFamily("family");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Integer> familyId = createNumber("familyId", Integer.class);

    public final StringPath familyName = createString("familyName");

    public final ListPath<Income, QIncome> incomeList = this.<Income, QIncome>createList("incomeList", Income.class, QIncome.class, PathInits.DIRECT2);

    public final ListPath<MonthlyOutcome, QMonthlyOutcome> monthlyOutcomeList = this.<MonthlyOutcome, QMonthlyOutcome>createList("monthlyOutcomeList", MonthlyOutcome.class, QMonthlyOutcome.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final ListPath<User, QUser> userList = this.<User, QUser>createList("userList", User.class, QUser.class, PathInits.DIRECT2);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QFamily(String variable) {
        super(Family.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QFamily(Path<? extends Family> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    public QFamily(PathMetadata<?> metadata) {
        super(Family.class, metadata);
    }

}

