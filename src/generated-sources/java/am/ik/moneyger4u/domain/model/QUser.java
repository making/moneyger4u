package am.ik.moneyger4u.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1570756907;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final ListPath<DailyOutcome, QDailyOutcome> dailyOutcomeList = this.<DailyOutcome, QDailyOutcome>createList("dailyOutcomeList", DailyOutcome.class, QDailyOutcome.class, PathInits.DIRECT2);

    public final ListPath<DailyOutcome, QDailyOutcome> dailyOutcomeList1 = this.<DailyOutcome, QDailyOutcome>createList("dailyOutcomeList1", DailyOutcome.class, QDailyOutcome.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final QFamily familyId;

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final ListPath<MonthlyOutcome, QMonthlyOutcome> monthlyOutcomeList = this.<MonthlyOutcome, QMonthlyOutcome>createList("monthlyOutcomeList", MonthlyOutcome.class, QMonthlyOutcome.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final ListPath<Role, QRole> roleList = this.<Role, QRole>createList("roleList", Role.class, QRole.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QUser(Path<? extends User> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.familyId = inits.isInitialized("familyId") ? new QFamily(forProperty("familyId")) : null;
    }

}

