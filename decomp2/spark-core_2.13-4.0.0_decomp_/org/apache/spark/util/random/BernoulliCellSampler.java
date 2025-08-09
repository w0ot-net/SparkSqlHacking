package org.apache.spark.util.random;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.annotation.DeveloperApi;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a\u0001\u0002\t\u0012\u0001qA\u0001b\r\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\to\u0001\u0011\t\u0011)A\u0005i!A\u0001\b\u0001B\u0001B\u0003%\u0011\bC\u0003=\u0001\u0011\u0005Q\bC\u0004C\u0001\t\u0007I\u0011B\"\t\r-\u0003\u0001\u0015!\u0003E\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u00151\u0006\u0001\"\u0011X\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u0015i\u0006\u0001\"\u0011]\u000f\u001d)\u0017#!A\t\u0002\u00194q\u0001E\t\u0002\u0002#\u0005q\rC\u0003=\u0019\u0011\u0005a\u000eC\u0004p\u0019E\u0005I\u0011\u00019\t\u000fqd\u0011\u0011!C\u0005{\n!\")\u001a:o_VdG.[\"fY2\u001c\u0016-\u001c9mKJT!AE\n\u0002\rI\fg\u000eZ8n\u0015\t!R#\u0001\u0003vi&d'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0004\u0001U\u0011QDK\n\u0004\u0001y!\u0003CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\r\u0005\u0003&M!BS\"A\t\n\u0005\u001d\n\"!\u0004*b]\u0012|WnU1na2,'\u000f\u0005\u0002*U1\u0001A!B\u0016\u0001\u0005\u0004a#!\u0001+\u0012\u00055\u0002\u0004CA\u0010/\u0013\ty\u0003EA\u0004O_RD\u0017N\\4\u0011\u0005}\t\u0014B\u0001\u001a!\u0005\r\te._\u0001\u0003Y\n\u0004\"aH\u001b\n\u0005Y\u0002#A\u0002#pk\ndW-\u0001\u0002vE\u0006Q1m\\7qY\u0016lWM\u001c;\u0011\u0005}Q\u0014BA\u001e!\u0005\u001d\u0011un\u001c7fC:\fa\u0001P5oSRtD\u0003\u0002 @\u0001\u0006\u00032!\n\u0001)\u0011\u0015\u0019D\u00011\u00015\u0011\u00159D\u00011\u00015\u0011\u001dAD\u0001%AA\u0002e\n1A\u001d8h+\u0005!\u0005CA#J\u001b\u00051%B\u0001\u000bH\u0015\u0005A\u0015\u0001\u00026bm\u0006L!A\u0013$\u0003\rI\u000bg\u000eZ8n\u0003\u0011\u0011hn\u001a\u0011\u0002\u000fM,GoU3fIR\u0011a*\u0015\t\u0003?=K!\u0001\u0015\u0011\u0003\tUs\u0017\u000e\u001e\u0005\u0006%\u001e\u0001\raU\u0001\u0005g\u0016,G\r\u0005\u0002 )&\u0011Q\u000b\t\u0002\u0005\u0019>tw-\u0001\u0004tC6\u0004H.\u001a\u000b\u00021B\u0011q$W\u0005\u00035\u0002\u00121!\u00138u\u0003=\u0019Gn\u001c8f\u0007>l\u0007\u000f\\3nK:$H#\u0001 \u0002\u000b\rdwN\\3)\u0005\u0001y\u0006C\u00011d\u001b\u0005\t'B\u00012\u0016\u0003)\tgN\\8uCRLwN\\\u0005\u0003I\u0006\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\fACQ3s]>,H\u000e\\5DK2d7+Y7qY\u0016\u0014\bCA\u0013\r'\raa\u0004\u001b\t\u0003S2l\u0011A\u001b\u0006\u0003W\u001e\u000b!![8\n\u00055T'\u0001D*fe&\fG.\u001b>bE2,G#\u00014\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t\t80F\u0001sU\tI4oK\u0001u!\t)\u00180D\u0001w\u0015\t9\b0A\u0005v]\u000eDWmY6fI*\u0011!\rI\u0005\u0003uZ\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015YcB1\u0001-\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005q\bcA@\u0002\u00065\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u00079\u0015\u0001\u00027b]\u001eLA!a\u0002\u0002\u0002\t1qJ\u00196fGR\u0004"
)
public class BernoulliCellSampler implements RandomSampler {
   private final double lb;
   private final double ub;
   private final boolean complement;
   private final Random rng;

   public static boolean $lessinit$greater$default$3() {
      return BernoulliCellSampler$.MODULE$.$lessinit$greater$default$3();
   }

   public Iterator sample(final Iterator items) {
      return RandomSampler.sample$(this, items);
   }

   private Random rng() {
      return this.rng;
   }

   public void setSeed(final long seed) {
      this.rng().setSeed(seed);
   }

   public int sample() {
      if (this.ub - this.lb <= (double)0.0F) {
         return this.complement ? 1 : 0;
      } else {
         double x = this.rng().nextDouble();
         int n = x >= this.lb && x < this.ub ? 1 : 0;
         return this.complement ? 1 - n : n;
      }
   }

   public BernoulliCellSampler cloneComplement() {
      return new BernoulliCellSampler(this.lb, this.ub, !this.complement);
   }

   public BernoulliCellSampler clone() {
      return new BernoulliCellSampler(this.lb, this.ub, this.complement);
   }

   public BernoulliCellSampler(final double lb, final double ub, final boolean complement) {
      this.lb = lb;
      this.ub = ub;
      this.complement = complement;
      RandomSampler.$init$(this);
      .MODULE$.require(lb <= ub + RandomSampler$.MODULE$.roundingEpsilon(), () -> "Lower bound (" + this.lb + ") must be <= upper bound (" + this.ub + ")");
      .MODULE$.require(lb >= (double)0.0F - RandomSampler$.MODULE$.roundingEpsilon(), () -> "Lower bound (" + this.lb + ") must be >= 0.0");
      .MODULE$.require(ub <= (double)1.0F + RandomSampler$.MODULE$.roundingEpsilon(), () -> "Upper bound (" + this.ub + ") must be <= 1.0");
      this.rng = new XORShiftRandom();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
