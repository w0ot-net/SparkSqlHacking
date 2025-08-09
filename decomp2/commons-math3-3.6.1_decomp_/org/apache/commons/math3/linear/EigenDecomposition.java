package org.apache.commons.math3.linear;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class EigenDecomposition {
   private static final double EPSILON = 1.0E-12;
   private byte maxIter;
   private double[] main;
   private double[] secondary;
   private TriDiagonalTransformer transformer;
   private double[] realEigenvalues;
   private double[] imagEigenvalues;
   private ArrayRealVector[] eigenvectors;
   private RealMatrix cachedV;
   private RealMatrix cachedD;
   private RealMatrix cachedVt;
   private final boolean isSymmetric;

   public EigenDecomposition(RealMatrix matrix) throws MathArithmeticException {
      this.maxIter = 30;
      double symTol = (double)(10 * matrix.getRowDimension() * matrix.getColumnDimension()) * Precision.EPSILON;
      this.isSymmetric = MatrixUtils.isSymmetric(matrix, symTol);
      if (this.isSymmetric) {
         this.transformToTridiagonal(matrix);
         this.findEigenVectors(this.transformer.getQ().getData());
      } else {
         SchurTransformer t = this.transformToSchur(matrix);
         this.findEigenVectorsFromSchur(t);
      }

   }

   /** @deprecated */
   @Deprecated
   public EigenDecomposition(RealMatrix matrix, double splitTolerance) throws MathArithmeticException {
      this(matrix);
   }

   public EigenDecomposition(double[] main, double[] secondary) {
      this.maxIter = 30;
      this.isSymmetric = true;
      this.main = (double[])(([D)main).clone();
      this.secondary = (double[])(([D)secondary).clone();
      this.transformer = null;
      int size = main.length;
      double[][] z = new double[size][size];

      for(int i = 0; i < size; ++i) {
         z[i][i] = (double)1.0F;
      }

      this.findEigenVectors(z);
   }

   /** @deprecated */
   @Deprecated
   public EigenDecomposition(double[] main, double[] secondary, double splitTolerance) {
      this(main, secondary);
   }

   public RealMatrix getV() {
      if (this.cachedV == null) {
         int m = this.eigenvectors.length;
         this.cachedV = MatrixUtils.createRealMatrix(m, m);

         for(int k = 0; k < m; ++k) {
            this.cachedV.setColumnVector(k, this.eigenvectors[k]);
         }
      }

      return this.cachedV;
   }

   public RealMatrix getD() {
      if (this.cachedD == null) {
         this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);

         for(int i = 0; i < this.imagEigenvalues.length; ++i) {
            if (Precision.compareTo(this.imagEigenvalues[i], (double)0.0F, 1.0E-12) > 0) {
               this.cachedD.setEntry(i, i + 1, this.imagEigenvalues[i]);
            } else if (Precision.compareTo(this.imagEigenvalues[i], (double)0.0F, 1.0E-12) < 0) {
               this.cachedD.setEntry(i, i - 1, this.imagEigenvalues[i]);
            }
         }
      }

      return this.cachedD;
   }

   public RealMatrix getVT() {
      if (this.cachedVt == null) {
         int m = this.eigenvectors.length;
         this.cachedVt = MatrixUtils.createRealMatrix(m, m);

         for(int k = 0; k < m; ++k) {
            this.cachedVt.setRowVector(k, this.eigenvectors[k]);
         }
      }

      return this.cachedVt;
   }

   public boolean hasComplexEigenvalues() {
      for(int i = 0; i < this.imagEigenvalues.length; ++i) {
         if (!Precision.equals(this.imagEigenvalues[i], (double)0.0F, 1.0E-12)) {
            return true;
         }
      }

      return false;
   }

   public double[] getRealEigenvalues() {
      return (double[])this.realEigenvalues.clone();
   }

   public double getRealEigenvalue(int i) {
      return this.realEigenvalues[i];
   }

   public double[] getImagEigenvalues() {
      return (double[])this.imagEigenvalues.clone();
   }

   public double getImagEigenvalue(int i) {
      return this.imagEigenvalues[i];
   }

   public RealVector getEigenvector(int i) {
      return this.eigenvectors[i].copy();
   }

   public double getDeterminant() {
      double determinant = (double)1.0F;

      for(double lambda : this.realEigenvalues) {
         determinant *= lambda;
      }

      return determinant;
   }

   public RealMatrix getSquareRoot() {
      if (!this.isSymmetric) {
         throw new MathUnsupportedOperationException();
      } else {
         double[] sqrtEigenValues = new double[this.realEigenvalues.length];

         for(int i = 0; i < this.realEigenvalues.length; ++i) {
            double eigen = this.realEigenvalues[i];
            if (eigen <= (double)0.0F) {
               throw new MathUnsupportedOperationException();
            }

            sqrtEigenValues[i] = FastMath.sqrt(eigen);
         }

         RealMatrix sqrtEigen = MatrixUtils.createRealDiagonalMatrix(sqrtEigenValues);
         RealMatrix v = this.getV();
         RealMatrix vT = this.getVT();
         return v.multiply(sqrtEigen).multiply(vT);
      }
   }

   public DecompositionSolver getSolver() {
      if (this.hasComplexEigenvalues()) {
         throw new MathUnsupportedOperationException();
      } else {
         return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
      }
   }

   private void transformToTridiagonal(RealMatrix matrix) {
      this.transformer = new TriDiagonalTransformer(matrix);
      this.main = this.transformer.getMainDiagonalRef();
      this.secondary = this.transformer.getSecondaryDiagonalRef();
   }

   private void findEigenVectors(double[][] householderMatrix) {
      double[][] z = (double[][])(([[D)householderMatrix).clone();
      int n = this.main.length;
      this.realEigenvalues = new double[n];
      this.imagEigenvalues = new double[n];
      double[] e = new double[n];

      for(int i = 0; i < n - 1; ++i) {
         this.realEigenvalues[i] = this.main[i];
         e[i] = this.secondary[i];
      }

      this.realEigenvalues[n - 1] = this.main[n - 1];
      e[n - 1] = (double)0.0F;
      double maxAbsoluteValue = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         if (FastMath.abs(this.realEigenvalues[i]) > maxAbsoluteValue) {
            maxAbsoluteValue = FastMath.abs(this.realEigenvalues[i]);
         }

         if (FastMath.abs(e[i]) > maxAbsoluteValue) {
            maxAbsoluteValue = FastMath.abs(e[i]);
         }
      }

      if (maxAbsoluteValue != (double)0.0F) {
         for(int i = 0; i < n; ++i) {
            if (FastMath.abs(this.realEigenvalues[i]) <= Precision.EPSILON * maxAbsoluteValue) {
               this.realEigenvalues[i] = (double)0.0F;
            }

            if (FastMath.abs(e[i]) <= Precision.EPSILON * maxAbsoluteValue) {
               e[i] = (double)0.0F;
            }
         }
      }

      for(int j = 0; j < n; ++j) {
         int its = 0;

         while(true) {
            int m;
            for(m = j; m < n - 1; ++m) {
               double delta = FastMath.abs(this.realEigenvalues[m]) + FastMath.abs(this.realEigenvalues[m + 1]);
               if (FastMath.abs(e[m]) + delta == delta) {
                  break;
               }
            }

            if (m != j) {
               if (its == this.maxIter) {
                  throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, this.maxIter, new Object[0]);
               }

               ++its;
               double q = (this.realEigenvalues[j + 1] - this.realEigenvalues[j]) / ((double)2.0F * e[j]);
               double t = FastMath.sqrt((double)1.0F + q * q);
               if (q < (double)0.0F) {
                  q = this.realEigenvalues[m] - this.realEigenvalues[j] + e[j] / (q - t);
               } else {
                  q = this.realEigenvalues[m] - this.realEigenvalues[j] + e[j] / (q + t);
               }

               double u = (double)0.0F;
               double s = (double)1.0F;
               double c = (double)1.0F;

               int i;
               for(i = m - 1; i >= j; --i) {
                  double p = s * e[i];
                  double h = c * e[i];
                  if (FastMath.abs(p) >= FastMath.abs(q)) {
                     c = q / p;
                     t = FastMath.sqrt(c * c + (double)1.0F);
                     e[i + 1] = p * t;
                     s = (double)1.0F / t;
                     c *= s;
                  } else {
                     s = p / q;
                     t = FastMath.sqrt(s * s + (double)1.0F);
                     e[i + 1] = q * t;
                     c = (double)1.0F / t;
                     s *= c;
                  }

                  if (e[i + 1] == (double)0.0F) {
                     double[] var10000 = this.realEigenvalues;
                     var10000[i + 1] -= u;
                     e[m] = (double)0.0F;
                     break;
                  }

                  q = this.realEigenvalues[i + 1] - u;
                  t = (this.realEigenvalues[i] - q) * s + (double)2.0F * c * h;
                  u = s * t;
                  this.realEigenvalues[i + 1] = q + u;
                  q = c * t - h;

                  for(int ia = 0; ia < n; ++ia) {
                     p = z[ia][i + 1];
                     z[ia][i + 1] = s * z[ia][i] + c * p;
                     z[ia][i] = c * z[ia][i] - s * p;
                  }
               }

               if (t != (double)0.0F || i < j) {
                  double[] var46 = this.realEigenvalues;
                  var46[j] -= u;
                  e[j] = q;
                  e[m] = (double)0.0F;
               }
            }

            if (m == j) {
               break;
            }
         }
      }

      for(int i = 0; i < n; ++i) {
         int k = i;
         double p = this.realEigenvalues[i];

         for(int j = i + 1; j < n; ++j) {
            if (this.realEigenvalues[j] > p) {
               k = j;
               p = this.realEigenvalues[j];
            }
         }

         if (k != i) {
            this.realEigenvalues[k] = this.realEigenvalues[i];
            this.realEigenvalues[i] = p;

            for(int j = 0; j < n; ++j) {
               p = z[j][i];
               z[j][i] = z[j][k];
               z[j][k] = p;
            }
         }
      }

      maxAbsoluteValue = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         if (FastMath.abs(this.realEigenvalues[i]) > maxAbsoluteValue) {
            maxAbsoluteValue = FastMath.abs(this.realEigenvalues[i]);
         }
      }

      if (maxAbsoluteValue != (double)0.0F) {
         for(int i = 0; i < n; ++i) {
            if (FastMath.abs(this.realEigenvalues[i]) < Precision.EPSILON * maxAbsoluteValue) {
               this.realEigenvalues[i] = (double)0.0F;
            }
         }
      }

      this.eigenvectors = new ArrayRealVector[n];
      double[] tmp = new double[n];

      for(int i = 0; i < n; ++i) {
         for(int j = 0; j < n; ++j) {
            tmp[j] = z[j][i];
         }

         this.eigenvectors[i] = new ArrayRealVector(tmp);
      }

   }

   private SchurTransformer transformToSchur(RealMatrix matrix) {
      SchurTransformer schurTransform = new SchurTransformer(matrix);
      double[][] matT = schurTransform.getT().getData();
      this.realEigenvalues = new double[matT.length];
      this.imagEigenvalues = new double[matT.length];

      for(int i = 0; i < this.realEigenvalues.length; ++i) {
         if (i != this.realEigenvalues.length - 1 && !Precision.equals(matT[i + 1][i], (double)0.0F, 1.0E-12)) {
            double x = matT[i + 1][i + 1];
            double p = (double)0.5F * (matT[i][i] - x);
            double z = FastMath.sqrt(FastMath.abs(p * p + matT[i + 1][i] * matT[i][i + 1]));
            this.realEigenvalues[i] = x + p;
            this.imagEigenvalues[i] = z;
            this.realEigenvalues[i + 1] = x + p;
            this.imagEigenvalues[i + 1] = -z;
            ++i;
         } else {
            this.realEigenvalues[i] = matT[i][i];
         }
      }

      return schurTransform;
   }

   private Complex cdiv(double xr, double xi, double yr, double yi) {
      return (new Complex(xr, xi)).divide(new Complex(yr, yi));
   }

   private void findEigenVectorsFromSchur(SchurTransformer schur) throws MathArithmeticException {
      double[][] matrixT = schur.getT().getData();
      double[][] matrixP = schur.getP().getData();
      int n = matrixT.length;
      double norm = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         for(int j = FastMath.max(i - 1, 0); j < n; ++j) {
            norm += FastMath.abs(matrixT[i][j]);
         }
      }

      if (Precision.equals(norm, (double)0.0F, 1.0E-12)) {
         throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
      } else {
         double r = (double)0.0F;
         double s = (double)0.0F;
         double z = (double)0.0F;

         for(int idx = n - 1; idx >= 0; --idx) {
            double p = this.realEigenvalues[idx];
            double q = this.imagEigenvalues[idx];
            if (Precision.equals(q, (double)0.0F)) {
               int l = idx;
               matrixT[idx][idx] = (double)1.0F;

               for(int i = idx - 1; i >= 0; --i) {
                  double w = matrixT[i][i] - p;
                  r = (double)0.0F;

                  for(int j = l; j <= idx; ++j) {
                     r += matrixT[i][j] * matrixT[j][idx];
                  }

                  if (Precision.compareTo(this.imagEigenvalues[i], (double)0.0F, 1.0E-12) < 0) {
                     z = w;
                     s = r;
                  } else {
                     l = i;
                     if (Precision.equals(this.imagEigenvalues[i], (double)0.0F)) {
                        if (w != (double)0.0F) {
                           matrixT[i][idx] = -r / w;
                        } else {
                           matrixT[i][idx] = -r / (Precision.EPSILON * norm);
                        }
                     } else {
                        double x = matrixT[i][i + 1];
                        double y = matrixT[i + 1][i];
                        q = (this.realEigenvalues[i] - p) * (this.realEigenvalues[i] - p) + this.imagEigenvalues[i] * this.imagEigenvalues[i];
                        double t = (x * s - z * r) / q;
                        matrixT[i][idx] = t;
                        if (FastMath.abs(x) > FastMath.abs(z)) {
                           matrixT[i + 1][idx] = (-r - w * t) / x;
                        } else {
                           matrixT[i + 1][idx] = (-s - y * t) / z;
                        }
                     }

                     double t = FastMath.abs(matrixT[i][idx]);
                     if (Precision.EPSILON * t * t > (double)1.0F) {
                        for(int j = i; j <= idx; ++j) {
                           matrixT[j][idx] /= t;
                        }
                     }
                  }
               }
            } else if (q < (double)0.0F) {
               int l = idx - 1;
               if (FastMath.abs(matrixT[idx][idx - 1]) > FastMath.abs(matrixT[idx - 1][idx])) {
                  matrixT[idx - 1][idx - 1] = q / matrixT[idx][idx - 1];
                  matrixT[idx - 1][idx] = -(matrixT[idx][idx] - p) / matrixT[idx][idx - 1];
               } else {
                  Complex result = this.cdiv((double)0.0F, -matrixT[idx - 1][idx], matrixT[idx - 1][idx - 1] - p, q);
                  matrixT[idx - 1][idx - 1] = result.getReal();
                  matrixT[idx - 1][idx] = result.getImaginary();
               }

               matrixT[idx][idx - 1] = (double)0.0F;
               matrixT[idx][idx] = (double)1.0F;

               for(int i = idx - 2; i >= 0; --i) {
                  double ra = (double)0.0F;
                  double sa = (double)0.0F;

                  for(int j = l; j <= idx; ++j) {
                     ra += matrixT[i][j] * matrixT[j][idx - 1];
                     sa += matrixT[i][j] * matrixT[j][idx];
                  }

                  double w = matrixT[i][i] - p;
                  if (Precision.compareTo(this.imagEigenvalues[i], (double)0.0F, 1.0E-12) < 0) {
                     z = w;
                     r = ra;
                     s = sa;
                  } else {
                     l = i;
                     if (Precision.equals(this.imagEigenvalues[i], (double)0.0F)) {
                        Complex c = this.cdiv(-ra, -sa, w, q);
                        matrixT[i][idx - 1] = c.getReal();
                        matrixT[i][idx] = c.getImaginary();
                     } else {
                        double x = matrixT[i][i + 1];
                        double y = matrixT[i + 1][i];
                        double vr = (this.realEigenvalues[i] - p) * (this.realEigenvalues[i] - p) + this.imagEigenvalues[i] * this.imagEigenvalues[i] - q * q;
                        double vi = (this.realEigenvalues[i] - p) * (double)2.0F * q;
                        if (Precision.equals(vr, (double)0.0F) && Precision.equals(vi, (double)0.0F)) {
                           vr = Precision.EPSILON * norm * (FastMath.abs(w) + FastMath.abs(q) + FastMath.abs(x) + FastMath.abs(y) + FastMath.abs(z));
                        }

                        Complex c = this.cdiv(x * r - z * ra + q * sa, x * s - z * sa - q * ra, vr, vi);
                        matrixT[i][idx - 1] = c.getReal();
                        matrixT[i][idx] = c.getImaginary();
                        if (FastMath.abs(x) > FastMath.abs(z) + FastMath.abs(q)) {
                           matrixT[i + 1][idx - 1] = (-ra - w * matrixT[i][idx - 1] + q * matrixT[i][idx]) / x;
                           matrixT[i + 1][idx] = (-sa - w * matrixT[i][idx] - q * matrixT[i][idx - 1]) / x;
                        } else {
                           Complex c2 = this.cdiv(-r - y * matrixT[i][idx - 1], -s - y * matrixT[i][idx], z, q);
                           matrixT[i + 1][idx - 1] = c2.getReal();
                           matrixT[i + 1][idx] = c2.getImaginary();
                        }
                     }

                     double t = FastMath.max(FastMath.abs(matrixT[i][idx - 1]), FastMath.abs(matrixT[i][idx]));
                     if (Precision.EPSILON * t * t > (double)1.0F) {
                        for(int j = i; j <= idx; ++j) {
                           matrixT[j][idx - 1] /= t;
                           matrixT[j][idx] /= t;
                        }
                     }
                  }
               }
            }
         }

         for(int j = n - 1; j >= 0; --j) {
            for(int i = 0; i <= n - 1; ++i) {
               z = (double)0.0F;

               for(int k = 0; k <= FastMath.min(j, n - 1); ++k) {
                  z += matrixP[i][k] * matrixT[k][j];
               }

               matrixP[i][j] = z;
            }
         }

         this.eigenvectors = new ArrayRealVector[n];
         double[] tmp = new double[n];

         for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
               tmp[j] = matrixP[j][i];
            }

            this.eigenvectors[i] = new ArrayRealVector(tmp);
         }

      }
   }

   private static class Solver implements DecompositionSolver {
      private double[] realEigenvalues;
      private double[] imagEigenvalues;
      private final ArrayRealVector[] eigenvectors;

      private Solver(double[] realEigenvalues, double[] imagEigenvalues, ArrayRealVector[] eigenvectors) {
         this.realEigenvalues = realEigenvalues;
         this.imagEigenvalues = imagEigenvalues;
         this.eigenvectors = eigenvectors;
      }

      public RealVector solve(RealVector b) {
         if (!this.isNonSingular()) {
            throw new SingularMatrixException();
         } else {
            int m = this.realEigenvalues.length;
            if (b.getDimension() != m) {
               throw new DimensionMismatchException(b.getDimension(), m);
            } else {
               double[] bp = new double[m];

               for(int i = 0; i < m; ++i) {
                  ArrayRealVector v = this.eigenvectors[i];
                  double[] vData = v.getDataRef();
                  double s = v.dotProduct(b) / this.realEigenvalues[i];

                  for(int j = 0; j < m; ++j) {
                     bp[j] += s * vData[j];
                  }
               }

               return new ArrayRealVector(bp, false);
            }
         }
      }

      public RealMatrix solve(RealMatrix b) {
         if (!this.isNonSingular()) {
            throw new SingularMatrixException();
         } else {
            int m = this.realEigenvalues.length;
            if (b.getRowDimension() != m) {
               throw new DimensionMismatchException(b.getRowDimension(), m);
            } else {
               int nColB = b.getColumnDimension();
               double[][] bp = new double[m][nColB];
               double[] tmpCol = new double[m];

               for(int k = 0; k < nColB; ++k) {
                  for(int i = 0; i < m; ++i) {
                     tmpCol[i] = b.getEntry(i, k);
                     bp[i][k] = (double)0.0F;
                  }

                  for(int i = 0; i < m; ++i) {
                     ArrayRealVector v = this.eigenvectors[i];
                     double[] vData = v.getDataRef();
                     double s = (double)0.0F;

                     for(int j = 0; j < m; ++j) {
                        s += v.getEntry(j) * tmpCol[j];
                     }

                     s /= this.realEigenvalues[i];

                     for(int j = 0; j < m; ++j) {
                        bp[j][k] += s * vData[j];
                     }
                  }
               }

               return new Array2DRowRealMatrix(bp, false);
            }
         }
      }

      public boolean isNonSingular() {
         double largestEigenvalueNorm = (double)0.0F;

         for(int i = 0; i < this.realEigenvalues.length; ++i) {
            largestEigenvalueNorm = FastMath.max(largestEigenvalueNorm, this.eigenvalueNorm(i));
         }

         if (largestEigenvalueNorm == (double)0.0F) {
            return false;
         } else {
            for(int i = 0; i < this.realEigenvalues.length; ++i) {
               if (Precision.equals(this.eigenvalueNorm(i) / largestEigenvalueNorm, (double)0.0F, 1.0E-12)) {
                  return false;
               }
            }

            return true;
         }
      }

      private double eigenvalueNorm(int i) {
         double re = this.realEigenvalues[i];
         double im = this.imagEigenvalues[i];
         return FastMath.sqrt(re * re + im * im);
      }

      public RealMatrix getInverse() {
         if (!this.isNonSingular()) {
            throw new SingularMatrixException();
         } else {
            int m = this.realEigenvalues.length;
            double[][] invData = new double[m][m];

            for(int i = 0; i < m; ++i) {
               double[] invI = invData[i];

               for(int j = 0; j < m; ++j) {
                  double invIJ = (double)0.0F;

                  for(int k = 0; k < m; ++k) {
                     double[] vK = this.eigenvectors[k].getDataRef();
                     invIJ += vK[i] * vK[j] / this.realEigenvalues[k];
                  }

                  invI[j] = invIJ;
               }
            }

            return MatrixUtils.createRealMatrix(invData);
         }
      }
   }
}
