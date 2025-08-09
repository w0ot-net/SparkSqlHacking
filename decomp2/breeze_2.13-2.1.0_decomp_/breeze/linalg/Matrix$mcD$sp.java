package breeze.linalg;

import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction2;

public interface Matrix$mcD$sp extends Matrix, MatrixLike$mcD$sp {
   // $FF: synthetic method
   static double apply$(final Matrix$mcD$sp $this, final Tuple2 i) {
      return $this.apply(i);
   }

   default double apply(final Tuple2 i) {
      return this.apply$mcD$sp(i);
   }

   // $FF: synthetic method
   static double apply$mcD$sp$(final Matrix$mcD$sp $this, final Tuple2 i) {
      return $this.apply$mcD$sp(i);
   }

   default double apply$mcD$sp(final Tuple2 i) {
      return this.apply$mcD$sp(i._1$mcI$sp(), i._2$mcI$sp());
   }

   // $FF: synthetic method
   static void update$(final Matrix$mcD$sp $this, final Tuple2 i, final double e) {
      $this.update(i, e);
   }

   default void update(final Tuple2 i, final double e) {
      this.update$mcD$sp(i, e);
   }

   // $FF: synthetic method
   static void update$mcD$sp$(final Matrix$mcD$sp $this, final Tuple2 i, final double e) {
      $this.update$mcD$sp(i, e);
   }

   default void update$mcD$sp(final Tuple2 i, final double e) {
      this.update$mcD$sp(i._1$mcI$sp(), i._2$mcI$sp(), e);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$(final Matrix$mcD$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix(cm, zero);
   }

   default DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcD$sp(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcD$sp$(final Matrix$mcD$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcD$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mDc$sp(this.rows(), this.cols(), (JFunction2.mcDII.sp)(i, j) -> this.apply$mcD$sp(i, j), cm, zero);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
