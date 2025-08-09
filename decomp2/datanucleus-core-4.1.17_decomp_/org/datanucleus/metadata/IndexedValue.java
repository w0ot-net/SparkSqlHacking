package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public enum IndexedValue {
   TRUE,
   FALSE,
   UNIQUE;

   public static IndexedValue getIndexedValue(String value) {
      if (StringUtils.isWhitespace(value)) {
         return null;
      } else if (TRUE.toString().equals(value)) {
         return TRUE;
      } else if (FALSE.toString().equals(value)) {
         return FALSE;
      } else {
         return UNIQUE.toString().equals(value) ? UNIQUE : TRUE;
      }
   }
}
