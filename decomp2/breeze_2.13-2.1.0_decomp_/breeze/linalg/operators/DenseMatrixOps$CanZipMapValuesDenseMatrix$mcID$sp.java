package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.support.CanZipMapValues$mcID$sp;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.reflect.ClassTag;

public class DenseMatrixOps$CanZipMapValuesDenseMatrix$mcID$sp extends DenseMatrixOps.CanZipMapValuesDenseMatrix implements CanZipMapValues$mcID$sp {
   private final ClassTag evidence$9;

   public DenseMatrix create(final int rows, final int cols) {
      return this.create$mcI$sp(rows, cols);
   }

   public DenseMatrix create$mcI$sp(final int rows, final int cols) {
      return DenseMatrix$.MODULE$.create$mIc$sp(rows, cols, (int[])this.breeze$linalg$operators$DenseMatrixOps$CanZipMapValuesDenseMatrix$$evidence$9.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.create$default$6());
   }

   public DenseMatrix map(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
      return this.map$mcID$sp(from, from2, fn);
   }

   public DenseMatrix map$mcID$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
      int left$macro$1 = from.rows();
      int right$macro$2 = from2.rows();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Vector row dimensions must match!: ").append("from.rows == from2.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         int left$macro$3 = from.cols();
         int right$macro$4 = from2.cols();
         if (left$macro$3 != right$macro$4) {
            throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Vector col dimensions must match!: ").append("from.cols == from2.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
         } else {
            DenseMatrix result = this.create$mcI$sp(from.rows(), from.cols());
            int index$macro$11 = 0;

            for(int limit$macro$13 = from.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
               int index$macro$6 = 0;

               for(int limit$macro$8 = from.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                  ((j, i) -> result.update$mcI$sp(i, j, fn.apply$mcIDD$sp(from.apply$mcD$sp(i, j), from2.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$11, index$macro$6);
               }
            }

            return result;
         }
      }
   }

   // $FF: synthetic method
   public DenseMatrixOps breeze$linalg$operators$DenseMatrixOps$CanZipMapValuesDenseMatrix$mcID$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrixOps$CanZipMapValuesDenseMatrix$mcID$sp(final DenseMatrixOps $outer, final ClassTag evidence$9) {
      super(evidence$9);
      this.evidence$9 = evidence$9;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
