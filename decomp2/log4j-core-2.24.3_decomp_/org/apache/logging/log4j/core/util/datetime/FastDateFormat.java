package org.apache.logging.log4j.core.util.datetime;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class FastDateFormat extends Format implements DatePrinter {
   public static final int FULL = 0;
   public static final int LONG = 1;
   public static final int MEDIUM = 2;
   public static final int SHORT = 3;
   private static final FormatCache cache = new FormatCache() {
      protected FastDateFormat createInstance(final String pattern, final TimeZone timeZone, final Locale locale) {
         return new FastDateFormat(pattern, timeZone, locale);
      }
   };
   private final FastDatePrinter printer;

   public static FastDateFormat getInstance() {
      return (FastDateFormat)cache.getInstance();
   }

   public static FastDateFormat getInstance(final String pattern) {
      return (FastDateFormat)cache.getInstance(pattern, (TimeZone)null, (Locale)null);
   }

   public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone) {
      return (FastDateFormat)cache.getInstance(pattern, timeZone, (Locale)null);
   }

   public static FastDateFormat getInstance(final String pattern, final Locale locale) {
      return (FastDateFormat)cache.getInstance(pattern, (TimeZone)null, locale);
   }

   public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone, final Locale locale) {
      return (FastDateFormat)cache.getInstance(pattern, timeZone, locale);
   }

   public static FastDateFormat getDateInstance(final int style) {
      return (FastDateFormat)cache.getDateInstance(style, (TimeZone)null, (Locale)null);
   }

   public static FastDateFormat getDateInstance(final int style, final Locale locale) {
      return (FastDateFormat)cache.getDateInstance(style, (TimeZone)null, locale);
   }

   public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone) {
      return (FastDateFormat)cache.getDateInstance(style, timeZone, (Locale)null);
   }

   public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone, final Locale locale) {
      return (FastDateFormat)cache.getDateInstance(style, timeZone, locale);
   }

   public static FastDateFormat getTimeInstance(final int style) {
      return (FastDateFormat)cache.getTimeInstance(style, (TimeZone)null, (Locale)null);
   }

   public static FastDateFormat getTimeInstance(final int style, final Locale locale) {
      return (FastDateFormat)cache.getTimeInstance(style, (TimeZone)null, locale);
   }

   public static FastDateFormat getTimeInstance(final int style, final TimeZone timeZone) {
      return (FastDateFormat)cache.getTimeInstance(style, timeZone, (Locale)null);
   }

   public static FastDateFormat getTimeInstance(final int style, final TimeZone timeZone, final Locale locale) {
      return (FastDateFormat)cache.getTimeInstance(style, timeZone, locale);
   }

   public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle) {
      return (FastDateFormat)cache.getDateTimeInstance(dateStyle, timeStyle, (TimeZone)null, (Locale)null);
   }

   public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final Locale locale) {
      return (FastDateFormat)cache.getDateTimeInstance(dateStyle, timeStyle, (TimeZone)null, locale);
   }

   public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone) {
      return getDateTimeInstance(dateStyle, timeStyle, timeZone, (Locale)null);
   }

   public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone, final Locale locale) {
      return (FastDateFormat)cache.getDateTimeInstance(dateStyle, timeStyle, timeZone, locale);
   }

   protected FastDateFormat(final String pattern, final TimeZone timeZone, final Locale locale) {
      this(pattern, timeZone, locale, (Date)null);
   }

   protected FastDateFormat(final String pattern, final TimeZone timeZone, final Locale locale, final Date centuryStart) {
      this.printer = new FastDatePrinter(pattern, timeZone, locale);
   }

   public StringBuilder format(final Object obj, final StringBuilder toAppendTo, final FieldPosition pos) {
      return toAppendTo.append(this.printer.format(obj));
   }

   public String format(final long millis) {
      return this.printer.format(millis);
   }

   public String format(final Date date) {
      return this.printer.format(date);
   }

   public String format(final Calendar calendar) {
      return this.printer.format(calendar);
   }

   public Appendable format(final long millis, final Appendable buf) {
      return this.printer.format(millis, buf);
   }

   public Appendable format(final Date date, final Appendable buf) {
      return this.printer.format(date, buf);
   }

   public Appendable format(final Calendar calendar, final Appendable buf) {
      return this.printer.format(calendar, buf);
   }

   public String getPattern() {
      return this.printer.getPattern();
   }

   public TimeZone getTimeZone() {
      return this.printer.getTimeZone();
   }

   public Locale getLocale() {
      return this.printer.getLocale();
   }

   public int getMaxLengthEstimate() {
      return this.printer.getMaxLengthEstimate();
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof FastDateFormat)) {
         return false;
      } else {
         FastDateFormat other = (FastDateFormat)obj;
         return this.printer.equals(other.printer);
      }
   }

   public int hashCode() {
      return this.printer.hashCode();
   }

   public String toString() {
      return "FastDateFormat[" + this.printer.getPattern() + "," + this.printer.getLocale() + "," + this.printer.getTimeZone().getID() + "]";
   }
}
