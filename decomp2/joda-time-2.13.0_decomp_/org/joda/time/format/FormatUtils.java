package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

public class FormatUtils {
   private static final double LOG_10 = Math.log((double)10.0F);

   private FormatUtils() {
   }

   public static void appendPaddedInteger(StringBuffer var0, int var1, int var2) {
      try {
         appendPaddedInteger((Appendable)var0, var1, var2);
      } catch (IOException var4) {
      }

   }

   public static void appendPaddedInteger(Appendable var0, int var1, int var2) throws IOException {
      if (var1 < 0) {
         var0.append('-');
         if (var1 == Integer.MIN_VALUE) {
            while(var2 > 10) {
               var0.append('0');
               --var2;
            }

            var0.append("2147483648");
            return;
         }

         var1 = -var1;
      }

      if (var1 >= 10) {
         if (var1 >= 100) {
            int var4;
            if (var1 < 1000) {
               var4 = 3;
            } else if (var1 < 10000) {
               var4 = 4;
            } else {
               var4 = (int)(Math.log((double)var1) / LOG_10) + 1;
            }

            while(var2 > var4) {
               var0.append('0');
               --var2;
            }

            var0.append(Integer.toString(var1));
         } else {
            while(var2 > 2) {
               var0.append('0');
               --var2;
            }

            int var3 = (var1 + 1) * 13421772 >> 27;
            var0.append((char)(var3 + 48));
            var0.append((char)(var1 - (var3 << 3) - (var3 << 1) + 48));
         }
      } else {
         while(var2 > 1) {
            var0.append('0');
            --var2;
         }

         var0.append((char)(var1 + 48));
      }

   }

   public static void appendPaddedInteger(StringBuffer var0, long var1, int var3) {
      try {
         appendPaddedInteger((Appendable)var0, var1, var3);
      } catch (IOException var5) {
      }

   }

   public static void appendPaddedInteger(Appendable var0, long var1, int var3) throws IOException {
      int var4 = (int)var1;
      if ((long)var4 == var1) {
         appendPaddedInteger(var0, var4, var3);
      } else if (var3 <= 19) {
         var0.append(Long.toString(var1));
      } else {
         if (var1 < 0L) {
            var0.append('-');
            if (var1 == Long.MIN_VALUE) {
               while(var3 > 19) {
                  var0.append('0');
                  --var3;
               }

               var0.append("9223372036854775808");
               return;
            }

            var1 = -var1;
         }

         for(int var5 = (int)(Math.log((double)var1) / LOG_10) + 1; var3 > var5; --var3) {
            var0.append('0');
         }

         var0.append(Long.toString(var1));
      }

   }

   public static void writePaddedInteger(Writer var0, int var1, int var2) throws IOException {
      if (var1 < 0) {
         var0.write(45);
         if (var1 == Integer.MIN_VALUE) {
            while(var2 > 10) {
               var0.write(48);
               --var2;
            }

            var0.write("2147483648");
            return;
         }

         var1 = -var1;
      }

      if (var1 >= 10) {
         if (var1 >= 100) {
            int var4;
            if (var1 < 1000) {
               var4 = 3;
            } else if (var1 < 10000) {
               var4 = 4;
            } else {
               var4 = (int)(Math.log((double)var1) / LOG_10) + 1;
            }

            while(var2 > var4) {
               var0.write(48);
               --var2;
            }

            var0.write(Integer.toString(var1));
         } else {
            while(var2 > 2) {
               var0.write(48);
               --var2;
            }

            int var3 = (var1 + 1) * 13421772 >> 27;
            var0.write(var3 + 48);
            var0.write(var1 - (var3 << 3) - (var3 << 1) + 48);
         }
      } else {
         while(var2 > 1) {
            var0.write(48);
            --var2;
         }

         var0.write(var1 + 48);
      }

   }

   public static void writePaddedInteger(Writer var0, long var1, int var3) throws IOException {
      int var4 = (int)var1;
      if ((long)var4 == var1) {
         writePaddedInteger(var0, var4, var3);
      } else if (var3 <= 19) {
         var0.write(Long.toString(var1));
      } else {
         if (var1 < 0L) {
            var0.write(45);
            if (var1 == Long.MIN_VALUE) {
               while(var3 > 19) {
                  var0.write(48);
                  --var3;
               }

               var0.write("9223372036854775808");
               return;
            }

            var1 = -var1;
         }

         for(int var5 = (int)(Math.log((double)var1) / LOG_10) + 1; var3 > var5; --var3) {
            var0.write(48);
         }

         var0.write(Long.toString(var1));
      }

   }

   public static void appendUnpaddedInteger(StringBuffer var0, int var1) {
      try {
         appendUnpaddedInteger((Appendable)var0, var1);
      } catch (IOException var3) {
      }

   }

   public static void appendUnpaddedInteger(Appendable var0, int var1) throws IOException {
      if (var1 < 0) {
         var0.append('-');
         if (var1 == Integer.MIN_VALUE) {
            var0.append("2147483648");
            return;
         }

         var1 = -var1;
      }

      if (var1 < 10) {
         var0.append((char)(var1 + 48));
      } else if (var1 < 100) {
         int var2 = (var1 + 1) * 13421772 >> 27;
         var0.append((char)(var2 + 48));
         var0.append((char)(var1 - (var2 << 3) - (var2 << 1) + 48));
      } else {
         var0.append(Integer.toString(var1));
      }

   }

   public static void appendUnpaddedInteger(StringBuffer var0, long var1) {
      try {
         appendUnpaddedInteger((Appendable)var0, var1);
      } catch (IOException var4) {
      }

   }

   public static void appendUnpaddedInteger(Appendable var0, long var1) throws IOException {
      int var3 = (int)var1;
      if ((long)var3 == var1) {
         appendUnpaddedInteger(var0, var3);
      } else {
         var0.append(Long.toString(var1));
      }

   }

   public static void writeUnpaddedInteger(Writer var0, int var1) throws IOException {
      if (var1 < 0) {
         var0.write(45);
         if (var1 == Integer.MIN_VALUE) {
            var0.write("2147483648");
            return;
         }

         var1 = -var1;
      }

      if (var1 < 10) {
         var0.write(var1 + 48);
      } else if (var1 < 100) {
         int var2 = (var1 + 1) * 13421772 >> 27;
         var0.write(var2 + 48);
         var0.write(var1 - (var2 << 3) - (var2 << 1) + 48);
      } else {
         var0.write(Integer.toString(var1));
      }

   }

   public static void writeUnpaddedInteger(Writer var0, long var1) throws IOException {
      int var3 = (int)var1;
      if ((long)var3 == var1) {
         writeUnpaddedInteger(var0, var3);
      } else {
         var0.write(Long.toString(var1));
      }

   }

   public static int calculateDigitCount(long var0) {
      if (var0 < 0L) {
         return var0 != Long.MIN_VALUE ? calculateDigitCount(-var0) + 1 : 20;
      } else {
         return var0 < 10L ? 1 : (var0 < 100L ? 2 : (var0 < 1000L ? 3 : (var0 < 10000L ? 4 : (int)(Math.log((double)var0) / LOG_10) + 1)));
      }
   }

   static int parseTwoDigits(CharSequence var0, int var1) {
      int var2 = var0.charAt(var1) - 48;
      return (var2 << 3) + (var2 << 1) + var0.charAt(var1 + 1) - 48;
   }

   static String createErrorMessage(String var0, int var1) {
      int var2 = var1 + 32;
      String var3;
      if (var0.length() <= var2 + 3) {
         var3 = var0;
      } else {
         var3 = var0.substring(0, var2).concat("...");
      }

      if (var1 <= 0) {
         return "Invalid format: \"" + var3 + '"';
      } else {
         return var1 >= var0.length() ? "Invalid format: \"" + var3 + "\" is too short" : "Invalid format: \"" + var3 + "\" is malformed at \"" + var3.substring(var1) + '"';
      }
   }
}
