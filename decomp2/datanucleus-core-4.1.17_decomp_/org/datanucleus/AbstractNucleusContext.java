package org.datanucleus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.api.ApiAdapterFactory;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.properties.CorePropertyValidator;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.store.types.TypeManagerImpl;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractNucleusContext implements NucleusContext {
   protected final Configuration config;
   protected final PluginManager pluginManager;
   protected MetaDataManager metaDataManager = null;
   protected final ApiAdapter apiAdapter;
   protected TypeManager typeManager;
   protected final String classLoaderResolverClassName;
   protected transient Map classLoaderResolverMap = new HashMap();
   public static final Set STARTUP_PROPERTIES = new HashSet();

   public AbstractNucleusContext(String apiName, Map startupProps, PluginManager pluginMgr) {
      if (pluginMgr != null) {
         this.pluginManager = pluginMgr;
      } else {
         this.pluginManager = PluginManager.createPluginManager(startupProps, this.getClass().getClassLoader());
      }

      this.config = new Configuration(this);
      if (startupProps != null && !startupProps.isEmpty()) {
         this.config.setPersistenceProperties(startupProps);
      }

      String clrName = this.config.getStringProperty("datanucleus.classLoaderResolverName");
      if (clrName != null) {
         this.classLoaderResolverClassName = this.pluginManager.getAttributeValueForExtension("org.datanucleus.classloader_resolver", "name", clrName, "class-name");
         if (this.classLoaderResolverClassName == null) {
            throw (new NucleusUserException(Localiser.msg("001001", clrName))).setFatal();
         }
      } else {
         this.classLoaderResolverClassName = null;
      }

      if (apiName != null) {
         this.apiAdapter = ApiAdapterFactory.getInstance().getApiAdapter(apiName, this.pluginManager);
         this.config.setDefaultProperties(this.apiAdapter.getDefaultFactoryProperties());
      } else {
         this.apiAdapter = null;
      }

   }

   public void applyDefaultProperties(Configuration conf) {
      conf.addDefaultProperty("datanucleus.plugin.pluginRegistryClassName", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.plugin.allowUserBundles", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.plugin.validatePlugins", (String)null, false, false, false);
      conf.addDefaultProperty("datanucleus.plugin.pluginRegistryBundleCheck", (String)null, "EXCEPTION", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultProperty("datanucleus.classLoaderResolverName", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultProperty("datanucleus.primaryClassLoader", (String)null, (String)null, (String)null, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.alwaysDetachable", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.xml.validate", (String)null, false, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.xml.namespaceAware", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.autoregistration", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.allowXML", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.allowAnnotations", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.allowLoadAtRuntime", (String)null, true, false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.supportORM", (String)null, (Boolean)null, false, false);
      conf.addDefaultProperty("datanucleus.metadata.jdoFileExtension", (String)null, "jdo", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.metadata.ormFileExtension", (String)null, "orm", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.metadata.jdoqueryFileExtension", (String)null, "jdoquery", (String)null, false, false);
      conf.addDefaultProperty("datanucleus.metadata.defaultInheritanceStrategy", (String)null, "JDO2", CorePropertyValidator.class.getName(), false, false);
      conf.addDefaultBooleanProperty("datanucleus.metadata.embedded.flat", (String)null, true, false, false);
   }

   public synchronized void initialise() {
      this.logConfiguration();
   }

   public abstract void close();

   public ApiAdapter getApiAdapter() {
      return this.apiAdapter;
   }

   public String getApiName() {
      return this.apiAdapter != null ? this.apiAdapter.getName() : null;
   }

   public Configuration getConfiguration() {
      return this.config;
   }

   public PluginManager getPluginManager() {
      return this.pluginManager;
   }

   public synchronized MetaDataManager getMetaDataManager() {
      if (this.metaDataManager == null) {
         String apiName = this.getApiName();

         try {
            this.metaDataManager = (MetaDataManager)this.pluginManager.createExecutableExtension("org.datanucleus.metadata_manager", new String[]{"name"}, new String[]{apiName}, "class", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this});
         } catch (Exception e) {
            throw new NucleusException(Localiser.msg("008010", apiName, e.getMessage()), e);
         }

         if (this.metaDataManager == null) {
            throw new NucleusException(Localiser.msg("008009", apiName));
         }
      }

      return this.metaDataManager;
   }

   public boolean supportsORMMetaData() {
      return true;
   }

   public TypeManager getTypeManager() {
      if (this.typeManager == null) {
         this.typeManager = new TypeManagerImpl(this);
      }

      return this.typeManager;
   }

   public ClassLoaderResolver getClassLoaderResolver(ClassLoader primaryLoader) {
      String resolverName = this.config.getStringProperty("datanucleus.classLoaderResolverName");
      String key = resolverName != null ? resolverName : "datanucleus";
      if (primaryLoader != null) {
         key = key + ":[" + StringUtils.toJVMIDString(primaryLoader) + "]";
      }

      if (this.classLoaderResolverMap == null) {
         this.classLoaderResolverMap = new HashMap();
      }

      ClassLoaderResolver clr = (ClassLoaderResolver)this.classLoaderResolverMap.get(key);
      if (clr != null) {
         return clr;
      } else {
         if (resolverName == null) {
            clr = new ClassLoaderResolverImpl(primaryLoader);
         } else {
            try {
               clr = (ClassLoaderResolver)this.pluginManager.createExecutableExtension("org.datanucleus.classloader_resolver", "name", resolverName, "class-name", new Class[]{ClassLoader.class}, new Object[]{primaryLoader});
            } catch (ClassNotFoundException cnfe) {
               throw (new NucleusUserException(Localiser.msg("001002", this.classLoaderResolverClassName), cnfe)).setFatal();
            } catch (Exception e) {
               throw (new NucleusUserException(Localiser.msg("001003", this.classLoaderResolverClassName), e)).setFatal();
            }
         }

         clr.registerUserClassLoader((ClassLoader)this.config.getProperty("datanucleus.primaryClassLoader"));
         this.classLoaderResolverMap.put(key, clr);
         return clr;
      }
   }

   protected void logConfiguration() {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug("================= NucleusContext ===============");
         String javaVersion = System.getProperty("java.version");
         if (StringUtils.isWhitespace(javaVersion)) {
            javaVersion = "unknown";
         }

         NucleusLogger.PERSISTENCE.debug(Localiser.msg("008000", this.pluginManager.getVersionForBundle("org.datanucleus"), javaVersion, System.getProperty("os.name")));
         NucleusLogger.PERSISTENCE.debug("Persistence API : " + this.getApiName());
         if (this.config.hasPropertyNotNull("datanucleus.PersistenceUnitName")) {
            NucleusLogger.PERSISTENCE.debug("Persistence-Unit : " + this.config.getStringProperty("datanucleus.PersistenceUnitName"));
         }

         NucleusLogger.PERSISTENCE.debug("Plugin Registry : " + this.pluginManager.getRegistryClassName());
         Object primCL = this.config.getProperty("datanucleus.primaryClassLoader");
         String clrName = this.config.getStringProperty("datanucleus.classLoaderResolverName");
         if (clrName == null) {
            clrName = "default";
         }

         NucleusLogger.PERSISTENCE.debug("ClassLoading : " + clrName + (primCL != null ? "primary=" + primCL : ""));
         this.logConfigurationDetails();
         NucleusLogger.PERSISTENCE.debug("================================================");
      }

   }

   protected abstract void logConfigurationDetails();

   static {
      STARTUP_PROPERTIES.add("datanucleus.plugin.pluginRegistryClassName");
      STARTUP_PROPERTIES.add("datanucleus.plugin.pluginRegistryBundleCheck");
      STARTUP_PROPERTIES.add("datanucleus.plugin.allowUserBundles");
      STARTUP_PROPERTIES.add("datanucleus.plugin.validatePlugins");
      STARTUP_PROPERTIES.add("datanucleus.classLoaderResolverName");
      STARTUP_PROPERTIES.add("datanucleus.persistenceXmlFilename");
      STARTUP_PROPERTIES.add("datanucleus.primaryClassLoader");
   }
}
