package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public class CanZipMapKeyValues$OpArray$mcDI$sp extends CanZipMapKeyValues.OpArray implements CanZipMapKeyValues$mcIDI$sp {
   private final ClassTag evidence$1;

   public double[] map(final int[] from, final int[] from2, final Function3 fn) {
      return this.map$mcDI$sp(from, from2, fn);
   }

   public double[] map$mcDI$sp(final int[] from, final int[] from2, final Function3 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      double[] arr = (double[])this.breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = BoxesRunTime.unboxToDouble(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(from[i]), BoxesRunTime.boxToInteger(from2[i]))));
      return arr;
   }

   public double[] mapActive(final int[] from, final int[] from2, final Function3 fn) {
      return this.mapActive$mcDI$sp(from, from2, fn);
   }

   public double[] mapActive$mcDI$sp(final int[] from, final int[] from2, final Function3 fn) {
      return this.map$mcDI$sp(from, from2, fn);
   }

   public CanZipMapKeyValues$OpArray$mcDI$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
