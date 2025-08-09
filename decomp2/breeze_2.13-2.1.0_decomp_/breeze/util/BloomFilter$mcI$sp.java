package breeze.util;

import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import scala.Function1;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public class BloomFilter$mcI$sp extends BloomFilter implements Function1.mcZI.sp {
   private static final long serialVersionUID = 1L;

   public IndexedSeq activeBuckets(final int key) {
      return this.activeBuckets$mcI$sp(key);
   }

   public IndexedSeq activeBuckets$mcI$sp(final int key) {
      int hash1 = key & '\uffff';
      int hash2 = key >> 16;
      return .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), this.numHashFunctions()).map((JFunction1.mcII.sp)(i) -> {
         int h = (hash1 + i * hash2) % this.numBuckets();
         return h < 0 ? ~h : h;
      });
   }

   public boolean apply(final int o) {
      return this.apply$mcI$sp(o);
   }

   public boolean apply$mcI$sp(final int o) {
      return this.activeBuckets$mcI$sp(o).forall((JFunction1.mcZI.sp)(i) -> this.bits().get(i));
   }

   public boolean contains(final int o) {
      return this.contains$mcI$sp(o);
   }

   public boolean contains$mcI$sp(final int o) {
      return this.apply$mcI$sp(o);
   }

   public BloomFilter$mcI$sp $plus$eq(final int o) {
      return this.$plus$eq$mcI$sp(o);
   }

   public BloomFilter$mcI$sp $plus$eq$mcI$sp(final int o) {
      this.activeBuckets$mcI$sp(o).foreach((JFunction1.mcVI.sp)(i) -> this.bits().set(i));
      return this;
   }

   public BloomFilter $amp(final BloomFilter that) {
      return this.$amp$mcI$sp(that);
   }

   public BloomFilter $amp$mcI$sp(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
      return new BloomFilter$mcI$sp(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$amp$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public void checkCompatibility(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
   }

   public void checkCompatibility$mcI$sp(final BloomFilter that) {
      scala.Predef..MODULE$.require(that.numBuckets() == this.numBuckets(), () -> "Must have the same number of buckets to intersect");
      scala.Predef..MODULE$.require(that.numHashFunctions() == this.numHashFunctions(), () -> "Must have the same number of hash functions to intersect");
   }

   public BloomFilter $bar(final BloomFilter that) {
      return this.$bar$mcI$sp(that);
   }

   public BloomFilter $bar$mcI$sp(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
      return new BloomFilter$mcI$sp(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$bar$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public BloomFilter$mcI$sp $bar$eq(final BloomFilter that) {
      return this.$bar$eq$mcI$sp(that);
   }

   public BloomFilter$mcI$sp $bar$eq$mcI$sp(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
      package.AwesomeBitSet$.MODULE$.$bar$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter$mcI$sp $amp$eq(final BloomFilter that) {
      return this.$amp$eq$mcI$sp(that);
   }

   public BloomFilter$mcI$sp $amp$eq$mcI$sp(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
      package.AwesomeBitSet$.MODULE$.$amp$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter$mcI$sp $amp$tilde$eq(final BloomFilter that) {
      return this.$amp$tilde$eq$mcI$sp(that);
   }

   public BloomFilter$mcI$sp $amp$tilde$eq$mcI$sp(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
      package.AwesomeBitSet$.MODULE$.$amp$tilde$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter $amp$tilde(final BloomFilter that) {
      return this.$amp$tilde$mcI$sp(that);
   }

   public BloomFilter $amp$tilde$mcI$sp(final BloomFilter that) {
      this.checkCompatibility$mcI$sp(that);
      return new BloomFilter$mcI$sp(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$amp$tilde$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public BloomFilter$mcI$sp(final int numBuckets, final int numHashFunctions, final BitSet bits) {
      super(numBuckets, numHashFunctions, bits);
   }

   public BloomFilter$mcI$sp(final int numBuckets, final int numHashFunctions) {
      this(numBuckets, numHashFunctions, new BitSet(numBuckets));
   }

   public BloomFilter$mcI$sp(final int numBuckets) {
      this(numBuckets, 3);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
