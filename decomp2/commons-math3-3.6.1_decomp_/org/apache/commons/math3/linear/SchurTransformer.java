package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class SchurTransformer {
   private static final int MAX_ITERATIONS = 100;
   private final double[][] matrixP;
   private final double[][] matrixT;
   private RealMatrix cachedP;
   private RealMatrix cachedT;
   private RealMatrix cachedPt;
   private final double epsilon;

   SchurTransformer(RealMatrix matrix) {
      this.epsilon = Precision.EPSILON;
      if (!matrix.isSquare()) {
         throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
      } else {
         HessenbergTransformer transformer = new HessenbergTransformer(matrix);
         this.matrixT = transformer.getH().getData();
         this.matrixP = transformer.getP().getData();
         this.cachedT = null;
         this.cachedP = null;
         this.cachedPt = null;
         this.transform();
      }
   }

   public RealMatrix getP() {
      if (this.cachedP == null) {
         this.cachedP = MatrixUtils.createRealMatrix(this.matrixP);
      }

      return this.cachedP;
   }

   public RealMatrix getPT() {
      if (this.cachedPt == null) {
         this.cachedPt = this.getP().transpose();
      }

      return this.cachedPt;
   }

   public RealMatrix getT() {
      if (this.cachedT == null) {
         this.cachedT = MatrixUtils.createRealMatrix(this.matrixT);
      }

      return this.cachedT;
   }

   private void transform() {
      int n = this.matrixT.length;
      double norm = this.getNorm();
      ShiftInfo shift = new ShiftInfo();
      int iteration = 0;
      int iu = n - 1;

      while(iu >= 0) {
         int il = this.findSmallSubDiagonalElement(iu, norm);
         if (il == iu) {
            double[] var33 = this.matrixT[iu];
            var33[iu] += shift.exShift;
            --iu;
            iteration = 0;
         } else if (il != iu - 1) {
            this.computeShift(il, iu, iteration, shift);
            ++iteration;
            if (iteration > 100) {
               throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, 100, new Object[0]);
            }

            double[] hVec = new double[3];
            int im = this.initQRStep(il, iu, shift, hVec);
            this.performDoubleQRStep(il, im, iu, shift, hVec);
         } else {
            double p = (this.matrixT[iu - 1][iu - 1] - this.matrixT[iu][iu]) / (double)2.0F;
            double q = p * p + this.matrixT[iu][iu - 1] * this.matrixT[iu - 1][iu];
            double[] var10000 = this.matrixT[iu];
            var10000[iu] += shift.exShift;
            var10000 = this.matrixT[iu - 1];
            var10000[iu - 1] += shift.exShift;
            if (q >= (double)0.0F) {
               double z = FastMath.sqrt(FastMath.abs(q));
               if (p >= (double)0.0F) {
                  z = p + z;
               } else {
                  z = p - z;
               }

               double x = this.matrixT[iu][iu - 1];
               double s = FastMath.abs(x) + FastMath.abs(z);
               p = x / s;
               q = z / s;
               double r = FastMath.sqrt(p * p + q * q);
               p /= r;
               q /= r;

               for(int j = iu - 1; j < n; ++j) {
                  z = this.matrixT[iu - 1][j];
                  this.matrixT[iu - 1][j] = q * z + p * this.matrixT[iu][j];
                  this.matrixT[iu][j] = q * this.matrixT[iu][j] - p * z;
               }

               for(int i = 0; i <= iu; ++i) {
                  z = this.matrixT[i][iu - 1];
                  this.matrixT[i][iu - 1] = q * z + p * this.matrixT[i][iu];
                  this.matrixT[i][iu] = q * this.matrixT[i][iu] - p * z;
               }

               for(int i = 0; i <= n - 1; ++i) {
                  z = this.matrixP[i][iu - 1];
                  this.matrixP[i][iu - 1] = q * z + p * this.matrixP[i][iu];
                  this.matrixP[i][iu] = q * this.matrixP[i][iu] - p * z;
               }
            }

            iu -= 2;
            iteration = 0;
         }
      }

   }

   private double getNorm() {
      double norm = (double)0.0F;

      for(int i = 0; i < this.matrixT.length; ++i) {
         for(int j = FastMath.max(i - 1, 0); j < this.matrixT.length; ++j) {
            norm += FastMath.abs(this.matrixT[i][j]);
         }
      }

      return norm;
   }

   private int findSmallSubDiagonalElement(int startIdx, double norm) {
      int l;
      for(l = startIdx; l > 0; --l) {
         double s = FastMath.abs(this.matrixT[l - 1][l - 1]) + FastMath.abs(this.matrixT[l][l]);
         if (s == (double)0.0F) {
            s = norm;
         }

         if (FastMath.abs(this.matrixT[l][l - 1]) < this.epsilon * s) {
            break;
         }
      }

      return l;
   }

   private void computeShift(int l, int idx, int iteration, ShiftInfo shift) {
      shift.x = this.matrixT[idx][idx];
      shift.y = shift.w = (double)0.0F;
      if (l < idx) {
         shift.y = this.matrixT[idx - 1][idx - 1];
         shift.w = this.matrixT[idx][idx - 1] * this.matrixT[idx - 1][idx];
      }

      if (iteration == 10) {
         shift.exShift += shift.x;

         for(int i = 0; i <= idx; ++i) {
            double[] var10000 = this.matrixT[i];
            var10000[i] -= shift.x;
         }

         double s = FastMath.abs(this.matrixT[idx][idx - 1]) + FastMath.abs(this.matrixT[idx - 1][idx - 2]);
         shift.x = (double)0.75F * s;
         shift.y = (double)0.75F * s;
         shift.w = (double)-0.4375F * s * s;
      }

      if (iteration == 30) {
         double s = (shift.y - shift.x) / (double)2.0F;
         s = s * s + shift.w;
         if (s > (double)0.0F) {
            s = FastMath.sqrt(s);
            if (shift.y < shift.x) {
               s = -s;
            }

            s = shift.x - shift.w / ((shift.y - shift.x) / (double)2.0F + s);

            for(int i = 0; i <= idx; ++i) {
               double[] var13 = this.matrixT[i];
               var13[i] -= s;
            }

            shift.exShift += s;
            shift.x = shift.y = shift.w = 0.964;
         }
      }

   }

   private int initQRStep(int il, int iu, ShiftInfo shift, double[] hVec) {
      int im;
      for(im = iu - 2; im >= il; --im) {
         double z = this.matrixT[im][im];
         double r = shift.x - z;
         double s = shift.y - z;
         hVec[0] = (r * s - shift.w) / this.matrixT[im + 1][im] + this.matrixT[im][im + 1];
         hVec[1] = this.matrixT[im + 1][im + 1] - z - r - s;
         hVec[2] = this.matrixT[im + 2][im + 1];
         if (im == il) {
            break;
         }

         double lhs = FastMath.abs(this.matrixT[im][im - 1]) * (FastMath.abs(hVec[1]) + FastMath.abs(hVec[2]));
         double rhs = FastMath.abs(hVec[0]) * (FastMath.abs(this.matrixT[im - 1][im - 1]) + FastMath.abs(z) + FastMath.abs(this.matrixT[im + 1][im + 1]));
         if (lhs < this.epsilon * rhs) {
            break;
         }
      }

      return im;
   }

   private void performDoubleQRStep(int il, int im, int iu, ShiftInfo shift, double[] hVec) {
      int n = this.matrixT.length;
      double p = hVec[0];
      double q = hVec[1];
      double r = hVec[2];

      for(int k = im; k <= iu - 1; ++k) {
         boolean notlast = k != iu - 1;
         if (k != im) {
            p = this.matrixT[k][k - 1];
            q = this.matrixT[k + 1][k - 1];
            r = notlast ? this.matrixT[k + 2][k - 1] : (double)0.0F;
            shift.x = FastMath.abs(p) + FastMath.abs(q) + FastMath.abs(r);
            if (Precision.equals(shift.x, (double)0.0F, this.epsilon)) {
               continue;
            }

            p /= shift.x;
            q /= shift.x;
            r /= shift.x;
         }

         double s = FastMath.sqrt(p * p + q * q + r * r);
         if (p < (double)0.0F) {
            s = -s;
         }

         if (s != (double)0.0F) {
            if (k != im) {
               this.matrixT[k][k - 1] = -s * shift.x;
            } else if (il != im) {
               this.matrixT[k][k - 1] = -this.matrixT[k][k - 1];
            }

            p += s;
            shift.x = p / s;
            shift.y = q / s;
            double z = r / s;
            q /= p;
            r /= p;

            for(int j = k; j < n; ++j) {
               p = this.matrixT[k][j] + q * this.matrixT[k + 1][j];
               if (notlast) {
                  p += r * this.matrixT[k + 2][j];
                  double[] var10000 = this.matrixT[k + 2];
                  var10000[j] -= p * z;
               }

               double[] var24 = this.matrixT[k];
               var24[j] -= p * shift.x;
               var24 = this.matrixT[k + 1];
               var24[j] -= p * shift.y;
            }

            for(int i = 0; i <= FastMath.min(iu, k + 3); ++i) {
               p = shift.x * this.matrixT[i][k] + shift.y * this.matrixT[i][k + 1];
               if (notlast) {
                  p += z * this.matrixT[i][k + 2];
                  double[] var26 = this.matrixT[i];
                  var26[k + 2] -= p * r;
               }

               double[] var27 = this.matrixT[i];
               var27[k] -= p;
               var27 = this.matrixT[i];
               var27[k + 1] -= p * q;
            }

            int high = this.matrixT.length - 1;

            for(int i = 0; i <= high; ++i) {
               p = shift.x * this.matrixP[i][k] + shift.y * this.matrixP[i][k + 1];
               if (notlast) {
                  p += z * this.matrixP[i][k + 2];
                  double[] var29 = this.matrixP[i];
                  var29[k + 2] -= p * r;
               }

               double[] var30 = this.matrixP[i];
               var30[k] -= p;
               var30 = this.matrixP[i];
               var30[k + 1] -= p * q;
            }
         }
      }

      for(int i = im + 2; i <= iu; ++i) {
         this.matrixT[i][i - 2] = (double)0.0F;
         if (i > im + 2) {
            this.matrixT[i][i - 3] = (double)0.0F;
         }
      }

   }

   private static class ShiftInfo {
      double x;
      double y;
      double w;
      double exShift;

      private ShiftInfo() {
      }
   }
}
