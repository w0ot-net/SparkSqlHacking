package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class CanCreateZerosLike$OpArray$mcI$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcI$sp;
   private final ClassTag evidence$1;

   public int[] apply(final int[] from) {
      return this.apply$mcI$sp(from);
   }

   public int[] apply$mcI$sp(final int[] from) {
      return (int[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, BoxesRunTime.boxToInteger(((Semiring).MODULE$.implicitly(this.evidence$2$mcI$sp)).zero$mcI$sp()));
   }

   public CanCreateZerosLike$OpArray$mcI$sp(final ClassTag evidence$1, final Semiring evidence$2$mcI$sp) {
      super(evidence$1, evidence$2$mcI$sp);
      this.evidence$2$mcI$sp = evidence$2$mcI$sp;
      this.evidence$1 = evidence$1;
   }
}
