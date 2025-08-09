package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.task.JobContextImpl;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.input.WholeTextFileInputFormat;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q!\u0003\u0006\u0001\u0019IA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tK\u0001\u0011\t\u0011)A\u0005M!A1\t\u0001B\u0001B\u0003%A\t\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003E\u0011!1\u0005A!A!\u0002\u00139\u0005\u0002\u0003'\u0001\u0005\u0003\u0005\u000b\u0011B'\t\u000bA\u0003A\u0011A)\t\u000bu\u0003A\u0011\t0\u0003!]Cw\u000e\\3UKb$h)\u001b7f%\u0012#%BA\u0006\r\u0003\r\u0011H\r\u001a\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sON\u0011\u0001a\u0005\t\u0005)U9r#D\u0001\u000b\u0013\t1\"B\u0001\u0007OK^D\u0015\rZ8paJ#E\t\u0005\u0002\u0019;5\t\u0011D\u0003\u0002\u001b7\u0005\u0011\u0011n\u001c\u0006\u000399\ta\u0001[1e_>\u0004\u0018B\u0001\u0010\u001a\u0005\u0011!V\r\u001f;\u0002\u0005M\u001c7\u0001\u0001\t\u0003E\rj\u0011\u0001D\u0005\u0003I1\u0011Ab\u00159be.\u001cuN\u001c;fqR\f\u0001#\u001b8qkR4uN]7bi\u000ec\u0017m]:1\u0005\u001d2\u0004c\u0001\u00152i9\u0011\u0011f\f\t\u0003U5j\u0011a\u000b\u0006\u0003Y\u0001\na\u0001\u0010:p_Rt$\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj\u0013A\u0002)sK\u0012,g-\u0003\u00023g\t)1\t\\1tg*\u0011\u0001'\f\t\u0003kYb\u0001\u0001B\u00058\u0005\u0005\u0005\t\u0011!B\u0001q\t\u0019q\fJ\u0019\u0012\u0005ej\u0004C\u0001\u001e<\u001b\u0005i\u0013B\u0001\u001f.\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AP!\u000e\u0003}R!\u0001\u0011\u0007\u0002\u000b%t\u0007/\u001e;\n\u0005\t{$\u0001G,i_2,G+\u001a=u\r&dW-\u00138qkR4uN]7bi\u0006A1.Z=DY\u0006\u001c8\u000fE\u0002)c]\t!B^1mk\u0016\u001cE.Y:t\u0003\u0011\u0019wN\u001c4\u0011\u0005!SU\"A%\u000b\u0005\u0019[\u0012BA&J\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0006iQ.\u001b8QCJ$\u0018\u000e^5p]N\u0004\"A\u000f(\n\u0005=k#aA%oi\u00061A(\u001b8jiz\"rAU*U3j[F\f\u0005\u0002\u0015\u0001!)qd\u0002a\u0001C!)Qe\u0002a\u0001+B\u0012a\u000b\u0017\t\u0004QE:\u0006CA\u001bY\t%9D+!A\u0001\u0002\u000b\u0005\u0001\bC\u0003D\u000f\u0001\u0007A\tC\u0003F\u000f\u0001\u0007A\tC\u0003G\u000f\u0001\u0007q\tC\u0003M\u000f\u0001\u0007Q*A\u0007hKR\u0004\u0016M\u001d;ji&|gn]\u000b\u0002?B\u0019!\b\u00192\n\u0005\u0005l#!B!se\u0006L\bC\u0001\u0012d\u0013\t!GBA\u0005QCJ$\u0018\u000e^5p]\u0002"
)
public class WholeTextFileRDD extends NewHadoopRDD {
   private final Class inputFormatClass;
   private final int minPartitions;

   public Partition[] getPartitions() {
      Configuration conf = this.getConf();
      conf.setIfUnset("mapreduce.input.fileinputformat.list-status.num-threads", Integer.toString(Runtime.getRuntime().availableProcessors()));
      WholeTextFileInputFormat inputFormat = (WholeTextFileInputFormat)this.inputFormatClass.getConstructor().newInstance();
      if (inputFormat != null) {
         inputFormat.setConf(conf);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var9 = BoxedUnit.UNIT;
      }

      JobContextImpl jobContext = new JobContextImpl(conf, this.jobId());
      inputFormat.setMinPartitions(jobContext, this.minPartitions);
      Object[] rawSplits = inputFormat.getSplits(jobContext).toArray();
      Partition[] result = new Partition[.MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps(rawSplits))];
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), .MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps(rawSplits))).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> result[i] = new NewHadoopPartition(this.id(), i, (InputSplit)rawSplits[i]));
      return result;
   }

   public WholeTextFileRDD(final SparkContext sc, final Class inputFormatClass, final Class keyClass, final Class valueClass, final Configuration conf, final int minPartitions) {
      super(sc, inputFormatClass, keyClass, valueClass, conf);
      this.inputFormatClass = inputFormatClass;
      this.minPartitions = minPartitions;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
