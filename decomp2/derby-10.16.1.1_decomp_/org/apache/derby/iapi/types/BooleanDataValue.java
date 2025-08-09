package org.apache.derby.iapi.types;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.shared.common.error.StandardException;

public interface BooleanDataValue extends DataValueDescriptor {
   boolean getBoolean();

   BooleanDataValue and(BooleanDataValue var1);

   BooleanDataValue or(BooleanDataValue var1);

   BooleanDataValue is(BooleanDataValue var1);

   BooleanDataValue isNot(BooleanDataValue var1);

   BooleanDataValue throwExceptionIfFalse(String var1, String var2, String var3) throws StandardException;

   BooleanDataValue throwExceptionIfImmediateAndFalse(String var1, String var2, String var3, Activation var4, int var5) throws StandardException;

   void setValue(Boolean var1);

   boolean equals(boolean var1);

   BooleanDataValue getImmutable();
}
