package org.apache.spark.ml.recommendation;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.collection.OpenHashMap;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.mutable.ArrayBuilder;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class ALS$ implements DefaultParamsReadable, Logging, Serializable {
   public static final ALS$ MODULE$ = new ALS$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
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

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public ALS load(final String path) {
      return (ALS)MLReadable.load$(this, path);
   }

   public Tuple2 train(final RDD ratings, final int rank, final int numUserBlocks, final int numItemBlocks, final int maxIter, final double regParam, final boolean implicitPrefs, final double alpha, final boolean nonnegative, final StorageLevel intermediateRDDStorageLevel, final StorageLevel finalRDDStorageLevel, final int checkpointInterval, final long seed, final ClassTag evidence$1, final Ordering ord) {
      Predef var10000;
      boolean var10001;
      label57: {
         label56: {
            .MODULE$.require(!ratings.isEmpty(), () -> "No ratings available from " + ratings);
            var10000 = .MODULE$;
            StorageLevel var21 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (intermediateRDDStorageLevel == null) {
               if (var21 != null) {
                  break label56;
               }
            } else if (!intermediateRDDStorageLevel.equals(var21)) {
               break label56;
            }

            var10001 = false;
            break label57;
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> "ALS is not designed to run without persisting intermediate RDDs.");
      SparkContext sc = ratings.sparkContext();
      HashPartitioner userPart = new HashPartitioner(numUserBlocks);
      HashPartitioner itemPart = new HashPartitioner(numItemBlocks);
      RDD blockRatings = this.partitionRatings(ratings, userPart, itemPart, evidence$1).persist(intermediateRDDStorageLevel);
      Tuple2 var27 = this.makeBlocks("user", blockRatings, userPart, itemPart, intermediateRDDStorageLevel, evidence$1, ord);
      if (var27 == null) {
         throw new MatchError(var27);
      } else {
         RDD userInBlocks = (RDD)var27._1();
         RDD userOutBlocks = (RDD)var27._2();
         Tuple2 var26 = new Tuple2(userInBlocks, userOutBlocks);
         RDD userInBlocks = (RDD)var26._1();
         RDD userOutBlocks = (RDD)var26._2();
         userOutBlocks.count();
         RDD swappedBlockRatings = blockRatings.map((x0$1) -> {
            if (x0$1 != null) {
               Tuple2 var4 = (Tuple2)x0$1._1();
               ALS.RatingBlock var5 = (ALS.RatingBlock)x0$1._2();
               if (var4 != null) {
                  int userBlockId = var4._1$mcI$sp();
                  int itemBlockId = var4._2$mcI$sp();
                  if (var5 != null) {
                     Object userIds = var5.srcIds();
                     Object itemIds = var5.dstIds();
                     float[] localRatings = var5.ratings();
                     return new Tuple2(new Tuple2.mcII.sp(itemBlockId, userBlockId), new ALS.RatingBlock(itemIds, userIds, localRatings, evidence$1));
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         Tuple2 var34 = this.makeBlocks("item", swappedBlockRatings, itemPart, userPart, intermediateRDDStorageLevel, evidence$1, ord);
         if (var34 != null) {
            RDD itemInBlocks = (RDD)var34._1();
            RDD itemOutBlocks = (RDD)var34._2();
            Tuple2 var33 = new Tuple2(itemInBlocks, itemOutBlocks);
            RDD itemInBlocks = (RDD)var33._1();
            RDD itemOutBlocks = (RDD)var33._2();
            itemOutBlocks.count();
            ALS.LocalIndexEncoder userLocalIndexEncoder = new ALS.LocalIndexEncoder(userPart.numPartitions());
            ALS.LocalIndexEncoder itemLocalIndexEncoder = new ALS.LocalIndexEncoder(itemPart.numPartitions());
            XORShiftRandom seedGen = new XORShiftRandom(seed);
            ObjectRef userFactors = ObjectRef.create(this.initialize(userInBlocks, rank, seedGen.nextLong()));
            ObjectRef itemFactors = ObjectRef.create(this.initialize(itemInBlocks, rank, seedGen.nextLong()));
            ALS.LeastSquaresNESolver solver = (ALS.LeastSquaresNESolver)(nonnegative ? new ALS.NNLSSolver() : new ALS.CholeskySolver());
            ObjectRef previousCheckpointFile = ObjectRef.create(scala.None..MODULE$);
            Function1 shouldCheckpoint = (iter) -> sc.checkpointDir().isDefined() && checkpointInterval != -1 && iter % checkpointInterval == 0;
            Function0 deletePreviousCheckpointFile = () -> ((Option)previousCheckpointFile.elem).foreach((file) -> {
                  Object var10000;
                  try {
                     Path checkpointFile = new Path(file);
                     var10000 = BoxesRunTime.boxToBoolean(checkpointFile.getFileSystem(sc.hadoopConfiguration()).delete(checkpointFile, true));
                  } catch (IOException var4) {
                     MODULE$.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot delete checkpoint file ", ":"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), var4);
                     var10000 = BoxedUnit.UNIT;
                  }

                  return var10000;
               });
            if (implicitPrefs) {
               scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(1), maxIter).foreach((iter) -> $anonfun$train$8(userFactors, intermediateRDDStorageLevel, itemFactors, userOutBlocks, itemInBlocks, rank, regParam, userLocalIndexEncoder, implicitPrefs, alpha, solver, shouldCheckpoint, itemOutBlocks, userInBlocks, itemLocalIndexEncoder, deletePreviousCheckpointFile, previousCheckpointFile, BoxesRunTime.unboxToInt(iter)));
            } else {
               ObjectRef previousCachedItemFactors = ObjectRef.create(scala.None..MODULE$);
               scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), maxIter).foreach$mVc$sp((JFunction1.mcVI.sp)(iter) -> {
                  RDD x$2 = (RDD)userFactors.elem;
                  boolean x$9 = MODULE$.computeFactors$default$7();
                  double x$10 = MODULE$.computeFactors$default$8();
                  itemFactors.elem = MODULE$.computeFactors(x$2, userOutBlocks, itemInBlocks, rank, regParam, userLocalIndexEncoder, x$9, x$10, solver);
                  if (shouldCheckpoint.apply$mcZI$sp(iter)) {
                     ((RDD)itemFactors.elem).setName("itemFactors-" + iter).persist(intermediateRDDStorageLevel);
                     ((RDD)itemFactors.elem).checkpoint();
                     ((RDD)itemFactors.elem).count();
                     RDD qual$2 = (RDD)itemFactors.elem;
                     boolean x$11 = qual$2.cleanShuffleDependencies$default$1();
                     qual$2.cleanShuffleDependencies(x$11);
                     deletePreviousCheckpointFile.apply$mcV$sp();
                     ((Option)previousCachedItemFactors.elem).foreach((x$8) -> x$8.unpersist(x$8.unpersist$default$1()));
                     previousCheckpointFile.elem = ((RDD)itemFactors.elem).getCheckpointFile();
                     previousCachedItemFactors.elem = scala.Option..MODULE$.apply((RDD)itemFactors.elem);
                  }

                  RDD x$12 = (RDD)itemFactors.elem;
                  boolean x$19 = MODULE$.computeFactors$default$7();
                  double x$20 = MODULE$.computeFactors$default$8();
                  userFactors.elem = MODULE$.computeFactors(x$12, itemOutBlocks, userInBlocks, rank, regParam, itemLocalIndexEncoder, x$19, x$20, solver);
               });
            }

            RDD userIdAndFactors;
            RDD itemIdAndFactors;
            label71: {
               userIdAndFactors = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(userInBlocks, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(ALS.InBlock.class), scala.math.Ordering.Int..MODULE$).mapValues((x$9) -> x$9.srcIds()), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(evidence$1.runtimeClass())), scala.math.Ordering.Int..MODULE$).join((RDD)userFactors.elem).mapPartitions((items) -> items.flatMap((x0$2) -> {
                     if (x0$2 != null) {
                        Tuple2 var3 = (Tuple2)x0$2._2();
                        if (var3 != null) {
                           Object ids = var3._1();
                           float[][] factors = (float[][])var3._2();
                           return scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.genericArrayOps(ids)).zip(scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.refArrayOps((Object[])factors)));
                        }
                     }

                     throw new MatchError(x0$2);
                  }), true, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName("userFactors").persist(finalRDDStorageLevel);
               itemIdAndFactors = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(itemInBlocks, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(ALS.InBlock.class), scala.math.Ordering.Int..MODULE$).mapValues((x$10) -> x$10.srcIds()), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(evidence$1.runtimeClass())), scala.math.Ordering.Int..MODULE$).join((RDD)itemFactors.elem).mapPartitions((items) -> items.flatMap((x0$3) -> {
                     if (x0$3 != null) {
                        Tuple2 var3 = (Tuple2)x0$3._2();
                        if (var3 != null) {
                           Object ids = var3._1();
                           float[][] factors = (float[][])var3._2();
                           return scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.genericArrayOps(ids)).zip(scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.refArrayOps((Object[])factors)));
                        }
                     }

                     throw new MatchError(x0$3);
                  }), true, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName("itemFactors").persist(finalRDDStorageLevel);
               StorageLevel var51 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (finalRDDStorageLevel == null) {
                  if (var51 != null) {
                     break label71;
                  }
               } else if (!finalRDDStorageLevel.equals(var51)) {
                  break label71;
               }

               BoxedUnit var54 = BoxedUnit.UNIT;
               return new Tuple2(userIdAndFactors, itemIdAndFactors);
            }

            userIdAndFactors.count();
            userInBlocks.unpersist(userInBlocks.unpersist$default$1());
            userOutBlocks.unpersist(userOutBlocks.unpersist$default$1());
            itemOutBlocks.unpersist(itemOutBlocks.unpersist$default$1());
            blockRatings.unpersist(blockRatings.unpersist$default$1());
            itemIdAndFactors.count();
            RDD qual$3 = (RDD)itemFactors.elem;
            boolean x$21 = qual$3.unpersist$default$1();
            qual$3.unpersist(x$21);
            itemInBlocks.unpersist(itemInBlocks.unpersist$default$1());
            return new Tuple2(userIdAndFactors, itemIdAndFactors);
         } else {
            throw new MatchError(var34);
         }
      }
   }

   public int train$default$2() {
      return 10;
   }

   public int train$default$3() {
      return 10;
   }

   public int train$default$4() {
      return 10;
   }

   public int train$default$5() {
      return 10;
   }

   public double train$default$6() {
      return 0.1;
   }

   public boolean train$default$7() {
      return false;
   }

   public double train$default$8() {
      return (double)1.0F;
   }

   public boolean train$default$9() {
      return false;
   }

   public StorageLevel train$default$10() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK();
   }

   public StorageLevel train$default$11() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK();
   }

   public int train$default$12() {
      return 10;
   }

   public long train$default$13() {
      return 0L;
   }

   private RDD initialize(final RDD inBlocks, final int rank, final long seed) {
      return inBlocks.mapPartitions((iter) -> iter.map((x0$1) -> {
            if (x0$1 != null) {
               int srcBlockId = x0$1._1$mcI$sp();
               ALS.InBlock inBlock = (ALS.InBlock)x0$1._2();
               XORShiftRandom random = new XORShiftRandom(scala.util.hashing.package..MODULE$.byteswap64(seed ^ (long)srcBlockId));
               float[][] factors = (float[][])scala.Array..MODULE$.fill(scala.runtime.ScalaRunTime..MODULE$.array_length(inBlock.srcIds()), () -> {
                  float[] factor = (float[])scala.Array..MODULE$.fill(rank, (JFunction0.mcF.sp)() -> (float)random.nextGaussian(), scala.reflect.ClassTag..MODULE$.Float());
                  float nrm = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().snrm2(rank, factor, 1);
                  org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().sscal(rank, 1.0F / nrm, factor, 1);
                  return factor;
               }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)));
               return new Tuple2(BoxesRunTime.boxToInteger(srcBlockId), factors);
            } else {
               throw new MatchError(x0$1);
            }
         }), true, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   private RDD partitionRatings(final RDD ratings, final Partitioner srcPart, final Partitioner dstPart, final ClassTag evidence$5) {
      int numPartitions = srcPart.numPartitions() * dstPart.numPartitions();
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(ratings.mapPartitions((iter) -> {
         ALS.RatingBlockBuilder[] builders = (ALS.RatingBlockBuilder[])scala.Array..MODULE$.fill(numPartitions, () -> new ALS.RatingBlockBuilder(evidence$5), scala.reflect.ClassTag..MODULE$.apply(ALS.RatingBlockBuilder.class));
         return iter.flatMap((r) -> {
            int srcBlockId = srcPart.getPartition(r.user());
            int dstBlockId = dstPart.getPartition(r.item());
            int idx = srcBlockId + srcPart.numPartitions() * dstBlockId;
            ALS.RatingBlockBuilder builder = builders[idx];
            builder.add(r);
            if (builder.size() >= 2048) {
               builders[idx] = new ALS.RatingBlockBuilder(evidence$5);
               return scala.package..MODULE$.Iterator().single(new Tuple2(new Tuple2.mcII.sp(srcBlockId, dstBlockId), builder.build()));
            } else {
               return scala.package..MODULE$.Iterator().empty();
            }
         }).$plus$plus(() -> scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.refArrayOps(builders)).zipWithIndex().filter((x$11) -> BoxesRunTime.boxToBoolean($anonfun$partitionRatings$5(x$11))).map((x0$1) -> {
               if (x0$1 != null) {
                  ALS.RatingBlockBuilder block = (ALS.RatingBlockBuilder)x0$1._1();
                  int idx = x0$1._2$mcI$sp();
                  int srcBlockId = idx % srcPart.numPartitions();
                  int dstBlockId = idx / srcPart.numPartitions();
                  return new Tuple2(new Tuple2.mcII.sp(srcBlockId, dstBlockId), block.build());
               } else {
                  throw new MatchError(x0$1);
               }
            }));
      }, ratings.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(ALS.RatingBlock.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).groupByKey(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Iterable.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).mapValues((blocks) -> {
         ALS.RatingBlockBuilder builder = new ALS.RatingBlockBuilder(evidence$5);
         blocks.foreach((other) -> builder.merge(other));
         return builder.build();
      }).setName("ratingBlocks");
   }

   private Tuple2 makeBlocks(final String prefix, final RDD ratingBlocks, final Partitioner srcPart, final Partitioner dstPart, final StorageLevel storageLevel, final ClassTag evidence$10, final Ordering srcOrd) {
      RDD inBlocks = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(ratingBlocks.map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var5 = (Tuple2)x0$1._1();
            ALS.RatingBlock var6 = (ALS.RatingBlock)x0$1._2();
            if (var5 != null) {
               int srcBlockId = var5._1$mcI$sp();
               int dstBlockId = var5._2$mcI$sp();
               if (var6 != null) {
                  Object srcIds = var6.srcIds();
                  Object dstIds = var6.dstIds();
                  float[] ratings = var6.ratings();
                  long start = System.nanoTime();
                  OpenHashSet dstIdSet = new OpenHashSet(1048576, evidence$10);
                  scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.genericArrayOps(dstIds), (k) -> {
                     $anonfun$makeBlocks$2(dstIdSet, k);
                     return BoxedUnit.UNIT;
                  });
                  Object sortedDstIds = evidence$10.newArray(dstIdSet.size());
                  int i = 0;

                  for(int pos = dstIdSet.nextPos(0); pos != -1; ++i) {
                     scala.runtime.ScalaRunTime..MODULE$.array_update(sortedDstIds, i, dstIdSet.getValue(pos));
                     pos = dstIdSet.nextPos(pos + 1);
                  }

                  .MODULE$.assert(i == dstIdSet.size());
                  scala.util.Sorting..MODULE$.quickSort(sortedDstIds, srcOrd);
                  OpenHashMap dstIdToLocalIndex = new OpenHashMap.mcI.sp(scala.runtime.ScalaRunTime..MODULE$.array_length(sortedDstIds), evidence$10, scala.reflect.ClassTag..MODULE$.Int());

                  for(int var20 = 0; var20 < scala.runtime.ScalaRunTime..MODULE$.array_length(sortedDstIds); ++var20) {
                     dstIdToLocalIndex.update$mcI$sp(scala.runtime.ScalaRunTime..MODULE$.array_apply(sortedDstIds, var20), var20);
                  }

                  MODULE$.logDebug((Function0)(() -> {
                     double var10000 = (double)(System.nanoTime() - start);
                     return "Converting to local indices took " + var10000 / (double)1.0E9F + " seconds.";
                  }));
                  int[] dstLocalIndices = (int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.genericArrayOps(dstIds), (k) -> BoxesRunTime.boxToInteger($anonfun$makeBlocks$4(dstIdToLocalIndex, k)), scala.reflect.ClassTag..MODULE$.Int());
                  return new Tuple2(BoxesRunTime.boxToInteger(srcBlockId), new Tuple4(BoxesRunTime.boxToInteger(dstBlockId), srcIds, dstLocalIndices, ratings));
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala.math.Ordering.Int..MODULE$).groupByKey(new HashPartitioner(srcPart.numPartitions())), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Iterable.class), scala.math.Ordering.Int..MODULE$).mapValues((iter) -> {
         ALS.UncompressedInBlockBuilder builder = new ALS.UncompressedInBlockBuilder(new ALS.LocalIndexEncoder(dstPart.numPartitions()), evidence$10, srcOrd);
         iter.foreach((x0$2) -> {
            if (x0$2 != null) {
               int dstBlockId = BoxesRunTime.unboxToInt(x0$2._1());
               Object srcIds = x0$2._2();
               int[] dstLocalIndices = (int[])x0$2._3();
               float[] ratings = (float[])x0$2._4();
               return builder.add(dstBlockId, srcIds, dstLocalIndices, ratings);
            } else {
               throw new MatchError(x0$2);
            }
         });
         return builder.build().compress();
      }).setName(prefix + "InBlocks").persist(storageLevel);
      RDD outBlocks = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(inBlocks, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(ALS.InBlock.class), scala.math.Ordering.Int..MODULE$).mapValues((x0$3) -> {
         if (x0$3 == null) {
            throw new MatchError(x0$3);
         } else {
            Object srcIds = x0$3.srcIds();
            int[] dstPtrs = x0$3.dstPtrs();
            int[] dstEncodedIndices = x0$3.dstEncodedIndices();
            ALS.LocalIndexEncoder encoder = new ALS.LocalIndexEncoder(dstPart.numPartitions());
            ArrayBuilder[] activeIds = (ArrayBuilder[])scala.Array..MODULE$.fill(dstPart.numPartitions(), () -> scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int()), scala.reflect.ClassTag..MODULE$.apply(ArrayBuilder.class));
            int i = 0;

            for(boolean[] seen = new boolean[dstPart.numPartitions()]; i < scala.runtime.ScalaRunTime..MODULE$.array_length(srcIds); ++i) {
               int j = dstPtrs[i];
               Arrays.fill(seen, false);

               for(; j < dstPtrs[i + 1]; ++j) {
                  int dstBlockId = encoder.blockId(dstEncodedIndices[j]);
                  if (!seen[dstBlockId]) {
                     activeIds[dstBlockId].$plus$eq(BoxesRunTime.boxToInteger(i));
                     seen[dstBlockId] = true;
                  }
               }
            }

            return (int[][])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])activeIds), (x) -> (int[])x.result(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
         }
      }).setName(prefix + "OutBlocks").persist(storageLevel);
      return new Tuple2(inBlocks, outBlocks);
   }

   private RDD computeFactors(final RDD srcFactorBlocks, final RDD srcOutBlocks, final RDD dstInBlocks, final int rank, final double regParam, final ALS.LocalIndexEncoder srcEncoder, final boolean implicitPrefs, final double alpha, final ALS.LeastSquaresNESolver solver) {
      int numSrcBlocks;
      Option YtY;
      RDD merged;
      boolean var20;
      label29: {
         label33: {
            numSrcBlocks = srcFactorBlocks.partitions().length;
            YtY = (Option)(implicitPrefs ? new Some(this.computeYtY(srcFactorBlocks, rank)) : scala.None..MODULE$);
            RDD srcOut = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(srcOutBlocks, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE))), scala.math.Ordering.Int..MODULE$).join(srcFactorBlocks).flatMap((x0$1) -> {
               if (x0$1 != null) {
                  int srcBlockId = x0$1._1$mcI$sp();
                  Tuple2 var4 = (Tuple2)x0$1._2();
                  if (var4 != null) {
                     int[][] srcOutBlock = (int[][])var4._1();
                     float[][] srcFactors = (float[][])var4._2();
                     return scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.refArrayOps((Object[])srcOutBlock)).zipWithIndex().map((x0$2) -> {
                        if (x0$2 != null) {
                           int[] activeIndices = (int[])x0$2._1();
                           int dstBlockId = x0$2._2$mcI$sp();
                           return new Tuple2(BoxesRunTime.boxToInteger(dstBlockId), new Tuple2(BoxesRunTime.boxToInteger(srcBlockId), scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps(activeIndices), (idx) -> $anonfun$computeFactors$3(srcFactors, BoxesRunTime.unboxToInt(idx)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)))));
                        } else {
                           throw new MatchError(x0$2);
                        }
                     });
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            merged = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(srcOut, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).groupByKey(new HashPartitioner(dstInBlocks.partitions().length));
            Enumeration.Value var10000 = dstInBlocks.outputDeterministicLevel();
            Enumeration.Value var17 = org.apache.spark.rdd.DeterministicLevel..MODULE$.INDETERMINATE();
            if (var10000 == null) {
               if (var17 == null) {
                  break label33;
               }
            } else if (var10000.equals(var17)) {
               break label33;
            }

            var10000 = srcOutBlocks.outputDeterministicLevel();
            Enumeration.Value var18 = org.apache.spark.rdd.DeterministicLevel..MODULE$.INDETERMINATE();
            if (var10000 == null) {
               if (var18 == null) {
                  break label33;
               }
            } else if (var10000.equals(var18)) {
               break label33;
            }

            var20 = false;
            break label29;
         }

         var20 = true;
      }

      boolean isBlockRDDNondeterministic = var20;
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(dstInBlocks, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(ALS.InBlock.class), scala.math.Ordering.Int..MODULE$).join(merged), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).mapValues((x0$3) -> {
         if (x0$3 != null) {
            ALS.InBlock var15 = (ALS.InBlock)x0$3._1();
            Iterable srcFactors = (Iterable)x0$3._2();
            if (var15 != null) {
               Object dstIds = var15.srcIds();
               int[] srcPtrs = var15.dstPtrs();
               int[] srcEncodedIndices = var15.dstEncodedIndices();
               float[] ratings = var15.ratings();
               float[][][] sortedSrcFactors = new float[numSrcBlocks][][];
               srcFactors.foreach((x0$4) -> {
                  $anonfun$computeFactors$5(sortedSrcFactors, x0$4);
                  return BoxedUnit.UNIT;
               });
               float[][] dstFactors = new float[scala.runtime.ScalaRunTime..MODULE$.array_length(dstIds)][];
               int j = 0;

               for(ALS.NormalEquation ls = new ALS.NormalEquation(rank); j < scala.runtime.ScalaRunTime..MODULE$.array_length(dstIds); ++j) {
                  ls.reset();
                  if (implicitPrefs) {
                     ls.merge((ALS.NormalEquation)YtY.get());
                  } else {
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  int i = srcPtrs[j];

                  int numExplicits;
                  for(numExplicits = 0; i < srcPtrs[j + 1]; ++i) {
                     int encoded = srcEncodedIndices[i];
                     int blockId = srcEncoder.blockId(encoded);
                     int localIndex = srcEncoder.localIndex(encoded);
                     float[] srcFactor = null;

                     try {
                        srcFactor = sortedSrcFactors[blockId][localIndex];
                     } catch (Throwable var38) {
                        if (var38 instanceof ArrayIndexOutOfBoundsException) {
                           ArrayIndexOutOfBoundsException var33 = (ArrayIndexOutOfBoundsException)var38;
                           if (isBlockRDDNondeterministic) {
                              String errMsg = "A failure detected when matching In/Out blocks of users/items. Because at least one In/Out block RDD is found to be nondeterministic now, the issue is probably caused by nondeterministic input data. You can try to checkpoint training data to make it deterministic. If you do `repartition` + `sample` or `randomSplit`, you can also try to sort it before `sample` or `randomSplit` to make it deterministic.";
                              throw new SparkException(errMsg, var33);
                           }
                        }

                        throw var38;
                     }

                     float rating = ratings[i];
                     if (implicitPrefs) {
                        double c1 = alpha * (double)scala.math.package..MODULE$.abs(rating);
                        if ((double)rating > (double)0.0F) {
                           ++numExplicits;
                        }

                        ls.add(srcFactor, (double)rating > (double)0.0F ? (double)1.0F + c1 : (double)0.0F, c1);
                     } else {
                        ls.add(srcFactor, (double)rating, ls.add$default$3());
                        ++numExplicits;
                        BoxedUnit var40 = BoxedUnit.UNIT;
                     }
                  }

                  dstFactors[j] = solver.solve(ls, (double)numExplicits * regParam);
               }

               return dstFactors;
            }
         }

         throw new MatchError(x0$3);
      });
   }

   private boolean computeFactors$default$7() {
      return false;
   }

   private double computeFactors$default$8() {
      return (double)1.0F;
   }

   private ALS.NormalEquation computeYtY(final RDD factorBlocks, final int rank) {
      return (ALS.NormalEquation)org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(factorBlocks, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE))), scala.math.Ordering.Int..MODULE$).values().aggregate(new ALS.NormalEquation(rank), (ne, factors) -> {
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])factors), (x$13) -> ne.add(x$13, (double)0.0F, ne.add$default$3()));
         return ne;
      }, (ne1, ne2) -> ne1.merge(ne2), scala.reflect.ClassTag..MODULE$.apply(ALS.NormalEquation.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ALS$.class);
   }

   // $FF: synthetic method
   public static final RDD $anonfun$train$8(final ObjectRef userFactors$1, final StorageLevel intermediateRDDStorageLevel$1, final ObjectRef itemFactors$1, final RDD userOutBlocks$1, final RDD itemInBlocks$1, final int rank$1, final double regParam$1, final ALS.LocalIndexEncoder userLocalIndexEncoder$1, final boolean implicitPrefs$1, final double alpha$1, final ALS.LeastSquaresNESolver solver$1, final Function1 shouldCheckpoint$1, final RDD itemOutBlocks$1, final RDD userInBlocks$1, final ALS.LocalIndexEncoder itemLocalIndexEncoder$1, final Function0 deletePreviousCheckpointFile$1, final ObjectRef previousCheckpointFile$1, final int iter) {
      ((RDD)userFactors$1.elem).setName("userFactors-" + iter).persist(intermediateRDDStorageLevel$1);
      RDD previousItemFactors = (RDD)itemFactors$1.elem;
      itemFactors$1.elem = MODULE$.computeFactors((RDD)userFactors$1.elem, userOutBlocks$1, itemInBlocks$1, rank$1, regParam$1, userLocalIndexEncoder$1, implicitPrefs$1, alpha$1, solver$1);
      previousItemFactors.unpersist(previousItemFactors.unpersist$default$1());
      ((RDD)itemFactors$1.elem).setName("itemFactors-" + iter).persist(intermediateRDDStorageLevel$1);
      if (shouldCheckpoint$1.apply$mcZI$sp(iter)) {
         ((RDD)itemFactors$1.elem).checkpoint();
      }

      RDD previousUserFactors = (RDD)userFactors$1.elem;
      userFactors$1.elem = MODULE$.computeFactors((RDD)itemFactors$1.elem, itemOutBlocks$1, userInBlocks$1, rank$1, regParam$1, itemLocalIndexEncoder$1, implicitPrefs$1, alpha$1, solver$1);
      if (shouldCheckpoint$1.apply$mcZI$sp(iter)) {
         RDD qual$1 = (RDD)itemFactors$1.elem;
         boolean x$1 = qual$1.cleanShuffleDependencies$default$1();
         qual$1.cleanShuffleDependencies(x$1);
         deletePreviousCheckpointFile$1.apply$mcV$sp();
         previousCheckpointFile$1.elem = ((RDD)itemFactors$1.elem).getCheckpointFile();
      }

      return previousUserFactors.unpersist(previousUserFactors.unpersist$default$1());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$partitionRatings$5(final Tuple2 x$11) {
      return ((ALS.RatingBlockBuilder)x$11._1()).size() > 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$makeBlocks$2(final OpenHashSet dstIdSet$1, final Object k) {
      dstIdSet$1.add(k);
   }

   // $FF: synthetic method
   public static final int $anonfun$makeBlocks$4(final OpenHashMap dstIdToLocalIndex$1, final Object k) {
      return dstIdToLocalIndex$1.apply$mcI$sp(k);
   }

   // $FF: synthetic method
   public static final float[] $anonfun$computeFactors$3(final float[][] srcFactors$1, final int idx) {
      return srcFactors$1[idx];
   }

   // $FF: synthetic method
   public static final void $anonfun$computeFactors$5(final float[][][] sortedSrcFactors$1, final Tuple2 x0$4) {
      if (x0$4 != null) {
         int srcBlockId = x0$4._1$mcI$sp();
         float[][] factors = (float[][])x0$4._2();
         sortedSrcFactors$1[srcBlockId] = factors;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$4);
      }
   }

   private ALS$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
