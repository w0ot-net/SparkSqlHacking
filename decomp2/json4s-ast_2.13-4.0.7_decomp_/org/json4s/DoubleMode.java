package org.json4s;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3\u0001\u0002C\u0005\u0011\u0002\u0007\u0005aB\u0010\u0005\u0006+\u0001!\tA\u0006\u0005\u00065\u0001!\u0019a\u0007\u0005\u0006S\u0001!\u0019A\u000b\u0005\u0006_\u0001!\u0019\u0001M\u0004\u0006\u000b&A\tA\u0012\u0004\u0006\u0011%A\ta\u0012\u0005\u0006\u0011\u001a!\t!\u0013\u0002\u000b\t>,(\r\\3N_\u0012,'B\u0001\u0006\f\u0003\u0019Q7o\u001c85g*\tA\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001fA\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\f\u0011\u0005AA\u0012BA\r\u0012\u0005\u0011)f.\u001b;\u0002\u001b\u0011|WO\u00197fe)4\u0018\r\\;f)\taB\u0005\u0005\u0002\u001eC9\u0011adH\u0007\u0002\u0013%\u0011\u0001%C\u0001\b\u0015N|g.Q*U\u0013\t\u00113E\u0001\u0004K-\u0006dW/\u001a\u0006\u0003A%AQ!\n\u0002A\u0002\u0019\n\u0011\u0001\u001f\t\u0003!\u001dJ!\u0001K\t\u0003\r\u0011{WO\u00197f\u000311Gn\\1ue)4\u0018\r\\;f)\ta2\u0006C\u0003&\u0007\u0001\u0007A\u0006\u0005\u0002\u0011[%\u0011a&\u0005\u0002\u0006\r2|\u0017\r^\u0001\u0012E&<G-Z2j[\u0006d'G\u001b<bYV,GC\u0001\u000f2\u0011\u0015)C\u00011\u00013!\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011q'D\u0001\u0007yI|w\u000e\u001e \n\u0003II!AO\t\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\u000b\u0005&<G)Z2j[\u0006d'B\u0001\u001e\u0012%\ry\u0014I\u0011\u0004\u0005\u0001\u0002\u0001aH\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002\u001f\u0001A\u0011adQ\u0005\u0003\t&\u0011\u0011\"S7qY&\u001c\u0017\u000e^:\u0002\u0015\u0011{WO\u00197f\u001b>$W\r\u0005\u0002\u001f\rM!aa\u0004\"B\u0003\u0019a\u0014N\\5u}Q\ta\t"
)
public interface DoubleMode {
   static JValue string2jvalue(final String x) {
      return DoubleMode$.MODULE$.string2jvalue(x);
   }

   static JValue boolean2jvalue(final boolean x) {
      return DoubleMode$.MODULE$.boolean2jvalue(x);
   }

   static JValue bigint2jvalue(final BigInt x) {
      return DoubleMode$.MODULE$.bigint2jvalue(x);
   }

   static JValue long2jvalue(final long x) {
      return DoubleMode$.MODULE$.long2jvalue(x);
   }

   static JValue int2jvalue(final int x) {
      return DoubleMode$.MODULE$.int2jvalue(x);
   }

   static JValue char2jvalue(final char x) {
      return DoubleMode$.MODULE$.char2jvalue(x);
   }

   static JValue byte2jvalue(final byte x) {
      return DoubleMode$.MODULE$.byte2jvalue(x);
   }

   static JValue short2jvalue(final short x) {
      return DoubleMode$.MODULE$.short2jvalue(x);
   }

   // $FF: synthetic method
   static JValue double2jvalue$(final DoubleMode $this, final double x) {
      return $this.double2jvalue(x);
   }

   default JValue double2jvalue(final double x) {
      return JsonAST$.MODULE$.JDouble().apply(x);
   }

   // $FF: synthetic method
   static JValue float2jvalue$(final DoubleMode $this, final float x) {
      return $this.float2jvalue(x);
   }

   default JValue float2jvalue(final float x) {
      return JsonAST$.MODULE$.JDouble().apply((double)x);
   }

   // $FF: synthetic method
   static JValue bigdecimal2jvalue$(final DoubleMode $this, final BigDecimal x) {
      return $this.bigdecimal2jvalue(x);
   }

   default JValue bigdecimal2jvalue(final BigDecimal x) {
      return JsonAST$.MODULE$.JDouble().apply(x.doubleValue());
   }

   static void $init$(final DoubleMode $this) {
   }
}
