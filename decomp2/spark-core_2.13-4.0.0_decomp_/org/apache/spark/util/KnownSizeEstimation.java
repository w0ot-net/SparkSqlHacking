package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a1\u0001BA\u0002\u0011\u0002G\u0005Qa\u0003\u0005\u0006%\u00011\t\u0001\u0006\u0002\u0014\u0017:|wO\\*ju\u0016,5\u000f^5nCRLwN\u001c\u0006\u0003\t\u0015\tA!\u001e;jY*\u0011aaB\u0001\u0006gB\f'o\u001b\u0006\u0003\u0011%\ta!\u00199bG\",'\"\u0001\u0006\u0002\u0007=\u0014xm\u0005\u0002\u0001\u0019A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\fQ\"Z:uS6\fG/\u001a3TSj,7\u0001A\u000b\u0002+A\u0011QBF\u0005\u0003/9\u0011A\u0001T8oO\u0002"
)
public interface KnownSizeEstimation {
   long estimatedSize();
}
