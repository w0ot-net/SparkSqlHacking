package org.apache.derby.iapi.services.diag;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public class DiagnosticableGeneric implements Diagnosticable {
   protected Object diag_object = null;

   public void init(Object var1) {
      this.diag_object = var1;
   }

   public String diag() throws StandardException {
      return this.diag_object.toString();
   }

   public void diag_detail(Properties var1) throws StandardException {
   }
}
