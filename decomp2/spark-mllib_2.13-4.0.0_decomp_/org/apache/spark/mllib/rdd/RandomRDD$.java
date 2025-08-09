package org.apache.spark.mllib.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.random.RandomDataGenerator;
import org.apache.spark.util.Utils.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.util.Random;

public final class RandomRDD$ implements Serializable {
   public static final RandomRDD$ MODULE$ = new RandomRDD$();

   public long $lessinit$greater$default$5() {
      return .MODULE$.random().nextLong();
   }

   public Partition[] getPartitions(final long size, final int numPartitions, final RandomDataGenerator rng, final long seed) {
      RandomRDDPartition[] partitions = new RandomRDDPartition[numPartitions];
      int i = 0;
      long start = 0L;
      long end = 0L;

      for(Random random = new Random(seed); i < numPartitions; ++i) {
         end = (long)(i + 1) * size / (long)numPartitions;
         partitions[i] = new RandomRDDPartition(i, (int)(end - start), rng, random.nextLong());
         start = end;
      }

      return partitions;
   }

   public Iterator getPointIterator(final RandomRDDPartition partition, final ClassTag evidence$2) {
      RandomDataGenerator generator = partition.generator().copy();
      generator.setSeed(partition.seed());
      return scala.package..MODULE$.Iterator().fill(partition.size(), () -> generator.nextValue());
   }

   public Iterator getVectorIterator(final RandomRDDPartition partition, final int vectorSize) {
      RandomDataGenerator generator = partition.generator().copy();
      generator.setSeed(partition.seed());
      return scala.package..MODULE$.Iterator().fill(partition.size(), () -> new DenseVector((double[])scala.Array..MODULE$.fill(vectorSize, (JFunction0.mcD.sp)() -> BoxesRunTime.unboxToDouble(generator.nextValue()), scala.reflect.ClassTag..MODULE$.Double())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomRDD$.class);
   }

   private RandomRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
