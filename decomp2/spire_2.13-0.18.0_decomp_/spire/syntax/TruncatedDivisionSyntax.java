package spire.syntax;

import algebra.ring.TruncatedDivision;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003C\u0001\u0011\r1\tC\u0003M\u0001\u0011\rQ\nC\u0003V\u0001\u0011\raKA\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c\u0016P\u001c;bq*\u0011\u0001\"C\u0001\u0007gftG/\u0019=\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u001bM\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007C\u0001\u000b\u0016\u001b\u00059\u0011B\u0001\f\b\u00051\u0019\u0016n\u001a8fINKh\u000e^1y\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0004\u0005\u0002\u000f5%\u00111d\u0004\u0002\u0005+:LG/\u0001\u000bueVt7-\u0019;fI\u0012Kg/[:j_:|\u0005o]\u000b\u0003=\u0015\"\"a\b!\u0015\u0005\u0001r\u0003c\u0001\u000b\"G%\u0011!e\u0002\u0002\u0015)J,hnY1uK\u0012$\u0015N^5tS>tw\n]:\u0011\u0005\u0011*C\u0002\u0001\u0003\u0006M\t\u0011\ra\n\u0002\u0002\u0003F\u0011\u0001f\u000b\t\u0003\u001d%J!AK\b\u0003\u000f9{G\u000f[5oOB\u0011a\u0002L\u0005\u0003[=\u00111!\u00118z\u0011\u001dy#!!AA\u0004A\n!\"\u001a<jI\u0016t7-\u001a\u00136!\r\tTh\t\b\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005YZ\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\tI\u0014\"A\u0004bY\u001e,'M]1\n\u0005mb\u0014a\u00029bG.\fw-\u001a\u0006\u0003s%I!AP \u0003#Q\u0013XO\\2bi\u0016$G)\u001b<jg&|gN\u0003\u0002<y!)\u0011I\u0001a\u0001G\u0005\t\u0011-\u0001\u0010mSR,'/\u00197J]R$&/\u001e8dCR,G\rR5wSNLwN\\(qgR\u0011Ai\u0012\t\u0003)\u0015K!AR\u0004\u0003=1KG/\u001a:bY&sG\u000f\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u001fB\u001c\b\"\u0002%\u0004\u0001\u0004I\u0015a\u00017igB\u0011aBS\u0005\u0003\u0017>\u00111!\u00138u\u0003}a\u0017\u000e^3sC2duN\\4UeVt7-\u0019;fI\u0012Kg/[:j_:|\u0005o\u001d\u000b\u0003\u001dF\u0003\"\u0001F(\n\u0005A;!a\b'ji\u0016\u0014\u0018\r\u001c'p]\u001e$&/\u001e8dCR,G\rR5wSNLwN\\(qg\")\u0001\n\u0002a\u0001%B\u0011abU\u0005\u0003)>\u0011A\u0001T8oO\u0006\tC.\u001b;fe\u0006dGi\\;cY\u0016$&/\u001e8dCR,G\rR5wSNLwN\\(qgR\u0011qK\u0017\t\u0003)aK!!W\u0004\u0003C1KG/\u001a:bY\u0012{WO\u00197f)J,hnY1uK\u0012$\u0015N^5tS>tw\n]:\t\u000b!+\u0001\u0019A.\u0011\u00059a\u0016BA/\u0010\u0005\u0019!u.\u001e2mK\u0002"
)
public interface TruncatedDivisionSyntax extends SignedSyntax {
   // $FF: synthetic method
   static TruncatedDivisionOps truncatedDivisionOps$(final TruncatedDivisionSyntax $this, final Object a, final TruncatedDivision evidence$5) {
      return $this.truncatedDivisionOps(a, evidence$5);
   }

   default TruncatedDivisionOps truncatedDivisionOps(final Object a, final TruncatedDivision evidence$5) {
      return new TruncatedDivisionOps(a, evidence$5);
   }

   // $FF: synthetic method
   static int literalIntTruncatedDivisionOps$(final TruncatedDivisionSyntax $this, final int lhs) {
      return $this.literalIntTruncatedDivisionOps(lhs);
   }

   default int literalIntTruncatedDivisionOps(final int lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static long literalLongTruncatedDivisionOps$(final TruncatedDivisionSyntax $this, final long lhs) {
      return $this.literalLongTruncatedDivisionOps(lhs);
   }

   default long literalLongTruncatedDivisionOps(final long lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static double literalDoubleTruncatedDivisionOps$(final TruncatedDivisionSyntax $this, final double lhs) {
      return $this.literalDoubleTruncatedDivisionOps(lhs);
   }

   default double literalDoubleTruncatedDivisionOps(final double lhs) {
      return lhs;
   }

   static void $init$(final TruncatedDivisionSyntax $this) {
   }
}
