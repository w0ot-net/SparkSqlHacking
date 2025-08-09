package org.datanucleus.store.rdbms.schema;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.schema.MapStoreSchemaData;
import org.datanucleus.store.schema.StoreSchemaData;

public class RDBMSSchemaInfo implements MapStoreSchemaData {
   private int hash = 0;
   Map properties = new HashMap();
   Map tables = new HashMap();

   public RDBMSSchemaInfo(String catalog, String schema) {
      this.addProperty("catalog", catalog);
      this.addProperty("schema", schema);
   }

   public void addChild(StoreSchemaData data) {
      RDBMSTableInfo table = (RDBMSTableInfo)data;
      String tableKey = (String)data.getProperty("table_key");
      if (tableKey == null) {
         throw new NucleusException("Attempt to add RDBMSTableInfo to RDBMSSchemaInfo with null table key! tableName=" + data.getProperty("table_name"));
      } else {
         this.tables.put(tableKey, table);
      }
   }

   public void clearChildren() {
      this.tables.clear();
   }

   public StoreSchemaData getChild(String key) {
      return (StoreSchemaData)this.tables.get(key);
   }

   public Map getChildren() {
      return this.tables;
   }

   public int getNumberOfChildren() {
      return this.tables.size();
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
      } else if (!(obj instanceof RDBMSSchemaInfo)) {
         return false;
      } else {
         boolean var10000;
         label43: {
            label29: {
               RDBMSSchemaInfo other = (RDBMSSchemaInfo)obj;
               String cat1 = (String)this.getProperty("table_cat");
               String sch1 = (String)this.getProperty("table_schem");
               String cat2 = (String)other.getProperty("table_cat");
               String sch2 = (String)other.getProperty("table_schem");
               if (cat1 == null) {
                  if (cat2 != null) {
                     break label29;
                  }
               } else if (!cat1.equals(cat2)) {
                  break label29;
               }

               if (sch1 == null) {
                  if (sch2 == null) {
                     break label43;
                  }
               } else if (sch1.equals(sch2)) {
                  break label43;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public final int hashCode() {
      if (this.hash == 0) {
         String cat = (String)this.getProperty("table_cat");
         String sch = (String)this.getProperty("table_schem");
         this.hash = (cat == null ? 0 : cat.hashCode()) ^ (sch == null ? 0 : sch.hashCode());
      }

      return this.hash;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("RDBMSSchemaInfo : ");
      Iterator iter = this.properties.entrySet().iterator();

      while(iter.hasNext()) {
         Map.Entry entry = (Map.Entry)iter.next();
         str.append(entry.getKey() + " = " + entry.getValue());
         if (iter.hasNext()) {
            str.append(", ");
         }
      }

      str.append(", numTables=" + this.tables.size());
      return str.toString();
   }
}
