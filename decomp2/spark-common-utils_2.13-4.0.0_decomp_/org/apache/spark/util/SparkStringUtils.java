package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m2\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\t!H\u0004\u0007i\u001dA\t!C\u001b\u0007\r\u00199\u0001\u0012A\u00058\u0011\u0015ID\u0001\"\u0001;\u0005A\u0019\u0006/\u0019:l'R\u0014\u0018N\\4Vi&d7O\u0003\u0002\t\u0013\u0005!Q\u000f^5m\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7C\u0001\u0001\u0011!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001a!\t\t\"$\u0003\u0002\u001c%\t!QK\\5u\u0003-\u0019HO]5oOR{7+Z9\u0015\u0005y\u0011\u0004cA\u0010(U9\u0011\u0001%\n\b\u0003C\u0011j\u0011A\t\u0006\u0003G]\ta\u0001\u0010:p_Rt\u0014\"A\n\n\u0005\u0019\u0012\u0012a\u00029bG.\fw-Z\u0005\u0003Q%\u00121aU3r\u0015\t1#\u0003\u0005\u0002,_9\u0011A&\f\t\u0003CII!A\f\n\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]IAQa\r\u0002A\u0002)\n1a\u001d;s\u0003A\u0019\u0006/\u0019:l'R\u0014\u0018N\\4Vi&d7\u000f\u0005\u00027\t5\tqaE\u0002\u0005!a\u0002\"A\u000e\u0001\u0002\rqJg.\u001b;?)\u0005)\u0004"
)
public interface SparkStringUtils {
   // $FF: synthetic method
   static Seq stringToSeq$(final SparkStringUtils $this, final String str) {
      return $this.stringToSeq(str);
   }

   default Seq stringToSeq(final String str) {
      return ArrayImplicits$.MODULE$.SparkArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])str.split(",")), (x$1) -> x$1.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$stringToSeq$2(x$2)))).toImmutableArraySeq();
   }

   // $FF: synthetic method
   static boolean $anonfun$stringToSeq$2(final String x$2) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   static void $init$(final SparkStringUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
