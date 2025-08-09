package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;

public class SparseFieldMatrix extends AbstractFieldMatrix {
   private final OpenIntToFieldHashMap entries;
   private final int rows;
   private final int columns;

   public SparseFieldMatrix(Field field) {
      super(field);
      this.rows = 0;
      this.columns = 0;
      this.entries = new OpenIntToFieldHashMap(field);
   }

   public SparseFieldMatrix(Field field, int rowDimension, int columnDimension) {
      super(field, rowDimension, columnDimension);
      this.rows = rowDimension;
      this.columns = columnDimension;
      this.entries = new OpenIntToFieldHashMap(field);
   }

   public SparseFieldMatrix(SparseFieldMatrix other) {
      super(other.getField(), other.getRowDimension(), other.getColumnDimension());
      this.rows = other.getRowDimension();
      this.columns = other.getColumnDimension();
      this.entries = new OpenIntToFieldHashMap(other.entries);
   }

   public SparseFieldMatrix(FieldMatrix other) {
      super(other.getField(), other.getRowDimension(), other.getColumnDimension());
      this.rows = other.getRowDimension();
      this.columns = other.getColumnDimension();
      this.entries = new OpenIntToFieldHashMap(this.getField());

      for(int i = 0; i < this.rows; ++i) {
         for(int j = 0; j < this.columns; ++j) {
            this.setEntry(i, j, other.getEntry(i, j));
         }
      }

   }

   public void addToEntry(int row, int column, FieldElement increment) {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      int key = this.computeKey(row, column);
      T value = (T)((FieldElement)this.entries.get(key).add(increment));
      if (((FieldElement)this.getField().getZero()).equals(value)) {
         this.entries.remove(key);
      } else {
         this.entries.put(key, value);
      }

   }

   public FieldMatrix copy() {
      return new SparseFieldMatrix(this);
   }

   public FieldMatrix createMatrix(int rowDimension, int columnDimension) {
      return new SparseFieldMatrix(this.getField(), rowDimension, columnDimension);
   }

   public int getColumnDimension() {
      return this.columns;
   }

   public FieldElement getEntry(int row, int column) {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      return this.entries.get(this.computeKey(row, column));
   }

   public int getRowDimension() {
      return this.rows;
   }

   public void multiplyEntry(int row, int column, FieldElement factor) {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      int key = this.computeKey(row, column);
      T value = (T)((FieldElement)this.entries.get(key).multiply(factor));
      if (((FieldElement)this.getField().getZero()).equals(value)) {
         this.entries.remove(key);
      } else {
         this.entries.put(key, value);
      }

   }

   public void setEntry(int row, int column, FieldElement value) {
      this.checkRowIndex(row);
      this.checkColumnIndex(column);
      if (((FieldElement)this.getField().getZero()).equals(value)) {
         this.entries.remove(this.computeKey(row, column));
      } else {
         this.entries.put(this.computeKey(row, column), value);
      }

   }

   private int computeKey(int row, int column) {
      return row * this.columns + column;
   }
}
