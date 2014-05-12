package moneyger4u.domain.model;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QIncomeCategory is a Querydsl query type for IncomeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIncomeCategory extends EntityPathBase<IncomeCategory> {

    private static final long serialVersionUID = 2062114845;

    public static final QIncomeCategory incomeCategory = new QIncomeCategory("incomeCategory");

    public final StringPath categoryName = createString("categoryName");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Integer> incomeCategoryId = createNumber("incomeCategoryId", Integer.class);

    public final ListPath<Income, QIncome> incomeList = this.<Income, QIncome>createList("incomeList", Income.class, QIncome.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QIncomeCategory(String variable) {
        super(IncomeCategory.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QIncomeCategory(Path<? extends IncomeCategory> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    public QIncomeCategory(PathMetadata<?> metadata) {
        super(IncomeCategory.class, metadata);
    }

}

