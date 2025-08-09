package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ResourceClaimSchedulingStatusFluent extends BaseFluent {
   private String name;
   private List unsuitableNodes = new ArrayList();
   private Map additionalProperties;

   public ResourceClaimSchedulingStatusFluent() {
   }

   public ResourceClaimSchedulingStatusFluent(ResourceClaimSchedulingStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClaimSchedulingStatus instance) {
      instance = instance != null ? instance : new ResourceClaimSchedulingStatus();
      if (instance != null) {
         this.withName(instance.getName());
         this.withUnsuitableNodes(instance.getUnsuitableNodes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public ResourceClaimSchedulingStatusFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ResourceClaimSchedulingStatusFluent addToUnsuitableNodes(int index, String item) {
      if (this.unsuitableNodes == null) {
         this.unsuitableNodes = new ArrayList();
      }

      this.unsuitableNodes.add(index, item);
      return this;
   }

   public ResourceClaimSchedulingStatusFluent setToUnsuitableNodes(int index, String item) {
      if (this.unsuitableNodes == null) {
         this.unsuitableNodes = new ArrayList();
      }

      this.unsuitableNodes.set(index, item);
      return this;
   }

   public ResourceClaimSchedulingStatusFluent addToUnsuitableNodes(String... items) {
      if (this.unsuitableNodes == null) {
         this.unsuitableNodes = new ArrayList();
      }

      for(String item : items) {
         this.unsuitableNodes.add(item);
      }

      return this;
   }

   public ResourceClaimSchedulingStatusFluent addAllToUnsuitableNodes(Collection items) {
      if (this.unsuitableNodes == null) {
         this.unsuitableNodes = new ArrayList();
      }

      for(String item : items) {
         this.unsuitableNodes.add(item);
      }

      return this;
   }

   public ResourceClaimSchedulingStatusFluent removeFromUnsuitableNodes(String... items) {
      if (this.unsuitableNodes == null) {
         return this;
      } else {
         for(String item : items) {
            this.unsuitableNodes.remove(item);
         }

         return this;
      }
   }

   public ResourceClaimSchedulingStatusFluent removeAllFromUnsuitableNodes(Collection items) {
      if (this.unsuitableNodes == null) {
         return this;
      } else {
         for(String item : items) {
            this.unsuitableNodes.remove(item);
         }

         return this;
      }
   }

   public List getUnsuitableNodes() {
      return this.unsuitableNodes;
   }

   public String getUnsuitableNode(int index) {
      return (String)this.unsuitableNodes.get(index);
   }

   public String getFirstUnsuitableNode() {
      return (String)this.unsuitableNodes.get(0);
   }

   public String getLastUnsuitableNode() {
      return (String)this.unsuitableNodes.get(this.unsuitableNodes.size() - 1);
   }

   public String getMatchingUnsuitableNode(Predicate predicate) {
      for(String item : this.unsuitableNodes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingUnsuitableNode(Predicate predicate) {
      for(String item : this.unsuitableNodes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceClaimSchedulingStatusFluent withUnsuitableNodes(List unsuitableNodes) {
      if (unsuitableNodes != null) {
         this.unsuitableNodes = new ArrayList();

         for(String item : unsuitableNodes) {
            this.addToUnsuitableNodes(item);
         }
      } else {
         this.unsuitableNodes = null;
      }

      return this;
   }

   public ResourceClaimSchedulingStatusFluent withUnsuitableNodes(String... unsuitableNodes) {
      if (this.unsuitableNodes != null) {
         this.unsuitableNodes.clear();
         this._visitables.remove("unsuitableNodes");
      }

      if (unsuitableNodes != null) {
         for(String item : unsuitableNodes) {
            this.addToUnsuitableNodes(item);
         }
      }

      return this;
   }

   public boolean hasUnsuitableNodes() {
      return this.unsuitableNodes != null && !this.unsuitableNodes.isEmpty();
   }

   public ResourceClaimSchedulingStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClaimSchedulingStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClaimSchedulingStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClaimSchedulingStatusFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public ResourceClaimSchedulingStatusFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            ResourceClaimSchedulingStatusFluent that = (ResourceClaimSchedulingStatusFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.unsuitableNodes, that.unsuitableNodes)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.name, this.unsuitableNodes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.unsuitableNodes != null && !this.unsuitableNodes.isEmpty()) {
         sb.append("unsuitableNodes:");
         sb.append(this.unsuitableNodes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
