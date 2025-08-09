package org.apache.spark.ml.linalg;

import breeze.linalg.CSCMatrix;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.MatchError;
import scala.Tuple3;
import scala.Array.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

public final class Matrices$ {
   public static final Matrices$ MODULE$ = new Matrices$();
   private static final DenseMatrix empty;

   static {
      empty = new DenseMatrix(0, 0, .MODULE$.emptyDoubleArray());
   }

   public DenseMatrix empty() {
      return empty;
   }

   public Matrix fromVectors(final Seq vectors) {
      int numRows = vectors.length();
      int numCols = ((Vector)vectors.head()).size();
      long denseSize = this.getDenseSize((long)numCols, (long)numRows);
      int nnz = BoxesRunTime.unboxToInt(vectors.iterator().map((x$3) -> BoxesRunTime.boxToInteger($anonfun$fromVectors$3(x$3))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      long sparseSize = this.getSparseSize((long)nnz, (long)(numRows + 1));
      return (Matrix)(denseSize < sparseSize ? DenseMatrix$.MODULE$.fromVectors(vectors) : SparseMatrix$.MODULE$.fromVectors(vectors));
   }

   public Matrix dense(final int numRows, final int numCols, final double[] values) {
      return new DenseMatrix(numRows, numCols, values);
   }

   public Matrix sparse(final int numRows, final int numCols, final int[] colPtrs, final int[] rowIndices, final double[] values) {
      return new SparseMatrix(numRows, numCols, colPtrs, rowIndices, values);
   }

   public Matrix fromBreeze(final breeze.linalg.Matrix breeze) {
      if (breeze instanceof breeze.linalg.DenseMatrix var4) {
         return new DenseMatrix(var4.rows(), var4.cols(), var4.data$mcD$sp(), var4.isTranspose());
      } else if (breeze instanceof CSCMatrix var5) {
         return new SparseMatrix(var5.rows(), var5.cols(), var5.colPtrs(), var5.rowIndices(), var5.data$mcD$sp());
      } else {
         throw new UnsupportedOperationException("Do not support conversion from type " + breeze.getClass().getName() + ".");
      }
   }

   public Matrix zeros(final int numRows, final int numCols) {
      return DenseMatrix$.MODULE$.zeros(numRows, numCols);
   }

   public Matrix ones(final int numRows, final int numCols) {
      return DenseMatrix$.MODULE$.ones(numRows, numCols);
   }

   public Matrix eye(final int n) {
      return DenseMatrix$.MODULE$.eye(n);
   }

   public Matrix speye(final int n) {
      return SparseMatrix$.MODULE$.speye(n);
   }

   public Matrix rand(final int numRows, final int numCols, final Random rng) {
      return DenseMatrix$.MODULE$.rand(numRows, numCols, rng);
   }

   public Matrix sprand(final int numRows, final int numCols, final double density, final Random rng) {
      return SparseMatrix$.MODULE$.sprand(numRows, numCols, density, rng);
   }

   public Matrix randn(final int numRows, final int numCols, final Random rng) {
      return DenseMatrix$.MODULE$.randn(numRows, numCols, rng);
   }

   public Matrix sprandn(final int numRows, final int numCols, final double density, final Random rng) {
      return SparseMatrix$.MODULE$.sprandn(numRows, numCols, density, rng);
   }

   public Matrix diag(final Vector vector) {
      return DenseMatrix$.MODULE$.diag(vector);
   }

   public Matrix horzcat(final Matrix[] matrices) {
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(matrices))) {
         return new DenseMatrix(0, 0, (double[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()));
      } else if (matrices.length == 1) {
         return matrices[0];
      } else {
         int numRows = matrices[0].numRows();
         BooleanRef hasSparse = BooleanRef.create(false);
         IntRef numCols = IntRef.create(0);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(matrices), (mat) -> {
            $anonfun$horzcat$1(numRows, hasSparse, numCols, mat);
            return BoxedUnit.UNIT;
         });
         if (!hasSparse.elem) {
            return new DenseMatrix(numRows, numCols.elem, (double[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(matrices), (m) -> m.toArray(), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), scala.reflect.ClassTag..MODULE$.Double()));
         } else {
            IntRef startCol = IntRef.create(0);
            Tuple3[] entries = (Tuple3[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(matrices), (mat) -> {
               int nCols = mat.numCols();
               if (mat instanceof SparseMatrix var5) {
                  Tuple3[] data = new Tuple3[var5.values().length];
                  IntRef cnt = IntRef.create(0);
                  var5.foreachActive((i, j, v) -> {
                     $anonfun$horzcat$6(data, cnt, startCol, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
                     return BoxedUnit.UNIT;
                  });
                  startCol.elem += nCols;
                  return scala.collection.immutable.ArraySeq..MODULE$.unsafeWrapArray(data);
               } else if (mat instanceof DenseMatrix var8) {
                  ArrayBuffer data = new ArrayBuffer();
                  var8.foreachActive((i, j, v) -> {
                     $anonfun$horzcat$7(data, startCol, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
                     return BoxedUnit.UNIT;
                  });
                  startCol.elem += nCols;
                  return data.toSeq();
               } else {
                  throw new MatchError(mat);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
            return SparseMatrix$.MODULE$.fromCOO(numRows, numCols.elem, scala.Predef..MODULE$.wrapRefArray((Object[])entries));
         }
      }
   }

   public Matrix vertcat(final Matrix[] matrices) {
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(matrices))) {
         return new DenseMatrix(0, 0, (double[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()));
      } else if (matrices.length == 1) {
         return matrices[0];
      } else {
         int numCols = matrices[0].numCols();
         BooleanRef hasSparse = BooleanRef.create(false);
         IntRef numRows = IntRef.create(0);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(matrices), (mat) -> {
            $anonfun$vertcat$1(numCols, hasSparse, numRows, mat);
            return BoxedUnit.UNIT;
         });
         if (!hasSparse.elem) {
            double[] allValues = new double[numRows.elem * numCols];
            IntRef startRow = IntRef.create(0);
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(matrices), (mat) -> {
               $anonfun$vertcat$3(numRows, startRow, allValues, mat);
               return BoxedUnit.UNIT;
            });
            return new DenseMatrix(numRows.elem, numCols, allValues);
         } else {
            IntRef startRow = IntRef.create(0);
            Tuple3[] entries = (Tuple3[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(matrices), (mat) -> {
               int nRows = mat.numRows();
               if (mat instanceof SparseMatrix var5) {
                  Tuple3[] data = new Tuple3[var5.values().length];
                  IntRef cnt = IntRef.create(0);
                  var5.foreachActive((i, j, v) -> {
                     $anonfun$vertcat$6(data, cnt, startRow, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
                     return BoxedUnit.UNIT;
                  });
                  startRow.elem += nRows;
                  return scala.collection.immutable.ArraySeq..MODULE$.unsafeWrapArray(data);
               } else if (mat instanceof DenseMatrix var8) {
                  ArrayBuffer data = new ArrayBuffer();
                  var8.foreachActive((i, j, v) -> {
                     $anonfun$vertcat$7(data, startRow, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
                     return BoxedUnit.UNIT;
                  });
                  startRow.elem += nRows;
                  return data.toSeq();
               } else {
                  throw new MatchError(mat);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
            return SparseMatrix$.MODULE$.fromCOO(numRows.elem, numCols, scala.Predef..MODULE$.wrapRefArray((Object[])entries));
         }
      }
   }

   public long getSparseSize(final long numActives, final long numPtrs) {
      int doubleBytes = 8;
      int intBytes = 4;
      long arrayHeader = 12L;
      return (long)doubleBytes * numActives + (long)intBytes * numActives + (long)intBytes * numPtrs + arrayHeader * 3L + 9L;
   }

   public long getDenseSize(final long numCols, final long numRows) {
      int doubleBytes = 8;
      long arrayHeader = 12L;
      return (long)doubleBytes * numCols * numRows + arrayHeader + 9L;
   }

   // $FF: synthetic method
   public static final int $anonfun$fromVectors$3(final Vector x$3) {
      return x$3.numNonzeros();
   }

   // $FF: synthetic method
   public static final void $anonfun$horzcat$1(final int numRows$7, final BooleanRef hasSparse$1, final IntRef numCols$7, final Matrix mat) {
      scala.Predef..MODULE$.require(numRows$7 == mat.numRows(), () -> "The number of rows of the matrices in this sequence, don't match!");
      if (mat instanceof SparseMatrix) {
         hasSparse$1.elem = true;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!(mat instanceof DenseMatrix)) {
            throw new IllegalArgumentException("Unsupported matrix format. Expected SparseMatrix or DenseMatrix. Instead got: " + mat.getClass());
         }

         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      numCols$7.elem += mat.numCols();
   }

   // $FF: synthetic method
   public static final void $anonfun$horzcat$6(final Tuple3[] data$1, final IntRef cnt$1, final IntRef startCol$1, final int i, final int j, final double v) {
      data$1[cnt$1.elem] = new Tuple3(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j + startCol$1.elem), BoxesRunTime.boxToDouble(v));
      ++cnt$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$horzcat$7(final ArrayBuffer data$2, final IntRef startCol$1, final int i, final int j, final double v) {
      if (v != (double)0.0F) {
         data$2.$plus$eq(new Tuple3(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j + startCol$1.elem), BoxesRunTime.boxToDouble(v)));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$1(final int numCols$8, final BooleanRef hasSparse$2, final IntRef numRows$8, final Matrix mat) {
      scala.Predef..MODULE$.require(numCols$8 == mat.numCols(), () -> "The number of columns of the matrices in this sequence, don't match!");
      if (mat instanceof SparseMatrix) {
         hasSparse$2.elem = true;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!(mat instanceof DenseMatrix)) {
            throw new IllegalArgumentException("Unsupported matrix format. Expected SparseMatrix or DenseMatrix. Instead got: " + mat.getClass());
         }

         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      numRows$8.elem += mat.numRows();
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$4(final IntRef numRows$8, final IntRef startRow$1, final double[] allValues$1, final int i, final int j, final double v) {
      int indStart = j * numRows$8.elem + startRow$1.elem;
      allValues$1[indStart + i] = v;
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$3(final IntRef numRows$8, final IntRef startRow$1, final double[] allValues$1, final Matrix mat) {
      int nRows = mat.numRows();
      mat.foreachActive((i, j, v) -> {
         $anonfun$vertcat$4(numRows$8, startRow$1, allValues$1, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
         return BoxedUnit.UNIT;
      });
      startRow$1.elem += nRows;
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$6(final Tuple3[] data$3, final IntRef cnt$2, final IntRef startRow$2, final int i, final int j, final double v) {
      data$3[cnt$2.elem] = new Tuple3(BoxesRunTime.boxToInteger(i + startRow$2.elem), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(v));
      ++cnt$2.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$vertcat$7(final ArrayBuffer data$4, final IntRef startRow$2, final int i, final int j, final double v) {
      if (v != (double)0.0F) {
         data$4.$plus$eq(new Tuple3(BoxesRunTime.boxToInteger(i + startRow$2.elem), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(v)));
      }
   }

   private Matrices$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
