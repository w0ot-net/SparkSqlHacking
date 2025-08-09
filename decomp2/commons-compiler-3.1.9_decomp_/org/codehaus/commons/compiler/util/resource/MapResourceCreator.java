package org.codehaus.commons.compiler.util.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MapResourceCreator implements ResourceCreator {
   private final Map map;

   public MapResourceCreator() {
      this.map = new HashMap();
   }

   public MapResourceCreator(Map map) {
      this.map = map;
   }

   public final Map getMap() {
      return this.map;
   }

   public final OutputStream createResource(final String resourceName) {
      return new ByteArrayOutputStream() {
         public void close() throws IOException {
            super.close();
            MapResourceCreator.this.map.put(resourceName, this.toByteArray());
         }
      };
   }

   public final boolean deleteResource(String resourceName) {
      return this.map.remove(resourceName) != null;
   }
}
