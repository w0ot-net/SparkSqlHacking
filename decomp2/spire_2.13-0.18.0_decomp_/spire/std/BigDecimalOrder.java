package spire.std;

import cats.kernel.Order;
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003?\u0001\u0011\u0005s\bC\u0003C\u0001\u0011\u00053\tC\u0003G\u0001\u0011\u0005s\tC\u0003K\u0001\u0011\u00053\nC\u0003O\u0001\u0011\u0005s\nC\u0003S\u0001\u0011\u00053\u000bC\u0003W\u0001\u0011\u0005s\u000bC\u0003[\u0001\u0011\u00051LA\bCS\u001e$UmY5nC2|%\u000fZ3s\u0015\tia\"A\u0002ti\u0012T\u0011aD\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0003\u0007\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007e)\u0003F\u0004\u0002\u001bE9\u00111\u0004\t\b\u00039}i\u0011!\b\u0006\u0003=A\ta\u0001\u0010:p_Rt\u0014\"A\b\n\u0005\u0005r\u0011aB1mO\u0016\u0014'/Y\u0005\u0003G\u0011\nq\u0001]1dW\u0006<WM\u0003\u0002\"\u001d%\u0011ae\n\u0002\u0006\u001fJ$WM\u001d\u0006\u0003G\u0011\u0002\"!K\u0017\u000f\u0005)bcB\u0001\u000f,\u0013\u0005)\u0012BA\u0012\u0015\u0013\tqsF\u0001\u0006CS\u001e$UmY5nC2T!a\t\u000b\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0004CA\n4\u0013\t!DC\u0001\u0003V]&$\u0018aA3rmR\u0019qG\u000f\u001f\u0011\u0005MA\u0014BA\u001d\u0015\u0005\u001d\u0011un\u001c7fC:DQa\u000f\u0002A\u0002!\n\u0011\u0001\u001f\u0005\u0006{\t\u0001\r\u0001K\u0001\u0002s\u0006!a.Z9w)\r9\u0004)\u0011\u0005\u0006w\r\u0001\r\u0001\u000b\u0005\u0006{\r\u0001\r\u0001K\u0001\u0003OR$2a\u000e#F\u0011\u0015YD\u00011\u0001)\u0011\u0015iD\u00011\u0001)\u0003\u00159G/Z9w)\r9\u0004*\u0013\u0005\u0006w\u0015\u0001\r\u0001\u000b\u0005\u0006{\u0015\u0001\r\u0001K\u0001\u0003YR$2a\u000e'N\u0011\u0015Yd\u00011\u0001)\u0011\u0015id\u00011\u0001)\u0003\u0015aG/Z9w)\r9\u0004+\u0015\u0005\u0006w\u001d\u0001\r\u0001\u000b\u0005\u0006{\u001d\u0001\r\u0001K\u0001\u0004[&tGc\u0001\u0015U+\")1\b\u0003a\u0001Q!)Q\b\u0003a\u0001Q\u0005\u0019Q.\u0019=\u0015\u0007!B\u0016\fC\u0003<\u0013\u0001\u0007\u0001\u0006C\u0003>\u0013\u0001\u0007\u0001&A\u0004d_6\u0004\u0018M]3\u0015\u0007q{\u0006\r\u0005\u0002\u0014;&\u0011a\f\u0006\u0002\u0004\u0013:$\b\"B\u001e\u000b\u0001\u0004A\u0003\"B\u001f\u000b\u0001\u0004A\u0003"
)
public interface BigDecimalOrder extends Order {
   // $FF: synthetic method
   static boolean eqv$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final BigDecimal x, final BigDecimal y) {
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
   static boolean neqv$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final BigDecimal x, final BigDecimal y) {
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
   static boolean gt$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.gt(x, y);
   }

   default boolean gt(final BigDecimal x, final BigDecimal y) {
      return x.$greater(y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final BigDecimal x, final BigDecimal y) {
      return x.$greater$eq(y);
   }

   // $FF: synthetic method
   static boolean lt$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.lt(x, y);
   }

   default boolean lt(final BigDecimal x, final BigDecimal y) {
      return x.$less(y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final BigDecimal x, final BigDecimal y) {
      return x.$less$eq(y);
   }

   // $FF: synthetic method
   static BigDecimal min$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.min(x, y);
   }

   default BigDecimal min(final BigDecimal x, final BigDecimal y) {
      return x.min(y);
   }

   // $FF: synthetic method
   static BigDecimal max$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.max(x, y);
   }

   default BigDecimal max(final BigDecimal x, final BigDecimal y) {
      return x.max(y);
   }

   // $FF: synthetic method
   static int compare$(final BigDecimalOrder $this, final BigDecimal x, final BigDecimal y) {
      return $this.compare(x, y);
   }

   default int compare(final BigDecimal x, final BigDecimal y) {
      return x.bigDecimal().compareTo(y.bigDecimal());
   }

   static void $init$(final BigDecimalOrder $this) {
   }
}
