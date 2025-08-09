package org.apache.commons.lang3.time;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DatePrinter {
   String format(Calendar var1);

   Appendable format(Calendar var1, Appendable var2);

   /** @deprecated */
   @Deprecated
   StringBuffer format(Calendar var1, StringBuffer var2);

   String format(Date var1);

   Appendable format(Date var1, Appendable var2);

   /** @deprecated */
   @Deprecated
   StringBuffer format(Date var1, StringBuffer var2);

   String format(long var1);

   Appendable format(long var1, Appendable var3);

   /** @deprecated */
   @Deprecated
   StringBuffer format(long var1, StringBuffer var3);

   StringBuffer format(Object var1, StringBuffer var2, FieldPosition var3);

   Locale getLocale();

   String getPattern();

   TimeZone getTimeZone();
}
