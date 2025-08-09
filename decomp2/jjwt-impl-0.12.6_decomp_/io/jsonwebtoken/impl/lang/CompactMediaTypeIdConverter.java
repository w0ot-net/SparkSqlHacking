package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;

public final class CompactMediaTypeIdConverter implements Converter {
   public static final Converter INSTANCE = new CompactMediaTypeIdConverter();
   private static final char FORWARD_SLASH = '/';
   private static final String APP_MEDIA_TYPE_PREFIX = "application/";

   static String compactIfPossible(String cty) {
      Assert.hasText(cty, "Value cannot be null or empty.");
      if (Strings.startsWithIgnoreCase(cty, "application/")) {
         for(int i = cty.length() - 1; i >= "application/".length(); --i) {
            char c = cty.charAt(i);
            if (c == '/') {
               return cty;
            }
         }

         return cty.substring("application/".length());
      } else {
         return cty;
      }
   }

   public Object applyTo(String s) {
      return compactIfPossible(s);
   }

   public String applyFrom(Object o) {
      Assert.notNull(o, "Value cannot be null.");
      String s = (String)Assert.isInstanceOf(String.class, o, "Value must be a string.");
      if (s.indexOf(47) < 0) {
         s = "application/" + s;
      }

      return s;
   }
}
