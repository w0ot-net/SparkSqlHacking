package org.apache.derby.iapi.sql;

import java.sql.ResultSetMetaData;

public interface ResultDescription {
   String getStatementType();

   int getColumnCount();

   ResultColumnDescriptor[] getColumnInfo();

   ResultColumnDescriptor getColumnInfo(int var1);

   ResultColumnDescriptor getColumnDescriptor(int var1);

   ResultDescription truncateColumns(int var1);

   void setMetaData(ResultSetMetaData var1);

   ResultSetMetaData getMetaData();

   int findColumnInsenstive(String var1);
}
