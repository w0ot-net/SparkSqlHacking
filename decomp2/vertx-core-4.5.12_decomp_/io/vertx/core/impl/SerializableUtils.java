package io.vertx.core.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtils {
   public static byte[] toBytes(Object o) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      try {
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         Throwable var3 = null;

         try {
            oos.writeObject(o);
            oos.flush();
         } catch (Throwable var13) {
            var3 = var13;
            throw var13;
         } finally {
            if (oos != null) {
               if (var3 != null) {
                  try {
                     oos.close();
                  } catch (Throwable var12) {
                     var3.addSuppressed(var12);
                  }
               } else {
                  oos.close();
               }
            }

         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }

      return baos.toByteArray();
   }

   public static Object fromBytes(byte[] bytes, ObjectInputStreamFactory factory) {
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

      try {
         ObjectInputStream ois = factory.create(bais);
         Throwable var4 = null;

         Object var5;
         try {
            var5 = ois.readObject();
         } catch (Throwable var15) {
            var4 = var15;
            throw var15;
         } finally {
            if (ois != null) {
               if (var4 != null) {
                  try {
                     ois.close();
                  } catch (Throwable var14) {
                     var4.addSuppressed(var14);
                  }
               } else {
                  ois.close();
               }
            }

         }

         return var5;
      } catch (ClassNotFoundException | IOException e) {
         throw new RuntimeException(e);
      }
   }

   private SerializableUtils() {
   }

   @FunctionalInterface
   public interface ObjectInputStreamFactory {
      ObjectInputStream create(ByteArrayInputStream var1) throws IOException;
   }
}
