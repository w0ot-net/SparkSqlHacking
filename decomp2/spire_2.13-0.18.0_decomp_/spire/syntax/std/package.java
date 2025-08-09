package spire.syntax.std;

import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005i;Q!\u0006\f\t\u0002u1Qa\b\f\t\u0002\u0001BQaJ\u0001\u0005\u0002!:Q!K\u0001\t\u0002)2Q\u0001L\u0001\t\u00025BQa\n\u0003\u0005\u0002E:QAM\u0001\t\u0002M2Q\u0001N\u0001\t\u0002UBQaJ\u0004\u0005\u0002e:QAO\u0001\t\u0002m2Q\u0001P\u0001\t\u0002uBQa\n\u0006\u0005\u0002\u0005;QAQ\u0001\t\u0002\r3Q\u0001R\u0001\t\u0002\u0015CQaJ\u0007\u0005\u0002%;QAS\u0001\t\u0002-3Q\u0001T\u0001\t\u00025CQa\n\t\u0005\u0002E;QAU\u0001\t\u0002M3Q\u0001V\u0001\t\u0002UCQaJ\n\u0005\u0002e\u000bq\u0001]1dW\u0006<WM\u0003\u0002\u00181\u0005\u00191\u000f\u001e3\u000b\u0005eQ\u0012AB:z]R\f\u0007PC\u0001\u001c\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0001\"AH\u0001\u000e\u0003Y\u0011q\u0001]1dW\u0006<Wm\u0005\u0002\u0002CA\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u000f\u0002\u0007%tG\u000f\u0005\u0002,\t5\t\u0011AA\u0002j]R\u001c2\u0001B\u0011/!\tqr&\u0003\u00021-\tI\u0011J\u001c;Ts:$\u0018\r\u001f\u000b\u0002U\u0005!An\u001c8h!\tYsA\u0001\u0003m_:<7cA\u0004\"mA\u0011adN\u0005\u0003qY\u0011!\u0002T8oONKh\u000e^1y)\u0005\u0019\u0014A\u00023pk\ndW\r\u0005\u0002,\u0015\t1Am\\;cY\u0016\u001c2AC\u0011?!\tqr(\u0003\u0002A-\taAi\\;cY\u0016\u001c\u0016P\u001c;bqR\t1(\u0001\u0004cS\u001eLe\u000e\u001e\t\u0003W5\u0011aAY5h\u0013:$8cA\u0007\"\rB\u0011adR\u0005\u0003\u0011Z\u0011ABQ5h\u0013:$8+\u001f8uCb$\u0012aQ\u0001\u0006CJ\u0014\u0018-\u001f\t\u0003WA\u0011Q!\u0019:sCf\u001c2\u0001E\u0011O!\tqr*\u0003\u0002Q-\tY\u0011I\u001d:bsNKh\u000e^1y)\u0005Y\u0015aA:fcB\u00111f\u0005\u0002\u0004g\u0016\f8cA\n\"-B\u0011adV\u0005\u00031Z\u0011\u0011bU3r'ftG/\u0019=\u0015\u0003M\u0003"
)
public final class package {
   public static class int$ implements IntSyntax {
      public static final int$ MODULE$ = new int$();

      static {
         IntSyntax.$init$(MODULE$);
      }

      public int literalIntOps(final int n) {
         return IntSyntax.literalIntOps$(this, n);
      }

      public Object intToA(final int n, final ConvertableTo c) {
         return IntSyntax.intToA$(this, n, c);
      }
   }

   public static class long$ implements LongSyntax {
      public static final long$ MODULE$ = new long$();

      static {
         LongSyntax.$init$(MODULE$);
      }

      public long literalLongOps(final long n) {
         return LongSyntax.literalLongOps$(this, n);
      }
   }

   public static class double$ implements DoubleSyntax {
      public static final double$ MODULE$ = new double$();

      static {
         DoubleSyntax.$init$(MODULE$);
      }

      public double literalDoubleOps(final double n) {
         return DoubleSyntax.literalDoubleOps$(this, n);
      }
   }

   public static class bigInt$ implements BigIntSyntax {
      public static final bigInt$ MODULE$ = new bigInt$();

      static {
         BigIntSyntax.$init$(MODULE$);
      }

      public BigInt literalBigIntOps(final BigInt b) {
         return BigIntSyntax.literalBigIntOps$(this, b);
      }
   }

   public static class array$ implements ArraySyntax {
      public static final array$ MODULE$ = new array$();

      static {
         ArraySyntax.$init$(MODULE$);
      }

      public ArrayOps arrayOps(final Object lhs) {
         return ArraySyntax.arrayOps$(this, lhs);
      }

      public ArrayOps arrayOps$mZc$sp(final boolean[] lhs) {
         return ArraySyntax.arrayOps$mZc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mBc$sp(final byte[] lhs) {
         return ArraySyntax.arrayOps$mBc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mCc$sp(final char[] lhs) {
         return ArraySyntax.arrayOps$mCc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mDc$sp(final double[] lhs) {
         return ArraySyntax.arrayOps$mDc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mFc$sp(final float[] lhs) {
         return ArraySyntax.arrayOps$mFc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mIc$sp(final int[] lhs) {
         return ArraySyntax.arrayOps$mIc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mJc$sp(final long[] lhs) {
         return ArraySyntax.arrayOps$mJc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mSc$sp(final short[] lhs) {
         return ArraySyntax.arrayOps$mSc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mVc$sp(final BoxedUnit[] lhs) {
         return ArraySyntax.arrayOps$mVc$sp$(this, lhs);
      }
   }

   public static class seq$ implements SeqSyntax {
      public static final seq$ MODULE$ = new seq$();

      static {
         SeqSyntax.$init$(MODULE$);
      }

      public SeqOps seqOps(final Iterable lhs) {
         return SeqSyntax.seqOps$(this, lhs);
      }

      public IndexedSeqOps indexedSeqOps(final IndexedSeq lhs) {
         return SeqSyntax.indexedSeqOps$(this, lhs);
      }
   }
}
