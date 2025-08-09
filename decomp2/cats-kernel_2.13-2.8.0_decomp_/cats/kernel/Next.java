package cats.kernel;

import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003&\u0001\u0011\u0005a\u0005C\u0003+\u0001\u0019\u00051\u0006C\u0003/\u0001\u0011\u0005sF\u0001\u0003OKb$(B\u0001\u0004\b\u0003\u0019YWM\u001d8fY*\t\u0001\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u0017a\u00192\u0001\u0001\u0007\u0013!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191\u0003\u0006\f\u000e\u0003\u0015I!!F\u0003\u0003\u0017A\u000b'\u000f^5bY:+\u0007\u0010\u001e\t\u0003/aa\u0001\u0001B\u0005\u001a\u0001\u0001\u0006\t\u0011!b\u00015\t\t\u0011)\u0005\u0002\u001c=A\u0011Q\u0002H\u0005\u0003;9\u0011qAT8uQ&tw\r\u0005\u0002\u000e?%\u0011\u0001E\u0004\u0002\u0004\u0003:L\bF\u0001\r#!\ti1%\u0003\u0002%\u001d\tY1\u000f]3dS\u0006d\u0017N_3e\u0003\u0019!\u0013N\\5uIQ\tq\u0005\u0005\u0002\u000eQ%\u0011\u0011F\u0004\u0002\u0005+:LG/\u0001\u0003oKb$HC\u0001\f-\u0011\u0015i#\u00011\u0001\u0017\u0003\u0005\t\u0017a\u00039beRL\u0017\r\u001c(fqR$\"\u0001M\u001a\u0011\u00075\td#\u0003\u00023\u001d\t1q\n\u001d;j_:DQ!L\u0002A\u0002Y\u0001"
)
public interface Next extends PartialNext {
   Object next(final Object a);

   // $FF: synthetic method
   static Option partialNext$(final Next $this, final Object a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final Object a) {
      return new Some(this.next(a));
   }

   // $FF: synthetic method
   static boolean next$mcZ$sp$(final Next $this, final boolean a) {
      return $this.next$mcZ$sp(a);
   }

   default boolean next$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.next(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte next$mcB$sp$(final Next $this, final byte a) {
      return $this.next$mcB$sp(a);
   }

   default byte next$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.next(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char next$mcC$sp$(final Next $this, final char a) {
      return $this.next$mcC$sp(a);
   }

   default char next$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.next(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double next$mcD$sp$(final Next $this, final double a) {
      return $this.next$mcD$sp(a);
   }

   default double next$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.next(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float next$mcF$sp$(final Next $this, final float a) {
      return $this.next$mcF$sp(a);
   }

   default float next$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.next(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int next$mcI$sp$(final Next $this, final int a) {
      return $this.next$mcI$sp(a);
   }

   default int next$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.next(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long next$mcJ$sp$(final Next $this, final long a) {
      return $this.next$mcJ$sp(a);
   }

   default long next$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.next(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short next$mcS$sp$(final Next $this, final short a) {
      return $this.next$mcS$sp(a);
   }

   default short next$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.next(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void next$mcV$sp$(final Next $this, final BoxedUnit a) {
      $this.next$mcV$sp(a);
   }

   default void next$mcV$sp(final BoxedUnit a) {
      this.next(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcZ$sp$(final Next $this, final boolean a) {
      return $this.partialNext$mcZ$sp(a);
   }

   default Option partialNext$mcZ$sp(final boolean a) {
      return this.partialNext(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcB$sp$(final Next $this, final byte a) {
      return $this.partialNext$mcB$sp(a);
   }

   default Option partialNext$mcB$sp(final byte a) {
      return this.partialNext(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcC$sp$(final Next $this, final char a) {
      return $this.partialNext$mcC$sp(a);
   }

   default Option partialNext$mcC$sp(final char a) {
      return this.partialNext(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcD$sp$(final Next $this, final double a) {
      return $this.partialNext$mcD$sp(a);
   }

   default Option partialNext$mcD$sp(final double a) {
      return this.partialNext(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcF$sp$(final Next $this, final float a) {
      return $this.partialNext$mcF$sp(a);
   }

   default Option partialNext$mcF$sp(final float a) {
      return this.partialNext(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcI$sp$(final Next $this, final int a) {
      return $this.partialNext$mcI$sp(a);
   }

   default Option partialNext$mcI$sp(final int a) {
      return this.partialNext(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcJ$sp$(final Next $this, final long a) {
      return $this.partialNext$mcJ$sp(a);
   }

   default Option partialNext$mcJ$sp(final long a) {
      return this.partialNext(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcS$sp$(final Next $this, final short a) {
      return $this.partialNext$mcS$sp(a);
   }

   default Option partialNext$mcS$sp(final short a) {
      return this.partialNext(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Option partialNext$mcV$sp$(final Next $this, final BoxedUnit a) {
      return $this.partialNext$mcV$sp(a);
   }

   default Option partialNext$mcV$sp(final BoxedUnit a) {
      return this.partialNext(a);
   }

   static void $init$(final Next $this) {
   }
}
