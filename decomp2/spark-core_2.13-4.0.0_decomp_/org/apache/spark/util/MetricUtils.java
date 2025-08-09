package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!<a\u0001F\u000b\t\u0002]ibAB\u0010\u0016\u0011\u00039\u0002\u0005C\u0003(\u0003\u0011\u0005\u0011\u0006C\u0004+\u0003\t\u0007I\u0011A\u0016\t\r]\n\u0001\u0015!\u0003-\u0011\u001dA\u0014A1A\u0005\u0002-Ba!O\u0001!\u0002\u0013a\u0003b\u0002\u001e\u0002\u0005\u0004%\ta\u000b\u0005\u0007w\u0005\u0001\u000b\u0011\u0002\u0017\t\u000fq\n!\u0019!C\u0001W!1Q(\u0001Q\u0001\n1BqAP\u0001C\u0002\u0013\u00051\u0006\u0003\u0004@\u0003\u0001\u0006I\u0001\f\u0005\b\u0001\u0006\u0011\r\u0011\"\u0003B\u0011\u0019)\u0015\u0001)A\u0005\u0005\"9a)\u0001b\u0001\n\u00139\u0005BB(\u0002A\u0003%\u0001\nC\u0003Q\u0003\u0011%\u0011\u000bC\u0003X\u0003\u0011\u0005\u0001\fC\u0003_\u0003\u0011\u0005q,A\u0006NKR\u0014\u0018nY+uS2\u001c(B\u0001\f\u0018\u0003\u0011)H/\u001b7\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u0004\"AH\u0001\u000e\u0003U\u00111\"T3ue&\u001cW\u000b^5mgN\u0011\u0011!\t\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!H\u0001\u000b'Vku,T#U%&\u001bU#\u0001\u0017\u0011\u00055\"dB\u0001\u00183!\ty3%D\u00011\u0015\t\t\u0004&\u0001\u0004=e>|GOP\u0005\u0003g\r\na\u0001\u0015:fI\u00164\u0017BA\u001b7\u0005\u0019\u0019FO]5oO*\u00111gI\u0001\f'Vku,T#U%&\u001b\u0005%A\u0006T\u0013j+u,T#U%&\u001b\u0015\u0001D*J5\u0016{V*\u0012+S\u0013\u000e\u0003\u0013!\u0004+J\u001b&suiX'F)JK5)\u0001\bU\u00136KejR0N\u000bR\u0013\u0016j\u0011\u0011\u0002!9\u001bv\fV%N\u0013:;u,T#U%&\u001b\u0015!\u0005(T?RKU*\u0013(H?6+EKU%DA\u0005q\u0011IV#S\u0003\u001e+u,T#U%&\u001b\u0015aD!W\u000bJ\u000bu)R0N\u000bR\u0013\u0016j\u0011\u0011\u0002!\t\f7/\u001a$pe\u00063x-T3ue&\u001cW#\u0001\"\u0011\u0005\t\u001a\u0015B\u0001#$\u0005\rIe\u000e^\u0001\u0012E\u0006\u001cXMR8s\u0003Z<W*\u001a;sS\u000e\u0004\u0013aE'F)JK5iU0O\u00036+ulU+G\r&CV#\u0001%\u0011\u0005%sU\"\u0001&\u000b\u0005-c\u0015\u0001\u00027b]\u001eT\u0011!T\u0001\u0005U\u00064\u0018-\u0003\u00026\u0015\u0006!R*\u0012+S\u0013\u000e\u001bvLT!N\u000b~\u001bVK\u0012$J1\u0002\na\u0002^8Ok6\u0014WM\u001d$pe6\fG\u000f\u0006\u0002-%\")1+\u0005a\u0001)\u0006)a/\u00197vKB\u0011!%V\u0005\u0003-\u000e\u0012A\u0001T8oO\u0006qQ.\u001a;sS\u000etU-\u001a3t\u001b\u0006DHCA-]!\t\u0011#,\u0003\u0002\\G\t9!i\\8mK\u0006t\u0007\"B/\u0013\u0001\u0004a\u0013aC7fiJL7m\u001d+za\u0016\f1b\u001d;sS:<g+\u00197vKR!A\u0006Y1g\u0011\u0015i6\u00031\u0001-\u0011\u0015\u00117\u00031\u0001d\u0003\u00191\u0018\r\\;fgB\u0019!\u0005\u001a+\n\u0005\u0015\u001c#!B!se\u0006L\b\"B4\u0014\u0001\u0004\u0019\u0017AC7bq6+GO]5dg\u0002"
)
public final class MetricUtils {
   public static String stringValue(final String metricsType, final long[] values, final long[] maxMetrics) {
      return MetricUtils$.MODULE$.stringValue(metricsType, values, maxMetrics);
   }

   public static boolean metricNeedsMax(final String metricsType) {
      return MetricUtils$.MODULE$.metricNeedsMax(metricsType);
   }

   public static String AVERAGE_METRIC() {
      return MetricUtils$.MODULE$.AVERAGE_METRIC();
   }

   public static String NS_TIMING_METRIC() {
      return MetricUtils$.MODULE$.NS_TIMING_METRIC();
   }

   public static String TIMING_METRIC() {
      return MetricUtils$.MODULE$.TIMING_METRIC();
   }

   public static String SIZE_METRIC() {
      return MetricUtils$.MODULE$.SIZE_METRIC();
   }

   public static String SUM_METRIC() {
      return MetricUtils$.MODULE$.SUM_METRIC();
   }
}
