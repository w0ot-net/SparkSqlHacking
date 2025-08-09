package spire.std;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005c\u0006C\u00032\u0001\u0011\u0005#GA\u0005J]R\u001c\u0016n\u001a8fI*\u0011aaB\u0001\u0004gR$'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001aC\t%!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0019!CH\u0011\u000f\u0005MYbB\u0001\u000b\u001a\u001d\t)\u0002$D\u0001\u0017\u0015\t9\u0012\"\u0001\u0004=e>|GOP\u0005\u0002\u0011%\u0011!dB\u0001\bC2<WM\u0019:b\u0013\taR$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005i9\u0011BA\u0010!\u0005\u0019\u0019\u0016n\u001a8fI*\u0011A$\b\t\u0003\u0019\tJ!aI\u0007\u0003\u0007%sG\u000f\u0005\u0002&M5\tQ!\u0003\u0002(\u000b\tA\u0011J\u001c;Pe\u0012,'/\u0001\u0004%S:LG\u000f\n\u000b\u0002UA\u0011AbK\u0005\u0003Y5\u0011A!\u00168ji\u000611/[4ok6$\"!I\u0018\t\u000bA\u0012\u0001\u0019A\u0011\u0002\u0003\u0005\f1!\u00192t)\t\t3\u0007C\u00031\u0007\u0001\u0007\u0011\u0005"
)
public interface IntSigned extends Signed.mcI.sp, IntOrder {
   // $FF: synthetic method
   static int signum$(final IntSigned $this, final int a) {
      return $this.signum(a);
   }

   default int signum(final int a) {
      return this.signum$mcI$sp(a);
   }

   // $FF: synthetic method
   static int abs$(final IntSigned $this, final int a) {
      return $this.abs(a);
   }

   default int abs(final int a) {
      return this.abs$mcI$sp(a);
   }

   // $FF: synthetic method
   static int signum$mcI$sp$(final IntSigned $this, final int a) {
      return $this.signum$mcI$sp(a);
   }

   default int signum$mcI$sp(final int a) {
      return Integer.signum(a);
   }

   // $FF: synthetic method
   static int abs$mcI$sp$(final IntSigned $this, final int a) {
      return $this.abs$mcI$sp(a);
   }

   default int abs$mcI$sp(final int a) {
      return a < 0 ? -a : a;
   }

   static void $init$(final IntSigned $this) {
   }
}
