package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface ExecAggregator extends Formatable {
   void setup(ClassFactory var1, String var2, DataTypeDescriptor var3);

   void accumulate(DataValueDescriptor var1, Object var2) throws StandardException;

   void merge(ExecAggregator var1) throws StandardException;

   DataValueDescriptor getResult() throws StandardException;

   ExecAggregator newAggregator();

   boolean didEliminateNulls();
}
