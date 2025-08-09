package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class CanCreateZerosLike$OpArray$mcS$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcS$sp;
   private final ClassTag evidence$1;

   public short[] apply(final short[] from) {
      return this.apply$mcS$sp(from);
   }

   public short[] apply$mcS$sp(final short[] from) {
      return (short[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, BoxesRunTime.boxToShort(((Semiring).MODULE$.implicitly(this.evidence$2$mcS$sp)).zero$mcS$sp()));
   }

   public CanCreateZerosLike$OpArray$mcS$sp(final ClassTag evidence$1, final Semiring evidence$2$mcS$sp) {
      super(evidence$1, evidence$2$mcS$sp);
      this.evidence$2$mcS$sp = evidence$2$mcS$sp;
      this.evidence$1 = evidence$1;
   }
}
