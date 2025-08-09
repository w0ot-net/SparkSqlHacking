package org.apache.derby.impl.store.raw.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.zip.CRC32;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.ArrayOutputStream;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.DataInputUtil;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.ErrorObjectInput;
import org.apache.derby.iapi.services.io.FormatIdInputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.PageTimeStamp;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class StoredPage extends CachedPage {
   public static final int FORMAT_NUMBER = 117;
   protected static final int PAGE_HEADER_OFFSET = 4;
   protected static final int PAGE_HEADER_SIZE = 56;
   protected static final int RECORD_SPACE_OFFSET = 60;
   protected static final int PAGE_VERSION_OFFSET = 6;
   protected static final int SMALL_SLOT_SIZE = 2;
   protected static final int LARGE_SLOT_SIZE = 4;
   protected static final int CHECKSUM_SIZE = 8;
   protected static final int OVERFLOW_POINTER_SIZE = 12;
   protected static final int OVERFLOW_PTR_FIELD_SIZE = 14;
   ByteHolder bh = null;
   protected static final int COLUMN_NONE = 0;
   protected static final int COLUMN_FIRST = 1;
   protected static final int COLUMN_LONG = 2;
   protected static final int COLUMN_CREATE_NULL = 3;
   private int maxFieldSize;
   private boolean isOverflowPage;
   private int slotsInUse;
   private int nextId;
   private int generation;
   private int prevGeneration;
   private long bipLocation;
   private int deletedRowCount;
   private boolean headerOutOfDate;
   private CRC32 checksum;
   protected int minimumRecordSize;
   private int userRowSize;
   private int slotFieldSize;
   private int slotEntrySize;
   private int slotTableOffsetToFirstEntry;
   private int slotTableOffsetToFirstRecordLengthField;
   private int slotTableOffsetToFirstReservedSpaceField;
   protected int totalSpace;
   protected int freeSpace = Integer.MIN_VALUE;
   private int firstFreeByte = Integer.MIN_VALUE;
   protected int spareSpace;
   private StoredRecordHeader overflowRecordHeader;
   protected ArrayInputStream rawDataIn;
   protected ArrayOutputStream rawDataOut;
   protected FormatIdOutputStream logicalDataOut;

   public int getTypeFormatId() {
      return 117;
   }

   private StoredRecordHeader getOverFlowRecordHeader() throws StandardException {
      return this.overflowRecordHeader != null ? this.overflowRecordHeader : (this.overflowRecordHeader = new StoredRecordHeader());
   }

   protected void initialize() {
      super.initialize();
      if (this.rawDataIn == null) {
         this.rawDataIn = new ArrayInputStream();
         this.checksum = new CRC32();
      }

      if (this.pageData != null) {
         this.rawDataIn.setData(this.pageData);
      }

   }

   private void createOutStreams() {
      this.rawDataOut = new ArrayOutputStream();
      this.rawDataOut.setData(this.pageData);
      this.logicalDataOut = new FormatIdOutputStream(this.rawDataOut);
   }

   private void setOutputStream(OutputStream var1) {
      if (this.rawDataOut == null) {
         this.createOutStreams();
      }

      this.logicalDataOut.setOutput(var1);
   }

   private void resetOutputStream() {
      this.logicalDataOut.setOutput(this.rawDataOut);
   }

   protected void usePageBuffer(byte[] var1) {
      this.pageData = var1;
      int var2 = this.pageData.length;
      if (this.rawDataIn != null) {
         this.rawDataIn.setData(this.pageData);
      }

      this.slotFieldSize = this.calculateSlotFieldSize(var2);
      this.slotEntrySize = 3 * this.slotFieldSize;
      this.initSpace();
      this.slotTableOffsetToFirstEntry = var2 - 8 - this.slotEntrySize;
      this.slotTableOffsetToFirstRecordLengthField = this.slotTableOffsetToFirstEntry + this.slotFieldSize;
      this.slotTableOffsetToFirstReservedSpaceField = this.slotTableOffsetToFirstEntry + 2 * this.slotFieldSize;
      if (this.rawDataOut != null) {
         this.rawDataOut.setData(this.pageData);
      }

   }

   private int calculateSlotFieldSize(int var1) {
      return var1 < 65536 ? 2 : 4;
   }

   protected void createPage(PageKey var1, PageCreationArgs var2) throws StandardException {
      this.spareSpace = var2.spareSpace;
      this.minimumRecordSize = var2.minimumRecordSize;
      this.setPageArray(var2.pageSize);
      this.cleanPage();
      this.setPageVersion(0L);
      this.nextId = 6;
      this.generation = 0;
      this.prevGeneration = 0;
      this.bipLocation = 0L;
      this.createOutStreams();
   }

   protected void initFromData(FileContainer var1, PageKey var2) throws StandardException {
      if (var1 != null) {
         this.spareSpace = var1.getSpareSpace();
         this.minimumRecordSize = var1.getMinimumRecordSize();
      }

      try {
         this.validateChecksum(var2);
      } catch (StandardException var11) {
         if (var11.getMessageId().equals("XSDG2.D")) {
            int var4 = this.getPageSize();
            byte[] var5 = this.pageData;
            this.pageData = null;
            this.setPageArray(var4);

            try {
               var1.readPage(var2.getPageNumber(), this.pageData);
            } catch (IOException var9) {
               throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var9, new Object[]{var2}));
            }

            try {
               this.validateChecksum(var2);
            } catch (StandardException var8) {
               throw this.dataFactory.markCorrupt(var11);
            }

            String var6 = pagedataToHexDump(var5);
            String var7 = pagedataToHexDump(var5);
            throw StandardException.newException("XSDFD.S", var11, new Object[]{var2, var6, var7});
         }

         throw var11;
      }

      try {
         this.readPageHeader();
         this.initSlotTable(var2);
      } catch (IOException var10) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var10, new Object[]{var2}));
      }
   }

   protected void validateChecksum(PageKey var1) throws StandardException {
      long var2;
      try {
         this.rawDataIn.setPosition(this.getPageSize() - 8);
         var2 = this.rawDataIn.readLong();
      } catch (IOException var5) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var5, new Object[]{var1}));
      }

      this.checksum.reset();
      this.checksum.update(this.pageData, 0, this.getPageSize() - 8);
      if (var2 != this.checksum.getValue()) {
         CRC32 var4 = new CRC32();
         var4.reset();
         var4.update(this.pageData, 0, this.getPageSize() - 8);
         if (var2 != var4.getValue()) {
            throw StandardException.newException("XSDG2.D", new Object[]{var1, this.checksum.getValue(), var2, pagedataToHexDump(this.pageData)});
         }

         this.checksum = var4;
      }

   }

   protected void updateChecksum() throws IOException {
      this.checksum.reset();
      this.checksum.update(this.pageData, 0, this.getPageSize() - 8);
      this.rawDataOut.setPosition(this.getPageSize() - 8);
      this.logicalDataOut.writeLong(this.checksum.getValue());
   }

   protected void writePage(PageKey var1) throws StandardException {
      try {
         if (this.headerOutOfDate) {
            this.updatePageHeader();
         } else {
            this.updatePageVersion();
         }

         this.updateChecksum();
      } catch (IOException var3) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var3, new Object[]{var1}));
      }
   }

   protected void writeFormatId(PageKey var1) throws StandardException {
      try {
         if (this.rawDataOut == null) {
            this.createOutStreams();
         }

         this.rawDataOut.setPosition(0);
         FormatIdUtil.writeFormatIdInteger(this.logicalDataOut, this.getTypeFormatId());
      } catch (IOException var3) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var3, new Object[]{var1}));
      }
   }

   protected void releaseExclusive() {
      super.releaseExclusive();
      this.pageCache.release(this);
   }

   public int getTotalSpace(int var1) throws StandardException {
      try {
         this.rawDataIn.setPosition(this.getSlotOffset(var1) + this.slotFieldSize);
         return this.slotFieldSize == 2 ? this.rawDataIn.readUnsignedShort() + this.rawDataIn.readUnsignedShort() : this.rawDataIn.readInt() + this.rawDataIn.readInt();
      } catch (IOException var3) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var3, new Object[]{this.getPageId()}));
      }
   }

   public boolean spaceForInsert() throws StandardException {
      if (this.slotsInUse == 0) {
         return true;
      } else if (!this.allowInsert()) {
         return false;
      } else {
         int var1 = this.totalSpace - this.freeSpace;
         int var2 = var1 / this.slotsInUse;
         return var2 <= this.freeSpace;
      }
   }

   public boolean spaceForInsert(Object[] var1, FormatableBitSet var2, int var3) throws StandardException {
      if (this.slotsInUse == 0) {
         return true;
      } else if (!this.allowInsert()) {
         return false;
      } else {
         DynamicByteArrayOutputStream var4 = new DynamicByteArrayOutputStream();

         try {
            this.logRow(0, true, this.nextId, var1, var2, var4, 0, (byte)1, -1, -1, var3);
            return true;
         } catch (NoSpaceOnPage var6) {
            return false;
         } catch (IOException var7) {
            throw StandardException.newException("XSDA4.S", var7, new Object[0]);
         }
      }
   }

   private boolean spaceForInsert(Object[] var1, FormatableBitSet var2, int var3, int var4, int var5) throws StandardException {
      if (this.spaceForInsert() && this.freeSpace >= var3) {
         DynamicByteArrayOutputStream var6 = new DynamicByteArrayOutputStream();

         try {
            this.logRow(0, true, this.nextId, var1, var2, var6, var4, (byte)1, -1, -1, var5);
            return true;
         } catch (NoSpaceOnPage var8) {
            return false;
         } catch (IOException var9) {
            throw StandardException.newException("XSDA4.S", var9, new Object[0]);
         }
      } else {
         return false;
      }
   }

   public boolean unfilled() {
      return this.allowInsert() && this.freeSpace > this.getPageSize() / 2;
   }

   public boolean allowInsert() {
      if (this.slotsInUse == 0) {
         return true;
      } else {
         int var1 = this.freeSpace;
         var1 -= this.slotEntrySize;
         if (var1 >= this.minimumRecordSize && var1 >= 17) {
            return var1 * 100 / this.totalSpace >= this.spareSpace;
         } else {
            return false;
         }
      }
   }

   public boolean spaceForCopy(int var1, int[] var2) {
      int var3 = this.slotEntrySize * var1;

      for(int var4 = 0; var4 < var1; ++var4) {
         if (var2[var4] > 0) {
            var3 += var2[var4] >= this.minimumRecordSize ? var2[var4] : this.minimumRecordSize;
         }
      }

      return this.freeSpace - var3 >= 0;
   }

   protected boolean spaceForCopy(int var1, int var2) {
      var1 = var1 - StoredRecordHeader.getStoredSizeRecordId(var2) + StoredRecordHeader.getStoredSizeRecordId(this.nextId);
      int var3 = this.slotEntrySize + (var1 >= this.minimumRecordSize ? var1 : this.minimumRecordSize);
      return this.freeSpace - var3 >= 0;
   }

   protected boolean restoreRecordFromSlot(int var1, Object[] var2, FetchDescriptor var3, RecordHandle var4, StoredRecordHeader var5, boolean var6) throws StandardException {
      try {
         int var7 = this.getRecordOffset(var1) + var5.size();
         ArrayInputStream var8 = this.rawDataIn;
         var8.setPosition(var7);
         if (!var5.hasOverflow()) {
            if (var6 && var3 != null && var3.getQualifierList() != null) {
               var3.reset();
               if (!this.qualifyRecordFromSlot(var2, var7, var3, var5, var4)) {
                  return false;
               }

               var8.setPosition(var7);
            }

            if (var3 != null) {
               this.readRecordFromArray(var2, var3.getValidColumns() == null ? var2.length - 1 : var3.getMaxFetchColumnId(), var3.getValidColumnsArray(), var3.getMaterializedColumns(), var8, var5, var4);
            } else {
               this.readRecordFromArray(var2, var2.length - 1, (int[])null, (int[])null, var8, var5, var4);
            }

            return true;
         } else {
            if (var3 != null) {
               if (var3.getQualifierList() != null) {
                  var3.reset();
               }

               this.readRecordFromArray(var2, var3.getValidColumns() == null ? var2.length - 1 : var3.getMaxFetchColumnId(), var3.getValidColumnsArray(), var3.getMaterializedColumns(), var8, var5, var4);
            } else {
               this.readRecordFromArray(var2, var2.length - 1, (int[])null, (int[])null, var8, var5, var4);
            }

            while(var5 != null) {
               StoredPage var9 = this.getOverflowPage(var5.getOverflowPage());
               var5 = var9.restoreLongRecordFromSlot(var2, var3, var4, var5);
               var9.unlatch();
               Object var11 = null;
            }

            return var3 == null || var3.getQualifierList() == null || this.qualifyRecordFromRow(var2, var3.getQualifierList());
         }
      } catch (IOException var10) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var10, new Object[]{this.getPageId()}));
      }
   }

   private StoredRecordHeader restoreLongRecordFromSlot(Object[] var1, FetchDescriptor var2, RecordHandle var3, StoredRecordHeader var4) throws StandardException {
      int var5 = this.findRecordById(var4.getOverflowId(), 0);
      StoredRecordHeader var6 = this.getHeaderAtSlot(var5);

      try {
         int var7 = this.getRecordOffset(var5) + var6.size();
         ArrayInputStream var8 = this.rawDataIn;
         var8.setPosition(var7);
         if (var2 != null) {
            if (var2.getQualifierList() != null) {
               var2.reset();
            }

            this.readRecordFromArray(var1, var2.getValidColumns() == null ? var1.length - 1 : var2.getMaxFetchColumnId(), var2.getValidColumnsArray(), var2.getMaterializedColumns(), var8, var6, var3);
         } else {
            this.readRecordFromArray(var1, var1.length - 1, (int[])null, (int[])null, var8, var6, var3);
         }

         return var6.hasOverflow() ? var6 : null;
      } catch (IOException var9) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var9, new Object[]{this.getPageId()}));
      }
   }

   public int newRecordId() {
      return this.nextId;
   }

   public int newRecordIdAndBump() {
      this.headerOutOfDate = true;
      return this.nextId++;
   }

   protected int newRecordId(int var1) {
      return var1 + 1;
   }

   public boolean isOverflowPage() {
      return this.isOverflowPage;
   }

   public final int getPageSize() {
      return this.pageData.length;
   }

   protected final void clearSection(int var1, int var2) {
      Arrays.fill(this.pageData, var1, var1 + var2, (byte)0);
   }

   protected int getMaxFreeSpace() {
      return this.getPageSize() - 60 - 8;
   }

   protected int getCurrentFreeSpace() {
      return this.freeSpace;
   }

   private void readPageHeader() throws IOException {
      ArrayInputStream var1 = this.rawDataIn;
      var1.setPosition(4);
      this.isOverflowPage = var1.readBoolean();
      this.setPageStatus(var1.readByte());
      this.setPageVersion(var1.readLong());
      this.slotsInUse = var1.readUnsignedShort();
      this.nextId = var1.readInt();
      this.generation = var1.readInt();
      this.prevGeneration = var1.readInt();
      this.bipLocation = var1.readLong();
      this.deletedRowCount = var1.readUnsignedShort() - 1;
      long var2 = (long)var1.readUnsignedShort();
      var2 = (long)var1.readInt();
      var2 = var1.readLong();
      var2 = var1.readLong();
   }

   private void updatePageHeader() throws IOException {
      this.rawDataOut.setPosition(4);
      this.logicalDataOut.writeBoolean(this.isOverflowPage);
      this.logicalDataOut.writeByte(this.getPageStatus());
      this.logicalDataOut.writeLong(this.getPageVersion());
      this.logicalDataOut.writeShort(this.slotsInUse);
      this.logicalDataOut.writeInt(this.nextId);
      this.logicalDataOut.writeInt(this.generation);
      this.logicalDataOut.writeInt(this.prevGeneration);
      this.logicalDataOut.writeLong(this.bipLocation);
      this.logicalDataOut.writeShort(this.deletedRowCount + 1);
      this.logicalDataOut.writeShort(0);
      this.logicalDataOut.writeInt(this.dataFactory.random());
      this.logicalDataOut.writeLong(0L);
      this.logicalDataOut.writeLong(0L);
      this.headerOutOfDate = false;
   }

   private void updatePageVersion() throws IOException {
      this.rawDataOut.setPosition(6);
      this.logicalDataOut.writeLong(this.getPageVersion());
   }

   private int getSlotOffset(int var1) {
      return this.slotTableOffsetToFirstEntry - var1 * this.slotEntrySize;
   }

   private int getRecordOffset(int var1) {
      byte[] var2 = this.pageData;
      int var3 = this.slotTableOffsetToFirstEntry - var1 * this.slotEntrySize;
      return this.slotFieldSize == 2 ? (var2[var3++] & 255) << 8 | var2[var3] & 255 : (var2[var3++] & 255) << 24 | (var2[var3++] & 255) << 16 | (var2[var3++] & 255) << 8 | var2[var3] & 255;
   }

   private void setRecordOffset(int var1, int var2) throws IOException {
      this.rawDataOut.setPosition(this.getSlotOffset(var1));
      if (this.slotFieldSize == 2) {
         this.logicalDataOut.writeShort(var2);
      } else {
         this.logicalDataOut.writeInt(var2);
      }

   }

   protected int getRecordPortionLength(int var1) throws IOException {
      ArrayInputStream var2 = this.rawDataIn;
      var2.setPosition(this.slotTableOffsetToFirstRecordLengthField - var1 * this.slotEntrySize);
      return this.slotFieldSize == 2 ? var2.readUnsignedShort() : var2.readInt();
   }

   public int getReservedCount(int var1) throws IOException {
      ArrayInputStream var2 = this.rawDataIn;
      var2.setPosition(this.slotTableOffsetToFirstReservedSpaceField - var1 * this.slotEntrySize);
      return this.slotFieldSize == 2 ? var2.readUnsignedShort() : var2.readInt();
   }

   private void updateRecordPortionLength(int var1, int var2, int var3) throws IOException {
      this.rawDataOut.setPosition(this.slotTableOffsetToFirstRecordLengthField - var1 * this.slotEntrySize);
      if (this.slotFieldSize == 2) {
         this.logicalDataOut.writeShort(this.getRecordPortionLength(var1) + var2);
      } else {
         this.logicalDataOut.writeInt(this.getRecordPortionLength(var1) + var2);
      }

      if (var3 != 0) {
         if (this.slotFieldSize == 2) {
            this.logicalDataOut.writeShort(this.getReservedCount(var1) + var3);
         } else {
            this.logicalDataOut.writeInt(this.getReservedCount(var1) + var3);
         }
      }

   }

   private void initSlotTable(PageKey var1) throws StandardException {
      int var2 = this.slotsInUse;
      this.initializeHeaders(var2);
      this.clearAllSpace();
      this.freeSpace -= var2 * this.slotEntrySize;
      int var3 = -1;
      int var4 = -1;

      try {
         for(int var5 = 0; var5 < var2; ++var5) {
            int var6 = this.getRecordOffset(var5);
            if (var6 < 60 || var6 >= this.getPageSize() - 8) {
               throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", new Object[]{var1}));
            }

            if (var6 > var4) {
               var4 = var6;
               var3 = var5;
            }
         }

         this.bumpRecordCount(var2);
         if (var3 != -1) {
            this.firstFreeByte = var4 + this.getTotalSpace(var3);
            this.freeSpace -= this.firstFreeByte - 60;
         }

         if (this.deletedRowCount == -1) {
            int var9 = 0;
            int var10 = this.slotsInUse;

            for(int var7 = 0; var7 < var10; ++var7) {
               if (this.isDeletedOnPage(var7)) {
                  ++var9;
               }
            }

            this.deletedRowCount = var9;
         }

      } catch (IOException var8) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var8, new Object[]{var1}));
      }
   }

   private void setSlotEntry(int var1, int var2, int var3, int var4) throws IOException {
      this.rawDataOut.setPosition(this.getSlotOffset(var1));
      if (this.slotFieldSize == 2) {
         this.logicalDataOut.writeShort(var2);
         this.logicalDataOut.writeShort(var3);
         this.logicalDataOut.writeShort(var4);
      } else {
         this.logicalDataOut.writeInt(var2);
         this.logicalDataOut.writeInt(var3);
         this.logicalDataOut.writeInt(var4);
      }

   }

   private void addSlotEntry(int var1, int var2, int var3, int var4) throws IOException {
      if (var1 < this.slotsInUse) {
         int var6 = this.getSlotOffset(this.slotsInUse - 1);
         int var7 = this.getSlotOffset(var1) + this.slotEntrySize - var6;
         int var5 = this.getSlotOffset(this.slotsInUse);
         System.arraycopy(this.pageData, var6, this.pageData, var5, var7);
      } else {
         this.getSlotOffset(var1);
      }

      this.freeSpace -= this.slotEntrySize;
      ++this.slotsInUse;
      this.headerOutOfDate = true;
      this.setSlotEntry(var1, var2, var3, var4);
   }

   private void removeSlotEntry(int var1) throws IOException {
      int var2 = this.getSlotOffset(this.slotsInUse - 1);
      int var3 = this.getSlotOffset(this.slotsInUse - 2);
      if (var1 != this.slotsInUse - 1) {
         int var4 = this.getSlotOffset(var1) - var2;
         System.arraycopy(this.pageData, var2, this.pageData, var3, var4);
      }

      this.clearSection(var2, this.slotEntrySize);
      this.freeSpace += this.slotEntrySize;
      --this.slotsInUse;
      this.headerOutOfDate = true;
   }

   public StoredRecordHeader recordHeaderOnDemand(int var1) {
      StoredRecordHeader var2 = new StoredRecordHeader(this.pageData, this.getRecordOffset(var1));
      this.setHeaderAtSlot(var1, var2);
      return var2;
   }

   public boolean entireRecordOnPage(int var1) throws StandardException {
      StoredRecordHeader var2 = this.getHeaderAtSlot(var1);
      if (var2.hasOverflow()) {
         return false;
      } else {
         try {
            int var3 = this.getRecordOffset(var1);
            int var4 = var2.getNumberFields();
            ArrayInputStream var5 = this.rawDataIn;
            var5.setPosition(var3 + var2.size());

            for(int var6 = 0; var6 < var4; ++var6) {
               int var7 = StoredFieldHeader.readStatus(var5);
               if (StoredFieldHeader.isOverflow(var7)) {
                  return false;
               }

               int var8 = StoredFieldHeader.readFieldDataLength(var5, var7, this.slotFieldSize);
               if (var8 != 0) {
                  var5.setPosition(var5.getPosition() + var8);
               }
            }

            return true;
         } catch (IOException var9) {
            throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var9, new Object[]{this.getPageId()}));
         }
      }
   }

   protected void purgeOverflowAtSlot(int var1, RecordHandle var2, boolean var3) throws StandardException {
      if (var1 >= 0 && var1 < this.slotsInUse) {
         RawTransaction var4 = this.owner.getTransaction();
         int[] var5 = new int[]{this.getHeaderAtSlot(var1).getId()};
         this.owner.getActionSet().actionPurge(var4, this, var1, 1, var5, var3);
      } else {
         throw StandardException.newException("XSDA1.S", new Object[0]);
      }
   }

   private void purgeOneColumnChain(long var1, int var3) throws StandardException {
      StoredPage var4 = null;
      boolean var5 = false;

      try {
         while(var1 != -1L) {
            var4 = this.getOverflowPage(var1);
            var5 = false;
            if (var4 == null) {
               break;
            }

            byte var6 = 0;
            RecordHandle var7 = var4.getNextColumnPiece(var6);
            if (var4.recordCount() == 1) {
               var5 = true;
               this.owner.removePage(var4);
            } else {
               var4.unlatch();
               var4 = null;
            }

            if (var7 != null) {
               var1 = var7.getPageNumber();
               var3 = var7.getId();
            } else {
               var1 = -1L;
            }
         }
      } finally {
         if (!var5 && var4 != null) {
            var4.unlatch();
            Object var12 = null;
         }

      }

   }

   private void purgeColumnChains(RawTransaction var1, int var2, RecordHandle var3) throws StandardException {
      try {
         StoredRecordHeader var4 = this.getHeaderAtSlot(var2);
         int var5 = var4.getNumberFields();
         ArrayInputStream var6 = this.rawDataIn;
         int var7 = this.getRecordOffset(var2) + var4.size();
         var6.setPosition(var7);

         for(int var8 = 0; var8 < var5; ++var8) {
            int var9 = StoredFieldHeader.readStatus(var6);
            int var10 = StoredFieldHeader.readFieldDataLength(var6, var9, this.slotFieldSize);
            if (!StoredFieldHeader.isOverflow(var9)) {
               if (var10 != 0) {
                  var6.setPosition(var6.getPosition() + var10);
               }
            } else {
               long var11 = CompressedNumber.readLong((InputStream)var6);
               int var13 = CompressedNumber.readInt((InputStream)var6);
               this.purgeOneColumnChain(var11, var13);
            }
         }

      } catch (IOException var14) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var14, new Object[]{this.getPageId()}));
      }
   }

   protected void purgeRowPieces(RawTransaction var1, int var2, RecordHandle var3, boolean var4) throws StandardException {
      this.purgeColumnChains(var1, var2, var3);
      StoredRecordHeader var5 = this.getHeaderAtSlot(var2);

      while(var5.hasOverflow()) {
         StoredPage var6 = this.getOverflowPage(var5.getOverflowPage());
         if (var6 == null) {
            break;
         }

         try {
            int var7 = getOverflowSlot(var6, var5);
            var6.purgeColumnChains(var1, var7, var3);
            var5 = var6.getHeaderAtSlot(var7);
            if (var7 == 0 && var6.recordCount() == 1) {
               try {
                  this.owner.removePage(var6);
               } finally {
                  var6 = null;
               }
            } else {
               var6.purgeOverflowAtSlot(var7, var3, var4);
               var6.unlatch();
               var6 = null;
            }
         } finally {
            if (var6 != null) {
               var6.unlatch();
               Object var16 = null;
            }

         }
      }

   }

   void removeOrphanedColumnChain(ReclaimSpace var1, ContainerHandle var2) throws StandardException {
      StoredPage var3 = (StoredPage)var2.getPageNoWait(var1.getColumnPageId());
      if (var3 != null) {
         boolean var4 = var3.equalTimeStamp(var1.getPageTimeStamp());
         var3.unlatch();
         if (var4) {
            RecordHandle var5 = var1.getHeadRowHandle();
            int var6 = this.findRecordById(var5.getId(), var5.getSlotNumberHint());
            if (var6 >= 0) {
               label152: {
                  StoredPage var7 = this;

                  try {
                     int var8 = var1.getColumnId();

                     StoredRecordHeader var9;
                     for(var9 = this.getHeaderAtSlot(var6); var9.getNumberFields() + var9.getFirstField() <= var8; var9 = var7.getHeaderAtSlot(getOverflowSlot(var7, var9))) {
                        if (var7 != this) {
                           var7.unlatch();
                           var7 = null;
                        }

                        if (!var9.hasOverflow()) {
                           break;
                        }

                        var7 = this.getOverflowPage(var9.getOverflowPage());
                     }

                     if (var9.getNumberFields() + var9.getFirstField() <= var8 || var7.isColumnOrphaned(var9, var8, var1.getColumnPageId(), (long)var1.getColumnRecordId())) {
                        break label152;
                     }

                     if (var7 != this) {
                        var7.unlatch();
                        var7 = null;
                     }
                  } catch (IOException var13) {
                     throw StandardException.newException("XSDA4.S", var13, new Object[0]);
                  } finally {
                     if (var7 != this && var7 != null) {
                        var7.unlatch();
                     }

                  }

                  return;
               }
            }

            long var15 = var1.getColumnPageId();
            int var16 = var1.getColumnRecordId();
            this.purgeOneColumnChain(var15, var16);
         }
      }
   }

   private boolean isColumnOrphaned(StoredRecordHeader var1, int var2, long var3, long var5) throws StandardException, IOException {
      int var7 = this.findRecordById(var1.getId(), 0);
      ArrayInputStream var8 = this.rawDataIn;
      int var9 = this.getRecordOffset(var7);
      var8.setPosition(var9 + var1.size());

      for(int var10 = var1.getFirstField(); var10 < var2; ++var10) {
         this.skipField(var8);
      }

      int var15 = StoredFieldHeader.readStatus(var8);
      StoredFieldHeader.readFieldDataLength(var8, var15, this.slotFieldSize);
      if (StoredFieldHeader.isOverflow(var15)) {
         long var12 = CompressedNumber.readLong((InputStream)var8);
         int var14 = CompressedNumber.readInt((InputStream)var8);
         if (var12 == var3 && (long)var14 == var5) {
            return false;
         }
      }

      return true;
   }

   private RecordHandle getNextColumnPiece(int var1) throws StandardException {
      try {
         StoredRecordHeader var2 = this.getHeaderAtSlot(var1);
         int var3 = var2.getNumberFields();
         if (var3 != 2) {
            return null;
         } else {
            ArrayInputStream var4 = this.rawDataIn;
            int var5 = this.getRecordOffset(var1) + var2.size();
            var4.setPosition(var5);
            this.skipField(var4);
            int var6 = StoredFieldHeader.readStatus(var4);
            StoredFieldHeader.readFieldDataLength(var4, var6, this.slotFieldSize);
            long var8 = CompressedNumber.readLong((InputStream)var4);
            int var10 = CompressedNumber.readInt((InputStream)var4);
            return this.owner.makeRecordHandle(var8, var10);
         }
      } catch (IOException var11) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var11, new Object[]{this.getPageId()}));
      }
   }

   private void initSpace() {
      this.totalSpace = this.getMaxFreeSpace();
      this.maxFieldSize = this.totalSpace - this.slotEntrySize - 16 - 12;
   }

   private void clearAllSpace() {
      this.freeSpace = this.totalSpace;
      this.firstFreeByte = this.getPageSize() - this.totalSpace - 8;
   }

   private void compressPage(int var1, int var2) throws IOException {
      int var3 = var2 + 1 - var1;
      if (var2 + 1 != this.firstFreeByte) {
         int var4 = this.firstFreeByte - var2 - 1;
         System.arraycopy(this.pageData, var2 + 1, this.pageData, var1, var4);

         for(int var5 = 0; var5 < this.slotsInUse; ++var5) {
            int var6 = this.getRecordOffset(var5);
            if (var6 >= var2 + 1) {
               var6 -= var3;
               this.setRecordOffset(var5, var6);
            }
         }
      }

      this.freeSpace += var3;
      this.firstFreeByte -= var3;
      this.clearSection(this.firstFreeByte, var3);
   }

   protected void expandPage(int var1, int var2) throws IOException {
      int var3 = this.firstFreeByte - var1;
      if (var3 > 0) {
         System.arraycopy(this.pageData, var1, this.pageData, var1 + var2, var3);

         for(int var4 = 0; var4 < this.slotsInUse; ++var4) {
            int var5 = this.getRecordOffset(var4);
            if (var5 >= var1) {
               var5 += var2;
               this.setRecordOffset(var4, var5);
            }
         }
      }

      this.freeSpace -= var2;
      this.firstFreeByte += var2;
   }

   private void shrinkPage(int var1, int var2) throws IOException {
      int var3 = this.firstFreeByte - var1;
      if (var3 > 0) {
         System.arraycopy(this.pageData, var1, this.pageData, var1 - var2, var3);

         for(int var4 = 0; var4 < this.slotsInUse; ++var4) {
            int var5 = this.getRecordOffset(var4);
            if (var5 >= var1) {
               var5 -= var2;
               this.setRecordOffset(var4, var5);
            }
         }
      }

      this.freeSpace += var2;
      this.firstFreeByte -= var2;
   }

   public int getRecordLength(int var1) throws IOException {
      return this.getRecordPortionLength(var1);
   }

   protected boolean getIsOverflow(int var1) throws IOException {
      return this.getHeaderAtSlot(var1).hasOverflow();
   }

   public int logRow(int var1, boolean var2, int var3, Object[] var4, FormatableBitSet var5, DynamicByteArrayOutputStream var6, int var7, byte var8, int var9, int var10, int var11) throws StandardException, IOException {
      if (!var2 && var9 != -1 && var10 == -1) {
         return var9;
      } else {
         int var12 = this.freeSpace;
         this.setOutputStream(var6);
         int var13 = var6.getPosition();
         this.userRowSize = 0;
         boolean var14 = false;
         if (var9 != -1) {
            var12 = var10;
            var13 = var6.getBeginPosition();
         } else {
            if (!var2) {
               var12 += this.getTotalSpace(var1);
            } else {
               var12 -= this.slotEntrySize;
               if (var7 == 0) {
                  var14 = true;
               }
            }

            if (var12 <= 0) {
               throw new NoSpaceOnPage(this.isOverflowPage());
            }
         }

         int var15;
         try {
            if (var4 != null) {
               var15 = 0;
               StoredRecordHeader var16;
               if (var2) {
                  var16 = new StoredRecordHeader();
               } else {
                  var16 = new StoredRecordHeader(this.getHeaderAtSlot(var1));
                  var7 = var16.getFirstField();
               }

               if (var5 == null) {
                  var15 = var4.length - var7;
               } else {
                  for(int var17 = var5.getLength() - 1; var17 >= var7; --var17) {
                     if (var5.isSet(var17)) {
                        var15 = var17 + 1 - var7;
                        break;
                     }
                  }
               }

               int var46 = -1;
               if (var2) {
                  var16.setId(var3);
                  var16.setNumberFields(var15);
               } else {
                  var46 = var16.getNumberFields();
                  if (var15 > var46) {
                     if (var16.hasOverflow()) {
                        var15 = var46;
                     } else {
                        var16.setNumberFields(var15);
                     }
                  } else if (var15 < var46) {
                     if (var5 == null) {
                        var16.setNumberFields(var15);
                     } else {
                        var15 = var46;
                     }
                  }
               }

               int var18 = var7 + var15;
               if (var9 >= var18) {
                  byte var47 = -1;
                  return var47;
               }

               if ((var8 & 1) != 1) {
                  var16.setFirstField(var7);
               }

               int var19 = var9;
               if (var9 == -1) {
                  int var20 = var16.write(this.logicalDataOut);
                  var12 -= var20;
                  if (var12 < 0) {
                     throw new NoSpaceOnPage(this.isOverflowPage());
                  }

                  var19 = var7;
               }

               boolean var48 = false;
               int var21 = var5 == null ? 0 : var5.getLength();
               if (var5 != null && !var2 && var5 != null && var19 < var7 + var46) {
                  this.rawDataIn.setPosition(this.getFieldOffset(var1, var19));
                  var48 = true;
               }

               int var23 = 0;
               int var24 = var6.getPosition();
               int var25 = var7;
               if (var12 > 12) {
                  var24 = -1;
               }

               byte var26 = 1;

               for(int var27 = var19; var27 < var18; ++var27) {
                  Object var28 = null;
                  boolean var29 = false;
                  if (var5 == null || var21 > var27 && var5.isSet(var27)) {
                     if (var27 < var4.length) {
                        var28 = var4[var27];
                     }
                  } else if (!var2) {
                     var29 = true;
                  }

                  if (var12 > 12) {
                     var24 = var6.getPosition();
                     var25 = var27;
                  }

                  int var22 = var12;
                  if (var29) {
                     if (var27 < var7 + var46) {
                        int var30 = this.rawDataIn.getPosition();
                        this.skipField(this.rawDataIn);
                        int var31 = this.rawDataIn.getPosition() - var30;
                        if (var31 <= var12) {
                           this.logColumn((Object[])null, 0, var6, Integer.MAX_VALUE, 0, var11);
                           var12 -= var31;
                        }
                     } else {
                        var12 = this.logColumn((Object[])null, 0, var6, var12, 3, var11);
                     }
                  } else {
                     if (var48 && var27 < var7 + var46) {
                        this.skipField(this.rawDataIn);
                     }

                     try {
                        if (var28 == null) {
                           var12 = this.logColumn((Object[])null, 0, var6, var12, var26, var11);
                        } else {
                           var12 = this.logColumn(var4, var27, var6, var12, var26, var11);
                        }
                     } catch (LongColumnException var41) {
                        if ((var8 & 1) == 1) {
                           if (var41.getColumn() instanceof InputStream && var4[var27] instanceof StreamStorable && (var4[var27] instanceof InputStream || ((StreamStorable)var4[var27]).returnStream() != null)) {
                              ((StreamStorable)var4[var27]).setStream((InputStream)var41.getColumn());
                           }

                           throw new NoSpaceOnPage(this.isOverflowPage());
                        }

                        if (var12 >= 14 && var27 == var18 - 1 || var12 >= 28 && var27 < var18 - 1) {
                           var6.setBeginPosition(var13);
                           var41.setExceptionInfo(var6, var27, var12);
                           throw var41;
                        }
                     }
                  }

                  var23 += var22 - var12;
                  boolean var50 = var11 == 100 ? false : this.isLong(var23, var11);
                  int var49;
                  if (var22 != var12 && !var50) {
                     var49 = var18;
                  } else {
                     if ((var8 & 1) == 1) {
                        throw new NoSpaceOnPage(this.isOverflowPage());
                     }

                     if (var50) {
                        var6.setPosition(var6.getPosition() - var23);
                     }

                     var49 = var27;
                  }

                  if ((var22 == var12 || (var8 & 16) == 16) && var12 < 12) {
                     if (var27 == var7 || var24 < 0) {
                        throw new NoSpaceOnPage(this.isOverflowPage());
                     }

                     var6.setPosition(var24);
                     var49 = var25;
                  }

                  if (var49 < var18) {
                     int var32 = var49 - var7;
                     int var33 = var16.size();
                     var16.setNumberFields(var32);
                     int var34 = var16.size();
                     int var35 = var6.getPosition();
                     if (var33 > var34) {
                        int var36 = var33 - var34;
                        var6.setBeginPosition(var13 + var36);
                        var6.setPosition(var13 + var36);
                     } else if (var34 > var33) {
                        var6.setPosition(var13);
                     } else {
                        var6.setBeginPosition(var13);
                        var6.setPosition(var13);
                     }

                     var16.write(this.logicalDataOut);
                     var6.setPosition(var35);
                     if (!var2 && var5 != null) {
                        this.handleIncompleteLogRow(var1, var49, var5, var6);
                     }

                     int var37 = var49;
                     return var37;
                  }

                  var26 = 0;
               }

               var6.setBeginPosition(var13);
               var7 = -1;
               if (var14 && var12 < this.minimumRecordSize - this.userRowSize) {
                  throw new NoSpaceOnPage(this.isOverflowPage());
               }

               return var7;
            }

            var15 = this.logOverflowRecord(var1, var12, var6);
         } finally {
            this.resetOutputStream();
         }

         return var15;
      }
   }

   private void handleIncompleteLogRow(int var1, int var2, FormatableBitSet var3, DynamicByteArrayOutputStream var4) throws StandardException {
      StoredRecordHeader var5 = this.getHeaderAtSlot(var1);
      int var6 = var5.getFirstField() + var5.getNumberFields();
      boolean var7 = false;
      int var8 = var3.size();

      for(int var9 = var2; var9 < var6; ++var9) {
         if (var8 <= var9 || !var3.get(var9)) {
            var7 = true;
            break;
         }
      }

      if (var7) {
         Object[] var14 = new Object[var6 - var2];
         ByteArrayOutputStream var10 = null;

         for(int var11 = var2; var11 < var6; ++var11) {
            if (var8 <= var11 || !var3.get(var11)) {
               try {
                  if (var10 == null) {
                     var10 = new ByteArrayOutputStream();
                  } else {
                     var10.reset();
                  }

                  this.logField(var1, var11, var10);
                  var14[var11 - var2] = new RawField(var10.toByteArray());
               } catch (IOException var13) {
                  throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var13, new Object[]{this.getPageId()}));
               }
            }
         }

         LongColumnException var15 = new LongColumnException();
         var15.setExceptionInfo(var4, var2, -1);
         var15.setColumn(var14);
         throw var15;
      }
   }

   public void restoreRecordFromStream(LimitObjectInput var1, Object[] var2) throws StandardException, IOException {
      StoredRecordHeader var3 = new StoredRecordHeader();
      var3.read(var1);
      this.readRecordFromStream(var2, var2.length - 1, (int[])null, (int[])null, var1, var3, (RecordHandle)null);
   }

   private boolean qualifyRecordFromRow(Object[] var1, Qualifier[][] var2) throws StandardException {
      boolean var3 = true;

      for(int var4 = 0; var4 < var2[0].length; ++var4) {
         var3 = false;
         Qualifier var5 = var2[0][var4];
         DataValueDescriptor var6 = (DataValueDescriptor)var1[var5.getColumnId()];
         var3 = var6.compare(var5.getOperator(), var5.getOrderable(), var5.getOrderedNulls(), var5.getUnknownRV());
         if (var5.negateCompareResult()) {
            var3 = !var3;
         }

         if (!var3) {
            return false;
         }
      }

      for(int var10 = 1; var10 < var2.length; ++var10) {
         var3 = false;

         for(int var11 = 0; var11 < var2[var10].length; ++var11) {
            Qualifier var12 = var2[var10][var11];
            int var7 = var12.getColumnId();
            DataValueDescriptor var8 = (DataValueDescriptor)var1[var12.getColumnId()];
            var3 = var8.compare(var12.getOperator(), var12.getOrderable(), var12.getOrderedNulls(), var12.getUnknownRV());
            if (var12.negateCompareResult()) {
               var3 = !var3;
            }

            if (var3) {
               break;
            }
         }

         if (!var3) {
            break;
         }
      }

      return var3;
   }

   private final void readOneColumnFromPage(Object[] var1, int var2, int var3, StoredRecordHeader var4, RecordHandle var5) throws StandardException, IOException {
      Object var6 = null;
      ArrayInputStream var7 = this.rawDataIn;

      try {
         Object var8 = var1[var2];
         if (var2 <= var4.getNumberFields() - 1) {
            for(int var23 = var2; var23 > 0; --var23) {
               var3 += StoredFieldHeader.readTotalFieldLength(this.pageData, var3);
            }

            int var24 = StoredFieldHeader.readStatus(this.pageData, var3);
            int var10 = StoredFieldHeader.readFieldLengthAndSetStreamPosition(this.pageData, var3 + 1, var24, this.slotFieldSize, var7);
            if (!StoredFieldHeader.isNonexistent(var24)) {
               boolean var11 = StoredFieldHeader.isOverflow(var24);
               OverflowInputStream var12 = null;
               if (var11) {
                  long var13 = CompressedNumber.readLong((InputStream)var7);
                  int var15 = CompressedNumber.readInt((InputStream)var7);
                  MemByteHolder var16 = new MemByteHolder(this.pageData.length);
                  var12 = new OverflowInputStream(var16, this.owner, var13, var15, var5);
               }

               if (var8 instanceof DataValueDescriptor) {
                  DataValueDescriptor var25 = (DataValueDescriptor)var8;
                  if (StoredFieldHeader.isNull(var24)) {
                     var25.restoreToNull();
                  } else if (!var11) {
                     var7.setLimit(var10);
                     var25.readExternalFromArray(var7);
                     var6 = null;
                     int var14 = var7.clearLimit();
                     if (var14 != 0) {
                        DataInputUtil.skipFully(var7, var14);
                     }
                  } else {
                     FormatIdInputStream var27 = new FormatIdInputStream(var12);
                     if (var25 instanceof StreamStorable) {
                        ((StreamStorable)var25).setStream(var27);
                     } else {
                        var25.readExternal(var27);
                        var6 = null;
                     }
                  }
               } else {
                  if (StoredFieldHeader.isNull(var24)) {
                     throw StandardException.newException("XSDA6.S", new Object[]{Integer.toString(var2)});
                  }

                  var7.setLimit(var10);
                  var1[var2] = var7.readObject();
                  var6 = null;
                  int var26 = var7.clearLimit();
                  if (var26 != 0) {
                     DataInputUtil.skipFully(var7, var26);
                  }
               }
            } else if (var8 instanceof DataValueDescriptor) {
               ((DataValueDescriptor)var8).restoreToNull();
            } else {
               var1[var2] = null;
            }
         } else if (var8 instanceof DataValueDescriptor) {
            ((DataValueDescriptor)var8).restoreToNull();
         } else {
            var1[var2] = null;
         }

      } catch (IOException var17) {
         if (var6 != null) {
            var7.clearLimit();
            if (var17 instanceof EOFException) {
               throw StandardException.newException("XSDA7.S", var17, new Object[]{((ErrorObjectInput)var6).getErrorInfo()});
            } else {
               Exception var9 = ((ErrorObjectInput)var6).getNestedException();
               if (var9 != null) {
                  if (var9 instanceof InstantiationException) {
                     throw StandardException.newException("XSDAM.S", var9, new Object[]{((ErrorObjectInput)var6).getErrorInfo()});
                  }

                  if (var9 instanceof IllegalAccessException) {
                     throw StandardException.newException("XSDAN.S", var9, new Object[]{((ErrorObjectInput)var6).getErrorInfo()});
                  }

                  if (var9 instanceof StandardException) {
                     throw (StandardException)var9;
                  }
               }

               throw StandardException.newException("XSDA8.S", var17, new Object[]{((ErrorObjectInput)var6).getErrorInfo()});
            }
         } else {
            throw var17;
         }
      } catch (ClassNotFoundException var18) {
         var7.clearLimit();
         throw StandardException.newException("XSDA9.S", var18, new Object[]{((ErrorObjectInput)var6).getErrorInfo()});
      } catch (LinkageError var19) {
         if (var6 != null) {
            var7.clearLimit();
            throw StandardException.newException("XSDA8.S", var19, new Object[]{((ErrorObjectInput)var6).getErrorInfo()});
         } else {
            throw var19;
         }
      }
   }

   private final boolean qualifyRecordFromSlot(Object[] var1, int var2, FetchDescriptor var3, StoredRecordHeader var4, RecordHandle var5) throws StandardException, IOException {
      boolean var6 = true;
      Qualifier[][] var7 = var3.getQualifierList();
      int[] var8 = var3.getMaterializedColumns();

      for(int var9 = 0; var9 < var7[0].length; ++var9) {
         var6 = false;
         Qualifier var10 = var7[0][var9];
         int var11 = var10.getColumnId();
         if (var8[var11] == 0) {
            this.readOneColumnFromPage(var1, var11, var2, var4, var5);
            var8[var11] = var2;
         }

         var6 = ((DataValueDescriptor)var1[var11]).compare(var10.getOperator(), var10.getOrderable(), var10.getOrderedNulls(), var10.getUnknownRV());
         if (var10.negateCompareResult()) {
            var6 = !var6;
         }

         if (!var6) {
            return false;
         }
      }

      for(int var14 = 1; var14 < var7.length; ++var14) {
         var6 = false;

         for(int var15 = 0; var15 < var7[var14].length; ++var15) {
            Qualifier var16 = var7[var14][var15];
            int var12 = var16.getColumnId();
            if (var8[var12] == 0) {
               this.readOneColumnFromPage(var1, var12, var2, var4, var5);
               var8[var12] = var2;
            }

            var6 = ((DataValueDescriptor)var1[var12]).compare(var16.getOperator(), var16.getOrderable(), var16.getOrderedNulls(), var16.getUnknownRV());
            if (var16.negateCompareResult()) {
               var6 = !var6;
            }

            if (var6) {
               break;
            }
         }

         if (!var6) {
            break;
         }
      }

      return var6;
   }

   private final boolean readRecordFromStream(Object[] var1, int var2, int[] var3, int[] var4, LimitObjectInput var5, StoredRecordHeader var6, RecordHandle var7) throws StandardException, IOException {
      Object var8 = null;

      try {
         int var9 = var6.getNumberFields();
         int var29 = var6.getFirstField();
         if (var29 > var2) {
            return true;
         } else {
            int var11 = var9 + var29;
            int var12 = var3 == null ? 0 : var3.length;

            for(int var13 = var29; var13 <= var2; ++var13) {
               if (var3 != null && (var12 <= var13 || var3[var13] == 0) || var4 != null && var4[var13] != 0) {
                  if (var13 < var11) {
                     this.skipField(var5);
                  }
               } else if (var13 >= var11) {
                  Object var14 = var1[var13];
                  if (var14 instanceof DataValueDescriptor) {
                     ((DataValueDescriptor)var14).restoreToNull();
                  } else {
                     var1[var13] = null;
                  }
               } else {
                  int var30 = StoredFieldHeader.readStatus(var5);
                  int var15 = StoredFieldHeader.readFieldDataLength(var5, var30, this.slotFieldSize);
                  Object var16 = var1[var13];
                  OverflowInputStream var17 = null;
                  if (StoredFieldHeader.isNonexistent(var30)) {
                     if (var16 instanceof DataValueDescriptor) {
                        ((DataValueDescriptor)var16).restoreToNull();
                     } else {
                        var1[var13] = null;
                     }
                  } else {
                     boolean var18 = StoredFieldHeader.isOverflow(var30);
                     if (var18) {
                        long var19 = CompressedNumber.readLong((InputStream)var5);
                        int var21 = CompressedNumber.readInt((InputStream)var5);
                        MemByteHolder var22 = new MemByteHolder(this.pageData.length);
                        var17 = new OverflowInputStream(var22, this.owner, var19, var21, var7);
                     }

                     if (var16 instanceof DataValueDescriptor) {
                        DataValueDescriptor var31 = (DataValueDescriptor)var16;
                        if (StoredFieldHeader.isNull(var30)) {
                           var31.restoreToNull();
                        } else if (!var18) {
                           var5.setLimit(var15);
                           var31.readExternal(var5);
                           var8 = null;
                           int var20 = var5.clearLimit();
                           if (var20 != 0) {
                              DataInputUtil.skipFully(var5, var20);
                           }
                        } else {
                           FormatIdInputStream var33 = new FormatIdInputStream(var17);
                           boolean var34 = true;
                           if (!(var31 instanceof StreamStorable)) {
                              var34 = false;
                           }

                           if (var34) {
                              ((StreamStorable)var31).setStream(var33);
                           } else {
                              var31.readExternal(var33);
                              var8 = null;
                           }
                        }
                     } else {
                        if (StoredFieldHeader.isNull(var30)) {
                           throw StandardException.newException("XSDA6.S", new Object[]{Integer.toString(var13)});
                        }

                        var5.setLimit(var15);
                        var1[var13] = var5.readObject();
                        var8 = null;
                        int var32 = var5.clearLimit();
                        if (var32 != 0) {
                           DataInputUtil.skipFully(var5, var32);
                        }
                     }
                  }
               }
            }

            if (var9 + var29 > var2) {
               return true;
            } else {
               return false;
            }
         }
      } catch (IOException var23) {
         if (var8 != null) {
            var5.clearLimit();
            if (var23 instanceof EOFException) {
               throw StandardException.newException("XSDA7.S", var23, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
            } else {
               Exception var10 = ((ErrorObjectInput)var8).getNestedException();
               if (var10 != null) {
                  if (var10 instanceof InstantiationException) {
                     throw StandardException.newException("XSDAM.S", var10, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
                  }

                  if (var10 instanceof IllegalAccessException) {
                     throw StandardException.newException("XSDAN.S", var10, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
                  }

                  if (var10 instanceof StandardException) {
                     throw (StandardException)var10;
                  }
               }

               throw StandardException.newException("XSDA8.S", var23, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
            }
         } else {
            throw var23;
         }
      } catch (ClassNotFoundException var24) {
         var5.clearLimit();
         throw StandardException.newException("XSDA9.S", var24, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
      } catch (LinkageError var25) {
         if (var8 != null) {
            var5.clearLimit();
            throw StandardException.newException("XSDA8.S", var25, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
         } else {
            throw var25;
         }
      }
   }

   private final boolean readRecordFromArray(Object[] var1, int var2, int[] var3, int[] var4, ArrayInputStream var5, StoredRecordHeader var6, RecordHandle var7) throws StandardException, IOException {
      Object var8 = null;

      try {
         int var9 = var6.getNumberFields();
         int var30 = var6.getFirstField();
         if (var30 > var2) {
            return true;
         } else {
            int var11 = var9 + var30;
            int var12 = var3 == null ? 0 : var3.length;
            int var13 = var5.getPosition();

            for(int var14 = var30; var14 <= var2; ++var14) {
               if (var3 != null && (var12 <= var14 || var3[var14] == 0) || var4 != null && var4[var14] != 0) {
                  if (var14 < var11) {
                     var13 += StoredFieldHeader.readTotalFieldLength(this.pageData, var13);
                  }
               } else if (var14 < var11) {
                  int var15 = StoredFieldHeader.readStatus(this.pageData, var13);
                  int var16 = StoredFieldHeader.readFieldLengthAndSetStreamPosition(this.pageData, var13 + 1, var15, this.slotFieldSize, var5);
                  Object var17 = var1[var14];
                  OverflowInputStream var18 = null;
                  if ((var15 & 5) != 5) {
                     boolean var19 = (var15 & 2) != 0;
                     if (var19) {
                        long var20 = CompressedNumber.readLong((InputStream)var5);
                        int var22 = CompressedNumber.readInt((InputStream)var5);
                        MemByteHolder var23 = new MemByteHolder(this.pageData.length);
                        var18 = new OverflowInputStream(var23, this.owner, var20, var22, var7);
                     }

                     if (var17 instanceof DataValueDescriptor) {
                        DataValueDescriptor var32 = (DataValueDescriptor)var17;
                        if ((var15 & 1) == 0) {
                           if (!var19) {
                              var5.setLimit(var16);
                              var32.readExternalFromArray(var5);
                              var8 = null;
                              int var21 = var5.clearLimit();
                              if (var21 != 0) {
                                 DataInputUtil.skipFully(var5, var21);
                              }
                           } else {
                              FormatIdInputStream var34 = new FormatIdInputStream(var18);
                              boolean var35 = true;
                              if (!(var32 instanceof StreamStorable)) {
                                 var35 = false;
                              }

                              if (var35) {
                                 ((StreamStorable)var32).setStream(var34);
                              } else {
                                 var32.readExternal(var34);
                                 var8 = null;
                              }
                           }
                        } else {
                           var32.restoreToNull();
                        }
                     } else {
                        if (StoredFieldHeader.isNull(var15)) {
                           throw StandardException.newException("XSDA6.S", new Object[]{Integer.toString(var14)});
                        }

                        var5.setLimit(var16);
                        var1[var14] = var5.readObject();
                        var8 = null;
                        int var33 = var5.clearLimit();
                        if (var33 != 0) {
                           DataInputUtil.skipFully(var5, var33);
                        }
                     }
                  } else if (var17 instanceof DataValueDescriptor) {
                     ((DataValueDescriptor)var17).restoreToNull();
                  } else {
                     var1[var14] = null;
                  }

                  var13 = var5.getPosition();
               } else {
                  Object var31 = var1[var14];
                  if (var31 instanceof DataValueDescriptor) {
                     ((DataValueDescriptor)var31).restoreToNull();
                  } else {
                     var1[var14] = null;
                  }
               }
            }

            if (var9 + var30 > var2) {
               return true;
            } else {
               return false;
            }
         }
      } catch (IOException var24) {
         if (var8 != null) {
            var5.clearLimit();
            if (var24 instanceof EOFException) {
               throw StandardException.newException("XSDA7.S", var24, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
            } else {
               Exception var10 = ((ErrorObjectInput)var8).getNestedException();
               if (var10 != null) {
                  if (var10 instanceof InstantiationException) {
                     throw StandardException.newException("XSDAM.S", var10, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
                  }

                  if (var10 instanceof IllegalAccessException) {
                     throw StandardException.newException("XSDAN.S", var10, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
                  }

                  if (var10 instanceof StandardException) {
                     throw (StandardException)var10;
                  }
               }

               throw StandardException.newException("XSDA8.S", var24, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
            }
         } else {
            throw var24;
         }
      } catch (ClassNotFoundException var25) {
         var5.clearLimit();
         throw StandardException.newException("XSDA9.S", var25, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
      } catch (LinkageError var26) {
         if (var8 != null) {
            var5.clearLimit();
            throw StandardException.newException("XSDA8.S", var26, new Object[]{((ErrorObjectInput)var8).getErrorInfo()});
         } else {
            throw var26;
         }
      }
   }

   public void restorePortionLongColumn(OverflowInputStream var1) throws StandardException, IOException {
      int var2 = this.findRecordById(var1.getOverflowId(), 0);
      StoredRecordHeader var3 = this.getHeaderAtSlot(var2);
      int var4 = this.getRecordOffset(var2);
      int var5 = var3.getNumberFields();
      this.rawDataIn.setPosition(var4 + var3.size());
      int var6 = StoredFieldHeader.readStatus(this.rawDataIn);
      int var7 = StoredFieldHeader.readFieldDataLength(this.rawDataIn, var6, this.slotFieldSize);
      ByteHolder var8 = var1.getByteHolder();
      var8.write(this.rawDataIn, (long)var7);
      var1.setByteHolder(var8);
      if (var5 == 1) {
         var1.setOverflowPage(-1L);
         var1.setOverflowId(-1);
      } else {
         var6 = StoredFieldHeader.readStatus(this.rawDataIn);
         StoredFieldHeader.readFieldDataLength(this.rawDataIn, var6, this.slotFieldSize);
         long var10 = CompressedNumber.readLong((InputStream)this.rawDataIn);
         int var12 = CompressedNumber.readInt((InputStream)this.rawDataIn);
         var1.setOverflowPage(var10);
         var1.setOverflowId(var12);
      }

   }

   public void logColumn(int var1, int var2, Object var3, DynamicByteArrayOutputStream var4, int var5) throws StandardException, IOException {
      int var6 = this.freeSpace;
      int var7 = -1;
      var6 += this.getReservedCount(var1);
      this.rawDataIn.setPosition(this.getFieldOffset(var1, var2));
      int var8 = StoredFieldHeader.readStatus(this.rawDataIn);
      int var9 = StoredFieldHeader.readFieldDataLength(this.rawDataIn, var8, this.slotFieldSize);
      var6 += StoredFieldHeader.size(var8, var9, this.slotFieldSize) + var9;

      try {
         this.setOutputStream(var4);
         var7 = this.rawDataOut.getPosition();
         Object[] var10 = new Object[]{var3};
         if (var6 == this.logColumn(var10, 0, var4, var6, 0, var5)) {
            throw new NoSpaceOnPage(this.isOverflowPage());
         }
      } finally {
         this.rawDataOut.setPosition(var7);
         this.resetOutputStream();
      }

   }

   public int logLongColumn(int var1, int var2, Object var3, DynamicByteArrayOutputStream var4) throws StandardException, IOException {
      int var5 = this.freeSpace;
      var5 -= this.slotEntrySize;
      if (var5 <= 0) {
         throw new NoSpaceOnPage(this.isOverflowPage());
      } else {
         this.setOutputStream(var4);
         int var6 = var4.getPosition();

         int var11;
         try {
            byte var7 = 1;
            StoredRecordHeader var8 = new StoredRecordHeader(var2, var7);
            int var9 = var8.write(this.logicalDataOut);
            var5 -= var9;
            if (var5 < 0) {
               throw new NoSpaceOnPage(this.isOverflowPage());
            }

            Object[] var10 = new Object[]{var3};
            var11 = this.logColumn(var10, 0, var4, var5, 2, 100);
         } finally {
            this.resetOutputStream();
         }

         return var11;
      }
   }

   private int logColumn(Object[] var1, int var2, DynamicByteArrayOutputStream var3, int var4, int var5, int var6) throws StandardException, IOException {
      Object var7 = var1 != null ? var1[var2] : null;
      if (var7 instanceof RawField) {
         byte[] var22 = ((RawField)var7).getData();
         if (var22.length <= var4) {
            var3.write(var22);
            var4 -= var22.length;
         }

         return var4;
      } else {
         boolean var8 = true;
         int var9 = StoredFieldHeader.setFixed(StoredFieldHeader.setInitial(), true);
         int var10 = var3.getPosition();
         int var11 = 0;
         int var13 = 0;
         if (var7 instanceof StreamStorable) {
            StreamStorable var14 = (StreamStorable)var7;
            if (var14.returnStream() != null) {
               var7 = var14.returnStream();
            }
         }

         if (var7 == null && var5 != 3) {
            var9 = StoredFieldHeader.setNonexistent(var9);
            StoredFieldHeader.write(this.logicalDataOut, var9, var13, this.slotFieldSize);
         } else if (var7 instanceof InputStream) {
            Object var28 = null;
            int var15 = 0;
            int var16 = this.getMaxDataLength(var4, var6);
            RememberBytesInputStream var29;
            if (var7 instanceof RememberBytesInputStream) {
               var29 = (RememberBytesInputStream)var7;
               var15 = var29.numBytesSaved();
            } else {
               var29 = new RememberBytesInputStream((InputStream)var7, new MemByteHolder(this.maxFieldSize + 1));
               if (var1[var2] instanceof StreamStorable) {
                  ((StreamStorable)var1[var2]).setStream(var29);
               }

               var7 = var29;
            }

            if (var15 < var16 + 1) {
               var15 = (int)((long)var15 + var29.fillBuf(var16 + 1 - var15));
            }

            if (var15 <= var16) {
               var13 = var15;
               var9 = StoredFieldHeader.setFixed(var9, true);
               StoredFieldHeader.write(this.logicalDataOut, var9, var15, this.slotFieldSize);
               var29.putBuf(this.logicalDataOut, var15);
            } else if (var5 == 2) {
               var8 = false;
               var13 = var16 - 12 - 2;
               var9 = StoredFieldHeader.setFixed(var9, true);
               StoredFieldHeader.write(this.logicalDataOut, var9, var13, this.slotFieldSize);
               var29.putBuf(this.logicalDataOut, var13);
               int var17 = var29.available();
               int var18 = var29.shiftToFront();
            } else {
               int var37 = this.maxFieldSize - var15 + 1;
               if (var37 > 0) {
                  var15 = (int)((long)var15 + var29.fillBuf(var37));
               }

               var13 = var15;
               var7 = var29;
            }
         } else if (var5 == 3) {
            var9 = StoredFieldHeader.setNull(var9, true);
            StoredFieldHeader.write(this.logicalDataOut, var9, var13, this.slotFieldSize);
         } else if (var7 instanceof DataValueDescriptor) {
            DataValueDescriptor var30 = (DataValueDescriptor)var7;
            boolean var33 = var5 == 3 || var30.isNull();
            if (var33) {
               var9 = StoredFieldHeader.setNull(var9, true);
            }

            int var12 = StoredFieldHeader.write(this.logicalDataOut, var9, var13, this.slotFieldSize);
            if (!var33) {
               try {
                  var11 = var3.getPosition();
                  var30.writeExternal(this.logicalDataOut);
               } catch (IOException var20) {
                  if (this.logicalDataOut != null) {
                     Exception var38 = this.logicalDataOut.getNestedException();
                     if (var38 != null && var38 instanceof StandardException) {
                        throw (StandardException)var38;
                     }
                  }

                  throw StandardException.newException("XSDAJ.S", var20, new Object[0]);
               }

               var13 = var3.getPosition() - var10 - var12;
            }
         } else if (var7 instanceof RecordHandle) {
            RecordHandle var31 = (RecordHandle)var7;
            var9 = StoredFieldHeader.setOverflow(var9, true);
            StoredFieldHeader.write(this.logicalDataOut, var9, var13, this.slotFieldSize);
            var13 += CompressedNumber.writeLong((OutputStream)var3, var31.getPageNumber());
            var13 += CompressedNumber.writeInt((OutputStream)var3, var31.getId());
         } else {
            int var25 = StoredFieldHeader.write(this.logicalDataOut, var9, var13, this.slotFieldSize);
            this.logicalDataOut.writeObject(var7);
            var13 = var3.getPosition() - var10 - var25;
         }

         var9 = StoredFieldHeader.setFixed(var9, false);
         int var32 = StoredFieldHeader.size(var9, var13, this.slotFieldSize) + var13;
         this.userRowSize += var13;
         boolean var34 = this.isLong(var32, var6);
         if ((var4 < var32 || var34) && var5 != 2) {
            if (var34) {
               if (!(var7 instanceof InputStream)) {
                  ByteArray var35 = new ByteArray(var3.getByteArray(), var11, var13);
                  ByteArrayInputStream var39 = new ByteArrayInputStream(var35.getArray(), var11, var13);
                  MemByteHolder var40 = new MemByteHolder(var13 + 1);
                  RememberBytesInputStream var19 = new RememberBytesInputStream(var39, var40);
                  var7 = var19;
               }

               var3.setPosition(var10);
               LongColumnException var36 = new LongColumnException();
               var36.setColumn(var7);
               throw var36;
            } else {
               var3.setPosition(var10);
               return var4;
            }
         } else {
            var3.setPosition(var10);
            var9 = StoredFieldHeader.setFixed(var9, true);
            int var26 = StoredFieldHeader.write(var3, var9, var13, this.slotFieldSize);
            var3.setPosition(var10 + var13 + var26);
            var4 -= var32;
            if (var5 == 2) {
               return var8 ? -1 : 1;
            } else {
               return var4;
            }
         }
      }
   }

   private int logOverflowRecord(int var1, int var2, DynamicByteArrayOutputStream var3) throws StandardException, IOException {
      this.setOutputStream(var3);
      StoredRecordHeader var4 = this.getHeaderAtSlot(var1);
      StoredRecordHeader var5 = this.getOverFlowRecordHeader();
      var5.setOverflowFields(var4);
      int var6 = var4.size();
      int var7 = var5.size();
      if (var6 < var7) {
         int var8 = var7 - var6;
         if (var2 < var8) {
            throw new NoSpaceOnPage(this.isOverflowPage());
         }
      }

      var5.write(this.logicalDataOut);
      this.logRecordDataPortion(var1, 0, var4, (FormatableBitSet)null, this.logicalDataOut, (RecordHandle)null);
      return -1;
   }

   private int logOverflowField(DynamicByteArrayOutputStream var1, int var2, long var3, int var5) throws StandardException, IOException {
      int var6 = StoredFieldHeader.setOverflow(StoredFieldHeader.setInitial(), true);
      int var7 = CompressedNumber.sizeLong(var3) + CompressedNumber.sizeInt(var5);
      int var8 = var7;
      var7 += StoredFieldHeader.size(var6, var7, this.slotFieldSize);
      var2 -= var7;
      if (var2 < 0) {
         throw new NoSpaceOnPage(this.isOverflowPage());
      } else {
         StoredFieldHeader.write(this.logicalDataOut, var6, var8, this.slotFieldSize);
         CompressedNumber.writeLong((OutputStream)var1, var3);
         CompressedNumber.writeInt((OutputStream)var1, var5);
         return var2;
      }
   }

   public void logRecord(int var1, int var2, int var3, FormatableBitSet var4, OutputStream var5, RecordHandle var6) throws StandardException, IOException {
      StoredRecordHeader var7 = this.getHeaderAtSlot(var1);
      if (var3 != var7.getId()) {
         StoredRecordHeader var8 = new StoredRecordHeader(var7);
         var8.setId(var3);
         var8.write(var5);
         Object var9 = null;
      } else {
         var7.write(var5);
      }

      this.logRecordDataPortion(var1, var2, var7, var4, var5, var6);
   }

   private void logRecordDataPortion(int var1, int var2, StoredRecordHeader var3, FormatableBitSet var4, OutputStream var5, RecordHandle var6) throws StandardException, IOException {
      int var7 = this.getRecordOffset(var1);
      int var8 = var3.size();
      var7 += var8;
      int var9 = var3.getFirstField();
      int var10 = var9 + var3.getNumberFields();
      int var11 = var4 == null ? 0 : var4.getLength();

      for(int var12 = var9; var12 < var10; ++var12) {
         this.rawDataIn.setPosition(var7);
         int var13 = StoredFieldHeader.readStatus(this.rawDataIn);
         int var14 = StoredFieldHeader.readFieldDataLength(this.rawDataIn, var13, this.slotFieldSize);
         if ((var4 == null || var11 > var12 && var4.isSet(var12)) && ((var2 & 2) == 0 || StoredFieldHeader.isOverflow(var13))) {
            if ((var2 & 1) != 0 && var6 != null && StoredFieldHeader.isOverflow(var13) && !this.owner.isTemporaryContainer()) {
               int var15 = this.rawDataIn.getPosition();
               long var16 = CompressedNumber.readLong((InputStream)this.rawDataIn);
               int var18 = CompressedNumber.readInt((InputStream)this.rawDataIn);
               StoredPage var19 = this.getOverflowPage(var16);
               PageTimeStamp var20 = var19.currentTimeStamp();
               var19.unlatch();
               RawTransaction var21 = this.owner.getTransaction();
               ReclaimSpace var22 = new ReclaimSpace(4, var6, var12, var16, var18, var20, var21.getDataFactory(), true);
               var21.addPostCommitWork(var22);
               this.rawDataIn.setPosition(var15);
            }

            var7 += StoredFieldHeader.write(var5, var13, var14, this.slotFieldSize);
            if (var14 != 0) {
               var5.write(this.pageData, var7, var14);
               var7 += var14;
            }
         } else {
            var7 += StoredFieldHeader.size(var13, var14, this.slotFieldSize);
            var7 += var14;
            var13 = StoredFieldHeader.setInitial();
            var13 = StoredFieldHeader.setNonexistent(var13);
            StoredFieldHeader.write(var5, var13, 0, this.slotFieldSize);
         }
      }

   }

   public void logField(int var1, int var2, OutputStream var3) throws StandardException, IOException {
      int var4 = this.getFieldOffset(var1, var2);
      ArrayInputStream var5 = this.rawDataIn;
      var5.setPosition(var4);
      int var6 = StoredFieldHeader.readStatus(var5);
      int var7 = StoredFieldHeader.readFieldDataLength(var5, var6, this.slotFieldSize);
      StoredFieldHeader.write(var3, var6, var7, this.slotFieldSize);
      if (var7 != 0) {
         var3.write(this.pageData, var5.getPosition(), var7);
      }

   }

   public RecordHandle insertAtSlot(int var1, Object[] var2, FormatableBitSet var3, LogicalUndo var4, byte var5, int var6) throws StandardException {
      try {
         return super.insertAtSlot(var1, var2, var3, var4, var5, var6);
      } catch (NoSpaceOnPage var8) {
         return null;
      }
   }

   public RecordHandle updateFieldAtSlot(int var1, int var2, Object var3, LogicalUndo var4) throws StandardException {
      try {
         return super.updateFieldAtSlot(var1, var2, var3, var4);
      } catch (NoSpaceOnPage var6) {
         if (this.slotsInUse == 1) {
            throw StandardException.newException("XSDA3.S", new Object[0]);
         } else {
            throw StandardException.newException("XSDA3.S", new Object[0]);
         }
      }
   }

   public int fetchNumFieldsAtSlot(int var1) throws StandardException {
      StoredRecordHeader var2 = this.getHeaderAtSlot(var1);
      if (!var2.hasOverflow()) {
         return super.fetchNumFieldsAtSlot(var1);
      } else {
         StoredPage var3 = this.getOverflowPage(var2.getOverflowPage());
         int var4 = ((BasePage)var3).fetchNumFieldsAtSlot(getOverflowSlot(var3, var2));
         ((BasePage)var3).unlatch();
         return var4;
      }
   }

   public int moveRecordForCompressAtSlot(int var1, Object[] var2, RecordHandle[] var3, RecordHandle[] var4) throws StandardException {
      long var5 = this.getPageNumber();

      try {
         this.fetchFromSlot((RecordHandle)null, var1, var2, (FetchDescriptor)null, false);
         int var7 = this.getRecordPortionLength(var1);
         int var8 = this.getHeaderAtSlot(var1).getId();
         StoredPage var9 = (StoredPage)this.owner.getPageForCompress(0, var5);
         if (var9 != null && (var9.getPageNumber() >= this.getPageNumber() || !var9.spaceForCopy(var7, var8))) {
            var9.unlatch();
            var9 = null;
         }

         if (var9 == null) {
            var9 = (StoredPage)this.owner.getPageForCompress(1, var5);
            if (var9 != null && (var9.getPageNumber() >= this.getPageNumber() || !var9.spaceForCopy(var7, var8))) {
               var9.unlatch();
               var9 = null;
            }
         }

         if (var9 == null) {
            var9 = (StoredPage)this.owner.addPage();
            if (var9.getPageNumber() >= this.getPageNumber() || !var9.spaceForCopy(var7, var8)) {
               this.owner.removePage(var9);
               var9 = null;
            }
         }

         if (var9 != null) {
            int var10 = var9.recordCount();
            var3[0] = this.getRecordHandleAtSlot(var1);
            this.copyAndPurge(var9, var1, 1, var10);
            var4[0] = var9.getRecordHandleAtSlot(var10);
            var9.unlatch();
            return 1;
         } else {
            return 0;
         }
      } catch (IOException var11) {
         throw StandardException.newException("XSDA4.S", var11, new Object[0]);
      }
   }

   public void logAction(LogInstant var1) throws StandardException {
      if (this.rawDataOut == null) {
         this.createOutStreams();
      }

      if (!this.isActuallyDirty()) {
         if (!this.isOverflowPage() && (this.getPageStatus() & 1) != 0) {
            this.initialRowCount = this.internalNonDeletedRecordCount();
         } else {
            this.initialRowCount = 0;
         }
      }

      this.setDirty();
      this.bumpPageVersion();
      this.updateLastLogInstant(var1);
   }

   private void cleanPage() {
      this.setDirty();
      this.clearSection(0, this.getPageSize());
      this.slotsInUse = 0;
      this.deletedRowCount = 0;
      this.headerOutOfDate = true;
      this.clearAllSpace();
   }

   public void initPage(LogInstant var1, byte var2, int var3, boolean var4, boolean var5) throws StandardException {
      this.logAction(var1);
      if (var5) {
         this.cleanPage();
         super.cleanPageForReuse();
      }

      this.headerOutOfDate = true;
      this.setPageStatus(var2);
      this.isOverflowPage = var4;
      this.nextId = var3;
   }

   public void setPageStatus(LogInstant var1, byte var2) throws StandardException {
      this.logAction(var1);
      this.headerOutOfDate = true;
      this.setPageStatus(var2);
   }

   public void setReservedSpace(LogInstant var1, int var2, int var3) throws StandardException, IOException {
      this.logAction(var1);
      this.headerOutOfDate = true;
      int var4 = var3 - this.getReservedCount(var2);
      int var5 = this.getRecordOffset(var2) + this.getTotalSpace(var2);
      if (var4 > 0) {
         this.expandPage(var5, var4);
      } else {
         this.shrinkPage(var5, -var4);
      }

      this.rawDataOut.setPosition(this.getSlotOffset(var2) + 2 * this.slotFieldSize);
      if (this.slotFieldSize == 2) {
         this.logicalDataOut.writeShort(var3);
      } else {
         this.logicalDataOut.writeInt(var3);
      }

   }

   public void storeRecord(LogInstant var1, int var2, boolean var3, ObjectInput var4) throws StandardException, IOException {
      this.logAction(var1);
      if (var3) {
         this.storeRecordForInsert(var2, var4);
      } else {
         this.storeRecordForUpdate(var2, var4);
      }

   }

   private void storeRecordForInsert(int var1, ObjectInput var2) throws StandardException, IOException {
      StoredRecordHeader var3 = this.shiftUp(var1);
      if (var3 == null) {
         var3 = new StoredRecordHeader();
         this.setHeaderAtSlot(var1, var3);
      }

      this.bumpRecordCount(1);
      var3.read(var2);
      if (var3.isDeleted()) {
         ++this.deletedRowCount;
         this.headerOutOfDate = true;
      }

      if (this.nextId <= var3.getId()) {
         this.nextId = var3.getId() + 1;
      }

      int var4 = this.firstFreeByte;
      int var6 = var3.getNumberFields();
      this.rawDataOut.setPosition(var4);
      int var5 = var4 + var3.write(this.rawDataOut);
      int var7 = 0;

      for(int var8 = 0; var8 < var6; ++var8) {
         int var9 = StoredFieldHeader.readStatus(var2);
         int var10 = StoredFieldHeader.readFieldDataLength(var2, var9, this.slotFieldSize);
         var9 = StoredFieldHeader.setFixed(var9, false);
         this.rawDataOut.setPosition(var5);
         var5 += StoredFieldHeader.write(this.rawDataOut, var9, var10, this.slotFieldSize);
         if (var10 != 0) {
            var2.readFully(this.pageData, var5, var10);
            var5 += var10;
            var7 += var10;
         }
      }

      int var11 = var5 - this.firstFreeByte;
      this.freeSpace -= var11;
      this.firstFreeByte += var11;
      int var13 = 0;
      if (this.minimumRecordSize > 0 && var7 < this.minimumRecordSize) {
         var13 = this.minimumRecordSize - var7;
         this.freeSpace -= var13;
         this.firstFreeByte += var13;
      }

      if (this.isOverflowPage()) {
         int var14 = 17 - (var11 + var13);
         if (var14 > 0) {
            this.freeSpace -= var14;
            this.firstFreeByte += var14;
            var13 += var14;
         }
      }

      this.addSlotEntry(var1, var4, var11, var13);
      if (this.firstFreeByte > this.getSlotOffset(var1) || this.freeSpace < 0) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", new Object[]{this.getPageId()}));
      }
   }

   private void storeRecordForUpdate(int var1, ObjectInput var2) throws StandardException, IOException {
      StoredRecordHeader var3 = this.getHeaderAtSlot(var1);
      StoredRecordHeader var4 = new StoredRecordHeader();
      var4.read(var2);
      int var5 = var3.getNumberFields();
      int var6 = var4.getNumberFields();
      int var7 = var3.getFirstField();
      if (var6 < var5) {
         int var8 = this.getFieldOffset(var1, var7 + var6);
         int var9 = this.getRecordOffset(var1) + this.getRecordPortionLength(var1) - var8;
         this.updateRecordPortionLength(var1, -var9, var9);
      }

      int var29 = this.getRecordOffset(var1);
      int var30 = var29;
      int var11 = var6 < var5 ? var6 - 1 : var5 - 1;
      var11 += var7;
      DynamicByteArrayOutputStream var12 = null;
      this.rawDataOut.setPosition(var29);
      int var13 = var3.size();
      int var14 = var4.size();
      int var15 = var13;
      if (var11 < var7) {
         var15 = var13 + this.getReservedCount(var1);
      }

      if (var15 >= var14) {
         var4.write(this.rawDataOut);
         var30 = var29 + var14;
         var15 -= var14;
      } else {
         var12 = new DynamicByteArrayOutputStream(this.getPageSize());
         var4.write(var12);
      }

      int var10 = var29 + var13;
      int var16 = var14 - var13;
      int var17 = 0;
      int var18 = 0;
      int var19 = 0;
      int var20 = 0;
      int var21 = var7 + var5;
      int var22 = var7 + var6;

      for(int var23 = var7; var23 < var22; ++var23) {
         int var24 = 0;
         if (var23 < var21) {
            this.rawDataIn.setPosition(var10);
            var17 = StoredFieldHeader.readStatus(this.rawDataIn);
            var18 = StoredFieldHeader.readFieldDataLength(this.rawDataIn, var17, this.slotFieldSize);
            var24 = StoredFieldHeader.size(var17, var18, this.slotFieldSize) + var18;
         }

         var19 = StoredFieldHeader.readStatus(var2);
         var20 = StoredFieldHeader.readFieldDataLength(var2, var19, this.slotFieldSize);
         if (StoredFieldHeader.isNonexistent(var19) && var23 < var21) {
            if (var12 != null && var12.getUsed() != 0) {
               int var41 = var12.getPosition();
               var12.setPosition(var41 + var24);
               System.arraycopy(this.pageData, var10, var12.getByteArray(), var41, var24);
               var15 += var24;
               if (var23 == var11) {
                  var15 += this.getReservedCount(var1);
               }

               int var43 = this.moveSavedDataToPage(var12, var15, var30);
               var30 += var43;
               var15 -= var43;
            } else {
               if (var30 != var10) {
                  System.arraycopy(this.pageData, var10, this.pageData, var30, var24);
               }

               var30 += var24;
               if (var23 == var11) {
                  var15 += this.getReservedCount(var1);
               }
            }

            var10 += var24;
         } else {
            var19 = StoredFieldHeader.setFixed(var19, false);
            int var25 = StoredFieldHeader.size(var19, var20, this.slotFieldSize);
            int var26 = var25 + var20;
            var16 += var26 - var24;
            var15 += var24;
            var10 += var24;
            if (var23 == var11) {
               var15 += this.getReservedCount(var1);
            }

            if (var12 != null && var12.getUsed() != 0) {
               int var27 = this.moveSavedDataToPage(var12, var15, var30);
               var30 += var27;
               var15 -= var27;
            }

            if ((var12 == null || var12.getUsed() == 0) && var15 >= var25) {
               this.rawDataOut.setPosition(var30);
               var30 += StoredFieldHeader.write(this.rawDataOut, var19, var20, this.slotFieldSize);
               var15 -= var25;
               if (var20 != 0) {
                  int var45 = var15 >= var20 ? var20 : var15;
                  if (var45 != 0) {
                     var2.readFully(this.pageData, var30, var45);
                     var30 += var45;
                     var15 -= var45;
                  }

                  var45 = var20 - var45;
                  if (var45 != 0) {
                     if (var12 == null) {
                        var12 = new DynamicByteArrayOutputStream(var26 * 2);
                     }

                     int var28 = var12.getPosition();
                     var12.setPosition(var28 + var45);
                     var2.readFully(var12.getByteArray(), var28, var45);
                  }
               }
            } else {
               if (var12 == null) {
                  var12 = new DynamicByteArrayOutputStream(var26 * 2);
               }

               StoredFieldHeader.write(var12, var19, var20, this.slotFieldSize);
               if (var20 != 0) {
                  int var44 = var12.getPosition();
                  var12.setPosition(var44 + var20);
                  var2.readFully(var12.getByteArray(), var44, var20);
               }
            }
         }
      }

      int var39;
      if (var12 != null && var12.getUsed() != 0) {
         int var40 = var29 + this.getTotalSpace(var1);
         int var42 = var12.getUsed() - (var40 - var30);
         if (var42 > this.freeSpace) {
            throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", new Object[]{this.getPageId()}));
         }

         this.expandPage(var40, var42);
         var15 += var42;
         this.moveSavedDataToPage(var12, var15, var30);
         var39 = -1 * this.getReservedCount(var1);
      } else {
         var39 = -1 * var16;
      }

      this.updateRecordPortionLength(var1, var16, var39);
      this.setHeaderAtSlot(var1, var4);
   }

   private int moveSavedDataToPage(DynamicByteArrayOutputStream var1, int var2, int var3) {
      if (var2 > var1.getUsed() / 2) {
         int var4 = var2 <= var1.getUsed() ? var2 : var1.getUsed();
         System.arraycopy(var1.getByteArray(), 0, this.pageData, var3, var4);
         var1.discardLeft(var4);
         return var4;
      } else {
         return 0;
      }
   }

   private void createSpaceForUpdate(int var1, int var2, int var3, int var4) throws StandardException, IOException {
      if (var4 <= var3) {
         int var10 = var3 - var4;
         if (var10 != 0) {
            int var11 = this.shiftRemainingData(var1, var2, var3, var4);
            this.clearSection(var2 + var4 + var11, var10);
            this.updateRecordPortionLength(var1, -var10, var10);
         }
      } else {
         int var5 = var4 - var3;
         int var6 = this.getReservedCount(var1);
         int var7 = 0;
         int var8 = var5 - var6;
         if (var8 > 0) {
            int var9 = this.getRecordOffset(var1) + this.getTotalSpace(var1);
            this.expandPage(var9, var8);
            var7 = -var6;
         } else {
            var7 = -var5;
         }

         this.shiftRemainingData(var1, var2, var3, var4);
         this.updateRecordPortionLength(var1, var5, var7);
      }
   }

   public void storeField(LogInstant var1, int var2, int var3, ObjectInput var4) throws StandardException, IOException {
      this.logAction(var1);
      int var5 = this.getFieldOffset(var2, var3);
      ArrayInputStream var6 = this.rawDataIn;
      var6.setPosition(var5);
      int var7 = StoredFieldHeader.readStatus(var6);
      int var8 = StoredFieldHeader.readFieldDataLength(var6, var7, this.slotFieldSize);
      int var9 = StoredFieldHeader.readStatus(var4);
      int var10 = StoredFieldHeader.readFieldDataLength(var4, var9, this.slotFieldSize);
      var9 = StoredFieldHeader.setFixed(var9, false);
      int var11 = StoredFieldHeader.size(var7, var8, this.slotFieldSize) + var8;
      int var12 = StoredFieldHeader.size(var9, var10, this.slotFieldSize) + var10;
      this.createSpaceForUpdate(var2, var5, var11, var12);
      this.rawDataOut.setPosition(var5);
      var5 += StoredFieldHeader.write(this.rawDataOut, var9, var10, this.slotFieldSize);
      if (var10 != 0) {
         var4.readFully(this.pageData, var5, var10);
      }

   }

   public void reserveSpaceForSlot(LogInstant var1, int var2, int var3) throws StandardException, IOException {
      this.logAction(var1);
      int var4 = var3 - this.getReservedCount(var2);
      if (var4 > 0) {
         if (this.freeSpace < var4) {
            throw new NoSpaceOnPage(this.isOverflowPage());
         } else {
            int var5 = this.getRecordOffset(var2);
            int var6 = var5 + this.getTotalSpace(var2);
            this.expandPage(var6, var4);
            this.setSlotEntry(var2, var5, this.getRecordPortionLength(var2), var3);
         }
      }
   }

   public void skipField(ObjectInput var1) throws IOException {
      int var2 = StoredFieldHeader.readStatus(var1);
      int var3 = StoredFieldHeader.readFieldDataLength(var1, var2, this.slotFieldSize);
      if (var3 != 0) {
         DataInputUtil.skipFully(var1, var3);
      }

   }

   public void skipRecord(ObjectInput var1) throws IOException {
      StoredRecordHeader var2 = new StoredRecordHeader();
      var2.read(var1);

      for(int var3 = var2.getNumberFields(); var3 > 0; --var3) {
         this.skipField(var1);
      }

   }

   private int shiftRemainingData(int var1, int var2, int var3, int var4) throws IOException {
      int var5 = this.getRecordOffset(var1) + this.getRecordPortionLength(var1) - (var2 + var3);
      if (var5 != 0) {
         System.arraycopy(this.pageData, var2 + var3, this.pageData, var2 + var4, var5);
      }

      return var5;
   }

   public void setDeleteStatus(LogInstant var1, int var2, boolean var3) throws StandardException, IOException {
      this.logAction(var1);
      this.deletedRowCount += super.setDeleteStatus(var2, var3);
      this.headerOutOfDate = true;
      int var4 = this.getRecordOffset(var2);
      StoredRecordHeader var5 = this.getHeaderAtSlot(var2);
      this.rawDataOut.setPosition(var4);
      var5.write(this.logicalDataOut);
   }

   protected int internalDeletedRecordCount() {
      return this.deletedRowCount;
   }

   public void purgeRecord(LogInstant var1, int var2, int var3) throws StandardException, IOException {
      this.logAction(var1);
      if (this.getHeaderAtSlot(var2).isDeleted()) {
         --this.deletedRowCount;
      }

      int var4 = this.getRecordOffset(var2);
      int var5 = var4 + this.getTotalSpace(var2) - 1;
      this.compressPage(var4, var5);
      this.removeSlotEntry(var2);
      this.removeAndShiftDown(var2);
   }

   private int getFieldOffset(int var1, int var2) throws IOException {
      int var3 = this.getRecordOffset(var1);
      StoredRecordHeader var4 = this.getHeaderAtSlot(var1);
      int var5 = var4.getFirstField();
      ArrayInputStream var6 = this.rawDataIn;
      var6.setPosition(var3 + var4.size());

      for(int var7 = var5; var7 < var2; ++var7) {
         this.skipField(var6);
      }

      return this.rawDataIn.getPosition();
   }

   public PageTimeStamp currentTimeStamp() {
      return new PageVersion(this.getPageNumber(), this.getPageVersion());
   }

   public void setTimeStamp(PageTimeStamp var1) throws StandardException {
      if (var1 == null) {
         throw StandardException.newException("XSDAB.S", new Object[0]);
      } else if (!(var1 instanceof PageVersion)) {
         throw StandardException.newException("XSDAA.S", new Object[]{var1});
      } else {
         PageVersion var2 = (PageVersion)var1;
         var2.setPageNumber(this.getPageNumber());
         var2.setPageVersion(this.getPageVersion());
      }
   }

   public boolean equalTimeStamp(PageTimeStamp var1) throws StandardException {
      if (var1 == null) {
         return false;
      } else if (!(var1 instanceof PageVersion)) {
         throw StandardException.newException("XSDAA.S", new Object[]{var1});
      } else {
         PageVersion var2 = (PageVersion)var1;
         if (var2.getPageNumber() != this.getPageNumber()) {
            throw StandardException.newException("XSDAA.S", new Object[]{var1});
         } else {
            return var2.getPageVersion() == this.getPageVersion();
         }
      }
   }

   public String toString() {
      return null;
   }

   public String toUncheckedString() {
      return null;
   }

   private static String pagedataToHexDump(byte[] var0) {
      return StringUtil.hexDump(var0);
   }

   private String pageHeaderToString() {
      return null;
   }

   String getPageDumpString() {
      return MessageService.getTextMessage("D016", new Object[]{this.getIdentity(), this.isOverflowPage, this.getPageVersion(), this.slotsInUse, this.deletedRowCount, this.getPageStatus(), this.nextId, this.firstFreeByte, this.freeSpace, this.totalSpace, this.spareSpace, this.minimumRecordSize, this.getPageSize(), pagedataToHexDump(this.pageData)});
   }

   private String recordToString(int var1) {
      return null;
   }

   protected StoredPage getOverflowPage(long var1) throws StandardException {
      StoredPage var3 = (StoredPage)this.owner.getPage(var1);
      if (var3 == null) {
      }

      return var3;
   }

   protected BasePage getNewOverflowPage() throws StandardException {
      FileContainer var1 = (FileContainer)this.containerCache.find(this.identity.getContainerId());

      BasePage var2;
      try {
         var2 = (BasePage)var1.addPage(this.owner, true);
      } finally {
         this.containerCache.release(var1);
      }

      return var2;
   }

   protected static int getOverflowSlot(BasePage var0, StoredRecordHeader var1) throws StandardException {
      int var2 = var0.findRecordById(var1.getOverflowId(), 0);
      if (var2 < 0) {
         throw StandardException.newException("XSDA1.S", new Object[0]);
      } else {
         return var2;
      }
   }

   public BasePage getOverflowPageForInsert(int var1, Object[] var2, FormatableBitSet var3) throws StandardException {
      return this.getOverflowPageForInsert(var1, var2, var3, 0);
   }

   public BasePage getOverflowPageForInsert(int var1, Object[] var2, FormatableBitSet var3, int var4) throws StandardException {
      long[] var5 = new long[5];
      int var6 = 0;
      long var7 = 0L;

      label63:
      for(int var9 = 0; var9 < this.slotsInUse && var6 < var5.length; ++var9) {
         StoredRecordHeader var10 = this.getHeaderAtSlot(var9);
         if (var10.hasOverflow()) {
            long var11 = var10.getOverflowPage();
            if (var9 == var1) {
               var7 = var11;
            } else {
               for(int var13 = 0; var13 < var6; ++var13) {
                  if (var5[var13] == var11) {
                     continue label63;
                  }
               }

               var5[var6++] = var11;
            }
         }
      }

      for(int var16 = 0; var16 < var6; ++var16) {
         long var17 = var5[var16];
         if (var17 != var7) {
            StoredPage var12 = null;
            int var21 = 0;

            try {
               var12 = this.getOverflowPage(var17);
               if (var12.spaceForInsert(var2, var3, var21, var4, 100)) {
                  return var12;
               }

               var21 = var12.getCurrentFreeSpace();
               var12.unlatch();
               Object var20 = null;
            } catch (StandardException var15) {
               if (var12 != null) {
                  var12.unlatch();
                  Object var18 = null;
               }
            }
         }
      }

      return this.getNewOverflowPage();
   }

   protected void updateOverflowed(RawTransaction var1, int var2, Object[] var3, FormatableBitSet var4, StoredRecordHeader var5) throws StandardException {
      StoredPage var6 = this.getOverflowPage(var5.getOverflowPage());

      try {
         int var7 = getOverflowSlot(var6, var5);
         ((BasePage)var6).doUpdateAtSlot(var1, var7, var5.getOverflowId(), var3, var4);
         ((BasePage)var6).unlatch();
         var6 = null;
      } finally {
         if (var6 != null) {
            ((BasePage)var6).unlatch();
            Object var11 = null;
         }

      }

   }

   public void updateOverflowDetails(RecordHandle var1, RecordHandle var2) throws StandardException {
      long var3 = var1.getPageNumber();
      if (var3 == this.getPageNumber()) {
         this.updateOverflowDetails(this, var1, var2);
      } else {
         StoredPage var5 = (StoredPage)this.owner.getPage(var3);
         this.updateOverflowDetails(var5, var1, var2);
         var5.unlatch();
      }
   }

   private void updateOverflowDetails(StoredPage var1, RecordHandle var2, RecordHandle var3) throws StandardException {
      var1.getOverFlowRecordHeader().setOverflowDetails(var3);
      int var4 = var1.getSlotNumber(var2);
      var1.doUpdateAtSlot(this.owner.getTransaction(), var4, var2.getId(), (Object[])null, (FormatableBitSet)null);
   }

   public void updateFieldOverflowDetails(RecordHandle var1, RecordHandle var2) throws StandardException {
      Object[] var3 = new Object[]{null, var2};
      FormatableBitSet var4 = new FormatableBitSet(2);
      var4.set(1);
      int var5 = this.getSlotNumber(var1);
      this.doUpdateAtSlot(this.owner.getTransaction(), var5, var1.getId(), var3, var4);
   }

   public int appendOverflowFieldHeader(DynamicByteArrayOutputStream var1, RecordHandle var2) throws StandardException, IOException {
      int var3 = StoredFieldHeader.setInitial();
      var3 = StoredFieldHeader.setOverflow(var3, true);
      long var4 = var2.getPageNumber();
      int var6 = var2.getId();
      int var7 = CompressedNumber.sizeLong(var4) + CompressedNumber.sizeInt(var6);
      int var8 = StoredFieldHeader.write(var1, var3, var7, this.slotFieldSize);
      var8 += CompressedNumber.writeLong((OutputStream)var1, var4);
      var8 += CompressedNumber.writeInt((OutputStream)var1, var6);
      return var8;
   }

   protected int getSlotsInUse() {
      return this.slotsInUse;
   }

   private int getMaxDataLength(int var1, int var2) {
      int var3 = this.totalSpace * var2 / 100;
      int var4 = 0;
      if (var1 < 62) {
         var4 = var1 - 2;
      } else if (var1 < 16380) {
         var4 = var1 - 3;
      } else {
         var4 = var1 - 5;
      }

      return var4 > var3 ? var3 : var4;
   }

   private boolean isLong(int var1, int var2) {
      int var3 = this.maxFieldSize * var2 / 100;
      return var1 > var3;
   }

   public void doUpdateAtSlot(RawTransaction var1, int var2, int var3, Object[] var4, FormatableBitSet var5) throws StandardException {
      RecordHandle var6 = this.isOverflowPage() ? null : this.getRecordHandleAtSlot(var2);
      if (var4 == null) {
         this.owner.getActionSet().actionUpdate(var1, this, var2, var3, var4, var5, -1, (DynamicByteArrayOutputStream)null, -1, var6);
      } else {
         int var7 = RowUtil.nextColumn(var4, var5, 0);
         if (var7 != -1) {
            boolean var8 = false;
            StoredPage var9 = this;

            while(true) {
               StoredRecordHeader var10 = var9.getHeaderAtSlot(var2);
               int var11 = var10.getFirstField();
               int var12 = var11 + var10.getNumberFields();
               long var13 = -1L;
               int var15 = -1;
               int var16 = -1;
               if (!var10.hasOverflow() || var7 >= var11 && var7 < var12) {
                  int var18 = -1;
                  Object[] var19 = null;
                  DynamicByteArrayOutputStream var20 = null;

                  boolean var17;
                  do {
                     try {
                        var18 = this.owner.getActionSet().actionUpdate(var1, var9, var2, var3, var4, var5, var15, var20, var16, var6);
                        var17 = false;
                     } catch (LongColumnException var34) {
                        if (var34.getRealSpaceOnPage() == -1) {
                           var20 = var34.getLogBuffer();
                           var19 = var34.getColumn();
                           var15 = var34.getNextColumn();
                           var16 = -1;
                           var17 = true;
                        } else {
                           var20 = new DynamicByteArrayOutputStream(var34.getLogBuffer());
                           RecordHandle var22 = this.insertLongColumn(var9, var34, (byte)2);
                           int var23 = 0;

                           try {
                              var23 += this.appendOverflowFieldHeader(var20, var22);
                           } catch (IOException var33) {
                              throw StandardException.newException("XSDA4.S", var33, new Object[0]);
                           }

                           var15 = var34.getNextColumn() + 1;
                           var16 = var34.getRealSpaceOnPage() - var23;
                           var17 = true;
                        }
                     } catch (NoSpaceOnPage var35) {
                        throw StandardException.newException("XSDAP.S", var35, new Object[]{((PageKey)var9.getIdentity()).toString(), this.getPageDumpString(), var2, var3, var5.toString(), var15, 0, var6});
                     }
                  } while(var17);

                  int var21 = var5 == null ? 0 : var5.getLength();
                  if (var18 != -1) {
                     int var38 = var12;
                     if (!var10.hasOverflow()) {
                        if (var5 == null) {
                           if (var4.length > var12) {
                              var38 = var4.length;
                           }
                        } else if (var21 > var12) {
                           var38 = var21;
                        }
                     }

                     Object[] var40 = new Object[var38];
                     FormatableBitSet var24 = new FormatableBitSet(var38);
                     Object var25 = null;

                     for(int var26 = var18; var26 < var38; ++var26) {
                        if (var5 != null && (var21 <= var26 || !var5.isSet(var26))) {
                           if (var26 < var12) {
                              var24.set(var26);
                              var40[var26] = var19[var26 - var18];
                           }
                        } else {
                           var24.set(var26);
                           var40[var26] = RowUtil.getColumn(var4, var5, var26);
                        }
                     }

                     RecordHandle var41 = var9.getRecordHandleAtSlot(var2);
                     if (var10.hasOverflow()) {
                        var13 = var10.getOverflowPage();
                        var3 = var10.getOverflowId();
                        var7 = RowUtil.nextColumn(var4, var5, var12);
                     } else {
                        var7 = -1;
                        var13 = 0L;
                     }

                     if (!var8 && var6 != null && var9 != null && !this.owner.isTemporaryContainer()) {
                        var8 = var9.checkRowReservedSpace(var2);
                     }

                     BasePage var27 = var9.getOverflowPageForInsert(var2, var40, var24, var18);
                     if (var9 != this) {
                        var9.unlatch();
                        var9 = null;
                     }

                     byte var28 = 8;
                     if (var13 != 0L) {
                        var28 = (byte)(var28 | 16);
                     }

                     RecordHandle var29 = var13 == 0L ? null : this.owner.makeRecordHandle(var13, var3);

                     RecordHandle var30;
                     try {
                        var30 = var27.insertAllowOverflow(0, var40, var24, var18, var28, 100, var29);
                     } catch (NoSpaceOnPage var32) {
                        throw StandardException.newException("XSDAP.S", var32, new Object[]{((PageKey)var27.getIdentity()).toString(), this.getPageDumpString(), var2, var3, var24.toString(), var18, var28, var29});
                     }

                     if (var9 == this) {
                        this.updateOverflowDetails(this, var41, var30);
                     } else {
                        this.updateOverflowDetails(var41, var30);
                     }

                     var27.unlatch();
                  } else {
                     if (!var8 && var6 != null && var9 != null && !this.owner.isTemporaryContainer()) {
                        var8 = var9.checkRowReservedSpace(var2);
                     }

                     var7 = var10.hasOverflow() ? RowUtil.nextColumn(var4, var5, var12) : -1;
                  }

                  if (var7 == -1) {
                     if (var9 != this && var9 != null) {
                        var9.unlatch();
                     }

                     if (var8) {
                        RawTransaction var36 = this.owner.getTransaction();
                        ReclaimSpace var37 = new ReclaimSpace(3, var6, var36.getDataFactory(), true);
                        var36.addPostCommitWork(var37);
                     }

                     return;
                  }
               }

               if (var13 == -1L) {
                  var13 = var10.getOverflowPage();
                  var3 = var10.getOverflowId();
               }

               if (var9 != this && var9 != null) {
                  var9.unlatch();
               }

               var9 = (StoredPage)this.owner.getPage(var13);
               var2 = var9.findRecordById(var3, 0);
            }
         }
      }
   }

   private boolean checkRowReservedSpace(int var1) throws StandardException {
      boolean var2 = false;

      try {
         int var3 = this.getReservedCount(var1);
         byte var4 = 12;
         if (var3 > var4) {
            int var5 = this.getRecordPortionLength(var1) + var3;
            if (this.isOverflowPage()) {
               if (var5 > 17 + var4) {
                  var2 = true;
               }
            } else if (var5 > this.minimumRecordSize + var4) {
               var2 = true;
            }
         }

         return var2;
      } catch (IOException var6) {
         throw StandardException.newException("XSDA4.S", var6, new Object[0]);
      }
   }

   protected void compactRecord(RawTransaction var1, int var2, int var3) throws StandardException {
      if (!this.isOverflowPage()) {
         StoredRecordHeader var4 = this.getHeaderAtSlot(var2);

         while(var4.hasOverflow()) {
            StoredPage var5 = this.getOverflowPage(var4.getOverflowPage());

            try {
               int var6 = var4.getOverflowId();
               int var7 = getOverflowSlot(var5, var4);
               var5.compactRecord(var1, var7, var6);
               var4 = var5.getHeaderAtSlot(var7);
            } finally {
               var5.unlatch();
            }
         }
      }

      byte var13 = 12;

      try {
         int var14 = this.getReservedCount(var2);
         if (var14 > var13) {
            int var15 = this.getRecordPortionLength(var2);
            int var16 = var14;
            int var8 = var15 + var14;
            if (this.isOverflowPage()) {
               if (var8 > 17 + var13) {
                  if (var15 >= 17) {
                     var16 = 0;
                  } else {
                     var16 = 17 - var15;
                  }
               }
            } else if (var8 > this.minimumRecordSize + var13) {
               if (var15 >= this.minimumRecordSize) {
                  var16 = 0;
               } else {
                  var16 = this.minimumRecordSize - var15;
               }
            }

            if (var16 < var14) {
               this.owner.getActionSet().actionShrinkReservedSpace(var1, this, var2, var3, var16, var14);
            }
         }

      } catch (IOException var11) {
         throw StandardException.newException("XSDA4.S", var11, new Object[0]);
      }
   }
}
