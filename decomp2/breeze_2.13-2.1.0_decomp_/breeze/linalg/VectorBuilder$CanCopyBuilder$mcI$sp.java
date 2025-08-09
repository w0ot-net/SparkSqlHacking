package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class VectorBuilder$CanCopyBuilder$mcI$sp extends VectorBuilder.CanCopyBuilder {
   public final Semiring evidence$19$mcI$sp;
   public final Zero evidence$20$mcI$sp;
   private final ClassTag evidence$18;

   public VectorBuilder apply(final VectorBuilder v1) {
      return this.apply$mcI$sp(v1);
   }

   public VectorBuilder apply$mcI$sp(final VectorBuilder v1) {
      return v1.copy$mcI$sp();
   }

   public VectorBuilder$CanCopyBuilder$mcI$sp(final ClassTag evidence$18, final Semiring evidence$19$mcI$sp, final Zero evidence$20$mcI$sp) {
      super(evidence$18, evidence$19$mcI$sp, evidence$20$mcI$sp);
      this.evidence$19$mcI$sp = evidence$19$mcI$sp;
      this.evidence$20$mcI$sp = evidence$20$mcI$sp;
      this.evidence$18 = evidence$18;
   }
}
