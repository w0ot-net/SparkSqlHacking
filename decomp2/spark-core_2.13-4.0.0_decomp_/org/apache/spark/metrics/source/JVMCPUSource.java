package org.apache.spark.metrics.source;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005]2QAB\u0004\u0001\u0017EAQ\u0001\b\u0001\u0005\u0002yAq\u0001\t\u0001C\u0002\u0013\u0005\u0013\u0005\u0003\u0004,\u0001\u0001\u0006IA\t\u0005\bY\u0001\u0011\r\u0011\"\u0011.\u0011\u00191\u0004\u0001)A\u0005]\ta!JV'D!V\u001bv.\u001e:dK*\u0011\u0001\"C\u0001\u0007g>,(oY3\u000b\u0005)Y\u0011aB7fiJL7m\u001d\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\tI\"$D\u0001\b\u0013\tYrA\u0001\u0004T_V\u00148-Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\u0004\u0005\u0002\u001a\u0001\u0005qQ.\u001a;sS\u000e\u0014VmZ5tiJLX#\u0001\u0012\u0011\u0005\rJS\"\u0001\u0013\u000b\u0005))#B\u0001\u0014(\u0003!\u0019w\u000eZ1iC2,'\"\u0001\u0015\u0002\u0007\r|W.\u0003\u0002+I\tqQ*\u001a;sS\u000e\u0014VmZ5tiJL\u0018aD7fiJL7MU3hSN$(/\u001f\u0011\u0002\u0015M|WO]2f\u001d\u0006lW-F\u0001/!\tyC'D\u00011\u0015\t\t$'\u0001\u0003mC:<'\"A\u001a\u0002\t)\fg/Y\u0005\u0003kA\u0012aa\u0015;sS:<\u0017aC:pkJ\u001cWMT1nK\u0002\u0002"
)
public class JVMCPUSource implements Source {
   private final MetricRegistry metricRegistry = new MetricRegistry();
   private final String sourceName = "JVMCPU";

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public JVMCPUSource() {
      this.metricRegistry().register(MetricRegistry.name("jvmCpuTime", new String[0]), new Gauge() {
         private final MBeanServer mBean = ManagementFactory.getPlatformMBeanServer();
         private final ObjectName name = new ObjectName("java.lang", "type", "OperatingSystem");

         public MBeanServer mBean() {
            return this.mBean;
         }

         public ObjectName name() {
            return this.name;
         }

         public long getValue() {
            long var10000;
            try {
               var10000 = BoxesRunTime.unboxToLong(this.mBean().getAttribute(this.name(), "ProcessCpuTime"));
            } catch (Throwable var6) {
               if (var6 != null) {
                  Option var5 = .MODULE$.unapply(var6);
                  if (!var5.isEmpty()) {
                     var10000 = -1L;
                     return var10000;
                  }
               }

               throw var6;
            }

            return var10000;
         }
      });
   }
}
