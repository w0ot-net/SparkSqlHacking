package org.apache.spark.status.api.v1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

public final class JacksonMessageWriter$ {
   public static final JacksonMessageWriter$ MODULE$ = new JacksonMessageWriter$();

   public SimpleDateFormat makeISODateFormat() {
      SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'", Locale.US);
      Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
      iso8601.setCalendar(cal);
      return iso8601;
   }

   private JacksonMessageWriter$() {
   }
}
