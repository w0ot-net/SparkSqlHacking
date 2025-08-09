package spire.math;

import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0011\u0005S\u0006C\u00038\u0001\u0011\u0005\u0003H\u0001\u000eTG\u0006d\u0017m\u0014:eKJLgnZ,sCB\u0004XM]\"p[B\fGO\u0003\u0002\u0007\u000f\u0005!Q.\u0019;i\u0015\u0005A\u0011!B:qSJ,7\u0001A\u000b\u0003\u0017u\u00192\u0001\u0001\u0007\u0015!\ti!#D\u0001\u000f\u0015\ty\u0001#\u0001\u0003mC:<'\"A\t\u0002\t)\fg/Y\u0005\u0003'9\u0011aa\u00142kK\u000e$\bcA\u000b\u001a75\taC\u0003\u0002\u0007/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b-\tAqJ\u001d3fe&tw\r\u0005\u0002\u001d;1\u0001A!\u0002\u0010\u0001\u0005\u0004y\"!A!\u0012\u0005\u0001\"\u0003CA\u0011#\u001b\u00059\u0012BA\u0012\u0018\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!I\u0013\n\u0005\u0019:\"aA!os\u00061A%\u001b8ji\u0012\"\u0012!\u000b\t\u0003C)J!aK\f\u0003\tUs\u0017\u000e^\u0001\u0004[&tWC\u0001\u00181)\ry3'\u000e\t\u00039A\"Q!\r\u0002C\u0002I\u0012\u0011!V\t\u0003AmAQ\u0001\u000e\u0002A\u0002=\n\u0011\u0001\u001f\u0005\u0006m\t\u0001\raL\u0001\u0002s\u0006\u0019Q.\u0019=\u0016\u0005eZDc\u0001\u001e={A\u0011Ad\u000f\u0003\u0006c\r\u0011\rA\r\u0005\u0006i\r\u0001\rA\u000f\u0005\u0006m\r\u0001\rA\u000f"
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
