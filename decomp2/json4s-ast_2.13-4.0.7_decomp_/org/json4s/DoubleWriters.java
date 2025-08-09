package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f\u0019\u0002!\u0019!C\u0002O!9A\u0006\u0001b\u0001\n\u0007is!B\u001e\n\u0011\u0003ad!\u0002\u0005\n\u0011\u0003i\u0004\"B \u0007\t\u0003\u0001%!\u0004#pk\ndWm\u0016:ji\u0016\u00148O\u0003\u0002\u000b\u0017\u00051!n]8oiMT\u0011\u0001D\u0001\u0004_J<7\u0001A\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u0017/5\t\u0011\"\u0003\u0002\u0019\u0013\tqA)\u001a4bk2$xK]5uKJ\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001c!\t\u0001B$\u0003\u0002\u001e#\t!QK\\5u\u0003-1En\\1u/JLG/\u001a:\u0016\u0003\u0001\u00022AF\u0011$\u0013\t\u0011\u0013B\u0001\u0004Xe&$XM\u001d\t\u0003!\u0011J!!J\t\u0003\u000b\u0019cw.\u0019;\u0002\u0019\u0011{WO\u00197f/JLG/\u001a:\u0016\u0003!\u00022AF\u0011*!\t\u0001\"&\u0003\u0002,#\t1Ai\\;cY\u0016\f\u0001CQ5h\t\u0016\u001c\u0017.\\1m/JLG/\u001a:\u0016\u00039\u00022AF\u00110!\t\u0001\u0004H\u0004\u00022m9\u0011!'N\u0007\u0002g)\u0011A'D\u0001\u0007yI|w\u000e\u001e \n\u0003II!aN\t\u0002\u000fA\f7m[1hK&\u0011\u0011H\u000f\u0002\u000b\u0005&<G)Z2j[\u0006d'BA\u001c\u0012\u00035!u.\u001e2mK^\u0013\u0018\u000e^3sgB\u0011aCB\n\u0004\r=q\u0004C\u0001\f\u0001\u0003\u0019a\u0014N\\5u}Q\tA\b"
)
public interface DoubleWriters extends DefaultWriters {
   void org$json4s$DoubleWriters$_setter_$FloatWriter_$eq(final Writer x$1);

   void org$json4s$DoubleWriters$_setter_$DoubleWriter_$eq(final Writer x$1);

   void org$json4s$DoubleWriters$_setter_$BigDecimalWriter_$eq(final Writer x$1);

   Writer FloatWriter();

   Writer DoubleWriter();

   Writer BigDecimalWriter();

   // $FF: synthetic method
   static JDouble $anonfun$FloatWriter$1(final float x) {
      return new JDouble((double)x);
   }

   // $FF: synthetic method
   static JDouble $anonfun$DoubleWriter$1(final double x$8) {
      return new JDouble(x$8);
   }

   static void $init$(final DoubleWriters $this) {
      $this.org$json4s$DoubleWriters$_setter_$FloatWriter_$eq($this.new W((x) -> $anonfun$FloatWriter$1(BoxesRunTime.unboxToFloat(x))));
      $this.org$json4s$DoubleWriters$_setter_$DoubleWriter_$eq($this.new W((x$8) -> $anonfun$DoubleWriter$1(BoxesRunTime.unboxToDouble(x$8))));
      $this.org$json4s$DoubleWriters$_setter_$BigDecimalWriter_$eq($this.new W((d) -> new JDouble(d.doubleValue())));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
