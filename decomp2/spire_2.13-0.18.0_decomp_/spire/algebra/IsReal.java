package spire.algebra;

import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.math.Real;

@ScalaSignature(
   bytes = "\u0006\u0005E4q\u0001D\u0007\u0011\u0002G\u0005!\u0003C\u00032\u0001\u0019\u0005!\u0007C\u00036\u0001\u0019\u0005a\u0007C\u00039\u0001\u0019\u0005\u0011\bC\u0003<\u0001\u0019\u0005A\bC\u0003B\u0001\u0019\u0005!\tC\u0003H\u0001\u0019\u0005\u0001jB\u0003Q\u001b!\u0005\u0011KB\u0003\r\u001b!\u0005!\u000bC\u0003_\u0011\u0011\u0005q\fC\u0003a\u0011\u0011\u0005\u0011\rC\u0004j\u0011\u0005\u0005I\u0011\u00026\u0003\r%\u001b(+Z1m\u0015\tqq\"A\u0004bY\u001e,'M]1\u000b\u0003A\tQa\u001d9je\u0016\u001c\u0001!\u0006\u0002\u0014IM!\u0001\u0001\u0006\u000e/!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\r\te.\u001f\t\u00047}\u0011cB\u0001\u000f\u001e\u001b\u0005i\u0011B\u0001\u0010\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001I\u0011\u0003\u000b=\u0013H-\u001a:\u000b\u0005yi\u0001CA\u0012%\u0019\u0001!\u0011\"\n\u0001!\u0002\u0003\u0005)\u0019\u0001\u0014\u0003\u0003\u0005\u000b\"a\n\u000b\u0011\u0005UA\u0013BA\u0015\u0017\u0005\u001dqu\u000e\u001e5j]\u001eD#\u0001J\u0016\u0011\u0005Ua\u0013BA\u0017\u0017\u0005-\u0019\b/Z2jC2L'0\u001a3\u0011\u0007my#%\u0003\u00021C\t11+[4oK\u0012\fAaY3jYR\u0011!e\r\u0005\u0006i\u0005\u0001\rAI\u0001\u0002C\u0006)a\r\\8peR\u0011!e\u000e\u0005\u0006i\t\u0001\rAI\u0001\u0006e>,h\u000e\u001a\u000b\u0003EiBQ\u0001N\u0002A\u0002\t\nq![:XQ>dW\r\u0006\u0002>\u0001B\u0011QCP\u0005\u0003\u007fY\u0011qAQ8pY\u0016\fg\u000eC\u00035\t\u0001\u0007!%\u0001\u0005u_\u0012{WO\u00197f)\t\u0019e\t\u0005\u0002\u0016\t&\u0011QI\u0006\u0002\u0007\t>,(\r\\3\t\u000bQ*\u0001\u0019\u0001\u0012\u0002\rQ|'+Z1m)\tIu\n\u0005\u0002K\u001b6\t1J\u0003\u0002M\u001f\u0005!Q.\u0019;i\u0013\tq5J\u0001\u0003SK\u0006d\u0007\"\u0002\u001b\u0007\u0001\u0004\u0011\u0013AB%t%\u0016\fG\u000e\u0005\u0002\u001d\u0011M\u0019\u0001b\u0015,\u0011\u0005U!\u0016BA+\u0017\u0005\u0019\te.\u001f*fMB\u0011q\u000bX\u0007\u00021*\u0011\u0011LW\u0001\u0003S>T\u0011aW\u0001\u0005U\u00064\u0018-\u0003\u0002^1\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!U\u0001\u0006CB\u0004H._\u000b\u0003E\u0016$\"aY4\u0011\u0007q\u0001A\r\u0005\u0002$K\u0012IQE\u0003Q\u0001\u0002\u0003\u0015\rA\n\u0015\u0003K.BQ\u0001\u001b\u0006A\u0004\r\f\u0011!Q\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002WB\u0011An\\\u0007\u0002[*\u0011aNW\u0001\u0005Y\u0006tw-\u0003\u0002q[\n1qJ\u00196fGR\u0004"
)
public interface IsReal extends Order, Signed {
   static IsReal apply(final IsReal A) {
      return IsReal$.MODULE$.apply(A);
   }

   Object ceil(final Object a);

   Object floor(final Object a);

   Object round(final Object a);

   boolean isWhole(final Object a);

   double toDouble(final Object a);

   Real toReal(final Object a);

   // $FF: synthetic method
   static boolean ceil$mcZ$sp$(final IsReal $this, final boolean a) {
      return $this.ceil$mcZ$sp(a);
   }

   default boolean ceil$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.ceil(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte ceil$mcB$sp$(final IsReal $this, final byte a) {
      return $this.ceil$mcB$sp(a);
   }

   default byte ceil$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.ceil(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char ceil$mcC$sp$(final IsReal $this, final char a) {
      return $this.ceil$mcC$sp(a);
   }

   default char ceil$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.ceil(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double ceil$mcD$sp$(final IsReal $this, final double a) {
      return $this.ceil$mcD$sp(a);
   }

   default double ceil$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.ceil(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float ceil$mcF$sp$(final IsReal $this, final float a) {
      return $this.ceil$mcF$sp(a);
   }

   default float ceil$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.ceil(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int ceil$mcI$sp$(final IsReal $this, final int a) {
      return $this.ceil$mcI$sp(a);
   }

   default int ceil$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.ceil(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long ceil$mcJ$sp$(final IsReal $this, final long a) {
      return $this.ceil$mcJ$sp(a);
   }

   default long ceil$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.ceil(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short ceil$mcS$sp$(final IsReal $this, final short a) {
      return $this.ceil$mcS$sp(a);
   }

   default short ceil$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.ceil(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void ceil$mcV$sp$(final IsReal $this, final BoxedUnit a) {
      $this.ceil$mcV$sp(a);
   }

   default void ceil$mcV$sp(final BoxedUnit a) {
      this.ceil(a);
   }

   // $FF: synthetic method
   static boolean floor$mcZ$sp$(final IsReal $this, final boolean a) {
      return $this.floor$mcZ$sp(a);
   }

   default boolean floor$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.floor(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte floor$mcB$sp$(final IsReal $this, final byte a) {
      return $this.floor$mcB$sp(a);
   }

   default byte floor$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.floor(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char floor$mcC$sp$(final IsReal $this, final char a) {
      return $this.floor$mcC$sp(a);
   }

   default char floor$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.floor(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double floor$mcD$sp$(final IsReal $this, final double a) {
      return $this.floor$mcD$sp(a);
   }

   default double floor$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.floor(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float floor$mcF$sp$(final IsReal $this, final float a) {
      return $this.floor$mcF$sp(a);
   }

   default float floor$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.floor(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int floor$mcI$sp$(final IsReal $this, final int a) {
      return $this.floor$mcI$sp(a);
   }

   default int floor$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.floor(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long floor$mcJ$sp$(final IsReal $this, final long a) {
      return $this.floor$mcJ$sp(a);
   }

   default long floor$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.floor(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short floor$mcS$sp$(final IsReal $this, final short a) {
      return $this.floor$mcS$sp(a);
   }

   default short floor$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.floor(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void floor$mcV$sp$(final IsReal $this, final BoxedUnit a) {
      $this.floor$mcV$sp(a);
   }

   default void floor$mcV$sp(final BoxedUnit a) {
      this.floor(a);
   }

   // $FF: synthetic method
   static boolean round$mcZ$sp$(final IsReal $this, final boolean a) {
      return $this.round$mcZ$sp(a);
   }

   default boolean round$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.round(BoxesRunTime.boxToBoolean(a)));
   }

   // $FF: synthetic method
   static byte round$mcB$sp$(final IsReal $this, final byte a) {
      return $this.round$mcB$sp(a);
   }

   default byte round$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.round(BoxesRunTime.boxToByte(a)));
   }

   // $FF: synthetic method
   static char round$mcC$sp$(final IsReal $this, final char a) {
      return $this.round$mcC$sp(a);
   }

   default char round$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.round(BoxesRunTime.boxToCharacter(a)));
   }

   // $FF: synthetic method
   static double round$mcD$sp$(final IsReal $this, final double a) {
      return $this.round$mcD$sp(a);
   }

   default double round$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.round(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float round$mcF$sp$(final IsReal $this, final float a) {
      return $this.round$mcF$sp(a);
   }

   default float round$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.round(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int round$mcI$sp$(final IsReal $this, final int a) {
      return $this.round$mcI$sp(a);
   }

   default int round$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.round(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long round$mcJ$sp$(final IsReal $this, final long a) {
      return $this.round$mcJ$sp(a);
   }

   default long round$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.round(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static short round$mcS$sp$(final IsReal $this, final short a) {
      return $this.round$mcS$sp(a);
   }

   default short round$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.round(BoxesRunTime.boxToShort(a)));
   }

   // $FF: synthetic method
   static void round$mcV$sp$(final IsReal $this, final BoxedUnit a) {
      $this.round$mcV$sp(a);
   }

   default void round$mcV$sp(final BoxedUnit a) {
      this.round(a);
   }

   // $FF: synthetic method
   static boolean isWhole$mcZ$sp$(final IsReal $this, final boolean a) {
      return $this.isWhole$mcZ$sp(a);
   }

   default boolean isWhole$mcZ$sp(final boolean a) {
      return this.isWhole(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcB$sp$(final IsReal $this, final byte a) {
      return $this.isWhole$mcB$sp(a);
   }

   default boolean isWhole$mcB$sp(final byte a) {
      return this.isWhole(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcC$sp$(final IsReal $this, final char a) {
      return $this.isWhole$mcC$sp(a);
   }

   default boolean isWhole$mcC$sp(final char a) {
      return this.isWhole(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcD$sp$(final IsReal $this, final double a) {
      return $this.isWhole$mcD$sp(a);
   }

   default boolean isWhole$mcD$sp(final double a) {
      return this.isWhole(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcF$sp$(final IsReal $this, final float a) {
      return $this.isWhole$mcF$sp(a);
   }

   default boolean isWhole$mcF$sp(final float a) {
      return this.isWhole(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcI$sp$(final IsReal $this, final int a) {
      return $this.isWhole$mcI$sp(a);
   }

   default boolean isWhole$mcI$sp(final int a) {
      return this.isWhole(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcJ$sp$(final IsReal $this, final long a) {
      return $this.isWhole$mcJ$sp(a);
   }

   default boolean isWhole$mcJ$sp(final long a) {
      return this.isWhole(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcS$sp$(final IsReal $this, final short a) {
      return $this.isWhole$mcS$sp(a);
   }

   default boolean isWhole$mcS$sp(final short a) {
      return this.isWhole(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean isWhole$mcV$sp$(final IsReal $this, final BoxedUnit a) {
      return $this.isWhole$mcV$sp(a);
   }

   default boolean isWhole$mcV$sp(final BoxedUnit a) {
      return this.isWhole(a);
   }

   // $FF: synthetic method
   static double toDouble$mcZ$sp$(final IsReal $this, final boolean a) {
      return $this.toDouble$mcZ$sp(a);
   }

   default double toDouble$mcZ$sp(final boolean a) {
      return this.toDouble(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static double toDouble$mcB$sp$(final IsReal $this, final byte a) {
      return $this.toDouble$mcB$sp(a);
   }

   default double toDouble$mcB$sp(final byte a) {
      return this.toDouble(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static double toDouble$mcC$sp$(final IsReal $this, final char a) {
      return $this.toDouble$mcC$sp(a);
   }

   default double toDouble$mcC$sp(final char a) {
      return this.toDouble(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static double toDouble$mcD$sp$(final IsReal $this, final double a) {
      return $this.toDouble$mcD$sp(a);
   }

   default double toDouble$mcD$sp(final double a) {
      return this.toDouble(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static double toDouble$mcF$sp$(final IsReal $this, final float a) {
      return $this.toDouble$mcF$sp(a);
   }

   default double toDouble$mcF$sp(final float a) {
      return this.toDouble(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static double toDouble$mcI$sp$(final IsReal $this, final int a) {
      return $this.toDouble$mcI$sp(a);
   }

   default double toDouble$mcI$sp(final int a) {
      return this.toDouble(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static double toDouble$mcJ$sp$(final IsReal $this, final long a) {
      return $this.toDouble$mcJ$sp(a);
   }

   default double toDouble$mcJ$sp(final long a) {
      return this.toDouble(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static double toDouble$mcS$sp$(final IsReal $this, final short a) {
      return $this.toDouble$mcS$sp(a);
   }

   default double toDouble$mcS$sp(final short a) {
      return this.toDouble(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static double toDouble$mcV$sp$(final IsReal $this, final BoxedUnit a) {
      return $this.toDouble$mcV$sp(a);
   }

   default double toDouble$mcV$sp(final BoxedUnit a) {
      return this.toDouble(a);
   }

   // $FF: synthetic method
   static Real toReal$mcZ$sp$(final IsReal $this, final boolean a) {
      return $this.toReal$mcZ$sp(a);
   }

   default Real toReal$mcZ$sp(final boolean a) {
      return this.toReal(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Real toReal$mcB$sp$(final IsReal $this, final byte a) {
      return $this.toReal$mcB$sp(a);
   }

   default Real toReal$mcB$sp(final byte a) {
      return this.toReal(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Real toReal$mcC$sp$(final IsReal $this, final char a) {
      return $this.toReal$mcC$sp(a);
   }

   default Real toReal$mcC$sp(final char a) {
      return this.toReal(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Real toReal$mcD$sp$(final IsReal $this, final double a) {
      return $this.toReal$mcD$sp(a);
   }

   default Real toReal$mcD$sp(final double a) {
      return this.toReal(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Real toReal$mcF$sp$(final IsReal $this, final float a) {
      return $this.toReal$mcF$sp(a);
   }

   default Real toReal$mcF$sp(final float a) {
      return this.toReal(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Real toReal$mcI$sp$(final IsReal $this, final int a) {
      return $this.toReal$mcI$sp(a);
   }

   default Real toReal$mcI$sp(final int a) {
      return this.toReal(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Real toReal$mcJ$sp$(final IsReal $this, final long a) {
      return $this.toReal$mcJ$sp(a);
   }

   default Real toReal$mcJ$sp(final long a) {
      return this.toReal(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Real toReal$mcS$sp$(final IsReal $this, final short a) {
      return $this.toReal$mcS$sp(a);
   }

   default Real toReal$mcS$sp(final short a) {
      return this.toReal(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Real toReal$mcV$sp$(final IsReal $this, final BoxedUnit a) {
      return $this.toReal$mcV$sp(a);
   }

   default Real toReal$mcV$sp(final BoxedUnit a) {
      return this.toReal(a);
   }
}
