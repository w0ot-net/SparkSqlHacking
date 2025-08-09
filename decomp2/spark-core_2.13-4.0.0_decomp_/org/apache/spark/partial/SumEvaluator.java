package org.apache.spark.partial;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.spark.util.StatCounter;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3Qa\u0003\u0007\u0001\u001dQA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t[\u0001\u0011\t\u0011)A\u0005]!)\u0011\u0007\u0001C\u0001e!9a\u0007\u0001a\u0001\n\u00139\u0004b\u0002\u001d\u0001\u0001\u0004%I!\u000f\u0005\u0007\u007f\u0001\u0001\u000b\u0015\u0002\u0016\t\u000f\u0001\u0003!\u0019!C\u0005\u0003\"1!\t\u0001Q\u0001\n}AQa\u0011\u0001\u0005B\u0011CQ!\u0013\u0001\u0005B)\u0013AbU;n\u000bZ\fG.^1u_JT!!\u0004\b\u0002\u000fA\f'\u000f^5bY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xmE\u0002\u0001+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007\u0003\u0002\u000f\u001e?\u0015j\u0011\u0001D\u0005\u0003=1\u0011A#\u00119qe>D\u0018.\\1uK\u00163\u0018\r\\;bi>\u0014\bC\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u000f\u0003\u0011)H/\u001b7\n\u0005\u0011\n#aC*uCR\u001cu.\u001e8uKJ\u0004\"\u0001\b\u0014\n\u0005\u001db!!\u0004\"pk:$W\r\u001a#pk\ndW-\u0001\u0007u_R\fGnT;uaV$8o\u0001\u0001\u0011\u0005YY\u0013B\u0001\u0017\u0018\u0005\rIe\u000e^\u0001\u000bG>tg-\u001b3f]\u000e,\u0007C\u0001\f0\u0013\t\u0001tC\u0001\u0004E_V\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007M\"T\u0007\u0005\u0002\u001d\u0001!)\u0001f\u0001a\u0001U!)Qf\u0001a\u0001]\u0005iq.\u001e;qkR\u001cX*\u001a:hK\u0012,\u0012AK\u0001\u0012_V$\b/\u001e;t\u001b\u0016\u0014x-\u001a3`I\u0015\fHC\u0001\u001e>!\t12(\u0003\u0002=/\t!QK\\5u\u0011\u001dqT!!AA\u0002)\n1\u0001\u001f\u00132\u00039yW\u000f\u001e9viNlUM]4fI\u0002\nqaY8v]R,'/F\u0001 \u0003!\u0019w.\u001e8uKJ\u0004\u0013!B7fe\u001e,Gc\u0001\u001eF\u000f\")a)\u0003a\u0001U\u0005Aq.\u001e;qkRLE\rC\u0003I\u0013\u0001\u0007q$\u0001\u0006uCN\\'+Z:vYR\fQbY;se\u0016tGOU3tk2$H#A\u0013"
)
public class SumEvaluator implements ApproximateEvaluator {
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
         return new BoundedDouble(this.counter().sum(), (double)1.0F, this.counter().sum(), this.counter().sum());
      } else if (this.outputsMerged() != 0 && this.counter().count() != 0L) {
         double p = (double)this.outputsMerged() / (double)this.totalOutputs;
         double meanEstimate = this.counter().mean();
         double countEstimate = (double)this.counter().count() * ((double)1 - p) / p;
         double sumEstimate = meanEstimate * countEstimate;
         double meanVar = this.counter().sampleVariance() / (double)this.counter().count();
         if (!Double.isNaN(meanVar) && this.counter().count() != 1L) {
            double countVar = (double)this.counter().count() * ((double)1 - p) / (p * p);
            double sumVar = meanEstimate * meanEstimate * countVar + countEstimate * countEstimate * meanVar + meanVar * countVar;
            double sumStdev = .MODULE$.sqrt(sumVar);
            double var10000;
            if (this.counter().count() > 100L) {
               var10000 = (new NormalDistribution()).inverseCumulativeProbability(((double)1 + this.confidence) / (double)2);
            } else {
               int degreesOfFreedom = (int)(this.counter().count() - 1L);
               var10000 = (new TDistribution((double)degreesOfFreedom)).inverseCumulativeProbability(((double)1 + this.confidence) / (double)2);
            }

            double confFactor = var10000;
            double low = sumEstimate - confFactor * sumStdev;
            double high = sumEstimate + confFactor * sumStdev;
            return new BoundedDouble(this.counter().sum() + sumEstimate, this.confidence, this.counter().sum() + low, this.counter().sum() + high);
         } else {
            return new BoundedDouble(this.counter().sum() + sumEstimate, this.confidence, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
         }
      } else {
         return new BoundedDouble((double)0.0F, (double)0.0F, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
      }
   }

   public SumEvaluator(final int totalOutputs, final double confidence) {
      this.totalOutputs = totalOutputs;
      this.confidence = confidence;
      this.outputsMerged = 0;
      this.counter = new StatCounter();
   }
}
