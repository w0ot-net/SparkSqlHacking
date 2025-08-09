package org.apache.spark.deploy.history;

import org.apache.spark.scheduler.SparkListenerInterface;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00052\u0001BA\u0002\u0011\u0002G\u0005q!\u0004\u0005\u00065\u00011\t\u0001\b\u0002\u0013\u000bZ,g\u000e\u001e$jYR,'OQ;jY\u0012,'O\u0003\u0002\u0005\u000b\u00059\u0001.[:u_JL(B\u0001\u0004\b\u0003\u0019!W\r\u001d7ps*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xmE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u0019\u001b\u00051\"BA\f\b\u0003%\u00198\r[3ek2,'/\u0003\u0002\u001a-\t12\u000b]1sW2K7\u000f^3oKJLe\u000e^3sM\u0006\u001cW-\u0001\u0007de\u0016\fG/\u001a$jYR,'o\u0001\u0001\u0015\u0003u\u0001\"AH\u0010\u000e\u0003\rI!\u0001I\u0002\u0003\u0017\u00153XM\u001c;GS2$XM\u001d"
)
public interface EventFilterBuilder extends SparkListenerInterface {
   EventFilter createFilter();
}
