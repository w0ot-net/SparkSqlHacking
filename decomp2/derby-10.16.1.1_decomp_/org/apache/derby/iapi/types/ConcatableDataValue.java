package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

public interface ConcatableDataValue extends DataValueDescriptor, VariableSizeDataValue {
   NumberDataValue charLength(NumberDataValue var1) throws StandardException;

   ConcatableDataValue substring(NumberDataValue var1, NumberDataValue var2, ConcatableDataValue var3, int var4) throws StandardException;
}
