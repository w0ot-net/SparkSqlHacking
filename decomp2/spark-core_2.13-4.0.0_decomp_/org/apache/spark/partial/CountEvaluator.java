package org.apache.spark.partial;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3Q\u0001E\t\u0001'eA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\t_\u0001\u0011\t\u0011)A\u0005a!)1\u0007\u0001C\u0001i!9\u0001\b\u0001a\u0001\n\u0013I\u0004b\u0002\u001e\u0001\u0001\u0004%Ia\u000f\u0005\u0007\u0003\u0002\u0001\u000b\u0015\u0002\u0017\t\u000f\t\u0003\u0001\u0019!C\u0005\u0007\"9A\t\u0001a\u0001\n\u0013)\u0005BB$\u0001A\u0003&A\u0005C\u0003I\u0001\u0011\u0005\u0013\nC\u0003O\u0001\u0011\u0005sj\u0002\u0004Q#!\u0005\u0011#\u0015\u0004\u0007!EA\t!\u0005*\t\u000bMjA\u0011A*\t\u000bQkA\u0011A+\u0003\u001d\r{WO\u001c;Fm\u0006dW/\u0019;pe*\u0011!cE\u0001\ba\u0006\u0014H/[1m\u0015\t!R#A\u0003ta\u0006\u00148N\u0003\u0002\u0017/\u00051\u0011\r]1dQ\u0016T\u0011\u0001G\u0001\u0004_J<7c\u0001\u0001\u001bAA\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t1\u0011I\\=SK\u001a\u0004B!\t\u0012%O5\t\u0011#\u0003\u0002$#\t!\u0012\t\u001d9s_bLW.\u0019;f\u000bZ\fG.^1u_J\u0004\"aG\u0013\n\u0005\u0019b\"\u0001\u0002'p]\u001e\u0004\"!\t\u0015\n\u0005%\n\"!\u0004\"pk:$W\r\u001a#pk\ndW-\u0001\u0007u_R\fGnT;uaV$8o\u0001\u0001\u0011\u0005mi\u0013B\u0001\u0018\u001d\u0005\rIe\u000e^\u0001\u000bG>tg-\u001b3f]\u000e,\u0007CA\u000e2\u0013\t\u0011DD\u0001\u0004E_V\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007U2t\u0007\u0005\u0002\"\u0001!)!f\u0001a\u0001Y!)qf\u0001a\u0001a\u0005iq.\u001e;qkR\u001cX*\u001a:hK\u0012,\u0012\u0001L\u0001\u0012_V$\b/\u001e;t\u001b\u0016\u0014x-\u001a3`I\u0015\fHC\u0001\u001f@!\tYR(\u0003\u0002?9\t!QK\\5u\u0011\u001d\u0001U!!AA\u00021\n1\u0001\u001f\u00132\u00039yW\u000f\u001e9viNlUM]4fI\u0002\n1a];n+\u0005!\u0013aB:v[~#S-\u001d\u000b\u0003y\u0019Cq\u0001\u0011\u0005\u0002\u0002\u0003\u0007A%\u0001\u0003tk6\u0004\u0013!B7fe\u001e,Gc\u0001\u001fK\u0019\")1J\u0003a\u0001Y\u0005Aq.\u001e;qkRLE\rC\u0003N\u0015\u0001\u0007A%\u0001\u0006uCN\\'+Z:vYR\fQbY;se\u0016tGOU3tk2$H#A\u0014\u0002\u001d\r{WO\u001c;Fm\u0006dW/\u0019;peB\u0011\u0011%D\n\u0003\u001bi!\u0012!U\u0001\u0006E>,h\u000e\u001a\u000b\u0005OY;\u0006\fC\u00030\u001f\u0001\u0007\u0001\u0007C\u0003C\u001f\u0001\u0007A\u0005C\u0003Z\u001f\u0001\u0007\u0001'A\u0001q\u0001"
)
public class CountEvaluator implements ApproximateEvaluator {
   private final int totalOutputs;
   private final double confidence;
   private int outputsMerged;
   private long sum;

   public static BoundedDouble bound(final double confidence, final long sum, final double p) {
      return CountEvaluator$.MODULE$.bound(confidence, sum, p);
   }

   private int outputsMerged() {
      return this.outputsMerged;
   }

   private void outputsMerged_$eq(final int x$1) {
      this.outputsMerged = x$1;
   }

   private long sum() {
      return this.sum;
   }

   private void sum_$eq(final long x$1) {
      this.sum = x$1;
   }

   public void merge(final int outputId, final long taskResult) {
      this.outputsMerged_$eq(this.outputsMerged() + 1);
      this.sum_$eq(this.sum() + taskResult);
   }

   public BoundedDouble currentResult() {
      if (this.outputsMerged() == this.totalOutputs) {
         return new BoundedDouble((double)this.sum(), (double)1.0F, (double)this.sum(), (double)this.sum());
      } else if (this.outputsMerged() != 0 && this.sum() != 0L) {
         double p = (double)this.outputsMerged() / (double)this.totalOutputs;
         return CountEvaluator$.MODULE$.bound(this.confidence, this.sum(), p);
      } else {
         return new BoundedDouble((double)0.0F, (double)0.0F, (double)0.0F, Double.POSITIVE_INFINITY);
      }
   }

   public CountEvaluator(final int totalOutputs, final double confidence) {
      this.totalOutputs = totalOutputs;
      this.confidence = confidence;
      this.outputsMerged = 0;
      this.sum = 0L;
   }
}
