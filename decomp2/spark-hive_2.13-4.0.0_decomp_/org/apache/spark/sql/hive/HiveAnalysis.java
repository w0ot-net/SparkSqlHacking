package org.apache.spark.sql.hive;

import org.apache.spark.internal.Logging;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.internal.SQLConf;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005):Q\u0001B\u0003\t\u0002A1QAE\u0003\t\u0002MAQ\u0001J\u0001\u0005\u0002\u0015BQAJ\u0001\u0005B\u001d\nA\u0002S5wK\u0006s\u0017\r\\=tSNT!AB\u0004\u0002\t!Lg/\u001a\u0006\u0003\u0011%\t1a]9m\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7\u0001\u0001\t\u0003#\u0005i\u0011!\u0002\u0002\r\u0011&4X-\u00118bYf\u001c\u0018n]\n\u0003\u0003Q\u00012!\u0006\u000e\u001d\u001b\u00051\"BA\f\u0019\u0003\u0015\u0011X\u000f\\3t\u0015\tIr!\u0001\u0005dCR\fG._:u\u0013\tYbC\u0001\u0003Sk2,\u0007CA\u000f#\u001b\u0005q\"BA\u0010!\u0003\u001dawnZ5dC2T!!\t\r\u0002\u000bAd\u0017M\\:\n\u0005\rr\"a\u0003'pO&\u001c\u0017\r\u001c)mC:\fa\u0001P5oSRtD#\u0001\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005qA\u0003\"B\u0015\u0004\u0001\u0004a\u0012\u0001\u00029mC:\u0004"
)
public final class HiveAnalysis {
   public static LogicalPlan apply(final LogicalPlan plan) {
      return HiveAnalysis$.MODULE$.apply(plan);
   }

   public static String ruleName() {
      return HiveAnalysis$.MODULE$.ruleName();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return HiveAnalysis$.MODULE$.LogStringContext(sc);
   }

   public static SQLConf conf() {
      return HiveAnalysis$.MODULE$.conf();
   }
}
