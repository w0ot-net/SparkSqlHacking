package spire.syntax;

import algebra.ring.MultiplicativeGroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003C\u0001\u0011\r1\tC\u0003M\u0001\u0011\rQ\nC\u0003V\u0001\u0011\raKA\rNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3He>,\boU=oi\u0006D(B\u0001\u0005\n\u0003\u0019\u0019\u0018P\u001c;bq*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011aB\u0005\u0003-\u001d\u0011!$T;mi&\u0004H.[2bi&4X-T8o_&$7+\u001f8uCb\fa\u0001J5oSR$C#A\r\u0011\u00059Q\u0012BA\u000e\u0010\u0005\u0011)f.\u001b;\u0002-5,H\u000e^5qY&\u001c\u0017\r^5wK\u001e\u0013x.\u001e9PaN,\"AH\u0013\u0015\u0005}\u0001EC\u0001\u0011/!\r!\u0012eI\u0005\u0003E\u001d\u0011a#T;mi&\u0004H.[2bi&4Xm\u0012:pkB|\u0005o\u001d\t\u0003I\u0015b\u0001\u0001B\u0003'\u0005\t\u0007qEA\u0001B#\tA3\u0006\u0005\u0002\u000fS%\u0011!f\u0004\u0002\b\u001d>$\b.\u001b8h!\tqA&\u0003\u0002.\u001f\t\u0019\u0011I\\=\t\u000f=\u0012\u0011\u0011!a\u0002a\u0005YQM^5eK:\u001cW\rJ\u00195!\r\tTh\t\b\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005YZ\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\tI\u0014\"A\u0004bY\u001e,'M]1\n\u0005mb\u0014a\u00029bG.\fw-\u001a\u0006\u0003s%I!AP \u0003'5+H\u000e^5qY&\u001c\u0017\r^5wK\u001e\u0013x.\u001e9\u000b\u0005mb\u0004\"B!\u0003\u0001\u0004\u0019\u0013!A1\u0002A1LG/\u001a:bY&sG/T;mi&\u0004H.[2bi&4Xm\u0012:pkB|\u0005o\u001d\u000b\u0003\t\u001e\u0003\"\u0001F#\n\u0005\u0019;!\u0001\t'ji\u0016\u0014\u0018\r\\%oi6+H\u000e^5qY&\u001c\u0017\r^5wK\u001e\u0013x.\u001e9PaNDQ\u0001S\u0002A\u0002%\u000b1\u0001\u001c5t!\tq!*\u0003\u0002L\u001f\t\u0019\u0011J\u001c;\u0002C1LG/\u001a:bY2{gnZ'vYRL\u0007\u000f\\5dCRLg/Z$s_V\u0004x\n]:\u0015\u00059\u000b\u0006C\u0001\u000bP\u0013\t\u0001vAA\u0011MSR,'/\u00197M_:<W*\u001e7uSBd\u0017nY1uSZ,wI]8va>\u00038\u000fC\u0003I\t\u0001\u0007!\u000b\u0005\u0002\u000f'&\u0011Ak\u0004\u0002\u0005\u0019>tw-A\u0012mSR,'/\u00197E_V\u0014G.Z'vYRL\u0007\u000f\\5dCRLg/Z$s_V\u0004x\n]:\u0015\u0005]S\u0006C\u0001\u000bY\u0013\tIvAA\u0012MSR,'/\u00197E_V\u0014G.Z'vYRL\u0007\u000f\\5dCRLg/Z$s_V\u0004x\n]:\t\u000b!+\u0001\u0019A.\u0011\u00059a\u0016BA/\u0010\u0005\u0019!u.\u001e2mK\u0002"
)
public interface MultiplicativeGroupSyntax extends MultiplicativeMonoidSyntax {
   // $FF: synthetic method
   static MultiplicativeGroupOps multiplicativeGroupOps$(final MultiplicativeGroupSyntax $this, final Object a, final MultiplicativeGroup evidence$14) {
      return $this.multiplicativeGroupOps(a, evidence$14);
   }

   default MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
      return new MultiplicativeGroupOps(a, evidence$14);
   }

   // $FF: synthetic method
   static int literalIntMultiplicativeGroupOps$(final MultiplicativeGroupSyntax $this, final int lhs) {
      return $this.literalIntMultiplicativeGroupOps(lhs);
   }

   default int literalIntMultiplicativeGroupOps(final int lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static long literalLongMultiplicativeGroupOps$(final MultiplicativeGroupSyntax $this, final long lhs) {
      return $this.literalLongMultiplicativeGroupOps(lhs);
   }

   default long literalLongMultiplicativeGroupOps(final long lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static double literalDoubleMultiplicativeGroupOps$(final MultiplicativeGroupSyntax $this, final double lhs) {
      return $this.literalDoubleMultiplicativeGroupOps(lhs);
   }

   default double literalDoubleMultiplicativeGroupOps(final double lhs) {
      return lhs;
   }

   static void $init$(final MultiplicativeGroupSyntax $this) {
   }
}
