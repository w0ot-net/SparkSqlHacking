package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.task.JobContextImpl;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.input.StreamFileInputFormat;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005I4QAC\u0006\u0001\u001bMA\u0001b\r\u0001\u0003\u0006\u0004%I\u0001\u000e\u0005\ts\u0001\u0011\t\u0011)A\u0005k!Aa\b\u0001B\u0001B\u0003%q\b\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003O\u0011!y\u0005A!A!\u0002\u0013\u0001\u0006\u0002C)\u0001\u0005\u0003\u0005\u000b\u0011\u0002*\t\u0011e\u0003!\u0011!Q\u0001\niCQ!\u0018\u0001\u0005\u0002yCQA\u001b\u0001\u0005B-\u0014QBQ5oCJLh)\u001b7f%\u0012#%B\u0001\u0007\u000e\u0003\r\u0011H\r\u001a\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sOV\u0011A#K\n\u0003\u0001U\u0001BAF\f\u001aO5\t1\"\u0003\u0002\u0019\u0017\taa*Z<IC\u0012|w\u000e\u001d*E\tB\u0011!\u0004\n\b\u00037\t\u0002\"\u0001\b\u0011\u000e\u0003uQ!AH\u0010\u0002\rq\u0012xn\u001c;?\u0007\u0001Q\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\na\u0001\u0015:fI\u00164\u0017BA\u0013'\u0005\u0019\u0019FO]5oO*\u00111\u0005\t\t\u0003Q%b\u0001\u0001B\u0003+\u0001\t\u00071FA\u0001U#\ta\u0003\u0007\u0005\u0002.]5\t\u0001%\u0003\u00020A\t9aj\u001c;iS:<\u0007CA\u00172\u0013\t\u0011\u0004EA\u0002B]f\f!a]2\u0016\u0003U\u0002\"AN\u001c\u000e\u00035I!\u0001O\u0007\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\u0007M\u001c\u0007\u0005\u000b\u0002\u0003wA\u0011Q\u0006P\u0005\u0003{\u0001\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002!%t\u0007/\u001e;G_Jl\u0017\r^\"mCN\u001c\bG\u0001!E!\rQ\u0012iQ\u0005\u0003\u0005\u001a\u0012Qa\u00117bgN\u0004\"\u0001\u000b#\u0005\u0013\u0015\u001b\u0011\u0011!A\u0001\u0006\u00031%aA0%cE\u0011Af\u0012\t\u0004\u0011.;S\"A%\u000b\u0005)k\u0011!B5oaV$\u0018B\u0001'J\u0005U\u0019FO]3b[\u001aKG.Z%oaV$hi\u001c:nCR\f\u0001b[3z\u00072\f7o\u001d\t\u00045\u0005K\u0012A\u0003<bYV,7\t\\1tgB\u0019!$Q\u0014\u0002\t\r|gN\u001a\t\u0003'^k\u0011\u0001\u0016\u0006\u0003#VS!AV\b\u0002\r!\fGm\\8q\u0013\tAFKA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u000e[&t\u0007+\u0019:uSRLwN\\:\u0011\u00055Z\u0016B\u0001/!\u0005\rIe\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000f}\u0003\u0017MZ4iSB\u0019a\u0003A\u0014\t\u000bMB\u0001\u0019A\u001b\t\u000byB\u0001\u0019\u000121\u0005\r,\u0007c\u0001\u000eBIB\u0011\u0001&\u001a\u0003\n\u000b\u0006\f\t\u0011!A\u0003\u0002\u0019CQ!\u0014\u0005A\u00029CQa\u0014\u0005A\u0002ACQ!\u0015\u0005A\u0002ICQ!\u0017\u0005A\u0002i\u000bQbZ3u!\u0006\u0014H/\u001b;j_:\u001cX#\u00017\u0011\u00075jw.\u0003\u0002oA\t)\u0011I\u001d:bsB\u0011a\u0007]\u0005\u0003c6\u0011\u0011\u0002U1si&$\u0018n\u001c8"
)
public class BinaryFileRDD extends NewHadoopRDD {
   private final transient SparkContext sc;
   private final Class inputFormatClass;
   private final int minPartitions;

   private SparkContext sc() {
      return this.sc;
   }

   public Partition[] getPartitions() {
      Configuration conf = this.getConf();
      conf.setIfUnset("mapreduce.input.fileinputformat.list-status.num-threads", Integer.toString(Runtime.getRuntime().availableProcessors()));
      StreamFileInputFormat inputFormat = (StreamFileInputFormat)this.inputFormatClass.getConstructor().newInstance();
      if (inputFormat instanceof Configurable) {
         ((Configurable)inputFormat).setConf(conf);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var9 = BoxedUnit.UNIT;
      }

      JobContextImpl jobContext = new JobContextImpl(conf, this.jobId());
      inputFormat.setMinPartitions(this.sc(), jobContext, this.minPartitions);
      Object[] rawSplits = inputFormat.getSplits(jobContext).toArray();
      Partition[] result = new Partition[.MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps(rawSplits))];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), .MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps(rawSplits))).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> result[i] = new NewHadoopPartition(this.id(), i, (InputSplit)rawSplits[i]));
      return result;
   }

   public BinaryFileRDD(final SparkContext sc, final Class inputFormatClass, final Class keyClass, final Class valueClass, final Configuration conf, final int minPartitions) {
      super(sc, inputFormatClass, keyClass, valueClass, conf);
      this.sc = sc;
      this.inputFormatClass = inputFormatClass;
      this.minPartitions = minPartitions;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
