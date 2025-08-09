package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$mcIJ$sp extends DenseMatrixOps.CanZipMapKeyValuesDenseMatrix {
   private final ClassTag evidence$11;

   public DenseMatrix create(final int rows, final int cols) {
      return this.create$mcI$sp(rows, cols);
   }

   public DenseMatrix create$mcI$sp(final int rows, final int cols) {
      return DenseMatrix$.MODULE$.create$mIc$sp(rows, cols, (int[])this.breeze$linalg$operators$DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$$evidence$11.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.create$default$6());
   }

   public DenseMatrix mapActive(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
      return this.mapActive$mcIJ$sp(from, from2, fn);
   }

   public DenseMatrix mapActive$mcIJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public DenseMatrix map(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public DenseMatrix map$mcIJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
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
                  ((j, i) -> result.update$mcI$sp(i, j, BoxesRunTime.unboxToInt(fn.apply(new Tuple2.mcII.sp(i, j), BoxesRunTime.boxToLong(from.apply$mcJ$sp(i, j)), BoxesRunTime.boxToLong(from2.apply$mcJ$sp(i, j)))))).apply$mcVII$sp(index$macro$11, index$macro$6);
               }
            }

            return result;
         }
      }
   }

   // $FF: synthetic method
   public DenseMatrixOps breeze$linalg$operators$DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$mcIJ$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$mcIJ$sp(final DenseMatrixOps $outer, final ClassTag evidence$11) {
      super(evidence$11);
      this.evidence$11 = evidence$11;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
