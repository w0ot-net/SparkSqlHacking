package org.apache.spark.mllib.recommendation;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.runtime.ModuleSerializationProxy;

public final class ALS$ implements Serializable {
   public static final ALS$ MODULE$ = new ALS$();

   public long org$apache$spark$mllib$recommendation$ALS$$$lessinit$greater$default$8() {
      return System.nanoTime();
   }

   public MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks, final long seed) {
      return (new ALS(blocks, blocks, rank, iterations, lambda, false, (double)1.0F, seed)).run(ratings);
   }

   public MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks) {
      return (new ALS(blocks, blocks, rank, iterations, lambda, false, (double)1.0F, this.org$apache$spark$mllib$recommendation$ALS$$$lessinit$greater$default$8())).run(ratings);
   }

   public MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations, final double lambda) {
      return this.train(ratings, rank, iterations, lambda, -1);
   }

   public MatrixFactorizationModel train(final RDD ratings, final int rank, final int iterations) {
      return this.train(ratings, rank, iterations, 0.01, -1);
   }

   public MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks, final double alpha, final long seed) {
      return (new ALS(blocks, blocks, rank, iterations, lambda, true, alpha, seed)).run(ratings);
   }

   public MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations, final double lambda, final int blocks, final double alpha) {
      return (new ALS(blocks, blocks, rank, iterations, lambda, true, alpha, this.org$apache$spark$mllib$recommendation$ALS$$$lessinit$greater$default$8())).run(ratings);
   }

   public MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations, final double lambda, final double alpha) {
      return this.trainImplicit(ratings, rank, iterations, lambda, -1, alpha);
   }

   public MatrixFactorizationModel trainImplicit(final RDD ratings, final int rank, final int iterations) {
      return this.trainImplicit(ratings, rank, iterations, 0.01, -1, (double)1.0F);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ALS$.class);
   }

   private ALS$() {
   }
}
