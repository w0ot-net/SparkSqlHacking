package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u00034\u0001\u0011\u0005A\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003?\u0001\u0011\u0005s\bC\u0003C\u0001\u0011\u00053\tC\u0003G\u0001\u0011\u0005s\tC\u0003K\u0001\u0011\u00053\nC\u0003O\u0001\u0011\u0005s\nC\u0003S\u0001\u0011\u00053\u000bC\u0003W\u0001\u0011\u0005s\u000bC\u0003[\u0001\u0011\u00051LA\u0005V]&$xJ\u001d3fe*\u0011QBD\u0001\u0004gR$'\"A\b\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001A\u0005\r,!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0019\u0011$\n\u0015\u000f\u0005i\u0011cBA\u000e!\u001d\tar$D\u0001\u001e\u0015\tq\u0002#\u0001\u0004=e>|GOP\u0005\u0002\u001f%\u0011\u0011ED\u0001\bC2<WM\u0019:b\u0013\t\u0019C%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0005r\u0011B\u0001\u0014(\u0005\u0015y%\u000fZ3s\u0015\t\u0019C\u0005\u0005\u0002\u0014S%\u0011!\u0006\u0006\u0002\u0005+:LG\u000f\u0005\u0002-a9\u0011Qf\f\b\u000399J\u0011!F\u0005\u0003GQI!!\r\u001a\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\r\"\u0012A\u0002\u0013j]&$H\u0005F\u0001)\u0003\r)\u0017O\u001e\u000b\u0004oib\u0004CA\n9\u0013\tIDCA\u0004C_>dW-\u00198\t\u000bm\u0012\u0001\u0019\u0001\u0015\u0002\u0003aDQ!\u0010\u0002A\u0002!\n\u0011!_\u0001\u0005]\u0016\fh\u000fF\u00028\u0001\u0006CQaO\u0002A\u0002!BQ!P\u0002A\u0002!\n!a\u001a;\u0015\u0007]\"U\tC\u0003<\t\u0001\u0007\u0001\u0006C\u0003>\t\u0001\u0007\u0001&\u0001\u0002miR\u0019q\u0007S%\t\u000bm*\u0001\u0019\u0001\u0015\t\u000bu*\u0001\u0019\u0001\u0015\u0002\u000b\u001d$X-\u001d<\u0015\u0007]bU\nC\u0003<\r\u0001\u0007\u0001\u0006C\u0003>\r\u0001\u0007\u0001&A\u0003mi\u0016\fh\u000fF\u00028!FCQaO\u0004A\u0002!BQ!P\u0004A\u0002!\n1!\\5o)\rAC+\u0016\u0005\u0006w!\u0001\r\u0001\u000b\u0005\u0006{!\u0001\r\u0001K\u0001\u0004[\u0006DHc\u0001\u0015Y3\")1(\u0003a\u0001Q!)Q(\u0003a\u0001Q\u000591m\\7qCJ,Gc\u0001/`AB\u00111#X\u0005\u0003=R\u00111!\u00138u\u0011\u0015Y$\u00021\u0001)\u0011\u0015i$\u00021\u0001)\u0001"
)
public interface UnitOrder extends Order.mcV.sp {
   // $FF: synthetic method
   static boolean eqv$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final BoxedUnit x, final BoxedUnit y) {
      return this.neqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt(x, y);
   }

   default boolean gt(final BoxedUnit x, final BoxedUnit y) {
      return this.gt$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt(x, y);
   }

   default boolean lt(final BoxedUnit x, final BoxedUnit y) {
      return this.lt$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final BoxedUnit x, final BoxedUnit y) {
      return this.gteqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final BoxedUnit x, final BoxedUnit y) {
      return this.lteqv$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static void min$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      $this.min(x, y);
   }

   default void min(final BoxedUnit x, final BoxedUnit y) {
      this.min$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static void max$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      $this.max(x, y);
   }

   default void max(final BoxedUnit x, final BoxedUnit y) {
      this.max$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.compare(x, y);
   }

   default int compare(final BoxedUnit x, final BoxedUnit y) {
      return this.compare$mcV$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv$mcV$sp(x, y);
   }

   default boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return true;
   }

   // $FF: synthetic method
   static boolean neqv$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv$mcV$sp(x, y);
   }

   default boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return false;
   }

   // $FF: synthetic method
   static boolean gt$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt$mcV$sp(x, y);
   }

   default boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return false;
   }

   // $FF: synthetic method
   static boolean lt$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt$mcV$sp(x, y);
   }

   default boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return false;
   }

   // $FF: synthetic method
   static boolean gteqv$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv$mcV$sp(x, y);
   }

   default boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return true;
   }

   // $FF: synthetic method
   static boolean lteqv$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv$mcV$sp(x, y);
   }

   default boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return true;
   }

   // $FF: synthetic method
   static void min$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      $this.min$mcV$sp(x, y);
   }

   default void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
   }

   // $FF: synthetic method
   static void max$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      $this.max$mcV$sp(x, y);
   }

   default void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
   }

   // $FF: synthetic method
   static int compare$mcV$sp$(final UnitOrder $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.compare$mcV$sp(x, y);
   }

   default int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return 0;
   }

   static void $init$(final UnitOrder $this) {
   }
}
