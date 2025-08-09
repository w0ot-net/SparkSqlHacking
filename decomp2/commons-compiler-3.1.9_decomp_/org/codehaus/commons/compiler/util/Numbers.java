package org.codehaus.commons.compiler.util;

import org.codehaus.commons.nullanalysis.Nullable;

public final class Numbers {
   private static final int[] INT_LIMITS = new int[]{0, 0, Integer.MIN_VALUE, 1431655766, 1073741824, 858993460, 715827883, 613566757, 536870912, 477218589, 429496730, 390451573, 357913942, 330382100, 306783379, 286331154, 268435456, 252645136, 238609295, 226050911, 214748365, 204522253, 195225787, 186737709, 178956971, 171798692, 165191050, 159072863, 153391690, 148102321, 143165577, 138547333, 134217728, 130150525, 126322568, 122713352, 119304648};
   private static final long[] LONG_LIMITS = new long[]{0L, 0L, Long.MIN_VALUE, 6148914691236517206L, 4611686018427387904L, 3689348814741910324L, 3074457345618258603L, 2635249153387078803L, 2305843009213693952L, 2049638230412172402L, 1844674407370955162L, 1676976733973595602L, 1537228672809129302L, 1418980313362273202L, 1317624576693539402L, 1229782938247303442L, 1152921504606846976L, 1085102592571150096L, 1024819115206086201L, 970881267037344822L, 922337203685477581L, 878416384462359601L, 838488366986797801L, 802032351030850071L, 768614336404564651L, 737869762948382065L, 709490156681136601L, 683212743470724134L, 658812288346769701L, 636094623231363849L, 614891469123651721L, 595056260442243601L, 576460752303423488L, 558992244657865201L, 542551296285575048L, 527049830677415761L, 512409557603043101L};

   private Numbers() {
   }

   public static int parseUnsignedInt(@Nullable String s, int radix) throws NumberFormatException {
      if (s != null && !s.isEmpty()) {
         if (radix >= 2 && radix <= 36) {
            int limit = INT_LIMITS[radix];
            int result = 0;

            for(int i = 0; i < s.length(); ++i) {
               if (result < 0 || radix != 2 && result >= limit) {
                  throw new NumberFormatException("For input string \"" + s + "\" and radix " + radix);
               }

               int digitValue = Character.digit(s.charAt(i), radix);
               if (digitValue == -1) {
                  throw new NumberFormatException("For input string \"" + s + "\" and radix " + radix);
               }

               int result2 = result * radix;
               result = result2 + digitValue;
               if (result2 < 0 && result >= 0) {
                  throw new NumberFormatException("For input string \"" + s + "\" and radix " + radix);
               }
            }

            return result;
         } else {
            throw new NumberFormatException("Invalid radix " + radix);
         }
      } else {
         throw new NumberFormatException("null");
      }
   }

   public static long parseUnsignedLong(@Nullable String s, int radix) throws NumberFormatException {
      if (s != null && !s.isEmpty()) {
         if (radix >= 2 && radix <= 36) {
            long limit = LONG_LIMITS[radix];
            long result = 0L;

            for(int i = 0; i < s.length(); ++i) {
               if (result < 0L || radix != 2 && result >= limit) {
                  throw new NumberFormatException("For input string \"" + s + "\" and radix " + radix);
               }

               int digitValue = Character.digit(s.charAt(i), radix);
               if (digitValue == -1) {
                  throw new NumberFormatException("For input string \"" + s + "\" and radix " + radix);
               }

               long result2 = result * (long)radix;
               result = result2 + (long)digitValue;
               if (result2 < 0L && result >= 0L) {
                  throw new NumberFormatException("For input string \"" + s + "\" and radix " + radix);
               }
            }

            return result;
         } else {
            throw new NumberFormatException("Invalid radix " + radix);
         }
      } else {
         throw new NumberFormatException("null");
      }
   }
}
