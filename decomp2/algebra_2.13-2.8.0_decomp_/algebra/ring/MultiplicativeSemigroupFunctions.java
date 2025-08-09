package algebra.ring;

import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005maa\u0002\u0004\b!\u0003\r\t\u0001\u0004\u0005\u0006)\u0001!\t!\u0006\u0005\u00063\u0001!\tA\u0007\u0005\u0006q\u0001!\t!\u000f\u0005\u0006C\u0002!\tA\u0019\u0005\u0006s\u0002!\tA\u001f\u0002!\u001bVdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB4UO\\2uS>t7O\u0003\u0002\t\u0013\u0005!!/\u001b8h\u0015\u0005Q\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\ti1e\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\f\u0011\u0005=9\u0012B\u0001\r\u0011\u0005\u0011)f.\u001b;\u00027%\u001cX*\u001e7uSBd\u0017nY1uSZ,7i\\7nkR\fG/\u001b<f+\tYb\u0007\u0006\u0002\u001d?A\u0011q\"H\u0005\u0003=A\u0011qAQ8pY\u0016\fg\u000eC\u0003!\u0005\u0001\u000f\u0011%\u0001\u0002fmB\u0019!eI\u001b\r\u0001\u0011)A\u0005\u0001b\u0001K\t\t1+\u0006\u0002'_E\u0011qE\u000b\t\u0003\u001f!J!!\u000b\t\u0003\u000f9{G\u000f[5oOB\u00191\u0006\f\u0018\u000e\u0003\u001dI!!L\u0004\u0003/5+H\u000e^5qY&\u001c\u0017\r^5wKN+W.[4s_V\u0004\bC\u0001\u00120\t\u0015\u00014E1\u00012\u0005\u0005!\u0016CA\u00143!\ty1'\u0003\u00025!\t\u0019\u0011I\\=\u0011\u0005\t2D!B\u001c\u0003\u0005\u0004\t$!A!\u0002\u000bQLW.Z:\u0016\u0005ijDcA\u001e^?R\u0011Ah\u0017\t\u0003Eu\"\u0011bN\u0002!\u0002\u0003\u0005)\u0019A\u0019)\ruz$\tT)W!\ty\u0001)\u0003\u0002B!\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u00193\t\u0012$F\u001d\tyA)\u0003\u0002F!\u0005\u0019\u0011J\u001c;2\t\u0011:5*\u0005\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015.\ta\u0001\u0010:p_Rt\u0014\"A\t2\u000b\rje\nU(\u000f\u0005=q\u0015BA(\u0011\u0003\u0011auN\\42\t\u0011:5*E\u0019\u0006GI\u001bV\u000b\u0016\b\u0003\u001fMK!\u0001\u0016\t\u0002\u000b\u0019cw.\u0019;2\t\u0011:5*E\u0019\u0006G]C&,\u0017\b\u0003\u001faK!!\u0017\t\u0002\r\u0011{WO\u00197fc\u0011!siS\t\t\u000b\u0001\u001a\u00019\u0001/\u0011\u0007\t\u001aC\bC\u0003_\u0007\u0001\u0007A(A\u0001y\u0011\u0015\u00017\u00011\u0001=\u0003\u0005I\u0018a\u00019poV\u00111M\u001a\u000b\u0004IJ$HCA3q!\t\u0011c\rB\u00058\t\u0001\u0006\t\u0011!b\u0001c!2am\u00105kY:\fTaI\"ES\u0016\u000bD\u0001J$L#E*1%\u0014(l\u001fF\"AeR&\u0012c\u0015\u0019#kU7Uc\u0011!siS\t2\u000b\r:\u0006l\\-2\t\u0011:5*\u0005\u0005\u0006A\u0011\u0001\u001d!\u001d\t\u0004E\r*\u0007\"B:\u0005\u0001\u0004)\u0017!A1\t\u000bU$\u0001\u0019\u0001<\u0002\u00039\u0004\"aD<\n\u0005a\u0004\"aA%oi\u0006QAO]=Qe>$Wo\u0019;\u0016\u0007m\f\u0019\u0001F\u0002}\u0003\u0013!2!`A\u0003!\u0011ya0!\u0001\n\u0005}\u0004\"AB(qi&|g\u000eE\u0002#\u0003\u0007!QaN\u0003C\u0002EBa\u0001I\u0003A\u0004\u0005\u001d\u0001\u0003\u0002\u0012$\u0003\u0003Aq!a\u0003\u0006\u0001\u0004\ti!\u0001\u0002bgB1\u0011qBA\u000b\u0003\u0003q1aRA\t\u0013\r\t\u0019\u0002E\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t9\"!\u0007\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016T1!a\u0005\u0011\u0001"
)
public interface MultiplicativeSemigroupFunctions {
   // $FF: synthetic method
   static boolean isMultiplicativeCommutative$(final MultiplicativeSemigroupFunctions $this, final MultiplicativeSemigroup ev) {
      return $this.isMultiplicativeCommutative(ev);
   }

   default boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return ev instanceof MultiplicativeCommutativeSemigroup;
   }

   // $FF: synthetic method
   static Object times$(final MultiplicativeSemigroupFunctions $this, final Object x, final Object y, final MultiplicativeSemigroup ev) {
      return $this.times(x, y, ev);
   }

   default Object times(final Object x, final Object y, final MultiplicativeSemigroup ev) {
      return ev.times(x, y);
   }

   // $FF: synthetic method
   static Object pow$(final MultiplicativeSemigroupFunctions $this, final Object a, final int n, final MultiplicativeSemigroup ev) {
      return $this.pow(a, n, ev);
   }

   default Object pow(final Object a, final int n, final MultiplicativeSemigroup ev) {
      return ev.pow(a, n);
   }

   // $FF: synthetic method
   static Option tryProduct$(final MultiplicativeSemigroupFunctions $this, final IterableOnce as, final MultiplicativeSemigroup ev) {
      return $this.tryProduct(as, ev);
   }

   default Option tryProduct(final IterableOnce as, final MultiplicativeSemigroup ev) {
      return ev.tryProduct(as);
   }

   // $FF: synthetic method
   static double times$mDc$sp$(final MultiplicativeSemigroupFunctions $this, final double x, final double y, final MultiplicativeSemigroup ev) {
      return $this.times$mDc$sp(x, y, ev);
   }

   default double times$mDc$sp(final double x, final double y, final MultiplicativeSemigroup ev) {
      return ev.times$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static float times$mFc$sp$(final MultiplicativeSemigroupFunctions $this, final float x, final float y, final MultiplicativeSemigroup ev) {
      return $this.times$mFc$sp(x, y, ev);
   }

   default float times$mFc$sp(final float x, final float y, final MultiplicativeSemigroup ev) {
      return ev.times$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static int times$mIc$sp$(final MultiplicativeSemigroupFunctions $this, final int x, final int y, final MultiplicativeSemigroup ev) {
      return $this.times$mIc$sp(x, y, ev);
   }

   default int times$mIc$sp(final int x, final int y, final MultiplicativeSemigroup ev) {
      return ev.times$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long times$mJc$sp$(final MultiplicativeSemigroupFunctions $this, final long x, final long y, final MultiplicativeSemigroup ev) {
      return $this.times$mJc$sp(x, y, ev);
   }

   default long times$mJc$sp(final long x, final long y, final MultiplicativeSemigroup ev) {
      return ev.times$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static double pow$mDc$sp$(final MultiplicativeSemigroupFunctions $this, final double a, final int n, final MultiplicativeSemigroup ev) {
      return $this.pow$mDc$sp(a, n, ev);
   }

   default double pow$mDc$sp(final double a, final int n, final MultiplicativeSemigroup ev) {
      return ev.pow$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static float pow$mFc$sp$(final MultiplicativeSemigroupFunctions $this, final float a, final int n, final MultiplicativeSemigroup ev) {
      return $this.pow$mFc$sp(a, n, ev);
   }

   default float pow$mFc$sp(final float a, final int n, final MultiplicativeSemigroup ev) {
      return ev.pow$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static int pow$mIc$sp$(final MultiplicativeSemigroupFunctions $this, final int a, final int n, final MultiplicativeSemigroup ev) {
      return $this.pow$mIc$sp(a, n, ev);
   }

   default int pow$mIc$sp(final int a, final int n, final MultiplicativeSemigroup ev) {
      return ev.pow$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static long pow$mJc$sp$(final MultiplicativeSemigroupFunctions $this, final long a, final int n, final MultiplicativeSemigroup ev) {
      return $this.pow$mJc$sp(a, n, ev);
   }

   default long pow$mJc$sp(final long a, final int n, final MultiplicativeSemigroup ev) {
      return ev.pow$mcJ$sp(a, n);
   }

   static void $init$(final MultiplicativeSemigroupFunctions $this) {
   }
}
