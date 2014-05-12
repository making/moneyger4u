package moneyger4u.domain.repository.outcome;

import lombok.AllArgsConstructor;
import lombok.Data;
import moneyger4u.domain.model.DailyOutcome;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class DailyOutcomeSearchResult {
    private final Page<DailyOutcome> page;

    private final Integer sum;
}
