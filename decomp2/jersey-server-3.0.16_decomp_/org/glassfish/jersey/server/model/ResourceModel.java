package org.glassfish.jersey.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;

public class ResourceModel implements ResourceModelComponent {
   private final List rootResources;
   private final List resources;
   private final Value runtimeRootResourceModelValue;

   private ResourceModel(List rootResources, List allResources) {
      this.resources = allResources;
      this.rootResources = rootResources;
      this.runtimeRootResourceModelValue = Values.lazy(new Value() {
         public RuntimeResourceModel get() {
            return new RuntimeResourceModel(ResourceModel.this.resources);
         }
      });
   }

   public List getRootResources() {
      return this.rootResources;
   }

   public List getResources() {
      return this.resources;
   }

   public void accept(ResourceModelVisitor visitor) {
      visitor.visitResourceModel(this);
   }

   public List getComponents() {
      List<ResourceModelComponent> components = new ArrayList();
      components.addAll(this.resources);
      components.addAll(this.getRuntimeResourceModel().getRuntimeResources());
      return components;
   }

   public RuntimeResourceModel getRuntimeResourceModel() {
      return (RuntimeResourceModel)this.runtimeRootResourceModelValue.get();
   }

   public static class Builder {
      private final List resources;
      private final boolean subResourceModel;

      public Builder(ResourceModel resourceModel, boolean subResourceModel) {
         this.resources = resourceModel.getResources();
         this.subResourceModel = subResourceModel;
      }

      public Builder(List resources, boolean subResourceModel) {
         this.resources = resources;
         this.subResourceModel = subResourceModel;
      }

      public Builder(boolean subResourceModel) {
         this.resources = new ArrayList();
         this.subResourceModel = subResourceModel;
      }

      public Builder addResource(Resource resource) {
         this.resources.add(resource);
         return this;
      }

      public ResourceModel build() {
         Map<String, Resource> resourceMap = new LinkedHashMap();
         Set<Resource> separateResources = Collections.newSetFromMap(new IdentityHashMap());

         for(Resource resource : this.resources) {
            String path = resource.getPath();
            if (path == null && !this.subResourceModel) {
               separateResources.add(resource);
            } else {
               Resource fromMap = (Resource)resourceMap.get(path);
               if (fromMap == null) {
                  resourceMap.put(path, resource);
               } else {
                  resourceMap.put(path, Resource.builder(fromMap).mergeWith(resource).build());
               }
            }
         }

         List<Resource> rootResources = new ArrayList();
         List<Resource> allResources = new ArrayList();

         for(Map.Entry entry : resourceMap.entrySet()) {
            if (entry.getKey() != null) {
               rootResources.add(entry.getValue());
            }

            allResources.add(entry.getValue());
         }

         if (!this.subResourceModel) {
            allResources.addAll(separateResources);
         }

         return new ResourceModel(rootResources, allResources);
      }
   }
}
