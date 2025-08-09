package org.apache.commons.lang3;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;

/** @deprecated */
@Deprecated
public class CharEncoding {
   public static final String ISO_8859_1;
   public static final String US_ASCII;
   public static final String UTF_16;
   public static final String UTF_16BE;
   public static final String UTF_16LE;
   public static final String UTF_8;

   /** @deprecated */
   @Deprecated
   public static boolean isSupported(String name) {
      if (name == null) {
         return false;
      } else {
         try {
            return Charset.isSupported(name);
         } catch (IllegalCharsetNameException var2) {
            return false;
         }
      }
   }

   static {
      ISO_8859_1 = StandardCharsets.ISO_8859_1.name();
      US_ASCII = StandardCharsets.US_ASCII.name();
      UTF_16 = StandardCharsets.UTF_16.name();
      UTF_16BE = StandardCharsets.UTF_16BE.name();
      UTF_16LE = StandardCharsets.UTF_16LE.name();
      UTF_8 = StandardCharsets.UTF_8.name();
   }
}
