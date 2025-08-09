package jakarta.activation;

import jakarta.activation.spi.MimeTypeRegistryProvider;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.Vector;

public class MimetypesFileTypeMap extends FileTypeMap {
   private MimeTypeRegistry[] DB;
   private static final int PROG = 0;
   private static final String defaultType = "application/octet-stream";
   private static final String confDir;

   public MimetypesFileTypeMap() {
      Vector<MimeTypeRegistry> dbv = new Vector(5);
      MimeTypeRegistry mf = null;
      dbv.addElement((Object)null);
      LogSupport.log("MimetypesFileTypeMap: load HOME");

      try {
         String user_home = System.getProperty("user.home");
         if (user_home != null) {
            String path = user_home + File.separator + ".mime.types";
            mf = this.loadFile(path);
            if (mf != null) {
               dbv.addElement(mf);
            }
         }
      } catch (SecurityException ex) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Exception during MimetypesFileTypeMap class instantiation", ex);
         }
      }

      LogSupport.log("MimetypesFileTypeMap: load SYS");

      try {
         if (confDir != null) {
            mf = this.loadFile(confDir + "mime.types");
            if (mf != null) {
               dbv.addElement(mf);
            }
         }
      } catch (SecurityException ex) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Exception during MimetypesFileTypeMap class instantiation", ex);
         }
      }

      LogSupport.log("MimetypesFileTypeMap: load JAR");
      this.loadAllResources(dbv, "META-INF/mime.types");
      LogSupport.log("MimetypesFileTypeMap: load DEF");
      mf = this.loadResource("/META-INF/mimetypes.default");
      if (mf != null) {
         dbv.addElement(mf);
      }

      this.DB = new MimeTypeRegistry[dbv.size()];
      dbv.copyInto(this.DB);
   }

   private MimeTypeRegistry loadResource(String name) {
      InputStream clis = null;

      MimeTypeRegistry var4;
      try {
         clis = SecuritySupport.getResourceAsStream(this.getClass(), name);
         if (clis == null) {
            if (LogSupport.isLoggable()) {
               LogSupport.log("MimetypesFileTypeMap: not loading mime types file: " + name);
            }

            return null;
         }

         MimeTypeRegistry mf = this.getImplementation().getByInputStream(clis);
         if (LogSupport.isLoggable()) {
            LogSupport.log("MimetypesFileTypeMap: successfully loaded mime types file: " + name);
         }

         var4 = mf;
      } catch (IOException e) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("MimetypesFileTypeMap: can't load " + name, e);
         }

         return null;
      } catch (IllegalStateException | ServiceConfigurationError | NoSuchElementException e) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Cannot find or load an implementation for MimeTypeRegistryProvider.MimeTypeRegistry: can't load " + name, e);
         }

         return null;
      } finally {
         try {
            if (clis != null) {
               clis.close();
            }
         } catch (IOException ex) {
            if (LogSupport.isLoggable()) {
               LogSupport.log("InputStream cannot be close for " + name, ex);
            }
         }

      }

      return var4;
   }

   private void loadAllResources(Vector v, String name) {
      boolean anyLoaded = false;

      try {
         ClassLoader cld = null;
         cld = SecuritySupport.getContextClassLoader();
         if (cld == null) {
            cld = this.getClass().getClassLoader();
         }

         URL[] urls;
         if (cld != null) {
            urls = SecuritySupport.getResources(cld, name);
         } else {
            urls = SecuritySupport.getSystemResources(name);
         }

         if (urls != null) {
            if (LogSupport.isLoggable()) {
               LogSupport.log("MimetypesFileTypeMap: getResources");
            }

            for(int i = 0; i < urls.length; ++i) {
               URL url = urls[i];
               InputStream clis = null;
               if (LogSupport.isLoggable()) {
                  LogSupport.log("MimetypesFileTypeMap: URL " + url);
               }

               try {
                  clis = SecuritySupport.openStream(url);
                  if (clis != null) {
                     v.addElement(this.getImplementation().getByInputStream(clis));
                     anyLoaded = true;
                     if (LogSupport.isLoggable()) {
                        LogSupport.log("MimetypesFileTypeMap: successfully loaded mime types from URL: " + url);
                     }
                  } else if (LogSupport.isLoggable()) {
                     LogSupport.log("MimetypesFileTypeMap: not loading mime types from URL: " + url);
                  }
               } catch (IOException ioex) {
                  if (LogSupport.isLoggable()) {
                     LogSupport.log("MimetypesFileTypeMap: can't load " + url, ioex);
                  }
               } catch (IllegalStateException | ServiceConfigurationError | NoSuchElementException e) {
                  if (LogSupport.isLoggable()) {
                     LogSupport.log("Cannot find or load an implementation for MimeTypeRegistryProvider.MimeTypeRegistry: can't load " + url, e);
                  }
               } finally {
                  try {
                     if (clis != null) {
                        clis.close();
                     }
                  } catch (IOException cex) {
                     if (LogSupport.isLoggable()) {
                        LogSupport.log("InputStream cannot be close for " + name, cex);
                     }
                  }

               }
            }
         }
      } catch (Exception ex) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("MimetypesFileTypeMap: can't load " + name, ex);
         }
      }

      if (!anyLoaded) {
         LogSupport.log("MimetypesFileTypeMap: !anyLoaded");
         MimeTypeRegistry mf = this.loadResource("/" + name);
         if (mf != null) {
            v.addElement(mf);
         }
      }

   }

   private MimeTypeRegistry loadFile(String name) {
      MimeTypeRegistry mtf = null;

      try {
         mtf = this.getImplementation().getByFileName(name);
      } catch (IOException e) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("MimeTypeRegistry: can't load from file - " + name, e);
         }
      } catch (IllegalStateException | ServiceConfigurationError | NoSuchElementException e) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Cannot find or load an implementation for MimeTypeRegistryProvider.MimeTypeRegistry: can't load " + name, e);
         }
      }

      return mtf;
   }

   public MimetypesFileTypeMap(String mimeTypeFileName) throws IOException {
      this();

      try {
         this.DB[0] = this.getImplementation().getByFileName(mimeTypeFileName);
      } catch (IllegalStateException | ServiceConfigurationError | NoSuchElementException var4) {
         String errorMessage = "Cannot find or load an implementation for MimeTypeRegistryProvider.MimeTypeRegistry: can't load " + mimeTypeFileName;
         if (LogSupport.isLoggable()) {
            LogSupport.log(errorMessage, var4);
         }

         throw new IOException(errorMessage, var4);
      }
   }

   public MimetypesFileTypeMap(InputStream is) {
      this();

      try {
         this.DB[0] = this.getImplementation().getByInputStream(is);
      } catch (IOException var3) {
      } catch (IllegalStateException | ServiceConfigurationError | NoSuchElementException e) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Cannot find or load an implementation for MimeTypeRegistryProvider.MimeTypeRegistry: can't load InputStream", e);
         }
      }

   }

   public synchronized void addMimeTypes(String mime_types) {
      try {
         if (this.DB[0] == null) {
            this.DB[0] = this.getImplementation().getInMemory();
         }

         this.DB[0].appendToRegistry(mime_types);
      } catch (IllegalStateException | ServiceConfigurationError | NoSuchElementException var3) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Cannot find or load an implementation for MimeTypeRegistryProvider.MimeTypeRegistry: can't add " + mime_types, var3);
         }

         throw var3;
      }
   }

   public String getContentType(File f) {
      return this.getContentType(f.getName());
   }

   public synchronized String getContentType(String filename) {
      int dot_pos = filename.lastIndexOf(".");
      if (dot_pos < 0) {
         return "application/octet-stream";
      } else {
         String file_ext = filename.substring(dot_pos + 1);
         if (file_ext.length() == 0) {
            return "application/octet-stream";
         } else {
            for(int i = 0; i < this.DB.length; ++i) {
               if (this.DB[i] != null) {
                  String result = this.DB[i].getMIMETypeString(file_ext);
                  if (result != null) {
                     return result;
                  }
               }
            }

            return "application/octet-stream";
         }
      }
   }

   private MimeTypeRegistryProvider getImplementation() {
      return System.getSecurityManager() != null ? (MimeTypeRegistryProvider)AccessController.doPrivileged(new PrivilegedAction() {
         public MimeTypeRegistryProvider run() {
            return (MimeTypeRegistryProvider)FactoryFinder.find(MimeTypeRegistryProvider.class);
         }
      }) : (MimeTypeRegistryProvider)FactoryFinder.find(MimeTypeRegistryProvider.class);
   }

   static {
      String dir = null;

      try {
         dir = (String)AccessController.doPrivileged(new PrivilegedAction() {
            public String run() {
               String home = System.getProperty("java.home");
               String newdir = home + File.separator + "conf";
               File conf = new File(newdir);
               return conf.exists() ? newdir + File.separator : home + File.separator + "lib" + File.separator;
            }
         });
      } catch (Exception ex) {
         if (LogSupport.isLoggable()) {
            LogSupport.log("Exception during MimetypesFileTypeMap class loading", ex);
         }
      }

      confDir = dir;
   }
}
