package org.apache.derby.vti;

import java.sql.SQLException;

public interface RestrictedVTI {
   void initScan(String[] var1, Restriction var2) throws SQLException;
}
