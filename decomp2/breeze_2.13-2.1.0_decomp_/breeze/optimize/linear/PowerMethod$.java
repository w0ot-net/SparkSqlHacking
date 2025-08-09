package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package;
import breeze.numerics.package$abs$absDoubleImpl$;
import breeze.optimize.proximal.QuadraticMinimizer$;
import breeze.util.Implicits$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class PowerMethod$ implements Serializable {
   public static final PowerMethod$ MODULE$ = new PowerMethod$();

   public int $lessinit$greater$default$1() {
      return 10;
   }

   public double $lessinit$greater$default$2() {
      return 1.0E-5;
   }

   public PowerMethod inverse(final int maxIters, final double tolerance) {
      return new PowerMethod(maxIters, tolerance) {
         private final double tolerance$1;
         private final int maxIters$1;

         public PowerMethod.State reset(final DenseMatrix A, final DenseVector y, final PowerMethod.State init) {
            .MODULE$.require(init.eigenVector().length() == y.length(), () -> "InversePowerMethod:reset mismatch in state dimension");
            this.normalize(init.eigenVector(), y);
            init.ay().$colon$eq(init.eigenVector(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            QuadraticMinimizer$.MODULE$.dpotrs(A, init.ay());
            double lambda = this.nextEigen(init.eigenVector(), init.ay());
            return this.State().apply(lambda, init.eigenVector(), init.ay(), 0, false);
         }

         public Iterator iterations(final DenseMatrix A, final DenseVector y, final PowerMethod.State initialState) {
            return Implicits$.MODULE$.scEnrichIterator(scala.package..MODULE$.Iterator().iterate(this.reset(A, y, initialState), (state) -> {
               state.ay().$colon$eq(state.eigenVector(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
               QuadraticMinimizer$.MODULE$.dpotrs(A, state.ay());
               double lambda = this.nextEigen(state.eigenVector(), state.ay());
               double val_dif = package.abs$.MODULE$.apply$mDDc$sp(lambda - state.eigenValue(), package$abs$absDoubleImpl$.MODULE$);
               return !(val_dif <= this.tolerance$1) && state.iter() <= this.maxIters$1 ? this.State().apply(lambda, state.eigenVector(), state.ay(), state.iter() + 1, false) : this.State().apply(lambda, state.eigenVector(), state.ay(), state.iter() + 1, true);
            })).takeUpToWhere((x$2) -> BoxesRunTime.boxToBoolean($anonfun$iterations$4(x$2)));
         }

         // $FF: synthetic method
         public static final boolean $anonfun$iterations$4(final PowerMethod.State x$2) {
            return x$2.converged();
         }

         public {
            this.tolerance$1 = tolerance$1;
            this.maxIters$1 = maxIters$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public int inverse$default$1() {
      return 10;
   }

   public double inverse$default$2() {
      return 1.0E-5;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PowerMethod$.class);
   }

   private PowerMethod$() {
   }
}
