package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.DataProcessingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ColumnSplitter {
   private List columnValues;
   private String[] headers = null;
   private int expectedRowCount = 1000;
   private long rowCount;
   private long addNullsFrom;

   ColumnSplitter(int expectedRowCount) {
      if (expectedRowCount <= 0) {
         throw new IllegalArgumentException("Expected row count must be positive");
      } else {
         this.expectedRowCount = expectedRowCount;
      }
   }

   void clearValues() {
      this.addNullsFrom = this.rowCount;
      this.columnValues = null;
   }

   void reset() {
      this.columnValues = null;
      this.headers = null;
      this.addNullsFrom = 0L;
      this.rowCount = 0L;
   }

   List getColumnValues() {
      return this.columnValues;
   }

   String[] getHeaders() {
      return this.headers;
   }

   private void initialize(Context context) {
      if (this.headers == null) {
         String[] allHeaders = context.headers();
         if (allHeaders == null) {
            this.headers = ArgumentUtils.EMPTY_STRING_ARRAY;
         } else if (!context.columnsReordered()) {
            this.headers = allHeaders;
         } else {
            int[] selectedIndexes = context.extractedFieldIndexes();
            int last = Math.min(allHeaders.length, selectedIndexes.length);
            this.headers = new String[selectedIndexes.length];

            for(int i = 0; i < last; ++i) {
               int idx = selectedIndexes[i];
               if (idx < allHeaders.length) {
                  this.headers[i] = allHeaders[selectedIndexes[i]];
               }
            }
         }
      }

      this.columnValues = new ArrayList(this.headers.length > 0 ? this.headers.length : 10);
   }

   String getHeader(int columnIndex) {
      return columnIndex < this.headers.length ? this.headers[columnIndex] : null;
   }

   void addValuesToColumns(Object[] row, Context context) {
      if (this.columnValues == null) {
         this.initialize(context);
      }

      if (this.columnValues.size() < row.length) {
         int columnsToAdd = row.length - this.columnValues.size();

         while(columnsToAdd-- > 0) {
            long records = context.currentRecord() - this.addNullsFrom;
            ArrayList<T> values = new ArrayList((long)this.expectedRowCount < records ? (int)records : this.expectedRowCount);

            while(--records > 0L) {
               values.add((Object)null);
            }

            this.columnValues.add(values);
         }
      }

      for(int i = 0; i < row.length; ++i) {
         ((List)this.columnValues.get(i)).add(row[i]);
      }

      if (row.length < this.columnValues.size()) {
         for(int i = row.length; i < this.columnValues.size(); ++i) {
            ((List)this.columnValues.get(i)).add((Object)null);
         }
      }

      ++this.rowCount;
   }

   void putColumnValuesInMapOfNames(Map map) {
      if (this.columnValues != null) {
         for(int i = 0; i < this.columnValues.size(); ++i) {
            String header = this.getHeader(i);
            if (header == null) {
               throw new DataProcessingException("Parsed input does not have header for column at index '" + i + "'. Parsed header names: " + Arrays.toString(this.getHeaders()), i);
            }

            map.put(header, this.columnValues.get(i));
         }

      }
   }

   List getColumnValues(int columnIndex, Class columnType) {
      if (columnIndex < 0) {
         throw new IllegalArgumentException("Column index must be positive");
      } else if (columnIndex >= this.columnValues.size()) {
         throw new IllegalArgumentException("Column index must be less than " + this.columnValues.size() + ". Got " + columnIndex);
      } else {
         return (List)this.columnValues.get(columnIndex);
      }
   }

   List getColumnValues(String columnName, Class columnType) {
      int index = ArgumentUtils.indexOf(this.headers, columnName);
      if (index == -1) {
         throw new IllegalArgumentException("No column named '" + columnName + "' has been found. Available column headers: " + Arrays.toString(this.headers));
      } else {
         return this.getColumnValues(index, columnType);
      }
   }

   void putColumnValuesInMapOfIndexes(Map map) {
      if (this.columnValues != null) {
         for(int i = 0; i < this.columnValues.size(); ++i) {
            map.put(i, this.columnValues.get(i));
         }

      }
   }

   Map getColumnValuesAsMapOfNames() {
      Map<String, List<T>> map = new HashMap();
      this.putColumnValuesInMapOfNames(map);
      return map;
   }

   Map getColumnValuesAsMapOfIndexes() {
      Map<Integer, List<T>> map = new HashMap();
      this.putColumnValuesInMapOfIndexes(map);
      return map;
   }
}
