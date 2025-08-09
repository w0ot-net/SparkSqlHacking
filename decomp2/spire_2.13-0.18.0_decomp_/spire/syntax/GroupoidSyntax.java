package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.NotGiven;
import spire.algebra.partial.Groupoid;

@ScalaSignature(
   bytes = "\u0006\u0005a3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003M\u0001\u0011\rQJ\u0001\bHe>,\bo\\5e'ftG/\u0019=\u000b\u0005\u00199\u0011AB:z]R\f\u0007PC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001A\u0006\u0012!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0011!cE\u0007\u0002\u000b%\u0011A#\u0002\u0002\u0013'\u0016l\u0017n\u001a:pkB|\u0017\u000eZ*z]R\f\u00070\u0001\u0004%S:LG\u000f\n\u000b\u0002/A\u0011A\u0002G\u0005\u000335\u0011A!\u00168ji\u0006\trM]8va>LGmQ8n[>tw\n]:\u0016\u0005q\u0019CCA\u000fK)\rqBF\u000e\t\u0004%}\t\u0013B\u0001\u0011\u0006\u0005E9%o\\;q_&$7i\\7n_:|\u0005o\u001d\t\u0003E\rb\u0001\u0001B\u0003%\u0005\t\u0007QEA\u0001B#\t1\u0013\u0006\u0005\u0002\rO%\u0011\u0001&\u0004\u0002\b\u001d>$\b.\u001b8h!\ta!&\u0003\u0002,\u001b\t\u0019\u0011I\\=\t\u000b5\u0012\u00019\u0001\u0018\u0002\u0005\u00154\bcA\u00185C5\t\u0001G\u0003\u00022e\u00059\u0001/\u0019:uS\u0006d'BA\u001a\b\u0003\u001d\tGnZ3ce\u0006L!!\u000e\u0019\u0003\u0011\u001d\u0013x.\u001e9pS\u0012DQa\u000e\u0002A\u0004a\n!A\\5\u0011\u0007eRD(D\u0001\b\u0013\tYtA\u0001\u0005O_R<\u0015N^3o!\rit)\t\b\u0003}\u0015s!a\u0010#\u000f\u0005\u0001\u001bU\"A!\u000b\u0005\tK\u0011A\u0002\u001fs_>$h(C\u0001\t\u0013\t\u0019t!\u0003\u0002Ge\u00059\u0001/Y2lC\u001e,\u0017B\u0001%J\u0005\u0019iuN\\8jI*\u0011aI\r\u0005\u0006\u0017\n\u0001\r!I\u0001\u0002C\u0006YqM]8va>LGm\u00149t+\tqE\u000b\u0006\u0002P/R\u0011\u0001+\u0016\t\u0004%E\u001b\u0016B\u0001*\u0006\u0005-9%o\\;q_&$w\n]:\u0011\u0005\t\"F!\u0002\u0013\u0004\u0005\u0004)\u0003\"B\u0017\u0004\u0001\b1\u0006cA\u00185'\")1j\u0001a\u0001'\u0002"
)
public interface GroupoidSyntax extends SemigroupoidSyntax {
   // $FF: synthetic method
   static GroupoidCommonOps groupoidCommonOps$(final GroupoidSyntax $this, final Object a, final Groupoid ev, final NotGiven ni) {
      return $this.groupoidCommonOps(a, ev, ni);
   }

   default GroupoidCommonOps groupoidCommonOps(final Object a, final Groupoid ev, final NotGiven ni) {
      return new GroupoidCommonOps(a, ev);
   }

   // $FF: synthetic method
   static GroupoidOps groupoidOps$(final GroupoidSyntax $this, final Object a, final Groupoid ev) {
      return $this.groupoidOps(a, ev);
   }

   default GroupoidOps groupoidOps(final Object a, final Groupoid ev) {
      return new GroupoidOps(a, ev);
   }

   static void $init$(final GroupoidSyntax $this) {
   }
}
