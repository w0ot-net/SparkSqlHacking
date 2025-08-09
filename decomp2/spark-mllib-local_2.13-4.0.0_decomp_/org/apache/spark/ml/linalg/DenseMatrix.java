package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.Function1;
import scala.Function3;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuilder;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tub\u0001B\u0015+\u0001UB\u0001\u0002\u0011\u0001\u0003\u0006\u0004%\t!\u0011\u0005\t\u001d\u0002\u0011\t\u0011)A\u0005\u0005\"A\u0001\u000b\u0001BC\u0002\u0013\u0005\u0011\t\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003C\u0011!!\u0006A!b\u0001\n\u0003)\u0006\u0002C/\u0001\u0005\u0003\u0005\u000b\u0011\u0002,\t\u0011}\u0003!Q1A\u0005B\u0001D\u0001\u0002\u001a\u0001\u0003\u0002\u0003\u0006I!\u0019\u0005\u0006K\u0002!\tA\u001a\u0005\u0006K\u0002!\t\u0001\u001d\u0005\u0006k\u0002!\tE\u001e\u0005\u0006y\u0002!\t% \u0005\u0007}\u0002!\t\u0001L@\t\u0011\u00055\u0001\u0001\"\u0001-\u0003\u001fAq!!\u0004\u0001\t\u0003\n)\u0002\u0003\u0005\u0002\u001e\u0001!\t\u0001LA\u0010\u0011!\t)\u0003\u0001C\u0001Y\u0005\u001d\u0002bBA\u001c\u0001\u0011\u0005\u0013\u0011\b\u0005\t\u0003w\u0001A\u0011\u0001\u0018\u0002>!A\u0011Q\u0005\u0001\u0005\u00021\nI\u0005C\u0004\u0002N\u0001!\t%!\u000f\t\u000f\u0005=\u0003\u0001\"\u0011\u0002R!1\u00111\f\u0001\u0005B\u0005Ca!!\u0018\u0001\t\u0003\n\u0005\u0002CA0\u0001\u0011\u0005C&!\u0019\t\u0011\u00055\u0004\u0001\"\u0011-\u0003_Bq!a\u001d\u0001\t\u0003\n)\b\u0003\u0005\u0002\u0016\u0002!\t\u0001LAL\u000f\u001d\t\tK\u000bE\u0001\u0003G3a!\u000b\u0016\t\u0002\u0005\u0015\u0006BB3\u001f\t\u0003\t9\f\u0003\u0005\u0002:z!\t\u0001LA^\u0011!\tiM\bC\u0001Y\u0005=\u0007bBAn=\u0011\u0005\u0011Q\u001c\u0005\b\u0003KtB\u0011AAt\u0011\u001d\tyO\bC\u0001\u0003cDq!!?\u001f\t\u0003\tY\u0010C\u0004\u0003\u0014y!\tA!\u0006\t\u000f\t}a\u0004\"\u0001\u0003\"!I!\u0011\u0006\u0010\u0002\u0002\u0013%!1\u0006\u0002\f\t\u0016t7/Z'biJL\u0007P\u0003\u0002,Y\u00051A.\u001b8bY\u001eT!!\f\u0018\u0002\u00055d'BA\u00181\u0003\u0015\u0019\b/\u0019:l\u0015\t\t$'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002g\u0005\u0019qN]4\u0004\u0001M\u0019\u0001A\u000e\u001f\u0011\u0005]RT\"\u0001\u001d\u000b\u0003e\nQa]2bY\u0006L!a\u000f\u001d\u0003\r\u0005s\u0017PU3g!\tid(D\u0001+\u0013\ty$F\u0001\u0004NCR\u0014\u0018\u000e_\u0001\b]Vl'k\\<t+\u0005\u0011\u0005CA\u001cD\u0013\t!\u0005HA\u0002J]RD3!\u0001$M!\t9%*D\u0001I\u0015\tIe&\u0001\u0006b]:|G/\u0019;j_:L!a\u0013%\u0003\u000bMKgnY3\"\u00035\u000bQA\r\u00181]A\n\u0001B\\;n%><8\u000f\t\u0015\u0004\u0005\u0019c\u0015a\u00028v[\u000e{Gn\u001d\u0015\u0004\u0007\u0019c\u0015\u0001\u00038v[\u000e{Gn\u001d\u0011)\u0007\u00111E*\u0001\u0004wC2,Xm]\u000b\u0002-B\u0019qgV-\n\u0005aC$!B!se\u0006L\bCA\u001c[\u0013\tY\u0006H\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000b\u0019c\u0015a\u0002<bYV,7\u000f\t\u0015\u0004\r\u0019c\u0015\u0001D5t)J\fgn\u001d9pg\u0016$W#A1\u0011\u0005]\u0012\u0017BA29\u0005\u001d\u0011un\u001c7fC:\fQ\"[:Ue\u0006t7\u000f]8tK\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0003hQ*dg\u000e\u0005\u0002>\u0001!)\u0001)\u0003a\u0001\u0005\"\u001a\u0001N\u0012'\t\u000bAK\u0001\u0019\u0001\")\u0007)4E\nC\u0003U\u0013\u0001\u0007a\u000bK\u0002m\r2CQaX\u0005A\u0002\u0005D3!\u0003$M)\u00119\u0017O]:\t\u000b\u0001S\u0001\u0019\u0001\"\t\u000bAS\u0001\u0019\u0001\"\t\u000bQS\u0001\u0019\u0001,)\u0007)1E*\u0001\u0004fcV\fGn\u001d\u000b\u0003C^DQ\u0001_\u0006A\u0002e\f\u0011a\u001c\t\u0003oiL!a\u001f\u001d\u0003\u0007\u0005s\u00170\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0015\u0001C1t\u0005J,WM_3\u0016\u0005\u0005\u0005\u0001#BA\u0002\u0003\u0017IVBAA\u0003\u0015\rY\u0013q\u0001\u0006\u0003\u0003\u0013\taA\u0019:fKj,\u0017bA \u0002\u0006\u0005)\u0011\r\u001d9msR\u0019\u0011,!\u0005\t\r\u0005Ma\u00021\u0001C\u0003\u0005IG#B-\u0002\u0018\u0005e\u0001BBA\n\u001f\u0001\u0007!\t\u0003\u0004\u0002\u001c=\u0001\rAQ\u0001\u0002U\u0006)\u0011N\u001c3fqR)!)!\t\u0002$!1\u00111\u0003\tA\u0002\tCa!a\u0007\u0011\u0001\u0004\u0011\u0015AB;qI\u0006$X\r\u0006\u0005\u0002*\u0005=\u0012\u0011GA\u001a!\r9\u00141F\u0005\u0004\u0003[A$\u0001B+oSRDa!a\u0005\u0012\u0001\u0004\u0011\u0005BBA\u000e#\u0001\u0007!\t\u0003\u0004\u00026E\u0001\r!W\u0001\u0002m\u0006!1m\u001c9z+\u00059\u0017aA7baR\u0019q-a\u0010\t\u000f\u0005\u00053\u00031\u0001\u0002D\u0005\ta\rE\u00038\u0003\u000bJ\u0016,C\u0002\u0002Ha\u0012\u0011BR;oGRLwN\\\u0019\u0015\u0007\u001d\fY\u0005C\u0004\u0002BQ\u0001\r!a\u0011\u0002\u0013Q\u0014\u0018M\\:q_N,\u0017!\u00044pe\u0016\f7\r[!di&4X\r\u0006\u0003\u0002*\u0005M\u0003bBA!-\u0001\u0007\u0011Q\u000b\t\to\u0005]#IQ-\u0002*%\u0019\u0011\u0011\f\u001d\u0003\u0013\u0019+hn\u0019;j_:\u001c\u0014a\u00038v[:{gN_3s_N\f!B\\;n\u0003\u000e$\u0018N^3t\u00039!xn\u00159beN,W*\u0019;sSb$B!a\u0019\u0002jA\u0019Q(!\u001a\n\u0007\u0005\u001d$F\u0001\u0007Ta\u0006\u00148/Z'biJL\u0007\u0010\u0003\u0004\u0002le\u0001\r!Y\u0001\tG>dW*\u00196pe\u0006iAo\u001c#f]N,W*\u0019;sSb$2aZA9\u0011\u0019\tYG\u0007a\u0001C\u000691m\u001c7Ji\u0016\u0014XCAA<!\u0019\tI(!#\u0002\u0010:!\u00111PAC\u001d\u0011\ti(a!\u000e\u0005\u0005}$bAAAi\u00051AH]8pizJ\u0011!O\u0005\u0004\u0003\u000fC\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003\u0017\u000biI\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\r\t9\t\u000f\t\u0004{\u0005E\u0015bAAJU\t1a+Z2u_J\fabZ3u'&TX-\u00138CsR,7/\u0006\u0002\u0002\u001aB\u0019q'a'\n\u0007\u0005u\u0005H\u0001\u0003M_:<\u0007f\u0001\u0001G\u0019\u0006YA)\u001a8tK6\u000bGO]5y!\tidd\u0005\u0003\u001fm\u0005\u001d\u0006\u0003BAU\u0003gk!!a+\u000b\t\u00055\u0016qV\u0001\u0003S>T!!!-\u0002\t)\fg/Y\u0005\u0005\u0003k\u000bYK\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002$\u00069QO\\1qa2LH\u0003BA_\u0003\u0013\u0004RaNA`\u0003\u0007L1!!19\u0005\u0019y\u0005\u000f^5p]B9q'!2C\u0005Z\u000b\u0017bAAdq\t1A+\u001e9mKRBa!a3!\u0001\u00049\u0017A\u00013n\u0003-1'o\\7WK\u000e$xN]:\u0015\u0007\u001d\f\t\u000eC\u0004\u0002T\u0006\u0002\r!!6\u0002\u000fY,7\r^8sgB1\u0011\u0011PAl\u0003\u001fKA!!7\u0002\u000e\n\u00191+Z9\u0002\u000bi,'o\\:\u0015\u000b\u001d\fy.!9\t\u000b\u0001\u0013\u0003\u0019\u0001\"\t\u000bA\u0013\u0003\u0019\u0001\")\u0007\t2E*\u0001\u0003p]\u0016\u001cH#B4\u0002j\u0006-\b\"\u0002!$\u0001\u0004\u0011\u0005\"\u0002)$\u0001\u0004\u0011\u0005fA\u0012G\u0019\u0006\u0019Q-_3\u0015\u0007\u001d\f\u0019\u0010\u0003\u0004\u0002v\u0012\u0002\rAQ\u0001\u0002]\"\u001aAE\u0012'\u0002\tI\fg\u000e\u001a\u000b\bO\u0006u\u0018q B\u0001\u0011\u0015\u0001U\u00051\u0001C\u0011\u0015\u0001V\u00051\u0001C\u0011\u001d\u0011\u0019!\na\u0001\u0005\u000b\t1A\u001d8h!\u0011\u00119A!\u0004\u000e\u0005\t%!\u0002\u0002B\u0006\u0003_\u000bA!\u001e;jY&!!q\u0002B\u0005\u0005\u0019\u0011\u0016M\u001c3p[\"\u001aQE\u0012'\u0002\u000bI\fg\u000e\u001a8\u0015\u000f\u001d\u00149B!\u0007\u0003\u001c!)\u0001I\na\u0001\u0005\")\u0001K\na\u0001\u0005\"9!1\u0001\u0014A\u0002\t\u0015\u0001f\u0001\u0014G\u0019\u0006!A-[1h)\r9'1\u0005\u0005\b\u0005K9\u0003\u0019AAH\u0003\u00191Xm\u0019;pe\"\u001aqE\u0012'\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t5\u0002\u0003\u0002B\u0018\u0005ki!A!\r\u000b\t\tM\u0012qV\u0001\u0005Y\u0006tw-\u0003\u0003\u00038\tE\"AB(cU\u0016\u001cG\u000fK\u0002\u001f\r2C3!\b$M\u0001"
)
public class DenseMatrix implements Matrix {
   private final int numRows;
   private final int numCols;
   private final double[] values;
   private final boolean isTransposed;

   public static DenseMatrix diag(final Vector vector) {
      return DenseMatrix$.MODULE$.diag(vector);
   }

   public static DenseMatrix randn(final int numRows, final int numCols, final Random rng) {
      return DenseMatrix$.MODULE$.randn(numRows, numCols, rng);
   }

   public static DenseMatrix rand(final int numRows, final int numCols, final Random rng) {
      return DenseMatrix$.MODULE$.rand(numRows, numCols, rng);
   }

   public static DenseMatrix eye(final int n) {
      return DenseMatrix$.MODULE$.eye(n);
   }

   public static DenseMatrix ones(final int numRows, final int numCols) {
      return DenseMatrix$.MODULE$.ones(numRows, numCols);
   }

   public static DenseMatrix zeros(final int numRows, final int numCols) {
      return DenseMatrix$.MODULE$.zeros(numRows, numCols);
   }

   public boolean isColMajor() {
      return Matrix.isColMajor$(this);
   }

   public boolean isRowMajor() {
      return Matrix.isRowMajor$(this);
   }

   public double[] toArray() {
      return Matrix.toArray$(this);
   }

   public Iterator rowIter() {
      return Matrix.rowIter$(this);
   }

   public DenseMatrix multiply(final DenseMatrix y) {
      return Matrix.multiply$(this, (DenseMatrix)y);
   }

   public DenseVector multiply(final DenseVector y) {
      return Matrix.multiply$(this, (DenseVector)y);
   }

   public DenseVector multiply(final Vector y) {
      return Matrix.multiply$(this, (Vector)y);
   }

   public String toString() {
      return Matrix.toString$(this);
   }

   public String toString(final int maxLines, final int maxLineWidth) {
      return Matrix.toString$(this, maxLines, maxLineWidth);
   }

   public SparseMatrix toSparseColMajor() {
      return Matrix.toSparseColMajor$(this);
   }

   public SparseMatrix toSparseRowMajor() {
      return Matrix.toSparseRowMajor$(this);
   }

   public SparseMatrix toSparse() {
      return Matrix.toSparse$(this);
   }

   public DenseMatrix toDense() {
      return Matrix.toDense$(this);
   }

   public DenseMatrix toDenseRowMajor() {
      return Matrix.toDenseRowMajor$(this);
   }

   public DenseMatrix toDenseColMajor() {
      return Matrix.toDenseColMajor$(this);
   }

   public Matrix compressedColMajor() {
      return Matrix.compressedColMajor$(this);
   }

   public Matrix compressedRowMajor() {
      return Matrix.compressedRowMajor$(this);
   }

   public Matrix compressed() {
      return Matrix.compressed$(this);
   }

   public long getDenseSizeInBytes() {
      return Matrix.getDenseSizeInBytes$(this);
   }

   public long getSparseSizeInBytes(final boolean colMajor) {
      return Matrix.getSparseSizeInBytes$(this, colMajor);
   }

   public void org$apache$spark$ml$linalg$Matrix$_setter_$isTransposed_$eq(final boolean x$1) {
   }

   public int numRows() {
      return this.numRows;
   }

   public int numCols() {
      return this.numCols;
   }

   public double[] values() {
      return this.values;
   }

   public boolean isTransposed() {
      return this.isTransposed;
   }

   public boolean equals(final Object o) {
      if (!(o instanceof Matrix var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            breeze.linalg.Matrix var10000 = this.asBreeze();
            breeze.linalg.Matrix var5 = var4.asBreeze();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public int hashCode() {
      return Statics.anyHash(.MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.numRows()), BoxesRunTime.boxToInteger(this.numCols()), this.toArray()})));
   }

   public breeze.linalg.Matrix asBreeze() {
      if (!this.isTransposed()) {
         return new breeze.linalg.DenseMatrix.mcD.sp(this.numRows(), this.numCols(), this.values());
      } else {
         breeze.linalg.DenseMatrix breezeMatrix = new breeze.linalg.DenseMatrix.mcD.sp(this.numCols(), this.numRows(), this.values());
         return (breeze.linalg.Matrix)breezeMatrix.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM());
      }
   }

   public double apply(final int i) {
      return this.values()[i];
   }

   public double apply(final int i, final int j) {
      return this.values()[this.index(i, j)];
   }

   public int index(final int i, final int j) {
      scala.Predef..MODULE$.require(i >= 0 && i < this.numRows(), () -> {
         int var10000 = this.numRows();
         return "Expected 0 <= i < " + var10000 + ", got i = " + i + ".";
      });
      scala.Predef..MODULE$.require(j >= 0 && j < this.numCols(), () -> {
         int var10000 = this.numCols();
         return "Expected 0 <= j < " + var10000 + ", got j = " + j + ".";
      });
      return !this.isTransposed() ? i + this.numRows() * j : j + this.numCols() * i;
   }

   public void update(final int i, final int j, final double v) {
      this.values()[this.index(i, j)] = v;
   }

   public DenseMatrix copy() {
      return new DenseMatrix(this.numRows(), this.numCols(), (double[])this.values().clone());
   }

   public DenseMatrix map(final Function1 f) {
      return new DenseMatrix(this.numRows(), this.numCols(), (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.values()), f, scala.reflect.ClassTag..MODULE$.Double()), this.isTransposed());
   }

   public DenseMatrix update(final Function1 f) {
      int len = this.values().length;

      for(int i = 0; i < len; ++i) {
         this.values()[i] = f.apply$mcDD$sp(this.values()[i]);
      }

      return this;
   }

   public DenseMatrix transpose() {
      return new DenseMatrix(this.numCols(), this.numRows(), this.values(), !this.isTransposed());
   }

   public void foreachActive(final Function3 f) {
      if (!this.isTransposed()) {
         for(int j = 0; j < this.numCols(); ++j) {
            int i = 0;

            for(int indStart = j * this.numRows(); i < this.numRows(); ++i) {
               f.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(this.values()[indStart + i]));
            }
         }

      } else {
         for(int i = 0; i < this.numRows(); ++i) {
            int j = 0;

            for(int indStart = i * this.numCols(); j < this.numCols(); ++j) {
               f.apply(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToDouble(this.values()[indStart + j]));
            }
         }

      }
   }

   public int numNonzeros() {
      return scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.doubleArrayOps(this.values()), (JFunction1.mcZD.sp)(x$1) -> x$1 != (double)0);
   }

   public int numActives() {
      return this.values().length;
   }

   public SparseMatrix toSparseMatrix(final boolean colMajor) {
      if (!colMajor) {
         return this.transpose().toSparseColMajor().transpose();
      } else {
         ArrayBuilder spVals = new ArrayBuilder.ofDouble();
         int[] colPtrs = new int[this.numCols() + 1];
         ArrayBuilder rowIndices = new ArrayBuilder.ofInt();
         int nnz = 0;

         for(int j = 0; j < this.numCols(); colPtrs[j] = nnz) {
            for(int i = 0; i < this.numRows(); ++i) {
               double v = this.values()[this.index(i, j)];
               if (v != (double)0.0F) {
                  rowIndices.$plus$eq(BoxesRunTime.boxToInteger(i));
                  spVals.$plus$eq(BoxesRunTime.boxToDouble(v));
                  ++nnz;
               }
            }

            ++j;
         }

         return new SparseMatrix(this.numRows(), this.numCols(), colPtrs, (int[])rowIndices.result(), (double[])spVals.result());
      }
   }

   public DenseMatrix toDenseMatrix(final boolean colMajor) {
      if (this.isRowMajor() && colMajor) {
         return new DenseMatrix(this.numRows(), this.numCols(), this.toArray(), false);
      } else {
         return this.isColMajor() && !colMajor ? new DenseMatrix(this.numRows(), this.numCols(), this.transpose().toArray(), true) : this;
      }
   }

   public Iterator colIter() {
      return this.isTransposed() ? .MODULE$.Iterator().tabulate(this.numCols(), (j) -> $anonfun$colIter$1(this, BoxesRunTime.unboxToInt(j))) : .MODULE$.Iterator().tabulate(this.numCols(), (j) -> $anonfun$colIter$2(this, BoxesRunTime.unboxToInt(j)));
   }

   public long getSizeInBytes() {
      return Matrices$.MODULE$.getDenseSize((long)this.numCols(), (long)this.numRows());
   }

   // $FF: synthetic method
   public static final DenseVector $anonfun$colIter$1(final DenseMatrix $this, final int j) {
      double[] col = new double[$this.numRows()];
      BLAS$.MODULE$.nativeBLAS().dcopy($this.numRows(), $this.values(), j, $this.numCols(), col, 0, 1);
      return new DenseVector(col);
   }

   // $FF: synthetic method
   public static final DenseVector $anonfun$colIter$2(final DenseMatrix $this, final int j) {
      return new DenseVector((double[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps($this.values()), j * $this.numRows(), (j + 1) * $this.numRows()));
   }

   public DenseMatrix(final int numRows, final int numCols, final double[] values, final boolean isTransposed) {
      this.numRows = numRows;
      this.numCols = numCols;
      this.values = values;
      this.isTransposed = isTransposed;
      Matrix.$init$(this);
      scala.Predef..MODULE$.require(values.length == numRows * numCols, () -> {
         int var10000 = this.values().length;
         return "The number of values supplied doesn't match the size of the matrix! values.length: " + var10000 + ", numRows * numCols: " + this.numRows() * this.numCols();
      });
   }

   public DenseMatrix(final int numRows, final int numCols, final double[] values) {
      this(numRows, numCols, values, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
