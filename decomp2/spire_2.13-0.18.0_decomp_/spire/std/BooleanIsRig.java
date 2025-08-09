package spire.std;

import algebra.ring.CommutativeRig;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013qa\u0002\u0005\u0011\u0002\u0007\u0005Q\u0002C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0011\u0005Q\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00035\u0001\u0011\u0005S\u0007C\u0003<\u0001\u0011\u0005C\bC\u0003@\u0001\u0011\u0005QF\u0001\u0007C_>dW-\u00198JgJKwM\u0003\u0002\n\u0015\u0005\u00191\u000f\u001e3\u000b\u0003-\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\"I9\u0011aC\b\b\u0003/qq!\u0001G\u000e\u000e\u0003eQ!A\u0007\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0011BA\u000f\u000b\u0003\u001d\tGnZ3ce\u0006L!a\b\u0011\u0002\u000fA\f7m[1hK*\u0011QDC\u0005\u0003E\r\u0012Aa\u0011*jO*\u0011q\u0004\t\t\u0003\u001f\u0015J!A\n\t\u0003\u000f\t{w\u000e\\3b]\u00061A%\u001b8ji\u0012\"\u0012!\u000b\t\u0003\u001f)J!a\u000b\t\u0003\tUs\u0017\u000e^\u0001\u0004_:,W#\u0001\u0013\u0002\tAdWo\u001d\u000b\u0004IA\u0012\u0004\"B\u0019\u0004\u0001\u0004!\u0013!A1\t\u000bM\u001a\u0001\u0019\u0001\u0013\u0002\u0003\t\f1\u0001]8x)\r!cg\u000e\u0005\u0006c\u0011\u0001\r\u0001\n\u0005\u0006g\u0011\u0001\r\u0001\u000f\t\u0003\u001feJ!A\u000f\t\u0003\u0007%sG/A\u0003uS6,7\u000fF\u0002%{yBQ!M\u0003A\u0002\u0011BQaM\u0003A\u0002\u0011\nAA_3s_\u0002"
)
public interface BooleanIsRig extends CommutativeRig {
   // $FF: synthetic method
   static boolean one$(final BooleanIsRig $this) {
      return $this.one();
   }

   default boolean one() {
      return true;
   }

   // $FF: synthetic method
   static boolean plus$(final BooleanIsRig $this, final boolean a, final boolean b) {
      return $this.plus(a, b);
   }

   default boolean plus(final boolean a, final boolean b) {
      return a || b;
   }

   // $FF: synthetic method
   static boolean pow$(final BooleanIsRig $this, final boolean a, final int b) {
      return $this.pow(a, b);
   }

   default boolean pow(final boolean a, final int b) {
      return a;
   }

   // $FF: synthetic method
   static boolean times$(final BooleanIsRig $this, final boolean a, final boolean b) {
      return $this.times(a, b);
   }

   default boolean times(final boolean a, final boolean b) {
      return a && b;
   }

   // $FF: synthetic method
   static boolean zero$(final BooleanIsRig $this) {
      return $this.zero();
   }

   default boolean zero() {
      return false;
   }

   static void $init$(final BooleanIsRig $this) {
   }
}
