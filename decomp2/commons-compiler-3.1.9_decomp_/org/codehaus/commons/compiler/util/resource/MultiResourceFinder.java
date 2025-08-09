package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.commons.nullanalysis.Nullable;

public class MultiResourceFinder extends ListableResourceFinder {
   private final Iterable resourceFinders;

   public MultiResourceFinder(Iterable resourceFinders) {
      this.resourceFinders = resourceFinders;
   }

   public MultiResourceFinder(ResourceFinder... resourceFinders) {
      this((Iterable)Arrays.asList(resourceFinders));
   }

   @Nullable
   public final Resource findResource(String resourceName) {
      for(ResourceFinder rf : this.resourceFinders) {
         Resource resource = rf.findResource(resourceName);
         if (resource != null) {
            return resource;
         }
      }

      return null;
   }

   @Nullable
   public Iterable list(String resourceNamePrefix, boolean recurse) throws IOException {
      List<Resource> result = new ArrayList();

      for(ResourceFinder rf : this.resourceFinders) {
         Iterable<Resource> resources = ((ListableResourceFinder)rf).list(resourceNamePrefix, recurse);
         if (resources != null) {
            for(Resource r : resources) {
               result.add(r);
            }
         }
      }

      return result;
   }
}
