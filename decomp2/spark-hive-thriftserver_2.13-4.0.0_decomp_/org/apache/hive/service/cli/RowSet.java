package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TRowSet;

public interface RowSet extends Iterable {
   RowSet addRow(Object[] var1);

   RowSet extractSubset(int var1);

   int numColumns();

   int numRows();

   long getStartOffset();

   void setStartOffset(long var1);

   TRowSet toTRowSet();
}
