package org.apache.commons.math3.linear;

import org.apache.commons.math3.util.FastMath;

class BiDiagonalTransformer {
   private final double[][] householderVectors;
   private final double[] main;
   private final double[] secondary;
   private RealMatrix cachedU;
   private RealMatrix cachedB;
   private RealMatrix cachedV;

   BiDiagonalTransformer(RealMatrix matrix) {
      int m = matrix.getRowDimension();
      int n = matrix.getColumnDimension();
      int p = FastMath.min(m, n);
      this.householderVectors = matrix.getData();
      this.main = new double[p];
      this.secondary = new double[p - 1];
      this.cachedU = null;
      this.cachedB = null;
      this.cachedV = null;
      if (m >= n) {
         this.transformToUpperBiDiagonal();
      } else {
         this.transformToLowerBiDiagonal();
      }

   }

   public RealMatrix getU() {
      if (this.cachedU == null) {
         int m = this.householderVectors.length;
         int n = this.householderVectors[0].length;
         int p = this.main.length;
         int diagOffset = m >= n ? 0 : 1;
         double[] diagonal = m >= n ? this.main : this.secondary;
         double[][] ua = new double[m][m];

         for(int k = m - 1; k >= p; --k) {
            ua[k][k] = (double)1.0F;
         }

         for(int k = p - 1; k >= diagOffset; --k) {
            double[] hK = this.householderVectors[k];
            ua[k][k] = (double)1.0F;
            if (hK[k - diagOffset] != (double)0.0F) {
               for(int j = k; j < m; ++j) {
                  double alpha = (double)0.0F;

                  for(int i = k; i < m; ++i) {
                     alpha -= ua[i][j] * this.householderVectors[i][k - diagOffset];
                  }

                  alpha /= diagonal[k - diagOffset] * hK[k - diagOffset];

                  for(int i = k; i < m; ++i) {
                     ua[i][j] += -alpha * this.householderVectors[i][k - diagOffset];
                  }
               }
            }
         }

         if (diagOffset > 0) {
            ua[0][0] = (double)1.0F;
         }

         this.cachedU = MatrixUtils.createRealMatrix(ua);
      }

      return this.cachedU;
   }

   public RealMatrix getB() {
      if (this.cachedB == null) {
         int m = this.householderVectors.length;
         int n = this.householderVectors[0].length;
         double[][] ba = new double[m][n];

         for(int i = 0; i < this.main.length; ++i) {
            ba[i][i] = this.main[i];
            if (m < n) {
               if (i > 0) {
                  ba[i][i - 1] = this.secondary[i - 1];
               }
            } else if (i < this.main.length - 1) {
               ba[i][i + 1] = this.secondary[i];
            }
         }

         this.cachedB = MatrixUtils.createRealMatrix(ba);
      }

      return this.cachedB;
   }

   public RealMatrix getV() {
      if (this.cachedV == null) {
         int m = this.householderVectors.length;
         int n = this.householderVectors[0].length;
         int p = this.main.length;
         int diagOffset = m >= n ? 1 : 0;
         double[] diagonal = m >= n ? this.secondary : this.main;
         double[][] va = new double[n][n];

         for(int k = n - 1; k >= p; --k) {
            va[k][k] = (double)1.0F;
         }

         for(int k = p - 1; k >= diagOffset; --k) {
            double[] hK = this.householderVectors[k - diagOffset];
            va[k][k] = (double)1.0F;
            if (hK[k] != (double)0.0F) {
               for(int j = k; j < n; ++j) {
                  double beta = (double)0.0F;

                  for(int i = k; i < n; ++i) {
                     beta -= va[i][j] * hK[i];
                  }

                  beta /= diagonal[k - diagOffset] * hK[k];

                  for(int i = k; i < n; ++i) {
                     va[i][j] += -beta * hK[i];
                  }
               }
            }
         }

         if (diagOffset > 0) {
            va[0][0] = (double)1.0F;
         }

         this.cachedV = MatrixUtils.createRealMatrix(va);
      }

      return this.cachedV;
   }

   double[][] getHouseholderVectorsRef() {
      return this.householderVectors;
   }

   double[] getMainDiagonalRef() {
      return this.main;
   }

   double[] getSecondaryDiagonalRef() {
      return this.secondary;
   }

   boolean isUpperBiDiagonal() {
      return this.householderVectors.length >= this.householderVectors[0].length;
   }

   private void transformToUpperBiDiagonal() {
      int m = this.householderVectors.length;
      int n = this.householderVectors[0].length;

      for(int k = 0; k < n; ++k) {
         double xNormSqr = (double)0.0F;

         for(int i = k; i < m; ++i) {
            double c = this.householderVectors[i][k];
            xNormSqr += c * c;
         }

         double[] hK = this.householderVectors[k];
         double a = hK[k] > (double)0.0F ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
         this.main[k] = a;
         if (a != (double)0.0F) {
            hK[k] -= a;

            for(int j = k + 1; j < n; ++j) {
               double alpha = (double)0.0F;

               for(int i = k; i < m; ++i) {
                  double[] hI = this.householderVectors[i];
                  alpha -= hI[j] * hI[k];
               }

               alpha /= a * this.householderVectors[k][k];

               for(int i = k; i < m; ++i) {
                  double[] hI = this.householderVectors[i];
                  hI[j] -= alpha * hI[k];
               }
            }
         }

         if (k < n - 1) {
            xNormSqr = (double)0.0F;

            for(int j = k + 1; j < n; ++j) {
               double c = hK[j];
               xNormSqr += c * c;
            }

            double b = hK[k + 1] > (double)0.0F ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            this.secondary[k] = b;
            if (b != (double)0.0F) {
               hK[k + 1] -= b;

               for(int i = k + 1; i < m; ++i) {
                  double[] hI = this.householderVectors[i];
                  double beta = (double)0.0F;

                  for(int j = k + 1; j < n; ++j) {
                     beta -= hI[j] * hK[j];
                  }

                  beta /= b * hK[k + 1];

                  for(int j = k + 1; j < n; ++j) {
                     hI[j] -= beta * hK[j];
                  }
               }
            }
         }
      }

   }

   private void transformToLowerBiDiagonal() {
      int m = this.householderVectors.length;
      int n = this.householderVectors[0].length;

      for(int k = 0; k < m; ++k) {
         double[] hK = this.householderVectors[k];
         double xNormSqr = (double)0.0F;

         for(int j = k; j < n; ++j) {
            double c = hK[j];
            xNormSqr += c * c;
         }

         double a = hK[k] > (double)0.0F ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
         this.main[k] = a;
         if (a != (double)0.0F) {
            hK[k] -= a;

            for(int i = k + 1; i < m; ++i) {
               double[] hI = this.householderVectors[i];
               double alpha = (double)0.0F;

               for(int j = k; j < n; ++j) {
                  alpha -= hI[j] * hK[j];
               }

               alpha /= a * this.householderVectors[k][k];

               for(int j = k; j < n; ++j) {
                  hI[j] -= alpha * hK[j];
               }
            }
         }

         if (k < m - 1) {
            double[] hKp1 = this.householderVectors[k + 1];
            xNormSqr = (double)0.0F;

            for(int i = k + 1; i < m; ++i) {
               double c = this.householderVectors[i][k];
               xNormSqr += c * c;
            }

            double b = hKp1[k] > (double)0.0F ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            this.secondary[k] = b;
            if (b != (double)0.0F) {
               hKp1[k] -= b;

               for(int j = k + 1; j < n; ++j) {
                  double beta = (double)0.0F;

                  for(int i = k + 1; i < m; ++i) {
                     double[] hI = this.householderVectors[i];
                     beta -= hI[j] * hI[k];
                  }

                  beta /= b * hKp1[k];

                  for(int i = k + 1; i < m; ++i) {
                     double[] hI = this.householderVectors[i];
                     hI[j] -= beta * hI[k];
                  }
               }
            }
         }
      }

   }
}
