package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

public interface NumberDataValue extends DataValueDescriptor {
   int MIN_DECIMAL_DIVIDE_SCALE = 4;
   int MAX_DECIMAL_PRECISION_SCALE = 31;

   NumberDataValue plus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException;

   NumberDataValue minus(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException;

   NumberDataValue times(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException;

   NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException;

   NumberDataValue divide(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3, int var4) throws StandardException;

   NumberDataValue mod(NumberDataValue var1, NumberDataValue var2, NumberDataValue var3) throws StandardException;

   NumberDataValue minus(NumberDataValue var1) throws StandardException;

   NumberDataValue absolute(NumberDataValue var1) throws StandardException;

   NumberDataValue sqrt(NumberDataValue var1) throws StandardException;

   void setValue(Number var1) throws StandardException;
}
