package org.apache.spark.status;

import org.apache.spark.SparkConf;
import org.apache.spark.ui.SparkUI;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0004\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u00011\t\u0001\b\u0005\u0006w\u00011\t\u0001\u0010\u0005\u0006\t\u0002!\t!\u0012\u0002\u0017\u0003B\u0004\b*[:u_JL8+\u001a:wKJ\u0004F.^4j]*\u0011q\u0001C\u0001\u0007gR\fG/^:\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c\"\u0001A\b\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\r\u0011\u0005AI\u0012B\u0001\u000e\u0012\u0005\u0011)f.\u001b;\u0002\u001f\r\u0014X-\u0019;f\u0019&\u001cH/\u001a8feN$2!H\u00186!\rqb%\u000b\b\u0003?\u0011r!\u0001I\u0012\u000e\u0003\u0005R!A\t\f\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0012BA\u0013\u0012\u0003\u001d\u0001\u0018mY6bO\u0016L!a\n\u0015\u0003\u0007M+\u0017O\u0003\u0002&#A\u0011!&L\u0007\u0002W)\u0011A\u0006C\u0001\ng\u000eDW\rZ;mKJL!AL\u0016\u0003\u001bM\u0003\u0018M]6MSN$XM\\3s\u0011\u0015\u0001$\u00011\u00012\u0003\u0011\u0019wN\u001c4\u0011\u0005I\u001aT\"\u0001\u0005\n\u0005QB!!C*qCJ\\7i\u001c8g\u0011\u00151$\u00011\u00018\u0003\u0015\u0019Ho\u001c:f!\tA\u0014(D\u0001\u0007\u0013\tQdA\u0001\u000bFY\u0016lWM\u001c;Ue\u0006\u001c7.\u001b8h'R|'/Z\u0001\bg\u0016$X\u000f]+J)\tAR\bC\u0003?\u0007\u0001\u0007q(\u0001\u0002vSB\u0011\u0001IQ\u0007\u0002\u0003*\u0011a\bC\u0005\u0003\u0007\u0006\u0013qa\u00159be.,\u0016*\u0001\u0007eSN\u0004H.Y=Pe\u0012,'/F\u0001G!\t\u0001r)\u0003\u0002I#\t\u0019\u0011J\u001c;"
)
public interface AppHistoryServerPlugin {
   Seq createListeners(final SparkConf conf, final ElementTrackingStore store);

   void setupUI(final SparkUI ui);

   // $FF: synthetic method
   static int displayOrder$(final AppHistoryServerPlugin $this) {
      return $this.displayOrder();
   }

   default int displayOrder() {
      return Integer.MAX_VALUE;
   }

   static void $init$(final AppHistoryServerPlugin $this) {
   }
}
