package org.apache.spark.ml.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple6;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ArrayBuilder.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class SparseMatrix$ implements Serializable {
   public static final SparseMatrix$ MODULE$ = new SparseMatrix$();

   public Option unapply(final SparseMatrix sm) {
      return new Some(new Tuple6(BoxesRunTime.boxToInteger(sm.numRows()), BoxesRunTime.boxToInteger(sm.numCols()), sm.colPtrs(), sm.rowIndices(), sm.values(), BoxesRunTime.boxToBoolean(sm.isTransposed())));
   }

   public SparseMatrix fromVectors(final Seq vectors) {
      int numRows = vectors.length();
      int numCols = ((Vector)vectors.head()).size();
      ArrayBuilder colIndices = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      ArrayBuilder values = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      ArrayBuilder rowPtrs = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      int rowPtr = 0;
      rowPtrs.$plus$eq(BoxesRunTime.boxToInteger(0));

      for(int j = 0; j < numRows; ++j) {
         IntRef nnz = IntRef.create(0);
         ((Vector)vectors.apply(j)).foreachNonZero((JFunction2.mcVID.sp)(i, v) -> {
            colIndices.$plus$eq(BoxesRunTime.boxToInteger(i));
            values.$plus$eq(BoxesRunTime.boxToDouble(v));
            ++nnz.elem;
         });
         rowPtr += nnz.elem;
         rowPtrs.$plus$eq(BoxesRunTime.boxToInteger(rowPtr));
      }

      return new SparseMatrix(numRows, numCols, (int[])rowPtrs.result(), (int[])colIndices.result(), (double[])values.result(), true);
   }

   public SparseMatrix fromCOO(final int numRows, final int numCols, final Iterable entries) {
      Seq sortedEntries = (Seq)entries.toSeq().sortBy((v) -> new Tuple2.mcII.sp(BoxesRunTime.unboxToInt(v._2()), BoxesRunTime.unboxToInt(v._1())), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$));
      int numEntries = sortedEntries.size();
      if (sortedEntries.nonEmpty()) {
         scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{BoxesRunTime.unboxToInt(((Tuple3)sortedEntries.head())._2()), BoxesRunTime.unboxToInt(((Tuple3)sortedEntries.last())._2())})).foreach((JFunction1.mcVI.sp)(col) -> scala.Predef..MODULE$.require(col >= 0 && col < numCols, () -> "Column index out of range [0, " + numCols + "): " + col + "."));
      }

      int[] colPtrs = new int[numCols + 1];
      ArrayBuilder rowIndices = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      rowIndices.sizeHint(numEntries);
      ArrayBuilder values = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      values.sizeHint(numEntries);
      IntRef nnz = IntRef.create(0);
      IntRef prevCol = IntRef.create(0);
      IntRef prevRow = IntRef.create(-1);
      DoubleRef prevVal = DoubleRef.create((double)0.0F);
      ((IterableOnceOps)sortedEntries.view().$colon$plus(new Tuple3(BoxesRunTime.boxToInteger(numRows), BoxesRunTime.boxToInteger(numCols), BoxesRunTime.boxToDouble((double)1.0F)))).foreach((x0$1) -> {
         $anonfun$fromCOO$4(prevRow, prevCol, prevVal, numRows, nnz, rowIndices, values, colPtrs, x0$1);
         return BoxedUnit.UNIT;
      });
      return new SparseMatrix(numRows, numCols, colPtrs, (int[])rowIndices.result(), (double[])values.result());
   }

   public SparseMatrix speye(final int n) {
      return new SparseMatrix(n, n, (int[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), n).toArray(scala.reflect.ClassTag..MODULE$.Int()), (int[])scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n).toArray(scala.reflect.ClassTag..MODULE$.Int()), (double[])scala.Array..MODULE$.fill(n, (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double()));
   }

   private SparseMatrix genRandMatrix(final int numRows, final int numCols, final double density, final Random rng) {
      scala.Predef..MODULE$.require(numRows > 0, () -> "numRows must be greater than 0 but got " + numRows);
      scala.Predef..MODULE$.require(numCols > 0, () -> "numCols must be greater than 0 but got " + numCols);
      scala.Predef..MODULE$.require(density >= (double)0.0F && density <= (double)1.0F, () -> "density must be a double in the range 0.0 <= d <= 1.0. Currently, density: " + density);
      long size = (long)numRows * (long)numCols;
      double expected = (double)size * density;
      scala.Predef..MODULE$.assert(expected < (double)Integer.MAX_VALUE, () -> "The expected number of nonzeros cannot be greater than Int.MaxValue.");
      int nnz = (int)scala.math.package..MODULE$.ceil(expected);
      if (density == (double)0.0F) {
         return new SparseMatrix(numRows, numCols, new int[numCols + 1], (int[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Int()), (double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()));
      } else if (density == (double)1.0F) {
         int[] colPtrs = (int[])scala.Array..MODULE$.tabulate(numCols + 1, (JFunction1.mcII.sp)(jx) -> jx * numRows, scala.reflect.ClassTag..MODULE$.Int());
         int[] rowIndices = (int[])scala.Array..MODULE$.tabulate((int)size, (JFunction1.mcII.sp)(idxx) -> idxx % numRows, scala.reflect.ClassTag..MODULE$.Int());
         return new SparseMatrix(numRows, numCols, colPtrs, rowIndices, new double[numRows * numCols]);
      } else if (density < 0.34) {
         HashSet entries = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);

         while(entries.size() < nnz) {
            entries.$plus$eq(new Tuple2.mcII.sp(rng.nextInt(numRows), rng.nextInt(numCols)));
         }

         return this.fromCOO(numRows, numCols, (Iterable)entries.map((v) -> new Tuple3(BoxesRunTime.boxToInteger(v._1$mcI$sp()), BoxesRunTime.boxToInteger(v._2$mcI$sp()), BoxesRunTime.boxToDouble((double)1.0F))));
      } else {
         long idx = 0L;
         int numSelected = 0;
         int j = 0;
         int[] colPtrs = new int[numCols + 1];

         int[] rowIndices;
         for(rowIndices = new int[nnz]; j < numCols && numSelected < nnz; ++j) {
            for(int i = 0; i < numRows && numSelected < nnz; ++idx) {
               if (rng.nextDouble() < (double)1.0F * (double)(nnz - numSelected) / (double)(size - idx)) {
                  rowIndices[numSelected] = i;
                  ++numSelected;
               }

               ++i;
            }

            colPtrs[j + 1] = numSelected;
         }

         return new SparseMatrix(numRows, numCols, colPtrs, rowIndices, new double[nnz]);
      }
   }

   public SparseMatrix sprand(final int numRows, final int numCols, final double density, final Random rng) {
      SparseMatrix mat = this.genRandMatrix(numRows, numCols, density, rng);
      return mat.update((JFunction1.mcDD.sp)(i) -> rng.nextDouble());
   }

   public SparseMatrix sprandn(final int numRows, final int numCols, final double density, final Random rng) {
      SparseMatrix mat = this.genRandMatrix(numRows, numCols, density, rng);
      return mat.update((JFunction1.mcDD.sp)(i) -> rng.nextGaussian());
   }

   public SparseMatrix spdiag(final Vector vector) {
      int n = vector.size();
      if (vector instanceof SparseVector var5) {
         return this.fromCOO(n, n, scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.intArrayOps(var5.indices()), scala.Predef..MODULE$.wrapDoubleArray(var5.values()))), (v) -> new Tuple3(BoxesRunTime.boxToInteger(v._1$mcI$sp()), BoxesRunTime.boxToInteger(v._1$mcI$sp()), BoxesRunTime.boxToDouble(v._2$mcD$sp())), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))));
      } else if (vector instanceof DenseVector var6) {
         Tuple2[] entries = scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(var6.values()));
         Tuple2[] nnzVals = (Tuple2[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])entries), (v) -> BoxesRunTime.boxToBoolean($anonfun$spdiag$2(v)));
         return this.fromCOO(n, n, scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])nnzVals), (v) -> new Tuple3(BoxesRunTime.boxToInteger(v._2$mcI$sp()), BoxesRunTime.boxToInteger(v._2$mcI$sp()), BoxesRunTime.boxToDouble(v._1$mcD$sp())), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))));
      } else {
         throw new MatchError(vector);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparseMatrix$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$fromCOO$4(final IntRef prevRow$1, final IntRef prevCol$1, final DoubleRef prevVal$1, final int numRows$5, final IntRef nnz$2, final ArrayBuilder rowIndices$1, final ArrayBuilder values$3, final int[] colPtrs$1, final Tuple3 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         int i = BoxesRunTime.unboxToInt(x0$1._1());
         int j = BoxesRunTime.unboxToInt(x0$1._2());
         double v = BoxesRunTime.unboxToDouble(x0$1._3());
         if (v != (double)0) {
            if (i == prevRow$1.elem && j == prevCol$1.elem) {
               prevVal$1.elem += v;
               BoxedUnit var17 = BoxedUnit.UNIT;
            } else {
               if (prevVal$1.elem != (double)0) {
                  scala.Predef..MODULE$.require(prevRow$1.elem >= 0 && prevRow$1.elem < numRows$5, () -> "Row index out of range [0, " + numRows$5 + "): " + prevRow$1.elem + ".");
                  ++nnz$2.elem;
                  rowIndices$1.$plus$eq(BoxesRunTime.boxToInteger(prevRow$1.elem));
                  values$3.$plus$eq(BoxesRunTime.boxToDouble(prevVal$1.elem));
               } else {
                  BoxedUnit var15 = BoxedUnit.UNIT;
               }

               prevRow$1.elem = i;

               for(prevVal$1.elem = v; prevCol$1.elem < j; ++prevCol$1.elem) {
                  colPtrs$1[prevCol$1.elem + 1] = nnz$2.elem;
               }

               BoxedUnit var16 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$spdiag$2(final Tuple2 v) {
      return v._1$mcD$sp() != (double)0.0F;
   }

   private SparseMatrix$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
