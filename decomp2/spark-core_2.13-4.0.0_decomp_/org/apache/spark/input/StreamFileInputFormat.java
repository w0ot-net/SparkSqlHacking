package org.apache.spark.input;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.config.package$;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.mutable.Buffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000514a!\u0002\u0004\u0002\u0002!q\u0001\"B\u001b\u0001\t\u00031\u0004\"B\u001d\u0001\t#R\u0004\"\u0002'\u0001\t\u0003i\u0005\"B/\u0001\r\u0003q&!F*ue\u0016\fWNR5mK&s\u0007/\u001e;G_Jl\u0017\r\u001e\u0006\u0003\u000f!\tQ!\u001b8qkRT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u000b\u0003\u001f-\u001a\"\u0001\u0001\t\u0011\tEI2$K\u0007\u0002%)\u0011qa\u0005\u0006\u0003)U\t1\u0001\\5c\u0015\t1r#A\u0005nCB\u0014X\rZ;dK*\u0011\u0001DC\u0001\u0007Q\u0006$wn\u001c9\n\u0005i\u0011\"AF\"p[\nLg.\u001a$jY\u0016Le\u000e];u\r>\u0014X.\u0019;\u0011\u0005q1cBA\u000f%!\tq\"%D\u0001 \u0015\t\u0001\u0013%\u0001\u0004=e>|GOP\u0002\u0001\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0005\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015\u0012\u0003C\u0001\u0016,\u0019\u0001!Q\u0001\f\u0001C\u00025\u0012\u0011\u0001V\t\u0003]I\u0002\"a\f\u0019\u000e\u0003\tJ!!\r\u0012\u0003\u000f9{G\u000f[5oOB\u0011qfM\u0005\u0003i\t\u00121!\u00118z\u0003\u0019a\u0014N\\5u}Q\tq\u0007E\u00029\u0001%j\u0011AB\u0001\fSN\u001c\u0006\u000f\\5uC\ndW\rF\u0002<}\u0011\u0003\"a\f\u001f\n\u0005u\u0012#a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u007f\t\u0001\r\u0001Q\u0001\bG>tG/\u001a=u!\t\t%)D\u0001\u0016\u0013\t\u0019UC\u0001\u0006K_\n\u001cuN\u001c;fqRDQ!\u0012\u0002A\u0002\u0019\u000bAAZ5mKB\u0011qIS\u0007\u0002\u0011*\u0011\u0011jF\u0001\u0003MNL!a\u0013%\u0003\tA\u000bG\u000f[\u0001\u0011g\u0016$X*\u001b8QCJ$\u0018\u000e^5p]N$BAT)X1B\u0011qfT\u0005\u0003!\n\u0012A!\u00168ji\")!k\u0001a\u0001'\u0006\u00111o\u0019\t\u0003)Vk\u0011\u0001C\u0005\u0003-\"\u0011Ab\u00159be.\u001cuN\u001c;fqRDQaP\u0002A\u0002\u0001CQ!W\u0002A\u0002i\u000bQ\"\\5o!\u0006\u0014H/\u001b;j_:\u001c\bCA\u0018\\\u0013\ta&EA\u0002J]R\f!c\u0019:fCR,'+Z2pe\u0012\u0014V-\u00193feR\u0019qLY4\u0011\t\u0005\u00037$K\u0005\u0003CV\u0011ABU3d_J$'+Z1eKJDQa\u0019\u0003A\u0002\u0011\fQa\u001d9mSR\u0004\"!Q3\n\u0005\u0019,\"AC%oaV$8\u000b\u001d7ji\")\u0001\u000e\u0002a\u0001S\u0006IA/Y\"p]R,\u0007\u0010\u001e\t\u0003\u0003*L!a[\u000b\u0003%Q\u000b7o[!ui\u0016l\u0007\u000f^\"p]R,\u0007\u0010\u001e"
)
public abstract class StreamFileInputFormat extends CombineFileInputFormat {
   public boolean isSplitable(final JobContext context, final Path file) {
      return false;
   }

   public void setMinPartitions(final SparkContext sc, final JobContext context, final int minPartitions) {
      long defaultMaxSplitBytes = BoxesRunTime.unboxToLong(sc.conf().get(package$.MODULE$.FILES_MAX_PARTITION_BYTES()));
      long openCostInBytes = BoxesRunTime.unboxToLong(sc.conf().get(package$.MODULE$.FILES_OPEN_COST_IN_BYTES()));
      int defaultParallelism = Math.max(sc.defaultParallelism(), minPartitions);
      Buffer files = .MODULE$.ListHasAsScala(this.listStatus(context)).asScala();
      long totalBytes = BoxesRunTime.unboxToLong(((IterableOnceOps)((IterableOps)files.filterNot((x$1) -> BoxesRunTime.boxToBoolean($anonfun$setMinPartitions$1(x$1)))).map((x$2) -> BoxesRunTime.boxToLong($anonfun$setMinPartitions$2(openCostInBytes, x$2)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      long bytesPerCore = totalBytes / (long)defaultParallelism;
      long maxSplitSize = Math.min(defaultMaxSplitBytes, Math.max(openCostInBytes, bytesPerCore));
      Configuration jobConfig = context.getConfiguration();
      long minSplitSizePerNode = jobConfig.getLong("mapreduce.input.fileinputformat.split.minsize.per.node", 0L);
      long minSplitSizePerRack = jobConfig.getLong("mapreduce.input.fileinputformat.split.minsize.per.rack", 0L);
      if (maxSplitSize < minSplitSizePerNode) {
         super.setMinSplitSizeNode(maxSplitSize);
      }

      if (maxSplitSize < minSplitSizePerRack) {
         super.setMinSplitSizeRack(maxSplitSize);
      }

      super.setMaxSplitSize(maxSplitSize);
   }

   public abstract RecordReader createRecordReader(final InputSplit split, final TaskAttemptContext taContext);

   // $FF: synthetic method
   public static final boolean $anonfun$setMinPartitions$1(final FileStatus x$1) {
      return x$1.isDirectory();
   }

   // $FF: synthetic method
   public static final long $anonfun$setMinPartitions$2(final long openCostInBytes$1, final FileStatus x$2) {
      return x$2.getLen() + openCostInBytes$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
