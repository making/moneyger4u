package am.ik.moneyger4u.domain.repository.outcome;

import org.springframework.data.domain.Page;

import am.ik.moneyger4u.domain.model.DailyOutcome;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyOutcomeSearchResult {
	private final Page<DailyOutcome> page;

	private final Integer sum;
}
