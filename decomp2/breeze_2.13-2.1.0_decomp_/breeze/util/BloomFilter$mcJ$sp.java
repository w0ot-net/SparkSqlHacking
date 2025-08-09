package breeze.util;

import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import scala.Function1;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.Statics;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

public class BloomFilter$mcJ$sp extends BloomFilter implements Function1.mcZJ.sp {
   private static final long serialVersionUID = 1L;

   public IndexedSeq activeBuckets(final long key) {
      return this.activeBuckets$mcJ$sp(key);
   }

   public IndexedSeq activeBuckets$mcJ$sp(final long key) {
      int baseHash = Statics.longHash(key);
      int hash1 = baseHash & '\uffff';
      int hash2 = baseHash >> 16;
      return .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), this.numHashFunctions()).map((JFunction1.mcII.sp)(i) -> {
         int h = (hash1 + i * hash2) % this.numBuckets();
         return h < 0 ? ~h : h;
      });
   }

   public boolean apply(final long o) {
      return this.apply$mcJ$sp(o);
   }

   public boolean apply$mcJ$sp(final long o) {
      return this.activeBuckets$mcJ$sp(o).forall((JFunction1.mcZI.sp)(i) -> this.bits().get(i));
   }

   public boolean contains(final long o) {
      return this.contains$mcJ$sp(o);
   }

   public boolean contains$mcJ$sp(final long o) {
      return this.apply$mcJ$sp(o);
   }

   public BloomFilter$mcJ$sp $plus$eq(final long o) {
      return this.$plus$eq$mcJ$sp(o);
   }

   public BloomFilter$mcJ$sp $plus$eq$mcJ$sp(final long o) {
      this.activeBuckets$mcJ$sp(o).foreach((JFunction1.mcVI.sp)(i) -> this.bits().set(i));
      return this;
   }

   public BloomFilter $amp(final BloomFilter that) {
      return this.$amp$mcJ$sp(that);
   }

   public BloomFilter $amp$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
      return new BloomFilter$mcJ$sp(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$amp$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public void checkCompatibility(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
   }

   public void checkCompatibility$mcJ$sp(final BloomFilter that) {
      scala.Predef..MODULE$.require(that.numBuckets() == this.numBuckets(), () -> "Must have the same number of buckets to intersect");
      scala.Predef..MODULE$.require(that.numHashFunctions() == this.numHashFunctions(), () -> "Must have the same number of hash functions to intersect");
   }

   public BloomFilter $bar(final BloomFilter that) {
      return this.$bar$mcJ$sp(that);
   }

   public BloomFilter $bar$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
      return new BloomFilter$mcJ$sp(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$bar$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public BloomFilter$mcJ$sp $bar$eq(final BloomFilter that) {
      return this.$bar$eq$mcJ$sp(that);
   }

   public BloomFilter$mcJ$sp $bar$eq$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
      package.AwesomeBitSet$.MODULE$.$bar$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter$mcJ$sp $amp$eq(final BloomFilter that) {
      return this.$amp$eq$mcJ$sp(that);
   }

   public BloomFilter$mcJ$sp $amp$eq$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
      package.AwesomeBitSet$.MODULE$.$amp$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter$mcJ$sp $amp$tilde$eq(final BloomFilter that) {
      return this.$amp$tilde$eq$mcJ$sp(that);
   }

   public BloomFilter$mcJ$sp $amp$tilde$eq$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
      package.AwesomeBitSet$.MODULE$.$amp$tilde$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter $amp$tilde(final BloomFilter that) {
      return this.$amp$tilde$mcJ$sp(that);
   }

   public BloomFilter $amp$tilde$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility$mcJ$sp(that);
      return new BloomFilter$mcJ$sp(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$amp$tilde$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public BloomFilter$mcJ$sp(final int numBuckets, final int numHashFunctions, final BitSet bits) {
      super(numBuckets, numHashFunctions, bits);
   }

   public BloomFilter$mcJ$sp(final int numBuckets, final int numHashFunctions) {
      this(numBuckets, numHashFunctions, new BitSet(numBuckets));
   }

   public BloomFilter$mcJ$sp(final int numBuckets) {
      this(numBuckets, 3);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
