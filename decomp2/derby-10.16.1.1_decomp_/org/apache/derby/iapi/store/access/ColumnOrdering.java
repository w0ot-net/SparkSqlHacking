package org.apache.derby.iapi.store.access;

public interface ColumnOrdering {
   int getColumnId();

   boolean getIsAscending();

   boolean getIsNullsOrderedLow();
}
