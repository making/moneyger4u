package moneyger4u.domain.repository.outcome;

import org.springframework.data.domain.Pageable;

public interface DailyOutcomeRepositoryCustom {
    DailyOutcomeSearchResult search(DailyOutcomeSearchCriteria criteria,
                                    Pageable pageable);
}
