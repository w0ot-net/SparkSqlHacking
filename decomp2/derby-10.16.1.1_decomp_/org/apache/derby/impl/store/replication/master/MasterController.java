package org.apache.derby.impl.store.replication.master;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.replication.master.MasterFactory;
import org.apache.derby.impl.store.replication.ReplicationLogger;
import org.apache.derby.impl.store.replication.buffer.LogBufferFullException;
import org.apache.derby.impl.store.replication.buffer.ReplicationLogBuffer;
import org.apache.derby.impl.store.replication.net.ReplicationMessage;
import org.apache.derby.impl.store.replication.net.ReplicationMessageTransmit;
import org.apache.derby.impl.store.replication.net.SlaveAddress;
import org.apache.derby.shared.common.error.StandardException;

public class MasterController implements MasterFactory, ModuleControl, ModuleSupportable {
   private static final int DEFAULT_LOG_BUFFER_SIZE = 32768;
   private static final int LOG_BUFFER_SIZE_MIN = 8192;
   private static final int LOG_BUFFER_SIZE_MAX = 1048576;
   private RawStoreFactory rawStoreFactory;
   private DataFactory dataFactory;
   private LogFactory logFactory;
   private ReplicationLogBuffer logBuffer;
   private AsynchronousLogShipper logShipper;
   private ReplicationMessageTransmit transmitter;
   private ReplicationLogger repLogger;
   private String replicationMode;
   private SlaveAddress slaveAddr;
   private String dbname;
   private int logBufferSize = 0;
   private boolean active = false;
   private static final int SLAVE_CONNECTION_ATTEMPT_TIMEOUT = 5000;

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.replicationMode = var2.getProperty("derby.__rt.replication.master.mode");
   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("derby.__rt.replication.master.mode");
      return var2 != null && var2.equals("derby.__rt.asynch");
   }

   public void stop() {
      try {
         this.stopMaster();
      } catch (StandardException var2) {
         this.repLogger.logError("R008", var2);
      }

   }

   public void startMaster(RawStoreFactory var1, DataFactory var2, LogFactory var3, String var4, int var5, String var6) throws StandardException {
      if (this.active) {
         throw StandardException.newException("XRE22.C", new Object[]{var6});
      } else {
         try {
            this.slaveAddr = new SlaveAddress(var4, var5);
         } catch (UnknownHostException var8) {
            throw StandardException.newException("XRE04.C.1", var8, new Object[]{var6, this.getHostName(), String.valueOf(this.getPortNumber())});
         }

         this.dbname = var6;
         this.rawStoreFactory = var1;
         this.dataFactory = var2;
         this.logFactory = var3;
         this.repLogger = new ReplicationLogger(var6);
         this.getMasterProperties();
         this.logBuffer = new ReplicationLogBuffer(this.logBufferSize, this);

         try {
            this.logFactory.startReplicationMasterRole(this);
            this.rawStoreFactory.unfreeze();
            this.setupConnection();
            if (this.replicationMode.equals("derby.__rt.asynch")) {
               this.logShipper = new AsynchronousLogShipper(this.logBuffer, this.transmitter, this, this.repLogger);
               this.logShipper.setDaemon(true);
               this.logShipper.start();
            }
         } catch (StandardException var9) {
            this.repLogger.logError("R005", var9);
            this.logFactory.stopReplicationMasterRole();
            this.teardownNetwork();
            throw var9;
         }

         this.active = true;
         Monitor.logTextMessage("R007", var6);
      }
   }

   public void stopMaster() throws StandardException {
      if (!this.active) {
         throw StandardException.newException("XRE07", new Object[0]);
      } else {
         this.active = false;
         this.logFactory.stopReplicationMasterRole();

         try {
            this.logShipper.flushBuffer();
         } catch (IOException var6) {
            this.repLogger.logError("R009", var6);
         } catch (StandardException var7) {
            this.repLogger.logError("R009", var7);
         } finally {
            this.teardownNetwork();
         }

         Monitor.logTextMessage("R008", this.dbname);
      }
   }

   public void startFailover() throws StandardException {
      if (!this.active) {
         throw StandardException.newException("XRE07", new Object[0]);
      } else {
         ReplicationMessage var1 = null;
         this.active = false;
         this.rawStoreFactory.freeze();

         try {
            this.logShipper.flushBuffer();
            ReplicationMessage var2 = new ReplicationMessage(21, (Object)null);
            var1 = this.transmitter.sendMessageWaitForReply(var2);
         } catch (IOException var3) {
            this.handleFailoverFailure(var3);
         } catch (StandardException var4) {
            this.handleFailoverFailure(var4);
         }

         if (var1 == null) {
            this.handleFailoverFailure((Throwable)null);
         } else {
            if (var1.getType() == 11) {
               this.teardownNetwork();
               this.rawStoreFactory.unfreeze();
               throw StandardException.newException("XRE20.D", new Object[]{this.dbname});
            }

            this.handleFailoverFailure((Throwable)null);
         }

      }
   }

   private void getMasterProperties() {
      this.logBufferSize = PropertyUtil.getSystemInt("derby.replication.logBufferSize", 32768);
      if (this.logBufferSize < 8192) {
         this.logBufferSize = 8192;
      } else if (this.logBufferSize > 1048576) {
         this.logBufferSize = 1048576;
      }

   }

   private void handleFailoverFailure(Throwable var1) throws StandardException {
      this.teardownNetwork();
      this.rawStoreFactory.unfreeze();
      if (var1 != null) {
         throw StandardException.newException("XRE21.C", var1, new Object[]{this.dbname});
      } else {
         throw StandardException.newException("XRE21.C", new Object[]{this.dbname});
      }
   }

   public void appendLog(long var1, byte[] var3, int var4, int var5) {
      try {
         this.logBuffer.appendLog(var1, var3, var4, var5);
      } catch (LogBufferFullException var11) {
         try {
            this.logShipper.forceFlush();
            this.logBuffer.appendLog(var1, var3, var4, var5);
         } catch (LogBufferFullException var8) {
            this.printStackAndStopMaster(var8);
         } catch (IOException var9) {
            this.printStackAndStopMaster(var9);
         } catch (StandardException var10) {
            this.printStackAndStopMaster(var10);
         }
      }

   }

   public void flushedTo(long var1) {
      this.logShipper.flushedInstance(var1);
   }

   private void setupConnection() throws StandardException {
      try {
         if (this.transmitter != null) {
            this.transmitter.tearDown();
         }

         this.transmitter = new ReplicationMessageTransmit(this.slaveAddr);
         if (this.logShipper != null && this.logShipper.getHighestShippedInstant() != -1L) {
            this.transmitter.initConnection(5000, this.logShipper.getHighestShippedInstant());
         } else {
            this.transmitter.initConnection(5000, this.logFactory.getFirstUnflushedInstantAsLong());
         }

      } catch (SocketTimeoutException var2) {
         throw StandardException.newException("XRE06", new Object[]{this.dbname});
      } catch (IOException var3) {
         throw StandardException.newException("XRE04.C.1", var3, new Object[]{this.dbname, this.getHostName(), String.valueOf(this.getPortNumber())});
      } catch (StandardException var4) {
         throw var4;
      } catch (Exception var5) {
         throw StandardException.newException("XRE04.C.1", var5, new Object[]{this.dbname, this.getHostName(), String.valueOf(this.getPortNumber())});
      }
   }

   ReplicationMessageTransmit handleExceptions(Exception var1) {
      if (var1 instanceof IOException) {
         this.repLogger.logError("R009", var1);
         Monitor.logTextMessage("R010", this.dbname);

         while(this.active) {
            try {
               this.transmitter = new ReplicationMessageTransmit(this.slaveAddr);
               if (this.logShipper != null && this.logShipper.getHighestShippedInstant() != -1L) {
                  this.transmitter.initConnection(5000, this.logShipper.getHighestShippedInstant());
                  break;
               }

               this.transmitter.initConnection(5000, this.logFactory.getFirstUnflushedInstantAsLong());
               break;
            } catch (SocketTimeoutException var3) {
            } catch (IOException var4) {
            } catch (Exception var5) {
               this.printStackAndStopMaster(var5);
               return null;
            }
         }
      } else if (var1 instanceof StandardException) {
         this.printStackAndStopMaster(var1);
         return null;
      }

      return this.transmitter;
   }

   private void printStackAndStopMaster(Exception var1) {
      this.repLogger.logError("R009", var1);

      try {
         this.stopMaster();
      } catch (StandardException var3) {
         this.repLogger.logError("R008", var3);
      }

   }

   public void workToDo() {
      this.logShipper.workToDo();
   }

   private void teardownNetwork() {
      if (this.logShipper != null) {
         this.logShipper.stopLogShipment();
      }

      if (this.transmitter != null) {
         try {
            ReplicationMessage var1 = new ReplicationMessage(20, (Object)null);
            this.transmitter.sendMessage(var1);
         } catch (IOException var3) {
         }

         try {
            this.transmitter.tearDown();
         } catch (IOException var2) {
         }
      }

   }

   String getDbName() {
      return this.dbname;
   }

   private String getHostName() {
      return this.slaveAddr.getHostAddress().getHostName();
   }

   private int getPortNumber() {
      return this.slaveAddr.getPortNumber();
   }
}
