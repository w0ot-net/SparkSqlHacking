package org.apache.parquet.io;

import java.util.Arrays;
import java.util.List;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;

public class PrimitiveColumnIO extends ColumnIO {
   private ColumnIO[] path;
   private ColumnDescriptor columnDescriptor;
   private final int id;

   PrimitiveColumnIO(Type type, GroupColumnIO parent, int index, int id) {
      super(type, parent, index);
      this.id = id;
   }

   void setLevels(int r, int d, String[] fieldPath, int[] fieldIndexPath, List repetition, List path) {
      super.setLevels(r, d, fieldPath, fieldIndexPath, repetition, path);
      PrimitiveType type = this.getType().asPrimitiveType();
      this.columnDescriptor = new ColumnDescriptor(fieldPath, type, this.getRepetitionLevel(), this.getDefinitionLevel());
      this.path = (ColumnIO[])path.toArray(new ColumnIO[0]);
   }

   List getColumnNames() {
      return Arrays.asList(this.getFieldPath());
   }

   public ColumnDescriptor getColumnDescriptor() {
      return this.columnDescriptor;
   }

   public ColumnIO[] getPath() {
      return this.path;
   }

   public boolean isLast(int r) {
      return this.getLast(r) == this;
   }

   private PrimitiveColumnIO getLast(int r) {
      ColumnIO parent = this.getParent(r);
      PrimitiveColumnIO last = parent.getLast();
      return last;
   }

   PrimitiveColumnIO getLast() {
      return this;
   }

   PrimitiveColumnIO getFirst() {
      return this;
   }

   public boolean isFirst(int r) {
      return this.getFirst(r) == this;
   }

   private PrimitiveColumnIO getFirst(int r) {
      ColumnIO parent = this.getParent(r);
      return parent.getFirst();
   }

   public PrimitiveType.PrimitiveTypeName getPrimitive() {
      return this.getType().asPrimitiveType().getPrimitiveTypeName();
   }

   public int getId() {
      return this.id;
   }
}
