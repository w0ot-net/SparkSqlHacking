package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanZerosBuilder$mcD$sp extends VectorBuilder.CanZerosBuilder {
   public final Semiring evidence$22$mcD$sp;
   public final Zero evidence$23$mcD$sp;
   private final ClassTag evidence$21;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcD$sp(v1);
   }

   public VectorBuilder apply$mcD$sp(final VectorBuilder v1) {
      return v1.zerosLike$mcD$sp();
   }

   public VectorBuilder$CanZerosBuilder$mcD$sp(final ClassTag evidence$21, final Semiring evidence$22$mcD$sp, final Zero evidence$23$mcD$sp) {
      super(evidence$21, evidence$22$mcD$sp, evidence$23$mcD$sp);
      this.evidence$22$mcD$sp = evidence$22$mcD$sp;
      this.evidence$23$mcD$sp = evidence$23$mcD$sp;
      this.evidence$21 = evidence$21;
   }
}
