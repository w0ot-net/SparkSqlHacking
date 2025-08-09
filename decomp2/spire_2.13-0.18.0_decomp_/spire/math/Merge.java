package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053qAA\u0002\u0011\u0002G\u0005\u0001\u0002C\u0003\u0010\u0001\u0019\u0005\u0001CA\u0003NKJ<WM\u0003\u0002\u0005\u000b\u0005!Q.\u0019;i\u0015\u00051\u0011!B:qSJ,7\u0001A\n\u0003\u0001%\u0001\"AC\u0007\u000e\u0003-Q\u0011\u0001D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001d-\u00111!\u00118z\u0003\u0015iWM]4f+\t\t\u0002\u0004F\u0002\u0013{}\"2a\u0005\u00125!\rQACF\u0005\u0003+-\u0011Q!\u0011:sCf\u0004\"a\u0006\r\r\u0001\u0011I\u0011$\u0001Q\u0001\u0002\u0003\u0015\rA\u0007\u0002\u0002\u0003F\u00111$\u0003\t\u0003\u0015qI!!H\u0006\u0003\u000f9{G\u000f[5oO\"\u0012\u0001d\b\t\u0003\u0015\u0001J!!I\u0006\u0003\u0017M\u0004XmY5bY&TX\r\u001a\u0005\bG\u0005\t\t\u0011q\u0001%\u0003))g/\u001b3f]\u000e,G%\r\t\u0004KE2bB\u0001\u0014/\u001d\t9CF\u0004\u0002)W5\t\u0011F\u0003\u0002+\u000f\u00051AH]8pizJ\u0011AB\u0005\u0003[\u0015\tq!\u00197hK\n\u0014\u0018-\u0003\u00020a\u00059\u0001/Y2lC\u001e,'BA\u0017\u0006\u0013\t\u00114GA\u0003Pe\u0012,'O\u0003\u00020a!9Q'AA\u0001\u0002\b1\u0014AC3wS\u0012,gnY3%eA\u0019qG\u000f\f\u000f\u0005aJT\"A\u0003\n\u0005=*\u0011BA\u001e=\u0005!\u0019E.Y:t)\u0006<'BA\u0018\u0006\u0011\u0015q\u0014\u00011\u0001\u0014\u0003\u0005\t\u0007\"\u0002!\u0002\u0001\u0004\u0019\u0012!\u00012"
)
public interface Merge {
   Object merge(final Object a, final Object b, final Order evidence$1, final ClassTag evidence$2);

   // $FF: synthetic method
   static boolean[] merge$mZc$sp$(final Merge $this, final boolean[] a, final boolean[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mZc$sp(a, b, evidence$1, evidence$2);
   }

   default boolean[] merge$mZc$sp(final boolean[] a, final boolean[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static byte[] merge$mBc$sp$(final Merge $this, final byte[] a, final byte[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mBc$sp(a, b, evidence$1, evidence$2);
   }

   default byte[] merge$mBc$sp(final byte[] a, final byte[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static char[] merge$mCc$sp$(final Merge $this, final char[] a, final char[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mCc$sp(a, b, evidence$1, evidence$2);
   }

   default char[] merge$mCc$sp(final char[] a, final char[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static double[] merge$mDc$sp$(final Merge $this, final double[] a, final double[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mDc$sp(a, b, evidence$1, evidence$2);
   }

   default double[] merge$mDc$sp(final double[] a, final double[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static float[] merge$mFc$sp$(final Merge $this, final float[] a, final float[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mFc$sp(a, b, evidence$1, evidence$2);
   }

   default float[] merge$mFc$sp(final float[] a, final float[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static int[] merge$mIc$sp$(final Merge $this, final int[] a, final int[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mIc$sp(a, b, evidence$1, evidence$2);
   }

   default int[] merge$mIc$sp(final int[] a, final int[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static long[] merge$mJc$sp$(final Merge $this, final long[] a, final long[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mJc$sp(a, b, evidence$1, evidence$2);
   }

   default long[] merge$mJc$sp(final long[] a, final long[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static short[] merge$mSc$sp$(final Merge $this, final short[] a, final short[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mSc$sp(a, b, evidence$1, evidence$2);
   }

   default short[] merge$mSc$sp(final short[] a, final short[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static BoxedUnit[] merge$mVc$sp$(final Merge $this, final BoxedUnit[] a, final BoxedUnit[] b, final Order evidence$1, final ClassTag evidence$2) {
      return $this.merge$mVc$sp(a, b, evidence$1, evidence$2);
   }

   default BoxedUnit[] merge$mVc$sp(final BoxedUnit[] a, final BoxedUnit[] b, final Order evidence$1, final ClassTag evidence$2) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }
}
