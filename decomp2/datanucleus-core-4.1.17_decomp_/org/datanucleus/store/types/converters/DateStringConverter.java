package org.datanucleus.store.types.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.util.Localiser;

public class DateStringConverter implements TypeConverter {
   private static final long serialVersionUID = 4638239842151376340L;
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

   public Date toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         try {
            return this.getFormatter().parse(str);
         } catch (ParseException pe) {
            throw new NucleusDataStoreException(Localiser.msg("016002", str, Date.class.getName()), pe);
         }
      }
   }

   public String toDatastoreType(Date date) {
      return date != null ? this.getFormatter().format(date) : null;
   }

   static class FormatterInfo {
      SimpleDateFormat formatter;
   }
}
