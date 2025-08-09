package org.apache.derby.impl.db;

import java.sql.SQLException;
import java.util.Properties;
import org.apache.derby.iapi.jdbc.AuthenticationService;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.replication.slave.SlaveFactory;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.store.replication.ReplicationLogger;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class SlaveDatabase extends BasicDatabase {
   private volatile boolean inReplicationSlaveMode;
   private volatile boolean shutdownInitiated;
   private volatile boolean inBoot;
   private volatile StandardException bootException;
   private String dbname;
   private volatile SlaveFactory slaveFac;

   public boolean canSupport(Properties var1) {
      boolean var2 = Monitor.isDesiredCreateType(var1, this.getEngineType());
      if (var2) {
         String var3 = var1.getProperty("replication.slave.mode");
         if (var3 == null || !var3.equals("slavemode")) {
            var2 = false;
         }
      }

      return var2;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.inReplicationSlaveMode = true;
      this.inBoot = true;
      this.shutdownInitiated = false;
      this.dbname = var2.getProperty("replication.slave.dbname");
      SlaveDatabaseBootThread var3 = new SlaveDatabaseBootThread(var1, var2);
      Thread var4 = new Thread(var3, "derby.slave.boot-" + this.dbname);
      var4.setDaemon(true);
      var4.start();
      this.verifySuccessfulBoot();
      this.inBoot = false;
      this.active = true;
   }

   public void stop() {
      if (this.inReplicationSlaveMode && this.slaveFac != null) {
         try {
            this.slaveFac.stopSlave(true);
         } catch (StandardException var5) {
         } finally {
            this.slaveFac = null;
         }
      }

      super.stop();
   }

   public boolean isInSlaveMode() {
      return this.inReplicationSlaveMode;
   }

   public LanguageConnectionContext setupConnection(ContextManager var1, String var2, String var3, String var4) throws StandardException {
      if (this.inReplicationSlaveMode) {
         throw StandardException.newException("08004.C.7", new Object[]{var4});
      } else {
         return super.setupConnection(var1, var2, var3, var4);
      }
   }

   public AuthenticationService getAuthenticationService() throws StandardException {
      if (this.inReplicationSlaveMode) {
         throw StandardException.newException("08004.C.7", new Object[]{this.dbname});
      } else {
         return super.getAuthenticationService();
      }
   }

   public void verifyShutdownSlave() throws StandardException {
      if (!this.shutdownInitiated) {
         throw StandardException.newException("XRE43", new Object[0]);
      } else {
         this.pushDbContext(getContextService().getCurrentContextManager());
      }
   }

   public void stopReplicationSlave() throws SQLException {
      if (!this.shutdownInitiated) {
         if (!this.inReplicationSlaveMode) {
            StandardException var1 = StandardException.newException("XRE40", new Object[0]);
            throw PublicAPI.wrapStandardException(var1);
         } else {
            try {
               this.slaveFac.stopSlave(false);
            } catch (StandardException var2) {
               throw PublicAPI.wrapStandardException(var2);
            }

            this.slaveFac = null;
         }
      }
   }

   public void failover(String var1) throws StandardException {
      if (this.inReplicationSlaveMode) {
         this.slaveFac.failover();

         while(this.inReplicationSlaveMode) {
            try {
               Thread.sleep(500L);
            } catch (InterruptedException var3) {
               InterruptStatus.setInterrupted();
            }
         }
      } else {
         super.failover(var1);
      }

   }

   private void verifySuccessfulBoot() throws StandardException {
      while(!this.isSlaveFactorySet() || !this.slaveFac.isStarted()) {
         if (this.bootException != null) {
            throw this.bootException;
         }

         try {
            Thread.sleep(500L);
         } catch (InterruptedException var2) {
            InterruptStatus.setInterrupted();
         }
      }

      if (this.bootException != null) {
         throw this.bootException;
      }
   }

   private boolean isSlaveFactorySet() {
      if (this.slaveFac != null) {
         return true;
      } else {
         try {
            this.slaveFac = (SlaveFactory)findServiceModule(this, "org.apache.derby.iapi.store.replication.slave.SlaveFactory");
            return true;
         } catch (StandardException var2) {
            return false;
         }
      }
   }

   private void handleShutdown(StandardException var1) {
      if (this.inBoot) {
         this.bootException = var1;
      } else {
         try {
            this.shutdownInitiated = true;
            String var2 = "jdbc:derby:" + this.dbname + ";internal_stopslave=true";
            InternalDriver var3 = InternalDriver.activeDriver();
            if (var3 != null) {
               var3.connect(var2, (Properties)null, 0);
            }
         } catch (Exception var4) {
         }

      }
   }

   private void bootBasicDatabase(boolean var1, Properties var2) throws StandardException {
      super.boot(var1, var2);
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }

   private class SlaveDatabaseBootThread implements Runnable {
      private boolean create;
      private Properties params;

      public SlaveDatabaseBootThread(boolean var2, Properties var3) {
         this.create = var2;
         this.params = var3;
      }

      public void run() {
         Object var1 = null;

         try {
            ContextManager var5 = SlaveDatabase.getContextService().newContextManager();
            SlaveDatabase.getContextService().setCurrentContextManager(var5);
            SlaveDatabase.this.bootBasicDatabase(this.create, this.params);
            SlaveDatabase.this.inReplicationSlaveMode = false;
            if (var5 != null) {
               SlaveDatabase.getContextService().resetCurrentContextManager(var5);
               var5 = null;
            }
         } catch (Exception var4) {
            ReplicationLogger var3 = new ReplicationLogger(SlaveDatabase.this.dbname);
            var3.logError("R005", var4);
            if (var4 instanceof StandardException) {
               SlaveDatabase.this.handleShutdown((StandardException)var4);
            }
         }

      }
   }
}
