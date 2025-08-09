package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.shared.common.error.StandardException;

public final class ConstantActionActivation extends BaseActivation {
   protected boolean shouldWeCheckRowCounts() {
      return false;
   }

   protected ResultSet createResultSet() throws StandardException {
      return this.getResultSetFactory().getDDLResultSet(this);
   }

   public void postConstructor() {
   }
}
