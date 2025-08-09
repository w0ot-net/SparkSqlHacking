package org.apache.spark.mllib.linalg;

public final class MatrixImplicits$ {
   public static final MatrixImplicits$ MODULE$ = new MatrixImplicits$();

   public org.apache.spark.ml.linalg.Matrix mllibMatrixToMLMatrix(final Matrix m) {
      return m.asML();
   }

   public org.apache.spark.ml.linalg.DenseMatrix mllibDenseMatrixToMLDenseMatrix(final DenseMatrix m) {
      return m.asML();
   }

   public org.apache.spark.ml.linalg.SparseMatrix mllibSparseMatrixToMLSparseMatrix(final SparseMatrix m) {
      return m.asML();
   }

   public Matrix mlMatrixToMLlibMatrix(final org.apache.spark.ml.linalg.Matrix m) {
      return Matrices$.MODULE$.fromML(m);
   }

   public DenseMatrix mlDenseMatrixToMLlibDenseMatrix(final org.apache.spark.ml.linalg.DenseMatrix m) {
      return (DenseMatrix)Matrices$.MODULE$.fromML(m);
   }

   public SparseMatrix mlSparseMatrixToMLlibSparseMatrix(final org.apache.spark.ml.linalg.SparseMatrix m) {
      return (SparseMatrix)Matrices$.MODULE$.fromML(m);
   }

   private MatrixImplicits$() {
   }
}
