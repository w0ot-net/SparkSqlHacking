package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanCopyBuilder$mcJ$sp extends VectorBuilder.CanCopyBuilder {
   public final Semiring evidence$19$mcJ$sp;
   public final Zero evidence$20$mcJ$sp;
   private final ClassTag evidence$18;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcJ$sp(v1);
   }

   public VectorBuilder apply$mcJ$sp(final VectorBuilder v1) {
      return v1.copy$mcJ$sp();
   }

   public VectorBuilder$CanCopyBuilder$mcJ$sp(final ClassTag evidence$18, final Semiring evidence$19$mcJ$sp, final Zero evidence$20$mcJ$sp) {
      super(evidence$18, evidence$19$mcJ$sp, evidence$20$mcJ$sp);
      this.evidence$19$mcJ$sp = evidence$19$mcJ$sp;
      this.evidence$20$mcJ$sp = evidence$20$mcJ$sp;
      this.evidence$18 = evidence$18;
   }
}
