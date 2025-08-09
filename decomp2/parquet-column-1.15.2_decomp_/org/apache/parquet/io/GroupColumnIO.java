package org.apache.parquet.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupColumnIO extends ColumnIO {
   private static final Logger LOG = LoggerFactory.getLogger(GroupColumnIO.class);
   private final Map childrenByName = new HashMap();
   private final List children = new ArrayList();
   private int childrenSize = 0;

   GroupColumnIO(GroupType groupType, GroupColumnIO parent, int index) {
      super(groupType, parent, index);
   }

   void add(ColumnIO child) {
      this.children.add(child);
      this.childrenByName.put(child.getType().getName(), child);
      ++this.childrenSize;
   }

   void setLevels(int r, int d, String[] fieldPath, int[] indexFieldPath, List repetition, List path) {
      super.setLevels(r, d, fieldPath, indexFieldPath, repetition, path);

      for(ColumnIO child : this.children) {
         String[] newFieldPath = (String[])Arrays.copyOf(fieldPath, fieldPath.length + 1);
         int[] newIndexFieldPath = Arrays.copyOf(indexFieldPath, indexFieldPath.length + 1);
         newFieldPath[fieldPath.length] = child.getType().getName();
         newIndexFieldPath[indexFieldPath.length] = child.getIndex();
         List<ColumnIO> newRepetition;
         if (child.getType().isRepetition(Type.Repetition.REPEATED)) {
            newRepetition = new ArrayList(repetition);
            newRepetition.add(child);
         } else {
            newRepetition = repetition;
         }

         List<ColumnIO> newPath = new ArrayList(path);
         newPath.add(child);
         child.setLevels(child.getType().isRepetition(Type.Repetition.REPEATED) ? r + 1 : r, !child.getType().isRepetition(Type.Repetition.REQUIRED) ? d + 1 : d, newFieldPath, newIndexFieldPath, newRepetition, newPath);
      }

   }

   List getColumnNames() {
      ArrayList<String[]> result = new ArrayList();

      for(ColumnIO c : this.children) {
         result.addAll(c.getColumnNames());
      }

      return result;
   }

   PrimitiveColumnIO getLast() {
      return ((ColumnIO)this.children.get(this.children.size() - 1)).getLast();
   }

   PrimitiveColumnIO getFirst() {
      return ((ColumnIO)this.children.get(0)).getFirst();
   }

   public ColumnIO getChild(String name) {
      return (ColumnIO)this.childrenByName.get(name);
   }

   public ColumnIO getChild(int fieldIndex) {
      try {
         return (ColumnIO)this.children.get(fieldIndex);
      } catch (IndexOutOfBoundsException e) {
         throw new InvalidRecordException("could not get child " + fieldIndex + " from " + this.children, e);
      }
   }

   public int getChildrenCount() {
      return this.childrenSize;
   }
}
