package breeze.linalg;

import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction2;

public interface Matrix$mcF$sp extends Matrix, MatrixLike$mcF$sp {
   // $FF: synthetic method
   static float apply$(final Matrix$mcF$sp $this, final Tuple2 i) {
      return $this.apply(i);
   }

   default float apply(final Tuple2 i) {
      return this.apply$mcF$sp(i);
   }

   // $FF: synthetic method
   static float apply$mcF$sp$(final Matrix$mcF$sp $this, final Tuple2 i) {
      return $this.apply$mcF$sp(i);
   }

   default float apply$mcF$sp(final Tuple2 i) {
      return this.apply$mcF$sp(i._1$mcI$sp(), i._2$mcI$sp());
   }

   // $FF: synthetic method
   static void update$(final Matrix$mcF$sp $this, final Tuple2 i, final float e) {
      $this.update(i, e);
   }

   default void update(final Tuple2 i, final float e) {
      this.update$mcF$sp(i, e);
   }

   // $FF: synthetic method
   static void update$mcF$sp$(final Matrix$mcF$sp $this, final Tuple2 i, final float e) {
      $this.update$mcF$sp(i, e);
   }

   default void update$mcF$sp(final Tuple2 i, final float e) {
      this.update$mcF$sp(i._1$mcI$sp(), i._2$mcI$sp(), e);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$(final Matrix$mcF$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix(cm, zero);
   }

   default DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix$mcF$sp(cm, zero);
   }

   // $FF: synthetic method
   static DenseMatrix toDenseMatrix$mcF$sp$(final Matrix$mcF$sp $this, final ClassTag cm, final Zero zero) {
      return $this.toDenseMatrix$mcF$sp(cm, zero);
   }

   default DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
      return (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mFc$sp(this.rows(), this.cols(), (JFunction2.mcFII.sp)(i, j) -> this.apply$mcF$sp(i, j), cm, zero);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
