package org.json4s;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.BigDecimal.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3\u0001\u0002C\u0005\u0011\u0002\u0007\u0005aB\u0010\u0005\u0006+\u0001!\tA\u0006\u0005\u00065\u0001!\u0019a\u0007\u0005\u0006S\u0001!\u0019A\u000b\u0005\u0006_\u0001!\u0019\u0001M\u0004\u0006\u000b&A\tA\u0012\u0004\u0006\u0011%A\ta\u0012\u0005\u0006\u0011\u001a!\t!\u0013\u0002\u000f\u0005&<G)Z2j[\u0006dWj\u001c3f\u0015\tQ1\"\u0001\u0004kg>tGg\u001d\u0006\u0002\u0019\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\u0004\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u00059\u0002C\u0001\t\u0019\u0013\tI\u0012C\u0001\u0003V]&$\u0018!\u00043pk\ndWM\r6wC2,X\r\u0006\u0002\u001dIA\u0011Q$\t\b\u0003=}i\u0011!C\u0005\u0003A%\tqAS:p]\u0006\u001bF+\u0003\u0002#G\t1!JV1mk\u0016T!\u0001I\u0005\t\u000b\u0015\u0012\u0001\u0019\u0001\u0014\u0002\u0003a\u0004\"\u0001E\u0014\n\u0005!\n\"A\u0002#pk\ndW-\u0001\u0007gY>\fGO\r6wC2,X\r\u0006\u0002\u001dW!)Qe\u0001a\u0001YA\u0011\u0001#L\u0005\u0003]E\u0011QA\u00127pCR\f\u0011CY5hI\u0016\u001c\u0017.\\1me)4\u0018\r\\;f)\ta\u0012\u0007C\u0003&\t\u0001\u0007!\u0007\u0005\u00024w9\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003o5\ta\u0001\u0010:p_Rt\u0014\"\u0001\n\n\u0005i\n\u0012a\u00029bG.\fw-Z\u0005\u0003yu\u0012!BQ5h\t\u0016\u001c\u0017.\\1m\u0015\tQ\u0014CE\u0002@\u0003\n3A\u0001\u0011\u0001\u0001}\taAH]3gS:,W.\u001a8u}A\u0011a\u0004\u0001\t\u0003=\rK!\u0001R\u0005\u0003\u0013%k\u0007\u000f\\5dSR\u001c\u0018A\u0004\"jO\u0012+7-[7bY6{G-\u001a\t\u0003=\u0019\u0019BAB\bC\u0003\u00061A(\u001b8jiz\"\u0012A\u0012"
)
public interface BigDecimalMode {
   static JValue string2jvalue(final String x) {
      return BigDecimalMode$.MODULE$.string2jvalue(x);
   }

   static JValue boolean2jvalue(final boolean x) {
      return BigDecimalMode$.MODULE$.boolean2jvalue(x);
   }

   static JValue bigint2jvalue(final BigInt x) {
      return BigDecimalMode$.MODULE$.bigint2jvalue(x);
   }

   static JValue long2jvalue(final long x) {
      return BigDecimalMode$.MODULE$.long2jvalue(x);
   }

   static JValue int2jvalue(final int x) {
      return BigDecimalMode$.MODULE$.int2jvalue(x);
   }

   static JValue char2jvalue(final char x) {
      return BigDecimalMode$.MODULE$.char2jvalue(x);
   }

   static JValue byte2jvalue(final byte x) {
      return BigDecimalMode$.MODULE$.byte2jvalue(x);
   }

   static JValue short2jvalue(final short x) {
      return BigDecimalMode$.MODULE$.short2jvalue(x);
   }

   // $FF: synthetic method
   static JValue double2jvalue$(final BigDecimalMode $this, final double x) {
      return $this.double2jvalue(x);
   }

   default JValue double2jvalue(final double x) {
      return JsonAST$.MODULE$.JDecimal().apply(.MODULE$.double2bigDecimal(x));
   }

   // $FF: synthetic method
   static JValue float2jvalue$(final BigDecimalMode $this, final float x) {
      return $this.float2jvalue(x);
   }

   default JValue float2jvalue(final float x) {
      return JsonAST$.MODULE$.JDecimal().apply(.MODULE$.double2bigDecimal((double)x));
   }

   // $FF: synthetic method
   static JValue bigdecimal2jvalue$(final BigDecimalMode $this, final BigDecimal x) {
      return $this.bigdecimal2jvalue(x);
   }

   default JValue bigdecimal2jvalue(final BigDecimal x) {
      return JsonAST$.MODULE$.JDecimal().apply(x);
   }

   static void $init$(final BigDecimalMode $this) {
   }
}
