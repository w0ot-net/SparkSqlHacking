package scala.collection;

import scala.Array$;
import scala.Function1;
import scala.Predef$;

public final class BitSetOps$ {
   public static final BitSetOps$ MODULE$ = new BitSetOps$();

   public final int LogWL() {
      return 6;
   }

   public final int WordLength() {
      return 64;
   }

   public final int MaxSize() {
      return 33554432;
   }

   public long[] updateArray(final long[] elems, final int idx, final long w) {
      int len;
      for(len = elems.length; len > 0 && (elems[len - 1] == 0L || w == 0L && idx == len - 1); --len) {
      }

      int newlen = len;
      if (idx >= len && w != 0L) {
         newlen = idx + 1;
      }

      long[] newelems = new long[newlen];
      Array$.MODULE$.copy(elems, 0, newelems, 0, len);
      if (idx < newlen) {
         newelems[idx] = w;
      } else {
         Predef$.MODULE$.assert(w == 0L);
      }

      return newelems;
   }

   public long computeWordForFilter(final Function1 pred, final boolean isFlipped, final long oldWord, final int wordIndex) {
      if (oldWord == 0L) {
         return 0L;
      } else {
         long w = oldWord;
         int trailingZeroes = Long.numberOfTrailingZeros(oldWord);
         long jmask = 1L << trailingZeroes;
         int j = wordIndex * 64 + trailingZeroes;

         for(int maxJ = (wordIndex + 1) * 64 - Long.numberOfLeadingZeros(oldWord); j != maxJ; ++j) {
            if ((w & jmask) != 0L && pred.apply$mcZI$sp(j) == isFlipped) {
               w &= ~jmask;
            }

            jmask <<= 1;
         }

         return w;
      }
   }

   private BitSetOps$() {
   }
}
