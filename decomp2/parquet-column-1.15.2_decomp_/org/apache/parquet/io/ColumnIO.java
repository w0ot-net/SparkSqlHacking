package org.apache.parquet.io;

import java.util.Arrays;
import java.util.List;
import org.apache.parquet.schema.Type;

public abstract class ColumnIO {
   private final GroupColumnIO parent;
   private final Type type;
   private final String name;
   private final int index;
   private int repetitionLevel;
   private int definitionLevel;
   private String[] fieldPath;
   private int[] indexFieldPath;

   ColumnIO(Type type, GroupColumnIO parent, int index) {
      this.type = type;
      this.parent = parent;
      this.index = index;
      this.name = type.getName();
   }

   public String[] getFieldPath() {
      return this.fieldPath;
   }

   public String getFieldPath(int level) {
      return this.fieldPath[level];
   }

   public int[] getIndexFieldPath() {
      return this.indexFieldPath;
   }

   public int getIndexFieldPath(int level) {
      return this.indexFieldPath[level];
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public int getRepetitionLevel() {
      return this.repetitionLevel;
   }

   public int getDefinitionLevel() {
      return this.definitionLevel;
   }

   void setRepetitionLevel(int repetitionLevel) {
      this.repetitionLevel = repetitionLevel;
   }

   void setDefinitionLevel(int definitionLevel) {
      this.definitionLevel = definitionLevel;
   }

   void setFieldPath(String[] fieldPath, int[] indexFieldPath) {
      this.fieldPath = fieldPath;
      this.indexFieldPath = indexFieldPath;
   }

   public Type getType() {
      return this.type;
   }

   void setLevels(int r, int d, String[] fieldPath, int[] indexFieldPath, List repetition, List path) {
      this.setRepetitionLevel(r);
      this.setDefinitionLevel(d);
      this.setFieldPath(fieldPath, indexFieldPath);
   }

   abstract List getColumnNames();

   public GroupColumnIO getParent() {
      return this.parent;
   }

   abstract PrimitiveColumnIO getLast();

   abstract PrimitiveColumnIO getFirst();

   ColumnIO getParent(int r) {
      if (this.getRepetitionLevel() == r && this.getType().isRepetition(Type.Repetition.REPEATED)) {
         return this;
      } else if (this.getParent() != null && this.getParent().getDefinitionLevel() >= r) {
         return this.getParent().getParent(r);
      } else {
         throw new InvalidRecordException("no parent(" + r + ") for " + Arrays.toString(this.getFieldPath()));
      }
   }

   public String toString() {
      return this.getClass().getSimpleName() + " " + this.type.getName() + " r:" + this.repetitionLevel + " d:" + this.definitionLevel + " " + Arrays.toString(this.fieldPath);
   }
}
