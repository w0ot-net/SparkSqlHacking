package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.runtime.IntRef;

public class DenseMatrix_SetOps$SetDMDVOp$mcF$sp extends DenseMatrix_SetOps.SetDMDVOp {
   public void apply(final DenseMatrix a, final DenseVector b) {
      this.apply$mcF$sp(a, b);
   }

   public void apply$mcF$sp(final DenseMatrix a, final DenseVector b) {
      boolean cond$macro$1 = a.rows() == b.length() && a.cols() == 1 || a.cols() == b.length() && a.rows() == 1;
      if (!cond$macro$1) {
         throw new IllegalArgumentException("requirement failed: DenseMatrix must have same number of rows, or same number of columns, as DenseVector, and the other dim must be 1.: a.rows.==(b.length).&&(a.cols.==(1)).||(a.cols.==(b.length).&&(a.rows.==(1)))");
      } else {
         float[] ad = a.data$mcF$sp();
         float[] bd = b.data$mcF$sp();
         IntRef boff = IntRef.create(b.offset());
         int index$macro$8 = 0;

         for(int limit$macro$10 = a.cols(); index$macro$8 < limit$macro$10; ++index$macro$8) {
            int index$macro$3 = 0;

            for(int limit$macro$5 = a.rows(); index$macro$3 < limit$macro$5; ++index$macro$3) {
               ((c, r) -> {
                  ad[a.linearIndex(r, c)] = bd[boff.elem];
                  boff.elem += b.stride();
               }).apply$mcVII$sp(index$macro$8, index$macro$3);
            }
         }

      }
   }

   // $FF: synthetic method
   public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetDMDVOp$mcF$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrix_SetOps$SetDMDVOp$mcF$sp(final DenseMatrix_SetOps $outer) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
