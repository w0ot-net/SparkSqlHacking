package org.datanucleus.store.rdbms.schema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.store.schema.MapStoreSchemaData;
import org.datanucleus.store.schema.StoreSchemaData;

public class JDBCTypeInfo implements MapStoreSchemaData {
   private int hash = 0;
   Map properties = new HashMap();
   Map sqlTypes = new HashMap();

   public JDBCTypeInfo(short type) {
      this.addProperty("jdbc_type", type);
   }

   public void setParent(StoreSchemaData parent) {
   }

   public StoreSchemaData getParent() {
      return null;
   }

   public void addProperty(String name, Object value) {
      this.properties.put(name, value);
   }

   public Object getProperty(String name) {
      return this.properties.get(name);
   }

   public void addChild(StoreSchemaData child) {
      SQLTypeInfo sqlType = (SQLTypeInfo)child;
      this.sqlTypes.put(sqlType.getTypeName(), sqlType);
      if (this.sqlTypes.size() == 1) {
         this.sqlTypes.put("DEFAULT", sqlType);
      }

   }

   public void clearChildren() {
      this.sqlTypes.clear();
   }

   public StoreSchemaData getChild(String key) {
      return (StoreSchemaData)this.sqlTypes.get(key);
   }

   public Map getChildren() {
      return this.sqlTypes;
   }

   public int getNumberOfChildren() {
      return this.sqlTypes.size();
   }

   public final boolean equals(Object obj) {
      if (!(obj instanceof JDBCTypeInfo)) {
         return false;
      } else {
         JDBCTypeInfo other = (JDBCTypeInfo)obj;
         short jdbcType1 = (Short)this.getProperty("jdbc_type");
         short jdbcType2 = (Short)other.getProperty("jdbc_type");
         return jdbcType1 == jdbcType2;
      }
   }

   public final int hashCode() {
      if (this.hash == 0) {
         short jdbcType1 = (Short)this.getProperty("jdbc_type");
         this.hash = jdbcType1;
      }

      return this.hash;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("JDBCTypeInfo : ");
      Iterator iter = this.properties.entrySet().iterator();

      while(iter.hasNext()) {
         Map.Entry entry = (Map.Entry)iter.next();
         str.append(entry.getKey() + " = " + entry.getValue());
         if (iter.hasNext()) {
            str.append(", ");
         }
      }

      str.append(", numSQLTypes=" + this.sqlTypes.size());
      return str.toString();
   }
}
