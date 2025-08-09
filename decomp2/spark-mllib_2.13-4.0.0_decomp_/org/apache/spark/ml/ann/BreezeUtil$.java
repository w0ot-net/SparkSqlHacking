package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;

public final class BreezeUtil$ {
   public static final BreezeUtil$ MODULE$ = new BreezeUtil$();

   private String transposeString(final DenseMatrix A) {
      return A.isTranspose() ? "T" : "N";
   }

   public void dgemm(final double alpha, final DenseMatrix A, final DenseMatrix B, final double beta, final DenseMatrix C) {
      .MODULE$.require(A.cols() == B.rows(), () -> "A & B Dimension mismatch!");
      .MODULE$.require(A.rows() == C.rows(), () -> "A & C Dimension mismatch!");
      .MODULE$.require(B.cols() == C.cols(), () -> "A & C Dimension mismatch!");
      org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dgemm(this.transposeString(A), this.transposeString(B), C.rows(), C.cols(), A.cols(), alpha, A.data$mcD$sp(), A.offset(), A.majorStride(), B.data$mcD$sp(), B.offset(), B.majorStride(), beta, C.data$mcD$sp(), C.offset(), C.rows());
   }

   public void dgemv(final double alpha, final DenseMatrix A, final DenseVector x, final double beta, final DenseVector y) {
      .MODULE$.require(A.cols() == x.length(), () -> "A & x Dimension mismatch!");
      .MODULE$.require(A.rows() == y.length(), () -> "A & y Dimension mismatch!");
      org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dgemv(this.transposeString(A), A.rows(), A.cols(), alpha, A.data$mcD$sp(), A.offset(), A.majorStride(), x.data$mcD$sp(), x.offset(), x.stride(), beta, y.data$mcD$sp(), y.offset(), y.stride());
   }

   private BreezeUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
