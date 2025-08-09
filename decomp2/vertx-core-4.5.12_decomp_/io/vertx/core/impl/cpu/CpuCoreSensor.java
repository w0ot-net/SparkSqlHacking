package io.vertx.core.impl.cpu;

import io.vertx.core.impl.launcher.commands.ExecUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;

public class CpuCoreSensor {
   private static final String CPUS_ALLOWED = "Cpus_allowed:";
   private static final byte[] BITS = new byte[]{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

   public static int availableProcessors() {
      return System.getSecurityManager() != null ? (Integer)AccessController.doPrivileged(() -> determineProcessors()) : determineProcessors();
   }

   private static int determineProcessors() {
      int fromJava = Runtime.getRuntime().availableProcessors();
      int fromProcFile = 0;
      if (!ExecUtils.isLinux()) {
         return fromJava;
      } else {
         try {
            fromProcFile = readCPUMask(new File("/proc/self/status"));
         } catch (Exception var3) {
         }

         return fromProcFile > 0 ? Math.min(fromJava, fromProcFile) : fromJava;
      }
   }

   protected static int readCPUMask(File file) throws IOException {
      if (file != null && file.exists()) {
         FileInputStream stream = new FileInputStream(file);
         Throwable var2 = null;

         try {
            InputStreamReader inputReader = new InputStreamReader(stream, StandardCharsets.US_ASCII);
            Throwable var4 = null;

            try {
               BufferedReader reader = new BufferedReader(inputReader);
               Throwable var6 = null;

               try {
                  String line;
                  while((line = reader.readLine()) != null) {
                     if (line.startsWith("Cpus_allowed:")) {
                        int count = 0;
                        int start = "Cpus_allowed:".length();

                        for(int i = start; i < line.length(); ++i) {
                           char ch = line.charAt(i);
                           if (ch >= '0' && ch <= '9') {
                              count += BITS[ch - 48];
                           } else if (ch >= 'a' && ch <= 'f') {
                              count += BITS[ch - 97 + 10];
                           } else if (ch >= 'A' && ch <= 'F') {
                              count += BITS[ch - 65 + 10];
                           }
                        }

                        int var66 = count;
                        return var66;
                     }
                  }

                  return -1;
               } catch (Throwable var60) {
                  var6 = var60;
                  throw var60;
               } finally {
                  if (reader != null) {
                     if (var6 != null) {
                        try {
                           reader.close();
                        } catch (Throwable var59) {
                           var6.addSuppressed(var59);
                        }
                     } else {
                        reader.close();
                     }
                  }

               }
            } catch (Throwable var62) {
               var4 = var62;
               throw var62;
            } finally {
               if (inputReader != null) {
                  if (var4 != null) {
                     try {
                        inputReader.close();
                     } catch (Throwable var58) {
                        var4.addSuppressed(var58);
                     }
                  } else {
                     inputReader.close();
                  }
               }

            }
         } catch (Throwable var64) {
            var2 = var64;
            throw var64;
         } finally {
            if (stream != null) {
               if (var2 != null) {
                  try {
                     stream.close();
                  } catch (Throwable var57) {
                     var2.addSuppressed(var57);
                  }
               } else {
                  stream.close();
               }
            }

         }
      } else {
         return -1;
      }
   }
}
