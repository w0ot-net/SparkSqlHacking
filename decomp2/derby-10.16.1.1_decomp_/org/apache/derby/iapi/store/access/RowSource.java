package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface RowSource {
   DataValueDescriptor[] getNextRowFromRowSource() throws StandardException;

   boolean needsToClone();

   FormatableBitSet getValidColumns();

   void closeRowSource();
}
