package org.apache.commons.math3.linear;

import java.util.ArrayList;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public abstract class AbstractFieldMatrix implements FieldMatrix {
   private final Field field;

   protected AbstractFieldMatrix() {
      this.field = null;
   }

   protected AbstractFieldMatrix(Field field) {
      this.field = field;
   }

   protected AbstractFieldMatrix(Field field, int rowDimension, int columnDimension) throws NotStrictlyPositiveException {
      if (rowDimension <= 0) {
         throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, rowDimension);
      } else if (columnDimension <= 0) {
         throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, columnDimension);
      } else {
         this.field = field;
      }
   }

   protected static Field extractField(FieldElement[][] d) throws NoDataException, NullArgumentException {
      if (d == null) {
         throw new NullArgumentException();
      } else if (d.length == 0) {
         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
      } else if (d[0].length == 0) {
         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
      } else {
         return d[0][0].getField();
      }
   }

   protected static Field extractField(FieldElement[] d) throws NoDataException {
      if (d.length == 0) {
         throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
      } else {
         return d[0].getField();
      }
   }

   /** @deprecated */
   @Deprecated
   protected static FieldElement[][] buildArray(Field field, int rows, int columns) {
      return (FieldElement[][])MathArrays.buildArray(field, rows, columns);
   }

   /** @deprecated */
   @Deprecated
   protected static FieldElement[] buildArray(Field field, int length) {
      return (FieldElement[])MathArrays.buildArray(field, length);
   }

   public Field getField() {
      return this.field;
   }

   public abstract FieldMatrix createMatrix(int var1, int var2) throws NotStrictlyPositiveException;

   public abstract FieldMatrix copy();

   public FieldMatrix add(FieldMatrix m) throws MatrixDimensionMismatchException {
      this.checkAdditionCompatible(m);
      int rowCount = this.getRowDimension();
      int columnCount = this.getColumnDimension();
      FieldMatrix<T> out = this.createMatrix(rowCount, columnCount);

      for(int row = 0; row < rowCount; ++row) {
         for(int col = 0; col < columnCount; ++col) {
            out.setEntry(row, col, (FieldElement)this.getEntry(row, col).add(m.getEntry(row, col)));
         }
      }

      return out;
   }

   public FieldMatrix subtract(FieldMatrix m) throws MatrixDimensionMismatchException {
      this.checkSubtractionCompatible(m);
      int rowCount = this.getRowDimension();
      int columnCount = this.getColumnDimension();
      FieldMatrix<T> out = this.createMatrix(rowCount, columnCount);

      for(int row = 0; row < rowCount; ++row) {
         for(int col = 0; col < columnCount; ++col) {
            out.setEntry(row, col, (FieldElement)this.getEntry(row, col).subtract(m.getEntry(row, col)));
         }
      }

      return out;
   }

   public FieldMatrix scalarAdd(FieldElement d) {
      int rowCount = this.getRowDimension();
      int columnCount = this.getColumnDimension();
      FieldMatrix<T> out = this.createMatrix(rowCount, columnCount);

      for(int row = 0; row < rowCount; ++row) {
         for(int col = 0; col < columnCount; ++col) {
            out.setEntry(row, col, (FieldElement)this.getEntry(row, col).add(d));
         }
      }

      return out;
   }

   public FieldMatrix scalarMultiply(FieldElement d) {
      int rowCount = this.getRowDimension();
      int columnCount = this.getColumnDimension();
      FieldMatrix<T> out = this.createMatrix(rowCount, columnCount);

      for(int row = 0; row < rowCount; ++row) {
         for(int col = 0; col < columnCount; ++col) {
            out.setEntry(row, col, (FieldElement)this.getEntry(row, col).multiply(d));
         }
      }

      return out;
   }

   public FieldMatrix multiply(FieldMatrix m) throws DimensionMismatchException {
      this.checkMultiplicationCompatible(m);
      int nRows = this.getRowDimension();
      int nCols = m.getColumnDimension();
      int nSum = this.getColumnDimension();
      FieldMatrix<T> out = this.createMatrix(nRows, nCols);

      for(int row = 0; row < nRows; ++row) {
         for(int col = 0; col < nCols; ++col) {
            T sum = (T)((FieldElement)this.field.getZero());

            for(int i = 0; i < nSum; ++i) {
               sum = (T)((FieldElement)sum.add(this.getEntry(row, i).multiply(m.getEntry(i, col))));
            }

            out.setEntry(row, col, sum);
         }
      }

      return out;
   }

   public FieldMatrix preMultiply(FieldMatrix m) throws DimensionMismatchException {
      return m.multiply(this);
   }

   public FieldMatrix power(int p) throws NonSquareMatrixException, NotPositiveException {
      if (p < 0) {
         throw new NotPositiveException(p);
      } else if (!this.isSquare()) {
         throw new NonSquareMatrixException(this.getRowDimension(), this.getColumnDimension());
      } else if (p == 0) {
         return MatrixUtils.createFieldIdentityMatrix(this.getField(), this.getRowDimension());
      } else if (p == 1) {
         return this.copy();
      } else {
         int power = p - 1;
         char[] binaryRepresentation = Integer.toBinaryString(power).toCharArray();
         ArrayList<Integer> nonZeroPositions = new ArrayList();

         for(int i = 0; i < binaryRepresentation.length; ++i) {
            if (binaryRepresentation[i] == '1') {
               int pos = binaryRepresentation.length - i - 1;
               nonZeroPositions.add(pos);
            }
         }

         ArrayList<FieldMatrix<T>> results = new ArrayList(binaryRepresentation.length);
         results.add(0, this.copy());

         for(int i = 1; i < binaryRepresentation.length; ++i) {
            FieldMatrix<T> s = (FieldMatrix)results.get(i - 1);
            FieldMatrix<T> r = s.multiply(s);
            results.add(i, r);
         }

         FieldMatrix<T> result = this.copy();

         for(Integer i : nonZeroPositions) {
            result = result.multiply((FieldMatrix)results.get(i));
         }

         return result;
      }
   }

   public FieldElement[][] getData() {
      T[][] data = (T[][])((FieldElement[][])MathArrays.buildArray(this.field, this.getRowDimension(), this.getColumnDimension()));

      for(int i = 0; i < data.length; ++i) {
         T[] dataI = (T[])data[i];

         for(int j = 0; j < dataI.length; ++j) {
            dataI[j] = this.getEntry(i, j);
         }
      }

      return data;
   }

   public FieldMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      FieldMatrix<T> subMatrix = this.createMatrix(endRow - startRow + 1, endColumn - startColumn + 1);

      for(int i = startRow; i <= endRow; ++i) {
         for(int j = startColumn; j <= endColumn; ++j) {
            subMatrix.setEntry(i - startRow, j - startColumn, this.getEntry(i, j));
         }
      }

      return subMatrix;
   }

   public FieldMatrix getSubMatrix(int[] selectedRows, int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
      // $FF: Couldn't be decompiled
   }

   public void copySubMatrix(int startRow, int endRow, int startColumn, int endColumn, FieldElement[][] destination) throws MatrixDimensionMismatchException, NumberIsTooSmallException, OutOfRangeException {
      // $FF: Couldn't be decompiled
   }

   public void copySubMatrix(int[] selectedRows, int[] selectedColumns, FieldElement[][] destination) throws MatrixDimensionMismatchException, NoDataException, NullArgumentException, OutOfRangeException {
      this.checkSubMatrixIndex(selectedRows, selectedColumns);
      if (destination.length >= selectedRows.length && destination[0].length >= selectedColumns.length) {
         for(int i = 0; i < selectedRows.length; ++i) {
            T[] destinationI = (T[])destination[i];

            for(int j = 0; j < selectedColumns.length; ++j) {
               destinationI[j] = this.getEntry(selectedRows[i], selectedColumns[j]);
            }
         }

      } else {
         throw new MatrixDimensionMismatchException(destination.length, destination[0].length, selectedRows.length, selectedColumns.length);
      }
   }

   public void setSubMatrix(FieldElement[][] subMatrix, int row, int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
      if (subMatrix == null) {
         throw new NullArgumentException();
      } else {
         int nRows = subMatrix.length;
         if (nRows == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
         } else {
            int nCols = subMatrix[0].length;
            if (nCols == 0) {
               throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            } else {
               for(int r = 1; r < nRows; ++r) {
                  if (subMatrix[r].length != nCols) {
                     throw new DimensionMismatchException(nCols, subMatrix[r].length);
                  }
               }

               this.checkRowIndex(row);
               this.checkColumnIndex(column);
               this.checkRowIndex(nRows + row - 1);
               this.checkColumnIndex(nCols + column - 1);

               for(int i = 0; i < nRows; ++i) {
                  for(int j = 0; j < nCols; ++j) {
                     this.setEntry(row + i, column + j, subMatrix[i][j]);
                  }
               }

            }
         }
      }
   }

   public FieldMatrix getRowMatrix(int row) throws OutOfRangeException {
      this.checkRowIndex(row);
      int nCols = this.getColumnDimension();
      FieldMatrix<T> out = this.createMatrix(1, nCols);

      for(int i = 0; i < nCols; ++i) {
         out.setEntry(0, i, this.getEntry(row, i));
      }

      return out;
   }

   public void setRowMatrix(int row, FieldMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
      this.checkRowIndex(row);
      int nCols = this.getColumnDimension();
      if (matrix.getRowDimension() == 1 && matrix.getColumnDimension() == nCols) {
         for(int i = 0; i < nCols; ++i) {
            this.setEntry(row, i, matrix.getEntry(0, i));
         }

      } else {
         throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), 1, nCols);
      }
   }

   public FieldMatrix getColumnMatrix(int column) throws OutOfRangeException {
      this.checkColumnIndex(column);
      int nRows = this.getRowDimension();
      FieldMatrix<T> out = this.createMatrix(nRows, 1);

      for(int i = 0; i < nRows; ++i) {
         out.setEntry(i, 0, this.getEntry(i, column));
      }

      return out;
   }

   public void setColumnMatrix(int column, FieldMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
      this.checkColumnIndex(column);
      int nRows = this.getRowDimension();
      if (matrix.getRowDimension() == nRows && matrix.getColumnDimension() == 1) {
         for(int i = 0; i < nRows; ++i) {
            this.setEntry(i, column, matrix.getEntry(i, 0));
         }

      } else {
         throw new MatrixDimensionMismatchException(matrix.getRowDimension(), matrix.getColumnDimension(), nRows, 1);
      }
   }

   public FieldVector getRowVector(int row) throws OutOfRangeException {
      return new ArrayFieldVector(this.field, this.getRow(row), false);
   }

   public void setRowVector(int row, FieldVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
      this.checkRowIndex(row);
      int nCols = this.getColumnDimension();
      if (vector.getDimension() != nCols) {
         throw new MatrixDimensionMismatchException(1, vector.getDimension(), 1, nCols);
      } else {
         for(int i = 0; i < nCols; ++i) {
            this.setEntry(row, i, vector.getEntry(i));
         }

      }
   }

   public FieldVector getColumnVector(int column) throws OutOfRangeException {
      return new ArrayFieldVector(this.field, this.getColumn(column), false);
   }

   public void setColumnVector(int column, FieldVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
      this.checkColumnIndex(column);
      int nRows = this.getRowDimension();
      if (vector.getDimension() != nRows) {
         throw new MatrixDimensionMismatchException(vector.getDimension(), 1, nRows, 1);
      } else {
         for(int i = 0; i < nRows; ++i) {
            this.setEntry(i, column, vector.getEntry(i));
         }

      }
   }

   public FieldElement[] getRow(int row) throws OutOfRangeException {
      this.checkRowIndex(row);
      int nCols = this.getColumnDimension();
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, nCols));

      for(int i = 0; i < nCols; ++i) {
         out[i] = this.getEntry(row, i);
      }

      return out;
   }

   public void setRow(int row, FieldElement[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
      this.checkRowIndex(row);
      int nCols = this.getColumnDimension();
      if (array.length != nCols) {
         throw new MatrixDimensionMismatchException(1, array.length, 1, nCols);
      } else {
         for(int i = 0; i < nCols; ++i) {
            this.setEntry(row, i, array[i]);
         }

      }
   }

   public FieldElement[] getColumn(int column) throws OutOfRangeException {
      this.checkColumnIndex(column);
      int nRows = this.getRowDimension();
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, nRows));

      for(int i = 0; i < nRows; ++i) {
         out[i] = this.getEntry(i, column);
      }

      return out;
   }

   public void setColumn(int column, FieldElement[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
      this.checkColumnIndex(column);
      int nRows = this.getRowDimension();
      if (array.length != nRows) {
         throw new MatrixDimensionMismatchException(array.length, 1, nRows, 1);
      } else {
         for(int i = 0; i < nRows; ++i) {
            this.setEntry(i, column, array[i]);
         }

      }
   }

   public abstract FieldElement getEntry(int var1, int var2) throws OutOfRangeException;

   public abstract void setEntry(int var1, int var2, FieldElement var3) throws OutOfRangeException;

   public abstract void addToEntry(int var1, int var2, FieldElement var3) throws OutOfRangeException;

   public abstract void multiplyEntry(int var1, int var2, FieldElement var3) throws OutOfRangeException;

   public FieldMatrix transpose() {
      // $FF: Couldn't be decompiled
   }

   public boolean isSquare() {
      return this.getColumnDimension() == this.getRowDimension();
   }

   public abstract int getRowDimension();

   public abstract int getColumnDimension();

   public FieldElement getTrace() throws NonSquareMatrixException {
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      if (nRows != nCols) {
         throw new NonSquareMatrixException(nRows, nCols);
      } else {
         T trace = (T)((FieldElement)this.field.getZero());

         for(int i = 0; i < nRows; ++i) {
            trace = (T)((FieldElement)trace.add(this.getEntry(i, i)));
         }

         return trace;
      }
   }

   public FieldElement[] operate(FieldElement[] v) throws DimensionMismatchException {
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      if (v.length != nCols) {
         throw new DimensionMismatchException(v.length, nCols);
      } else {
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, nRows));

         for(int row = 0; row < nRows; ++row) {
            T sum = (T)((FieldElement)this.field.getZero());

            for(int i = 0; i < nCols; ++i) {
               sum = (T)((FieldElement)sum.add(this.getEntry(row, i).multiply(v[i])));
            }

            out[row] = sum;
         }

         return out;
      }
   }

   public FieldVector operate(FieldVector v) throws DimensionMismatchException {
      try {
         return new ArrayFieldVector(this.field, this.operate(((ArrayFieldVector)v).getDataRef()), false);
      } catch (ClassCastException var9) {
         int nRows = this.getRowDimension();
         int nCols = this.getColumnDimension();
         if (v.getDimension() != nCols) {
            throw new DimensionMismatchException(v.getDimension(), nCols);
         } else {
            T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, nRows));

            for(int row = 0; row < nRows; ++row) {
               T sum = (T)((FieldElement)this.field.getZero());

               for(int i = 0; i < nCols; ++i) {
                  sum = (T)((FieldElement)sum.add(this.getEntry(row, i).multiply(v.getEntry(i))));
               }

               out[row] = sum;
            }

            return new ArrayFieldVector(this.field, out, false);
         }
      }
   }

   public FieldElement[] preMultiply(FieldElement[] v) throws DimensionMismatchException {
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      if (v.length != nRows) {
         throw new DimensionMismatchException(v.length, nRows);
      } else {
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, nCols));

         for(int col = 0; col < nCols; ++col) {
            T sum = (T)((FieldElement)this.field.getZero());

            for(int i = 0; i < nRows; ++i) {
               sum = (T)((FieldElement)sum.add(this.getEntry(i, col).multiply(v[i])));
            }

            out[col] = sum;
         }

         return out;
      }
   }

   public FieldVector preMultiply(FieldVector v) throws DimensionMismatchException {
      try {
         return new ArrayFieldVector(this.field, this.preMultiply(((ArrayFieldVector)v).getDataRef()), false);
      } catch (ClassCastException var9) {
         int nRows = this.getRowDimension();
         int nCols = this.getColumnDimension();
         if (v.getDimension() != nRows) {
            throw new DimensionMismatchException(v.getDimension(), nRows);
         } else {
            T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, nCols));

            for(int col = 0; col < nCols; ++col) {
               T sum = (T)((FieldElement)this.field.getZero());

               for(int i = 0; i < nRows; ++i) {
                  sum = (T)((FieldElement)sum.add(this.getEntry(i, col).multiply(v.getEntry(i))));
               }

               out[col] = sum;
            }

            return new ArrayFieldVector(this.field, out, false);
         }
      }
   }

   public FieldElement walkInRowOrder(FieldMatrixChangingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int row = 0; row < rows; ++row) {
         for(int column = 0; column < columns; ++column) {
            T oldValue = (T)this.getEntry(row, column);
            T newValue = (T)visitor.visit(row, column, oldValue);
            this.setEntry(row, column, newValue);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInRowOrder(FieldMatrixPreservingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int row = 0; row < rows; ++row) {
         for(int column = 0; column < columns; ++column) {
            visitor.visit(row, column, this.getEntry(row, column));
         }
      }

      return visitor.end();
   }

   public FieldElement walkInRowOrder(FieldMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int row = startRow; row <= endRow; ++row) {
         for(int column = startColumn; column <= endColumn; ++column) {
            T oldValue = (T)this.getEntry(row, column);
            T newValue = (T)visitor.visit(row, column, oldValue);
            this.setEntry(row, column, newValue);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInRowOrder(FieldMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int row = startRow; row <= endRow; ++row) {
         for(int column = startColumn; column <= endColumn; ++column) {
            visitor.visit(row, column, this.getEntry(row, column));
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixChangingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int column = 0; column < columns; ++column) {
         for(int row = 0; row < rows; ++row) {
            T oldValue = (T)this.getEntry(row, column);
            T newValue = (T)visitor.visit(row, column, oldValue);
            this.setEntry(row, column, newValue);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixPreservingVisitor visitor) {
      int rows = this.getRowDimension();
      int columns = this.getColumnDimension();
      visitor.start(rows, columns, 0, rows - 1, 0, columns - 1);

      for(int column = 0; column < columns; ++column) {
         for(int row = 0; row < rows; ++row) {
            visitor.visit(row, column, this.getEntry(row, column));
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int column = startColumn; column <= endColumn; ++column) {
         for(int row = startRow; row <= endRow; ++row) {
            T oldValue = (T)this.getEntry(row, column);
            T newValue = (T)visitor.visit(row, column, oldValue);
            this.setEntry(row, column, newValue);
         }
      }

      return visitor.end();
   }

   public FieldElement walkInColumnOrder(FieldMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkSubMatrixIndex(startRow, endRow, startColumn, endColumn);
      visitor.start(this.getRowDimension(), this.getColumnDimension(), startRow, endRow, startColumn, endColumn);

      for(int column = startColumn; column <= endColumn; ++column) {
         for(int row = startRow; row <= endRow; ++row) {
            visitor.visit(row, column, this.getEntry(row, column));
         }
      }

      return visitor.end();
   }

   public FieldElement walkInOptimizedOrder(FieldMatrixChangingVisitor visitor) {
      return this.walkInRowOrder(visitor);
   }

   public FieldElement walkInOptimizedOrder(FieldMatrixPreservingVisitor visitor) {
      return this.walkInRowOrder(visitor);
   }

   public FieldElement walkInOptimizedOrder(FieldMatrixChangingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      return this.walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
   }

   public FieldElement walkInOptimizedOrder(FieldMatrixPreservingVisitor visitor, int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      return this.walkInRowOrder(visitor, startRow, endRow, startColumn, endColumn);
   }

   public String toString() {
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      StringBuffer res = new StringBuffer();
      String fullClassName = this.getClass().getName();
      String shortClassName = fullClassName.substring(fullClassName.lastIndexOf(46) + 1);
      res.append(shortClassName).append("{");

      for(int i = 0; i < nRows; ++i) {
         if (i > 0) {
            res.append(",");
         }

         res.append("{");

         for(int j = 0; j < nCols; ++j) {
            if (j > 0) {
               res.append(",");
            }

            res.append(this.getEntry(i, j));
         }

         res.append("}");
      }

      res.append("}");
      return res.toString();
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof FieldMatrix)) {
         return false;
      } else {
         FieldMatrix<?> m = (FieldMatrix)object;
         int nRows = this.getRowDimension();
         int nCols = this.getColumnDimension();
         if (m.getColumnDimension() == nCols && m.getRowDimension() == nRows) {
            for(int row = 0; row < nRows; ++row) {
               for(int col = 0; col < nCols; ++col) {
                  if (!this.getEntry(row, col).equals(m.getEntry(row, col))) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public int hashCode() {
      int ret = 322562;
      int nRows = this.getRowDimension();
      int nCols = this.getColumnDimension();
      ret = ret * 31 + nRows;
      ret = ret * 31 + nCols;

      for(int row = 0; row < nRows; ++row) {
         for(int col = 0; col < nCols; ++col) {
            ret = ret * 31 + (11 * (row + 1) + 17 * (col + 1)) * this.getEntry(row, col).hashCode();
         }
      }

      return ret;
   }

   protected void checkRowIndex(int row) throws OutOfRangeException {
      if (row < 0 || row >= this.getRowDimension()) {
         throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, row, 0, this.getRowDimension() - 1);
      }
   }

   protected void checkColumnIndex(int column) throws OutOfRangeException {
      if (column < 0 || column >= this.getColumnDimension()) {
         throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, column, 0, this.getColumnDimension() - 1);
      }
   }

   protected void checkSubMatrixIndex(int startRow, int endRow, int startColumn, int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkRowIndex(startRow);
      this.checkRowIndex(endRow);
      if (endRow < startRow) {
         throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, endRow, startRow, true);
      } else {
         this.checkColumnIndex(startColumn);
         this.checkColumnIndex(endColumn);
         if (endColumn < startColumn) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, endColumn, startColumn, true);
         }
      }
   }

   protected void checkSubMatrixIndex(int[] selectedRows, int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
      if (selectedRows != null && selectedColumns != null) {
         if (selectedRows.length != 0 && selectedColumns.length != 0) {
            for(int row : selectedRows) {
               this.checkRowIndex(row);
            }

            for(int column : selectedColumns) {
               this.checkColumnIndex(column);
            }

         } else {
            throw new NoDataException();
         }
      } else {
         throw new NullArgumentException();
      }
   }

   protected void checkAdditionCompatible(FieldMatrix m) throws MatrixDimensionMismatchException {
      if (this.getRowDimension() != m.getRowDimension() || this.getColumnDimension() != m.getColumnDimension()) {
         throw new MatrixDimensionMismatchException(m.getRowDimension(), m.getColumnDimension(), this.getRowDimension(), this.getColumnDimension());
      }
   }

   protected void checkSubtractionCompatible(FieldMatrix m) throws MatrixDimensionMismatchException {
      if (this.getRowDimension() != m.getRowDimension() || this.getColumnDimension() != m.getColumnDimension()) {
         throw new MatrixDimensionMismatchException(m.getRowDimension(), m.getColumnDimension(), this.getRowDimension(), this.getColumnDimension());
      }
   }

   protected void checkMultiplicationCompatible(FieldMatrix m) throws DimensionMismatchException {
      if (this.getColumnDimension() != m.getRowDimension()) {
         throw new DimensionMismatchException(m.getRowDimension(), this.getColumnDimension());
      }
   }
}
