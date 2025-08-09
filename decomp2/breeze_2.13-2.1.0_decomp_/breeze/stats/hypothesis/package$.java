package breeze.stats.hypothesis;

import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$;
import breeze.stats.meanAndVariance;
import breeze.stats.meanAndVariance$;
import breeze.stats.distributions.Gamma;
import breeze.stats.distributions.RandBasis$;
import breeze.stats.distributions.StudentsT;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.IterableOnceExtensionMethods.;
import scala.collection.immutable.Seq;
import scala.math.Numeric;
import scala.runtime.BoxesRunTime;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public double tTest(final IterableOnce it1, final Iterable it2, final Numeric numeric) {
      return this.tTest((Object).MODULE$.map$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(it1), (x) -> BoxesRunTime.boxToDouble($anonfun$tTest$1(numeric, x))), (Object)it2.map((x) -> BoxesRunTime.boxToDouble($anonfun$tTest$2(numeric, x))), (CanTraverseValues)CanTraverseValues$.MODULE$.canTraverseTraversable());
   }

   public double tTest(final Object it1, final Object it2, final CanTraverseValues ct) {
      meanAndVariance.MeanAndVariance var7 = (meanAndVariance.MeanAndVariance)meanAndVariance$.MODULE$.apply(it1, meanAndVariance$.MODULE$.reduce_Double(ct));
      if (var7 != null) {
         double mu1 = var7.mean();
         double var1 = var7.variance();
         long n1 = var7.count();
         Tuple3 var5 = new Tuple3(BoxesRunTime.boxToDouble(mu1), BoxesRunTime.boxToDouble(var1), BoxesRunTime.boxToLong(n1));
         double mu1 = BoxesRunTime.unboxToDouble(var5._1());
         double var1 = BoxesRunTime.unboxToDouble(var5._2());
         long n1 = BoxesRunTime.unboxToLong(var5._3());
         meanAndVariance.MeanAndVariance var21 = (meanAndVariance.MeanAndVariance)meanAndVariance$.MODULE$.apply(it2, meanAndVariance$.MODULE$.reduce_Double(ct));
         if (var21 == null) {
            throw new MatchError(var21);
         } else {
            double mu2 = var21.mean();
            double var2 = var21.variance();
            long n2 = var21.count();
            Tuple3 var4 = new Tuple3(BoxesRunTime.boxToDouble(mu2), BoxesRunTime.boxToDouble(var2), BoxesRunTime.boxToLong(n2));
            double mu2 = BoxesRunTime.unboxToDouble(var4._1());
            double var2 = BoxesRunTime.unboxToDouble(var4._2());
            long n2 = BoxesRunTime.unboxToLong(var4._3());
            scala.Predef..MODULE$.require(var1 > (double)0 && var2 > (double)0, () -> "Two Sample T Test requires that bothsamples have variance > 0");
            double tScore = (mu1 - mu2) / scala.math.package..MODULE$.sqrt(var1 / (double)n1 + var2 / (double)n2);
            double dof = scala.math.package..MODULE$.pow(var1 / (double)n1 + var2 / (double)n2, (double)2.0F) / (scala.math.package..MODULE$.pow(var1, (double)2.0F) / (scala.math.package..MODULE$.pow((double)n1, (double)2.0F) * (double)(n1 - 1L)) + scala.math.package..MODULE$.pow(var2, (double)2.0F) / (scala.math.package..MODULE$.pow((double)n2, (double)2.0F) * (double)(n2 - 1L)));
            return (new StudentsT(dof, RandBasis$.MODULE$.mt0())).unnormalizedPdf(BoxesRunTime.boxToDouble(tScore));
         }
      } else {
         throw new MatchError(var7);
      }
   }

   public double tTest(final Iterable it1, final Numeric numeric) {
      return this.tTest(it1.map((x) -> BoxesRunTime.boxToDouble($anonfun$tTest$4(numeric, x))), CanTraverseValues$.MODULE$.canTraverseTraversable());
   }

   public double tTest(final Object it1, final CanTraverseValues ct) {
      meanAndVariance.MeanAndVariance var5 = (meanAndVariance.MeanAndVariance)meanAndVariance$.MODULE$.apply(it1, meanAndVariance$.MODULE$.reduce_Double(ct));
      if (var5 != null) {
         double mu1 = var5.mean();
         double var1 = var5.variance();
         long n1 = var5.count();
         Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble(mu1), BoxesRunTime.boxToDouble(var1), BoxesRunTime.boxToLong(n1));
         double mu1 = BoxesRunTime.unboxToDouble(var3._1());
         double var1 = BoxesRunTime.unboxToDouble(var3._2());
         long n1 = BoxesRunTime.unboxToLong(var3._3());
         double Z = mu1 / scala.math.package..MODULE$.sqrt(var1 / (double)n1);
         long dof = n1 - 1L;
         return (new StudentsT((double)dof, RandBasis$.MODULE$.mt0())).unnormalizedPdf(BoxesRunTime.boxToDouble(Z));
      } else {
         throw new MatchError(var5);
      }
   }

   private double chiSquaredTerm(final double e, final double o) {
      return (e - o) * (e - o) / e;
   }

   public package.Chi2Result chi2Test(final int successControl, final int trialsControl, final int successVariant, final int trialsVariant) {
      double meanP = (double)(successControl + successVariant) / (double)(trialsControl + trialsVariant);
      double chi2 = this.chiSquaredTerm(meanP * (double)trialsControl, (double)successControl) + this.chiSquaredTerm(((double)1 - meanP) * (double)trialsControl, (double)(trialsControl - successControl)) + this.chiSquaredTerm(meanP * (double)trialsVariant, (double)successVariant) + this.chiSquaredTerm(((double)1 - meanP) * (double)trialsVariant, (double)(trialsVariant - successVariant));
      double pVal = (double)1.0F - (new Gamma((double)0.5F, (double)2.0F, RandBasis$.MODULE$.mt0())).cdf(chi2);
      return new package.Chi2Result(chi2, pVal);
   }

   public Seq chi2Test(final Tuple2 control, final Seq trials) {
      int numTrials = trials.size();
      return (Seq)((IterableOps)trials.map((x) -> MODULE$.chi2Test(control._1$mcI$sp(), control._2$mcI$sp(), x._1$mcI$sp(), x._2$mcI$sp()))).map((r) -> new package.Chi2Result(r.chi2(), MODULE$.sidakCorrectedPVal(r.pVal(), numTrials)));
   }

   public double sidakCorrectedPVal(final double p, final int n) {
      return (double)1.0F - scala.math.package..MODULE$.pow((double)1.0F - p, (double)n);
   }

   public double sidakCorrectedPValCutoff(final double p, final int n) {
      return (double)1.0F - scala.math.package..MODULE$.pow((double)1.0F - p, (double)1.0F / (double)n);
   }

   // $FF: synthetic method
   public static final double $anonfun$tTest$1(final Numeric numeric$1, final Object x) {
      return numeric$1.toDouble(x);
   }

   // $FF: synthetic method
   public static final double $anonfun$tTest$2(final Numeric numeric$1, final Object x) {
      return numeric$1.toDouble(x);
   }

   // $FF: synthetic method
   public static final double $anonfun$tTest$4(final Numeric numeric$2, final Object x) {
      return numeric$2.toDouble(x);
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
