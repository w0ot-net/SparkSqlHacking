package org.apache.derby.iapi.store.raw;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.shared.common.error.StandardException;

public interface Page {
   int FIRST_SLOT_NUMBER = 0;
   int INVALID_SLOT_NUMBER = -1;
   byte INSERT_INITIAL = 0;
   byte INSERT_DEFAULT = 1;
   byte INSERT_UNDO_WITH_PURGE = 2;
   byte INSERT_CONDITIONAL = 4;
   byte INSERT_OVERFLOW = 8;
   byte INSERT_FOR_SPLIT = 16;
   String DIAG_PAGE_SIZE = "pageSize";
   String DIAG_RESERVED_SPACE = "reserveSpace";
   String DIAG_MINIMUM_REC_SIZE = "minRecSize";
   String DIAG_BYTES_FREE = "bytesFree";
   String DIAG_BYTES_RESERVED = "bytesReserved";
   String DIAG_NUMOVERFLOWED = "numOverFlowed";
   String DIAG_ROWSIZE = "rowSize";
   String DIAG_MINROWSIZE = "minRowSize";
   String DIAG_MAXROWSIZE = "maxRowSize";
   String DIAG_PAGEOVERHEAD = "pageOverhead";
   String DIAG_SLOTTABLE_SIZE = "slotTableSize";

   long getPageNumber();

   PageKey getPageKey();

   RecordHandle getInvalidRecordHandle();

   RecordHandle makeRecordHandle(int var1) throws StandardException;

   RecordHandle getRecordHandle(int var1);

   boolean recordExists(RecordHandle var1, boolean var2) throws StandardException;

   boolean spaceForInsert() throws StandardException;

   boolean spaceForInsert(Object[] var1, FormatableBitSet var2, int var3) throws StandardException;

   RecordHandle insert(Object[] var1, FormatableBitSet var2, byte var3, int var4) throws StandardException;

   int moveRecordForCompressAtSlot(int var1, Object[] var2, RecordHandle[] var3, RecordHandle[] var4) throws StandardException;

   int fetchNumFields(RecordHandle var1) throws StandardException;

   int getSlotNumber(RecordHandle var1) throws StandardException;

   RecordHandle getRecordHandleAtSlot(int var1) throws StandardException;

   int getNextSlotNumber(RecordHandle var1) throws StandardException;

   RecordHandle insertAtSlot(int var1, Object[] var2, FormatableBitSet var3, LogicalUndo var4, byte var5, int var6) throws StandardException;

   RecordHandle fetchFromSlot(RecordHandle var1, int var2, Object[] var3, FetchDescriptor var4, boolean var5) throws StandardException;

   RecordHandle fetchFieldFromSlot(int var1, int var2, Object var3) throws StandardException;

   boolean isDeletedAtSlot(int var1) throws StandardException;

   RecordHandle updateFieldAtSlot(int var1, int var2, Object var3, LogicalUndo var4) throws StandardException;

   int fetchNumFieldsAtSlot(int var1) throws StandardException;

   RecordHandle deleteAtSlot(int var1, boolean var2, LogicalUndo var3) throws StandardException;

   void purgeAtSlot(int var1, int var2, boolean var3) throws StandardException;

   void copyAndPurge(Page var1, int var2, int var3, int var4) throws StandardException;

   RecordHandle updateAtSlot(int var1, Object[] var2, FormatableBitSet var3) throws StandardException;

   void unlatch();

   int recordCount() throws StandardException;

   int nonDeletedRecordCount() throws StandardException;

   boolean shouldReclaimSpace(int var1, int var2) throws StandardException;

   void setAuxObject(AuxObject var1);

   AuxObject getAuxObject();

   void setRepositionNeeded();

   boolean isRepositionNeeded(long var1);

   long getPageVersion();

   void setTimeStamp(PageTimeStamp var1) throws StandardException;

   PageTimeStamp currentTimeStamp();

   boolean equalTimeStamp(PageTimeStamp var1) throws StandardException;

   boolean isLatched();
}
