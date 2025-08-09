package breeze.linalg.support;

import breeze.math.Semiring;
import breeze.util.ArrayUtil$;
import scala.Predef.;
import scala.reflect.ClassTag;

public class CanCreateZerosLike$OpArray$mcB$sp extends CanCreateZerosLike.OpArray {
   public final Semiring evidence$2$mcB$sp;
   private final ClassTag evidence$1;

   public byte[] apply(final byte[] from) {
      return this.apply$mcB$sp(from);
   }

   public byte[] apply$mcB$sp(final byte[] from) {
      return (byte[])ArrayUtil$.MODULE$.fillNewArrayLike(from, from.length, ((Semiring).MODULE$.implicitly(this.evidence$2$mcB$sp)).zero());
   }

   public CanCreateZerosLike$OpArray$mcB$sp(final ClassTag evidence$1, final Semiring evidence$2$mcB$sp) {
      super(evidence$1, evidence$2$mcB$sp);
      this.evidence$2$mcB$sp = evidence$2$mcB$sp;
      this.evidence$1 = evidence$1;
   }
}
