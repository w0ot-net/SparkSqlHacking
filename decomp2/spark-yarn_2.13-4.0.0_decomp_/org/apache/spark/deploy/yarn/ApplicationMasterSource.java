package org.apache.spark.deploy.yarn;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.apache.spark.metrics.source.Source;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3Q\u0001C\u0005\u0001\u001bMA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\t_\u0001\u0011\t\u0011)A\u0005a!)A\u0007\u0001C\u0001k!9\u0011\b\u0001b\u0001\n\u0003R\u0004BB\u001e\u0001A\u0003%A\u0005C\u0004=\u0001\t\u0007I\u0011I\u001f\t\r\u001d\u0003\u0001\u0015!\u0003?\u0005]\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8NCN$XM]*pkJ\u001cWM\u0003\u0002\u000b\u0017\u0005!\u00110\u0019:o\u0015\taQ\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sON\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\tY\u0002%D\u0001\u001d\u0015\tib$\u0001\u0004t_V\u00148-\u001a\u0006\u0003?5\tq!\\3ue&\u001c7/\u0003\u0002\"9\t11k\\;sG\u0016\fa\u0001\u001d:fM&D8\u0001\u0001\t\u0003K1r!A\n\u0016\u0011\u0005\u001d2R\"\u0001\u0015\u000b\u0005%\u001a\u0013A\u0002\u001fs_>$h(\u0003\u0002,-\u00051\u0001K]3eK\u001aL!!\f\u0018\u0003\rM#(/\u001b8h\u0015\tYc#A\u0007zCJt\u0017\t\u001c7pG\u0006$xN\u001d\t\u0003cIj\u0011!C\u0005\u0003g%\u0011Q\"W1s]\u0006cGn\\2bi>\u0014\u0018A\u0002\u001fj]&$h\bF\u00027oa\u0002\"!\r\u0001\t\u000b\t\u001a\u0001\u0019\u0001\u0013\t\u000b=\u001a\u0001\u0019\u0001\u0019\u0002\u0015M|WO]2f\u001d\u0006lW-F\u0001%\u0003-\u0019x.\u001e:dK:\u000bW.\u001a\u0011\u0002\u001d5,GO]5d%\u0016<\u0017n\u001d;ssV\ta\b\u0005\u0002@\u000b6\t\u0001I\u0003\u0002 \u0003*\u0011!iQ\u0001\tG>$\u0017\r[1mK*\tA)A\u0002d_6L!A\u0012!\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006yQ.\u001a;sS\u000e\u0014VmZ5tiJL\b\u0005"
)
public class ApplicationMasterSource implements Source {
   public final YarnAllocator org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator;
   private final String sourceName;
   private final MetricRegistry metricRegistry;

   public String sourceName() {
      return this.sourceName;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public ApplicationMasterSource(final String prefix, final YarnAllocator yarnAllocator) {
      this.org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator = yarnAllocator;
      this.sourceName = prefix + ".applicationMaster";
      this.metricRegistry = new MetricRegistry();
      this.metricRegistry().register(MetricRegistry.name("numExecutorsFailed", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationMasterSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator.getNumExecutorsFailed();
         }

         public {
            if (ApplicationMasterSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMasterSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("numExecutorsRunning", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationMasterSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator.getNumExecutorsRunning();
         }

         public {
            if (ApplicationMasterSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMasterSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("numReleasedContainers", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationMasterSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator.getNumReleasedContainers();
         }

         public {
            if (ApplicationMasterSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMasterSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("numLocalityAwareTasks", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationMasterSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator.getNumLocalityAwareTasks();
         }

         public {
            if (ApplicationMasterSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMasterSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("numContainersPendingAllocate", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationMasterSource $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$deploy$yarn$ApplicationMasterSource$$yarnAllocator.getNumContainersPendingAllocate();
         }

         public {
            if (ApplicationMasterSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMasterSource.this;
            }
         }
      });
   }
}
