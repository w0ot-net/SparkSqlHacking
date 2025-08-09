package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.java8.JFunction1;

public class CanZipMapValues$OpArray$mcII$sp extends CanZipMapValues.OpArray implements CanZipMapValues$mcII$sp {
   private final ClassTag evidence$1;

   public int[] map(final int[] from, final int[] from2, final Function2 fn) {
      return this.map$mcII$sp(from, from2, fn);
   }

   public int[] map$mcII$sp(final int[] from, final int[] from2, final Function2 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      int[] arr = (int[])this.breeze$linalg$support$CanZipMapValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = fn.apply$mcIII$sp(from[i], from2[i]));
      return arr;
   }

   public CanZipMapValues$OpArray$mcII$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
