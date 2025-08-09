package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanZerosBuilder$mcF$sp extends VectorBuilder.CanZerosBuilder {
   public final Semiring evidence$22$mcF$sp;
   public final Zero evidence$23$mcF$sp;
   private final ClassTag evidence$21;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcF$sp(v1);
   }

   public VectorBuilder apply$mcF$sp(final VectorBuilder v1) {
      return v1.zerosLike$mcF$sp();
   }

   public VectorBuilder$CanZerosBuilder$mcF$sp(final ClassTag evidence$21, final Semiring evidence$22$mcF$sp, final Zero evidence$23$mcF$sp) {
      super(evidence$21, evidence$22$mcF$sp, evidence$23$mcF$sp);
      this.evidence$22$mcF$sp = evidence$22$mcF$sp;
      this.evidence$23$mcF$sp = evidence$23$mcF$sp;
      this.evidence$21 = evidence$21;
   }
}
