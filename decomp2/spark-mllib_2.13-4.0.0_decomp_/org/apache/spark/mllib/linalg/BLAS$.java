package org.apache.spark.mllib.linalg;

import dev.ludovic.netlib.blas.JavaBLAS;
import dev.ludovic.netlib.blas.NativeBLAS;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;

public final class BLAS$ implements Serializable, Logging {
   public static final BLAS$ MODULE$ = new BLAS$();
   private static transient dev.ludovic.netlib.blas.BLAS _javaBLAS;
   private static transient dev.ludovic.netlib.blas.BLAS _nativeBLAS;
   private static final int nativeL1Threshold;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      nativeL1Threshold = 256;
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private dev.ludovic.netlib.blas.BLAS _javaBLAS() {
      return _javaBLAS;
   }

   private void _javaBLAS_$eq(final dev.ludovic.netlib.blas.BLAS x$1) {
      _javaBLAS = x$1;
   }

   private dev.ludovic.netlib.blas.BLAS _nativeBLAS() {
      return _nativeBLAS;
   }

   private void _nativeBLAS_$eq(final dev.ludovic.netlib.blas.BLAS x$1) {
      _nativeBLAS = x$1;
   }

   private int nativeL1Threshold() {
      return nativeL1Threshold;
   }

   public dev.ludovic.netlib.blas.BLAS javaBLAS() {
      if (this._javaBLAS() == null) {
         this._javaBLAS_$eq(JavaBLAS.getInstance());
      }

      return this._javaBLAS();
   }

   public dev.ludovic.netlib.blas.BLAS nativeBLAS() {
      if (this._nativeBLAS() == null) {
         this._nativeBLAS_$eq(this.liftedTree1$1());
      }

      return this._nativeBLAS();
   }

   public dev.ludovic.netlib.blas.BLAS getBLAS(final int vectorSize) {
      return vectorSize < this.nativeL1Threshold() ? this.javaBLAS() : this.nativeBLAS();
   }

   public void axpy(final double a, final Vector x, final Vector y) {
      .MODULE$.require(x.size() == y.size());
      if (y instanceof DenseVector var8) {
         if (x instanceof SparseVector var10) {
            this.axpy(a, var10, var8);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!(x instanceof DenseVector)) {
               throw new UnsupportedOperationException("axpy doesn't support x type " + x.getClass() + ".");
            }

            DenseVector var11 = (DenseVector)x;
            this.axpy(a, var11, var8);
            BoxedUnit var12 = BoxedUnit.UNIT;
         }

         BoxedUnit var13 = BoxedUnit.UNIT;
      } else {
         throw new IllegalArgumentException("axpy only supports adding to a dense vector but got type " + y.getClass() + ".");
      }
   }

   private void axpy(final double a, final DenseVector x, final DenseVector y) {
      int n = x.size();
      this.getBLAS(n).daxpy(n, a, x.values(), 1, y.values(), 1);
   }

   private void axpy(final double a, final SparseVector x, final DenseVector y) {
      double[] xValues = x.values();
      int[] xIndices = x.indices();
      double[] yValues = y.values();
      int nnz = xIndices.length;
      if (a == (double)1.0F) {
         for(int k = 0; k < nnz; ++k) {
            int var10 = xIndices[k];
            yValues[var10] += xValues[k];
         }

      } else {
         for(int k = 0; k < nnz; ++k) {
            int var12 = xIndices[k];
            yValues[var12] += a * xValues[k];
         }

      }
   }

   public void axpy(final double a, final DenseMatrix X, final DenseMatrix Y) {
      .MODULE$.require(X.numRows() == Y.numRows() && X.numCols() == Y.numCols(), () -> {
         Tuple2.mcII.sp var10000 = new Tuple2.mcII.sp(X.numRows(), X.numCols());
         return "Dimension mismatch: size(X) = " + var10000 + " but size(Y) = " + new Tuple2.mcII.sp(Y.numRows(), Y.numCols()) + ".";
      });
      this.getBLAS(X.values().length).daxpy(X.numRows() * X.numCols(), a, X.values(), 1, Y.values(), 1);
   }

   public double dot(final Vector x, final Vector y) {
      .MODULE$.require(x.size() == y.size(), () -> {
         int var10000 = x.size();
         return "BLAS.dot(x: Vector, y:Vector) was given Vectors with non-matching sizes: x.size = " + var10000 + ", y.size = " + y.size();
      });
      Tuple2 var5 = new Tuple2(x, y);
      if (var5 != null) {
         Vector dx = (Vector)var5._1();
         Vector dy = (Vector)var5._2();
         if (dx instanceof DenseVector) {
            DenseVector var8 = (DenseVector)dx;
            if (dy instanceof DenseVector) {
               DenseVector var9 = (DenseVector)dy;
               return this.dot(var8, var9);
            }
         }
      }

      if (var5 != null) {
         Vector sx = (Vector)var5._1();
         Vector dy = (Vector)var5._2();
         if (sx instanceof SparseVector) {
            SparseVector var12 = (SparseVector)sx;
            if (dy instanceof DenseVector) {
               DenseVector var13 = (DenseVector)dy;
               return this.dot(var12, var13);
            }
         }
      }

      if (var5 != null) {
         Vector dx = (Vector)var5._1();
         Vector sy = (Vector)var5._2();
         if (dx instanceof DenseVector) {
            DenseVector var16 = (DenseVector)dx;
            if (sy instanceof SparseVector) {
               SparseVector var17 = (SparseVector)sy;
               return this.dot(var17, var16);
            }
         }
      }

      if (var5 != null) {
         Vector sx = (Vector)var5._1();
         Vector sy = (Vector)var5._2();
         if (sx instanceof SparseVector) {
            SparseVector var20 = (SparseVector)sx;
            if (sy instanceof SparseVector) {
               SparseVector var21 = (SparseVector)sy;
               return this.dot(var20, var21);
            }
         }
      }

      Class var10002 = x.getClass();
      throw new IllegalArgumentException("dot doesn't support (" + var10002 + ", " + y.getClass() + ").");
   }

   private double dot(final DenseVector x, final DenseVector y) {
      int n = x.size();
      return this.getBLAS(n).ddot(n, x.values(), 1, y.values(), 1);
   }

   private double dot(final SparseVector x, final DenseVector y) {
      double[] xValues = x.values();
      int[] xIndices = x.indices();
      double[] yValues = y.values();
      int nnz = xIndices.length;
      double sum = (double)0.0F;

      for(int k = 0; k < nnz; ++k) {
         sum += xValues[k] * yValues[xIndices[k]];
      }

      return sum;
   }

   private double dot(final SparseVector x, final SparseVector y) {
      double[] xValues = x.values();
      int[] xIndices = x.indices();
      double[] yValues = y.values();
      int[] yIndices = y.indices();
      int nnzx = xIndices.length;
      int nnzy = yIndices.length;
      int kx = 0;
      int ky = 0;

      double sum;
      for(sum = (double)0.0F; kx < nnzx && ky < nnzy; ++kx) {
         int ix;
         for(ix = xIndices[kx]; ky < nnzy && yIndices[ky] < ix; ++ky) {
         }

         if (ky < nnzy && yIndices[ky] == ix) {
            sum += xValues[kx] * yValues[ky];
            ++ky;
         }
      }

      return sum;
   }

   public void copy(final Vector x, final Vector y) {
      int n = y.size();
      .MODULE$.require(x.size() == n);
      if (!(y instanceof DenseVector var7)) {
         throw new IllegalArgumentException("y must be dense in copy but got " + y.getClass());
      } else {
         if (x instanceof SparseVector var9) {
            int[] sxIndices = var9.indices();
            double[] sxValues = var9.values();
            double[] dyValues = var7.values();
            int nnz = sxIndices.length;
            int i = 0;

            for(int k = 0; k < nnz; ++k) {
               for(int j = sxIndices[k]; i < j; ++i) {
                  dyValues[i] = (double)0.0F;
               }

               dyValues[i] = sxValues[k];
               ++i;
            }

            while(i < n) {
               dyValues[i] = (double)0.0F;
               ++i;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!(x instanceof DenseVector)) {
               throw new MatchError(x);
            }

            DenseVector var17 = (DenseVector)x;
            scala.Array..MODULE$.copy(var17.values(), 0, var7.values(), 0, n);
            BoxedUnit var18 = BoxedUnit.UNIT;
         }

         BoxedUnit var19 = BoxedUnit.UNIT;
      }
   }

   public void scal(final double a, final Vector x) {
      if (x instanceof SparseVector var6) {
         this.getBLAS(var6.values().length).dscal(var6.values().length, a, var6.values(), 1);
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (x instanceof DenseVector var7) {
         this.getBLAS(var7.size()).dscal(var7.values().length, a, var7.values(), 1);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new IllegalArgumentException("scal doesn't support vector type " + x.getClass() + ".");
      }
   }

   public void spr(final double alpha, final Vector v, final DenseVector U) {
      this.spr(alpha, v, U.values());
   }

   public void spr(final double alpha, final Vector v, final double[] U) {
      int n = v.size();
      if (v instanceof DenseVector var8) {
         Option var9 = DenseVector$.MODULE$.unapply(var8);
         if (!var9.isEmpty()) {
            double[] values = (double[])var9.get();
            this.nativeBLAS().dspr("U", n, alpha, values, 1, U);
            BoxedUnit var27 = BoxedUnit.UNIT;
            return;
         }
      }

      if (v instanceof SparseVector var11) {
         Option var12 = SparseVector$.MODULE$.unapply(var11);
         if (!var12.isEmpty()) {
            int[] indices = (int[])((Tuple3)var12.get())._2();
            double[] values = (double[])((Tuple3)var12.get())._3();
            int nnz = indices.length;
            int colStartIdx = 0;
            int prevCol = 0;
            int col = 0;
            int j = 0;
            int i = 0;

            for(double av = (double)0.0F; j < nnz; prevCol = col) {
               col = indices[j];
               colStartIdx += (col - prevCol) * (col + prevCol + 1) / 2;
               av = alpha * values[j];

               for(int var25 = 0; var25 <= j; ++var25) {
                  int var23 = colStartIdx + indices[var25];
                  U[var23] += av * values[var25];
               }

               ++j;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new IllegalArgumentException("Unknown vector type " + v.getClass() + ".");
   }

   public void syr(final double alpha, final Vector x, final DenseMatrix A) {
      int mA = A.numRows();
      int nA = A.numCols();
      .MODULE$.require(mA == nA, () -> "A is not a square matrix (and hence is not symmetric). A: " + mA + " x " + nA);
      .MODULE$.require(mA == x.size(), () -> "The size of x doesn't match the rank of A. A: " + mA + " x " + nA + ", x: " + x.size());
      if (x instanceof DenseVector var9) {
         this.syr(alpha, var9, A);
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (x instanceof SparseVector var10) {
         this.syr(alpha, var10, A);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new IllegalArgumentException("syr doesn't support vector type " + x.getClass() + ".");
      }
   }

   private void syr(final double alpha, final DenseVector x, final DenseMatrix A) {
      int nA = A.numRows();
      int mA = A.numCols();
      this.nativeBLAS().dsyr("U", x.size(), alpha, x.values(), 1, A.values(), nA);

      for(int i = 0; i < mA; ++i) {
         for(int j = i + 1; j < nA; ++j) {
            A.update(j, i, A.apply(i, j));
         }
      }

   }

   private void syr(final double alpha, final SparseVector x, final DenseMatrix A) {
      int mA = A.numCols();
      int[] xIndices = x.indices();
      double[] xValues = x.values();
      int nnz = xValues.length;
      double[] Avalues = A.values();

      for(int i = 0; i < nnz; ++i) {
         double multiplier = alpha * xValues[i];
         int offset = xIndices[i] * mA;

         for(int j = 0; j < nnz; ++j) {
            int var15 = xIndices[j] + offset;
            Avalues[var15] += multiplier * xValues[j];
         }
      }

   }

   public void gemm(final double alpha, final Matrix A, final DenseMatrix B, final double beta, final DenseMatrix C) {
      .MODULE$.require(!C.isTransposed(), () -> "The matrix C cannot be the product of a transpose() call. C.isTransposed must be false.");
      if (alpha == (double)0.0F && beta == (double)1.0F) {
         this.logDebug((Function0)(() -> "gemm: alpha is equal to 0 and beta is equal to 1. Returning C."));
      } else if (alpha == (double)0.0F) {
         this.getBLAS(C.values().length).dscal(C.values().length, beta, C.values(), 1);
      } else if (A instanceof SparseMatrix) {
         SparseMatrix var10 = (SparseMatrix)A;
         this.gemm(alpha, var10, B, beta, C);
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (A instanceof DenseMatrix) {
         DenseMatrix var11 = (DenseMatrix)A;
         this.gemm(alpha, var11, B, beta, C);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new IllegalArgumentException("gemm doesn't support matrix type " + A.getClass() + ".");
      }
   }

   private void gemm(final double alpha, final DenseMatrix A, final DenseMatrix B, final double beta, final DenseMatrix C) {
      String tAstr = A.isTransposed() ? "T" : "N";
      String tBstr = B.isTransposed() ? "T" : "N";
      int lda = !A.isTransposed() ? A.numRows() : A.numCols();
      int ldb = !B.isTransposed() ? B.numRows() : B.numCols();
      .MODULE$.require(A.numCols() == B.numRows(), () -> {
         int var10000 = A.numCols();
         return "The columns of A don't match the rows of B. A: " + var10000 + ", B: " + B.numRows();
      });
      .MODULE$.require(A.numRows() == C.numRows(), () -> {
         int var10000 = C.numRows();
         return "The rows of C don't match the rows of A. C: " + var10000 + ", A: " + A.numRows();
      });
      .MODULE$.require(B.numCols() == C.numCols(), () -> {
         int var10000 = C.numCols();
         return "The columns of C don't match the columns of B. C: " + var10000 + ", A: " + B.numCols();
      });
      this.nativeBLAS().dgemm(tAstr, tBstr, A.numRows(), B.numCols(), A.numCols(), alpha, A.values(), lda, B.values(), ldb, beta, C.values(), C.numRows());
   }

   private void gemm(final double alpha, final SparseMatrix A, final DenseMatrix B, final double beta, final DenseMatrix C) {
      int mA = A.numRows();
      int nB = B.numCols();
      int kA = A.numCols();
      int kB = B.numRows();
      .MODULE$.require(kA == kB, () -> "The columns of A don't match the rows of B. A: " + kA + ", B: " + kB);
      .MODULE$.require(mA == C.numRows(), () -> {
         int var10000 = C.numRows();
         return "The rows of C don't match the rows of A. C: " + var10000 + ", A: " + mA;
      });
      .MODULE$.require(nB == C.numCols(), () -> {
         int var10000 = C.numCols();
         return "The columns of C don't match the columns of B. C: " + var10000 + ", A: " + nB;
      });
      double[] Avals = A.values();
      double[] Bvals = B.values();
      double[] Cvals = C.values();
      int[] ArowIndices = A.rowIndices();
      int[] AcolPtrs = A.colPtrs();
      if (A.isTransposed()) {
         int colCounterForB = 0;
         if (!B.isTransposed()) {
            while(colCounterForB < nB) {
               int rowCounterForA = 0;
               int Cstart = colCounterForB * mA;

               for(int Bstart = colCounterForB * kA; rowCounterForA < mA; ++rowCounterForA) {
                  int i = AcolPtrs[rowCounterForA];
                  int indEnd = AcolPtrs[rowCounterForA + 1];

                  double sum;
                  for(sum = (double)0.0F; i < indEnd; ++i) {
                     sum += Avals[i] * Bvals[Bstart + ArowIndices[i]];
                  }

                  int Cindex = Cstart + rowCounterForA;
                  Cvals[Cindex] = beta * Cvals[Cindex] + sum * alpha;
               }

               ++colCounterForB;
            }

         } else {
            while(colCounterForB < nB) {
               int rowCounterForA = 0;

               for(int Cstart = colCounterForB * mA; rowCounterForA < mA; ++rowCounterForA) {
                  int i = AcolPtrs[rowCounterForA];
                  int indEnd = AcolPtrs[rowCounterForA + 1];

                  double sum;
                  for(sum = (double)0.0F; i < indEnd; ++i) {
                     sum += Avals[i] * Bvals[colCounterForB + nB * ArowIndices[i]];
                  }

                  int Cindex = Cstart + rowCounterForA;
                  Cvals[Cindex] = beta * Cvals[Cindex] + sum * alpha;
               }

               ++colCounterForB;
            }

         }
      } else {
         if (beta != (double)1.0F) {
            this.getBLAS(C.values().length).dscal(C.values().length, beta, C.values(), 1);
         }

         int colCounterForB = 0;
         if (!B.isTransposed()) {
            while(colCounterForB < nB) {
               int colCounterForA = 0;
               int Bstart = colCounterForB * kB;

               for(int Cstart = colCounterForB * mA; colCounterForA < kA; ++colCounterForA) {
                  int i = AcolPtrs[colCounterForA];
                  int indEnd = AcolPtrs[colCounterForA + 1];

                  for(double Bval = Bvals[Bstart + colCounterForA] * alpha; i < indEnd; ++i) {
                     int var41 = Cstart + ArowIndices[i];
                     Cvals[var41] += Avals[i] * Bval;
                  }
               }

               ++colCounterForB;
            }

         } else {
            while(colCounterForB < nB) {
               int colCounterForA = 0;

               for(int Cstart = colCounterForB * mA; colCounterForA < kA; ++colCounterForA) {
                  int i = AcolPtrs[colCounterForA];
                  int indEnd = AcolPtrs[colCounterForA + 1];

                  for(double Bval = Bvals[colCounterForB + nB * colCounterForA] * alpha; i < indEnd; ++i) {
                     int var48 = Cstart + ArowIndices[i];
                     Cvals[var48] += Avals[i] * Bval;
                  }
               }

               ++colCounterForB;
            }

         }
      }
   }

   public void gemv(final double alpha, final Matrix A, final Vector x, final double beta, final DenseVector y) {
      .MODULE$.require(A.numCols() == x.size(), () -> {
         int var10000 = A.numCols();
         return "The columns of A don't match the number of elements of x. A: " + var10000 + ", x: " + x.size();
      });
      .MODULE$.require(A.numRows() == y.size(), () -> {
         int var10000 = A.numRows();
         return "The rows of A don't match the number of elements of y. A: " + var10000 + ", y:" + y.size();
      });
      if (alpha == (double)0.0F && beta == (double)1.0F) {
         this.logDebug((Function0)(() -> "gemv: alpha is equal to 0 and beta is equal to 1. Returning y."));
      } else if (alpha == (double)0.0F) {
         this.scal(beta, y);
      } else {
         Tuple2 var9 = new Tuple2(A, x);
         if (var9 != null) {
            Matrix smA = (Matrix)var9._1();
            Vector dvx = (Vector)var9._2();
            if (smA instanceof SparseMatrix) {
               SparseMatrix var12 = (SparseMatrix)smA;
               if (dvx instanceof DenseVector) {
                  DenseVector var13 = (DenseVector)dvx;
                  this.gemv(alpha, var12, var13, beta, y);
                  BoxedUnit var28 = BoxedUnit.UNIT;
                  return;
               }
            }
         }

         if (var9 != null) {
            Matrix smA = (Matrix)var9._1();
            Vector svx = (Vector)var9._2();
            if (smA instanceof SparseMatrix) {
               SparseMatrix var16 = (SparseMatrix)smA;
               if (svx instanceof SparseVector) {
                  SparseVector var17 = (SparseVector)svx;
                  this.gemv(alpha, var16, var17, beta, y);
                  BoxedUnit var27 = BoxedUnit.UNIT;
                  return;
               }
            }
         }

         if (var9 != null) {
            Matrix dmA = (Matrix)var9._1();
            Vector dvx = (Vector)var9._2();
            if (dmA instanceof DenseMatrix) {
               DenseMatrix var20 = (DenseMatrix)dmA;
               if (dvx instanceof DenseVector) {
                  DenseVector var21 = (DenseVector)dvx;
                  this.gemv(alpha, var20, var21, beta, y);
                  BoxedUnit var26 = BoxedUnit.UNIT;
                  return;
               }
            }
         }

         if (var9 != null) {
            Matrix dmA = (Matrix)var9._1();
            Vector svx = (Vector)var9._2();
            if (dmA instanceof DenseMatrix) {
               DenseMatrix var24 = (DenseMatrix)dmA;
               if (svx instanceof SparseVector) {
                  SparseVector var25 = (SparseVector)svx;
                  this.gemv(alpha, var24, var25, beta, y);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  return;
               }
            }
         }

         Class var10002 = A.getClass();
         throw new IllegalArgumentException("gemv doesn't support running on matrix type " + var10002 + " and vector type " + x.getClass() + ".");
      }
   }

   private void gemv(final double alpha, final DenseMatrix A, final DenseVector x, final double beta, final DenseVector y) {
      String tStrA = A.isTransposed() ? "T" : "N";
      int mA = !A.isTransposed() ? A.numRows() : A.numCols();
      int nA = !A.isTransposed() ? A.numCols() : A.numRows();
      this.nativeBLAS().dgemv(tStrA, mA, nA, alpha, A.values(), mA, x.values(), 1, beta, y.values(), 1);
   }

   private void gemv(final double alpha, final DenseMatrix A, final SparseVector x, final double beta, final DenseVector y) {
      int mA = A.numRows();
      int nA = A.numCols();
      double[] Avals = A.values();
      int[] xIndices = x.indices();
      int xNnz = xIndices.length;
      double[] xValues = x.values();
      double[] yValues = y.values();
      if (A.isTransposed()) {
         for(int rowCounterForA = 0; rowCounterForA < mA; ++rowCounterForA) {
            double sum = (double)0.0F;

            for(int k = 0; k < xNnz; ++k) {
               sum += xValues[k] * Avals[xIndices[k] + rowCounterForA * nA];
            }

            yValues[rowCounterForA] = sum * alpha + beta * yValues[rowCounterForA];
         }

      } else {
         for(int rowCounterForA = 0; rowCounterForA < mA; ++rowCounterForA) {
            double sum = (double)0.0F;

            for(int k = 0; k < xNnz; ++k) {
               sum += xValues[k] * Avals[xIndices[k] * mA + rowCounterForA];
            }

            yValues[rowCounterForA] = sum * alpha + beta * yValues[rowCounterForA];
         }

      }
   }

   private void gemv(final double alpha, final SparseMatrix A, final SparseVector x, final double beta, final DenseVector y) {
      double[] xValues = x.values();
      int[] xIndices = x.indices();
      int xNnz = xIndices.length;
      double[] yValues = y.values();
      int mA = A.numRows();
      int nA = A.numCols();
      double[] Avals = A.values();
      int[] Arows = !A.isTransposed() ? A.rowIndices() : A.colPtrs();
      int[] Acols = !A.isTransposed() ? A.colPtrs() : A.rowIndices();
      if (A.isTransposed()) {
         for(int rowCounter = 0; rowCounter < mA; ++rowCounter) {
            int i = Arows[rowCounter];
            int indEnd = Arows[rowCounter + 1];
            double sum = (double)0.0F;
            int k = 0;

            while(i < indEnd && k < xNnz) {
               if (xIndices[k] == Acols[i]) {
                  sum += Avals[i] * xValues[k];
                  ++k;
                  ++i;
               } else if (xIndices[k] < Acols[i]) {
                  ++k;
               } else {
                  ++i;
               }
            }

            yValues[rowCounter] = sum * alpha + beta * yValues[rowCounter];
         }

      } else {
         if (beta != (double)1.0F) {
            this.scal(beta, y);
         }

         int colCounterForA = 0;

         for(int k = 0; colCounterForA < nA && k < xNnz; ++colCounterForA) {
            if (xIndices[k] == colCounterForA) {
               int i = Acols[colCounterForA];
               int indEnd = Acols[colCounterForA + 1];

               for(double xTemp = xValues[k] * alpha; i < indEnd; ++i) {
                  int var29 = Arows[i];
                  yValues[var29] += Avals[i] * xTemp;
               }

               ++k;
            }
         }

      }
   }

   private void gemv(final double alpha, final SparseMatrix A, final DenseVector x, final double beta, final DenseVector y) {
      double[] xValues = x.values();
      double[] yValues = y.values();
      int mA = A.numRows();
      int nA = A.numCols();
      double[] Avals = A.values();
      int[] Arows = !A.isTransposed() ? A.rowIndices() : A.colPtrs();
      int[] Acols = !A.isTransposed() ? A.colPtrs() : A.rowIndices();
      if (A.isTransposed()) {
         for(int rowCounter = 0; rowCounter < mA; ++rowCounter) {
            int i = Arows[rowCounter];
            int indEnd = Arows[rowCounter + 1];

            double sum;
            for(sum = (double)0.0F; i < indEnd; ++i) {
               sum += Avals[i] * xValues[Acols[i]];
            }

            yValues[rowCounter] = beta * yValues[rowCounter] + sum * alpha;
         }

      } else {
         if (beta != (double)1.0F) {
            this.scal(beta, y);
         }

         for(int colCounterForA = 0; colCounterForA < nA; ++colCounterForA) {
            int i = Acols[colCounterForA];
            int indEnd = Acols[colCounterForA + 1];

            for(double xVal = xValues[colCounterForA] * alpha; i < indEnd; ++i) {
               int var25 = Arows[i];
               yValues[var25] += Avals[i] * xVal;
            }
         }

      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BLAS$.class);
   }

   // $FF: synthetic method
   private final dev.ludovic.netlib.blas.BLAS liftedTree1$1() {
      Object var10000;
      try {
         var10000 = NativeBLAS.getInstance();
      } catch (Throwable var1) {
         var10000 = this.javaBLAS();
      }

      return (dev.ludovic.netlib.blas.BLAS)var10000;
   }

   private BLAS$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
