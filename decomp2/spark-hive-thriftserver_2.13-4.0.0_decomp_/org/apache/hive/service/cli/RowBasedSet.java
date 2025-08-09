package org.apache.hive.service.cli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hive.service.rpc.thrift.TColumnValue;
import org.apache.hive.service.rpc.thrift.TRow;
import org.apache.hive.service.rpc.thrift.TRowSet;

public class RowBasedSet implements RowSet {
   private long startOffset;
   private final TypeDescriptor[] descriptors;
   private final RemovableList rows;

   public RowBasedSet(TableSchema schema) {
      this.descriptors = schema.toTypeDescriptors();
      this.rows = new RemovableList();
   }

   public RowBasedSet(TRowSet tRowSet) {
      this.descriptors = null;
      this.rows = new RemovableList(tRowSet.getRows());
      this.startOffset = tRowSet.getStartRowOffset();
   }

   private RowBasedSet(TypeDescriptor[] descriptors, List rows, long startOffset) {
      this.descriptors = descriptors;
      this.rows = new RemovableList(rows);
      this.startOffset = startOffset;
   }

   public RowBasedSet addRow(Object[] fields) {
      TRow tRow = new TRow();

      for(int i = 0; i < fields.length; ++i) {
         tRow.addToColVals(ColumnValue.toTColumnValue(this.descriptors[i], fields[i]));
      }

      this.rows.add(tRow);
      return this;
   }

   public int numColumns() {
      return this.rows.isEmpty() ? 0 : ((TRow)this.rows.get(0)).getColVals().size();
   }

   public int numRows() {
      return this.rows.size();
   }

   public RowBasedSet extractSubset(int maxRows) {
      int numRows = Math.min(this.numRows(), maxRows);
      RowBasedSet result = new RowBasedSet(this.descriptors, this.rows.subList(0, numRows), this.startOffset);
      this.rows.removeRange(0, numRows);
      this.startOffset += (long)numRows;
      return result;
   }

   public long getStartOffset() {
      return this.startOffset;
   }

   public void setStartOffset(long startOffset) {
      this.startOffset = startOffset;
   }

   public int getSize() {
      return this.rows.size();
   }

   public TRowSet toTRowSet() {
      TRowSet tRowSet = new TRowSet();
      tRowSet.setStartRowOffset(this.startOffset);
      tRowSet.setRows(new ArrayList(this.rows));
      return tRowSet;
   }

   public Iterator iterator() {
      return new Iterator() {
         final Iterator iterator;
         final Object[] convey;

         {
            this.iterator = RowBasedSet.this.rows.iterator();
            this.convey = new Object[RowBasedSet.this.numColumns()];
         }

         public boolean hasNext() {
            return this.iterator.hasNext();
         }

         public Object[] next() {
            TRow row = (TRow)this.iterator.next();
            List<TColumnValue> values = row.getColVals();

            for(int i = 0; i < values.size(); ++i) {
               this.convey[i] = ColumnValue.toColumnValue((TColumnValue)values.get(i));
            }

            return this.convey;
         }

         public void remove() {
            throw new UnsupportedOperationException("remove");
         }
      };
   }

   private static class RemovableList extends ArrayList {
      RemovableList() {
      }

      RemovableList(List rows) {
         super(rows);
      }

      public void removeRange(int fromIndex, int toIndex) {
         super.removeRange(fromIndex, toIndex);
      }
   }
}
