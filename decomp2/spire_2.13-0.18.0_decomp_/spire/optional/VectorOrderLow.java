package spire.optional;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.std.ArrayVectorEq;
import spire.std.ArrayVectorEq$mcD$sp;
import spire.std.ArrayVectorEq$mcF$sp;
import spire.std.ArrayVectorEq$mcI$sp;
import spire.std.ArrayVectorEq$mcJ$sp;
import spire.std.MapVectorEq;
import spire.std.SeqVectorEq;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=baB\u0003\u0007!\u0003\r\ta\u0003\u0005\u0006%\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0005\u0006)\u0002!\u0019!\u0016\u0005\u0006}\u0002!\u0019a \u0002\u000f-\u0016\u001cGo\u001c:Pe\u0012,'\u000fT8x\u0015\t9\u0001\"\u0001\u0005paRLwN\\1m\u0015\u0005I\u0011!B:qSJ,7\u0001A\n\u0003\u00011\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0015!\tiQ#\u0003\u0002\u0017\u001d\t!QK\\5u\u0003\u0015\u0019X-]#r+\rI\"\u0005\f\u000b\u00045qr\u0005\u0003B\u000e\u001fA-j\u0011\u0001\b\u0006\u0003;!\t1a\u001d;e\u0013\tyBDA\u0006TKF4Vm\u0019;pe\u0016\u000b\bCA\u0011#\u0019\u0001!Qa\t\u0002C\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"!\u0004\u0014\n\u0005\u001dr!a\u0002(pi\"Lgn\u001a\t\u0003\u001b%J!A\u000b\b\u0003\u0007\u0005s\u0017\u0010E\u0002\"Y\u0001\"Q!\f\u0002C\u00029\u0012!aQ\"\u0016\u0005=:\u0014CA\u00131!\u0015\tDG\u000e\u001d<\u001b\u0005\u0011$BA\u001a\u000f\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003kI\u0012aaU3r\u001fB\u001c\bCA\u00118\t\u0015\u0019CF1\u0001%!\t\t\u0014(\u0003\u0002;e\t\u00191+Z9\u0011\u0007\u0005bc\u0007C\u0003>\u0005\u0001\u000fa(\u0001\u0002BaA\u0019qh\u0013\u0011\u000f\u0005\u0001CeBA!G\u001d\t\u0011U)D\u0001D\u0015\t!%\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011q\tC\u0001\bC2<WM\u0019:b\u0013\tI%*A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u001dC\u0011B\u0001'N\u0005\t)\u0015O\u0003\u0002J\u0015\")qJ\u0001a\u0002!\u00061Qn\u001c3vY\u0016\u0004B!\u0015*,A5\t!*\u0003\u0002T\u0015\n91)T8ek2,\u0017aB1se\u0006LX)]\u000b\u0003-n#2a\u0016<z!\rY\u0002LW\u0005\u00033r\u0011Q\"\u0011:sCf4Vm\u0019;pe\u0016\u000b\bCA\u0011\\\t%\u00193\u0001)A\u0001\u0002\u000b\u0007A\u0005\u000b\u0004\\;\u0002<G.\u001d\t\u0003\u001byK!a\u0018\b\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\u0005\u0014Gm\u0019\b\u0003\u001b\tL!a\u0019\b\u0002\u0007%sG/\r\u0003%K\u001a|aB\u0001\"g\u0013\u0005y\u0011'B\u0012iS.TgBA\u0007j\u0013\tQg\"\u0001\u0003M_:<\u0017\u0007\u0002\u0013fM>\tTaI7oa>t!!\u00048\n\u0005=t\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013fM>\tTa\t:tkRt!!D:\n\u0005Qt\u0011A\u0002#pk\ndW-\r\u0003%K\u001a|\u0001\"B<\u0004\u0001\bA\u0018AA3w!\ry4J\u0017\u0005\u0006\u001f\u000e\u0001\u001dA\u001f\t\u0005#J[(\fE\u0002\u000eyjK!! \b\u0003\u000b\u0005\u0013(/Y=\u0002\u000b5\f\u0007/R9\u0016\r\u0005\u0005\u00111BA\t)\u0019\t\u0019!!\u0006\u0002\u001cA91$!\u0002\u0002\n\u0005=\u0011bAA\u00049\tYQ*\u00199WK\u000e$xN]#r!\r\t\u00131\u0002\u0003\u0007\u0003\u001b!!\u0019\u0001\u0013\u0003\u0003-\u00032!IA\t\t\u0019\t\u0019\u0002\u0002b\u0001I\t\ta\u000bC\u0004\u0002\u0018\u0011\u0001\u001d!!\u0007\u0002\u0005Y\u0003\u0004\u0003B L\u0003\u001fAaa\u0014\u0003A\u0004\u0005u\u0001CB)S\u0003?\ty\u0001\u0005\u0005\u0002\"\u0005%\u0012\u0011BA\b\u001d\u0011\t\u0019#!\n\u0011\u0005\ts\u0011bAA\u0014\u001d\u00051\u0001K]3eK\u001aLA!a\u000b\u0002.\t\u0019Q*\u00199\u000b\u0007\u0005\u001db\u0002"
)
public interface VectorOrderLow {
   // $FF: synthetic method
   static SeqVectorEq seqEq$(final VectorOrderLow $this, final Eq A0, final CModule module) {
      return $this.seqEq(A0, module);
   }

   default SeqVectorEq seqEq(final Eq A0, final CModule module) {
      return new SeqVectorEq(A0, module.scalar());
   }

   // $FF: synthetic method
   static ArrayVectorEq arrayEq$(final VectorOrderLow $this, final Eq ev, final CModule module) {
      return $this.arrayEq(ev, module);
   }

   default ArrayVectorEq arrayEq(final Eq ev, final CModule module) {
      return new ArrayVectorEq(ev, module.scalar());
   }

   // $FF: synthetic method
   static MapVectorEq mapEq$(final VectorOrderLow $this, final Eq V0, final CModule module) {
      return $this.mapEq(V0, module);
   }

   default MapVectorEq mapEq(final Eq V0, final CModule module) {
      return new MapVectorEq(V0, module.scalar());
   }

   // $FF: synthetic method
   static ArrayVectorEq arrayEq$mDc$sp$(final VectorOrderLow $this, final Eq ev, final CModule module) {
      return $this.arrayEq$mDc$sp(ev, module);
   }

   default ArrayVectorEq arrayEq$mDc$sp(final Eq ev, final CModule module) {
      return new ArrayVectorEq$mcD$sp(ev, module.scalar$mcD$sp());
   }

   // $FF: synthetic method
   static ArrayVectorEq arrayEq$mFc$sp$(final VectorOrderLow $this, final Eq ev, final CModule module) {
      return $this.arrayEq$mFc$sp(ev, module);
   }

   default ArrayVectorEq arrayEq$mFc$sp(final Eq ev, final CModule module) {
      return new ArrayVectorEq$mcF$sp(ev, module.scalar$mcF$sp());
   }

   // $FF: synthetic method
   static ArrayVectorEq arrayEq$mIc$sp$(final VectorOrderLow $this, final Eq ev, final CModule module) {
      return $this.arrayEq$mIc$sp(ev, module);
   }

   default ArrayVectorEq arrayEq$mIc$sp(final Eq ev, final CModule module) {
      return new ArrayVectorEq$mcI$sp(ev, module.scalar$mcI$sp());
   }

   // $FF: synthetic method
   static ArrayVectorEq arrayEq$mJc$sp$(final VectorOrderLow $this, final Eq ev, final CModule module) {
      return $this.arrayEq$mJc$sp(ev, module);
   }

   default ArrayVectorEq arrayEq$mJc$sp(final Eq ev, final CModule module) {
      return new ArrayVectorEq$mcJ$sp(ev, module.scalar$mcJ$sp());
   }

   static void $init$(final VectorOrderLow $this) {
   }
}
