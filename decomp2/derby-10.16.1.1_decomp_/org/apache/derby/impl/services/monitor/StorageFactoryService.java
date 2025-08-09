package org.apache.derby.impl.services.monitor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.WritableStorageFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

final class StorageFactoryService implements PersistentService {
   private static final String SERVICE_PROPERTIES_EOF_TOKEN = "#--- last line, don't put anything after this line ---";
   private String home;
   private String canonicalHome;
   private final String subSubProtocol;
   private final Class storageFactoryClass;
   private StorageFactory rootStorageFactory;
   private char separatorChar;

   StorageFactoryService(String var1, Class var2) throws StandardException {
      this.subSubProtocol = var1;
      this.storageFactoryClass = var2;
      Object var3 = getMonitor().getEnvironment();
      if (var3 instanceof File var4) {
         try {
            this.home = var4.getPath();
            this.canonicalHome = var4.getCanonicalPath();
            this.rootStorageFactory = this.getStorageFactoryInstance(true, (String)null, (String)null, (String)null);
            if (this.home != null) {
               StorageFile var5 = this.rootStorageFactory.newStorageFile((String)null);
               boolean var6 = var5.mkdirs();
               if (var6) {
                  var5.limitAccessToOwner();
               }
            }
         } catch (IOException var8) {
            this.home = null;
            this.canonicalHome = null;
         }
      }

      if (this.rootStorageFactory == null) {
         try {
            this.rootStorageFactory = this.getStorageFactoryInstance(true, (String)null, (String)null, (String)null);
         } catch (IOException var7) {
            throw Monitor.exceptionStartingModule(var7);
         }
      }

      this.separatorChar = this.rootStorageFactory.getSeparator();
   }

   public boolean hasStorageFactory() {
      return true;
   }

   public StorageFactory getStorageFactoryInstance(boolean var1, String var2, String var3, String var4) throws StandardException, IOException {
      try {
         return this.privGetStorageFactoryInstance(var1, var2, var3, var4);
      } catch (IOException var6) {
         throw this.registeredClassInstanceError(var6);
      } catch (InstantiationException var7) {
         throw this.registeredClassInstanceError(var7);
      } catch (IllegalAccessException var8) {
         throw this.registeredClassInstanceError(var8);
      } catch (NoSuchMethodException var9) {
         throw this.registeredClassInstanceError(var9);
      } catch (InvocationTargetException var10) {
         throw this.registeredClassInstanceError(var10);
      }
   }

   private StandardException registeredClassInstanceError(Exception var1) {
      return StandardException.newException("XBM0W.S", var1, new Object[]{this.subSubProtocol, this.storageFactoryClass});
   }

   private StorageFactory privGetStorageFactoryInstance(boolean var1, String var2, String var3, String var4) throws InstantiationException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {
      StorageFactory var5 = (StorageFactory)this.storageFactoryClass.getConstructor().newInstance();
      String var6;
      if (var2 != null && this.subSubProtocol != null && var2.startsWith(this.subSubProtocol + ":")) {
         var6 = var2.substring(this.subSubProtocol.length() + 1);
      } else {
         var6 = var2;
      }

      var5.init(var1 ? this.home : null, var6, var3, var4);
      return var5;
   }

   public String getType() {
      return this.subSubProtocol;
   }

   public Enumeration getBootTimeServices() {
      return this.home == null ? null : new DirectoryList();
   }

   public Properties getServiceProperties(String var1, Properties var2) throws StandardException {
      String var3 = this.recreateServiceRoot(var1, var2);
      Properties var4 = new Properties(var2);

      try {
         if (var3 != null) {
            File var5 = new File(var3, "service.properties");
            FileInputStream var6 = new FileInputStream(var5);

            try {
               var4.load(new BufferedInputStream(var6));
            } finally {
               ((InputStream)var6).close();
            }
         } else {
            StorageFactory var46 = this.privGetStorageFactoryInstance(true, var1, (String)null, (String)null);
            StorageFile var47 = var46.newStorageFile("service.properties");
            this.resolveServicePropertiesFiles(var46, var47);

            try {
               InputStream var7 = var47.getInputStream();

               try {
                  var4.load(new BufferedInputStream(var7));
               } finally {
                  var7.close();
               }
            } finally {
               var46.shutdown();
            }
         }

         return var4;
      } catch (FileNotFoundException var40) {
         return null;
      } catch (IOException var41) {
         throw Monitor.exceptionStartingModule(var41);
      } catch (InstantiationException var42) {
         throw Monitor.exceptionStartingModule(var42);
      } catch (IllegalAccessException var43) {
         throw Monitor.exceptionStartingModule(var43);
      } catch (NoSuchMethodException var44) {
         throw Monitor.exceptionStartingModule(var44);
      } catch (InvocationTargetException var45) {
         throw Monitor.exceptionStartingModule(var45);
      }
   }

   public void saveServiceProperties(String var1, StorageFactory var2, Properties var3, boolean var4) throws StandardException {
      if (!(var2 instanceof WritableStorageFactory var5)) {
         throw StandardException.newException("XBM0P.D", new Object[0]);
      } else {
         StorageFile var6 = var4 ? var5.newStorageFile("service.properties".concat("old")) : null;
         StorageFile var7 = var5.newStorageFile("service.properties");
         FileOperationHelper var8 = new FileOperationHelper();
         if (var4) {
            var8.renameTo(var7, var6, true);
         }

         OutputStream var9 = null;

         try {
            var9 = var7.getOutputStream();
            var3.store(var9, var1 + MessageService.getTextMessage("M001", new Object[0]));
            BufferedWriter var10 = new BufferedWriter(new OutputStreamWriter(var9, "ISO-8859-1"));
            var10.write("#--- last line, don't put anything after this line ---");
            var10.newLine();
            var5.sync(var9, false);
            var10.close();
            var9.close();
            var9 = null;
         } catch (IOException var18) {
            if (var6 != null) {
               var8.renameTo(var6, var7, false);
            }

            if (var4) {
               throw StandardException.newException("XBM0B.D", var18, new Object[0]);
            }

            throw Monitor.exceptionStartingModule(var18);
         } finally {
            if (var9 != null) {
               try {
                  var9.close();
               } catch (IOException var17) {
               }
            }

         }

         if (var6 != null && !var8.delete(var6, false)) {
            Monitor.getStream().printlnWithHeader(MessageService.getTextMessage("M004", new Object[]{getMostAccuratePath(var6)}));
         }

      }
   }

   public void createDataWarningFile(StorageFactory var1) throws StandardException {
      if (!(var1 instanceof WritableStorageFactory var2)) {
         throw StandardException.newException("XBM0P.D", new Object[0]);
      } else {
         OutputStreamWriter var3 = null;

         try {
            StorageFile var4 = var2.newStorageFile("README_DO_NOT_TOUCH_FILES.txt");
            var3 = new OutputStreamWriter(var4.getOutputStream(), "UTF8");
            var3.write(MessageService.getTextMessage("M005", new Object[0]));
         } catch (IOException var13) {
         } finally {
            if (var3 != null) {
               try {
                  var3.close();
               } catch (IOException var12) {
               }
            }

         }

      }
   }

   public void saveServiceProperties(String var1, Properties var2) throws StandardException {
      File var3 = new File(var1, "service.properties");
      FileOutputStream var4 = null;

      try {
         var4 = new FileOutputStream(var3);
         FileUtil.limitAccessToOwner(var3);
         var2.store(var4, var1 + MessageService.getTextMessage("M001", new Object[0]));
         var4.getFD().sync();
         var4.close();
         Object var11 = null;
      } catch (IOException var8) {
         if (var4 != null) {
            try {
               var4.close();
            } catch (IOException var7) {
            }

            Object var9 = null;
         }

         throw Monitor.exceptionStartingModule(var8);
      }
   }

   private void resolveServicePropertiesFiles(StorageFactory var1, StorageFile var2) throws StandardException {
      StorageFile var3 = var1.newStorageFile("service.properties".concat("old"));
      FileOperationHelper var4 = new FileOperationHelper();
      boolean var5 = var4.exists(var2, true);
      boolean var6 = var4.exists(var3, true);
      if (!var5 || var6) {
         if (var6 && !var5) {
            var4.renameTo(var3, var2, true);
            Monitor.getStream().printlnWithHeader(MessageService.getTextMessage("M002", new Object[0]));
         } else if (var6 && var5) {
            BufferedReader var7 = null;
            String var8 = null;

            try {
               var7 = new BufferedReader(new InputStreamReader(new FileInputStream(var2.getPath()), "ISO-8859-1"));

               String var9;
               while((var9 = var7.readLine()) != null) {
                  if (var9.trim().length() != 0) {
                     var8 = var9;
                  }
               }
            } catch (IOException var17) {
               throw StandardException.newException("XJ113.S", var17, new Object[]{var2.getPath(), var17.getMessage()});
            } finally {
               try {
                  if (var7 != null) {
                     var7.close();
                  }
               } catch (IOException var16) {
               }

            }

            if (var8 != null && var8.startsWith("#--- last line, don't put anything after this line ---")) {
               String var19;
               if (var4.delete(var3, false)) {
                  var19 = MessageService.getTextMessage("M003", new Object[0]);
               } else {
                  var19 = MessageService.getTextMessage("M004", new Object[]{getMostAccuratePath(var3)});
               }

               Monitor.getStream().printlnWithHeader(var19);
            } else {
               var4.delete(var2, false);
               var4.renameTo(var3, var2, true);
               Monitor.getStream().printlnWithHeader(MessageService.getTextMessage("M002", new Object[0]));
            }
         }

      }
   }

   protected String recreateServiceRoot(String var1, Properties var2) throws StandardException {
      if (var2 == null) {
         return null;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         String var3 = var2.getProperty("createFrom");
         if (var3 != null) {
            var4 = true;
            var5 = false;
         } else {
            var3 = var2.getProperty("restoreFrom");
            if (var3 != null) {
               var4 = true;
               var5 = true;
            } else {
               var3 = var2.getProperty("rollForwardRecoveryFrom");
               if (var3 != null) {
                  try {
                     Object var6 = null;
                     StorageFactory var7 = this.privGetStorageFactoryInstance(true, var1, (String)null, (String)null);

                     try {
                        StorageFile var8 = var7.newStorageFile((String)null);
                        var47 = var8.exists() ? this : null;
                     } finally {
                        var7.shutdown();
                     }

                     if (var47 == null) {
                        var4 = true;
                        var5 = false;
                     }
                  } catch (IOException var36) {
                     throw Monitor.exceptionStartingModule(var36);
                  } catch (InstantiationException var37) {
                     throw Monitor.exceptionStartingModule(var37);
                  } catch (IllegalAccessException var38) {
                     throw Monitor.exceptionStartingModule(var38);
                  } catch (NoSuchMethodException var39) {
                     throw Monitor.exceptionStartingModule(var39);
                  } catch (InvocationTargetException var40) {
                     throw Monitor.exceptionStartingModule(var40);
                  }
               }
            }
         }

         if (var3 != null) {
            File var48 = new File(var3);
            if (!this.fileExists(var48)) {
               throw StandardException.newException("XBM0Y.D", new Object[]{var48});
            }

            File var49 = new File(var3, "service.properties");
            if (!this.fileExists(var49)) {
               throw StandardException.newException("XBM0Q.D", new Object[]{var49});
            }

            if (var4) {
               this.createServiceRoot(var1, var5);
            }

            try {
               WritableStorageFactory var50 = (WritableStorageFactory)this.privGetStorageFactoryInstance(true, var1, (String)null, (String)null);

               try {
                  StorageFile var9 = var50.newStorageFile("service.properties");
                  if (var9.exists() && !var9.delete()) {
                     throw StandardException.newException("XBM0R.D", new Object[]{var9});
                  }
               } finally {
                  var50.shutdown();
               }
            } catch (IOException var42) {
               throw Monitor.exceptionStartingModule(var42);
            } catch (InstantiationException var43) {
               throw Monitor.exceptionStartingModule(var43);
            } catch (IllegalAccessException var44) {
               throw Monitor.exceptionStartingModule(var44);
            } catch (NoSuchMethodException var45) {
               throw Monitor.exceptionStartingModule(var45);
            } catch (InvocationTargetException var46) {
               throw Monitor.exceptionStartingModule(var46);
            }

            var2.put("derby.__rt.inRestore", "True");
            if (var4) {
               var2.put("derby.__rt.deleteRootOnError", "True");
            }
         }

         return var3;
      }
   }

   public String createServiceRoot(String var1, boolean var2) throws StandardException {
      if (!(this.rootStorageFactory instanceof WritableStorageFactory)) {
         throw StandardException.newException("XBM0P.D", new Object[0]);
      } else {
         String var3 = null;

         try {
            StorageFactory var4 = this.privGetStorageFactoryInstance(true, var1, (String)null, (String)null);

            try {
               StorageFile var5 = var4.newStorageFile((String)null);
               if (var5.exists()) {
                  if (!var2) {
                     this.vetService(var4, var1);
                     throw StandardException.newException("XBM0J.D", new Object[]{this.getDirectoryPath(var1)});
                  }

                  if (!var5.deleteAll()) {
                     throw StandardException.newException("XBM0I.D", new Object[]{this.getDirectoryPath(var1)});
                  }
               }

               if (var5.mkdirs()) {
                  var5.limitAccessToOwner();
                  String var6 = var5.getCanonicalPath();
                  var4.setCanonicalName(var6);

                  try {
                     var3 = var4.getCanonicalName();
                  } catch (IOException var16) {
                     var5.deleteAll();
                     throw var16;
                  }
               }
            } finally {
               var4.shutdown();
            }
         } catch (IOException var18) {
            throw this.serviceDirectoryCreateError(var18, var1);
         } catch (InstantiationException var19) {
            throw this.serviceDirectoryCreateError(var19, var1);
         } catch (IllegalAccessException var20) {
            throw this.serviceDirectoryCreateError(var20, var1);
         } catch (NoSuchMethodException var21) {
            throw this.serviceDirectoryCreateError(var21, var1);
         } catch (InvocationTargetException var22) {
            throw this.serviceDirectoryCreateError(var22, var1);
         }

         String var10000 = this.getProtocolLeadIn();
         return var10000 + var3;
      }
   }

   private StandardException serviceDirectoryCreateError(Exception var1, String var2) {
      return StandardException.newException("XBM0H.D", var1, new Object[]{var2});
   }

   private void vetService(StorageFactory var1, String var2) throws StandardException {
      StorageFile var3 = var1.newStorageFile("service.properties");
      if (!var3.exists()) {
         StorageFile var4 = var1.newStorageFile("seg0");
         if (var4.exists()) {
            throw StandardException.newException("XBM0A.D", new Object[]{var2, "service.properties"});
         }
      }

   }

   private String getDirectoryPath(String var1) {
      StringBuffer var2 = new StringBuffer();
      if (this.home != null) {
         var2.append(this.home);
         var2.append(this.separatorChar);
      }

      if (this.separatorChar != '/') {
         var2.append(var1.replace('/', this.separatorChar));
      } else {
         var2.append(var1);
      }

      return var2.toString();
   }

   public boolean removeServiceRoot(String var1) {
      if (!(this.rootStorageFactory instanceof WritableStorageFactory)) {
         return false;
      } else {
         try {
            Object var2 = null;
            StorageFactory var3 = this.privGetStorageFactoryInstance(true, var1, (String)null, (String)null);

            try {
               StorageFile var4 = var3.newStorageFile((String)null);
               var10 = var4.deleteAll() ? this : null;
            } finally {
               var3.shutdown();
            }

            return var10 != null;
         } catch (Exception var9) {
            return false;
         }
      }
   }

   public String getCanonicalServiceName(String var1) throws StandardException {
      int var2 = var1.indexOf(58);
      if (var2 < 2 && !this.getType().equals("directory")) {
         return null;
      } else {
         if (var2 > 1) {
            if (!var1.startsWith(this.getType() + ":")) {
               return null;
            }

            var1 = var1.substring(var2 + 1);
         }

         String var3 = var1;

         try {
            Object var4 = null;
            StorageFactory var5 = this.privGetStorageFactoryInstance(true, var3, (String)null, (String)null);

            try {
               var11 = var5.getCanonicalName();
            } finally {
               var5.shutdown();
            }

            String var10000 = this.getProtocolLeadIn();
            return var10000 + var11;
         } catch (Exception var10) {
            throw Monitor.exceptionStartingModule(var10);
         }
      }
   }

   public String getUserServiceName(String var1) {
      if (this.home != null && var1.length() > this.canonicalHome.length() + 1 && var1.startsWith(this.canonicalHome)) {
         var1 = var1.substring(this.canonicalHome.length());
         if (var1.charAt(0) == this.separatorChar) {
            var1 = var1.substring(1);
         }
      }

      return var1.replace(this.separatorChar, '/');
   }

   public boolean isSameService(String var1, String var2) {
      return var1.equals(var2);
   }

   private final boolean fileExists(File var1) {
      return var1.exists();
   }

   public Class getStorageFactoryClass() {
      return this.storageFactoryClass;
   }

   private String getProtocolLeadIn() {
      return this.getType().equals("directory") ? "" : this.getType() + ":";
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static String getMostAccuratePath(StorageFile var0) {
      String var1 = var0.getPath();

      try {
         var1 = var0.getCanonicalPath();
      } catch (IOException var3) {
      }

      return var1;
   }

   final class DirectoryList implements Enumeration {
      private String[] contents;
      private StorageFile systemDirectory;
      private int index;
      private boolean validIndex;
      private int actionCode = 0;
      private static final int INIT_ACTION = 0;
      private static final int HAS_MORE_ELEMENTS_ACTION = 1;

      DirectoryList() {
         this.run();
      }

      public boolean hasMoreElements() {
         if (this.contents == null) {
            return false;
         } else if (this.validIndex) {
            return true;
         } else {
            this.actionCode = 1;
            return this.run() != null;
         }
      }

      public Object nextElement() throws NoSuchElementException {
         if (!this.hasMoreElements()) {
            throw new NoSuchElementException();
         } else {
            this.validIndex = false;
            return this.contents[this.index++];
         }
      }

      public final DirectoryList run() {
         switch (this.actionCode) {
            case 0:
               this.systemDirectory = StorageFactoryService.this.rootStorageFactory.newStorageFile((String)null);
               this.contents = this.systemDirectory.list();
               return null;
            case 1:
               for(; this.index < this.contents.length; this.contents[this.index++] = null) {
                  try {
                     String var1 = this.contents[this.index];
                     StorageFile var2 = StorageFactoryService.this.rootStorageFactory.newStorageFile(var1);
                     if (var2.isDirectory()) {
                        StorageFile var3 = StorageFactoryService.this.rootStorageFactory.newStorageFile(var2, "service.properties");
                        if (var3.exists()) {
                           this.contents[this.index] = var2.getCanonicalPath();
                           this.validIndex = true;
                           return this;
                        }
                     }
                  } catch (Exception var4) {
                  }
               }

               return null;
            default:
               return null;
         }
      }
   }

   private static class FileOperationHelper {
      private String operation;

      boolean exists(StorageFile var1, boolean var2) throws StandardException {
         this.operation = "exists";
         boolean var3 = false;
         var3 = var1.exists();
         return var3;
      }

      boolean delete(StorageFile var1, boolean var2) throws StandardException {
         this.operation = "delete";
         boolean var3 = false;
         var3 = var1.delete();
         if (var2 && !var3) {
            throw StandardException.newException("XBM0R.D", new Object[]{var1.getPath()});
         } else {
            return var3;
         }
      }

      boolean renameTo(StorageFile var1, StorageFile var2, boolean var3) throws StandardException {
         this.operation = "renameTo";
         this.delete(var2, false);
         boolean var4 = false;
         var4 = var1.renameTo(var2);
         if (var3 && !var4) {
            throw StandardException.newException("XBM0S.D", new Object[]{var1.getPath(), var2.getPath()});
         } else {
            return var4;
         }
      }
   }
}
