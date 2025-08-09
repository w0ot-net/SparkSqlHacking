package spire.std;

import algebra.ring.Field;
import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.InnerProductSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003[\u0001\u0011\r1LA\bBeJ\f\u00170\u00138ti\u0006t7-Z:3\u0015\t1q!A\u0002ti\u0012T\u0011\u0001C\u0001\u0006gBL'/Z\u0002\u0001'\r\u00011\"\u0005\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005I\u0019R\"A\u0003\n\u0005Q)!aD!se\u0006L\u0018J\\:uC:\u001cWm]\u0019\u0002\r\u0011Jg.\u001b;%)\u00059\u0002C\u0001\u0007\u0019\u0013\tIRB\u0001\u0003V]&$\u0018AF!se\u0006L\u0018J\u001c8feB\u0013x\u000eZ;diN\u0003\u0018mY3\u0016\u0005qACcA\u000fE#B!a$I\u0012'\u001b\u0005y\"B\u0001\u0011\b\u0003\u001d\tGnZ3ce\u0006L!AI\u0010\u0003#%sg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cW\rE\u0002\rI\u0019J!!J\u0007\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u001dBC\u0002\u0001\u0003\nS\t\u0001\u000b\u0011!AC\u0002)\u0012\u0011!Q\t\u0003W9\u0002\"\u0001\u0004\u0017\n\u00055j!a\u0002(pi\"Lgn\u001a\t\u0003\u0019=J!\u0001M\u0007\u0003\u0007\u0005s\u0017\u0010\u000b\u0003)eUz\u0004C\u0001\u00074\u0013\t!TBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00127oeBdB\u0001\u00078\u0013\tAT\"A\u0003GY>\fG/\r\u0003%uyraBA\u001e?\u001b\u0005a$BA\u001f\n\u0003\u0019a$o\\8u}%\ta\"M\u0003$\u0001\u0006\u001b%I\u0004\u0002\r\u0003&\u0011!)D\u0001\u0007\t>,(\r\\32\t\u0011RdH\u0004\u0005\b\u000b\n\t\t\u0011q\u0001G\u0003-)g/\u001b3f]\u000e,G%M\u001d\u0011\u0007\u001dseE\u0004\u0002I\u0019:\u0011\u0011j\u0013\b\u0003w)K\u0011\u0001C\u0005\u0003A\u001dI!!T\u0010\u0002\u000fA\f7m[1hK&\u0011q\n\u0015\u0002\u0006\r&,G\u000e\u001a\u0006\u0003\u001b~AqA\u0015\u0002\u0002\u0002\u0003\u000f1+A\u0006fm&$WM\\2fII\u0002\u0004c\u0001+XM9\u0011QKV\u0007\u0002\u000f%\u0011QjB\u0005\u00031f\u0013\u0001b\u00117bgN$\u0016m\u001a\u0006\u0003\u001b\u001e\t!\"\u0011:sCf|%\u000fZ3s+\ta&\r\u0006\u0002^IB\u0019qI\u00181\n\u0005}\u0003&!B(sI\u0016\u0014\bc\u0001\u0007%CB\u0011qE\u0019\u0003\nS\r\u0001\u000b\u0011!AC\u0002)B#A\u0019\u001a\t\u000f\u0015\u001c\u0011\u0011!a\u0002M\u0006YQM^5eK:\u001cW\r\n\u001a2!\r9e,\u0019"
)
public interface ArrayInstances2 extends ArrayInstances1 {
   // $FF: synthetic method
   static InnerProductSpace ArrayInnerProductSpace$(final ArrayInstances2 $this, final Field evidence$19, final ClassTag evidence$20) {
      return $this.ArrayInnerProductSpace(evidence$19, evidence$20);
   }

   default InnerProductSpace ArrayInnerProductSpace(final Field evidence$19, final ClassTag evidence$20) {
      return new ArrayInnerProductSpace(evidence$20, evidence$19);
   }

   // $FF: synthetic method
   static Order ArrayOrder$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder(evidence$21);
   }

   default Order ArrayOrder(final Order evidence$21) {
      return new ArrayOrder(evidence$21);
   }

   // $FF: synthetic method
   static InnerProductSpace ArrayInnerProductSpace$mDc$sp$(final ArrayInstances2 $this, final Field evidence$19, final ClassTag evidence$20) {
      return $this.ArrayInnerProductSpace$mDc$sp(evidence$19, evidence$20);
   }

   default InnerProductSpace ArrayInnerProductSpace$mDc$sp(final Field evidence$19, final ClassTag evidence$20) {
      return new ArrayInnerProductSpace$mcD$sp(evidence$20, evidence$19);
   }

   // $FF: synthetic method
   static InnerProductSpace ArrayInnerProductSpace$mFc$sp$(final ArrayInstances2 $this, final Field evidence$19, final ClassTag evidence$20) {
      return $this.ArrayInnerProductSpace$mFc$sp(evidence$19, evidence$20);
   }

   default InnerProductSpace ArrayInnerProductSpace$mFc$sp(final Field evidence$19, final ClassTag evidence$20) {
      return new ArrayInnerProductSpace$mcF$sp(evidence$20, evidence$19);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mZc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mZc$sp(evidence$21);
   }

   default Order ArrayOrder$mZc$sp(final Order evidence$21) {
      return new ArrayOrder(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mBc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mBc$sp(evidence$21);
   }

   default Order ArrayOrder$mBc$sp(final Order evidence$21) {
      return new ArrayOrder(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mCc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mCc$sp(evidence$21);
   }

   default Order ArrayOrder$mCc$sp(final Order evidence$21) {
      return new ArrayOrder(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mDc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mDc$sp(evidence$21);
   }

   default Order ArrayOrder$mDc$sp(final Order evidence$21) {
      return new ArrayOrder$mcD$sp(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mFc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mFc$sp(evidence$21);
   }

   default Order ArrayOrder$mFc$sp(final Order evidence$21) {
      return new ArrayOrder$mcF$sp(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mIc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mIc$sp(evidence$21);
   }

   default Order ArrayOrder$mIc$sp(final Order evidence$21) {
      return new ArrayOrder$mcI$sp(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mJc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mJc$sp(evidence$21);
   }

   default Order ArrayOrder$mJc$sp(final Order evidence$21) {
      return new ArrayOrder$mcJ$sp(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mSc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mSc$sp(evidence$21);
   }

   default Order ArrayOrder$mSc$sp(final Order evidence$21) {
      return new ArrayOrder(evidence$21);
   }

   // $FF: synthetic method
   static Order ArrayOrder$mVc$sp$(final ArrayInstances2 $this, final Order evidence$21) {
      return $this.ArrayOrder$mVc$sp(evidence$21);
   }

   default Order ArrayOrder$mVc$sp(final Order evidence$21) {
      return new ArrayOrder(evidence$21);
   }

   static void $init$(final ArrayInstances2 $this) {
   }
}
