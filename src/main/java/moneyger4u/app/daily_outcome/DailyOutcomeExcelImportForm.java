package moneyger4u.app.daily_outcome;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DailyOutcomeExcelImportForm {
    @NotNull
    private MultipartFile file;

    @NotNull
    @Min(0)
    private Integer createUserId;
}
