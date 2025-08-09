package org.datanucleus.store.types.converters;

import java.util.Locale;
import org.datanucleus.util.I18nUtils;

public class LocaleStringConverter implements TypeConverter, ColumnLengthDefiningTypeConverter {
   private static final long serialVersionUID = -5566584819761013454L;

   public Locale toMemberType(String str) {
      return str == null ? null : I18nUtils.getLocaleFromString(str);
   }

   public String toDatastoreType(Locale loc) {
      return loc != null ? loc.toString() : null;
   }

   public int getDefaultColumnLength(int columnPosition) {
      return columnPosition != 0 ? -1 : 20;
   }
}
