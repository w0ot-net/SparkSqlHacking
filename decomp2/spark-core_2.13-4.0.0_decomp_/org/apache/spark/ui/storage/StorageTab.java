package org.apache.spark.ui.storage;

import org.apache.spark.status.AppStatusStore;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUITab;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192Q\u0001B\u0003\u0001\u000f=A\u0001\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\t3\u0001\u0011\t\u0011)A\u00055!)\u0001\u0005\u0001C\u0001C\tQ1\u000b^8sC\u001e,G+\u00192\u000b\u0005\u00199\u0011aB:u_J\fw-\u001a\u0006\u0003\u0011%\t!!^5\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\"\u0001\u0001\t\u0011\u0005E\u0011R\"A\u0004\n\u0005M9!AC*qCJ\\W+\u0013+bE\u00061\u0001/\u0019:f]R\u001c\u0001\u0001\u0005\u0002\u0012/%\u0011\u0001d\u0002\u0002\b'B\f'o[+J\u0003\u0015\u0019Ho\u001c:f!\tYb$D\u0001\u001d\u0015\ti\u0012\"\u0001\u0004ti\u0006$Xo]\u0005\u0003?q\u0011a\"\u00119q'R\fG/^:Ti>\u0014X-\u0001\u0004=S:LGO\u0010\u000b\u0004E\u0011*\u0003CA\u0012\u0001\u001b\u0005)\u0001\"\u0002\u000b\u0004\u0001\u00041\u0002\"B\r\u0004\u0001\u0004Q\u0002"
)
public class StorageTab extends SparkUITab {
   public StorageTab(final SparkUI parent, final AppStatusStore store) {
      super(parent, "storage");
      this.attachPage(new StoragePage(this, store));
      this.attachPage(new RDDPage(this, store));
   }
}
