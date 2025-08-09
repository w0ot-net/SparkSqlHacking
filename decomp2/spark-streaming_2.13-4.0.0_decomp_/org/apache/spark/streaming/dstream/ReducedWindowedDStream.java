package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.CoGroupedRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Interval;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IndexedSeqOps;
import scala.collection.Iterable;
import scala.collection.immutable.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c!\u0002\f\u0018\u0001e\t\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\t\u0011u\u0002!\u0011!Q\u0001\nyB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005\u0007\"AA\n\u0001B\u0001B\u0003%Q\n\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003N\u0011!\u0011\u0006A!A!\u0002\u0013\u0019\u0006\u0002C,\u0001\u0005\u0007\u0005\u000b1\u0002-\t\u0011y\u0003!1!Q\u0001\f}CQ\u0001\u0019\u0001\u0005\u0002\u0005Dq!\u001c\u0001C\u0002\u0013%a\u000e\u0003\u0004p\u0001\u0001\u0006Ia\t\u0005\u0006a\u0002!\t!\u001d\u0005\u0006e\u0002!\te\u001d\u0005\u0007\u0003\u0017\u0001A\u0011I9\t\u0013\u00055\u0001A1A\u0005B\u0005=\u0001bBA\t\u0001\u0001\u0006I!\u0013\u0005\u0007\u0003'\u0001A\u0011I9\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018!9\u0011\u0011\u0006\u0001\u0005B\u0005-\u0002bBA\u0019\u0001\u0011\u0005\u00131\u0007\u0002\u0017%\u0016$WoY3e/&tGm\\<fI\u0012\u001bFO]3b[*\u0011\u0001$G\u0001\bIN$(/Z1n\u0015\tQ2$A\u0005tiJ,\u0017-\\5oO*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014x-F\u0002#_i\u001a\"\u0001A\u0012\u0011\u0007\u0011*s%D\u0001\u0018\u0013\t1sCA\u0004E'R\u0014X-Y7\u0011\t!ZS&O\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t1A+\u001e9mKJ\u0002\"AL\u0018\r\u0001\u0011)\u0001\u0007\u0001b\u0001e\t\t1j\u0001\u0001\u0012\u0005M2\u0004C\u0001\u00155\u0013\t)\u0014FA\u0004O_RD\u0017N\\4\u0011\u0005!:\u0014B\u0001\u001d*\u0005\r\te.\u001f\t\u0003]i\"Qa\u000f\u0001C\u0002I\u0012\u0011AV\u0001\u0007a\u0006\u0014XM\u001c;\u0002\u0015I,G-^2f\rVt7\rE\u0003)\u007feJ\u0014(\u0003\u0002AS\tIa)\u001e8di&|gNM\u0001\u000eS:4(+\u001a3vG\u00164UO\\2\u0002\u0015\u0019LG\u000e^3s\rVt7\rE\u0002)\t\u001aK!!R\u0015\u0003\r=\u0003H/[8o!\u0011AsiJ%\n\u0005!K#!\u0003$v]\u000e$\u0018n\u001c82!\tA#*\u0003\u0002LS\t9!i\\8mK\u0006t\u0017aD0xS:$wn\u001e#ve\u0006$\u0018n\u001c8\u0011\u00059{U\"A\r\n\u0005AK\"\u0001\u0003#ve\u0006$\u0018n\u001c8\u0002\u001d}\u001bH.\u001b3f\tV\u0014\u0018\r^5p]\u0006Y\u0001/\u0019:uSRLwN\\3s!\t!V+D\u0001\u001c\u0013\t16DA\u0006QCJ$\u0018\u000e^5p]\u0016\u0014\u0018AC3wS\u0012,gnY3%cA\u0019\u0011\fX\u0017\u000e\u0003iS!aW\u0015\u0002\u000fI,g\r\\3di&\u0011QL\u0017\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007ec\u0016(\u0001\u0004=S:LGO\u0010\u000b\tE\u001a<\u0007.\u001b6lYR\u00191\rZ3\u0011\t\u0011\u0002Q&\u000f\u0005\u0006/*\u0001\u001d\u0001\u0017\u0005\u0006=*\u0001\u001da\u0018\u0005\u0006y)\u0001\ra\t\u0005\u0006{)\u0001\rA\u0010\u0005\u0006\u0003*\u0001\rA\u0010\u0005\u0006\u0005*\u0001\ra\u0011\u0005\u0006\u0019*\u0001\r!\u0014\u0005\u0006#*\u0001\r!\u0014\u0005\u0006%*\u0001\raU\u0001\u000ee\u0016$WoY3e'R\u0014X-Y7\u0016\u0003\r\naB]3ek\u000e,Gm\u0015;sK\u0006l\u0007%\u0001\bxS:$wn\u001e#ve\u0006$\u0018n\u001c8\u0016\u00035\u000bA\u0002Z3qK:$WM\\2jKN,\u0012\u0001\u001e\t\u0005kv\f\tA\u0004\u0002ww:\u0011qO_\u0007\u0002q*\u0011\u00110M\u0001\u0007yI|w\u000e\u001e \n\u0003)J!\u0001`\u0015\u0002\u000fA\f7m[1hK&\u0011ap \u0002\u0005\u0019&\u001cHO\u0003\u0002}SA\"\u00111AA\u0004!\u0011!S%!\u0002\u0011\u00079\n9\u0001\u0002\u0006\u0002\n9\t\t\u0011!A\u0003\u0002I\u00121a\u0018\u00132\u00035\u0019H.\u001b3f\tV\u0014\u0018\r^5p]\u0006qQ.^:u\u0007\",7m\u001b9pS:$X#A%\u0002\u001f5,8\u000f^\"iK\u000e\\\u0007o\\5oi\u0002\na\u0003]1sK:$(+Z7f[\n,'\u000fR;sCRLwN\\\u0001\ba\u0016\u00148/[:u)\r\u0019\u0013\u0011\u0004\u0005\b\u00037\u0019\u0002\u0019AA\u000f\u00031\u0019Ho\u001c:bO\u0016dUM^3m!\u0011\ty\"!\n\u000e\u0005\u0005\u0005\"bAA\u00127\u000591\u000f^8sC\u001e,\u0017\u0002BA\u0014\u0003C\u0011Ab\u0015;pe\u0006<W\rT3wK2\f!b\u00195fG.\u0004x.\u001b8u)\r\u0019\u0013Q\u0006\u0005\u0007\u0003_!\u0002\u0019A'\u0002\u0011%tG/\u001a:wC2\fqaY8naV$X\r\u0006\u0003\u00026\u0005\r\u0003\u0003\u0002\u0015E\u0003o\u0001R!!\u000f\u0002@\u001dj!!a\u000f\u000b\u0007\u0005u2$A\u0002sI\u0012LA!!\u0011\u0002<\t\u0019!\u000b\u0012#\t\u000f\u0005\u0015S\u00031\u0001\u0002H\u0005Ia/\u00197jIRKW.\u001a\t\u0004\u001d\u0006%\u0013bAA&3\t!A+[7f\u0001"
)
public class ReducedWindowedDStream extends DStream {
   private final DStream parent;
   private final Function2 reduceFunc;
   private final Function2 invReduceFunc;
   private final Option filterFunc;
   private final Duration _windowDuration;
   private final Duration _slideDuration;
   private final Partitioner partitioner;
   private final ClassTag evidence$1;
   private final DStream reducedStream;
   private final boolean mustCheckpoint;

   private DStream reducedStream() {
      return this.reducedStream;
   }

   public Duration windowDuration() {
      return this._windowDuration;
   }

   public List dependencies() {
      return new .colon.colon(this.reducedStream(), scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this._slideDuration;
   }

   public boolean mustCheckpoint() {
      return this.mustCheckpoint;
   }

   public Duration parentRememberDuration() {
      return this.rememberDuration().$plus(this.windowDuration());
   }

   public DStream persist(final StorageLevel storageLevel) {
      super.persist(storageLevel);
      this.reducedStream().persist(storageLevel);
      return this;
   }

   public DStream checkpoint(final Duration interval) {
      super.checkpoint(interval);
      return this;
   }

   public Option compute(final Time validTime) {
      Function2 reduceF = this.reduceFunc;
      Function2 invReduceF = this.invReduceFunc;
      Interval currentWindow = new Interval(validTime.$minus(this.windowDuration()).$plus(this.parent.slideDuration()), validTime);
      Interval previousWindow = currentWindow.$minus(this.slideDuration());
      this.logDebug(() -> "Window time = " + this.windowDuration());
      this.logDebug(() -> "Slide time = " + this.slideDuration());
      this.logDebug(() -> "Zero time = " + this.zeroTime());
      this.logDebug(() -> "Current window = " + currentWindow);
      this.logDebug(() -> "Previous window = " + previousWindow);
      Seq oldRDDs = this.reducedStream().slice(previousWindow.beginTime(), currentWindow.beginTime().$minus(this.parent.slideDuration()));
      this.logDebug(() -> "# old RDDs = " + oldRDDs.size());
      Seq newRDDs = this.reducedStream().slice(previousWindow.endTime().$plus(this.parent.slideDuration()), currentWindow.endTime());
      this.logDebug(() -> "# new RDDs = " + newRDDs.size());
      RDD previousWindowRDD = (RDD)this.getOrCompute(previousWindow.endTime()).getOrElse(() -> {
         SparkContext qual$1 = this.ssc().sc();
         Seq x$1 = (Seq)scala.collection.immutable.Nil..MODULE$;
         int x$2 = qual$1.makeRDD$default$2();
         return qual$1.makeRDD(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      });
      ArrayBuffer allRDDs = (ArrayBuffer)(new ArrayBuffer()).$plus$eq(previousWindowRDD).$plus$plus$eq(oldRDDs).$plus$plus$eq(newRDDs);
      CoGroupedRDD cogroupedRDD = new CoGroupedRDD(allRDDs.toSeq(), this.partitioner, this.evidence$1);
      int numOldValues = oldRDDs.size();
      int numNewValues = newRDDs.size();
      Function1 mergeValues = (arrayOfValues) -> {
         if (arrayOfValues.length != 1 + numOldValues + numNewValues) {
            throw new Exception("Unexpected number of sequences of reduced values");
         } else {
            IndexedSeq oldValues = (IndexedSeq)((IndexedSeqOps)scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), numOldValues).map((i) -> $anonfun$compute$10(arrayOfValues, BoxesRunTime.unboxToInt(i))).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$compute$11(x$1)))).map((x$2) -> x$2.head());
            IndexedSeq newValues = (IndexedSeq)((IndexedSeqOps)scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), numNewValues).map((i) -> $anonfun$compute$13(arrayOfValues, numOldValues, BoxesRunTime.unboxToInt(i))).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$compute$14(x$3)))).map((x$4) -> x$4.head());
            if (arrayOfValues[0].isEmpty()) {
               if (newValues.isEmpty()) {
                  throw new Exception("Neither previous window has value for key, nor new values found. Are you sure your key class hashes consistently?");
               } else {
                  return newValues.reduce(reduceF);
               }
            } else {
               Object tempValue = arrayOfValues[0].head();
               if (!oldValues.isEmpty()) {
                  tempValue = invReduceF.apply(tempValue, oldValues.reduce(reduceF));
               }

               if (!newValues.isEmpty()) {
                  tempValue = reduceF.apply(tempValue, newValues.reduce(reduceF));
               }

               return tempValue;
            }
         }
      };
      ClassTag x$4 = this.evidence$1;
      ClassTag x$5 = scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Iterable.class));
      Null x$6 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(cogroupedRDD);
      RDD mergedValuesRDD = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(cogroupedRDD, x$4, x$5, (Ordering)null).mapValues(mergeValues);
      return this.filterFunc.isDefined() ? new Some(mergedValuesRDD.filter((Function1)this.filterFunc.get())) : new Some(mergedValuesRDD);
   }

   // $FF: synthetic method
   public static final Iterable $anonfun$compute$10(final Iterable[] arrayOfValues$1, final int i) {
      return arrayOfValues$1[i];
   }

   // $FF: synthetic method
   public static final boolean $anonfun$compute$11(final Iterable x$1) {
      return !x$1.isEmpty();
   }

   // $FF: synthetic method
   public static final Iterable $anonfun$compute$13(final Iterable[] arrayOfValues$1, final int numOldValues$1, final int i) {
      return arrayOfValues$1[numOldValues$1 + i];
   }

   // $FF: synthetic method
   public static final boolean $anonfun$compute$14(final Iterable x$3) {
      return !x$3.isEmpty();
   }

   public ReducedWindowedDStream(final DStream parent, final Function2 reduceFunc, final Function2 invReduceFunc, final Option filterFunc, final Duration _windowDuration, final Duration _slideDuration, final Partitioner partitioner, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.parent = parent;
      this.reduceFunc = reduceFunc;
      this.invReduceFunc = invReduceFunc;
      this.filterFunc = filterFunc;
      this._windowDuration = _windowDuration;
      this._slideDuration = _slideDuration;
      this.partitioner = partitioner;
      this.evidence$1 = evidence$1;
      scala.Predef..MODULE$.require(_windowDuration.isMultipleOf(parent.slideDuration()), () -> {
         Duration var10000 = this._windowDuration;
         return "The window duration of ReducedWindowedDStream (" + var10000 + ") must be multiple of the slide duration of parent DStream (" + this.parent.slideDuration() + ")";
      });
      scala.Predef..MODULE$.require(_slideDuration.isMultipleOf(parent.slideDuration()), () -> {
         Duration var10000 = this._slideDuration;
         return "The slide duration of ReducedWindowedDStream (" + var10000 + ") must be multiple of the slide duration of parent DStream (" + this.parent.slideDuration() + ")";
      });
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(parent);
      this.reducedStream = DStream$.MODULE$.toPairDStreamFunctions(parent, evidence$1, evidence$2, (Ordering)null).reduceByKey(reduceFunc, partitioner);
      super.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY_SER());
      this.reducedStream().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY_SER());
      this.mustCheckpoint = true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
