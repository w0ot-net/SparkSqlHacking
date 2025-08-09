package org.apache.jute;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class RecordReader {
   private static HashMap archiveFactory = new HashMap();
   private InputArchive archive;

   private static InputArchive createArchive(InputStream in, String format) {
      Method factory = (Method)archiveFactory.get(format);
      if (factory != null) {
         Object[] params = new Object[]{in};

         try {
            return (InputArchive)factory.invoke((Object)null, params);
         } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException ex) {
            ((Exception)ex).printStackTrace();
         }
      }

      return null;
   }

   public RecordReader(InputStream in, String format) {
      this.archive = createArchive(in, format);
   }

   public void read(Record r) throws IOException {
      r.deserialize(this.archive, "");
   }

   static {
      try {
         archiveFactory.put("binary", BinaryInputArchive.class.getDeclaredMethod("getArchive", InputStream.class));
      } catch (NoSuchMethodException | SecurityException ex) {
         ((Exception)ex).printStackTrace();
      }

   }
}
