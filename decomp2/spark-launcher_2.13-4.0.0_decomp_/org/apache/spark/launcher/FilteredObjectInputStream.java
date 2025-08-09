package org.apache.spark.launcher;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.List;

class FilteredObjectInputStream extends ObjectInputStream {
   private static final List ALLOWED_PACKAGES = Arrays.asList("org.apache.spark.launcher.", "java.lang.");

   FilteredObjectInputStream(InputStream is) throws IOException {
      super(is);
   }

   protected Class resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
      boolean isValid = ALLOWED_PACKAGES.stream().anyMatch((p) -> desc.getName().startsWith(p));
      if (!isValid) {
         throw new IllegalArgumentException(String.format("Unexpected class in stream: %s", desc.getName()));
      } else {
         return super.resolveClass(desc);
      }
   }
}
