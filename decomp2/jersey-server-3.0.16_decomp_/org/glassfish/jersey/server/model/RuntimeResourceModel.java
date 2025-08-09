package org.glassfish.jersey.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.uri.PathTemplate;

public class RuntimeResourceModel {
   private final List runtimeResources = new ArrayList();

   public RuntimeResourceModel(List resources) {
      for(RuntimeResource.Builder builder : this.getRuntimeResources(resources)) {
         this.runtimeResources.add(builder.build((RuntimeResource)null));
      }

      Collections.sort(this.runtimeResources, RuntimeResource.COMPARATOR);
   }

   private List getRuntimeResources(List resources) {
      Map<String, List<Resource>> regexMap = new HashMap();

      for(Resource resource : resources) {
         String path = resource.getPath();
         String regex = null;
         if (path != null) {
            if (path.endsWith("/")) {
               path = path.substring(0, path.length() - 1);
            }

            regex = (new PathTemplate(path)).getPattern().getRegex();
         }

         List<Resource> listFromMap = (List)regexMap.get(regex);
         if (listFromMap == null) {
            listFromMap = new ArrayList();
            regexMap.put(regex, listFromMap);
         }

         listFromMap.add(resource);
      }

      List<RuntimeResource.Builder> runtimeResources = new ArrayList();

      for(Map.Entry entry : regexMap.entrySet()) {
         List<Resource> resourcesWithSameRegex = (List)entry.getValue();
         List<Resource> childResources = new ArrayList();

         for(Resource res : resourcesWithSameRegex) {
            childResources.addAll(res.getChildResources());
         }

         List<RuntimeResource.Builder> childRuntimeResources = this.getRuntimeResources(childResources);
         runtimeResources.add(new RuntimeResource.Builder(resourcesWithSameRegex, childRuntimeResources, (String)entry.getKey()));
      }

      return runtimeResources;
   }

   public List getRuntimeResources() {
      return this.runtimeResources;
   }
}
