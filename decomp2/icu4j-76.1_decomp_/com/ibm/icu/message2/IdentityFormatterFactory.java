package com.ibm.icu.message2;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

class IdentityFormatterFactory implements FormatterFactory {
   public Formatter createFormatter(Locale locale, Map fixedOptions) {
      return new IdentityFormatterImpl();
   }

   private static class IdentityFormatterImpl implements Formatter {
      private IdentityFormatterImpl() {
      }

      public FormattedPlaceholder format(Object toFormat, Map variableOptions) {
         return new FormattedPlaceholder(toFormat, new PlainStringFormattedValue(Objects.toString(toFormat)));
      }

      public String formatToString(Object toFormat, Map variableOptions) {
         return this.format(toFormat, variableOptions).toString();
      }
   }
}
