package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.Rule;
import org.json4s.scalap.RulesWithState;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m3q!\u0003\u0006\u0011\u0002\u0007\u00051\u0003C\u0003\u001f\u0001\u0011\u0005q$\u0002\u0003$\u0001\u0001!S\u0001\u0002\u0015\u0001\u0001%BqA\u0012\u0001C\u0002\u0013\u0005q\tC\u0004O\u0001\t\u0007I\u0011A(\t\u000fQ\u0003!\u0019!C\u0001\u001f\"9Q\u000b\u0001b\u0001\n\u0003y\u0005\"\u0002,\u0001\t\u00039&A\u0004\"zi\u0016\u001cu\u000eZ3SK\u0006$WM\u001d\u0006\u0003\u00171\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u0003\u001b9\taa]2bY\u0006\u0004(BA\b\u0011\u0003\u0019Q7o\u001c85g*\t\u0011#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007CA\u000e\u001d\u001b\u0005a\u0011BA\u000f\r\u00059\u0011V\u000f\\3t/&$\bn\u0015;bi\u0016\fa\u0001J5oSR$C#\u0001\u0011\u0011\u0005U\t\u0013B\u0001\u0012\u0017\u0005\u0011)f.\u001b;\u0003\u0003M\u0003\"!\n\u0014\u000e\u0003)I!a\n\u0006\u0003\u0011\tKH/Z\"pI\u0016\u0014a\u0001U1sg\u0016\u0014XC\u0001\u00163!\u0011YC\u0006M\u001e\u000e\u0003\u0001I!!\f\u0018\u0003\tI+H.Z\u0005\u0003_1\u0011!b\u0015;bi\u0016\u0014V\u000f\\3t!\t\t$\u0007\u0004\u0001\u0005\u000bM\u001a!\u0019\u0001\u001b\u0003\u0003\u0005\u000b\"!\u000e\u001d\u0011\u0005U1\u0014BA\u001c\u0017\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!F\u001d\n\u0005i2\"aA!osB\u0011Ah\u0011\b\u0003{\u0005\u0003\"A\u0010\f\u000e\u0003}R!\u0001\u0011\n\u0002\rq\u0012xn\u001c;?\u0013\t\u0011e#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\t\u0016\u0013aa\u0015;sS:<'B\u0001\"\u0017\u0003\u0011\u0011\u0017\u0010^3\u0016\u0003!\u0003baG%K\u0015.+\u0014BA\u0017\r!\tY#\u0001\u0005\u0002\u0016\u0019&\u0011QJ\u0006\u0002\u0005\u0005f$X-\u0001\u0002vcU\t\u0001\u000b\u0005\u0004\u001c\u0013*S\u0015+\u000e\t\u0003+IK!a\u0015\f\u0003\u0007%sG/\u0001\u0002ve\u0005\u0011Q\u000fN\u0001\u0006Ef$Xm\u001d\u000b\u00031f\u0003baG%K\u0015\u0012*\u0004\"\u0002.\t\u0001\u0004\t\u0016!\u00018"
)
public interface ByteCodeReader extends RulesWithState {
   void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$byte_$eq(final Rule x$1);

   void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u1_$eq(final Rule x$1);

   void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u2_$eq(final Rule x$1);

   void org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u4_$eq(final Rule x$1);

   Rule byte();

   Rule u1();

   Rule u2();

   Rule u4();

   // $FF: synthetic method
   static Rule bytes$(final ByteCodeReader $this, final int n) {
      return $this.bytes(n);
   }

   default Rule bytes(final int n) {
      return this.apply((x$5) -> x$5.next(n));
   }

   // $FF: synthetic method
   static int $anonfun$u1$1(final byte x$2) {
      return x$2 & 255;
   }

   // $FF: synthetic method
   static int $anonfun$u2$1(final ByteCode x$3) {
      return x$3.toInt();
   }

   // $FF: synthetic method
   static int $anonfun$u4$1(final ByteCode x$4) {
      return x$4.toInt();
   }

   static void $init$(final ByteCodeReader $this) {
      $this.org$json4s$scalap$scalasig$ByteCodeReader$_setter_$byte_$eq($this.apply((x$1) -> x$1.nextByte()));
      $this.org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u1_$eq($this.byte().$up$up((x$2) -> BoxesRunTime.boxToInteger($anonfun$u1$1(BoxesRunTime.unboxToByte(x$2)))));
      $this.org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u2_$eq($this.bytes(2).$up$up((x$3) -> BoxesRunTime.boxToInteger($anonfun$u2$1(x$3))));
      $this.org$json4s$scalap$scalasig$ByteCodeReader$_setter_$u4_$eq($this.bytes(4).$up$up((x$4) -> BoxesRunTime.boxToInteger($anonfun$u4$1(x$4))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
