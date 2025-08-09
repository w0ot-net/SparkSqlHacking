package org.apache.spark.sql.hive;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i:a\u0001B\u0003\t\u0002\u0015yaAB\t\u0006\u0011\u0003)!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q$A\u0007ISZ,G+\u00192mKV#\u0018\u000e\u001c\u0006\u0003\r\u001d\tA\u0001[5wK*\u0011\u0001\"C\u0001\u0004gFd'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0011\u0005A\tQ\"A\u0003\u0003\u001b!Kg/\u001a+bE2,W\u000b^5m'\t\t1\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\"A\u0014d_:4\u0017nZ;sK*{'\r\u0015:pa\u0016\u0014H/[3t\r>\u00148\u000b^8sC\u001e,\u0007*\u00198eY\u0016\u0014H\u0003\u0002\u0010\"]U\u0002\"\u0001F\u0010\n\u0005\u0001*\"\u0001B+oSRDQAI\u0002A\u0002\r\n\u0011\u0002^1cY\u0016$Um]2\u0011\u0005\u0011bS\"A\u0013\u000b\u0005\u0019:\u0013\u0001\u00029mC:T!\u0001K\u0015\u0002\u0005Ed'B\u0001\u0004+\u0015\tY3\"\u0001\u0004iC\u0012|w\u000e]\u0005\u0003[\u0015\u0012\u0011\u0002V1cY\u0016$Um]2\t\u000b=\u001a\u0001\u0019\u0001\u0019\u0002\t\r|gN\u001a\t\u0003cMj\u0011A\r\u0006\u0003_)J!\u0001\u000e\u001a\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0011\u001514\u00011\u00018\u0003\u0015Ig\u000e];u!\t!\u0002(\u0003\u0002:+\t9!i\\8mK\u0006t\u0007"
)
public final class HiveTableUtil {
   public static void configureJobPropertiesForStorageHandler(final TableDesc tableDesc, final Configuration conf, final boolean input) {
      HiveTableUtil$.MODULE$.configureJobPropertiesForStorageHandler(tableDesc, conf, input);
   }
}
