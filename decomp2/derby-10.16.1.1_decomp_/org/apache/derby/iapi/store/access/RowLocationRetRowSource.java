package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface RowLocationRetRowSource extends RowSource {
   boolean needsRowLocation();

   boolean needsRowLocationForDeferredCheckConstraints();

   void rowLocation(RowLocation var1) throws StandardException;

   void offendingRowLocation(RowLocation var1, long var2) throws StandardException;
}
