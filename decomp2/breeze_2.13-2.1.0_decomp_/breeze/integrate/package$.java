package breeze.integrate;

import breeze.linalg.DenseVector;
import breeze.linalg.sum$;
import breeze.linalg.support.CanTraverseValues$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public double trapezoid(final Function1 f, final double start, final double end, final int nodes) {
      if (nodes < 2) {
         throw new Exception("When using trapezoid, you have to use at least two nodes.");
      } else {
         double h = (end - start) / (double)(nodes - 1);
         double s = BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nodes).map((JFunction1.mcDI.sp)(i) -> f.apply$mcDD$sp(start + (double)i * h)), sum$.MODULE$.reduce_Double(CanTraverseValues$.MODULE$.canTraverseTraversable())));
         return h * (s - (f.apply$mcDD$sp(start) + f.apply$mcDD$sp(end)) / (double)2.0F);
      }
   }

   public double simpson(final Function1 f, final double start, final double end, final int nodes) {
      if (nodes < 2) {
         throw new Exception("When using simpson, you have to use at least two nodes.");
      } else {
         double h = (end - start) / (double)(nodes - 1);
         double s = BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), nodes - 1).map((JFunction1.mcDI.sp)(i) -> f.apply$mcDD$sp(start + ((double)i + (double)0.5F) * h)), sum$.MODULE$.reduce_Double(CanTraverseValues$.MODULE$.canTraverseTraversable())));
         return this.trapezoid(f, start, end, nodes) / (double)3.0F + s * (double)2 / (double)3.0F * h;
      }
   }

   public DenseVector[] ode45(final Function2 f, final DenseVector y0, final double[] t, final DenseVector relTol, final DenseVector absTol) {
      DormandPrince54Integrator integrator = new DormandPrince54Integrator((double)0.0F, (double)1.0F, relTol, absTol);
      return integrator.integrate(f, y0, t);
   }

   public DenseVector ode45$default$4() {
      return null;
   }

   public DenseVector ode45$default$5() {
      return null;
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
