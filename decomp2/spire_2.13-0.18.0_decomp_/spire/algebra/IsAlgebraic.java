package spire.algebra;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.math.Algebraic;
import spire.math.Real;

@ScalaSignature(
   bytes = "\u0006\u0005u3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0019\u0005Q\u0006C\u00037\u0001\u0011\u0005qgB\u0003=\u0015!\u0005QHB\u0003\n\u0015!\u0005a\bC\u0003K\u000b\u0011\u00051\nC\u0003M\u000b\u0011\u0005Q\nC\u0004V\u000b\u0005\u0005I\u0011\u0002,\u0003\u0017%\u001b\u0018\t\\4fEJ\f\u0017n\u0019\u0006\u0003\u00171\tq!\u00197hK\n\u0014\u0018MC\u0001\u000e\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"\u0001E\u000f\u0014\u0007\u0001\tr\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12CA\u0002B]f\u00042\u0001G\r\u001c\u001b\u0005Q\u0011B\u0001\u000e\u000b\u0005\u0019I5OU3bYB\u0011A$\b\u0007\u0001\t%q\u0002\u0001)A\u0001\u0002\u000b\u0007qDA\u0001B#\t\u0001\u0013\u0003\u0005\u0002\u0013C%\u0011!e\u0005\u0002\b\u001d>$\b.\u001b8hQ\tiB\u0005\u0005\u0002\u0013K%\u0011ae\u0005\u0002\fgB,7-[1mSj,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002SA\u0011!CK\u0005\u0003WM\u0011A!\u00168ji\u0006YAo\\!mO\u0016\u0014'/Y5d)\tqC\u0007\u0005\u00020e5\t\u0001G\u0003\u00022\u0019\u0005!Q.\u0019;i\u0013\t\u0019\u0004GA\u0005BY\u001e,'M]1jG\")QG\u0001a\u00017\u0005\t\u0011-\u0001\u0004u_J+\u0017\r\u001c\u000b\u0003qm\u0002\"aL\u001d\n\u0005i\u0002$\u0001\u0002*fC2DQ!N\u0002A\u0002m\t1\"S:BY\u001e,'M]1jGB\u0011\u0001$B\n\u0004\u000b}\u0012\u0005C\u0001\nA\u0013\t\t5C\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0007\"k\u0011\u0001\u0012\u0006\u0003\u000b\u001a\u000b!![8\u000b\u0003\u001d\u000bAA[1wC&\u0011\u0011\n\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003u\nQ!\u00199qYf,\"AT)\u0015\u0005=\u001b\u0006c\u0001\r\u0001!B\u0011A$\u0015\u0003\n=\u001d\u0001\u000b\u0011!AC\u0002}A#!\u0015\u0013\t\u000bQ;\u00019A(\u0002\u0003\u0005\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u0016\t\u00031nk\u0011!\u0017\u0006\u00035\u001a\u000bA\u0001\\1oO&\u0011A,\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public interface IsAlgebraic extends IsReal {
   static IsAlgebraic apply(final IsAlgebraic A) {
      return IsAlgebraic$.MODULE$.apply(A);
   }

   Algebraic toAlgebraic(final Object a);

   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic $this, final Object a) {
      return $this.toReal(a);
   }

   default Real toReal(final Object a) {
      return this.toAlgebraic(a).toReal();
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcZ$sp$(final IsAlgebraic $this, final boolean a) {
      return $this.toAlgebraic$mcZ$sp(a);
   }

   default Algebraic toAlgebraic$mcZ$sp(final boolean a) {
      return this.toAlgebraic(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcB$sp$(final IsAlgebraic $this, final byte a) {
      return $this.toAlgebraic$mcB$sp(a);
   }

   default Algebraic toAlgebraic$mcB$sp(final byte a) {
      return this.toAlgebraic(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcC$sp$(final IsAlgebraic $this, final char a) {
      return $this.toAlgebraic$mcC$sp(a);
   }

   default Algebraic toAlgebraic$mcC$sp(final char a) {
      return this.toAlgebraic(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcD$sp$(final IsAlgebraic $this, final double a) {
      return $this.toAlgebraic$mcD$sp(a);
   }

   default Algebraic toAlgebraic$mcD$sp(final double a) {
      return this.toAlgebraic(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcF$sp$(final IsAlgebraic $this, final float a) {
      return $this.toAlgebraic$mcF$sp(a);
   }

   default Algebraic toAlgebraic$mcF$sp(final float a) {
      return this.toAlgebraic(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcI$sp$(final IsAlgebraic $this, final int a) {
      return $this.toAlgebraic$mcI$sp(a);
   }

   default Algebraic toAlgebraic$mcI$sp(final int a) {
      return this.toAlgebraic(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcJ$sp$(final IsAlgebraic $this, final long a) {
      return $this.toAlgebraic$mcJ$sp(a);
   }

   default Algebraic toAlgebraic$mcJ$sp(final long a) {
      return this.toAlgebraic(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcS$sp$(final IsAlgebraic $this, final short a) {
      return $this.toAlgebraic$mcS$sp(a);
   }

   default Algebraic toAlgebraic$mcS$sp(final short a) {
      return this.toAlgebraic(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcV$sp$(final IsAlgebraic $this, final BoxedUnit a) {
      return $this.toAlgebraic$mcV$sp(a);
   }

   default Algebraic toAlgebraic$mcV$sp(final BoxedUnit a) {
      return this.toAlgebraic(a);
   }

   // $FF: synthetic method
   static Real toReal$mcZ$sp$(final IsAlgebraic $this, final boolean a) {
      return $this.toReal$mcZ$sp(a);
   }

   default Real toReal$mcZ$sp(final boolean a) {
      return this.toReal(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Real toReal$mcB$sp$(final IsAlgebraic $this, final byte a) {
      return $this.toReal$mcB$sp(a);
   }

   default Real toReal$mcB$sp(final byte a) {
      return this.toReal(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Real toReal$mcC$sp$(final IsAlgebraic $this, final char a) {
      return $this.toReal$mcC$sp(a);
   }

   default Real toReal$mcC$sp(final char a) {
      return this.toReal(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Real toReal$mcD$sp$(final IsAlgebraic $this, final double a) {
      return $this.toReal$mcD$sp(a);
   }

   default Real toReal$mcD$sp(final double a) {
      return this.toReal(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Real toReal$mcF$sp$(final IsAlgebraic $this, final float a) {
      return $this.toReal$mcF$sp(a);
   }

   default Real toReal$mcF$sp(final float a) {
      return this.toReal(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Real toReal$mcI$sp$(final IsAlgebraic $this, final int a) {
      return $this.toReal$mcI$sp(a);
   }

   default Real toReal$mcI$sp(final int a) {
      return this.toReal(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Real toReal$mcJ$sp$(final IsAlgebraic $this, final long a) {
      return $this.toReal$mcJ$sp(a);
   }

   default Real toReal$mcJ$sp(final long a) {
      return this.toReal(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Real toReal$mcS$sp$(final IsAlgebraic $this, final short a) {
      return $this.toReal$mcS$sp(a);
   }

   default Real toReal$mcS$sp(final short a) {
      return this.toReal(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Real toReal$mcV$sp$(final IsAlgebraic $this, final BoxedUnit a) {
      return $this.toReal$mcV$sp(a);
   }

   default Real toReal$mcV$sp(final BoxedUnit a) {
      return this.toReal(a);
   }

   static void $init$(final IsAlgebraic $this) {
   }
}
