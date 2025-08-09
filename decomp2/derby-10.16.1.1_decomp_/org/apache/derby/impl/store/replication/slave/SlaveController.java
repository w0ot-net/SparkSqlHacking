package org.apache.derby.impl.store.replication.slave;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.replication.slave.SlaveFactory;
import org.apache.derby.impl.store.raw.log.LogCounter;
import org.apache.derby.impl.store.raw.log.LogToFile;
import org.apache.derby.impl.store.replication.ReplicationLogger;
import org.apache.derby.impl.store.replication.net.ReplicationMessage;
import org.apache.derby.impl.store.replication.net.ReplicationMessageReceive;
import org.apache.derby.impl.store.replication.net.SlaveAddress;
import org.apache.derby.shared.common.error.StandardException;

public class SlaveController implements SlaveFactory, ModuleControl, ModuleSupportable {
   private static final int DEFAULT_SOCKET_TIMEOUT = 1000;
   private RawStoreFactory rawStoreFactory;
   private LogToFile logToFile;
   private ReplicationMessageReceive receiver;
   private ReplicationLogger repLogger;
   private SlaveAddress slaveAddr;
   private String dbname;
   private volatile long highestLogInstant = -1L;
   private volatile boolean inReplicationSlaveMode = true;
   private volatile boolean startupSuccessful = false;
   private ReplicationLogScan logScan;
   private SlaveLogReceiverThread logReceiverThread;

   public void boot(boolean var1, Properties var2) throws StandardException {
      String var3 = var2.getProperty("slavePort");

      try {
         int var4 = -1;
         if (var3 != null) {
            var4 = Integer.parseInt(var3);
         }

         this.slaveAddr = new SlaveAddress(var2.getProperty("slaveHost"), var4);
      } catch (UnknownHostException var5) {
         throw StandardException.newException("XRE04.C.1", var5, new Object[]{this.dbname, this.getHostName(), String.valueOf(this.getPortNumber())});
      }

      this.dbname = var2.getProperty("replication.slave.dbname");
      this.repLogger = new ReplicationLogger(this.dbname);
   }

   public void stop() {
      if (this.inReplicationSlaveMode) {
         try {
            this.stopSlave(true);
         } catch (StandardException var2) {
         }
      }

   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("replication.slave.mode");
      return var2 != null && var2.equals("slavemode");
   }

   public void startSlave(RawStoreFactory var1, LogFactory var2) throws StandardException {
      this.rawStoreFactory = var1;

      try {
         this.logToFile = (LogToFile)var2;
      } catch (ClassCastException var4) {
         throw StandardException.newException("XRE00", new Object[0]);
      }

      this.logToFile.initializeReplicationSlaveRole();
      this.receiver = new ReplicationMessageReceive(this.slaveAddr, this.dbname);

      while(!this.setupConnection()) {
         if (!this.inReplicationSlaveMode) {
            return;
         }
      }

      this.logScan = new ReplicationLogScan();
      this.startLogReceiverThread();
      this.startupSuccessful = true;
      Monitor.logTextMessage("R003", this.dbname);
   }

   private void stopSlave() throws StandardException {
      this.inReplicationSlaveMode = false;
      this.teardownNetwork();
      this.logToFile.stopReplicationSlaveRole();
      Monitor.logTextMessage("R004", this.dbname);
   }

   public void stopSlave(boolean var1) throws StandardException {
      if (!var1 && this.isConnectedToMaster()) {
         throw StandardException.newException("XRE41.C", new Object[0]);
      } else {
         this.stopSlave();
      }
   }

   public void failover() throws StandardException {
      if (this.isConnectedToMaster()) {
         throw StandardException.newException("XRE41.C", new Object[0]);
      } else {
         this.doFailover();
         this.teardownNetwork();
      }
   }

   private void doFailover() {
      this.inReplicationSlaveMode = false;
      this.logToFile.failoverSlave();
      Monitor.logTextMessage("R020", this.dbname);
   }

   public boolean isStarted() {
      return this.startupSuccessful;
   }

   private boolean setupConnection() throws StandardException {
      try {
         if (this.highestLogInstant != -1L) {
            this.receiver.initConnection(1000, this.highestLogInstant, this.dbname);
         } else {
            this.receiver.initConnection(1000, this.logToFile.getFirstUnflushedInstantAsLong(), this.dbname);
         }

         return true;
      } catch (StandardException var2) {
         throw var2;
      } catch (SocketTimeoutException var3) {
         return false;
      } catch (Exception var4) {
         throw StandardException.newException("XRE04.C.1", var4, new Object[]{this.dbname, this.getHostName(), String.valueOf(this.getPortNumber())});
      }
   }

   private void handleDisconnect(Exception var1) {
      if (this.inReplicationSlaveMode) {
         this.repLogger.logError("R006", var1);

         try {
            while(!this.setupConnection()) {
               if (!this.inReplicationSlaveMode) {
                  return;
               }
            }

            this.startLogReceiverThread();
         } catch (StandardException var3) {
            this.handleFatalException(var3);
         }

      }
   }

   private boolean isConnectedToMaster() {
      return this.receiver == null ? false : this.receiver.isConnectedToMaster();
   }

   private void startLogReceiverThread() {
      this.logReceiverThread = new SlaveLogReceiverThread();
      this.logReceiverThread.setDaemon(true);
      this.logReceiverThread.start();
   }

   private void handleFatalException(Exception var1) {
      if (this.inReplicationSlaveMode) {
         this.repLogger.logError("R005", var1);

         try {
            this.stopSlave();
         } catch (StandardException var3) {
            this.repLogger.logError("R005", var3);
         }

      }
   }

   private void teardownNetwork() {
      try {
         if (this.receiver != null) {
            this.receiver.tearDown();
            this.receiver = null;
         }
      } catch (IOException var2) {
         this.repLogger.logError((String)null, var2);
      }

   }

   private String getHostName() {
      return this.slaveAddr.getHostAddress().getHostName();
   }

   private int getPortNumber() {
      return this.slaveAddr.getPortNumber();
   }

   private class SlaveLogReceiverThread extends Thread {
      SlaveLogReceiverThread() {
         super("derby.slave.logger-" + SlaveController.this.dbname);
      }

      public void run() {
         try {
            while(SlaveController.this.inReplicationSlaveMode) {
               ReplicationMessage var1 = SlaveController.this.receiver.readMessage();
               switch (var1.getType()) {
                  case 10:
                     byte[] var7 = (byte[])var1.getMessage();
                     this.handleLogChunk(var7);
                     break;
                  case 20:
                     SlaveController.this.stopSlave();
                     break;
                  case 21:
                     SlaveController.this.doFailover();
                     ReplicationMessage var3 = new ReplicationMessage(11, "failover succeeded");
                     SlaveController.this.receiver.sendMessage(var3);
                     SlaveController.this.teardownNetwork();
                     break;
                  default:
                     System.out.println("Not handling non-log messages yet - got a type " + var1.getType());
               }
            }
         } catch (EOFException var4) {
            SlaveController.this.handleDisconnect(var4);
         } catch (StandardException var5) {
            SlaveController.this.handleFatalException(var5);
         } catch (Exception var6) {
            StandardException var2 = StandardException.newException("XRE03", var6, new Object[0]);
            SlaveController.this.handleFatalException(var2);
         }

      }

      private void handleLogChunk(byte[] var1) throws StandardException {
         SlaveController.this.logScan.init(var1);

         while(SlaveController.this.logScan.next()) {
            if (SlaveController.this.logScan.isLogFileSwitch()) {
               SlaveController.this.logToFile.switchLogFile();
            } else {
               long var2 = SlaveController.this.logToFile.appendLogRecord(SlaveController.this.logScan.getData(), 0, SlaveController.this.logScan.getDataLength(), (byte[])null, 0, 0);
               if (SlaveController.this.logScan.getInstant() != var2) {
                  throw StandardException.newException("XRE05.C", new Object[]{SlaveController.this.dbname, LogCounter.getLogFileNumber(SlaveController.this.logScan.getInstant()), LogCounter.getLogFilePosition(SlaveController.this.logScan.getInstant()), LogCounter.getLogFileNumber(var2), LogCounter.getLogFilePosition(var2)});
               }

               SlaveController.this.highestLogInstant = var2;
            }
         }

      }
   }
}
