package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class SparseVector$CanCopySparseVector$mcD$sp extends SparseVector.CanCopySparseVector {
   public final Zero evidence$18$mcD$sp;
   private final ClassTag evidence$17;

   public SparseVector apply(final SparseVector v1) {
      return this.apply$mcD$sp(v1);
   }

   public SparseVector apply$mcD$sp(final SparseVector v1) {
      return v1.copy$mcD$sp();
   }

   public SparseVector$CanCopySparseVector$mcD$sp(final ClassTag evidence$17, final Zero evidence$18$mcD$sp) {
      super(evidence$17, evidence$18$mcD$sp);
      this.evidence$18$mcD$sp = evidence$18$mcD$sp;
      this.evidence$17 = evidence$17;
   }
}
