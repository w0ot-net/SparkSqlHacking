package org.rocksdb;

public abstract class AbstractEventListener extends RocksCallbackObject implements EventListener {
   protected AbstractEventListener() {
      this(AbstractEventListener.EnabledEventCallback.ON_FLUSH_COMPLETED, AbstractEventListener.EnabledEventCallback.ON_FLUSH_BEGIN, AbstractEventListener.EnabledEventCallback.ON_TABLE_FILE_DELETED, AbstractEventListener.EnabledEventCallback.ON_COMPACTION_BEGIN, AbstractEventListener.EnabledEventCallback.ON_COMPACTION_COMPLETED, AbstractEventListener.EnabledEventCallback.ON_TABLE_FILE_CREATED, AbstractEventListener.EnabledEventCallback.ON_TABLE_FILE_CREATION_STARTED, AbstractEventListener.EnabledEventCallback.ON_MEMTABLE_SEALED, AbstractEventListener.EnabledEventCallback.ON_COLUMN_FAMILY_HANDLE_DELETION_STARTED, AbstractEventListener.EnabledEventCallback.ON_EXTERNAL_FILE_INGESTED, AbstractEventListener.EnabledEventCallback.ON_BACKGROUND_ERROR, AbstractEventListener.EnabledEventCallback.ON_STALL_CONDITIONS_CHANGED, AbstractEventListener.EnabledEventCallback.ON_FILE_READ_FINISH, AbstractEventListener.EnabledEventCallback.ON_FILE_WRITE_FINISH, AbstractEventListener.EnabledEventCallback.ON_FILE_FLUSH_FINISH, AbstractEventListener.EnabledEventCallback.ON_FILE_SYNC_FINISH, AbstractEventListener.EnabledEventCallback.ON_FILE_RANGE_SYNC_FINISH, AbstractEventListener.EnabledEventCallback.ON_FILE_TRUNCATE_FINISH, AbstractEventListener.EnabledEventCallback.ON_FILE_CLOSE_FINISH, AbstractEventListener.EnabledEventCallback.SHOULD_BE_NOTIFIED_ON_FILE_IO, AbstractEventListener.EnabledEventCallback.ON_ERROR_RECOVERY_BEGIN, AbstractEventListener.EnabledEventCallback.ON_ERROR_RECOVERY_COMPLETED);
   }

   protected AbstractEventListener(EnabledEventCallback... var1) {
      super(packToLong(var1));
   }

   private static long packToLong(EnabledEventCallback... var0) {
      long var1 = 0L;

      for(EnabledEventCallback var6 : var0) {
         var1 |= 1L << var6.getValue();
      }

      return var1;
   }

   public void onFlushCompleted(RocksDB var1, FlushJobInfo var2) {
   }

   private void onFlushCompletedProxy(long var1, FlushJobInfo var3) {
      RocksDB var4 = new RocksDB(var1);
      var4.disOwnNativeHandle();
      this.onFlushCompleted(var4, var3);
   }

   public void onFlushBegin(RocksDB var1, FlushJobInfo var2) {
   }

   private void onFlushBeginProxy(long var1, FlushJobInfo var3) {
      RocksDB var4 = new RocksDB(var1);
      var4.disOwnNativeHandle();
      this.onFlushBegin(var4, var3);
   }

   public void onTableFileDeleted(TableFileDeletionInfo var1) {
   }

   public void onCompactionBegin(RocksDB var1, CompactionJobInfo var2) {
   }

   private void onCompactionBeginProxy(long var1, CompactionJobInfo var3) {
      RocksDB var4 = new RocksDB(var1);
      var4.disOwnNativeHandle();
      this.onCompactionBegin(var4, var3);
   }

   public void onCompactionCompleted(RocksDB var1, CompactionJobInfo var2) {
   }

   private void onCompactionCompletedProxy(long var1, CompactionJobInfo var3) {
      RocksDB var4 = new RocksDB(var1);
      var4.disOwnNativeHandle();
      this.onCompactionCompleted(var4, var3);
   }

   public void onTableFileCreated(TableFileCreationInfo var1) {
   }

   public void onTableFileCreationStarted(TableFileCreationBriefInfo var1) {
   }

   public void onMemTableSealed(MemTableInfo var1) {
   }

   public void onColumnFamilyHandleDeletionStarted(ColumnFamilyHandle var1) {
   }

   public void onExternalFileIngested(RocksDB var1, ExternalFileIngestionInfo var2) {
   }

   private void onExternalFileIngestedProxy(long var1, ExternalFileIngestionInfo var3) {
      RocksDB var4 = new RocksDB(var1);
      var4.disOwnNativeHandle();
      this.onExternalFileIngested(var4, var3);
   }

   public void onBackgroundError(BackgroundErrorReason var1, Status var2) {
   }

   private void onBackgroundErrorProxy(byte var1, Status var2) {
      this.onBackgroundError(BackgroundErrorReason.fromValue(var1), var2);
   }

   public void onStallConditionsChanged(WriteStallInfo var1) {
   }

   public void onFileReadFinish(FileOperationInfo var1) {
   }

   public void onFileWriteFinish(FileOperationInfo var1) {
   }

   public void onFileFlushFinish(FileOperationInfo var1) {
   }

   public void onFileSyncFinish(FileOperationInfo var1) {
   }

   public void onFileRangeSyncFinish(FileOperationInfo var1) {
   }

   public void onFileTruncateFinish(FileOperationInfo var1) {
   }

   public void onFileCloseFinish(FileOperationInfo var1) {
   }

   public boolean shouldBeNotifiedOnFileIO() {
      return false;
   }

   public boolean onErrorRecoveryBegin(BackgroundErrorReason var1, Status var2) {
      return true;
   }

   private boolean onErrorRecoveryBeginProxy(byte var1, Status var2) {
      return this.onErrorRecoveryBegin(BackgroundErrorReason.fromValue(var1), var2);
   }

   public void onErrorRecoveryCompleted(Status var1) {
   }

   protected long initializeNative(long... var1) {
      return this.createNewEventListener(var1[0]);
   }

   protected void disposeInternal() {
      this.disposeInternal(this.nativeHandle_);
   }

   private native long createNewEventListener(long var1);

   private native void disposeInternal(long var1);

   public static enum EnabledEventCallback {
      ON_FLUSH_COMPLETED((byte)0),
      ON_FLUSH_BEGIN((byte)1),
      ON_TABLE_FILE_DELETED((byte)2),
      ON_COMPACTION_BEGIN((byte)3),
      ON_COMPACTION_COMPLETED((byte)4),
      ON_TABLE_FILE_CREATED((byte)5),
      ON_TABLE_FILE_CREATION_STARTED((byte)6),
      ON_MEMTABLE_SEALED((byte)7),
      ON_COLUMN_FAMILY_HANDLE_DELETION_STARTED((byte)8),
      ON_EXTERNAL_FILE_INGESTED((byte)9),
      ON_BACKGROUND_ERROR((byte)10),
      ON_STALL_CONDITIONS_CHANGED((byte)11),
      ON_FILE_READ_FINISH((byte)12),
      ON_FILE_WRITE_FINISH((byte)13),
      ON_FILE_FLUSH_FINISH((byte)14),
      ON_FILE_SYNC_FINISH((byte)15),
      ON_FILE_RANGE_SYNC_FINISH((byte)16),
      ON_FILE_TRUNCATE_FINISH((byte)17),
      ON_FILE_CLOSE_FINISH((byte)18),
      SHOULD_BE_NOTIFIED_ON_FILE_IO((byte)19),
      ON_ERROR_RECOVERY_BEGIN((byte)20),
      ON_ERROR_RECOVERY_COMPLETED((byte)21);

      private final byte value;

      private EnabledEventCallback(byte var3) {
         this.value = var3;
      }

      byte getValue() {
         return this.value;
      }

      static EnabledEventCallback fromValue(byte var0) {
         for(EnabledEventCallback var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         throw new IllegalArgumentException("Illegal value provided for EnabledEventCallback: " + var0);
      }
   }
}
