package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

interface AggregateDefinition {
   DataTypeDescriptor getAggregator(DataTypeDescriptor var1, StringBuffer var2) throws StandardException;
}
