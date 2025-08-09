package org.apache.spark.ml.image;

import org.apache.spark.sql.SparkSession;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:Q\u0001B\u0003\t\nA1QAE\u0003\t\nMAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002u\tQBU3dkJ\u001c\u0018N^3GY\u0006<'B\u0001\u0004\b\u0003\u0015IW.Y4f\u0015\tA\u0011\"\u0001\u0002nY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005E\tQ\"A\u0003\u0003\u001bI+7-\u001e:tSZ,g\t\\1h'\t\tA\u0003\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\t\u0011c^5uQJ+7-\u001e:tSZ,g\t\\1h+\tq\"\u0005F\u0002 aU\"\"\u0001I\u0016\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\r\u0011\r\u0001\n\u0002\u0002)F\u0011Q\u0005\u000b\t\u0003+\u0019J!a\n\f\u0003\u000f9{G\u000f[5oOB\u0011Q#K\u0005\u0003UY\u00111!\u00118z\u0011\u0019a3\u0001\"a\u0001[\u0005\ta\rE\u0002\u0016]\u0001J!a\f\f\u0003\u0011q\u0012\u0017P\\1nKzBQ!M\u0002A\u0002I\nQA^1mk\u0016\u0004\"!F\u001a\n\u0005Q2\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u0015\r\u0001\rA\u000e\t\u0003oij\u0011\u0001\u000f\u0006\u0003s%\t1a]9m\u0013\tY\u0004H\u0001\u0007Ta\u0006\u00148nU3tg&|g\u000e"
)
public final class RecursiveFlag {
   public static Object withRecursiveFlag(final boolean value, final SparkSession spark, final Function0 f) {
      return RecursiveFlag$.MODULE$.withRecursiveFlag(value, spark, f);
   }
}
