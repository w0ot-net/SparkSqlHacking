package org.datanucleus.store.rdbms.schema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.store.schema.MapStoreSchemaData;
import org.datanucleus.store.schema.StoreSchemaData;

public class RDBMSTypesInfo implements MapStoreSchemaData {
   Map properties = new HashMap();
   Map jdbcTypes = new HashMap();

   public void addChild(StoreSchemaData type) {
      this.jdbcTypes.put("" + type.getProperty("jdbc_type"), type);
   }

   public void clearChildren() {
      this.jdbcTypes.clear();
   }

   public StoreSchemaData getChild(String key) {
      return (StoreSchemaData)this.jdbcTypes.get(key);
   }

   public Map getChildren() {
      return this.jdbcTypes;
   }

   public int getNumberOfChildren() {
      return this.jdbcTypes.size();
   }

   public void addProperty(String name, Object value) {
      this.properties.put(name, value);
   }

   public Object getProperty(String name) {
      return this.properties.get(name);
   }

   public StoreSchemaData getParent() {
      return null;
   }

   public void setParent(StoreSchemaData parent) {
   }

   public final boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof RDBMSTypesInfo)) {
         return false;
      } else {
         return obj == this;
      }
   }

   public final int hashCode() {
      return super.hashCode();
   }

   public String toString() {
      StringBuilder str = new StringBuilder("RDBMSTypesInfo : ");
      Iterator iter = this.properties.entrySet().iterator();

      while(iter.hasNext()) {
         Map.Entry entry = (Map.Entry)iter.next();
         str.append(entry.getKey() + " = " + entry.getValue());
         if (iter.hasNext()) {
            str.append(", ");
         }
      }

      str.append(", numJDBCTypes=" + this.jdbcTypes.size());
      return str.toString();
   }
}
