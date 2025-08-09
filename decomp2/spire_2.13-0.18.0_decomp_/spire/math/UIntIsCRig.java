package spire.math;

import algebra.ring.CommutativeRig;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006k\u0001!\tE\u000e\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\tA\f\u0002\u000b+&sG/S:D%&<'BA\u0005\u000b\u0003\u0011i\u0017\r\u001e5\u000b\u0003-\tQa\u001d9je\u0016\u001c2\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#\t\u0013\u000f\u0005UqbB\u0001\f\u001d\u001d\t92$D\u0001\u0019\u0015\tI\"$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0011BA\u000f\u000b\u0003\u001d\tGnZ3ce\u0006L!a\b\u0011\u0002\u000fA\f7m[1hK*\u0011QDC\u0005\u0003E\r\u0012Aa\u0011*jO*\u0011q\u0004\t\t\u0003K\u0019j\u0011\u0001C\u0005\u0003O!\u0011A!V%oi\u00061A%\u001b8ji\u0012\"\u0012A\u000b\t\u0003\u001d-J!\u0001L\b\u0003\tUs\u0017\u000e^\u0001\u0004_:,W#\u0001\u0013\u0002\tAdWo\u001d\u000b\u0004IE\u001a\u0004\"\u0002\u001a\u0004\u0001\u0004!\u0013!A1\t\u000bQ\u001a\u0001\u0019\u0001\u0013\u0002\u0003\t\f1\u0001]8x)\r!s\u0007\u000f\u0005\u0006e\u0011\u0001\r\u0001\n\u0005\u0006i\u0011\u0001\r!\u000f\t\u0003\u001diJ!aO\b\u0003\u0007%sG/A\u0003uS6,7\u000fF\u0002%}}BQAM\u0003A\u0002\u0011BQ\u0001N\u0003A\u0002\u0011\nAA_3s_\u0002"
)
public interface UIntIsCRig extends CommutativeRig {
   // $FF: synthetic method
   static int one$(final UIntIsCRig $this) {
      return $this.one();
   }

   default int one() {
      return UInt$.MODULE$.apply(1);
   }

   // $FF: synthetic method
   static int plus$(final UIntIsCRig $this, final int a, final int b) {
      return $this.plus(a, b);
   }

   default int plus(final int a, final int b) {
      return UInt$.MODULE$.$plus$extension(a, b);
   }

   // $FF: synthetic method
   static int pow$(final UIntIsCRig $this, final int a, final int b) {
      return $this.pow(a, b);
   }

   default int pow(final int a, final int b) {
      if (b < 0) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("negative exponent: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(b)})));
      } else {
         return UInt$.MODULE$.$times$times$extension(a, UInt$.MODULE$.apply(b));
      }
   }

   // $FF: synthetic method
   static int times$(final UIntIsCRig $this, final int a, final int b) {
      return $this.times(a, b);
   }

   default int times(final int a, final int b) {
      return UInt$.MODULE$.$times$extension(a, b);
   }

   // $FF: synthetic method
   static int zero$(final UIntIsCRig $this) {
      return $this.zero();
   }

   default int zero() {
      return UInt$.MODULE$.apply(0);
   }

   static void $init$(final UIntIsCRig $this) {
   }
}
