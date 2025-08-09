package org.apache.spark.metrics.source;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.BufferPoolMetricSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import java.lang.management.ManagementFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2QAB\u0004\u0001\u0017EAQ\u0001\b\u0001\u0005\u0002yAq\u0001\t\u0001C\u0002\u0013\u0005\u0013\u0005\u0003\u0004+\u0001\u0001\u0006IA\t\u0005\bW\u0001\u0011\r\u0011\"\u0011-\u0011\u00191\u0004\u0001)A\u0005[\tI!J^7T_V\u00148-\u001a\u0006\u0003\u0011%\taa]8ve\u000e,'B\u0001\u0006\f\u0003\u001diW\r\u001e:jGNT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a55\tq!\u0003\u0002\u001c\u000f\t11k\\;sG\u0016\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002?A\u0011\u0011\u0004A\u0001\u000bg>,(oY3OC6,W#\u0001\u0012\u0011\u0005\rBS\"\u0001\u0013\u000b\u0005\u00152\u0013\u0001\u00027b]\u001eT\u0011aJ\u0001\u0005U\u00064\u0018-\u0003\u0002*I\t11\u000b\u001e:j]\u001e\f1b]8ve\u000e,g*Y7fA\u0005qQ.\u001a;sS\u000e\u0014VmZ5tiJLX#A\u0017\u0011\u00059\"T\"A\u0018\u000b\u0005)\u0001$BA\u00193\u0003!\u0019w\u000eZ1iC2,'\"A\u001a\u0002\u0007\r|W.\u0003\u00026_\tqQ*\u001a;sS\u000e\u0014VmZ5tiJL\u0018aD7fiJL7MU3hSN$(/\u001f\u0011"
)
public class JvmSource implements Source {
   private final String sourceName = "jvm";
   private final MetricRegistry metricRegistry = new MetricRegistry();

   public String sourceName() {
      return this.sourceName;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public JvmSource() {
      this.metricRegistry().registerAll(new GarbageCollectorMetricSet());
      this.metricRegistry().registerAll(new MemoryUsageGaugeSet());
      this.metricRegistry().registerAll(new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));
   }
}
