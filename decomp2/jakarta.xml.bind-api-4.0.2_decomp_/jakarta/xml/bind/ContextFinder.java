package jakarta.xml.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class ContextFinder {
   private static final Logger logger = Logger.getLogger("jakarta.xml.bind");
   static final String DEFAULT_FACTORY_CLASS = "org.glassfish.jaxb.runtime.v2.ContextFactory";
   private static ServiceLoaderUtil.ExceptionHandler EXCEPTION_HANDLER;

   private static Throwable handleInvocationTargetException(InvocationTargetException x) throws JAXBException {
      Throwable t = x.getTargetException();
      if (t != null) {
         if (t instanceof JAXBException) {
            throw (JAXBException)t;
         } else if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
         } else if (t instanceof Error) {
            throw (Error)t;
         } else {
            return t;
         }
      } else {
         return x;
      }
   }

   private static JAXBException handleClassCastException(Class originalType, Class targetType) {
      URL targetTypeURL = which(targetType);
      return new JAXBException(Messages.format("JAXBContext.IllegalCast", getClassClassLoader(originalType).getResource("jakarta/xml/bind/JAXBContext.class"), targetTypeURL));
   }

   static JAXBContext newInstance(String contextPath, Class[] contextPathClasses, String className, ClassLoader classLoader, Map properties) throws JAXBException {
      try {
         Class<?> spFactory = ServiceLoaderUtil.safeLoadClass(className, "org.glassfish.jaxb.runtime.v2.ContextFactory", classLoader);
         return newInstance(contextPath, contextPathClasses, spFactory, classLoader, properties);
      } catch (ClassNotFoundException x) {
         throw new JAXBException(Messages.format("ContextFinder.DefaultProviderNotFound"), x);
      } catch (JAXBException | RuntimeException x) {
         throw x;
      } catch (Exception x) {
         throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", className, x), x);
      }
   }

   static JAXBContext newInstance(String contextPath, Class[] contextPathClasses, Class spFactory, ClassLoader classLoader, Map properties) throws JAXBException {
      try {
         ModuleUtil.delegateAddOpensToImplModule(contextPathClasses, spFactory);
         Object context = null;

         try {
            Method m = spFactory.getMethod("createContext", String.class, ClassLoader.class, Map.class);
            Object obj = instantiateProviderIfNecessary(spFactory);
            context = m.invoke(obj, contextPath, classLoader, properties);
         } catch (NoSuchMethodException var8) {
         }

         if (context == null) {
            Method m = spFactory.getMethod("createContext", String.class, ClassLoader.class);
            Object obj = instantiateProviderIfNecessary(spFactory);
            context = m.invoke(obj, contextPath, classLoader);
         }

         if (!(context instanceof JAXBContext)) {
            throw handleClassCastException(context.getClass(), JAXBContext.class);
         } else {
            return (JAXBContext)context;
         }
      } catch (InvocationTargetException x) {
         Throwable e = handleInvocationTargetException(x);
         throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", spFactory, e), e);
      } catch (Exception x) {
         throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", spFactory, x), x);
      }
   }

   private static Object instantiateProviderIfNecessary(final Class implClass) throws JAXBException {
      try {
         return JAXBContextFactory.class.isAssignableFrom(implClass) ? AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Object run() throws Exception {
               return implClass.getConstructor().newInstance();
            }
         }) : null;
      } catch (PrivilegedActionException x) {
         Throwable e = (Throwable)(x.getCause() == null ? x : x.getCause());
         throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", implClass, e), e);
      }
   }

   private static JAXBContext newInstance(Class[] classes, Map properties, String className) throws JAXBException {
      return newInstance(classes, properties, className, getContextClassLoader());
   }

   private static JAXBContext newInstance(Class[] classes, Map properties, String className, ClassLoader loader) throws JAXBException {
      Class<?> spi;
      try {
         spi = ServiceLoaderUtil.safeLoadClass(className, "org.glassfish.jaxb.runtime.v2.ContextFactory", loader);
      } catch (ClassNotFoundException e) {
         throw new JAXBException(Messages.format("ContextFinder.DefaultProviderNotFound"), e);
      }

      if (logger.isLoggable(Level.FINE)) {
         logger.log(Level.FINE, "loaded {0} from {1}", new Object[]{className, which(spi)});
      }

      return newInstance(classes, properties, spi);
   }

   static JAXBContext newInstance(Class[] classes, Map properties, Class spFactory) throws JAXBException {
      try {
         ModuleUtil.delegateAddOpensToImplModule(classes, spFactory);
         Method m = spFactory.getMethod("createContext", Class[].class, Map.class);
         Object obj = instantiateProviderIfNecessary(spFactory);
         Object context = m.invoke(obj, classes, properties);
         if (!(context instanceof JAXBContext)) {
            throw handleClassCastException(context.getClass(), JAXBContext.class);
         } else {
            return (JAXBContext)context;
         }
      } catch (IllegalAccessException | NoSuchMethodException e) {
         throw new JAXBException(e);
      } catch (InvocationTargetException e) {
         Throwable x = handleInvocationTargetException(e);
         throw new JAXBException(x);
      }
   }

   static JAXBContext find(String factoryId, String contextPath, ClassLoader classLoader, Map properties) throws JAXBException {
      if (contextPath != null && !contextPath.isEmpty()) {
         Class<?>[] contextPathClasses = ModuleUtil.getClassesFromContextPath(contextPath, classLoader);
         String factoryName = classNameFromSystemProperties();
         if (factoryName != null) {
            return newInstance(contextPath, contextPathClasses, factoryName, classLoader, properties);
         } else {
            if (properties != null) {
               Object factory = properties.get(factoryId);
               if (factory != null) {
                  if (!(factory instanceof String)) {
                     throw new JAXBException(Messages.format("JAXBContext.IllegalCast", factory.getClass().getName(), "String"));
                  }

                  factoryName = (String)factory;
               }

               if (factoryName != null) {
                  return newInstance(contextPath, contextPathClasses, factoryName, classLoader, properties);
               }
            }

            JAXBContextFactory obj = (JAXBContextFactory)ServiceLoaderUtil.firstByServiceLoader(JAXBContextFactory.class, logger, EXCEPTION_HANDLER);
            if (obj != null) {
               ModuleUtil.delegateAddOpensToImplModule(contextPathClasses, obj.getClass());
               return obj.createContext(contextPath, classLoader, properties);
            } else {
               Iterable<Class<? extends JAXBContextFactory>> ctxFactories = ServiceLoaderUtil.lookupsUsingOSGiServiceLoader("jakarta.xml.bind.JAXBContextFactory", logger);
               if (ctxFactories != null) {
                  for(Class ctxFactory : ctxFactories) {
                     try {
                        return newInstance(contextPath, contextPathClasses, ctxFactory, classLoader, properties);
                     } catch (Throwable t) {
                        logger.log(Level.FINE, t, () -> "Error instantiating provider " + String.valueOf(ctxFactory));
                     }
                  }
               }

               logger.fine("Trying to create the platform default provider");
               return newInstance(contextPath, contextPathClasses, "org.glassfish.jaxb.runtime.v2.ContextFactory", classLoader, properties);
            }
         }
      } else {
         throw new JAXBException(Messages.format("ContextFinder.NoPackageInContextPath"));
      }
   }

   static JAXBContext find(Class[] classes, Map properties) throws JAXBException {
      String factoryClassName = classNameFromSystemProperties();
      if (factoryClassName != null) {
         return newInstance(classes, properties, factoryClassName);
      } else {
         if (properties != null) {
            Object ctxFactory = properties.get("jakarta.xml.bind.JAXBContextFactory");
            if (ctxFactory != null) {
               if (!(ctxFactory instanceof String)) {
                  throw new JAXBException(Messages.format("JAXBContext.IllegalCast", ctxFactory.getClass().getName(), "String"));
               }

               factoryClassName = (String)ctxFactory;
            }

            if (factoryClassName != null) {
               Map<String, ?> props = (Map)properties.entrySet().stream().filter(Predicate.not((e) -> "jakarta.xml.bind.JAXBContextFactory".equals(e.getKey()))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
               return newInstance(classes, props, factoryClassName);
            }
         }

         JAXBContextFactory factory = (JAXBContextFactory)ServiceLoaderUtil.firstByServiceLoader(JAXBContextFactory.class, logger, EXCEPTION_HANDLER);
         if (factory != null) {
            ModuleUtil.delegateAddOpensToImplModule(classes, factory.getClass());
            return factory.createContext(classes, properties);
         } else {
            logger.fine("Trying to create the platform default provider");
            Class<?> ctxFactoryClass = (Class)ServiceLoaderUtil.lookupUsingOSGiServiceLoader("jakarta.xml.bind.JAXBContextFactory", logger);
            if (ctxFactoryClass != null) {
               return newInstance(classes, properties, ctxFactoryClass);
            } else {
               logger.fine("Trying to create the platform default provider");
               return newInstance(classes, properties, "org.glassfish.jaxb.runtime.v2.ContextFactory");
            }
         }
      }
   }

   private static String classNameFromSystemProperties() throws JAXBException {
      String factoryClassName = getSystemProperty("jakarta.xml.bind.JAXBContextFactory");
      return factoryClassName != null ? factoryClassName : null;
   }

   private static String getSystemProperty(String property) {
      logger.log(Level.FINE, "Checking system property {0}", property);
      String value = (String)AccessController.doPrivileged(new GetPropertyAction(property));
      if (value != null) {
         logger.log(Level.FINE, "  found {0}", value);
      } else {
         logger.log(Level.FINE, "  not found");
      }

      return value;
   }

   static URL which(Class clazz, ClassLoader loader) {
      String var10000 = clazz.getName();
      String classnameAsResource = var10000.replace('.', '/') + ".class";
      if (loader == null) {
         loader = getSystemClassLoader();
      }

      return loader.getResource(classnameAsResource);
   }

   static URL which(Class clazz) {
      return which(clazz, getClassClassLoader(clazz));
   }

   private static ClassLoader getContextClassLoader() {
      return System.getSecurityManager() == null ? Thread.currentThread().getContextClassLoader() : (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return Thread.currentThread().getContextClassLoader();
         }
      });
   }

   private static ClassLoader getClassClassLoader(final Class c) {
      return System.getSecurityManager() == null ? c.getClassLoader() : (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return c.getClassLoader();
         }
      });
   }

   private static ClassLoader getSystemClassLoader() {
      return System.getSecurityManager() == null ? ClassLoader.getSystemClassLoader() : (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return ClassLoader.getSystemClassLoader();
         }
      });
   }

   static {
      try {
         if (AccessController.doPrivileged(new GetPropertyAction("jaxb.debug")) != null) {
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
         }
      } catch (Throwable var1) {
      }

      EXCEPTION_HANDLER = new ServiceLoaderUtil.ExceptionHandler() {
         public JAXBException createException(Throwable throwable, String message) {
            return new JAXBException(message, throwable);
         }
      };
   }
}
