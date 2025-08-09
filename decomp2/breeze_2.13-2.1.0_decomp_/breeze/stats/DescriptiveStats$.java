package breeze.stats;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceExtensionMethods.;
import scala.math.Fractional;
import scala.runtime.BoxesRunTime;
import scala.runtime.Tuple2Zipped;

public final class DescriptiveStats$ {
   public static final DescriptiveStats$ MODULE$ = new DescriptiveStats$();

   public double percentile(final IterableOnce it, final double p) {
      if (!(p > (double)1) && !(p < (double)0)) {
         double[] arr = (double[]).MODULE$.toArray$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(it), scala.reflect.ClassTag..MODULE$.Double());
         scala.util.Sorting..MODULE$.quickSort(arr);
         return this.percentileInPlace(arr, p);
      } else {
         throw new IllegalArgumentException("p must be in [0,1]");
      }
   }

   public double percentileInPlace(final double[] arr, final double p) {
      if (!(p > (double)1) && !(p < (double)0)) {
         double f = (double)(arr.length + 1) * p;
         int i = (int)f;
         return i == 0 ? BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.doubleArrayOps(arr))) : (i >= arr.length ? BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(arr))) : arr[i - 1] + (f - (double)i) * (arr[i] - arr[i - 1]));
      } else {
         throw new IllegalArgumentException("p must be in [0,1]");
      }
   }

   public Tuple3 meanAndCov(final IterableOnce it1, final IterableOnce it2, final Fractional frac) {
      Tuple4 var6 = (Tuple4)scala.runtime.ZippedIterable2..MODULE$.zippedIterable2ToIterable(new Tuple2Zipped(scala.runtime.Tuple2Zipped.Ops..MODULE$.zipped$extension(scala.Predef..MODULE$.tuple2ToZippedOps(new Tuple2(it1.iterator().to(scala.collection.IterableFactory..MODULE$.toFactory(scala.package..MODULE$.Iterable())), it2.iterator().to(scala.collection.IterableFactory..MODULE$.toFactory(scala.package..MODULE$.Iterable())))), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms()))).foldLeft(new Tuple4(frac.zero(), frac.zero(), frac.zero(), frac.zero()), (acc, y) -> {
         if (acc != null) {
            Object oldMu1 = acc._1();
            Object oldMu2 = acc._2();
            Object oldC = acc._3();
            Object oldN = acc._4();
            Tuple4 var3 = new Tuple4(oldMu1, oldMu2, oldC, oldN);
            Object oldMu1x = var3._1();
            Object oldMu2 = var3._2();
            Object oldC = var3._3();
            Object oldN = var3._4();
            Object newN = frac.mkNumericOps(oldN).$plus(frac.fromInt(1));
            Object newMu1 = frac.mkNumericOps(oldMu1x).$plus(frac.mkNumericOps(frac.mkNumericOps(y._1()).$minus(oldMu1x)).$div(newN));
            Object newMu2 = frac.mkNumericOps(oldMu2).$plus(frac.mkNumericOps(frac.mkNumericOps(y._2()).$minus(oldMu2)).$div(newN));
            Object newC = frac.mkNumericOps(oldC).$plus(frac.mkNumericOps(frac.mkNumericOps(y._1()).$minus(oldMu1x)).$times(frac.mkNumericOps(y._2()).$minus(newMu2)));
            return new Tuple4(newMu1, newMu2, newC, newN);
         } else {
            throw new MatchError(acc);
         }
      });
      if (var6 != null) {
         Object mu1 = var6._1();
         Object mu2 = var6._2();
         Object c = var6._3();
         Object n = var6._4();
         Tuple4 var4 = new Tuple4(mu1, mu2, c, n);
         Object mu1 = var4._1();
         Object mu2 = var4._2();
         Object c = var4._3();
         Object n = var4._4();
         return BoxesRunTime.equals(n, BoxesRunTime.boxToInteger(1)) ? new Tuple3(mu1, mu2, BoxesRunTime.boxToInteger(0)) : new Tuple3(mu1, mu2, frac.mkNumericOps(c).$div(frac.mkNumericOps(n).$minus(frac.fromInt(1))));
      } else {
         throw new MatchError(var6);
      }
   }

   public Object cov(final Iterable it1, final Iterable it2, final Fractional n) {
      return this.meanAndCov(it1, it2, n)._3();
   }

   private DescriptiveStats$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
