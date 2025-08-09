package org.apache.jute;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class RecordWriter {
   private OutputArchive archive;
   private static HashMap archiveFactory = constructFactory();

   static HashMap constructFactory() {
      HashMap<String, Method> factory = new HashMap();

      try {
         factory.put("binary", BinaryOutputArchive.class.getDeclaredMethod("getArchive", OutputStream.class));
      } catch (NoSuchMethodException | SecurityException ex) {
         ((Exception)ex).printStackTrace();
      }

      return factory;
   }

   private static OutputArchive createArchive(OutputStream out, String format) {
      Method factory = (Method)archiveFactory.get(format);
      if (factory != null) {
         Object[] params = new Object[]{out};

         try {
            return (OutputArchive)factory.invoke((Object)null, params);
         } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException ex) {
            ((Exception)ex).printStackTrace();
         }
      }

      return null;
   }

   public RecordWriter(OutputStream out, String format) {
      this.archive = createArchive(out, format);
   }

   public void write(Record r) throws IOException {
      r.serialize(this.archive, "");
   }
}
