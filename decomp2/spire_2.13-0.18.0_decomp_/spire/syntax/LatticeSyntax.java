package spire.syntax;

import algebra.lattice.JoinSemilattice;
import algebra.lattice.MeetSemilattice;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003B\u0001\u0011\r!IA\u0007MCR$\u0018nY3Ts:$\u0018\r\u001f\u0006\u0003\r\u001d\taa]=oi\u0006D(\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0002C\u0001\u0007\u0015\u0013\t)RB\u0001\u0003V]&$\u0018aB7fKR|\u0005o]\u000b\u00031\u0001\"\"!G \u0015\u0005iI\u0003cA\u000e\u001d=5\tQ!\u0003\u0002\u001e\u000b\t9Q*Z3u\u001fB\u001c\bCA\u0010!\u0019\u0001!Q!\t\u0002C\u0002\t\u0012\u0011!Q\t\u0003G\u0019\u0002\"\u0001\u0004\u0013\n\u0005\u0015j!a\u0002(pi\"Lgn\u001a\t\u0003\u0019\u001dJ!\u0001K\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004+\u0005\u0005\u0005\t9A\u0016\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\r\t\u0004YqrbBA\u0017:\u001d\tqcG\u0004\u00020i9\u0011\u0001gM\u0007\u0002c)\u0011!'C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!!N\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0007O\u0001\bY\u0006$H/[2f\u0015\t)t!\u0003\u0002;w\u00059\u0001/Y2lC\u001e,'BA\u001c9\u0013\tidHA\bNK\u0016$8+Z7jY\u0006$H/[2f\u0015\tQ4\bC\u0003A\u0005\u0001\u0007a$A\u0001b\u0003\u001dQw.\u001b8PaN,\"aQ%\u0015\u0005\u0011{ECA#K!\rYb\tS\u0005\u0003\u000f\u0016\u0011qAS8j]>\u00038\u000f\u0005\u0002 \u0013\u0012)\u0011e\u0001b\u0001E!91jAA\u0001\u0002\ba\u0015aC3wS\u0012,gnY3%eE\u00022\u0001L'I\u0013\tqeHA\bK_&t7+Z7jY\u0006$H/[2f\u0011\u0015\u00015\u00011\u0001I\u0001"
)
public interface LatticeSyntax {
   // $FF: synthetic method
   static MeetOps meetOps$(final LatticeSyntax $this, final Object a, final MeetSemilattice evidence$20) {
      return $this.meetOps(a, evidence$20);
   }

   default MeetOps meetOps(final Object a, final MeetSemilattice evidence$20) {
      return new MeetOps(a, evidence$20);
   }

   // $FF: synthetic method
   static JoinOps joinOps$(final LatticeSyntax $this, final Object a, final JoinSemilattice evidence$21) {
      return $this.joinOps(a, evidence$21);
   }

   default JoinOps joinOps(final Object a, final JoinSemilattice evidence$21) {
      return new JoinOps(a, evidence$21);
   }

   static void $init$(final LatticeSyntax $this) {
   }
}
