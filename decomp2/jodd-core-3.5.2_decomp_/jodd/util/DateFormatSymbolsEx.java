package jodd.util;

import java.text.DateFormatSymbols;
import java.util.Locale;

public class DateFormatSymbolsEx {
   protected final String[] months;
   protected final String[] shortMonths;
   protected final String[] weekdays;
   protected final String[] shortWeekdays;
   protected final String[] eras;
   protected final String[] ampms;

   public DateFormatSymbolsEx(Locale locale) {
      DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
      this.months = dateFormatSymbols.getMonths();
      this.shortMonths = dateFormatSymbols.getShortMonths();
      this.weekdays = dateFormatSymbols.getWeekdays();
      this.shortWeekdays = dateFormatSymbols.getShortWeekdays();
      this.eras = dateFormatSymbols.getEras();
      this.ampms = dateFormatSymbols.getAmPmStrings();
   }

   public String getMonth(int i) {
      return this.months[i];
   }

   public String getShortMonth(int i) {
      return this.shortMonths[i];
   }

   public String getWeekday(int i) {
      return this.weekdays[i];
   }

   public String getShortWeekday(int i) {
      return this.shortWeekdays[i];
   }

   public String getBcEra() {
      return this.eras[0];
   }

   public String getAdEra() {
      return this.eras[1];
   }

   public String getAM() {
      return this.ampms[0];
   }

   public String getPM() {
      return this.ampms[1];
   }
}
