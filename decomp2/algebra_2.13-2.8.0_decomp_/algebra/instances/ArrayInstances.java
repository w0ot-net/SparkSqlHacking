package algebra.instances;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001\u0004C\u00038\u0001\u0011\r\u0001\bC\u0003E\u0001\u0011\rQI\u0001\bBeJ\f\u00170\u00138ti\u0006t7-Z:\u000b\u0005\u001dA\u0011!C5ogR\fgnY3t\u0015\u0005I\u0011aB1mO\u0016\u0014'/Y\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0001\"!D\u000b\n\u0005Yq!\u0001B+oSR\fq!\u0019:sCf,\u0015/\u0006\u0002\u001aOQ\u0011!\u0004\u000e\t\u00047}\u0011cB\u0001\u000f\u001e\u001b\u0005A\u0011B\u0001\u0010\t\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001I\u0011\u0003\u0005\u0015\u000b(B\u0001\u0010\t!\ri1%J\u0005\u0003I9\u0011Q!\u0011:sCf\u0004\"AJ\u0014\r\u0001\u0011I\u0001F\u0001Q\u0001\u0002\u0003\u0015\r!\u000b\u0002\u0002\u0003F\u0011!&\f\t\u0003\u001b-J!\u0001\f\b\u0003\u000f9{G\u000f[5oOB\u0011QBL\u0005\u0003_9\u00111!\u00118zQ\t9\u0013\u0007\u0005\u0002\u000ee%\u00111G\u0004\u0002\fgB,7-[1mSj,G\rC\u00046\u0005\u0005\u0005\t9\u0001\u001c\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002\u001c?\u0015\n!\"\u0019:sCf|%\u000fZ3s+\tIt\b\u0006\u0002;\u0003B\u00191dO\u001f\n\u0005q\n#!B(sI\u0016\u0014\bcA\u0007$}A\u0011ae\u0010\u0003\nQ\r\u0001\u000b\u0011!AC\u0002%B#aP\u0019\t\u000f\t\u001b\u0011\u0011!a\u0002\u0007\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007mYd(A\tbeJ\f\u0017\u0010U1si&\fGn\u0014:eKJ,\"A\u0012'\u0015\u0005\u001ds\u0005cA\u000eI\u0015&\u0011\u0011*\t\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d\t\u0004\u001b\rZ\u0005C\u0001\u0014M\t%AC\u0001)A\u0001\u0002\u000b\u0007\u0011\u0006\u000b\u0002Mc!9q\nBA\u0001\u0002\b\u0001\u0016AC3wS\u0012,gnY3%gA\u00191\u0004S&"
)
public interface ArrayInstances {
   // $FF: synthetic method
   static Eq arrayEq$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq(evidence$1);
   }

   default Eq arrayEq(final Eq evidence$1) {
      return new ArrayEq(evidence$1);
   }

   // $FF: synthetic method
   static Order arrayOrder$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder(evidence$2);
   }

   default Order arrayOrder(final Order evidence$2) {
      return new ArrayOrder(evidence$2);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder(evidence$3);
   }

   default PartialOrder arrayPartialOrder(final PartialOrder evidence$3) {
      return new ArrayPartialOrder(evidence$3);
   }

   // $FF: synthetic method
   static Eq arrayEq$mZc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mZc$sp(evidence$1);
   }

   default Eq arrayEq$mZc$sp(final Eq evidence$1) {
      return new ArrayEq$mcZ$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mBc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mBc$sp(evidence$1);
   }

   default Eq arrayEq$mBc$sp(final Eq evidence$1) {
      return new ArrayEq$mcB$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mCc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mCc$sp(evidence$1);
   }

   default Eq arrayEq$mCc$sp(final Eq evidence$1) {
      return new ArrayEq$mcC$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mDc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mDc$sp(evidence$1);
   }

   default Eq arrayEq$mDc$sp(final Eq evidence$1) {
      return new ArrayEq$mcD$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mFc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mFc$sp(evidence$1);
   }

   default Eq arrayEq$mFc$sp(final Eq evidence$1) {
      return new ArrayEq$mcF$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mIc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mIc$sp(evidence$1);
   }

   default Eq arrayEq$mIc$sp(final Eq evidence$1) {
      return new ArrayEq$mcI$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mJc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mJc$sp(evidence$1);
   }

   default Eq arrayEq$mJc$sp(final Eq evidence$1) {
      return new ArrayEq$mcJ$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mSc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mSc$sp(evidence$1);
   }

   default Eq arrayEq$mSc$sp(final Eq evidence$1) {
      return new ArrayEq$mcS$sp(evidence$1);
   }

   // $FF: synthetic method
   static Eq arrayEq$mVc$sp$(final ArrayInstances $this, final Eq evidence$1) {
      return $this.arrayEq$mVc$sp(evidence$1);
   }

   default Eq arrayEq$mVc$sp(final Eq evidence$1) {
      return new ArrayEq$mcV$sp(evidence$1);
   }

   // $FF: synthetic method
   static Order arrayOrder$mZc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mZc$sp(evidence$2);
   }

   default Order arrayOrder$mZc$sp(final Order evidence$2) {
      return new ArrayOrder$mcZ$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mBc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mBc$sp(evidence$2);
   }

   default Order arrayOrder$mBc$sp(final Order evidence$2) {
      return new ArrayOrder$mcB$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mCc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mCc$sp(evidence$2);
   }

   default Order arrayOrder$mCc$sp(final Order evidence$2) {
      return new ArrayOrder$mcC$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mDc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mDc$sp(evidence$2);
   }

   default Order arrayOrder$mDc$sp(final Order evidence$2) {
      return new ArrayOrder$mcD$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mFc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mFc$sp(evidence$2);
   }

   default Order arrayOrder$mFc$sp(final Order evidence$2) {
      return new ArrayOrder$mcF$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mIc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mIc$sp(evidence$2);
   }

   default Order arrayOrder$mIc$sp(final Order evidence$2) {
      return new ArrayOrder$mcI$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mJc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mJc$sp(evidence$2);
   }

   default Order arrayOrder$mJc$sp(final Order evidence$2) {
      return new ArrayOrder$mcJ$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mSc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mSc$sp(evidence$2);
   }

   default Order arrayOrder$mSc$sp(final Order evidence$2) {
      return new ArrayOrder$mcS$sp(evidence$2);
   }

   // $FF: synthetic method
   static Order arrayOrder$mVc$sp$(final ArrayInstances $this, final Order evidence$2) {
      return $this.arrayOrder$mVc$sp(evidence$2);
   }

   default Order arrayOrder$mVc$sp(final Order evidence$2) {
      return new ArrayOrder$mcV$sp(evidence$2);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mZc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mZc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mZc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcZ$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mBc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mBc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mBc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcB$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mCc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mCc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mCc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcC$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mDc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mDc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mDc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcD$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mFc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mFc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mFc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcF$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mIc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mIc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mIc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcI$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mJc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mJc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mJc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcJ$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mSc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mSc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mSc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcS$sp(evidence$3);
   }

   // $FF: synthetic method
   static PartialOrder arrayPartialOrder$mVc$sp$(final ArrayInstances $this, final PartialOrder evidence$3) {
      return $this.arrayPartialOrder$mVc$sp(evidence$3);
   }

   default PartialOrder arrayPartialOrder$mVc$sp(final PartialOrder evidence$3) {
      return new ArrayPartialOrder$mcV$sp(evidence$3);
   }

   static void $init$(final ArrayInstances $this) {
   }
}
