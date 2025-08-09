package scala.collection;

import scala.runtime.Statics;

public final class Hashing$ {
   public static final Hashing$ MODULE$ = new Hashing$();

   public int elemHashCode(final Object key) {
      return Statics.anyHash(key);
   }

   public int improve(final int hcode) {
      int h = hcode + ~(hcode << 9);
      h ^= h >>> 14;
      h += h << 4;
      return h ^ h >>> 10;
   }

   public int computeHash(final Object key) {
      return this.improve(Statics.anyHash(key));
   }

   public int keepBits(final int bitmap, final int keep) {
      int result = 0;
      int current = bitmap;

      for(int kept = keep; kept != 0; kept >>>= 1) {
         int lsb = current ^ current & current - 1;
         if ((kept & 1) != 0) {
            result |= lsb;
         }

         current &= ~lsb;
      }

      return result;
   }

   private Hashing$() {
   }
}
