package javolution.lang;

import java.util.Random;

public final class MathLib {
   private static final Random RANDOM = new Random();
   private static final byte[] BIT_LENGTH = new byte[]{0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
   private static final long MASK_63 = Long.MAX_VALUE;
   private static final long MASK_32 = 4294967295L;
   private static final int[] POW5_INT = new int[]{1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
   private static final double LOG2_DIV_LOG10 = 0.3010299956639812;
   public static final double E = Math.E;
   public static final double PI = Math.PI;
   public static final double HALF_PI = (Math.PI / 2D);
   public static final double TWO_PI = (Math.PI * 2D);
   public static final double FOUR_PI = 12.566370614359172;
   public static final double PI_SQUARE = 9.869604401089358;
   public static final double LOG2 = 0.6931471805599453;
   public static final double LOG10 = 2.302585092994046;
   public static final double SQRT2 = 1.4142135623730951;
   public static final double NaN = Double.NaN;
   public static final double Infinity = Double.POSITIVE_INFINITY;
   private static double INV_LOG10 = 0.4342944819032518;
   private static final double NORMALIZATION_FACTOR = (double)4.656613E-10F;
   static final double[] atanhi = new double[]{0.4636476090008061, (Math.PI / 4D), 0.982793723247329, (Math.PI / 2D)};
   static final double[] atanlo = new double[]{2.2698777452961687E-17, 3.061616997868383E-17, 1.3903311031230998E-17, 6.123233995736766E-17};
   static final double[] aT = new double[]{0.3333333333333293, -0.19999999999876483, 0.14285714272503466, -0.11111110405462356, 0.09090887133436507, -0.0769187620504483, 0.06661073137387531, -0.058335701337905735, 0.049768779946159324, -0.036531572744216916, 0.016285820115365782};
   static final double one = (double)1.0F;
   static final double huge = 1.0E300;
   static final double ln2_hi = 0.6931471803691238;
   static final double ln2_lo = 1.9082149292705877E-10;
   static final double two54 = (double)1.80143985E16F;
   static final double Lg1 = 0.6666666666666735;
   static final double Lg2 = 0.3999999999940942;
   static final double Lg3 = 0.2857142874366239;
   static final double Lg4 = 0.22222198432149784;
   static final double Lg5 = 0.1818357216161805;
   static final double Lg6 = 0.15313837699209373;
   static final double Lg7 = 0.14798198605116586;
   static final double zero = (double)0.0F;
   static final double[] halF = new double[]{(double)0.5F, (double)-0.5F};
   static final double twom1000 = 9.332636185032189E-302;
   static final double o_threshold = 709.782712893384;
   static final double u_threshold = -745.1332191019411;
   static final double[] ln2HI = new double[]{0.6931471803691238, -0.6931471803691238};
   static final double[] ln2LO = new double[]{1.9082149292705877E-10, -1.9082149292705877E-10};
   static final double invln2 = 1.4426950408889634;
   static final double P1 = 0.16666666666666602;
   static final double P2 = -0.0027777777777015593;
   static final double P3 = 6.613756321437934E-5;
   static final double P4 = -1.6533902205465252E-6;
   static final double P5 = 4.1381367970572385E-8;

   private MathLib() {
   }

   public static int random(int min, int max) {
      int next = RANDOM.nextInt();
      if (next >= min && next <= max) {
         return next;
      } else {
         next -= Integer.MIN_VALUE;
         if (next >= min && next <= max) {
            return next;
         } else {
            int interval = 1 + max - min;
            if (interval <= 0) {
               throw new Error("Interval [" + min + ".." + max + "] error");
            } else {
               return abs(next % interval) + min;
            }
         }
      }
   }

   public static long random(long min, long max) {
      long next = RANDOM.nextLong();
      if (next >= min && next <= max) {
         return next;
      } else {
         next += Long.MIN_VALUE;
         if (next >= min && next <= max) {
            return next;
         } else {
            long interval = 1L + max - min;
            if (interval <= 0L) {
               throw new Error("Interval error");
            } else {
               return abs(next % interval) + min;
            }
         }
      }
   }

   public static float random(float min, float max) {
      return (float)random((double)min, (double)max);
   }

   public static double random(double min, double max) {
      double next = RANDOM.nextDouble();
      return min + next * max - next * min;
   }

   public static int bitLength(int i) {
      if (i < 0) {
         ++i;
         i = -i;
      }

      return i < 65536 ? (i < 256 ? BIT_LENGTH[i] : BIT_LENGTH[i >>> 8] + 8) : (i < 16777216 ? BIT_LENGTH[i >>> 16] + 16 : BIT_LENGTH[i >>> 24] + 24);
   }

   public static int bitLength(long l) {
      int i = (int)(l >> 32);
      if (i > 0) {
         return i < 65536 ? (i < 256 ? BIT_LENGTH[i] + 32 : BIT_LENGTH[i >>> 8] + 40) : (i < 16777216 ? BIT_LENGTH[i >>> 16] + 48 : BIT_LENGTH[i >>> 24] + 56);
      } else if (i < 0) {
         return bitLength(-(++l));
      } else {
         i = (int)l;
         return i < 0 ? 32 : (i < 65536 ? (i < 256 ? BIT_LENGTH[i] : BIT_LENGTH[i >>> 8] + 8) : (i < 16777216 ? BIT_LENGTH[i >>> 16] + 16 : BIT_LENGTH[i >>> 24] + 24));
      }
   }

   public static int bitCount(long longValue) {
      longValue -= longValue >>> 1 & 6148914691236517205L;
      longValue = (longValue & 3689348814741910323L) + (longValue >>> 2 & 3689348814741910323L);
      longValue = longValue + (longValue >>> 4) & 1085102592571150095L;
      longValue += longValue >>> 8;
      longValue += longValue >>> 16;
      longValue += longValue >>> 32;
      return (int)longValue & 127;
   }

   public static int numberOfLeadingZeros(long longValue) {
      if (longValue == 0L) {
         return 64;
      } else {
         int n = 1;
         int x = (int)(longValue >>> 32);
         if (x == 0) {
            n += 32;
            x = (int)longValue;
         }

         if (x >>> 16 == 0) {
            n += 16;
            x <<= 16;
         }

         if (x >>> 24 == 0) {
            n += 8;
            x <<= 8;
         }

         if (x >>> 28 == 0) {
            n += 4;
            x <<= 4;
         }

         if (x >>> 30 == 0) {
            n += 2;
            x <<= 2;
         }

         n -= x >>> 31;
         return n;
      }
   }

   public static int numberOfTrailingZeros(long longValue) {
      if (longValue == 0L) {
         return 64;
      } else {
         int n = 63;
         int y = (int)longValue;
         int x;
         if (y != 0) {
            n -= 32;
            x = y;
         } else {
            x = (int)(longValue >>> 32);
         }

         y = x << 16;
         if (y != 0) {
            n -= 16;
            x = y;
         }

         y = x << 8;
         if (y != 0) {
            n -= 8;
            x = y;
         }

         y = x << 4;
         if (y != 0) {
            n -= 4;
            x = y;
         }

         y = x << 2;
         if (y != 0) {
            n -= 2;
            x = y;
         }

         return n - (x << 1 >>> 31);
      }
   }

   public static int digitLength(int i) {
      if (i >= 0) {
         return i >= 100000 ? (i >= 10000000 ? (i >= 1000000000 ? 10 : (i >= 100000000 ? 9 : 8)) : (i >= 1000000 ? 7 : 6)) : (i >= 100 ? (i >= 10000 ? 5 : (i >= 1000 ? 4 : 3)) : (i >= 10 ? 2 : 1));
      } else {
         return i == Integer.MIN_VALUE ? 10 : digitLength(-i);
      }
   }

   public static int digitLength(long l) {
      if (l >= 0L) {
         return l <= 2147483647L ? digitLength((int)l) : (l >= 100000000000000L ? (l >= 10000000000000000L ? (l >= 1000000000000000000L ? 19 : (l >= 100000000000000000L ? 18 : 17)) : (l >= 1000000000000000L ? 16 : 15)) : (l >= 100000000000L ? (l >= 10000000000000L ? 14 : (l >= 1000000000000L ? 13 : 12)) : (l >= 10000000000L ? 11 : 10)));
      } else {
         return l == Long.MIN_VALUE ? 19 : digitLength(-l);
      }
   }

   public static double toDoublePow2(long m, int n) {
      if (m == 0L) {
         return (double)0.0F;
      } else if (m == Long.MIN_VALUE) {
         return toDoublePow2(-4611686018427387904L, n + 1);
      } else if (m < 0L) {
         return -toDoublePow2(-m, n);
      } else {
         int bitLength = bitLength(m);
         int shift = bitLength - 53;
         long exp = 1075L + (long)n + (long)shift;
         if (exp >= 2047L) {
            return Double.POSITIVE_INFINITY;
         } else if (exp <= 0L) {
            return exp <= -54L ? (double)0.0F : toDoublePow2(m, n + 54) / (double)1.80143985E16F;
         } else {
            long bits = shift > 0 ? (m >> shift) + (m >> shift - 1 & 1L) : m << -shift;
            if (bits >> 52 != 1L && ++exp >= 2047L) {
               return Double.POSITIVE_INFINITY;
            } else {
               bits &= 4503599627370495L;
               bits |= exp << 52;
               return Double.longBitsToDouble(bits);
            }
         }
      }
   }

   public static double toDoublePow10(long m, int n) {
      if (m == 0L) {
         return (double)0.0F;
      } else if (m == Long.MIN_VALUE) {
         return toDoublePow10(-922337203685477580L, n + 1);
      } else if (m < 0L) {
         return -toDoublePow10(-m, n);
      } else if (n >= 0) {
         if (n > 308) {
            return Double.POSITIVE_INFINITY;
         } else {
            long x0 = 0L;
            long x1 = 0L;
            long x2 = m & 4294967295L;
            long x3 = m >>> 32;
            int pow2 = 0;

            while(n != 0) {
               int i = n >= POW5_INT.length ? POW5_INT.length - 1 : n;
               int coef = POW5_INT[i];
               if ((int)x0 != 0) {
                  x0 *= (long)coef;
               }

               if ((int)x1 != 0) {
                  x1 *= (long)coef;
               }

               x2 *= (long)coef;
               long var32 = x3 * (long)coef;
               x1 += x0 >>> 32;
               x0 &= 4294967295L;
               x2 += x1 >>> 32;
               x1 &= 4294967295L;
               x3 = var32 + (x2 >>> 32);
               x2 &= 4294967295L;
               pow2 += i;
               n -= i;
               long carry = x3 >>> 32;
               if (carry != 0L) {
                  x0 = x1;
                  x1 = x2;
                  x2 = x3 & 4294967295L;
                  x3 = carry;
                  pow2 += 32;
               }
            }

            int shift = 31 - bitLength(x3);
            pow2 -= shift;
            long mantissa = shift < 0 ? x3 << 31 | x2 >>> 1 : (x3 << 32 | x2) << shift | x1 >>> 32 - shift;
            return toDoublePow2(mantissa, pow2);
         }
      } else if (n < -344) {
         return (double)0.0F;
      } else {
         long x1 = m;
         long x0 = 0L;
         int pow2 = 0;

         while(true) {
            int shift = 63 - bitLength(x1);
            x1 <<= shift;
            x1 |= x0 >>> 63 - shift;
            x0 = x0 << shift & Long.MAX_VALUE;
            pow2 -= shift;
            if (n == 0) {
               return toDoublePow2(x1, pow2);
            }

            int i = -n >= POW5_INT.length ? POW5_INT.length - 1 : -n;
            int divisor = POW5_INT[i];
            long wh = x1 >>> 32;
            long qh = wh / (long)divisor;
            long r = wh - qh * (long)divisor;
            long wl = r << 32 | x1 & 4294967295L;
            long ql = wl / (long)divisor;
            r = wl - ql * (long)divisor;
            x1 = qh << 32 | ql;
            wh = r << 31 | x0 >>> 32;
            qh = wh / (long)divisor;
            r = wh - qh * (long)divisor;
            wl = r << 32 | x0 & 4294967295L;
            ql = wl / (long)divisor;
            x0 = qh << 32 | ql;
            n += i;
            pow2 -= i;
         }
      }
   }

   public static long toLongPow2(double d, int n) {
      long bits = Double.doubleToLongBits(d);
      boolean isNegative = bits >> 63 != 0L;
      int exp = (int)(bits >> 52) & 2047;
      long m = bits & 4503599627370495L;
      if (exp == 2047) {
         throw new ArithmeticException("Cannot convert to long (Infinity or NaN)");
      } else if (exp == 0) {
         return m == 0L ? 0L : toLongPow2(d * (double)1.80143985E16F, n - 54);
      } else {
         m |= 4503599627370496L;
         long shift = (long)exp - 1023L - 52L + (long)n;
         if (shift <= -64L) {
            return 0L;
         } else if (shift >= 11L) {
            throw new ArithmeticException("Cannot convert to long (overflow)");
         } else {
            m = shift >= 0L ? m << (int)shift : (m >> (int)(-shift)) + (m >> (int)(-(shift + 1L)) & 1L);
            return isNegative ? -m : m;
         }
      }
   }

   public static long toLongPow10(double d, int n) {
      long bits = Double.doubleToLongBits(d);
      boolean isNegative = bits >> 63 != 0L;
      int exp = (int)(bits >> 52) & 2047;
      long m = bits & 4503599627370495L;
      if (exp == 2047) {
         throw new ArithmeticException("Cannot convert to long (Infinity or NaN)");
      } else if (exp == 0) {
         return m == 0L ? 0L : toLongPow10(d * 1.0E16, n - 16);
      } else {
         m |= 4503599627370496L;
         int pow2 = exp - 1023 - 52;
         if (n >= 0) {
            long x0 = 0L;
            long x1 = 0L;
            long x2 = m & 4294967295L;
            long x3 = m >>> 32;

            while(n != 0) {
               int i = n >= POW5_INT.length ? POW5_INT.length - 1 : n;
               int coef = POW5_INT[i];
               if ((int)x0 != 0) {
                  x0 *= (long)coef;
               }

               if ((int)x1 != 0) {
                  x1 *= (long)coef;
               }

               x2 *= (long)coef;
               long divisor = x3 * (long)coef;
               x1 += x0 >>> 32;
               x0 &= 4294967295L;
               x2 += x1 >>> 32;
               x1 &= 4294967295L;
               x3 = divisor + (x2 >>> 32);
               x2 &= 4294967295L;
               pow2 += i;
               n -= i;
               long carry = x3 >>> 32;
               if (carry != 0L) {
                  x0 = x1;
                  x1 = x2;
                  x2 = x3 & 4294967295L;
                  x3 = carry;
                  pow2 += 32;
               }
            }

            int shift = 31 - bitLength(x3);
            pow2 -= shift;
            m = shift < 0 ? x3 << 31 | x2 >>> 1 : (x3 << 32 | x2) << shift | x1 >>> 32 - shift;
         } else {
            long x1 = m;
            long x0 = 0L;

            while(true) {
               int shift = 63 - bitLength(x1);
               x1 <<= shift;
               x1 |= x0 >>> 63 - shift;
               x0 = x0 << shift & Long.MAX_VALUE;
               pow2 -= shift;
               if (n == 0) {
                  m = x1;
                  break;
               }

               int i = -n >= POW5_INT.length ? POW5_INT.length - 1 : -n;
               int divisor = POW5_INT[i];
               long wh = x1 >>> 32;
               long qh = wh / (long)divisor;
               long r = wh - qh * (long)divisor;
               long wl = r << 32 | x1 & 4294967295L;
               long ql = wl / (long)divisor;
               r = wl - ql * (long)divisor;
               x1 = qh << 32 | ql;
               wh = r << 31 | x0 >>> 32;
               qh = wh / (long)divisor;
               r = wh - qh * (long)divisor;
               wl = r << 32 | x0 & 4294967295L;
               ql = wl / (long)divisor;
               x0 = qh << 32 | ql;
               n += i;
               pow2 -= i;
            }
         }

         if (pow2 > 0) {
            throw new ArithmeticException("Overflow");
         } else if (pow2 < -63) {
            return 0L;
         } else {
            m = (m >> -pow2) + (m >> -(pow2 + 1) & 1L);
            return isNegative ? -m : m;
         }
      }
   }

   public static int floorLog2(double d) {
      if (d <= (double)0.0F) {
         throw new ArithmeticException("Negative number or zero");
      } else {
         long bits = Double.doubleToLongBits(d);
         int exp = (int)(bits >> 52) & 2047;
         if (exp == 2047) {
            throw new ArithmeticException("Infinity or NaN");
         } else {
            return exp == 0 ? floorLog2(d * (double)1.80143985E16F) - 54 : exp - 1023;
         }
      }
   }

   public static int floorLog10(double d) {
      int guess = (int)(0.3010299956639812 * (double)floorLog2(d));
      double pow10 = toDoublePow10(1L, guess);
      if (pow10 <= d && pow10 * (double)10.0F > d) {
         return guess;
      } else {
         return pow10 > d ? guess - 1 : guess + 1;
      }
   }

   public static double toRadians(double degrees) {
      return degrees * (Math.PI / 180D);
   }

   public static double toDegrees(double radians) {
      return radians * (180D / Math.PI);
   }

   public static double sqrt(double x) {
      return Math.sqrt(x);
   }

   public static double rem(double x, double y) {
      double tmp = x / y;
      return abs(tmp) <= (double)Long.MAX_VALUE ? x - (double)round(tmp) * y : Double.NaN;
   }

   public static double ceil(double x) {
      return Math.ceil(x);
   }

   public static double floor(double x) {
      return Math.floor(x);
   }

   public static double sin(double radians) {
      return Math.sin(radians);
   }

   public static double cos(double radians) {
      return Math.cos(radians);
   }

   public static double tan(double radians) {
      return Math.tan(radians);
   }

   public static double asin(double x) {
      if (!(x < (double)-1.0F) && !(x > (double)1.0F)) {
         if (x == (double)-1.0F) {
            return (-Math.PI / 2D);
         } else {
            return x == (double)1.0F ? (Math.PI / 2D) : atan(x / sqrt((double)1.0F - x * x));
         }
      } else {
         return Double.NaN;
      }
   }

   public static double acos(double x) {
      return (Math.PI / 2D) - asin(x);
   }

   public static double atan(double x) {
      return _atan(x);
   }

   public static double atan2(double y, double x) {
      double epsilon = 1.0E-128;
      if (abs(x) > 1.0E-128) {
         double temp = atan(abs(y) / abs(x));
         if (x < (double)0.0F) {
            temp = Math.PI - temp;
         }

         if (y < (double)0.0F) {
            temp = (Math.PI * 2D) - temp;
         }

         return temp;
      } else if (y > 1.0E-128) {
         return (Math.PI / 2D);
      } else {
         return y < -1.0E-128 ? (Math.PI * 1.5D) : (double)0.0F;
      }
   }

   public static double sinh(double x) {
      return (exp(x) - exp(-x)) * (double)0.5F;
   }

   public static double cosh(double x) {
      return (exp(x) + exp(-x)) * (double)0.5F;
   }

   public static double tanh(double x) {
      return (exp((double)2.0F * x) - (double)1.0F) / (exp((double)2.0F * x) + (double)1.0F);
   }

   public static double exp(double x) {
      return _ieee754_exp(x);
   }

   public static double log(double x) {
      return _ieee754_log(x);
   }

   public static double log10(double x) {
      return log(x) * INV_LOG10;
   }

   public static double pow(double x, double y) {
      return Math.pow(x, y);
   }

   public static int round(float f) {
      return (int)floor((double)(f + 0.5F));
   }

   public static long round(double d) {
      return (long)floor(d + (double)0.5F);
   }

   public static double random() {
      return (double)random(0, Integer.MAX_VALUE) * (double)4.656613E-10F;
   }

   public static int abs(int i) {
      return i < 0 ? -i : i;
   }

   public static long abs(long l) {
      return l < 0L ? -l : l;
   }

   public static float abs(float f) {
      return f < 0.0F ? -f : f;
   }

   public static double abs(double d) {
      return d < (double)0.0F ? -d : d;
   }

   public static int max(int x, int y) {
      return x >= y ? x : y;
   }

   public static long max(long x, long y) {
      return x >= y ? x : y;
   }

   public static float max(float x, float y) {
      return x >= y ? x : y;
   }

   public static double max(double x, double y) {
      return x >= y ? x : y;
   }

   public static int min(int x, int y) {
      return x < y ? x : y;
   }

   public static long min(long x, long y) {
      return x < y ? x : y;
   }

   public static float min(float x, float y) {
      return x < y ? x : y;
   }

   public static double min(double x, double y) {
      return x < y ? x : y;
   }

   static double _atan(double x) {
      long xBits = Double.doubleToLongBits(x);
      int __HIx = (int)(xBits >> 32);
      int __LOx = (int)xBits;
      int ix = __HIx & Integer.MAX_VALUE;
      if (ix < 1141899264) {
         int id;
         if (ix < 1071382528) {
            if (ix < 1042284544 && 1.0E300 + x > (double)1.0F) {
               return x;
            }

            id = -1;
         } else {
            x = abs(x);
            if (ix < 1072889856) {
               if (ix < 1072037888) {
                  id = 0;
                  x = ((double)2.0F * x - (double)1.0F) / ((double)2.0F + x);
               } else {
                  id = 1;
                  x = (x - (double)1.0F) / (x + (double)1.0F);
               }
            } else if (ix < 1073971200) {
               id = 2;
               x = (x - (double)1.5F) / ((double)1.0F + (double)1.5F * x);
            } else {
               id = 3;
               x = (double)-1.0F / x;
            }
         }

         double z = x * x;
         double w = z * z;
         double s1 = z * (aT[0] + w * (aT[2] + w * (aT[4] + w * (aT[6] + w * (aT[8] + w * aT[10])))));
         double s2 = w * (aT[1] + w * (aT[3] + w * (aT[5] + w * (aT[7] + w * aT[9]))));
         if (id < 0) {
            return x - x * (s1 + s2);
         } else {
            z = atanhi[id] - (x * (s1 + s2) - atanlo[id] - x);
            return __HIx < 0 ? -z : z;
         }
      } else if (ix <= 2146435072 && (ix != 2146435072 || __LOx == 0)) {
         return __HIx > 0 ? atanhi[3] + atanlo[3] : -atanhi[3] - atanlo[3];
      } else {
         return x + x;
      }
   }

   static double _ieee754_log(double x) {
      long xBits = Double.doubleToLongBits(x);
      int hx = (int)(xBits >> 32);
      int lx = (int)xBits;
      int k = 0;
      if (hx < 1048576) {
         if ((hx & Integer.MAX_VALUE | lx) == 0) {
            return Double.NEGATIVE_INFINITY;
         }

         if (hx < 0) {
            return (x - x) / (double)0.0F;
         }

         k -= 54;
         x *= (double)1.80143985E16F;
         xBits = Double.doubleToLongBits(x);
         hx = (int)(xBits >> 32);
      }

      if (hx >= 2146435072) {
         return x + x;
      } else {
         k += (hx >> 20) - 1023;
         hx &= 1048575;
         int i = hx + 614244 & 1048576;
         xBits = Double.doubleToLongBits(x);
         int HIx = hx | i ^ 1072693248;
         xBits = ((long)HIx & 4294967295L) << 32 | xBits & 4294967295L;
         x = Double.longBitsToDouble(xBits);
         k += i >> 20;
         double f = x - (double)1.0F;
         if ((1048575 & 2 + hx) < 3) {
            if (f == (double)0.0F) {
               if (k == 0) {
                  return (double)0.0F;
               } else {
                  double dk = (double)k;
                  return dk * 0.6931471803691238 + dk * 1.9082149292705877E-10;
               }
            } else {
               double R = f * f * ((double)0.5F - 0.3333333333333333 * f);
               if (k == 0) {
                  return f - R;
               } else {
                  double dk = (double)k;
                  return dk * 0.6931471803691238 - (R - dk * 1.9082149292705877E-10 - f);
               }
            }
         } else {
            double s = f / ((double)2.0F + f);
            double dk = (double)k;
            double z = s * s;
            i = hx - 398458;
            double w = z * z;
            int j = 440401 - hx;
            double t1 = w * (0.3999999999940942 + w * (0.22222198432149784 + w * 0.15313837699209373));
            double t2 = z * (0.6666666666666735 + w * (0.2857142874366239 + w * (0.1818357216161805 + w * 0.14798198605116586)));
            i |= j;
            double R = t2 + t1;
            if (i > 0) {
               double hfsq = (double)0.5F * f * f;
               return k == 0 ? f - (hfsq - s * (hfsq + R)) : dk * 0.6931471803691238 - (hfsq - (s * (hfsq + R) + dk * 1.9082149292705877E-10) - f);
            } else {
               return k == 0 ? f - s * (f - R) : dk * 0.6931471803691238 - (s * (f - R) - dk * 1.9082149292705877E-10 - f);
            }
         }
      }
   }

   static double _ieee754_exp(double x) {
      double hi = (double)0.0F;
      double lo = (double)0.0F;
      int k = 0;
      long xBits = Double.doubleToLongBits(x);
      int __HIx = (int)(xBits >> 32);
      int __LOx = (int)xBits;
      int xsb = __HIx >> 31 & 1;
      int hx = __HIx & Integer.MAX_VALUE;
      if (hx >= 1082535490) {
         if (hx >= 2146435072) {
            if ((hx & 1048575 | __LOx) != 0) {
               return x + x;
            }

            return xsb == 0 ? x : (double)0.0F;
         }

         if (x > 709.782712893384) {
            return Double.POSITIVE_INFINITY;
         }

         if (x < -745.1332191019411) {
            return (double)0.0F;
         }
      }

      if (hx > 1071001154) {
         if (hx < 1072734898) {
            hi = x - ln2HI[xsb];
            lo = ln2LO[xsb];
            k = 1 - xsb - xsb;
         } else {
            k = (int)(1.4426950408889634 * x + halF[xsb]);
            double t = (double)k;
            hi = x - t * ln2HI[0];
            lo = t * ln2LO[0];
         }

         x = hi - lo;
      } else if (hx < 1043333120) {
         if (1.0E300 + x > (double)1.0F) {
            return (double)1.0F + x;
         }
      } else {
         k = 0;
      }

      double t = x * x;
      double c = x - t * (0.16666666666666602 + t * (-0.0027777777777015593 + t * (6.613756321437934E-5 + t * (-1.6533902205465252E-6 + t * 4.1381367970572385E-8))));
      if (k == 0) {
         return (double)1.0F - (x * c / (c - (double)2.0F) - x);
      } else {
         double y = (double)1.0F - (lo - x * c / ((double)2.0F - c) - hi);
         long yBits = Double.doubleToLongBits(y);
         int __HIy = (int)(yBits >> 32);
         if (k >= -1021) {
            __HIy += k << 20;
            yBits = ((long)__HIy & 4294967295L) << 32 | yBits & 4294967295L;
            y = Double.longBitsToDouble(yBits);
            return y;
         } else {
            __HIy += k + 1000 << 20;
            yBits = ((long)__HIy & 4294967295L) << 32 | yBits & 4294967295L;
            y = Double.longBitsToDouble(yBits);
            return y * 9.332636185032189E-302;
         }
      }
   }
}
