package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface NoPutResultSet extends ResultSet, RowLocationRetRowSource {
   String ABSOLUTE = "absolute";
   String RELATIVE = "relative";
   String FIRST = "first";
   String NEXT = "next";
   String LAST = "last";
   String PREVIOUS = "previous";
   int TEMPORARY_RESULT_SET_NUMBER = 0;

   void markAsTopResultSet();

   void openCore() throws StandardException;

   void reopenCore() throws StandardException;

   ExecRow getNextRowCore() throws StandardException;

   int getPointOfAttachment();

   int getScanIsolationLevel();

   void setTargetResultSet(TargetResultSet var1);

   void setNeedsRowLocation(boolean var1);

   void setHasDeferrableChecks();

   double getEstimatedRowCount();

   int resultSetNumber();

   void setCurrentRow(ExecRow var1);

   boolean requiresRelocking();

   boolean isForUpdate();

   void updateRow(ExecRow var1, RowChanger var2) throws StandardException;

   void markRowAsDeleted() throws StandardException;

   void positionScanAtRowLocation(RowLocation var1) throws StandardException;
}
