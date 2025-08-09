package org.apache.derby.iapi.store.raw;

import java.util.Properties;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.shared.common.error.StandardException;

public interface ContainerHandle {
   int DEFAULT_PAGESIZE = -1;
   int DEFAULT_SPARESPACE = -1;
   int DEFAULT_ASSIGN_ID = 0;
   int MODE_DEFAULT = 0;
   int MODE_UNLOGGED = 1;
   int MODE_CREATE_UNLOGGED = 2;
   int MODE_FORUPDATE = 4;
   int MODE_READONLY = 8;
   int MODE_TRUNCATE_ON_COMMIT = 16;
   int MODE_DROP_ON_COMMIT = 32;
   int MODE_OPEN_FOR_LOCK_ONLY = 64;
   int MODE_LOCK_NOWAIT = 128;
   int MODE_TRUNCATE_ON_ROLLBACK = 256;
   int MODE_FLUSH_ON_COMMIT = 512;
   int MODE_NO_ACTIONS_ON_COMMIT = 1024;
   int MODE_TEMP_IS_KEPT = 2048;
   int MODE_USE_UPDATE_LOCKS = 4096;
   int MODE_SECONDARY_LOCKED = 8192;
   int MODE_BASEROW_INSERT_LOCKED = 16384;
   int MODE_LOCK_ROW_NOWAIT = 32768;
   int TEMPORARY_SEGMENT = -1;
   long FIRST_PAGE_NUMBER = 1L;
   long INVALID_PAGE_NUMBER = -1L;
   int ADD_PAGE_DEFAULT = 1;
   int ADD_PAGE_BULK = 2;
   int GET_PAGE_UNFILLED = 1;

   ContainerKey getId();

   Object getUniqueId();

   boolean isReadOnly();

   Page addPage() throws StandardException;

   void compressContainer() throws StandardException;

   long getReusableRecordIdSequenceNumber() throws StandardException;

   Page addPage(int var1) throws StandardException;

   void preAllocate(int var1);

   void removePage(Page var1) throws StandardException;

   Page getPage(long var1) throws StandardException;

   Page getPageNoWait(long var1) throws StandardException;

   Page getUserPageNoWait(long var1) throws StandardException;

   Page getUserPageWait(long var1) throws StandardException;

   Page getFirstPage() throws StandardException;

   Page getNextPage(long var1) throws StandardException;

   Page getPageForInsert(int var1) throws StandardException;

   Page getPageForCompress(int var1, long var2) throws StandardException;

   void getContainerProperties(Properties var1) throws StandardException;

   void close();

   long getEstimatedRowCount(int var1) throws StandardException;

   void setEstimatedRowCount(long var1, int var3) throws StandardException;

   long getEstimatedPageCount(int var1) throws StandardException;

   void flushContainer() throws StandardException;

   LockingPolicy getLockingPolicy();

   void setLockingPolicy(LockingPolicy var1);

   RecordHandle makeRecordHandle(long var1, int var3) throws StandardException;

   void compactRecord(RecordHandle var1) throws StandardException;

   boolean isTemporaryContainer() throws StandardException;

   SpaceInfo getSpaceInfo() throws StandardException;

   void backupContainer(String var1) throws StandardException;
}
