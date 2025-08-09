package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.util.ArrayUtil$;
import scala.runtime.BoxesRunTime;

public class DenseMatrix_SetOps$SetMSOp$mcJ$sp extends DenseMatrix_SetOps.SetMSOp {
   public void apply(final DenseMatrix a, final long b) {
      this.apply$mcJ$sp(a, b);
   }

   public void apply$mcJ$sp(final DenseMatrix a, final long b) {
      if (a.isContiguous()) {
         ArrayUtil$.MODULE$.fill(a.data$mcJ$sp(), a.offset(), a.size(), BoxesRunTime.boxToLong(b));
      } else {
         this.slowPath$mcJ$sp(a, b);
      }

   }

   public void slowPath(final DenseMatrix a, final long b) {
      this.slowPath$mcJ$sp(a, b);
   }

   public void slowPath$mcJ$sp(final DenseMatrix a, final long b) {
      long[] ad = a.data$mcJ$sp();
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
   public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetMSOp$mcJ$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrix_SetOps$SetMSOp$mcJ$sp(final DenseMatrix_SetOps $outer) {
   }
}
