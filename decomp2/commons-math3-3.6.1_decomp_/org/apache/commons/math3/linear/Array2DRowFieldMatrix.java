package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class Array2DRowFieldMatrix extends AbstractFieldMatrix implements Serializable {
   private static final long serialVersionUID = 7260756672015356458L;
   private FieldElement[][] data;

   public Array2DRowFieldMatrix(Field field) {
      super(field);
   }

   public Array2DRowFieldMatrix(Field field, int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
      super(field, rowDimension, columnDimension);
      this.data = (FieldElement[][])MathArrays.buildArray(field, rowDimension, columnDimension);
   }

   public Array2DRowFieldMatrix(FieldElement[][] d) throws DimensionMismatchException, NullArgumentException, NoDataException {
      this(extractField(d), d);
   }

   public Array2DRowFieldMatrix(Field field, FieldElement[][] d) throws DimensionMismatchException, NullArgumentException, NoDataException {
      super(field);
      this.copyIn(d);
   }

   public Array2DRowFieldMatrix(FieldElement[][] d, boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
      this(extractField(d), d, copyArray);
   }

   public Array2DRowFieldMatrix(Field field, FieldElement[][] d, boolean copyArray) throws DimensionMismatchException, NoDataException, NullArgumentException {
      super(field);
      if (copyArray) {
         this.copyIn(d);
      } else {
         MathUtils.checkNotNull(d);
         int nRows = d.length;
         if (nRows == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
         }

         int nCols = d[0].length;
         if (nCols == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
         }

         for(int r = 1; r < nRows; ++r) {
            if (d[r].length != nCols) {
               throw new DimensionMismatchException(nCols, d[r].length);
            }
         }

         this.data = d;
      }

   }

   public Array2DRowFieldMatrix(FieldElement[] v) throws NoDataException {
      this(extractField(v), v);
   }

   public Array2DRowFieldMatrix(Field field, FieldElement[] v) {
      super(field);
      int nRows = v.length;
      this.data = (FieldElement[][])MathArrays.buildArray(this.getField(), nRows, 1);

      for(int row = 0; row < nRows; ++row) {
         this.data[row][0] = v[row];
      }

   }

   public FieldMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
      return new Array2DRowFieldMatrix(this.getField(), rowDimension, columnDimension);
   }

   public FieldMatrix copy() {
      return new Array2DRowFieldMatrix(this.getField(), this.copyOut(), false);
   }

   public Array2DRowFieldMatrix add(Array2DRowFieldMatrix m) throws MatrixDimensionMismatchException {
      this.checkAdditionCompatible(m);
      int rowCount = this.getRowDimension();
      int columnCount = this.getColumnDimension();
      T[][] outData = (T[][])((FieldElement[][])MathArrays.buildArray(this.getField(), rowCount, columnCount));

      for(int row = 0; row < rowCount; ++row) {
         T[] dataRow = (T[])this.data[row];
         T[] mRow = (T[])m.data[row];
         T[] outDataRow = (T[])outData[row];

         for(int col = 0; col < columnCount; ++col) {
            outDataRow[col] = (FieldElement)dataRow[col].add(mRow[col]);
         }
      }

      return new Array2DRowFieldMatrix(this.getField(), outData, false);
   }

   public Array2DRowFieldMatrix subtract(Array2DRowFieldMatrix m) throws MatrixDimensionMismatchException {
      this.checkSubtractionCompatible(m);
      int rowCount = this.getRowDimension();
      int columnCount = this.getColumnDimension();
      T[][] outData = (T[][])((FieldElement[][])MathArrays.buildArray(this.getField(), rowCount, columnCount));

      for(int row = 0; row < rowCount; ++row) {
         T[] dataRow = (T[])this.data[row];
         T[] mRow = (T[])m.data[row];
         T[] outDataRow = (T[])outData[row];

         for(int col = 0; col < columnCount; ++col) {
            outDataRow[col] = (FieldElement)dataRow[col].subtract(mRow[col]);
         }
      }

      return new Array2DRowFieldMatrix(this.getField(), outData, false);
   }

   public Array2DRowFieldMatrix multiply(Array2DRowFieldMatrix m) throws DimensionMismatchException {
      this.checkMultiplicationCompatible(m);
      int nRows = this.getRowDimension();
      int nCols = m.getColumnDimension();
      int nSum = this.getColumnDimension();
      T[][] outData = (T[][])((FieldElement[][])MathArrays.buildArray(this.getField(), nRows, nCols));

      for(int row = 0; row < nRows; ++row) {
         T[] dataRow = (T[])this.data[row];
         T[] outDataRow = (T[])outData[row];

         for(int col = 0; col < nCols; ++col) {
            T sum = (T)((FieldElement)this.getField().getZero());

            for(int i = 0; i < nSum; ++i) {
               sum = (T)((FieldElement)sum.add(dataRow[i].multiply(m.data[i][col])));
            }

            outDataRow[col] = sum;
         }
      }

      return new Array2DRowFieldMatrix(this.getField(), outData, false);
   }

   public FieldElement[][] getData() {
      return this.copyOut();
   }

   public FieldElement[][] getDataRef() {
      return this.data;
   }

   public void setSubMatrix(FieldElement[][] subMatrix, int row, int column) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
      if (this.data == null) {
         if (row > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, new Object[]{row});
         }

         if (column > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, new Object[]{column});
         }

         int nRows = subMatrix.length;
         if (nRows == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
         }

         int nCols = subMatrix[0].length;
         if (nCols == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
         }

         this.data = (FieldElement[][])MathArrays.buildArray(this.getField(), subMatrix.length, nCols);

         for(int i = 0; i < this.data.length; ++i) {
            if (subMatrix[i].length != nCols) {
               throw new DimensionMismatchException(nCols, subMatrix[i].length);
            }

            System.arraycopy(subMatrix[i], 0, this.data[i + row], column, nCols);
         }
      } else {
         super.setSubMatrix(subMatrix, row, column);
      }

   }

   public FieldElement getEntry(int row, int column) throws OutOfRangeException {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      return this.data[row][column];
   }

   public void setEntry(int row, int column, FieldElement value) throws OutOfRangeException {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      this.data[row][column] = value;
   }

   public void addToEntry(int row, int column, FieldElement increment) throws OutOfRangeException {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      this.data[row][column] = (FieldElement)this.data[row][column].add(increment);
   }

   public void multiplyEntry(int row, int column, FieldElement factor) throws OutOfRangeException {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      this.data[row][column] = (FieldElement)this.data[row][column].multiply(factor);
   }

   public int getRowDimension() {
      return this.data == null ? 0 : this.data.length;
   }

   public int getColumnDimension() {
      return this.data != null && this.data[0] != null ? this.data[0].length : 0;
   }

   public FieldElement[] operate(FieldElement[] v) throws DimensionMismatchException {
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      if (v.length != nCols) {
         throw new DimensionMismatchException(v.length, nCols);
      } else {
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.getField(), nRows));

         for(int row = 0; row < nRows; ++row) {
            T[] dataRow = (T[])this.data[row];
            T sum = (T)((FieldElement)this.getField().getZero());

            for(int i = 0; i < nCols; ++i) {
               sum = (T)((FieldElement)sum.add(dataRow[i].multiply(v[i])));
            }

            out[row] = sum;
         }

         return out;
      }
   }

   public FieldElement[] preMultiply(FieldElement[] v) throws DimensionMismatchException {
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      if (v.length != nRows) {
         throw new DimensionMismatchException(v.length, nRows);
      } else {
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.getField(), nCols));

         for(int col = 0; col < nCols; ++col) {
            T sum = (T)((FieldElement)this.getField().getZero());

            for(int i = 0; i < nRows; ++i) {
               sum = (T)((FieldElement)sum.add(this.data[i][col].multiply(v[i])));
            }

            out[col] = sum;
         }

         return out;
      }
   }

   public FieldElement walkInRowOrder(FieldMatrixChangingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int i = 0; i < rows; ++i) {
         T[] rowI = (T[])this.data[i];

         for(int j = 0; j < columns; ++j) {
            rowI[j] = visitor.visit(i, j, rowI[j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInRowOrder(FieldMatrixPreservingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int i = 0; i < rows; ++i) {
         T[] rowI = (T[])this.data[i];

         for(int j = 0; j < columns; ++j) {
            visitor.visit(i, j, rowI[j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInRowOrder(FieldMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int i = startRow; i <= endRow; ++i) {
         T[] rowI = (T[])this.data[i];

         for(int j = startColumn; j <= endColumn; ++j) {
            rowI[j] = visitor.visit(i, j, rowI[j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInRowOrder(FieldMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int i = startRow; i <= endRow; ++i) {
         T[] rowI = (T[])this.data[i];

         for(int j = startColumn; j <= endColumn; ++j) {
            visitor.visit(i, j, rowI[j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixChangingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int j = 0; j < columns; ++j) {
         for(int i = 0; i < rows; ++i) {
            T[] rowI = (T[])this.data[i];
            rowI[j] = visitor.visit(i, j, rowI[j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixPreservingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int j = 0; j < columns; ++j) {
         for(int i = 0; i < rows; ++i) {
            visitor.visit(i, j, this.data[i][j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int j = startColumn; j <= endColumn; ++j) {
         for(int i = startRow; i <= endRow; ++i) {
            T[] rowI = (T[])this.data[i];
            rowI[j] = visitor.visit(i, j, rowI[j]);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int j = startColumn; j <= endColumn; ++j) {
         for(int i = startRow; i <= endRow; ++i) {
            visitor.visit(i, j, this.data[i][j]);
         }
      }

      return visitor.end();
   }

   private FieldElement[][] copyOut() {
      int nRows = this.getRowDimension();
      T[][] out = (T[][])((FieldElement[][])MathArrays.buildArray(this.getField(), nRows, this.getColumnDimension()));

      for(int i = 0; i < nRows; ++i) {
         System.arraycopy(this.data[i], 0, out[i], 0, this.data[i].length);
      }

      return out;
   }

   private void copyIn(FieldElement[][] in) throws NullArgumentException, NoDataException, DimensionMismatchException {
      this.setSubMatrix(in, 0, 0);
   }
}
