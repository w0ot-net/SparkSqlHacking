package spire.algebra;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u00038\u0001\u0019\u0005\u0001\bC\u0003>\u0001\u0019\u0005aH\u0001\bBI\u0012LG/\u001b<f\u0003\u000e$\u0018n\u001c8\u000b\u0005\u001dA\u0011aB1mO\u0016\u0014'/\u0019\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u0001Qc\u0001\u0007!kM\u0011\u0001!\u0004\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u0001\u0016!\tqa#\u0003\u0002\u0018\u001f\t!QK\\5u\u0003!\tG\rZ5uSZ,W#\u0001\u000e\u0011\tmab\u0004N\u0007\u0002\r%\u0011QD\u0002\u0002\u0007\u0003\u000e$\u0018n\u001c8\u0011\u0005}\u0001C\u0002\u0001\u0003\nC\u0001\u0001\u000b\u0011!AC\u0002\t\u0012\u0011\u0001U\t\u0003G5\u0001\"A\u0004\u0013\n\u0005\u0015z!a\u0002(pi\"Lgn\u001a\u0015\u0004A\u001dR\u0003C\u0001\b)\u0013\tIsBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012,Y9jcB\u0001\b-\u0013\tis\"A\u0002J]R\fD\u0001J\u00184!9\u0011\u0001gM\u0007\u0002c)\u0011!GC\u0001\u0007yI|w\u000e\u001e \n\u0003A\u0001\"aH\u001b\u0005\u000bY\u0002!\u0019\u0001\u0012\u0003\u0003\u001d\u000baa\u001a9mkNdGc\u0001\u0010:w!)!h\u0001a\u0001i\u0005\tq\rC\u0003=\u0007\u0001\u0007a$A\u0001q\u0003\u00199\u0007\u000f\\;teR\u0019ad\u0010!\t\u000bq\"\u0001\u0019\u0001\u0010\t\u000bi\"\u0001\u0019\u0001\u001b"
)
public interface AdditiveAction {
   // $FF: synthetic method
   static Action additive$(final AdditiveAction $this) {
      return $this.additive();
   }

   default Action additive() {
      return new Action() {
         // $FF: synthetic field
         private final AdditiveAction $outer;

         public int actr$mcI$sp(final int p, final Object g) {
            return RightAction.actr$mcI$sp$(this, p, g);
         }

         public int actl$mcI$sp(final Object g, final int p) {
            return LeftAction.actl$mcI$sp$(this, g, p);
         }

         public Object actl(final Object g, final Object p) {
            return this.$outer.gplusl(g, p);
         }

         public Object actr(final Object p, final Object g) {
            return this.$outer.gplusr(p, g);
         }

         public {
            if (AdditiveAction.this == null) {
               throw null;
            } else {
               this.$outer = AdditiveAction.this;
            }
         }
      };
   }

   Object gplusl(final Object g, final Object p);

   Object gplusr(final Object p, final Object g);

   // $FF: synthetic method
   static Action additive$mcI$sp$(final AdditiveAction $this) {
      return $this.additive$mcI$sp();
   }

   default Action additive$mcI$sp() {
      return this.additive();
   }

   // $FF: synthetic method
   static int gplusl$mcI$sp$(final AdditiveAction $this, final Object g, final int p) {
      return $this.gplusl$mcI$sp(g, p);
   }

   default int gplusl$mcI$sp(final Object g, final int p) {
      return BoxesRunTime.unboxToInt(this.gplusl(g, BoxesRunTime.boxToInteger(p)));
   }

   // $FF: synthetic method
   static int gplusr$mcI$sp$(final AdditiveAction $this, final int p, final Object g) {
      return $this.gplusr$mcI$sp(p, g);
   }

   default int gplusr$mcI$sp(final int p, final Object g) {
      return BoxesRunTime.unboxToInt(this.gplusr(BoxesRunTime.boxToInteger(p), g));
   }

   static void $init$(final AdditiveAction $this) {
   }
}
