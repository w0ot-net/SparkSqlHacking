package org.apache.spark.partial;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.collection.OpenHashMap;
import org.apache.spark.util.collection.OpenHashMap$mcJ$sp;
import scala.MatchError;
import scala.Tuple2;
import scala..less.colon.less.;
import scala.collection.IterableOnceOps;
import scala.collection.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005-4Q\u0001D\u0007\u0001\u001fUA\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!\u0011\u0005\t\t\u0002\u0011\t\u0011)A\u0005\u000b\"A\u0001\n\u0001B\u0002B\u0003-\u0011\nC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0004W\u0001\u0001\u0007I\u0011B,\t\u000fa\u0003\u0001\u0019!C\u00053\"1q\f\u0001Q!\n\u0005Cq\u0001\u0019\u0001C\u0002\u0013%\u0011\r\u0003\u0004c\u0001\u0001\u0006I!\t\u0005\u0006G\u0002!\t\u0005\u001a\u0005\u0006S\u0002!\tE\u001b\u0002\u0016\u000fJ|W\u000f]3e\u0007>,h\u000e^#wC2,\u0018\r^8s\u0015\tqq\"A\u0004qCJ$\u0018.\u00197\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e,\"AF\u0016\u0014\u0007\u00019R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VM\u001a\t\u0005=}\t\u0003(D\u0001\u000e\u0013\t\u0001SB\u0001\u000bBaB\u0014x\u000e_5nCR,WI^1mk\u0006$xN\u001d\t\u0005E\u001dJS'D\u0001$\u0015\t!S%\u0001\u0006d_2dWm\u0019;j_:T!AJ\b\u0002\tU$\u0018\u000e\\\u0005\u0003Q\r\u00121b\u00149f]\"\u000b7\u000f['baB\u0011!f\u000b\u0007\u0001\t\u0015a\u0003A1\u0001/\u0005\u0005!6\u0001A\t\u0003_I\u0002\"\u0001\u0007\u0019\n\u0005EJ\"a\u0002(pi\"Lgn\u001a\t\u00031MJ!\u0001N\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0019m%\u0011q'\u0007\u0002\u0005\u0019>tw\r\u0005\u0003:w%jT\"\u0001\u001e\u000b\u0005\u0011J\u0012B\u0001\u001f;\u0005\ri\u0015\r\u001d\t\u0003=yJ!aP\u0007\u0003\u001b\t{WO\u001c3fI\u0012{WO\u00197f\u00031!x\u000e^1m\u001fV$\b/\u001e;t!\tA\")\u0003\u0002D3\t\u0019\u0011J\u001c;\u0002\u0015\r|gNZ5eK:\u001cW\r\u0005\u0002\u0019\r&\u0011q)\u0007\u0002\u0007\t>,(\r\\3\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002K\u001b&j\u0011a\u0013\u0006\u0003\u0019f\tqA]3gY\u0016\u001cG/\u0003\u0002O\u0017\nA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0004#R+FC\u0001*T!\rq\u0002!\u000b\u0005\u0006\u0011\u0012\u0001\u001d!\u0013\u0005\u0006\u0001\u0012\u0001\r!\u0011\u0005\u0006\t\u0012\u0001\r!R\u0001\u000e_V$\b/\u001e;t\u001b\u0016\u0014x-\u001a3\u0016\u0003\u0005\u000b\u0011c\\;uaV$8/T3sO\u0016$w\fJ3r)\tQV\f\u0005\u0002\u00197&\u0011A,\u0007\u0002\u0005+:LG\u000fC\u0004_\r\u0005\u0005\t\u0019A!\u0002\u0007a$\u0013'\u0001\bpkR\u0004X\u000f^:NKJ<W\r\u001a\u0011\u0002\tM,Xn]\u000b\u0002C\u0005)1/^7tA\u0005)Q.\u001a:hKR\u0019!,Z4\t\u000b\u0019T\u0001\u0019A!\u0002\u0011=,H\u000f];u\u0013\u0012DQ\u0001\u001b\u0006A\u0002\u0005\n!\u0002^1tWJ+7/\u001e7u\u00035\u0019WO\u001d:f]R\u0014Vm];miR\t\u0001\b"
)
public class GroupedCountEvaluator implements ApproximateEvaluator {
   private final int totalOutputs;
   private final double confidence;
   private int outputsMerged;
   private final OpenHashMap sums;

   private int outputsMerged() {
      return this.outputsMerged;
   }

   private void outputsMerged_$eq(final int x$1) {
      this.outputsMerged = x$1;
   }

   private OpenHashMap sums() {
      return this.sums;
   }

   public void merge(final int outputId, final OpenHashMap taskResult) {
      this.outputsMerged_$eq(this.outputsMerged() + 1);
      taskResult.foreach((x0$1) -> BoxesRunTime.boxToLong($anonfun$merge$1(this, x0$1)));
   }

   public Map currentResult() {
      if (this.outputsMerged() == this.totalOutputs) {
         return ((IterableOnceOps)this.sums().map((x0$1) -> {
            if (x0$1 != null) {
               Object key = x0$1._1();
               long sum = x0$1._2$mcJ$sp();
               return new Tuple2(key, new BoundedDouble((double)sum, (double)1.0F, (double)sum, (double)sum));
            } else {
               throw new MatchError(x0$1);
            }
         })).toMap(.MODULE$.refl());
      } else if (this.outputsMerged() == 0) {
         return new HashMap();
      } else {
         double p = (double)this.outputsMerged() / (double)this.totalOutputs;
         return ((IterableOnceOps)this.sums().map((x0$2) -> {
            if (x0$2 != null) {
               Object key = x0$2._1();
               long sum = x0$2._2$mcJ$sp();
               return new Tuple2(key, CountEvaluator$.MODULE$.bound(this.confidence, sum, p));
            } else {
               throw new MatchError(x0$2);
            }
         })).toMap(.MODULE$.refl());
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$merge$1(final GroupedCountEvaluator $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object key = x0$1._1();
         long value = x0$1._2$mcJ$sp();
         return $this.sums().changeValue$mcJ$sp(key, (JFunction0.mcJ.sp)() -> value, (JFunction1.mcJJ.sp)(x$1) -> x$1 + value);
      } else {
         throw new MatchError(x0$1);
      }
   }

   public GroupedCountEvaluator(final int totalOutputs, final double confidence, final ClassTag evidence$1) {
      this.totalOutputs = totalOutputs;
      this.confidence = confidence;
      this.outputsMerged = 0;
      this.sums = new OpenHashMap$mcJ$sp(evidence$1, scala.reflect.ClassTag..MODULE$.Long());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
