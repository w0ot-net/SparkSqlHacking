package spire.syntax.std;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001DA\u0006BeJ\f\u0017pU=oi\u0006D(BA\u0003\u0007\u0003\r\u0019H\u000f\u001a\u0006\u0003\u000f!\taa]=oi\u0006D(\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005!\u0002CA\u0007\u0016\u0013\t1bB\u0001\u0003V]&$\u0018\u0001C1se\u0006Lx\n]:\u0016\u0005e\u0001CC\u0001\u000e.!\rYBDH\u0007\u0002\t%\u0011Q\u0004\u0002\u0002\t\u0003J\u0014\u0018-_(qgB\u0011q\u0004\t\u0007\u0001\t%\t#\u0001)A\u0001\u0002\u000b\u0007!EA\u0001B#\t\u0019c\u0005\u0005\u0002\u000eI%\u0011QE\u0004\u0002\b\u001d>$\b.\u001b8h!\tiq%\u0003\u0002)\u001d\t\u0019\u0011I\\=)\u0005\u0001R\u0003CA\u0007,\u0013\tacBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007\"\u0002\u0018\u0003\u0001\u0004y\u0013a\u00017igB\u0019Q\u0002\r\u0010\n\u0005Er!!B!se\u0006L\b"
)
public interface ArraySyntax {
   // $FF: synthetic method
   static ArrayOps arrayOps$(final ArraySyntax $this, final Object lhs) {
      return $this.arrayOps(lhs);
   }

   default ArrayOps arrayOps(final Object lhs) {
      return new ArrayOps(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mZc$sp$(final ArraySyntax $this, final boolean[] lhs) {
      return $this.arrayOps$mZc$sp(lhs);
   }

   default ArrayOps arrayOps$mZc$sp(final boolean[] lhs) {
      return new ArrayOps$mcZ$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mBc$sp$(final ArraySyntax $this, final byte[] lhs) {
      return $this.arrayOps$mBc$sp(lhs);
   }

   default ArrayOps arrayOps$mBc$sp(final byte[] lhs) {
      return new ArrayOps$mcB$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mCc$sp$(final ArraySyntax $this, final char[] lhs) {
      return $this.arrayOps$mCc$sp(lhs);
   }

   default ArrayOps arrayOps$mCc$sp(final char[] lhs) {
      return new ArrayOps$mcC$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mDc$sp$(final ArraySyntax $this, final double[] lhs) {
      return $this.arrayOps$mDc$sp(lhs);
   }

   default ArrayOps arrayOps$mDc$sp(final double[] lhs) {
      return new ArrayOps$mcD$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mFc$sp$(final ArraySyntax $this, final float[] lhs) {
      return $this.arrayOps$mFc$sp(lhs);
   }

   default ArrayOps arrayOps$mFc$sp(final float[] lhs) {
      return new ArrayOps$mcF$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mIc$sp$(final ArraySyntax $this, final int[] lhs) {
      return $this.arrayOps$mIc$sp(lhs);
   }

   default ArrayOps arrayOps$mIc$sp(final int[] lhs) {
      return new ArrayOps$mcI$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mJc$sp$(final ArraySyntax $this, final long[] lhs) {
      return $this.arrayOps$mJc$sp(lhs);
   }

   default ArrayOps arrayOps$mJc$sp(final long[] lhs) {
      return new ArrayOps$mcJ$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mSc$sp$(final ArraySyntax $this, final short[] lhs) {
      return $this.arrayOps$mSc$sp(lhs);
   }

   default ArrayOps arrayOps$mSc$sp(final short[] lhs) {
      return new ArrayOps$mcS$sp(lhs);
   }

   // $FF: synthetic method
   static ArrayOps arrayOps$mVc$sp$(final ArraySyntax $this, final BoxedUnit[] lhs) {
      return $this.arrayOps$mVc$sp(lhs);
   }

   default ArrayOps arrayOps$mVc$sp(final BoxedUnit[] lhs) {
      return new ArrayOps$mcV$sp(lhs);
   }

   static void $init$(final ArraySyntax $this) {
   }
}
