package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanCopyBuilder$mcD$sp extends VectorBuilder.CanCopyBuilder {
   public final Semiring evidence$19$mcD$sp;
   public final Zero evidence$20$mcD$sp;
   private final ClassTag evidence$18;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcD$sp(v1);
   }

   public VectorBuilder apply$mcD$sp(final VectorBuilder v1) {
      return v1.copy$mcD$sp();
   }

   public VectorBuilder$CanCopyBuilder$mcD$sp(final ClassTag evidence$18, final Semiring evidence$19$mcD$sp, final Zero evidence$20$mcD$sp) {
      super(evidence$18, evidence$19$mcD$sp, evidence$20$mcD$sp);
      this.evidence$19$mcD$sp = evidence$19$mcD$sp;
      this.evidence$20$mcD$sp = evidence$20$mcD$sp;
      this.evidence$18 = evidence$18;
   }
}
