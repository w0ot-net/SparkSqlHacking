package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public class CanZipMapValues$OpArray$mcJF$sp extends CanZipMapValues.OpArray implements CanZipMapValues$mcJF$sp {
   private final ClassTag evidence$1;

   public long[] map(final float[] from, final float[] from2, final Function2 fn) {
      return this.map$mcJF$sp(from, from2, fn);
   }

   public long[] map$mcJF$sp(final float[] from, final float[] from2, final Function2 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      long[] arr = (long[])this.breeze$linalg$support$CanZipMapValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = BoxesRunTime.unboxToLong(fn.apply(BoxesRunTime.boxToFloat(from[i]), BoxesRunTime.boxToFloat(from2[i]))));
      return arr;
   }

   public CanZipMapValues$OpArray$mcJF$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
