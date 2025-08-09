package org.apache.spark.sql;

import org.apache.spark.SparkContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113\u0001BB\u0004\u0011\u0002\u0007\u0005qa\u0004\u0005\u0006-\u0001!\t\u0001\u0007\u0003\u00079\u0001\u0011\taB\u000f\t\u000b\u0015\u0002a\u0011\u0001\u0014\t\u000be\u0002A\u0011\u0001\u001e\t\u000b\u0001\u0003A\u0011\u0001\r\u0003'M\u000bFjQ8oi\u0016DHoQ8na\u0006t\u0017n\u001c8\u000b\u0005!I\u0011aA:rY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u00023A\u0011\u0011CG\u0005\u00037I\u0011A!\u00168ji\nq1+\u0015'D_:$X\r\u001f;J[Bd\u0017C\u0001\u0010\"!\t\tr$\u0003\u0002!%\t9aj\u001c;iS:<\u0007C\u0001\u0012$\u001b\u00059\u0011B\u0001\u0013\b\u0005)\u0019\u0016\u000bT\"p]R,\u0007\u0010^\u0001\fO\u0016$xJ]\"sK\u0006$X\r\u0006\u0002(SA\u0011\u0001FA\u0007\u0002\u0001!)!f\u0001a\u0001W\u0005a1\u000f]1sW\u000e{g\u000e^3yiB\u0011A&L\u0007\u0002\u0013%\u0011a&\u0003\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0015\u0007\u0007A\u001aDGN\u001c\u0011\u0005E\t\u0014B\u0001\u001a\u0013\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005)\u0014\u0001I+tK\u0002\u001a\u0006/\u0019:l'\u0016\u001c8/[8o]\t,\u0018\u000e\u001c3fe\u0002Jgn\u001d;fC\u0012\fQa]5oG\u0016\f\u0013\u0001O\u0001\u0006e9\u0002d\u0006M\u0001\ng\u0016$\u0018i\u0019;jm\u0016$\"!G\u001e\t\u000bq\"\u0001\u0019A\u0014\u0002\u0015M\fHnQ8oi\u0016DH\u000f\u000b\u0004\u0005aMrdgN\u0011\u0002\u007f\u0005ISk]3!'B\f'o[*fgNLwN\u001c\u0018tKR\f5\r^5wKN+7o]5p]\u0002Jgn\u001d;fC\u0012\f1b\u00197fCJ\f5\r^5wK\"2Q\u0001M\u001aCm]\n\u0013aQ\u0001,+N,\u0007e\u00159be.\u001cVm]:j_:t3\r\\3be\u0006\u001bG/\u001b<f'\u0016\u001c8/[8oA%t7\u000f^3bI\u0002"
)
public interface SQLContextCompanion {
   /** @deprecated */
   SQLContext getOrCreate(final SparkContext sparkContext);

   // $FF: synthetic method
   static void setActive$(final SQLContextCompanion $this, final SQLContext sqlContext) {
      $this.setActive(sqlContext);
   }

   /** @deprecated */
   default void setActive(final SQLContext sqlContext) {
      SparkSession$.MODULE$.setActiveSession(sqlContext.sparkSession());
   }

   // $FF: synthetic method
   static void clearActive$(final SQLContextCompanion $this) {
      $this.clearActive();
   }

   /** @deprecated */
   default void clearActive() {
      SparkSession$.MODULE$.clearActiveSession();
   }

   static void $init$(final SQLContextCompanion $this) {
   }
}
