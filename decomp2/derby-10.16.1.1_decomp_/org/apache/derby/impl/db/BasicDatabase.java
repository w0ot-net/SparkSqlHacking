package org.apache.derby.impl.db;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Dictionary;
import java.util.Locale;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.jdbc.AuthenticationService;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.JarReader;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.services.property.PropertySetCallback;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.FileInfoDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.util.DoubleProperties;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.sql.execute.JarUtil;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class BasicDatabase implements ModuleControl, ModuleSupportable, PropertySetCallback, Database, JarReader {
   protected boolean active;
   private AuthenticationService authenticationService;
   protected AccessFactory af;
   protected PropertyFactory pf;
   protected ClassFactory cfDB;
   private DataDictionary dd;
   protected LanguageConnectionFactory lcf;
   protected LanguageFactory lf;
   protected Object resourceAdapter;
   private Locale databaseLocale;
   private DateFormat dateFormat;
   private DateFormat timeFormat;
   private DateFormat timestampFormat;
   private UUID myUUID;
   protected boolean lastToBoot = true;

   public boolean canSupport(Properties var1) {
      boolean var2 = Monitor.isDesiredCreateType(var1, this.getEngineType());
      if (var2) {
         String var3 = var1.getProperty("replication.slave.mode");
         if (var3 != null && !var3.equals("slavepremode")) {
            var2 = false;
         }
      }

      return var2;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      ModuleFactory var3 = getMonitor();
      if (var1) {
         if (var2.getProperty("derby.__rt.storage.createWithNoLog") == null) {
            var2.put("derby.__rt.storage.createWithNoLog", "true");
         }

         String var4 = var2.getProperty("territory");
         if (var4 == null) {
            var4 = Locale.getDefault().toString();
         }

         this.databaseLocale = var3.setLocale(var2, var4);
      } else {
         this.databaseLocale = var3.getLocale(this);
      }

      this.setLocale(this.databaseLocale);
      this.bootValidation(var1, var2);
      DataValueFactory var6 = (DataValueFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.types.DataValueFactory", var2);
      this.bootStore(var1, var2);
      this.myUUID = this.makeDatabaseID(var1, var2);
      DoubleProperties var5 = new DoubleProperties(this.getAllDatabaseProperties(), var2);
      if (this.pf != null) {
         this.pf.addPropertySetNotification(this);
      }

      this.bootClassFactory(var1, var5);
      this.dd = (DataDictionary)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.dictionary.DataDictionary", var5);
      this.lcf = (LanguageConnectionFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.conn.LanguageConnectionFactory", var5);
      this.lf = (LanguageFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.LanguageFactory", var5);
      this.bootResourceAdapter(var1, var5);
      this.authenticationService = this.bootAuthenticationService(var1, var5);
      if (var1 && this.lastToBoot && var2.getProperty("derby.__rt.storage.createWithNoLog") != null) {
         this.createFinished();
      }

      this.active = true;
      if (this.dd.doCreateIndexStatsRefresher()) {
         this.dd.createIndexStatsRefresher(this, ((Properties)var5).getProperty("derby.__rt.serviceDirectory"));
      }

   }

   public void stop() {
      if (this.dd != null) {
         try {
            this.dd.clearSequenceCaches();
         } catch (StandardException var2) {
            var2.printStackTrace(Monitor.getStream().getPrintWriter());
         }
      }

      this.active = false;
   }

   public int getEngineType() {
      return 2;
   }

   public boolean isReadOnly() {
      return this.af.isReadOnly();
   }

   public LanguageConnectionContext setupConnection(ContextManager var1, String var2, String var3, String var4) throws StandardException {
      TransactionController var5 = this.getConnectionTransaction(var1);
      var1.setLocaleFinder(this);
      this.pushDbContext(var1);
      LanguageConnectionContext var6 = this.lcf.newLanguageConnectionContext(var1, var5, this.lf, this, var2, var3, var4);
      this.pushClassFactoryContext(var1, this.lcf.getClassFactory());
      ExecutionFactory var7 = this.lcf.getExecutionFactory();
      var7.newExecutionContext(var1);
      var6.initialize();
      var6.internalCommitNoSync(5);
      return var6;
   }

   public final DataDictionary getDataDictionary() {
      return this.dd;
   }

   public void pushDbContext(ContextManager var1) {
      new DatabaseContextImpl(var1, this);
   }

   public AuthenticationService getAuthenticationService() throws StandardException {
      return this.authenticationService;
   }

   public void startReplicationMaster(String var1, String var2, int var3, String var4) throws SQLException {
      try {
         this.af.startReplicationMaster(var1, var2, var3, var4);
      } catch (StandardException var6) {
         throw PublicAPI.wrapStandardException(var6);
      }
   }

   public void stopReplicationMaster() throws SQLException {
      try {
         this.af.stopReplicationMaster();
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public void stopReplicationSlave() throws SQLException {
      StandardException var1 = StandardException.newException("XRE40", new Object[0]);
      throw PublicAPI.wrapStandardException(var1);
   }

   public boolean isInSlaveMode() {
      return false;
   }

   public void failover(String var1) throws StandardException {
      this.af.failover(var1);
   }

   public void freeze() throws SQLException {
      try {
         this.af.freeze();
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public void unfreeze() throws SQLException {
      try {
         this.af.unfreeze();
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public void backup(String var1, boolean var2) throws SQLException {
      try {
         this.af.backup(var1, var2);
         if (this.luceneLoaded()) {
            this.backupLucene(var1);
         }

      } catch (StandardException var4) {
         throw PublicAPI.wrapStandardException(var4);
      }
   }

   public void backupAndEnableLogArchiveMode(String var1, boolean var2, boolean var3) throws SQLException {
      try {
         this.af.backupAndEnableLogArchiveMode(var1, var2, var3);
         if (this.luceneLoaded()) {
            this.backupLucene(var1);
         }

      } catch (StandardException var5) {
         throw PublicAPI.wrapStandardException(var5);
      }
   }

   public void disableLogArchiveMode(boolean var1) throws SQLException {
      try {
         this.af.disableLogArchiveMode(var1);
      } catch (StandardException var3) {
         throw PublicAPI.wrapStandardException(var3);
      }
   }

   public void checkpoint() throws SQLException {
      try {
         this.af.checkpoint();
      } catch (StandardException var2) {
         throw PublicAPI.wrapStandardException(var2);
      }
   }

   public Locale getLocale() {
      return this.databaseLocale;
   }

   /** @deprecated */
   public final UUID getId() {
      return this.myUUID;
   }

   public Locale getCurrentLocale() throws StandardException {
      if (this.databaseLocale != null) {
         return this.databaseLocale;
      } else {
         throw noLocale();
      }
   }

   public DateFormat getDateFormat() throws StandardException {
      if (this.databaseLocale != null) {
         if (this.dateFormat == null) {
            this.dateFormat = DateFormat.getDateInstance(1, this.databaseLocale);
         }

         return this.dateFormat;
      } else {
         throw noLocale();
      }
   }

   public DateFormat getTimeFormat() throws StandardException {
      if (this.databaseLocale != null) {
         if (this.timeFormat == null) {
            this.timeFormat = DateFormat.getTimeInstance(1, this.databaseLocale);
         }

         return this.timeFormat;
      } else {
         throw noLocale();
      }
   }

   public DateFormat getTimestampFormat() throws StandardException {
      if (this.databaseLocale != null) {
         if (this.timestampFormat == null) {
            this.timestampFormat = DateFormat.getDateTimeInstance(1, 1, this.databaseLocale);
         }

         return this.timestampFormat;
      } else {
         throw noLocale();
      }
   }

   private static StandardException noLocale() {
      return StandardException.newException("XCXE0.S", new Object[0]);
   }

   public void setLocale(Locale var1) {
      this.databaseLocale = var1;
      this.dateFormat = null;
      this.timeFormat = null;
      this.timestampFormat = null;
   }

   public boolean isActive() {
      return this.active;
   }

   protected UUID makeDatabaseID(boolean var1, Properties var2) throws StandardException {
      TransactionController var3 = this.af.getTransaction(getContextService().getCurrentContextManager());
      String var4 = null;
      UUID var5;
      if ((var5 = (UUID)var3.getProperty("derby.databaseID")) == null) {
         UUIDFactory var6 = getMonitor().getUUIDFactory();
         var4 = var2.getProperty("derby.databaseID");
         if (var4 == null) {
            var5 = var6.createUUID();
         } else {
            var5 = var6.recreateUUID(var4);
         }

         var3.setProperty("derby.databaseID", var5, true);
      }

      if (var4 != null) {
         var2.remove("derby.databaseID");
      }

      var3.commit();
      var3.destroy();
      return var5;
   }

   public Object getResourceAdapter() {
      return this.resourceAdapter;
   }

   public void init(boolean var1, Dictionary var2) {
   }

   public boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var1.equals("derby.engineType")) {
         throw StandardException.newException("XCY02.S", new Object[]{var1, var2});
      } else if (!var1.equals("derby.database.classpath")) {
         return false;
      } else {
         String var4 = (String)var2;
         String[][] var5 = null;
         if (var4 != null) {
            var5 = IdUtil.parseDbClassPath(var4);
         }

         if (var5 != null) {
            for(int var6 = 0; var6 < var5.length; ++var6) {
               SchemaDescriptor var7 = this.dd.getSchemaDescriptor(var5[var6][0], (TransactionController)null, false);
               FileInfoDescriptor var8 = null;
               if (var7 != null) {
                  var8 = this.dd.getFileInfoDescriptor(var7, var5[var6][1]);
               }

               if (var8 == null) {
                  throw StandardException.newException("42X96", new Object[]{IdUtil.mkQualifiedName(var5[var6])});
               }
            }
         }

         return true;
      }
   }

   public Serviceable apply(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (!var1.equals("derby.database.classpath")) {
         return null;
      } else {
         if (this.cfDB != null) {
            this.getDataDictionary().invalidateAllSPSPlans();
            String var4 = (String)var2;
            if (var4 == null) {
               var4 = "";
            }

            this.cfDB.notifyModifyClasspath(var4);
         }

         return null;
      }
   }

   public Serializable map(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   protected void createFinished() throws StandardException {
      this.af.createFinished();
   }

   protected String getClasspath(Properties var1) {
      String var2 = PropertyUtil.getPropertyFromSet(var1, "derby.database.classpath");
      if (var2 == null) {
         var2 = PropertyUtil.getSystemProperty("derby.database.classpath", "");
      }

      return var2;
   }

   protected void bootClassFactory(boolean var1, Properties var2) throws StandardException {
      String var3 = this.getClasspath(var2);
      IdUtil.parseDbClassPath(var3);
      var2.put("derby.__rt.database.classpath", var3);
      this.cfDB = (ClassFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.services.loader.ClassFactory", var2);
   }

   protected TransactionController getConnectionTransaction(ContextManager var1) throws StandardException {
      return this.af.getTransaction(var1);
   }

   protected AuthenticationService bootAuthenticationService(boolean var1, Properties var2) throws StandardException {
      return (AuthenticationService)bootServiceModule(var1, this, "org.apache.derby.iapi.jdbc.AuthenticationService", var2);
   }

   protected void bootValidation(boolean var1, Properties var2) throws StandardException {
      this.pf = (PropertyFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.services.property.PropertyFactory", var2);
   }

   protected void bootStore(boolean var1, Properties var2) throws StandardException {
      this.af = (AccessFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.store.access.AccessFactory", var2);
   }

   protected Properties getAllDatabaseProperties() throws StandardException {
      TransactionController var1 = this.af.getTransaction(getContextService().getCurrentContextManager());
      Properties var2 = var1.getProperties();
      var1.commit();
      var1.destroy();
      return var2;
   }

   protected void bootResourceAdapter(boolean var1, Properties var2) {
      try {
         this.resourceAdapter = bootServiceModule(var1, this, "org.apache.derby.iapi.jdbc.ResourceAdapter", var2);
      } catch (StandardException var4) {
      }

   }

   protected void pushClassFactoryContext(ContextManager var1, ClassFactory var2) {
      new StoreClassFactoryContext(var1, var2, this.af, this);
   }

   public StorageFile getJarFile(String var1, String var2) throws StandardException {
      SchemaDescriptor var3 = this.dd.getSchemaDescriptor(var1, (TransactionController)null, true);
      FileInfoDescriptor var4 = this.dd.getFileInfoDescriptor(var3, var2);
      if (var4 == null) {
         throw StandardException.newException("X0X13.S", new Object[]{var2, var1});
      } else {
         long var5 = var4.getGenerationId();
         ContextManager var7 = getContextService().getCurrentContextManager();
         FileResource var8 = this.af.getTransaction(var7).getFileHandler();
         String var9 = JarUtil.mkExternalName(var4.getUUID(), var1, var2, var8.getSeparatorChar());
         return var8.getAsFile(var9, var5);
      }
   }

   private boolean luceneLoaded() throws StandardException {
      return this.getLuceneDir().exists();
   }

   private StorageFile getLuceneDir() throws StandardException {
      StorageFactory var1 = this.getStorageFactory();
      return var1.newStorageFile("LUCENE");
   }

   private StorageFactory getStorageFactory() throws StandardException {
      DataFactory var1 = (DataFactory)findServiceModule(this, "org.apache.derby.iapi.store.raw.data.DataFactory");
      return var1.getStorageFactory();
   }

   private void backupLucene(String var1) throws StandardException {
      try {
         File var2 = new File(var1);
         StorageFactory var3 = this.getStorageFactory();
         String var4 = var3.getCanonicalName();
         String var5 = StringUtil.shortDBName(var4, var3.getSeparator());
         File var6 = new File(var2, var5);
         File var7 = new File(var6, "LUCENE");
         StorageFile var8 = this.getLuceneDir();
         if (!FileUtil.copyDirectory(this.getStorageFactory(), var8, var7, (byte[])null, (String[])null, true)) {
            throw StandardException.newException("XBM0Z.D", new Object[]{var8.getPath(), var7.getAbsolutePath()});
         }
      } catch (IOException var9) {
         throw StandardException.plainWrapException(var9);
      }
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
