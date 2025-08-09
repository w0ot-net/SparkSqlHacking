package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public class CanCreateZerosLike$OpArray$mcV$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcV$sp;
   private final ClassTag evidence$1;

   public BoxedUnit[] apply(final BoxedUnit[] from) {
      return this.apply$mcV$sp(from);
   }

   public BoxedUnit[] apply$mcV$sp(final BoxedUnit[] from) {
      return (BoxedUnit[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, ((Semiring).MODULE$.implicitly(this.evidence$2$mcV$sp)).zero());
   }

   public CanCreateZerosLike$OpArray$mcV$sp(final ClassTag evidence$1, final Semiring evidence$2$mcV$sp) {
      super(evidence$1, evidence$2$mcV$sp);
      this.evidence$2$mcV$sp = evidence$2$mcV$sp;
      this.evidence$1 = evidence$1;
   }
}
