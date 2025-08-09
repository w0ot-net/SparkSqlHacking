package org.apache.derby.impl.services.reflect;

import java.io.InputStream;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.loader.ClassFactoryContext;
import org.apache.derby.iapi.services.loader.JarReader;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.locks.ShExLockable;
import org.apache.derby.iapi.services.locks.ShExQual;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

final class UpdateLoader implements LockOwner {
   private static final String[] RESTRICTED_PACKAGES = new String[]{"javax.", "org.apache.derby."};
   private JarLoader[] jarList;
   private HeaderPrintWriter vs;
   private final ClassLoader myLoader;
   private boolean initDone;
   private String thisClasspath;
   private final LockFactory lf;
   private final ShExLockable classLoaderLock;
   private int version;
   private boolean normalizeToUpper;
   private DatabaseClasses parent;
   private final CompatibilitySpace compat;
   private boolean needReload;
   private JarReader jarReader;

   UpdateLoader(String var1, DatabaseClasses var2, boolean var3, boolean var4) throws StandardException {
      this.normalizeToUpper = var4;
      this.parent = var2;
      this.lf = (LockFactory)getServiceModule(var2, "org.apache.derby.iapi.services.locks.LockFactory");
      this.compat = this.lf != null ? this.lf.createCompatibilitySpace(this) : null;
      if (var3) {
         this.vs = Monitor.getStream();
      }

      this.myLoader = this.getClass().getClassLoader();
      this.classLoaderLock = new ClassLoaderLock(this);
      this.initializeFromClassPath(var1);
   }

   private void initializeFromClassPath(String var1) throws StandardException {
      String[][] var2 = IdUtil.parseDbClassPath(var1);
      int var3 = var2.length;
      this.jarList = new JarLoader[var3];
      if (var3 != 0) {
         for(int var4 = 0; var4 < var3; ++var4) {
            this.jarList[var4] = new JarLoader(this, var2[var4], this.vs);
         }
      }

      if (this.vs != null) {
         this.vs.println(MessageService.getTextMessage("C005", new Object[]{var1}));
      }

      this.thisClasspath = var1;
      this.initDone = false;
   }

   Class loadClass(String var1, boolean var2) throws ClassNotFoundException {
      Object var3 = null;
      boolean var4 = false;

      try {
         var4 = this.lockClassLoader(ShExQual.SH);
         synchronized(this) {
            if (this.needReload) {
               this.reload();
            }

            Class var6 = this.checkLoaded(var1, var2);
            if (var6 != null) {
               Class var21 = var6;
               return var21;
            }

            for(int var7 = 0; var7 < RESTRICTED_PACKAGES.length; ++var7) {
               if (var1.startsWith(RESTRICTED_PACKAGES[var7])) {
                  throw new ClassNotFoundException(var1);
               }
            }

            String var20 = var1.replace('.', '/').concat(".class");
            if (!this.initDone) {
               this.initLoaders();
            }

            for(int var8 = 0; var8 < this.jarList.length; ++var8) {
               JarLoader var19 = this.jarList[var8];
               Class var9 = var19.loadClassData(var1, var20, var2);
               if (var9 != null) {
                  if (this.vs != null) {
                     this.vs.println(MessageService.getTextMessage("C006", new Object[]{var1, var19.getJarName()}));
                  }

                  Class var10 = var9;
                  return var10;
               }
            }
         }

         Object var5 = null;
         return (Class)var5;
      } catch (StandardException var17) {
         throw new ClassNotFoundException(MessageService.getTextMessage("C007", new Object[]{var1, var3 == null ? null : ((JarLoader)var3).getJarName(), var17}));
      } finally {
         if (var4) {
            this.lf.unlock(this.compat, this, this.classLoaderLock, ShExQual.SH);
         }

      }
   }

   InputStream getResourceAsStream(String var1) {
      InputStream var2 = this.myLoader == null ? ClassLoader.getSystemResourceAsStream(var1) : this.myLoader.getResourceAsStream(var1);
      if (var2 != null) {
         return var2;
      } else if (var1.endsWith(".class")) {
         return null;
      } else {
         boolean var3 = false;

         try {
            var3 = this.lockClassLoader(ShExQual.SH);
            synchronized(this) {
               if (this.needReload) {
                  this.reload();
               }

               if (!this.initDone) {
                  this.initLoaders();
               }

               for(int var17 = 0; var17 < this.jarList.length; ++var17) {
                  JarLoader var6 = this.jarList[var17];
                  var2 = var6.getStream(var1);
                  if (var2 != null) {
                     InputStream var7 = var2;
                     return var7;
                  }
               }
            }

            Object var4 = null;
            return (InputStream)var4;
         } catch (StandardException var14) {
            Object var5 = null;
            return (InputStream)var5;
         } finally {
            if (var3) {
               this.lf.unlock(this.compat, this, this.classLoaderLock, ShExQual.SH);
            }

         }
      }
   }

   synchronized void modifyClasspath(String var1) throws StandardException {
      this.lockClassLoader(ShExQual.EX);
      ++this.version;
      this.modifyJar(false);
      this.initializeFromClassPath(var1);
   }

   synchronized void modifyJar(boolean var1) throws StandardException {
      this.lockClassLoader(ShExQual.EX);
      ++this.version;
      if (this.initDone) {
         this.close();
         if (var1) {
            this.initializeFromClassPath(this.thisClasspath);
         }

      }
   }

   private boolean lockClassLoader(ShExQual var1) throws StandardException {
      if (this.lf == null) {
         return false;
      } else {
         ClassFactoryContext var2 = (ClassFactoryContext)getContextOrNull("ClassFactoryContext");
         CompatibilitySpace var3 = null;
         if (var2 != null) {
            var3 = var2.getLockSpace();
         }

         if (var3 == null) {
            var3 = this.compat;
         }

         LockOwner var4 = var3.getOwner();
         this.lf.lockObject(var3, var4, this.classLoaderLock, var1, -2);
         return var4 == this;
      }
   }

   Class checkLoaded(String var1, boolean var2) {
      for(int var3 = 0; var3 < this.jarList.length; ++var3) {
         Class var4 = this.jarList[var3].checkLoaded(var1, var2);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   void close() {
      for(int var1 = 0; var1 < this.jarList.length; ++var1) {
         this.jarList[var1].setInvalid();
      }

   }

   private void initLoaders() {
      if (!this.initDone) {
         for(int var1 = 0; var1 < this.jarList.length; ++var1) {
            this.jarList[var1].initialize();
         }

         this.initDone = true;
      }
   }

   int getClassLoaderVersion() {
      return this.version;
   }

   synchronized void needReload() {
      ++this.version;
      this.needReload = true;
   }

   private void reload() throws StandardException {
      this.thisClasspath = this.getClasspath();
      this.close();
      this.initializeFromClassPath(this.thisClasspath);
      this.needReload = false;
   }

   private String getClasspath() throws StandardException {
      ClassFactoryContext var1 = (ClassFactoryContext)getContextOrNull("ClassFactoryContext");
      PersistentSet var2 = var1.getPersistentSet();
      String var3 = PropertyUtil.getServiceProperty(var2, "derby.database.classpath");
      if (var3 == null) {
         var3 = "";
      }

      return var3;
   }

   JarReader getJarReader() {
      if (this.jarReader == null) {
         ClassFactoryContext var1 = (ClassFactoryContext)getContextOrNull("ClassFactoryContext");
         this.jarReader = var1.getJarReader();
      }

      return this.jarReader;
   }

   public boolean noWait() {
      return false;
   }

   public boolean isNestedOwner() {
      return false;
   }

   public boolean nestsUnder(LockOwner var1) {
      return false;
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   private static Object getServiceModule(Object var0, String var1) {
      return Monitor.getServiceModule(var0, var1);
   }
}
