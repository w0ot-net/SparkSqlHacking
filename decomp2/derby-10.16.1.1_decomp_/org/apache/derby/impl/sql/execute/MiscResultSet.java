package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.shared.common.error.StandardException;

class MiscResultSet extends NoRowsResultSetImpl {
   MiscResultSet(Activation var1) {
      super(var1);
   }

   public void open() throws StandardException {
      this.setup();
      this.activation.getConstantAction().executeConstantAction(this.activation);
      this.close();
   }

   public void close() throws StandardException {
      this.close(false);
   }

   public void cleanUp() {
   }
}
