package org.apache.spark.mapred;

import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:Q\u0001B\u0003\t\u000291Q\u0001E\u0003\t\u0002EAQAH\u0001\u0005\u0002}AQ\u0001I\u0001\u0005\u0002\u0005\nQc\u00159be.D\u0015\rZ8pa6\u000b\u0007OU3e+RLGN\u0003\u0002\u0007\u000f\u00051Q.\u00199sK\u0012T!\u0001C\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Y\u0011AB1qC\u000eDWMC\u0001\r\u0003\ry'oZ\u0002\u0001!\ty\u0011!D\u0001\u0006\u0005U\u0019\u0006/\u0019:l\u0011\u0006$wn\u001c9NCB\u0014V\rZ+uS2\u001c2!\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011\u0011\u0004H\u0007\u00025)\u00111dB\u0001\tS:$XM\u001d8bY&\u0011QD\u0007\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}Q\ta\"\u0001\u0006d_6l\u0017\u000e\u001e+bg.$RAI\u00130ie\u0002\"aE\u0012\n\u0005\u0011\"\"\u0001B+oSRDQAJ\u0002A\u0002\u001d\n\u0011bY8n[&$H/\u001a:\u0011\u0005!jS\"A\u0015\u000b\u0005)Z\u0013!C7baJ,G-^2f\u0015\ta\u0013\"\u0001\u0004iC\u0012|w\u000e]\u0005\u0003]%\u0012qbT;uaV$8i\\7nSR$XM\u001d\u0005\u0006a\r\u0001\r!M\u0001\u000e[J$\u0016m]6D_:$X\r\u001f;\u0011\u0005!\u0012\u0014BA\u001a*\u0005I!\u0016m]6BiR,W\u000e\u001d;D_:$X\r\u001f;\t\u000bU\u001a\u0001\u0019\u0001\u001c\u0002\u000b)|'-\u00133\u0011\u0005M9\u0014B\u0001\u001d\u0015\u0005\rIe\u000e\u001e\u0005\u0006u\r\u0001\rAN\u0001\bgBd\u0017\u000e^%e\u0001"
)
public final class SparkHadoopMapRedUtil {
   public static void commitTask(final OutputCommitter committer, final TaskAttemptContext mrTaskContext, final int jobId, final int splitId) {
      SparkHadoopMapRedUtil$.MODULE$.commitTask(committer, mrTaskContext, jobId, splitId);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SparkHadoopMapRedUtil$.MODULE$.LogStringContext(sc);
   }
}
