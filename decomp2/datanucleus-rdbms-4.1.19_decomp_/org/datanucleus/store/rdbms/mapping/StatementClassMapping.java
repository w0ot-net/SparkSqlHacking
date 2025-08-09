package org.datanucleus.store.rdbms.mapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StatementClassMapping {
   public static final int MEMBER_DATASTORE_ID = -1;
   public static final int MEMBER_VERSION = -2;
   public static final int MEMBER_DISCRIMINATOR = -3;
   String className;
   String memberName;
   String nucleusTypeColumn;
   int[] memberNumbers;
   Map mappings;
   Map children;

   public StatementClassMapping() {
      this((String)null, (String)null);
   }

   public StatementClassMapping(String className, String memberName) {
      this.mappings = new HashMap();
      this.className = className;
      this.memberName = memberName;
   }

   public String getClassName() {
      return this.className;
   }

   public String getMemberName() {
      return this.memberName;
   }

   public void setNucleusTypeColumnName(String colName) {
      this.nucleusTypeColumn = colName;
   }

   public String getNucleusTypeColumnName() {
      return this.nucleusTypeColumn;
   }

   public StatementMappingIndex getMappingForMemberPosition(int position) {
      return (StatementMappingIndex)this.mappings.get(position);
   }

   public StatementClassMapping getMappingDefinitionForMemberPosition(int position) {
      return this.children != null ? (StatementClassMapping)this.children.get(position) : null;
   }

   public boolean hasChildMappingDefinitions() {
      return this.children != null && this.children.size() > 0;
   }

   public int[] getMemberNumbers() {
      if (this.memberNumbers != null) {
         return this.memberNumbers;
      } else {
         int length = this.mappings.size();
         if (this.mappings.containsKey(-1)) {
            --length;
         }

         if (this.mappings.containsKey(-2)) {
            --length;
         }

         if (this.mappings.containsKey(-3)) {
            --length;
         }

         int[] positions = new int[length];
         Iterator<Integer> iter = this.mappings.keySet().iterator();
         int i = 0;

         while(iter.hasNext()) {
            Integer val = (Integer)iter.next();
            if (val >= 0) {
               positions[i++] = val;
            }
         }

         this.memberNumbers = positions;
         return positions;
      }
   }

   public void addMappingForMember(int position, StatementMappingIndex mapping) {
      this.memberNumbers = null;
      this.mappings.put(position, mapping);
   }

   public void addMappingDefinitionForMember(int position, StatementClassMapping defn) {
      this.memberNumbers = null;
      if (this.children == null) {
         this.children = new HashMap();
      }

      this.children.put(position, defn);
   }

   public StatementClassMapping cloneStatementMappingWithoutChildren() {
      StatementClassMapping mapping = new StatementClassMapping(this.className, this.memberName);
      mapping.nucleusTypeColumn = this.nucleusTypeColumn;

      for(Map.Entry entry : this.mappings.entrySet()) {
         Integer key = (Integer)entry.getKey();
         StatementMappingIndex value = (StatementMappingIndex)entry.getValue();
         mapping.addMappingForMember(key, value);
      }

      return mapping;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("StatementClassMapping:");
      str.append("class=" + this.className + ",member=" + this.memberName);
      str.append(",mappings=[");
      Iterator<Map.Entry<Integer, StatementMappingIndex>> mapIter = this.mappings.entrySet().iterator();

      while(mapIter.hasNext()) {
         Map.Entry<Integer, StatementMappingIndex> entry = (Map.Entry)mapIter.next();
         str.append("{field=").append(entry.getKey());
         str.append(",mapping=").append(entry.getValue());
         str.append("}");
         if (mapIter.hasNext() || this.children != null) {
            str.append(",");
         }
      }

      str.append("]");
      if (this.children != null) {
         str.append(",children=[");
         Iterator<Map.Entry<Integer, StatementClassMapping>> childIter = this.children.entrySet().iterator();

         while(childIter.hasNext()) {
            Map.Entry<Integer, StatementClassMapping> entry = (Map.Entry)childIter.next();
            str.append("{field=").append(entry.getKey());
            str.append(",mapping=").append(entry.getValue());
            str.append("}");
            if (childIter.hasNext()) {
               str.append(",");
            }
         }

         str.append("]");
      }

      if (this.nucleusTypeColumn != null) {
         str.append(",nucleusTypeColumn=" + this.nucleusTypeColumn);
      }

      return str.toString();
   }
}
