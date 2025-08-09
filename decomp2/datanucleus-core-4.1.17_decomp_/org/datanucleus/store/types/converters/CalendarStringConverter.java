package org.datanucleus.store.types.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class CalendarStringConverter implements TypeConverter {
   private static final long serialVersionUID = -4905708644688677004L;
   private static final ThreadLocal formatterThreadInfo = new ThreadLocal() {
      protected FormatterInfo initialValue() {
         return new FormatterInfo();
      }
   };

   private DateFormat getFormatter() {
      FormatterInfo formatInfo = (FormatterInfo)formatterThreadInfo.get();
      if (formatInfo.formatter == null) {
         formatInfo.formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
      }

      return formatInfo.formatter;
   }

   public Calendar toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            Date date = this.getFormatter().parse(str);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
         } catch (ParseException pe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", str, Calendar.class.getName()), pe);
         }
      }
   }

   public String toDatastoreType(Calendar cal) {
      return cal != null ? this.getFormatter().format(cal.getTime()) : null;
   }

   static class FormatterInfo {
      SimpleDateFormat formatter;
   }
}
