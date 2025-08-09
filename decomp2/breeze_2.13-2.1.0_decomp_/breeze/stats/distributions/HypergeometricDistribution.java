package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593Aa\u0003\u0007\u0001'!Aa\u0004\u0001B\u0001B\u0003%q\u0004\u0003\u0005#\u0001\t\u0005\t\u0015!\u0003 \u0011!\u0019\u0003A!A!\u0002\u0013y\u0002\"\u0002\u0013\u0001\t\u0003)\u0003b\u0002\u0016\u0001\u0005\u0004%)b\u000b\u0005\u0007s\u0001\u0001\u000bQ\u0002\u0017\b\u000bib\u0001\u0012A\u001e\u0007\u000b-a\u0001\u0012\u0001\u001f\t\u000b\u0011BA\u0011A#\t\u000f\u0019C\u0011\u0011!C\u0005\u000f\nQ\u0002*\u001f9fe\u001e,w.\\3ue&\u001cG)[:ue&\u0014W\u000f^5p]*\u0011QBD\u0001\u000eI&\u001cHO]5ckRLwN\\:\u000b\u0005=\u0001\u0012!B:uCR\u001c(\"A\t\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00111\u0004H\u0007\u0002\u0019%\u0011Q\u0004\u0004\u0002\u001b\u0003B\f7\r[3ESN\u001c'/\u001a;f\t&\u001cHO]5ckRLwN\\\u0001\u000fa>\u0004X\u000f\\1uS>t7+\u001b>f!\t)\u0002%\u0003\u0002\"-\t\u0019\u0011J\u001c;\u0002#9,XNY3s\u001f\u001a\u001cVoY2fgN,7/\u0001\u0006tC6\u0004H.Z*ju\u0016\fa\u0001P5oSRtD\u0003\u0002\u0014(Q%\u0002\"a\u0007\u0001\t\u000by!\u0001\u0019A\u0010\t\u000b\t\"\u0001\u0019A\u0010\t\u000b\r\"\u0001\u0019A\u0010\u0002\u000b%tg.\u001a:\u0016\u00031\u0002\"!\f\u001d\u000e\u00039R!a\f\u0019\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005E\u0012\u0014!B7bi\"\u001c$BA\u001a5\u0003\u001d\u0019w.\\7p]NT!!\u000e\u001c\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0014aA8sO&\u00111BL\u0001\u0007S:tWM\u001d\u0011\u00025!K\b/\u001a:hK>lW\r\u001e:jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u0011\u0005mA1c\u0001\u0005\u0015{A\u0011ahQ\u0007\u0002\u007f)\u0011\u0001)Q\u0001\u0003S>T\u0011AQ\u0001\u0005U\u00064\u0018-\u0003\u0002E\u007f\ta1+\u001a:jC2L'0\u00192mKR\t1(\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001I!\tIE*D\u0001K\u0015\tY\u0015)\u0001\u0003mC:<\u0017BA'K\u0005\u0019y%M[3di\u0002"
)
public class HypergeometricDistribution implements ApacheDiscreteDistribution {
   private final org.apache.commons.math3.distribution.HypergeometricDistribution inner;

   public double probabilityOf(final int x) {
      return ApacheDiscreteDistribution.probabilityOf$(this, x);
   }

   public int draw() {
      return ApacheDiscreteDistribution.draw$(this);
   }

   public int[] drawMany(final int n) {
      return ApacheDiscreteDistribution.drawMany$(this, n);
   }

   public int draw$mcI$sp() {
      return ApacheDiscreteDistribution.draw$mcI$sp$(this);
   }

   public double logProbabilityOf(final Object x) {
      return DiscreteDistr.logProbabilityOf$(this, x);
   }

   public double unnormalizedProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedProbabilityOf$(this, x);
   }

   public double unnormalizedLogProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedLogProbabilityOf$(this, x);
   }

   public double apply(final Object x) {
      return DiscreteDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return DiscreteDistr.logApply$(this, x);
   }

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   public final org.apache.commons.math3.distribution.HypergeometricDistribution inner() {
      return this.inner;
   }

   public HypergeometricDistribution(final int populationSize, final int numberOfSuccesses, final int sampleSize) {
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      ApacheDiscreteDistribution.$init$(this);
      this.inner = new org.apache.commons.math3.distribution.HypergeometricDistribution(populationSize, numberOfSuccesses, sampleSize);
   }
}
