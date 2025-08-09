package org.sparkproject.jpmml.model;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import org.sparkproject.dmg.pmml.PMMLObject;

public class JavaSerializer implements Serializer {
   private ClassLoader clazzLoader;

   public JavaSerializer() {
      this((ClassLoader)null);
   }

   public JavaSerializer(ClassLoader clazzLoader) {
      this.clazzLoader = null;
      this.setClassLoader(clazzLoader);
   }

   public PMMLObject deserialize(InputStream is) throws ClassNotFoundException, IOException {
      final ClassLoader clazzLoader = this.getClassLoader();
      FilterInputStream safeIs = new FilterInputStream(is) {
         public void close() {
         }
      };
      ObjectInputStream objectIs = new ObjectInputStream(safeIs) {
         public Class resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException, IOException {
            if (clazzLoader != null) {
               Class<?> clazz = Class.forName(objectStreamClass.getName(), false, clazzLoader);
               if (clazz != null) {
                  return clazz;
               }
            }

            return super.resolveClass(objectStreamClass);
         }
      };

      PMMLObject var5;
      try {
         var5 = (PMMLObject)objectIs.readObject();
      } catch (Throwable var8) {
         try {
            objectIs.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      objectIs.close();
      return var5;
   }

   public void serialize(PMMLObject object, OutputStream os) throws IOException {
      FilterOutputStream safeOs = new FilterOutputStream(os) {
         public void close() throws IOException {
            super.flush();
         }
      };
      ObjectOutputStream objectOs = new ObjectOutputStream(safeOs);

      try {
         objectOs.writeObject(object);
         objectOs.flush();
      } catch (Throwable var8) {
         try {
            objectOs.close();
         } catch (Throwable var7) {
            var8.addSuppressed(var7);
         }

         throw var8;
      }

      objectOs.close();
   }

   protected ClassLoader getClassLoader() {
      return this.clazzLoader;
   }

   private void setClassLoader(ClassLoader clazzLoader) {
      this.clazzLoader = clazzLoader;
   }
}
