package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class CSCMatrixOps$CanCopyCSCMatrix$mcI$sp extends CSCMatrixOps.CanCopyCSCMatrix {
   public final Zero evidence$2$mcI$sp;
   private final ClassTag evidence$1;

   public CSCMatrix apply(final CSCMatrix v1) {
      return this.apply$mcI$sp(v1);
   }

   public CSCMatrix apply$mcI$sp(final CSCMatrix v1) {
      return v1.copy$mcI$sp();
   }

   // $FF: synthetic method
   public CSCMatrixOps breeze$linalg$operators$CSCMatrixOps$CanCopyCSCMatrix$mcI$sp$$$outer() {
      return this.$outer;
   }

   public CSCMatrixOps$CanCopyCSCMatrix$mcI$sp(final CSCMatrixOps $outer, final ClassTag evidence$1, final Zero evidence$2$mcI$sp) {
      super(evidence$1, evidence$2$mcI$sp);
      this.evidence$2$mcI$sp = evidence$2$mcI$sp;
      this.evidence$1 = evidence$1;
   }
}
