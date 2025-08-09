package org.apache.spark.scheduler;

import java.util.Properties;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2\u0001\u0002B\u0003\u0011\u0002G\u0005q!\u0004\u0005\u0006)\u00011\tA\u0006\u0005\u00067\u00011\t\u0001\b\u0005\u0006A\u00011\t!\t\u0002\u0013'\u000eDW\rZ;mC\ndWMQ;jY\u0012,'O\u0003\u0002\u0007\u000f\u0005I1o\u00195fIVdWM\u001d\u0006\u0003\u0011%\tQa\u001d9be.T!AC\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0011aA8sON\u0011\u0001A\u0004\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\u0011I|w\u000e\u001e)p_2\u001c\u0001!F\u0001\u0018!\tA\u0012$D\u0001\u0006\u0013\tQRA\u0001\u0003Q_>d\u0017A\u00032vS2$\u0007k\\8mgR\tQ\u0004\u0005\u0002\u0010=%\u0011q\u0004\u0005\u0002\u0005+:LG/A\tbI\u0012$\u0016m]6TKRl\u0015M\\1hKJ$2!\b\u0012(\u0011\u0015\u00193\u00011\u0001%\u0003\u001di\u0017M\\1hKJ\u0004\"\u0001G\u0013\n\u0005\u0019*!aC*dQ\u0016$W\u000f\\1cY\u0016DQ\u0001K\u0002A\u0002%\n!\u0002\u001d:pa\u0016\u0014H/[3t!\tQs&D\u0001,\u0015\taS&\u0001\u0003vi&d'\"\u0001\u0018\u0002\t)\fg/Y\u0005\u0003a-\u0012!\u0002\u0015:pa\u0016\u0014H/[3t\u0001"
)
public interface SchedulableBuilder {
   Pool rootPool();

   void buildPools();

   void addTaskSetManager(final Schedulable manager, final Properties properties);
}
