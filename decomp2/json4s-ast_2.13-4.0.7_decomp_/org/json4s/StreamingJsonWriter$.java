package org.json4s;

import scala.runtime.RichFloat.;

public final class StreamingJsonWriter$ {
   public static final StreamingJsonWriter$ MODULE$ = new StreamingJsonWriter$();
   private static final String posInfinityVal = "1e+500";
   private static final String negInfiniteVal = "-1e+500";

   public String handleInfinity(final float value) {
      return .MODULE$.isPosInfinity$extension(scala.Predef..MODULE$.floatWrapper(value)) ? posInfinityVal : (.MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.floatWrapper(value)) ? negInfiniteVal : Float.toString(value));
   }

   public String handleInfinity(final double value) {
      return scala.runtime.RichDouble..MODULE$.isPosInfinity$extension(scala.Predef..MODULE$.doubleWrapper(value)) ? posInfinityVal : (scala.runtime.RichDouble..MODULE$.isNegInfinity$extension(scala.Predef..MODULE$.doubleWrapper(value)) ? negInfiniteVal : Double.toString(value));
   }

   private StreamingJsonWriter$() {
   }
}
