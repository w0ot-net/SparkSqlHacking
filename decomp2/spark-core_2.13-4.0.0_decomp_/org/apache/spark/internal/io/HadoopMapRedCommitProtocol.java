package org.apache.spark.internal.io;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2A!\u0002\u0004\u0001#!Aa\u0003\u0001B\u0001B\u0003%q\u0003\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003\u0018\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u0015Q\u0003\u0001\"\u0011,\u0005iA\u0015\rZ8pa6\u000b\u0007OU3e\u0007>lW.\u001b;Qe>$xnY8m\u0015\t9\u0001\"\u0001\u0002j_*\u0011\u0011BC\u0001\tS:$XM\u001d8bY*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0011\u0002CA\n\u0015\u001b\u00051\u0011BA\u000b\u0007\u0005uA\u0015\rZ8pa6\u000b\u0007OU3ek\u000e,7i\\7nSR\u0004&o\u001c;pG>d\u0017!\u00026pE&#\u0007C\u0001\r\"\u001d\tIr\u0004\u0005\u0002\u001b;5\t1D\u0003\u0002\u001d!\u00051AH]8pizR\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0012$\u0005\u0019\u0019FO]5oO*\u0011\u0001%H\u0001\u0005a\u0006$\b.\u0001\u0004=S:LGO\u0010\u000b\u0004O!J\u0003CA\n\u0001\u0011\u001512\u00011\u0001\u0018\u0011\u0015!3\u00011\u0001\u0018\u00039\u0019X\r^;q\u0007>lW.\u001b;uKJ$\"\u0001\f\u001b\u0011\u00055\u0012T\"\u0001\u0018\u000b\u0005=\u0002\u0014AB7baJ,GM\u0003\u00022\u0019\u00051\u0001.\u00193p_BL!a\r\u0018\u0003\u001f=+H\u000f];u\u0007>lW.\u001b;uKJDQ!\u000e\u0003A\u0002Y\nqaY8oi\u0016DH\u000f\u0005\u00028u5\t\u0001H\u0003\u0002:a\u0005IQ.\u00199sK\u0012,8-Z\u0005\u0003wa\u0012!\u0003V1tW\u0006#H/Z7qi\u000e{g\u000e^3yi\u0002"
)
public class HadoopMapRedCommitProtocol extends HadoopMapReduceCommitProtocol {
   public OutputCommitter setupCommitter(final TaskAttemptContext context) {
      JobConf config = (JobConf)context.getConfiguration();
      OutputCommitter committer = config.getOutputCommitter();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using output committer class"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, committer.getClass().getCanonicalName())}))))));
      return committer;
   }

   public HadoopMapRedCommitProtocol(final String jobId, final String path) {
      super(jobId, path, HadoopMapReduceCommitProtocol$.MODULE$.$lessinit$greater$default$3());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
