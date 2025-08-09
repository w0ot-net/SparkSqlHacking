package org.apache.spark.metrics;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y:a!\u0005\n\t\u0002QQbA\u0002\u000f\u0013\u0011\u0003!R\u0004C\u0003%\u0003\u0011\u0005a\u0005C\u0004(\u0003\t\u0007I\u0011\u0001\u0015\t\rE\n\u0001\u0015!\u0003*\u0011\u001d\u0011\u0014A1A\u0005\u0002!BaaM\u0001!\u0002\u0013I\u0003b\u0002\u001b\u0002\u0005\u0004%\t\u0001\u000b\u0005\u0007k\u0005\u0001\u000b\u0011B\u0015\t\u000fY\n!\u0019!C\u0001Q!1q'\u0001Q\u0001\n%Bq\u0001O\u0001C\u0002\u0013\u0005\u0001\u0006\u0003\u0004:\u0003\u0001\u0006I!\u000b\u0005\bu\u0005\u0011\r\u0011\"\u0001)\u0011\u0019Y\u0014\u0001)A\u0005S!9A(\u0001b\u0001\n\u0003A\u0003BB\u001f\u0002A\u0003%\u0011&\u0001\fNKR\u0014\u0018nY:TsN$X-\\%ogR\fgnY3t\u0015\t\u0019B#A\u0004nKR\u0014\u0018nY:\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u0004\"aG\u0001\u000e\u0003I\u0011a#T3ue&\u001c7oU=ti\u0016l\u0017J\\:uC:\u001cWm]\n\u0003\u0003y\u0001\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\ta!T!T)\u0016\u0013V#A\u0015\u0011\u0005)zS\"A\u0016\u000b\u00051j\u0013\u0001\u00027b]\u001eT\u0011AL\u0001\u0005U\u00064\u0018-\u0003\u00021W\t11\u000b\u001e:j]\u001e\fq!T!T)\u0016\u0013\u0006%\u0001\u0007B!Bc\u0015jQ!U\u0013>s5+A\u0007B!Bc\u0015jQ!U\u0013>s5\u000bI\u0001\u0007/>\u00136*\u0012*\u0002\u000f]{%kS#SA\u0005AQ\tW#D+R{%+A\u0005F1\u0016\u001bU\u000bV(SA\u00051AIU%W\u000bJ\u000bq\u0001\u0012*J-\u0016\u0013\u0006%A\bT\u0011V3e\tT#`'\u0016\u0013f+S\"F\u0003A\u0019\u0006*\u0016$G\u0019\u0016{6+\u0012*W\u0013\u000e+\u0005%\u0001\nB!Bc\u0015jQ!U\u0013>su,T!T)\u0016\u0013\u0016aE!Q!2K5)\u0011+J\u001f:{V*Q*U\u000bJ\u0003\u0003"
)
public final class MetricsSystemInstances {
   public static String APPLICATION_MASTER() {
      return MetricsSystemInstances$.MODULE$.APPLICATION_MASTER();
   }

   public static String SHUFFLE_SERVICE() {
      return MetricsSystemInstances$.MODULE$.SHUFFLE_SERVICE();
   }

   public static String DRIVER() {
      return MetricsSystemInstances$.MODULE$.DRIVER();
   }

   public static String EXECUTOR() {
      return MetricsSystemInstances$.MODULE$.EXECUTOR();
   }

   public static String WORKER() {
      return MetricsSystemInstances$.MODULE$.WORKER();
   }

   public static String APPLICATIONS() {
      return MetricsSystemInstances$.MODULE$.APPLICATIONS();
   }

   public static String MASTER() {
      return MetricsSystemInstances$.MODULE$.MASTER();
   }
}
