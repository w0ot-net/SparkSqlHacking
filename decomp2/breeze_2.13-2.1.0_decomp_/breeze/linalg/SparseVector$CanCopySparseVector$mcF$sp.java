package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class SparseVector$CanCopySparseVector$mcF$sp extends SparseVector.CanCopySparseVector {
   public final Zero evidence$18$mcF$sp;
   private final ClassTag evidence$17;

   public SparseVector apply(final SparseVector v1) {
      return this.apply$mcF$sp(v1);
   }

   public SparseVector apply$mcF$sp(final SparseVector v1) {
      return v1.copy$mcF$sp();
   }

   public SparseVector$CanCopySparseVector$mcF$sp(final ClassTag evidence$17, final Zero evidence$18$mcF$sp) {
      super(evidence$17, evidence$18$mcF$sp);
      this.evidence$18$mcF$sp = evidence$18$mcF$sp;
      this.evidence$17 = evidence$17;
   }
}
