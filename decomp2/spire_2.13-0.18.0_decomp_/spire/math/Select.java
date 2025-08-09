package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3qAA\u0002\u0011\u0002G\u0005\u0001\u0002C\u0003\u0010\u0001\u0019\u0005\u0001C\u0001\u0004TK2,7\r\u001e\u0006\u0003\t\u0015\tA!\\1uQ*\ta!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001I\u0001C\u0001\u0006\u000e\u001b\u0005Y!\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u00059Y!aA!os\u000611/\u001a7fGR,\"!\u0005\u0016\u0015\u0007Ii$\tF\u0002\u0014-Q\u0002\"A\u0003\u000b\n\u0005UY!\u0001B+oSRDqaF\u0001\u0002\u0002\u0003\u000f\u0001$\u0001\u0006fm&$WM\\2fIE\u00022!G\u0013)\u001d\tQ\"E\u0004\u0002\u001cA9\u0011AdH\u0007\u0002;)\u0011adB\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019I!!I\u0003\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0005J\u0001\ba\u0006\u001c7.Y4f\u0015\t\tS!\u0003\u0002'O\t)qJ\u001d3fe*\u00111\u0005\n\t\u0003S)b\u0001\u0001B\u0005,\u0003\u0001\u0006\t\u0011!b\u0001Y\t\t\u0011)\u0005\u0002.\u0013A\u0011!BL\u0005\u0003_-\u0011qAT8uQ&tw\r\u000b\u0002+cA\u0011!BM\u0005\u0003g-\u00111b\u001d9fG&\fG.\u001b>fI\"9Q'AA\u0001\u0002\b1\u0014AC3wS\u0012,gnY3%eA\u0019qG\u000f\u0015\u000f\u0005aJT\"A\u0003\n\u0005\r*\u0011BA\u001e=\u0005!\u0019E.Y:t)\u0006<'BA\u0012\u0006\u0011\u0015q\u0014\u00011\u0001@\u0003\u0011!\u0017\r^1\u0011\u0007)\u0001\u0005&\u0003\u0002B\u0017\t)\u0011I\u001d:bs\")1)\u0001a\u0001\t\u0006\t1\u000e\u0005\u0002\u000b\u000b&\u0011ai\u0003\u0002\u0004\u0013:$\b"
)
public interface Select {
   void select(final Object data, final int k, final Order evidence$1, final ClassTag evidence$2);

   // $FF: synthetic method
   static void select$mZc$sp$(final Select $this, final boolean[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mZc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mZc$sp(final boolean[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mBc$sp$(final Select $this, final byte[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mBc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mBc$sp(final byte[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mCc$sp$(final Select $this, final char[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mCc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mCc$sp(final char[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mDc$sp$(final Select $this, final double[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mDc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mDc$sp(final double[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mFc$sp$(final Select $this, final float[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mFc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mFc$sp(final float[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mIc$sp$(final Select $this, final int[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mIc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mIc$sp(final int[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mJc$sp$(final Select $this, final long[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mJc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mJc$sp(final long[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mSc$sp$(final Select $this, final short[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mSc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mSc$sp(final short[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static void select$mVc$sp$(final Select $this, final BoxedUnit[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      $this.select$mVc$sp(data, k, evidence$1, evidence$2);
   }

   default void select$mVc$sp(final BoxedUnit[] data, final int k, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }
}
