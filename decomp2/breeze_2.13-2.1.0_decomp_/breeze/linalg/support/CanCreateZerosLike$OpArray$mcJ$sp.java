package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class CanCreateZerosLike$OpArray$mcJ$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcJ$sp;
   private final ClassTag evidence$1;

   public long[] apply(final long[] from) {
      return this.apply$mcJ$sp(from);
   }

   public long[] apply$mcJ$sp(final long[] from) {
      return (long[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, BoxesRunTime.boxToLong(((Semiring).MODULE$.implicitly(this.evidence$2$mcJ$sp)).zero$mcJ$sp()));
   }

   public CanCreateZerosLike$OpArray$mcJ$sp(final ClassTag evidence$1, final Semiring evidence$2$mcJ$sp) {
      super(evidence$1, evidence$2$mcJ$sp);
      this.evidence$2$mcJ$sp = evidence$2$mcJ$sp;
      this.evidence$1 = evidence$1;
   }
}
