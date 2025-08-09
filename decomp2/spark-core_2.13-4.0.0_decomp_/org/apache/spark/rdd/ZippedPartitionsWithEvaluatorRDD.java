package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.PartitionEvaluator;
import org.apache.spark.PartitionEvaluatorFactory;
import org.apache.spark.TaskContext;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u000594Q\u0001D\u0007\u0001\u001fUA\u0001B\u000b\u0001\u0003\u0002\u0004%\ta\u000b\u0005\te\u0001\u0011\t\u0019!C\u0001g!A\u0011\b\u0001B\u0001B\u0003&A\u0006\u0003\u0005;\u0001\t\u0005\r\u0011\"\u0001,\u0011!Y\u0004A!a\u0001\n\u0003a\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0015\u0002\u0017\t\u0011}\u0002!\u0011!Q\u0001\n\u0001C\u0001\u0002\u0012\u0001\u0003\u0004\u0003\u0006Y!\u0012\u0005\t\u0017\u0002\u0011\u0019\u0011)A\u0006\u0019\")Q\n\u0001C\u0001\u001d\")a\u000b\u0001C!/\n\u0001#,\u001b9qK\u0012\u0004\u0016M\u001d;ji&|gn],ji\",e/\u00197vCR|'O\u0015#E\u0015\tqq\"A\u0002sI\u0012T!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u000b\u0004-Aj2C\u0001\u0001\u0018!\rA\u0012dG\u0007\u0002\u001b%\u0011!$\u0004\u0002\u00185&\u0004\b/\u001a3QCJ$\u0018\u000e^5p]N\u0014\u0015m]3S\t\u0012\u0003\"\u0001H\u000f\r\u0001\u0011)a\u0004\u0001b\u0001A\t\tQk\u0001\u0001\u0012\u0005\u0005:\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#a\u0002(pi\"Lgn\u001a\t\u0003E!J!!K\u0012\u0003\u0007\u0005s\u00170\u0001\u0003sI\u0012\fT#\u0001\u0017\u0011\u0007ais&\u0003\u0002/\u001b\t\u0019!\u000b\u0012#\u0011\u0005q\u0001D!B\u0019\u0001\u0005\u0004\u0001#!\u0001+\u0002\u0011I$G-M0%KF$\"\u0001N\u001c\u0011\u0005\t*\u0014B\u0001\u001c$\u0005\u0011)f.\u001b;\t\u000fa\u0012\u0011\u0011!a\u0001Y\u0005\u0019\u0001\u0010J\u0019\u0002\u000bI$G-\r\u0011\u0002\tI$GMM\u0001\te\u0012$'g\u0018\u0013fcR\u0011A'\u0010\u0005\bq\u0015\t\t\u00111\u0001-\u0003\u0015\u0011H\r\u001a\u001a!\u0003A)g/\u00197vCR|'OR1di>\u0014\u0018\u0010\u0005\u0003B\u0005>ZR\"A\b\n\u0005\r{!!\u0007)beRLG/[8o\u000bZ\fG.^1u_J4\u0015m\u0019;pef\f!\"\u001a<jI\u0016t7-\u001a\u00132!\r1\u0015jL\u0007\u0002\u000f*\u0011\u0001jI\u0001\be\u00164G.Z2u\u0013\tQuI\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004\r&[\u0012A\u0002\u001fj]&$h\b\u0006\u0003P'R+Fc\u0001)R%B!\u0001\u0004A\u0018\u001c\u0011\u0015!%\u0002q\u0001F\u0011\u0015Y%\u0002q\u0001M\u0011\u0015Q#\u00021\u0001-\u0011\u0015Q$\u00021\u0001-\u0011\u0015y$\u00021\u0001A\u0003\u001d\u0019w.\u001c9vi\u0016$2\u0001\u00173j!\rI\u0016m\u0007\b\u00035~s!a\u00170\u000e\u0003qS!!X\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0013B\u00011$\u0003\u001d\u0001\u0018mY6bO\u0016L!AY2\u0003\u0011%#XM]1u_JT!\u0001Y\u0012\t\u000b\u0015\\\u0001\u0019\u00014\u0002\u000bM\u0004H.\u001b;\u0011\u0005\u0005;\u0017B\u00015\u0010\u0005%\u0001\u0016M\u001d;ji&|g\u000eC\u0003k\u0017\u0001\u00071.A\u0004d_:$X\r\u001f;\u0011\u0005\u0005c\u0017BA7\u0010\u0005-!\u0016m]6D_:$X\r\u001f;"
)
public class ZippedPartitionsWithEvaluatorRDD extends ZippedPartitionsBaseRDD {
   private RDD rdd1;
   private RDD rdd2;
   private final PartitionEvaluatorFactory evaluatorFactory;

   public RDD rdd1() {
      return this.rdd1;
   }

   public void rdd1_$eq(final RDD x$1) {
      this.rdd1 = x$1;
   }

   public RDD rdd2() {
      return this.rdd2;
   }

   public void rdd2_$eq(final RDD x$1) {
      this.rdd2 = x$1;
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      PartitionEvaluator evaluator = this.evaluatorFactory.createEvaluator();
      Seq partitions = ((ZippedPartitionsPartition)split).partitions();
      return evaluator.eval(split.index(), .MODULE$.wrapRefArray((Object[])(new Iterator[]{this.rdd1().iterator((Partition)partitions.apply(0), context), this.rdd2().iterator((Partition)partitions.apply(1), context)})));
   }

   public ZippedPartitionsWithEvaluatorRDD(final RDD rdd1, final RDD rdd2, final PartitionEvaluatorFactory evaluatorFactory, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      this.evaluatorFactory = evaluatorFactory;
      super(rdd1.context(), new scala.collection.immutable..colon.colon(rdd1, new scala.collection.immutable..colon.colon(rdd2, scala.collection.immutable.Nil..MODULE$)), ZippedPartitionsBaseRDD$.MODULE$.$lessinit$greater$default$3(), evidence$2);
   }
}
