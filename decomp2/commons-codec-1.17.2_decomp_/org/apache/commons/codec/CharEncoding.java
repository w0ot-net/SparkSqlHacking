package org.apache.commons.codec;

import java.nio.charset.StandardCharsets;

public class CharEncoding {
   public static final String ISO_8859_1;
   public static final String US_ASCII;
   public static final String UTF_16;
   public static final String UTF_16BE;
   public static final String UTF_16LE;
   public static final String UTF_8;

   static {
      ISO_8859_1 = StandardCharsets.ISO_8859_1.name();
      US_ASCII = StandardCharsets.US_ASCII.name();
      UTF_16 = StandardCharsets.UTF_16.name();
      UTF_16BE = StandardCharsets.UTF_16BE.name();
      UTF_16LE = StandardCharsets.UTF_16LE.name();
      UTF_8 = StandardCharsets.UTF_8.name();
   }
}
