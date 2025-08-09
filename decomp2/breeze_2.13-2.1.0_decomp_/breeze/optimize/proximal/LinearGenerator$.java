package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.operators.HasOps$;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Tuple3;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class LinearGenerator$ {
   public static final LinearGenerator$ MODULE$ = new LinearGenerator$();

   public Tuple3 apply(final int ndim) {
      Rand rand = Rand$.MODULE$.gaussian((double)0.0F, (double)1.0F);
      DenseMatrix data = (DenseMatrix)DenseMatrix$.MODULE$.rand(ndim, ndim, rand, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector labels = (DenseVector)DenseVector$.MODULE$.rand(ndim, rand, .MODULE$.Double()).map$mcD$sp((JFunction1.mcDD.sp)(x) -> x > (double)0.5F ? (double)1.0F : (double)0.0F, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()));
      DenseMatrix h = (DenseMatrix)((ImmutableNumericOps)((ImmutableNumericOps)data.t(HasOps$.MODULE$.canTranspose_DM())).$times(data, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD())).$times(BoxesRunTime.boxToDouble((double)2.0F), HasOps$.MODULE$.op_DM_S_Double_OpMulMatrix());
      DenseVector q = (DenseVector)((ImmutableNumericOps)data.t(HasOps$.MODULE$.canTranspose_DM())).$times(labels, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
      q.$times$eq(BoxesRunTime.boxToDouble((double)-2.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
      return new Tuple3(new LinearGenerator.Cost(data, labels), h, q);
   }

   private LinearGenerator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
