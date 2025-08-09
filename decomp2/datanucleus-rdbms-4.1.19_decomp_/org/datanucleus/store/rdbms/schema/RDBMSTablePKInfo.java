package org.datanucleus.store.rdbms.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.store.schema.ListStoreSchemaData;
import org.datanucleus.store.schema.StoreSchemaData;

public class RDBMSTablePKInfo implements ListStoreSchemaData {
   private int hash = 0;
   Map properties = new HashMap();
   List pks = new ArrayList();

   public RDBMSTablePKInfo() {
   }

   public RDBMSTablePKInfo(String catalog, String schema, String table) {
      this.addProperty("table_cat", catalog);
      this.addProperty("table_schem", schema);
      this.addProperty("table_name", table);
   }

   public void addChild(StoreSchemaData child) {
      this.pks.add(child);
   }

   public void clearChildren() {
      this.pks.clear();
   }

   public StoreSchemaData getChild(int position) {
      return (StoreSchemaData)this.pks.get(position);
   }

   public List getChildren() {
      return this.pks;
   }

   public int getNumberOfChildren() {
      return this.pks.size();
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
      } else if (!(obj instanceof RDBMSTablePKInfo)) {
         return false;
      } else {
         boolean var10000;
         label39: {
            RDBMSTablePKInfo other = (RDBMSTablePKInfo)obj;
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
      StringBuilder str = new StringBuilder("RDBMSTablePKInfo : ");
      Iterator iter = this.properties.entrySet().iterator();

      while(iter.hasNext()) {
         Map.Entry entry = (Map.Entry)iter.next();
         str.append(entry.getKey() + " = " + entry.getValue());
         if (iter.hasNext()) {
            str.append(", ");
         }
      }

      str.append(", numPKs=" + this.pks.size());
      return str.toString();
   }
}
