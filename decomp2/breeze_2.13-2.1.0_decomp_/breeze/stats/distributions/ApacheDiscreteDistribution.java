package breeze.stats.distributions;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053qAB\u0004\u0011\u0002\u0007\u0005a\u0002C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0004\"\u0001\t\u0007i\u0011\u0003\u0012\t\u000bE\u0002A\u0011\u0001\u001a\t\u000ba\u0002A\u0011A\u001d\t\u000bi\u0002A\u0011A\u001e\u00035\u0005\u0003\u0018m\u00195f\t&\u001c8M]3uK\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005!I\u0011!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002\u000b\u0017\u0005)1\u000f^1ug*\tA\"\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u0001q\"\u0006\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Y9\u0012$D\u0001\b\u0013\tArAA\u0007ESN\u001c'/\u001a;f\t&\u001cHO\u001d\t\u0003!iI!aG\t\u0003\u0007%sG/\u0001\u0004%S:LG\u000f\n\u000b\u0002=A\u0011\u0001cH\u0005\u0003AE\u0011A!\u00168ji\u0006)\u0011N\u001c8feV\t1\u0005\u0005\u0002%_5\tQE\u0003\u0002'O\u0005aA-[:ue&\u0014W\u000f^5p]*\u0011\u0001&K\u0001\u0006[\u0006$\bn\r\u0006\u0003U-\nqaY8n[>t7O\u0003\u0002-[\u00051\u0011\r]1dQ\u0016T\u0011AL\u0001\u0004_J<\u0017B\u0001\u0019&\u0005m\t%m\u001d;sC\u000e$\u0018J\u001c;fO\u0016\u0014H)[:ue&\u0014W\u000f^5p]\u0006i\u0001O]8cC\nLG.\u001b;z\u001f\u001a$\"a\r\u001c\u0011\u0005A!\u0014BA\u001b\u0012\u0005\u0019!u.\u001e2mK\")qg\u0001a\u00013\u0005\t\u00010\u0001\u0003ee\u0006<H#A\r\u0002\u0011\u0011\u0014\u0018m^'b]f$\"\u0001P \u0011\u0007Ai\u0014$\u0003\u0002?#\t)\u0011I\u001d:bs\")\u0001)\u0002a\u00013\u0005\ta\u000e"
)
public interface ApacheDiscreteDistribution extends DiscreteDistr {
   AbstractIntegerDistribution inner();

   // $FF: synthetic method
   static double probabilityOf$(final ApacheDiscreteDistribution $this, final int x) {
      return $this.probabilityOf(x);
   }

   default double probabilityOf(final int x) {
      return this.inner().probability(x);
   }

   // $FF: synthetic method
   static int draw$(final ApacheDiscreteDistribution $this) {
      return $this.draw();
   }

   default int draw() {
      return this.draw$mcI$sp();
   }

   // $FF: synthetic method
   static int[] drawMany$(final ApacheDiscreteDistribution $this, final int n) {
      return $this.drawMany(n);
   }

   default int[] drawMany(final int n) {
      return this.inner().sample(n);
   }

   // $FF: synthetic method
   static int draw$mcI$sp$(final ApacheDiscreteDistribution $this) {
      return $this.draw$mcI$sp();
   }

   default int draw$mcI$sp() {
      return this.inner().sample();
   }

   static void $init$(final ApacheDiscreteDistribution $this) {
   }
}
