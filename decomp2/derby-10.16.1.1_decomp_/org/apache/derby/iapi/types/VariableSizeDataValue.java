package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

public interface VariableSizeDataValue {
   int IGNORE_PRECISION = -1;

   void setWidth(int var1, int var2, boolean var3) throws StandardException;
}
