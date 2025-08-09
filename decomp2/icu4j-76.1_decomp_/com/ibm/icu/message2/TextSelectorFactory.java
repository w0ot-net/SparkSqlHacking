package com.ibm.icu.message2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

class TextSelectorFactory implements SelectorFactory {
   public Selector createSelector(Locale locale, Map fixedOptions) {
      return new TextSelector();
   }

   private static class TextSelector implements Selector {
      private TextSelector() {
      }

      public List matches(Object value, List keys, Map variableOptions) {
         List<String> result = new ArrayList();
         if (value == null) {
            return result;
         } else {
            for(String key : keys) {
               if (this.matches(value, key)) {
                  result.add(key);
               }
            }

            result.sort(String::compareTo);
            return result;
         }
      }

      private boolean matches(Object value, String key) {
         return "*".equals(key) ? true : key.equals(Objects.toString(value));
      }
   }
}
