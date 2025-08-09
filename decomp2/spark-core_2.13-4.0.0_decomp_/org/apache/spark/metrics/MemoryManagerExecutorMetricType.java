package org.apache.spark.metrics;

import org.apache.spark.memory.MemoryManager;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000592a\u0001B\u0003\u0002\u0002\u001di\u0001\u0002\u0003\r\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u000e\t\u000b\u0019\u0002A\u0011A\u0014\t\r)\u0002A\u0011I\u0004,\u0005}iU-\\8ss6\u000bg.Y4fe\u0016CXmY;u_JlU\r\u001e:jGRK\b/\u001a\u0006\u0003\r\u001d\tq!\\3ue&\u001c7O\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\r\u0001a\u0002\u0006\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005U1R\"A\u0003\n\u0005])!!H*j]\u001edWMV1mk\u0016,\u00050Z2vi>\u0014X*\u001a;sS\u000e$\u0016\u0010]3\u0002\u0003\u0019\u001c\u0001\u0001\u0005\u0003\u00107u\u0019\u0013B\u0001\u000f\u0011\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u001fC5\tqD\u0003\u0002!\u000f\u00051Q.Z7pefL!AI\u0010\u0003\u001b5+Wn\u001c:z\u001b\u0006t\u0017mZ3s!\tyA%\u0003\u0002&!\t!Aj\u001c8h\u0003\u0019a\u0014N\\5u}Q\u0011\u0001&\u000b\t\u0003+\u0001AQ\u0001\u0007\u0002A\u0002i\tabZ3u\u001b\u0016$(/[2WC2,X\r\u0006\u0002$Y!)Qf\u0001a\u0001;\u0005iQ.Z7pefl\u0015M\\1hKJ\u0004"
)
public abstract class MemoryManagerExecutorMetricType implements SingleValueExecutorMetricType {
   private final Function1 f;

   public Seq names() {
      return SingleValueExecutorMetricType.names$(this);
   }

   public long[] getMetricValues(final MemoryManager memoryManager) {
      return SingleValueExecutorMetricType.getMetricValues$(this, memoryManager);
   }

   public long getMetricValue(final MemoryManager memoryManager) {
      return BoxesRunTime.unboxToLong(this.f.apply(memoryManager));
   }

   public MemoryManagerExecutorMetricType(final Function1 f) {
      this.f = f;
      SingleValueExecutorMetricType.$init$(this);
   }
}
