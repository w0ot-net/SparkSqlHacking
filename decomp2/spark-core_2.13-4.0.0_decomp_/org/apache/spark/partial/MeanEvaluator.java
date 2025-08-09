package org.apache.spark.partial;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.spark.util.StatCounter;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3Qa\u0003\u0007\u0001\u001dQA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t[\u0001\u0011\t\u0011)A\u0005]!)\u0011\u0007\u0001C\u0001e!9a\u0007\u0001a\u0001\n\u00139\u0004b\u0002\u001d\u0001\u0001\u0004%I!\u000f\u0005\u0007\u007f\u0001\u0001\u000b\u0015\u0002\u0016\t\u000f\u0001\u0003!\u0019!C\u0005\u0003\"1!\t\u0001Q\u0001\n}AQa\u0011\u0001\u0005B\u0011CQ!\u0013\u0001\u0005B)\u0013Q\"T3b]\u00163\u0018\r\\;bi>\u0014(BA\u0007\u000f\u0003\u001d\u0001\u0018M\u001d;jC2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0003\u001d;})S\"\u0001\u0007\n\u0005ya!\u0001F!qaJ|\u00070[7bi\u0016,e/\u00197vCR|'\u000f\u0005\u0002!G5\t\u0011E\u0003\u0002#\u001d\u0005!Q\u000f^5m\u0013\t!\u0013EA\u0006Ti\u0006$8i\\;oi\u0016\u0014\bC\u0001\u000f'\u0013\t9CBA\u0007C_VtG-\u001a3E_V\u0014G.Z\u0001\ri>$\u0018\r\\(viB,Ho]\u0002\u0001!\t12&\u0003\u0002-/\t\u0019\u0011J\u001c;\u0002\u0015\r|gNZ5eK:\u001cW\r\u0005\u0002\u0017_%\u0011\u0001g\u0006\u0002\u0007\t>,(\r\\3\u0002\rqJg.\u001b;?)\r\u0019D'\u000e\t\u00039\u0001AQ\u0001K\u0002A\u0002)BQ!L\u0002A\u00029\nQb\\;uaV$8/T3sO\u0016$W#\u0001\u0016\u0002#=,H\u000f];ug6+'oZ3e?\u0012*\u0017\u000f\u0006\u0002;{A\u0011acO\u0005\u0003y]\u0011A!\u00168ji\"9a(BA\u0001\u0002\u0004Q\u0013a\u0001=%c\u0005qq.\u001e;qkR\u001cX*\u001a:hK\u0012\u0004\u0013aB2pk:$XM]\u000b\u0002?\u0005A1m\\;oi\u0016\u0014\b%A\u0003nKJ<W\rF\u0002;\u000b\u001eCQAR\u0005A\u0002)\n\u0001b\\;uaV$\u0018\n\u001a\u0005\u0006\u0011&\u0001\raH\u0001\u000bi\u0006\u001c8NU3tk2$\u0018!D2veJ,g\u000e\u001e*fgVdG\u000fF\u0001&\u0001"
)
public class MeanEvaluator implements ApproximateEvaluator {
   private final int totalOutputs;
   private final double confidence;
   private int outputsMerged;
   private final StatCounter counter;

   private int outputsMerged() {
      return this.outputsMerged;
   }

   private void outputsMerged_$eq(final int x$1) {
      this.outputsMerged = x$1;
   }

   private StatCounter counter() {
      return this.counter;
   }

   public void merge(final int outputId, final StatCounter taskResult) {
      this.outputsMerged_$eq(this.outputsMerged() + 1);
      this.counter().merge(taskResult);
   }

   public BoundedDouble currentResult() {
      if (this.outputsMerged() == this.totalOutputs) {
         return new BoundedDouble(this.counter().mean(), (double)1.0F, this.counter().mean(), this.counter().mean());
      } else if (this.outputsMerged() != 0 && this.counter().count() != 0L) {
         if (this.counter().count() == 1L) {
            return new BoundedDouble(this.counter().mean(), this.confidence, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
         } else {
            double mean = this.counter().mean();
            double stdev = .MODULE$.sqrt(this.counter().sampleVariance() / (double)this.counter().count());
            double var10000;
            if (this.counter().count() > 100L) {
               var10000 = (new NormalDistribution()).inverseCumulativeProbability(((double)1 + this.confidence) / (double)2);
            } else {
               int degreesOfFreedom = (int)(this.counter().count() - 1L);
               var10000 = (new TDistribution((double)degreesOfFreedom)).inverseCumulativeProbability(((double)1 + this.confidence) / (double)2);
            }

            double confFactor = var10000;
            double low = mean - confFactor * stdev;
            double high = mean + confFactor * stdev;
            return new BoundedDouble(mean, this.confidence, low, high);
         }
      } else {
         return new BoundedDouble((double)0.0F, (double)0.0F, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
      }
   }

   public MeanEvaluator(final int totalOutputs, final double confidence) {
      this.totalOutputs = totalOutputs;
      this.confidence = confidence;
      this.outputsMerged = 0;
      this.counter = new StatCounter();
   }
}
