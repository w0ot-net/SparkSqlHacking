package cats.kernel;

import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003'\u0001\u0011\u0005q\u0005C\u0003,\u0001\u0019\u0005A\u0006C\u00031\u0001\u0019\u0005\u0011\u0007C\u00035\u0001\u0011\u0005SG\u0001\u0005Qe\u00164\u0018n\\;t\u0015\t9\u0001\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0013\u0005!1-\u0019;t\u0007\u0001)\"\u0001D\r\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0004)U9R\"\u0001\u0004\n\u0005Y1!a\u0004)beRL\u0017\r\u001c)sKZLw.^:\u0011\u0005aIB\u0002\u0001\u0003\n5\u0001\u0001\u000b\u0011!AC\u0002m\u0011\u0011!Q\t\u00039}\u0001\"AD\u000f\n\u0005yy!a\u0002(pi\"Lgn\u001a\t\u0003\u001d\u0001J!!I\b\u0003\u0007\u0005s\u0017\u0010\u000b\u0002\u001aGA\u0011a\u0002J\u0005\u0003K=\u00111b\u001d9fG&\fG.\u001b>fI\u00061A%\u001b8ji\u0012\"\u0012\u0001\u000b\t\u0003\u001d%J!AK\b\u0003\tUs\u0017\u000e^\u0001\ra\u0006\u0014H/[1m\u001fJ$WM]\u000b\u0002[A\u0019ACL\f\n\u0005=2!\u0001\u0004)beRL\u0017\r\\(sI\u0016\u0014\u0018\u0001\u00039sKZLw.^:\u0015\u0005]\u0011\u0004\"B\u001a\u0004\u0001\u00049\u0012!A1\u0002\u001fA\f'\u000f^5bYB\u0013XM^5pkN$\"AN\u001d\u0011\u000799t#\u0003\u00029\u001f\t1q\n\u001d;j_:DQa\r\u0003A\u0002]\u0001"
)
public interface Previous extends PartialPrevious {
   PartialOrder partialOrder();

   Object previous(final Object a);

   // $FF: synthetic method
   static Option partialPrevious$(final Previous $this, final Object a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final Object a) {
      return new Some(this.previous(a));
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final Previous $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final Previous $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final Previous $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final Previous $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final Previous $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final Previous $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final Previous $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final Previous $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final Previous $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static boolean previous$mcZ$sp$(final Previous $this, final boolean a) {
      return $this.previous$mcZ$sp(a);
   }

   default boolean previous$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.previous(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte previous$mcB$sp$(final Previous $this, final byte a) {
      return $this.previous$mcB$sp(a);
   }

   default byte previous$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.previous(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char previous$mcC$sp$(final Previous $this, final char a) {
      return $this.previous$mcC$sp(a);
   }

   default char previous$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.previous(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double previous$mcD$sp$(final Previous $this, final double a) {
      return $this.previous$mcD$sp(a);
   }

   default double previous$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.previous(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float previous$mcF$sp$(final Previous $this, final float a) {
      return $this.previous$mcF$sp(a);
   }

   default float previous$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.previous(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int previous$mcI$sp$(final Previous $this, final int a) {
      return $this.previous$mcI$sp(a);
   }

   default int previous$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.previous(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long previous$mcJ$sp$(final Previous $this, final long a) {
      return $this.previous$mcJ$sp(a);
   }

   default long previous$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.previous(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short previous$mcS$sp$(final Previous $this, final short a) {
      return $this.previous$mcS$sp(a);
   }

   default short previous$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.previous(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void previous$mcV$sp$(final Previous $this, final BoxedUnit a) {
      $this.previous$mcV$sp(a);
   }

   default void previous$mcV$sp(final BoxedUnit a) {
      this.previous(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcZ$sp$(final Previous $this, final boolean a) {
      return $this.partialPrevious$mcZ$sp(a);
   }

   default Option partialPrevious$mcZ$sp(final boolean a) {
      return this.partialPrevious(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcB$sp$(final Previous $this, final byte a) {
      return $this.partialPrevious$mcB$sp(a);
   }

   default Option partialPrevious$mcB$sp(final byte a) {
      return this.partialPrevious(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcC$sp$(final Previous $this, final char a) {
      return $this.partialPrevious$mcC$sp(a);
   }

   default Option partialPrevious$mcC$sp(final char a) {
      return this.partialPrevious(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcD$sp$(final Previous $this, final double a) {
      return $this.partialPrevious$mcD$sp(a);
   }

   default Option partialPrevious$mcD$sp(final double a) {
      return this.partialPrevious(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcF$sp$(final Previous $this, final float a) {
      return $this.partialPrevious$mcF$sp(a);
   }

   default Option partialPrevious$mcF$sp(final float a) {
      return this.partialPrevious(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcI$sp$(final Previous $this, final int a) {
      return $this.partialPrevious$mcI$sp(a);
   }

   default Option partialPrevious$mcI$sp(final int a) {
      return this.partialPrevious(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcJ$sp$(final Previous $this, final long a) {
      return $this.partialPrevious$mcJ$sp(a);
   }

   default Option partialPrevious$mcJ$sp(final long a) {
      return this.partialPrevious(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcS$sp$(final Previous $this, final short a) {
      return $this.partialPrevious$mcS$sp(a);
   }

   default Option partialPrevious$mcS$sp(final short a) {
      return this.partialPrevious(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcV$sp$(final Previous $this, final BoxedUnit a) {
      return $this.partialPrevious$mcV$sp(a);
   }

   default Option partialPrevious$mcV$sp(final BoxedUnit a) {
      return this.partialPrevious(a);
   }

   static void $init$(final Previous $this) {
   }
}
