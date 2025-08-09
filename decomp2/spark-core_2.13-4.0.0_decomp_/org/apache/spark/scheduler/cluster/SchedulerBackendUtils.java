package org.apache.spark.scheduler.cluster;

import org.apache.spark.SparkConf;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:aa\u0002\u0005\t\u00021\u0011bA\u0002\u000b\t\u0011\u0003aQ\u0003C\u0003\u001d\u0003\u0011\u0005a\u0004C\u0004 \u0003\t\u0007I\u0011\u0001\u0011\t\r\u0011\n\u0001\u0015!\u0003\"\u0011\u0015)\u0013\u0001\"\u0001'\u0011\u001dy\u0013!%A\u0005\u0002A\nQcU2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3Vi&d7O\u0003\u0002\n\u0015\u000591\r\\;ti\u0016\u0014(BA\u0006\r\u0003%\u00198\r[3ek2,'O\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h!\t\u0019\u0012!D\u0001\t\u0005U\u00196\r[3ek2,'OQ1dW\u0016tG-\u0016;jYN\u001c\"!\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\n\u00021\u0011+e)Q+M)~sU+\u0014\"F%~+\u0005,R\"V)>\u00136+F\u0001\"!\t9\"%\u0003\u0002$1\t\u0019\u0011J\u001c;\u00023\u0011+e)Q+M)~sU+\u0014\"F%~+\u0005,R\"V)>\u00136\u000bI\u0001\u001fO\u0016$\u0018J\\5uS\u0006dG+\u0019:hKR,\u00050Z2vi>\u0014h*^7cKJ$2!I\u0014.\u0011\u0015AS\u00011\u0001*\u0003\u0011\u0019wN\u001c4\u0011\u0005)ZS\"\u0001\u0007\n\u00051b!!C*qCJ\\7i\u001c8g\u0011\u001dqS\u0001%AA\u0002\u0005\nAB\\;n\u000bb,7-\u001e;peN\f\u0001fZ3u\u0013:LG/[1m)\u0006\u0014x-\u001a;Fq\u0016\u001cW\u000f^8s\u001dVl'-\u001a:%I\u00164\u0017-\u001e7uII*\u0012!\r\u0016\u0003CIZ\u0013a\r\t\u0003iej\u0011!\u000e\u0006\u0003m]\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005aB\u0012AC1o]>$\u0018\r^5p]&\u0011!(\u000e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public final class SchedulerBackendUtils {
   public static int getInitialTargetExecutorNumber$default$2() {
      return SchedulerBackendUtils$.MODULE$.getInitialTargetExecutorNumber$default$2();
   }

   public static int getInitialTargetExecutorNumber(final SparkConf conf, final int numExecutors) {
      return SchedulerBackendUtils$.MODULE$.getInitialTargetExecutorNumber(conf, numExecutors);
   }

   public static int DEFAULT_NUMBER_EXECUTORS() {
      return SchedulerBackendUtils$.MODULE$.DEFAULT_NUMBER_EXECUTORS();
   }
}
