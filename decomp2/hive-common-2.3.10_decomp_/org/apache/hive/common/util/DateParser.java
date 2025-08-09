package org.apache.hive.common.util;

import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class DateParser {
   private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   private final ParsePosition pos = new ParsePosition(0);

   public Date parseDate(String strValue) {
      Date result = new Date(0L);
      return this.parseDate(strValue, result) ? result : null;
   }

   public boolean parseDate(String strValue, Date result) {
      this.pos.setIndex(0);
      java.util.Date parsedVal = this.formatter.parse(strValue, this.pos);
      if (parsedVal == null) {
         return false;
      } else {
         result.setTime(parsedVal.getTime());
         return true;
      }
   }
}
