package org.apache.commons.math3.linear;

import org.apache.commons.math3.util.FastMath;

public class RectangularCholeskyDecomposition {
   private final RealMatrix root;
   private int rank;

   public RectangularCholeskyDecomposition(RealMatrix matrix) throws NonPositiveDefiniteMatrixException {
      this(matrix, (double)0.0F);
   }

   public RectangularCholeskyDecomposition(RealMatrix matrix, double small) throws NonPositiveDefiniteMatrixException {
      int order = matrix.getRowDimension();
      double[][] c = matrix.getData();
      double[][] b = new double[order][order];
      int[] index = new int[order];

      for(int i = 0; i < order; index[i] = i++) {
      }

      int r = 0;
      boolean loop = true;

      while(loop) {
         int swapR = r;

         for(int i = r + 1; i < order; ++i) {
            int ii = index[i];
            int isr = index[swapR];
            if (c[ii][ii] > c[isr][isr]) {
               swapR = i;
            }
         }

         if (swapR != r) {
            int tmpIndex = index[r];
            index[r] = index[swapR];
            index[swapR] = tmpIndex;
            double[] tmpRow = b[r];
            b[r] = b[swapR];
            b[swapR] = tmpRow;
         }

         int ir = index[r];
         if (c[ir][ir] <= small) {
            if (r == 0) {
               throw new NonPositiveDefiniteMatrixException(c[ir][ir], ir, small);
            }

            for(int i = r; i < order; ++i) {
               if (c[index[i]][index[i]] < -small) {
                  throw new NonPositiveDefiniteMatrixException(c[index[i]][index[i]], i, small);
               }
            }

            loop = false;
         } else {
            double sqrt = FastMath.sqrt(c[ir][ir]);
            b[r][r] = sqrt;
            double inverse = (double)1.0F / sqrt;
            double inverse2 = (double)1.0F / c[ir][ir];

            for(int i = r + 1; i < order; ++i) {
               int ii = index[i];
               double e = inverse * c[ii][ir];
               b[i][r] = e;
               c[ii][ii] -= c[ii][ir] * c[ii][ir] * inverse2;

               for(int j = r + 1; j < i; ++j) {
                  int ij = index[j];
                  double f = c[ii][ij] - e * b[j][r];
                  c[ii][ij] = f;
                  c[ij][ii] = f;
               }
            }

            ++r;
            loop = r < order;
         }
      }

      this.rank = r;
      this.root = MatrixUtils.createRealMatrix(order, r);

      for(int i = 0; i < order; ++i) {
         for(int j = 0; j < r; ++j) {
            this.root.setEntry(index[i], j, b[i][j]);
         }
      }

   }

   public RealMatrix getRootMatrix() {
      return this.root;
   }

   public int getRank() {
      return this.rank;
   }
}
