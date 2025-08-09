package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.Set;
import scala.collection.mutable.Set.;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction2;

public final class PrefixSpan$ implements Logging, Serializable {
   public static final PrefixSpan$ MODULE$ = new PrefixSpan$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Object findFrequentItems(final RDD data, final long minCount, final ClassTag evidence$2) {
      RDD x$1 = data.flatMap((itemsets) -> {
         Set uniqItems = (Set).MODULE$.empty();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(itemsets), (set) -> (Set)uniqItems.$plus$plus$eq(scala.Predef..MODULE$.genericWrapArray(set)));
         return uniqItems.iterator().map((x$3) -> new Tuple2(x$3, BoxesRunTime.boxToLong(1L)));
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Long();
      Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(x$1);
      RDD qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(x$1, evidence$2, x$3, (Ordering)null).reduceByKey((JFunction2.mcJJJ.sp)(x$4x, x$5x) -> x$4x + x$5x).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$findFrequentItems$5(minCount, x0$1)));
      Function1 x$5 = (x$6x) -> BoxesRunTime.boxToLong($anonfun$findFrequentItems$6(x$6x));
      boolean x$6 = qual$1.sortBy$default$2();
      int x$7 = qual$1.sortBy$default$3();
      return qual$1.sortBy(x$5, x$6, x$7, scala.math.Ordering.Long..MODULE$, scala.reflect.ClassTag..MODULE$.Long()).map((x$7x) -> x$7x._1(), evidence$2).collect();
   }

   public RDD toDatabaseInternalRepr(final RDD data, final scala.collection.immutable.Map itemToInt, final ClassTag evidence$3) {
      return data.flatMap((itemsets) -> {
         ArrayBuilder allItems = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
         BooleanRef containsFreqItems = BooleanRef.create(false);
         allItems.$plus$eq(BoxesRunTime.boxToInteger(0));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(itemsets), (itemsetsx) -> {
            ArrayBuilder items = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.genericArrayOps(itemsetsx), (item) -> itemToInt.contains(item) ? items.$plus$eq(BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(itemToInt.apply(item)) + 1)) : BoxedUnit.UNIT);
            int[] result = (int[])items.result();
            if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.intArrayOps(result))) {
               containsFreqItems.elem = true;
               allItems.$plus$plus$eq(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps(result), scala.math.Ordering.Int..MODULE$)));
               return allItems.$plus$eq(BoxesRunTime.boxToInteger(0));
            } else {
               return BoxedUnit.UNIT;
            }
         });
         return containsFreqItems.elem ? scala.package..MODULE$.Iterator().single(allItems.result()) : scala.package..MODULE$.Iterator().empty();
      }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
   }

   public RDD genFreqPatterns(final RDD data, final long minCount, final int maxPatternLength, final long maxLocalProjDBSize) {
      SparkContext sc;
      label29: {
         sc = data.sparkContext();
         StorageLevel var10000 = data.getStorageLevel();
         StorageLevel var8 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var8 != null) {
               break label29;
            }
         } else if (!var10000.equals(var8)) {
            break label29;
         }

         this.logWarning((Function0)(() -> "Input data is not cached."));
      }

      RDD postfixes = data.map((items) -> new PrefixSpan.Postfix(items, PrefixSpan.Postfix$.MODULE$.$lessinit$greater$default$2(), PrefixSpan.Postfix$.MODULE$.$lessinit$greater$default$3()), scala.reflect.ClassTag..MODULE$.apply(PrefixSpan.Postfix.class));
      ArrayBuffer localFreqPatterns = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      scala.collection.mutable.Map smallPrefixes = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      PrefixSpan.Prefix emptyPrefix = PrefixSpan.Prefix$.MODULE$.empty();

      scala.collection.mutable.Map newLargePrefixes;
      for(ObjectRef largePrefixes = ObjectRef.create((scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(emptyPrefix.id())), emptyPrefix)})))); ((scala.collection.mutable.Map)largePrefixes.elem).nonEmpty(); largePrefixes.elem = newLargePrefixes) {
         int numLocalFreqPatterns = localFreqPatterns.length();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of local frequent patterns: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_LOCAL_FREQUENT_PATTERN..MODULE$, BoxesRunTime.boxToInteger(numLocalFreqPatterns))}))))));
         if (numLocalFreqPatterns > 1000000) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n             | Collected ", "\n             | local frequent patterns. You may want to consider:\n             |   1. increase minSupport,\n             |   2. decrease maxPatternLength,\n             |   3. increase maxLocalProjDBSize.\n           "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_LOCAL_FREQUENT_PATTERN..MODULE$, BoxesRunTime.boxToInteger(numLocalFreqPatterns))}))).stripMargin()));
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of small prefixes: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PREFIXES..MODULE$, BoxesRunTime.boxToInteger(smallPrefixes.size()))})))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of large prefixes: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PREFIXES..MODULE$, BoxesRunTime.boxToInteger(((scala.collection.mutable.Map)largePrefixes.elem).size()))})))));
         PrefixSpan.Prefix[] largePrefixArray = (PrefixSpan.Prefix[])((scala.collection.mutable.Map)largePrefixes.elem).values().toArray(scala.reflect.ClassTag..MODULE$.apply(PrefixSpan.Prefix.class));
         Tuple2[] freqPrefixes = (Tuple2[])org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(postfixes.flatMap((postfix) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(largePrefixArray), (prefix) -> postfix.project(prefix).genPrefixItems().map((x0$1) -> {
                  if (x0$1 != null) {
                     int item = x0$1._1$mcI$sp();
                     long postfixSize = x0$1._2$mcJ$sp();
                     return new Tuple2(new Tuple2.mcII.sp(prefix.id(), item), new Tuple2.mcJJ.sp(1L, postfixSize));
                  } else {
                     throw new MatchError(x0$1);
                  }
               }), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).reduceByKey((cs0, cs1) -> new Tuple2.mcJJ.sp(cs0._1$mcJ$sp() + cs1._1$mcJ$sp(), cs0._2$mcJ$sp() + cs1._2$mcJ$sp())).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$genFreqPatterns$11(minCount, x0$2))).collect();
         newLargePrefixes = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])freqPrefixes), (x0$3) -> {
            if (x0$3 != null) {
               Tuple2 var10 = (Tuple2)x0$3._1();
               Tuple2 var11 = (Tuple2)x0$3._2();
               if (var10 != null) {
                  int id = var10._1$mcI$sp();
                  int item = var10._2$mcI$sp();
                  if (var11 != null) {
                     long count = var11._1$mcJ$sp();
                     long projDBSize = var11._2$mcJ$sp();
                     PrefixSpan.Prefix newPrefix = ((PrefixSpan.Prefix)((scala.collection.mutable.Map)largePrefixes.elem).apply(BoxesRunTime.boxToInteger(id))).$colon$plus(item);
                     localFreqPatterns.$plus$eq(new Tuple2(scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.intArrayOps(newPrefix.items()), BoxesRunTime.boxToInteger(0), scala.reflect.ClassTag..MODULE$.Int()), BoxesRunTime.boxToLong(count)));
                     if (newPrefix.length() < maxPatternLength) {
                        if (projDBSize > maxLocalProjDBSize) {
                           return newLargePrefixes.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(newPrefix.id())), newPrefix));
                        }

                        return smallPrefixes.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(newPrefix.id())), newPrefix));
                     }

                     return BoxedUnit.UNIT;
                  }
               }
            }

            throw new MatchError(x0$3);
         });
      }

      RDD freqPatterns = sc.parallelize(localFreqPatterns.toSeq(), 1, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      int numSmallPrefixes = smallPrefixes.size();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"number of small prefixes for local processing: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PREFIXES..MODULE$, BoxesRunTime.boxToInteger(numSmallPrefixes))}))))));
      if (numSmallPrefixes > 0) {
         Broadcast bcSmallPrefixes = sc.broadcast(smallPrefixes, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.Map.class));
         RDD distributedFreqPattern = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(postfixes.flatMap((postfix) -> (Iterable)((IterableOps)((MapOps)bcSmallPrefixes.value()).values().map((prefix) -> new Tuple2(BoxesRunTime.boxToInteger(prefix.id()), postfix.project(prefix).compressed()))).filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$genFreqPatterns$16(x$8))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(PrefixSpan.Postfix.class), scala.math.Ordering.Int..MODULE$).groupByKey().flatMap((x0$4) -> {
            if (x0$4 != null) {
               int id = x0$4._1$mcI$sp();
               Iterable projPostfixes = (Iterable)x0$4._2();
               PrefixSpan.Prefix prefix = (PrefixSpan.Prefix)((MapOps)bcSmallPrefixes.value()).apply(BoxesRunTime.boxToInteger(id));
               LocalPrefixSpan localPrefixSpan = new LocalPrefixSpan(minCount, maxPatternLength - prefix.length());
               return localPrefixSpan.run((PrefixSpan.Postfix[])projPostfixes.toArray(scala.reflect.ClassTag..MODULE$.apply(PrefixSpan.Postfix.class))).map((x0$5) -> {
                  if (x0$5 != null) {
                     int[] pattern = (int[])x0$5._1();
                     long count = x0$5._2$mcJ$sp();
                     return new Tuple2(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps(prefix.items()), pattern, scala.reflect.ClassTag..MODULE$.Int()), BoxesRunTime.boxToLong(count));
                  } else {
                     throw new MatchError(x0$5);
                  }
               });
            } else {
               throw new MatchError(x0$4);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         freqPatterns = freqPatterns.$plus$plus(distributedFreqPattern);
      }

      return freqPatterns;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PrefixSpan$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findFrequentItems$5(final long minCount$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long count = x0$1._2$mcJ$sp();
         return count >= minCount$2;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$findFrequentItems$6(final Tuple2 x$6) {
      return -x$6._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genFreqPatterns$11(final long minCount$3, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tuple2 cs = (Tuple2)x0$2._2();
         return cs._1$mcJ$sp() >= minCount$3;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genFreqPatterns$16(final Tuple2 x$8) {
      return ((PrefixSpan.Postfix)x$8._2()).nonEmpty();
   }

   private PrefixSpan$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
