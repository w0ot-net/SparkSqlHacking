package org.apache.ivy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
   public static final String DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

   private DateUtil() {
   }

   public static String format(Date date) {
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      return format.format(date);
   }

   public static Date parse(String date) throws ParseException {
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      return format.parse(date);
   }
}
