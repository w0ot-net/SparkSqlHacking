package org.datanucleus.store.rdbms.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StatementNewObjectMapping {
   Class cls = null;
   Map ctrArgMappings = null;

   public StatementNewObjectMapping(Class cls) {
      this.cls = cls;
   }

   public Class getObjectClass() {
      return this.cls;
   }

   public Object getConstructorArgMapping(int position) {
      return this.ctrArgMappings == null ? null : this.ctrArgMappings.get(position);
   }

   public void addConstructorArgMapping(int ctrPos, Object argMapping) {
      if (this.ctrArgMappings == null) {
         this.ctrArgMappings = new HashMap();
      }

      this.ctrArgMappings.put(ctrPos, argMapping);
   }

   public boolean isEmpty() {
      return this.getNumberOfConstructorArgMappings() == 0;
   }

   public int getNumberOfConstructorArgMappings() {
      return this.ctrArgMappings != null ? this.ctrArgMappings.size() : 0;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("StatementNewObject: " + this.cls.getName() + "(");
      if (this.ctrArgMappings != null) {
         Iterator<Integer> keyIter = this.ctrArgMappings.keySet().iterator();

         while(keyIter.hasNext()) {
            Integer position = (Integer)keyIter.next();
            str.append(this.ctrArgMappings.get(position));
            if (keyIter.hasNext()) {
               str.append(",");
            }
         }
      }

      str.append(")");
      return str.toString();
   }
}
