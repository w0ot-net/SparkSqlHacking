package org.apache.hadoop.hive.serde2.lazy.fast;

import java.nio.charset.StandardCharsets;

public class StringToDouble {
   static final int maxExponent = 511;
   static final double[] powersOf10 = new double[]{(double)10.0F, (double)100.0F, (double)10000.0F, (double)1.0E8F, 1.0E16, 1.0E32, 1.0E64, 1.0E128, 1.0E256};

   static double strtod(String s) {
      byte[] utf8 = s.getBytes(StandardCharsets.UTF_8);
      return strtod(utf8, 0, utf8.length);
   }

   public static double strtod(byte[] utf8, int offset, int length) {
      if (length == 0) {
         throw new NumberFormatException();
      } else {
         boolean signIsNegative = true;
         boolean expSignIsNegative = true;
         int p = offset;
         int end = offset + length;
         int exp = 0;

         for(int fracExp = 0; p < end && Character.isWhitespace(utf8[p]); ++p) {
         }

         while(end > p && Character.isWhitespace(utf8[end - 1])) {
            --end;
         }

         if (!testSimpleDecimal(utf8, p, end - p)) {
            return Double.parseDouble(new String(utf8, p, end - p, StandardCharsets.UTF_8));
         } else {
            if (utf8[p] == 45) {
               signIsNegative = true;
               ++p;
            } else {
               if (utf8[p] == 43) {
                  ++p;
               }

               signIsNegative = false;
            }

            int decPt = -1;
            int mantEnd = end - p;

            int mantSize;
            for(mantSize = 0; mantSize < mantEnd; ++mantSize) {
               int c = utf8[p];
               if (!isdigit(c)) {
                  if (c != 46 || decPt >= 0) {
                     break;
                  }

                  decPt = mantSize;
               }

               ++p;
            }

            p -= mantSize;
            if (decPt < 0) {
               decPt = mantSize;
            } else {
               --mantSize;
            }

            int var29;
            if (mantSize > 18) {
               var29 = decPt - 18;
               mantSize = 18;
            } else {
               var29 = decPt - mantSize;
            }

            if (mantSize == 0) {
               return signIsNegative ? (double)-0.0F : (double)0.0F;
            } else {
               double frac1;
               for(frac1 = (double)0.0F; mantSize > 9; --mantSize) {
                  int c = utf8[p];
                  ++p;
                  if (c == 46) {
                     c = utf8[p];
                     ++p;
                  }

                  frac1 = (double)10.0F * frac1 + (double)(c - 48);
               }

               double frac2;
               for(frac2 = (double)0.0F; mantSize > 0; --mantSize) {
                  int c = utf8[p];
                  ++p;
                  if (c == 46) {
                     c = utf8[p];
                     ++p;
                  }

                  frac2 = (double)10.0F * frac2 + (double)(c - 48);
               }

               double fraction = (double)1.0E9F * frac1 + frac2;
               if (p < end && (utf8[p] == 69 || utf8[p] == 101)) {
                  ++p;
                  if (p < end) {
                     if (utf8[p] == 45) {
                        expSignIsNegative = true;
                        ++p;
                     } else {
                        if (utf8[p] == 43) {
                           ++p;
                        }

                        expSignIsNegative = false;
                     }

                     while(p < end && isdigit(utf8[p])) {
                        exp = exp * 10 + (utf8[p] - 48);
                        ++p;
                     }
                  }
               }

               if (expSignIsNegative) {
                  exp = var29 - exp;
               } else {
                  exp = var29 + exp;
               }

               if (exp < 0) {
                  expSignIsNegative = true;
                  exp = -exp;
               } else {
                  expSignIsNegative = false;
               }

               if (exp > 511) {
                  exp = 511;
               }

               frac1 = (double)1.0F;

               for(int d = 0; exp != 0; ++d) {
                  if ((exp & 1) == 1) {
                     frac1 *= powersOf10[d];
                  }

                  exp >>= 1;
               }

               if (expSignIsNegative) {
                  fraction /= frac1;
               } else {
                  fraction *= frac1;
               }

               if (signIsNegative) {
                  return -fraction;
               } else {
                  return fraction;
               }
            }
         }
      }
   }

   private static boolean testSimpleDecimal(byte[] utf8, int off, int len) {
      if (len > 18) {
         return false;
      } else {
         int decimalPts = 0;
         int signs = 0;
         int nondigits = 0;
         int digits = 0;

         for(int i = off; i < len + off; ++i) {
            int c = utf8[i];
            if (c == 46) {
               ++decimalPts;
            } else if (c != 45 && c != 43) {
               if (!isdigit(c)) {
                  ++nondigits;
               } else {
                  ++digits;
               }
            } else {
               ++signs;
            }
         }

         return decimalPts <= 1 && signs <= 1 && nondigits == 0 && digits < 16;
      }
   }

   private static boolean isdigit(int c) {
      return 48 <= c && c <= 57;
   }
}
