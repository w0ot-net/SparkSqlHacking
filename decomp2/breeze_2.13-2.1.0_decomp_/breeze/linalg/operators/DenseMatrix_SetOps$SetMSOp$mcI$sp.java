package breeze.linalg.operators;

import breeze.generic.UFunc$InPlaceImpl2$mcI$sp;
import breeze.linalg.DenseMatrix;
import breeze.util.ArrayUtil$;
import scala.runtime.BoxesRunTime;

public class DenseMatrix_SetOps$SetMSOp$mcI$sp extends DenseMatrix_SetOps.SetMSOp implements UFunc$InPlaceImpl2$mcI$sp {
   public void apply(final DenseMatrix a, final int b) {
      this.apply$mcI$sp(a, b);
   }

   public void apply$mcI$sp(final DenseMatrix a, final int b) {
      if (a.isContiguous()) {
         ArrayUtil$.MODULE$.fill(a.data$mcI$sp(), a.offset(), a.size(), BoxesRunTime.boxToInteger(b));
      } else {
         this.slowPath$mcI$sp(a, b);
      }

   }

   public void slowPath(final DenseMatrix a, final int b) {
      this.slowPath$mcI$sp(a, b);
   }

   public void slowPath$mcI$sp(final DenseMatrix a, final int b) {
      int[] ad = a.data$mcI$sp();
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
   public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetMSOp$mcI$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrix_SetOps$SetMSOp$mcI$sp(final DenseMatrix_SetOps $outer) {
   }
}
