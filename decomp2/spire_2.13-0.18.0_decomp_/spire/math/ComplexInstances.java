package spire.math;

import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005-4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003^\u0001\u0011\raL\u0001\tD_6\u0004H.\u001a=J]N$\u0018M\\2fg*\u0011aaB\u0001\u0005[\u0006$\bNC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001A\u0006\u0012!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0011!cE\u0007\u0002\u000b%\u0011A#\u0002\u0002\u0012\u0007>l\u0007\u000f\\3y\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$H\u0005F\u0001\u0018!\ta\u0001$\u0003\u0002\u001a\u001b\t!QK\\5u\u00035\u0019u.\u001c9mKb|e\u000e\u0016:jOV\u0011AD\t\u000b\u0006;y\u001a%\u000b\u0017\t\u0004%y\u0001\u0013BA\u0010\u0006\u0005E\u0019u.\u001c9mKb|e\u000e\u0016:jO&k\u0007\u000f\u001c\t\u0003C\tb\u0001\u0001B\u0005$\u0005\u0001\u0006\t\u0011!b\u0001I\t\t\u0011)\u0005\u0002&QA\u0011ABJ\u0005\u0003O5\u0011qAT8uQ&tw\r\u0005\u0002\rS%\u0011!&\u0004\u0002\u0004\u0003:L\b\u0006\u0002\u0012-_e\u0002\"\u0001D\u0017\n\u00059j!aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u00192gIr!\u0001D\u0019\n\u0005Ij\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u00135q9q!!\u000e\u001d\u000e\u0003YR!aN\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011'B\u0012;wubdB\u0001\u0007<\u0013\taT\"\u0001\u0004E_V\u0014G.Z\u0019\u0005IQBd\u0002C\u0004@\u0005\u0005\u0005\t9\u0001!\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\bE\u0002\u0013\u0003\u0002J!AQ\u0003\u0003\u0015\u0019\u0013\u0018m\u0019;j_:\fG\u000eC\u0004E\u0005\u0005\u0005\t9A#\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0004\r>\u0003cBA$M\u001d\tA%J\u0004\u00026\u0013&\t\u0001\"\u0003\u0002L\u000f\u00059\u0011\r\\4fEJ\f\u0017BA'O\u0003\u001d\u0001\u0018mY6bO\u0016T!aS\u0004\n\u0005A\u000b&!B(sI\u0016\u0014(BA'O\u0011\u001d\u0019&!!AA\u0004Q\u000b1\"\u001a<jI\u0016t7-\u001a\u00132cA\u0019QK\u0016\u0011\u000e\u00039K!a\u0016(\u0003\tQ\u0013\u0018n\u001a\u0005\b3\n\t\t\u0011q\u0001[\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\u0007\u0019[\u0006%\u0003\u0002]#\n11+[4oK\u0012\f\u0011bQ8na2,\u00070R9\u0016\u0005};GC\u00011i!\r1\u0015mY\u0005\u0003EF\u0013!!R9\u0011\u0007I!g-\u0003\u0002f\u000b\t91i\\7qY\u0016D\bCA\u0011h\t\u0015\u00193A1\u0001%\u0011\u001dI7!!AA\u0004)\f1\"\u001a<jI\u0016t7-\u001a\u00132gA\u0019a)\u00194"
)
public interface ComplexInstances extends ComplexInstances1 {
   // $FF: synthetic method
   static ComplexOnTrigImpl ComplexOnTrig$(final ComplexInstances $this, final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return $this.ComplexOnTrig(evidence$9, evidence$10, evidence$11, evidence$12);
   }

   default ComplexOnTrigImpl ComplexOnTrig(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return new ComplexOnTrigImpl(evidence$9, evidence$9, evidence$9, evidence$11, evidence$9);
   }

   // $FF: synthetic method
   static Eq ComplexEq$(final ComplexInstances $this, final Eq evidence$13) {
      return $this.ComplexEq(evidence$13);
   }

   default Eq ComplexEq(final Eq evidence$13) {
      return new ComplexEq(evidence$13);
   }

   // $FF: synthetic method
   static ComplexOnTrigImpl ComplexOnTrig$mDc$sp$(final ComplexInstances $this, final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return $this.ComplexOnTrig$mDc$sp(evidence$9, evidence$10, evidence$11, evidence$12);
   }

   default ComplexOnTrigImpl ComplexOnTrig$mDc$sp(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return new ComplexOnTrigImpl$mcD$sp(evidence$9, evidence$9, evidence$9, evidence$11, evidence$9);
   }

   // $FF: synthetic method
   static ComplexOnTrigImpl ComplexOnTrig$mFc$sp$(final ComplexInstances $this, final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return $this.ComplexOnTrig$mFc$sp(evidence$9, evidence$10, evidence$11, evidence$12);
   }

   default ComplexOnTrigImpl ComplexOnTrig$mFc$sp(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return new ComplexOnTrigImpl$mcF$sp(evidence$9, evidence$9, evidence$9, evidence$11, evidence$9);
   }

   static void $init$(final ComplexInstances $this) {
   }
}
