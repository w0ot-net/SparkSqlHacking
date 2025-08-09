package org.apache.spark.sql.hive.thriftserver;

import org.apache.spark.SparkContext;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e;a\u0001D\u0007\t\u0002=IbAB\u000e\u000e\u0011\u0003yA\u0004C\u0003*\u0003\u0011\u00051\u0006C\u0005-\u0003\u0001\u0007\t\u0019!C\u0001[!I!'\u0001a\u0001\u0002\u0004%\ta\r\u0005\ns\u0005\u0001\r\u0011!Q!\n9B\u0011BO\u0001A\u0002\u0003\u0007I\u0011A\u001e\t\u0013\u0001\u000b\u0001\u0019!a\u0001\n\u0003\t\u0005\"C\"\u0002\u0001\u0004\u0005\t\u0015)\u0003=\u0011\u0015!\u0015\u0001\"\u0001F\u0011\u00151\u0015\u0001\"\u0001H\u0011\u001di\u0015!%A\u0005\u00029\u000b1b\u00159be.\u001c\u0016\u000bT#om*\u0011abD\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003!E\tA\u0001[5wK*\u0011!cE\u0001\u0004gFd'B\u0001\u000b\u0016\u0003\u0015\u0019\b/\u0019:l\u0015\t1r#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00021\u0005\u0019qN]4\u0011\u0005i\tQ\"A\u0007\u0003\u0017M\u0003\u0018M]6T#2+eN^\n\u0004\u0003u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\r\u0005\u0002%O5\tQE\u0003\u0002''\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002)K\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003e\tAb\u001d9be.\u001cVm]:j_:,\u0012A\f\t\u0003_Aj\u0011!E\u0005\u0003cE\u0011Ab\u00159be.\u001cVm]:j_:\f\u0001c\u001d9be.\u001cVm]:j_:|F%Z9\u0015\u0005Q:\u0004C\u0001\u00106\u0013\t1tD\u0001\u0003V]&$\bb\u0002\u001d\u0005\u0003\u0003\u0005\rAL\u0001\u0004q\u0012\n\u0014!D:qCJ\\7+Z:tS>t\u0007%\u0001\u0007ta\u0006\u00148nQ8oi\u0016DH/F\u0001=!\tid(D\u0001\u0014\u0013\ty4C\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/\u0001\tta\u0006\u00148nQ8oi\u0016DHo\u0018\u0013fcR\u0011AG\u0011\u0005\bq\u001d\t\t\u00111\u0001=\u00035\u0019\b/\u0019:l\u0007>tG/\u001a=uA\u0005!\u0011N\\5u)\u0005!\u0014\u0001B:u_B$\"\u0001\u000e%\t\u000f%S\u0001\u0013!a\u0001\u0015\u0006AQ\r_5u\u0007>$W\r\u0005\u0002\u001f\u0017&\u0011Aj\b\u0002\u0004\u0013:$\u0018AD:u_B$C-\u001a4bk2$H%M\u000b\u0002\u001f*\u0012!\nU\u0016\u0002#B\u0011!kV\u0007\u0002'*\u0011A+V\u0001\nk:\u001c\u0007.Z2lK\u0012T!AV\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Y'\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public final class SparkSQLEnv {
   public static int stop$default$1() {
      return SparkSQLEnv$.MODULE$.stop$default$1();
   }

   public static void stop(final int exitCode) {
      SparkSQLEnv$.MODULE$.stop(exitCode);
   }

   public static void init() {
      SparkSQLEnv$.MODULE$.init();
   }

   public static void sparkContext_$eq(final SparkContext x$1) {
      SparkSQLEnv$.MODULE$.sparkContext_$eq(x$1);
   }

   public static SparkContext sparkContext() {
      return SparkSQLEnv$.MODULE$.sparkContext();
   }

   public static void sparkSession_$eq(final SparkSession x$1) {
      SparkSQLEnv$.MODULE$.sparkSession_$eq(x$1);
   }

   public static SparkSession sparkSession() {
      return SparkSQLEnv$.MODULE$.sparkSession();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SparkSQLEnv$.MODULE$.LogStringContext(sc);
   }
}
