package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.MathArrays;

public class FieldLUDecomposition {
   private final Field field;
   private FieldElement[][] lu;
   private int[] pivot;
   private boolean even;
   private boolean singular;
   private FieldMatrix cachedL;
   private FieldMatrix cachedU;
   private FieldMatrix cachedP;

   public FieldLUDecomposition(FieldMatrix matrix) {
      if (!matrix.isSquare()) {
         throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
      } else {
         int m = matrix.getColumnDimension();
         this.field = matrix.getField();
         this.lu = matrix.getData();
         this.pivot = new int[m];
         this.cachedL = null;
         this.cachedU = null;
         this.cachedP = null;

         for(int row = 0; row < m; this.pivot[row] = row++) {
         }

         this.even = true;
         this.singular = false;

         for(int col = 0; col < m; ++col) {
            T sum = (T)((FieldElement)this.field.getZero());

            for(int row = 0; row < col; ++row) {
               T[] luRow = (T[])this.lu[row];
               sum = (T)luRow[col];

               for(int i = 0; i < row; ++i) {
                  sum = (T)((FieldElement)sum.subtract(luRow[i].multiply(this.lu[i][col])));
               }

               luRow[col] = sum;
            }

            int nonZero = col;

            for(int row = col; row < m; ++row) {
               T[] luRow = (T[])this.lu[row];
               sum = (T)luRow[col];

               for(int i = 0; i < col; ++i) {
                  sum = (T)((FieldElement)sum.subtract(luRow[i].multiply(this.lu[i][col])));
               }

               luRow[col] = sum;
               if (this.lu[nonZero][col].equals(this.field.getZero())) {
                  ++nonZero;
               }
            }

            if (nonZero >= m) {
               this.singular = true;
               return;
            }

            if (nonZero != col) {
               T tmp = (T)((FieldElement)this.field.getZero());

               for(int i = 0; i < m; ++i) {
                  tmp = (T)this.lu[nonZero][i];
                  this.lu[nonZero][i] = this.lu[col][i];
                  this.lu[col][i] = tmp;
               }

               int temp = this.pivot[nonZero];
               this.pivot[nonZero] = this.pivot[col];
               this.pivot[col] = temp;
               this.even = !this.even;
            }

            T luDiag = (T)this.lu[col][col];

            for(int row = col + 1; row < m; ++row) {
               T[] luRow = (T[])this.lu[row];
               luRow[col] = (FieldElement)luRow[col].divide(luDiag);
            }
         }

      }
   }

   public FieldMatrix getL() {
      if (this.cachedL == null && !this.singular) {
         int m = this.pivot.length;
         this.cachedL = new Array2DRowFieldMatrix(this.field, m, m);

         for(int i = 0; i < m; ++i) {
            T[] luI = (T[])this.lu[i];

            for(int j = 0; j < i; ++j) {
               this.cachedL.setEntry(i, j, luI[j]);
            }

            this.cachedL.setEntry(i, i, (FieldElement)this.field.getOne());
         }
      }

      return this.cachedL;
   }

   public FieldMatrix getU() {
      if (this.cachedU == null && !this.singular) {
         int m = this.pivot.length;
         this.cachedU = new Array2DRowFieldMatrix(this.field, m, m);

         for(int i = 0; i < m; ++i) {
            T[] luI = (T[])this.lu[i];

            for(int j = i; j < m; ++j) {
               this.cachedU.setEntry(i, j, luI[j]);
            }
         }
      }

      return this.cachedU;
   }

   public FieldMatrix getP() {
      if (this.cachedP == null && !this.singular) {
         int m = this.pivot.length;
         this.cachedP = new Array2DRowFieldMatrix(this.field, m, m);

         for(int i = 0; i < m; ++i) {
            this.cachedP.setEntry(i, this.pivot[i], (FieldElement)this.field.getOne());
         }
      }

      return this.cachedP;
   }

   public int[] getPivot() {
      return (int[])this.pivot.clone();
   }

   public FieldElement getDeterminant() {
      if (this.singular) {
         return (FieldElement)this.field.getZero();
      } else {
         int m = this.pivot.length;
         T determinant = (T)(this.even ? (FieldElement)this.field.getOne() : (FieldElement)((FieldElement)this.field.getZero()).subtract(this.field.getOne()));

         for(int i = 0; i < m; ++i) {
            determinant = (T)((FieldElement)determinant.multiply(this.lu[i][i]));
         }

         return determinant;
      }
   }

   public FieldDecompositionSolver getSolver() {
      return new Solver(this.field, this.lu, this.pivot, this.singular);
   }

   private static class Solver implements FieldDecompositionSolver {
      private final Field field;
      private final FieldElement[][] lu;
      private final int[] pivot;
      private final boolean singular;

      private Solver(Field field, FieldElement[][] lu, int[] pivot, boolean singular) {
         this.field = field;
         this.lu = lu;
         this.pivot = pivot;
         this.singular = singular;
      }

      public boolean isNonSingular() {
         return !this.singular;
      }

      public FieldVector solve(FieldVector b) {
         try {
            return this.solve((ArrayFieldVector)b);
         } catch (ClassCastException var8) {
            int m = this.pivot.length;
            if (b.getDimension() != m) {
               throw new DimensionMismatchException(b.getDimension(), m);
            } else if (this.singular) {
               throw new SingularMatrixException();
            } else {
               T[] bp = (T[])((FieldElement[])MathArrays.buildArray(this.field, m));

               for(int row = 0; row < m; ++row) {
                  bp[row] = b.getEntry(this.pivot[row]);
               }

               for(int col = 0; col < m; ++col) {
                  T bpCol = (T)bp[col];

                  for(int i = col + 1; i < m; ++i) {
                     bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
                  }
               }

               for(int col = m - 1; col >= 0; --col) {
                  bp[col] = (FieldElement)bp[col].divide(this.lu[col][col]);
                  T bpCol = (T)bp[col];

                  for(int i = 0; i < col; ++i) {
                     bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
                  }
               }

               return new ArrayFieldVector(this.field, bp, false);
            }
         }
      }

      public ArrayFieldVector solve(ArrayFieldVector b) {
         int m = this.pivot.length;
         int length = b.getDimension();
         if (length != m) {
            throw new DimensionMismatchException(length, m);
         } else if (this.singular) {
            throw new SingularMatrixException();
         } else {
            T[] bp = (T[])((FieldElement[])MathArrays.buildArray(this.field, m));

            for(int row = 0; row < m; ++row) {
               bp[row] = b.getEntry(this.pivot[row]);
            }

            for(int col = 0; col < m; ++col) {
               T bpCol = (T)bp[col];

               for(int i = col + 1; i < m; ++i) {
                  bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
               }
            }

            for(int col = m - 1; col >= 0; --col) {
               bp[col] = (FieldElement)bp[col].divide(this.lu[col][col]);
               T bpCol = (T)bp[col];

               for(int i = 0; i < col; ++i) {
                  bp[i] = (FieldElement)bp[i].subtract(bpCol.multiply(this.lu[i][col]));
               }
            }

            return new ArrayFieldVector(bp, false);
         }
      }

      public FieldMatrix solve(FieldMatrix b) {
         int m = this.pivot.length;
         if (b.getRowDimension() != m) {
            throw new DimensionMismatchException(b.getRowDimension(), m);
         } else if (this.singular) {
            throw new SingularMatrixException();
         } else {
            int nColB = b.getColumnDimension();
            T[][] bp = (T[][])((FieldElement[][])MathArrays.buildArray(this.field, m, nColB));

            for(int row = 0; row < m; ++row) {
               T[] bpRow = (T[])bp[row];
               int pRow = this.pivot[row];

               for(int col = 0; col < nColB; ++col) {
                  bpRow[col] = b.getEntry(pRow, col);
               }
            }

            for(int col = 0; col < m; ++col) {
               T[] bpCol = (T[])bp[col];

               for(int i = col + 1; i < m; ++i) {
                  T[] bpI = (T[])bp[i];
                  T luICol = (T)this.lu[i][col];

                  for(int j = 0; j < nColB; ++j) {
                     bpI[j] = (FieldElement)bpI[j].subtract(bpCol[j].multiply(luICol));
                  }
               }
            }

            for(int col = m - 1; col >= 0; --col) {
               T[] bpCol = (T[])bp[col];
               T luDiag = (T)this.lu[col][col];

               for(int j = 0; j < nColB; ++j) {
                  bpCol[j] = (FieldElement)bpCol[j].divide(luDiag);
               }

               for(int i = 0; i < col; ++i) {
                  T[] bpI = (T[])bp[i];
                  T luICol = (T)this.lu[i][col];

                  for(int j = 0; j < nColB; ++j) {
                     bpI[j] = (FieldElement)bpI[j].subtract(bpCol[j].multiply(luICol));
                  }
               }
            }

            return new Array2DRowFieldMatrix(this.field, bp, false);
         }
      }

      public FieldMatrix getInverse() {
         int m = this.pivot.length;
         T one = (T)((FieldElement)this.field.getOne());
         FieldMatrix<T> identity = new Array2DRowFieldMatrix(this.field, m, m);

         for(int i = 0; i < m; ++i) {
            identity.setEntry(i, i, one);
         }

         return this.solve(identity);
      }
   }
}
