package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.jdbc.AuthenticationService;
import org.apache.derby.iapi.jdbc.EngineConnection;
import org.apache.derby.iapi.jdbc.EngineLOB;
import org.apache.derby.iapi.jdbc.FailedProperties40;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.memory.LowMemory;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.XATransactionController;
import org.apache.derby.iapi.transaction.TransactionControl;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.db.SlaveDatabase;
import org.apache.derby.impl.jdbc.authentication.NoneAuthenticationServiceImpl;
import org.apache.derby.security.DatabasePermission;
import org.apache.derby.shared.common.error.SQLWarningFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class EmbedConnection implements EngineConnection {
   protected static final StandardException exceptionClose = StandardException.closeException();
   public static final SQLException NO_MEM = newSQLException("08004", "java.lang.OutOfMemoryError");
   public static final LowMemory memoryState = new LowMemory();
   DatabaseMetaData dbMetadata;
   TransactionResourceImpl tr;
   private HashMap lobHashMap = null;
   private int lobHMKey = 0;
   private WeakHashMap lobReferences = null;
   private HashSet lobFiles;
   private boolean active;
   private boolean aborting = false;
   boolean autoCommit = true;
   boolean needCommit;
   private boolean usingNoneAuth;
   private int connectionHoldAbility = 1;
   final EmbedConnection rootConnection;
   private SQLWarning topWarning;
   private InternalDriver factory;
   private Connection applicationConnection;
   private int resultSetId;
   private String connString;
   private static final int OP_ENCRYPT = 0;
   private static final int OP_SHUTDOWN = 1;
   private static final int OP_HARD_UPGRADE = 2;
   private static final int OP_REPLICATION = 3;
   private static final int OP_DECRYPT = 4;

   public EmbedConnection(InternalDriver var1, String var2, Properties var3) throws SQLException {
      this.applicationConnection = this.rootConnection = this;
      this.factory = var1;
      this.tr = new TransactionResourceImpl(var1, var2, var3);
      this.active = true;
      this.setupContextStack();

      try {
         EmbedConnectionContext var4 = this.pushConnectionContext(this.tr.getContextManager());
         boolean var31 = isTrue(var3, "shutdown");
         Database var6 = (Database)findService("org.apache.derby.database.Database", this.tr.getDBName());
         if (var6 != null && this.isCryptoBoot(var3)) {
            this.addWarning(SQLWarningFactory.newSQLWarning("01J17", new Object[0]));
         }

         boolean var7 = this.createBoot(var3);
         boolean var8 = !var7 && this.isCryptoBoot(var3);
         boolean var9 = !var7 && this.isHardUpgradeBoot(var3);
         boolean var10 = this.isStartReplicationSlaveBoot(var3);
         boolean var11 = false;
         boolean var12 = false;
         boolean var13 = false;
         boolean var14 = this.isDropDatabase(var3);
         if (var31 && var14) {
            throw newSQLException("XJ048.C", "shutdown, drop");
         } else {
            if (var8) {
               this.checkConflictingCryptoAttributes(var3);
            }

            String var15 = this.getReplicationOperation(var3);
            if (var15 != null && (var7 || var31 || var14 || var8 || var9)) {
               throw StandardException.newException("XRE10", new Object[]{var15});
            } else {
               if (this.isReplicationFailover(var3)) {
                  this.checkDatabaseBooted(var6, "failover", this.tr.getDBName());
                  if (var6.isInSlaveMode()) {
                     var13 = true;
                  } else {
                     var12 = true;
                  }
               }

               Properties var16 = null;
               if (var10) {
                  if (var6 != null) {
                     var11 = true;
                  } else {
                     var3.setProperty("replication.slave.mode", "slavepremode");
                  }
               }

               if (this.isStopReplicationSlaveBoot(var3)) {
                  this.handleStopReplicationSlave(var6, var3);
               } else {
                  if (this.isInternalShutdownSlaveDatabase(var3)) {
                     this.internalStopReplicationSlave(var6, var3);
                     return;
                  }

                  if (var13) {
                     this.handleFailoverSlave(var6);
                  }
               }

               if (var6 != null) {
                  this.tr.setDatabase(var6);
                  var8 = false;
                  var9 = false;
               } else if (!var31) {
                  if (var8 || var9) {
                     var16 = var3;
                     var3 = this.removePhaseTwoProps((Properties)var3.clone());
                  }

                  if (!this.bootDatabase(var3, var9)) {
                     this.tr.clearContextInError();
                     this.setInactive();
                     return;
                  }
               }

               if (var7 && !var31 && !var14) {
                  if (this.tr.getDatabase() != null) {
                     this.addWarning(SQLWarningFactory.newSQLWarning("01J01", new Object[]{this.getDBName()}));
                  } else {
                     this.checkUserCredentials(true, (String)null, var3);
                     var6 = this.createDatabase(this.tr.getDBName(), var3);
                     this.tr.setDatabase(var6);
                  }
               }

               if (this.tr.getDatabase() == null) {
                  this.handleDBNotFound();
               }

               try {
                  this.checkUserCredentials(false, this.tr.getDBName(), var3);
               } catch (SQLException var26) {
                  if (var10 && !var11) {
                     this.tr.startTransaction();
                     this.handleException(this.tr.shutdownDatabaseException());
                  }

                  throw var26;
               }

               this.tr.startTransaction();
               if (this.isStartReplicationMasterBoot(var3) || this.isStopReplicationMasterBoot(var3) || var12) {
                  if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
                     this.checkIsDBOwner(3);
                  }

                  if (this.isStartReplicationMasterBoot(var3)) {
                     this.handleStartReplicationMaster(this.tr, var3);
                  } else if (this.isStopReplicationMasterBoot(var3)) {
                     this.handleStopReplicationMaster(this.tr, var3);
                  } else if (var12) {
                     this.handleFailoverMaster(this.tr);
                  }
               }

               if (var8 || var9 || var10) {
                  if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
                     byte var17;
                     if (var8) {
                        if (isTrue(var16, "decryptDatabase")) {
                           var17 = 4;
                        } else {
                           var17 = 0;
                        }
                     } else if (var9) {
                        var17 = 2;
                     } else {
                        var17 = 3;
                     }

                     try {
                        this.checkIsDBOwner(var17);
                     } catch (SQLException var25) {
                        if (var10) {
                           this.handleException(this.tr.shutdownDatabaseException());
                        }

                        throw var25;
                     }
                  }

                  if (var10) {
                     if (var11) {
                        throw StandardException.newException("XRE09.C", new Object[]{this.getTR().getDBName()});
                     }

                     var3.setProperty("replication.slave.mode", "slavemode");
                     var3.setProperty("replication.slave.dbname", this.getTR().getDBName());
                  } else {
                     var3 = var16;
                  }

                  this.handleException(this.tr.shutdownDatabaseException());
                  this.restoreContextStack();
                  this.tr = new TransactionResourceImpl(var1, var2, var3);
                  this.active = true;
                  this.setupContextStack();
                  var4 = this.pushConnectionContext(this.tr.getContextManager());
                  if (!this.bootDatabase(var3, false)) {
                     this.tr.clearContextInError();
                     this.setInactive();
                     return;
                  }

                  if (var10) {
                     throw StandardException.newException("XRE08", new Object[]{this.getTR().getDBName()});
                  }

                  this.tr.startTransaction();
               }

               if (var31) {
                  if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
                     this.checkIsDBOwner(1);
                  }

                  throw this.tr.shutdownDatabaseException();
               } else if (var14) {
                  if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
                     this.checkIsDBOwner(1);
                  }

                  String var33 = this.tr.getDBName();
                  this.handleException(this.tr.shutdownDatabaseException());
                  sleep(500L);
                  removePersistentService(var33);
                  StandardException var18 = StandardException.newException("08006.D.1", new Object[]{var33});
                  var18.setReport(1);
                  throw var18;
               } else {
                  if (this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
                     this.addWarning(SQLWarningFactory.newSQLWarning("01J14", new Object[0]));
                  }

                  InterruptStatus.restoreIntrFlagIfSeen(this.privilegedGetLCC());
               }
            }
         }
      } catch (OutOfMemoryError var27) {
         InterruptStatus.restoreIntrFlagIfSeen();
         this.restoreContextStack();
         this.tr.lcc = null;
         this.tr.cm = null;
         memoryState.setLowMemory();
         throw NO_MEM;
      } catch (Throwable var28) {
         InterruptStatus.restoreIntrFlagIfSeen();
         if (var28 instanceof StandardException var5) {
            if (var5.getSeverity() < 40000) {
               var5.setSeverity(40000);
            }
         }

         this.tr.cleanupOnError(var28, false);
         throw this.handleException(var28);
      } finally {
         this.restoreContextStack();
      }
   }

   private void checkDatabaseBooted(Database var1, String var2, String var3) throws SQLException {
      if (var1 == null) {
         this.setInactive();
         throw newSQLException("XRE11.C", var2, var3);
      }
   }

   private boolean createBoot(Properties var1) throws SQLException {
      int var2 = 0;
      if (isTrue(var1, "create")) {
         ++var2;
      }

      int var3 = 0;
      if (isSet(var1, "createFrom")) {
         ++var3;
      }

      if (isSet(var1, "restoreFrom")) {
         ++var3;
      }

      if (isSet(var1, "rollForwardRecoveryFrom")) {
         ++var3;
      }

      if (var3 > 1) {
         throw newSQLException("XJ081.C");
      } else if (var3 != 0 && this.isCryptoBoot(var1)) {
         throw newSQLException("XJ081.C");
      } else {
         var2 += var3;
         if (var2 > 1) {
            throw newSQLException("XJ049.C");
         } else if (var2 == 1 && this.isDropDatabase(var1)) {
            String var4 = "XJ049.C";
            if (var3 > 0) {
               var4 = "XJ081.C";
            }

            throw newSQLException(var4);
         } else {
            return var2 - var3 == 1;
         }
      }
   }

   private void handleDBNotFound() throws SQLException {
      String var1 = this.tr.getDBName();
      this.setInactive();
      throw newSQLException("XJ004.C", var1);
   }

   private boolean isDropDatabase(Properties var1) {
      return isTrue(var1, "drop");
   }

   private boolean isCryptoBoot(Properties var1) throws SQLException {
      return vetTrue(var1, "dataEncryption") || vetTrue(var1, "decryptDatabase") || isSet(var1, "newBootPassword") || isSet(var1, "newEncryptionKey");
   }

   private boolean isHardUpgradeBoot(Properties var1) {
      return isTrue(var1, "upgrade");
   }

   private boolean isStartReplicationSlaveBoot(Properties var1) {
      return isTrue(var1, "startSlave");
   }

   private boolean isStartReplicationMasterBoot(Properties var1) {
      return isTrue(var1, "startMaster");
   }

   private boolean isReplicationFailover(Properties var1) {
      return isTrue(var1, "failover");
   }

   private boolean isStopReplicationMasterBoot(Properties var1) {
      return isTrue(var1, "stopMaster");
   }

   private boolean isStopReplicationSlaveBoot(Properties var1) {
      return isTrue(var1, "stopSlave");
   }

   private boolean isInternalShutdownSlaveDatabase(Properties var1) {
      return isTrue(var1, "internal_stopslave");
   }

   private static boolean isSet(Properties var0, String var1) {
      return var0.getProperty(var1) != null;
   }

   private static boolean isTrue(Properties var0, String var1) {
      return Boolean.valueOf(var0.getProperty(var1));
   }

   private static boolean vetTrue(Properties var0, String var1) throws SQLException {
      String var2 = var0.getProperty(var1);
      if (var2 == null) {
         return false;
      } else if (Boolean.valueOf(var2)) {
         return true;
      } else {
         throw newSQLException("XJ05B.C", var1, var2, Boolean.TRUE.toString());
      }
   }

   private String getReplicationOperation(Properties var1) throws StandardException {
      String var2 = null;
      int var3 = 0;
      if (this.isStartReplicationSlaveBoot(var1)) {
         var2 = "startSlave";
         ++var3;
      }

      if (this.isStartReplicationMasterBoot(var1)) {
         var2 = "startMaster";
         ++var3;
      }

      if (this.isStopReplicationSlaveBoot(var1)) {
         var2 = "stopSlave";
         ++var3;
      }

      if (this.isInternalShutdownSlaveDatabase(var1)) {
         var2 = "internal_stopslave";
         ++var3;
      }

      if (this.isStopReplicationMasterBoot(var1)) {
         var2 = "stopMaster";
         ++var3;
      }

      if (this.isReplicationFailover(var1)) {
         var2 = "failover";
         ++var3;
      }

      if (var3 > 1) {
         throw StandardException.newException("XRE10", new Object[]{var2});
      } else {
         return var2;
      }
   }

   private void handleStartReplicationMaster(TransactionResourceImpl var1, Properties var2) throws SQLException {
      if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
         this.checkIsDBOwner(3);
      }

      String var3 = var2.getProperty("slaveHost");
      if (var3 == null) {
         SQLException var6 = newSQLException("XCY03.S", "slaveHost");
         throw newSQLException("08004", var6);
      } else {
         String var4 = var2.getProperty("slavePort");
         int var5 = -1;
         if (var4 != null) {
            var5 = Integer.parseInt(var4);
         }

         var1.getDatabase().startReplicationMaster(this.getTR().getDBName(), var3, var5, "derby.__rt.asynch");
      }
   }

   private void handleStopReplicationMaster(TransactionResourceImpl var1, Properties var2) throws SQLException {
      if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
         this.checkIsDBOwner(3);
      }

      var1.getDatabase().stopReplicationMaster();
   }

   private void handleStopReplicationSlave(Database var1, Properties var2) throws StandardException, SQLException {
      this.checkDatabaseBooted(var1, "stopSlave", this.tr.getDBName());
      var1.stopReplicationSlave();
      throw newSQLException("XRE42.C", this.getTR().getDBName());
   }

   private void internalStopReplicationSlave(Database var1, Properties var2) throws StandardException, SQLException {
      this.checkDatabaseBooted(var1, "internal_stopslave", this.tr.getDBName());
      if (!(var1 instanceof SlaveDatabase)) {
         throw newSQLException("XRE40");
      } else {
         ((SlaveDatabase)var1).verifyShutdownSlave();
         this.handleException(this.tr.shutdownDatabaseException());
      }
   }

   private void handleFailoverMaster(TransactionResourceImpl var1) throws SQLException, StandardException {
      if (!this.usingNoneAuth && this.privilegedGetLCC().usesSqlAuthorization()) {
         this.checkIsDBOwner(3);
      }

      var1.getDatabase().failover(var1.getDBName());
   }

   private void handleFailoverSlave(Database var1) throws SQLException {
      try {
         var1.failover(this.getTR().getDBName());
      } catch (StandardException var3) {
         throw Util.generateCsSQLException(var3);
      }
   }

   private Properties removePhaseTwoProps(Properties var1) {
      var1.remove("dataEncryption");
      var1.remove("decryptDatabase");
      var1.remove("newBootPassword");
      var1.remove("newEncryptionKey");
      var1.remove("upgrade");
      return var1;
   }

   public EmbedConnection(EmbedConnection var1) {
      this.autoCommit = false;
      this.tr = null;
      this.active = true;
      this.rootConnection = var1.rootConnection;
      this.applicationConnection = this;
      this.factory = var1.factory;
      this.connectionHoldAbility = var1.connectionHoldAbility;
   }

   private void checkUserCredentials(boolean var1, String var2, Properties var3) throws SQLException {
      AuthenticationService var4 = null;

      try {
         if (var2 == null) {
            var4 = this.getLocalDriver().getAuthenticationService();
         } else {
            var4 = this.getTR().getDatabase().getAuthenticationService();
         }
      } catch (StandardException var8) {
         throw Util.generateCsSQLException(var8);
      }

      if (var4 == null) {
         String var11 = MessageService.getTextMessage(var2 == null ? "A001" : "A002", new Object[0]);
         throw newSQLException("08004", var11);
      } else if (var1 && this.compareDatabaseNames(this.getDBName(), var4.getSystemCredentialsDatabaseName())) {
         String var10 = var3.getProperty("user");
         String var6 = var3.getProperty("password");
         if (this.emptyCredential(var10) || this.emptyCredential(var6)) {
            throw newSQLException("08004.C.13");
         }
      } else {
         if (var2 != null) {
            this.checkUserIsNotARole();
         }

         boolean var5 = true;

         try {
            var5 = var4.authenticate(var2, var3);
         } catch (SQLWarning var7) {
            this.addWarning(var7);
         }

         if (!var5) {
            throw newSQLException("08004.C.1", MessageService.getTextMessage("A020", new Object[0]));
         } else {
            if (var4 instanceof NoneAuthenticationServiceImpl) {
               this.usingNoneAuth = true;
            }

         }
      }
   }

   private boolean emptyCredential(String var1) {
      return var1 == null || var1.length() == 0;
   }

   private boolean compareDatabaseNames(String var1, String var2) throws SQLException {
      try {
         String var3 = getMonitor().getCanonicalServiceName(var1);
         String var4 = getMonitor().getCanonicalServiceName(var2);
         return var3 == null ? false : var3.equals(var4);
      } catch (StandardException var5) {
         throw Util.generateCsSQLException(var5);
      }
   }

   private void checkUserIsNotARole() throws SQLException {
      TransactionResourceImpl var1 = this.getTR();

      try {
         var1.startTransaction();
         LanguageConnectionContext var2 = var1.getLcc();
         String var3 = var2.getSessionUserId();
         DataDictionary var4 = var2.getDataDictionary();
         if (var2.usesSqlAuthorization() && var4.checkVersion(160, (String)null)) {
            TransactionController var5 = var2.getTransactionExecute();
            String var6 = MessageService.getTextMessage("A020", new Object[0]);
            if (var4.getRoleDefinitionDescriptor(var3) != null) {
               throw newSQLException("08004.C.1", var6);
            }
         }

         var1.rollback();
         InterruptStatus.restoreIntrFlagIfSeen(var2);
      } catch (StandardException var8) {
         try {
            var1.rollback();
         } catch (StandardException var7) {
         }

         throw this.handleException(var8);
      }
   }

   private void checkIsDBOwner(int var1) throws SQLException {
      LanguageConnectionContext var2 = this.privilegedGetLCC();
      String var3 = var2.getSessionUserId();
      String var4 = var2.getDataDictionary().getAuthorizationDatabaseOwner();
      if (!var3.equals(var4)) {
         switch (var1) {
            case 0 -> throw newSQLException("08004.C.5", var3, this.tr.getDBName());
            case 1 -> throw newSQLException("08004.C.4", var3, this.tr.getDBName());
            case 2 -> throw newSQLException("08004.C.6", var3, this.tr.getDBName());
            case 3 -> throw newSQLException("08004.C.8", var3, this.tr.getDBName());
            case 4 -> throw newSQLException("08004.C.14", var3, this.tr.getDBName());
            default -> throw newSQLException("08004.C.3");
         }
      }
   }

   public int getEngineType() {
      Database var1 = this.getDatabase();
      return null == var1 ? 0 : var1.getEngineType();
   }

   public final Statement createStatement() throws SQLException {
      return this.createStatement(1003, 1007, this.connectionHoldAbility);
   }

   public final Statement createStatement(int var1, int var2) throws SQLException {
      return this.createStatement(var1, var2, this.connectionHoldAbility);
   }

   public final Statement createStatement(int var1, int var2, int var3) throws SQLException {
      this.checkIfClosed();
      return this.factory.newEmbedStatement(this, false, this.setResultSetType(var1), var2, var3);
   }

   public final PreparedStatement prepareStatement(String var1) throws SQLException {
      return this.prepareStatement(var1, 1003, 1007, this.connectionHoldAbility, 2, (int[])null, (String[])null);
   }

   public final PreparedStatement prepareStatement(String var1, int var2, int var3) throws SQLException {
      return this.prepareStatement(var1, var2, var3, this.connectionHoldAbility, 2, (int[])null, (String[])null);
   }

   public final PreparedStatement prepareStatement(String var1, int var2, int var3, int var4) throws SQLException {
      return this.prepareStatement(var1, var2, var3, var4, 2, (int[])null, (String[])null);
   }

   public final PreparedStatement prepareStatement(String var1, int[] var2) throws SQLException {
      return this.prepareStatement(var1, 1003, 1007, this.connectionHoldAbility, var2 != null && var2.length != 0 ? 1 : 2, var2, (String[])null);
   }

   public final PreparedStatement prepareStatement(String var1, String[] var2) throws SQLException {
      return this.prepareStatement(var1, 1003, 1007, this.connectionHoldAbility, var2 != null && var2.length != 0 ? 1 : 2, (int[])null, var2);
   }

   public final PreparedStatement prepareStatement(String var1, int var2) throws SQLException {
      return this.prepareStatement(var1, 1003, 1007, this.connectionHoldAbility, var2, (int[])null, (String[])null);
   }

   private PreparedStatement prepareStatement(String var1, int var2, int var3, int var4, int var5, int[] var6, String[] var7) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         PreparedStatement var9;
         try {
            var9 = this.factory.newEmbedPreparedStatement(this, var1, false, this.setResultSetType(var2), var3, var4, var5, var6, var7);
         } finally {
            this.restoreContextStack();
         }

         return var9;
      }
   }

   public final CallableStatement prepareCall(String var1) throws SQLException {
      return this.prepareCall(var1, 1003, 1007, this.connectionHoldAbility);
   }

   public final CallableStatement prepareCall(String var1, int var2, int var3) throws SQLException {
      return this.prepareCall(var1, var2, var3, this.connectionHoldAbility);
   }

   public final CallableStatement prepareCall(String var1, int var2, int var3, int var4) throws SQLException {
      this.checkIfClosed();
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         CallableStatement var6;
         try {
            var6 = this.factory.newEmbedCallableStatement(this, var1, this.setResultSetType(var2), var3, var4);
         } finally {
            this.restoreContextStack();
         }

         return var6;
      }
   }

   public String nativeSQL(String var1) throws SQLException {
      this.checkIfClosed();
      return var1;
   }

   public void setAutoCommit(boolean var1) throws SQLException {
      this.checkIfClosed();
      if (this.rootConnection != this && var1) {
         throw newSQLException("XJ030.S");
      } else {
         if (this.autoCommit != var1) {
            this.commit();
         }

         this.autoCommit = var1;
      }
   }

   public boolean getAutoCommit() throws SQLException {
      this.checkIfClosed();
      return this.autoCommit;
   }

   public void commit() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            this.getTR().commit();
            this.clearLOBMapping();
            InterruptStatus.restoreIntrFlagIfSeen(this.privilegedGetLCC());
         } catch (Throwable var8) {
            throw this.handleException(var8);
         } finally {
            this.restoreContextStack();
         }

         this.needCommit = false;
      }
   }

   public void rollback() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            this.getTR().rollback();
            this.clearLOBMapping();
            InterruptStatus.restoreIntrFlagIfSeen(this.privilegedGetLCC());
         } catch (Throwable var8) {
            throw this.handleException(var8);
         } finally {
            this.restoreContextStack();
         }

         this.needCommit = false;
      }
   }

   public void close() throws SQLException {
      this.checkForTransactionInProgress();
      this.close(exceptionClose);
   }

   public void checkForTransactionInProgress() throws SQLException {
      if (!this.isClosed() && this.rootConnection == this && !this.autoCommit && !this.transactionIsIdle()) {
         Util.logAndThrowSQLException(newSQLException("25001"));
      }

   }

   protected void close(StandardException var1) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         if (this.rootConnection == this && (this.active || this.isAborting())) {
            if (this.tr.isActive()) {
               this.setupContextStack();

               try {
                  this.tr.rollback();
                  InterruptStatus.restoreIntrFlagIfSeen(this.tr.getLcc());
                  this.tr.clearLcc();
                  this.tr.cleanupOnError(var1, false);
               } catch (Throwable var9) {
                  throw this.handleException(var9);
               } finally {
                  this.restoreContextStack();
               }
            } else {
               InterruptStatus.restoreIntrFlagIfSeen();
               this.tr.clearLcc();
               this.tr.cleanupOnError(var1, false);
            }
         }

         this.aborting = false;
         if (!this.isClosed()) {
            this.setInactive();
         }

      }
   }

   public final boolean isClosed() {
      return !this.active || !this.getTR().isActive();
   }

   public DatabaseMetaData getMetaData() throws SQLException {
      this.checkIfClosed();
      if (this.dbMetadata == null) {
         this.dbMetadata = this.factory.newEmbedDatabaseMetaData(this, this.getTR().getUrl());
      }

      return this.dbMetadata;
   }

   public final int getHoldability() throws SQLException {
      this.checkIfClosed();
      return this.connectionHoldAbility;
   }

   public final void setHoldability(int var1) throws SQLException {
      this.checkIfClosed();
      this.connectionHoldAbility = var1;
   }

   public final void setReadOnly(boolean var1) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            LanguageConnectionContext var3 = this.privilegedGetLCC();
            var3.setReadOnly(var1);
            InterruptStatus.restoreIntrFlagIfSeen(var3);
         } catch (StandardException var9) {
            throw this.handleException(var9);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   public final boolean isReadOnly() throws SQLException {
      this.checkIfClosed();
      return this.privilegedGetLCC().isReadOnly();
   }

   public void setCatalog(String var1) throws SQLException {
      this.checkIfClosed();
   }

   public String getCatalog() throws SQLException {
      this.checkIfClosed();
      return null;
   }

   public void setTransactionIsolation(int var1) throws SQLException {
      if (var1 != this.getTransactionIsolation()) {
         byte var2;
         switch (var1) {
            case 1:
               var2 = 1;
               break;
            case 2:
               var2 = 2;
               break;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               throw newSQLException("XJ045.S", var1);
            case 4:
               var2 = 3;
               break;
            case 8:
               var2 = 4;
         }

         synchronized(this.getConnectionSynchronization()) {
            this.setupContextStack();

            try {
               LanguageConnectionContext var4 = this.privilegedGetLCC();
               var4.setIsolationLevel(var2);
               InterruptStatus.restoreIntrFlagIfSeen(var4);
            } catch (StandardException var10) {
               throw this.handleException(var10);
            } finally {
               this.restoreContextStack();
            }

         }
      }
   }

   public final int getTransactionIsolation() throws SQLException {
      this.checkIfClosed();
      return TransactionControl.jdbcIsolationLevel(this.privilegedGetLCC().getCurrentIsolationLevel());
   }

   public final synchronized SQLWarning getWarnings() throws SQLException {
      this.checkIfClosed();
      return this.topWarning;
   }

   public final synchronized void clearWarnings() throws SQLException {
      this.checkIfClosed();
      this.topWarning = null;
   }

   public final void setTypeMap(Map var1) throws SQLException {
      this.checkIfClosed();
      if (var1 == null) {
         throw newSQLException("XJ081.S", var1, "map", "java.sql.Connection.setTypeMap");
      } else if (!var1.isEmpty()) {
         throw Util.notImplemented();
      }
   }

   public final synchronized void addWarning(SQLWarning var1) {
      if (this.topWarning == null) {
         this.topWarning = var1;
      } else {
         this.topWarning.setNextWarning(var1);
      }
   }

   public String getDBName() {
      return this.getTR().getDBName();
   }

   public final LanguageConnectionContext getLanguageConnection() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return this.privilegedGetLCC();
   }

   protected final void checkIfClosed() throws SQLException {
      if (this.isClosed()) {
         throw Util.noCurrentConnection();
      }
   }

   SQLException handleException(Throwable var1) throws SQLException {
      if (var1 instanceof StandardException && ((StandardException)var1).getSeverity() >= 30000) {
         this.clearLOBMapping();
      }

      return this.getTR().handleException(var1, this.autoCommit, true);
   }

   final SQLException handleException(Throwable var1, boolean var2) throws SQLException {
      if (var1 instanceof StandardException && ((StandardException)var1).getSeverity() >= 30000) {
         this.clearLOBMapping();
      }

      return this.getTR().handleException(var1, this.autoCommit, var2);
   }

   public final void setInactive() {
      if (this.active) {
         synchronized(this.getConnectionSynchronization()) {
            this.active = false;
            this.dbMetadata = null;
         }
      }
   }

   protected void finalize() throws Throwable {
      try {
         if (this.rootConnection == this) {
            this.close(exceptionClose);
         }
      } finally {
         super.finalize();
      }

   }

   protected void needCommit() {
      if (!this.needCommit) {
         this.needCommit = true;
      }

   }

   protected void commitIfNeeded() throws SQLException {
      if (this.autoCommit && this.needCommit) {
         try {
            this.getTR().commit();
            this.clearLOBMapping();
            InterruptStatus.restoreIntrFlagIfSeen(this.privilegedGetLCC());
         } catch (Throwable var2) {
            throw this.handleException(var2);
         }

         this.needCommit = false;
      }

   }

   protected void commitIfAutoCommit() throws SQLException {
      if (this.autoCommit) {
         try {
            this.getTR().commit();
            this.clearLOBMapping();
            InterruptStatus.restoreIntrFlagIfSeen(this.privilegedGetLCC());
         } catch (Throwable var2) {
            throw this.handleException(var2);
         }

         this.needCommit = false;
      }

   }

   protected final Object getConnectionSynchronization() {
      return this.rootConnection;
   }

   protected final void setupContextStack() throws SQLException {
      if (!this.isAborting()) {
         this.checkIfClosed();
      }

      this.getTR().setupContextStack();
   }

   protected final void restoreContextStack() throws SQLException {
      TransactionResourceImpl var1 = this.getTR();
      var1.restoreContextStack();
   }

   private Database createDatabase(String var1, Properties var2) throws SQLException {
      var2 = this.filterProperties(var2);

      try {
         if (createPersistentService("org.apache.derby.database.Database", var1, var2) == null) {
            this.addWarning(SQLWarningFactory.newSQLWarning("01J01", new Object[]{var1}));
         }
      } catch (StandardException var4) {
         throw Util.seeNextException("XJ041.C", this.handleException(var4), var4, var1);
      }

      var2.clear();
      return (Database)findService("org.apache.derby.database.Database", var1);
   }

   private void checkDatabaseCreatePrivileges(String var1, String var2) throws SQLException {
      if (System.getSecurityManager() != null) {
         if (var2 == null) {
            throw new NullPointerException("dbname can't be null");
         } else {
            try {
               String var3 = "directory:" + stripSubSubProtocolPrefix(var2);
               DatabasePermission var4 = new DatabasePermission(var3, "create");
               this.factory.checkSystemPrivileges(var1, var4);
            } catch (IOException var5) {
               throw newSQLException("08004.C.10", var2, var5);
            } catch (Exception var6) {
               throw newSQLException("08004.C.10", var2, var6);
            }
         }
      }
   }

   private static void sleep(long var0) {
      long var2 = System.currentTimeMillis();

      for(long var4 = 0L; var4 < var0; var4 = System.currentTimeMillis() - var2) {
         try {
            Thread.sleep(var0 - var4);
            break;
         } catch (InterruptedException var7) {
            InterruptStatus.setInterrupted();
         }
      }

   }

   public static String stripSubSubProtocolPrefix(String var0) {
      int var1 = var0.indexOf(58);
      if (var1 > 0) {
         String var10000 = var0.substring(0, var1);
         String var2 = "derby.subSubProtocol." + var10000;
         if (PropertyUtil.getSystemProperty(var2, (String)null) != null) {
            return var0.substring(var1 + 1);
         }
      }

      return var0;
   }

   private boolean bootDatabase(Properties var1, boolean var2) throws Throwable {
      String var3 = this.tr.getDBName();

      try {
         var1 = this.filterProperties(var1);
         if (var2) {
            var1.setProperty("softUpgradeNoFeatureCheck", "true");
         } else {
            var1.remove("softUpgradeNoFeatureCheck");
         }

         if (!startPersistentService(var3, var1)) {
            return false;
         } else {
            var1.clear();
            Database var4 = (Database)findService("org.apache.derby.database.Database", var3);
            this.tr.setDatabase(var4);
            return true;
         }
      } catch (StandardException var7) {
         Throwable var5 = var7.getCause();
         SQLException var6;
         if (var5 instanceof StandardException) {
            var6 = Util.generateCsSQLException((StandardException)var5);
         } else if (var5 != null) {
            var6 = Util.javaException(var5);
         } else {
            var6 = Util.generateCsSQLException(var7);
         }

         throw Util.seeNextException("XJ040.C", var6, (Throwable)(var5 == null ? var7 : var5), var3, this.getClass().getClassLoader());
      }
   }

   PreparedStatement prepareMetaDataStatement(String var1) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();
         Object var3 = null;

         try {
            var10 = this.factory.newEmbedPreparedStatement(this, var1, true, 1003, 1007, this.connectionHoldAbility, 2, (int[])null, (String[])null);
         } finally {
            InterruptStatus.restoreIntrFlagIfSeen(this.privilegedGetLCC());
            this.restoreContextStack();
         }

         return var10;
      }
   }

   public final InternalDriver getLocalDriver() {
      return this.getTR().getDriver();
   }

   public final ContextManager getContextManager() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return this.getTR().getContextManager();
   }

   private Properties filterProperties(Properties var1) {
      Properties var2 = new Properties();
      Enumeration var3 = var1.propertyNames();

      while(var3.hasMoreElements()) {
         String var4 = (String)var3.nextElement();
         if (!var4.startsWith("derby.")) {
            var2.put(var4, var1.getProperty(var4));
         }
      }

      return var2;
   }

   protected Database getDatabase() {
      return this.getTR().getDatabase();
   }

   protected final TransactionResourceImpl getTR() {
      return this.rootConnection.tr;
   }

   private EmbedConnectionContext pushConnectionContext(ContextManager var1) {
      return new EmbedConnectionContext(var1, this);
   }

   public final void setApplicationConnection(Connection var1) {
      this.applicationConnection = var1;
   }

   public final Connection getApplicationConnection() {
      return this.applicationConnection;
   }

   public void setDrdaID(String var1) {
      this.privilegedGetLCC().setDrdaID(var1);
   }

   public boolean isInGlobalTransaction() {
      return false;
   }

   public void resetFromPool() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            LanguageConnectionContext var2 = this.privilegedGetLCC();
            var2.resetFromPool();
            InterruptStatus.restoreIntrFlagIfSeen(var2);
         } catch (StandardException var8) {
            throw this.handleException(var8);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   public final int xa_prepare() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         int var5;
         try {
            LanguageConnectionContext var2 = this.privilegedGetLCC();
            XATransactionController var3 = (XATransactionController)var2.getTransactionExecute();

            try {
               var2.checkIntegrity();
            } catch (StandardException var12) {
               var2.xaRollback();
               throw var12;
            }

            int var4 = var3.xa_prepare();
            if (var4 == 1) {
               var2.internalCommit(false);
            }

            InterruptStatus.restoreIntrFlagIfSeen(var2);
            var5 = var4;
         } catch (StandardException var13) {
            throw this.handleException(var13);
         } finally {
            this.restoreContextStack();
         }

         return var5;
      }
   }

   public final void xa_commit(boolean var1) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            LanguageConnectionContext var3 = this.privilegedGetLCC();
            var3.xaCommit(var1);
            InterruptStatus.restoreIntrFlagIfSeen(var3);
         } catch (StandardException var9) {
            throw this.handleException(var9);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   public final void xa_rollback() throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            LanguageConnectionContext var2 = this.privilegedGetLCC();
            var2.xaRollback();
            InterruptStatus.restoreIntrFlagIfSeen(var2);
         } catch (StandardException var8) {
            throw this.handleException(var8);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   public final boolean transactionIsIdle() {
      return this.getTR().isIdle();
   }

   private int setResultSetType(int var1) {
      if (var1 == 1005) {
         this.addWarning(SQLWarningFactory.newSQLWarning("01J02", new Object[0]));
         var1 = 1004;
      }

      return var1;
   }

   public void setPrepareIsolation(int var1) throws SQLException {
      if (var1 != this.getPrepareIsolation()) {
         switch (var1) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
               synchronized(this.getConnectionSynchronization()) {
                  this.privilegedGetLCC().setPrepareIsolationLevel(var1);
                  return;
               }
            default:
               throw newSQLException("XJ045.S", var1);
         }
      }
   }

   public int getPrepareIsolation() {
      return this.privilegedGetLCC().getPrepareIsolationLevel();
   }

   final int getResultSetOrderId() {
      return this == this.rootConnection ? 0 : this.rootConnection.resultSetId++;
   }

   static SQLException newSQLException(String var0, Object... var1) {
      return Util.generateCsSQLException(var0, var1);
   }

   public String toString() {
      if (this.connString == null) {
         LanguageConnectionContext var1 = this.privilegedGetLCC();
         String var10001 = this.getClass().getName();
         this.connString = var10001 + "@" + this.hashCode() + " " + "(XID = " + var1.getTransactionExecute().getTransactionIdString() + "), " + "(SESSIONID = " + Integer.toString(var1.getInstanceNumber()) + "), " + "(DATABASE = " + var1.getDbname() + "), " + "(DRDAID = " + var1.getDrdaID() + ") ";
      }

      return this.connString;
   }

   public Clob createClob() throws SQLException {
      this.checkIfClosed();
      return new EmbedClob(this);
   }

   public Blob createBlob() throws SQLException {
      this.checkIfClosed();
      return new EmbedBlob(new byte[0], this);
   }

   public int addLOBMapping(Object var1) {
      int var2 = this.getIncLOBKey();
      this.getlobHMObj().put(var2, var1);
      return var2;
   }

   public void removeLOBMapping(int var1) {
      this.getlobHMObj().remove(var1);
   }

   public Object getLOBMapping(int var1) {
      return this.getlobHMObj().get(var1);
   }

   private void clearLOBMapping() throws SQLException {
      WeakHashMap var1 = this.rootConnection.lobReferences;
      if (var1 != null) {
         Iterator var2 = var1.keySet().iterator();

         while(var2.hasNext()) {
            ((EngineLOB)var2.next()).free();
         }

         var1.clear();
      }

      if (this.rootConnection.lobHashMap != null) {
         this.rootConnection.lobHashMap.clear();
      }

      synchronized(this) {
         if (this.lobFiles != null) {
            SQLException var3 = null;
            Iterator var4 = this.lobFiles.iterator();

            while(var4.hasNext()) {
               try {
                  ((LOBFile)var4.next()).close();
               } catch (IOException var7) {
                  if (var3 == null) {
                     var3 = Util.javaException(var7);
                  }
               }
            }

            this.lobFiles.clear();
            if (var3 != null) {
               throw var3;
            }
         }

      }
   }

   private int getIncLOBKey() {
      int var1 = ++this.rootConnection.lobHMKey;
      if (var1 == 32768 || var1 == 32770 || var1 == 32772 || var1 == 32774 || var1 == 32776) {
         var1 = ++this.rootConnection.lobHMKey;
      }

      if (var1 == Integer.MIN_VALUE || var1 == 0) {
         var1 = this.rootConnection.lobHMKey = 1;
      }

      return var1;
   }

   void addLOBReference(Object var1) {
      if (this.rootConnection.lobReferences == null) {
         this.rootConnection.lobReferences = new WeakHashMap();
      }

      this.rootConnection.lobReferences.put(var1, (Object)null);
   }

   private HashMap getlobHMObj() {
      if (this.rootConnection.lobHashMap == null) {
         this.rootConnection.lobHashMap = new HashMap();
      }

      return this.rootConnection.lobHashMap;
   }

   public void cancelRunningStatement() {
      this.privilegedGetLCC().getStatementContext().cancel();
   }

   public String getCurrentSchemaName() {
      return this.privilegedGetLCC().getCurrentSchemaName();
   }

   void addLobFile(LOBFile var1) {
      synchronized(this) {
         if (this.lobFiles == null) {
            this.lobFiles = new HashSet();
         }

         this.lobFiles.add(var1);
      }
   }

   void removeLobFile(LOBFile var1) {
      synchronized(this) {
         this.lobFiles.remove(var1);
      }
   }

   public boolean isAborting() {
      return this.aborting;
   }

   protected void beginAborting() {
      this.aborting = true;
      this.setInactive();
   }

   public Savepoint setSavepoint() throws SQLException {
      return this.commonSetSavepointCode((String)null, false);
   }

   public Savepoint setSavepoint(String var1) throws SQLException {
      return this.commonSetSavepointCode(var1, true);
   }

   private Savepoint commonSetSavepointCode(String var1, boolean var2) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         EmbedSavepoint var5;
         try {
            this.verifySavepointCommandIsAllowed();
            if (var2 && var1 == null) {
               throw newSQLException("XJ011.S");
            }

            if (var2 && var1.length() > 128) {
               throw newSQLException("42622", var1, String.valueOf(128));
            }

            if (var2 && var1.startsWith("SYS")) {
               throw newSQLException("42939", "SYS");
            }

            EmbedSavepoint var4 = new EmbedSavepoint(this, var1);
            var5 = var4;
         } catch (StandardException var11) {
            throw this.handleException(var11);
         } finally {
            this.restoreContextStack();
         }

         return var5;
      }
   }

   public void rollback(Savepoint var1) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            this.verifySavepointCommandIsAllowed();
            this.verifySavepointArg(var1);
            this.privilegedGetLCC().internalRollbackToSavepoint(((EmbedSavepoint)var1).getInternalName(), true, var1);
         } catch (StandardException var9) {
            throw this.handleException(var9);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   public void releaseSavepoint(Savepoint var1) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         try {
            this.verifySavepointCommandIsAllowed();
            this.verifySavepointArg(var1);
            this.privilegedGetLCC().releaseSavePoint(((EmbedSavepoint)var1).getInternalName(), var1);
         } catch (StandardException var9) {
            throw this.handleException(var9);
         } finally {
            this.restoreContextStack();
         }

      }
   }

   private void verifySavepointCommandIsAllowed() throws SQLException {
      if (this.autoCommit) {
         throw newSQLException("XJ010.S");
      } else {
         StatementContext var1 = this.privilegedGetLCC().getStatementContext();
         if (var1 != null && var1.inTrigger()) {
            throw newSQLException("XJ017.S");
         }
      }
   }

   private void verifySavepointArg(Savepoint var1) throws SQLException {
      EmbedSavepoint var2 = (EmbedSavepoint)var1;
      if (var2 == null) {
         throw newSQLException("3B001.S", "null");
      } else if (!var2.sameConnection(this)) {
         throw newSQLException("3B502.S");
      }
   }

   public String getSchema() throws SQLException {
      this.checkIfClosed();
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();

         String var3;
         try {
            LanguageConnectionContext var2 = this.privilegedGetLCC();
            var3 = var2.getCurrentSchemaName();
         } finally {
            this.restoreContextStack();
         }

         return var3;
      }
   }

   public void setSchema(String var1) throws SQLException {
      this.checkIfClosed();
      PreparedStatement var2 = null;

      try {
         var2 = this.prepareStatement("set schema ?");
         var2.setString(1, var1);
         var2.execute();
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

   }

   private void checkConflictingCryptoAttributes(Properties var1) throws SQLException {
      boolean var2 = isSet(var1, "encryptionKey") || isSet(var1, "bootPassword");
      if (var2 && isTrue(var1, "decryptDatabase")) {
         if (isSet(var1, "newBootPassword")) {
            throw newSQLException("XJ048.C", "decryptDatabase, newBootPassword");
         }

         if (isSet(var1, "newEncryptionKey")) {
            throw newSQLException("XJ048.C", "decryptDatabase, newEncryptionKey");
         }
      }

   }

   public Array createArrayOf(String var1, Object[] var2) throws SQLException {
      throw Util.notImplemented();
   }

   public NClob createNClob() throws SQLException {
      throw Util.notImplemented();
   }

   public SQLXML createSQLXML() throws SQLException {
      throw Util.notImplemented();
   }

   public Struct createStruct(String var1, Object[] var2) throws SQLException {
      throw Util.notImplemented();
   }

   public boolean isValid(int var1) throws SQLException {
      if (var1 < 0) {
         throw newSQLException("XJ081.S", var1, "timeout", "java.sql.Connection.isValid");
      } else {
         return !this.isClosed();
      }
   }

   public void setClientInfo(String var1, String var2) throws SQLClientInfoException {
      Properties var3 = FailedProperties40.makeProperties(var1, var2);

      try {
         this.checkIfClosed();
      } catch (SQLException var6) {
         FailedProperties40 var5 = new FailedProperties40(var3);
         throw new SQLClientInfoException(var6.getMessage(), var6.getSQLState(), var6.getErrorCode(), var5.getProperties());
      }

      if (var1 != null || var2 != null) {
         this.setClientInfo(var3);
      }
   }

   public void setClientInfo(Properties var1) throws SQLClientInfoException {
      FailedProperties40 var2 = new FailedProperties40(var1);

      try {
         this.checkIfClosed();
      } catch (SQLException var4) {
         throw new SQLClientInfoException(var4.getMessage(), var4.getSQLState(), var4.getErrorCode(), var2.getProperties());
      }

      if (var1 != null && !var1.isEmpty()) {
         StandardException var3 = StandardException.newException("XCY02.S", new Object[]{var2.getFirstKey(), var2.getFirstValue()});
         throw new SQLClientInfoException(var3.getMessage(), var3.getSQLState(), var3.getErrorCode(), var2.getProperties());
      }
   }

   public String getClientInfo(String var1) throws SQLException {
      this.checkIfClosed();
      return null;
   }

   public Properties getClientInfo() throws SQLException {
      this.checkIfClosed();
      return new Properties();
   }

   public final Map getTypeMap() throws SQLException {
      this.checkIfClosed();
      return Collections.emptyMap();
   }

   public boolean isWrapperFor(Class var1) throws SQLException {
      this.checkIfClosed();
      return var1.isInstance(this);
   }

   public Object unwrap(Class var1) throws SQLException {
      this.checkIfClosed();

      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw newSQLException("XJ128.S", var1);
      }
   }

   public void abort(Executor var1) throws SQLException {
      if (!this.isClosed()) {
         if (var1 == null) {
            throw newSQLException("XCZ02.S", "executor", "null");
         } else {
            this.beginAborting();
            var1.execute(new Runnable() {
               public void run() {
                  try {
                     EmbedConnection.this.rollback();
                     EmbedConnection.this.close(EmbedConnection.exceptionClose);
                  } catch (SQLException var2) {
                     Util.logSQLException(var2);
                  }

               }
            });
         }
      }
   }

   public int getNetworkTimeout() throws SQLException {
      throw Util.notImplemented();
   }

   public void setNetworkTimeout(Executor var1, int var2) throws SQLException {
      throw Util.notImplemented();
   }

   static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object findService(String var0, String var1) {
      return Monitor.findService(var0, var1);
   }

   private static boolean startPersistentService(String var0, Properties var1) throws StandardException {
      return Monitor.startPersistentService(var0, var1);
   }

   private static Object createPersistentService(String var0, String var1, Properties var2) throws StandardException {
      return Monitor.createPersistentService(var0, var1, var2);
   }

   private static void removePersistentService(String var0) throws StandardException {
      Monitor.removePersistentService(var0);
   }

   private LanguageConnectionContext privilegedGetLCC() {
      return this.getTR().getLcc();
   }
}
