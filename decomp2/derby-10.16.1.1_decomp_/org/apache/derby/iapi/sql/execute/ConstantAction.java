package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.shared.common.error.StandardException;

public interface ConstantAction {
   int WHEN_NOT_MATCHED_THEN_INSERT = 0;
   int WHEN_MATCHED_THEN_UPDATE = 1;
   int WHEN_MATCHED_THEN_DELETE = 2;

   void executeConstantAction(Activation var1) throws StandardException;
}
