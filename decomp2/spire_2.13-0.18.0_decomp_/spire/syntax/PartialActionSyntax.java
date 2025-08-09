package spire.syntax;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003+\u0001\u0011\r1FA\nQCJ$\u0018.\u00197BGRLwN\\*z]R\f\u0007P\u0003\u0002\u0007\u000f\u000511/\u001f8uCbT\u0011\u0001C\u0001\u0006gBL'/Z\u0002\u0001'\t\u00011\u0002\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0001\"\u0001\u0004\u000b\n\u0005Ui!\u0001B+oSR\fA\u0003\\3giB\u000b'\u000f^5bY\u0006\u001bG/[8o\u001fB\u001cXC\u0001\r )\tI\u0002\u0006E\u0002\u001b7ui\u0011!B\u0005\u00039\u0015\u0011A\u0003T3giB\u000b'\u000f^5bY\u0006\u001bG/[8o\u001fB\u001c\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0002C\u0002\u0005\u0012\u0011aR\t\u0003E\u0015\u0002\"\u0001D\u0012\n\u0005\u0011j!a\u0002(pi\"Lgn\u001a\t\u0003\u0019\u0019J!aJ\u0007\u0003\u0007\u0005s\u0017\u0010C\u0003*\u0005\u0001\u0007Q$A\u0001h\u0003U\u0011\u0018n\u001a5u!\u0006\u0014H/[1m\u0003\u000e$\u0018n\u001c8PaN,\"\u0001L\u0019\u0015\u00055\u001a\u0004c\u0001\u000e/a%\u0011q&\u0002\u0002\u0016%&<\u0007\u000e\u001e)beRL\u0017\r\\!di&|gn\u00149t!\tq\u0012\u0007B\u00033\u0007\t\u0007\u0011EA\u0001Q\u0011\u0015!4\u00011\u00011\u0003\u0005\u0001\b"
)
public interface PartialActionSyntax {
   // $FF: synthetic method
   static LeftPartialActionOps leftPartialActionOps$(final PartialActionSyntax $this, final Object g) {
      return $this.leftPartialActionOps(g);
   }

   default LeftPartialActionOps leftPartialActionOps(final Object g) {
      return new LeftPartialActionOps(g);
   }

   // $FF: synthetic method
   static RightPartialActionOps rightPartialActionOps$(final PartialActionSyntax $this, final Object p) {
      return $this.rightPartialActionOps(p);
   }

   default RightPartialActionOps rightPartialActionOps(final Object p) {
      return new RightPartialActionOps(p);
   }

   static void $init$(final PartialActionSyntax $this) {
   }
}
