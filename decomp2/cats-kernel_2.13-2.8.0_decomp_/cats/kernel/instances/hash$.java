package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.HashToHashingConversion;
import scala.util.hashing.Hashing;

public final class hash$ implements HashInstances {
   public static final hash$ MODULE$ = new hash$();

   static {
      HashToHashingConversion.$init$(MODULE$);
   }

   public Hashing catsKernelHashToHashing(final Hash ev) {
      return HashToHashingConversion.catsKernelHashToHashing$(this, ev);
   }

   private hash$() {
   }
}
