package breeze.linalg.operators;

import breeze.generic.UFunc$InPlaceImpl2$mcF$sp;
import breeze.linalg.DenseMatrix;
import breeze.util.ArrayUtil$;
import scala.runtime.BoxesRunTime;

public class DenseMatrix_SetOps$SetMSOp$mcF$sp extends DenseMatrix_SetOps.SetMSOp implements UFunc$InPlaceImpl2$mcF$sp {
   public void apply(final DenseMatrix a, final float b) {
      this.apply$mcF$sp(a, b);
   }

   public void apply$mcF$sp(final DenseMatrix a, final float b) {
      if (a.isContiguous()) {
         ArrayUtil$.MODULE$.fill(a.data$mcF$sp(), a.offset(), a.size(), BoxesRunTime.boxToFloat(b));
      } else {
         this.slowPath$mcF$sp(a, b);
      }

   }

   public void slowPath(final DenseMatrix a, final float b) {
      this.slowPath$mcF$sp(a, b);
   }

   public void slowPath$mcF$sp(final DenseMatrix a, final float b) {
      float[] ad = a.data$mcF$sp();
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
   public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetMSOp$mcF$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrix_SetOps$SetMSOp$mcF$sp(final DenseMatrix_SetOps $outer) {
   }
}
