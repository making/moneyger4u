package am.ik.moneyger4u.domain.repository.outcome;

import java.util.Date;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DailyOutcomeReportGroupByOutcomeDate {
    private final DateTime date;

    private final Number amount;

    public DailyOutcomeReportGroupByOutcomeDate(Date date, Number amount) {
        this.date = new DateTime(date);
        this.amount = amount;
    }

}
