package org.apache.commons.math3.linear;

import org.apache.commons.math3.util.FastMath;

public class RRQRDecomposition extends QRDecomposition {
   private int[] p;
   private RealMatrix cachedP;

   public RRQRDecomposition(RealMatrix matrix) {
      this(matrix, (double)0.0F);
   }

   public RRQRDecomposition(RealMatrix matrix, double threshold) {
      super(matrix, threshold);
   }

   protected void decompose(double[][] qrt) {
      this.p = new int[qrt.length];

      for(int i = 0; i < this.p.length; this.p[i] = i++) {
      }

      super.decompose(qrt);
   }

   protected void performHouseholderReflection(int minor, double[][] qrt) {
      double l2NormSquaredMax = (double)0.0F;
      int l2NormSquaredMaxIndex = minor;

      for(int i = minor; i < qrt.length; ++i) {
         double l2NormSquared = (double)0.0F;

         for(int j = 0; j < qrt[i].length; ++j) {
            l2NormSquared += qrt[i][j] * qrt[i][j];
         }

         if (l2NormSquared > l2NormSquaredMax) {
            l2NormSquaredMax = l2NormSquared;
            l2NormSquaredMaxIndex = i;
         }
      }

      if (l2NormSquaredMaxIndex != minor) {
         double[] tmp1 = qrt[minor];
         qrt[minor] = qrt[l2NormSquaredMaxIndex];
         qrt[l2NormSquaredMaxIndex] = tmp1;
         int tmp2 = this.p[minor];
         this.p[minor] = this.p[l2NormSquaredMaxIndex];
         this.p[l2NormSquaredMaxIndex] = tmp2;
      }

      super.performHouseholderReflection(minor, qrt);
   }

   public RealMatrix getP() {
      if (this.cachedP == null) {
         int n = this.p.length;
         this.cachedP = MatrixUtils.createRealMatrix(n, n);

         for(int i = 0; i < n; ++i) {
            this.cachedP.setEntry(this.p[i], i, (double)1.0F);
         }
      }

      return this.cachedP;
   }

   public int getRank(double dropThreshold) {
      RealMatrix r = this.getR();
      int rows = r.getRowDimension();
      int columns = r.getColumnDimension();
      int rank = 1;
      double lastNorm = r.getFrobeniusNorm();

      for(double rNorm = lastNorm; rank < FastMath.min(rows, columns); ++rank) {
         double thisNorm = r.getSubMatrix(rank, rows - 1, rank, columns - 1).getFrobeniusNorm();
         if (thisNorm == (double)0.0F || thisNorm / lastNorm * rNorm < dropThreshold) {
            break;
         }

         lastNorm = thisNorm;
      }

      return rank;
   }

   public DecompositionSolver getSolver() {
      return new Solver(super.getSolver(), this.getP());
   }

   private static class Solver implements DecompositionSolver {
      private final DecompositionSolver upper;
      private RealMatrix p;

      private Solver(DecompositionSolver upper, RealMatrix p) {
         this.upper = upper;
         this.p = p;
      }

      public boolean isNonSingular() {
         return this.upper.isNonSingular();
      }

      public RealVector solve(RealVector b) {
         return this.p.operate(this.upper.solve(b));
      }

      public RealMatrix solve(RealMatrix b) {
         return this.p.multiply(this.upper.solve(b));
      }

      public RealMatrix getInverse() {
         return this.solve(MatrixUtils.createRealIdentityMatrix(this.p.getRowDimension()));
      }
   }
}
