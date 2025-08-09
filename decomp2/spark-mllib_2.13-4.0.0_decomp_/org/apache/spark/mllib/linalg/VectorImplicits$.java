package org.apache.spark.mllib.linalg;

public final class VectorImplicits$ {
   public static final VectorImplicits$ MODULE$ = new VectorImplicits$();

   public org.apache.spark.ml.linalg.Vector mllibVectorToMLVector(final Vector v) {
      return v.asML();
   }

   public org.apache.spark.ml.linalg.DenseVector mllibDenseVectorToMLDenseVector(final DenseVector v) {
      return v.asML();
   }

   public org.apache.spark.ml.linalg.SparseVector mllibSparseVectorToMLSparseVector(final SparseVector v) {
      return v.asML();
   }

   public Vector mlVectorToMLlibVector(final org.apache.spark.ml.linalg.Vector v) {
      return Vectors$.MODULE$.fromML(v);
   }

   public DenseVector mlDenseVectorToMLlibDenseVector(final org.apache.spark.ml.linalg.DenseVector v) {
      return (DenseVector)Vectors$.MODULE$.fromML(v);
   }

   public SparseVector mlSparseVectorToMLlibSparseVector(final org.apache.spark.ml.linalg.SparseVector v) {
      return (SparseVector)Vectors$.MODULE$.fromML(v);
   }

   private VectorImplicits$() {
   }
}
