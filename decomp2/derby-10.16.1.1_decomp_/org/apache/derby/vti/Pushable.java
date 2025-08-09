package org.apache.derby.vti;

import java.sql.SQLException;

public interface Pushable {
   boolean pushProjection(VTIEnvironment var1, int[] var2) throws SQLException;
}
