package org.apache.commons.text.lookup;

import org.apache.commons.lang3.StringUtils;

abstract class AbstractStringLookup implements StringLookup {
   protected static final char SPLIT_CH = ':';
   protected static final String SPLIT_STR = String.valueOf(':');

   static String toLookupKey(String left, String right) {
      return toLookupKey(left, SPLIT_STR, right);
   }

   static String toLookupKey(String left, String separator, String right) {
      return left + separator + right;
   }

   /** @deprecated */
   @Deprecated
   protected String substringAfter(String value, char ch) {
      return StringUtils.substringAfter(value, ch);
   }

   /** @deprecated */
   @Deprecated
   protected String substringAfter(String value, String str) {
      return StringUtils.substringAfter(value, str);
   }

   /** @deprecated */
   @Deprecated
   protected String substringAfterLast(String value, char ch) {
      return StringUtils.substringAfterLast(value, ch);
   }
}
