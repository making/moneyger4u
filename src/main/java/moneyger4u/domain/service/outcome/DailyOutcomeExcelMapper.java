package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.DailyOutcomeCategory;
import moneyger4u.domain.model.Payment;
import moneyger4u.domain.repository.outcome.ParentOutcomeCategoryRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DailyOutcomeExcelMapper {
    public static enum ColumnLabel {
        日付,
        支出名,
        単価,
        税,
        数量,
        カテゴリ,
        支払い方法
    }

    private final Map<String, DailyOutcomeCategory> categoryMap;

    @Inject
    public DailyOutcomeExcelMapper(ParentOutcomeCategoryRepository parentOutcomeCategoryRepository) {
        categoryMap = Collections.unmodifiableMap(
                parentOutcomeCategoryRepository.findAll().stream()
                        .flatMap(p -> p.getDailyOutcomeCategoryList().stream())
                        .collect(Collectors.toMap(c -> c.getParentOutcomeCategoryId().getCategoryName() + "/" + c.getCategoryName(),
                                Function.identity())));
    }

    public List<DailyOutcome> map(InputStream in) throws Exception {
        Workbook workbook = WorkbookFactory.create(in);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Cell> header = sheet.getRow(sheet.getFirstRowNum()).cellIterator();
        Map<ColumnLabel, Integer> reverseIndex = StreamSupport.stream(Spliterators.spliteratorUnknownSize(header, Spliterator.ORDERED), false)
                .collect(Collectors.toMap(cell -> ColumnLabel.valueOf(cell.getStringCellValue().trim()), Cell::getColumnIndex));
        List<DailyOutcome> outcomes = new ArrayList<>();
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String outcomeName = row.getCell(reverseIndex.get(ColumnLabel.支出名)).getStringCellValue();
            if (outcomeName == null || outcomeName.isEmpty()) {
                continue;
            }
            Date outcomeDate = row.getCell(reverseIndex.get(ColumnLabel.日付)).getDateCellValue();
            int amount = (int) row.getCell(reverseIndex.get(ColumnLabel.単価)).getNumericCellValue();
            int quantity = (int) row.getCell(reverseIndex.get(ColumnLabel.数量)).getNumericCellValue();
            double tax = row.getCell(reverseIndex.get(ColumnLabel.税)).getNumericCellValue();
            Payment payment = Payment.valueOf(row.getCell(reverseIndex.get(ColumnLabel.支払い方法)).getStringCellValue().trim().toUpperCase());
            String categoryName = row.getCell(reverseIndex.get(ColumnLabel.カテゴリ)).getStringCellValue().trim();

            DailyOutcome dailyOutcome = new DailyOutcome();
            dailyOutcome.setOutcomeDate(outcomeDate);
            dailyOutcome.setOutcomeName(outcomeName);
            dailyOutcome.setAmount((int) (amount * (1.0 + tax)));
            dailyOutcome.setQuantity(quantity);
            dailyOutcome.setPayment(payment);
            dailyOutcome.setDailyOutcomeCategoryId(categoryMap.get(categoryName));

            outcomes.add(dailyOutcome);
        }
        return outcomes;
    }


}
