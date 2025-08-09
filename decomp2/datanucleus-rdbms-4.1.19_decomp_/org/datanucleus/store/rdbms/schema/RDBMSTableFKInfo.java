package org.datanucleus.store.rdbms.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.store.schema.ListStoreSchemaData;
import org.datanucleus.store.schema.StoreSchemaData;

public class RDBMSTableFKInfo implements ListStoreSchemaData {
   private int hash = 0;
   Map properties = new HashMap();
   List fks = new ArrayList();

   public RDBMSTableFKInfo() {
   }

   public RDBMSTableFKInfo(String catalog, String schema, String table) {
      this.addProperty("table_cat", catalog);
      this.addProperty("table_schem", schema);
      this.addProperty("table_name", table);
   }

   public void addChild(StoreSchemaData child) {
      this.fks.add(child);
   }

   public void clearChildren() {
      this.fks.clear();
   }

   public StoreSchemaData getChild(int position) {
      return (StoreSchemaData)this.fks.get(position);
   }

   public List getChildren() {
      return this.fks;
   }

   public int getNumberOfChildren() {
      return this.fks.size();
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
      } else if (!(obj instanceof RDBMSTableFKInfo)) {
         return false;
      } else {
         boolean var10000;
         label39: {
            RDBMSTableFKInfo other = (RDBMSTableFKInfo)obj;
            String cat1 = (String)this.getProperty("table_cat");
            String sch1 = (String)this.getProperty("table_schem");
            String name1 = (String)this.getProperty("table_name");
            String cat2 = (String)other.getProperty("table_cat");
            String sch2 = (String)other.getProperty("table_schem");
            String name2 = (String)other.getProperty("table_name");
            if (cat1 == null) {
               if (cat2 != null) {
                  break label39;
               }
            } else if (!cat1.equals(cat2)) {
               break label39;
            }

            if (sch1 == null) {
               if (sch2 != null) {
                  break label39;
               }
            } else if (!sch1.equals(sch2)) {
               break label39;
            }

            if (name1.equals(name2)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }

   public final int hashCode() {
      if (this.hash == 0) {
         String cat = (String)this.getProperty("table_cat");
         String sch = (String)this.getProperty("table_schem");
         String name = (String)this.getProperty("table_name");
         this.hash = (cat == null ? 0 : cat.hashCode()) ^ (sch == null ? 0 : sch.hashCode()) ^ name.hashCode();
      }

      return this.hash;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("RDBMSTableFKInfo : ");
      Iterator iter = this.properties.entrySet().iterator();

      while(iter.hasNext()) {
         Map.Entry entry = (Map.Entry)iter.next();
         str.append(entry.getKey() + " = " + entry.getValue());
         if (iter.hasNext()) {
            str.append(", ");
         }
      }

      str.append(", numFKs=" + this.fks.size());
      return str.toString();
   }
}
