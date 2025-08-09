package breeze.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import scala.Function1;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ef\u0001\u0002\u0010 \u0001\u0011B\u0001b\u0016\u0001\u0003\u0006\u0004%\t\u0001\u0017\u0005\t9\u0002\u0011\t\u0011)A\u00053\"AQ\f\u0001BC\u0002\u0013\u0005\u0001\f\u0003\u0005_\u0001\t\u0005\t\u0015!\u0003Z\u0011!y\u0006A!b\u0001\n\u0003\u0001\u0007\u0002\u00035\u0001\u0005\u0003\u0005\u000b\u0011B1\t\u000b%\u0004A\u0011\u00016\t\u000b%\u0004A\u0011\u00019\t\u000b%\u0004A\u0011A:\t\u000bU\u0004A\u0011\u0002<\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u00037\u0001A\u0011IA\u000f\u0011\u001d\ty\u0002\u0001C!\u0003CAq!a\n\u0001\t\u0003\tI\u0003C\u0004\u00020\u0001!\t!!\r\t\u000f\u0005]\u0002\u0001\"\u0003\u0002:!9\u00111\t\u0001\u0005\u0002\u0005\u0015\u0003bBA%\u0001\u0011\u0005\u00111\n\u0005\b\u0003\u001f\u0002A\u0011AA)\u0011\u001d\t)\u0006\u0001C\u0001\u0003/Bq!a\u0017\u0001\t\u0003\tifB\u0004\u0002n}A\t!a\u001c\u0007\ryy\u0002\u0012AA9\u0011\u0019I\u0017\u0004\"\u0001\u0002~!9\u0011qP\r\u0005\u0002\u0005\u0005\u0005bBAI3\u0011\u0005\u00111\u0013\u0005\n\u0003CK\u0012\u0011!C\u0005\u0003G\u00131B\u00117p_64\u0015\u000e\u001c;fe*\u0011\u0001%I\u0001\u0005kRLGNC\u0001#\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\u00132'\u0011\u0001a\u0005\f)\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\u00119SfL'\n\u00059B#!\u0003$v]\u000e$\u0018n\u001c82!\t\u0001\u0014\u0007\u0004\u0001\u0005\u0013I\u0002\u0001\u0015!A\u0001\u0006\u0004\u0019$!\u0001+\u0012\u0005Q:\u0004CA\u00146\u0013\t1\u0004FA\u0004O_RD\u0017N\\4\u0011\u0005\u001dB\u0014BA\u001d)\u0005\r\te.\u001f\u0015\u0005cmr\u0004\n\u0005\u0002(y%\u0011Q\b\u000b\u0002\fgB,7-[1mSj,G-M\u0003$\u007f\u0001\u0013\u0015I\u0004\u0002(\u0001&\u0011\u0011\tK\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013D\u000f&r!\u0001R$\u000e\u0003\u0015S!AR\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013'B\u0012J\u00152[eBA\u0014K\u0013\tY\u0005&\u0001\u0003M_:<\u0017\u0007\u0002\u0013D\u000f&\u0002\"a\n(\n\u0005=C#a\u0002\"p_2,\u0017M\u001c\t\u0003#Rs!a\u0011*\n\u0005MC\u0013a\u00029bG.\fw-Z\u0005\u0003+Z\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0015\u0015\u0002\u00159,XNQ;dW\u0016$8/F\u0001Z!\t9#,\u0003\u0002\\Q\t\u0019\u0011J\u001c;\u0002\u00179,XNQ;dW\u0016$8\u000fI\u0001\u0011]Vl\u0007*Y:i\rVt7\r^5p]N\f\u0011C\\;n\u0011\u0006\u001c\bNR;oGRLwN\\:!\u0003\u0011\u0011\u0017\u000e^:\u0016\u0003\u0005\u0004\"A\u00194\u000e\u0003\rT!\u0001\t3\u000b\u0003\u0015\fAA[1wC&\u0011qm\u0019\u0002\u0007\u0005&$8+\u001a;\u0002\u000b\tLGo\u001d\u0011\u0002\rqJg.\u001b;?)\u0011YWN\\8\u0011\u00071\u0004q&D\u0001 \u0011\u00159v\u00011\u0001Z\u0011\u0015iv\u00011\u0001Z\u0011\u0015yv\u00011\u0001b)\rY\u0017O\u001d\u0005\u0006/\"\u0001\r!\u0017\u0005\u0006;\"\u0001\r!\u0017\u000b\u0003WRDQaV\u0005A\u0002e\u000bQ\"Y2uSZ,')^2lKR\u001cHCA<\u0000!\rAX0W\u0007\u0002s*\u0011!p_\u0001\nS6lW\u000f^1cY\u0016T!\u0001 \u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002\u007fs\nQ\u0011J\u001c3fq\u0016$7+Z9\t\r\u0005\u0005!\u00021\u00010\u0003\rYW-_\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u001b\u0006\u001d\u0001BBA\u0005\u0017\u0001\u0007q&A\u0001p\u0003!\u0019wN\u001c;bS:\u001cHcA'\u0002\u0010!1\u0011\u0011\u0002\u0007A\u0002=\nA\u0001\\8bIV\u0011\u0011Q\u0003\t\u0004O\u0005]\u0011bAA\rQ\t1Ai\\;cY\u0016\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00023\u00061Q-];bYN$2!TA\u0012\u0011\u0019\t)c\u0004a\u0001o\u0005)q\u000e\u001e5fe\u0006AA\u0005\u001d7vg\u0012*\u0017\u000f\u0006\u0003\u0002,\u00055R\"\u0001\u0001\t\r\u0005%\u0001\u00031\u00010\u0003\u0011!\u0013-\u001c9\u0015\u0007-\f\u0019\u0004\u0003\u0004\u00026E\u0001\ra[\u0001\u0005i\"\fG/\u0001\ndQ\u0016\u001c7nQ8na\u0006$\u0018NY5mSRLH\u0003BA\u001e\u0003\u0003\u00022aJA\u001f\u0013\r\ty\u0004\u000b\u0002\u0005+:LG\u000f\u0003\u0004\u00026I\u0001\ra[\u0001\u0005I\t\f'\u000fF\u0002l\u0003\u000fBa!!\u000e\u0014\u0001\u0004Y\u0017a\u0002\u0013cCJ$S-\u001d\u000b\u0005\u0003W\ti\u0005\u0003\u0004\u00026Q\u0001\ra[\u0001\bI\u0005l\u0007\u000fJ3r)\u0011\tY#a\u0015\t\r\u0005UR\u00031\u0001l\u00035!\u0013-\u001c9%i&dG-\u001a\u0013fcR!\u00111FA-\u0011\u0019\t)D\u0006a\u0001W\u0006QA%Y7qIQLG\u000eZ3\u0015\u0007-\fy\u0006\u0003\u0004\u00026]\u0001\ra\u001b\u0015\b\u0001\u0005\r\u0014\u0011NA6!\r9\u0013QM\u0005\u0004\u0003OB#\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\t\u0011a\u0003\"m_>lg)\u001b7uKJ\u0004\"\u0001\\\r\u0014\te1\u00131\u000f\t\u0005\u0003k\nY(\u0004\u0002\u0002x)\u0019\u0011\u0011\u00103\u0002\u0005%|\u0017bA+\u0002xQ\u0011\u0011qN\u0001\f_B$\u0018.\\1m'&TX\r\u0006\u0004\u0002\u0004\u0006%\u0015Q\u0012\t\u0006O\u0005\u0015\u0015,W\u0005\u0004\u0003\u000fC#A\u0002+va2,'\u0007C\u0004\u0002\fn\u0001\r!!\u0006\u0002!\u0015D\b/Z2uK\u0012tU/\\%uK6\u001c\bbBAH7\u0001\u0007\u0011QC\u0001\u0012M\u0006d7/\u001a)pg&$\u0018N^3SCR,\u0017AD8qi&l\u0017\r\u001c7z'&TX\rZ\u000b\u0005\u0003+\u000bY\n\u0006\u0004\u0002\u0018\u0006u\u0015q\u0014\t\u0005Y\u0002\tI\nE\u00021\u00037#QA\r\u000fC\u0002MBq!a#\u001d\u0001\u0004\t)\u0002C\u0004\u0002\u0010r\u0001\r!!\u0006\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0006\u0003BAT\u0003[k!!!+\u000b\u0007\u0005-F-\u0001\u0003mC:<\u0017\u0002BAX\u0003S\u0013aa\u00142kK\u000e$\b"
)
public class BloomFilter implements Function1, Serializable {
   private static final long serialVersionUID = 1L;
   private final int numBuckets;
   private final int numHashFunctions;
   private final BitSet bits;

   public static BloomFilter optimallySized(final double expectedNumItems, final double falsePositiveRate) {
      return BloomFilter$.MODULE$.optimallySized(expectedNumItems, falsePositiveRate);
   }

   public static Tuple2 optimalSize(final double expectedNumItems, final double falsePositiveRate) {
      return BloomFilter$.MODULE$.optimalSize(expectedNumItems, falsePositiveRate);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public int numBuckets() {
      return this.numBuckets;
   }

   public int numHashFunctions() {
      return this.numHashFunctions;
   }

   public BitSet bits() {
      return this.bits;
   }

   public IndexedSeq activeBuckets(final Object key) {
      int baseHash = Statics.anyHash(key);
      int hash1 = baseHash & '\uffff';
      int hash2 = baseHash >> 16;
      return .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), this.numHashFunctions()).map((JFunction1.mcII.sp)(i) -> {
         int h = (hash1 + i * hash2) % this.numBuckets();
         return h < 0 ? ~h : h;
      });
   }

   public boolean apply(final Object o) {
      return this.activeBuckets(o).forall((JFunction1.mcZI.sp)(i) -> this.bits().get(i));
   }

   public boolean contains(final Object o) {
      return this.apply(o);
   }

   public double load() {
      return (double)this.bits().cardinality() / (double)this.numBuckets();
   }

   public int hashCode() {
      return this.bits().hashCode();
   }

   public boolean equals(final Object other) {
      boolean var2;
      if (other instanceof BloomFilter) {
         boolean var6;
         label25: {
            label24: {
               BloomFilter var4 = (BloomFilter)other;
               if (this.numBuckets() == var4.numBuckets() && this.numHashFunctions() == var4.numHashFunctions()) {
                  BitSet var10000 = this.bits();
                  BitSet var5 = var4.bits();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label24;
                     }
                  } else if (var10000.equals(var5)) {
                     break label24;
                  }
               }

               var6 = false;
               break label25;
            }

            var6 = true;
         }

         var2 = var6;
      } else {
         var2 = false;
      }

      return var2;
   }

   public BloomFilter $plus$eq(final Object o) {
      this.activeBuckets(o).foreach((JFunction1.mcVI.sp)(i) -> this.bits().set(i));
      return this;
   }

   public BloomFilter $amp(final BloomFilter that) {
      this.checkCompatibility(that);
      return new BloomFilter(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$amp$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public void checkCompatibility(final BloomFilter that) {
      scala.Predef..MODULE$.require(that.numBuckets() == this.numBuckets(), () -> "Must have the same number of buckets to intersect");
      scala.Predef..MODULE$.require(that.numHashFunctions() == this.numHashFunctions(), () -> "Must have the same number of hash functions to intersect");
   }

   public BloomFilter $bar(final BloomFilter that) {
      this.checkCompatibility(that);
      return new BloomFilter(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$bar$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public BloomFilter $bar$eq(final BloomFilter that) {
      this.checkCompatibility(that);
      package.AwesomeBitSet$.MODULE$.$bar$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter $amp$eq(final BloomFilter that) {
      this.checkCompatibility(that);
      package.AwesomeBitSet$.MODULE$.$amp$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter $amp$tilde$eq(final BloomFilter that) {
      this.checkCompatibility(that);
      package.AwesomeBitSet$.MODULE$.$amp$tilde$eq$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits());
      return this;
   }

   public BloomFilter $amp$tilde(final BloomFilter that) {
      this.checkCompatibility(that);
      return new BloomFilter(this.numBuckets(), this.numHashFunctions(), package.AwesomeBitSet$.MODULE$.$amp$tilde$extension(package$.MODULE$.AwesomeBitSet(this.bits()), that.bits()));
   }

   public IndexedSeq activeBuckets$mcI$sp(final int key) {
      return this.activeBuckets(BoxesRunTime.boxToInteger(key));
   }

   public IndexedSeq activeBuckets$mcJ$sp(final long key) {
      return this.activeBuckets(BoxesRunTime.boxToLong(key));
   }

   public boolean apply$mcI$sp(final int o) {
      return this.apply(BoxesRunTime.boxToInteger(o));
   }

   public boolean apply$mcJ$sp(final long o) {
      return this.apply(BoxesRunTime.boxToLong(o));
   }

   public boolean contains$mcI$sp(final int o) {
      return this.contains(BoxesRunTime.boxToInteger(o));
   }

   public boolean contains$mcJ$sp(final long o) {
      return this.contains(BoxesRunTime.boxToLong(o));
   }

   public BloomFilter $plus$eq$mcI$sp(final int o) {
      return this.$plus$eq(BoxesRunTime.boxToInteger(o));
   }

   public BloomFilter $plus$eq$mcJ$sp(final long o) {
      return this.$plus$eq(BoxesRunTime.boxToLong(o));
   }

   public BloomFilter $amp$mcI$sp(final BloomFilter that) {
      return this.$amp(that);
   }

   public BloomFilter $amp$mcJ$sp(final BloomFilter that) {
      return this.$amp(that);
   }

   public void checkCompatibility$mcI$sp(final BloomFilter that) {
      this.checkCompatibility(that);
   }

   public void checkCompatibility$mcJ$sp(final BloomFilter that) {
      this.checkCompatibility(that);
   }

   public BloomFilter $bar$mcI$sp(final BloomFilter that) {
      return this.$bar(that);
   }

   public BloomFilter $bar$mcJ$sp(final BloomFilter that) {
      return this.$bar(that);
   }

   public BloomFilter $bar$eq$mcI$sp(final BloomFilter that) {
      return this.$bar$eq(that);
   }

   public BloomFilter $bar$eq$mcJ$sp(final BloomFilter that) {
      return this.$bar$eq(that);
   }

   public BloomFilter $amp$eq$mcI$sp(final BloomFilter that) {
      return this.$amp$eq(that);
   }

   public BloomFilter $amp$eq$mcJ$sp(final BloomFilter that) {
      return this.$amp$eq(that);
   }

   public BloomFilter $amp$tilde$eq$mcI$sp(final BloomFilter that) {
      return this.$amp$tilde$eq(that);
   }

   public BloomFilter $amp$tilde$eq$mcJ$sp(final BloomFilter that) {
      return this.$amp$tilde$eq(that);
   }

   public BloomFilter $amp$tilde$mcI$sp(final BloomFilter that) {
      return this.$amp$tilde(that);
   }

   public BloomFilter $amp$tilde$mcJ$sp(final BloomFilter that) {
      return this.$amp$tilde(that);
   }

   public BloomFilter(final int numBuckets, final int numHashFunctions, final BitSet bits) {
      this.numBuckets = numBuckets;
      this.numHashFunctions = numHashFunctions;
      this.bits = bits;
      Function1.$init$(this);
   }

   public BloomFilter(final int numBuckets, final int numHashFunctions) {
      this(numBuckets, numHashFunctions, new BitSet(numBuckets));
   }

   public BloomFilter(final int numBuckets) {
      this(numBuckets, 3);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
