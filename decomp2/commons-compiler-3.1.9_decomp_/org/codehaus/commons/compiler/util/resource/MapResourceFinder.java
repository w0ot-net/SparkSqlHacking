package org.codehaus.commons.compiler.util.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.commons.compiler.util.Predicate;
import org.codehaus.commons.compiler.util.iterator.Iterables;
import org.codehaus.commons.nullanalysis.Nullable;

public class MapResourceFinder extends ListableResourceFinder {
   private final Map map = new HashMap();
   private long lastModified;

   public MapResourceFinder() {
   }

   public MapResourceFinder(Map map) {
      for(Map.Entry me : map.entrySet()) {
         Resource prev = this.addResource((String)me.getKey(), (byte[])me.getValue());

         assert prev == null;
      }

   }

   @Nullable
   public Resource addResource(final String fileName, final byte[] data) {
      return (Resource)this.map.put(fileName, new Resource() {
         public InputStream open() {
            return new ByteArrayInputStream(data);
         }

         public String getFileName() {
            return fileName;
         }

         public long lastModified() {
            return MapResourceFinder.this.lastModified;
         }
      });
   }

   @Nullable
   public Resource addResource(String fileName, String data) {
      return this.addResource(fileName, data.getBytes());
   }

   public Resource addResource(Resource resource) {
      return (Resource)this.map.put(resource.getFileName(), resource);
   }

   public Collection resources() {
      return Collections.unmodifiableCollection(this.map.values());
   }

   public final void setLastModified(long lastModified) {
      this.lastModified = lastModified;
   }

   @Nullable
   public final Resource findResource(String resourceName) {
      return (Resource)this.map.get(resourceName);
   }

   @Nullable
   public Iterable list(final String resourceNamePrefix, final boolean recurse) {
      return Iterables.filter((Iterable)this.map.values(), new Predicate() {
         public boolean evaluate(@Nullable Object o) {
            Resource r = (Resource)o;

            assert r != null;

            String resourceName = r.getFileName();
            int rnpl = resourceNamePrefix.length();
            return resourceName.startsWith(resourceNamePrefix) && (recurse || resourceName.indexOf(47, rnpl) == -1);
         }
      });
   }
}
