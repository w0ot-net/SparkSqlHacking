package org.apache.parquet.hadoop.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;

public final class SerializationUtil {
   private SerializationUtil() {
   }

   public static void writeObjectToConfAsBase64(String key, Object obj, Configuration conf) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Throwable var4 = null;

      try {
         GZIPOutputStream gos = new GZIPOutputStream(baos);
         Throwable var6 = null;

         try {
            ObjectOutputStream oos = new ObjectOutputStream(gos);
            Throwable var8 = null;

            try {
               oos.writeObject(obj);
            } catch (Throwable var52) {
               var8 = var52;
               throw var52;
            } finally {
               if (oos != null) {
                  if (var8 != null) {
                     try {
                        oos.close();
                     } catch (Throwable var51) {
                        var8.addSuppressed(var51);
                     }
                  } else {
                     oos.close();
                  }
               }

            }
         } catch (Throwable var54) {
            var6 = var54;
            throw var54;
         } finally {
            if (gos != null) {
               if (var6 != null) {
                  try {
                     gos.close();
                  } catch (Throwable var50) {
                     var6.addSuppressed(var50);
                  }
               } else {
                  gos.close();
               }
            }

         }

         conf.set(key, new String(Base64.getMimeEncoder().encode(baos.toByteArray()), StandardCharsets.UTF_8));
      } catch (Throwable var56) {
         var4 = var56;
         throw var56;
      } finally {
         if (baos != null) {
            if (var4 != null) {
               try {
                  baos.close();
               } catch (Throwable var49) {
                  var4.addSuppressed(var49);
               }
            } else {
               baos.close();
            }
         }

      }

   }

   public static Object readObjectFromConfAsBase64(String key, Configuration conf) throws IOException {
      return readObjectFromConfAsBase64(key, (ParquetConfiguration)(new HadoopParquetConfiguration(conf)));
   }

   public static Object readObjectFromConfAsBase64(String key, ParquetConfiguration conf) throws IOException {
      String b64 = conf.get(key);
      if (b64 == null) {
         return null;
      } else {
         byte[] bytes = Base64.getMimeDecoder().decode(b64.getBytes(StandardCharsets.UTF_8));

         try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            Throwable var5 = null;

            Object var10;
            try {
               GZIPInputStream gis = new GZIPInputStream(bais);
               Throwable var7 = null;

               try {
                  ObjectInputStream ois = new ObjectInputStream(gis);
                  Throwable var9 = null;

                  try {
                     var10 = ois.readObject();
                  } catch (Throwable var60) {
                     var10 = var60;
                     var9 = var60;
                     throw var60;
                  } finally {
                     if (ois != null) {
                        if (var9 != null) {
                           try {
                              ois.close();
                           } catch (Throwable var59) {
                              var9.addSuppressed(var59);
                           }
                        } else {
                           ois.close();
                        }
                     }

                  }
               } catch (Throwable var62) {
                  var7 = var62;
                  throw var62;
               } finally {
                  if (gis != null) {
                     if (var7 != null) {
                        try {
                           gis.close();
                        } catch (Throwable var58) {
                           var7.addSuppressed(var58);
                        }
                     } else {
                        gis.close();
                     }
                  }

               }
            } catch (Throwable var64) {
               var5 = var64;
               throw var64;
            } finally {
               if (bais != null) {
                  if (var5 != null) {
                     try {
                        bais.close();
                     } catch (Throwable var57) {
                        var5.addSuppressed(var57);
                     }
                  } else {
                     bais.close();
                  }
               }

            }

            return var10;
         } catch (ClassNotFoundException e) {
            throw new IOException("Could not read object from config with key " + key, e);
         } catch (ClassCastException e) {
            throw new IOException("Could not cast object read from config with key " + key, e);
         }
      }
   }
}
