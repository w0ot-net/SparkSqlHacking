package spire.syntax;

import algebra.ring.AdditiveGroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003C\u0001\u0011\r1\tC\u0003M\u0001\u0011\rQ\nC\u0003V\u0001\u0011\raKA\nBI\u0012LG/\u001b<f\u000fJ|W\u000f]*z]R\f\u0007P\u0003\u0002\t\u0013\u000511/\u001f8uCbT\u0011AC\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q)R\"A\u0004\n\u0005Y9!\u0001F!eI&$\u0018N^3N_:|\u0017\u000eZ*z]R\f\u00070\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011aBG\u0005\u00037=\u0011A!\u00168ji\u0006\u0001\u0012\r\u001a3ji&4Xm\u0012:pkB|\u0005o]\u000b\u0003=\u0015\"\"a\b!\u0015\u0005\u0001r\u0003c\u0001\u000b\"G%\u0011!e\u0002\u0002\u0011\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9PaN\u0004\"\u0001J\u0013\r\u0001\u0011)aE\u0001b\u0001O\t\t\u0011)\u0005\u0002)WA\u0011a\"K\u0005\u0003U=\u0011qAT8uQ&tw\r\u0005\u0002\u000fY%\u0011Qf\u0004\u0002\u0004\u0003:L\bbB\u0018\u0003\u0003\u0003\u0005\u001d\u0001M\u0001\fKZLG-\u001a8dK\u0012\n$\u0007E\u00022{\rr!A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c\f\u0003\u0019a$o\\8u}%\t!\"\u0003\u0002:\u0013\u00059\u0011\r\\4fEJ\f\u0017BA\u001e=\u0003\u001d\u0001\u0018mY6bO\u0016T!!O\u0005\n\u0005yz$!D!eI&$\u0018N^3He>,\bO\u0003\u0002<y!)\u0011I\u0001a\u0001G\u0005\t\u0011-\u0001\u000emSR,'/\u00197J]R\fE\rZ5uSZ,wI]8va>\u00038\u000f\u0006\u0002E\u000fB\u0011A#R\u0005\u0003\r\u001e\u0011!\u0004T5uKJ\fG.\u00138u\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9PaNDQ\u0001S\u0002A\u0002%\u000b1\u0001\u001c5t!\tq!*\u0003\u0002L\u001f\t\u0019\u0011J\u001c;\u000271LG/\u001a:bY2{gnZ!eI&$\u0018N^3He>,\bo\u00149t)\tq\u0015\u000b\u0005\u0002\u0015\u001f&\u0011\u0001k\u0002\u0002\u001c\u0019&$XM]1m\u0019>tw-\u00113eSRLg/Z$s_V\u0004x\n]:\t\u000b!#\u0001\u0019\u0001*\u0011\u00059\u0019\u0016B\u0001+\u0010\u0005\u0011auN\\4\u0002;1LG/\u001a:bY\u0012{WO\u00197f\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9PaN$\"a\u0016.\u0011\u0005QA\u0016BA-\b\u0005ua\u0015\u000e^3sC2$u.\u001e2mK\u0006#G-\u001b;jm\u0016<%o\\;q\u001fB\u001c\b\"\u0002%\u0006\u0001\u0004Y\u0006C\u0001\b]\u0013\tivB\u0001\u0004E_V\u0014G.\u001a"
)
public interface AdditiveGroupSyntax extends AdditiveMonoidSyntax {
   // $FF: synthetic method
   static AdditiveGroupOps additiveGroupOps$(final AdditiveGroupSyntax $this, final Object a, final AdditiveGroup evidence$12) {
      return $this.additiveGroupOps(a, evidence$12);
   }

   default AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
      return new AdditiveGroupOps(a, evidence$12);
   }

   // $FF: synthetic method
   static int literalIntAdditiveGroupOps$(final AdditiveGroupSyntax $this, final int lhs) {
      return $this.literalIntAdditiveGroupOps(lhs);
   }

   default int literalIntAdditiveGroupOps(final int lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static long literalLongAdditiveGroupOps$(final AdditiveGroupSyntax $this, final long lhs) {
      return $this.literalLongAdditiveGroupOps(lhs);
   }

   default long literalLongAdditiveGroupOps(final long lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static double literalDoubleAdditiveGroupOps$(final AdditiveGroupSyntax $this, final double lhs) {
      return $this.literalDoubleAdditiveGroupOps(lhs);
   }

   default double literalDoubleAdditiveGroupOps(final double lhs) {
      return lhs;
   }

   static void $init$(final AdditiveGroupSyntax $this) {
   }
}
