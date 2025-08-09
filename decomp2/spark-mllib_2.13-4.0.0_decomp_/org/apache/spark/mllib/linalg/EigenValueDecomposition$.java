package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.netlib.util.doubleW;
import org.netlib.util.intW;
import scala.Function1;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class EigenValueDecomposition$ {
   public static final EigenValueDecomposition$ MODULE$ = new EigenValueDecomposition$();

   public Tuple2 symmetricEigs(final Function1 mul, final int n, final int k, final double tol, final int maxIterations) {
      .MODULE$.require(n > k, () -> "Number of required eigenvalues " + k + " must be smaller than matrix dimension " + n);
      doubleW tolW = new doubleW(tol);
      intW nev = new intW(k);
      int ncv = scala.math.package..MODULE$.min(2 * k, n);
      String bmat = "I";
      String which = "LM";
      int[] iparam = new int[11];
      iparam[0] = 1;
      iparam[2] = maxIterations;
      iparam[6] = 1;
      .MODULE$.require((long)n * (long)ncv <= 2147483647L && (long)ncv * ((long)ncv + 8L) <= 2147483647L, () -> "k = " + k + " and/or n = " + n + " are too large to compute an eigendecomposition");
      intW ido = new intW(0);
      intW info = new intW(0);
      double[] resid = new double[n];
      double[] v = new double[n * ncv];
      double[] workd = new double[n * 3];
      double[] workl = new double[ncv * (ncv + 8)];
      int[] ipntr = new int[11];
      ARPACK$.MODULE$.nativeARPACK().dsaupd(ido, bmat, n, which, nev.val, tolW, resid, ncv, v, n, iparam, ipntr, workd, workl, workl.length, info);
      breeze.linalg.DenseVector w = breeze.linalg.DenseVector..MODULE$.apply$mDc$sp(workd);

      while(ido.val != 99) {
         if (ido.val != -1 && ido.val != 1) {
            throw new IllegalStateException("ARPACK returns ido = " + ido.val + " This flag is not compatible with Mode 1: A*x = lambda*x, A symmetric.");
         }

         int inputOffset = ipntr[0] - 1;
         int outputOffset = ipntr[1] - 1;
         breeze.linalg.DenseVector x = w.slice$mcD$sp(inputOffset, inputOffset + n, w.slice$default$3());
         breeze.linalg.DenseVector y = w.slice$mcD$sp(outputOffset, outputOffset + n, w.slice$default$3());
         y.$colon$eq(mul.apply(x), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         ARPACK$.MODULE$.nativeARPACK().dsaupd(ido, bmat, n, which, nev.val, tolW, resid, ncv, v, n, iparam, ipntr, workd, workl, workl.length, info);
      }

      if (info.val != 0) {
         int var25 = info.val;
         switch (var25) {
            case 1 -> throw new IllegalStateException("ARPACK returns non-zero info = " + info.val + " Maximum number of iterations taken. (Refer ARPACK user guide for details)");
            case 3 -> throw new IllegalStateException("ARPACK returns non-zero info = " + info.val + " No shifts could be applied. Try to increase NCV. (Refer ARPACK user guide for details)");
            default -> throw new IllegalStateException("ARPACK returns non-zero info = " + info.val + " Please refer ARPACK user guide for error message.");
         }
      } else {
         double[] d = new double[nev.val];
         boolean[] select = new boolean[ncv];
         double[] z = Arrays.copyOfRange(v, 0, nev.val * n);
         ARPACK$.MODULE$.nativeARPACK().dseupd(true, "A", select, d, z, n, (double)0.0F, bmat, n, which, nev, tol, resid, ncv, v, n, iparam, ipntr, workd, workl, workl.length, info);
         int computed = iparam[4];
         Tuple2[] eigenPairs = (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.doubleArrayOps(Arrays.copyOfRange(d, 0, computed)))), (r) -> new Tuple2(BoxesRunTime.boxToDouble(r._1$mcD$sp()), Arrays.copyOfRange(z, r._2$mcI$sp() * n, r._2$mcI$sp() * n + n)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         Tuple2[] sortedEigenPairs = (Tuple2[])scala.collection.ArrayOps..MODULE$.sortBy$extension(.MODULE$.refArrayOps((Object[])eigenPairs), (x$1) -> BoxesRunTime.boxToDouble($anonfun$symmetricEigs$4(x$1)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
         breeze.linalg.DenseMatrix sortedU = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(n, computed, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps((Object[])sortedEigenPairs))), (r) -> {
            $anonfun$symmetricEigs$5(n, sortedU, r);
            return BoxedUnit.UNIT;
         });
         return new Tuple2(breeze.linalg.DenseVector..MODULE$.apply(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])sortedEigenPairs), (x$2) -> BoxesRunTime.boxToDouble($anonfun$symmetricEigs$6(x$2)), scala.reflect.ClassTag..MODULE$.Double())), sortedU);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$symmetricEigs$4(final Tuple2 x$1) {
      return -x$1._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$symmetricEigs$5(final int n$1, final breeze.linalg.DenseMatrix sortedU$1, final Tuple2 r) {
      int b = r._2$mcI$sp() * n$1;

      for(int i = 0; i < n$1; ++i) {
         sortedU$1.data$mcD$sp()[b + i] = ((double[])((Tuple2)r._1())._2())[i];
      }

   }

   // $FF: synthetic method
   public static final double $anonfun$symmetricEigs$6(final Tuple2 x$2) {
      return x$2._1$mcD$sp();
   }

   private EigenValueDecomposition$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
