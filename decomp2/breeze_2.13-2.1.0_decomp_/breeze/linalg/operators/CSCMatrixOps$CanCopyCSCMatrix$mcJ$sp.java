package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.storage.Zero;
import scala.reflect.ClassTag;

public class CSCMatrixOps$CanCopyCSCMatrix$mcJ$sp extends CSCMatrixOps.CanCopyCSCMatrix {
   public final Zero evidence$2$mcJ$sp;
   private final ClassTag evidence$1;

   public CSCMatrix apply(final CSCMatrix v1) {
      return this.apply$mcJ$sp(v1);
   }

   public CSCMatrix apply$mcJ$sp(final CSCMatrix v1) {
      return v1.copy$mcJ$sp();
   }

   // $FF: synthetic method
   public CSCMatrixOps breeze$linalg$operators$CSCMatrixOps$CanCopyCSCMatrix$mcJ$sp$$$outer() {
      return this.$outer;
   }

   public CSCMatrixOps$CanCopyCSCMatrix$mcJ$sp(final CSCMatrixOps $outer, final ClassTag evidence$1, final Zero evidence$2$mcJ$sp) {
      super(evidence$1, evidence$2$mcJ$sp);
      this.evidence$2$mcJ$sp = evidence$2$mcJ$sp;
      this.evidence$1 = evidence$1;
   }
}
