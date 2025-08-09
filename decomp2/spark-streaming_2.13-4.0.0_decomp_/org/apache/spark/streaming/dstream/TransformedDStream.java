package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Function0;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b!B\u0005\u000b\u00011!\u0002\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u0011m\u0002!\u0011!Q\u0001\nqB\u0001b\u0014\u0001\u0003\u0004\u0003\u0006Y\u0001\u0015\u0005\u0006-\u0002!\ta\u0016\u0005\u0006Q\u0002!\t%\u001b\u0005\u0006e\u0002!\te\u001d\u0005\u0006o\u0002!\t\u0005\u001f\u0005\u0007}\u0002!\t\u0006D@\u0003%Q\u0013\u0018M\\:g_JlW\r\u001a#TiJ,\u0017-\u001c\u0006\u0003\u00171\tq\u0001Z:ue\u0016\fWN\u0003\u0002\u000e\u001d\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sOV\u0011Q\u0003H\n\u0003\u0001Y\u00012a\u0006\r\u001b\u001b\u0005Q\u0011BA\r\u000b\u0005\u001d!5\u000b\u001e:fC6\u0004\"a\u0007\u000f\r\u0001\u0011)Q\u0004\u0001b\u0001?\t\tQk\u0001\u0001\u0012\u0005\u00012\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#a\u0002(pi\"Lgn\u001a\t\u0003C\u001dJ!\u0001\u000b\u0012\u0003\u0007\u0005s\u00170A\u0004qCJ,g\u000e^:\u0011\u0007-\u001adG\u0004\u0002-c9\u0011Q\u0006M\u0007\u0002])\u0011qFH\u0001\u0007yI|w\u000e\u001e \n\u0003\rJ!A\r\u0012\u0002\u000fA\f7m[1hK&\u0011A'\u000e\u0002\u0004'\u0016\f(B\u0001\u001a#a\t9\u0014\bE\u0002\u00181a\u0002\"aG\u001d\u0005\u0013i\n\u0011\u0011!A\u0001\u0006\u0003y\"aA0%c\u0005iAO]1og\u001a|'/\u001c$v]\u000e\u0004R!I\u001f@\u0015:K!A\u0010\u0012\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004cA\u00164\u0001B\u0012\u0011\t\u0013\t\u0004\u0005\u0016;U\"A\"\u000b\u0005\u0011s\u0011a\u0001:eI&\u0011ai\u0011\u0002\u0004%\u0012#\u0005CA\u000eI\t%I%!!A\u0001\u0002\u000b\u0005qDA\u0002`II\u0002\"a\u0013'\u000e\u00031I!!\u0014\u0007\u0003\tQKW.\u001a\t\u0004\u0005\u0016S\u0012AC3wS\u0012,gnY3%cA\u0019\u0011\u000b\u0016\u000e\u000e\u0003IS!a\u0015\u0012\u0002\u000fI,g\r\\3di&\u0011QK\u0015\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"2\u0001W.b)\tI&\fE\u0002\u0018\u0001iAQa\u0014\u0003A\u0004ACQ!\u000b\u0003A\u0002q\u00032aK\u001a^a\tq\u0006\rE\u0002\u00181}\u0003\"a\u00071\u0005\u0013iZ\u0016\u0011!A\u0001\u0006\u0003y\u0002\"B\u001e\u0005\u0001\u0004\u0011\u0007#B\u0011>G*s\u0005cA\u00164IB\u0012Qm\u001a\t\u0004\u0005\u00163\u0007CA\u000eh\t%I\u0015-!A\u0001\u0002\u000b\u0005q$\u0001\u0007eKB,g\u000eZ3oG&,7/F\u0001k!\rY3.\\\u0005\u0003YV\u0012A\u0001T5tiB\u0012a\u000e\u001d\t\u0004/ay\u0007CA\u000eq\t%\tX!!A\u0001\u0002\u000b\u0005qDA\u0002`IM\nQb\u001d7jI\u0016$UO]1uS>tW#\u0001;\u0011\u0005-+\u0018B\u0001<\r\u0005!!UO]1uS>t\u0017aB2p[B,H/\u001a\u000b\u0003sr\u00042!\t>O\u0013\tY(E\u0001\u0004PaRLwN\u001c\u0005\u0006{\u001e\u0001\rAS\u0001\nm\u0006d\u0017\u000e\u001a+j[\u0016\fAd\u0019:fCR,'\u000b\u0012#XSRDGj\\2bYB\u0013x\u000e]3si&,7/\u0006\u0003\u0002\u0002\u0005\u001dACBA\u0002\u0003'\t9\u0002\u0006\u0003\u0002\u0006\u0005%\u0001cA\u000e\u0002\b\u0011)Q\u0004\u0003b\u0001?!A\u00111\u0002\u0005\u0005\u0002\u0004\ti!\u0001\u0003c_\u0012L\b#B\u0011\u0002\u0010\u0005\u0015\u0011bAA\tE\tAAHY=oC6,g\b\u0003\u0004\u0002\u0016!\u0001\rAS\u0001\u0005i&lW\rC\u0004\u0002\u001a!\u0001\r!a\u0007\u0002%\u0011L7\u000f\u001d7bs&sg.\u001a:S\t\u0012{\u0005o\u001d\t\u0004C\u0005u\u0011bAA\u0010E\t9!i\\8mK\u0006t\u0007"
)
public class TransformedDStream extends DStream {
   private final Seq parents;
   private final Function2 transformFunc;

   public List dependencies() {
      return this.parents.toList();
   }

   public Duration slideDuration() {
      return ((DStream)this.parents.head()).slideDuration();
   }

   public Option compute(final Time validTime) {
      Seq parentRDDs = (Seq)this.parents.map((parent) -> (RDD)parent.getOrCompute(validTime).getOrElse(() -> {
            throw new SparkException("Couldn't generate RDD from parent at time " + validTime);
         }));
      RDD transformedRDD = (RDD)this.transformFunc.apply(parentRDDs, validTime);
      if (transformedRDD == null) {
         throw new SparkException("Transform function must not return null. Return SparkContext.emptyRDD() instead to represent no element as the result of transformation.");
      } else {
         return new Some(transformedRDD);
      }
   }

   public Object createRDDWithLocalProperties(final Time time, final boolean displayInnerRDDOps, final Function0 body) {
      return super.createRDDWithLocalProperties(time, true, body);
   }

   public TransformedDStream(final Seq parents, final Function2 transformFunc, final ClassTag evidence$1) {
      super(((DStream)parents.head()).ssc(), evidence$1);
      this.parents = parents;
      this.transformFunc = transformFunc;
      .MODULE$.require(parents.nonEmpty(), () -> "List of DStreams to transform is empty");
      .MODULE$.require(((SeqOps)((SeqOps)parents.map((x$1) -> x$1.ssc())).distinct()).size() == 1, () -> "Some of the DStreams have different contexts");
      .MODULE$.require(((SeqOps)((SeqOps)parents.map((x$2) -> x$2.slideDuration())).distinct()).size() == 1, () -> "Some of the DStreams have different slide durations");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
