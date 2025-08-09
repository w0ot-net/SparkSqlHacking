package spire.math;

import algebra.ring.CommutativeRig;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006k\u0001!\tE\u000e\u0005\u0006y\u0001!\t%\u0010\u0005\u0006\u0001\u0002!\tA\f\u0002\f+2{gnZ%t\u0007JKwM\u0003\u0002\n\u0015\u0005!Q.\u0019;i\u0015\u0005Y\u0011!B:qSJ,7c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001F\u0011%\u001d\t)bD\u0004\u0002\u001799\u0011qcG\u0007\u00021)\u0011\u0011DG\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t1\"\u0003\u0002\u001e\u0015\u00059\u0011\r\\4fEJ\f\u0017BA\u0010!\u0003\u001d\u0001\u0018mY6bO\u0016T!!\b\u0006\n\u0005\t\u001a#\u0001B\"SS\u001eT!a\b\u0011\u0011\u0005\u00152S\"\u0001\u0005\n\u0005\u001dB!!B+M_:<\u0017A\u0002\u0013j]&$H\u0005F\u0001+!\tq1&\u0003\u0002-\u001f\t!QK\\5u\u0003\ryg.Z\u000b\u0002I\u0005!\u0001\u000f\\;t)\r!\u0013g\r\u0005\u0006e\r\u0001\r\u0001J\u0001\u0002C\")Ag\u0001a\u0001I\u0005\t!-A\u0002q_^$2\u0001J\u001c9\u0011\u0015\u0011D\u00011\u0001%\u0011\u0015!D\u00011\u0001:!\tq!(\u0003\u0002<\u001f\t\u0019\u0011J\u001c;\u0002\u000bQLW.Z:\u0015\u0007\u0011rt\bC\u00033\u000b\u0001\u0007A\u0005C\u00035\u000b\u0001\u0007A%\u0001\u0003{KJ|\u0007"
)
public interface ULongIsCRig extends CommutativeRig {
   // $FF: synthetic method
   static long one$(final ULongIsCRig $this) {
      return $this.one();
   }

   default long one() {
      return ULong$.MODULE$.apply(1L);
   }

   // $FF: synthetic method
   static long plus$(final ULongIsCRig $this, final long a, final long b) {
      return $this.plus(a, b);
   }

   default long plus(final long a, final long b) {
      return ULong$.MODULE$.$plus$extension(a, b);
   }

   // $FF: synthetic method
   static long pow$(final ULongIsCRig $this, final long a, final int b) {
      return $this.pow(a, b);
   }

   default long pow(final long a, final int b) {
      if (b < 0) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("negative exponent: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(b)})));
      } else {
         return ULong$.MODULE$.$times$times$extension(a, ULong$.MODULE$.apply((long)b));
      }
   }

   // $FF: synthetic method
   static long times$(final ULongIsCRig $this, final long a, final long b) {
      return $this.times(a, b);
   }

   default long times(final long a, final long b) {
      return ULong$.MODULE$.$times$extension(a, b);
   }

   // $FF: synthetic method
   static long zero$(final ULongIsCRig $this) {
      return $this.zero();
   }

   default long zero() {
      return ULong$.MODULE$.apply(0L);
   }

   static void $init$(final ULongIsCRig $this) {
   }
}
