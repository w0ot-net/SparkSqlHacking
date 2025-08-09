package org.sparkproject.jetty.util;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.util.Optional;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ManifestUtils {
   private ManifestUtils() {
   }

   public static Optional getManifest(Class klass) {
      try {
         CodeSource codeSource = klass.getProtectionDomain().getCodeSource();
         if (codeSource != null) {
            URL location = codeSource.getLocation();
            if (location != null) {
               JarFile jarFile = new JarFile(new File(location.toURI()));

               Optional var4;
               try {
                  var4 = Optional.of(jarFile.getManifest());
               } catch (Throwable var7) {
                  try {
                     jarFile.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }

                  throw var7;
               }

               jarFile.close();
               return var4;
            }
         }

         return Optional.empty();
      } catch (Throwable var8) {
         return Optional.empty();
      }
   }

   public static Optional getVersion(Class klass) {
      Optional<String> version = getManifest(klass).map(Manifest::getMainAttributes).map((attributes) -> attributes.getValue("Implementation-Version"));
      if (version.isPresent()) {
         return version;
      } else {
         try {
            Object module = klass.getClass().getMethod("getModule").invoke(klass);
            Object descriptor = module.getClass().getMethod("getDescriptor").invoke(module);
            return (Optional)descriptor.getClass().getMethod("rawVersion").invoke(descriptor);
         } catch (Throwable var4) {
            return Optional.empty();
         }
      }
   }
}
