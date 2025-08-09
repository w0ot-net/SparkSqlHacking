package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class CanCreateZerosLike$OpArray$mcD$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcD$sp;
   private final ClassTag evidence$1;

   public double[] apply(final double[] from) {
      return this.apply$mcD$sp(from);
   }

   public double[] apply$mcD$sp(final double[] from) {
      return (double[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, BoxesRunTime.boxToDouble(((Semiring).MODULE$.implicitly(this.evidence$2$mcD$sp)).zero$mcD$sp()));
   }

   public CanCreateZerosLike$OpArray$mcD$sp(final ClassTag evidence$1, final Semiring evidence$2$mcD$sp) {
      super(evidence$1, evidence$2$mcD$sp);
      this.evidence$2$mcD$sp = evidence$2$mcD$sp;
      this.evidence$1 = evidence$1;
   }
}
