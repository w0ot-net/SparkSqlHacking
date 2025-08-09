package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.shared.common.error.StandardException;

public interface BulkInsertCounter {
   void getCurrentValueAndAdvance(NumberDataValue var1) throws StandardException;

   Long peekAtCurrentValue() throws StandardException;
}
