package org.apache.derby.iapi.store.access;

import org.apache.derby.shared.common.error.StandardException;

public interface RowCountable {
   long getEstimatedRowCount() throws StandardException;

   void setEstimatedRowCount(long var1) throws StandardException;
}
