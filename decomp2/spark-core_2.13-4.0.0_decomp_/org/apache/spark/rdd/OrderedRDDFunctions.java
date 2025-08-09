package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.Partitioner;
import org.apache.spark.RangePartitioner;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.collection.ExternalSorter;
import org.apache.spark.util.collection.ExternalSorter$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product2;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Range;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001\u0002\b\u0010\u0001aA\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\t\u0019\u0002\u0011\u0019\u0011)A\u0006\u001b\"A\u0001\u000b\u0001B\u0002B\u0003-\u0011\u000b\u0003\u0005X\u0001\t\r\t\u0015a\u0003Y\u0011!I\u0006AaA!\u0002\u0017Q\u0006\"B.\u0001\t\u0003a\u0006bB6\u0001\u0005\u0004%I\u0001\u001c\u0005\u0007[\u0002\u0001\u000b\u0011B'\t\u000b9\u0004A\u0011A8\t\u000fy\u0004\u0011\u0013!C\u0001\u007f\"I\u00111\u0003\u0001\u0012\u0002\u0013\u0005\u0011Q\u0003\u0005\b\u00033\u0001A\u0011AA\u000e\u0011\u001d\tI\u0003\u0001C\u0001\u0003W\u00111c\u0014:eKJ,GM\u0015#E\rVt7\r^5p]NT!\u0001E\t\u0002\u0007I$GM\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001)B!G\"KsM!\u0001A\u0007\u0011'!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\u0019\te.\u001f*fMB\u0011\u0011\u0005J\u0007\u0002E)\u00111%E\u0001\tS:$XM\u001d8bY&\u0011QE\t\u0002\b\u0019><w-\u001b8h!\t9sF\u0004\u0002)[9\u0011\u0011\u0006L\u0007\u0002U)\u00111fF\u0001\u0007yI|w\u000e\u001e \n\u0003uI!A\f\u000f\u0002\u000fA\f7m[1hK&\u0011\u0001'\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003]q\tAa]3mMB\u0019A'N\u001c\u000e\u0003=I!AN\b\u0003\u0007I#E\t\u0005\u00029s1\u0001A!\u0002\u001e\u0001\u0005\u0004Y$!\u0001)\u0012\u0005qz\u0004CA\u000e>\u0013\tqDDA\u0004O_RD\u0017N\\4\u0011\tm\u0001%)S\u0005\u0003\u0003r\u0011\u0001\u0002\u0015:pIV\u001cGO\r\t\u0003q\r#Q\u0001\u0012\u0001C\u0002\u0015\u0013\u0011aS\t\u0003y\u0019\u0003\"aG$\n\u0005!c\"aA!osB\u0011\u0001H\u0013\u0003\u0006\u0017\u0002\u0011\r!\u0012\u0002\u0002-\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u001dr%)\u0003\u0002Pc\tAqJ\u001d3fe&tw-\u0001\u0006fm&$WM\\2fII\u00022AU+C\u001b\u0005\u0019&B\u0001+\u001d\u0003\u001d\u0011XM\u001a7fGRL!AV*\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00134!\r\u0011V+S\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004c\u0001*Vo\u00051A(\u001b8jiz\"\"!X2\u0015\u000by{\u0006-\u00192\u0011\u000bQ\u0002!)S\u001c\t\u000b13\u00019A'\t\u000bA3\u00019A)\t\u000b]3\u00019\u0001-\t\u000be3\u00019\u0001.\t\u000bI2\u0001\u0019A\u001a)\u0005\u0019)\u0007C\u00014j\u001b\u00059'B\u00015\u0012\u0003)\tgN\\8uCRLwN\\\u0005\u0003U\u001e\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\f\u0001b\u001c:eKJLgnZ\u000b\u0002\u001b\u0006IqN\u001d3fe&tw\rI\u0001\ng>\u0014HOQ=LKf$2\u0001\u001d;z!\r!T'\u001d\t\u00057I\u0014\u0015*\u0003\u0002t9\t1A+\u001e9mKJBq!^\u0005\u0011\u0002\u0003\u0007a/A\u0005bg\u000e,g\u000eZ5oOB\u00111d^\u0005\u0003qr\u0011qAQ8pY\u0016\fg\u000eC\u0004{\u0013A\u0005\t\u0019A>\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t!\tYB0\u0003\u0002~9\t\u0019\u0011J\u001c;\u0002'M|'\u000f\u001e\"z\u0017\u0016LH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0005!f\u0001<\u0002\u0004-\u0012\u0011Q\u0001\t\u0005\u0003\u000f\ty!\u0004\u0002\u0002\n)!\u00111BA\u0007\u0003%)hn\u00195fG.,GM\u0003\u0002i9%!\u0011\u0011CA\u0005\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0014g>\u0014HOQ=LKf$C-\u001a4bk2$HEM\u000b\u0003\u0003/Q3a_A\u0002\u0003\t\u0012X\r]1si&$\u0018n\u001c8B]\u0012\u001cvN\u001d;XSRD\u0017N\u001c)beRLG/[8ogR\u0019\u0001/!\b\t\u000f\u0005}A\u00021\u0001\u0002\"\u0005Y\u0001/\u0019:uSRLwN\\3s!\u0011\t\u0019#!\n\u000e\u0003EI1!a\n\u0012\u0005-\u0001\u0016M\u001d;ji&|g.\u001a:\u0002\u001b\u0019LG\u000e^3s\u0005f\u0014\u0016M\\4f)\u0015\u0019\u0014QFA\u0019\u0011\u0019\ty#\u0004a\u0001\u0005\u0006)An\\<fe\"1\u00111G\u0007A\u0002\t\u000bQ!\u001e9qKJ\u0004"
)
public class OrderedRDDFunctions implements Logging, Serializable {
   private final RDD self;
   private final Ordering evidence$1;
   private final ClassTag evidence$2;
   private final ClassTag evidence$3;
   private final Ordering ordering;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Ordering ordering() {
      return this.ordering;
   }

   public RDD sortByKey(final boolean ascending, final int numPartitions) {
      return (RDD)this.self.withScope(() -> {
         RangePartitioner part = new RangePartitioner(numPartitions, this.self, ascending, this.evidence$1, this.evidence$2);
         return (new ShuffledRDD(this.self, part, this.evidence$2, this.evidence$3, this.evidence$3)).setKeyOrdering(ascending ? this.ordering() : this.ordering().reverse());
      });
   }

   public boolean sortByKey$default$1() {
      return true;
   }

   public int sortByKey$default$2() {
      return this.self.partitions().length;
   }

   public RDD repartitionAndSortWithinPartitions(final Partitioner partitioner) {
      return (RDD)this.self.withScope(() -> {
         Option var10000 = this.self.partitioner();
         Some var2 = new Some(partitioner);
         if (var10000 == null) {
            if (var2 == null) {
               return this.self.mapPartitions((iter) -> {
                  TaskContext context = TaskContext$.MODULE$.get();
                  ExternalSorter sorter = new ExternalSorter(context, .MODULE$, .MODULE$, new Some(this.ordering()), ExternalSorter$.MODULE$.$lessinit$greater$default$5());
                  return new InterruptibleIterator(context, sorter.insertAllAndUpdateMetrics(iter));
               }, true, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            }
         } else if (var10000.equals(var2)) {
            return this.self.mapPartitions((iter) -> {
               TaskContext context = TaskContext$.MODULE$.get();
               ExternalSorter sorter = new ExternalSorter(context, .MODULE$, .MODULE$, new Some(this.ordering()), ExternalSorter$.MODULE$.$lessinit$greater$default$5());
               return new InterruptibleIterator(context, sorter.insertAllAndUpdateMetrics(iter));
            }, true, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         }

         return (new ShuffledRDD(this.self, partitioner, this.evidence$2, this.evidence$3, this.evidence$3)).setKeyOrdering(this.ordering());
      });
   }

   public RDD filterByRange(final Object lower, final Object upper) {
      return (RDD)this.self.withScope(() -> {
         Object var10000;
         label20: {
            Option var6 = this.self.partitioner();
            if (var6 instanceof Some var7) {
               Partitioner rp = (Partitioner)var7.value();
               if (rp instanceof RangePartitioner var9) {
                  Tuple2.mcII.sp var11 = new Tuple2.mcII.sp(var9.getPartition(lower), var9.getPartition(upper));
                  if (var11 == null) {
                     throw new MatchError(var11);
                  }

                  int l = ((Tuple2)var11)._1$mcI$sp();
                  int u = ((Tuple2)var11)._2$mcI$sp();
                  Range.Inclusive partitionIndices = scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(Math.min(l, u)), Math.max(l, u));
                  var10000 = PartitionPruningRDD$.MODULE$.create(this.self, (JFunction1.mcZI.sp)(x) -> partitionIndices.contains(x));
                  break label20;
               }
            }

            var10000 = this.self;
         }

         RDD rddToFilter = (RDD)var10000;
         return rddToFilter.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$filterByRange$3(this, lower, upper, x0$1)));
      });
   }

   private final boolean inRange$1(final Object k, final Object lower$1, final Object upper$1) {
      return this.ordering().gteq(k, lower$1) && this.ordering().lteq(k, upper$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterByRange$3(final OrderedRDDFunctions $this, final Object lower$1, final Object upper$1, final Product2 x0$1) {
      if (x0$1 instanceof Tuple2 var6) {
         Object k = var6._1();
         return $this.inRange$1(k, lower$1, upper$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   @DeveloperApi
   public OrderedRDDFunctions(final RDD self, final Ordering evidence$1, final ClassTag evidence$2, final ClassTag evidence$3, final ClassTag evidence$4) {
      this.self = self;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.evidence$3 = evidence$3;
      Logging.$init$(this);
      this.ordering = (Ordering)scala.Predef..MODULE$.implicitly(evidence$1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
