package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.schema.StoreSchemaData;

public class IndexInfo implements StoreSchemaData {
   Map properties = new HashMap();
   private int hash = 0;

   public IndexInfo(ResultSet rs) {
      try {
         this.addProperty("table_cat", rs.getString(1));
         this.addProperty("table_schem", rs.getString(2));
         this.addProperty("table_name", rs.getString(3));
         this.addProperty("non_unique", rs.getBoolean(4));
         this.addProperty("index_name", rs.getString(6));
         this.addProperty("type", rs.getShort(7));
         this.addProperty("ordinal_position", rs.getShort(8));
         this.addProperty("column_name", rs.getString(9));
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
      } else if (!(obj instanceof IndexInfo)) {
         return false;
      } else {
         boolean var10000;
         label55: {
            label54: {
               label61: {
                  IndexInfo other = (IndexInfo)obj;
                  String tableCat1 = (String)this.getProperty("table_cat");
                  String tableSch1 = (String)this.getProperty("table_schema");
                  String tableName1 = (String)this.getProperty("table_name");
                  String columnName1 = (String)this.getProperty("column_name");
                  String indexName1 = (String)this.getProperty("index_name");
                  String tableCat2 = (String)other.getProperty("table_cat");
                  String tableSch2 = (String)other.getProperty("table_schema");
                  String tableName2 = (String)other.getProperty("table_name");
                  String columnName2 = (String)other.getProperty("column_name");
                  String indexName2 = (String)other.getProperty("index_name");
                  if (tableCat1 == null) {
                     if (tableCat2 != null) {
                        break label61;
                     }
                  } else if (!tableCat1.equals(tableCat2)) {
                     break label61;
                  }

                  if (tableSch1 == null) {
                     if (tableSch2 != null) {
                        break label61;
                     }
                  } else if (!tableSch1.equals(tableSch2)) {
                     break label61;
                  }

                  if (tableName1 == null) {
                     if (tableName2 != null) {
                        break label61;
                     }
                  } else if (!tableName1.equals(tableName2)) {
                     break label61;
                  }

                  if (columnName1 == null) {
                     if (columnName2 != null) {
                        break label61;
                     }
                  } else if (!columnName1.equals(columnName2)) {
                     break label61;
                  }

                  if (indexName1 == null) {
                     if (indexName2 == null) {
                        break label54;
                     }
                  } else if (indexName1.equals(indexName2)) {
                     break label54;
                  }
               }

               var10000 = false;
               break label55;
            }

            var10000 = true;
         }

         boolean equals = var10000;
         return equals;
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
      str.append("  tableCat        = " + this.getProperty("table_cat") + "\n");
      str.append("  tableSchem      = " + this.getProperty("table_schema") + "\n");
      str.append("  tableName       = " + this.getProperty("table_name") + "\n");
      str.append("  columnName      = " + this.getProperty("column_name") + "\n");
      str.append("  nonUnique       = " + this.getProperty("non_unique") + "\n");
      str.append("  ordinalPosition = " + this.getProperty("ordinal_position") + "\n");
      str.append("  indexName       = " + this.getProperty("index_name") + "\n");
      return str.toString();
   }
}
