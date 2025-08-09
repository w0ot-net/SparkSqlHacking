package org.apache.derby.vti;

import java.sql.SQLException;

public interface VTICosting {
   double defaultEstimatedRowCount = (double)10000.0F;
   double defaultEstimatedCost = (double)100000.0F;

   double getEstimatedRowCount(VTIEnvironment var1) throws SQLException;

   double getEstimatedCostPerInstantiation(VTIEnvironment var1) throws SQLException;

   boolean supportsMultipleInstantiations(VTIEnvironment var1) throws SQLException;
}
