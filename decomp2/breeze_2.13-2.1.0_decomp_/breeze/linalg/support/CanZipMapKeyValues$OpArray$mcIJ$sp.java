package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public class CanZipMapKeyValues$OpArray$mcIJ$sp extends CanZipMapKeyValues.OpArray implements CanZipMapKeyValues$mcIIJ$sp {
   private final ClassTag evidence$1;

   public int[] map(final long[] from, final long[] from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public int[] map$mcIJ$sp(final long[] from, final long[] from2, final Function3 fn) {
      .MODULE$.require(from.length == from2.length, () -> "Array lengths don't match!");
      int[] arr = (int[])this.breeze$linalg$support$CanZipMapKeyValues$OpArray$$evidence$1.newArray(from.length);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), from.length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> arr[i] = BoxesRunTime.unboxToInt(fn.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToLong(from[i]), BoxesRunTime.boxToLong(from2[i]))));
      return arr;
   }

   public int[] mapActive(final long[] from, final long[] from2, final Function3 fn) {
      return this.mapActive$mcIJ$sp(from, from2, fn);
   }

   public int[] mapActive$mcIJ$sp(final long[] from, final long[] from2, final Function3 fn) {
      return this.map$mcIJ$sp(from, from2, fn);
   }

   public CanZipMapKeyValues$OpArray$mcIJ$sp(final ClassTag evidence$1) {
      super(evidence$1);
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
