package org.apache.spark.ml.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import scala.Predef.;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3QAC\u0006\u0001\u001fUA\u0001\"\u000b\u0001\u0003\u0006\u0004%IA\u000b\u0005\t_\u0001\u0011\t\u0011)A\u0005W!)A\u0007\u0001C\u0001k!9\u0011\b\u0001b\u0001\n\u0013Q\u0004B\u0002(\u0001A\u0003%1\bC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0003U\u0001\u0011\u0005Q\u000bC\u0003X\u0001\u0011\u0005\u0001\fC\u0003[\u0001\u0011\u00053L\u0001\bNk2$\u0018n\u0015;pa^\fGo\u00195\u000b\u00051i\u0011\u0001B;uS2T!AD\b\u0002\u00055d'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0014\u0007\u00011B\u0004\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VM\u001a\t\u0003;\u0019r!A\b\u0013\u000f\u0005}\u0019S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003eI!!\n\r\u0002\u000fA\f7m[1hK&\u0011q\u0005\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Ka\t!a]2\u0016\u0003-\u0002\"\u0001L\u0017\u000e\u0003=I!AL\b\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\u0007M\u001c\u0007\u0005\u000b\u0002\u0003cA\u0011qCM\u0005\u0003ga\u0011\u0011\u0002\u001e:b]NLWM\u001c;\u0002\rqJg.\u001b;?)\t1\u0004\b\u0005\u00028\u00015\t1\u0002C\u0003*\u0007\u0001\u00071&A\u0006ti>\u0004x/\u0019;dQ\u0016\u001cX#A\u001e\u0011\tq\n5iS\u0007\u0002{)\u0011ahP\u0001\b[V$\u0018M\u00197f\u0015\t\u0001\u0005$\u0001\u0006d_2dWm\u0019;j_:L!AQ\u001f\u0003\u00075\u000b\u0007\u000f\u0005\u0002E\u0011:\u0011QI\u0012\t\u0003?aI!a\u0012\r\u0002\rA\u0013X\rZ3g\u0013\tI%J\u0001\u0004TiJLgn\u001a\u0006\u0003\u000fb\u0001\"a\u000e'\n\u00055[!!C*u_B<\u0018\r^2i\u00031\u0019Ho\u001c9xCR\u001c\u0007.Z:!\u0003!\tG\r\u001a'pG\u0006dGCA)S\u001b\u0005\u0001\u0001\"B*\u0007\u0001\u0004\u0019\u0015\u0001\u00028b[\u0016\fa\"\u00193e\t&\u001cHO]5ckR,G\r\u0006\u0002R-\")1k\u0002a\u0001\u0007\u0006)\u0011\r\u001d9msR\u00111*\u0017\u0005\u0006'\"\u0001\raQ\u0001\ti>\u001cFO]5oOR\t1\t"
)
public class MultiStopwatch implements Serializable {
   private final transient SparkContext sc;
   private final Map stopwatches;

   private SparkContext sc() {
      return this.sc;
   }

   private Map stopwatches() {
      return this.stopwatches;
   }

   public MultiStopwatch addLocal(final String name) {
      .MODULE$.require(!this.stopwatches().contains(name), () -> "Stopwatch with name " + name + " already exists.");
      this.stopwatches().update(name, new LocalStopwatch(name));
      return this;
   }

   public MultiStopwatch addDistributed(final String name) {
      .MODULE$.require(!this.stopwatches().contains(name), () -> "Stopwatch with name " + name + " already exists.");
      this.stopwatches().update(name, new DistributedStopwatch(this.sc(), name));
      return this;
   }

   public Stopwatch apply(final String name) {
      return (Stopwatch)this.stopwatches().apply(name);
   }

   public String toString() {
      return .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(.MODULE$.refArrayOps(this.stopwatches().values().toArray(scala.reflect.ClassTag..MODULE$.apply(Stopwatch.class))), (x$2) -> x$2.name(), scala.math.Ordering.String..MODULE$)), (c) -> "  " + c, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("{\n", ",\n", "\n}");
   }

   public MultiStopwatch(final SparkContext sc) {
      this.sc = sc;
      this.stopwatches = (Map)scala.collection.mutable.Map..MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
