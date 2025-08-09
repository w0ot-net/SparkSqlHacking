package org.apache.datasketches.quantilescommon;

import java.util.Objects;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

public final class QuantilesUtil {
   public static final double tailRoundingFactor = (double)1.0E7F;

   private QuantilesUtil() {
   }

   public static final void checkNormalizedRankBounds(double nRank) {
      if (nRank < (double)0.0F || nRank > (double)1.0F) {
         throw new SketchesArgumentException("A normalized rank must be >= 0 and <= 1.0: " + nRank);
      }
   }

   public static final void checkDoublesSplitPointsOrder(double[] values) {
      Objects.requireNonNull(values);
      int len = values.length;
      if (len == 1 && Double.isNaN(values[0])) {
         throw new SketchesArgumentException("Values must be unique, monotonically increasing and not NaN.");
      } else {
         for(int j = 0; j < len - 1; ++j) {
            if (!(values[j] < values[j + 1])) {
               throw new SketchesArgumentException("Values must be unique, monotonically increasing and not NaN.");
            }
         }

      }
   }

   public static void checkLongsSplitPointsOrder(long[] values) {
      Objects.requireNonNull(values);
      int len = values.length;

      for(int j = 0; j < len - 1; ++j) {
         if (values[j] >= values[j + 1]) {
            throw new SketchesArgumentException("Values must be unique and monotonically increasing.");
         }
      }

   }

   public static final void checkFloatsSplitPointsOrder(float[] values) {
      Objects.requireNonNull(values);
      int len = values.length;
      if (len == 1 && Float.isNaN(values[0])) {
         throw new SketchesArgumentException("Values must be unique, monotonically increasing and not NaN.");
      } else {
         for(int j = 0; j < len - 1; ++j) {
            if (!(values[j] < values[j + 1])) {
               throw new SketchesArgumentException("Values must be unique, monotonically increasing and not NaN.");
            }
         }

      }
   }

   public static double[] equallySpacedDoubles(int num) {
      if (num < 1) {
         throw new IllegalArgumentException("num must be >= 1");
      } else {
         double[] out = new double[num + 1];
         out[0] = (double)0.0F;
         out[num] = (double)1.0F;
         double delta = (double)1.0F / (double)num;

         for(int i = 1; i < num; ++i) {
            out[i] = (double)i * delta;
         }

         return out;
      }
   }

   public static long[] equallySpacedLongs(long min, long max, int num) {
      if (num >= 1 && min >= 0L && max >= 1L && min < max && (long)num <= max - min) {
         long span = max - min;
         double[] splits = equallySpacedDoubles(num);
         int len = num + 1;
         long[] out = new long[len];
         long prev = -1L;

         for(int i = 0; i < len; ++i) {
            long cur = Math.round(splits[i] * (double)span);
            if (cur == prev) {
               ++cur;
            } else {
               prev = cur;
            }

            out[i] = min + cur;
         }

         return out;
      } else {
         throw new SketchesArgumentException("Improper inputs: n < 1, min < 0, max < 1, min >= max, or n > (max - min)");
      }
   }

   public static float[] evenlySpacedFloats(float value1, float value2, int num) {
      if (num < 2) {
         throw new SketchesArgumentException("num must be >= 2");
      } else {
         float[] out = new float[num];
         out[0] = value1;
         out[num - 1] = value2;
         if (num == 2) {
            return out;
         } else {
            float delta = (value2 - value1) / (float)(num - 1);

            for(int i = 1; i < num - 1; ++i) {
               out[i] = (float)i * delta + value1;
            }

            return out;
         }
      }
   }

   public static double[] evenlySpacedDoubles(double value1, double value2, int num) {
      if (num < 2) {
         throw new SketchesArgumentException("num must be >= 2");
      } else {
         double[] out = new double[num];
         out[0] = value1;
         out[num - 1] = value2;
         if (num == 2) {
            return out;
         } else {
            double delta = (value2 - value1) / (double)(num - 1);

            for(int i = 1; i < num - 1; ++i) {
               out[i] = (double)i * delta + value1;
            }

            return out;
         }
      }
   }

   public static double[] evenlyLogSpaced(double value1, double value2, int num) {
      if (num < 2) {
         throw new SketchesArgumentException("num must be >= 2");
      } else if (!(value1 <= (double)0.0F) && !(value2 <= (double)0.0F)) {
         double[] arr = evenlySpacedDoubles(Math.log(value1) / Util.LOG2, Math.log(value2) / Util.LOG2, num);

         for(int i = 0; i < arr.length; ++i) {
            arr[i] = Math.pow((double)2.0F, arr[i]);
         }

         return arr;
      } else {
         throw new SketchesArgumentException("value1 and value2 must be > 0.");
      }
   }

   public static double getNaturalRank(double normalizedRank, long totalN, QuantileSearchCriteria searchCrit) {
      double naturalRank = normalizedRank * (double)totalN;
      if ((double)totalN <= (double)1.0E7F) {
         naturalRank = (double)Math.round(naturalRank * (double)1.0E7F) / (double)1.0E7F;
      }

      return searchCrit == QuantileSearchCriteria.INCLUSIVE ? (double)((long)Math.ceil(naturalRank)) : (double)((long)Math.floor(naturalRank));
   }
}
