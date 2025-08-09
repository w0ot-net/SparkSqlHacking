package org.apache.spark.mllib.random;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0003\u0006\u0001+!A1\u0005\u0001BC\u0002\u0013\u0005A\u0005\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003!\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u001d1\u0004A1A\u0005\n]BaA\u0011\u0001!\u0002\u0013A\u0004\"B\"\u0001\t\u0003\"\u0005\"\u0002$\u0001\t\u0003:\u0005\"B)\u0001\t\u0003\u0012&\u0001F#ya>tWM\u001c;jC2<UM\\3sCR|'O\u0003\u0002\f\u0019\u00051!/\u00198e_6T!!\u0004\b\u0002\u000b5dG.\u001b2\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007cA\u000f\u001fA5\t!\"\u0003\u0002 \u0015\t\u0019\"+\u00198e_6$\u0015\r^1HK:,'/\u0019;peB\u0011q#I\u0005\u0003Ea\u0011a\u0001R8vE2,\u0017\u0001B7fC:,\u0012\u0001\t\u0015\u0004\u0003\u0019b\u0003CA\u0014+\u001b\u0005A#BA\u0015\u000f\u0003)\tgN\\8uCRLwN\\\u0005\u0003W!\u0012QaU5oG\u0016\f\u0013!L\u0001\u0006c9\u001ad\u0006M\u0001\u0006[\u0016\fg\u000e\t\u0015\u0004\u0005\u0019b\u0013A\u0002\u001fj]&$h\b\u0006\u00023gA\u0011Q\u0004\u0001\u0005\u0006G\r\u0001\r\u0001\t\u0015\u0004g\u0019b\u0003fA\u0002'Y\u0005\u0019!O\\4\u0016\u0003a\u0002\"!\u000f!\u000e\u0003iR!a\u000f\u001f\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005ur\u0014!B7bi\"\u001c$BA \u0011\u0003\u001d\u0019w.\\7p]NL!!\u0011\u001e\u0003/\u0015C\bo\u001c8f]RL\u0017\r\u001c#jgR\u0014\u0018NY;uS>t\u0017\u0001\u0002:oO\u0002\n\u0011B\\3yiZ\u000bG.^3\u0015\u0003\u0001B3A\u0002\u0014-\u0003\u001d\u0019X\r^*fK\u0012$\"\u0001S&\u0011\u0005]I\u0015B\u0001&\u0019\u0005\u0011)f.\u001b;\t\u000b1;\u0001\u0019A'\u0002\tM,W\r\u001a\t\u0003/9K!a\u0014\r\u0003\t1{gn\u001a\u0015\u0004\u000f\u0019b\u0013\u0001B2paf$\u0012A\r\u0015\u0004\u0011\u0019b\u0003f\u0001\u0001'Y\u0001"
)
public class ExponentialGenerator implements RandomDataGenerator {
   private final double mean;
   private final ExponentialDistribution rng;

   public double mean() {
      return this.mean;
   }

   private ExponentialDistribution rng() {
      return this.rng;
   }

   public double nextValue() {
      return this.rng().sample();
   }

   public void setSeed(final long seed) {
      this.rng().reseedRandomGenerator(seed);
   }

   public ExponentialGenerator copy() {
      return new ExponentialGenerator(this.mean());
   }

   public ExponentialGenerator(final double mean) {
      this.mean = mean;
      this.rng = new ExponentialDistribution(mean);
   }
}
