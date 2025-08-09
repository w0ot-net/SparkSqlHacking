package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.shared.common.error.StandardException;

public interface ResultSetStatisticsFactory {
   String MODULE = "org.apache.derby.iapi.sql.execute.ResultSetStatisticsFactory";

   RunTimeStatistics getRunTimeStatistics(Activation var1, ResultSet var2, NoPutResultSet[] var3) throws StandardException;

   ResultSetStatistics getResultSetStatistics(ResultSet var1);

   ResultSetStatistics getResultSetStatistics(NoPutResultSet var1);

   ResultSetStatistics getNoRowsResultSetStatistics(ResultSet var1);
}
