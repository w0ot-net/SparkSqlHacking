package org.joda.time;

import org.joda.time.format.DateTimeFormat;

public class IllegalInstantException extends IllegalArgumentException {
   private static final long serialVersionUID = 2858712538216L;

   public IllegalInstantException(String var1) {
      super(var1);
   }

   public IllegalInstantException(long var1, String var3) {
      super(createMessage(var1, var3));
   }

   private static String createMessage(long var0, String var2) {
      String var3 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print((ReadableInstant)(new Instant(var0)));
      String var4 = var2 != null ? " (" + var2 + ")" : "";
      return "Illegal instant due to time zone offset transition (daylight savings time 'gap'): " + var3 + var4;
   }

   public static boolean isIllegalInstant(Throwable var0) {
      if (var0 instanceof IllegalInstantException) {
         return true;
      } else {
         return var0.getCause() != null && var0.getCause() != var0 ? isIllegalInstant(var0.getCause()) : false;
      }
   }
}
