package org.apache.spark.deploy.worker;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.apache.spark.metrics.source.Source;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q\u0001C\u0005\u0001\u0013MA\u0001B\u0003\u0001\u0003\u0006\u0004%\ta\t\u0005\tQ\u0001\u0011\t\u0011)A\u0005I!)\u0011\u0006\u0001C\u0001U!9Q\u0006\u0001b\u0001\n\u0003r\u0003BB\u001c\u0001A\u0003%q\u0006C\u00049\u0001\t\u0007I\u0011I\u001d\t\r\r\u0003\u0001\u0015!\u0003;\u000519vN]6feN{WO]2f\u0015\tQ1\"\u0001\u0004x_J\\WM\u001d\u0006\u0003\u00195\ta\u0001Z3qY>L(B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00037\u0001j\u0011\u0001\b\u0006\u0003;y\taa]8ve\u000e,'BA\u0010\u000e\u0003\u001diW\r\u001e:jGNL!!\t\u000f\u0003\rM{WO]2f\u0007\u0001)\u0012\u0001\n\t\u0003K\u0019j\u0011!C\u0005\u0003O%\u0011aaV8sW\u0016\u0014\u0018aB<pe.,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005-b\u0003CA\u0013\u0001\u0011\u0015Q1\u00011\u0001%\u0003)\u0019x.\u001e:dK:\u000bW.Z\u000b\u0002_A\u0011\u0001'N\u0007\u0002c)\u0011!gM\u0001\u0005Y\u0006twMC\u00015\u0003\u0011Q\u0017M^1\n\u0005Y\n$AB*ue&tw-A\u0006t_V\u00148-\u001a(b[\u0016\u0004\u0013AD7fiJL7MU3hSN$(/_\u000b\u0002uA\u00111(Q\u0007\u0002y)\u0011q$\u0010\u0006\u0003}}\n\u0001bY8eC\"\fG.\u001a\u0006\u0002\u0001\u0006\u00191m\\7\n\u0005\tc$AD'fiJL7MU3hSN$(/_\u0001\u0010[\u0016$(/[2SK\u001eL7\u000f\u001e:zA\u0001"
)
public class WorkerSource implements Source {
   private final Worker worker;
   private final String sourceName;
   private final MetricRegistry metricRegistry;

   public Worker worker() {
      return this.worker;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public WorkerSource(final Worker worker) {
      this.worker = worker;
      this.sourceName = "worker";
      this.metricRegistry = new MetricRegistry();
      this.metricRegistry().register(MetricRegistry.name("executors", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final WorkerSource $outer;

         public int getValue() {
            return this.$outer.worker().executors().size();
         }

         public {
            if (WorkerSource.this == null) {
               throw null;
            } else {
               this.$outer = WorkerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("coresUsed", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final WorkerSource $outer;

         public int getValue() {
            return this.$outer.worker().coresUsed();
         }

         public {
            if (WorkerSource.this == null) {
               throw null;
            } else {
               this.$outer = WorkerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("memUsed_MB", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final WorkerSource $outer;

         public int getValue() {
            return this.$outer.worker().memoryUsed();
         }

         public {
            if (WorkerSource.this == null) {
               throw null;
            } else {
               this.$outer = WorkerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("coresFree", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final WorkerSource $outer;

         public int getValue() {
            return this.$outer.worker().coresFree();
         }

         public {
            if (WorkerSource.this == null) {
               throw null;
            } else {
               this.$outer = WorkerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("memFree_MB", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final WorkerSource $outer;

         public int getValue() {
            return this.$outer.worker().memoryFree();
         }

         public {
            if (WorkerSource.this == null) {
               throw null;
            } else {
               this.$outer = WorkerSource.this;
            }
         }
      });
   }
}
