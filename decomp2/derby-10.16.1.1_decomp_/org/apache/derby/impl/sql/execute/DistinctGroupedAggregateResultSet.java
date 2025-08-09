package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.StandardException;

class DistinctGroupedAggregateResultSet extends GroupedAggregateResultSet {
   DistinctGroupedAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, Activation var5, int var6, int var7, int var8, double var9, double var11, boolean var13) throws StandardException {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11, var13);
   }
}
