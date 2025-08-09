package org.apache.spark.mllib.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d;a!\u0003\u0006\t\u00029!bA\u0002\f\u000b\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0003\"\u0003\u0011\r!\u0005C\u0003/\u0003\u0011\rq\u0006C\u00037\u0003\u0011\rq\u0007C\u0003?\u0003\u0011\rq\bC\u0003B\u0003\u0011\r!\tC\u0003E\u0003\u0011\rQ)A\bWK\u000e$xN]%na2L7-\u001b;t\u0015\tYA\"\u0001\u0004mS:\fGn\u001a\u0006\u0003\u001b9\tQ!\u001c7mS\nT!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'o\u001a\t\u0003+\u0005i\u0011A\u0003\u0002\u0010-\u0016\u001cGo\u001c:J[Bd\u0017nY5ugN\u0011\u0011\u0001\u0007\t\u00033qi\u0011A\u0007\u0006\u00027\u0005)1oY1mC&\u0011QD\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001F\u0001\u0016[2d\u0017N\u0019,fGR|'\u000fV8N\u0019Z+7\r^8s)\t\u0019#\u0006\u0005\u0002%Q5\tQE\u0003\u0002\fM)\u0011qED\u0001\u0003[2L!!K\u0013\u0003\rY+7\r^8s\u0011\u0015Y3\u00011\u0001-\u0003\u00051\bCA\u000b.\u0013\tI#\"A\u0010nY2L'\rR3og\u00164Vm\u0019;peR{W\n\u0014#f]N,g+Z2u_J$\"\u0001M\u001a\u0011\u0005\u0011\n\u0014B\u0001\u001a&\u0005-!UM\\:f-\u0016\u001cGo\u001c:\t\u000b-\"\u0001\u0019\u0001\u001b\u0011\u0005U)\u0014B\u0001\u001a\u000b\u0003\u0005jG\u000e\\5c'B\f'o]3WK\u000e$xN\u001d+p\u001b2\u001b\u0006/\u0019:tKZ+7\r^8s)\tA4\b\u0005\u0002%s%\u0011!(\n\u0002\r'B\f'o]3WK\u000e$xN\u001d\u0005\u0006W\u0015\u0001\r\u0001\u0010\t\u0003+uJ!A\u000f\u0006\u0002+5dg+Z2u_J$v.\u0014'mS\n4Vm\u0019;peR\u0011A\u0006\u0011\u0005\u0006W\u0019\u0001\raI\u0001 [2$UM\\:f-\u0016\u001cGo\u001c:U_6cE.\u001b2EK:\u001cXMV3di>\u0014HC\u0001\u001bD\u0011\u0015Ys\u00011\u00011\u0003\u0005jGn\u00159beN,g+Z2u_J$v.\u0014'mS\n\u001c\u0006/\u0019:tKZ+7\r^8s)\tad\tC\u0003,\u0011\u0001\u0007\u0001\b"
)
public final class VectorImplicits {
   public static SparseVector mlSparseVectorToMLlibSparseVector(final org.apache.spark.ml.linalg.SparseVector v) {
      return VectorImplicits$.MODULE$.mlSparseVectorToMLlibSparseVector(v);
   }

   public static DenseVector mlDenseVectorToMLlibDenseVector(final org.apache.spark.ml.linalg.DenseVector v) {
      return VectorImplicits$.MODULE$.mlDenseVectorToMLlibDenseVector(v);
   }

   public static Vector mlVectorToMLlibVector(final org.apache.spark.ml.linalg.Vector v) {
      return VectorImplicits$.MODULE$.mlVectorToMLlibVector(v);
   }

   public static org.apache.spark.ml.linalg.SparseVector mllibSparseVectorToMLSparseVector(final SparseVector v) {
      return VectorImplicits$.MODULE$.mllibSparseVectorToMLSparseVector(v);
   }

   public static org.apache.spark.ml.linalg.DenseVector mllibDenseVectorToMLDenseVector(final DenseVector v) {
      return VectorImplicits$.MODULE$.mllibDenseVectorToMLDenseVector(v);
   }

   public static org.apache.spark.ml.linalg.Vector mllibVectorToMLVector(final Vector v) {
      return VectorImplicits$.MODULE$.mllibVectorToMLVector(v);
   }
}
