package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanZerosBuilder$mcJ$sp extends VectorBuilder.CanZerosBuilder {
   public final Semiring evidence$22$mcJ$sp;
   public final Zero evidence$23$mcJ$sp;
   private final ClassTag evidence$21;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcJ$sp(v1);
   }

   public VectorBuilder apply$mcJ$sp(final VectorBuilder v1) {
      return v1.zerosLike$mcJ$sp();
   }

   public VectorBuilder$CanZerosBuilder$mcJ$sp(final ClassTag evidence$21, final Semiring evidence$22$mcJ$sp, final Zero evidence$23$mcJ$sp) {
      super(evidence$21, evidence$22$mcJ$sp, evidence$23$mcJ$sp);
      this.evidence$22$mcJ$sp = evidence$22$mcJ$sp;
      this.evidence$23$mcJ$sp = evidence$23$mcJ$sp;
      this.evidence$21 = evidence$21;
   }
}
