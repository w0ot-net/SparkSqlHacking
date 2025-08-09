package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!\u0002\t\u0012\u0001MY\u0002\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001c\t\u0011q\u0002!\u0011!Q\u0001\nuB\u0001B\u0017\u0001\u0003\u0002\u0003\u0006Ia\u0017\u0005\t?\u0002\u0011\t\u0011)A\u0005A\"A1\r\u0001B\u0001B\u0003%A\r\u0003\u0005l\u0001\t\r\t\u0015a\u0003m\u0011!\u0011\bAaA!\u0002\u0017\u0019\b\u0002\u0003;\u0001\u0005\u0007\u0005\u000b1B;\t\u000bY\u0004A\u0011A<\t\u000f\u0005\u0015\u0001\u0001\"\u0011\u0002\b!9\u0011\u0011\u0004\u0001\u0005B\u0005m\u0001\"CA\u0012\u0001\t\u0007I\u0011IA\u0013\u0011\u001d\t9\u0003\u0001Q\u0001\n\u0001D\u0001\"!\u000b\u0001A\u0013%\u00111\u0006\u0005\b\u0003\u0003\u0002A\u0011IA\"\u00051\u0019F/\u0019;f\tN#(/Z1n\u0015\t\u00112#A\u0004egR\u0014X-Y7\u000b\u0005Q)\u0012!C:ue\u0016\fW.\u001b8h\u0015\t1r#A\u0003ta\u0006\u00148N\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<W\u0003\u0002\u000f*uQ\u001a\"\u0001A\u000f\u0011\u0007yy\u0012%D\u0001\u0012\u0013\t\u0001\u0013CA\u0004E'R\u0014X-Y7\u0011\t\t*seM\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1A+\u001e9mKJ\u0002\"\u0001K\u0015\r\u0001\u0011)!\u0006\u0001b\u0001Y\t\t1j\u0001\u0001\u0012\u00055\u0002\u0004C\u0001\u0012/\u0013\ty3EA\u0004O_RD\u0017N\\4\u0011\u0005\t\n\u0014B\u0001\u001a$\u0005\r\te.\u001f\t\u0003QQ\"Q!\u000e\u0001C\u00021\u0012\u0011aU\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007yy\u0002\b\u0005\u0003#K\u001dJ\u0004C\u0001\u0015;\t\u0015Y\u0004A1\u0001-\u0005\u00051\u0016AC;qI\u0006$XMR;oGB)!E\u0010!E3&\u0011qh\t\u0002\n\rVt7\r^5p]J\u0002\"!\u0011\"\u000e\u0003MI!aQ\n\u0003\tQKW.\u001a\t\u0004\u000b6\u0003fB\u0001$L\u001d\t9%*D\u0001I\u0015\tI5&\u0001\u0004=e>|GOP\u0005\u0002I%\u0011AjI\u0001\ba\u0006\u001c7.Y4f\u0013\tquJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\ta5\u0005E\u0003##\u001e\u001af+\u0003\u0002SG\t1A+\u001e9mKN\u00022!\u0012+:\u0013\t)vJA\u0002TKF\u00042AI,4\u0013\tA6E\u0001\u0004PaRLwN\u001c\t\u0004\u000b6\u000b\u0013a\u00039beRLG/[8oKJ\u0004\"\u0001X/\u000e\u0003UI!AX\u000b\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\u0015aJ,7/\u001a:wKB\u000b'\u000f^5uS>t\u0017N\\4\u0011\u0005\t\n\u0017B\u00012$\u0005\u001d\u0011un\u001c7fC:\f!\"\u001b8ji&\fGN\u0015#E!\r\u0011s+\u001a\t\u0004M&\fS\"A4\u000b\u0005!,\u0012a\u0001:eI&\u0011!n\u001a\u0002\u0004%\u0012#\u0015AC3wS\u0012,gnY3%cA\u0019Q\u000e]\u0014\u000e\u00039T!a\\\u0012\u0002\u000fI,g\r\\3di&\u0011\u0011O\u001c\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u00075\u0004\u0018(\u0001\u0006fm&$WM\\2fIM\u00022!\u001c94\u0003\u0019a\u0014N\\5u}QA\u00010 @\u0000\u0003\u0003\t\u0019\u0001\u0006\u0003zund\b#\u0002\u0010\u0001Oe\u001a\u0004\"B6\n\u0001\ba\u0007\"\u0002:\n\u0001\b\u0019\b\"\u0002;\n\u0001\b)\b\"\u0002\u001c\n\u0001\u00049\u0004\"\u0002\u001f\n\u0001\u0004i\u0004\"\u0002.\n\u0001\u0004Y\u0006\"B0\n\u0001\u0004\u0001\u0007\"B2\n\u0001\u0004!\u0017\u0001\u00043fa\u0016tG-\u001a8dS\u0016\u001cXCAA\u0005!\u0015)\u00151BA\b\u0013\r\tia\u0014\u0002\u0005\u0019&\u001cH\u000f\r\u0003\u0002\u0012\u0005U\u0001\u0003\u0002\u0010 \u0003'\u00012\u0001KA\u000b\t)\t9BCA\u0001\u0002\u0003\u0015\t\u0001\f\u0002\u0004?\u0012\n\u0014!D:mS\u0012,G)\u001e:bi&|g.\u0006\u0002\u0002\u001eA\u0019\u0011)a\b\n\u0007\u0005\u00052C\u0001\u0005EkJ\fG/[8o\u00039iWo\u001d;DQ\u0016\u001c7\u000e]8j]R,\u0012\u0001Y\u0001\u0010[V\u001cHo\u00115fG.\u0004x.\u001b8uA\u000592m\\7qkR,Wk]5oOB\u0013XM^5pkN\u0014F\t\u0012\u000b\t\u0003[\t\u0019$a\u000e\u0002>A!!%a\ff\u0013\r\t\td\t\u0002\u0005'>lW\r\u0003\u0004\u000269\u0001\r\u0001Q\u0001\nE\u0006$8\r\u001b+j[\u0016Dq!!\u000f\u000f\u0001\u0004\tY$A\u0005qCJ,g\u000e\u001e*E\tB\u0019a-\u001b\u001d\t\r\u0005}b\u00021\u0001f\u00031\u0001(/\u001a<Ti\u0006$XM\u0015#E\u0003\u001d\u0019w.\u001c9vi\u0016$2\u0001ZA#\u0011\u0019\t9e\u0004a\u0001\u0001\u0006Ia/\u00197jIRKW.\u001a"
)
public class StateDStream extends DStream {
   private final DStream parent;
   private final Function2 updateFunc;
   private final Partitioner partitioner;
   private final boolean preservePartitioning;
   private final Option initialRDD;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;
   private final boolean mustCheckpoint;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public boolean mustCheckpoint() {
      return this.mustCheckpoint;
   }

   private Some computeUsingPreviousRDD(final Time batchTime, final RDD parentRDD, final RDD prevStateRDD) {
      Function2 updateFuncLocal = this.updateFunc;
      Function1 finalFunc = (iterator) -> {
         Iterator i = iterator.map((t) -> {
            Iterator itr = ((IterableOnce)((Tuple2)t._2())._2()).iterator();
            Option headOption = (Option)(itr.hasNext() ? new Some(itr.next()) : scala.None..MODULE$);
            return new Tuple3(t._1(), ((IterableOnceOps)((Tuple2)t._2())._1()).toSeq(), headOption);
         });
         return (Iterator)updateFuncLocal.apply(batchTime, i);
      };
      ClassTag x$2 = this.evidence$1;
      ClassTag x$3 = this.evidence$2;
      Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(parentRDD);
      RDD cogroupedRDD = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(parentRDD, x$2, x$3, (Ordering)null).cogroup(prevStateRDD, this.partitioner);
      RDD stateRDD = cogroupedRDD.mapPartitions(finalFunc, this.preservePartitioning, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new Some(stateRDD);
   }

   public Option compute(final Time validTime) {
      Option var6 = this.getOrCompute(validTime.$minus(this.slideDuration()));
      if (var6 instanceof Some var7) {
         RDD prevStateRDD = (RDD)var7.value();
         Option var9 = this.parent.getOrCompute(validTime);
         if (var9 instanceof Some var10) {
            RDD parentRDD = (RDD)var10.value();
            return this.computeUsingPreviousRDD(validTime, parentRDD, prevStateRDD);
         } else if (scala.None..MODULE$.equals(var9)) {
            Function2 updateFuncLocal = this.updateFunc;
            Function1 finalFunc = (iterator) -> {
               Iterator i = iterator.map((t) -> new Tuple3(t._1(), scala.package..MODULE$.Seq().empty(), scala.Option..MODULE$.apply(t._2())));
               return (Iterator)updateFuncLocal.apply(validTime, i);
            };
            RDD stateRDD = prevStateRDD.mapPartitions(finalFunc, this.preservePartitioning, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            return new Some(stateRDD);
         } else {
            throw new MatchError(var9);
         }
      } else if (scala.None..MODULE$.equals(var6)) {
         Option var15 = this.parent.getOrCompute(validTime);
         if (var15 instanceof Some) {
            Some var16 = (Some)var15;
            RDD parentRDD = (RDD)var16.value();
            Option var18 = this.initialRDD;
            if (scala.None..MODULE$.equals(var18)) {
               Function2 updateFuncLocal = this.updateFunc;
               Function1 finalFunc = (iterator) -> (Iterator)updateFuncLocal.apply(validTime, iterator.map((tuple) -> new Tuple3(tuple._1(), ((IterableOnceOps)tuple._2()).toSeq(), scala.None..MODULE$)));
               ClassTag x$2 = this.evidence$1;
               ClassTag x$3 = this.evidence$2;
               Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(parentRDD);
               RDD groupedRDD = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(parentRDD, x$2, x$3, (Ordering)null).groupByKey(this.partitioner);
               RDD sessionRDD = groupedRDD.mapPartitions(finalFunc, this.preservePartitioning, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
               return new Some(sessionRDD);
            } else if (var18 instanceof Some) {
               Some var27 = (Some)var18;
               RDD initialStateRDD = (RDD)var27.value();
               return this.computeUsingPreviousRDD(validTime, parentRDD, initialStateRDD);
            } else {
               throw new MatchError(var18);
            }
         } else if (scala.None..MODULE$.equals(var15)) {
            return scala.None..MODULE$;
         } else {
            throw new MatchError(var15);
         }
      } else {
         throw new MatchError(var6);
      }
   }

   public StateDStream(final DStream parent, final Function2 updateFunc, final Partitioner partitioner, final boolean preservePartitioning, final Option initialRDD, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.parent = parent;
      this.updateFunc = updateFunc;
      this.partitioner = partitioner;
      this.preservePartitioning = preservePartitioning;
      this.initialRDD = initialRDD;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      super.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY_SER());
      this.mustCheckpoint = true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
