package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.OutputStream;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;

public final class StoredRecordHeader {
   private static final byte RECORD_DELETED = 1;
   private static final byte RECORD_OVERFLOW = 2;
   private static final byte RECORD_HAS_FIRST_FIELD = 4;
   private static final byte RECORD_VALID_MASK = 15;
   public static final int MAX_OVERFLOW_ONLY_REC_SIZE = 17;
   protected int id;
   private byte status;
   protected int numberFields;
   protected RecordHandle handle;
   private OverflowInfo overflow;

   public StoredRecordHeader() {
   }

   public StoredRecordHeader(int var1, int var2) {
      this.setId(var1);
      this.setNumberFields(var2);
   }

   public StoredRecordHeader(byte[] var1, int var2) {
      this.read(var1, var2);
   }

   public StoredRecordHeader(StoredRecordHeader var1) {
      this.status = var1.status;
      this.id = var1.id;
      this.numberFields = var1.numberFields;
      this.handle = null;
      if (var1.overflow != null) {
         this.overflow = new OverflowInfo(var1.overflow);
      }

   }

   protected RecordHandle getHandle(PageKey var1, int var2) {
      if (this.handle == null) {
         this.handle = new RecordId(var1, this.id, var2);
      }

      return this.handle;
   }

   public final int getId() {
      return this.id;
   }

   public int getNumberFields() {
      return this.numberFields;
   }

   public long getOverflowPage() {
      return this.overflow == null ? 0L : this.overflow.overflowPage;
   }

   public int getOverflowId() {
      return this.overflow == null ? 0 : this.overflow.overflowId;
   }

   public int getFirstField() {
      return this.overflow == null ? 0 : this.overflow.firstField;
   }

   public final boolean hasOverflow() {
      return (this.status & 2) == 2;
   }

   protected final boolean hasFirstField() {
      return (this.status & 4) == 4;
   }

   public final boolean isDeleted() {
      return (this.status & 1) == 1;
   }

   public int size() {
      int var1 = this.id <= 63 ? 2 : (this.id <= 16383 ? 3 : 5);
      if ((this.status & 6) == 0) {
         var1 += this.numberFields <= 63 ? 1 : (this.numberFields <= 16383 ? 2 : 4);
      } else if ((this.status & 2) == 0) {
         var1 += CompressedNumber.sizeInt(this.numberFields);
         var1 += CompressedNumber.sizeInt(this.overflow.firstField);
      } else {
         var1 += CompressedNumber.sizeLong(this.overflow.overflowPage);
         var1 += CompressedNumber.sizeInt(this.overflow.overflowId);
         if (this.hasFirstField()) {
            var1 += CompressedNumber.sizeInt(this.overflow.firstField);
            var1 += CompressedNumber.sizeInt(this.numberFields);
         }
      }

      return var1;
   }

   public int setDeleted(boolean var1) {
      byte var2 = 0;
      if (var1) {
         if (!this.isDeleted()) {
            var2 = 1;
            this.status = (byte)(this.status | 1);
         }
      } else if (this.isDeleted()) {
         var2 = -1;
         this.status &= -2;
      }

      return var2;
   }

   public void setFirstField(int var1) {
      if (this.overflow == null) {
         this.overflow = new OverflowInfo();
      }

      this.overflow.firstField = var1;
      this.status = (byte)(this.status | 4);
   }

   public final void setId(int var1) {
      this.id = var1;
   }

   public void setOverflowDetails(RecordHandle var1) {
      if (this.overflow == null) {
         this.overflow = new OverflowInfo();
      }

      this.overflow.overflowPage = var1.getPageNumber();
      this.overflow.overflowId = var1.getId();
   }

   public void setOverflowFields(StoredRecordHeader var1) {
      if (this.overflow == null) {
         this.overflow = new OverflowInfo();
      }

      this.status = (byte)(var1.status | 2);
      this.id = var1.id;
      this.numberFields = var1.numberFields;
      this.overflow.firstField = var1.overflow.firstField;
      this.handle = null;
   }

   public final void setNumberFields(int var1) {
      this.numberFields = var1;
   }

   public int write(OutputStream var1) throws IOException {
      int var2 = 1;
      var1.write(this.status);
      var2 += CompressedNumber.writeInt(var1, this.id);
      if (this.hasOverflow()) {
         var2 += CompressedNumber.writeLong(var1, this.overflow.overflowPage);
         var2 += CompressedNumber.writeInt(var1, this.overflow.overflowId);
      }

      if (this.hasFirstField()) {
         var2 += CompressedNumber.writeInt(var1, this.overflow.firstField);
      }

      if (!this.hasOverflow() || this.hasFirstField()) {
         var2 += CompressedNumber.writeInt(var1, this.numberFields);
      }

      return var2;
   }

   public void read(ObjectInput var1) throws IOException {
      int var2 = var1.read();
      if (var2 < 0) {
         throw new EOFException();
      } else {
         this.status = (byte)var2;
         this.id = CompressedNumber.readInt((DataInput)var1);
         if (!this.hasOverflow() && !this.hasFirstField()) {
            this.overflow = null;
         } else {
            this.overflow = new OverflowInfo();
         }

         if (this.hasOverflow()) {
            this.overflow.overflowPage = CompressedNumber.readLong((DataInput)var1);
            this.overflow.overflowId = CompressedNumber.readInt((DataInput)var1);
         }

         if (this.hasFirstField()) {
            this.overflow.firstField = CompressedNumber.readInt((DataInput)var1);
         }

         if (this.hasOverflow() && !this.hasFirstField()) {
            this.numberFields = 0;
         } else {
            this.numberFields = CompressedNumber.readInt((DataInput)var1);
         }

         this.handle = null;
      }
   }

   private int readOverFlowPage(byte[] var1, int var2) {
      byte var3 = var1[var2++];
      if ((var3 & -64) == 0) {
         this.overflow.overflowPage = (long)(var3 << 8 | var1[var2] & 255);
         return 2;
      } else if ((var3 & 128) == 0) {
         this.overflow.overflowPage = (long)((var3 & 63) << 24 | (var1[var2++] & 255) << 16 | (var1[var2++] & 255) << 8 | var1[var2] & 255);
         return 4;
      } else {
         this.overflow.overflowPage = (long)(var3 & 127) << 56 | (long)(var1[var2++] & 255) << 48 | (long)(var1[var2++] & 255) << 40 | (long)(var1[var2++] & 255) << 32 | (long)(var1[var2++] & 255) << 24 | (long)(var1[var2++] & 255) << 16 | (long)(var1[var2++] & 255) << 8 | (long)(var1[var2] & 255);
         return 8;
      }
   }

   private int readOverFlowId(byte[] var1, int var2) {
      byte var3 = var1[var2++];
      if ((var3 & -64) == 0) {
         this.overflow.overflowId = var3;
         return 1;
      } else if ((var3 & 128) == 0) {
         this.overflow.overflowId = (var3 & 63) << 8 | var1[var2] & 255;
         return 2;
      } else {
         this.overflow.overflowId = (var3 & 127) << 24 | (var1[var2++] & 255) << 16 | (var1[var2++] & 255) << 8 | var1[var2] & 255;
         return 4;
      }
   }

   private int readFirstField(byte[] var1, int var2) {
      byte var3 = var1[var2++];
      if ((var3 & -64) == 0) {
         this.overflow.firstField = var3;
         return 1;
      } else if ((var3 & 128) == 0) {
         this.overflow.firstField = (var3 & 63) << 8 | var1[var2] & 255;
         return 2;
      } else {
         this.overflow.firstField = (var3 & 127) << 24 | (var1[var2++] & 255) << 16 | (var1[var2++] & 255) << 8 | var1[var2] & 255;
         return 4;
      }
   }

   private void readNumberFields(byte[] var1, int var2) {
      byte var3 = var1[var2++];
      if ((var3 & -64) == 0) {
         this.numberFields = var3;
      } else if ((var3 & 128) == 0) {
         this.numberFields = (var3 & 63) << 8 | var1[var2] & 255;
      } else {
         this.numberFields = (var3 & 127) << 24 | (var1[var2++] & 255) << 16 | (var1[var2++] & 255) << 8 | var1[var2] & 255;
      }

   }

   private void read(byte[] var1, int var2) {
      this.status = var1[var2++];
      byte var3 = var1[var2++];
      if ((var3 & -64) == 0) {
         this.id = var3;
      } else if ((var3 & 128) == 0) {
         this.id = (var3 & 63) << 8 | var1[var2++] & 255;
      } else {
         this.id = (var3 & 127) << 24 | (var1[var2++] & 255) << 16 | (var1[var2++] & 255) << 8 | var1[var2++] & 255;
      }

      if ((this.status & 6) == 0) {
         this.overflow = null;
         this.readNumberFields(var1, var2);
      } else if ((this.status & 2) == 0) {
         this.overflow = new OverflowInfo();
         var2 += this.readFirstField(var1, var2);
         this.readNumberFields(var1, var2);
      } else {
         this.overflow = new OverflowInfo();
         var2 += this.readOverFlowPage(var1, var2);
         var2 += this.readOverFlowId(var1, var2);
         if (this.hasFirstField()) {
            var2 += this.readFirstField(var1, var2);
            this.readNumberFields(var1, var2);
         } else {
            this.numberFields = 0;
         }
      }

      this.handle = null;
   }

   public static final int getStoredSizeRecordId(int var0) {
      return CompressedNumber.sizeInt(var0);
   }

   public String toString() {
      return null;
   }

   private static class OverflowInfo {
      private int overflowId;
      private long overflowPage;
      private int firstField;

      private OverflowInfo() {
      }

      private OverflowInfo(OverflowInfo var1) {
         this.overflowId = var1.overflowId;
         this.overflowPage = var1.overflowPage;
         this.firstField = var1.firstField;
      }
   }
}
