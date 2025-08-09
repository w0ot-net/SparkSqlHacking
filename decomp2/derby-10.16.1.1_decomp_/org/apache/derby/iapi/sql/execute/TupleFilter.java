package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.types.BooleanDataValue;
import org.apache.derby.shared.common.error.StandardException;

public interface TupleFilter {
   void init(ExecRow var1) throws StandardException;

   BooleanDataValue execute(ExecRow var1) throws StandardException;
}
