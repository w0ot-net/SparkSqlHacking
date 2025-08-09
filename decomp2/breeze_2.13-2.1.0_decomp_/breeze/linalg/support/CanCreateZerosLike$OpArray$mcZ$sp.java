package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;

public class CanCreateZerosLike$OpArray$mcZ$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcZ$sp;
   private final ClassTag evidence$1;

   public boolean[] apply(final boolean[] from) {
      return this.apply$mcZ$sp(from);
   }

   public boolean[] apply$mcZ$sp(final boolean[] from) {
      return (boolean[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, ((Semiring).MODULE$.implicitly(this.evidence$2$mcZ$sp)).zero());
   }

   public CanCreateZerosLike$OpArray$mcZ$sp(final ClassTag evidence$1, final Semiring evidence$2$mcZ$sp) {
      super(evidence$1, evidence$2$mcZ$sp);
      this.evidence$2$mcZ$sp = evidence$2$mcZ$sp;
      this.evidence$1 = evidence$1;
   }
}
