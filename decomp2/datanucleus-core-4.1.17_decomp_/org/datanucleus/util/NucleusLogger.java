package org.datanucleus.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.datanucleus.exceptions.NucleusException;

public abstract class NucleusLogger {
   private static Class LOGGER_CLASS = null;
   public static final NucleusLogger PERSISTENCE;
   public static final NucleusLogger TRANSACTION;
   public static final NucleusLogger CONNECTION;
   public static final NucleusLogger QUERY;
   public static final NucleusLogger METADATA;
   public static final NucleusLogger CACHE;
   public static final NucleusLogger DATASTORE;
   public static final NucleusLogger DATASTORE_PERSIST;
   public static final NucleusLogger DATASTORE_RETRIEVE;
   public static final NucleusLogger DATASTORE_SCHEMA;
   public static final NucleusLogger DATASTORE_NATIVE;
   public static final NucleusLogger LIFECYCLE;
   public static final NucleusLogger GENERAL;
   public static final NucleusLogger VALUEGENERATION;

   public static NucleusLogger getLoggerInstance(String logCategory) {
      Class[] ctrTypes = new Class[]{String.class};
      Object[] ctrArgs = new Object[]{logCategory};

      Object obj;
      try {
         Constructor ctor = LOGGER_CLASS.getConstructor(ctrTypes);
         obj = ctor.newInstance(ctrArgs);
      } catch (NoSuchMethodException e) {
         throw (new NucleusException("Missing constructor in class " + LOGGER_CLASS.getName() + ", parameters " + Arrays.asList(ctrTypes).toString(), new Exception[]{e})).setFatal();
      } catch (IllegalAccessException e) {
         throw (new NucleusException("Failed attempting to access class " + LOGGER_CLASS.getName(), new Exception[]{e})).setFatal();
      } catch (InstantiationException e) {
         throw (new NucleusException("Failed instantiating a new object of type " + LOGGER_CLASS.getName(), new Exception[]{e})).setFatal();
      } catch (InvocationTargetException e) {
         Throwable t = e.getTargetException();
         if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
         }

         if (t instanceof Error) {
            throw (Error)t;
         }

         throw (new NucleusException("Unexpected exception thrown by constructor for " + LOGGER_CLASS.getName() + "," + t)).setFatal();
      }

      return (NucleusLogger)obj;
   }

   public abstract void debug(Object var1);

   public abstract void debug(Object var1, Throwable var2);

   public abstract void info(Object var1);

   public abstract void info(Object var1, Throwable var2);

   public abstract void warn(Object var1);

   public abstract void warn(Object var1, Throwable var2);

   public abstract void error(Object var1);

   public abstract void error(Object var1, Throwable var2);

   public abstract void fatal(Object var1);

   public abstract void fatal(Object var1, Throwable var2);

   public abstract boolean isDebugEnabled();

   public abstract boolean isInfoEnabled();

   static {
      Class loggerClass = null;

      try {
         NucleusLogger.class.getClassLoader().loadClass("org.apache.log4j.Logger");
         loggerClass = Log4JLogger.class;
      } catch (Exception var2) {
         loggerClass = JDK14Logger.class;
      }

      LOGGER_CLASS = loggerClass;
      PERSISTENCE = getLoggerInstance("DataNucleus.Persistence");
      TRANSACTION = getLoggerInstance("DataNucleus.Transaction");
      CONNECTION = getLoggerInstance("DataNucleus.Connection");
      QUERY = getLoggerInstance("DataNucleus.Query");
      METADATA = getLoggerInstance("DataNucleus.MetaData");
      CACHE = getLoggerInstance("DataNucleus.Cache");
      DATASTORE = getLoggerInstance("DataNucleus.Datastore");
      DATASTORE_PERSIST = getLoggerInstance("DataNucleus.Datastore.Persist");
      DATASTORE_RETRIEVE = getLoggerInstance("DataNucleus.Datastore.Retrieve");
      DATASTORE_SCHEMA = getLoggerInstance("DataNucleus.Datastore.Schema");
      DATASTORE_NATIVE = getLoggerInstance("DataNucleus.Datastore.Native");
      LIFECYCLE = getLoggerInstance("DataNucleus.Lifecycle");
      GENERAL = getLoggerInstance("DataNucleus.General");
      VALUEGENERATION = getLoggerInstance("DataNucleus.ValueGeneration");
   }
}
