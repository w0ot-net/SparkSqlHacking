package org.apache.spark.api.java;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.function.DoubleFlatMapFunction;
import org.apache.spark.api.java.function.DoubleFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.FlatMapFunction2;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.partial.PartialResult;
import org.apache.spark.storage.StorageLevel;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512aAA\u0002\u0002\u0002\u001di\u0001\"B\u0015\u0001\t\u0003Q#aE!cgR\u0014\u0018m\u0019;KCZ\f'\u000b\u0012#MS.,'B\u0001\u0003\u0006\u0003\u0011Q\u0017M^1\u000b\u0005\u00199\u0011aA1qS*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014x-F\u0002\u000f7\u0019\u001a2\u0001A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB!acF\r&\u001b\u0005\u0019\u0011B\u0001\r\u0004\u0005-Q\u0015M^1S\t\u0012c\u0015n[3\u0011\u0005iYB\u0002\u0001\u0003\u00069\u0001\u0011\rA\b\u0002\u0002)\u000e\u0001\u0011CA\u0010#!\t\u0001\u0002%\u0003\u0002\"#\t9aj\u001c;iS:<\u0007C\u0001\t$\u0013\t!\u0013CA\u0002B]f\u0004\"A\u0007\u0014\u0005\u000b\u001d\u0002!\u0019\u0001\u0015\u0003\tQC\u0017n]\t\u0003?U\ta\u0001P5oSRtD#A\u0016\u0011\tY\u0001\u0011$\n"
)
public abstract class AbstractJavaRDDLike implements JavaRDDLike {
   public List partitions() {
      return JavaRDDLike.partitions$(this);
   }

   public int getNumPartitions() {
      return JavaRDDLike.getNumPartitions$(this);
   }

   public Optional partitioner() {
      return JavaRDDLike.partitioner$(this);
   }

   public SparkContext context() {
      return JavaRDDLike.context$(this);
   }

   public int id() {
      return JavaRDDLike.id$(this);
   }

   public StorageLevel getStorageLevel() {
      return JavaRDDLike.getStorageLevel$(this);
   }

   public Iterator iterator(final Partition split, final TaskContext taskContext) {
      return JavaRDDLike.iterator$(this, split, taskContext);
   }

   public JavaRDD map(final Function f) {
      return JavaRDDLike.map$(this, f);
   }

   public JavaRDD mapPartitionsWithIndex(final Function2 f, final boolean preservesPartitioning) {
      return JavaRDDLike.mapPartitionsWithIndex$(this, f, preservesPartitioning);
   }

   public boolean mapPartitionsWithIndex$default$2() {
      return JavaRDDLike.mapPartitionsWithIndex$default$2$(this);
   }

   public JavaDoubleRDD mapToDouble(final DoubleFunction f) {
      return JavaRDDLike.mapToDouble$(this, f);
   }

   public JavaPairRDD mapToPair(final PairFunction f) {
      return JavaRDDLike.mapToPair$(this, f);
   }

   public JavaRDD flatMap(final FlatMapFunction f) {
      return JavaRDDLike.flatMap$(this, f);
   }

   public JavaDoubleRDD flatMapToDouble(final DoubleFlatMapFunction f) {
      return JavaRDDLike.flatMapToDouble$(this, f);
   }

   public JavaPairRDD flatMapToPair(final PairFlatMapFunction f) {
      return JavaRDDLike.flatMapToPair$(this, f);
   }

   public JavaRDD mapPartitions(final FlatMapFunction f) {
      return JavaRDDLike.mapPartitions$(this, f);
   }

   public JavaRDD mapPartitions(final FlatMapFunction f, final boolean preservesPartitioning) {
      return JavaRDDLike.mapPartitions$(this, f, preservesPartitioning);
   }

   public JavaDoubleRDD mapPartitionsToDouble(final DoubleFlatMapFunction f) {
      return JavaRDDLike.mapPartitionsToDouble$(this, f);
   }

   public JavaPairRDD mapPartitionsToPair(final PairFlatMapFunction f) {
      return JavaRDDLike.mapPartitionsToPair$(this, f);
   }

   public JavaDoubleRDD mapPartitionsToDouble(final DoubleFlatMapFunction f, final boolean preservesPartitioning) {
      return JavaRDDLike.mapPartitionsToDouble$(this, f, preservesPartitioning);
   }

   public JavaPairRDD mapPartitionsToPair(final PairFlatMapFunction f, final boolean preservesPartitioning) {
      return JavaRDDLike.mapPartitionsToPair$(this, f, preservesPartitioning);
   }

   public void foreachPartition(final VoidFunction f) {
      JavaRDDLike.foreachPartition$(this, f);
   }

   public JavaRDD glom() {
      return JavaRDDLike.glom$(this);
   }

   public JavaPairRDD cartesian(final JavaRDDLike other) {
      return JavaRDDLike.cartesian$(this, other);
   }

   public JavaPairRDD groupBy(final Function f) {
      return JavaRDDLike.groupBy$(this, f);
   }

   public JavaPairRDD groupBy(final Function f, final int numPartitions) {
      return JavaRDDLike.groupBy$(this, f, numPartitions);
   }

   public JavaRDD pipe(final String command) {
      return JavaRDDLike.pipe$(this, (String)command);
   }

   public JavaRDD pipe(final List command) {
      return JavaRDDLike.pipe$(this, (List)command);
   }

   public JavaRDD pipe(final List command, final Map env) {
      return JavaRDDLike.pipe$(this, command, env);
   }

   public JavaRDD pipe(final List command, final Map env, final boolean separateWorkingDir, final int bufferSize) {
      return JavaRDDLike.pipe$(this, command, env, separateWorkingDir, bufferSize);
   }

   public JavaRDD pipe(final List command, final Map env, final boolean separateWorkingDir, final int bufferSize, final String encoding) {
      return JavaRDDLike.pipe$(this, command, env, separateWorkingDir, bufferSize, encoding);
   }

   public JavaPairRDD zip(final JavaRDDLike other) {
      return JavaRDDLike.zip$(this, other);
   }

   public JavaRDD zipPartitions(final JavaRDDLike other, final FlatMapFunction2 f) {
      return JavaRDDLike.zipPartitions$(this, other, f);
   }

   public JavaPairRDD zipWithUniqueId() {
      return JavaRDDLike.zipWithUniqueId$(this);
   }

   public JavaPairRDD zipWithIndex() {
      return JavaRDDLike.zipWithIndex$(this);
   }

   public void foreach(final VoidFunction f) {
      JavaRDDLike.foreach$(this, f);
   }

   public List collect() {
      return JavaRDDLike.collect$(this);
   }

   public Iterator toLocalIterator() {
      return JavaRDDLike.toLocalIterator$(this);
   }

   public List[] collectPartitions(final int[] partitionIds) {
      return JavaRDDLike.collectPartitions$(this, partitionIds);
   }

   public Object reduce(final Function2 f) {
      return JavaRDDLike.reduce$(this, f);
   }

   public Object treeReduce(final Function2 f, final int depth) {
      return JavaRDDLike.treeReduce$(this, f, depth);
   }

   public Object treeReduce(final Function2 f) {
      return JavaRDDLike.treeReduce$(this, f);
   }

   public Object fold(final Object zeroValue, final Function2 f) {
      return JavaRDDLike.fold$(this, zeroValue, f);
   }

   public Object aggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp) {
      return JavaRDDLike.aggregate$(this, zeroValue, seqOp, combOp);
   }

   public Object treeAggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp, final int depth) {
      return JavaRDDLike.treeAggregate$(this, zeroValue, seqOp, combOp, depth);
   }

   public Object treeAggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp) {
      return JavaRDDLike.treeAggregate$(this, zeroValue, seqOp, combOp);
   }

   public Object treeAggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp, final int depth, final boolean finalAggregateOnExecutor) {
      return JavaRDDLike.treeAggregate$(this, zeroValue, seqOp, combOp, depth, finalAggregateOnExecutor);
   }

   public long count() {
      return JavaRDDLike.count$(this);
   }

   public PartialResult countApprox(final long timeout, final double confidence) {
      return JavaRDDLike.countApprox$(this, timeout, confidence);
   }

   public PartialResult countApprox(final long timeout) {
      return JavaRDDLike.countApprox$(this, timeout);
   }

   public Map countByValue() {
      return JavaRDDLike.countByValue$(this);
   }

   public PartialResult countByValueApprox(final long timeout, final double confidence) {
      return JavaRDDLike.countByValueApprox$(this, timeout, confidence);
   }

   public PartialResult countByValueApprox(final long timeout) {
      return JavaRDDLike.countByValueApprox$(this, timeout);
   }

   public List take(final int num) {
      return JavaRDDLike.take$(this, num);
   }

   public List takeSample(final boolean withReplacement, final int num) {
      return JavaRDDLike.takeSample$(this, withReplacement, num);
   }

   public List takeSample(final boolean withReplacement, final int num, final long seed) {
      return JavaRDDLike.takeSample$(this, withReplacement, num, seed);
   }

   public Object first() {
      return JavaRDDLike.first$(this);
   }

   public boolean isEmpty() {
      return JavaRDDLike.isEmpty$(this);
   }

   public void saveAsTextFile(final String path) {
      JavaRDDLike.saveAsTextFile$(this, path);
   }

   public void saveAsTextFile(final String path, final Class codec) {
      JavaRDDLike.saveAsTextFile$(this, path, codec);
   }

   public void saveAsObjectFile(final String path) {
      JavaRDDLike.saveAsObjectFile$(this, path);
   }

   public JavaPairRDD keyBy(final Function f) {
      return JavaRDDLike.keyBy$(this, f);
   }

   public void checkpoint() {
      JavaRDDLike.checkpoint$(this);
   }

   public boolean isCheckpointed() {
      return JavaRDDLike.isCheckpointed$(this);
   }

   public Optional getCheckpointFile() {
      return JavaRDDLike.getCheckpointFile$(this);
   }

   public String toDebugString() {
      return JavaRDDLike.toDebugString$(this);
   }

   public List top(final int num, final Comparator comp) {
      return JavaRDDLike.top$(this, num, comp);
   }

   public List top(final int num) {
      return JavaRDDLike.top$(this, num);
   }

   public List takeOrdered(final int num, final Comparator comp) {
      return JavaRDDLike.takeOrdered$(this, num, comp);
   }

   public Object max(final Comparator comp) {
      return JavaRDDLike.max$(this, comp);
   }

   public Object min(final Comparator comp) {
      return JavaRDDLike.min$(this, comp);
   }

   public List takeOrdered(final int num) {
      return JavaRDDLike.takeOrdered$(this, num);
   }

   public long countApproxDistinct(final double relativeSD) {
      return JavaRDDLike.countApproxDistinct$(this, relativeSD);
   }

   public String name() {
      return JavaRDDLike.name$(this);
   }

   public JavaFutureAction countAsync() {
      return JavaRDDLike.countAsync$(this);
   }

   public JavaFutureAction collectAsync() {
      return JavaRDDLike.collectAsync$(this);
   }

   public JavaFutureAction takeAsync(final int num) {
      return JavaRDDLike.takeAsync$(this, num);
   }

   public JavaFutureAction foreachAsync(final VoidFunction f) {
      return JavaRDDLike.foreachAsync$(this, f);
   }

   public JavaFutureAction foreachPartitionAsync(final VoidFunction f) {
      return JavaRDDLike.foreachPartitionAsync$(this, f);
   }

   public AbstractJavaRDDLike() {
      JavaRDDLike.$init$(this);
   }
}
