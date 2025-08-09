package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils.;
import org.apache.spark.util.random.XORShiftRandom;
import scala.Function1;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class BaggedPoint$ implements Serializable {
   public static final BaggedPoint$ MODULE$ = new BaggedPoint$();

   public double $lessinit$greater$default$3() {
      return (double)1.0F;
   }

   public RDD convertToBaggedRDD(final RDD input, final double subsamplingRate, final int numSubsamples, final boolean withReplacement, final Function1 extractSampleWeight, final long seed) {
      if (withReplacement) {
         return this.convertToBaggedRDDSamplingWithReplacement(input, subsamplingRate, numSubsamples, extractSampleWeight, seed);
      } else {
         return subsamplingRate == (double)1.0F ? this.convertToBaggedRDDWithoutSampling(input, numSubsamples, extractSampleWeight) : this.convertToBaggedRDDSamplingWithoutReplacement(input, subsamplingRate, numSubsamples, extractSampleWeight, seed);
      }
   }

   public Function1 convertToBaggedRDD$default$5() {
      return (x$1) -> BoxesRunTime.boxToDouble($anonfun$convertToBaggedRDD$default$5$1(x$1));
   }

   public long convertToBaggedRDD$default$6() {
      return .MODULE$.random().nextLong();
   }

   private RDD convertToBaggedRDDSamplingWithoutReplacement(final RDD input, final double subsamplingRate, final int numSubsamples, final Function1 extractSampleWeight, final long seed) {
      return input.mapPartitionsWithIndex((partitionIndex, instances) -> $anonfun$convertToBaggedRDDSamplingWithoutReplacement$1(seed, numSubsamples, subsamplingRate, extractSampleWeight, BoxesRunTime.unboxToInt(partitionIndex), instances), input.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(BaggedPoint.class));
   }

   private RDD convertToBaggedRDDSamplingWithReplacement(final RDD input, final double subsample, final int numSubsamples, final Function1 extractSampleWeight, final long seed) {
      return input.mapPartitionsWithIndex((partitionIndex, instances) -> $anonfun$convertToBaggedRDDSamplingWithReplacement$1(subsample, seed, numSubsamples, extractSampleWeight, BoxesRunTime.unboxToInt(partitionIndex), instances), input.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(BaggedPoint.class));
   }

   private RDD convertToBaggedRDDWithoutSampling(final RDD input, final int numSubsamples, final Function1 extractSampleWeight) {
      return input.mapPartitions((instances) -> {
         int[] subsampleCounts = (int[])scala.Array..MODULE$.fill(numSubsamples, (JFunction0.mcI.sp)() -> 1, scala.reflect.ClassTag..MODULE$.Int());
         return instances.map((instance) -> new BaggedPoint(instance, subsampleCounts, BoxesRunTime.unboxToDouble(extractSampleWeight.apply(instance))));
      }, input.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(BaggedPoint.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BaggedPoint$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$convertToBaggedRDD$default$5$1(final Object x$1) {
      return (double)1.0F;
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$convertToBaggedRDDSamplingWithoutReplacement$1(final long seed$1, final int numSubsamples$1, final double subsamplingRate$1, final Function1 extractSampleWeight$1, final int partitionIndex, final Iterator instances) {
      XORShiftRandom rng = new XORShiftRandom();
      rng.setSeed(seed$1 + (long)partitionIndex + 1L);
      return instances.map((instance) -> {
         int[] subsampleCounts = new int[numSubsamples$1];

         for(int subsampleIndex = 0; subsampleIndex < numSubsamples$1; ++subsampleIndex) {
            if (rng.nextDouble() < subsamplingRate$1) {
               subsampleCounts[subsampleIndex] = 1;
            }
         }

         return new BaggedPoint(instance, subsampleCounts, BoxesRunTime.unboxToDouble(extractSampleWeight$1.apply(instance)));
      });
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$convertToBaggedRDDSamplingWithReplacement$1(final double subsample$1, final long seed$2, final int numSubsamples$2, final Function1 extractSampleWeight$2, final int partitionIndex, final Iterator instances) {
      PoissonDistribution poisson = new PoissonDistribution(subsample$1);
      poisson.reseedRandomGenerator(seed$2 + (long)partitionIndex + 1L);
      return instances.map((instance) -> {
         int[] subsampleCounts = new int[numSubsamples$2];

         for(int subsampleIndex = 0; subsampleIndex < numSubsamples$2; ++subsampleIndex) {
            subsampleCounts[subsampleIndex] = poisson.sample();
         }

         return new BaggedPoint(instance, subsampleCounts, BoxesRunTime.unboxToDouble(extractSampleWeight$2.apply(instance)));
      });
   }

   private BaggedPoint$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
