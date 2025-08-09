package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.schema.StoreSchemaData;

public class ForeignKeyInfo implements StoreSchemaData {
   Map properties = new HashMap();
   private int hash = 0;

   public ForeignKeyInfo(ResultSet rs) {
      try {
         this.addProperty("pk_table_cat", rs.getString(1));
         this.addProperty("pk_table_schem", rs.getString(2));
         this.addProperty("pk_table_name", rs.getString(3));
         this.addProperty("pk_column_name", rs.getString(4));
         this.addProperty("fk_table_cat", rs.getString(5));
         this.addProperty("fk_table_schem", rs.getString(6));
         this.addProperty("fk_table_name", rs.getString(7));
         this.addProperty("fk_column_name", rs.getString(8));
         this.addProperty("key_seq", rs.getShort(9));
         this.addProperty("update_rule", rs.getShort(10));
         this.addProperty("delete_rule", rs.getShort(11));
         this.addProperty("fk_name", rs.getString(12));
         this.addProperty("pk_name", rs.getString(13));
         this.addProperty("deferrability", rs.getShort(14));
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
      } else if (!(obj instanceof ForeignKeyInfo)) {
         return false;
      } else {
         boolean var10000;
         label92: {
            label81: {
               ForeignKeyInfo other = (ForeignKeyInfo)obj;
               String pkTableCat1 = (String)this.getProperty("pk_table_cat");
               String pkTableSch1 = (String)this.getProperty("pk_table_schema");
               String pkTableName1 = (String)this.getProperty("pk_table_name");
               String pkColumnName1 = (String)this.getProperty("pk_column_name");
               String fkTableCat1 = (String)this.getProperty("fk_table_cat");
               String fkTableSch1 = (String)this.getProperty("fk_table_schema");
               String fkTableName1 = (String)this.getProperty("fk_table_name");
               String fkColumnName1 = (String)this.getProperty("fk_column_name");
               String pkName1 = (String)this.getProperty("pk_name");
               String fkName1 = (String)this.getProperty("fk_name");
               String pkTableCat2 = (String)other.getProperty("pk_table_cat");
               String pkTableSch2 = (String)other.getProperty("pk_table_schema");
               String pkTableName2 = (String)other.getProperty("pk_table_name");
               String pkColumnName2 = (String)other.getProperty("pk_column_name");
               String fkTableCat2 = (String)other.getProperty("fk_table_cat");
               String fkTableSch2 = (String)other.getProperty("fk_table_schema");
               String fkTableName2 = (String)other.getProperty("fk_table_name");
               String fkColumnName2 = (String)other.getProperty("fk_column_name");
               String pkName2 = (String)other.getProperty("pk_name");
               String fkName2 = (String)other.getProperty("fk_name");
               if (pkTableCat1 == null) {
                  if (pkTableCat2 != null) {
                     break label81;
                  }
               } else if (!pkTableCat1.equals(pkTableCat2)) {
                  break label81;
               }

               if (pkTableSch1 == null) {
                  if (pkTableSch2 != null) {
                     break label81;
                  }
               } else if (!pkTableSch1.equals(pkTableSch2)) {
                  break label81;
               }

               if (pkTableName1.equals(pkTableName2) && pkColumnName1.equals(pkColumnName2)) {
                  label85: {
                     if (fkTableCat1 == null) {
                        if (fkTableCat2 != null) {
                           break label85;
                        }
                     } else if (!fkTableCat1.equals(fkTableCat2)) {
                        break label85;
                     }

                     if (fkTableSch1 == null) {
                        if (fkTableSch2 != null) {
                           break label85;
                        }
                     } else if (!fkTableSch1.equals(fkTableSch2)) {
                        break label85;
                     }

                     if (fkTableName1.equals(fkTableName2) && fkColumnName1.equals(fkColumnName2)) {
                        label87: {
                           if (fkName1 == null) {
                              if (fkName2 != null) {
                                 break label87;
                              }
                           } else if (!fkName1.equals(fkName2)) {
                              break label87;
                           }

                           if (pkName1 == null) {
                              if (pkName2 == null) {
                                 break label92;
                              }
                           } else if (pkName1.equals(pkName2)) {
                              break label92;
                           }
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
         String pkTableCat = (String)this.getProperty("pk_table_cat");
         String pkTableSch = (String)this.getProperty("pk_table_schema");
         String pkTableName = (String)this.getProperty("pk_table_name");
         String pkColumnName = (String)this.getProperty("pk_column_name");
         String fkTableCat = (String)this.getProperty("fk_table_cat");
         String fkTableSch = (String)this.getProperty("fk_table_schema");
         String fkTableName = (String)this.getProperty("fk_table_name");
         String fkColumnName = (String)this.getProperty("fk_column_name");
         this.hash = (pkTableCat == null ? 0 : pkTableCat.hashCode()) ^ (pkTableSch == null ? 0 : pkTableSch.hashCode()) ^ pkTableName.hashCode() ^ pkColumnName.hashCode() ^ (fkTableCat == null ? 0 : fkTableCat.hashCode()) ^ (fkTableSch == null ? 0 : fkTableSch.hashCode()) ^ fkTableName.hashCode() ^ fkColumnName.hashCode();
      }

      return this.hash;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(this.getClass().getName() + "\n");
      str.append("  pkTableCat    = " + this.getProperty("pk_table_cat") + "\n");
      str.append("  pkTableSchem  = " + this.getProperty("pk_table_schema") + "\n");
      str.append("  pkTableName   = " + this.getProperty("pk_table_name") + "\n");
      str.append("  pkColumnName  = " + this.getProperty("pk_column_name") + "\n");
      str.append("  fkTableCat    = " + this.getProperty("fk_table_cat") + "\n");
      str.append("  fkTableSchem  = " + this.getProperty("fk_table_schema") + "\n");
      str.append("  fkTableName   = " + this.getProperty("fk_table_name") + "\n");
      str.append("  fkColumnName  = " + this.getProperty("fk_column_name") + "\n");
      str.append("  keySeq        = " + this.getProperty("key_seq") + "\n");
      str.append("  updateRule    = " + this.getProperty("update_rule") + "\n");
      str.append("  deleteRule    = " + this.getProperty("delete_rule") + "\n");
      str.append("  fkName        = " + this.getProperty("fk_name") + "\n");
      str.append("  pkName        = " + this.getProperty("pk_name") + "\n");
      str.append("  deferrability = " + this.getProperty("deferrability") + "\n");
      return str.toString();
   }
}
