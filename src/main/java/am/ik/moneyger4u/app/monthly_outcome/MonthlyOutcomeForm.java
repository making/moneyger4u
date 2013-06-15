package am.ik.moneyger4u.app.monthly_outcome;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MonthlyOutcomeForm {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outcomeDate;

    @NotNull
    @Size(max = 100)
    private String outcomeName;

    @NotNull
    @Min(1)
    @Max(1000)
    private Integer quantity;

    @NotNull
    private Integer amount;

    @NotNull
    @Size(max = 256)
    private String remarks;

    @NotNull
    private Integer monthlyOutcomeCategoryId;

    @Min(0)
    private Integer version;
}
