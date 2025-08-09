package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u00034\u0001\u0011\u0005A\u0007C\u00039\u0001\u0011\u0005\u0013\bC\u0003?\u0001\u0011\u0005s\bC\u0003C\u0001\u0011\u00053\tC\u0003G\u0001\u0011\u0005s\tC\u0003K\u0001\u0011\u00053\nC\u0003O\u0001\u0011\u0005s\nC\u0003S\u0001\u0011\u00053\u000bC\u0003W\u0001\u0011\u0005s\u000bC\u0003[\u0001\u0011\u00051L\u0001\u0007C_>dW-\u00198Pe\u0012,'O\u0003\u0002\u000e\u001d\u0005\u00191\u000f\u001e3\u000b\u0003=\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0003\u0001%aY\u0003CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\rE\u0002\u001aK!r!A\u0007\u0012\u000f\u0005m\u0001cB\u0001\u000f \u001b\u0005i\"B\u0001\u0010\u0011\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002\"\u001d\u00059\u0011\r\\4fEJ\f\u0017BA\u0012%\u0003\u001d\u0001\u0018mY6bO\u0016T!!\t\b\n\u0005\u0019:#!B(sI\u0016\u0014(BA\u0012%!\t\u0019\u0012&\u0003\u0002+)\t9!i\\8mK\u0006t\u0007C\u0001\u00171\u001d\tisF\u0004\u0002\u001d]%\tQ#\u0003\u0002$)%\u0011\u0011G\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003GQ\ta\u0001J5oSR$C#A\u001b\u0011\u0005M1\u0014BA\u001c\u0015\u0005\u0011)f.\u001b;\u0002\u0007\u0015\fh\u000fF\u0002)uqBQa\u000f\u0002A\u0002!\n\u0011\u0001\u001f\u0005\u0006{\t\u0001\r\u0001K\u0001\u0002s\u0006!a.Z9w)\rA\u0003)\u0011\u0005\u0006w\r\u0001\r\u0001\u000b\u0005\u0006{\r\u0001\r\u0001K\u0001\u0003OR$2\u0001\u000b#F\u0011\u0015YD\u00011\u0001)\u0011\u0015iD\u00011\u0001)\u0003\taG\u000fF\u0002)\u0011&CQaO\u0003A\u0002!BQ!P\u0003A\u0002!\nQa\u001a;fcZ$2\u0001\u000b'N\u0011\u0015Yd\u00011\u0001)\u0011\u0015id\u00011\u0001)\u0003\u0015aG/Z9w)\rA\u0003+\u0015\u0005\u0006w\u001d\u0001\r\u0001\u000b\u0005\u0006{\u001d\u0001\r\u0001K\u0001\u0004[&tGc\u0001\u0015U+\")1\b\u0003a\u0001Q!)Q\b\u0003a\u0001Q\u0005\u0019Q.\u0019=\u0015\u0007!B\u0016\fC\u0003<\u0013\u0001\u0007\u0001\u0006C\u0003>\u0013\u0001\u0007\u0001&A\u0004d_6\u0004\u0018M]3\u0015\u0007q{\u0006\r\u0005\u0002\u0014;&\u0011a\f\u0006\u0002\u0004\u0013:$\b\"B\u001e\u000b\u0001\u0004A\u0003\"B\u001f\u000b\u0001\u0004A\u0003"
)
public interface BooleanOrder extends Order.mcZ.sp {
   // $FF: synthetic method
   static boolean eqv$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final boolean x, final boolean y) {
      return this.eqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final boolean x, final boolean y) {
      return this.neqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.gt(x, y);
   }

   default boolean gt(final boolean x, final boolean y) {
      return this.gt$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.lt(x, y);
   }

   default boolean lt(final boolean x, final boolean y) {
      return this.lt$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final boolean x, final boolean y) {
      return this.gteqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final boolean x, final boolean y) {
      return this.lteqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean min$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.min(x, y);
   }

   default boolean min(final boolean x, final boolean y) {
      return this.min$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean max$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.max(x, y);
   }

   default boolean max(final boolean x, final boolean y) {
      return this.max$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.compare(x, y);
   }

   default int compare(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.eqv$mcZ$sp(x, y);
   }

   default boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y;
   }

   // $FF: synthetic method
   static boolean neqv$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.neqv$mcZ$sp(x, y);
   }

   default boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return x != y;
   }

   // $FF: synthetic method
   static boolean gt$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.gt$mcZ$sp(x, y);
   }

   default boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return x && !y;
   }

   // $FF: synthetic method
   static boolean lt$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.lt$mcZ$sp(x, y);
   }

   default boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return !x && y;
   }

   // $FF: synthetic method
   static boolean gteqv$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.gteqv$mcZ$sp(x, y);
   }

   default boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y || x;
   }

   // $FF: synthetic method
   static boolean lteqv$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.lteqv$mcZ$sp(x, y);
   }

   default boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y || y;
   }

   // $FF: synthetic method
   static boolean min$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.min$mcZ$sp(x, y);
   }

   default boolean min$mcZ$sp(final boolean x, final boolean y) {
      return x && y;
   }

   // $FF: synthetic method
   static boolean max$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.max$mcZ$sp(x, y);
   }

   default boolean max$mcZ$sp(final boolean x, final boolean y) {
      return x || y;
   }

   // $FF: synthetic method
   static int compare$mcZ$sp$(final BooleanOrder $this, final boolean x, final boolean y) {
      return $this.compare$mcZ$sp(x, y);
   }

   default int compare$mcZ$sp(final boolean x, final boolean y) {
      return x ? (y ? 0 : 1) : (y ? -1 : 0);
   }

   static void $init$(final BooleanOrder $this) {
   }
}
