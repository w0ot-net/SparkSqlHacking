package org.datanucleus.store.rdbms.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;

public class StatementResultMapping {
   Map mappings = null;

   public Object getMappingForResultExpression(int position) {
      return this.mappings == null ? null : this.mappings.get(position);
   }

   public void addMappingForResultExpression(int position, StatementMappingIndex mapping) {
      if (this.mappings == null) {
         this.mappings = new HashMap();
      }

      this.mappings.put(position, mapping);
   }

   public void addMappingForResultExpression(int position, StatementNewObjectMapping mapping) {
      if (this.mappings == null) {
         this.mappings = new HashMap();
      }

      this.mappings.put(position, mapping);
   }

   public void addMappingForResultExpression(int position, StatementClassMapping mapping) {
      if (this.mappings == null) {
         this.mappings = new HashMap();
      }

      this.mappings.put(position, mapping);
   }

   public boolean isEmpty() {
      return this.getNumberOfResultExpressions() == 0;
   }

   public int getNumberOfResultExpressions() {
      return this.mappings != null ? this.mappings.size() : 0;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("StatementResults:");
      if (this.mappings != null) {
         Iterator<Map.Entry<Integer, Object>> mapIter = this.mappings.entrySet().iterator();

         while(mapIter.hasNext()) {
            Map.Entry<Integer, Object> entry = (Map.Entry)mapIter.next();
            str.append(" position=").append(entry.getKey());
            str.append(" mapping=").append(entry.getValue());
            if (mapIter.hasNext()) {
               str.append(",");
            }
         }
      }

      return str.toString();
   }
}
