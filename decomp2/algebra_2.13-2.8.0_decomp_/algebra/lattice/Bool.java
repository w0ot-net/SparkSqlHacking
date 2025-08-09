package algebra.lattice;

import algebra.ring.BoolRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uaa\u0002\u0007\u000e!\u0003\r\tA\u0005\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0005\u0006\u001f\u0002!\t\u0005\u0015\u0005\u0006%\u0002!\teU\u0004\u000656A\ta\u0017\u0004\u0006\u00195A\t\u0001\u0018\u0005\u0006_\"!\t\u0001\u001d\u0005\u0006c\"!)A\u001d\u0005\n\u0003\u000bA\u0011\u0011!C\u0005\u0003\u000f\u0011AAQ8pY*\u0011abD\u0001\bY\u0006$H/[2f\u0015\u0005\u0001\u0012aB1mO\u0016\u0014'/Y\u0002\u0001+\t\u0019\u0002e\u0005\u0003\u0001)iI\u0004CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"aA!osB\u00191\u0004\b\u0010\u000e\u00035I!!H\u0007\u0003\u000f!+\u0017\u0010^5oOB\u0011q\u0004\t\u0007\u0001\t%\t\u0003\u0001)A\u0001\u0002\u000b\u0007!EA\u0001B#\t\u0019C\u0003\u0005\u0002\u0016I%\u0011QE\u0006\u0002\b\u001d>$\b.\u001b8hQ\u0011\u0001sE\u000b\u001b\u0011\u0005UA\u0013BA\u0015\u0017\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rZCFL\u0017\u000f\u0005Ua\u0013BA\u0017\u0017\u0003\rIe\u000e^\u0019\u0005I=\u001atC\u0004\u00021g5\t\u0011G\u0003\u00023#\u00051AH]8pizJ\u0011aF\u0019\u0006GU2\u0004h\u000e\b\u0003+YJ!a\u000e\f\u0002\t1{gnZ\u0019\u0005I=\u001at\u0003E\u0002\u001cuyI!aO\u0007\u0003\u000f\u001d+gNQ8pY\u00061A%\u001b8ji\u0012\"\u0012A\u0010\t\u0003+}J!\u0001\u0011\f\u0003\tUs\u0017\u000e^\u0001\u0004S6\u0004Hc\u0001\u0010D\u000b\")AI\u0001a\u0001=\u0005\t\u0011\rC\u0003G\u0005\u0001\u0007a$A\u0001c\u0003\u001d9\u0018\u000e\u001e5pkR$2AH%K\u0011\u0015!5\u00011\u0001\u001f\u0011\u001515\u00011\u0001\u001f\u0003\rAxN\u001d\u000b\u0004=5s\u0005\"\u0002#\u0005\u0001\u0004q\u0002\"\u0002$\u0005\u0001\u0004q\u0012\u0001\u00023vC2,\u0012!\u0015\t\u00047\u0001q\u0012AC1t\u0005>|GNU5oOV\tA\u000bE\u0002V1zi\u0011A\u0016\u0006\u0003/>\tAA]5oO&\u0011\u0011L\u0016\u0002\t\u0005>|GNU5oO\u0006!!i\\8m!\tY\u0002bE\u0003\t;\u0002$w\r\u0005\u0002\u0016=&\u0011qL\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007m\t7-\u0003\u0002c\u001b\t\u0001\u0002*Z=uS:<g)\u001e8di&|gn\u001d\t\u00037\u0001\u00012aG3d\u0013\t1WB\u0001\tHK:\u0014un\u001c7Gk:\u001cG/[8ogB\u0011\u0001.\\\u0007\u0002S*\u0011!n[\u0001\u0003S>T\u0011\u0001\\\u0001\u0005U\u00064\u0018-\u0003\u0002oS\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012aW\u0001\u0006CB\u0004H._\u000b\u0003gZ$\"\u0001\u001e?\u0011\u0007m\u0001Q\u000f\u0005\u0002 m\u0012I\u0011E\u0003Q\u0001\u0002\u0003\u0015\rA\t\u0015\u0005m\u001eB(0M\u0003$W1JX&\r\u0003%_M:\u0012'B\u00126mm<\u0014\u0007\u0002\u00130g]AQ! \u0006A\u0004Q\f!!\u001a<)\u0005)y\bcA\u000b\u0002\u0002%\u0019\u00111\u0001\f\u0003\r%tG.\u001b8f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0001\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\tya[\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0014\u00055!AB(cU\u0016\u001cG\u000f"
)
public interface Bool extends Heyting, GenBool {
   static Bool apply(final Bool ev) {
      return Bool$.MODULE$.apply(ev);
   }

   // $FF: synthetic method
   static Object imp$(final Bool $this, final Object a, final Object b) {
      return $this.imp(a, b);
   }

   default Object imp(final Object a, final Object b) {
      return this.or(this.complement(a), b);
   }

   // $FF: synthetic method
   static Object without$(final Bool $this, final Object a, final Object b) {
      return $this.without(a, b);
   }

   default Object without(final Object a, final Object b) {
      return this.and(a, this.complement(b));
   }

   // $FF: synthetic method
   static Object xor$(final Bool $this, final Object a, final Object b) {
      return $this.xor(a, b);
   }

   default Object xor(final Object a, final Object b) {
      return this.or(this.without(a, b), this.without(b, a));
   }

   // $FF: synthetic method
   static Bool dual$(final Bool $this) {
      return $this.dual();
   }

   default Bool dual() {
      return new DualBool(this);
   }

   // $FF: synthetic method
   static BoolRing asBoolRing$(final Bool $this) {
      return $this.asBoolRing();
   }

   default BoolRing asBoolRing() {
      return new BoolRingFromBool(this);
   }

   // $FF: synthetic method
   static int imp$mcI$sp$(final Bool $this, final int a, final int b) {
      return $this.imp$mcI$sp(a, b);
   }

   default int imp$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.imp(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long imp$mcJ$sp$(final Bool $this, final long a, final long b) {
      return $this.imp$mcJ$sp(a, b);
   }

   default long imp$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.imp(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static int without$mcI$sp$(final Bool $this, final int a, final int b) {
      return $this.without$mcI$sp(a, b);
   }

   default int without$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.without(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long without$mcJ$sp$(final Bool $this, final long a, final long b) {
      return $this.without$mcJ$sp(a, b);
   }

   default long without$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.without(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static int xor$mcI$sp$(final Bool $this, final int a, final int b) {
      return $this.xor$mcI$sp(a, b);
   }

   default int xor$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.xor(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long xor$mcJ$sp$(final Bool $this, final long a, final long b) {
      return $this.xor$mcJ$sp(a, b);
   }

   default long xor$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.xor(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static Bool dual$mcI$sp$(final Bool $this) {
      return $this.dual$mcI$sp();
   }

   default Bool dual$mcI$sp() {
      return this.dual();
   }

   // $FF: synthetic method
   static Bool dual$mcJ$sp$(final Bool $this) {
      return $this.dual$mcJ$sp();
   }

   default Bool dual$mcJ$sp() {
      return this.dual();
   }

   static void $init$(final Bool $this) {
   }
}
