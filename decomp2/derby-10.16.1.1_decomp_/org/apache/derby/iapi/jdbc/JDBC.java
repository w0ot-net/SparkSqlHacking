package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;
import org.apache.derby.mbeans.JDBCMBean;
import org.apache.derby.shared.common.info.JVMInfo;

public final class JDBC implements JDBCMBean {
   private final InternalDriver driver;

   public JDBC(InternalDriver var1) {
      this.driver = var1;
   }

   public String getDriverLevel() {
      return JVMInfo.derbyVMLevel();
   }

   public int getMajorVersion() {
      return this.driver.getMajorVersion();
   }

   public int getMinorVersion() {
      return this.driver.getMinorVersion();
   }

   public boolean isCompliantDriver() {
      return this.driver.jdbcCompliant();
   }

   public boolean acceptsURL(String var1) throws SQLException {
      return this.driver.acceptsURL(var1);
   }
}
