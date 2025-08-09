package breeze.linalg.operators;

import breeze.generic.UFunc$InPlaceImpl2$mcD$sp;
import breeze.linalg.DenseMatrix;
import breeze.util.ArrayUtil$;
import scala.runtime.BoxesRunTime;

public class DenseMatrix_SetOps$SetMSOp$mcD$sp extends DenseMatrix_SetOps.SetMSOp implements UFunc$InPlaceImpl2$mcD$sp {
   public void apply(final DenseMatrix a, final double b) {
      this.apply$mcD$sp(a, b);
   }

   public void apply$mcD$sp(final DenseMatrix a, final double b) {
      if (a.isContiguous()) {
         ArrayUtil$.MODULE$.fill(a.data$mcD$sp(), a.offset(), a.size(), BoxesRunTime.boxToDouble(b));
      } else {
         this.slowPath$mcD$sp(a, b);
      }

   }

   public void slowPath(final DenseMatrix a, final double b) {
      this.slowPath$mcD$sp(a, b);
   }

   public void slowPath$mcD$sp(final DenseMatrix a, final double b) {
      double[] ad = a.data$mcD$sp();
      int aoff = a.offset();
      int index$macro$7 = 0;

      for(int limit$macro$9 = a.majorSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
         int index$macro$2 = 0;

         for(int limit$macro$4 = a.minorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            ad[aoff + index$macro$2] = b;
         }

         aoff += a.majorStride();
      }

   }

   // $FF: synthetic method
   public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetMSOp$mcD$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrix_SetOps$SetMSOp$mcD$sp(final DenseMatrix_SetOps $outer) {
   }
}
