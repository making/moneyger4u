package am.ik.moneyger4u.app.daily_outcome.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import am.ik.moneyger4u.domain.model.Payment;

import lombok.Data;

@Data
public class DailyOutcomeForm {
    /**
     * Validation group for create daily outcome
     */
    public static interface DailyOutcomeCreateGroup {
    }

    /**
     * Validation group for update daily outcome
     */
    public static interface DailyOutcomeUpdateGroup {
    }

    @Null(groups = { DailyOutcomeCreateGroup.class })
    @NotNull(groups = { DailyOutcomeUpdateGroup.class })
    @Min(0)
    private Integer dailyOutcomeId;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outcomeDate;

    @NotEmpty(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    @Size(max = 100)
    private String outcomeName;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    @Min(1)
    @Max(1000)
    private Integer quantity;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    @Min(0)
    private Integer amount;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    private Payment payment;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    private boolean isWaste = false;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    @Size(max = 256)
    private String remarks;

    @NotNull(groups = { DailyOutcomeUpdateGroup.class,
            DailyOutcomeCreateGroup.class })
    private Integer dailyOutcomeCategoryId;

    @Null(groups = { DailyOutcomeCreateGroup.class })
    @NotNull(groups = { DailyOutcomeUpdateGroup.class })
    @Min(0)
    private Integer version;
}
