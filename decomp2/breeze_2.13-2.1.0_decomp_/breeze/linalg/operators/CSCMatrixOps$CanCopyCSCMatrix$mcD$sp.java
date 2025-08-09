package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class CSCMatrixOps$CanCopyCSCMatrix$mcD$sp extends CSCMatrixOps.CanCopyCSCMatrix {
   public final Zero evidence$2$mcD$sp;
   private final ClassTag evidence$1;

   public CSCMatrix apply(final CSCMatrix v1) {
      return this.apply$mcD$sp(v1);
   }

   public CSCMatrix apply$mcD$sp(final CSCMatrix v1) {
      return v1.copy$mcD$sp();
   }

   // $FF: synthetic method
   public CSCMatrixOps breeze$linalg$operators$CSCMatrixOps$CanCopyCSCMatrix$mcD$sp$$$outer() {
      return this.$outer;
   }

   public CSCMatrixOps$CanCopyCSCMatrix$mcD$sp(final CSCMatrixOps $outer, final ClassTag evidence$1, final Zero evidence$2$mcD$sp) {
      super(evidence$1, evidence$2$mcD$sp);
      this.evidence$2$mcD$sp = evidence$2$mcD$sp;
      this.evidence$1 = evidence$1;
   }
}
