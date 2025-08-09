package org.json4s;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001\u0004C\u0003#\u0001\u0011\r1\u0005C\u0003)\u0001\u0011\u0005\u0011F\u0001\fEs:\fW.[2K-\u0006dW/Z%na2L7-\u001b;t\u0015\t9\u0001\"\u0001\u0004kg>tGg\u001d\u0006\u0002\u0013\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005!\u0002CA\u0007\u0016\u0013\t1bB\u0001\u0003V]&$\u0018A\u00033z]\u0006l\u0017n\u0019\u001aKmR\u0011\u0011$\b\t\u00035mi\u0011AB\u0005\u00039\u0019\u0011aA\u0013,bYV,\u0007\"\u0002\u0010\u0003\u0001\u0004y\u0012!\u00023z]*3\bC\u0001\u000e!\u0013\t\tcAA\u0007Es:\fW.[2K-\u0006dW/Z\u0001\u0010Ift\u0017-\\5de5|g.\u00193jGR\u0011Ae\n\t\u00035\u0015J!A\n\u0004\u0003\u001b5{g.\u00193jG*3\u0016\r\\;f\u0011\u0015q2\u00011\u0001 \u0003\r!\u0017P\u001c\u000b\u0003?)BQa\u000b\u0003A\u0002e\t!A\u001b<"
)
public interface DynamicJValueImplicits {
   // $FF: synthetic method
   static JValue dynamic2Jv$(final DynamicJValueImplicits $this, final DynamicJValue dynJv) {
      return $this.dynamic2Jv(dynJv);
   }

   default JValue dynamic2Jv(final DynamicJValue dynJv) {
      return dynJv.raw();
   }

   // $FF: synthetic method
   static JValue dynamic2monadic$(final DynamicJValueImplicits $this, final DynamicJValue dynJv) {
      return $this.dynamic2monadic(dynJv);
   }

   default JValue dynamic2monadic(final DynamicJValue dynJv) {
      return dynJv.raw();
   }

   // $FF: synthetic method
   static DynamicJValue dyn$(final DynamicJValueImplicits $this, final JValue jv) {
      return $this.dyn(jv);
   }

   default DynamicJValue dyn(final JValue jv) {
      return new DynamicJValue(jv);
   }

   static void $init$(final DynamicJValueImplicits $this) {
   }
}
