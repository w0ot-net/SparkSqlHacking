package org.apache.derby.impl.services.reflect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.Properties;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

abstract class DatabaseClasses implements ClassFactory, ModuleControl {
   private ClassInspector classInspector;
   private UpdateLoader applicationLoader;

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.classInspector = this.makeClassInspector(this);
      String var3 = null;
      if (var2 != null) {
         var3 = var2.getProperty("derby.__rt.database.classpath");
      }

      if (var3 != null) {
         this.applicationLoader = new UpdateLoader(var3, this, true, true);
      }

   }

   public void stop() {
      if (this.applicationLoader != null) {
         this.applicationLoader.close();
      }

   }

   protected ClassInspector makeClassInspector(DatabaseClasses var1) {
      return new ClassInspector(var1);
   }

   public final GeneratedClass loadGeneratedClass(String var1, ByteArray var2) throws StandardException {
      try {
         return this.loadGeneratedClassFromData(var1, var2);
      } catch (LinkageError var4) {
         WriteClassFile(var1, var2, var4);
         throw StandardException.newException("XBCM1.S", var4, new Object[]{var1});
      } catch (VirtualMachineError var5) {
         WriteClassFile(var1, var2, var5);
         throw var5;
      }
   }

   private static void WriteClassFile(String var0, ByteArray var1, Throwable var2) {
      int var3 = var0.lastIndexOf(46);
      String var4 = var0.substring(var3 + 1, var0.length()).concat(".class");
      Object var5 = getMonitor().getEnvironment();
      File var6 = var5 instanceof File ? (File)var5 : null;
      File var7 = new File(var6, var4);
      HeaderPrintWriter var8 = Monitor.getStream();

      try {
         FileOutputStream var9 = new FileOutputStream(var7);
         var9.write(var1.getArray(), var1.getOffset(), var1.getLength());
         var9.flush();
         if (var2 != null) {
            var8.printlnWithHeader(MessageService.getTextMessage("C000", new Object[]{var0, var7, var2}));
         }

         var9.close();
      } catch (IOException var10) {
      }

   }

   public ClassInspector getClassInspector() {
      return this.classInspector;
   }

   public final Class loadApplicationClass(String var1) throws ClassNotFoundException {
      if (var1.startsWith("org.apache.derby.")) {
         try {
            return Class.forName(var1);
         } catch (ClassNotFoundException var8) {
         }
      }

      Object var2;
      try {
         try {
            return this.loadClassNotInDatabaseJar(var1);
         } catch (ClassNotFoundException var5) {
            if (this.applicationLoader == null) {
               throw var5;
            }

            Class var4 = this.applicationLoader.loadClass(var1, true);
            if (var4 == null) {
               throw var5;
            }

            return var4;
         }
      } catch (SecurityException var6) {
         var2 = var6;
      } catch (LinkageError var7) {
         var2 = var7;
      }

      throw new ClassNotFoundException(var1 + " : " + ((Throwable)var2).getMessage());
   }

   abstract Class loadClassNotInDatabaseJar(String var1) throws ClassNotFoundException;

   public final Class loadApplicationClass(ObjectStreamClass var1) throws ClassNotFoundException {
      return this.loadApplicationClass(var1.getName());
   }

   public boolean isApplicationClass(Class var1) {
      return var1.getClassLoader() instanceof JarLoader;
   }

   public void notifyModifyJar(boolean var1) throws StandardException {
      if (this.applicationLoader != null) {
         this.applicationLoader.modifyJar(var1);
      }

   }

   public void notifyModifyClasspath(String var1) throws StandardException {
      if (this.applicationLoader != null) {
         this.applicationLoader.modifyClasspath(var1);
      }

   }

   public int getClassLoaderVersion() {
      return this.applicationLoader != null ? this.applicationLoader.getClassLoaderVersion() : -1;
   }

   abstract LoadedGeneratedClass loadGeneratedClassFromData(String var1, ByteArray var2);

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
