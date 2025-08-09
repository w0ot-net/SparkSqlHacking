package org.datanucleus.store.rdbms.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;

public class StatementParameterMapping {
   Map mappings = null;

   public StatementMappingIndex getMappingForParameter(String name) {
      return this.mappings == null ? null : (StatementMappingIndex)this.mappings.get(name);
   }

   public StatementMappingIndex getMappingForParameterPosition(int pos) {
      if (this.mappings == null) {
         return null;
      } else {
         for(Map.Entry entry : this.mappings.entrySet()) {
            StatementMappingIndex idx = (StatementMappingIndex)entry.getValue();

            for(int i = 0; i < idx.getNumberOfParameterOccurrences(); ++i) {
               int[] positions = idx.getParameterPositionsForOccurrence(i);

               for(int j = 0; j < positions.length; ++j) {
                  if (positions[j] == pos) {
                     return idx;
                  }
               }
            }
         }

         return null;
      }
   }

   public void addMappingForParameter(String name, StatementMappingIndex mapping) {
      if (this.mappings == null) {
         this.mappings = new HashMap();
      }

      this.mappings.put(name, mapping);
   }

   public String[] getParameterNames() {
      return this.mappings == null ? null : (String[])this.mappings.keySet().toArray(new String[this.mappings.size()]);
   }

   public boolean isEmpty() {
      return this.mappings == null || this.mappings.size() == 0;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("StatementParameters:");
      if (this.mappings != null) {
         Iterator<Map.Entry<String, StatementMappingIndex>> mapIter = this.mappings.entrySet().iterator();

         while(mapIter.hasNext()) {
            Map.Entry<String, StatementMappingIndex> entry = (Map.Entry)mapIter.next();
            str.append(" param=").append((String)entry.getKey());
            str.append(" mapping=").append(entry.getValue());
            if (mapIter.hasNext()) {
               str.append(",");
            }
         }
      }

      return str.toString();
   }
}
