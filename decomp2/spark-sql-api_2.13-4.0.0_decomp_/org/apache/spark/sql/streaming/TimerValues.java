package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005-2qa\u0001\u0003\u0011\u0002G\u0005q\u0002C\u0003\u001f\u0001\u0019\u0005q\u0004C\u0003$\u0001\u0019\u0005qDA\u0006US6,'OV1mk\u0016\u001c(BA\u0003\u0007\u0003%\u0019HO]3b[&twM\u0003\u0002\b\u0011\u0005\u00191/\u001d7\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001!Y\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007CA\f\u001d\u001b\u0005A\"BA\r\u001b\u0003\tIwNC\u0001\u001c\u0003\u0011Q\u0017M^1\n\u0005uA\"\u0001D*fe&\fG.\u001b>bE2,\u0017\u0001H4fi\u000e+(O]3oiB\u0013xnY3tg&tw\rV5nK&sWj\u001d\u000b\u0002AA\u0011\u0011#I\u0005\u0003EI\u0011A\u0001T8oO\u00069r-\u001a;DkJ\u0014XM\u001c;XCR,'/\\1sW&sWj\u001d\u0015\u0003\u0001\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\u0005\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002+O\tAQI^8mm&tw\r"
)
public interface TimerValues extends Serializable {
   long getCurrentProcessingTimeInMs();

   long getCurrentWatermarkInMs();
}
