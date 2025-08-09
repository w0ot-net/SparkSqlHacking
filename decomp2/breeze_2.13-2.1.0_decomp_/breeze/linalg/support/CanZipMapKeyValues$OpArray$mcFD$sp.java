package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public class CanZipMapKeyValues$OpArray$mcFD$sp extends CanZipMapKeyValues.OpArray implements CanZipMapKeyValues$mcIFD$sp {
   private final ClassTag evidence$1;

   public float[] map(final double[] from, final double[] from2, final Function3 fn) {
      return this.map$mcFD$sp(from, from2, fn);
   }

   public float[] map$mcFD$sp(final double[] from, final double[] from2, final Function3 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      float[] arr = (float[])this.breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = BoxesRunTime.unboxToFloat(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(from[i]), BoxesRunTime.boxToDouble(from2[i]))));
      return arr;
   }

   public float[] mapActive(final double[] from, final double[] from2, final Function3 fn) {
      return this.mapActive$mcFD$sp(from, from2, fn);
   }

   public float[] mapActive$mcFD$sp(final double[] from, final double[] from2, final Function3 fn) {
      return this.map$mcFD$sp(from, from2, fn);
   }

   public CanZipMapKeyValues$OpArray$mcFD$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
