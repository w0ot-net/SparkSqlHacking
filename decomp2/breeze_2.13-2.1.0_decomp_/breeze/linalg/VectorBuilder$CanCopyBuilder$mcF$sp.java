package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanCopyBuilder$mcF$sp extends VectorBuilder.CanCopyBuilder {
   public final Semiring evidence$19$mcF$sp;
   public final Zero evidence$20$mcF$sp;
   private final ClassTag evidence$18;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcF$sp(v1);
   }

   public VectorBuilder apply$mcF$sp(final VectorBuilder v1) {
      return v1.copy$mcF$sp();
   }

   public VectorBuilder$CanCopyBuilder$mcF$sp(final ClassTag evidence$18, final Semiring evidence$19$mcF$sp, final Zero evidence$20$mcF$sp) {
      super(evidence$18, evidence$19$mcF$sp, evidence$20$mcF$sp);
      this.evidence$19$mcF$sp = evidence$19$mcF$sp;
      this.evidence$20$mcF$sp = evidence$20$mcF$sp;
      this.evidence$18 = evidence$18;
   }
}
