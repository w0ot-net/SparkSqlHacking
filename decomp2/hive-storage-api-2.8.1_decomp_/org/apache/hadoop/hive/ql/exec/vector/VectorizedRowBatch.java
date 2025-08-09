package org.apache.hadoop.hive.ql.exec.vector;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.hive.ql.io.filter.MutableFilterContext;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hive.common.util.SuppressFBWarnings;

public class VectorizedRowBatch implements Writable, MutableFilterContext {
   public int numCols;
   public ColumnVector[] cols;
   public int size;
   public int[] selected;
   public int[] projectedColumns;
   public int projectionSize;
   private int dataColumnCount;
   private int partitionColumnCount;
   public boolean selectedInUse;
   public boolean endOfFile;
   public static final int DEFAULT_SIZE = 1024;
   public static final int DEFAULT_BYTES = 33554432;

   public VectorizedRowBatch(int numCols) {
      this(numCols, 1024);
   }

   public VectorizedRowBatch(int numCols, int size) {
      this.numCols = numCols;
      this.size = size;
      this.selected = new int[size];
      this.selectedInUse = false;
      this.cols = new ColumnVector[numCols];
      this.projectedColumns = new int[numCols];
      this.projectionSize = numCols;

      for(int i = 0; i < numCols; this.projectedColumns[i] = i++) {
      }

      this.dataColumnCount = -1;
      this.partitionColumnCount = -1;
   }

   public void setPartitionInfo(int dataColumnCount, int partitionColumnCount) {
      this.dataColumnCount = dataColumnCount;
      this.partitionColumnCount = partitionColumnCount;
   }

   public int getDataColumnCount() {
      return this.dataColumnCount;
   }

   public int getPartitionColumnCount() {
      return this.partitionColumnCount;
   }

   public int getMaxSize() {
      return this.selected.length;
   }

   public long count() {
      return (long)this.size;
   }

   private static String toUTF8(Object o) {
      return o != null && !(o instanceof NullWritable) ? o.toString() : "\\N";
   }

   public String stringifyColumn(int columnNum) {
      if (this.size == 0) {
         return "";
      } else {
         StringBuilder b = new StringBuilder();
         b.append("columnNum ");
         b.append(columnNum);
         b.append(", size ");
         b.append(this.size);
         b.append(", selectedInUse ");
         b.append(this.selectedInUse);
         ColumnVector colVector = this.cols[columnNum];
         b.append(", noNulls ");
         b.append(colVector.noNulls);
         b.append(", isRepeating ");
         b.append(colVector.isRepeating);
         b.append('\n');
         boolean noNulls = colVector.noNulls;
         boolean[] isNull = colVector.isNull;
         if (colVector.isRepeating) {
            boolean hasRepeatedValue = noNulls || !isNull[0];

            for(int i = 0; i < this.size; ++i) {
               if (hasRepeatedValue) {
                  colVector.stringifyValue(b, 0);
               } else {
                  b.append("NULL");
               }

               b.append('\n');
            }
         } else {
            for(int i = 0; i < this.size; ++i) {
               int batchIndex = this.selectedInUse ? this.selected[i] : i;
               if (!noNulls && isNull[batchIndex]) {
                  b.append("NULL");
               } else {
                  colVector.stringifyValue(b, batchIndex);
               }

               b.append('\n');
            }
         }

         return b.toString();
      }
   }

   private void appendVectorType(StringBuilder b, ColumnVector cv) {
      String colVectorType = null;
      if (cv instanceof LongColumnVector) {
         colVectorType = "LONG";
      } else if (cv instanceof DoubleColumnVector) {
         colVectorType = "DOUBLE";
      } else if (cv instanceof BytesColumnVector) {
         colVectorType = "BYTES";
      } else if (cv instanceof DecimalColumnVector) {
         colVectorType = "DECIMAL";
      } else if (cv instanceof TimestampColumnVector) {
         colVectorType = "TIMESTAMP";
      } else if (cv instanceof IntervalDayTimeColumnVector) {
         colVectorType = "INTERVAL_DAY_TIME";
      } else if (cv instanceof ListColumnVector) {
         colVectorType = "LIST";
      } else if (cv instanceof MapColumnVector) {
         colVectorType = "MAP";
      } else if (cv instanceof StructColumnVector) {
         colVectorType = "STRUCT";
      } else if (cv instanceof UnionColumnVector) {
         colVectorType = "UNION";
      } else {
         colVectorType = "Unknown";
      }

      b.append(colVectorType);
      if (cv instanceof ListColumnVector) {
         ListColumnVector listColumnVector = (ListColumnVector)cv;
         b.append("<");
         this.appendVectorType(b, listColumnVector.child);
         b.append(">");
      } else if (cv instanceof MapColumnVector) {
         MapColumnVector mapColumnVector = (MapColumnVector)cv;
         b.append("<");
         this.appendVectorType(b, mapColumnVector.keys);
         b.append(", ");
         this.appendVectorType(b, mapColumnVector.values);
         b.append(">");
      } else if (cv instanceof StructColumnVector) {
         StructColumnVector structColumnVector = (StructColumnVector)cv;
         b.append("<");
         int fieldCount = structColumnVector.fields.length;

         for(int i = 0; i < fieldCount; ++i) {
            if (i > 0) {
               b.append(", ");
            }

            this.appendVectorType(b, structColumnVector.fields[i]);
         }

         b.append(">");
      } else if (cv instanceof UnionColumnVector) {
         UnionColumnVector unionColumnVector = (UnionColumnVector)cv;
         b.append("<");
         int fieldCount = unionColumnVector.fields.length;

         for(int i = 0; i < fieldCount; ++i) {
            if (i > 0) {
               b.append(", ");
            }

            this.appendVectorType(b, unionColumnVector.fields[i]);
         }

         b.append(">");
      }

   }

   public String stringify(String prefix) {
      if (this.size == 0) {
         return "";
      } else {
         StringBuilder b = new StringBuilder();
         b.append(prefix);
         b.append("Column vector types: ");

         for(int k = 0; k < this.projectionSize; ++k) {
            int projIndex = this.projectedColumns[k];
            ColumnVector cv = this.cols[projIndex];
            if (k > 0) {
               b.append(", ");
            }

            b.append(projIndex);
            b.append(":");
            this.appendVectorType(b, cv);
         }

         b.append('\n');
         b.append(prefix);
         if (this.selectedInUse) {
            for(int j = 0; j < this.size; ++j) {
               int i = this.selected[j];
               b.append('[');

               for(int k = 0; k < this.projectionSize; ++k) {
                  int projIndex = this.projectedColumns[k];
                  ColumnVector cv = this.cols[projIndex];
                  if (k > 0) {
                     b.append(", ");
                  }

                  if (cv != null) {
                     try {
                        cv.stringifyValue(b, i);
                     } catch (Exception var10) {
                        b.append("<invalid>");
                     }
                  }
               }

               b.append(']');
               if (j < this.size - 1) {
                  b.append('\n');
                  b.append(prefix);
               }
            }
         } else {
            for(int i = 0; i < this.size; ++i) {
               b.append('[');

               for(int k = 0; k < this.projectionSize; ++k) {
                  int projIndex = this.projectedColumns[k];
                  ColumnVector cv = this.cols[projIndex];
                  if (k > 0) {
                     b.append(", ");
                  }

                  if (cv != null) {
                     try {
                        cv.stringifyValue(b, i);
                     } catch (Exception var9) {
                        b.append("<invalid>");
                     }
                  }
               }

               b.append(']');
               if (i < this.size - 1) {
                  b.append('\n');
                  b.append(prefix);
               }
            }
         }

         return b.toString();
      }
   }

   public String toString() {
      return this.stringify("");
   }

   public void readFields(DataInput arg0) throws IOException {
      throw new UnsupportedOperationException("Do you really need me?");
   }

   public void write(DataOutput arg0) throws IOException {
      throw new UnsupportedOperationException("Don't call me");
   }

   public void reset() {
      this.selectedInUse = false;
      this.size = 0;
      this.endOfFile = false;

      for(ColumnVector vc : this.cols) {
         if (vc != null) {
            vc.reset();
            vc.init();
         }
      }

   }

   public void ensureSize(int rows) {
      for(ColumnVector col : this.cols) {
         col.ensureSize(rows, false);
      }

      this.updateSelected(rows);
   }

   public boolean isSelectedInUse() {
      return this.selectedInUse;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public int[] getSelected() {
      return this.selected;
   }

   public int getSelectedSize() {
      return this.size;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "Ref external obj for efficiency"
   )
   public void setFilterContext(boolean isSelectedInUse, int[] selected, int selectedSize) {
      this.selectedInUse = isSelectedInUse;
      this.selected = selected;
      this.size = selectedSize;

      assert this.validateSelected() : "Selected array may not contain duplicates or unordered values";

   }

   public boolean validateSelected() {
      for(int i = 1; i < this.size; ++i) {
         if (this.selected[i - 1] >= this.selected[i]) {
            return false;
         }
      }

      return true;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public int[] updateSelected(int minCapacity) {
      if (this.selected == null || this.selected.length < minCapacity) {
         this.selected = new int[minCapacity];
      }

      return this.selected;
   }

   public void setSelectedInUse(boolean selectedInUse) {
      this.selectedInUse = selectedInUse;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "Ref external obj for efficiency"
   )
   public void setSelected(int[] selectedArray) {
      this.selected = selectedArray;
   }

   public void setSelectedSize(int selectedSize) {
      this.size = selectedSize;
   }
}
