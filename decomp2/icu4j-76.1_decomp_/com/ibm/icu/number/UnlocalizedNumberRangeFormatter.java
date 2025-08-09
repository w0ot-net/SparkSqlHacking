package com.ibm.icu.number;

import com.ibm.icu.util.ULocale;
import java.util.Locale;

public class UnlocalizedNumberRangeFormatter extends NumberRangeFormatterSettings {
   UnlocalizedNumberRangeFormatter() {
      super((NumberRangeFormatterSettings)null, 0, (Object)null);
   }

   UnlocalizedNumberRangeFormatter(NumberRangeFormatterSettings parent, int key, Object value) {
      super(parent, key, value);
   }

   public LocalizedNumberRangeFormatter locale(Locale locale) {
      return new LocalizedNumberRangeFormatter(this, 1, ULocale.forLocale(locale));
   }

   public LocalizedNumberRangeFormatter locale(ULocale locale) {
      return new LocalizedNumberRangeFormatter(this, 1, locale);
   }

   UnlocalizedNumberRangeFormatter create(int key, Object value) {
      return new UnlocalizedNumberRangeFormatter(this, key, value);
   }
}
