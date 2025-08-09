package org.apache.spark.streaming.receiver;

import org.apache.spark.storage.StreamBlockId;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192\u0001b\u0001\u0003\u0011\u0002G\u0005aA\u0004\u0005\u0006+\u00011\ta\u0006\u0005\u0006=\u00011\ta\b\u0002\u0019%\u0016\u001cW-\u001b<fI\ncwnY6Ti>\u0014XMU3tk2$(BA\u0003\u0007\u0003!\u0011XmY3jm\u0016\u0014(BA\u0004\t\u0003%\u0019HO]3b[&twM\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\t\u0001q\u0002\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VMZ\u0001\bE2|7m[%e\u0007\u0001)\u0012\u0001\u0007\t\u00033qi\u0011A\u0007\u0006\u00037!\tqa\u001d;pe\u0006<W-\u0003\u0002\u001e5\ti1\u000b\u001e:fC6\u0014En\\2l\u0013\u0012\f!B\\;n%\u0016\u001cwN\u001d3t+\u0005\u0001\u0003c\u0001\t\"G%\u0011!%\u0005\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005A!\u0013BA\u0013\u0012\u0005\u0011auN\\4"
)
public interface ReceivedBlockStoreResult {
   StreamBlockId blockId();

   Option numRecords();
}
