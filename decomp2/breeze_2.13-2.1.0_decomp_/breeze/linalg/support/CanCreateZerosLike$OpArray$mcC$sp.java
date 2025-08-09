package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;

public class CanCreateZerosLike$OpArray$mcC$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcC$sp;
   private final ClassTag evidence$1;

   public char[] apply(final char[] from) {
      return this.apply$mcC$sp(from);
   }

   public char[] apply$mcC$sp(final char[] from) {
      return (char[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, ((Semiring).MODULE$.implicitly(this.evidence$2$mcC$sp)).zero());
   }

   public CanCreateZerosLike$OpArray$mcC$sp(final ClassTag evidence$1, final Semiring evidence$2$mcC$sp) {
      super(evidence$1, evidence$2$mcC$sp);
      this.evidence$2$mcC$sp = evidence$2$mcC$sp;
      this.evidence$1 = evidence$1;
   }
}
