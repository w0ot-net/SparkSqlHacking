package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot$mcJ$sp;

@ScalaSignature(
   bytes = "\u0006\u0005I2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u0003.\u0001\u0011\u0005aFA\u0006M_:<\u0017j\u001d(S_>$(BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042a\u0005\f\u0019\u001b\u0005!\"BA\u000b\t\u0003\u001d\tGnZ3ce\u0006L!a\u0006\u000b\u0003\u000b9\u0013vn\u001c;\u0011\u00055I\u0012B\u0001\u000e\u000f\u0005\u0011auN\\4\u0002\r\u0011Jg.\u001b;%)\u0005i\u0002CA\u0007\u001f\u0013\tybB\u0001\u0003V]&$\u0018!\u00028s_>$Hc\u0001\r#I!)1E\u0001a\u00011\u0005\t\u0001\u0010C\u0003&\u0005\u0001\u0007a%A\u0001o!\tiq%\u0003\u0002)\u001d\t\u0019\u0011J\u001c;\u0002\u00071|w\r\u0006\u0002\u0019W!)Af\u0001a\u00011\u0005\t\u0011-\u0001\u0003ga><Hc\u0001\r0a!)A\u0006\u0002a\u00011!)\u0011\u0007\u0002a\u00011\u0005\t!\r"
)
public interface LongIsNRoot extends NRoot$mcJ$sp {
   // $FF: synthetic method
   static long nroot$(final LongIsNRoot $this, final long x, final int n) {
      return $this.nroot(x, n);
   }

   default long nroot(final long x, final int n) {
      return this.nroot$mcJ$sp(x, n);
   }

   // $FF: synthetic method
   static long log$(final LongIsNRoot $this, final long a) {
      return $this.log(a);
   }

   default long log(final long a) {
      return (long)Math.log((double)a);
   }

   // $FF: synthetic method
   static long fpow$(final LongIsNRoot $this, final long a, final long b) {
      return $this.fpow(a, b);
   }

   default long fpow(final long a, final long b) {
      return this.fpow$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long nroot$mcJ$sp$(final LongIsNRoot $this, final long x, final int n) {
      return $this.nroot$mcJ$sp(x, n);
   }

   default long nroot$mcJ$sp(final long x, final int n) {
      if (n < 1) {
         throw new IllegalArgumentException((new StringBuilder(7)).append("nroot(").append(n).append(")").toString());
      } else {
         return n == 1 ? x : this.findnroot$1(0L, 1L << (65 - n) / n, n, x);
      }
   }

   // $FF: synthetic method
   static long fpow$mcJ$sp$(final LongIsNRoot $this, final long a, final long b) {
      return $this.fpow$mcJ$sp(a, b);
   }

   default long fpow$mcJ$sp(final long a, final long b) {
      return spire.math.package$.MODULE$.pow(a, b);
   }

   private long findnroot$1(final long prev, final long add, final int n$1, final long x$1) {
      while(true) {
         long next = prev | add;
         long e = spire.math.package$.MODULE$.pow(next, (long)n$1);
         if (e == x$1 || add == 0L) {
            return next;
         }

         if (e > 0L && e <= x$1) {
            add >>= 1;
            prev = next;
         } else {
            add >>= 1;
            prev = prev;
         }
      }
   }

   static void $init$(final LongIsNRoot $this) {
   }
}
