package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public class CanZipMapKeyValues$OpArray$mcJD$sp extends CanZipMapKeyValues.OpArray implements CanZipMapKeyValues$mcIJD$sp {
   private final ClassTag evidence$1;

   public long[] map(final double[] from, final double[] from2, final Function3 fn) {
      return this.map$mcJD$sp(from, from2, fn);
   }

   public long[] map$mcJD$sp(final double[] from, final double[] from2, final Function3 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      long[] arr = (long[])this.breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = BoxesRunTime.unboxToLong(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(from[i]), BoxesRunTime.boxToDouble(from2[i]))));
      return arr;
   }

   public long[] mapActive(final double[] from, final double[] from2, final Function3 fn) {
      return this.mapActive$mcJD$sp(from, from2, fn);
   }

   public long[] mapActive$mcJD$sp(final double[] from, final double[] from2, final Function3 fn) {
      return this.map$mcJD$sp(from, from2, fn);
   }

   public CanZipMapKeyValues$OpArray$mcJD$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
