package scala.collection;

import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.runtime.BoxesRunTime;

public final class StringParsers$ {
   public static final StringParsers$ MODULE$ = new StringParsers$();

   private final int intOverflowBoundary() {
      return -214748364;
   }

   private final int intOverflowDigit() {
      return 9;
   }

   private final long longOverflowBoundary() {
      return -922337203685477580L;
   }

   private final int longOverflowDigit() {
      return 9;
   }

   private final boolean POS() {
      return true;
   }

   private final int decValue(final char ch) {
      return Character.digit(ch, 10);
   }

   private final Option stepToOverflow(final String from, final int len, final int agg, final boolean isPositive, final int min) {
      return this.rec$1(1, agg, min, len, isPositive, from);
   }

   private final boolean isDigit(final char c) {
      return c >= '0' && c <= '9';
   }

   public final Option parseBool(final String from) {
      if (from.equalsIgnoreCase("true")) {
         return new Some(true);
      } else {
         return (Option)(from.equalsIgnoreCase("false") ? new Some(false) : None$.MODULE$);
      }
   }

   public final Option parseByte(final String from) {
      int len = from.length();
      if (len == 0) {
         return None$.MODULE$;
      } else {
         char first = from.charAt(0);
         int v = Character.digit(first, 10);
         if (len == 1) {
            return (Option)(v > -1 ? new Some((byte)v) : None$.MODULE$);
         } else if (v > -1) {
            int var18 = -v;
            byte stepToOverflow_min = -128;
            boolean stepToOverflow_isPositive = true;
            int stepToOverflow_agg = var18;
            Option var19 = this.rec$1(1, stepToOverflow_agg, stepToOverflow_min, len, stepToOverflow_isPositive, from);
            if (var19 == null) {
               throw null;
            } else {
               Option map_this = var19;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some((byte)BoxesRunTime.unboxToInt(map_this.get())));
            }
         } else if (first == '+') {
            byte stepToOverflow_min = -128;
            boolean stepToOverflow_isPositive = true;
            int stepToOverflow_agg = 0;
            Option var17 = this.rec$1(1, stepToOverflow_agg, stepToOverflow_min, len, stepToOverflow_isPositive, from);
            if (var17 == null) {
               throw null;
            } else {
               Option map_this = var17;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some((byte)BoxesRunTime.unboxToInt(map_this.get())));
            }
         } else if (first == '-') {
            byte stepToOverflow_min = -128;
            boolean stepToOverflow_isPositive = false;
            int stepToOverflow_agg = 0;
            Option var10000 = this.rec$1(1, stepToOverflow_agg, stepToOverflow_min, len, stepToOverflow_isPositive, from);
            if (var10000 == null) {
               throw null;
            } else {
               Option map_this = var10000;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some((byte)BoxesRunTime.unboxToInt(map_this.get())));
            }
         } else {
            return None$.MODULE$;
         }
      }
   }

   public final Option parseShort(final String from) {
      int len = from.length();
      if (len == 0) {
         return None$.MODULE$;
      } else {
         char first = from.charAt(0);
         int v = Character.digit(first, 10);
         if (len == 1) {
            return (Option)(v > -1 ? new Some((short)v) : None$.MODULE$);
         } else if (v > -1) {
            int var18 = -v;
            short stepToOverflow_min = Short.MIN_VALUE;
            boolean stepToOverflow_isPositive = true;
            int stepToOverflow_agg = var18;
            Option var19 = this.rec$1(1, stepToOverflow_agg, stepToOverflow_min, len, stepToOverflow_isPositive, from);
            if (var19 == null) {
               throw null;
            } else {
               Option map_this = var19;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some((short)BoxesRunTime.unboxToInt(map_this.get())));
            }
         } else if (first == '+') {
            short stepToOverflow_min = Short.MIN_VALUE;
            boolean stepToOverflow_isPositive = true;
            int stepToOverflow_agg = 0;
            Option var17 = this.rec$1(1, stepToOverflow_agg, stepToOverflow_min, len, stepToOverflow_isPositive, from);
            if (var17 == null) {
               throw null;
            } else {
               Option map_this = var17;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some((short)BoxesRunTime.unboxToInt(map_this.get())));
            }
         } else if (first == '-') {
            short stepToOverflow_min = Short.MIN_VALUE;
            boolean stepToOverflow_isPositive = false;
            int stepToOverflow_agg = 0;
            Option var10000 = this.rec$1(1, stepToOverflow_agg, stepToOverflow_min, len, stepToOverflow_isPositive, from);
            if (var10000 == null) {
               throw null;
            } else {
               Option map_this = var10000;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some((short)BoxesRunTime.unboxToInt(map_this.get())));
            }
         } else {
            return None$.MODULE$;
         }
      }
   }

   public final Option parseInt(final String from) {
      int len = from.length();
      if (len == 0) {
         return None$.MODULE$;
      } else {
         char first = from.charAt(0);
         int v = Character.digit(first, 10);
         if (len == 1) {
            return (Option)(v > -1 ? new Some(v) : None$.MODULE$);
         } else if (v > -1) {
            return this.step$1(1, -v, true, len, from);
         } else if (first == '+') {
            return this.step$1(1, 0, true, len, from);
         } else {
            return (Option)(first == '-' ? this.step$1(1, 0, false, len, from) : None$.MODULE$);
         }
      }
   }

   public final Option parseLong(final String from) {
      int len = from.length();
      if (len == 0) {
         return None$.MODULE$;
      } else {
         char first = from.charAt(0);
         long v = (long)Character.digit(first, 10);
         if (len == 1) {
            return (Option)(v > -1L ? new Some(v) : None$.MODULE$);
         } else if (v > -1L) {
            return this.step$2(1, -v, true, len, from);
         } else if (first == '+') {
            return this.step$2(1, 0L, true, len, from);
         } else {
            return (Option)(first == '-' ? this.step$2(1, 0L, false, len, from) : None$.MODULE$);
         }
      }
   }

   public final boolean checkFloatFormat(final String format) {
      int x$2 = 0;
      int indexWhere$extension_len = format.length();
      int indexWhere$extension_i = x$2;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         if ($anonfun$checkFloatFormat$12(format.charAt(indexWhere$extension_i))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int unspacedStart = var10000;
      int x$4 = Integer.MAX_VALUE;
      int lastIndexWhere$extension_len = format.length();
      scala.math.package$ var18 = scala.math.package$.MODULE$;
      int lastIndexWhere$extension_min_y = lastIndexWhere$extension_len - 1;
      int lastIndexWhere$extension_i = Math.min(x$4, lastIndexWhere$extension_min_y);

      while(true) {
         if (lastIndexWhere$extension_i < 0) {
            var19 = -1;
            break;
         }

         if ($anonfun$checkFloatFormat$13(format.charAt(lastIndexWhere$extension_i))) {
            var19 = lastIndexWhere$extension_i;
            break;
         }

         --lastIndexWhere$extension_i;
      }

      int unspacedEnd = var19 + 1;
      if (unspacedStart != -1 && unspacedStart < unspacedEnd && unspacedEnd > 0) {
         char startchar = format.charAt(unspacedStart);
         int unsigned = startchar != '-' && startchar != '+' ? unspacedStart : unspacedStart + 1;
         if (unsigned >= unspacedEnd) {
            return false;
         } else if (format.charAt(unsigned) == 'N') {
            String var21 = format.substring(unsigned, unspacedEnd);
            String var8 = "NaN";
            if (var21 != null) {
               if (var21.equals(var8)) {
                  return true;
               }
            }

            return false;
         } else if (format.charAt(unsigned) == 'I') {
            String var20 = format.substring(unsigned, unspacedEnd);
            String var9 = "Infinity";
            if (var20 != null) {
               if (var20.equals(var9)) {
                  return true;
               }
            }

            return false;
         } else {
            char endchar = format.charAt(unspacedEnd - 1);
            int desuffixed = endchar != 'f' && endchar != 'F' && endchar != 'd' && endchar != 'D' ? unspacedEnd : unspacedEnd - 1;
            int len = desuffixed - unsigned;
            if (len <= 0) {
               return false;
            } else if (len >= 2 && (format.charAt(unsigned + 1) == 'x' || format.charAt(unsigned + 1) == 'X')) {
               return format.charAt(unsigned) == '0' && this.isHexFloatLiteral$1(unsigned + 2, desuffixed, format);
            } else {
               return this.isDecFloatLiteral$1(unsigned, desuffixed, format);
            }
         }
      } else {
         return false;
      }
   }

   public Option parseFloat(final String from) {
      return (Option)(this.checkFloatFormat(from) ? new Some(Float.parseFloat(from)) : None$.MODULE$);
   }

   public Option parseDouble(final String from) {
      return (Option)(this.checkFloatFormat(from) ? new Some(Double.parseDouble(from)) : None$.MODULE$);
   }

   private final Option rec$1(final int i, final int agg, final int min$1, final int len$1, final boolean isPositive$1, final String from$1) {
      while(agg >= min$1) {
         if (i == len$1) {
            if (!isPositive$1) {
               return new Some(agg);
            }

            if (agg == min$1) {
               return None$.MODULE$;
            }

            return new Some(-agg);
         }

         int digit = Character.digit(from$1.charAt(i), 10);
         if (digit == -1) {
            return None$.MODULE$;
         }

         int var10000 = i + 1;
         agg = agg * 10 - digit;
         i = var10000;
      }

      return None$.MODULE$;
   }

   // $FF: synthetic method
   public static final byte $anonfun$parseByte$1(final int x$1) {
      return (byte)x$1;
   }

   // $FF: synthetic method
   public static final byte $anonfun$parseByte$2(final int x$2) {
      return (byte)x$2;
   }

   // $FF: synthetic method
   public static final byte $anonfun$parseByte$3(final int x$3) {
      return (byte)x$3;
   }

   // $FF: synthetic method
   public static final short $anonfun$parseShort$1(final int x$4) {
      return (short)x$4;
   }

   // $FF: synthetic method
   public static final short $anonfun$parseShort$2(final int x$5) {
      return (short)x$5;
   }

   // $FF: synthetic method
   public static final short $anonfun$parseShort$3(final int x$6) {
      return (short)x$6;
   }

   private final Option step$1(final int i, final int agg, final boolean isPositive, final int len$2, final String from$2) {
      while(i != len$2) {
         if (agg < -214748364) {
            return None$.MODULE$;
         }

         int digit = Character.digit(from$2.charAt(i), 10);
         if (digit == -1 || agg == -214748364 && digit == 9) {
            return None$.MODULE$;
         }

         int var10000 = i + 1;
         int var10001 = agg * 10 - digit;
         isPositive = isPositive;
         agg = var10001;
         i = var10000;
      }

      if (!isPositive) {
         return new Some(agg);
      } else if (agg == Integer.MIN_VALUE) {
         return None$.MODULE$;
      } else {
         return new Some(-agg);
      }
   }

   private final Option step$2(final int i, final long agg, final boolean isPositive, final int len$3, final String from$3) {
      while(i != len$3) {
         if (agg < -922337203685477580L) {
            return None$.MODULE$;
         }

         int digit = Character.digit(from$3.charAt(i), 10);
         if (digit == -1 || agg == -922337203685477580L && digit == 9) {
            return None$.MODULE$;
         }

         int var10000 = i + 1;
         long var10001 = agg * 10L - (long)digit;
         isPositive = isPositive;
         agg = var10001;
         i = var10000;
      }

      if (isPositive && agg == Long.MIN_VALUE) {
         return None$.MODULE$;
      } else if (isPositive) {
         return new Some(-agg);
      } else {
         return new Some(agg);
      }
   }

   private final boolean rec$2(final int i, final int end$1, final Function1 pred$1, final String format$1) {
      while(true) {
         if (i < end$1) {
            if (BoxesRunTime.unboxToBoolean(pred$1.apply(format$1.charAt(i)))) {
               ++i;
               continue;
            }

            return false;
         }

         return true;
      }
   }

   private final boolean forAllBetween$1(final int start, final int end, final Function1 pred, final String format$1) {
      for(int rec$2_i = start; rec$2_i < end; ++rec$2_i) {
         if (!BoxesRunTime.unboxToBoolean(pred.apply(format$1.charAt(rec$2_i)))) {
            return false;
         }
      }

      return true;
   }

   private final int rec$3(final int i, final int until$1, final Function1 predicate$1, final String format$1) {
      while(i < until$1 && BoxesRunTime.unboxToBoolean(predicate$1.apply(format$1.charAt(i)))) {
         ++i;
      }

      return i;
   }

   private final int skipIndexWhile$1(final Function1 predicate, final int from, final int until, final String format$1) {
      int rec$3_i;
      for(rec$3_i = from; rec$3_i < until && BoxesRunTime.unboxToBoolean(predicate.apply(format$1.charAt(rec$3_i))); ++rec$3_i) {
      }

      return rec$3_i;
   }

   private static final boolean isHexDigit$1(final char ch) {
      return ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$1(final char ch) {
      return isHexDigit$1(ch);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$2(final char ch) {
      return isHexDigit$1(ch);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$3(final char ch) {
      return isHexDigit$1(ch);
   }

   private final boolean prefixOK$1(final int startIndex, final int endIndex, final String format$1) {
      int len = endIndex - startIndex;
      if (len > 0) {
         if (format$1.charAt(startIndex) == '.') {
            if (len > 1) {
               int forAllBetween$1_rec$2_i = startIndex + 1;

               boolean var10000;
               while(true) {
                  if (forAllBetween$1_rec$2_i < endIndex) {
                     if (isHexDigit$1(format$1.charAt(forAllBetween$1_rec$2_i))) {
                        ++forAllBetween$1_rec$2_i;
                        continue;
                     }

                     var10000 = false;
                     break;
                  }

                  var10000 = true;
                  break;
               }

               if (var10000) {
                  return true;
               }
            }
         } else {
            int skipIndexWhile$1_rec$3_i;
            for(skipIndexWhile$1_rec$3_i = startIndex; skipIndexWhile$1_rec$3_i < endIndex && isHexDigit$1(format$1.charAt(skipIndexWhile$1_rec$3_i)); ++skipIndexWhile$1_rec$3_i) {
            }

            if (skipIndexWhile$1_rec$3_i >= endIndex) {
               return true;
            }

            if (format$1.charAt(skipIndexWhile$1_rec$3_i) == '.') {
               int forAllBetween$1_rec$2_i = skipIndexWhile$1_rec$3_i + 1;

               boolean var8;
               while(true) {
                  if (forAllBetween$1_rec$2_i >= endIndex) {
                     var8 = true;
                     break;
                  }

                  if (!isHexDigit$1(format$1.charAt(forAllBetween$1_rec$2_i))) {
                     var8 = false;
                     break;
                  }

                  ++forAllBetween$1_rec$2_i;
               }

               if (var8) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$4(final char c) {
      return MODULE$.isDigit(c);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$5(final char c) {
      return MODULE$.isDigit(c);
   }

   private final boolean postfixOK$1(final int startIndex, final int endIndex, final String format$1) {
      if (startIndex < endIndex) {
         int forAllBetween$1_rec$2_i = startIndex;

         boolean var10000;
         while(true) {
            if (forAllBetween$1_rec$2_i < endIndex) {
               if ($anonfun$checkFloatFormat$4(format$1.charAt(forAllBetween$1_rec$2_i))) {
                  ++forAllBetween$1_rec$2_i;
                  continue;
               }

               var10000 = false;
               break;
            }

            var10000 = true;
            break;
         }

         if (var10000) {
            return true;
         }

         char startchar = format$1.charAt(startIndex);
         if ((startchar == '+' || startchar == '-') && endIndex - startIndex > 1) {
            int forAllBetween$1_rec$2_i = startIndex + 1;

            while(true) {
               if (forAllBetween$1_rec$2_i < endIndex) {
                  if ($anonfun$checkFloatFormat$5(format$1.charAt(forAllBetween$1_rec$2_i))) {
                     ++forAllBetween$1_rec$2_i;
                     continue;
                  }

                  var10000 = false;
               } else {
                  var10000 = true;
               }

               if (var10000) {
                  return true;
               }
               break;
            }
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$6(final char ch) {
      return ch == 'p' || ch == 'P';
   }

   private final boolean isHexFloatLiteral$1(final int startIndex, final int endIndex, final String format$1) {
      int indexWhere$extension_len = format$1.length();
      int indexWhere$extension_i = startIndex;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         if ($anonfun$checkFloatFormat$6(format$1.charAt(indexWhere$extension_i))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int pIndex = var10000;
      return pIndex <= endIndex && this.prefixOK$1(startIndex, pIndex, format$1) && this.postfixOK$1(pIndex + 1, endIndex, format$1);
   }

   private static final boolean isExp$1(final char c) {
      return c == 'e' || c == 'E';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$7(final char c) {
      return MODULE$.isDigit(c);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$8(final char c) {
      return MODULE$.isDigit(c);
   }

   private final boolean expOK$1(final int startIndex, final int endIndex, final String format$1) {
      if (startIndex < endIndex) {
         char startChar = format$1.charAt(startIndex);
         if (startChar != '+' && startChar != '-') {
            int skipIndexWhile$1_rec$3_i;
            for(skipIndexWhile$1_rec$3_i = startIndex; skipIndexWhile$1_rec$3_i < endIndex && $anonfun$checkFloatFormat$8(format$1.charAt(skipIndexWhile$1_rec$3_i)); ++skipIndexWhile$1_rec$3_i) {
            }

            if (skipIndexWhile$1_rec$3_i == endIndex) {
               return true;
            }
         } else if (endIndex > startIndex + 1) {
            int skipIndexWhile$1_rec$3_i;
            for(skipIndexWhile$1_rec$3_i = startIndex + 1; skipIndexWhile$1_rec$3_i < endIndex && $anonfun$checkFloatFormat$7(format$1.charAt(skipIndexWhile$1_rec$3_i)); ++skipIndexWhile$1_rec$3_i) {
            }

            if (skipIndexWhile$1_rec$3_i == endIndex) {
               return true;
            }
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$9(final char c) {
      return MODULE$.isDigit(c);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$10(final char c) {
      return MODULE$.isDigit(c);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$11(final char c) {
      return MODULE$.isDigit(c);
   }

   private final boolean isDecFloatLiteral$1(final int startIndex, final int endIndex, final String format$1) {
      char startChar = format$1.charAt(startIndex);
      if (startChar == '.') {
         int skipIndexWhile$1_rec$3_i;
         for(skipIndexWhile$1_rec$3_i = startIndex + 1; skipIndexWhile$1_rec$3_i < endIndex && $anonfun$checkFloatFormat$9(format$1.charAt(skipIndexWhile$1_rec$3_i)); ++skipIndexWhile$1_rec$3_i) {
         }

         return skipIndexWhile$1_rec$3_i > startIndex + 1 && (skipIndexWhile$1_rec$3_i >= endIndex || isExp$1(format$1.charAt(skipIndexWhile$1_rec$3_i)) && this.expOK$1(skipIndexWhile$1_rec$3_i + 1, endIndex, format$1));
      } else if (startChar < '0' || startChar > '9') {
         return false;
      } else {
         int skipIndexWhile$1_rec$3_i;
         for(skipIndexWhile$1_rec$3_i = startIndex; skipIndexWhile$1_rec$3_i < endIndex && $anonfun$checkFloatFormat$10(format$1.charAt(skipIndexWhile$1_rec$3_i)); ++skipIndexWhile$1_rec$3_i) {
         }

         if (skipIndexWhile$1_rec$3_i != endIndex) {
            if (format$1.charAt(skipIndexWhile$1_rec$3_i) == '.') {
               int skipIndexWhile$1_rec$3_i;
               for(skipIndexWhile$1_rec$3_i = skipIndexWhile$1_rec$3_i + 1; skipIndexWhile$1_rec$3_i < endIndex && $anonfun$checkFloatFormat$11(format$1.charAt(skipIndexWhile$1_rec$3_i)); ++skipIndexWhile$1_rec$3_i) {
               }

               if (skipIndexWhile$1_rec$3_i >= endIndex || isExp$1(format$1.charAt(skipIndexWhile$1_rec$3_i)) && this.expOK$1(skipIndexWhile$1_rec$3_i + 1, endIndex, format$1)) {
                  return true;
               }
            } else if (isExp$1(format$1.charAt(skipIndexWhile$1_rec$3_i)) && this.expOK$1(skipIndexWhile$1_rec$3_i + 1, endIndex, format$1)) {
               return true;
            }

            return false;
         } else {
            return true;
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$12(final char ch) {
      return ch > ' ';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$checkFloatFormat$13(final char ch) {
      return ch > ' ';
   }

   private StringParsers$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$parseByte$1$adapted(final Object x$1) {
      return BoxesRunTime.boxToByte($anonfun$parseByte$1(BoxesRunTime.unboxToInt(x$1)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$parseByte$2$adapted(final Object x$2) {
      return BoxesRunTime.boxToByte($anonfun$parseByte$2(BoxesRunTime.unboxToInt(x$2)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$parseByte$3$adapted(final Object x$3) {
      return BoxesRunTime.boxToByte($anonfun$parseByte$3(BoxesRunTime.unboxToInt(x$3)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$parseShort$1$adapted(final Object x$4) {
      return BoxesRunTime.boxToShort($anonfun$parseShort$1(BoxesRunTime.unboxToInt(x$4)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$parseShort$2$adapted(final Object x$5) {
      return BoxesRunTime.boxToShort($anonfun$parseShort$2(BoxesRunTime.unboxToInt(x$5)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$parseShort$3$adapted(final Object x$6) {
      return BoxesRunTime.boxToShort($anonfun$parseShort$3(BoxesRunTime.unboxToInt(x$6)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$12$adapted(final Object ch) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$12(BoxesRunTime.unboxToChar(ch)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$13$adapted(final Object ch) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$13(BoxesRunTime.unboxToChar(ch)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$1$adapted(final Object ch) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$1(BoxesRunTime.unboxToChar(ch)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$2$adapted(final Object ch) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$2(BoxesRunTime.unboxToChar(ch)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$3$adapted(final Object ch) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$3(BoxesRunTime.unboxToChar(ch)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$4$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$4(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$5$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$5(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$6$adapted(final Object ch) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$6(BoxesRunTime.unboxToChar(ch)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$7$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$7(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$8$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$8(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$9$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$9(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$10$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$10(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$checkFloatFormat$11$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$checkFloatFormat$11(BoxesRunTime.unboxToChar(c)));
   }
}
