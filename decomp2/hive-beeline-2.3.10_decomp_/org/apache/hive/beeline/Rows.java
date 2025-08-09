package org.apache.hive.beeline;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Iterator;

abstract class Rows implements Iterator {
   protected final BeeLine beeLine;
   final ResultSetMetaData rsMeta;
   final Boolean[] primaryKeys;
   final NumberFormat numberFormat;
   private final String nullStr;

   Rows(BeeLine beeLine, ResultSet rs) throws SQLException {
      this.beeLine = beeLine;
      this.nullStr = beeLine.getOpts().getNullString();
      this.rsMeta = rs.getMetaData();
      int count = this.rsMeta.getColumnCount();
      this.primaryKeys = new Boolean[count];
      if (beeLine.getOpts().getNumberFormat().equals("default")) {
         this.numberFormat = null;
      } else {
         this.numberFormat = new DecimalFormat(beeLine.getOpts().getNumberFormat());
      }

   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   abstract void normalizeWidths();

   boolean isPrimaryKey(int col) {
      if (this.primaryKeys[col] != null) {
         return this.primaryKeys[col];
      } else {
         try {
            String table = this.rsMeta.getTableName(col + 1);
            String column = this.rsMeta.getColumnName(col + 1);
            if (table != null && table.length() != 0 && column != null && column.length() != 0) {
               ResultSet pks = this.beeLine.getDatabaseConnection().getDatabaseMetaData().getPrimaryKeys(this.beeLine.getDatabaseConnection().getDatabaseMetaData().getConnection().getCatalog(), (String)null, table);

               try {
                  while(pks.next()) {
                     if (column.equalsIgnoreCase(pks.getString("COLUMN_NAME"))) {
                        boolean var5 = this.primaryKeys[col] = new Boolean(true);
                        return var5;
                     }
                  }

                  return this.primaryKeys[col] = new Boolean(false);
               } finally {
                  pks.close();
               }
            } else {
               return this.primaryKeys[col] = new Boolean(false);
            }
         } catch (SQLException var10) {
            return this.primaryKeys[col] = new Boolean(false);
         }
      }
   }

   class Row {
      final String[] values;
      final boolean isMeta;
      boolean deleted;
      boolean inserted;
      boolean updated;
      int[] sizes;

      Row(int size) throws SQLException {
         this.isMeta = true;
         this.values = new String[size];
         this.sizes = new int[size];

         for(int i = 0; i < size; ++i) {
            this.values[i] = Rows.this.rsMeta.getColumnLabel(i + 1);
            this.sizes[i] = this.values[i] == null ? 1 : this.values[i].length();
         }

         this.deleted = false;
         this.updated = false;
         this.inserted = false;
      }

      public String toString() {
         return Arrays.asList(this.values).toString();
      }

      Row(int size, ResultSet rs) throws SQLException {
         this.isMeta = false;
         this.values = new String[size];
         this.sizes = new int[size];

         try {
            this.deleted = rs.rowDeleted();
         } catch (Throwable var8) {
         }

         try {
            this.updated = rs.rowUpdated();
         } catch (Throwable var7) {
         }

         try {
            this.inserted = rs.rowInserted();
         } catch (Throwable var6) {
         }

         for(int i = 0; i < size; ++i) {
            if (Rows.this.numberFormat != null) {
               Object o = rs.getObject(i + 1);
               if (o == null) {
                  this.values[i] = null;
               } else if (o instanceof Number) {
                  this.values[i] = Rows.this.numberFormat.format(o);
               } else {
                  this.values[i] = o.toString();
               }
            } else {
               this.values[i] = rs.getString(i + 1);
            }

            this.values[i] = this.values[i] == null ? Rows.this.nullStr : this.values[i];
            this.sizes[i] = this.values[i].length();
         }

      }
   }
}
