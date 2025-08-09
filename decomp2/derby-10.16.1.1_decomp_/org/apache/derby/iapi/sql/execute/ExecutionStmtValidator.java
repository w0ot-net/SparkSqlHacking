package org.apache.derby.iapi.sql.execute;

import org.apache.derby.shared.common.error.StandardException;

public interface ExecutionStmtValidator {
   void validateStatement(ConstantAction var1) throws StandardException;
}
