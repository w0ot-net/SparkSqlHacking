package org.apache.parquet;

public class Exceptions {
   public static void throwIfInstance(Throwable t, Class excClass) throws Exception {
      if (excClass.isAssignableFrom(t.getClass())) {
         throw (Exception)excClass.cast(t);
      }
   }
}
