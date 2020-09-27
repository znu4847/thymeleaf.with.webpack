package znu.practice.ie.with.webpack.hwinfo.log.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// @Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Calendar> {

  @Override
  public Calendar convertToDatabaseColumn(LocalDateTime dateTime) {
    if (dateTime == null)
      return null;
    Long time = Timestamp.valueOf(dateTime).getTime();
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date(time));
    return cal;
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Calendar timestamp) {
    if (timestamp == null)
      return null;

    LocalDateTime dateTime = LocalDateTime.of(
        // year
        timestamp.get(Calendar.YEAR)
        // month
        , timestamp.get(Calendar.MONTH)
        // date
        , timestamp.get(Calendar.DATE)
        // hour
        , timestamp.get(Calendar.HOUR_OF_DAY)
        // min
        , timestamp.get(Calendar.MINUTE)
        // sec
        , timestamp.get(Calendar.SECOND));

    return dateTime;
  }

}
