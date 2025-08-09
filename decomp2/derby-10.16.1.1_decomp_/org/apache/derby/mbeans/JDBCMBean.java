package org.apache.derby.mbeans;

import java.sql.SQLException;

public interface JDBCMBean {
   String getDriverLevel();

   int getMajorVersion();

   int getMinorVersion();

   boolean isCompliantDriver();

   boolean acceptsURL(String var1) throws SQLException;
}
