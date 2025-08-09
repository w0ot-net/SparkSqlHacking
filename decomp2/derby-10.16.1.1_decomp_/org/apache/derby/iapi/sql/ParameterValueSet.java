package org.apache.derby.iapi.sql;

import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface ParameterValueSet {
   void initialize(DataTypeDescriptor[] var1) throws StandardException;

   void setParameterMode(int var1, int var2);

   void registerOutParameter(int var1, int var2, int var3) throws StandardException;

   void clearParameters();

   int getParameterCount();

   DataValueDescriptor getParameter(int var1) throws StandardException;

   DataValueDescriptor getParameterForSet(int var1) throws StandardException;

   void setParameterAsObject(int var1, Object var2) throws StandardException;

   DataValueDescriptor getParameterForGet(int var1) throws StandardException;

   boolean allAreSet();

   ParameterValueSet getClone();

   void validate() throws StandardException;

   boolean hasReturnOutputParameter();

   boolean checkNoDeclaredOutputParameters();

   void transferDataValues(ParameterValueSet var1) throws StandardException;

   short getParameterMode(int var1);

   DataValueDescriptor getReturnValueForSet() throws StandardException;

   int getScale(int var1);

   int getPrecision(int var1);
}
