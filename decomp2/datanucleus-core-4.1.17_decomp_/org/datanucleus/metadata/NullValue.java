package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public enum NullValue {
   EXCEPTION,
   DEFAULT,
   NONE;

   public static NullValue getNullValue(String value) {
      if (StringUtils.isWhitespace(value)) {
         return NONE;
      } else if (DEFAULT.toString().equalsIgnoreCase(value)) {
         return DEFAULT;
      } else if (EXCEPTION.toString().equalsIgnoreCase(value)) {
         return EXCEPTION;
      } else {
         return NONE.toString().equalsIgnoreCase(value) ? NONE : NONE;
      }
   }
}
