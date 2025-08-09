package org.apache.spark.deploy.master;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.apache.spark.metrics.source.Source;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!3Q\u0001C\u0005\u0001\u0013MA\u0001B\t\u0001\u0003\u0006\u0004%\t\u0001\n\u0005\tS\u0001\u0011\t\u0011)A\u0005K!)!\u0006\u0001C\u0001W!9a\u0006\u0001b\u0001\n\u0003z\u0003BB\u001d\u0001A\u0003%\u0001\u0007C\u0004;\u0001\t\u0007I\u0011I\u001e\t\r\u001d\u0003\u0001\u0015!\u0003=\u0005E\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8T_V\u00148-\u001a\u0006\u0003\u0015-\ta!\\1ti\u0016\u0014(B\u0001\u0007\u000e\u0003\u0019!W\r\u001d7ps*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xmE\u0002\u0001)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007CA\u000e!\u001b\u0005a\"BA\u000f\u001f\u0003\u0019\u0019x.\u001e:dK*\u0011q$D\u0001\b[\u0016$(/[2t\u0013\t\tCD\u0001\u0004T_V\u00148-Z\u0001\fCB\u0004H.[2bi&|gn\u0001\u0001\u0016\u0003\u0015\u0002\"AJ\u0014\u000e\u0003%I!\u0001K\u0005\u0003\u001f\u0005\u0003\b\u000f\\5dCRLwN\\%oM>\fA\"\u00199qY&\u001c\u0017\r^5p]\u0002\na\u0001P5oSRtDC\u0001\u0017.!\t1\u0003\u0001C\u0003#\u0007\u0001\u0007Q%\u0001\bnKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0016\u0003A\u0002\"!M\u001c\u000e\u0003IR!aH\u001a\u000b\u0005Q*\u0014\u0001C2pI\u0006D\u0017\r\\3\u000b\u0003Y\n1aY8n\u0013\tA$G\u0001\bNKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0002\u001f5,GO]5d%\u0016<\u0017n\u001d;ss\u0002\n!b]8ve\u000e,g*Y7f+\u0005a\u0004CA\u001fE\u001d\tq$\t\u0005\u0002@-5\t\u0001I\u0003\u0002BG\u00051AH]8pizJ!a\u0011\f\u0002\rA\u0013X\rZ3g\u0013\t)eI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0007Z\t1b]8ve\u000e,g*Y7fA\u0001"
)
public class ApplicationSource implements Source {
   private final ApplicationInfo application;
   private final MetricRegistry metricRegistry;
   private final String sourceName;

   public ApplicationInfo application() {
      return this.application;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public ApplicationSource(final ApplicationInfo application) {
      this.application = application;
      this.metricRegistry = new MetricRegistry();
      this.sourceName = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s.%s.%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"application", application.desc().name(), BoxesRunTime.boxToLong(System.currentTimeMillis())}));
      this.metricRegistry().register(MetricRegistry.name("status", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationSource $outer;

         public String getValue() {
            return this.$outer.application().state().toString();
         }

         public {
            if (ApplicationSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("runtime_ms", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationSource $outer;

         public long getValue() {
            return this.$outer.application().duration();
         }

         public {
            if (ApplicationSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationSource.this;
            }
         }
      });
      this.metricRegistry().register(MetricRegistry.name("cores", new String[0]), new Gauge() {
         // $FF: synthetic field
         private final ApplicationSource $outer;

         public int getValue() {
            return this.$outer.application().coresGranted();
         }

         public {
            if (ApplicationSource.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationSource.this;
            }
         }
      });
   }
}
