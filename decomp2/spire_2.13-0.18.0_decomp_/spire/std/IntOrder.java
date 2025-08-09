package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005s\u0006C\u00038\u0001\u0011\u0005\u0003\bC\u0003<\u0001\u0011\u0005C\bC\u0003@\u0001\u0011\u0005\u0003\tC\u0003D\u0001\u0011\u0005C\tC\u0003H\u0001\u0011\u0005\u0003\nC\u0003L\u0001\u0011\u0005AJ\u0001\u0005J]R|%\u000fZ3s\u0015\tYA\"A\u0002ti\u0012T\u0011!D\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]\u0019cE\u0004\u0002\u0019A9\u0011\u0011D\b\b\u00035ui\u0011a\u0007\u0006\u000399\ta\u0001\u0010:p_Rt\u0014\"A\u0007\n\u0005}a\u0011aB1mO\u0016\u0014'/Y\u0005\u0003C\t\nq\u0001]1dW\u0006<WM\u0003\u0002 \u0019%\u0011A%\n\u0002\u0006\u001fJ$WM\u001d\u0006\u0003C\t\u0002\"!E\u0014\n\u0005!\u0012\"aA%oi\u00061A%\u001b8ji\u0012\"\u0012a\u000b\t\u0003#1J!!\f\n\u0003\tUs\u0017\u000e^\u0001\u0004KF4Hc\u0001\u00194kA\u0011\u0011#M\u0005\u0003eI\u0011qAQ8pY\u0016\fg\u000eC\u00035\u0005\u0001\u0007a%A\u0001y\u0011\u00151$\u00011\u0001'\u0003\u0005I\u0018\u0001\u00028fcZ$2\u0001M\u001d;\u0011\u0015!4\u00011\u0001'\u0011\u001514\u00011\u0001'\u0003\t9G\u000fF\u00021{yBQ\u0001\u000e\u0003A\u0002\u0019BQA\u000e\u0003A\u0002\u0019\nQa\u001a;fcZ$2\u0001M!C\u0011\u0015!T\u00011\u0001'\u0011\u00151T\u00011\u0001'\u0003\taG\u000fF\u00021\u000b\u001aCQ\u0001\u000e\u0004A\u0002\u0019BQA\u000e\u0004A\u0002\u0019\nQ\u0001\u001c;fcZ$2\u0001M%K\u0011\u0015!t\u00011\u0001'\u0011\u00151t\u00011\u0001'\u0003\u001d\u0019w.\u001c9be\u0016$2AJ'O\u0011\u0015!\u0004\u00021\u0001'\u0011\u00151\u0004\u00021\u0001'\u0001"
)
public interface IntOrder extends Order.mcI.sp {
   // $FF: synthetic method
   static boolean eqv$(final IntOrder $this, final int x, final int y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final int x, final int y) {
      return this.eqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final IntOrder $this, final int x, final int y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final int x, final int y) {
      return this.neqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final IntOrder $this, final int x, final int y) {
      return $this.gt(x, y);
   }

   default boolean gt(final int x, final int y) {
      return this.gt$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final IntOrder $this, final int x, final int y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final int x, final int y) {
      return this.gteqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final IntOrder $this, final int x, final int y) {
      return $this.lt(x, y);
   }

   default boolean lt(final int x, final int y) {
      return this.lt$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final IntOrder $this, final int x, final int y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final int x, final int y) {
      return this.lteqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final IntOrder $this, final int x, final int y) {
      return $this.compare(x, y);
   }

   default int compare(final int x, final int y) {
      return this.compare$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.eqv$mcI$sp(x, y);
   }

   default boolean eqv$mcI$sp(final int x, final int y) {
      return x == y;
   }

   // $FF: synthetic method
   static boolean neqv$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.neqv$mcI$sp(x, y);
   }

   default boolean neqv$mcI$sp(final int x, final int y) {
      return x != y;
   }

   // $FF: synthetic method
   static boolean gt$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.gt$mcI$sp(x, y);
   }

   default boolean gt$mcI$sp(final int x, final int y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean gteqv$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.gteqv$mcI$sp(x, y);
   }

   default boolean gteqv$mcI$sp(final int x, final int y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.lt$mcI$sp(x, y);
   }

   default boolean lt$mcI$sp(final int x, final int y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean lteqv$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.lteqv$mcI$sp(x, y);
   }

   default boolean lteqv$mcI$sp(final int x, final int y) {
      return x <= y;
   }

   // $FF: synthetic method
   static int compare$mcI$sp$(final IntOrder $this, final int x, final int y) {
      return $this.compare$mcI$sp(x, y);
   }

   default int compare$mcI$sp(final int x, final int y) {
      return x < y ? -1 : (x == y ? 0 : 1);
   }

   static void $init$(final IntOrder $this) {
   }
}
