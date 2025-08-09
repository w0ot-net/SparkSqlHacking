package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class CSCMatrixOps$CanCopyCSCMatrix$mcF$sp extends CSCMatrixOps.CanCopyCSCMatrix {
   public final Zero evidence$2$mcF$sp;
   private final ClassTag evidence$1;

   public CSCMatrix apply(final CSCMatrix v1) {
      return this.apply$mcF$sp(v1);
   }

   public CSCMatrix apply$mcF$sp(final CSCMatrix v1) {
      return v1.copy$mcF$sp();
   }

   // $FF: synthetic method
   public CSCMatrixOps breeze$linalg$operators$CSCMatrixOps$CanCopyCSCMatrix$mcF$sp$$$outer() {
      return this.$outer;
   }

   public CSCMatrixOps$CanCopyCSCMatrix$mcF$sp(final CSCMatrixOps $outer, final ClassTag evidence$1, final Zero evidence$2$mcF$sp) {
      super(evidence$1, evidence$2$mcF$sp);
      this.evidence$2$mcF$sp = evidence$2$mcF$sp;
      this.evidence$1 = evidence$1;
   }
}
