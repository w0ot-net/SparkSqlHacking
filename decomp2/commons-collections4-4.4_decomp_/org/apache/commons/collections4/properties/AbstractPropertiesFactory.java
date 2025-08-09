package org.apache.commons.collections4.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public abstract class AbstractPropertiesFactory {
   protected AbstractPropertiesFactory() {
   }

   protected abstract Properties createProperties();

   public Properties load(ClassLoader classLoader, String name) throws IOException {
      InputStream inputStream = classLoader.getResourceAsStream(name);
      Throwable var4 = null;

      Properties var5;
      try {
         var5 = this.load(inputStream);
      } catch (Throwable var14) {
         var4 = var14;
         throw var14;
      } finally {
         if (inputStream != null) {
            if (var4 != null) {
               try {
                  inputStream.close();
               } catch (Throwable var13) {
                  var4.addSuppressed(var13);
               }
            } else {
               inputStream.close();
            }
         }

      }

      return var5;
   }

   public Properties load(File file) throws FileNotFoundException, IOException {
      FileInputStream inputStream = new FileInputStream(file);
      Throwable var3 = null;

      Properties var4;
      try {
         var4 = this.load((InputStream)inputStream);
      } catch (Throwable var13) {
         var3 = var13;
         throw var13;
      } finally {
         if (inputStream != null) {
            if (var3 != null) {
               try {
                  inputStream.close();
               } catch (Throwable var12) {
                  var3.addSuppressed(var12);
               }
            } else {
               inputStream.close();
            }
         }

      }

      return var4;
   }

   public Properties load(InputStream inputStream) throws IOException {
      if (inputStream == null) {
         return null;
      } else {
         T properties = (T)this.createProperties();
         properties.load(inputStream);
         return properties;
      }
   }

   public Properties load(Path path) throws IOException {
      InputStream inputStream = Files.newInputStream(path);
      Throwable var3 = null;

      Properties var4;
      try {
         var4 = this.load(inputStream);
      } catch (Throwable var13) {
         var3 = var13;
         throw var13;
      } finally {
         if (inputStream != null) {
            if (var3 != null) {
               try {
                  inputStream.close();
               } catch (Throwable var12) {
                  var3.addSuppressed(var12);
               }
            } else {
               inputStream.close();
            }
         }

      }

      return var4;
   }

   public Properties load(Reader reader) throws IOException {
      T properties = (T)this.createProperties();
      properties.load(reader);
      return properties;
   }

   public Properties load(String name) throws IOException {
      FileInputStream inputStream = new FileInputStream(name);
      Throwable var3 = null;

      Properties var4;
      try {
         var4 = this.load((InputStream)inputStream);
      } catch (Throwable var13) {
         var3 = var13;
         throw var13;
      } finally {
         if (inputStream != null) {
            if (var3 != null) {
               try {
                  inputStream.close();
               } catch (Throwable var12) {
                  var3.addSuppressed(var12);
               }
            } else {
               inputStream.close();
            }
         }

      }

      return var4;
   }

   public Properties load(URI uri) throws IOException {
      return this.load(Paths.get(uri));
   }

   public Properties load(URL url) throws IOException {
      InputStream inputStream = url.openStream();
      Throwable var3 = null;

      Properties var4;
      try {
         var4 = this.load(inputStream);
      } catch (Throwable var13) {
         var3 = var13;
         throw var13;
      } finally {
         if (inputStream != null) {
            if (var3 != null) {
               try {
                  inputStream.close();
               } catch (Throwable var12) {
                  var3.addSuppressed(var12);
               }
            } else {
               inputStream.close();
            }
         }

      }

      return var4;
   }
}
