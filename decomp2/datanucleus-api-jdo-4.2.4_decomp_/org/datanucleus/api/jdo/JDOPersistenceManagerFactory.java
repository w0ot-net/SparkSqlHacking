package org.datanucleus.api.jdo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.jdo.AttributeConverter;
import javax.jdo.FetchGroup;
import javax.jdo.JDOException;
import javax.jdo.JDOFatalUserException;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.datastore.DataStoreCache;
import javax.jdo.datastore.Sequence;
import javax.jdo.listener.InstanceLifecycleListener;
import javax.jdo.metadata.JDOMetadata;
import javax.jdo.metadata.TypeMetadata;
import javax.jdo.spi.JDOImplHelper;
import javax.jdo.spi.JDOPermission;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;
import org.datanucleus.AbstractNucleusContext;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.PersistenceNucleusContextImpl;
import org.datanucleus.api.jdo.metadata.ClassMetadataImpl;
import org.datanucleus.api.jdo.metadata.InterfaceMetadataImpl;
import org.datanucleus.api.jdo.metadata.JDOMetadataImpl;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.TransactionActiveOnCloseException;
import org.datanucleus.exceptions.TransactionIsolationNotSupportedException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.datanucleus.metadata.TransactionType;
import org.datanucleus.properties.CorePropertyValidator;
import org.datanucleus.query.cache.QueryCompilationCache;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ConnectionResourceType;
import org.datanucleus.store.query.cache.QueryDatastoreCompilationCache;
import org.datanucleus.store.query.cache.QueryResultsCache;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JDOPersistenceManagerFactory implements PersistenceManagerFactory, ObjectFactory, Referenceable {
   private static final long serialVersionUID = -575257641123665920L;
   private static final String JDO_TYPE_CONVERTER_PROP_PREFIX = "javax.jdo.option.typeconverter.";
   private static ConcurrentHashMap pmfByName;
   protected transient PersistenceNucleusContext nucleusContext;
   private transient Set pmCache;
   protected transient Map lifecycleListeners;
   private transient Map sequenceByFactoryClass;
   private transient DataStoreCache datastoreCache;
   private transient JDOQueryCache queryCache;
   private transient Set jdoFetchGroups;
   private boolean closed;
   private boolean configurable;
   private transient ThreadLocal pmProxyThreadLocal;
   private static final String[] OPTION_ARRAY;
   private Map deserialisationProps;

   public static synchronized PersistenceManagerFactory getPersistenceManagerFactory(Properties overridingProps) {
      Map overridingMap = new HashMap();
      Enumeration e = overridingProps.propertyNames();

      while(e.hasMoreElements()) {
         String param = (String)e.nextElement();
         overridingMap.put(param, overridingProps.get(param));
      }

      return getPersistenceManagerFactory(overridingMap);
   }

   public static synchronized PersistenceManagerFactory getPersistenceManagerFactory(Map overridingProps) {
      Map overridingMap = null;
      if (overridingProps instanceof Properties) {
         overridingMap = new HashMap();
         Enumeration e = ((Properties)overridingProps).propertyNames();

         while(e.hasMoreElements()) {
            String param = (String)e.nextElement();
            overridingMap.put(param, ((Properties)overridingProps).get(param));
         }
      } else {
         overridingMap = overridingProps;
      }

      return createPersistenceManagerFactory(overridingMap);
   }

   public static synchronized PersistenceManagerFactory getPersistenceManagerFactory(Map overrides, Map props) {
      Map propsMap = null;
      if (props instanceof Properties) {
         propsMap = new HashMap();
         Enumeration e = ((Properties)props).propertyNames();

         while(e.hasMoreElements()) {
            String param = (String)e.nextElement();
            propsMap.put(param, ((Properties)props).get(param));
         }
      } else {
         propsMap = props;
      }

      Map overridesMap = null;
      if (overrides instanceof Properties) {
         overridesMap = new HashMap();
         Enumeration e = ((Properties)overrides).propertyNames();

         while(e.hasMoreElements()) {
            String param = (String)e.nextElement();
            overridesMap.put(param, ((Properties)overrides).get(param));
         }
      } else {
         overridesMap = overrides;
      }

      Map overallMap = propsMap != null ? new HashMap(propsMap) : new HashMap();
      if (overridesMap != null) {
         overallMap.putAll(overridesMap);
      }

      return createPersistenceManagerFactory(overallMap);
   }

   protected static JDOPersistenceManagerFactory createPersistenceManagerFactory(Map props) {
      Class pmfClass = null;
      if (props != null && props.containsKey("javax.jdo.PersistenceManagerFactoryClass")) {
         String pmfClassName = (String)props.get("javax.jdo.PersistenceManagerFactoryClass");
         if (!pmfClassName.equals(JDOPersistenceManagerFactory.class.getName())) {
            try {
               pmfClass = Class.forName(pmfClassName);
            } catch (ClassNotFoundException var5) {
            }
         }
      }

      JDOPersistenceManagerFactory pmf;
      if (pmfClass != null) {
         pmf = (JDOPersistenceManagerFactory)ClassUtils.newInstance(pmfClass, new Class[]{Map.class}, new Object[]{props});
      } else {
         pmf = new JDOPersistenceManagerFactory(props);
      }

      Boolean singleton = pmf.getConfiguration().getBooleanObjectProperty("datanucleus.singletonPMFForName");
      if (singleton != null && singleton) {
         if (pmfByName == null) {
            pmfByName = new ConcurrentHashMap();
         }

         String name = pmf.getName();
         if (name == null) {
            name = pmf.getPersistenceUnitName();
         }

         if (name != null && pmfByName.containsKey(name)) {
            pmf.close();
            NucleusLogger.PERSISTENCE.warn("Requested PMF of name \"" + name + "\" but already exists and using singleton pattern, so returning existing PMF");
            return (JDOPersistenceManagerFactory)pmfByName.get(name);
         }

         pmfByName.putIfAbsent(name, pmf);
      }

      pmf.freezeConfiguration();
      return pmf;
   }

   public JDOPersistenceManagerFactory() {
      this((Map)null);
   }

   public JDOPersistenceManagerFactory(PersistenceUnitMetaData pumd, Map overrideProps) {
      this.pmCache = Collections.newSetFromMap(new ConcurrentHashMap());
      this.datastoreCache = null;
      this.queryCache = null;
      this.jdoFetchGroups = null;
      this.configurable = true;
      this.pmProxyThreadLocal = new InheritableThreadLocal() {
         protected PersistenceManager initialValue() {
            return null;
         }
      };
      this.deserialisationProps = null;
      Map props = new HashMap();
      if (pumd != null && pumd.getProperties() != null) {
         props.putAll(pumd.getProperties());
      }

      if (overrideProps != null) {
         props.putAll(overrideProps);
      }

      if (!props.containsKey("datanucleus.TransactionType") && !props.containsKey("javax.jdo.option.TransactionType")) {
         props.put("datanucleus.TransactionType", TransactionType.RESOURCE_LOCAL.toString());
      } else {
         String transactionType = props.get("datanucleus.TransactionType") != null ? (String)props.get("datanucleus.TransactionType") : (String)props.get("javax.jdo.option.TransactionType");
         if (TransactionType.JTA.toString().equalsIgnoreCase(transactionType)) {
            props.put("datanucleus.connection.resourceType", ConnectionResourceType.JTA.toString());
            props.put("datanucleus.connection2.resourceType", ConnectionResourceType.JTA.toString());
         }
      }

      this.nucleusContext = new PersistenceNucleusContextImpl("JDO", props);
      this.initialiseMetaData(pumd);
      this.processLifecycleListenersFromProperties(props);
   }

   public JDOPersistenceManagerFactory(Map props) {
      this.pmCache = Collections.newSetFromMap(new ConcurrentHashMap());
      this.datastoreCache = null;
      this.queryCache = null;
      this.jdoFetchGroups = null;
      this.configurable = true;
      this.pmProxyThreadLocal = new InheritableThreadLocal() {
         protected PersistenceManager initialValue() {
            return null;
         }
      };
      this.deserialisationProps = null;
      Map startupProps = null;
      if (props != null) {
         for(String startupPropName : AbstractNucleusContext.STARTUP_PROPERTIES) {
            if (props.containsKey(startupPropName)) {
               if (startupProps == null) {
                  startupProps = new HashMap();
               }

               startupProps.put(startupPropName, props.get(startupPropName));
            }
         }
      }

      this.nucleusContext = new PersistenceNucleusContextImpl("JDO", startupProps);
      Map pmfProps = new HashMap();
      PersistenceUnitMetaData pumd = null;
      if (props != null) {
         String persistenceUnitName = (String)props.get("datanucleus.PersistenceUnitName");
         if (persistenceUnitName == null) {
            persistenceUnitName = (String)props.get("javax.jdo.option.PersistenceUnitName");
         }

         if (persistenceUnitName != null) {
            this.getConfiguration().setProperty("datanucleus.PersistenceUnitName", persistenceUnitName);

            try {
               pumd = this.nucleusContext.getMetaDataManager().getMetaDataForPersistenceUnit(persistenceUnitName);
               if (pumd == null) {
                  throw new JDOUserException(Localiser.msg("012004", new Object[]{persistenceUnitName}));
               }

               if (pumd.getProperties() != null) {
                  pmfProps.putAll(pumd.getProperties());
               }
            } catch (NucleusException ne) {
               throw new JDOUserException(Localiser.msg("012005", new Object[]{persistenceUnitName}), ne);
            }
         }
      }

      if (props != null) {
         pmfProps.putAll(props);
         if (!pmfProps.containsKey("datanucleus.TransactionType") && !pmfProps.containsKey("javax.jdo.option.TransactionType")) {
            pmfProps.put("datanucleus.TransactionType", TransactionType.RESOURCE_LOCAL.toString());
         } else {
            String transactionType = pmfProps.get("datanucleus.TransactionType") != null ? (String)pmfProps.get("datanucleus.TransactionType") : (String)pmfProps.get("javax.jdo.option.TransactionType");
            if (TransactionType.JTA.toString().equalsIgnoreCase(transactionType)) {
               pmfProps.put("datanucleus.connection.resourceType", ConnectionResourceType.JTA.toString());
               pmfProps.put("datanucleus.connection2.resourceType", ConnectionResourceType.JTA.toString());
            }
         }
      } else {
         pmfProps.put("datanucleus.TransactionType", TransactionType.RESOURCE_LOCAL.toString());
      }

      Map typeProps = null;
      Iterator<Map.Entry<String, Object>> entryIter = pmfProps.entrySet().iterator();

      while(entryIter.hasNext()) {
         Map.Entry<String, Object> entry = (Map.Entry)entryIter.next();
         String propName = (String)entry.getKey();
         if (propName.startsWith("javax.jdo.option.typeconverter.")) {
            if (typeProps == null) {
               typeProps = new HashMap();
            }

            typeProps.put(propName, entry.getValue());
            entryIter.remove();
         }
      }

      try {
         String propsFileProp = "datanucleus.propertiesFile";
         if (pmfProps.containsKey(propsFileProp)) {
            this.getConfiguration().setPropertiesUsingFile((String)pmfProps.get(propsFileProp));
            pmfProps.remove(propsFileProp);
         }

         this.getConfiguration().setPersistenceProperties(pmfProps);
      } catch (IllegalArgumentException iae) {
         throw new JDOFatalUserException("Exception thrown setting persistence properties", iae);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }

      if (typeProps != null) {
         for(Map.Entry entry : typeProps.entrySet()) {
            String propName = (String)entry.getKey();
            String typeName = propName.substring("javax.jdo.option.typeconverter.".length());
            String converterName = (String)entry.getValue();

            try {
               Class attrConvCls = this.nucleusContext.getClassLoaderResolver((ClassLoader)null).classForName(converterName);
               AttributeConverter attrConv = (AttributeConverter)ClassUtils.newInstance(attrConvCls, (Class[])null, (Object[])null);
               Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(attrConvCls, (Class)null);
               Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(attrConvCls, attrType, (Class)null);
               if (attrType != null) {
                  NucleusLogger.GENERAL.debug("Registering javaType=" + typeName + " as using converter with name=" + converterName + " conv=" + attrConv);
                  JDOTypeConverter conv = new JDOTypeConverter(attrConv, attrType, dbType);
                  this.nucleusContext.getTypeManager().registerConverter(converterName, conv, true, attrType.getName());
               }
            } catch (NucleusException ne) {
               throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
            }
         }
      }

      this.initialiseMetaData(pumd);
      this.processLifecycleListenersFromProperties(props);
   }

   public synchronized void close() {
      checkJDOPermission(JDOPermission.CLOSE_PERSISTENCE_MANAGER_FACTORY);
      if (!this.isClosed()) {
         this.setIsNotConfigurable();
         Set<JDOUserException> exceptions = new HashSet();

         for(JDOPersistenceManager pm : this.pmCache) {
            ExecutionContext ec = pm.getExecutionContext();
            if (ec.getTransaction().isActive()) {
               TransactionActiveOnCloseException tae = new TransactionActiveOnCloseException(ec);
               exceptions.add(new JDOUserException(tae.getMessage(), pm));
            }
         }

         if (!exceptions.isEmpty()) {
            throw new JDOUserException(Localiser.msg("012002"), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
         } else {
            for(JDOPersistenceManager pm : this.pmCache) {
               pm.internalClose();
            }

            this.pmCache.clear();
            if (pmfByName != null) {
               synchronized(pmfByName) {
                  Iterator<Map.Entry<String, JDOPersistenceManagerFactory>> pmfIter = pmfByName.entrySet().iterator();

                  while(pmfIter.hasNext()) {
                     Map.Entry<String, JDOPersistenceManagerFactory> entry = (Map.Entry)pmfIter.next();
                     if (entry.getValue() == this) {
                        pmfIter.remove();
                        break;
                     }
                  }
               }
            }

            if (this.sequenceByFactoryClass != null) {
               this.sequenceByFactoryClass.clear();
               this.sequenceByFactoryClass = null;
            }

            if (this.lifecycleListeners != null) {
               this.lifecycleListeners.clear();
               this.lifecycleListeners = null;
            }

            if (this.datastoreCache != null) {
               this.datastoreCache.evictAll();
               this.datastoreCache = null;
            }

            if (this.queryCache != null) {
               this.queryCache.evictAll();
               this.queryCache = null;
            }

            if (this.jdoFetchGroups != null) {
               this.jdoFetchGroups.clear();
               this.jdoFetchGroups = null;
            }

            this.nucleusContext.close();
            this.closed = true;
         }
      }
   }

   public synchronized boolean isClosed() {
      return this.closed;
   }

   protected void processLifecycleListenersFromProperties(Map props) {
      if (props != null) {
         for(Map.Entry entry : props.entrySet()) {
            String key = (String)entry.getKey();
            if (key.startsWith("javax.jdo.listener.InstanceLifecycleListener")) {
               String listenerClsName = key.substring(45);
               String listenerClasses = (String)entry.getValue();
               ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);
               Class listenerCls = null;

               try {
                  listenerCls = clr.classForName(listenerClsName);
               } catch (ClassNotResolvedException var16) {
                  throw new JDOUserException(Localiser.msg("012022", new Object[]{listenerClsName}));
               }

               InstanceLifecycleListener listener = null;
               Method method = ClassUtils.getMethodForClass(listenerCls, "getInstance", (Class[])null);
               if (method != null) {
                  try {
                     listener = (InstanceLifecycleListener)method.invoke((Object)null);
                  } catch (Exception e) {
                     throw new JDOUserException(Localiser.msg("012021", new Object[]{listenerClsName}), e);
                  }
               } else {
                  try {
                     listener = (InstanceLifecycleListener)listenerCls.newInstance();
                  } catch (Exception e) {
                     throw new JDOUserException(Localiser.msg("012020", new Object[]{listenerClsName}), e);
                  }
               }

               Class[] classes = null;
               if (!StringUtils.isWhitespace(listenerClasses)) {
                  String[] classNames = StringUtils.split(listenerClasses, ",");
                  classes = new Class[classNames.length];

                  for(int i = 0; i < classNames.length; ++i) {
                     classes[i] = clr.classForName(classNames[i]);
                  }
               }

               this.addInstanceLifecycleListener(listener, classes);
            }
         }
      }

   }

   protected void initialiseMetaData(PersistenceUnitMetaData pumd) {
      this.nucleusContext.getMetaDataManager().setAllowXML(this.getConfiguration().getBooleanProperty("datanucleus.metadata.allowXML"));
      this.nucleusContext.getMetaDataManager().setAllowAnnotations(this.getConfiguration().getBooleanProperty("datanucleus.metadata.allowAnnotations"));
      this.nucleusContext.getMetaDataManager().setValidate(this.getConfiguration().getBooleanProperty("datanucleus.metadata.xml.validate"));
      if (pumd != null) {
         try {
            this.nucleusContext.getMetaDataManager().loadPersistenceUnit(pumd, (ClassLoader)null);
            if (pumd.getValidationMode() != null) {
               this.getConfiguration().setProperty("datanucleus.validation.mode", pumd.getValidationMode());
            }
         } catch (NucleusException jpe) {
            throw new JDOException(jpe.getMessage(), jpe);
         }
      }

      boolean allowMetadataLoad = this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.allowLoadAtRuntime");
      if (!allowMetadataLoad) {
         this.nucleusContext.getMetaDataManager().setAllowMetaDataLoad(false);
      }

   }

   protected void freezeConfiguration() {
      if (this.isConfigurable()) {
         Method m = null;

         try {
            m = JDOImplHelper.class.getDeclaredMethod("assertOnlyKnownStandardProperties", Map.class);
            m.invoke((Object)null, this.nucleusContext.getConfiguration().getPersistenceProperties());
         } catch (InvocationTargetException ite) {
            if (ite.getCause() instanceof JDOException) {
               throw (JDOException)ite.getCause();
            }
         } catch (JDOException jdoe) {
            throw jdoe;
         } catch (Exception var10) {
         }

         synchronized(this) {
            try {
               this.nucleusContext.initialise();
               this.datastoreCache = new JDODataStoreCache(this.nucleusContext.getLevel2Cache());
               this.setIsNotConfigurable();
            } catch (TransactionIsolationNotSupportedException inse) {
               throw new JDOUnsupportedOptionException(inse.getMessage());
            } catch (NucleusException jpe) {
               throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
            }
         }
      }

   }

   public PersistenceManager getPersistenceManager() {
      return this.getPersistenceManager(this.getConnectionUserName(), this.getConnectionPassword());
   }

   public PersistenceManager getPersistenceManager(String userName, String password) {
      this.assertIsOpen();
      this.freezeConfiguration();
      JDOPersistenceManager pm = this.newPM(this, userName, password);
      if (this.lifecycleListeners != null) {
         for(LifecycleListenerForClass listener : this.lifecycleListeners.values()) {
            pm.addInstanceLifecycleListener(listener.getListener(), listener.getClasses());
         }
      }

      this.pmCache.add(pm);
      return pm;
   }

   protected JDOPersistenceManager newPM(JDOPersistenceManagerFactory jdoPmf, String userName, String password) {
      return new JDOPersistenceManager(jdoPmf, userName, password);
   }

   public PersistenceNucleusContext getNucleusContext() {
      return this.nucleusContext;
   }

   protected Configuration getConfiguration() {
      return this.nucleusContext.getConfiguration();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof JDOPersistenceManagerFactory) ? false : super.equals(obj);
      }
   }

   public Object getObjectInstance(Object obj, Name name, Context ctx, Hashtable env) throws Exception {
      JDOPersistenceManagerFactory pmf = null;
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug("Creating PersistenceManagerFactory instance via JNDI with values [object] " + (obj == null ? "" : obj.toString()) + " [name] " + (name == null ? "" : name.toString()) + " [context] " + (ctx == null ? "" : ctx.toString()) + " [env] " + (env == null ? "" : env.toString()) + " ");
      }

      if (obj instanceof Reference) {
         Reference ref = (Reference)obj;
         if (!ref.getClassName().equals(JDOClassNameConstants.JDOPersistenceManagerFactory) && !ref.getClassName().equals(JDOClassNameConstants.JAVAX_JDO_PersistenceManagerFactory)) {
            NucleusLogger.PERSISTENCE.warn(Localiser.msg("012007", new Object[]{ref.getClassName(), JDOClassNameConstants.JDOPersistenceManagerFactory}));
         } else {
            Properties p = new Properties();
            Enumeration e = ref.getAll();

            while(e.hasMoreElements()) {
               StringRefAddr sra = (StringRefAddr)e.nextElement();
               p.setProperty(sra.getType(), (String)sra.getContent());
            }

            pmf = new JDOPersistenceManagerFactory(p);
            pmf.freezeConfiguration();
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("012006", new Object[]{name != null ? name.toString() : null}));
            }
         }
      } else {
         NucleusLogger.PERSISTENCE.warn(Localiser.msg("012008", new Object[]{obj != null ? obj.getClass().getName() : null}));
      }

      return pmf;
   }

   public Reference getReference() {
      Reference rc = null;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      try {
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         oos.writeObject(this);
         rc = new Reference(JDOClassNameConstants.JAVAX_JDO_PersistenceManagerFactory, JDOClassNameConstants.JDOPersistenceManagerFactory, (String)null);
         Map p = this.getConfiguration().getPersistenceProperties();

         for(Map.Entry entry : p.entrySet()) {
            String key = (String)entry.getKey();
            Object valueObj = entry.getValue();
            if (valueObj instanceof String) {
               String value = (String)valueObj;
               rc.add(new StringRefAddr(key, value));
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("012009", new Object[]{key, value}));
               }
            } else if (valueObj instanceof Long) {
               String value = "" + valueObj;
               rc.add(new StringRefAddr(key, value));
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("012009", new Object[]{key, value}));
               }
            } else if (valueObj instanceof Integer) {
               String value = "" + valueObj;
               rc.add(new StringRefAddr(key, value));
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("012009", new Object[]{key, value}));
               }
            } else if (valueObj instanceof Boolean) {
               String value = (Boolean)valueObj ? "true" : "false";
               rc.add(new StringRefAddr(key, value));
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("012009", new Object[]{key, value}));
               }
            } else {
               NucleusLogger.PERSISTENCE.warn(Localiser.msg("012010", new Object[]{key}));
            }
         }

         if (NucleusLogger.PERSISTENCE.isDebugEnabled() && p.isEmpty()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("012011"));
         }

         return rc;
      } catch (IOException ex) {
         NucleusLogger.PERSISTENCE.error(ex.getMessage());
         throw new NucleusException(ex.getMessage(), ex);
      }
   }

   public PersistenceManager getPersistenceManagerProxy() {
      return new JDOPersistenceManagerProxy(this);
   }

   PersistenceManager getPMProxyDelegate() {
      PersistenceManager pm = (PersistenceManager)this.pmProxyThreadLocal.get();
      if (pm == null) {
         pm = this.getPersistenceManager();
         this.pmProxyThreadLocal.set(pm);
      }

      return pm;
   }

   void clearPMProxyDelegate() {
      PersistenceManagerFactory pmf = this.getPMProxyDelegate().getPersistenceManagerFactory();
      String txnType = pmf.getTransactionType();
      if (TransactionType.RESOURCE_LOCAL.toString().equalsIgnoreCase(txnType)) {
         this.getPMProxyDelegate().close();
         this.pmProxyThreadLocal.remove();
      } else if (TransactionType.JTA.toString().equalsIgnoreCase(txnType)) {
      }

   }

   public Properties getProperties() {
      Properties props = new Properties();
      props.setProperty("VendorName", "DataNucleus");
      props.setProperty("VersionNumber", this.nucleusContext.getPluginManager().getVersionForBundle("org.datanucleus.api.jdo"));
      props.putAll(this.nucleusContext.getConfiguration().getPersistenceProperties());
      return props;
   }

   public Collection supportedOptions() {
      Set options = new HashSet(Arrays.asList(OPTION_ARRAY));
      StoreManager storeMgr = this.nucleusContext.getStoreManager();
      if (storeMgr != null) {
         Collection storeMgrOptions = storeMgr.getSupportedOptions();
         if (!storeMgrOptions.contains("NonDurableId")) {
            options.remove("javax.jdo.option.NonDurableIdentity");
         }

         if (!storeMgrOptions.contains("DatastoreId")) {
            options.remove("javax.jdo.option.DatastoreIdentity");
         }

         if (!storeMgrOptions.contains("ApplicationId")) {
            options.remove("javax.jdo.option.ApplicationIdentity");
         }

         if (!storeMgr.supportsQueryLanguage("JDOQL")) {
            options.remove("javax.jdo.query.JDOQL");
         }

         if (!storeMgr.supportsQueryLanguage("SQL")) {
            options.remove("javax.jdo.query.SQL");
         }

         if (storeMgrOptions.contains("TransactionIsolationLevel.read-committed")) {
            options.add("javax.jdo.option.TransactionIsolationLevel.read-committed");
         }

         if (storeMgrOptions.contains("TransactionIsolationLevel.read-uncommitted")) {
            options.add("javax.jdo.option.TransactionIsolationLevel.read-uncommitted");
         }

         if (storeMgrOptions.contains("TransactionIsolationLevel.repeatable-read")) {
            options.add("javax.jdo.option.TransactionIsolationLevel.repeatable-read");
         }

         if (storeMgrOptions.contains("TransactionIsolationLevel.serializable")) {
            options.add("javax.jdo.option.TransactionIsolationLevel.serializable");
         }

         if (storeMgrOptions.contains("TransactionIsolationLevel.snapshot")) {
            options.add("javax.jdo.option.TransactionIsolationLevel.snapshot");
         }

         if (storeMgrOptions.contains("Query.Cancel")) {
            options.add("javax.jdo.option.QueryCancel");
         }

         if (storeMgrOptions.contains("Datastore.Timeout")) {
            options.add("javax.jdo.option.DatastoreTimeout");
         }

         if (storeMgrOptions.contains("Query.JDOQL.BitwiseOperations")) {
            options.add("javax.jdo.query.JDOQL.BitwiseOperations");
         }
      }

      return Collections.unmodifiableSet(options);
   }

   public void releasePersistenceManager(JDOPersistenceManager pm) {
      if (this.pmCache.contains(pm)) {
         this.pmCache.remove(pm);
         pm.internalClose();
      }

   }

   protected void assertIsOpen() {
      if (this.isClosed()) {
         throw new JDOUserException(Localiser.msg("012025"));
      }
   }

   protected void finalize() throws Throwable {
      this.close();
      super.finalize();
   }

   public DataStoreCache getDataStoreCache() {
      this.freezeConfiguration();
      return this.datastoreCache;
   }

   public JDOQueryCache getQueryCache() {
      if (this.queryCache != null) {
         return this.queryCache;
      } else {
         QueryResultsCache cache = this.nucleusContext.getStoreManager().getQueryManager().getQueryResultsCache();
         this.queryCache = new JDOQueryCache(cache);
         return this.queryCache;
      }
   }

   public QueryCompilationCache getQueryGenericCompilationCache() {
      return this.nucleusContext.getStoreManager().getQueryManager().getQueryCompilationCache();
   }

   public QueryDatastoreCompilationCache getQueryDatastoreCompilationCache() {
      return this.nucleusContext.getStoreManager().getQueryManager().getQueryDatastoreCompilationCache();
   }

   public void setConnectionUserName(String userName) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionUserName", userName);
   }

   public void setConnectionPassword(String password) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionPassword", password);
   }

   public void setConnectionURL(String url) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionURL", url);
   }

   public void setConnectionDriverName(String driverName) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionDriverName", driverName);
   }

   public void setConnectionFactoryName(String connectionFactoryName) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionFactoryName", connectionFactoryName);
   }

   public void setConnectionFactory(Object connectionFactory) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionFactory", connectionFactory);
   }

   public void setConnectionFactory2Name(String connectionFactoryName) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionFactory2Name", connectionFactoryName);
   }

   public void setConnectionFactory2(Object connectionFactory) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.ConnectionFactory2", connectionFactory);
   }

   public void setMultithreaded(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.Multithreaded", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setOptimistic(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.Optimistic", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setRetainValues(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.RetainValues", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setRestoreValues(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.RestoreValues", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setNontransactionalRead(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.NontransactionalRead", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setNontransactionalWrite(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.NontransactionalWrite", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setNontransactionalWriteAutoCommit(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.nontx.atomic", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setIgnoreCache(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.IgnoreCache", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setDetachAllOnCommit(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.DetachAllOnCommit", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setCopyOnAttach(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.CopyOnAttach", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setMapping(String mapping) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.mapping", mapping);
   }

   public void setCatalog(String catalog) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.mapping.Catalog", catalog);
   }

   public void setSchema(String schema) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.mapping.Schema", schema);
   }

   public void setDatastoreReadTimeoutMillis(Integer timeout) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.datastoreReadTimeout", timeout);
   }

   public void setDatastoreWriteTimeoutMillis(Integer timeout) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.datastoreWriteTimeout", timeout);
   }

   public void setTransactionType(String type) {
      this.assertConfigurable();
      boolean validated = (new CorePropertyValidator()).validate("datanucleus.TransactionType", type);
      if (validated) {
         this.getConfiguration().setProperty("datanucleus.TransactionType", type);
      } else {
         throw new JDOUserException(Localiser.msg("012026", new Object[]{"javax.jdo.option.TransactionType", type}));
      }
   }

   public void setPersistenceUnitName(String name) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.PersistenceUnitName", name);
   }

   public void setPersistenceXmlFilename(String name) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.persistenceXmlFilename", name);
   }

   public void setName(String name) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.Name", name);
   }

   public void setServerTimeZoneID(String id) {
      this.assertConfigurable();
      boolean validated = (new CorePropertyValidator()).validate("datanucleus.ServerTimeZoneID", id);
      if (validated) {
         this.getConfiguration().setProperty("datanucleus.ServerTimeZoneID", id);
      } else {
         throw new JDOUserException("Invalid TimeZone ID specified");
      }
   }

   public void setReadOnly(boolean flag) {
      this.assertConfigurable();
      this.getConfiguration().setProperty("datanucleus.readOnlyDatastore", flag ? Boolean.TRUE : Boolean.FALSE);
   }

   public void setTransactionIsolationLevel(String level) {
      this.assertConfigurable();
      if (this.nucleusContext.getStoreManager() != null && !this.nucleusContext.getStoreManager().getSupportedOptions().contains("TransactionIsolationLevel." + level)) {
         throw new JDOUnsupportedOptionException("Isolation level \"" + level + "\" is not supported for this datastore");
      } else {
         this.getConfiguration().setProperty("datanucleus.transactionIsolation", level != null ? level : "read-committed");
      }
   }

   public String getConnectionUserName() {
      return this.getConfiguration().getStringProperty("datanucleus.ConnectionUserName");
   }

   public String getConnectionPassword() {
      return this.getConfiguration().getStringProperty("datanucleus.ConnectionPassword");
   }

   public String getConnectionURL() {
      return this.getConfiguration().getStringProperty("datanucleus.ConnectionURL");
   }

   public String getConnectionDriverName() {
      return this.getConfiguration().getStringProperty("datanucleus.ConnectionDriverName");
   }

   public String getConnectionFactoryName() {
      return this.getConfiguration().getStringProperty("datanucleus.ConnectionFactoryName");
   }

   public String getConnectionFactory2Name() {
      return this.getConfiguration().getStringProperty("datanucleus.ConnectionFactory2Name");
   }

   public Object getConnectionFactory() {
      return this.getConfiguration().getProperty("datanucleus.ConnectionFactory");
   }

   public Object getConnectionFactory2() {
      return this.getConfiguration().getProperty("datanucleus.ConnectionFactory2");
   }

   public boolean getMultithreaded() {
      return this.getConfiguration().getBooleanProperty("datanucleus.Multithreaded");
   }

   public boolean getOptimistic() {
      return this.getConfiguration().getBooleanProperty("datanucleus.Optimistic");
   }

   public boolean getRetainValues() {
      return this.getConfiguration().getBooleanProperty("datanucleus.RetainValues");
   }

   public boolean getRestoreValues() {
      return this.getConfiguration().getBooleanProperty("datanucleus.RestoreValues");
   }

   public boolean getNontransactionalRead() {
      return this.getConfiguration().getBooleanProperty("datanucleus.NontransactionalRead");
   }

   public boolean getNontransactionalWrite() {
      return this.getConfiguration().getBooleanProperty("datanucleus.NontransactionalWrite");
   }

   public boolean getNontransactionalWriteAutoCommit() {
      return this.getConfiguration().getBooleanProperty("datanucleus.nontx.atomic");
   }

   public boolean getIgnoreCache() {
      return this.getConfiguration().getBooleanProperty("datanucleus.IgnoreCache");
   }

   public boolean getDetachAllOnCommit() {
      return this.getConfiguration().getBooleanProperty("datanucleus.DetachAllOnCommit");
   }

   public boolean getCopyOnAttach() {
      return this.getConfiguration().getBooleanProperty("datanucleus.CopyOnAttach");
   }

   public String getMapping() {
      return this.getConfiguration().getStringProperty("datanucleus.mapping");
   }

   public String getCatalog() {
      return this.getConfiguration().getStringProperty("datanucleus.mapping.Catalog");
   }

   public String getSchema() {
      return this.getConfiguration().getStringProperty("datanucleus.mapping.Schema");
   }

   public String getName() {
      return this.getConfiguration().getStringProperty("datanucleus.Name");
   }

   public String getPersistenceUnitName() {
      return this.getConfiguration().getStringProperty("datanucleus.PersistenceUnitName");
   }

   public String getPersistenceXmlFilename() {
      return this.getConfiguration().getStringProperty("datanucleus.persistenceXmlFilename");
   }

   public Integer getDatastoreReadTimeoutMillis() {
      return this.getConfiguration().getIntProperty("datanucleus.datastoreReadTimeout");
   }

   public Integer getDatastoreWriteTimeoutMillis() {
      return this.getConfiguration().getIntProperty("datanucleus.datastoreWriteTimeout");
   }

   public String getServerTimeZoneID() {
      return this.getConfiguration().getStringProperty("datanucleus.ServerTimeZoneID");
   }

   public boolean getReadOnly() {
      return this.getConfiguration().getBooleanProperty("datanucleus.readOnlyDatastore");
   }

   public String getTransactionType() {
      return this.getConfiguration().getStringProperty("datanucleus.TransactionType");
   }

   public String getTransactionIsolationLevel() {
      return this.getConfiguration().getStringProperty("datanucleus.transactionIsolation");
   }

   public void setPrimaryClassLoader(ClassLoader loader) {
      this.getConfiguration().setProperty("datanucleus.primaryClassLoader", loader);
   }

   public ClassLoader getPrimaryClassLoader() {
      return (ClassLoader)this.getConfiguration().getProperty("datanucleus.primaryClassLoader");
   }

   public void setPersistenceProperties(Map props) {
      this.assertConfigurable();
      this.getConfiguration().setPersistenceProperties(props);
   }

   public Map getPersistenceProperties() {
      return this.getConfiguration().getPersistenceProperties();
   }

   protected void assertConfigurable() {
      if (!this.isConfigurable()) {
         throw new JDOUserException(Localiser.msg("012023"));
      }
   }

   protected boolean isConfigurable() {
      return this.configurable;
   }

   protected void setIsNotConfigurable() {
      this.configurable = false;
   }

   /** @deprecated */
   public List getLifecycleListenerSpecifications() {
      return (List)(this.lifecycleListeners == null ? Collections.EMPTY_LIST : new ArrayList(this.lifecycleListeners.values()));
   }

   public void addInstanceLifecycleListener(InstanceLifecycleListener listener, Class[] classes) {
      boolean allowListeners = this.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.allowListenerUpdateAfterInit", false);
      if (!allowListeners) {
         this.assertConfigurable();
      }

      if (listener != null) {
         classes = LifecycleListenerForClass.canonicaliseClasses(classes);
         if (classes == null || classes.length != 0) {
            if (this.lifecycleListeners == null) {
               this.lifecycleListeners = new ConcurrentHashMap(1);
            }

            LifecycleListenerForClass entry;
            if (this.lifecycleListeners.containsKey(listener)) {
               entry = ((LifecycleListenerForClass)this.lifecycleListeners.get(listener)).mergeClasses(classes);
            } else {
               entry = new LifecycleListenerForClass(listener, classes);
            }

            this.lifecycleListeners.put(listener, entry);
         }
      }
   }

   public void removeInstanceLifecycleListener(InstanceLifecycleListener listener) {
      boolean allowListeners = this.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.allowListenerUpdateAfterInit", false);
      if (!allowListeners) {
         this.assertConfigurable();
      }

      if (listener != null && this.lifecycleListeners != null) {
         this.lifecycleListeners.remove(listener);
      }
   }

   public void addSequenceForFactoryClass(String factoryClassName, Sequence sequence) {
      if (this.sequenceByFactoryClass == null) {
         this.sequenceByFactoryClass = new HashMap();
      }

      this.sequenceByFactoryClass.put(factoryClassName, sequence);
   }

   public Sequence getSequenceForFactoryClass(String factoryClassName) {
      return this.sequenceByFactoryClass == null ? null : (Sequence)this.sequenceByFactoryClass.get(factoryClassName);
   }

   public Set getFetchGroups() {
      Set<JDOFetchGroup> jdoGroups = this.getJDOFetchGroups(false);
      if (jdoGroups != null) {
         synchronized(jdoGroups) {
            if (!jdoGroups.isEmpty()) {
               return new HashSet(jdoGroups);
            }
         }
      }

      return null;
   }

   public FetchGroup getFetchGroup(Class cls, String name) {
      Set<JDOFetchGroup> jdoGroups = this.getJDOFetchGroups(false);
      if (jdoGroups != null) {
         synchronized(jdoGroups) {
            for(JDOFetchGroup jdoFetchGroup : jdoGroups) {
               if (jdoFetchGroup.getType() == cls && jdoFetchGroup.getName().equals(name)) {
                  return jdoFetchGroup;
               }
            }
         }
      }

      try {
         org.datanucleus.FetchGroup internalGrp = this.nucleusContext.getInternalFetchGroup(cls, name, false);
         if (internalGrp != null && !internalGrp.isUnmodifiable()) {
            return new JDOFetchGroup(internalGrp);
         } else {
            internalGrp = this.nucleusContext.createInternalFetchGroup(cls, name);
            JDOFetchGroup jdoGrp = new JDOFetchGroup(internalGrp);
            return jdoGrp;
         }
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void addFetchGroups(FetchGroup... groups) {
      checkJDOPermission(JDOPermission.GET_METADATA);
      if (groups != null && groups.length != 0) {
         Set<JDOFetchGroup> jdoGroups = this.getJDOFetchGroups(true);
         synchronized(jdoGroups) {
            for(int i = 0; i < groups.length; ++i) {
               JDOFetchGroup jdoFetchGroup = (JDOFetchGroup)groups[i];
               this.nucleusContext.addInternalFetchGroup(jdoFetchGroup.getInternalFetchGroup());
               jdoGroups.add(jdoFetchGroup);
            }

         }
      }
   }

   public void removeFetchGroups(FetchGroup... groups) {
      checkJDOPermission(JDOPermission.GET_METADATA);
      if (groups != null && groups.length != 0) {
         Set<JDOFetchGroup> jdoGroups = this.getJDOFetchGroups(false);
         if (jdoGroups != null) {
            synchronized(jdoGroups) {
               if (!jdoGroups.isEmpty()) {
                  for(int i = 0; i < groups.length; ++i) {
                     JDOFetchGroup jdoFetchGroup = (JDOFetchGroup)groups[i];
                     this.nucleusContext.removeInternalFetchGroup(jdoFetchGroup.getInternalFetchGroup());
                     jdoGroups.remove(jdoFetchGroup);
                  }
               }
            }
         }

      }
   }

   public void removeAllFetchGroups() {
      checkJDOPermission(JDOPermission.GET_METADATA);
      Set<JDOFetchGroup> jdoGroups = this.getJDOFetchGroups(false);
      if (jdoGroups != null) {
         synchronized(jdoGroups) {
            for(JDOFetchGroup jdoGrp : jdoGroups) {
               this.nucleusContext.removeInternalFetchGroup(jdoGrp.getInternalFetchGroup());
            }

            jdoGroups.clear();
         }
      }

   }

   private synchronized Set getJDOFetchGroups(boolean createIfNull) {
      if (this.jdoFetchGroups == null && createIfNull) {
         this.jdoFetchGroups = new HashSet();
      }

      return this.jdoFetchGroups;
   }

   public JDOMetadata newMetadata() {
      return new JDOMetadataImpl();
   }

   public void registerMetadata(JDOMetadata metadata) {
      checkJDOPermission(JDOPermission.GET_METADATA);
      MetaDataManager mmgr = this.nucleusContext.getMetaDataManager();
      FileMetaData filemd = ((JDOMetadataImpl)metadata).getInternal();

      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);

         for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
            ClassMetaData cmd = pmd.getClass(j);
            if (mmgr.hasMetaDataForClass(cmd.getFullClassName())) {
               throw new JDOUserException("Cannot redefine metadata for " + cmd.getFullClassName());
            }
         }

         for(int j = 0; j < pmd.getNoOfInterfaces(); ++j) {
            InterfaceMetaData imd = pmd.getInterface(j);
            if (mmgr.hasMetaDataForClass(imd.getFullClassName())) {
               throw new JDOUserException("Cannot redefine metadata for " + imd.getFullClassName());
            }
         }
      }

      mmgr.loadUserMetaData(filemd, (ClassLoader)null);
   }

   public TypeMetadata getMetadata(String className) {
      MetaDataManager mmgr = this.nucleusContext.getMetaDataManager();
      AbstractClassMetaData acmd = mmgr.getMetaDataForClass(className, this.nucleusContext.getClassLoaderResolver((ClassLoader)null));
      if (acmd == null) {
         return null;
      } else {
         return (TypeMetadata)(acmd instanceof ClassMetaData ? new ClassMetadataImpl((ClassMetaData)acmd) : new InterfaceMetadataImpl((InterfaceMetaData)acmd));
      }
   }

   public Collection getManagedClasses() {
      checkJDOPermission(JDOPermission.GET_METADATA);
      MetaDataManager mmgr = this.nucleusContext.getMetaDataManager();
      Collection<String> classNames = mmgr.getClassesWithMetaData();
      Collection<Class> classes = new HashSet();
      if (classNames != null) {
         ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);
         Iterator<String> iter = classNames.iterator();

         while(iter.hasNext()) {
            try {
               Class cls = clr.classForName((String)iter.next());
               classes.add(cls);
            } catch (ClassNotResolvedException var7) {
            }
         }
      }

      return classes;
   }

   public void unmanageClass(String className) {
      MetaDataManager mmgr = this.nucleusContext.getMetaDataManager();
      mmgr.unloadMetaDataForClass(className);
      this.nucleusContext.getStoreManager().unmanageClass(this.nucleusContext.getClassLoaderResolver((ClassLoader)null), className, false);
   }

   public static void checkJDOPermission(JDOPermission jdoPermission) {
      SecurityManager secmgr = System.getSecurityManager();
      if (secmgr != null) {
         secmgr.checkPermission(jdoPermission);
      }

   }

   private void writeObject(ObjectOutputStream oos) throws IOException {
      oos.defaultWriteObject();
      oos.writeObject(this.nucleusContext.getConfiguration().getPersistenceProperties());
   }

   private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
      ois.defaultReadObject();
      this.deserialisationProps = (Map)ois.readObject();
   }

   private Object readResolve() throws InvalidObjectException {
      JDOPersistenceManagerFactory pmf = null;
      if (pmfByName != null) {
         String name = (String)this.deserialisationProps.get("datanucleus.Name");
         if (name == null) {
            name = (String)this.deserialisationProps.get("datanucleus.PersistenceUnitName");
         }

         pmf = (JDOPersistenceManagerFactory)pmfByName.get(name);
         if (pmf != null) {
            return pmf;
         }
      }

      this.configurable = true;
      if (this.pmCache == null) {
         this.pmCache = Collections.newSetFromMap(new ConcurrentHashMap());
      }

      this.nucleusContext = new PersistenceNucleusContextImpl("JDO", this.deserialisationProps);
      PersistenceUnitMetaData pumd = null;
      if (this.getPersistenceUnitName() != null) {
         pumd = this.nucleusContext.getMetaDataManager().getMetaDataForPersistenceUnit(this.getPersistenceUnitName());
      }

      this.initialiseMetaData(pumd);
      this.processLifecycleListenersFromProperties(this.deserialisationProps);
      this.freezeConfiguration();
      this.deserialisationProps = null;
      return this;
   }

   static {
      Localiser.registerBundle("org.datanucleus.api.jdo.Localisation", JDOPersistenceManagerFactory.class.getClassLoader());
      JDOImplHelper.getInstance().addStateInterrogation(new JDOStateInterrogation());
      OPTION_ARRAY = new String[]{"javax.jdo.option.TransientTransactional", "javax.jdo.option.NontransactionalWrite", "javax.jdo.option.NontransactionalRead", "javax.jdo.option.RetainValues", "javax.jdo.option.Optimistic", "javax.jdo.option.ApplicationIdentity", "javax.jdo.option.DatastoreIdentity", "javax.jdo.option.NonDurableIdentity", "javax.jdo.option.GetDataStoreConnection", "javax.jdo.option.GetJDBCConnection", "javax.jdo.option.version.DateTime", "javax.jdo.option.PreDirtyEvent", "javax.jdo.option.ArrayList", "javax.jdo.option.LinkedList", "javax.jdo.option.TreeSet", "javax.jdo.option.TreeMap", "javax.jdo.option.Vector", "javax.jdo.option.List", "javax.jdo.option.Stack", "javax.jdo.option.Map", "javax.jdo.option.HashMap", "javax.jdo.option.Hashtable", "javax.jdo.option.SortedSet", "javax.jdo.option.SortedMap", "javax.jdo.option.Array", "javax.jdo.option.NullCollection", "javax.jdo.option.mapping.HeterogeneousObjectType", "javax.jdo.option.mapping.HeterogeneousInterfaceType", "javax.jdo.option.mapping.JoinedTablePerClass", "javax.jdo.option.mapping.JoinedTablePerConcreteClass", "javax.jdo.option.mapping.NonJoinedTablePerConcreteClass", "javax.jdo.query.SQL", "javax.jdo.query.JDOQL", "javax.jdo.query.JDOQL.UnconstrainedQueryVariables"};
   }
}
