package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanTranspose;
import breeze.math.MutableInnerProductVectorSpace;
import breeze.numerics.package$abs$absDoubleImpl$;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.lang.invoke.SerializedLambda;
import scala.math.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LSMR$ implements SerializableLogging {
   public static final LSMR$ MODULE$ = new LSMR$();
   private static transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   static {
      SerializableLogging.$init$(MODULE$);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public Object solve(final Object A, final Object b, final double regularization, final double tolerance, final int maxIter, final boolean quiet, final UFunc.UImpl2 multMV, final CanTranspose transA, final UFunc.UImpl2 multMTV, final MutableInnerProductVectorSpace ispace) {
      double lambda = .MODULE$.sqrt(regularization);
      Object At = transA.apply(A);
      double atol = tolerance;
      double btol = tolerance;
      Object u = ispace.copy().apply(b);
      double normb = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(u, ispace.normImpl()));
      double beta = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(u, ispace.normImpl()));
      if (beta > (double)0) {
         ((NumericOps)ispace.hasOps().apply(u)).$div$eq(BoxesRunTime.boxToDouble(beta), ispace.divIntoVS());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      Object v = multMTV.apply(At, u);
      Object x = ((ImmutableNumericOps)ispace.hasOps().apply(v)).$times(BoxesRunTime.boxToDouble((double)0.0F), ispace.mulVS_M());
      double alpha = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(v, ispace.normImpl()));
      if (alpha > (double)0) {
         ((NumericOps)ispace.hasOps().apply(v)).$div$eq(BoxesRunTime.boxToDouble(alpha), ispace.divIntoVS());
      } else {
         BoxedUnit var123 = BoxedUnit.UNIT;
      }

      double alphabar = alpha;
      double zetabar = alpha * beta;
      double rho = (double)1.0F;
      double rhobar = (double)1.0F;
      double cbar = (double)1.0F;
      double sbar = (double)0.0F;
      double zeta = (double)0.0F;
      Object h = v;
      Object hbar = ((ImmutableNumericOps)ispace.hasOps().apply(v)).$times(BoxesRunTime.boxToDouble((double)0.0F), ispace.mulVS_M());
      double betadd = beta;
      double betad = (double)0.0F;
      double rhodold = (double)1.0F;
      double thetatilde = (double)0.0F;
      double tautildeold = (double)0.0F;
      double d = (double)0.0F;
      double normA2 = sqr$1(alpha);
      double maxrbar = (double)0.0F;
      double minrbar = 1.0E100;
      boolean converged = false;

      double normr;
      double test1;
      double test2;
      double rtol;
      for(int iter = 0; !converged && iter < maxIter; converged = normr == (double)0.0F || iter >= maxIter || test1 < rtol || test2 < atol) {
         ++iter;
         u = ((ImmutableNumericOps)ispace.hasOps().apply(multMV.apply(A, v))).$minus(((ImmutableNumericOps)ispace.hasOps().apply(u)).$times(BoxesRunTime.boxToDouble(alpha), ispace.mulVS_M()), ispace.subVV());
         beta = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(u, ispace.normImpl()));
         if (beta > (double)0) {
            ((NumericOps)ispace.hasOps().apply(u)).$div$eq(BoxesRunTime.boxToDouble(beta), ispace.divIntoVS());
            v = ((ImmutableNumericOps)ispace.hasOps().apply(multMTV.apply(At, u))).$minus(((ImmutableNumericOps)ispace.hasOps().apply(v)).$times(BoxesRunTime.boxToDouble(beta), ispace.mulVS_M()), ispace.subVV());
         }

         alpha = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(v, ispace.normImpl()));
         if (alpha > (double)0) {
            ((NumericOps)ispace.hasOps().apply(v)).$div$eq(BoxesRunTime.boxToDouble(alpha), ispace.divIntoVS());
         } else {
            BoxedUnit var124 = BoxedUnit.UNIT;
         }

         double alphahat = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(DenseVector$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{alphabar, lambda}), scala.reflect.ClassTag..MODULE$.Double()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), ispace.scalarNorm()))));
         double chat = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(alphabar), alphahat);
         double shat = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(lambda), alphahat);
         double rhoold = rho;
         rho = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(DenseVector$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{alphahat, beta}), scala.reflect.ClassTag..MODULE$.Double()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), ispace.scalarNorm()))));
         double c = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(alphahat), rho);
         double s = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(beta), rho);
         double thetanew = s * alpha;
         alphabar = c * alpha;
         double rhobarold = rhobar;
         double zetaold = zeta;
         double thetabar = sbar * rho;
         double rhotemp = cbar * rho;
         rhobar = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(DenseVector$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{cbar * rho, thetanew}), scala.reflect.ClassTag..MODULE$.Double()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), ispace.scalarNorm()))));
         cbar = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(cbar * rho), rhobar);
         sbar = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(thetanew), rhobar);
         zeta = cbar * zetabar;
         zetabar = -sbar * zetabar;
         hbar = ((ImmutableNumericOps)ispace.hasOps().apply(h)).$minus(((ImmutableNumericOps)ispace.hasOps().apply(hbar)).$times(BoxesRunTime.boxToDouble(thetabar * rho / (rhoold * rhobarold)), ispace.mulVS_M()), ispace.subVV());
         x = ((NumericOps)ispace.hasOps().apply(x)).$plus(((ImmutableNumericOps)ispace.hasOps().apply(hbar)).$times(BoxesRunTime.boxToDouble(LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(zeta), rho * rhobar)), ispace.mulVS_M()), ispace.addVV());
         h = ((ImmutableNumericOps)ispace.hasOps().apply(v)).$minus(((ImmutableNumericOps)ispace.hasOps().apply(h)).$times(BoxesRunTime.boxToDouble(LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(thetanew), rho)), ispace.mulVS_M()), ispace.subVV());
         double betaacute = chat * betadd;
         double betacheck = -shat * betadd;
         double betahat = c * betaacute;
         betadd = -s * betaacute;
         double thetatildeold = thetatilde;
         double rhotildeold = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(DenseVector$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{rhodold, thetabar}), scala.reflect.ClassTag..MODULE$.Double()), norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), ispace.scalarNorm()))));
         double ctildeold = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(rhodold), rhotildeold);
         double stildeold = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(thetabar), rhotildeold);
         thetatilde = stildeold * rhobar;
         rhodold = ctildeold * rhobar;
         betad = -stildeold * betad + ctildeold * betahat;
         tautildeold = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(zetaold - thetatildeold * tautildeold), rhotildeold);
         double taud = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(zeta - thetatilde * tautildeold), rhodold);
         d += sqr$1(betacheck);
         normr = breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(d + sqr$1(betad - taud) + sqr$1(betadd), package$sqrt$sqrtDoubleImpl$.MODULE$);
         normA2 += sqr$1(beta);
         double normA = breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(normA2, package$sqrt$sqrtDoubleImpl$.MODULE$);
         normA2 += sqr$1(alpha);
         maxrbar = max$.MODULE$.apply$mDDDc$sp(maxrbar, rhobarold, max$.MODULE$.maxImpl2_Double());
         if (iter > 1) {
            minrbar = min$.MODULE$.apply$mDDDc$sp(minrbar, rhobarold, min$.MODULE$.minImpl2_Double());
         }

         double condA = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(max$.MODULE$.apply$mDDDc$sp(maxrbar, rhotemp, max$.MODULE$.maxImpl2_Double())), min$.MODULE$.apply$mDDDc$sp(minrbar, rhotemp, min$.MODULE$.minImpl2_Double()));
         maxrbar = max$.MODULE$.apply$mDDDc$sp(maxrbar, rhobarold, max$.MODULE$.maxImpl2_Double());
         if (iter > 1) {
            minrbar = min$.MODULE$.apply$mDDDc$sp(minrbar, rhobarold, min$.MODULE$.minImpl2_Double());
         }

         condA = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(max$.MODULE$.apply$mDDDc$sp(maxrbar, rhotemp, max$.MODULE$.maxImpl2_Double())), min$.MODULE$.apply$mDDDc$sp(minrbar, rhotemp, min$.MODULE$.minImpl2_Double()));
         double normAr = breeze.numerics.package.abs$.MODULE$.apply$mDDc$sp(zetabar, package$abs$absDoubleImpl$.MODULE$);
         double normx = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(x, ispace.normImpl()));
         test1 = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(normr), normb);
         test2 = LSMR.SafeDiv$.MODULE$.$div$qmark$extension(this.SafeDiv(normAr), normA * normr);
         rtol = btol + atol * normA * normx / normb;
         if (!quiet) {
            this.logger().info(() -> (new StringBuilder(0)).append(scala.collection.StringOps..MODULE$.format$extension("Residual: %.2g %.2g ", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(normr), BoxesRunTime.boxToDouble(normAr)}))).append(scala.collection.StringOps..MODULE$.format$extension(":: convtest1: %.2g <? %.2g :: convtest2: %.2g <? %.2g", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(test1), BoxesRunTime.boxToDouble(rtol), BoxesRunTime.boxToDouble(test2), BoxesRunTime.boxToDouble(atol)}))).toString());
         }
      }

      return x;
   }

   public double solve$default$3() {
      return (double)0.0F;
   }

   public double solve$default$4() {
      return 1.0E-9;
   }

   public int solve$default$5() {
      return 1000;
   }

   public boolean solve$default$6() {
      return false;
   }

   private double SafeDiv(final double __x) {
      return __x;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LSMR$.class);
   }

   private static final double sqr$1(final double x) {
      return x * x;
   }

   private LSMR$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
