package org.apache.derby.impl.store.replication.slave;

import java.util.NoSuchElementException;
import org.apache.derby.shared.common.error.StandardException;

class ReplicationLogScan {
   private byte[] logToScan;
   private int currentPosition;
   private long currentInstant;
   private int currentDataOffset;
   private byte[] currentData;
   private boolean hasInfo;
   private boolean isLogSwitch;

   protected ReplicationLogScan() {
   }

   protected void init(byte[] var1) {
      this.logToScan = var1;
      this.currentPosition = 0;
      this.currentInstant = -1L;
      this.currentData = null;
      this.isLogSwitch = false;
      this.hasInfo = false;
   }

   protected boolean next() throws StandardException {
      if (this.currentPosition == this.logToScan.length) {
         this.hasInfo = false;
         return this.hasInfo;
      } else {
         try {
            int var1 = this.retrieveInt();
            if (var1 == 0) {
               this.isLogSwitch = true;
               this.hasInfo = true;
            } else {
               this.currentInstant = this.retrieveLong();
               this.currentData = new byte[var1];
               this.retrieveBytes(this.currentData, var1);
               this.retrieveInt();
               this.isLogSwitch = false;
               this.hasInfo = true;
            }
         } catch (StandardException var2) {
            this.hasInfo = false;
            throw var2;
         }

         return this.hasInfo;
      }
   }

   protected long getInstant() throws NoSuchElementException {
      if (!this.hasInfo) {
         throw new NoSuchElementException();
      } else {
         return this.isLogSwitch ? -1L : this.currentInstant;
      }
   }

   protected int getDataLength() throws NoSuchElementException {
      if (!this.hasInfo) {
         throw new NoSuchElementException();
      } else {
         return this.isLogSwitch ? -1 : this.currentData.length;
      }
   }

   protected byte[] getData() throws NoSuchElementException {
      if (!this.hasInfo) {
         throw new NoSuchElementException();
      } else {
         return this.isLogSwitch ? null : this.currentData;
      }
   }

   protected boolean hasValidInformation() {
      return this.hasInfo;
   }

   protected boolean isLogRecord() throws NoSuchElementException {
      if (!this.hasInfo) {
         throw new NoSuchElementException();
      } else {
         return !this.isLogSwitch;
      }
   }

   protected boolean isLogFileSwitch() throws NoSuchElementException {
      if (!this.hasInfo) {
         throw new NoSuchElementException();
      } else {
         return this.isLogSwitch;
      }
   }

   private void retrieveBytes(byte[] var1, int var2) throws StandardException {
      try {
         System.arraycopy(this.logToScan, this.currentPosition, var1, 0, var2);
         this.currentPosition += var2;
      } catch (ArrayIndexOutOfBoundsException var4) {
         throw StandardException.newException("XRE01", var4, new Object[0]);
      }
   }

   private int retrieveInt() throws StandardException {
      try {
         int var1 = (this.logToScan[this.currentPosition++] << 24) + ((this.logToScan[this.currentPosition++] & 255) << 16) + ((this.logToScan[this.currentPosition++] & 255) << 8) + (this.logToScan[this.currentPosition++] & 255);
         return var1;
      } catch (ArrayIndexOutOfBoundsException var2) {
         throw StandardException.newException("XRE01", var2, new Object[0]);
      }
   }

   private long retrieveLong() throws StandardException {
      try {
         long var1 = ((long)this.logToScan[this.currentPosition++] << 56) + (((long)this.logToScan[this.currentPosition++] & 255L) << 48) + (((long)this.logToScan[this.currentPosition++] & 255L) << 40) + (((long)this.logToScan[this.currentPosition++] & 255L) << 32) + (((long)this.logToScan[this.currentPosition++] & 255L) << 24) + (long)((this.logToScan[this.currentPosition++] & 255) << 16) + (long)((this.logToScan[this.currentPosition++] & 255) << 8) + (long)(this.logToScan[this.currentPosition++] & 255);
         return var1;
      } catch (ArrayIndexOutOfBoundsException var3) {
         throw StandardException.newException("XRE01", var3, new Object[0]);
      }
   }
}
