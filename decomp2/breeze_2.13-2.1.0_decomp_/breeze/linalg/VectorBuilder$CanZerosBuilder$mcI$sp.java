package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanZerosBuilder$mcI$sp extends VectorBuilder.CanZerosBuilder {
   public final Semiring evidence$22$mcI$sp;
   public final Zero evidence$23$mcI$sp;
   private final ClassTag evidence$21;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcI$sp(v1);
   }

   public VectorBuilder apply$mcI$sp(final VectorBuilder v1) {
      return v1.zerosLike$mcI$sp();
   }

   public VectorBuilder$CanZerosBuilder$mcI$sp(final ClassTag evidence$21, final Semiring evidence$22$mcI$sp, final Zero evidence$23$mcI$sp) {
      super(evidence$21, evidence$22$mcI$sp, evidence$23$mcI$sp);
      this.evidence$22$mcI$sp = evidence$22$mcI$sp;
      this.evidence$23$mcI$sp = evidence$23$mcI$sp;
      this.evidence$21 = evidence$21;
   }
}
