package javax.jdo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.jdo.spi.I18NHelper;
import javax.jdo.spi.JDOImplHelper;
import javax.jdo.spi.PersistenceCapable;
import javax.jdo.spi.StateInterrogation;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class JDOHelper implements Constants {
   static final Map ATTRIBUTE_PROPERTY_XREF = createAttributePropertyXref();
   private static final I18NHelper msg = I18NHelper.getInstance("javax.jdo.Bundle");
   private static JDOImplHelper implHelper = (JDOImplHelper)AccessController.doPrivileged(new PrivilegedAction() {
      public JDOImplHelper run() {
         return JDOImplHelper.getInstance();
      }
   });
   private static JDOHelper instance = new JDOHelper();
   static JDOImplHelper.StateInterrogationObjectReturn getPersistenceManager = new JDOImplHelper.StateInterrogationObjectReturn() {
      public Object get(Object pc, StateInterrogation si) {
         return si.getPersistenceManager(pc);
      }
   };
   static JDOImplHelper.StateInterrogationObjectReturn getObjectId = new JDOImplHelper.StateInterrogationObjectReturn() {
      public Object get(Object pc, StateInterrogation si) {
         return si.getObjectId(pc);
      }
   };
   static JDOImplHelper.StateInterrogationObjectReturn getTransactionalObjectId = new JDOImplHelper.StateInterrogationObjectReturn() {
      public Object get(Object pc, StateInterrogation si) {
         return si.getTransactionalObjectId(pc);
      }
   };
   static JDOImplHelper.StateInterrogationObjectReturn getVersion = new JDOImplHelper.StateInterrogationObjectReturn() {
      public Object get(Object pc, StateInterrogation si) {
         return si.getVersion(pc);
      }
   };
   static JDOImplHelper.StateInterrogationBooleanReturn isPersistent = new JDOImplHelper.StateInterrogationBooleanReturn() {
      public Boolean is(Object pc, StateInterrogation si) {
         return si.isPersistent(pc);
      }
   };
   static JDOImplHelper.StateInterrogationBooleanReturn isTransactional = new JDOImplHelper.StateInterrogationBooleanReturn() {
      public Boolean is(Object pc, StateInterrogation si) {
         return si.isTransactional(pc);
      }
   };
   static JDOImplHelper.StateInterrogationBooleanReturn isDirty = new JDOImplHelper.StateInterrogationBooleanReturn() {
      public Boolean is(Object pc, StateInterrogation si) {
         return si.isDirty(pc);
      }
   };
   static JDOImplHelper.StateInterrogationBooleanReturn isNew = new JDOImplHelper.StateInterrogationBooleanReturn() {
      public Boolean is(Object pc, StateInterrogation si) {
         return si.isNew(pc);
      }
   };
   static JDOImplHelper.StateInterrogationBooleanReturn isDeleted = new JDOImplHelper.StateInterrogationBooleanReturn() {
      public Boolean is(Object pc, StateInterrogation si) {
         return si.isDeleted(pc);
      }
   };
   static JDOImplHelper.StateInterrogationBooleanReturn isDetached = new JDOImplHelper.StateInterrogationBooleanReturn() {
      public Boolean is(Object pc, StateInterrogation si) {
         return si.isDetached(pc);
      }
   };

   static Map createAttributePropertyXref() {
      Map<String, String> xref = new HashMap();
      xref.put("class", "javax.jdo.PersistenceManagerFactoryClass");
      xref.put("connection-driver-name", "javax.jdo.option.ConnectionDriverName");
      xref.put("connection-factory-name", "javax.jdo.option.ConnectionFactoryName");
      xref.put("connection-factory2-name", "javax.jdo.option.ConnectionFactory2Name");
      xref.put("connection-password", "javax.jdo.option.ConnectionPassword");
      xref.put("connection-url", "javax.jdo.option.ConnectionURL");
      xref.put("connection-user-name", "javax.jdo.option.ConnectionUserName");
      xref.put("ignore-cache", "javax.jdo.option.IgnoreCache");
      xref.put("mapping", "javax.jdo.option.Mapping");
      xref.put("multithreaded", "javax.jdo.option.Multithreaded");
      xref.put("nontransactional-read", "javax.jdo.option.NontransactionalRead");
      xref.put("nontransactional-write", "javax.jdo.option.NontransactionalWrite");
      xref.put("optimistic", "javax.jdo.option.Optimistic");
      xref.put("persistence-unit-name", "javax.jdo.option.PersistenceUnitName");
      xref.put("name", "javax.jdo.option.Name");
      xref.put("restore-values", "javax.jdo.option.RestoreValues");
      xref.put("retain-values", "javax.jdo.option.RetainValues");
      xref.put("detach-all-on-commit", "javax.jdo.option.DetachAllOnCommit");
      xref.put("server-time-zone-id", "javax.jdo.option.ServerTimeZoneID");
      xref.put("datastore-read-timeout-millis", "javax.jdo.option.DatastoreReadTimeoutMillis");
      xref.put("datastore-write-timeout-millis", "javax.jdo.option.DatastoreWriteTimeoutMillis");
      return Collections.unmodifiableMap(xref);
   }

   public static JDOHelper getInstance() {
      return instance;
   }

   public static PersistenceManager getPersistenceManager(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoGetPersistenceManager() : (PersistenceManager)implHelper.nonBinaryCompatibleGet(pc, getPersistenceManager);
   }

   public static void makeDirty(Object pc, String fieldName) {
      if (pc instanceof PersistenceCapable) {
         ((PersistenceCapable)pc).jdoMakeDirty(fieldName);
      } else {
         implHelper.nonBinaryCompatibleMakeDirty(pc, fieldName);
      }

   }

   public static Object getObjectId(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoGetObjectId() : implHelper.nonBinaryCompatibleGet(pc, getObjectId);
   }

   public static Collection getObjectIds(Collection pcs) {
      ArrayList<Object> result = new ArrayList();
      Iterator<?> it = pcs.iterator();

      while(it.hasNext()) {
         result.add(getObjectId(it.next()));
      }

      return result;
   }

   public static Object[] getObjectIds(Object[] pcs) {
      Object[] result = new Object[pcs.length];

      for(int i = 0; i < pcs.length; ++i) {
         result[i] = getObjectId(pcs[i]);
      }

      return result;
   }

   public static Object getTransactionalObjectId(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoGetTransactionalObjectId() : implHelper.nonBinaryCompatibleGet(pc, getTransactionalObjectId);
   }

   public static Object getVersion(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoGetVersion() : implHelper.nonBinaryCompatibleGet(pc, getVersion);
   }

   public static boolean isDirty(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoIsDirty() : implHelper.nonBinaryCompatibleIs(pc, isDirty);
   }

   public static boolean isTransactional(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoIsTransactional() : implHelper.nonBinaryCompatibleIs(pc, isTransactional);
   }

   public static boolean isPersistent(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoIsPersistent() : implHelper.nonBinaryCompatibleIs(pc, isPersistent);
   }

   public static boolean isNew(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoIsNew() : implHelper.nonBinaryCompatibleIs(pc, isNew);
   }

   public static boolean isDeleted(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoIsDeleted() : implHelper.nonBinaryCompatibleIs(pc, isDeleted);
   }

   public static boolean isDetached(Object pc) {
      return pc instanceof PersistenceCapable ? ((PersistenceCapable)pc).jdoIsDetached() : implHelper.nonBinaryCompatibleIs(pc, isDetached);
   }

   public static ObjectState getObjectState(Object pc) {
      if (pc == null) {
         return null;
      } else if (isDetached(pc)) {
         return isDirty(pc) ? ObjectState.DETACHED_DIRTY : ObjectState.DETACHED_CLEAN;
      } else if (isPersistent(pc)) {
         if (isTransactional(pc)) {
            if (isDirty(pc)) {
               if (isNew(pc)) {
                  return isDeleted(pc) ? ObjectState.PERSISTENT_NEW_DELETED : ObjectState.PERSISTENT_NEW;
               } else {
                  return isDeleted(pc) ? ObjectState.PERSISTENT_DELETED : ObjectState.PERSISTENT_DIRTY;
               }
            } else {
               return ObjectState.PERSISTENT_CLEAN;
            }
         } else {
            return isDirty(pc) ? ObjectState.PERSISTENT_NONTRANSACTIONAL_DIRTY : ObjectState.HOLLOW_PERSISTENT_NONTRANSACTIONAL;
         }
      } else if (isTransactional(pc)) {
         return isDirty(pc) ? ObjectState.TRANSIENT_DIRTY : ObjectState.TRANSIENT_CLEAN;
      } else {
         return ObjectState.TRANSIENT;
      }
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory() {
      ClassLoader cl = getContextClassLoader();
      return getPersistenceManagerFactory((Map)null, "", cl, cl);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(ClassLoader pmfClassLoader) {
      return getPersistenceManagerFactory((Map)null, "", pmfClassLoader, pmfClassLoader);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(Map props) {
      return getPersistenceManagerFactory((Map)null, (Map)props, getContextClassLoader());
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(Map props, ClassLoader pmfClassLoader) {
      return getPersistenceManagerFactory((Map)null, (Map)props, pmfClassLoader);
   }

   protected static PersistenceManagerFactory getPersistenceManagerFactory(Map overrides, Map props, ClassLoader pmfClassLoader) {
      List<Throwable> exceptions = new ArrayList();
      if (pmfClassLoader == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullLoader"));
      } else {
         JDOImplHelper.assertOnlyKnownStandardProperties(overrides);
         JDOImplHelper.assertOnlyKnownStandardProperties(props);
         String pmfClassName = (String)props.get("javax.jdo.PersistenceManagerFactoryClass");
         if (!isNullOrBlank(pmfClassName)) {
            return invokeGetPersistenceManagerFactoryOnImplementation(pmfClassName, overrides, props, pmfClassLoader);
         } else {
            Enumeration<URL> urls = null;

            try {
               urls = getResources(pmfClassLoader, "META-INF/services/javax.jdo.PersistenceManagerFactory");
            } catch (Throwable ex) {
               exceptions.add(ex);
            }

            if (urls != null) {
               while(urls.hasMoreElements()) {
                  try {
                     pmfClassName = getClassNameFromURL((URL)urls.nextElement());
                     PersistenceManagerFactory pmf = invokeGetPersistenceManagerFactoryOnImplementation(pmfClassName, overrides, props, pmfClassLoader);
                     return pmf;
                  } catch (Throwable ex) {
                     exceptions.add(ex);
                  }
               }
            }

            throw new JDOFatalUserException(msg.msg("EXC_GetPMFNoPMFClassNamePropertyOrPUNameProperty"), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
         }
      }
   }

   protected static String getClassNameFromURL(URL url) throws IOException {
      InputStream is = openStream(url);
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line = null;

      try {
         while(true) {
            if ((line = reader.readLine()) != null) {
               line = line.trim();
               if (line.length() == 0 || line.startsWith("#")) {
                  continue;
               }

               String[] tokens = line.split("\\s");
               String pmfClassName = tokens[0];
               int indexOfComment = pmfClassName.indexOf("#");
               if (indexOfComment != -1) {
                  String var21 = pmfClassName.substring(0, indexOfComment);
                  return var21;
               }

               String var7 = pmfClassName;
               return var7;
            }

            Object tokens = null;
            return (String)tokens;
         }
      } finally {
         try {
            reader.close();
         } catch (IOException var16) {
         }

      }
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(String name) {
      ClassLoader cl = getContextClassLoader();
      return getPersistenceManagerFactory((Map)null, name, cl, cl);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(String name, ClassLoader loader) {
      return getPersistenceManagerFactory((Map)null, name, loader, loader);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(String name, ClassLoader resourceLoader, ClassLoader pmfLoader) {
      return getPersistenceManagerFactory((Map)null, name, resourceLoader, pmfLoader);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(Map overrides, String name) {
      ClassLoader cl = getContextClassLoader();
      return getPersistenceManagerFactory(overrides, name, cl, cl);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(Map overrides, String name, ClassLoader resourceLoader) {
      return getPersistenceManagerFactory(overrides, name, resourceLoader, resourceLoader);
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(Map overrides, String name, ClassLoader resourceLoader, ClassLoader pmfLoader) {
      if (pmfLoader == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullPMFLoader"));
      } else if (resourceLoader == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullPropsLoader"));
      } else {
         Map<Object, Object> props = null;
         name = name == null ? "" : name.trim();
         if (!"".equals(name)) {
            props = loadPropertiesFromResource(resourceLoader, name);
         }

         if (props != null) {
            props.put("javax.jdo.option.spi.ResourceName", name);
            props.remove("javax.jdo.option.Name");
            return getPersistenceManagerFactory(overrides, props, pmfLoader);
         } else {
            props = getPropertiesFromJdoconfig(name, pmfLoader);
            if (props != null) {
               props.put("javax.jdo.option.Name", name);
               props.remove("javax.jdo.option.spi.ResourceName");
               return getPersistenceManagerFactory(overrides, props, pmfLoader);
            } else if (!"".equals(name)) {
               props = new Properties();
               props.put("javax.jdo.option.PersistenceUnitName", name);
               return getPersistenceManagerFactory(overrides, props, pmfLoader);
            } else {
               throw new JDOFatalUserException(msg.msg("EXC_NoPMFConfigurableViaPropertiesOrXML", (Object)name));
            }
         }
      }
   }

   protected static PersistenceManagerFactory invokeGetPersistenceManagerFactoryOnImplementation(String pmfClassName, Map overrides, Map properties, ClassLoader cl) {
      if (overrides != null) {
         try {
            Class<?> implClass = forName(pmfClassName, true, cl);
            Method m = getMethod(implClass, "getPersistenceManagerFactory", new Class[]{Map.class, Map.class});
            PersistenceManagerFactory pmf = (PersistenceManagerFactory)invoke(m, (Object)null, new Object[]{overrides, properties});
            if (pmf == null) {
               throw new JDOFatalInternalException(msg.msg("EXC_GetPMFNullPMF", (Object)pmfClassName));
            } else {
               return pmf;
            }
         } catch (ClassNotFoundException e) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFClassNotFound", (Object)pmfClassName), e);
         } catch (NoSuchMethodException e) {
            throw new JDOFatalInternalException(msg.msg("EXC_GetPMFNoSuchMethod2", (Object)pmfClassName), e);
         } catch (NullPointerException e) {
            throw new JDOFatalInternalException(msg.msg("EXC_GetPMFNullPointerException", (Object)pmfClassName), e);
         } catch (IllegalAccessException e) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFIllegalAccess", (Object)pmfClassName), e);
         } catch (ClassCastException e) {
            throw new JDOFatalInternalException(msg.msg("EXC_GetPMFClassCastException", (Object)pmfClassName), e);
         } catch (InvocationTargetException ite) {
            Throwable nested = ite.getTargetException();
            if (nested instanceof JDOException) {
               throw (JDOException)nested;
            } else {
               throw new JDOFatalInternalException(msg.msg("EXC_GetPMFUnexpectedException"), ite);
            }
         }
      } else {
         try {
            Class<?> implClass = forName(pmfClassName, true, cl);
            Method m = getMethod(implClass, "getPersistenceManagerFactory", new Class[]{Map.class});
            PersistenceManagerFactory pmf = (PersistenceManagerFactory)invoke(m, (Object)null, new Object[]{properties});
            if (pmf == null) {
               throw new JDOFatalInternalException(msg.msg("EXC_GetPMFNullPMF", (Object)pmfClassName));
            } else {
               return pmf;
            }
         } catch (ClassNotFoundException e) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFClassNotFound", (Object)pmfClassName), e);
         } catch (NoSuchMethodException e) {
            throw new JDOFatalInternalException(msg.msg("EXC_GetPMFNoSuchMethod", (Object)pmfClassName), e);
         } catch (NullPointerException e) {
            throw new JDOFatalInternalException(msg.msg("EXC_GetPMFNullPointerException", (Object)pmfClassName), e);
         } catch (IllegalAccessException e) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFIllegalAccess", (Object)pmfClassName), e);
         } catch (ClassCastException e) {
            throw new JDOFatalInternalException(msg.msg("EXC_GetPMFClassCastException", (Object)pmfClassName), e);
         } catch (InvocationTargetException ite) {
            Throwable nested = ite.getTargetException();
            if (nested instanceof JDOException) {
               throw (JDOException)nested;
            } else {
               throw new JDOFatalInternalException(msg.msg("EXC_GetPMFUnexpectedException"), ite);
            }
         }
      }
   }

   protected static Map loadPropertiesFromResource(ClassLoader resourceLoader, String name) {
      InputStream in = null;
      Properties props = null;

      try {
         in = getResourceAsStream(resourceLoader, name);
         if (in != null) {
            props = new Properties();
            props.load(in);
         }
      } catch (IOException ioe) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFIOExceptionRsrc", (Object)name), ioe);
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (IOException var11) {
            }
         }

      }

      return props;
   }

   protected static Map getPropertiesFromJdoconfig(String name, ClassLoader resourceLoader) {
      return getNamedPMFProperties(name, resourceLoader, "META-INF/jdoconfig.xml");
   }

   protected static Map getNamedPMFProperties(String name, ClassLoader resourceLoader, String jdoconfigResourceName) {
      Map<String, Map<Object, Object>> propertiesByNameInAllConfigs = new HashMap();

      try {
         URL firstFoundConfigURL = null;
         Enumeration<URL> resources = getResources(resourceLoader, jdoconfigResourceName);
         if (resources.hasMoreElements()) {
            ArrayList<URL> processedResources = new ArrayList();
            DocumentBuilderFactory factory = getDocumentBuilderFactory();

            do {
               URL currentConfigURL = (URL)resources.nextElement();
               if (!processedResources.contains(currentConfigURL)) {
                  processedResources.add(currentConfigURL);
                  Map<String, Map<Object, Object>> propertiesByNameInCurrentConfig = readNamedPMFProperties(currentConfigURL, name, factory);
                  if (propertiesByNameInCurrentConfig.containsKey(name)) {
                     if (firstFoundConfigURL == null) {
                        firstFoundConfigURL = currentConfigURL;
                     }

                     if (propertiesByNameInAllConfigs.containsKey(name)) {
                        throw new JDOFatalUserException(msg.msg("EXC_DuplicateRequestedNamedPMFFoundInDifferentConfigs", "".equals(name) ? "(anonymous)" : name, firstFoundConfigURL.toExternalForm(), currentConfigURL.toExternalForm()));
                     }
                  }

                  propertiesByNameInAllConfigs.putAll(propertiesByNameInCurrentConfig);
               }
            } while(resources.hasMoreElements());
         }
      } catch (FactoryConfigurationError e) {
         throw new JDOFatalUserException(msg.msg("ERR_NoDocumentBuilderFactory"), e);
      } catch (IOException ioe) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFIOExceptionRsrc", (Object)name), ioe);
      }

      return (Map)propertiesByNameInAllConfigs.get(name);
   }

   protected static DocumentBuilderFactory getDocumentBuilderFactory() {
      JDOImplHelper var10000 = implHelper;
      DocumentBuilderFactory factory = JDOImplHelper.getRegisteredDocumentBuilderFactory();
      if (factory == null) {
         factory = getDefaultDocumentBuilderFactory();
      }

      return factory;
   }

   protected static DocumentBuilderFactory getDefaultDocumentBuilderFactory() {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setIgnoringComments(true);
      factory.setNamespaceAware(true);
      factory.setValidating(false);
      factory.setIgnoringElementContentWhitespace(true);
      factory.setExpandEntityReferences(true);
      return factory;
   }

   protected static ErrorHandler getErrorHandler() {
      JDOImplHelper var10000 = implHelper;
      ErrorHandler handler = JDOImplHelper.getRegisteredErrorHandler();
      if (handler == null) {
         handler = getDefaultErrorHandler();
      }

      return handler;
   }

   protected static ErrorHandler getDefaultErrorHandler() {
      return new ErrorHandler() {
         public void error(SAXParseException exception) throws SAXException {
            throw exception;
         }

         public void fatalError(SAXParseException exception) throws SAXException {
            throw exception;
         }

         public void warning(SAXParseException exception) throws SAXException {
         }
      };
   }

   protected static Map readNamedPMFProperties(URL url, String requestedPMFName, DocumentBuilderFactory factory) {
      requestedPMFName = requestedPMFName == null ? "" : requestedPMFName.trim();
      Map<String, Map<Object, Object>> propertiesByName = new HashMap();
      InputStream in = null;

      Object var38;
      try {
         DocumentBuilder builder = factory.newDocumentBuilder();
         builder.setErrorHandler(getErrorHandler());
         in = openStream(url);
         Document doc = builder.parse(in);
         Element root = doc.getDocumentElement();
         if (root == null) {
            throw new JDOFatalUserException(msg.msg("EXC_InvalidJDOConfigNoRoot", (Object)url.toExternalForm()));
         }

         NodeList pmfs = root.getElementsByTagName("persistence-manager-factory");

         for(int i = 0; i < pmfs.getLength(); ++i) {
            Node pmfElement = pmfs.item(i);
            Properties pmfPropertiesFromAttributes = readPropertiesFromPMFElementAttributes(pmfElement);
            Properties pmfPropertiesFromElements = readPropertiesFromPMFSubelements(pmfElement, url);
            String pmfNameFromAtts = pmfPropertiesFromAttributes.getProperty("javax.jdo.option.Name");
            String pmfNameFromElem = pmfPropertiesFromElements.getProperty("javax.jdo.option.Name");
            String pmfName = null;
            if (isNullOrBlank(pmfNameFromAtts)) {
               if (!isNullOrBlank(pmfNameFromElem)) {
                  pmfName = pmfNameFromElem;
               } else {
                  pmfName = "";
               }
            } else {
               if (!isNullOrBlank(pmfNameFromElem)) {
                  throw new JDOFatalUserException(msg.msg("EXC_DuplicatePMFNamePropertyFoundWithinConfig", pmfNameFromAtts, pmfNameFromElem, url.toExternalForm()));
               }

               pmfName = pmfNameFromAtts;
            }

            pmfName = pmfName == null ? "" : pmfName.trim();
            if (requestedPMFName.equals(pmfName)) {
               for(String property : pmfPropertiesFromAttributes.keySet()) {
                  if (pmfPropertiesFromElements.contains(property)) {
                     throw new JDOFatalUserException(msg.msg("EXC_DuplicatePropertyFound", property, pmfName, url.toExternalForm()));
                  }
               }
            }

            Properties pmfProps = new Properties();
            pmfProps.putAll(pmfPropertiesFromAttributes);
            pmfProps.putAll(pmfPropertiesFromElements);
            if (pmfName.equals(requestedPMFName) && propertiesByName.containsKey(pmfName)) {
               throw new JDOFatalUserException(msg.msg("EXC_DuplicateRequestedNamedPMFFoundInSameConfig", pmfName, url.toExternalForm()));
            }

            propertiesByName.put(pmfName, pmfProps);
         }

         var38 = propertiesByName;
      } catch (IOException ioe) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFIOExceptionRsrc", (Object)url.toString()), ioe);
      } catch (ParserConfigurationException e) {
         throw new JDOFatalInternalException(msg.msg("EXC_ParserConfigException"), e);
      } catch (SAXParseException e) {
         throw new JDOFatalUserException(msg.msg("EXC_SAXParseException", url.toExternalForm(), new Integer(e.getLineNumber()), new Integer(e.getColumnNumber())), e);
      } catch (SAXException e) {
         throw new JDOFatalUserException(msg.msg("EXC_SAXException", (Object)url.toExternalForm()), e);
      } catch (JDOException e) {
         throw e;
      } catch (RuntimeException e) {
         throw new JDOFatalUserException(msg.msg("EXC_SAXException", (Object)url.toExternalForm()), e);
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (IOException var29) {
            }
         }

      }

      return (Map)var38;
   }

   protected static Properties readPropertiesFromPMFElementAttributes(Node pmfElement) {
      Properties p = new Properties();
      NamedNodeMap attributes = pmfElement.getAttributes();
      if (attributes == null) {
         return p;
      } else {
         for(int i = 0; i < attributes.getLength(); ++i) {
            Node att = attributes.item(i);
            String attName = att.getNodeName();
            String attValue = att.getNodeValue().trim();
            String jdoPropertyName = (String)ATTRIBUTE_PROPERTY_XREF.get(attName);
            p.put(jdoPropertyName != null ? jdoPropertyName : attName, attValue);
         }

         return p;
      }
   }

   protected static Properties readPropertiesFromPMFSubelements(Node pmfElement, URL url) {
      Properties p = new Properties();
      NodeList elements = pmfElement.getChildNodes();
      if (elements == null) {
         return p;
      } else {
         for(int i = 0; i < elements.getLength(); ++i) {
            Node element = elements.item(i);
            if (element.getNodeType() == 1) {
               String elementName = element.getNodeName();
               NamedNodeMap attributes = element.getAttributes();
               if ("property".equalsIgnoreCase(elementName)) {
                  Node nameAtt = attributes.getNamedItem("name");
                  if (nameAtt == null) {
                     throw new JDOFatalUserException(msg.msg("EXC_PropertyElementHasNoNameAttribute", (Object)url));
                  }

                  String name = nameAtt.getNodeValue().trim();
                  if ("".equals(name)) {
                     throw new JDOFatalUserException(msg.msg("EXC_PropertyElementNameAttributeHasNoValue", name, url));
                  }

                  String jdoPropertyName = (String)ATTRIBUTE_PROPERTY_XREF.get(name);
                  String propertyName = jdoPropertyName != null ? jdoPropertyName : name;
                  if (p.containsKey(propertyName)) {
                     throw new JDOFatalUserException(msg.msg("EXC_DuplicatePropertyNameGivenInPropertyElement", propertyName, url));
                  }

                  Node valueAtt = attributes.getNamedItem("value");
                  String value = valueAtt == null ? null : valueAtt.getNodeValue().trim();
                  p.put(propertyName, value);
               } else if ("instance-lifecycle-listener".equals(elementName)) {
                  Node listenerAtt = attributes.getNamedItem("listener");
                  if (listenerAtt == null) {
                     throw new JDOFatalUserException(msg.msg("EXC_MissingListenerAttribute", (Object)url));
                  }

                  String listener = listenerAtt.getNodeValue().trim();
                  if ("".equals(listener)) {
                     throw new JDOFatalUserException(msg.msg("EXC_MissingListenerAttributeValue", (Object)url));
                  }

                  listener = "javax.jdo.listener.InstanceLifecycleListener." + listener;
                  Node classesAtt = attributes.getNamedItem("classes");
                  String value = classesAtt == null ? "" : classesAtt.getNodeValue().trim();
                  p.put(listener, value);
               }
            }
         }

         return p;
      }
   }

   protected static boolean isNullOrBlank(String s) {
      return s == null || "".equals(s.trim());
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(File propsFile) {
      return getPersistenceManagerFactory(propsFile, getContextClassLoader());
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(File propsFile, ClassLoader loader) {
      if (propsFile == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullFile"));
      } else {
         InputStream in = null;

         PersistenceManagerFactory var3;
         try {
            in = new FileInputStream(propsFile);
            var3 = getPersistenceManagerFactory(in, loader);
         } catch (FileNotFoundException fnfe) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFNoFile", (Object)propsFile), fnfe);
         } finally {
            if (in != null) {
               try {
                  in.close();
               } catch (IOException var11) {
               }
            }

         }

         return var3;
      }
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(String jndiLocation, Context context) {
      return getPersistenceManagerFactory(jndiLocation, context, getContextClassLoader());
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(String jndiLocation, Context context, ClassLoader loader) {
      if (jndiLocation == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullJndiLoc"));
      } else if (loader == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullLoader"));
      } else {
         try {
            if (context == null) {
               context = new InitialContext();
            }

            Object o = context.lookup(jndiLocation);
            return (PersistenceManagerFactory)PortableRemoteObject.narrow(o, PersistenceManagerFactory.class);
         } catch (NamingException ne) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFNamingException", jndiLocation, loader), ne);
         }
      }
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(InputStream stream) {
      return getPersistenceManagerFactory(stream, getContextClassLoader());
   }

   public static PersistenceManagerFactory getPersistenceManagerFactory(InputStream stream, ClassLoader loader) {
      if (stream == null) {
         throw new JDOFatalUserException(msg.msg("EXC_GetPMFNullStream"));
      } else {
         Properties props = new Properties();

         try {
            props.load(stream);
         } catch (IOException ioe) {
            throw new JDOFatalUserException(msg.msg("EXC_GetPMFIOExceptionStream"), ioe);
         }

         return getPersistenceManagerFactory((Map)props, (ClassLoader)loader);
      }
   }

   public static JDOEnhancer getEnhancer() {
      return getEnhancer(getContextClassLoader());
   }

   public static JDOEnhancer getEnhancer(ClassLoader loader) {
      ClassLoader ctrLoader = loader;
      if (loader == null) {
         ctrLoader = Thread.currentThread().getContextClassLoader();
      }

      ArrayList<Throwable> exceptions = new ArrayList();
      int numberOfJDOEnhancers = 0;

      try {
         Enumeration<URL> urls = getResources(loader, "META-INF/services/javax.jdo.JDOEnhancer");
         if (urls != null) {
            while(urls.hasMoreElements()) {
               ++numberOfJDOEnhancers;

               try {
                  String enhancerClassName = getClassNameFromURL((URL)urls.nextElement());
                  Class<?> enhancerClass = forName(enhancerClassName, true, ctrLoader);
                  JDOEnhancer enhancer = (JDOEnhancer)enhancerClass.newInstance();
                  return enhancer;
               } catch (Throwable ex) {
                  exceptions.add(ex);
               }
            }
         }
      } catch (Throwable ex) {
         exceptions.add(ex);
      }

      throw new JDOFatalUserException(msg.msg("EXC_GetEnhancerNoValidEnhancerAvailable", numberOfJDOEnhancers), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
   }

   private static ClassLoader getContextClassLoader() {
      return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return Thread.currentThread().getContextClassLoader();
         }
      });
   }

   private static InputStream getResourceAsStream(final ClassLoader resourceLoader, final String name) {
      return (InputStream)AccessController.doPrivileged(new PrivilegedAction() {
         public InputStream run() {
            return resourceLoader.getResourceAsStream(name);
         }
      });
   }

   private static Method getMethod(final Class implClass, final String methodName, final Class[] parameterTypes) throws NoSuchMethodException {
      try {
         return (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Method run() throws NoSuchMethodException {
               return implClass.getMethod(methodName, parameterTypes);
            }
         });
      } catch (PrivilegedActionException ex) {
         throw (NoSuchMethodException)ex.getException();
      }
   }

   private static Object invoke(final Method method, final Object instance, final Object[] parameters) throws IllegalAccessException, InvocationTargetException {
      try {
         return AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws IllegalAccessException, InvocationTargetException {
               return method.invoke(instance, parameters);
            }
         });
      } catch (PrivilegedActionException ex) {
         Exception cause = ex.getException();
         if (cause instanceof IllegalAccessException) {
            throw (IllegalAccessException)cause;
         } else {
            throw (InvocationTargetException)cause;
         }
      }
   }

   protected static Enumeration getResources(final ClassLoader resourceLoader, final String resourceName) throws IOException {
      try {
         return (Enumeration)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Enumeration run() throws IOException {
               return resourceLoader.getResources(resourceName);
            }
         });
      } catch (PrivilegedActionException ex) {
         throw (IOException)ex.getException();
      }
   }

   private static Class forName(final String name, final boolean init, final ClassLoader loader) throws ClassNotFoundException {
      try {
         return (Class)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Class run() throws ClassNotFoundException {
               return Class.forName(name, init, loader);
            }
         });
      } catch (PrivilegedActionException ex) {
         throw (ClassNotFoundException)ex.getException();
      }
   }

   private static InputStream openStream(final URL url) throws IOException {
      try {
         return (InputStream)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public InputStream run() throws IOException {
               return url.openStream();
            }
         });
      } catch (PrivilegedActionException ex) {
         throw (IOException)ex.getException();
      }
   }
}
