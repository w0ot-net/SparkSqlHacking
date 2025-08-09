package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction1;

public class CanZipMapValues$OpArray$mcJJ$sp extends CanZipMapValues.OpArray implements CanZipMapValues$mcJJ$sp {
   private final ClassTag evidence$1;

   public long[] map(final long[] from, final long[] from2, final Function2 fn) {
      return this.map$mcJJ$sp(from, from2, fn);
   }

   public long[] map$mcJJ$sp(final long[] from, final long[] from2, final Function2 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      long[] arr = (long[])this.breeze$linalg$support$CanZipMapValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = fn.apply$mcJJJ$sp(from[i], from2[i]));
      return arr;
   }

   public CanZipMapValues$OpArray$mcJJ$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
