package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.java.util.jar.Pack200;

public class Pack200Utils {
   public static void normalize(File jar) throws IOException {
      normalize(jar, jar, (Map)null);
   }

   public static void normalize(File from, File to) throws IOException {
      normalize(from, to, (Map)null);
   }

   public static void normalize(File from, File to, Map props) throws IOException {
      if (props == null) {
         props = new HashMap();
      }

      props.put("pack.segment.limit", "-1");
      Path tempFile = Files.createTempFile("commons-compress", "pack200normalize");

      try {
         OutputStream fos = Files.newOutputStream(tempFile);

         try {
            JarFile jarFile = new JarFile(from);

            try {
               Pack200.Packer packer = Pack200.newPacker();
               packer.properties().putAll(props);
               packer.pack(jarFile, fos);
            } catch (Throwable var20) {
               try {
                  jarFile.close();
               } catch (Throwable var18) {
                  var20.addSuppressed(var18);
               }

               throw var20;
            }

            jarFile.close();
         } catch (Throwable var21) {
            if (fos != null) {
               try {
                  fos.close();
               } catch (Throwable var17) {
                  var21.addSuppressed(var17);
               }
            }

            throw var21;
         }

         if (fos != null) {
            fos.close();
         }

         Pack200.Unpacker unpacker = Pack200.newUnpacker();
         JarOutputStream jos = new JarOutputStream(Files.newOutputStream(to.toPath()));

         try {
            unpacker.unpack(tempFile.toFile(), jos);
         } catch (Throwable var19) {
            try {
               jos.close();
            } catch (Throwable var16) {
               var19.addSuppressed(var16);
            }

            throw var19;
         }

         jos.close();
      } finally {
         Files.delete(tempFile);
      }

   }

   public static void normalize(File jar, Map props) throws IOException {
      normalize(jar, jar, props);
   }

   private Pack200Utils() {
   }
}
