package breeze.signal.support;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import java.lang.invoke.SerializedLambda;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

public final class WindowFunctions$ {
   public static final WindowFunctions$ MODULE$ = new WindowFunctions$();

   public DenseVector hammingWindow(final int n, final double alpha, final double beta) {
      return n == 1 ? (DenseVector)DenseVector$.MODULE$.apply(.MODULE$.wrapDoubleArray(new double[]{(double)1.0F}), scala.reflect.ClassTag..MODULE$.Double()) : DenseVector$.MODULE$.tabulate$mDc$sp(n, (JFunction1.mcDI.sp)(count) -> alpha - beta * scala.math.package..MODULE$.cos((Math.PI * 2D) * (double)count / (double)(n - 1)), scala.reflect.ClassTag..MODULE$.Double());
   }

   public double hammingWindow$default$2() {
      return 0.54;
   }

   public double hammingWindow$default$3() {
      return 0.46;
   }

   public DenseVector blackmanWindow(final int n, final double a0, final double a1, final double a2) {
      return n == 1 ? (DenseVector)DenseVector$.MODULE$.apply(.MODULE$.wrapDoubleArray(new double[]{(double)1.0F}), scala.reflect.ClassTag..MODULE$.Double()) : DenseVector$.MODULE$.tabulate$mDc$sp(n, (JFunction1.mcDI.sp)(count) -> a0 - a1 * scala.math.package..MODULE$.cos((Math.PI * 2D) * (double)count / (double)(n - 1)) + a2 * scala.math.package..MODULE$.cos(12.566370614359172 * (double)count / (double)(n - 1)), scala.reflect.ClassTag..MODULE$.Double());
   }

   public double blackmanWindow$default$2() {
      return 0.42;
   }

   public double blackmanWindow$default$3() {
      return (double)0.5F;
   }

   public double blackmanWindow$default$4() {
      return 0.08;
   }

   public DenseVector hanningWindow(final int n) {
      return DenseVector$.MODULE$.tabulate$mDc$sp(n, (JFunction1.mcDI.sp)(i) -> (double)0.5F * ((double)1 - scala.math.package..MODULE$.cos((Math.PI * 2D) * (double)i / (double)(n - 1))), scala.reflect.ClassTag..MODULE$.Double());
   }

   private WindowFunctions$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
