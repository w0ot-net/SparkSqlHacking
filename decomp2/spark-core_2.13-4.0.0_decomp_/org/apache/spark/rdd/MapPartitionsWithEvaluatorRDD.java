package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.PartitionEvaluator;
import org.apache.spark.PartitionEvaluatorFactory;
import org.apache.spark.TaskContext;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u000514Qa\u0003\u0007\u0001\u001dQA\u0001\"\u000b\u0001\u0003\u0002\u0004%\tA\u000b\u0005\t_\u0001\u0011\t\u0019!C\u0001a!Aa\u0007\u0001B\u0001B\u0003&1\u0006\u0003\u00058\u0001\t\u0005\t\u0015!\u00039\u0011!a\u0004AaA!\u0002\u0017i\u0004\u0002C\"\u0001\u0005\u0007\u0005\u000b1\u0002#\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b5\u0003A\u0011\t(\t\u000bU\u0003A\u0011\t,\t\u000b)\u0004A\u0011I6\u0003;5\u000b\u0007\u000fU1si&$\u0018n\u001c8t/&$\b.\u0012<bYV\fGo\u001c:S\t\u0012S!!\u0004\b\u0002\u0007I$GM\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h+\r)R\u0006H\n\u0003\u0001Y\u00012a\u0006\r\u001b\u001b\u0005a\u0011BA\r\r\u0005\r\u0011F\t\u0012\t\u00037qa\u0001\u0001B\u0003\u001e\u0001\t\u0007qDA\u0001V\u0007\u0001\t\"\u0001\t\u0014\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\u000f9{G\u000f[5oOB\u0011\u0011eJ\u0005\u0003Q\t\u00121!\u00118z\u0003\u0011\u0001(/\u001a<\u0016\u0003-\u00022a\u0006\r-!\tYR\u0006B\u0003/\u0001\t\u0007qDA\u0001U\u0003!\u0001(/\u001a<`I\u0015\fHCA\u00195!\t\t#'\u0003\u00024E\t!QK\\5u\u0011\u001d)$!!AA\u0002-\n1\u0001\u001f\u00132\u0003\u0015\u0001(/\u001a<!\u0003A)g/\u00197vCR|'OR1di>\u0014\u0018\u0010\u0005\u0003:u1RR\"\u0001\b\n\u0005mr!!\u0007)beRLG/[8o\u000bZ\fG.^1u_J4\u0015m\u0019;pef\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rq\u0014\tL\u0007\u0002\u007f)\u0011\u0001II\u0001\be\u00164G.Z2u\u0013\t\u0011uH\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004}\u0005S\u0012A\u0002\u001fj]&$h\bF\u0002H\u00172#2\u0001S%K!\u00119\u0002\u0001\f\u000e\t\u000bq:\u00019A\u001f\t\u000b\r;\u00019\u0001#\t\u000b%:\u0001\u0019A\u0016\t\u000b]:\u0001\u0019\u0001\u001d\u0002\u001b\u001d,G\u000fU1si&$\u0018n\u001c8t+\u0005y\u0005cA\u0011Q%&\u0011\u0011K\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003sMK!\u0001\u0016\b\u0003\u0013A\u000b'\u000f^5uS>t\u0017aB2p[B,H/\u001a\u000b\u0004/\u000e,\u0007c\u0001-a59\u0011\u0011L\u0018\b\u00035vk\u0011a\u0017\u0006\u00039z\ta\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005}\u0013\u0013a\u00029bG.\fw-Z\u0005\u0003C\n\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003?\nBQ\u0001Z\u0005A\u0002I\u000bQa\u001d9mSRDQAZ\u0005A\u0002\u001d\fqaY8oi\u0016DH\u000f\u0005\u0002:Q&\u0011\u0011N\u0004\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\tdY\u0016\f'\u000fR3qK:$WM\\2jKN$\u0012!\r"
)
public class MapPartitionsWithEvaluatorRDD extends RDD {
   private RDD prev;
   private final PartitionEvaluatorFactory evaluatorFactory;
   private final ClassTag evidence$1;

   public RDD prev() {
      return this.prev;
   }

   public void prev_$eq(final RDD x$1) {
      this.prev = x$1;
   }

   public Partition[] getPartitions() {
      return this.firstParent(this.evidence$1).partitions();
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      PartitionEvaluator evaluator = this.evaluatorFactory.createEvaluator();
      Iterator input = this.firstParent(this.evidence$1).iterator(split, context);
      return evaluator.eval(split.index(), .MODULE$.wrapRefArray((Object[])(new Iterator[]{input})));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.prev_$eq((RDD)null);
   }

   public MapPartitionsWithEvaluatorRDD(final RDD prev, final PartitionEvaluatorFactory evaluatorFactory, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.prev = prev;
      this.evaluatorFactory = evaluatorFactory;
      this.evidence$1 = evidence$1;
      super(prev, evidence$2);
   }
}
