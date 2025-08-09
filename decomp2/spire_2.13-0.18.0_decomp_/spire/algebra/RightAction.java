package spire.algebra;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005)3qAB\u0004\u0011\u0002G\u0005A\u0002C\u0003\u0015\u0001\u0019\u0005QcB\u00034\u000f!\u0005AGB\u0003\u0007\u000f!\u0005a\u0007C\u0003;\u0007\u0011\u00051\bC\u0003=\u0007\u0011\u0005QHA\u0006SS\u001eDG/Q2uS>t'B\u0001\u0005\n\u0003\u001d\tGnZ3ce\u0006T\u0011AC\u0001\u0006gBL'/Z\u0002\u0001+\ri\u0001$M\n\u0003\u00019\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u00111!\u00118z\u0003\u0011\t7\r\u001e:\u0015\u0007Yac\u0006\u0005\u0002\u001811\u0001A!C\r\u0001A\u0003\u0005\tQ1\u0001\u001b\u0005\u0005\u0001\u0016CA\u000e\u000f!\tyA$\u0003\u0002\u001e!\t9aj\u001c;iS:<\u0007f\u0001\r EA\u0011q\u0002I\u0005\u0003CA\u00111b\u001d9fG&\fG.\u001b>fIF*1e\t\u0013'K9\u0011q\u0002J\u0005\u0003KA\t1!\u00138uc\u0011!seK\t\u000f\u0005!ZS\"A\u0015\u000b\u0005)Z\u0011A\u0002\u001fs_>$h(C\u0001\u0012\u0011\u0015i\u0013\u00011\u0001\u0017\u0003\u0005\u0001\b\"B\u0018\u0002\u0001\u0004\u0001\u0014!A4\u0011\u0005]\tD!\u0002\u001a\u0001\u0005\u0004Q\"!A$\u0002\u0017IKw\r\u001b;BGRLwN\u001c\t\u0003k\ri\u0011aB\n\u0003\u0007]\u0002\"a\u0004\u001d\n\u0005e\u0002\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002i\u0005)\u0011\r\u001d9msV\u0019a(Q\"\u0015\u0005}\"\u0005\u0003B\u001b\u0001\u0001\n\u0003\"aF!\u0005\u000be)!\u0019\u0001\u000e\u0011\u0005]\u0019E!\u0002\u001a\u0006\u0005\u0004Q\u0002\"B#\u0006\u0001\u0004y\u0014!A$)\u0005\u00159\u0005CA\bI\u0013\tI\u0005C\u0001\u0004j]2Lg.\u001a"
)
public interface RightAction {
   static RightAction apply(final RightAction G) {
      return RightAction$.MODULE$.apply(G);
   }

   Object actr(final Object p, final Object g);

   // $FF: synthetic method
   static int actr$mcI$sp$(final RightAction $this, final int p, final Object g) {
      return $this.actr$mcI$sp(p, g);
   }

   default int actr$mcI$sp(final int p, final Object g) {
      return BoxesRunTime.unboxToInt(this.actr(BoxesRunTime.boxToInteger(p), g));
   }
}
