package org.apache.spark.ui.exec;

import org.apache.spark.internal.config.UI$;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUITab;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192Q\u0001B\u0003\u0001\u000f=A\u0001\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\u00063\u0001!\tA\u0007\u0005\u0006=\u0001!Ia\b\u0002\r\u000bb,7-\u001e;peN$\u0016M\u0019\u0006\u0003\r\u001d\tA!\u001a=fG*\u0011\u0001\"C\u0001\u0003k&T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0003\u0001A\u0001\"!\u0005\n\u000e\u0003\u001dI!aE\u0004\u0003\u0015M\u0003\u0018M]6V\u0013R\u000b'-\u0001\u0004qCJ,g\u000e^\u0002\u0001!\t\tr#\u0003\u0002\u0019\u000f\t91\u000b]1sWVK\u0015A\u0002\u001fj]&$h\b\u0006\u0002\u001c;A\u0011A\u0004A\u0007\u0002\u000b!)AC\u0001a\u0001-\u0005!\u0011N\\5u)\u0005\u0001\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#\u0001B+oSR\u0004"
)
public class ExecutorsTab extends SparkUITab {
   private final SparkUI parent;

   private void init() {
      boolean threadDumpEnabled = this.parent.sc().isDefined() && BoxesRunTime.unboxToBoolean(this.parent.conf().get(UI$.MODULE$.UI_THREAD_DUMPS_ENABLED()));
      boolean heapHistogramEnabled = this.parent.sc().isDefined() && BoxesRunTime.unboxToBoolean(this.parent.conf().get(UI$.MODULE$.UI_HEAP_HISTOGRAM_ENABLED()));
      this.attachPage(new ExecutorsPage(this, threadDumpEnabled, heapHistogramEnabled));
      if (threadDumpEnabled) {
         this.attachPage(new ExecutorThreadDumpPage(this, this.parent.sc()));
      }

      if (heapHistogramEnabled) {
         this.attachPage(new ExecutorHeapHistogramPage(this, this.parent.sc()));
      }
   }

   public ExecutorsTab(final SparkUI parent) {
      super(parent, "executors");
      this.parent = parent;
      this.init();
   }
}
