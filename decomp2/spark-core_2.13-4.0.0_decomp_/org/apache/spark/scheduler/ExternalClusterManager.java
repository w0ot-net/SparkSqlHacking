package org.apache.spark.scheduler;

import org.apache.spark.SparkContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153\u0001\"\u0002\u0004\u0011\u0002G\u0005\u0001B\u0004\u0005\u0006+\u00011\ta\u0006\u0005\u0006Q\u00011\t!\u000b\u0005\u0006k\u00011\tA\u000e\u0005\u0006{\u00011\tA\u0010\u0002\u0017\u000bb$XM\u001d8bY\u000ecWo\u001d;fe6\u000bg.Y4fe*\u0011q\u0001C\u0001\ng\u000eDW\rZ;mKJT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0003\u0001=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017!C2b]\u000e\u0013X-\u0019;f\u0007\u0001!\"\u0001G\u000e\u0011\u0005AI\u0012B\u0001\u000e\u0012\u0005\u001d\u0011un\u001c7fC:DQ\u0001H\u0001A\u0002u\t\u0011\"\\1ti\u0016\u0014XK\u0015'\u0011\u0005y)cBA\u0010$!\t\u0001\u0013#D\u0001\"\u0015\t\u0011c#\u0001\u0004=e>|GOP\u0005\u0003IE\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0014(\u0005\u0019\u0019FO]5oO*\u0011A%E\u0001\u0014GJ,\u0017\r^3UCN\\7k\u00195fIVdWM\u001d\u000b\u0004U9\"\u0004CA\u0016-\u001b\u00051\u0011BA\u0017\u0007\u00055!\u0016m]6TG\",G-\u001e7fe\")qF\u0001a\u0001a\u0005\u00111o\u0019\t\u0003cIj\u0011\u0001C\u0005\u0003g!\u0011Ab\u00159be.\u001cuN\u001c;fqRDQ\u0001\b\u0002A\u0002u\tac\u0019:fCR,7k\u00195fIVdWM\u001d\"bG.,g\u000e\u001a\u000b\u0005oiZD\b\u0005\u0002,q%\u0011\u0011H\u0002\u0002\u0011'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012DQaL\u0002A\u0002ABQ\u0001H\u0002A\u0002uAQaB\u0002A\u0002)\n!\"\u001b8ji&\fG.\u001b>f)\ry$i\u0011\t\u0003!\u0001K!!Q\t\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u000f\u0011\u0001\rA\u000b\u0005\u0006\t\u0012\u0001\raN\u0001\bE\u0006\u001c7.\u001a8e\u0001"
)
public interface ExternalClusterManager {
   boolean canCreate(final String masterURL);

   TaskScheduler createTaskScheduler(final SparkContext sc, final String masterURL);

   SchedulerBackend createSchedulerBackend(final SparkContext sc, final String masterURL, final TaskScheduler scheduler);

   void initialize(final TaskScheduler scheduler, final SchedulerBackend backend);
}
