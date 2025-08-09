package org.apache.logging.log4j.core.util.datetime;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DatePrinter {
   String format(long millis);

   String format(Date date);

   String format(Calendar calendar);

   Appendable format(long millis, Appendable buf);

   Appendable format(Date date, Appendable buf);

   Appendable format(Calendar calendar, Appendable buf);

   String getPattern();

   TimeZone getTimeZone();

   Locale getLocale();

   StringBuilder format(Object obj, StringBuilder toAppendTo, FieldPosition pos);
}
