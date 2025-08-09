package org.apache.spark.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaPairRDD$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014A!\u0003\u0006\u0005+!A1\u0006\u0001B\u0001B\u0003%A\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00033\u0001\u0011\u00053\u0007C\u0004:\u0001\t\u0007I\u0011\t\u001e\t\r\u0005\u0003\u0001\u0015!\u0003<\u0011\u0015\u0011\u0005\u0001\"\u0011D\u0011\u001d9\u0006A1A\u0005\u0002aCaa\u0018\u0001!\u0002\u0013I&a\u0003)bSJ<\u0018n]3S\t\u0012S!a\u0003\u0007\u0002\rALH\u000f[8o\u0015\tia\"A\u0002ba&T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\u0002\u0001'\t\u0001a\u0003E\u0002\u00185qi\u0011\u0001\u0007\u0006\u000339\t1A\u001d3e\u0013\tY\u0002DA\u0002S\t\u0012\u0003B!\b\u0011#K5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004UkBdWM\r\t\u0003;\rJ!\u0001\n\u0010\u0003\t1{gn\u001a\t\u0004;\u0019B\u0013BA\u0014\u001f\u0005\u0015\t%O]1z!\ti\u0012&\u0003\u0002+=\t!!)\u001f;f\u0003\u0011\u0001(/\u001a<\u0011\u0007]QR%\u0001\u0004=S:LGO\u0010\u000b\u0003_E\u0002\"\u0001\r\u0001\u000e\u0003)AQa\u000b\u0002A\u00021\nQbZ3u!\u0006\u0014H/\u001b;j_:\u001cX#\u0001\u001b\u0011\u0007u1S\u0007\u0005\u00027o5\ta\"\u0003\u00029\u001d\tI\u0001+\u0019:uSRLwN\\\u0001\fa\u0006\u0014H/\u001b;j_:,'/F\u0001<!\riBHP\u0005\u0003{y\u0011aa\u00149uS>t\u0007C\u0001\u001c@\u0013\t\u0001eBA\u0006QCJ$\u0018\u000e^5p]\u0016\u0014\u0018\u0001\u00049beRLG/[8oKJ\u0004\u0013aB2p[B,H/\u001a\u000b\u0004\tB\u0013\u0006cA#N99\u0011ai\u0013\b\u0003\u000f*k\u0011\u0001\u0013\u0006\u0003\u0013R\ta\u0001\u0010:p_Rt\u0014\"A\u0010\n\u00051s\u0012a\u00029bG.\fw-Z\u0005\u0003\u001d>\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003\u0019zAQ!\u0015\u0004A\u0002U\nQa\u001d9mSRDQa\u0015\u0004A\u0002Q\u000bqaY8oi\u0016DH\u000f\u0005\u00027+&\u0011aK\u0004\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u0007bg*\u000bg/\u0019)bSJ\u0014F\tR\u000b\u00023B!!,\u0018\u0012&\u001b\u0005Y&B\u0001/\r\u0003\u0011Q\u0017M^1\n\u0005y[&a\u0003&bm\u0006\u0004\u0016-\u001b:S\t\u0012\u000ba\"Y:KCZ\f\u0007+Y5s%\u0012#\u0005\u0005"
)
public class PairwiseRDD extends RDD {
   private final RDD prev;
   private final Option partitioner;
   private final JavaPairRDD asJavaPairRDD;

   public Partition[] getPartitions() {
      return this.prev.partitions();
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      return this.prev.iterator(split, context).grouped(2).map((x0$1) -> {
         if (x0$1 != null) {
            SeqOps var3 = .MODULE$.Seq().unapplySeq(x0$1);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var3) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 2) == 0) {
               byte[] a = (byte[])scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 0);
               byte[] b = (byte[])scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 1);
               return new Tuple2(BoxesRunTime.boxToLong(Utils$.MODULE$.deserializeLongValue(a)), b);
            }
         }

         throw new SparkException("PairwiseRDD: unexpected value: " + x0$1);
      });
   }

   public JavaPairRDD asJavaPairRDD() {
      return this.asJavaPairRDD;
   }

   public PairwiseRDD(final RDD prev) {
      super(prev, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.prev = prev;
      this.partitioner = prev.partitioner();
      this.asJavaPairRDD = JavaPairRDD$.MODULE$.fromRDD(this, scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
