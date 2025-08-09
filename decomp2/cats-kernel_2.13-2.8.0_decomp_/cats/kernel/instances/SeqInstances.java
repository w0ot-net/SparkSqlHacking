package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003:\u0001\u0011\r!H\u0001\u0007TKFLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q)R\"A\u0003\n\u0005Y)!!D*fc&s7\u000f^1oG\u0016\u001c\u0018'\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011aBG\u0005\u00037=\u0011A!\u00168ji\u0006A2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012|%\u000fZ3s\r>\u00148+Z9\u0016\u0005yiCCA\u00107!\r\u0001\u0013eI\u0007\u0002\u000f%\u0011!e\u0002\u0002\u0006\u001fJ$WM\u001d\t\u0004I%ZS\"A\u0013\u000b\u0005\u0019:\u0013!C5n[V$\u0018M\u00197f\u0015\tAs\"\u0001\u0006d_2dWm\u0019;j_:L!AK\u0013\u0003\u0007M+\u0017\u000f\u0005\u0002-[1\u0001A!\u0002\u0018\u0003\u0005\u0004y#!A!\u0012\u0005A\u001a\u0004C\u0001\b2\u0013\t\u0011tBA\u0004O_RD\u0017N\\4\u0011\u00059!\u0014BA\u001b\u0010\u0005\r\te.\u001f\u0005\bo\t\t\t\u0011q\u00019\u0003))g/\u001b3f]\u000e,G%\r\t\u0004A\u0005Z\u0013!G2biN\\UM\u001d8fYN#H-T8o_&$gi\u001c:TKF,\"aO!\u0016\u0003q\u00022\u0001I\u001f@\u0013\tqtA\u0001\u0004N_:|\u0017\u000e\u001a\t\u0004I%\u0002\u0005C\u0001\u0017B\t\u0015q3A1\u00010Q\t\u00011\t\u0005\u0002E\u0017:\u0011Q\t\u0013\b\u0003A\u0019K!aR\u0004\u0002\r\r|W\u000e]1u\u0013\tI%*\u0001\u000btG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0003\u000f\u001eI!\u0001T'\u0003eM,\b\u000f\u001d:fgN,f.^:fI&k\u0007o\u001c:u/\u0006\u0014h.\u001b8h\r>\u00148kY1mCZ+'o]5p]N\u0003XmY5gS\u000eT!!\u0013&"
)
public interface SeqInstances extends SeqInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForSeq$(final SeqInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForSeq(evidence$1);
   }

   default Order catsKernelStdOrderForSeq(final Order evidence$1) {
      return new SeqOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForSeq$(final SeqInstances $this) {
      return $this.catsKernelStdMonoidForSeq();
   }

   default Monoid catsKernelStdMonoidForSeq() {
      return new SeqMonoid();
   }

   static void $init$(final SeqInstances $this) {
   }
}
