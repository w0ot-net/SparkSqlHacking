package org.apache.spark.shuffle;

import org.apache.spark.SparkConf;
import org.apache.spark.shuffle.api.ShuffleDataIO;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U:aAB\u0004\t\u0002%yaAB\t\b\u0011\u0003I!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0004\u001d\u0003\t\u0007I\u0011A\u000f\t\r\u0019\n\u0001\u0015!\u0003\u001f\u0011\u00159\u0013\u0001\"\u0001)\u0003I\u0019\u0006.\u001e4gY\u0016$\u0015\r^1J\u001fV#\u0018\u000e\\:\u000b\u0005!I\u0011aB:ik\u001a4G.\u001a\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sOB\u0011\u0001#A\u0007\u0002\u000f\t\u00112\u000b[;gM2,G)\u0019;b\u0013>+F/\u001b7t'\t\t1\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\"A\rT\u0011V3e\tT#`'B\u000b%kS0D\u001f:3u\f\u0015*F\r&CV#\u0001\u0010\u0011\u0005}!S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013\u0001\u00027b]\u001eT\u0011aI\u0001\u0005U\u00064\u0018-\u0003\u0002&A\t11\u000b\u001e:j]\u001e\f!d\u0015%V\r\u001acUiX*Q\u0003J[ulQ(O\r~\u0003&+\u0012$J1\u0002\n\u0011\u0003\\8bINCWO\u001a4mK\u0012\u000bG/Y%P)\tIs\u0006\u0005\u0002+[5\t1F\u0003\u0002-\u000f\u0005\u0019\u0011\r]5\n\u00059Z#!D*ik\u001a4G.\u001a#bi\u0006Lu\nC\u00031\u000b\u0001\u0007\u0011'\u0001\u0003d_:4\u0007C\u0001\u001a4\u001b\u0005I\u0011B\u0001\u001b\n\u0005%\u0019\u0006/\u0019:l\u0007>tg\r"
)
public final class ShuffleDataIOUtils {
   public static ShuffleDataIO loadShuffleDataIO(final SparkConf conf) {
      return ShuffleDataIOUtils$.MODULE$.loadShuffleDataIO(conf);
   }

   public static String SHUFFLE_SPARK_CONF_PREFIX() {
      return ShuffleDataIOUtils$.MODULE$.SHUFFLE_SPARK_CONF_PREFIX();
   }
}
