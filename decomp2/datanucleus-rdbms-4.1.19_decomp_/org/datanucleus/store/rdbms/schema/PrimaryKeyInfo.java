package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.schema.StoreSchemaData;

public class PrimaryKeyInfo implements StoreSchemaData {
   Map properties = new HashMap();
   private int hash = 0;

   public PrimaryKeyInfo(ResultSet rs) {
      try {
         this.addProperty("table_cat", rs.getString(1));
         this.addProperty("table_schem", rs.getString(2));
         this.addProperty("table_name", rs.getString(3));
         this.addProperty("column_name", rs.getString(4));
         this.addProperty("key_seq", rs.getShort(5));
         this.addProperty("pk_name", rs.getString(6));
      } catch (SQLException e) {
         throw (new NucleusDataStoreException("Can't read JDBC metadata from result set", e)).setFatal();
      }
   }

   public void addProperty(String name, Object value) {
      if (name != null && value != null) {
         this.properties.put(name, value);
      }

   }

   public Object getProperty(String name) {
      return this.properties.get(name);
   }

   public final boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof PrimaryKeyInfo)) {
         return false;
      } else {
         boolean var10000;
         label80: {
            label71: {
               PrimaryKeyInfo other = (PrimaryKeyInfo)obj;
               String tableCat1 = (String)this.getProperty("table_cat");
               String tableSch1 = (String)this.getProperty("table_schema");
               String tableName1 = (String)this.getProperty("table_name");
               String columnName1 = (String)this.getProperty("column_name");
               String pkName1 = (String)this.getProperty("pk_name");
               String tableCat2 = (String)other.getProperty("table_cat");
               String tableSch2 = (String)other.getProperty("table_schema");
               String tableName2 = (String)other.getProperty("table_name");
               String columnName2 = (String)other.getProperty("column_name");
               String pkName2 = (String)other.getProperty("pk_name");
               if (tableCat1 == null) {
                  if (tableCat2 != null) {
                     break label71;
                  }
               } else if (!tableCat1.equals(tableCat2)) {
                  break label71;
               }

               if (tableSch1 == null) {
                  if (tableSch2 != null) {
                     break label71;
                  }
               } else if (!tableSch1.equals(tableSch2)) {
                  break label71;
               }

               if (tableName1.equals(tableName2) && columnName1.equals(columnName2)) {
                  label75: {
                     if (tableCat1 == null) {
                        if (tableCat2 != null) {
                           break label75;
                        }
                     } else if (!tableCat1.equals(tableCat2)) {
                        break label75;
                     }

                     if (tableSch1 == null) {
                        if (tableSch2 != null) {
                           break label75;
                        }
                     } else if (!tableSch1.equals(tableSch2)) {
                        break label75;
                     }

                     if (tableName1.equals(tableName2) && columnName1.equals(columnName2)) {
                        if (pkName1 == null) {
                           if (pkName2 == null) {
                              break label80;
                           }
                        } else if (pkName1.equals(pkName2)) {
                           break label80;
                        }
                     }
                  }
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
         String tableCat = (String)this.getProperty("table_cat");
         String tableSch = (String)this.getProperty("table_schema");
         String tableName = (String)this.getProperty("table_name");
         String columnName = (String)this.getProperty("column_name");
         this.hash = (tableCat == null ? 0 : tableCat.hashCode()) ^ (tableSch == null ? 0 : tableSch.hashCode()) ^ tableName.hashCode() ^ columnName.hashCode();
      }

      return this.hash;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(this.getClass().getName() + "\n");
      str.append("  tableCat    = " + this.getProperty("table_cat") + "\n");
      str.append("  tableSchem  = " + this.getProperty("table_schema") + "\n");
      str.append("  tableName   = " + this.getProperty("table_name") + "\n");
      str.append("  columnName  = " + this.getProperty("column_name") + "\n");
      str.append("  keySeq      = " + this.getProperty("key_seq") + "\n");
      str.append("  pkName      = " + this.getProperty("pk_name") + "\n");
      return str.toString();
   }
}
