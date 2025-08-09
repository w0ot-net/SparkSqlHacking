package org.apache.spark.sql.hive.thriftserver.ui;

import org.apache.spark.SparkConf;
import org.apache.spark.status.AppHistoryServerPlugin;
import org.apache.spark.status.ElementTrackingStore;
import org.apache.spark.ui.SparkUI;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0002\u0004\u0001+!)!\u0005\u0001C\u0001G!)a\u0005\u0001C!O!)Q\t\u0001C!\r\")\u0001\u000b\u0001C!#\n!\u0003*\u001b<f)\"\u0014\u0018N\u001a;TKJ4XM\u001d\u001aISN$xN]=TKJ4XM\u001d)mk\u001eLgN\u0003\u0002\b\u0011\u0005\u0011Q/\u001b\u0006\u0003\u0013)\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!a\u0003\u0007\u0002\t!Lg/\u001a\u0006\u0003\u001b9\t1a]9m\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001A\n\u0004\u0001Ya\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\r\u0005\u0002\u001eA5\taD\u0003\u0002 \u001d\u000511\u000f^1ukNL!!\t\u0010\u0003-\u0005\u0003\b\u000fS5ti>\u0014\u0018pU3sm\u0016\u0014\b\u000b\\;hS:\fa\u0001P5oSRtD#\u0001\u0013\u0011\u0005\u0015\u0002Q\"\u0001\u0004\u0002\u001f\r\u0014X-\u0019;f\u0019&\u001cH/\u001a8feN$2\u0001\u000b\u001eA!\rI\u0013\u0007\u000e\b\u0003U=r!a\u000b\u0018\u000e\u00031R!!\f\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0012B\u0001\u0019\u0019\u0003\u001d\u0001\u0018mY6bO\u0016L!AM\u001a\u0003\u0007M+\u0017O\u0003\u000211A\u0011Q\u0007O\u0007\u0002m)\u0011qGD\u0001\ng\u000eDW\rZ;mKJL!!\u000f\u001c\u0003\u001bM\u0003\u0018M]6MSN$XM\\3s\u0011\u0015Y$\u00011\u0001=\u0003\u0011\u0019wN\u001c4\u0011\u0005urT\"\u0001\b\n\u0005}r!!C*qCJ\\7i\u001c8g\u0011\u0015\t%\u00011\u0001C\u0003\u0015\u0019Ho\u001c:f!\ti2)\u0003\u0002E=\t!R\t\\3nK:$HK]1dW&twm\u0015;pe\u0016\fqa]3ukB,\u0016\n\u0006\u0002H\u0015B\u0011q\u0003S\u0005\u0003\u0013b\u0011A!\u00168ji\")qa\u0001a\u0001\u0017B\u0011AJT\u0007\u0002\u001b*\u0011qAD\u0005\u0003\u001f6\u0013qa\u00159be.,\u0016*\u0001\u0007eSN\u0004H.Y=Pe\u0012,'/F\u0001S!\t92+\u0003\u0002U1\t\u0019\u0011J\u001c;"
)
public class HiveThriftServer2HistoryServerPlugin implements AppHistoryServerPlugin {
   public Seq createListeners(final SparkConf conf, final ElementTrackingStore store) {
      return new .colon.colon(new HiveThriftServer2Listener(store, conf, scala.None..MODULE$, false), scala.collection.immutable.Nil..MODULE$);
   }

   public void setupUI(final SparkUI ui) {
      HiveThriftServer2AppStatusStore store = new HiveThriftServer2AppStatusStore(ui.store().store());
      if (store.getSessionCount() > 0L) {
         new ThriftServerTab(store, ui);
      }
   }

   public int displayOrder() {
      return 1;
   }

   public HiveThriftServer2HistoryServerPlugin() {
      AppHistoryServerPlugin.$init$(this);
   }
}
