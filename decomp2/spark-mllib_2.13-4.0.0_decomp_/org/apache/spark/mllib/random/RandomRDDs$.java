package org.apache.spark.mllib.random;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.rdd.RandomRDD;
import org.apache.spark.mllib.rdd.RandomVectorRDD;
import org.apache.spark.rdd.RDD;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;

public final class RandomRDDs$ {
   public static final RandomRDDs$ MODULE$ = new RandomRDDs$();

   public RDD uniformRDD(final SparkContext sc, final long size, final int numPartitions, final long seed) {
      UniformGenerator uniform = new UniformGenerator();
      return this.randomRDD(sc, uniform, size, this.numPartitionsOrDefault(sc, numPartitions), seed, .MODULE$.Double());
   }

   public int uniformRDD$default$3() {
      return 0;
   }

   public long uniformRDD$default$4() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaDoubleRDD uniformJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions, final long seed) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.uniformRDD(jsc.sc(), size, numPartitions, seed));
   }

   public JavaDoubleRDD uniformJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.uniformRDD(jsc.sc(), size, numPartitions, this.uniformRDD$default$4()));
   }

   public JavaDoubleRDD uniformJavaRDD(final JavaSparkContext jsc, final long size) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.uniformRDD(jsc.sc(), size, this.uniformRDD$default$3(), this.uniformRDD$default$4()));
   }

   public RDD normalRDD(final SparkContext sc, final long size, final int numPartitions, final long seed) {
      StandardNormalGenerator normal = new StandardNormalGenerator();
      return this.randomRDD(sc, normal, size, this.numPartitionsOrDefault(sc, numPartitions), seed, .MODULE$.Double());
   }

   public int normalRDD$default$3() {
      return 0;
   }

   public long normalRDD$default$4() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaDoubleRDD normalJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions, final long seed) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.normalRDD(jsc.sc(), size, numPartitions, seed));
   }

   public JavaDoubleRDD normalJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.normalRDD(jsc.sc(), size, numPartitions, this.normalRDD$default$4()));
   }

   public JavaDoubleRDD normalJavaRDD(final JavaSparkContext jsc, final long size) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.normalRDD(jsc.sc(), size, this.normalRDD$default$3(), this.normalRDD$default$4()));
   }

   public RDD poissonRDD(final SparkContext sc, final double mean, final long size, final int numPartitions, final long seed) {
      PoissonGenerator poisson = new PoissonGenerator(mean);
      return this.randomRDD(sc, poisson, size, this.numPartitionsOrDefault(sc, numPartitions), seed, .MODULE$.Double());
   }

   public int poissonRDD$default$4() {
      return 0;
   }

   public long poissonRDD$default$5() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaDoubleRDD poissonJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions, final long seed) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.poissonRDD(jsc.sc(), mean, size, numPartitions, seed));
   }

   public JavaDoubleRDD poissonJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.poissonRDD(jsc.sc(), mean, size, numPartitions, this.poissonRDD$default$5()));
   }

   public JavaDoubleRDD poissonJavaRDD(final JavaSparkContext jsc, final double mean, final long size) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.poissonRDD(jsc.sc(), mean, size, this.poissonRDD$default$4(), this.poissonRDD$default$5()));
   }

   public RDD exponentialRDD(final SparkContext sc, final double mean, final long size, final int numPartitions, final long seed) {
      ExponentialGenerator exponential = new ExponentialGenerator(mean);
      return this.randomRDD(sc, exponential, size, this.numPartitionsOrDefault(sc, numPartitions), seed, .MODULE$.Double());
   }

   public int exponentialRDD$default$4() {
      return 0;
   }

   public long exponentialRDD$default$5() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaDoubleRDD exponentialJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions, final long seed) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.exponentialRDD(jsc.sc(), mean, size, numPartitions, seed));
   }

   public JavaDoubleRDD exponentialJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.exponentialRDD(jsc.sc(), mean, size, numPartitions, this.exponentialRDD$default$5()));
   }

   public JavaDoubleRDD exponentialJavaRDD(final JavaSparkContext jsc, final double mean, final long size) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.exponentialRDD(jsc.sc(), mean, size, this.exponentialRDD$default$4(), this.exponentialRDD$default$5()));
   }

   public RDD gammaRDD(final SparkContext sc, final double shape, final double scale, final long size, final int numPartitions, final long seed) {
      GammaGenerator gamma = new GammaGenerator(shape, scale);
      return this.randomRDD(sc, gamma, size, this.numPartitionsOrDefault(sc, numPartitions), seed, .MODULE$.Double());
   }

   public int gammaRDD$default$5() {
      return 0;
   }

   public long gammaRDD$default$6() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaDoubleRDD gammaJavaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size, final int numPartitions, final long seed) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.gammaRDD(jsc.sc(), shape, scale, size, numPartitions, seed));
   }

   public JavaDoubleRDD gammaJavaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size, final int numPartitions) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.gammaRDD(jsc.sc(), shape, scale, size, numPartitions, this.gammaRDD$default$6()));
   }

   public JavaDoubleRDD gammaJavaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.gammaRDD(jsc.sc(), shape, scale, size, this.gammaRDD$default$5(), this.gammaRDD$default$6()));
   }

   public RDD logNormalRDD(final SparkContext sc, final double mean, final double std, final long size, final int numPartitions, final long seed) {
      LogNormalGenerator logNormal = new LogNormalGenerator(mean, std);
      return this.randomRDD(sc, logNormal, size, this.numPartitionsOrDefault(sc, numPartitions), seed, .MODULE$.Double());
   }

   public int logNormalRDD$default$5() {
      return 0;
   }

   public long logNormalRDD$default$6() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaDoubleRDD logNormalJavaRDD(final JavaSparkContext jsc, final double mean, final double std, final long size, final int numPartitions, final long seed) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.logNormalRDD(jsc.sc(), mean, std, size, numPartitions, seed));
   }

   public JavaDoubleRDD logNormalJavaRDD(final JavaSparkContext jsc, final double mean, final double std, final long size, final int numPartitions) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.logNormalRDD(jsc.sc(), mean, std, size, numPartitions, this.logNormalRDD$default$6()));
   }

   public JavaDoubleRDD logNormalJavaRDD(final JavaSparkContext jsc, final double mean, final double std, final long size) {
      return org.apache.spark.api.java.JavaDoubleRDD..MODULE$.fromRDD(this.logNormalRDD(jsc.sc(), mean, std, size, this.logNormalRDD$default$5(), this.logNormalRDD$default$6()));
   }

   public RDD randomRDD(final SparkContext sc, final RandomDataGenerator generator, final long size, final int numPartitions, final long seed, final ClassTag evidence$1) {
      return new RandomRDD(sc, size, this.numPartitionsOrDefault(sc, numPartitions), generator, seed, evidence$1);
   }

   public int randomRDD$default$4() {
      return 0;
   }

   public long randomRDD$default$5() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD randomJavaRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long size, final int numPartitions, final long seed) {
      ClassTag ctag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      RDD rdd = this.randomRDD(jsc.sc(), generator, size, numPartitions, seed, ctag);
      return org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(rdd, ctag);
   }

   public JavaRDD randomJavaRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long size, final int numPartitions) {
      return this.randomJavaRDD(jsc, generator, size, numPartitions, org.apache.spark.util.Utils..MODULE$.random().nextLong());
   }

   public JavaRDD randomJavaRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long size) {
      return this.randomJavaRDD(jsc, generator, size, 0);
   }

   public RDD uniformVectorRDD(final SparkContext sc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      UniformGenerator uniform = new UniformGenerator();
      return this.randomVectorRDD(sc, uniform, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), seed);
   }

   public int uniformVectorRDD$default$4() {
      return 0;
   }

   public long uniformVectorRDD$default$5() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD uniformJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.uniformVectorRDD(jsc.sc(), numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD uniformJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions) {
      return this.uniformVectorRDD(jsc.sc(), numRows, numCols, numPartitions, this.uniformVectorRDD$default$5()).toJavaRDD();
   }

   public JavaRDD uniformJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols) {
      return this.uniformVectorRDD(jsc.sc(), numRows, numCols, this.uniformVectorRDD$default$4(), this.uniformVectorRDD$default$5()).toJavaRDD();
   }

   public RDD normalVectorRDD(final SparkContext sc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      StandardNormalGenerator normal = new StandardNormalGenerator();
      return this.randomVectorRDD(sc, normal, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), seed);
   }

   public int normalVectorRDD$default$4() {
      return 0;
   }

   public long normalVectorRDD$default$5() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD normalJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.normalVectorRDD(jsc.sc(), numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD normalJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions) {
      return this.normalVectorRDD(jsc.sc(), numRows, numCols, numPartitions, this.normalVectorRDD$default$5()).toJavaRDD();
   }

   public JavaRDD normalJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols) {
      return this.normalVectorRDD(jsc.sc(), numRows, numCols, this.normalVectorRDD$default$4(), this.normalVectorRDD$default$5()).toJavaRDD();
   }

   public RDD logNormalVectorRDD(final SparkContext sc, final double mean, final double std, final long numRows, final int numCols, final int numPartitions, final long seed) {
      LogNormalGenerator logNormal = new LogNormalGenerator(mean, std);
      return this.randomVectorRDD(sc, logNormal, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), seed);
   }

   public int logNormalVectorRDD$default$6() {
      return 0;
   }

   public long logNormalVectorRDD$default$7() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD logNormalJavaVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.logNormalVectorRDD(jsc.sc(), mean, std, numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD logNormalJavaVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols, final int numPartitions) {
      return this.logNormalVectorRDD(jsc.sc(), mean, std, numRows, numCols, numPartitions, this.logNormalVectorRDD$default$7()).toJavaRDD();
   }

   public JavaRDD logNormalJavaVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols) {
      return this.logNormalVectorRDD(jsc.sc(), mean, std, numRows, numCols, this.logNormalVectorRDD$default$6(), this.logNormalVectorRDD$default$7()).toJavaRDD();
   }

   public RDD poissonVectorRDD(final SparkContext sc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      PoissonGenerator poisson = new PoissonGenerator(mean);
      return this.randomVectorRDD(sc, poisson, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), seed);
   }

   public int poissonVectorRDD$default$5() {
      return 0;
   }

   public long poissonVectorRDD$default$6() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD poissonJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.poissonVectorRDD(jsc.sc(), mean, numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD poissonJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions) {
      return this.poissonVectorRDD(jsc.sc(), mean, numRows, numCols, numPartitions, this.poissonVectorRDD$default$6()).toJavaRDD();
   }

   public JavaRDD poissonJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols) {
      return this.poissonVectorRDD(jsc.sc(), mean, numRows, numCols, this.poissonVectorRDD$default$5(), this.poissonVectorRDD$default$6()).toJavaRDD();
   }

   public RDD exponentialVectorRDD(final SparkContext sc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      ExponentialGenerator exponential = new ExponentialGenerator(mean);
      return this.randomVectorRDD(sc, exponential, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), seed);
   }

   public int exponentialVectorRDD$default$5() {
      return 0;
   }

   public long exponentialVectorRDD$default$6() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD exponentialJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.exponentialVectorRDD(jsc.sc(), mean, numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD exponentialJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions) {
      return this.exponentialVectorRDD(jsc.sc(), mean, numRows, numCols, numPartitions, this.exponentialVectorRDD$default$6()).toJavaRDD();
   }

   public JavaRDD exponentialJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols) {
      return this.exponentialVectorRDD(jsc.sc(), mean, numRows, numCols, this.exponentialVectorRDD$default$5(), this.exponentialVectorRDD$default$6()).toJavaRDD();
   }

   public RDD gammaVectorRDD(final SparkContext sc, final double shape, final double scale, final long numRows, final int numCols, final int numPartitions, final long seed) {
      GammaGenerator gamma = new GammaGenerator(shape, scale);
      return this.randomVectorRDD(sc, gamma, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), seed);
   }

   public int gammaVectorRDD$default$6() {
      return 0;
   }

   public long gammaVectorRDD$default$7() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD gammaJavaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.gammaVectorRDD(jsc.sc(), shape, scale, numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD gammaJavaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols, final int numPartitions) {
      return this.gammaVectorRDD(jsc.sc(), shape, scale, numRows, numCols, numPartitions, this.gammaVectorRDD$default$7()).toJavaRDD();
   }

   public JavaRDD gammaJavaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols) {
      return this.gammaVectorRDD(jsc.sc(), shape, scale, numRows, numCols, this.gammaVectorRDD$default$6(), this.gammaVectorRDD$default$7()).toJavaRDD();
   }

   public RDD randomVectorRDD(final SparkContext sc, final RandomDataGenerator generator, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return new RandomVectorRDD(sc, numRows, numCols, this.numPartitionsOrDefault(sc, numPartitions), generator, seed);
   }

   public int randomVectorRDD$default$5() {
      return 0;
   }

   public long randomVectorRDD$default$6() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public JavaRDD randomJavaVectorRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return this.randomVectorRDD(jsc.sc(), generator, numRows, numCols, numPartitions, seed).toJavaRDD();
   }

   public JavaRDD randomJavaVectorRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long numRows, final int numCols, final int numPartitions) {
      return this.randomVectorRDD(jsc.sc(), generator, numRows, numCols, numPartitions, this.randomVectorRDD$default$6()).toJavaRDD();
   }

   public JavaRDD randomJavaVectorRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long numRows, final int numCols) {
      return this.randomVectorRDD(jsc.sc(), generator, numRows, numCols, this.randomVectorRDD$default$5(), this.randomVectorRDD$default$6()).toJavaRDD();
   }

   private int numPartitionsOrDefault(final SparkContext sc, final int numPartitions) {
      return numPartitions > 0 ? numPartitions : sc.defaultMinPartitions();
   }

   private RandomRDDs$() {
   }
}
