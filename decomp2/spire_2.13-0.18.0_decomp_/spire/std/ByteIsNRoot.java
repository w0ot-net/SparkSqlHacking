package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005I2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u0003.\u0001\u0011\u0005aFA\u0006CsR,\u0017j\u001d(S_>$(BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042a\u0005\f\u0019\u001b\u0005!\"BA\u000b\t\u0003\u001d\tGnZ3ce\u0006L!a\u0006\u000b\u0003\u000b9\u0013vn\u001c;\u0011\u00055I\u0012B\u0001\u000e\u000f\u0005\u0011\u0011\u0015\u0010^3\u0002\r\u0011Jg.\u001b;%)\u0005i\u0002CA\u0007\u001f\u0013\tybB\u0001\u0003V]&$\u0018!\u00028s_>$Hc\u0001\r#I!)1E\u0001a\u00011\u0005\t\u0001\u0010C\u0003&\u0005\u0001\u0007a%A\u0001o!\tiq%\u0003\u0002)\u001d\t\u0019\u0011J\u001c;\u0002\u00071|w\r\u0006\u0002\u0019W!)Af\u0001a\u00011\u0005\t\u0011-\u0001\u0003ga><Hc\u0001\r0a!)A\u0006\u0002a\u00011!)\u0011\u0007\u0002a\u00011\u0005\t!\r"
)
public interface ByteIsNRoot extends NRoot {
   // $FF: synthetic method
   static byte nroot$(final ByteIsNRoot $this, final byte x, final int n) {
      return $this.nroot(x, n);
   }

   default byte nroot(final byte x, final int n) {
      return this.findnroot$1(0, 1 << (33 - n) / n, n, x);
   }

   // $FF: synthetic method
   static byte log$(final ByteIsNRoot $this, final byte a) {
      return $this.log(a);
   }

   default byte log(final byte a) {
      return (byte)((int)Math.log((double)a));
   }

   // $FF: synthetic method
   static byte fpow$(final ByteIsNRoot $this, final byte a, final byte b) {
      return $this.fpow(a, b);
   }

   default byte fpow(final byte a, final byte b) {
      return (byte)((int)Math.pow((double)a, (double)b));
   }

   private byte findnroot$1(final int prev, final int add, final int n$1, final byte x$1) {
      while(true) {
         int next = prev | add;
         double e = Math.pow((double)next, (double)n$1);
         if (e == (double)x$1 || add == 0) {
            return (byte)next;
         }

         if (!(e <= (double)0) && !(e > (double)x$1)) {
            add >>= 1;
            prev = next;
         } else {
            add >>= 1;
            prev = prev;
         }
      }
   }

   static void $init$(final ByteIsNRoot $this) {
   }
}
