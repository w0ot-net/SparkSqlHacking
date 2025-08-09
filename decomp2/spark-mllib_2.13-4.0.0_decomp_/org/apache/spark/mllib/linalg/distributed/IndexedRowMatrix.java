package org.apache.spark.mllib.linalg.distributed;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.SingularValueDecomposition;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re\u0001\u0002\r\u001a\u0001\u0019B\u0001\"\r\u0001\u0003\u0006\u0004%\tA\r\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005g!Aq\t\u0001BA\u0002\u0013%\u0001\n\u0003\u0005M\u0001\t\u0005\r\u0011\"\u0003N\u0011!\u0019\u0006A!A!B\u0013I\u0005\u0002\u0003+\u0001\u0005\u0003\u0007I\u0011B+\t\u0011e\u0003!\u00111A\u0005\niC\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006KA\u0016\u0005\u0006;\u0002!\tA\u0018\u0005\u0006;\u0002!\t!\u001a\u0005\u0006Q\u0002!\t%\u001b\u0005\u0006W\u0002!\t%\u001b\u0005\u0006[\u0002!\tA\u001c\u0005\u0006k\u0002!\tA\u001e\u0005\u0006w\u0002!\t\u0001 \u0005\u0007w\u0002!\t!a\u0002\t\r\u0005M\u0001\u0001\"\u0001o\u0011\u001d\t9\u0002\u0001C\u0001\u00033A\u0011\"a\u0011\u0001#\u0003%\t!!\u0012\t\u0013\u0005e\u0003!%A\u0005\u0002\u0005m\u0003bBA0\u0001\u0011\u0005\u0011\u0011\r\u0005\b\u0003S\u0002A\u0011AA6\u0011!\ty\u0007\u0001C!;\u0005E$\u0001E%oI\u0016DX\r\u001a*po6\u000bGO]5y\u0015\tQ2$A\u0006eSN$(/\u001b2vi\u0016$'B\u0001\u000f\u001e\u0003\u0019a\u0017N\\1mO*\u0011adH\u0001\u0006[2d\u0017N\u0019\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u00011c\u0001\u0001([A\u0011\u0001fK\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t1\u0011I\\=SK\u001a\u0004\"AL\u0018\u000e\u0003eI!\u0001M\r\u0003#\u0011K7\u000f\u001e:jEV$X\rZ'biJL\u00070\u0001\u0003s_^\u001cX#A\u001a\u0011\u0007Q:\u0014(D\u00016\u0015\t1t$A\u0002sI\u0012L!\u0001O\u001b\u0003\u0007I#E\t\u0005\u0002/u%\u00111(\u0007\u0002\u000b\u0013:$W\r_3e%><\bfA\u0001>\u0007B\u0011a(Q\u0007\u0002\u007f)\u0011\u0001iH\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\"@\u0005\u0015\u0019\u0016N\\2fC\u0005!\u0015!B\u0019/a9\u0002\u0014!\u0002:poN\u0004\u0003f\u0001\u0002>\u0007\u0006)aNU8xgV\t\u0011\n\u0005\u0002)\u0015&\u00111*\u000b\u0002\u0005\u0019>tw-A\u0005o%><8o\u0018\u0013fcR\u0011a*\u0015\t\u0003Q=K!\u0001U\u0015\u0003\tUs\u0017\u000e\u001e\u0005\b%\u0012\t\t\u00111\u0001J\u0003\rAH%M\u0001\u0007]J{wo\u001d\u0011\u0002\u000b9\u001cu\u000e\\:\u0016\u0003Y\u0003\"\u0001K,\n\u0005aK#aA%oi\u0006IanQ8mg~#S-\u001d\u000b\u0003\u001dnCqAU\u0004\u0002\u0002\u0003\u0007a+\u0001\u0004o\u0007>d7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t}\u0003'm\u0019\t\u0003]\u0001AQ!M\u0005A\u0002MB3\u0001Y\u001fD\u0011\u00159\u0015\u00021\u0001J\u0011\u0015!\u0016\u00021\u0001WQ\rIQh\u0011\u000b\u0003?\u001aDQ!\r\u0006A\u0002MB3AC\u001fD\u0003\u001dqW/\\\"pYN$\u0012!\u0013\u0015\u0004\u0017u\u001a\u0015a\u00028v[J{wo\u001d\u0015\u0004\u0019u\u001a\u0015AE2pYVlgnU5nS2\f'/\u001b;jKN$\u0012a\u001c\t\u0003]AL!!]\r\u0003!\r{wN\u001d3j]\u0006$X-T1ue&D\bfA\u0007>g\u0006\nA/A\u00032]Yr\u0003'A\u0006u_J{w/T1ue&DH#A<\u0011\u00059B\u0018BA=\u001a\u0005%\u0011vn^'biJL\u0007\u0010K\u0002\u000f{\r\u000bQ\u0002^8CY>\u001c7.T1ue&DH#A?\u0011\u00059r\u0018BA@\u001a\u0005-\u0011En\\2l\u001b\u0006$(/\u001b=)\t=i\u00141A\u0011\u0003\u0003\u000b\tQ!\r\u00184]A\"R!`A\u0005\u0003\u001bAa!a\u0003\u0011\u0001\u00041\u0016\u0001\u0004:poN\u0004VM\u001d\"m_\u000e\\\u0007BBA\b!\u0001\u0007a+\u0001\u0007d_2\u001c\b+\u001a:CY>\u001c7\u000e\u000b\u0003\u0011{\u0005\r\u0011A\u0005;p\u0007>|'\u000fZ5oCR,W*\u0019;sSbDC!E\u001f\u0002\u0004\u0005Q1m\\7qkR,7K\u0016#\u0015\u0011\u0005m\u0011\u0011FA\u0017\u0003o\u0001r!!\b\u0002 }\u000b\u0019#D\u0001\u001c\u0013\r\t\tc\u0007\u0002\u001b'&tw-\u001e7beZ\u000bG.^3EK\u000e|W\u000e]8tSRLwN\u001c\t\u0005\u0003;\t)#C\u0002\u0002(m\u0011a!T1ue&D\bBBA\u0016%\u0001\u0007a+A\u0001l\u0011%\tyC\u0005I\u0001\u0002\u0004\t\t$\u0001\u0005d_6\u0004X\u000f^3V!\rA\u00131G\u0005\u0004\u0003kI#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003s\u0011\u0002\u0013!a\u0001\u0003w\tQA]\"p]\u0012\u00042\u0001KA\u001f\u0013\r\ty$\u000b\u0002\u0007\t>,(\r\\3)\u0007Ii4)\u0001\u000bd_6\u0004X\u000f^3T-\u0012#C-\u001a4bk2$HEM\u000b\u0003\u0003\u000fRC!!\r\u0002J-\u0012\u00111\n\t\u0005\u0003\u001b\n)&\u0004\u0002\u0002P)!\u0011\u0011KA*\u0003%)hn\u00195fG.,GM\u0003\u0002AS%!\u0011qKA(\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0015G>l\u0007/\u001e;f'Z#E\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005u#\u0006BA\u001e\u0003\u0013\n\u0001\"\\;mi&\u0004H.\u001f\u000b\u0004?\u0006\r\u0004bBA3+\u0001\u0007\u00111E\u0001\u0002\u0005\"\u001aQ#P\"\u0002)\r|W\u000e];uK\u001e\u0013\u0018-\\5b]6\u000bGO]5y)\t\t\u0019\u0003K\u0002\u0017{\r\u000b\u0001\u0002^8Ce\u0016,'0\u001a\u000b\u0003\u0003g\u0002b!!\u001e\u0002~\u0005mRBAA<\u0015\ra\u0012\u0011\u0010\u0006\u0003\u0003w\naA\u0019:fKj,\u0017\u0002BA@\u0003o\u00121\u0002R3og\u0016l\u0015\r\u001e:jq\"\u001a\u0001!P\""
)
public class IndexedRowMatrix implements DistributedMatrix {
   private final RDD rows;
   private long nRows;
   private int nCols;

   public RDD rows() {
      return this.rows;
   }

   private long nRows() {
      return this.nRows;
   }

   private void nRows_$eq(final long x$1) {
      this.nRows = x$1;
   }

   private int nCols() {
      return this.nCols;
   }

   private void nCols_$eq(final int x$1) {
      this.nCols = x$1;
   }

   public long numCols() {
      if (this.nCols() <= 0) {
         this.nCols_$eq(((IndexedRow)this.rows().first()).vector().size());
      }

      return (long)this.nCols();
   }

   public long numRows() {
      if (this.nRows() <= 0L) {
         this.nRows_$eq(BoxesRunTime.unboxToLong(this.rows().map((x$1) -> BoxesRunTime.boxToLong($anonfun$numRows$1(x$1)), .MODULE$.Long()).reduce((JFunction2.mcJJJ.sp)(x, y) -> scala.math.package..MODULE$.max(x, y))) + 1L);
      }

      return this.nRows();
   }

   public CoordinateMatrix columnSimilarities() {
      return this.toRowMatrix().columnSimilarities();
   }

   public RowMatrix toRowMatrix() {
      return new RowMatrix(this.rows().map((x$2) -> x$2.vector(), .MODULE$.apply(Vector.class)), 0L, this.nCols());
   }

   public BlockMatrix toBlockMatrix() {
      return this.toBlockMatrix(1024, 1024);
   }

   public BlockMatrix toBlockMatrix(final int rowsPerBlock, final int colsPerBlock) {
      scala.Predef..MODULE$.require(rowsPerBlock > 0, () -> "rowsPerBlock needs to be greater than 0. rowsPerBlock: " + rowsPerBlock);
      scala.Predef..MODULE$.require(colsPerBlock > 0, () -> "colsPerBlock needs to be greater than 0. colsPerBlock: " + colsPerBlock);
      long m = this.numRows();
      long n = this.numCols();
      scala.Predef..MODULE$.require(scala.math.package..MODULE$.ceil((double)m / (double)rowsPerBlock) <= (double)Integer.MAX_VALUE, () -> "Number of rows divided by rowsPerBlock cannot exceed maximum integer.");
      long remainderRowBlockIndex = m / (long)rowsPerBlock;
      long remainderColBlockIndex = n / (long)colsPerBlock;
      int remainderRowBlockSize = (int)(m % (long)rowsPerBlock);
      int remainderColBlockSize = (int)(n % (long)colsPerBlock);
      int numRowBlocks = (int)scala.math.package..MODULE$.ceil((double)m / (double)rowsPerBlock);
      int numColBlocks = (int)scala.math.package..MODULE$.ceil((double)n / (double)colsPerBlock);
      RDD blocks = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.rows().flatMap((ir) -> {
         long blockRow = ir.index() / (long)rowsPerBlock;
         long rowInBlock = ir.index() % (long)rowsPerBlock;
         Vector var8 = ir.vector();
         if (var8 instanceof SparseVector var9) {
            Option var10 = SparseVector$.MODULE$.unapply(var9);
            if (!var10.isEmpty()) {
               int[] indices = (int[])((Tuple3)var10.get())._2();
               double[] values = (double[])((Tuple3)var10.get())._3();
               return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.intArrayOps(indices), scala.Predef..MODULE$.wrapDoubleArray(values))), (x0$1) -> {
                  if (x0$1 != null) {
                     int index = x0$1._1$mcI$sp();
                     double value = x0$1._2$mcD$sp();
                     int blockColumn = index / colsPerBlock;
                     int columnInBlock = index % colsPerBlock;
                     return new Tuple2(new Tuple2.mcII.sp((int)blockRow, blockColumn), new Tuple2(BoxesRunTime.boxToInteger((int)rowInBlock), (Object[])(new Tuple2[]{new Tuple2.mcDI.sp(value, columnInBlock)})));
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, .MODULE$.apply(Tuple2.class)));
            }
         }

         if (var8 instanceof DenseVector var13) {
            Option var14 = DenseVector$.MODULE$.unapply(var13);
            if (!var14.isEmpty()) {
               double[] values = (double[])var14.get();
               return scala.collection.ArrayOps..MODULE$.grouped$extension(scala.Predef..MODULE$.doubleArrayOps(values), colsPerBlock).zipWithIndex().map((x0$2) -> {
                  if (x0$2 != null) {
                     double[] values = (double[])x0$2._1();
                     int blockColumn = x0$2._2$mcI$sp();
                     return new Tuple2(new Tuple2.mcII.sp((int)blockRow, blockColumn), new Tuple2(BoxesRunTime.boxToInteger((int)rowInBlock), scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(values))));
                  } else {
                     throw new MatchError(x0$2);
                  }
               });
            }
         }

         throw new IllegalArgumentException("Unknown vector type " + var8.getClass() + ".");
      }, .MODULE$.apply(Tuple2.class)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(Tuple2.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).groupByKey(GridPartitioner$.MODULE$.apply(numRowBlocks, numColBlocks, this.rows().getNumPartitions())).map((x0$3) -> {
         if (x0$3 != null) {
            Tuple2 var11 = (Tuple2)x0$3._1();
            Iterable itr = (Iterable)x0$3._2();
            if (var11 != null) {
               int blockRow = var11._1$mcI$sp();
               int blockColumn = var11._2$mcI$sp();
               int actualNumRows = (long)blockRow == remainderRowBlockIndex ? remainderRowBlockSize : rowsPerBlock;
               int actualNumColumns = (long)blockColumn == remainderColBlockIndex ? remainderColBlockSize : colsPerBlock;
               int arraySize = actualNumRows * actualNumColumns;
               double[] matrixAsArray = new double[arraySize];
               IntRef countForValues = IntRef.create(0);
               itr.foreach((x0$4) -> {
                  $anonfun$toBlockMatrix$8(matrixAsArray, actualNumRows, countForValues, x0$4);
                  return BoxedUnit.UNIT;
               });
               DenseMatrix denseMatrix = new DenseMatrix(actualNumRows, actualNumColumns, matrixAsArray);
               Matrix finalMatrix = (Matrix)((double)countForValues.elem / (double)arraySize >= 0.1 ? denseMatrix : denseMatrix.toSparse());
               return new Tuple2(new Tuple2.mcII.sp(blockRow, blockColumn), finalMatrix);
            }
         }

         throw new MatchError(x0$3);
      }, .MODULE$.apply(Tuple2.class));
      return new BlockMatrix(blocks, rowsPerBlock, colsPerBlock, m, n);
   }

   public CoordinateMatrix toCoordinateMatrix() {
      RDD entries = this.rows().flatMap((row) -> {
         long rowIndex = row.index();
         Vector var4 = row.vector();
         if (var4 instanceof SparseVector var5) {
            Option var6 = SparseVector$.MODULE$.unapply(var5);
            if (!var6.isEmpty()) {
               int[] indices = (int[])((Tuple3)var6.get())._2();
               double[] values = (double[])((Tuple3)var6.get())._3();
               return scala.package..MODULE$.Iterator().tabulate(indices.length, (i) -> $anonfun$toCoordinateMatrix$2(rowIndex, indices, values, BoxesRunTime.unboxToInt(i)));
            }
         }

         if (var4 instanceof DenseVector var9) {
            Option var10 = DenseVector$.MODULE$.unapply(var9);
            if (!var10.isEmpty()) {
               double[] values = (double[])var10.get();
               return scala.package..MODULE$.Iterator().tabulate(values.length, (i) -> $anonfun$toCoordinateMatrix$3(rowIndex, values, BoxesRunTime.unboxToInt(i)));
            }
         }

         throw new IllegalArgumentException("Unknown vector type " + var4.getClass() + ".");
      }, .MODULE$.apply(MatrixEntry.class));
      return new CoordinateMatrix(entries, this.numRows(), this.numCols());
   }

   public SingularValueDecomposition computeSVD(final int k, final boolean computeU, final double rCond) {
      int n = (int)this.numCols();
      scala.Predef..MODULE$.require(k > 0 && k <= n, () -> "Requested k singular values but got k=" + k + " and numCols=" + n + ".");
      RDD indices = this.rows().map((x$3) -> BoxesRunTime.boxToLong($anonfun$computeSVD$2(x$3)), .MODULE$.Long());
      SingularValueDecomposition svd = this.toRowMatrix().computeSVD(k, computeU, rCond);
      IndexedRowMatrix var10000;
      if (computeU) {
         RDD indexedRows = indices.zip(((RowMatrix)svd.U()).rows(), .MODULE$.apply(Vector.class)).map((x0$1) -> {
            if (x0$1 != null) {
               long i = x0$1._1$mcJ$sp();
               Vector v = (Vector)x0$1._2();
               return new IndexedRow(i, v);
            } else {
               throw new MatchError(x0$1);
            }
         }, .MODULE$.apply(IndexedRow.class));
         var10000 = new IndexedRowMatrix(indexedRows, this.nRows(), (int)((RowMatrix)svd.U()).numCols());
      } else {
         var10000 = null;
      }

      IndexedRowMatrix U = var10000;
      return new SingularValueDecomposition(U, svd.s(), svd.V());
   }

   public boolean computeSVD$default$2() {
      return false;
   }

   public double computeSVD$default$3() {
      return 1.0E-9;
   }

   public IndexedRowMatrix multiply(final Matrix B) {
      RowMatrix mat = this.toRowMatrix().multiply(B);
      RDD indexedRows = this.rows().map((x$4) -> BoxesRunTime.boxToLong($anonfun$multiply$1(x$4)), .MODULE$.Long()).zip(mat.rows(), .MODULE$.apply(Vector.class)).map((x0$1) -> {
         if (x0$1 != null) {
            long i = x0$1._1$mcJ$sp();
            Vector v = (Vector)x0$1._2();
            return new IndexedRow(i, v);
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(IndexedRow.class));
      return new IndexedRowMatrix(indexedRows, this.nRows(), B.numCols());
   }

   public Matrix computeGramianMatrix() {
      return this.toRowMatrix().computeGramianMatrix();
   }

   public breeze.linalg.DenseMatrix toBreeze() {
      int m = (int)this.numRows();
      int n = (int)this.numCols();
      breeze.linalg.DenseMatrix mat = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(m, n, .MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.rows().collect()), (x0$1) -> {
         $anonfun$toBreeze$1(mat, x0$1);
         return BoxedUnit.UNIT;
      });
      return mat;
   }

   // $FF: synthetic method
   public static final long $anonfun$numRows$1(final IndexedRow x$1) {
      return x$1.index();
   }

   // $FF: synthetic method
   public static final void $anonfun$toBlockMatrix$9(final double[] matrixAsArray$1, final int actualNumRows$1, final int rowWithinBlock$1, final IntRef countForValues$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         double value = x0$5._1$mcD$sp();
         int columnWithinBlock = x0$5._2$mcI$sp();
         matrixAsArray$1[columnWithinBlock * actualNumRows$1 + rowWithinBlock$1] = value;
         ++countForValues$1.elem;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$5);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$toBlockMatrix$8(final double[] matrixAsArray$1, final int actualNumRows$1, final IntRef countForValues$1, final Tuple2 x0$4) {
      if (x0$4 != null) {
         int rowWithinBlock = x0$4._1$mcI$sp();
         Tuple2[] valuesWithColumns = (Tuple2[])x0$4._2();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])valuesWithColumns), (x0$5) -> {
            $anonfun$toBlockMatrix$9(matrixAsArray$1, actualNumRows$1, rowWithinBlock, countForValues$1, x0$5);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$4);
      }
   }

   // $FF: synthetic method
   public static final MatrixEntry $anonfun$toCoordinateMatrix$2(final long rowIndex$1, final int[] indices$1, final double[] values$1, final int i) {
      return new MatrixEntry(rowIndex$1, (long)indices$1[i], values$1[i]);
   }

   // $FF: synthetic method
   public static final MatrixEntry $anonfun$toCoordinateMatrix$3(final long rowIndex$1, final double[] values$2, final int i) {
      return new MatrixEntry(rowIndex$1, (long)i, values$2[i]);
   }

   // $FF: synthetic method
   public static final long $anonfun$computeSVD$2(final IndexedRow x$3) {
      return x$3.index();
   }

   // $FF: synthetic method
   public static final long $anonfun$multiply$1(final IndexedRow x$4) {
      return x$4.index();
   }

   // $FF: synthetic method
   public static final void $anonfun$toBreeze$1(final breeze.linalg.DenseMatrix mat$1, final IndexedRow x0$1) {
      if (x0$1 != null) {
         long rowIndex = x0$1.index();
         Vector vector = x0$1.vector();
         int i = (int)rowIndex;
         vector.foreachNonZero((JFunction2.mcVID.sp)(x0$2, x1$1) -> {
            Tuple2.mcID.sp var6 = new Tuple2.mcID.sp(x0$2, x1$1);
            if (var6 != null) {
               int j = ((Tuple2)var6)._1$mcI$sp();
               double v = ((Tuple2)var6)._2$mcD$sp();
               mat$1.update$mcD$sp(i, j, v);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var6);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public IndexedRowMatrix(final RDD rows, final long nRows, final int nCols) {
      this.rows = rows;
      this.nRows = nRows;
      this.nCols = nCols;
      super();
   }

   public IndexedRowMatrix(final RDD rows) {
      this(rows, 0L, 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
