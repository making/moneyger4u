package am.ik.moneyger4u.domain.service.calendar;

import java.util.Locale;

import javax.inject.Inject;

import org.joda.time.DateTimeConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Inject
    protected MessageSource messageSource;

    @Cacheable(value = "weekArray")
    @Override
    public String[] getWeekArray(Locale locale) {
        String[] array = new String[DateTimeConstants.SUNDAY + 1];
        for (int i = DateTimeConstants.MONDAY; i <= DateTimeConstants.SUNDAY; i++) {
            String key = "week." + i;
            String value = messageSource.getMessage(key, null, locale);
            array[i] = value;
        }
        return array;
    }
}
