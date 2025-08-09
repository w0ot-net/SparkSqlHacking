package org.apache.spark.mllib.util;

import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I<Qa\u0002\u0005\t\u0002M1Q!\u0006\u0005\t\u0002YAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001Bq\u0001T\u0001\u0012\u0002\u0013\u0005Q\nC\u0004X\u0003E\u0005I\u0011\u0001-\t\u000bi\u000bA\u0011A.\u0002?1{w-[:uS\u000e\u0014Vm\u001a:fgNLwN\u001c#bi\u0006<UM\\3sCR|'O\u0003\u0002\n\u0015\u0005!Q\u000f^5m\u0015\tYA\"A\u0003nY2L'M\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h\u0007\u0001\u0001\"\u0001F\u0001\u000e\u0003!\u0011q\u0004T8hSN$\u0018n\u0019*fOJ,7o]5p]\u0012\u000bG/Y$f]\u0016\u0014\u0018\r^8s'\t\tq\u0003\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\t1cZ3oKJ\fG/\u001a'pO&\u001cH/[2S\t\u0012#r!I\u00174qiz\u0014\tE\u0002#K\u001dj\u0011a\t\u0006\u0003I1\t1A\u001d3e\u0013\t13EA\u0002S\t\u0012\u0003\"\u0001K\u0016\u000e\u0003%R!A\u000b\u0006\u0002\u0015I,wM]3tg&|g.\u0003\u0002-S\taA*\u00192fY\u0016$\u0007k\\5oi\")af\u0001a\u0001_\u0005\u00111o\u0019\t\u0003aEj\u0011\u0001D\u0005\u0003e1\u0011Ab\u00159be.\u001cuN\u001c;fqRDQ\u0001N\u0002A\u0002U\n\u0011B\\3yC6\u0004H.Z:\u0011\u0005a1\u0014BA\u001c\u001a\u0005\rIe\u000e\u001e\u0005\u0006s\r\u0001\r!N\u0001\n]\u001a,\u0017\r^;sKNDQaO\u0002A\u0002q\n1!\u001a9t!\tAR(\u0003\u0002?3\t1Ai\\;cY\u0016Dq\u0001Q\u0002\u0011\u0002\u0003\u0007Q'\u0001\u0004oa\u0006\u0014Ho\u001d\u0005\b\u0005\u000e\u0001\n\u00111\u0001=\u0003\u001d\u0001(o\u001c2P]\u0016D3a\u0001#K!\t)\u0005*D\u0001G\u0015\t9E\"\u0001\u0006b]:|G/\u0019;j_:L!!\u0013$\u0003\u000bMKgnY3\"\u0003-\u000bQ\u0001\r\u00189]A\nQdZ3oKJ\fG/\u001a'pO&\u001cH/[2S\t\u0012#C-\u001a4bk2$H%N\u000b\u0002\u001d*\u0012QgT\u0016\u0002!B\u0011\u0011+V\u0007\u0002%*\u00111\u000bV\u0001\nk:\u001c\u0007.Z2lK\u0012T!aR\r\n\u0005Y\u0013&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006ir-\u001a8fe\u0006$X\rT8hSN$\u0018n\u0019*E\t\u0012\"WMZ1vYR$c'F\u0001ZU\tat*\u0001\u0003nC&tGC\u0001/`!\tAR,\u0003\u0002_3\t!QK\\5u\u0011\u0015\u0001g\u00011\u0001b\u0003\u0011\t'oZ:\u0011\u0007a\u0011G-\u0003\u0002d3\t)\u0011I\u001d:bsB\u0011Q\r\u001c\b\u0003M*\u0004\"aZ\r\u000e\u0003!T!!\u001b\n\u0002\rq\u0012xn\u001c;?\u0013\tY\u0017$\u0001\u0004Qe\u0016$WMZ\u0005\u0003[:\u0014aa\u0015;sS:<'BA6\u001aQ\r1AI\u0013\u0015\u0004\u0003\u0011S\u0005f\u0001\u0001E\u0015\u0002"
)
public final class LogisticRegressionDataGenerator {
   public static void main(final String[] args) {
      LogisticRegressionDataGenerator$.MODULE$.main(args);
   }

   public static double generateLogisticRDD$default$6() {
      return LogisticRegressionDataGenerator$.MODULE$.generateLogisticRDD$default$6();
   }

   public static int generateLogisticRDD$default$5() {
      return LogisticRegressionDataGenerator$.MODULE$.generateLogisticRDD$default$5();
   }

   public static RDD generateLogisticRDD(final SparkContext sc, final int nexamples, final int nfeatures, final double eps, final int nparts, final double probOne) {
      return LogisticRegressionDataGenerator$.MODULE$.generateLogisticRDD(sc, nexamples, nfeatures, eps, nparts, probOne);
   }
}
