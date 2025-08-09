package breeze.linalg;

import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction2;

public interface Matrix$mcI$sp extends Matrix, MatrixLike$mcI$sp {
   // $FF: synthetic method
   static int apply$(final Matrix$mcI$sp $this, final Tuple2 i) {
      return $this.apply(i);
   }

   default int apply(final Tuple2 i) {
      return this.apply$mcI$sp(i);
   }

   // $FF: synthetic method
   static int apply$mcI$sp$(final Matrix$mcI$sp $this, final Tuple2 i) {
      return $this.apply$mcI$sp(i);
   }

   default int apply$mcI$sp(final Tuple2 i) {
      return this.apply$mcI$sp(i._1$mcI$sp(), i._2$mcI$sp());
   }

   // $FF: synthetic method
   static void update$(final Matrix$mcI$sp $this, final Tuple2 i, final int e) {
      $this.update(i, e);
   }

   default void update(final Tuple2 i, final int e) {
      this.update$mcI$sp(i, e);
   }

   // $FF: synthetic method
   static void update$mcI$sp$(final Matrix$mcI$sp $this, final Tuple2 i, final int e) {
      $this.update$mcI$sp(i, e);
   }

   default void update$mcI$sp(final Tuple2 i, final int e) {
      this.update$mcI$sp(i._1$mcI$sp(), i._2$mcI$sp(), e);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$(final Matrix$mcI$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix(cm, zero);
   }

   default DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcI$sp(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcI$sp$(final Matrix$mcI$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcI$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mIc$sp(this.rows(), this.cols(), (JFunction2.mcIII.sp)(i, j) -> this.apply$mcI$sp(i, j), cm, zero);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
