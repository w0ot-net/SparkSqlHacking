package org.apache.hive.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class HiveMetaDataResultSet extends HiveBaseResultSet {
   protected final List data;

   public HiveMetaDataResultSet(List columnNames, List columnTypes, List data) throws SQLException {
      if (data != null) {
         this.data = new ArrayList(data);
      } else {
         this.data = new ArrayList();
      }

      if (columnNames != null) {
         this.columnNames = new ArrayList(columnNames);
         this.normalizedColumnNames = new ArrayList();

         for(String colName : columnNames) {
            this.normalizedColumnNames.add(colName.toLowerCase());
         }
      } else {
         this.columnNames = new ArrayList();
         this.normalizedColumnNames = new ArrayList();
      }

      if (columnTypes != null) {
         this.columnTypes = new ArrayList(columnTypes);
      } else {
         this.columnTypes = new ArrayList();
      }

   }

   public void close() throws SQLException {
   }
}
