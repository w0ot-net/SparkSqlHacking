package org.apache.spark.mllib.linalg.distributed;

import breeze.linalg.DenseMatrix;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.SparseMatrix$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b\u0001\u0002\u000b\u0016\u0001\tB\u0001\"\f\u0001\u0003\u0006\u0004%\tA\f\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005_!A1\t\u0001BA\u0002\u0013%A\t\u0003\u0005I\u0001\t\u0005\r\u0011\"\u0003J\u0011!y\u0005A!A!B\u0013)\u0005\u0002\u0003)\u0001\u0005\u0003\u0007I\u0011\u0002#\t\u0011E\u0003!\u00111A\u0005\nIC\u0001\u0002\u0016\u0001\u0003\u0002\u0003\u0006K!\u0012\u0005\u0006+\u0002!\tA\u0016\u0005\u0006+\u0002!\t!\u0018\u0005\u0006A\u0002!\t%\u0019\u0005\u0006G\u0002!\t%\u0019\u0005\u0006K\u0002!\tA\u001a\u0005\u0006U\u0002!\ta\u001b\u0005\u0006a\u0002!\t!\u001d\u0005\u0006m\u0002!\ta\u001e\u0005\u0006m\u0002!\t\u0001 \u0005\b\u0003\u0017\u0001A\u0011BA\u0007\u0011!\ty\u0001\u0001C!3\u0005E!\u0001E\"p_J$\u0017N\\1uK6\u000bGO]5y\u0015\t1r#A\u0006eSN$(/\u001b2vi\u0016$'B\u0001\r\u001a\u0003\u0019a\u0017N\\1mO*\u0011!dG\u0001\u0006[2d\u0017N\u0019\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sO\u000e\u00011c\u0001\u0001$SA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t1\u0011I\\=SK\u001a\u0004\"AK\u0016\u000e\u0003UI!\u0001L\u000b\u0003#\u0011K7\u000f\u001e:jEV$X\rZ'biJL\u00070A\u0004f]R\u0014\u0018.Z:\u0016\u0003=\u00022\u0001M\u001a6\u001b\u0005\t$B\u0001\u001a\u001c\u0003\r\u0011H\rZ\u0005\u0003iE\u00121A\u0015#E!\tQc'\u0003\u00028+\tYQ*\u0019;sSb,e\u000e\u001e:zQ\r\t\u0011h\u0010\t\u0003uuj\u0011a\u000f\u0006\u0003ym\t!\"\u00198o_R\fG/[8o\u0013\tq4HA\u0003TS:\u001cW-I\u0001A\u0003\u0015\td\u0006\r\u00181\u0003!)g\u000e\u001e:jKN\u0004\u0003f\u0001\u0002:\u007f\u0005)aNU8xgV\tQ\t\u0005\u0002%\r&\u0011q)\n\u0002\u0005\u0019>tw-A\u0005o%><8o\u0018\u0013fcR\u0011!*\u0014\t\u0003I-K!\u0001T\u0013\u0003\tUs\u0017\u000e\u001e\u0005\b\u001d\u0012\t\t\u00111\u0001F\u0003\rAH%M\u0001\u0007]J{wo\u001d\u0011\u0002\u000b9\u001cu\u000e\\:\u0002\u00139\u001cu\u000e\\:`I\u0015\fHC\u0001&T\u0011\u001dqu!!AA\u0002\u0015\u000baA\\\"pYN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003X1j[\u0006C\u0001\u0016\u0001\u0011\u0015i\u0013\u00021\u00010Q\rA\u0016h\u0010\u0005\u0006\u0007&\u0001\r!\u0012\u0005\u0006!&\u0001\r!\u0012\u0015\u0004\u0013ezDCA,_\u0011\u0015i#\u00021\u00010Q\rQ\u0011hP\u0001\b]Vl7i\u001c7t)\u0005)\u0005fA\u0006:\u007f\u00059a.^7S_^\u001c\bf\u0001\u0007:\u007f\u0005IAO]1ogB|7/\u001a\u000b\u0002/\"\u001aQ\"\u000f5\"\u0003%\fQ!\r\u00184]A\n!\u0003^8J]\u0012,\u00070\u001a3S_^l\u0015\r\u001e:jqR\tA\u000e\u0005\u0002+[&\u0011a.\u0006\u0002\u0011\u0013:$W\r_3e%><X*\u0019;sSbD3AD\u001d@\u0003-!xNU8x\u001b\u0006$(/\u001b=\u0015\u0003I\u0004\"AK:\n\u0005Q,\"!\u0003*po6\u000bGO]5yQ\ry\u0011hP\u0001\u000ei>\u0014En\\2l\u001b\u0006$(/\u001b=\u0015\u0003a\u0004\"AK=\n\u0005i,\"a\u0003\"m_\u000e\\W*\u0019;sSbD3\u0001E\u001di)\u0011AX0!\u0002\t\u000by\f\u0002\u0019A@\u0002\u0019I|wo\u001d)fe\ncwnY6\u0011\u0007\u0011\n\t!C\u0002\u0002\u0004\u0015\u00121!\u00138u\u0011\u0019\t9!\u0005a\u0001\u007f\u0006a1m\u001c7t!\u0016\u0014(\t\\8dW\"\u001a\u0011#\u000f5\u0002\u0017\r|W\u000e];uKNK'0\u001a\u000b\u0002\u0015\u0006AAo\u001c\"sK\u0016TX\r\u0006\u0002\u0002\u0014A1\u0011QCA\u000f\u0003Ci!!a\u0006\u000b\u0007a\tIB\u0003\u0002\u0002\u001c\u00051!M]3fu\u0016LA!a\b\u0002\u0018\tYA)\u001a8tK6\u000bGO]5y!\r!\u00131E\u0005\u0004\u0003K)#A\u0002#pk\ndW\rK\u0002\u0001s}\u0002"
)
public class CoordinateMatrix implements DistributedMatrix {
   private final RDD entries;
   private long nRows;
   private long nCols;

   public RDD entries() {
      return this.entries;
   }

   private long nRows() {
      return this.nRows;
   }

   private void nRows_$eq(final long x$1) {
      this.nRows = x$1;
   }

   private long nCols() {
      return this.nCols;
   }

   private void nCols_$eq(final long x$1) {
      this.nCols = x$1;
   }

   public long numCols() {
      if (this.nCols() <= 0L) {
         this.computeSize();
      }

      return this.nCols();
   }

   public long numRows() {
      if (this.nRows() <= 0L) {
         this.computeSize();
      }

      return this.nRows();
   }

   public CoordinateMatrix transpose() {
      return new CoordinateMatrix(this.entries().map((x) -> new MatrixEntry(x.j(), x.i(), x.value()), .MODULE$.apply(MatrixEntry.class)), this.numCols(), this.numRows());
   }

   public IndexedRowMatrix toIndexedRowMatrix() {
      long nl = this.numCols();
      if (nl > 2147483647L) {
         throw scala.sys.package..MODULE$.error("Cannot convert to a row-oriented format because the number of columns " + nl + " is too large.");
      } else {
         int n = (int)nl;
         RDD indexedRows = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.entries().map((entry) -> new Tuple2(BoxesRunTime.boxToLong(entry.i()), new Tuple2.mcID.sp((int)entry.j(), entry.value())), .MODULE$.apply(Tuple2.class)), .MODULE$.Long(), .MODULE$.apply(Tuple2.class), scala.math.Ordering.Long..MODULE$).groupByKey().map((x0$1) -> {
            if (x0$1 != null) {
               long i = x0$1._1$mcJ$sp();
               Iterable vectorEntries = (Iterable)x0$1._2();
               return new IndexedRow(i, Vectors$.MODULE$.sparse(n, vectorEntries.toSeq()));
            } else {
               throw new MatchError(x0$1);
            }
         }, .MODULE$.apply(IndexedRow.class));
         return new IndexedRowMatrix(indexedRows, this.numRows(), n);
      }
   }

   public RowMatrix toRowMatrix() {
      return this.toIndexedRowMatrix().toRowMatrix();
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
      scala.Predef..MODULE$.require(scala.math.package..MODULE$.ceil((double)n / (double)colsPerBlock) <= (double)Integer.MAX_VALUE, () -> "Number of cols divided by colsPerBlock cannot exceed maximum integer.");
      int numRowBlocks = (int)scala.math.package..MODULE$.ceil((double)m / (double)rowsPerBlock);
      int numColBlocks = (int)scala.math.package..MODULE$.ceil((double)n / (double)colsPerBlock);
      GridPartitioner partitioner = GridPartitioner$.MODULE$.apply(numRowBlocks, numColBlocks, this.entries().partitions().length);
      RDD blocks = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.entries().map((entry) -> {
         int blockRowIndex = (int)(entry.i() / (long)rowsPerBlock);
         int blockColIndex = (int)(entry.j() / (long)colsPerBlock);
         long rowId = entry.i() % (long)rowsPerBlock;
         long colId = entry.j() % (long)colsPerBlock;
         return new Tuple2(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), new Tuple3(BoxesRunTime.boxToInteger((int)rowId), BoxesRunTime.boxToInteger((int)colId), BoxesRunTime.boxToDouble(entry.value())));
      }, .MODULE$.apply(Tuple2.class)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(Tuple3.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).groupByKey(partitioner).map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var9 = (Tuple2)x0$1._1();
            Iterable entry = (Iterable)x0$1._2();
            if (var9 != null) {
               int blockRowIndex = var9._1$mcI$sp();
               int blockColIndex = var9._2$mcI$sp();
               int effRows = (int)scala.math.package..MODULE$.min(m - (long)blockRowIndex * (long)rowsPerBlock, (long)rowsPerBlock);
               int effCols = (int)scala.math.package..MODULE$.min(n - (long)blockColIndex * (long)colsPerBlock, (long)colsPerBlock);
               return new Tuple2(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), SparseMatrix$.MODULE$.fromCOO(effRows, effCols, entry));
            }
         }

         throw new MatchError(x0$1);
      }, .MODULE$.apply(Tuple2.class));
      return new BlockMatrix(blocks, rowsPerBlock, colsPerBlock, m, n);
   }

   private void computeSize() {
      Tuple2 var3 = (Tuple2)this.entries().map((entry) -> new Tuple2.mcJJ.sp(entry.i(), entry.j()), .MODULE$.apply(Tuple2.class)).reduce((x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            Tuple2 var4 = (Tuple2)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var4 != null) {
               long i1 = var4._1$mcJ$sp();
               long j1 = var4._2$mcJ$sp();
               if (var5 != null) {
                  long i2 = var5._1$mcJ$sp();
                  long j2 = var5._2$mcJ$sp();
                  return new Tuple2.mcJJ.sp(scala.math.package..MODULE$.max(i1, i2), scala.math.package..MODULE$.max(j1, j2));
               }
            }
         }

         throw new MatchError(var3);
      });
      if (var3 != null) {
         long m1 = var3._1$mcJ$sp();
         long n1 = var3._2$mcJ$sp();
         Tuple2.mcJJ.sp var2 = new Tuple2.mcJJ.sp(m1, n1);
         long m1 = ((Tuple2)var2)._1$mcJ$sp();
         long n1 = ((Tuple2)var2)._2$mcJ$sp();
         this.nRows_$eq(scala.math.package..MODULE$.max(this.nRows(), m1 + 1L));
         this.nCols_$eq(scala.math.package..MODULE$.max(this.nCols(), n1 + 1L));
      } else {
         throw new MatchError(var3);
      }
   }

   public DenseMatrix toBreeze() {
      int m = (int)this.numRows();
      int n = (int)this.numCols();
      DenseMatrix mat = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(m, n, .MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.entries().collect()), (x0$1) -> {
         $anonfun$toBreeze$1(mat, x0$1);
         return BoxedUnit.UNIT;
      });
      return mat;
   }

   // $FF: synthetic method
   public static final void $anonfun$toBreeze$1(final DenseMatrix mat$1, final MatrixEntry x0$1) {
      if (x0$1 != null) {
         long i = x0$1.i();
         long j = x0$1.j();
         double value = x0$1.value();
         mat$1.update$mcD$sp((int)i, (int)j, value);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public CoordinateMatrix(final RDD entries, final long nRows, final long nCols) {
      this.entries = entries;
      this.nRows = nRows;
      this.nCols = nCols;
      super();
   }

   public CoordinateMatrix(final RDD entries) {
      this(entries, 0L, 0L);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
