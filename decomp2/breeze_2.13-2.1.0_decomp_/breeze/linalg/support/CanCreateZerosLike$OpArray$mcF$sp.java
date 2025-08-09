package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class CanCreateZerosLike$OpArray$mcF$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcF$sp;
   private final ClassTag evidence$1;

   public float[] apply(final float[] from) {
      return this.apply$mcF$sp(from);
   }

   public float[] apply$mcF$sp(final float[] from) {
      return (float[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, BoxesRunTime.boxToFloat(((Semiring).MODULE$.implicitly(this.evidence$2$mcF$sp)).zero$mcF$sp()));
   }

   public CanCreateZerosLike$OpArray$mcF$sp(final ClassTag evidence$1, final Semiring evidence$2$mcF$sp) {
      super(evidence$1, evidence$2$mcF$sp);
      this.evidence$2$mcF$sp = evidence$2$mcF$sp;
      this.evidence$1 = evidence$1;
   }
}
