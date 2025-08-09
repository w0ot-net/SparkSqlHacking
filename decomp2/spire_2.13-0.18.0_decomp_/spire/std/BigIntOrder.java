package spire.std;

import cats.kernel.Order;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003?\u0001\u0011\u0005s\bC\u0003C\u0001\u0011\u00053\tC\u0003G\u0001\u0011\u0005s\tC\u0003K\u0001\u0011\u00053\nC\u0003O\u0001\u0011\u0005s\nC\u0003S\u0001\u0011\u00053\u000bC\u0003W\u0001\u0011\u0005s\u000bC\u0003[\u0001\u0011\u00051LA\u0006CS\u001eLe\u000e^(sI\u0016\u0014(BA\u0007\u000f\u0003\r\u0019H\u000f\u001a\u0006\u0002\u001f\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u00042!G\u0013)\u001d\tQ\"E\u0004\u0002\u001cA9\u0011AdH\u0007\u0002;)\u0011a\u0004E\u0001\u0007yI|w\u000e\u001e \n\u0003=I!!\t\b\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0005J\u0001\ba\u0006\u001c7.Y4f\u0015\t\tc\"\u0003\u0002'O\t)qJ\u001d3fe*\u00111\u0005\n\t\u0003S5r!A\u000b\u0017\u000f\u0005qY\u0013\"A\u000b\n\u0005\r\"\u0012B\u0001\u00180\u0005\u0019\u0011\u0015nZ%oi*\u00111\u0005F\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0002\"aE\u001a\n\u0005Q\"\"\u0001B+oSR\f1!Z9w)\r9$\b\u0010\t\u0003'aJ!!\u000f\u000b\u0003\u000f\t{w\u000e\\3b]\")1H\u0001a\u0001Q\u0005\t\u0001\u0010C\u0003>\u0005\u0001\u0007\u0001&A\u0001z\u0003\u0011qW-\u001d<\u0015\u0007]\u0002\u0015\tC\u0003<\u0007\u0001\u0007\u0001\u0006C\u0003>\u0007\u0001\u0007\u0001&\u0001\u0002hiR\u0019q\u0007R#\t\u000bm\"\u0001\u0019\u0001\u0015\t\u000bu\"\u0001\u0019\u0001\u0015\u0002\u000b\u001d$X-\u001d<\u0015\u0007]B\u0015\nC\u0003<\u000b\u0001\u0007\u0001\u0006C\u0003>\u000b\u0001\u0007\u0001&\u0001\u0002miR\u0019q\u0007T'\t\u000bm2\u0001\u0019\u0001\u0015\t\u000bu2\u0001\u0019\u0001\u0015\u0002\u000b1$X-\u001d<\u0015\u0007]\u0002\u0016\u000bC\u0003<\u000f\u0001\u0007\u0001\u0006C\u0003>\u000f\u0001\u0007\u0001&A\u0002nS:$2\u0001\u000b+V\u0011\u0015Y\u0004\u00021\u0001)\u0011\u0015i\u0004\u00021\u0001)\u0003\ri\u0017\r\u001f\u000b\u0004QaK\u0006\"B\u001e\n\u0001\u0004A\u0003\"B\u001f\n\u0001\u0004A\u0013aB2p[B\f'/\u001a\u000b\u00049~\u0003\u0007CA\n^\u0013\tqFCA\u0002J]RDQa\u000f\u0006A\u0002!BQ!\u0010\u0006A\u0002!\u0002"
)
public interface BigIntOrder extends Order {
   // $FF: synthetic method
   static boolean eqv$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final BigInt x, final BigInt y) {
      boolean var10000;
      label23: {
         if (x == null) {
            if (y == null) {
               break label23;
            }
         } else if (x.equals(y)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   static boolean neqv$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final BigInt x, final BigInt y) {
      boolean var10000;
      label23: {
         if (x == null) {
            if (y != null) {
               break label23;
            }
         } else if (!x.equals(y)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   static boolean gt$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.gt(x, y);
   }

   default boolean gt(final BigInt x, final BigInt y) {
      return x.$greater(y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final BigInt x, final BigInt y) {
      return x.$greater$eq(y);
   }

   // $FF: synthetic method
   static boolean lt$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.lt(x, y);
   }

   default boolean lt(final BigInt x, final BigInt y) {
      return x.$less(y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final BigInt x, final BigInt y) {
      return x.$less$eq(y);
   }

   // $FF: synthetic method
   static BigInt min$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.min(x, y);
   }

   default BigInt min(final BigInt x, final BigInt y) {
      return x.min(y);
   }

   // $FF: synthetic method
   static BigInt max$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.max(x, y);
   }

   default BigInt max(final BigInt x, final BigInt y) {
      return x.max(y);
   }

   // $FF: synthetic method
   static int compare$(final BigIntOrder $this, final BigInt x, final BigInt y) {
      return $this.compare(x, y);
   }

   default int compare(final BigInt x, final BigInt y) {
      return x.bigInteger().compareTo(y.bigInteger());
   }

   static void $init$(final BigIntOrder $this) {
   }
}
