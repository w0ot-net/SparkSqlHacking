package org.apache.derby.iapi.services.monitor;

import java.io.PrintWriter;
import java.security.AccessControlException;
import java.util.Locale;
import java.util.Properties;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.loader.InstanceGetter;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.impl.services.monitor.FileMonitor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.info.ProductVersionHolder;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class Monitor {
   public static final String SERVICE_TYPE_DIRECTORY = "serviceDirectory";
   public static final Object syncMe = new Object();
   public static final String NEW_INSTANCE_FROM_ID_TRACE_DEBUG_FLAG = null;
   public static final String DEBUG_TRUE = null;
   public static final String DEBUG_FALSE = null;
   private static ModuleFactory monitor;
   private static boolean active;

   public static void startMonitor(Properties var0, PrintWriter var1) {
      SecurityUtil.checkDerbyInternalsPrivilege();

      try {
         new FileMonitor(var0, var1);
      } catch (AccessControlException var3) {
         clearMonitor();
         throw var3;
      }
   }

   public static boolean setMonitor(ModuleFactory var0) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      synchronized(syncMe) {
         if (active) {
            return false;
         } else {
            monitor = var0;
            active = true;
            return true;
         }
      }
   }

   public static void clearMonitor() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      synchronized(syncMe) {
         active = false;
      }
   }

   public static ModuleFactory getMonitor() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return monitor;
   }

   public static ModuleFactory getMonitorLite() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      synchronized(syncMe) {
         if (active && monitor != null) {
            return monitor;
         }
      }

      return new FileMonitor();
   }

   public static HeaderPrintWriter getStream() {
      return monitor.getSystemStreams().stream();
   }

   public static String getServiceName(Object var0) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return monitor.getServiceName(var0);
   }

   public static Object startSystemModule(String var0) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      Object var1 = monitor.startModule(false, (Object)null, var0, (String)null, (Properties)null);
      return var1;
   }

   public static Object findSystemModule(String var0) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      Object var1 = getMonitor().findModule((Object)null, var0, (String)null);
      if (var1 == null) {
         throw missingImplementation(var0);
      } else {
         return var1;
      }
   }

   public static Object getSystemModule(String var0) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      ModuleFactory var1 = getMonitor();
      if (var1 == null) {
         return null;
      } else {
         Object var2 = var1.findModule((Object)null, var0, (String)null);
         return var2;
      }
   }

   public static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      Object var4 = monitor.startModule(var0, var1, var2, (String)null, var3);
      return var4;
   }

   public static Object bootServiceModule(boolean var0, Object var1, String var2, String var3, Properties var4) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      Object var5 = monitor.startModule(var0, var1, var2, var3, var4);
      return var5;
   }

   public static Object findServiceModule(Object var0, String var1) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      Object var2 = getMonitor().findModule(var0, var1, (String)null);
      if (var2 == null) {
         throw missingImplementation(var1);
      } else {
         return var2;
      }
   }

   public static Object getServiceModule(Object var0, String var1) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      Object var2 = getMonitor().findModule(var0, var1, (String)null);
      return var2;
   }

   public static Object findService(String var0, String var1) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return monitor.findService(var0, var1);
   }

   public static boolean startPersistentService(String var0, Properties var1) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return monitor.startPersistentService(var0, var1);
   }

   public static Object startNonPersistentService(String var0, String var1, Properties var2) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return monitor.startNonPersistentService(var0, var1, var2);
   }

   public static Object createPersistentService(String var0, String var1, Properties var2) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      return monitor.createPersistentService(var0, var1, var2);
   }

   public static void removePersistentService(String var0) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      if (!var0.startsWith("memory:")) {
         throw StandardException.newException("XBM0I.D", new Object[]{var0});
      } else {
         monitor.removePersistentService(var0);
      }
   }

   public static InstanceGetter classFromIdentifier(int var0) throws StandardException {
      return monitor.classFromIdentifier(var0);
   }

   public static Object newInstanceFromIdentifier(int var0) throws StandardException {
      return monitor.newInstanceFromIdentifier(var0);
   }

   public static StandardException missingProductVersion(String var0) {
      return StandardException.newException("XBM05.D", new Object[]{var0});
   }

   public static StandardException missingImplementation(String var0) {
      return StandardException.newException("XBM02.D", new Object[]{var0});
   }

   public static StandardException exceptionStartingModule(Throwable var0) {
      return StandardException.newException("XBM01.D", var0, new Object[0]);
   }

   public static void logMessage(String var0) {
      getStream().println(var0);
   }

   public static void logTextMessage(String var0, Object... var1) {
      getStream().println(MessageService.getTextMessage(var0, var1));
   }

   public static Locale getLocaleFromString(String var0) throws StandardException {
      return monitor.getLocaleFromString(var0);
   }

   public static boolean isFullUpgrade(Properties var0, String var1) throws StandardException {
      SecurityUtil.checkDerbyInternalsPrivilege();
      boolean var2 = Boolean.valueOf(var0.getProperty("upgrade"));
      ProductVersionHolder var3 = getMonitor().getEngineVersion();
      if ((var3.isBeta() || var3.isAlpha()) && !PropertyUtil.getSystemBoolean("derby.database.allowPreReleaseUpgrade")) {
         throw StandardException.newException("XCW00.D", new Object[]{var1, var3.getSimpleVersionString()});
      } else {
         return var2;
      }
   }

   public static boolean isDesiredType(Properties var0, int var1) {
      int var2 = 2;
      if (var0 != null) {
         var2 = getEngineType(var0);
      }

      return (var2 & var1) != 0;
   }

   public static boolean isDesiredType(int var0, int var1) {
      return (var0 & var1) != 0;
   }

   public static int getEngineType(Properties var0) {
      if (var0 != null) {
         String var1 = var0.getProperty("derby.engineType");
         int var2 = var1 == null ? 2 : Integer.parseInt(var1.trim());
         return var2;
      } else {
         return 2;
      }
   }

   public static boolean isDesiredCreateType(Properties var0, int var1) {
      boolean var2 = Boolean.valueOf(var0.getProperty("create"));
      if (var2) {
         return (var1 & 2) != 0;
      } else {
         return isDesiredType(var0, var1);
      }
   }

   public static void logThrowable(Throwable var0) {
      var0.printStackTrace(getStream().getPrintWriter());
   }
}
