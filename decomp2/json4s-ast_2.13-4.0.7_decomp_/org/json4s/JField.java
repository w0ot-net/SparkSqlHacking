package org.json4s;

import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}:Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQAL\u0001\u0005\u0002=\naA\u0013$jK2$'BA\u0004\t\u0003\u0019Q7o\u001c85g*\t\u0011\"A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\r\u00035\taA\u0001\u0004K\r&,G\u000eZ\n\u0003\u0003=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\f\u0003\u0015\t\u0007\u000f\u001d7z)\rI\"\u0006\f\t\u0005!iar%\u0003\u0002\u001c#\t1A+\u001e9mKJ\u0002\"!\b\u0013\u000f\u0005y\u0011\u0003CA\u0010\u0012\u001b\u0005\u0001#BA\u0011\u000b\u0003\u0019a$o\\8u}%\u00111%E\u0001\u0007!J,G-\u001a4\n\u0005\u00152#AB*ue&twM\u0003\u0002$#A\u0011A\u0002K\u0005\u0003S\u0019\u0011aA\u0013,bYV,\u0007\"B\u0016\u0004\u0001\u0004a\u0012\u0001\u00028b[\u0016DQ!L\u0002A\u0002\u001d\nQA^1mk\u0016\fq!\u001e8baBd\u0017\u0010\u0006\u00021{A\u0019A\"M\u001a\n\u0005I2!!C*p[\u00164\u0016\r\\;f!\t!4H\u0004\u00026s9\u0011a\u0007\u000f\b\u0003?]J\u0011!C\u0005\u0003\u000f!I!A\u000f\u0004\u0002\u000f)\u001bxN\\!T)&\u0011Q\u0002\u0010\u0006\u0003u\u0019AQA\u0010\u0003A\u0002M\n\u0011A\u001a"
)
public final class JField {
   public static Tuple2 unapply(final Tuple2 f) {
      return JField$.MODULE$.unapply(f);
   }

   public static Tuple2 apply(final String name, final JValue value) {
      return JField$.MODULE$.apply(name, value);
   }
}
