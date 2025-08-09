package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.CRC32;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.ArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.services.io.TypedFormat;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.iapi.util.InterruptDetectedException;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.shared.common.error.StandardException;

abstract class FileContainer extends BaseContainer implements Cacheable, TypedFormat {
   protected static final int formatIdInteger = 116;
   protected final CacheManager pageCache;
   protected final CacheManager containerCache;
   protected final BaseDataFileFactory dataFactory;
   protected int pageSize;
   protected int spareSpace;
   protected int minimumRecordSize;
   protected short initialPages;
   protected boolean canUpdate;
   private int PreAllocThreshold;
   private int PreAllocSize;
   private boolean bulkIncreaseContainerSize;
   private static final int PRE_ALLOC_THRESHOLD = 8;
   private static final int MIN_PRE_ALLOC_SIZE = 1;
   private static final int DEFAULT_PRE_ALLOC_SIZE = 8;
   private static final int MAX_PRE_ALLOC_SIZE = 1000;
   protected long firstAllocPageNumber;
   protected long firstAllocPageOffset;
   protected long containerVersion;
   protected long estimatedRowCount;
   protected LogInstant lastLogInstant;
   private long reusableRecordIdSequenceNumber;
   private long[] lastInsertedPage;
   private int lastInsertedPage_index;
   private long lastUnfilledPage;
   private long lastAllocatedPage;
   private long estimatedPageCount;
   protected boolean preDirty;
   protected boolean isDirty;
   protected AllocationCache allocCache;
   byte[] containerInfo;
   private CRC32 checksum;
   private byte[] encryptionBuffer;
   private static final int CONTAINER_FORMAT_ID_SIZE = 4;
   protected static final int CHECKSUM_SIZE = 8;
   protected static final int CONTAINER_INFO_SIZE = 80;
   public static final long FIRST_ALLOC_PAGE_NUMBER = 0L;
   public static final long FIRST_ALLOC_PAGE_OFFSET = 0L;
   private static final int FILE_DROPPED = 1;
   private static final int FILE_COMMITTED_DROP = 2;
   private static final int FILE_REUSABLE_RECORDID = 8;
   protected static final String SPACE_TRACE = null;

   public int getTypeFormatId() {
      return 116;
   }

   FileContainer(BaseDataFileFactory var1) {
      this.dataFactory = var1;
      this.pageCache = var1.getPageCache();
      this.containerCache = var1.getContainerCache();
      this.initContainerHeader(true);
   }

   public SpaceInfo getSpaceInfo(BaseContainerHandle var1) throws StandardException {
      SpaceInformation var2;
      synchronized(this.allocCache) {
         var2 = this.allocCache.getAllPageCounts(var1, this.firstAllocPageNumber);
      }

      var2.setPageSize(this.pageSize);
      return var2;
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      ContainerKey var2 = (ContainerKey)var1;
      return var2.getSegmentId() == -1L ? (new TempRAFContainer(this.dataFactory)).setIdent(var2) : this.setIdent(var2);
   }

   protected Cacheable setIdent(ContainerKey var1) throws StandardException {
      boolean var2 = this.openContainer(var1);
      this.initializeLastInsertedPage(1);
      this.lastUnfilledPage = -1L;
      this.lastAllocatedPage = -1L;
      this.estimatedPageCount = -1L;
      if (var2) {
         this.fillInIdentity(var1);
         return this;
      } else {
         return null;
      }
   }

   public Cacheable createIdentity(Object var1, Object var2) throws StandardException {
      ContainerKey var3 = (ContainerKey)var1;
      if (var3.getSegmentId() == -1L) {
         TempRAFContainer var4 = new TempRAFContainer(this.dataFactory);
         return var4.createIdent(var3, var2);
      } else {
         return this.createIdent(var3, var2);
      }
   }

   protected Cacheable createIdent(ContainerKey var1, Object var2) throws StandardException {
      if (var2 != this) {
         this.initContainerHeader(true);
         if (var2 != null && var2 instanceof ByteArray) {
            this.createInfoFromLog((ByteArray)var2);
         } else {
            this.createInfoFromProp((Properties)var2);
         }
      } else {
         this.initContainerHeader(false);
      }

      if (this.initialPages > 1) {
         this.PreAllocThreshold = 0;
         this.PreAllocSize = this.initialPages;
         this.bulkIncreaseContainerSize = true;
      } else {
         this.PreAllocThreshold = 8;
      }

      this.createContainer(var1);
      this.setDirty(true);
      this.fillInIdentity(var1);
      return this;
   }

   public void clearIdentity() {
      this.closeContainer();
      this.initializeLastInsertedPage(1);
      this.lastUnfilledPage = -1L;
      this.lastAllocatedPage = -1L;
      this.canUpdate = false;
      super.clearIdentity();
   }

   public boolean isDirty() {
      synchronized(this) {
         return this.isDirty;
      }
   }

   public void preDirty(boolean var1) {
      synchronized(this) {
         if (var1) {
            this.preDirty = true;
         } else {
            this.preDirty = false;
            this.notifyAll();
         }

      }
   }

   protected void setDirty(boolean var1) {
      synchronized(this) {
         this.preDirty = false;
         this.isDirty = var1;
         this.notifyAll();
      }
   }

   abstract void createContainer(ContainerKey var1) throws StandardException;

   abstract boolean openContainer(ContainerKey var1) throws StandardException;

   abstract void closeContainer();

   protected void dropContainer(LogInstant var1, boolean var2) {
      synchronized(this) {
         this.setDroppedState(var2);
         this.setDirty(true);
         this.bumpContainerVersion(var1);
      }
   }

   protected final void bumpContainerVersion(LogInstant var1) {
      this.lastLogInstant = var1;
      ++this.containerVersion;
   }

   protected long getContainerVersion() {
      synchronized(this) {
         return this.containerVersion;
      }
   }

   public void getContainerProperties(Properties var1) throws StandardException {
      if (var1.getProperty("derby.storage.pageSize") != null) {
         var1.put("derby.storage.pageSize", Integer.toString(this.pageSize));
      }

      if (var1.getProperty("derby.storage.minimumRecordSize") != null) {
         var1.put("derby.storage.minimumRecordSize", Integer.toString(this.minimumRecordSize));
      }

      if (var1.getProperty("derby.storage.pageReservedSpace") != null) {
         var1.put("derby.storage.pageReservedSpace", Integer.toString(this.spareSpace));
      }

      if (var1.getProperty("derby.storage.reusableRecordId") != null) {
         Boolean var2 = this.isReusableRecordId();
         var1.put("derby.storage.reusableRecordId", var2.toString());
      }

      if (var1.getProperty("derby.storage.initialPages") != null) {
         var1.put("derby.storage.initialPages", Integer.toString(this.initialPages));
      }

   }

   protected void readHeader(byte[] var1) throws IOException, StandardException {
      AllocPage.ReadContainerInfo(this.containerInfo, var1);
      this.readHeaderFromArray(this.containerInfo);
   }

   private void initContainerHeader(boolean var1) {
      if (this.containerInfo == null) {
         this.containerInfo = new byte[80];
      }

      if (this.checksum == null) {
         this.checksum = new CRC32();
      } else {
         this.checksum.reset();
      }

      if (this.allocCache == null) {
         this.allocCache = new AllocationCache();
      } else {
         this.allocCache.reset();
      }

      if (var1) {
         this.pageSize = 0;
         this.spareSpace = 0;
         this.minimumRecordSize = 0;
      }

      this.initialPages = 1;
      this.firstAllocPageNumber = -1L;
      this.firstAllocPageOffset = -1L;
      this.containerVersion = 0L;
      this.estimatedRowCount = 0L;
      this.reusableRecordIdSequenceNumber = 0L;
      this.setDroppedState(false);
      this.setCommittedDropState(false);
      this.setReusableRecordIdState(false);
      this.lastLogInstant = null;
      this.initializeLastInsertedPage(1);
      this.lastUnfilledPage = -1L;
      this.lastAllocatedPage = -1L;
      this.estimatedPageCount = -1L;
      this.PreAllocThreshold = 8;
      this.PreAllocSize = 8;
      this.bulkIncreaseContainerSize = false;
   }

   private void readHeaderFromArray(byte[] var1) throws StandardException, IOException {
      ArrayInputStream var2 = new ArrayInputStream(var1);
      var2.setLimit(80);
      int var3 = var2.readInt();
      if (var3 != 116) {
         throw StandardException.newException("XSDB2.D", new Object[]{this.getIdentity(), var3});
      } else {
         int var4 = var2.readInt();
         this.pageSize = var2.readInt();
         this.spareSpace = var2.readInt();
         this.minimumRecordSize = var2.readInt();
         this.initialPages = var2.readShort();
         this.PreAllocSize = var2.readShort();
         this.firstAllocPageNumber = var2.readLong();
         this.firstAllocPageOffset = var2.readLong();
         this.containerVersion = var2.readLong();
         this.estimatedRowCount = var2.readLong();
         this.reusableRecordIdSequenceNumber = var2.readLong();
         this.lastLogInstant = null;
         if (this.PreAllocSize == 0) {
            this.PreAllocSize = 8;
         }

         long var5 = var2.readLong();
         if (this.initialPages == 0) {
            this.initialPages = 1;
         }

         this.PreAllocThreshold = 8;
         long var7 = var2.readLong();
         this.checksum.reset();
         this.checksum.update(var1, 0, 72);
         if (var7 != this.checksum.getValue()) {
            PageKey var9 = new PageKey(this.identity, 0L);
            throw this.dataFactory.markCorrupt(StandardException.newException("XSDG2.D", new Object[]{var9, this.checksum.getValue(), var7, StringUtil.hexDump(var1)}));
         } else {
            this.allocCache.reset();
            this.setDroppedState((var4 & 1) != 0);
            this.setCommittedDropState((var4 & 2) != 0);
            this.setReusableRecordIdState((var4 & 8) != 0);
         }
      }
   }

   protected void writeHeader(Object var1, byte[] var2) throws StandardException, IOException {
      this.writeHeaderToArray(this.containerInfo);

      try {
         AllocPage.WriteContainerInfo(this.containerInfo, var2, false);
      } catch (StandardException var4) {
         throw StandardException.newException("XSDBC.D", var4, new Object[]{var1});
      }
   }

   protected void writeHeader(Object var1, StorageRandomAccessFile var2, boolean var3, byte[] var4) throws IOException, StandardException {
      this.writeHeaderToArray(this.containerInfo);

      try {
         AllocPage.WriteContainerInfo(this.containerInfo, var4, var3);
      } catch (StandardException var10) {
         throw StandardException.newException("XSDBC.D", var10, new Object[]{var1});
      }

      this.dataFactory.flush(this.lastLogInstant);
      if (this.lastLogInstant != null) {
         this.lastLogInstant = null;
      }

      this.dataFactory.writeInProgress();

      try {
         this.writeAtOffset(var2, var4, 0L);
      } finally {
         this.dataFactory.writeFinished();
      }

   }

   void writeAtOffset(StorageRandomAccessFile var1, byte[] var2, long var3) throws IOException, StandardException {
      var1.seek(var3);
      var1.write(var2);
   }

   protected byte[] getEmbryonicPage(DataInput var1) throws IOException, StandardException {
      byte[] var2 = new byte[204];
      var1.readFully(var2);
      return var2;
   }

   byte[] getEmbryonicPage(StorageRandomAccessFile var1, long var2) throws IOException, StandardException {
      var1.seek(var2);
      return this.getEmbryonicPage(var1);
   }

   private void writeHeaderToArray(byte[] var1) throws IOException {
      ArrayOutputStream var2 = new ArrayOutputStream(var1);
      FormatIdOutputStream var3 = new FormatIdOutputStream(var2);
      int var4 = 0;
      if (this.getDroppedState()) {
         var4 |= 1;
      }

      if (this.getCommittedDropState()) {
         var4 |= 2;
      }

      if (this.isReusableRecordId()) {
         var4 |= 8;
      }

      var2.setPosition(0);
      var2.setLimit(80);
      var3.writeInt(116);
      var3.writeInt(var4);
      var3.writeInt(this.pageSize);
      var3.writeInt(this.spareSpace);
      var3.writeInt(this.minimumRecordSize);
      var3.writeShort(this.initialPages);
      var3.writeShort(this.PreAllocSize);
      var3.writeLong(this.firstAllocPageNumber);
      var3.writeLong(this.firstAllocPageOffset);
      var3.writeLong(this.containerVersion);
      var3.writeLong(this.estimatedRowCount);
      var3.writeLong(this.reusableRecordIdSequenceNumber);
      var3.writeLong(0L);
      this.checksum.reset();
      this.checksum.update(var1, 0, 72);
      var3.writeLong(this.checksum.getValue());
      var2.clearLimit();
   }

   protected ByteArray logCreateContainerInfo() throws StandardException {
      byte[] var1 = new byte[80];

      try {
         this.writeHeaderToArray(var1);
      } catch (IOException var3) {
         throw StandardException.newException("XSDA4.S", var3, new Object[0]);
      }

      return new ByteArray(var1);
   }

   private void createInfoFromLog(ByteArray var1) throws StandardException {
      byte[] var2 = var1.getArray();
      ArrayInputStream var3 = new ArrayInputStream(var2);
      int var4 = 0;

      try {
         var3.setLimit(80);
         int var5 = var3.readInt();
         if (var5 != 116) {
            throw StandardException.newException("XSDB2.D", new Object[]{this.getIdentity(), var5});
         }

         var4 = var3.readInt();
         this.pageSize = var3.readInt();
         this.spareSpace = var3.readInt();
         this.minimumRecordSize = var3.readInt();
         this.initialPages = var3.readShort();
      } catch (IOException var6) {
         throw StandardException.newException("XSDA4.S", var6, new Object[0]);
      }

      this.setReusableRecordIdState((var4 & 8) != 0);
   }

   private void createInfoFromProp(Properties var1) throws StandardException {
      AccessFactory var2 = (AccessFactory)getServiceModule(this.dataFactory, "org.apache.derby.iapi.store.access.AccessFactory");
      TransactionController var3 = var2 == null ? null : var2.getTransaction(getContextService().getCurrentContextManager());
      this.pageSize = PropertyUtil.getServiceInt(var3, var1, "derby.storage.pageSize", 4096, 32768, 4096);
      if (this.pageSize != 4096 && this.pageSize != 8192 && this.pageSize != 16384 && this.pageSize != 32768) {
         this.pageSize = 4096;
      }

      this.spareSpace = PropertyUtil.getServiceInt(var3, var1, "derby.storage.pageReservedSpace", 0, 100, 20);
      this.PreAllocSize = PropertyUtil.getServiceInt(var3, var1, "derby.storage.pagePerAllocate", 1, 1000, 8);
      if (var1 == null) {
         this.minimumRecordSize = PropertyUtil.getServiceInt(var3, "derby.storage.minimumRecordSize", 12, this.pageSize * (1 - this.spareSpace / 100) - 100, 12);
      } else {
         this.minimumRecordSize = PropertyUtil.getServiceInt(var3, var1, "derby.storage.minimumRecordSize", 1, this.pageSize * (1 - this.spareSpace / 100) - 100, 12);
      }

      if (var1 != null) {
         String var4 = var1.getProperty("derby.storage.reusableRecordId");
         if (var4 != null) {
            Boolean var5 = Boolean.parseBoolean(var4);
            this.setReusableRecordIdState(var5);
         }

         String var6 = var1.getProperty("derby.storage.initialPages");
         if (var6 != null) {
            this.initialPages = Short.parseShort(var6);
            if (this.initialPages > 1 && this.initialPages > 1000) {
               this.initialPages = 1000;
            }
         }
      }

   }

   protected boolean canUpdate() {
      return this.canUpdate;
   }

   protected void deallocatePage(BaseContainerHandle var1, BasePage var2) throws StandardException {
      long var3 = var2.getPageNumber();
      this.deallocatePagenum(var1, var3);
      var2.deallocatePage();
   }

   private void deallocatePagenum(BaseContainerHandle var1, long var2) throws StandardException {
      synchronized(this.allocCache) {
         long var5 = this.allocCache.getAllocPageNumber(var1, var2, this.firstAllocPageNumber);
         AllocPage var7 = (AllocPage)var1.getAllocPage(var5);
         if (var7 == null) {
            PageKey var8 = new PageKey(this.identity, var5);
            throw StandardException.newException("XSDF6.S", new Object[]{var8});
         }

         try {
            this.allocCache.invalidate(var7, var5);
            var7.deallocatePage(var1, var2);
         } finally {
            var7.unlatch();
         }
      }

      if (var2 <= this.lastAllocatedPage) {
         this.lastAllocatedPage = var2 - 1L;
      }

   }

   protected void compressContainer(RawTransaction var1, BaseContainerHandle var2) throws StandardException {
      AllocPage var3 = null;
      AllocPage var4 = null;
      if (this.firstAllocPageNumber != -1L) {
         this.dataFactory.getRawStoreFactory().checkpoint();
         var1.blockBackup(true);

         try {
            synchronized(this.allocCache) {
               long var6;
               for(var3 = (AllocPage)var2.getAllocPage(this.firstAllocPageNumber); !var3.isLast(); var3 = (AllocPage)var2.getAllocPage(var6)) {
                  if (var4 != null) {
                     var4.unlatch();
                  }

                  var4 = var3;
                  Object var16 = null;
                  var6 = var4.getNextAllocPageNumber();
                  long var8 = var4.getNextAllocPageOffset();
               }

               this.allocCache.invalidate();
               this.lastUnfilledPage = -1L;
               this.lastAllocatedPage = -1L;
               var3.compress(var1, this);
            }
         } finally {
            if (var3 != null) {
               var3.unlatch();
               Object var17 = null;
            }

            if (var4 != null) {
               var4.unlatch();
               Object var18 = null;
            }

            this.flushAll();
            this.pageCache.discard(this.identity);
         }

      }
   }

   public final long getReusableRecordIdSequenceNumber() {
      synchronized(this) {
         return this.reusableRecordIdSequenceNumber;
      }
   }

   protected final void incrementReusableRecordIdSequenceNumber() {
      boolean var1 = this.dataFactory.isReadOnly();
      synchronized(this) {
         ++this.reusableRecordIdSequenceNumber;
         if (!var1) {
            this.isDirty = true;
         }

      }
   }

   protected BasePage newPage(BaseContainerHandle var1, RawTransaction var2, BaseContainerHandle var3, boolean var4) throws StandardException {
      boolean var5 = var2 != null;
      if (!var5) {
         var2 = var1.getTransaction();
      }

      long var10 = -1L;
      int var15 = 0;
      int var16 = 120;
      long var17 = this.lastAllocatedPage;
      AllocPage var19 = null;
      BasePage var20 = null;

      try {
         boolean var14;
         try {
            do {
               var14 = false;
               synchronized(this.allocCache) {
                  try {
                     var19 = this.findAllocPageForAdd(var3, var2, var17);
                  } catch (InterruptDetectedException var36) {
                     --var16;
                     if (var16 > 0) {
                        this.firstAllocPageNumber = -1L;
                        var14 = true;

                        try {
                           Thread.sleep(500L);
                        } catch (InterruptedException var35) {
                           InterruptStatus.setInterrupted();
                        }
                        continue;
                     }

                     throw StandardException.newException("XSDG9.D", var36, new Object[0]);
                  }

                  this.allocCache.invalidate(var19, var19.getPageNumber());
               }

               var10 = var19.nextFreePageNumber(var17);
               long var6 = var19.getLastPagenum();
               long var8 = var19.getLastPreallocPagenum();
               boolean var13 = var10 <= var6;
               PageKey var12 = new PageKey(this.identity, var10);
               if (var13) {
                  RecordHandle var21 = BasePage.MakeRecordHandle(var12, 2);
                  if (!this.getDeallocLock(var3, var21, false, true)) {
                     if (var15 == 0) {
                        var17 = -1L;
                        this.lastAllocatedPage = var10;
                     } else {
                        var17 = var10;
                     }

                     ++var15;
                     var19.unlatch();
                     var19 = null;
                     var14 = true;
                  } else {
                     this.lastAllocatedPage = var10;
                  }
               } else if (var15 > 0) {
                  this.lastAllocatedPage = -1L;
               } else {
                  this.lastAllocatedPage = var10;
               }

               if (!var14) {
                  boolean var45 = (var3.getMode() & 1) == 1;
                  if (!var45 && (this.bulkIncreaseContainerSize || var10 > var8 && var10 > (long)this.PreAllocThreshold)) {
                     var19.preAllocatePage(this, this.PreAllocThreshold, this.PreAllocSize);
                  }

                  var8 = var19.getLastPreallocPagenum();
                  boolean var22 = var10 <= var8;
                  PageCreationArgs var23 = new PageCreationArgs(117, var22 ? 0 : (var45 ? 0 : 1), this.pageSize, this.spareSpace, this.minimumRecordSize, 0);
                  long var24 = var10 * (long)this.pageSize;

                  try {
                     var20 = this.initPage(var3, var12, var23, var24, var13, var4);
                  } catch (StandardException var34) {
                     this.allocCache.dumpAllocationCache();
                     throw var34;
                  }

                  var19.addPage(this, var10, var2, var1);
                  if (var5) {
                     var20.unlatch();
                     Object var43 = null;
                     BasePage var44 = (BasePage)this.pageCache.find(var12);
                     var20 = this.latchPage(var1, var44, false);
                     if (var20 == null || var20.recordCount() != 0 || var20.getPageStatus() != 1) {
                        var14 = true;
                        if (var20 != null) {
                           var20.unlatch();
                           var20 = null;
                        }

                        var19.unlatch();
                        var19 = null;
                     }
                  }
               }
            } while(var14);
         } catch (StandardException var38) {
            if (var20 != null) {
               var20.unlatch();
            }

            Object var42 = null;
            throw var38;
         }
      } finally {
         if (!var5 && var19 != null) {
            var19.unlatch();
            Object var41 = null;
         }

      }

      if (this.bulkIncreaseContainerSize) {
         this.bulkIncreaseContainerSize = false;
         this.PreAllocSize = 8;
      }

      if (!var4 && var20 != null) {
         this.setLastInsertedPage(var10);
      }

      if (this.estimatedPageCount >= 0L) {
         ++this.estimatedPageCount;
      }

      if (!this.identity.equals(var20.getPageId().getContainerId())) {
         throw StandardException.newException("XSDAC.S", new Object[]{this.identity, var20.getPageId().getContainerId()});
      } else {
         return var20;
      }
   }

   protected void clearPreallocThreshold() {
      this.PreAllocThreshold = 0;
   }

   protected void prepareForBulkLoad(BaseContainerHandle var1, int var2) {
      this.clearPreallocThreshold();
      RawTransaction var3 = var1.getTransaction();
      AllocPage var4 = this.findLastAllocPage(var1, var3);
      if (var4 != null) {
         var4.preAllocatePage(this, 0, var2);
         var4.unlatch();
      }

   }

   private boolean pageValid(BaseContainerHandle var1, long var2) throws StandardException {
      boolean var4 = false;
      int var6 = 120;

      boolean var5;
      do {
         var5 = true;
         synchronized(this.allocCache) {
            try {
               if (var2 <= this.allocCache.getLastPageNumber(var1, this.firstAllocPageNumber) && this.allocCache.getPageStatus(var1, var2, this.firstAllocPageNumber) == 0) {
                  var4 = true;
               }
            } catch (InterruptDetectedException var12) {
               --var6;
               if (var6 <= 0) {
                  throw StandardException.newException("XSDG9.D", var12, new Object[0]);
               }

               var5 = false;

               try {
                  Thread.sleep(500L);
               } catch (InterruptedException var11) {
                  InterruptStatus.setInterrupted();
               }
            }
         }
      } while(!var5);

      return var4;
   }

   protected long getLastPageNumber(BaseContainerHandle var1) throws StandardException {
      synchronized(this.allocCache) {
         long var2;
         if (this.firstAllocPageNumber == -1L) {
            var2 = -1L;
         } else {
            var2 = this.allocCache.getLastPageNumber(var1, this.firstAllocPageNumber);
         }

         return var2;
      }
   }

   private AllocPage findAllocPageForAdd(BaseContainerHandle var1, RawTransaction var2, long var3) throws StandardException {
      AllocPage var5 = null;
      AllocPage var6 = null;
      boolean var7 = false;

      try {
         if (this.firstAllocPageNumber == -1L) {
            var5 = this.makeAllocPage(var2, var1, 0L, 0L, 80);
         } else {
            var5 = (AllocPage)var1.getAllocPage(this.firstAllocPageNumber);
         }

         if (!var5.canAddFreePage(var3)) {
            boolean var8 = false;

            while(!var5.isLast()) {
               long var9 = var5.getNextAllocPageNumber();
               long var11 = var5.getNextAllocPageOffset();
               var5.unlatch();
               Object var16 = null;
               var5 = (AllocPage)var1.getAllocPage(var9);
               if (var5.canAddFreePage(var3)) {
                  var8 = true;
                  break;
               }
            }

            if (!var8) {
               var6 = var5;
               Object var17 = null;
               long var19 = var6.getMaxPagenum() + 1L;
               var5 = this.makeAllocPage(var2, var1, var19, var19, 0);
               var6.chainNewAllocPage(var1, var19, var19);
               var6.unlatch();
               var6 = null;
            }
         }

         var7 = true;
      } finally {
         if (!var7) {
            if (var6 != null) {
               var6.unlatch();
            }

            if (var5 != null) {
               var5.unlatch();
            }

            var5 = null;
         }

      }

      return var5;
   }

   private AllocPage findLastAllocPage(BaseContainerHandle var1, RawTransaction var2) {
      AllocPage var3 = null;
      Object var4 = null;
      if (this.firstAllocPageNumber == -1L) {
         return null;
      } else {
         long var5;
         try {
            for(var3 = (AllocPage)var1.getAllocPage(this.firstAllocPageNumber); !var3.isLast(); var3 = (AllocPage)var1.getAllocPage(var5)) {
               var5 = var3.getNextAllocPageNumber();
               long var7 = var3.getNextAllocPageOffset();
               var3.unlatch();
               Object var11 = null;
            }
         } catch (StandardException var9) {
            if (var3 != null) {
               var3.unlatch();
            }

            var3 = null;
         }

         return var3;
      }
   }

   private AllocPage makeAllocPage(RawTransaction var1, BaseContainerHandle var2, long var3, long var5, int var7) throws StandardException {
      boolean var8 = (var2.getMode() & 1) == 1;
      PageCreationArgs var9 = new PageCreationArgs(118, var8 ? 0 : 1, this.pageSize, 0, this.minimumRecordSize, var7);
      if (var3 == 0L) {
         this.firstAllocPageNumber = var3;
         this.firstAllocPageOffset = var5;
      }

      PageKey var10 = new PageKey(this.identity, var3);
      return (AllocPage)this.initPage(var2, var10, var9, var5, false, false);
   }

   protected BasePage initPage(BaseContainerHandle var1, PageKey var2, PageCreationArgs var3, long var4, boolean var6, boolean var7) throws StandardException {
      BasePage var8 = null;
      boolean var9 = true;

      try {
         if (var6) {
            var8 = (BasePage)this.pageCache.find(var2);
            if (var8 == null) {
               throw StandardException.newException("XSDF8.S", new Object[]{var2});
            }
         } else {
            var8 = (BasePage)this.pageCache.create(var2, var3);
         }

         var9 = false;
         var8 = this.latchPage(var1, var8, true);
         if (var8 == null) {
            throw StandardException.newException("XSDF7.S", new Object[]{var2});
         }

         int var10 = 0;
         if (var6) {
            var10 |= 1;
         }

         if (var7) {
            var10 |= 2;
         }

         if (var6 && this.isReusableRecordId()) {
            var10 |= 4;
         }

         var8.initPage(var10, var4);
         var8.setContainerRowCount(this.estimatedRowCount);
      } finally {
         if (var9 && var8 != null) {
            this.pageCache.release((Cacheable)var8);
            var8 = null;
         }

      }

      return var8;
   }

   private BasePage getUserPage(BaseContainerHandle var1, long var2, boolean var4, boolean var5) throws StandardException {
      if (var2 < 1L) {
         return null;
      } else if (this.getCommittedDropState()) {
         return null;
      } else if (!this.pageValid(var1, var2)) {
         return null;
      } else {
         PageKey var6 = new PageKey(this.identity, var2);
         BasePage var7 = (BasePage)this.pageCache.find(var6);
         if (var7 == null) {
            return var7;
         } else if (this.latchPage(var1, var7, var5) == null) {
            return null;
         } else {
            if (var7.isOverflowPage() && !var4 || var7.getPageStatus() != 1) {
               var7.unlatch();
               var7 = null;
            }

            return var7;
         }
      }
   }

   protected void trackUnfilledPage(long var1, boolean var3) {
      if (!this.dataFactory.isReadOnly()) {
         this.allocCache.trackUnfilledPage(var1, var3);
      }

   }

   protected BasePage getPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException {
      return this.getUserPage(var1, var2, true, var4);
   }

   protected BasePage getAnyPage(BaseContainerHandle var1, long var2) throws StandardException {
      if (this.getCommittedDropState()) {
         return null;
      } else {
         synchronized(this.allocCache) {
            this.allocCache.invalidate();
         }

         PageKey var4 = new PageKey(this.identity, var2);
         BasePage var5 = (BasePage)this.pageCache.find(var4);
         return var5;
      }
   }

   protected BasePage reCreatePageForRedoRecovery(BaseContainerHandle var1, int var2, long var3, long var5) throws StandardException {
      boolean var7 = var1.getTransaction().inRollForwardRecovery();
      if (!var7 && !PropertyUtil.getSystemBoolean("derby.storage.patchInitPageRecoverError")) {
         return null;
      } else {
         PageKey var8 = new PageKey(this.identity, var3);
         PageCreationArgs var9;
         if (var2 == 117) {
            var9 = new PageCreationArgs(var2, 1, this.pageSize, this.spareSpace, this.minimumRecordSize, 0);
         } else {
            if (var2 != 118) {
               throw StandardException.newException("XSDB1.D", new Object[]{var8});
            }

            byte var10 = 0;
            if (var3 == 0L) {
               var10 = 80;
               this.firstAllocPageNumber = var3;
               this.firstAllocPageOffset = var5;
            }

            var9 = new PageCreationArgs(var2, 1, this.pageSize, 0, this.minimumRecordSize, var10);
         }

         BasePage var18 = null;
         boolean var11 = true;

         try {
            try {
               var18 = (BasePage)this.pageCache.create(var8, var9);
            } catch (StandardException var16) {
               throw StandardException.newException("XSDFI.S", var16, new Object[]{var8});
            }

            if (var18 == null) {
               throw StandardException.newException("XSDFI.S", new Object[]{var8});
            }

            var11 = false;
            var18 = this.latchPage(var1, var18, false);
            if (var18 == null) {
               throw StandardException.newException("XSDF7.S", new Object[]{var8});
            }
         } finally {
            if (var11 && var18 != null) {
               this.pageCache.release((Cacheable)var18);
               var18 = null;
            }

         }

         return var18;
      }
   }

   protected BasePage getAllocPage(long var1) throws StandardException {
      if (this.getCommittedDropState()) {
         return null;
      } else {
         PageKey var3 = new PageKey(this.identity, var1);
         BasePage var4 = (BasePage)this.pageCache.find(var3);
         return var4;
      }
   }

   protected BasePage getHeadPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException {
      return this.getUserPage(var1, var2, false, var4);
   }

   protected BasePage getFirstHeadPage(BaseContainerHandle var1, boolean var2) throws StandardException {
      return this.getNextHeadPage(var1, 0L, var2);
   }

   protected BasePage getNextHeadPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException {
      while(true) {
         long var5;
         synchronized(this.allocCache) {
            var5 = this.allocCache.getNextValidPage(var1, var2, this.firstAllocPageNumber);
         }

         if (var5 == -1L) {
            return null;
         }

         BasePage var7 = this.getUserPage(var1, var5, false, var4);
         if (var7 != null) {
            return var7;
         }

         var2 = var5;
      }
   }

   private BasePage getInsertablePage(BaseContainerHandle var1, long var2, boolean var4, boolean var5) throws StandardException {
      if (var2 == -1L) {
         return null;
      } else {
         BasePage var6 = this.getUserPage(var1, var2, var5, var4);
         if (var6 != null && !var6.allowInsert()) {
            var6.unlatch();
            var6 = null;
            this.allocCache.trackUnfilledPage(var2, false);
         }

         return var6;
      }
   }

   protected BasePage getPageForCompress(BaseContainerHandle var1, int var2, long var3) throws StandardException {
      BasePage var5 = null;
      boolean var6 = (var2 & 1) == 0;
      if (var6) {
         long var7 = this.getLastInsertedPage();
         if (var7 < var3 && var7 != -1L) {
            var5 = this.getInsertablePage(var1, var7, true, false);
            if (var5 == null) {
               if (var7 == this.getLastUnfilledPage()) {
                  this.setLastUnfilledPage(-1L);
               }

               if (var7 == this.getLastInsertedPage()) {
                  this.setLastInsertedPage(-1L);
               }
            }
         }
      } else {
         long var9 = this.getLastUnfilledPage();
         if (var9 == -1L || var9 >= var3 || var9 == this.getLastInsertedPage()) {
            var9 = this.getUnfilledPageNumber(var1, 0L);
         }

         if (var9 != -1L && var9 < var3) {
            var5 = this.getInsertablePage(var1, var9, true, false);
         }

         if (var5 != null) {
            this.setLastUnfilledPage(var9);
            this.setLastInsertedPage(var9);
         }
      }

      return var5;
   }

   protected BasePage getPageForInsert(BaseContainerHandle var1, int var2) throws StandardException {
      BasePage var3 = null;
      boolean var4 = (var2 & 1) == 0;
      if (var4) {
         long var5 = this.getLastInsertedPage();
         if (var5 != -1L) {
            var3 = this.getInsertablePage(var1, var5, false, false);
            if (var3 == null) {
               var5 = this.getLastInsertedPage();
               var3 = this.getInsertablePage(var1, var5, true, false);
            }
         }

         if (var3 == null) {
            if (var5 == this.getLastUnfilledPage()) {
               this.setLastUnfilledPage(-1L);
            }

            if (var5 == this.getLastInsertedPage()) {
               this.setLastInsertedPage(-1L);
            }
         }
      } else {
         long var7 = this.getLastUnfilledPage();
         if (var7 == -1L || var7 == this.getLastInsertedPage()) {
            var7 = this.getUnfilledPageNumber(var1, var7);
         }

         if (var7 != -1L) {
            var3 = this.getInsertablePage(var1, var7, true, false);
            if (var3 == null) {
               var7 = this.getUnfilledPageNumber(var1, var7);
               if (var7 != -1L) {
                  var3 = this.getInsertablePage(var1, var7, true, false);
               }
            }
         }

         if (var3 != null) {
            this.setLastUnfilledPage(var7);
            this.setLastInsertedPage(var7);
         }
      }

      return var3;
   }

   protected BasePage getLatchedPage(BaseContainerHandle var1, long var2) throws StandardException {
      PageKey var4 = new PageKey(this.identity, var2);
      BasePage var5 = (BasePage)this.pageCache.find(var4);
      var5 = this.latchPage(var1, var5, true);
      return var5;
   }

   private long getUnfilledPageNumber(BaseContainerHandle var1, long var2) throws StandardException {
      synchronized(this.allocCache) {
         return this.allocCache.getUnfilledPageNumber(var1, this.firstAllocPageNumber, var2);
      }
   }

   public long getEstimatedRowCount(int var1) {
      return this.estimatedRowCount;
   }

   public void setEstimatedRowCount(long var1, int var3) {
      boolean var4 = this.dataFactory.isReadOnly();
      synchronized(this) {
         this.estimatedRowCount = var1;
         if (!var4) {
            this.isDirty = true;
         }

      }
   }

   protected void updateEstimatedRowCount(int var1) {
      boolean var2 = this.dataFactory.isReadOnly();
      synchronized(this) {
         this.estimatedRowCount += (long)var1;
         if (this.estimatedRowCount < 0L) {
            this.estimatedRowCount = 0L;
         }

         if (!var2) {
            this.isDirty = true;
         }

      }
   }

   public long getEstimatedPageCount(BaseContainerHandle var1, int var2) throws StandardException {
      if (this.estimatedPageCount < 0L) {
         synchronized(this.allocCache) {
            this.estimatedPageCount = this.allocCache.getEstimatedPageCount(var1, this.firstAllocPageNumber);
         }
      }

      return this.estimatedPageCount;
   }

   protected abstract void readPage(long var1, byte[] var3) throws IOException, StandardException;

   protected abstract void writePage(long var1, byte[] var3, boolean var4) throws IOException, StandardException;

   protected void decryptPage(byte[] var1, int var2) throws StandardException {
      synchronized(this) {
         if (this.encryptionBuffer == null || this.encryptionBuffer.length < var2) {
            this.encryptionBuffer = new byte[var2];
         }

         this.dataFactory.decrypt(var1, 0, var2, this.encryptionBuffer, 0);
         System.arraycopy(this.encryptionBuffer, 8, var1, 0, var2 - 8);
         System.arraycopy(this.encryptionBuffer, 0, var1, var2 - 8, 8);
      }
   }

   protected byte[] encryptPage(byte[] var1, int var2, byte[] var3, boolean var4) throws StandardException {
      System.arraycopy(var1, var2 - 8, var3, 0, 8);
      System.arraycopy(var1, 0, var3, 8, var2 - 8);
      this.dataFactory.encrypt(var3, 0, var2, var3, 0, var4);
      return var3;
   }

   protected byte[] getEncryptionBuffer() {
      if (this.encryptionBuffer == null || this.encryptionBuffer.length < this.pageSize) {
         this.encryptionBuffer = new byte[this.pageSize];
      }

      return this.encryptionBuffer;
   }

   protected abstract int preAllocate(long var1, int var3);

   protected int doPreAllocatePages(long var1, int var3) {
      PageCreationArgs var4 = new PageCreationArgs(117, 2, this.pageSize, this.spareSpace, this.minimumRecordSize, 0);
      StoredPage var5 = new StoredPage();
      var5.setFactory(this.dataFactory);
      boolean var6 = false;

      int var7;
      for(var7 = 0; var7 < var3; ++var7) {
         PageKey var8 = new PageKey(this.identity, var1 + (long)var7 + 1L);

         try {
            var5.createIdentity(var8, var4);
            var5.clearIdentity();
         } catch (StandardException var10) {
            var6 = true;
         }

         if (var6) {
            break;
         }
      }

      return var7;
   }

   protected int getPageSize() {
      return this.pageSize;
   }

   protected int getSpareSpace() {
      return this.spareSpace;
   }

   protected int getMinimumRecordSize() {
      return this.minimumRecordSize;
   }

   private synchronized void switchToMultiInsertPageMode(BaseContainerHandle var1) throws StandardException {
      if (this.lastInsertedPage.length == 1) {
         long var2 = this.lastInsertedPage[0];
         this.lastInsertedPage = new long[4];
         this.lastInsertedPage[0] = var2;

         for(int var4 = 3; var4 > 0; --var4) {
            Page var5 = this.addPage(var1, false);
            this.lastInsertedPage[var4] = var5.getPageNumber();
            var5.unlatch();
         }
      }

   }

   private synchronized long getLastInsertedPage() {
      if (this.lastInsertedPage.length == 1) {
         return this.lastInsertedPage[0];
      } else {
         long var1 = this.lastInsertedPage[this.lastInsertedPage_index++];
         if (this.lastInsertedPage_index > this.lastInsertedPage.length - 1) {
            this.lastInsertedPage_index = 0;
         }

         return var1;
      }
   }

   private synchronized long getLastUnfilledPage() {
      return this.lastUnfilledPage;
   }

   private synchronized void initializeLastInsertedPage(int var1) {
      this.lastInsertedPage = new long[var1];

      for(int var2 = this.lastInsertedPage.length - 1; var2 >= 0; --var2) {
         this.lastInsertedPage[var2] = -1L;
      }

      this.lastInsertedPage_index = 0;
   }

   private synchronized void setLastInsertedPage(long var1) {
      this.lastInsertedPage[this.lastInsertedPage_index] = var1;
   }

   private synchronized void setLastUnfilledPage(long var1) {
      this.lastUnfilledPage = var1;
   }

   protected void letGo(BaseContainerHandle var1) {
      super.letGo(var1);
      this.containerCache.release(this);
   }

   protected BasePage latchPage(BaseContainerHandle var1, BasePage var2, boolean var3) throws StandardException {
      if (var2 == null) {
         return null;
      } else {
         BasePage var4 = super.latchPage(var1, var2, var3);
         if (var4 == null) {
            this.pageCache.release((Cacheable)var2);
         }

         return var4;
      }
   }

   protected abstract void backupContainer(BaseContainerHandle var1, String var2) throws StandardException;

   static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Object getServiceModule(Object var0, String var1) {
      return Monitor.getServiceModule(var0, var1);
   }
}
