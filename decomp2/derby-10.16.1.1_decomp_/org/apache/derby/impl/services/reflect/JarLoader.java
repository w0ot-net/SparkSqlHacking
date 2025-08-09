package org.apache.derby.impl.services.reflect;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;
import java.security.GeneralSecurityException;
import java.security.SecureClassLoader;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import org.apache.derby.iapi.services.io.AccessibleByteArrayOutputStream;
import org.apache.derby.iapi.services.io.InputStreamUtil;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

final class JarLoader extends SecureClassLoader {
   private final String[] name;
   private StorageFile installedJar;
   private JarFile jar;
   private boolean isStream;
   private UpdateLoader updateLoader;
   private HeaderPrintWriter vs;

   JarLoader(UpdateLoader var1, String[] var2, HeaderPrintWriter var3) {
      this.updateLoader = var1;
      this.name = var2;
      this.vs = var3;
   }

   void initialize() {
      String var1 = this.name[0];
      String var2 = this.name[1];

      Object var3;
      try {
         this.installedJar = this.updateLoader.getJarReader().getJarFile(var1, var2);
         if (this.installedJar instanceof File) {
            this.jar = new JarFile((File)this.installedJar);
            return;
         }

         this.isStream = true;
         return;
      } catch (IOException var5) {
         var3 = var5;
      } catch (StandardException var6) {
         var3 = var6;
      }

      if (this.vs != null) {
         this.vs.println(MessageService.getTextMessage("C003", new Object[]{this.getJarName(), var3}));
      }

      this.setInvalid();
   }

   protected Class loadClass(String var1, boolean var2) throws ClassNotFoundException {
      if (var1.startsWith("org.apache.derby.") && !this.isDerbyDriver(var1) && !var1.startsWith("org.apache.derby.jdbc.") && !var1.startsWith("org.apache.derby.vti.") && !var1.startsWith("org.apache.derby.agg.") && !var1.startsWith("org.apache.derby.optional.") && !var1.startsWith("org.apache.derby.impl.tools.optional.")) {
         ClassNotFoundException var3 = new ClassNotFoundException(var1);
         throw var3;
      } else {
         try {
            return Class.forName(var1);
         } catch (ClassNotFoundException var5) {
            if (this.updateLoader == null) {
               throw new ClassNotFoundException(MessageService.getTextMessage("C004", new Object[]{var1}));
            } else {
               Class var4 = this.updateLoader.loadClass(var1, var2);
               if (var4 == null) {
                  throw var5;
               } else {
                  return var4;
               }
            }
         }
      }
   }

   private boolean isDerbyDriver(String var1) {
      return var1.startsWith("org.apache.derby.iapi.jdbc.AutoloadedDriver") || var1.startsWith("org.apache.derby.iapi.client.ClientAutoloadedDriver");
   }

   public InputStream getResourceAsStream(String var1) {
      return this.updateLoader == null ? null : this.updateLoader.getResourceAsStream(var1);
   }

   final String getJarName() {
      return IdUtil.mkQualifiedName(this.name);
   }

   Class loadClassData(String var1, String var2, boolean var3) {
      if (this.updateLoader == null) {
         return null;
      } else {
         try {
            if (this.jar != null) {
               return this.loadClassDataFromJar(var1, var2, var3);
            } else {
               return this.isStream ? this.loadClassData(this.installedJar.getInputStream(), var1, var2, var3) : null;
            }
         } catch (FileNotFoundException var5) {
            return null;
         } catch (IOException var6) {
            if (this.vs != null) {
               this.vs.println(MessageService.getTextMessage("C007", new Object[]{var1, this.getJarName(), var6}));
            }

            return null;
         }
      }
   }

   InputStream getStream(String var1) {
      if (this.updateLoader == null) {
         return null;
      } else if (this.jar != null) {
         return this.getRawStream(var1);
      } else {
         if (this.isStream) {
            try {
               return this.getRawStream(this.installedJar.getInputStream(), var1);
            } catch (FileNotFoundException var3) {
            }
         }

         return null;
      }
   }

   private Class loadClassDataFromJar(String var1, String var2, boolean var3) throws IOException {
      JarEntry var4 = this.jar.getJarEntry(var2);
      if (var4 == null) {
         return null;
      } else {
         InputStream var5 = this.jar.getInputStream(var4);

         Class var6;
         try {
            var6 = this.loadClassData(var4, var5, var1, var3);
         } finally {
            var5.close();
         }

         return var6;
      }
   }

   private Class loadClassData(InputStream var1, String var2, String var3, boolean var4) throws IOException {
      JarInputStream var5 = new JarInputStream(var1);

      JarEntry var6;
      do {
         var6 = var5.getNextJarEntry();
         if (var6 == null) {
            var5.close();
            return null;
         }
      } while(!var6.getName().equals(var3));

      Class var7 = this.loadClassData((JarEntry)var6, (InputStream)var5, var2, var4);
      var5.close();
      return var7;
   }

   private Class loadClassData(JarEntry var1, InputStream var2, String var3, boolean var4) throws IOException {
      byte[] var5 = this.readData(var1, var2, var3);
      Certificate[] var6 = this.getSigners(var3, var1);
      synchronized(this.updateLoader) {
         Class var8 = this.updateLoader.checkLoaded(var3, var4);
         if (var8 == null) {
            var8 = this.defineClass(var3, var5, 0, var5.length, (CodeSource)null);
            if (var6 != null) {
               this.setSigners(var8, var6);
            }

            if (var4) {
               this.resolveClass(var8);
            }
         }

         return var8;
      }
   }

   Class checkLoaded(String var1, boolean var2) {
      if (this.updateLoader == null) {
         return null;
      } else {
         Class var3 = this.findLoadedClass(var1);
         if (var3 != null && var2) {
            this.resolveClass(var3);
         }

         return var3;
      }
   }

   void setInvalid() {
      this.updateLoader = null;
      if (this.jar != null) {
         try {
            this.jar.close();
         } catch (IOException var2) {
         }

         this.jar = null;
      }

      this.isStream = false;
   }

   private InputStream getRawStream(String var1) {
      try {
         JarEntry var2 = this.jar.getJarEntry(var1);
         return var2 == null ? null : this.jar.getInputStream(var2);
      } catch (IOException var3) {
         return null;
      }
   }

   private InputStream getRawStream(InputStream var1, String var2) {
      JarInputStream var3 = null;

      InputStream var6;
      try {
         var3 = new JarInputStream(var1);

         JarEntry var4;
         do {
            if ((var4 = var3.getNextJarEntry()) == null) {
               return null;
            }
         } while(!var4.getName().equals(var2));

         int var5 = (int)var4.getSize();
         if (var5 == -1) {
            var5 = 8192;
         }

         var6 = AccessibleByteArrayOutputStream.copyStream(var3, var5);
      } catch (IOException var17) {
         return null;
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (IOException var16) {
            }
         }

      }

      return var6;
   }

   byte[] readData(JarEntry var1, InputStream var2, String var3) throws IOException {
      try {
         int var4 = (int)var1.getSize();
         if (var4 != -1) {
            byte[] var10 = new byte[var4];
            InputStreamUtil.readFully(var2, var10, 0, var4);
            return var10;
         } else {
            byte[] var5 = new byte[1024];
            ByteArrayOutputStream var6 = new ByteArrayOutputStream(1024);

            int var7;
            while((var7 = var2.read(var5)) != -1) {
               var6.write(var5, 0, var7);
            }

            var5 = var6.toByteArray();
            return var5;
         }
      } catch (SecurityException var8) {
         throw this.handleException(var8, var3);
      }
   }

   private Certificate[] getSigners(String var1, JarEntry var2) throws IOException {
      try {
         Certificate[] var3 = var2.getCertificates();
         if (var3 != null && var3.length != 0) {
            for(int var4 = 0; var4 < var3.length; ++var4) {
               if (!(var3[var4] instanceof X509Certificate)) {
                  String var7 = MessageService.getTextMessage("C001", new Object[]{var1, this.getJarName()});
                  throw new SecurityException(var7);
               }

               X509Certificate var5 = (X509Certificate)var3[var4];
               var5.checkValidity();
            }

            return var3;
         } else {
            return null;
         }
      } catch (GeneralSecurityException var6) {
         throw this.handleException(var6, var1);
      }
   }

   private SecurityException handleException(Exception var1, String var2) {
      String var3 = MessageService.getTextMessage("C002", new Object[]{var2, this.getJarName(), var1.getLocalizedMessage()});
      return new SecurityException(var3);
   }

   public String toString() {
      String var10000 = this.getJarName();
      return var10000 + ":" + super.toString();
   }
}
