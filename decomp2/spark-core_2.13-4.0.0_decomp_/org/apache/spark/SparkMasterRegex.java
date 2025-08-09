package org.apache.spark;

import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005Q:Q!\u0004\b\t\nU1Qa\u0006\b\t\naAQaH\u0001\u0005\u0002\u0001Bq!I\u0001C\u0002\u0013\u0005!\u0005\u0003\u0004,\u0003\u0001\u0006Ia\t\u0005\bY\u0005\u0011\r\u0011\"\u0001#\u0011\u0019i\u0013\u0001)A\u0005G!9a&\u0001b\u0001\n\u0003\u0011\u0003BB\u0018\u0002A\u0003%1\u0005C\u00041\u0003\t\u0007I\u0011\u0001\u0012\t\rE\n\u0001\u0015!\u0003$\u0011\u001d\u0011\u0014A1A\u0005\u0002\tBaaM\u0001!\u0002\u0013\u0019\u0013\u0001E*qCJ\\W*Y:uKJ\u0014VmZ3y\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001\u0001\t\u0003-\u0005i\u0011A\u0004\u0002\u0011'B\f'o['bgR,'OU3hKb\u001c\"!A\r\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tQ#A\u0007M\u001f\u000e\u000bEj\u0018(`%\u0016;U\tW\u000b\u0002GA\u0011A%K\u0007\u0002K)\u0011aeJ\u0001\t[\u0006$8\r[5oO*\u0011\u0001fG\u0001\u0005kRLG.\u0003\u0002+K\t)!+Z4fq\u0006qAjT\"B\u0019~suLU#H\u000bb\u0003\u0013A\u0006'P\u0007\u0006cuLT0G\u0003&cUKU#T?J+u)\u0012-\u0002/1{5)\u0011'`\u001d~3\u0015)\u0013'V%\u0016\u001bvLU#H\u000bb\u0003\u0013a\u0005'P\u0007\u0006cul\u0011'V'R+%k\u0018*F\u000f\u0016C\u0016\u0001\u0006'P\u0007\u0006cul\u0011'V'R+%k\u0018*F\u000f\u0016C\u0006%A\u0006T!\u0006\u00136j\u0018*F\u000f\u0016C\u0016\u0001D*Q\u0003J[uLU#H\u000bb\u0003\u0013\u0001E&V\u0005\u0016\u0013f*\u0012+F'~\u0013ViR#Y\u0003EYUKQ#S\u001d\u0016#ViU0S\u000b\u001e+\u0005\f\t"
)
public final class SparkMasterRegex {
   public static Regex KUBERNETES_REGEX() {
      return SparkMasterRegex$.MODULE$.KUBERNETES_REGEX();
   }

   public static Regex SPARK_REGEX() {
      return SparkMasterRegex$.MODULE$.SPARK_REGEX();
   }

   public static Regex LOCAL_CLUSTER_REGEX() {
      return SparkMasterRegex$.MODULE$.LOCAL_CLUSTER_REGEX();
   }

   public static Regex LOCAL_N_FAILURES_REGEX() {
      return SparkMasterRegex$.MODULE$.LOCAL_N_FAILURES_REGEX();
   }

   public static Regex LOCAL_N_REGEX() {
      return SparkMasterRegex$.MODULE$.LOCAL_N_REGEX();
   }
}
