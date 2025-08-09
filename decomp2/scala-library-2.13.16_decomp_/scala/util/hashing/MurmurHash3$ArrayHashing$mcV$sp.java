package scala.util.hashing;

import scala.runtime.BoxedUnit;

public class MurmurHash3$ArrayHashing$mcV$sp extends MurmurHash3.ArrayHashing {
   public int hash(final BoxedUnit[] a) {
      return this.hash$mcV$sp(a);
   }

   public int hash$mcV$sp(final BoxedUnit[] a) {
      return MurmurHash3$.MODULE$.arrayHash$mVc$sp(a, 1007110753);
   }
}
