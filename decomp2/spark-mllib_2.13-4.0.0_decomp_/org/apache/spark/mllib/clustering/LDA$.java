package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector.;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public final class LDA$ {
   public static final LDA$ MODULE$ = new LDA$();

   public long term2index(final int term) {
      return -(1L + (long)term);
   }

   public int index2term(final long termIndex) {
      return -((int)(1L + termIndex));
   }

   public boolean isDocumentVertex(final Tuple2 v) {
      return v._1$mcJ$sp() >= 0L;
   }

   public boolean isTermVertex(final Tuple2 v) {
      return v._1$mcJ$sp() < 0L;
   }

   public DenseVector computePTopic(final DenseVector docTopicCounts, final DenseVector termTopicCounts, final DenseVector totalTopicCounts, final int vocabSize, final double eta, final double alpha) {
      int K = docTopicCounts.length();
      double[] N_j = docTopicCounts.data$mcD$sp();
      double[] N_w = termTopicCounts.data$mcD$sp();
      double[] N = totalTopicCounts.data$mcD$sp();
      double eta1 = eta - (double)1.0F;
      double alpha1 = alpha - (double)1.0F;
      double Weta1 = (double)vocabSize * eta1;
      double sum = (double)0.0F;
      double[] gamma_wj = new double[K];

      for(int k = 0; k < K; ++k) {
         double gamma_wjk = (N_w[k] + eta1) * (N_j[k] + alpha1) / (N[k] + Weta1);
         gamma_wj[k] = gamma_wjk;
         sum += gamma_wjk;
      }

      return (DenseVector).MODULE$.apply$mDc$sp(gamma_wj).$div$eq(BoxesRunTime.boxToDouble(sum), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_S_Double_OpDiv());
   }

   private LDA$() {
   }
}
