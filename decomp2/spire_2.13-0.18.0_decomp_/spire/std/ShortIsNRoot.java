package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005I2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u0003.\u0001\u0011\u0005aF\u0001\u0007TQ>\u0014H/S:O%>|GO\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u0019I\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n\u001715\tAC\u0003\u0002\u0016\u0011\u00059\u0011\r\\4fEJ\f\u0017BA\f\u0015\u0005\u0015q%k\\8u!\ti\u0011$\u0003\u0002\u001b\u001d\t)1\u000b[8si\u00061A%\u001b8ji\u0012\"\u0012!\b\t\u0003\u001byI!a\b\b\u0003\tUs\u0017\u000e^\u0001\u0006]J|w\u000e\u001e\u000b\u00041\t\"\u0003\"B\u0012\u0003\u0001\u0004A\u0012!\u0001=\t\u000b\u0015\u0012\u0001\u0019\u0001\u0014\u0002\u00039\u0004\"!D\u0014\n\u0005!r!aA%oi\u0006\u0019An\\4\u0015\u0005aY\u0003\"\u0002\u0017\u0004\u0001\u0004A\u0012!A1\u0002\t\u0019\u0004xn\u001e\u000b\u00041=\u0002\u0004\"\u0002\u0017\u0005\u0001\u0004A\u0002\"B\u0019\u0005\u0001\u0004A\u0012!\u00012"
)
public interface ShortIsNRoot extends NRoot {
   // $FF: synthetic method
   static short nroot$(final ShortIsNRoot $this, final short x, final int n) {
      return $this.nroot(x, n);
   }

   default short nroot(final short x, final int n) {
      return this.findnroot$1(0, 1 << (33 - n) / n, n, x);
   }

   // $FF: synthetic method
   static short log$(final ShortIsNRoot $this, final short a) {
      return $this.log(a);
   }

   default short log(final short a) {
      return (short)((int)Math.log((double)a));
   }

   // $FF: synthetic method
   static short fpow$(final ShortIsNRoot $this, final short a, final short b) {
      return $this.fpow(a, b);
   }

   default short fpow(final short a, final short b) {
      return (short)((int)Math.pow((double)a, (double)b));
   }

   private short findnroot$1(final int prev, final int add, final int n$1, final short x$1) {
      while(true) {
         int next = prev | add;
         double e = Math.pow((double)next, (double)n$1);
         if (e == (double)x$1 || add == 0) {
            return (short)next;
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

   static void $init$(final ShortIsNRoot $this) {
   }
}
