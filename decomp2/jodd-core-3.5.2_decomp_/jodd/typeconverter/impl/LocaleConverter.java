package jodd.typeconverter.impl;

import java.util.Locale;
import jodd.typeconverter.TypeConverter;
import jodd.util.LocaleUtil;

public class LocaleConverter implements TypeConverter {
   public Locale convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == Locale.class ? (Locale)value : LocaleUtil.getLocale(value.toString());
      }
   }
}
