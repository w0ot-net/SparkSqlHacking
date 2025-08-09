package breeze.linalg;

import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction2;

public interface Matrix$mcJ$sp extends Matrix, MatrixLike$mcJ$sp {
   // $FF: synthetic method
   static long apply$(final Matrix$mcJ$sp $this, final Tuple2 i) {
      return $this.apply(i);
   }

   default long apply(final Tuple2 i) {
      return this.apply$mcJ$sp(i);
   }

   // $FF: synthetic method
   static long apply$mcJ$sp$(final Matrix$mcJ$sp $this, final Tuple2 i) {
      return $this.apply$mcJ$sp(i);
   }

   default long apply$mcJ$sp(final Tuple2 i) {
      return this.apply$mcJ$sp(i._1$mcI$sp(), i._2$mcI$sp());
   }

   // $FF: synthetic method
   static void update$(final Matrix$mcJ$sp $this, final Tuple2 i, final long e) {
      $this.update(i, e);
   }

   default void update(final Tuple2 i, final long e) {
      this.update$mcJ$sp(i, e);
   }

   // $FF: synthetic method
   static void update$mcJ$sp$(final Matrix$mcJ$sp $this, final Tuple2 i, final long e) {
      $this.update$mcJ$sp(i, e);
   }

   default void update$mcJ$sp(final Tuple2 i, final long e) {
      this.update$mcJ$sp(i._1$mcI$sp(), i._2$mcI$sp(), e);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$(final Matrix$mcJ$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix(cm, zero);
   }

   default DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcJ$sp(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcJ$sp$(final Matrix$mcJ$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcJ$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mJc$sp(this.rows(), this.cols(), (JFunction2.mcJII.sp)(i, j) -> this.apply$mcJ$sp(i, j), cm, zero);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
