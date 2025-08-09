package org.apache.spark.deploy.master;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.source.Source;
import scala.Enumeration;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q\u0001C\u0005\u0001\u001bMA\u0001B\u0003\u0001\u0003\u0006\u0004%\ta\t\u0005\tQ\u0001\u0011\t\u0011)A\u0005I!)\u0011\u0006\u0001C\u0001U!9Q\u0006\u0001b\u0001\n\u0003r\u0003B\u0002\u001d\u0001A\u0003%q\u0006C\u0004:\u0001\t\u0007I\u0011\t\u001e\t\r\r\u0003\u0001\u0015!\u0003<\u00051i\u0015m\u001d;feN{WO]2f\u0015\tQ1\"\u0001\u0004nCN$XM\u001d\u0006\u0003\u00195\ta\u0001Z3qY>L(B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00037\u0001j\u0011\u0001\b\u0006\u0003;y\taa]8ve\u000e,'BA\u0010\u000e\u0003\u001diW\r\u001e:jGNL!!\t\u000f\u0003\rM{WO]2f\u0007\u0001)\u0012\u0001\n\t\u0003K\u0019j\u0011!C\u0005\u0003O%\u0011a!T1ti\u0016\u0014\u0018aB7bgR,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005-b\u0003CA\u0013\u0001\u0011\u0015Q1\u00011\u0001%\u00039iW\r\u001e:jGJ+w-[:uef,\u0012a\f\t\u0003aYj\u0011!\r\u0006\u0003?IR!a\r\u001b\u0002\u0011\r|G-\u00195bY\u0016T\u0011!N\u0001\u0004G>l\u0017BA\u001c2\u00059iU\r\u001e:jGJ+w-[:uef\fq\"\\3ue&\u001c'+Z4jgR\u0014\u0018\u0010I\u0001\u000bg>,(oY3OC6,W#A\u001e\u0011\u0005q\nU\"A\u001f\u000b\u0005yz\u0014\u0001\u00027b]\u001eT\u0011\u0001Q\u0001\u0005U\u00064\u0018-\u0003\u0002C{\t11\u000b\u001e:j]\u001e\f1b]8ve\u000e,g*Y7fA\u0001"
)
public class MasterSource implements Source {
   private final Master master;
   private final MetricRegistry metricRegistry;
   private final String sourceName;

   public Master master() {
      return this.master;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public MasterSource(final Master master) {
      this.master = master;
      this.metricRegistry = new MetricRegistry();
      this.sourceName = "master";
      this.metricRegistry().register(MetricRegistry.name("workers", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final MasterSource $outer;

         public int getValue() {
            return this.$outer.master().workers().size();
         }

         public {
            if (MasterSource.this == null) {
               throw null;
            } else {
               this.$outer = MasterSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("aliveWorkers", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final MasterSource $outer;

         public int getValue() {
            return this.$outer.master().workers().count((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getValue$1(x$1)));
         }

         // $FF: synthetic method
         public static final boolean $anonfun$getValue$1(final WorkerInfo x$1) {
            boolean var2;
            label23: {
               Enumeration.Value var10000 = x$1.state();
               Enumeration.Value var1 = WorkerState$.MODULE$.ALIVE();
               if (var10000 == null) {
                  if (var1 == null) {
                     break label23;
                  }
               } else if (var10000.equals(var1)) {
                  break label23;
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            if (MasterSource.this == null) {
               throw null;
            } else {
               this.$outer = MasterSource.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      this.metricRegistry().register(MetricRegistry.name("apps", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final MasterSource $outer;

         public int getValue() {
            return this.$outer.master().apps().size();
         }

         public {
            if (MasterSource.this == null) {
               throw null;
            } else {
               this.$outer = MasterSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("waitingApps", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final MasterSource $outer;

         public int getValue() {
            return this.$outer.master().apps().count((x$2) -> BoxesRunTime.boxToBoolean($anonfun$getValue$2(x$2)));
         }

         // $FF: synthetic method
         public static final boolean $anonfun$getValue$2(final ApplicationInfo x$2) {
            boolean var2;
            label23: {
               Enumeration.Value var10000 = x$2.state();
               Enumeration.Value var1 = ApplicationState$.MODULE$.WAITING();
               if (var10000 == null) {
                  if (var1 == null) {
                     break label23;
                  }
               } else if (var10000.equals(var1)) {
                  break label23;
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            if (MasterSource.this == null) {
               throw null;
            } else {
               this.$outer = MasterSource.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
