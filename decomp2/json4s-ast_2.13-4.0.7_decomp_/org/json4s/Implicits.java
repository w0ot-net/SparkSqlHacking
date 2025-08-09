package org.json4s;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4q!\u0004\b\u0011\u0002\u0007\u00051\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\r\u0001\u0005C\u0003/\u0001\u0011\rq\u0006C\u00035\u0001\u0011\rQ\u0007C\u0003;\u0001\u0011\r1\bC\u0003A\u0001\u0011\r\u0011\tC\u0003G\u0001\u0011\rq\tC\u0003V\u0001\u0019\ra\u000bC\u0003\\\u0001\u0019\rA\fC\u0003b\u0001\u0019\r!\rC\u0003h\u0001\u0011\r\u0001\u000eC\u0003n\u0001\u0011\raNA\u0005J[Bd\u0017nY5ug*\u0011q\u0002E\u0001\u0007UN|g\u000eN:\u000b\u0003E\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tA\u0004\u0005\u0002\u0016;%\u0011aD\u0006\u0002\u0005+:LG/\u0001\u0007tQ>\u0014HO\r6wC2,X\r\u0006\u0002\"SA\u0011!E\n\b\u0003G\u0011j\u0011AD\u0005\u0003K9\tqAS:p]\u0006\u001bF+\u0003\u0002(Q\t1!JV1mk\u0016T!!\n\b\t\u000b)\u0012\u0001\u0019A\u0016\u0002\u0003a\u0004\"!\u0006\u0017\n\u000552\"!B*i_J$\u0018a\u00032zi\u0016\u0014$N^1mk\u0016$\"!\t\u0019\t\u000b)\u001a\u0001\u0019A\u0019\u0011\u0005U\u0011\u0014BA\u001a\u0017\u0005\u0011\u0011\u0015\u0010^3\u0002\u0017\rD\u0017M\u001d\u001akm\u0006dW/\u001a\u000b\u0003CYBQA\u000b\u0003A\u0002]\u0002\"!\u0006\u001d\n\u0005e2\"\u0001B\"iCJ\f!\"\u001b8ue)4\u0018\r\\;f)\t\tC\bC\u0003+\u000b\u0001\u0007Q\b\u0005\u0002\u0016}%\u0011qH\u0006\u0002\u0004\u0013:$\u0018a\u00037p]\u001e\u0014$N^1mk\u0016$\"!\t\"\t\u000b)2\u0001\u0019A\"\u0011\u0005U!\u0015BA#\u0017\u0005\u0011auN\\4\u0002\u001b\tLw-\u001b8ue)4\u0018\r\\;f)\t\t\u0003\nC\u0003+\u000f\u0001\u0007\u0011\n\u0005\u0002K%:\u00111\n\u0015\b\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001dJ\ta\u0001\u0010:p_Rt\u0014\"A\f\n\u0005E3\u0012a\u00029bG.\fw-Z\u0005\u0003'R\u0013aAQ5h\u0013:$(BA)\u0017\u00035!w.\u001e2mKJRg/\u00197vKR\u0011\u0011e\u0016\u0005\u0006U!\u0001\r\u0001\u0017\t\u0003+eK!A\u0017\f\u0003\r\u0011{WO\u00197f\u000311Gn\\1ue)4\u0018\r\\;f)\t\tS\fC\u0003+\u0013\u0001\u0007a\f\u0005\u0002\u0016?&\u0011\u0001M\u0006\u0002\u0006\r2|\u0017\r^\u0001\u0012E&<G-Z2j[\u0006d'G\u001b<bYV,GCA\u0011d\u0011\u0015Q#\u00021\u0001e!\tQU-\u0003\u0002g)\nQ!)[4EK\u000eLW.\u00197\u0002\u001d\t|w\u000e\\3b]JRg/\u00197vKR\u0011\u0011%\u001b\u0005\u0006U-\u0001\rA\u001b\t\u0003+-L!\u0001\u001c\f\u0003\u000f\t{w\u000e\\3b]\u0006i1\u000f\u001e:j]\u001e\u0014$N^1mk\u0016$\"!I8\t\u000b)b\u0001\u0019\u00019\u0011\u0005E,hB\u0001:t!\tae#\u0003\u0002u-\u00051\u0001K]3eK\u001aL!A^<\u0003\rM#(/\u001b8h\u0015\t!h\u0003"
)
public interface Implicits {
   // $FF: synthetic method
   static JValue short2jvalue$(final Implicits $this, final short x) {
      return $this.short2jvalue(x);
   }

   default JValue short2jvalue(final short x) {
      return JsonAST$.MODULE$.JInt().apply(.MODULE$.int2bigInt(x));
   }

   // $FF: synthetic method
   static JValue byte2jvalue$(final Implicits $this, final byte x) {
      return $this.byte2jvalue(x);
   }

   default JValue byte2jvalue(final byte x) {
      return JsonAST$.MODULE$.JInt().apply(.MODULE$.int2bigInt(x));
   }

   // $FF: synthetic method
   static JValue char2jvalue$(final Implicits $this, final char x) {
      return $this.char2jvalue(x);
   }

   default JValue char2jvalue(final char x) {
      return JsonAST$.MODULE$.JInt().apply(.MODULE$.int2bigInt(x));
   }

   // $FF: synthetic method
   static JValue int2jvalue$(final Implicits $this, final int x) {
      return $this.int2jvalue(x);
   }

   default JValue int2jvalue(final int x) {
      return JsonAST$.MODULE$.JInt().apply(.MODULE$.int2bigInt(x));
   }

   // $FF: synthetic method
   static JValue long2jvalue$(final Implicits $this, final long x) {
      return $this.long2jvalue(x);
   }

   default JValue long2jvalue(final long x) {
      return JsonAST$.MODULE$.JInt().apply(.MODULE$.long2bigInt(x));
   }

   // $FF: synthetic method
   static JValue bigint2jvalue$(final Implicits $this, final BigInt x) {
      return $this.bigint2jvalue(x);
   }

   default JValue bigint2jvalue(final BigInt x) {
      return JsonAST$.MODULE$.JInt().apply(x);
   }

   JValue double2jvalue(final double x);

   JValue float2jvalue(final float x);

   JValue bigdecimal2jvalue(final BigDecimal x);

   // $FF: synthetic method
   static JValue boolean2jvalue$(final Implicits $this, final boolean x) {
      return $this.boolean2jvalue(x);
   }

   default JValue boolean2jvalue(final boolean x) {
      return JsonAST$.MODULE$.JBool().apply(x);
   }

   // $FF: synthetic method
   static JValue string2jvalue$(final Implicits $this, final String x) {
      return $this.string2jvalue(x);
   }

   default JValue string2jvalue(final String x) {
      return JsonAST$.MODULE$.JString().apply(x);
   }

   static void $init$(final Implicits $this) {
   }
}
