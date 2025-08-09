package cats.kernel;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000552qa\u0001\u0003\u0011\u0002G\u0005\u0011\u0002C\u0003\u0012\u0001\u0019\u0005!\u0003C\u0003'\u0001\u0019\u0005qEA\u0006QCJ$\u0018.\u00197OKb$(BA\u0003\u0007\u0003\u0019YWM\u001d8fY*\tq!\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u0015e\u0019\"\u0001A\u0006\u0011\u00051yQ\"A\u0007\u000b\u00039\tQa]2bY\u0006L!\u0001E\u0007\u0003\r\u0005s\u0017PU3g\u00031\u0001\u0018M\u001d;jC2|%\u000fZ3s+\u0005\u0019\u0002c\u0001\u000b\u0016/5\tA!\u0003\u0002\u0017\t\ta\u0001+\u0019:uS\u0006dwJ\u001d3feB\u0011\u0001$\u0007\u0007\u0001\t%Q\u0002\u0001)A\u0001\u0002\u000b\u00071DA\u0001B#\tar\u0004\u0005\u0002\r;%\u0011a$\u0004\u0002\b\u001d>$\b.\u001b8h!\ta\u0001%\u0003\u0002\"\u001b\t\u0019\u0011I\\=)\u0005e\u0019\u0003C\u0001\u0007%\u0013\t)SBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017a\u00039beRL\u0017\r\u001c(fqR$\"\u0001K\u0016\u0011\u00071Is#\u0003\u0002+\u001b\t1q\n\u001d;j_:DQ\u0001\f\u0002A\u0002]\t\u0011!\u0019"
)
public interface PartialNext {
   PartialOrder partialOrder();

   Option partialNext(final Object a);

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final PartialNext $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final PartialNext $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final PartialNext $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final PartialNext $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final PartialNext $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final PartialNext $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final PartialNext $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final PartialNext $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final PartialNext $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static Option partialNext$mcZ$sp$(final PartialNext $this, final boolean a) {
      return $this.partialNext$mcZ$sp(a);
   }

   default Option partialNext$mcZ$sp(final boolean a) {
      return this.partialNext(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcB$sp$(final PartialNext $this, final byte a) {
      return $this.partialNext$mcB$sp(a);
   }

   default Option partialNext$mcB$sp(final byte a) {
      return this.partialNext(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcC$sp$(final PartialNext $this, final char a) {
      return $this.partialNext$mcC$sp(a);
   }

   default Option partialNext$mcC$sp(final char a) {
      return this.partialNext(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcD$sp$(final PartialNext $this, final double a) {
      return $this.partialNext$mcD$sp(a);
   }

   default Option partialNext$mcD$sp(final double a) {
      return this.partialNext(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcF$sp$(final PartialNext $this, final float a) {
      return $this.partialNext$mcF$sp(a);
   }

   default Option partialNext$mcF$sp(final float a) {
      return this.partialNext(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcI$sp$(final PartialNext $this, final int a) {
      return $this.partialNext$mcI$sp(a);
   }

   default Option partialNext$mcI$sp(final int a) {
      return this.partialNext(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcJ$sp$(final PartialNext $this, final long a) {
      return $this.partialNext$mcJ$sp(a);
   }

   default Option partialNext$mcJ$sp(final long a) {
      return this.partialNext(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcS$sp$(final PartialNext $this, final short a) {
      return $this.partialNext$mcS$sp(a);
   }

   default Option partialNext$mcS$sp(final short a) {
      return this.partialNext(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcV$sp$(final PartialNext $this, final BoxedUnit a) {
      return $this.partialNext$mcV$sp(a);
   }

   default Option partialNext$mcV$sp(final BoxedUnit a) {
      return this.partialNext(a);
   }
}
