package org.apache.derby.impl.sql.conn;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Properties;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.services.cache.CacheFactory;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;
import org.apache.derby.iapi.services.compiler.JavaFactory;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.services.property.PropertySetCallback;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.Statement;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.sql.GenericStatement;
import org.apache.derby.impl.sql.compile.ParserImpl;
import org.apache.derby.shared.common.error.StandardException;

public class GenericLanguageConnectionFactory implements LanguageConnectionFactory, CacheableFactory, PropertySetCallback, ModuleControl, ModuleSupportable {
   private ExecutionFactory ef;
   private OptimizerFactory of;
   private TypeCompilerFactory tcf;
   private DataValueFactory dvf;
   private UUIDFactory uuidFactory;
   private JavaFactory javaFactory;
   private ClassFactory classFactory;
   private PropertyFactory pf;
   private int nextLCCInstanceNumber;
   private int cacheSize = 100;
   private CacheManager singleStatementCache;

   public Statement getStatement(SchemaDescriptor var1, String var2, boolean var3) {
      return new GenericStatement(var1, var2, var3);
   }

   public LanguageConnectionContext newLanguageConnectionContext(ContextManager var1, TransactionController var2, LanguageFactory var3, Database var4, String var5, String var6, String var7) throws StandardException {
      return new GenericLanguageConnectionContext(var1, var2, var3, this, var4, var5, this.getNextLCCInstanceNumber(), var6, var7);
   }

   public Cacheable newCacheable(CacheManager var1) {
      return new CachedStatement();
   }

   public UUIDFactory getUUIDFactory() {
      return this.uuidFactory;
   }

   public ClassFactory getClassFactory() {
      return this.classFactory;
   }

   public JavaFactory getJavaFactory() {
      return this.javaFactory;
   }

   public ExecutionFactory getExecutionFactory() {
      return this.ef;
   }

   public PropertyFactory getPropertyFactory() {
      return this.pf;
   }

   public OptimizerFactory getOptimizerFactory() {
      return this.of;
   }

   public TypeCompilerFactory getTypeCompilerFactory() {
      return this.tcf;
   }

   public DataValueFactory getDataValueFactory() {
      return this.dvf;
   }

   public boolean canSupport(Properties var1) {
      return Monitor.isDesiredType(var1, 130);
   }

   private int statementCacheSize(Properties var1) {
      Object var2 = null;
      String var5 = PropertyUtil.getPropertyFromSet(var1, "derby.language.statementCacheSize");
      if (var5 != null) {
         try {
            this.cacheSize = Integer.parseInt(var5);
         } catch (NumberFormatException var4) {
            this.cacheSize = 100;
         }
      }

      return this.cacheSize;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.dvf = (DataValueFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.types.DataValueFactory", var2);
      this.javaFactory = (JavaFactory)startSystemModule("org.apache.derby.iapi.services.compiler.JavaFactory");
      this.uuidFactory = getMonitor().getUUIDFactory();
      this.classFactory = (ClassFactory)getServiceModule(this, "org.apache.derby.iapi.services.loader.ClassFactory");
      if (this.classFactory == null) {
         this.classFactory = (ClassFactory)findSystemModule("org.apache.derby.iapi.services.loader.ClassFactory");
      }

      this.setValidation();
      this.ef = (ExecutionFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.execute.ExecutionFactory", var2);
      this.of = (OptimizerFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.sql.compile.OptimizerFactory", var2);
      this.tcf = (TypeCompilerFactory)startSystemModule("org.apache.derby.iapi.sql.compile.TypeCompilerFactory");
      int var3 = this.statementCacheSize(var2);
      if (var3 > 0) {
         CacheFactory var4 = (CacheFactory)startSystemModule("org.apache.derby.iapi.services.cache.CacheFactory");
         this.singleStatementCache = var4.newCacheManager(this, "StatementCache", var3 / 4, var3);
         DataFactory var5 = (DataFactory)findServiceModule(this, "org.apache.derby.iapi.store.raw.data.DataFactory");
         this.singleStatementCache.registerMBean(var5.getRootDirectory());
      }

   }

   public CacheManager getStatementCache() {
      return this.singleStatementCache;
   }

   public void stop() {
      if (this.singleStatementCache != null) {
         this.singleStatementCache.deregisterMBean();
      }

   }

   public void init(boolean var1, Dictionary var2) {
   }

   public boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var2 == null) {
         return true;
      } else if (var1.equals("derby.database.defaultConnectionMode")) {
         String var11 = (String)var2;
         if (var11 != null && !StringUtil.SQLEqualsIgnoreCase(var11, "NOACCESS") && !StringUtil.SQLEqualsIgnoreCase(var11, "READONLYACCESS") && !StringUtil.SQLEqualsIgnoreCase(var11, "FULLACCESS")) {
            throw StandardException.newException("4250B", new Object[]{var1, var11});
         } else {
            return true;
         }
      } else if (!var1.equals("derby.database.readOnlyAccessUsers") && !var1.equals("derby.database.fullAccessUsers")) {
         return false;
      } else {
         String var4 = (String)var2;

         String[] var5;
         try {
            var5 = IdUtil.parseIdList(var4);
         } catch (StandardException var10) {
            throw StandardException.newException("4250B", var10, new Object[]{var1, var4});
         }

         String var6 = IdUtil.dups(var5);
         if (var6 != null) {
            throw StandardException.newException("4250D", new Object[]{var1, var6});
         } else {
            String var8;
            if (var1.equals("derby.database.readOnlyAccessUsers")) {
               var8 = (String)var3.get("derby.database.fullAccessUsers");
            } else {
               var8 = (String)var3.get("derby.database.readOnlyAccessUsers");
            }

            String[] var7 = IdUtil.parseIdList(var8);
            String var9 = IdUtil.intersect(var5, var7);
            if (var9 != null) {
               throw StandardException.newException("4250C", new Object[]{var9});
            } else {
               return true;
            }
         }
      }
   }

   public Serviceable apply(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   public Serializable map(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   protected void setValidation() throws StandardException {
      this.pf = (PropertyFactory)findServiceModule(this, "org.apache.derby.iapi.services.property.PropertyFactory");
      this.pf.addPropertySetNotification(this);
   }

   public Parser newParser(CompilerContext var1) {
      return new ParserImpl(var1);
   }

   protected synchronized int getNextLCCInstanceNumber() {
      return this.nextLCCInstanceNumber++;
   }

   static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object startSystemModule(String var0) throws StandardException {
      return Monitor.startSystemModule(var0);
   }

   private static Object findSystemModule(String var0) throws StandardException {
      return Monitor.findSystemModule(var0);
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }

   private static Object getServiceModule(Object var0, String var1) {
      return Monitor.getServiceModule(var0, var1);
   }
}
