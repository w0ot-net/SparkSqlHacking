package org.apache.derby.impl.services.monitor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.AccessibleByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.FormatableInstanceGetter;
import org.apache.derby.iapi.services.io.RegisteredFormatIds;
import org.apache.derby.iapi.services.loader.ClassInfo;
import org.apache.derby.iapi.services.loader.InstanceGetter;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.timer.TimerFactory;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.shared.common.error.ErrorStringBuilder;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.BundleFinder;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.info.JVMInfo;
import org.apache.derby.shared.common.stream.InfoStreams;
import org.apache.derby.shared.common.stream.PrintWriterGetHeader;

abstract class BaseMonitor implements ModuleFactory, BundleFinder {
   private final HashMap serviceProviders = new HashMap();
   private static final String LINE = "----------------------------------------------------------------";
   private List implementationSets;
   private final Vector services = new Vector(0, 1);
   Properties bootProperties;
   Properties applicationProperties;
   boolean inShutdown;
   private InfoStreams systemStreams;
   private ContextService contextService;
   private UUIDFactory uuidFactory;
   private TimerFactory timerFactory;
   boolean reportOn;
   private PrintWriter logging;
   ThreadGroup daemonGroup;
   private InstanceGetter[] rc2;
   private static final String SERVICE = "derby.service.";
   private static final HashMap storageFactories = new HashMap();
   private PrintWriter tmpWriter;
   private AccessibleByteArrayOutputStream tmpArray;
   private boolean dumpedTempWriter;

   BaseMonitor() {
      this.services.add(new TopService(this));
   }

   public InfoStreams getSystemStreams() {
      return this.systemStreams;
   }

   public void shutdown() {
      synchronized(this) {
         if (this.inShutdown) {
            return;
         }

         this.inShutdown = true;
      }

      Monitor.getStream().println("----------------------------------------------------------------");
      Monitor.getStream().println(MessageService.getTextMessage("J003", new Object[]{(new Date()).toString()}));
      this.contextService.notifyAllActiveThreads((Context)null);

      while(true) {
         TopService var1;
         synchronized(this) {
            int var2 = this.services.size() - 1;
            if (var2 == 0) {
               break;
            }

            var1 = (TopService)this.services.get(var2);
         }

         ContextManager var3 = this.contextService.newContextManager();

         try {
            var3.popContext();
            this.contextService.setCurrentContextManager(var3);
            this.shutdown(var1.getService());
         } finally {
            this.contextService.resetCurrentContextManager(var3);
         }
      }

      Monitor.getStream().println("----------------------------------------------------------------");
      ((TopService)this.services.get(0)).shutdown();
      stopContextService();
   }

   public void shutdown(Object var1) {
      if (var1 != null) {
         TopService var2 = this.findTopService(var1);
         if (var2 != null) {
            boolean var3 = true;
            boolean var14 = false;

            try {
               var14 = true;
               var3 = var2.shutdown();
               var14 = false;
            } finally {
               if (var14) {
                  synchronized(this) {
                     if (var3) {
                        this.services.remove(var2);
                     }

                  }
               }
            }

            synchronized(this) {
               if (var3) {
                  this.services.remove(var2);
               }

            }
         }
      }
   }

   protected final void runWithState(Properties var1, PrintWriter var2) {
      this.bootProperties = var1;
      this.logging = var2;
      if (!this.initialize(false)) {
         this.dumpTempWriter(true);
      } else if (!setMonitor(this)) {
         MessageService.setFinder(this);
         this.applicationProperties = this.readApplicationProperties();
         Object var3 = null;
         Vector var4 = this.getImplementations(this.bootProperties, false);
         Object var5 = null;
         Object var6 = null;
         Vector var11 = this.getImplementations((Properties)var3, false);
         Vector var12 = this.getImplementations(this.applicationProperties, false);
         Vector var7 = this.getDefaultImplementations();
         int var8 = 0;
         if (var4 != null) {
            ++var8;
         }

         if (var11 != null) {
            ++var8;
         }

         if (var12 != null) {
            ++var8;
         }

         if (var7 != null) {
            ++var8;
         }

         this.implementationSets = new ArrayList(var8);
         if (var4 != null) {
            this.implementationSets.add(var4);
         }

         if (var11 != null) {
            this.implementationSets.add(var11);
         }

         if (var12 != null) {
            this.implementationSets.add(var12);
         }

         if (var7 != null) {
            this.implementationSets.add(var7);
         }

         try {
            this.systemStreams = (InfoStreams)Monitor.startSystemModule("org.apache.derby.shared.common.stream.InfoStreams");
            this.contextService = new ContextService();
            this.uuidFactory = (UUIDFactory)Monitor.startSystemModule("org.apache.derby.iapi.services.uuid.UUIDFactory");
            this.timerFactory = (TimerFactory)Monitor.startSystemModule("org.apache.derby.iapi.services.timer.TimerFactory");
            Monitor.startSystemModule("org.apache.derby.iapi.services.jmx.ManagementService");
         } catch (StandardException var10) {
            this.reportException(var10);
            this.dumpTempWriter(true);
            return;
         }

         this.dumpTempWriter(false);
         this.determineSupportedServiceProviders();
         boolean var9 = Boolean.valueOf(PropertyUtil.getSystemProperty("derby.system.bootAll"));
         this.startServices(this.bootProperties, var9);
         this.startServices((Properties)var3, var9);
         this.startServices(this.applicationProperties, var9);
         if (var9) {
            this.bootPersistentServices();
         }

      }
   }

   public String getCanonicalServiceName(String var1) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         PersistentService var2 = this.findProviderForCreate(var1);
         return var2 == null ? null : var2.getCanonicalServiceName(var1);
      }
   }

   public Object findService(String var1, String var2) {
      if (var2 == null) {
         return null;
      } else {
         ProtocolKey var3;
         try {
            var3 = ProtocolKey.create(var1, var2);
         } catch (StandardException var9) {
            return null;
         }

         TopService var4 = null;
         synchronized(this) {
            for(int var6 = 1; var6 < this.services.size(); ++var6) {
               TopService var7 = (TopService)this.services.get(var6);
               if (var7.isPotentialService(var3)) {
                  var4 = var7;
                  break;
               }
            }
         }

         return var4 != null && var4.isActiveService(var3) ? var4.getService() : null;
      }
   }

   public Locale getLocale(Object var1) {
      TopService var2 = this.findTopService(var1);
      return var2 == null ? null : var2.serviceLocale;
   }

   public Locale getLocaleFromString(String var1) throws StandardException {
      return staticGetLocaleFromString(var1);
   }

   public String getServiceName(Object var1) {
      TopService var2 = this.findTopService(var1);
      return var2 == null ? null : var2.getServiceType().getUserServiceName(var2.getKey().getIdentifier());
   }

   public Locale setLocale(Object var1, String var2) throws StandardException {
      TopService var3 = this.findTopService(var1);
      if (var3 == null) {
         return null;
      } else {
         PersistentService var4 = var3.getServiceType();
         if (var4 == null) {
            return null;
         } else {
            String var5 = var3.getKey().getIdentifier();
            Properties var6 = var4.getServiceProperties(var5, (Properties)null);
            UpdateServiceProperties var7 = new UpdateServiceProperties(var4, var5, var6, true);
            return this.setLocale((Properties)var7, var2);
         }
      }
   }

   public Locale setLocale(Properties var1, String var2) throws StandardException {
      Locale var3 = staticGetLocaleFromString(var2);
      var1.put("derby.serviceLocale", var3.toString());
      return var3;
   }

   public PersistentService getServiceType(Object var1) {
      TopService var2 = this.findTopService(var1);
      return var2 == null ? null : var2.getServiceType();
   }

   public Object startModule(boolean var1, Object var2, String var3, String var4, Properties var5) throws StandardException {
      ProtocolKey var6 = ProtocolKey.create(var3, var4);
      TopService var7 = this.findTopService(var2);
      Object var8 = var7.bootModule(var1, var2, var6, var5);
      if (var8 == null) {
         throw Monitor.missingImplementation(var3);
      } else {
         return var8;
      }
   }

   private synchronized TopService findTopService(Object var1) {
      if (var1 == null) {
         return (TopService)this.services.get(0);
      } else {
         for(int var2 = 1; var2 < this.services.size(); ++var2) {
            TopService var3 = (TopService)this.services.get(var2);
            if (var3.inService(var1)) {
               return var3;
            }
         }

         return null;
      }
   }

   public Object findModule(Object var1, String var2, String var3) {
      ProtocolKey var4;
      try {
         var4 = ProtocolKey.create(var2, var3);
      } catch (StandardException var6) {
         return null;
      }

      TopService var5 = this.findTopService(var1);
      return var5 == null ? null : var5.findModule(var4, true, (Properties)null);
   }

   public InstanceGetter classFromIdentifier(int var1) throws StandardException {
      String var2;
      int var3;
      InstanceGetter[] var4;
      try {
         var3 = var1 - 0;
         var4 = this.rc2;
         if (var4 == null) {
            var4 = this.rc2 = new InstanceGetter[RegisteredFormatIds.countTwoByteIDs()];
         }

         InstanceGetter var5 = var4[var3];
         if (var5 != null) {
            return var5;
         }

         var2 = RegisteredFormatIds.classNameForTwoByteID(var3);
      } catch (ArrayIndexOutOfBoundsException var16) {
         var2 = null;
         var4 = null;
         var3 = 0;
      }

      if (var2 != null) {
         Object var6;
         try {
            Class var7 = Class.forName(var2);
            Constructor var8 = var7.getDeclaredConstructor();
            if (FormatableInstanceGetter.class.isAssignableFrom(var7)) {
               FormatableInstanceGetter var9 = (FormatableInstanceGetter)var8.newInstance();
               var9.setFormatId(var1);
               return var4[var3] = var9;
            }

            return var4[var3] = new ClassInfo(var7);
         } catch (ClassNotFoundException var10) {
            var6 = var10;
         } catch (IllegalAccessException var11) {
            var6 = var11;
         } catch (InstantiationException var12) {
            var6 = var12;
         } catch (NoSuchMethodException var13) {
            var6 = var13;
         } catch (InvocationTargetException var14) {
            var6 = var14;
         } catch (LinkageError var15) {
            var6 = var15;
         }

         throw StandardException.newException("XBM0V.S", (Throwable)var6, new Object[]{FormatIdUtil.formatIdToString(var1), var2});
      } else {
         throw StandardException.newException("XBM0U.S", new Object[]{FormatIdUtil.formatIdToString(var1)});
      }
   }

   public Object newInstanceFromIdentifier(int var1) throws StandardException {
      InstanceGetter var2 = this.classFromIdentifier(var1);

      Object var3;
      try {
         Object var4 = var2.getNewInstance();
         return var4;
      } catch (InstantiationException var5) {
         var3 = var5;
      } catch (IllegalAccessException var6) {
         var3 = var6;
      } catch (NoSuchMethodException var7) {
         var3 = var7;
      } catch (InvocationTargetException var8) {
         var3 = var8;
      } catch (LinkageError var9) {
         var3 = var9;
      }

      throw StandardException.newException("XBM0W.S", (Throwable)var3, new Object[]{var1, "XX"});
   }

   protected Object loadInstance(Class var1, Properties var2) {
      Object var3 = null;
      Vector var4 = this.getImplementations(var2, false);
      if (var4 != null) {
         var3 = this.loadInstance(var4, var1, var2);
      }

      for(List var6 : this.implementationSets) {
         var3 = this.loadInstance(var6, var1, var2);
         if (var3 != null) {
            break;
         }
      }

      return var3;
   }

   private Object loadInstance(List var1, Class var2, Properties var3) {
      int var6 = 0;

      while(true) {
         var6 = findImplementation(var1, var6, var2);
         if (var6 < 0) {
            return null;
         }

         Object var5 = this.newInstance((Class)var1.get(var6));
         if (canSupport(var5, var3)) {
            return var5;
         }

         ++var6;
      }
   }

   private static int findImplementation(List var0, int var1, Class var2) {
      for(int var3 = var1; var3 < var0.size(); ++var3) {
         Class var4 = (Class)var0.get(var3);
         if (var2.isAssignableFrom(var4)) {
            return var3;
         }
      }

      return -1;
   }

   private Object newInstance(Class var1) {
      try {
         Constructor var2 = var1.getDeclaredConstructor();
         Object var3 = var2.newInstance();

         try {
            Method var4 = var1.getMethod("getWarnings");
            String var5 = (String)var4.invoke(var3);
            if (var5 != null) {
               this.report(var5);
            }
         } catch (NoSuchMethodException var6) {
         } catch (InvocationTargetException var7) {
            this.report(var7.toString());
         }

         return var3;
      } catch (InstantiationException var8) {
         String var16 = var1.getName();
         this.report(var16 + " " + var8.toString());
      } catch (IllegalAccessException var9) {
         String var15 = var1.getName();
         this.report(var15 + " " + var9.toString());
      } catch (NoSuchMethodException var10) {
         String var14 = var1.getName();
         this.report(var14 + " " + var10.toString());
      } catch (InvocationTargetException var11) {
         String var13 = var1.getName();
         this.report(var13 + " " + var11.getCause().toString());
      } catch (LinkageError var12) {
         String var10001 = var1.getName();
         this.report(var10001 + " " + var12.toString());
         this.reportException(var12);
      }

      return null;
   }

   public Properties getApplicationProperties() {
      return this.applicationProperties;
   }

   public String[] getServiceList(String var1) {
      synchronized(this) {
         int var4 = 0;

         for(int var5 = 1; var5 < this.services.size(); ++var5) {
            TopService var2 = (TopService)this.services.get(var5);
            if (var2.isActiveService() && var2.getKey().getFactoryInterface().getName().equals(var1)) {
               ++var4;
            }
         }

         String[] var11 = new String[var4];
         if (var4 != 0) {
            int var6 = 0;

            for(int var7 = 1; var7 < this.services.size(); ++var7) {
               TopService var10 = (TopService)this.services.get(var7);
               if (var10.isActiveService() && var10.getKey().getFactoryInterface().getName().equals(var1)) {
                  var11[var6++] = var10.getServiceType().getUserServiceName(var10.getKey().getIdentifier());
                  if (var6 == var4) {
                     break;
                  }
               }
            }
         }

         return var11;
      }
   }

   void dumpProperties(String var1, Properties var2) {
   }

   protected void report(String var1) {
      PrintWriter var2 = this.getTempWriter();
      if (var2 != null) {
         var2.println(var1);
      }

      if (this.systemStreams != null) {
         this.systemStreams.stream().printlnWithHeader(var1);
      }

   }

   protected void reportException(Throwable var1) {
      PrintWriterGetHeader var2 = null;
      if (this.systemStreams != null) {
         var2 = this.systemStreams.stream().getHeader();
      }

      ErrorStringBuilder var3 = new ErrorStringBuilder(var2);
      var3.appendln(var1.getMessage());
      var3.stackTrace(var1);
      this.report(var3.get().toString());
   }

   private void addDebugFlags(String var1, boolean var2) {
   }

   public void startServices(Properties var1, boolean var2) {
      if (var1 != null) {
         Enumeration var3 = var1.propertyNames();

         while(var3.hasMoreElements()) {
            String var4 = (String)var3.nextElement();
            if (var4.startsWith("derby.service.")) {
               String var5 = var4.substring("derby.service.".length());
               String var6 = var1.getProperty(var4);

               try {
                  if (var6.equals("serviceDirectory")) {
                     if (var2) {
                        this.findProviderAndStartService(var5, var1, true);
                     }
                  } else {
                     this.bootService((PersistentService)null, var6, var5, (Properties)null, false);
                  }
               } catch (StandardException var8) {
                  if (!var6.equals("serviceDirectory")) {
                     this.reportException(var8);
                  }
               }
            }
         }

      }
   }

   public final boolean startPersistentService(String var1, Properties var2) throws StandardException {
      return this.findProviderAndStartService(var1, var2, false);
   }

   public Object createPersistentService(String var1, String var2, Properties var3) throws StandardException {
      PersistentService var4 = this.findProviderForCreate(var2);
      if (var4 == null) {
         throw StandardException.newException("XBM0K.D", new Object[]{var2});
      } else {
         return this.bootService(var4, var1, var2, var3, true);
      }
   }

   public void removePersistentService(String var1) throws StandardException {
      PersistentService var2 = null;
      var2 = this.findProviderForCreate(var1);
      String var3 = var2.getCanonicalServiceName(var1);
      boolean var4 = var2.removeServiceRoot(var3);
      if (!var4) {
         throw StandardException.newException("XBM0I.D", new Object[]{var3});
      }
   }

   public Object startNonPersistentService(String var1, String var2, Properties var3) throws StandardException {
      return this.bootService((PersistentService)null, var1, var2, var3, false);
   }

   private Vector getImplementations(Properties var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else {
         Vector var3 = var2 ? new Vector(var1.size()) : new Vector(0, 1);
         int var4 = JVMInfo.JDK_ID;
         int[] var5 = new int[var4 + 1];
         Enumeration var6 = var1.propertyNames();

         label99:
         while(true) {
            String var7;
            String var8;
            int var11;
            do {
               while(true) {
                  if (!var6.hasMoreElements()) {
                     if (var3.isEmpty()) {
                        return null;
                     }

                     var3.trimToSize();
                     return var3;
                  }

                  var7 = (String)var6.nextElement();
                  if (var7.startsWith("derby.module.")) {
                     var8 = var7.substring("derby.module.".length());
                     break;
                  }

                  if (var7.startsWith("derby.subSubProtocol.")) {
                     var8 = var7.substring("derby.module.".length());
                     break;
                  }
               }

               String var9 = "derby.env.jdk.".concat(var8);
               String var10 = var1.getProperty(var9);
               var11 = 0;
               if (var10 == null) {
                  break;
               }

               var11 = Integer.parseInt(var10.trim());
            } while(var11 > var4);

            String var21 = "derby.env.classes.".concat(var8);
            String var12 = var1.getProperty(var21);
            if (var12 != null) {
               StringTokenizer var13 = new StringTokenizer(var12, ",");

               while(var13.hasMoreTokens()) {
                  try {
                     Class.forName(var13.nextToken().trim());
                  } catch (ClassNotFoundException var17) {
                     continue label99;
                  } catch (LinkageError var18) {
                     continue label99;
                  }
               }
            }

            String var22 = var1.getProperty(var7);

            try {
               Class var14 = Class.forName(var22);
               if (!this.getPersistentServiceImplementation(var14)) {
                  if (StorageFactory.class.isAssignableFrom(var14)) {
                     storageFactories.put(var8, var22);
                  } else if (var11 == 0) {
                     var3.add(var14);
                  } else {
                     int var15 = 0;

                     for(int var16 = var4; var16 > var11; --var16) {
                        var15 += var5[var16];
                     }

                     var3.add(var15, var14);
                     int var10002 = var5[var11]++;
                  }
               }
            } catch (ClassNotFoundException var19) {
               this.report("Class " + var22 + " " + var19.toString() + ", module ignored.");
            } catch (LinkageError var20) {
               this.report("Class " + var22 + " " + var20.toString() + ", module ignored.");
            }
         }
      }
   }

   private boolean getPersistentServiceImplementation(Class var1) {
      if (!PersistentService.class.isAssignableFrom(var1)) {
         return false;
      } else {
         PersistentService var2 = (PersistentService)this.newInstance(var1);
         if (var2 == null) {
            this.report("Class " + var1.getName() + " cannot create instance, module ignored.");
         } else {
            this.serviceProviders.put(var2.getType(), var2);
         }

         return true;
      }
   }

   private Vector getDefaultImplementations() {
      Properties var1 = this.getDefaultModuleProperties();
      return this.getImplementations(var1, true);
   }

   Properties getDefaultModuleProperties() {
      Properties var1 = new Properties();
      boolean var2 = true;
      ClassLoader var3 = this.getClass().getClassLoader();

      try {
         Enumeration var4 = var3 == null ? ClassLoader.getSystemResources("org/apache/derby/modules.properties") : var3.getResources("org/apache/derby/modules.properties");

         while(var4.hasMoreElements()) {
            URL var5 = (URL)var4.nextElement();
            InputStream var6 = null;

            try {
               var6 = var5.openStream();
               if (var2) {
                  var1.load(var6);
                  var2 = false;
               } else {
                  Properties var7 = new Properties();
                  var7.load(var6);
                  Enumeration var8 = var7.keys();

                  while(var8.hasMoreElements()) {
                     String var9 = (String)var8.nextElement();
                     if (var1.containsKey(var9)) {
                        this.report("Ignored duplicate property " + var9 + " in " + var5.toString());
                     } else {
                        var1.setProperty(var9, var7.getProperty(var9));
                     }
                  }
               }
            } catch (IOException var19) {
            } finally {
               try {
                  if (var6 != null) {
                     var6.close();
                  }
               } catch (IOException var18) {
               }

            }
         }
      } catch (IOException var21) {
      }

      return var1;
   }

   protected static Properties removeRuntimeProperties(Properties var0) {
      Properties var1 = new Properties();
      Enumeration var2 = var0.keys();

      while(var2.hasMoreElements()) {
         String var3 = (String)var2.nextElement();
         if (!var3.startsWith("derby.__rt.")) {
            var1.put(var3, var0.get(var3));
         }
      }

      return var1;
   }

   abstract InputStream applicationPropertiesStream() throws IOException;

   protected Properties readApplicationProperties() {
      InputStream var1 = null;

      Properties var2;
      try {
         var1 = this.applicationPropertiesStream();
         if (var1 != null) {
            var2 = new Properties();
            org.apache.derby.iapi.util.PropertyUtil.loadWithTrimmedValues(new BufferedInputStream(var1), var2);
            Properties var18 = var2;
            return var18;
         }

         var2 = null;
      } catch (IOException var14) {
         this.report(var14.toString() + " (derby.properties)");
         this.reportException(var14);
         Object var3 = null;
         return (Properties)var3;
      } finally {
         try {
            if (var1 != null) {
               var1.close();
               Object var16 = null;
            }
         } catch (IOException var13) {
         }

      }

      return var2;
   }

   private void determineSupportedServiceProviders() {
      Iterator var1 = this.serviceProviders.values().iterator();

      while(var1.hasNext()) {
         PersistentService var2 = (PersistentService)var1.next();
         if (!canSupport(var2, (Properties)null)) {
            var1.remove();
         }
      }

   }

   private void bootPersistentServices() {
      ProviderEnumeration var1 = new ProviderEnumeration(this.applicationProperties);

      while(var1.hasMoreElements()) {
         PersistentService var2 = (PersistentService)var1.nextElement();
         this.bootProviderServices(var2);
      }

   }

   protected void bootProviderServices(PersistentService var1) {
      Enumeration var2 = var1.getBootTimeServices();

      while(var2 != null && var2.hasMoreElements()) {
         String var3 = (String)var2.nextElement();

         Properties var4;
         try {
            var4 = var1.getServiceProperties(var3, (Properties)null);
         } catch (StandardException var7) {
            this.report("Failed to load service properties, name: " + var3 + ", type = " + var1.getType());
            this.reportException(var7);
            continue;
         }

         if (!Boolean.valueOf(var4.getProperty("derby.database.noAutoBoot"))) {
            try {
               this.startProviderService(var1, var3, var4);
            } catch (StandardException var6) {
               this.report("Service failed to boot, name: " + var3 + ", type = " + var1.getType());
               this.reportException(var6);
            }
         }
      }

   }

   private boolean findProviderAndStartService(String var1, Properties var2, boolean var3) throws StandardException {
      PersistentService var4 = null;
      Properties var5 = null;
      String var6 = null;
      int var7 = var1.indexOf(58);
      if (var7 != -1) {
         var4 = this.findProviderFromName(var1, var7);
         if (var4 != null) {
            var6 = var4.getCanonicalServiceName(var1);
            if (var6 == null) {
               return true;
            }

            var5 = var4.getServiceProperties(var6, var2);
            if (var5 == null) {
               return true;
            }

            if (var3 && Boolean.valueOf(var5.getProperty("derby.database.noAutoBoot"))) {
               return true;
            }

            this.startProviderService(var4, var6, var5);
            return true;
         }
      }

      StandardException var8 = null;
      ProviderEnumeration var9 = new ProviderEnumeration(var2);

      while(true) {
         PersistentService var10;
         String var11;
         Properties var12;
         while(true) {
            if (!var9.hasMoreElements()) {
               if (var4 == null) {
                  return var7 == -1;
               }

               if (var8 != null) {
                  throw var8;
               }

               if (var3 && Boolean.valueOf(var5.getProperty("derby.database.noAutoBoot"))) {
                  return true;
               }

               this.startProviderService(var4, var6, var5);
               return true;
            }

            var10 = (PersistentService)var9.nextElement();
            var11 = var10.getCanonicalServiceName(var1);
            if (var11 != null) {
               var12 = null;

               try {
                  var12 = var10.getServiceProperties(var11, var2);
                  if (var12 == null) {
                     continue;
                  }
               } catch (StandardException var14) {
                  var8 = var14;
               }
               break;
            }
         }

         if (var4 != null) {
            throw StandardException.newException("XBM0T.D", new Object[]{var1});
         }

         var4 = var10;
         var6 = var11;
         var5 = var12;
      }
   }

   protected PersistentService findProviderForCreate(String var1) throws StandardException {
      return this.findProviderFromName(var1, var1.indexOf(58));
   }

   private PersistentService findProviderFromName(String var1, int var2) throws StandardException {
      if (var2 == 0) {
         return null;
      } else {
         String var3;
         if (var2 < 2) {
            var3 = "directory";
         } else {
            var3 = var1.substring(0, var2);
         }

         return this.getServiceProvider(var3);
      }
   }

   public PersistentService getServiceProvider(String var1) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         if (this.serviceProviders != null) {
            PersistentService var2 = (PersistentService)this.serviceProviders.get(var1);
            if (var2 != null) {
               return var2;
            }
         }

         return this.getPersistentService(var1);
      }
   }

   private PersistentService getPersistentService(String var1) throws StandardException {
      String var2 = this.getStorageFactoryClassName(var1);
      return this.getPersistentService(var2, var1);
   }

   private PersistentService getPersistentService(String var1, String var2) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         Object var3 = null;

         try {
            var6 = Class.forName(var1);
         } catch (Throwable var5) {
            throw StandardException.newException("XBM08.D", var5, new Object[]{var2, var1});
         }

         return new StorageFactoryService(var2, var6);
      }
   }

   private String getStorageFactoryClassName(String var1) {
      String var2 = "derby.subSubProtocol." + var1;
      String var3 = PropertyUtil.getSystemProperty(var2);
      return var3 != null ? var3 : (String)storageFactories.get(var1);
   }

   protected void startProviderService(PersistentService var1, String var2, Properties var3) throws StandardException {
      String var4 = var3.getProperty("derby.serviceProtocol");
      if (var4 == null) {
         throw StandardException.newException("XCY03.S", new Object[]{"derby.serviceProtocol"});
      } else {
         this.bootService(var1, var4, var2, var3, false);
      }
   }

   protected Object bootService(PersistentService var1, String var2, String var3, Properties var4, boolean var5) throws StandardException {
      if (var1 != null) {
         var3 = var1.getCanonicalServiceName(var3);
      }

      ProtocolKey var6 = ProtocolKey.create(var2, var3);
      ContextManager var7 = this.contextService.getCurrentContextManager();
      ContextManager var8 = var7;
      TopService var10 = null;
      ServiceBootContext var11 = null;

      Object var9;
      try {
         synchronized(this) {
            if (this.inShutdown) {
               throw StandardException.newException("XJ015.M", new Object[0]);
            }

            for(int var28 = 1; var28 < this.services.size(); ++var28) {
               TopService var32 = (TopService)this.services.get(var28);
               if (var32.isPotentialService(var6)) {
                  Object var15 = null;
                  return var15;
               }
            }

            Locale var29 = null;
            if (var5) {
               var4 = new Properties(var4);
               var29 = setLocale(var4);
               var4.put("derby.serviceProtocol", var2);
               var3 = var1.createServiceRoot(var3, Boolean.valueOf(var4.getProperty("derby.__deleteOnCreate")));
               var6 = ProtocolKey.create(var2, var3);
            } else if (var4 != null) {
               String var33 = var4.getProperty("derby.serviceLocale");
               if (var33 != null) {
                  var29 = staticGetLocaleFromString(var33);
               }
            }

            var10 = new TopService(this, var6, var1, var29);
            this.services.add(var10);
         }

         if (var4 != null) {
            var4.put("derby.__rt.serviceDirectory", var3);
            var4.put("derby.__rt.serviceType", var1.getType());
         }

         if (var7 == null) {
            var8 = this.contextService.newContextManager();
            this.contextService.setCurrentContextManager(var8);
         }

         var11 = new ServiceBootContext(var8);
         boolean var34 = var4 != null ? var4.getProperty("derby.__rt.inRestore") != null : false;
         UpdateServiceProperties var12;
         Object var30;
         if (var1 != null && var4 != null) {
            var12 = new UpdateServiceProperties(var1, var3, var4, !var5 && !var34);
            var30 = var12;
         } else {
            var12 = null;
            var30 = var4;
         }

         var9 = var10.bootModule(var5, (Object)null, var6, (Properties)var30);
         if (var5) {
            var1.createDataWarningFile(var12.getStorageFactory());
         }

         if (var5 || var34) {
            var1.saveServiceProperties(var3, var12.getStorageFactory(), removeRuntimeProperties(var4), false);
            var12.setServiceBooted();
         }

         if (var8 != var7) {
            var8.cleanupOnError(StandardException.closeException(), false);
         }
      } catch (Throwable var25) {
         if (var25 instanceof StandardException var13 && ((StandardException)var25).getSeverity() == 45000) {
            ;
         } else {
            var13 = Monitor.exceptionStartingModule(var25);
         }

         if (var8 != var7) {
            var8.cleanupOnError(var13, false);
         }

         if (var10 != null) {
            var10.shutdown();
            synchronized(this) {
               this.services.remove(var10);
            }

            boolean var14 = var4 != null ? var4.getProperty("derby.__rt.deleteRootOnError") != null : false;
            if (var5 || var14) {
               var1.removeServiceRoot(var3);
            }
         }

         Throwable var31 = var13.getCause();
         if (var31 instanceof ThreadDeath) {
            throw (ThreadDeath)var31;
         }

         throw var13;
      } finally {
         if (var7 == var8 && var11 != null) {
            var11.popMe();
         }

         if (var7 == null) {
            this.contextService.resetCurrentContextManager(var8);
         }

      }

      var10.setTopModule(var9);
      Thread.yield();
      return var9;
   }

   public UUIDFactory getUUIDFactory() {
      return this.uuidFactory;
   }

   public TimerFactory getTimerFactory() {
      return this.timerFactory;
   }

   private PrintWriter getTempWriter() {
      if (this.tmpWriter == null && !this.dumpedTempWriter) {
         this.tmpArray = new AccessibleByteArrayOutputStream();
         this.tmpWriter = new PrintWriter(this.tmpArray);
      }

      return this.tmpWriter;
   }

   private void dumpTempWriter(boolean var1) {
      if (this.tmpWriter != null) {
         this.tmpWriter.flush();
         BufferedReader var2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.tmpArray.getInternalByteArray())));

         String var3;
         try {
            while((var3 = var2.readLine()) != null) {
               if (this.systemStreams != null) {
                  this.systemStreams.stream().printlnWithHeader(var3);
               }

               if (this.systemStreams == null || var1) {
                  this.logging.println(var3);
               }
            }
         } catch (IOException var4) {
         }

         if (this.systemStreams == null || var1) {
            this.logging.flush();
         }

         this.tmpWriter = null;
         this.tmpArray = null;
         this.dumpedTempWriter = true;
         this.logging = null;
      }
   }

   static boolean canSupport(Object var0, Properties var1) {
      return !(var0 instanceof ModuleSupportable) || ((ModuleSupportable)var0).canSupport(var1);
   }

   static void boot(Object var0, boolean var1, Properties var2) throws StandardException {
      if (var0 instanceof ModuleControl) {
         ((ModuleControl)var0).boot(var1, var2);
      }

   }

   private static Locale staticGetLocaleFromString(String var0) throws StandardException {
      int var1 = var0.length();
      boolean var2 = var1 == 2 || var1 == 5 || var1 > 6;
      if (var2 && var1 != 2) {
         var2 = var0.charAt(2) == '_';
      }

      if (var2 && var1 > 5) {
         var2 = var0.charAt(5) == '_';
      }

      if (!var2) {
         throw StandardException.newException("XBM0X.D", new Object[]{var0});
      } else {
         String var3 = var0.substring(0, 2);
         String var4 = var1 == 2 ? "" : var0.substring(3, 5);
         if (var1 < 6) {
            return new Locale(var3, var4);
         } else {
            String var5 = var1 > 6 ? var0.substring(6, var1) : null;
            return new Locale(var3, var4, var5);
         }
      }
   }

   private static Locale setLocale(Properties var0) throws StandardException {
      String var1 = var0.getProperty("territory");
      Locale var2;
      if (var1 == null) {
         var2 = Locale.getDefault();
      } else {
         var2 = staticGetLocaleFromString(var1);
      }

      var0.put("derby.serviceLocale", var2.toString());
      return var2;
   }

   public ResourceBundle getBundle(String var1) {
      ContextManager var2;
      try {
         var2 = getContextService().getCurrentContextManager();
      } catch (ShutdownException var4) {
         var2 = null;
      }

      return var2 != null ? MessageService.getBundleForLocale(var2.getMessageLocale(), var1) : null;
   }

   public Thread getDaemonThread(Runnable var1, String var2, boolean var3) {
      Thread var4 = new Thread(this.daemonGroup, var1, "derby.".concat(var2));
      var4.setDaemon(true);
      if (var3) {
         var4.setPriority(1);
      }

      return var4;
   }

   public final boolean isDaemonThread(Thread var1) {
      return var1.getThreadGroup() == this.daemonGroup;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static void stopContextService() {
      ContextService.stop();
      Monitor.clearMonitor();
   }

   private static boolean setMonitor(BaseMonitor var0) {
      return !Monitor.setMonitor(var0);
   }

   abstract boolean initialize(boolean var1);

   static {
      storageFactories.put("directory", "org.apache.derby.impl.io.DirStorageFactory");
      storageFactories.put("classpath", "org.apache.derby.impl.io.CPStorageFactory");
      storageFactories.put("jar", "org.apache.derby.impl.io.JarStorageFactory");
      storageFactories.put("http", "org.apache.derby.impl.io.URLStorageFactory");
      storageFactories.put("https", "org.apache.derby.impl.io.URLStorageFactory");
      storageFactories.put("memory", "org.apache.derby.impl.io.VFMemoryStorageFactory");
   }

   class ProviderEnumeration implements Enumeration {
      private Enumeration serviceProvidersKeys;
      private Properties startParams;
      private Enumeration paramEnumeration;
      private boolean enumeratedDirectoryProvider;
      private PersistentService storageFactoryPersistentService;

      ProviderEnumeration(Properties var2) {
         this.serviceProvidersKeys = BaseMonitor.this.serviceProviders == null ? null : Collections.enumeration(BaseMonitor.this.serviceProviders.keySet());
         this.startParams = var2;
         if (var2 != null) {
            this.paramEnumeration = var2.keys();
         }

      }

      public PersistentService nextElement() throws NoSuchElementException {
         if (this.serviceProvidersKeys != null && this.serviceProvidersKeys.hasMoreElements()) {
            return (PersistentService)BaseMonitor.this.serviceProviders.get(this.serviceProvidersKeys.nextElement());
         } else {
            this.getNextStorageFactory();
            PersistentService var1 = this.storageFactoryPersistentService;
            this.storageFactoryPersistentService = null;
            return var1;
         }
      }

      private void getNextStorageFactory() {
         if (this.storageFactoryPersistentService == null) {
            if (this.paramEnumeration != null) {
               while(this.paramEnumeration.hasMoreElements()) {
                  String var1 = (String)this.paramEnumeration.nextElement();
                  if (var1.startsWith("derby.subSubProtocol.")) {
                     try {
                        String var2 = (String)this.startParams.get(var1);
                        if (var2 != null) {
                           this.storageFactoryPersistentService = BaseMonitor.this.getPersistentService((String)this.startParams.get(var1), var1.substring("derby.subSubProtocol.".length()));
                           if (this.storageFactoryPersistentService != null) {
                              return;
                           }
                        }
                     } catch (StandardException var4) {
                     }
                  }
               }
            }

            if (!this.enumeratedDirectoryProvider) {
               try {
                  this.storageFactoryPersistentService = BaseMonitor.this.getPersistentService(BaseMonitor.this.getStorageFactoryClassName("directory"), "directory");
               } catch (StandardException var3) {
                  this.storageFactoryPersistentService = null;
               }

               this.enumeratedDirectoryProvider = true;
            }

         }
      }

      public boolean hasMoreElements() {
         if (this.serviceProvidersKeys != null && this.serviceProvidersKeys.hasMoreElements()) {
            return true;
         } else {
            this.getNextStorageFactory();
            return this.storageFactoryPersistentService != null;
         }
      }
   }
}
