package org.datanucleus.store.rdbms.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.util.StringUtils;

public class StatementMappingIndex {
   JavaTypeMapping mapping;
   int[] columnPositions;
   List paramPositions = null;
   String columnName;

   public StatementMappingIndex(JavaTypeMapping mapping) {
      this.mapping = mapping;
   }

   public JavaTypeMapping getMapping() {
      return this.mapping;
   }

   public void setMapping(JavaTypeMapping mapping) {
      this.mapping = mapping;
   }

   public String getColumnAlias() {
      if (this.columnName != null) {
         return this.columnName;
      } else {
         return this.mapping != null && this.mapping.getMemberMetaData() != null ? this.mapping.getMemberMetaData().getName() : null;
      }
   }

   public void setColumnAlias(String alias) {
      this.columnName = alias;
   }

   public int[] getColumnPositions() {
      return this.columnPositions;
   }

   public void setColumnPositions(int[] pos) {
      this.columnPositions = pos;
   }

   public void addParameterOccurrence(int[] positions) {
      if (this.paramPositions == null) {
         this.paramPositions = new ArrayList();
      }

      if (this.mapping != null && positions.length != this.mapping.getNumberOfDatastoreMappings()) {
         throw new NucleusException("Mapping " + this.mapping + " cannot be " + positions.length + " parameters since it has " + this.mapping.getNumberOfDatastoreMappings() + " columns");
      } else {
         this.paramPositions.add(positions);
      }
   }

   public void removeParameterOccurrence(int[] positions) {
      if (this.paramPositions != null) {
         this.paramPositions.remove(positions);
      }
   }

   public int getNumberOfParameterOccurrences() {
      return this.paramPositions != null ? this.paramPositions.size() : 0;
   }

   public int[] getParameterPositionsForOccurrence(int num) {
      return this.paramPositions == null ? null : (int[])this.paramPositions.get(num);
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append("mapping: " + this.mapping);
      if (this.paramPositions != null) {
         str.append(" parameter(s): ");
         Iterator<int[]> iter = this.paramPositions.iterator();

         while(iter.hasNext()) {
            int[] positions = (int[])iter.next();
            str.append(StringUtils.intArrayToString(positions));
            if (iter.hasNext()) {
               str.append(',');
            }
         }
      }

      if (this.columnPositions != null) {
         str.append(" column(s): " + StringUtils.intArrayToString(this.columnPositions));
      }

      return str.toString();
   }
}
