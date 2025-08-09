package org.apache.spark.util.random;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U:aa\u0002\u0005\t\u00021\u0011bA\u0002\u000b\t\u0011\u0003aQ\u0003C\u0003\u001d\u0003\u0011\u0005a\u0004C\u0004 \u0003\t\u0007I\u0011\u0001\u0011\t\r\u0011\n\u0001\u0015!\u0003\"\u0011\u0015)\u0013\u0001\"\u0001'\u0011\u0015\u0001\u0014\u0001\"\u00012\u00039\u0011\u0015N\\8nS\u0006d'i\\;oINT!!\u0003\u0006\u0002\rI\fg\u000eZ8n\u0015\tYA\"\u0001\u0003vi&d'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0011\u0005M\tQ\"\u0001\u0005\u0003\u001d\tKgn\\7jC2\u0014u.\u001e8egN\u0011\u0011A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012AE\u0001\u0010[&t7+Y7qY&twMU1uKV\t\u0011\u0005\u0005\u0002\u0018E%\u00111\u0005\u0007\u0002\u0007\t>,(\r\\3\u0002!5LgnU1na2Lgn\u001a*bi\u0016\u0004\u0013!D4fi2{w/\u001a:C_VtG\r\u0006\u0003\"O%r\u0003\"\u0002\u0015\u0006\u0001\u0004\t\u0013!\u00023fYR\f\u0007\"\u0002\u0016\u0006\u0001\u0004Y\u0013!\u00018\u0011\u0005]a\u0013BA\u0017\u0019\u0005\u0011auN\\4\t\u000b=*\u0001\u0019A\u0011\u0002\u0011\u0019\u0014\u0018m\u0019;j_:\fQbZ3u+B\u0004XM\u001d\"pk:$G\u0003B\u00113gQBQ\u0001\u000b\u0004A\u0002\u0005BQA\u000b\u0004A\u0002-BQa\f\u0004A\u0002\u0005\u0002"
)
public final class BinomialBounds {
   public static double getUpperBound(final double delta, final long n, final double fraction) {
      return BinomialBounds$.MODULE$.getUpperBound(delta, n, fraction);
   }

   public static double getLowerBound(final double delta, final long n, final double fraction) {
      return BinomialBounds$.MODULE$.getLowerBound(delta, n, fraction);
   }

   public static double minSamplingRate() {
      return BinomialBounds$.MODULE$.minSamplingRate();
   }
}
