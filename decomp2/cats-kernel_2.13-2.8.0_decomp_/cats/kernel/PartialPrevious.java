package cats.kernel;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000552qa\u0001\u0003\u0011\u0002G\u0005\u0011\u0002C\u0003\u0012\u0001\u0019\u0005!\u0003C\u0003'\u0001\u0019\u0005qEA\bQCJ$\u0018.\u00197Qe\u00164\u0018n\\;t\u0015\t)a!\u0001\u0004lKJtW\r\u001c\u0006\u0002\u000f\u0005!1-\u0019;t\u0007\u0001)\"AC\r\u0014\u0005\u0001Y\u0001C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g-\u0001\u0007qCJ$\u0018.\u00197Pe\u0012,'/F\u0001\u0014!\r!RcF\u0007\u0002\t%\u0011a\u0003\u0002\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d\t\u00031ea\u0001\u0001B\u0005\u001b\u0001\u0001\u0006\t\u0011!b\u00017\t\t\u0011)\u0005\u0002\u001d?A\u0011A\"H\u0005\u0003=5\u0011qAT8uQ&tw\r\u0005\u0002\rA%\u0011\u0011%\u0004\u0002\u0004\u0003:L\bFA\r$!\taA%\u0003\u0002&\u001b\tY1\u000f]3dS\u0006d\u0017N_3e\u0003=\u0001\u0018M\u001d;jC2\u0004&/\u001a<j_V\u001cHC\u0001\u0015,!\ra\u0011fF\u0005\u0003U5\u0011aa\u00149uS>t\u0007\"\u0002\u0017\u0003\u0001\u00049\u0012!A1"
)
public interface PartialPrevious {
   PartialOrder partialOrder();

   Option partialPrevious(final Object a);

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final PartialPrevious $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static Option partialPrevious$mcZ$sp$(final PartialPrevious $this, final boolean a) {
      return $this.partialPrevious$mcZ$sp(a);
   }

   default Option partialPrevious$mcZ$sp(final boolean a) {
      return this.partialPrevious(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcB$sp$(final PartialPrevious $this, final byte a) {
      return $this.partialPrevious$mcB$sp(a);
   }

   default Option partialPrevious$mcB$sp(final byte a) {
      return this.partialPrevious(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcC$sp$(final PartialPrevious $this, final char a) {
      return $this.partialPrevious$mcC$sp(a);
   }

   default Option partialPrevious$mcC$sp(final char a) {
      return this.partialPrevious(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcD$sp$(final PartialPrevious $this, final double a) {
      return $this.partialPrevious$mcD$sp(a);
   }

   default Option partialPrevious$mcD$sp(final double a) {
      return this.partialPrevious(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcF$sp$(final PartialPrevious $this, final float a) {
      return $this.partialPrevious$mcF$sp(a);
   }

   default Option partialPrevious$mcF$sp(final float a) {
      return this.partialPrevious(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcI$sp$(final PartialPrevious $this, final int a) {
      return $this.partialPrevious$mcI$sp(a);
   }

   default Option partialPrevious$mcI$sp(final int a) {
      return this.partialPrevious(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcJ$sp$(final PartialPrevious $this, final long a) {
      return $this.partialPrevious$mcJ$sp(a);
   }

   default Option partialPrevious$mcJ$sp(final long a) {
      return this.partialPrevious(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcS$sp$(final PartialPrevious $this, final short a) {
      return $this.partialPrevious$mcS$sp(a);
   }

   default Option partialPrevious$mcS$sp(final short a) {
      return this.partialPrevious(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcV$sp$(final PartialPrevious $this, final BoxedUnit a) {
      return $this.partialPrevious$mcV$sp(a);
   }

   default Option partialPrevious$mcV$sp(final BoxedUnit a) {
      return this.partialPrevious(a);
   }
}
