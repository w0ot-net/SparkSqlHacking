package spire.math;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005!B\u0004\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0005\r\u0005\u0006q\u0001!\t%\u000f\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0011\u0002!\t%\u0013\u0005\u0006\u0019\u0002!\t!\u0014\u0002\f\u001dVl'-\u001a:Pe\u0012,'O\u0003\u0002\f\u0019\u0005!Q.\u0019;i\u0015\u0005i\u0011!B:qSJ,7c\u0001\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u00042AF\u0012'\u001d\t9\u0002E\u0004\u0002\u0019=9\u0011\u0011$H\u0007\u00025)\u00111\u0004H\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ\"\u0003\u0002 \u0019\u00059\u0011\r\\4fEJ\f\u0017BA\u0011#\u0003\u001d\u0001\u0018mY6bO\u0016T!a\b\u0007\n\u0005\u0011*#!B(sI\u0016\u0014(BA\u0011#!\t9\u0003&D\u0001\u000b\u0013\tI#B\u0001\u0004Ok6\u0014WM]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"\u0001E\u0017\n\u00059\n\"\u0001B+oSR\f1!Z9w)\r\tDG\u000e\t\u0003!IJ!aM\t\u0003\u000f\t{w\u000e\\3b]\")QG\u0001a\u0001M\u0005\t\u0001\u0010C\u00038\u0005\u0001\u0007a%A\u0001z\u0003\u0011qW-\u001d<\u0015\u0007ER4\bC\u00036\u0007\u0001\u0007a\u0005C\u00038\u0007\u0001\u0007a%\u0001\u0002hiR\u0019\u0011GP \t\u000bU\"\u0001\u0019\u0001\u0014\t\u000b]\"\u0001\u0019\u0001\u0014\u0002\u000b\u001d$X-\u001d<\u0015\u0007E\u00125\tC\u00036\u000b\u0001\u0007a\u0005C\u00038\u000b\u0001\u0007a%\u0001\u0002miR\u0019\u0011GR$\t\u000bU2\u0001\u0019\u0001\u0014\t\u000b]2\u0001\u0019\u0001\u0014\u0002\u000b1$X-\u001d<\u0015\u0007ER5\nC\u00036\u000f\u0001\u0007a\u0005C\u00038\u000f\u0001\u0007a%A\u0004d_6\u0004\u0018M]3\u0015\u00079\u000b&\u000b\u0005\u0002\u0011\u001f&\u0011\u0001+\u0005\u0002\u0004\u0013:$\b\"B\u001b\t\u0001\u00041\u0003\"B\u001c\t\u0001\u00041\u0003"
)
public interface NumberOrder extends Order {
   // $FF: synthetic method
   static boolean eqv$(final NumberOrder $this, final Number x, final Number y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Number x, final Number y) {
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
   static boolean neqv$(final NumberOrder $this, final Number x, final Number y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Number x, final Number y) {
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
   static boolean gt$(final NumberOrder $this, final Number x, final Number y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Number x, final Number y) {
      return x.$greater(y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final NumberOrder $this, final Number x, final Number y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final Number x, final Number y) {
      return x.$greater$eq(y);
   }

   // $FF: synthetic method
   static boolean lt$(final NumberOrder $this, final Number x, final Number y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Number x, final Number y) {
      return x.$less(y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final NumberOrder $this, final Number x, final Number y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final Number x, final Number y) {
      return x.$less$eq(y);
   }

   // $FF: synthetic method
   static int compare$(final NumberOrder $this, final Number x, final Number y) {
      return $this.compare(x, y);
   }

   default int compare(final Number x, final Number y) {
      return x.compare(y);
   }

   static void $init$(final NumberOrder $this) {
   }
}
