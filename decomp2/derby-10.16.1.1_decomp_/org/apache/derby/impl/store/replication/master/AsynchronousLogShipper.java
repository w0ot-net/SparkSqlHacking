package org.apache.derby.impl.store.replication.master;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.store.replication.ReplicationLogger;
import org.apache.derby.impl.store.replication.buffer.ReplicationLogBuffer;
import org.apache.derby.impl.store.replication.net.ReplicationMessage;
import org.apache.derby.impl.store.replication.net.ReplicationMessageTransmit;
import org.apache.derby.shared.common.error.StandardException;

public class AsynchronousLogShipper extends Thread implements LogShipper {
   private final ReplicationLogBuffer logBuffer;
   private ReplicationMessageTransmit transmitter;
   private long shippingInterval;
   private long minShippingInterval;
   private long maxShippingInterval;
   private long lastShippingTime;
   private volatile boolean stopShipping = false;
   private MasterController masterController = null;
   private Object objLSTSync = new Object();
   private Object forceFlushSemaphore = new Object();
   public static final int DEFAULT_FORCEFLUSH_TIMEOUT = 5000;
   private ReplicationMessage failedChunk = null;
   private long failedChunkHighestInstant = -1L;
   private long highestShippedInstant = -1L;
   private static final int FI_LOW = 10;
   private static final int FI_HIGH = 80;
   private static final long MIN = 100L;
   private static final long MAX = 5000L;
   private final ReplicationLogger repLogger;

   public AsynchronousLogShipper(ReplicationLogBuffer var1, ReplicationMessageTransmit var2, MasterController var3, ReplicationLogger var4) {
      super("derby.master.logger-" + var3.getDbName());
      this.logBuffer = var1;
      this.transmitter = var2;
      this.masterController = var3;
      this.stopShipping = false;
      this.repLogger = var4;
      this.getLogShipperProperties();
      this.shippingInterval = this.minShippingInterval;
      this.lastShippingTime = System.currentTimeMillis();
   }

   public void run() {
      while(!this.stopShipping) {
         try {
            synchronized(this.forceFlushSemaphore) {
               this.shipALogChunk();
               this.forceFlushSemaphore.notify();
            }

            this.shippingInterval = this.calculateSIfromFI();
            if (this.shippingInterval != -1L) {
               synchronized(this.objLSTSync) {
                  this.objLSTSync.wait(this.shippingInterval);
               }
            }
         } catch (InterruptedException var6) {
            InterruptStatus.setInterrupted();
         } catch (IOException var7) {
            this.transmitter = this.masterController.handleExceptions(var7);
            if (this.transmitter != null) {
            }
         } catch (StandardException var8) {
            this.masterController.handleExceptions(var8);
         }
      }

   }

   private synchronized boolean shipALogChunk() throws IOException, StandardException {
      Object var1 = null;
      Object var2 = null;

      try {
         if (this.failedChunk != null) {
            this.transmitter.sendMessage(this.failedChunk);
            this.highestShippedInstant = this.failedChunkHighestInstant;
            this.failedChunk = null;
         }

         if (this.logBuffer.next()) {
            byte[] var6 = this.logBuffer.getData();
            ReplicationMessage var7 = new ReplicationMessage(10, var6);
            this.transmitter.sendMessage(var7);
            this.highestShippedInstant = this.logBuffer.getLastInstant();
            this.lastShippingTime = System.currentTimeMillis();
            return true;
         }
      } catch (NoSuchElementException var4) {
         this.masterController.handleExceptions(StandardException.newException("XRE03", var4, new Object[0]));
      } catch (IOException var5) {
         if (var2 != null) {
            this.failedChunk = (ReplicationMessage)var2;
            this.failedChunkHighestInstant = this.logBuffer.getLastInstant();
         }

         throw var5;
      }

      return false;
   }

   public void flushBuffer() throws IOException, StandardException {
      while(this.shipALogChunk()) {
      }

   }

   public void forceFlush() throws IOException, StandardException {
      if (!this.stopShipping) {
         synchronized(this.forceFlushSemaphore) {
            synchronized(this.objLSTSync) {
               this.objLSTSync.notify();
            }

            try {
               this.forceFlushSemaphore.wait(5000L);
            } catch (InterruptedException var5) {
               InterruptStatus.setInterrupted();
            }

         }
      }
   }

   public long getHighestShippedInstant() {
      return this.highestShippedInstant;
   }

   public void flushedInstance(long var1) {
   }

   public void stopLogShipment() {
      this.stopShipping = true;
   }

   public void workToDo() {
      int var1 = this.logBuffer.getFillInformation();
      if (var1 >= 80 || System.currentTimeMillis() - this.lastShippingTime > this.minShippingInterval) {
         synchronized(this.objLSTSync) {
            this.objLSTSync.notify();
         }
      }

   }

   private long calculateSIfromFI() {
      int var1 = this.logBuffer.getFillInformation();
      long var2;
      if (var1 >= 80) {
         var2 = -1L;
      } else if (var1 > 10 && var1 < 80) {
         var2 = this.minShippingInterval;
      } else {
         var2 = this.maxShippingInterval;
      }

      return var2;
   }

   private void getLogShipperProperties() {
      this.minShippingInterval = (long)PropertyUtil.getSystemInt("derby.replication.minLogShippingInterval", 100);
      this.maxShippingInterval = (long)PropertyUtil.getSystemInt("derby.replication.maxLogShippingInterval", 5000);
      byte var1 = 10;
      if (this.minShippingInterval > this.maxShippingInterval / (long)var1) {
         this.minShippingInterval = this.maxShippingInterval / (long)var1;
      }

   }
}
