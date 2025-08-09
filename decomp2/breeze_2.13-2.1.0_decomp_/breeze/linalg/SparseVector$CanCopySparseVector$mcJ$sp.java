package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class SparseVector$CanCopySparseVector$mcJ$sp extends SparseVector.CanCopySparseVector {
   public final Zero evidence$18$mcJ$sp;
   private final ClassTag evidence$17;

   public SparseVector apply(final SparseVector v1) {
      return this.apply$mcJ$sp(v1);
   }

   public SparseVector apply$mcJ$sp(final SparseVector v1) {
      return v1.copy$mcJ$sp();
   }

   public SparseVector$CanCopySparseVector$mcJ$sp(final ClassTag evidence$17, final Zero evidence$18$mcJ$sp) {
      super(evidence$17, evidence$18$mcJ$sp);
      this.evidence$18$mcJ$sp = evidence$18$mcJ$sp;
      this.evidence$17 = evidence$17;
   }
}
