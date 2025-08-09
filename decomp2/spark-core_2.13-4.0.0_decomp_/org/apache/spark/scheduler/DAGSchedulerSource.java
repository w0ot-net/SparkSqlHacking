package org.apache.spark.scheduler;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.apache.spark.metrics.source.Source;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3QAC\u0006\u0001\u0017MA\u0001B\t\u0001\u0003\u0006\u0004%\t\u0001\n\u0005\tS\u0001\u0011\t\u0011)A\u0005K!)!\u0006\u0001C\u0001W!9a\u0006\u0001b\u0001\n\u0003z\u0003BB\u001d\u0001A\u0003%\u0001\u0007C\u0004;\u0001\t\u0007I\u0011I\u001e\t\r\u0011\u0003\u0001\u0015!\u0003=\u0011\u001d)\u0005A1A\u0005\u0002\u0019CaA\u0013\u0001!\u0002\u00139%A\u0005#B\u000fN\u001b\u0007.\u001a3vY\u0016\u00148k\\;sG\u0016T!\u0001D\u0007\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00037\u0001j\u0011\u0001\b\u0006\u0003;y\taa]8ve\u000e,'BA\u0010\u000e\u0003\u001diW\r\u001e:jGNL!!\t\u000f\u0003\rM{WO]2f\u00031!\u0017mZ*dQ\u0016$W\u000f\\3s\u0007\u0001)\u0012!\n\t\u0003M\u001dj\u0011aC\u0005\u0003Q-\u0011A\u0002R!H'\u000eDW\rZ;mKJ\fQ\u0002Z1h'\u000eDW\rZ;mKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002-[A\u0011a\u0005\u0001\u0005\u0006E\r\u0001\r!J\u0001\u000f[\u0016$(/[2SK\u001eL7\u000f\u001e:z+\u0005\u0001\u0004CA\u00198\u001b\u0005\u0011$BA\u00104\u0015\t!T'\u0001\u0005d_\u0012\f\u0007.\u00197f\u0015\u00051\u0014aA2p[&\u0011\u0001H\r\u0002\u000f\u001b\u0016$(/[2SK\u001eL7\u000f\u001e:z\u0003=iW\r\u001e:jGJ+w-[:uef\u0004\u0013AC:pkJ\u001cWMT1nKV\tA\b\u0005\u0002>\u00056\taH\u0003\u0002@\u0001\u0006!A.\u00198h\u0015\u0005\t\u0015\u0001\u00026bm\u0006L!a\u0011 \u0003\rM#(/\u001b8h\u0003-\u0019x.\u001e:dK:\u000bW.\u001a\u0011\u0002-5,7o]1hKB\u0013xnY3tg&tw\rV5nKJ,\u0012a\u0012\t\u0003c!K!!\u0013\u001a\u0003\u000bQKW.\u001a:\u0002/5,7o]1hKB\u0013xnY3tg&tw\rV5nKJ\u0004\u0003"
)
public class DAGSchedulerSource implements Source {
   private final DAGScheduler dagScheduler;
   private final MetricRegistry metricRegistry;
   private final String sourceName;
   private final Timer messageProcessingTimer;

   public DAGScheduler dagScheduler() {
      return this.dagScheduler;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public Timer messageProcessingTimer() {
      return this.messageProcessingTimer;
   }

   public DAGSchedulerSource(final DAGScheduler dagScheduler) {
      this.dagScheduler = dagScheduler;
      this.metricRegistry = new MetricRegistry();
      this.sourceName = "DAGScheduler";
      this.metricRegistry().register(MetricRegistry.name("stage", new String[]{"failedStages"}), new Gauge() {
         // $FF: synthetic field
         private final DAGSchedulerSource $outer;

         public int getValue() {
            return this.$outer.dagScheduler().failedStages().size();
         }

         public {
            if (DAGSchedulerSource.this == null) {
               throw null;
            } else {
               this.$outer = DAGSchedulerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("stage", new String[]{"runningStages"}), new Gauge() {
         // $FF: synthetic field
         private final DAGSchedulerSource $outer;

         public int getValue() {
            return this.$outer.dagScheduler().runningStages().size();
         }

         public {
            if (DAGSchedulerSource.this == null) {
               throw null;
            } else {
               this.$outer = DAGSchedulerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("stage", new String[]{"waitingStages"}), new Gauge() {
         // $FF: synthetic field
         private final DAGSchedulerSource $outer;

         public int getValue() {
            return this.$outer.dagScheduler().waitingStages().size();
         }

         public {
            if (DAGSchedulerSource.this == null) {
               throw null;
            } else {
               this.$outer = DAGSchedulerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("job", new String[]{"allJobs"}), new Gauge() {
         // $FF: synthetic field
         private final DAGSchedulerSource $outer;

         public int getValue() {
            return this.$outer.dagScheduler().numTotalJobs();
         }

         public {
            if (DAGSchedulerSource.this == null) {
               throw null;
            } else {
               this.$outer = DAGSchedulerSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("job", new String[]{"activeJobs"}), new Gauge() {
         // $FF: synthetic field
         private final DAGSchedulerSource $outer;

         public int getValue() {
            return this.$outer.dagScheduler().activeJobs().size();
         }

         public {
            if (DAGSchedulerSource.this == null) {
               throw null;
            } else {
               this.$outer = DAGSchedulerSource.this;
            }
         }
      });
      this.messageProcessingTimer = this.metricRegistry().timer(MetricRegistry.name("messageProcessingTime", new String[0]));
   }
}
