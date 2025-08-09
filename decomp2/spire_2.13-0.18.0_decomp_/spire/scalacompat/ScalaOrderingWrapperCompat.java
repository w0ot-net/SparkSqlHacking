package spire.scalacompat;

import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005c\u0006C\u00039\u0001\u0011\u0005\u0013H\u0001\u000eTG\u0006d\u0017m\u0014:eKJLgnZ,sCB\u0004XM]\"p[B\fGO\u0003\u0002\u0007\u000f\u0005Y1oY1mC\u000e|W\u000e]1u\u0015\u0005A\u0011!B:qSJ,7\u0001A\u000b\u0003\u0017y\u00192\u0001\u0001\u0007\u0015!\ti!#D\u0001\u000f\u0015\ty\u0001#\u0001\u0003mC:<'\"A\t\u0002\t)\fg/Y\u0005\u0003'9\u0011aa\u00142kK\u000e$\bcA\u000b\u001b95\taC\u0003\u0002\u00181\u0005!Q.\u0019;i\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0017\u0005!y%\u000fZ3sS:<\u0007CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0015\u0002\"AI\u0012\u000e\u0003aI!\u0001\n\r\u0003\u000f9{G\u000f[5oOB\u0011!EJ\u0005\u0003Oa\u00111!\u00118z\u0003\u0019!\u0013N\\5uIQ\t!\u0006\u0005\u0002#W%\u0011A\u0006\u0007\u0002\u0005+:LG/A\u0002nS:,\"aL\u0019\u0015\u0007A\"d\u0007\u0005\u0002\u001ec\u0011)!G\u0001b\u0001g\t\tQ+\u0005\u0002\"9!)QG\u0001a\u0001a\u0005\t\u0001\u0010C\u00038\u0005\u0001\u0007\u0001'A\u0001z\u0003\ri\u0017\r_\u000b\u0003uq\"2aO\u001f?!\tiB\bB\u00033\u0007\t\u00071\u0007C\u00036\u0007\u0001\u00071\bC\u00038\u0007\u0001\u00071\b"
)
public interface ScalaOrderingWrapperCompat extends Ordering {
   // $FF: synthetic method
   static Object min$(final ScalaOrderingWrapperCompat $this, final Object x, final Object y) {
      return $this.min(x, y);
   }

   default Object min(final Object x, final Object y) {
      return this.lt(x, y) ? x : y;
   }

   // $FF: synthetic method
   static Object max$(final ScalaOrderingWrapperCompat $this, final Object x, final Object y) {
      return $this.max(x, y);
   }

   default Object max(final Object x, final Object y) {
      return this.gt(x, y) ? x : y;
   }

   static void $init$(final ScalaOrderingWrapperCompat $this) {
   }
}
