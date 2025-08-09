package breeze.linalg;

import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class SparseVector$CanCopySparseVector$mcI$sp extends SparseVector.CanCopySparseVector {
   public final Zero evidence$18$mcI$sp;
   private final ClassTag evidence$17;

   public SparseVector apply(final SparseVector v1) {
      return this.apply$mcI$sp(v1);
   }

   public SparseVector apply$mcI$sp(final SparseVector v1) {
      return v1.copy$mcI$sp();
   }

   public SparseVector$CanCopySparseVector$mcI$sp(final ClassTag evidence$17, final Zero evidence$18$mcI$sp) {
      super(evidence$17, evidence$18$mcI$sp);
      this.evidence$18$mcI$sp = evidence$18$mcI$sp;
      this.evidence$17 = evidence$17;
   }
}
