package breeze.stats.distributions;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000553qAB\u0004\u0011\u0002\u0007%a\u0002C\u00039\u0001\u0011\u0005\u0011\bC\u0004>\u0001\t\u0007i\u0011\u0003 \t\u000b}\u0002a\u0011\u0003!\t\u000b\u0019\u0003A\u0011A$\t\u000b!\u0003A\u0011I%\u0003%A\u0013X\rZ5dCR,'+\u00198e\tJ\fwo\u001d\u0006\u0003\u0011%\tQ\u0002Z5tiJL'-\u001e;j_:\u001c(B\u0001\u0006\f\u0003\u0015\u0019H/\u0019;t\u0015\u0005a\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005=a2c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u00042a\u0006\r\u001b\u001b\u00059\u0011BA\r\b\u0005\u0011\u0011\u0016M\u001c3\u0011\u0005maB\u0002\u0001\u0003\n;\u0001\u0001\u000b\u0011!AC\u0002y\u0011\u0011\u0001V\t\u0003?\t\u0002\"!\u0005\u0011\n\u0005\u0005\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#\rJ!\u0001\n\n\u0003\u0007\u0005s\u0017\u0010\u000b\u0003\u001dM%\u001a\u0004CA\t(\u0013\tA#CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012+W5bcBA\t,\u0013\ta##A\u0002J]R\fD\u0001\n\u00183'9\u0011qFM\u0007\u0002a)\u0011\u0011'D\u0001\u0007yI|w\u000e\u001e \n\u0003M\tTa\t\u001b6oYr!!E\u001b\n\u0005Y\u0012\u0012A\u0002#pk\ndW-\r\u0003%]I\u001a\u0012A\u0002\u0013j]&$H\u0005F\u0001;!\t\t2(\u0003\u0002=%\t!QK\\5u\u0003\u0011\u0011\u0018M\u001c3\u0016\u0003Y\t\u0011\u0002\u001d:fI&\u001c\u0017\r^3\u0015\u0005\u0005#\u0005CA\tC\u0013\t\u0019%CA\u0004C_>dW-\u00198\t\u000b\u0015\u001b\u0001\u0019\u0001\u000e\u0002\u0003a\fA\u0001\u001a:boR\t!$A\u0004ee\u0006<x\n\u001d;\u0015\u0003)\u00032!E&\u001b\u0013\ta%C\u0001\u0004PaRLwN\u001c"
)
public interface PredicateRandDraws extends Rand {
   Rand rand();

   boolean predicate(final Object x);

   // $FF: synthetic method
   static Object draw$(final PredicateRandDraws $this) {
      return $this.draw();
   }

   default Object draw() {
      Object x;
      for(x = this.rand().draw(); !this.predicate(x); x = this.rand().draw()) {
      }

      return x;
   }

   // $FF: synthetic method
   static Option drawOpt$(final PredicateRandDraws $this) {
      return $this.drawOpt();
   }

   default Option drawOpt() {
      Object x = this.rand().get();
      return (Option)(this.predicate(x) ? new Some(x) : .MODULE$);
   }

   // $FF: synthetic method
   static Rand rand$mcD$sp$(final PredicateRandDraws $this) {
      return $this.rand$mcD$sp();
   }

   default Rand rand$mcD$sp() {
      return this.rand();
   }

   // $FF: synthetic method
   static Rand rand$mcI$sp$(final PredicateRandDraws $this) {
      return $this.rand$mcI$sp();
   }

   default Rand rand$mcI$sp() {
      return this.rand();
   }

   // $FF: synthetic method
   static boolean predicate$mcD$sp$(final PredicateRandDraws $this, final double x) {
      return $this.predicate$mcD$sp(x);
   }

   default boolean predicate$mcD$sp(final double x) {
      return this.predicate(BoxesRunTime.boxToDouble(x));
   }

   // $FF: synthetic method
   static boolean predicate$mcI$sp$(final PredicateRandDraws $this, final int x) {
      return $this.predicate$mcI$sp(x);
   }

   default boolean predicate$mcI$sp(final int x) {
      return this.predicate(BoxesRunTime.boxToInteger(x));
   }

   // $FF: synthetic method
   static double draw$mcD$sp$(final PredicateRandDraws $this) {
      return $this.draw$mcD$sp();
   }

   default double draw$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.draw());
   }

   // $FF: synthetic method
   static int draw$mcI$sp$(final PredicateRandDraws $this) {
      return $this.draw$mcI$sp();
   }

   default int draw$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.draw());
   }

   // $FF: synthetic method
   static boolean specInstance$$(final PredicateRandDraws $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }

   static void $init$(final PredicateRandDraws $this) {
   }
}
