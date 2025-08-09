package org.apache.spark.ml.util;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593Q\u0001B\u0003\u0002\u0002AAQ\u0001\b\u0001\u0005\u0002uAQA\u000b\u0001\u0007\u0002-BQA\u0011\u0001\u0005B\r\u0013\u0001\"\u0014'SK\u0006$WM\u001d\u0006\u0003\r\u001d\tA!\u001e;jY*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001+\t\t\u0012eE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u0005)\u0011BA\u000e\u0006\u00055\u0011\u0015m]3SK\u0006$wK]5uK\u00061A(\u001b8jiz\"\u0012A\b\t\u00043\u0001y\u0002C\u0001\u0011\"\u0019\u0001!QA\t\u0001C\u0002\r\u0012\u0011\u0001V\t\u0003I\u001d\u0002\"aE\u0013\n\u0005\u0019\"\"a\u0002(pi\"Lgn\u001a\t\u0003'!J!!\u000b\u000b\u0003\u0007\u0005s\u00170\u0001\u0003m_\u0006$GCA\u0010-\u0011\u0015i#\u00011\u0001/\u0003\u0011\u0001\u0018\r\u001e5\u0011\u0005=2dB\u0001\u00195!\t\tD#D\u00013\u0015\t\u0019t\"\u0001\u0004=e>|GOP\u0005\u0003kQ\ta\u0001\u0015:fI\u00164\u0017BA\u001c9\u0005\u0019\u0019FO]5oO*\u0011Q\u0007\u0006\u0015\u0004\u0005i\u0002\u0005CA\u001e?\u001b\u0005a$BA\u001f\n\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u007fq\u0012QaU5oG\u0016\f\u0013!Q\u0001\u0006c92d\u0006M\u0001\bg\u0016\u001c8/[8o)\t!U)D\u0001\u0001\u0011\u001515\u00011\u0001H\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\tA5*D\u0001J\u0015\tQ\u0015\"A\u0002tc2L!\u0001T%\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8)\u0007\u0001Q\u0004\t"
)
public abstract class MLReader implements BaseReadWrite {
   private Option org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession;

   public final SparkSession sparkSession() {
      return BaseReadWrite.sparkSession$(this);
   }

   public final SQLContext sqlContext() {
      return BaseReadWrite.sqlContext$(this);
   }

   public final SparkContext sc() {
      return BaseReadWrite.sc$(this);
   }

   public Option org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession() {
      return this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession;
   }

   public void org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession_$eq(final Option x$1) {
      this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession = x$1;
   }

   public abstract Object load(final String path);

   public MLReader session(final SparkSession sparkSession) {
      return (MLReader)BaseReadWrite.session$(this, sparkSession);
   }

   public MLReader() {
      BaseReadWrite.$init$(this);
   }
}
