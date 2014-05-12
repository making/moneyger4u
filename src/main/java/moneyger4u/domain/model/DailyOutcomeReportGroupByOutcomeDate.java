package moneyger4u.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class DailyOutcomeReportGroupByOutcomeDate {
    private final DateTime date;

    private final Number amount;

    public DailyOutcomeReportGroupByOutcomeDate(Date date, Number amount) {
        this.date = new DateTime(date);
        this.amount = amount;
    }

    @Getter
    @AllArgsConstructor
    public static class Total {
        private final BigDecimal value;
    }

    public static Total calcTotal(List<DailyOutcomeReportGroupByOutcomeDate> reportsByOutcomeDate) {
        BigDecimal total = BigDecimal.ZERO;
        for (DailyOutcomeReportGroupByOutcomeDate report : reportsByOutcomeDate) {
            total = total.add(new BigDecimal(report.getAmount().toString()));
        }
        return new Total(total);
    }
}
