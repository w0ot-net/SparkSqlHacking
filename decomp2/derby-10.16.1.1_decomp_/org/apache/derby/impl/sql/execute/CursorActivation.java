package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.StandardException;

public abstract class CursorActivation extends BaseActivation {
   public void setCursorName(String var1) {
      if (!this.isClosed()) {
         super.setCursorName(var1);
      }

   }

   public boolean isCursorActivation() {
      return true;
   }

   ResultSet decorateResultSet() throws StandardException {
      this.getLanguageConnectionContext().getAuthorizer().authorize(this, 1);
      NoPutResultSet var1 = (NoPutResultSet)this.createResultSet();
      var1.markAsTopResultSet();
      return var1;
   }
}
