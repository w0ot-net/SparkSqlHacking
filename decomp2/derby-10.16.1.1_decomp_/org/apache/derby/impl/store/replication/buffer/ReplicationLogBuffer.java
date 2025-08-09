package org.apache.derby.impl.store.replication.buffer;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.derby.iapi.store.replication.master.MasterFactory;

public class ReplicationLogBuffer {
   public static final int DEFAULT_NUMBER_LOG_BUFFERS = 10;
   private final LinkedList dirtyBuffers;
   private final LinkedList freeBuffers;
   private LogBufferElement currentDirtyBuffer;
   private boolean validOutBuffer;
   private byte[] outBufferData;
   private int outBufferStored;
   private long outBufferLastInstant;
   private final Object listLatch = new Object();
   private final Object outputLatch = new Object();
   private int defaultBufferSize;
   private final MasterFactory mf;

   public ReplicationLogBuffer(int var1, MasterFactory var2) {
      this.defaultBufferSize = var1;
      this.mf = var2;
      this.outBufferData = new byte[var1];
      this.outBufferStored = 0;
      this.outBufferLastInstant = 0L;
      this.validOutBuffer = false;
      this.dirtyBuffers = new LinkedList();
      this.freeBuffers = new LinkedList();

      for(int var3 = 0; var3 < 10; ++var3) {
         LogBufferElement var4 = new LogBufferElement(var1);
         this.freeBuffers.addLast(var4);
      }

      this.currentDirtyBuffer = (LogBufferElement)this.freeBuffers.removeFirst();
   }

   public void appendLog(long var1, byte[] var3, int var4, int var5) throws LogBufferFullException {
      boolean var6 = false;
      synchronized(this.listLatch) {
         if (this.currentDirtyBuffer == null) {
            this.switchDirtyBuffer();
         }

         if (var5 > this.currentDirtyBuffer.freeSize()) {
            this.switchDirtyBuffer();
            var6 = true;
         }

         if (var5 <= this.currentDirtyBuffer.freeSize()) {
            this.currentDirtyBuffer.appendLog(var1, var3, var4, var5);
         } else {
            LogBufferElement var8 = new LogBufferElement(var5);
            var8.setRecyclable(false);
            var8.appendLog(var1, var3, var4, var5);
            this.dirtyBuffers.addLast(var8);
         }
      }

      if (var6) {
         this.mf.workToDo();
      }

   }

   public boolean next() {
      synchronized(this.listLatch) {
         if (this.dirtyBuffers.size() == 0) {
            try {
               this.switchDirtyBuffer();
            } catch (LogBufferFullException var8) {
            }
         }

         synchronized(this.outputLatch) {
            if (this.dirtyBuffers.size() > 0) {
               LogBufferElement var3 = (LogBufferElement)this.dirtyBuffers.removeFirst();
               int var4 = Math.max(this.defaultBufferSize, var3.size());
               if (this.outBufferData.length != var4) {
                  this.outBufferData = new byte[var4];
               }

               System.arraycopy(var3.getData(), 0, this.outBufferData, 0, var3.size());
               this.outBufferStored = var3.size();
               this.outBufferLastInstant = var3.getLastInstant();
               if (var3.isRecyclable()) {
                  this.freeBuffers.addLast(var3);
               }

               this.validOutBuffer = true;
            } else {
               this.validOutBuffer = false;
            }
         }
      }

      return this.validOutBuffer;
   }

   public byte[] getData() throws NoSuchElementException {
      synchronized(this.outputLatch) {
         byte[] var2 = new byte[this.getSize()];
         if (this.validOutBuffer) {
            System.arraycopy(this.outBufferData, 0, var2, 0, this.getSize());
            return var2;
         } else {
            throw new NoSuchElementException();
         }
      }
   }

   public boolean validData() {
      synchronized(this.outputLatch) {
         return this.validOutBuffer;
      }
   }

   public int getSize() throws NoSuchElementException {
      synchronized(this.outputLatch) {
         if (this.validOutBuffer) {
            return this.outBufferStored;
         } else {
            throw new NoSuchElementException();
         }
      }
   }

   public long getLastInstant() throws NoSuchElementException {
      synchronized(this.outputLatch) {
         if (this.validOutBuffer) {
            return this.outBufferLastInstant;
         } else {
            throw new NoSuchElementException();
         }
      }
   }

   private void switchDirtyBuffer() throws LogBufferFullException {
      if (this.currentDirtyBuffer != null && this.currentDirtyBuffer.size() > 0) {
         this.dirtyBuffers.addLast(this.currentDirtyBuffer);
         this.currentDirtyBuffer = null;
      }

      if (this.currentDirtyBuffer == null) {
         try {
            this.currentDirtyBuffer = (LogBufferElement)this.freeBuffers.removeFirst();
            this.currentDirtyBuffer.init();
         } catch (NoSuchElementException var2) {
            throw new LogBufferFullException();
         }
      }

   }

   public int getFillInformation() {
      return this.dirtyBuffers.size() * 100 / 10;
   }
}
