package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.math.BigDecimal.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0004\u001f\u0001\t\u0007I1A\u0010\t\u000f\u0019\u0002!\u0019!C\u0002O!9A\u0006\u0001b\u0001\n\u0007is!B\u001e\n\u0011\u0003ad!\u0002\u0005\n\u0011\u0003i\u0004\"B \u0007\t\u0003\u0001%!\u0005\"jO\u0012+7-[7bY^\u0013\u0018\u000e^3sg*\u0011!bC\u0001\u0007UN|g\u000eN:\u000b\u00031\t1a\u001c:h\u0007\u0001\u00192\u0001A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011acF\u0007\u0002\u0013%\u0011\u0001$\u0003\u0002\u000f\t\u00164\u0017-\u001e7u/JLG/\u001a:t\u0003\u0019!\u0013N\\5uIQ\t1\u0004\u0005\u0002\u00119%\u0011Q$\u0005\u0002\u0005+:LG/A\u0006GY>\fGo\u0016:ji\u0016\u0014X#\u0001\u0011\u0011\u0007Y\t3%\u0003\u0002#\u0013\t1qK]5uKJ\u0004\"\u0001\u0005\u0013\n\u0005\u0015\n\"!\u0002$m_\u0006$\u0018\u0001\u0004#pk\ndWm\u0016:ji\u0016\u0014X#\u0001\u0015\u0011\u0007Y\t\u0013\u0006\u0005\u0002\u0011U%\u00111&\u0005\u0002\u0007\t>,(\r\\3\u0002!\tKw\rR3dS6\fGn\u0016:ji\u0016\u0014X#\u0001\u0018\u0011\u0007Y\ts\u0006\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i5\ta\u0001\u0010:p_Rt\u0014\"\u0001\n\n\u0005]\n\u0012a\u00029bG.\fw-Z\u0005\u0003si\u0012!BQ5h\t\u0016\u001c\u0017.\\1m\u0015\t9\u0014#A\tCS\u001e$UmY5nC2<&/\u001b;feN\u0004\"A\u0006\u0004\u0014\u0007\u0019ya\b\u0005\u0002\u0017\u0001\u00051A(\u001b8jiz\"\u0012\u0001\u0010"
)
public interface BigDecimalWriters extends DefaultWriters {
   void org$json4s$BigDecimalWriters$_setter_$FloatWriter_$eq(final Writer x$1);

   void org$json4s$BigDecimalWriters$_setter_$DoubleWriter_$eq(final Writer x$1);

   void org$json4s$BigDecimalWriters$_setter_$BigDecimalWriter_$eq(final Writer x$1);

   Writer FloatWriter();

   Writer DoubleWriter();

   Writer BigDecimalWriter();

   // $FF: synthetic method
   static JDecimal $anonfun$FloatWriter$2(final float x) {
      return new JDecimal(.MODULE$.double2bigDecimal((double)x));
   }

   // $FF: synthetic method
   static JDecimal $anonfun$DoubleWriter$2(final double x$9) {
      return new JDecimal(.MODULE$.double2bigDecimal(x$9));
   }

   static void $init$(final BigDecimalWriters $this) {
      $this.org$json4s$BigDecimalWriters$_setter_$FloatWriter_$eq($this.new W((x) -> $anonfun$FloatWriter$2(BoxesRunTime.unboxToFloat(x))));
      $this.org$json4s$BigDecimalWriters$_setter_$DoubleWriter_$eq($this.new W((x$9) -> $anonfun$DoubleWriter$2(BoxesRunTime.unboxToDouble(x$9))));
      $this.org$json4s$BigDecimalWriters$_setter_$BigDecimalWriter_$eq($this.new W((d) -> new JDecimal(d)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
