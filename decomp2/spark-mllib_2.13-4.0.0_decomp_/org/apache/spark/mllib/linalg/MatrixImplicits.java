package org.apache.spark.mllib.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d;a!\u0003\u0006\t\u00029!bA\u0002\f\u000b\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0003\"\u0003\u0011\r!\u0005C\u0003/\u0003\u0011\rq\u0006C\u00037\u0003\u0011\rq\u0007C\u0003?\u0003\u0011\rq\bC\u0003B\u0003\u0011\r!\tC\u0003E\u0003\u0011\rQ)A\bNCR\u0014\u0018\u000e_%na2L7-\u001b;t\u0015\tYA\"\u0001\u0004mS:\fGn\u001a\u0006\u0003\u001b9\tQ!\u001c7mS\nT!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'o\u001a\t\u0003+\u0005i\u0011A\u0003\u0002\u0010\u001b\u0006$(/\u001b=J[Bd\u0017nY5ugN\u0011\u0011\u0001\u0007\t\u00033qi\u0011A\u0007\u0006\u00027\u0005)1oY1mC&\u0011QD\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001F\u0001\u0016[2d\u0017NY'biJL\u0007\u0010V8N\u00196\u000bGO]5y)\t\u0019#\u0006\u0005\u0002%Q5\tQE\u0003\u0002\fM)\u0011qED\u0001\u0003[2L!!K\u0013\u0003\r5\u000bGO]5y\u0011\u0015Y3\u00011\u0001-\u0003\u0005i\u0007CA\u000b.\u0013\tI#\"A\u0010nY2L'\rR3og\u0016l\u0015\r\u001e:jqR{W\n\u0014#f]N,W*\u0019;sSb$\"\u0001M\u001a\u0011\u0005\u0011\n\u0014B\u0001\u001a&\u0005-!UM\\:f\u001b\u0006$(/\u001b=\t\u000b-\"\u0001\u0019\u0001\u001b\u0011\u0005U)\u0014B\u0001\u001a\u000b\u0003\u0005jG\u000e\\5c'B\f'o]3NCR\u0014\u0018\u000e\u001f+p\u001b2\u001b\u0006/\u0019:tK6\u000bGO]5y)\tA4\b\u0005\u0002%s%\u0011!(\n\u0002\r'B\f'o]3NCR\u0014\u0018\u000e\u001f\u0005\u0006W\u0015\u0001\r\u0001\u0010\t\u0003+uJ!A\u000f\u0006\u0002+5dW*\u0019;sSb$v.\u0014'mS\nl\u0015\r\u001e:jqR\u0011A\u0006\u0011\u0005\u0006W\u0019\u0001\raI\u0001 [2$UM\\:f\u001b\u0006$(/\u001b=U_6cE.\u001b2EK:\u001cX-T1ue&DHC\u0001\u001bD\u0011\u0015Ys\u00011\u00011\u0003\u0005jGn\u00159beN,W*\u0019;sSb$v.\u0014'mS\n\u001c\u0006/\u0019:tK6\u000bGO]5y)\tad\tC\u0003,\u0011\u0001\u0007\u0001\b"
)
public final class MatrixImplicits {
   public static SparseMatrix mlSparseMatrixToMLlibSparseMatrix(final org.apache.spark.ml.linalg.SparseMatrix m) {
      return MatrixImplicits$.MODULE$.mlSparseMatrixToMLlibSparseMatrix(m);
   }

   public static DenseMatrix mlDenseMatrixToMLlibDenseMatrix(final org.apache.spark.ml.linalg.DenseMatrix m) {
      return MatrixImplicits$.MODULE$.mlDenseMatrixToMLlibDenseMatrix(m);
   }

   public static Matrix mlMatrixToMLlibMatrix(final org.apache.spark.ml.linalg.Matrix m) {
      return MatrixImplicits$.MODULE$.mlMatrixToMLlibMatrix(m);
   }

   public static org.apache.spark.ml.linalg.SparseMatrix mllibSparseMatrixToMLSparseMatrix(final SparseMatrix m) {
      return MatrixImplicits$.MODULE$.mllibSparseMatrixToMLSparseMatrix(m);
   }

   public static org.apache.spark.ml.linalg.DenseMatrix mllibDenseMatrixToMLDenseMatrix(final DenseMatrix m) {
      return MatrixImplicits$.MODULE$.mllibDenseMatrixToMLDenseMatrix(m);
   }

   public static org.apache.spark.ml.linalg.Matrix mllibMatrixToMLMatrix(final Matrix m) {
      return MatrixImplicits$.MODULE$.mllibMatrixToMLMatrix(m);
   }
}
