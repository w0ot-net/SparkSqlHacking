package spire.std;

import algebra.ring.Field;
import cats.kernel.Eq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.NotGiven;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\raaB\u0003\u0007!\u0003\r\ta\u0003\u0005\u0006-\u0001!\taF\u0003\u00057\u0001\u0001A\u0004C\u00036\u0001\u0011\ra\u0007C\u0003u\u0001\u0011\rQOA\bBeJ\f\u00170\u00138ti\u0006t7-Z:2\u0015\t9\u0001\"A\u0002ti\u0012T\u0011!C\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001AB\u0005\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005M!R\"\u0001\u0004\n\u0005U1!aD!se\u0006L\u0018J\\:uC:\u001cWm\u001d\u0019\u0002\r\u0011Jg.\u001b;%)\u0005A\u0002CA\u0007\u001a\u0013\tQbB\u0001\u0003V]&$(a\u0001(JcU\u0011Q\u0004\f\t\u0004=}\tS\"\u0001\u0005\n\u0005\u0001B!\u0001\u0003(pi\u001eKg/\u001a8\u0011\t\t*sEK\u0007\u0002G)\u0011A\u0005C\u0001\bC2<WM\u0019:b\u0013\t13EA\tO_JlW\r\u001a,fGR|'o\u00159bG\u0016\u00042!\u0004\u0015+\u0013\tIcBA\u0003BeJ\f\u0017\u0010\u0005\u0002,Y1\u0001A!B\u0017\u0003\u0005\u0004q#!A!\u0012\u0005=\u0012\u0004CA\u00071\u0013\t\tdBA\u0004O_RD\u0017N\\4\u0011\u00055\u0019\u0014B\u0001\u001b\u000f\u0005\r\te._\u0001\u0011\u0003J\u0014\u0018-\u001f,fGR|'o\u00159bG\u0016,\"aN\u001f\u0015\taZv\f\u001b\t\u0005EeZD(\u0003\u0002;G\tYa+Z2u_J\u001c\u0006/Y2f!\ri\u0001\u0006\u0010\t\u0003Wu\"\u0011\"L\u0002!\u0002\u0003\u0005)\u0019\u0001\u0018)\ruz$\tT)W!\ti\u0001)\u0003\u0002B\u001d\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u00193\t\u0012$F\u001d\tiA)\u0003\u0002F\u001d\u0005\u0019\u0011J\u001c;2\t\u0011:5j\u0004\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015*\ta\u0001\u0010:p_Rt\u0014\"A\b2\u000b\rje\nU(\u000f\u00055q\u0015BA(\u000f\u0003\u0011auN\\42\t\u0011:5jD\u0019\u0006GI\u001bV\u000b\u0016\b\u0003\u001bMK!\u0001\u0016\b\u0002\u000b\u0019cw.\u0019;2\t\u0011:5jD\u0019\u0006G]C&,\u0017\b\u0003\u001baK!!\u0017\b\u0002\r\u0011{WO\u00197fc\u0011!siS\b\t\u000fq\u001b\u0011\u0011!a\u0002;\u0006YQM^5eK:\u001cW\rJ\u00196!\rq&\u0001P\u0007\u0002\u0001!9\u0001mAA\u0001\u0002\b\t\u0017aC3wS\u0012,gnY3%cY\u00022AY3=\u001d\tq2-\u0003\u0002e\u0011\u00059\u0001/Y2lC\u001e,\u0017B\u00014h\u0005!\u0019E.Y:t)\u0006<'B\u00013\t\u0011\u001dI7!!AA\u0004)\f1\"\u001a<jI\u0016t7-\u001a\u00132oA\u00191.\u001d\u001f\u000f\u00051\u0004hBA7p\u001d\tAe.C\u0001\n\u0013\t!\u0003\"\u0003\u0002eG%\u0011!o\u001d\u0002\u0006\r&,G\u000e\u001a\u0006\u0003I\u000e\nq!\u0011:sCf,\u0015/\u0006\u0002wyR\u0011qO \t\u0004WbT\u0018BA=t\u0005\t)\u0015\u000fE\u0002\u000eQm\u0004\"a\u000b?\u0005\u00135\"\u0001\u0015!A\u0001\u0006\u0004q\u0003F\u0001?@\u0011!yH!!AA\u0004\u0005\u0005\u0011aC3wS\u0012,gnY3%ca\u00022a\u001b=|\u0001"
)
public interface ArrayInstances1 extends ArrayInstances0 {
   // $FF: synthetic method
   static VectorSpace ArrayVectorSpace$(final ArrayInstances1 $this, final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return $this.ArrayVectorSpace(evidence$15, evidence$16, evidence$17);
   }

   default VectorSpace ArrayVectorSpace(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return new ArrayVectorSpace(evidence$16, evidence$17, evidence$15);
   }

   // $FF: synthetic method
   static Eq ArrayEq$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq(evidence$18);
   }

   default Eq ArrayEq(final Eq evidence$18) {
      return new ArrayEq(evidence$18);
   }

   // $FF: synthetic method
   static VectorSpace ArrayVectorSpace$mDc$sp$(final ArrayInstances1 $this, final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return $this.ArrayVectorSpace$mDc$sp(evidence$15, evidence$16, evidence$17);
   }

   default VectorSpace ArrayVectorSpace$mDc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return new ArrayVectorSpace$mcD$sp(evidence$16, evidence$17, evidence$15);
   }

   // $FF: synthetic method
   static VectorSpace ArrayVectorSpace$mFc$sp$(final ArrayInstances1 $this, final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return $this.ArrayVectorSpace$mFc$sp(evidence$15, evidence$16, evidence$17);
   }

   default VectorSpace ArrayVectorSpace$mFc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return new ArrayVectorSpace$mcF$sp(evidence$16, evidence$17, evidence$15);
   }

   // $FF: synthetic method
   static VectorSpace ArrayVectorSpace$mIc$sp$(final ArrayInstances1 $this, final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return $this.ArrayVectorSpace$mIc$sp(evidence$15, evidence$16, evidence$17);
   }

   default VectorSpace ArrayVectorSpace$mIc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return new ArrayVectorSpace$mcI$sp(evidence$16, evidence$17, evidence$15);
   }

   // $FF: synthetic method
   static VectorSpace ArrayVectorSpace$mJc$sp$(final ArrayInstances1 $this, final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return $this.ArrayVectorSpace$mJc$sp(evidence$15, evidence$16, evidence$17);
   }

   default VectorSpace ArrayVectorSpace$mJc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return new ArrayVectorSpace$mcJ$sp(evidence$16, evidence$17, evidence$15);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mZc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mZc$sp(evidence$18);
   }

   default Eq ArrayEq$mZc$sp(final Eq evidence$18) {
      return new ArrayEq(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mBc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mBc$sp(evidence$18);
   }

   default Eq ArrayEq$mBc$sp(final Eq evidence$18) {
      return new ArrayEq(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mCc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mCc$sp(evidence$18);
   }

   default Eq ArrayEq$mCc$sp(final Eq evidence$18) {
      return new ArrayEq(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mDc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mDc$sp(evidence$18);
   }

   default Eq ArrayEq$mDc$sp(final Eq evidence$18) {
      return new ArrayEq$mcD$sp(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mFc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mFc$sp(evidence$18);
   }

   default Eq ArrayEq$mFc$sp(final Eq evidence$18) {
      return new ArrayEq$mcF$sp(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mIc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mIc$sp(evidence$18);
   }

   default Eq ArrayEq$mIc$sp(final Eq evidence$18) {
      return new ArrayEq$mcI$sp(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mJc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mJc$sp(evidence$18);
   }

   default Eq ArrayEq$mJc$sp(final Eq evidence$18) {
      return new ArrayEq$mcJ$sp(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mSc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mSc$sp(evidence$18);
   }

   default Eq ArrayEq$mSc$sp(final Eq evidence$18) {
      return new ArrayEq(evidence$18);
   }

   // $FF: synthetic method
   static Eq ArrayEq$mVc$sp$(final ArrayInstances1 $this, final Eq evidence$18) {
      return $this.ArrayEq$mVc$sp(evidence$18);
   }

   default Eq ArrayEq$mVc$sp(final Eq evidence$18) {
      return new ArrayEq(evidence$18);
   }

   static void $init$(final ArrayInstances1 $this) {
   }
}
