package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot$mcI$sp;

@ScalaSignature(
   bytes = "\u0006\u0005=2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003'\u0001\u0011\u0005q\u0005C\u0003+\u0001\u0011\u00051F\u0001\u0006J]RL5O\u0014*p_RT!a\u0002\u0005\u0002\u0007M$HMC\u0001\n\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\u0007\u0013!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191C\u0006\r\u000e\u0003QQ!!\u0006\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0003\u0006\u0002\u0006\u001dJ{w\u000e\u001e\t\u0003\u001beI!A\u0007\b\u0003\u0007%sG/\u0001\u0004%S:LG\u000f\n\u000b\u0002;A\u0011QBH\u0005\u0003?9\u0011A!\u00168ji\u0006)aN]8piR\u0019\u0001D\t\u0013\t\u000b\r\u0012\u0001\u0019\u0001\r\u0002\u0003aDQ!\n\u0002A\u0002a\t\u0011A\\\u0001\u0004Y><GC\u0001\r)\u0011\u0015I3\u00011\u0001\u0019\u0003\u0005\t\u0017\u0001\u00024q_^$2\u0001\u0007\u0017.\u0011\u0015IC\u00011\u0001\u0019\u0011\u0015qC\u00011\u0001\u0019\u0003\u0005\u0011\u0007"
)
public interface IntIsNRoot extends NRoot$mcI$sp {
   // $FF: synthetic method
   static int nroot$(final IntIsNRoot $this, final int x, final int n) {
      return $this.nroot(x, n);
   }

   default int nroot(final int x, final int n) {
      return this.nroot$mcI$sp(x, n);
   }

   // $FF: synthetic method
   static int log$(final IntIsNRoot $this, final int a) {
      return $this.log(a);
   }

   default int log(final int a) {
      return (int)Math.log((double)a);
   }

   // $FF: synthetic method
   static int fpow$(final IntIsNRoot $this, final int a, final int b) {
      return $this.fpow(a, b);
   }

   default int fpow(final int a, final int b) {
      return this.fpow$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int nroot$mcI$sp$(final IntIsNRoot $this, final int x, final int n) {
      return $this.nroot$mcI$sp(x, n);
   }

   default int nroot$mcI$sp(final int x, final int n) {
      return this.findnroot$1(0, 1 << (33 - n) / n, n, x);
   }

   // $FF: synthetic method
   static int fpow$mcI$sp$(final IntIsNRoot $this, final int a, final int b) {
      return $this.fpow$mcI$sp(a, b);
   }

   default int fpow$mcI$sp(final int a, final int b) {
      return (int)Math.pow((double)a, (double)b);
   }

   private int findnroot$1(final int prev, final int add, final int n$1, final int x$1) {
      while(true) {
         int next = prev | add;
         double e = Math.pow((double)next, (double)n$1);
         if (e == (double)x$1 || add == 0) {
            return next;
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

   static void $init$(final IntIsNRoot $this) {
   }
}
