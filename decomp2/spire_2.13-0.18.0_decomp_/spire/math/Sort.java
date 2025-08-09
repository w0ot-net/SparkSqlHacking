package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\t3qAA\u0002\u0011\u0002G\u0005\u0001\u0002C\u0003\u0010\u0001\u0019\u0005\u0001C\u0001\u0003T_J$(B\u0001\u0003\u0006\u0003\u0011i\u0017\r\u001e5\u000b\u0003\u0019\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0013A\u0011!\"D\u0007\u0002\u0017)\tA\"A\u0003tG\u0006d\u0017-\u0003\u0002\u000f\u0017\t\u0019\u0011I\\=\u0002\tM|'\u000f^\u000b\u0003#)\"\"AE\u001f\u0015\u0007M1B\u0007\u0005\u0002\u000b)%\u0011Qc\u0003\u0002\u0005+:LG\u000fC\u0004\u0018\u0003\u0005\u0005\t9\u0001\r\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002\u001aK!r!A\u0007\u0012\u000f\u0005m\u0001cB\u0001\u000f \u001b\u0005i\"B\u0001\u0010\b\u0003\u0019a$o\\8u}%\ta!\u0003\u0002\"\u000b\u00059\u0011\r\\4fEJ\f\u0017BA\u0012%\u0003\u001d\u0001\u0018mY6bO\u0016T!!I\u0003\n\u0005\u0019:#!B(sI\u0016\u0014(BA\u0012%!\tI#\u0006\u0004\u0001\u0005\u0013-\n\u0001\u0015!A\u0001\u0006\u0004a#!A!\u0012\u00055J\u0001C\u0001\u0006/\u0013\ty3BA\u0004O_RD\u0017N\\4)\u0005)\n\u0004C\u0001\u00063\u0013\t\u00194BA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007bB\u001b\u0002\u0003\u0003\u0005\u001dAN\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004cA\u001c;Q9\u0011\u0001(O\u0007\u0002\u000b%\u00111%B\u0005\u0003wq\u0012\u0001b\u00117bgN$\u0016m\u001a\u0006\u0003G\u0015AQAP\u0001A\u0002}\nA\u0001Z1uCB\u0019!\u0002\u0011\u0015\n\u0005\u0005[!!B!se\u0006L\b"
)
public interface Sort {
   void sort(final Object data, final Order evidence$1, final ClassTag evidence$2);

   // $FF: synthetic method
   static void sort$mZc$sp$(final Sort $this, final boolean[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mZc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mZc$sp(final boolean[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mBc$sp$(final Sort $this, final byte[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mBc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mBc$sp(final byte[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mCc$sp$(final Sort $this, final char[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mCc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mCc$sp(final char[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mDc$sp$(final Sort $this, final double[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mDc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mDc$sp(final double[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mFc$sp$(final Sort $this, final float[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mFc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mFc$sp(final float[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mIc$sp$(final Sort $this, final int[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mIc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mIc$sp(final int[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mJc$sp$(final Sort $this, final long[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mJc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mJc$sp(final long[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mSc$sp$(final Sort $this, final short[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mSc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mSc$sp(final short[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void sort$mVc$sp$(final Sort $this, final BoxedUnit[] data, final Order evidence$1, final ClassTag evidence$2) {
      $this.sort$mVc$sp(data, evidence$1, evidence$2);
   }

   default void sort$mVc$sp(final BoxedUnit[] data, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }
}
