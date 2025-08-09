package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class PodSchedulingContextSpecFluent extends BaseFluent {
   private List potentialNodes = new ArrayList();
   private String selectedNode;
   private Map additionalProperties;

   public PodSchedulingContextSpecFluent() {
   }

   public PodSchedulingContextSpecFluent(PodSchedulingContextSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodSchedulingContextSpec instance) {
      instance = instance != null ? instance : new PodSchedulingContextSpec();
      if (instance != null) {
         this.withPotentialNodes(instance.getPotentialNodes());
         this.withSelectedNode(instance.getSelectedNode());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodSchedulingContextSpecFluent addToPotentialNodes(int index, String item) {
      if (this.potentialNodes == null) {
         this.potentialNodes = new ArrayList();
      }

      this.potentialNodes.add(index, item);
      return this;
   }

   public PodSchedulingContextSpecFluent setToPotentialNodes(int index, String item) {
      if (this.potentialNodes == null) {
         this.potentialNodes = new ArrayList();
      }

      this.potentialNodes.set(index, item);
      return this;
   }

   public PodSchedulingContextSpecFluent addToPotentialNodes(String... items) {
      if (this.potentialNodes == null) {
         this.potentialNodes = new ArrayList();
      }

      for(String item : items) {
         this.potentialNodes.add(item);
      }

      return this;
   }

   public PodSchedulingContextSpecFluent addAllToPotentialNodes(Collection items) {
      if (this.potentialNodes == null) {
         this.potentialNodes = new ArrayList();
      }

      for(String item : items) {
         this.potentialNodes.add(item);
      }

      return this;
   }

   public PodSchedulingContextSpecFluent removeFromPotentialNodes(String... items) {
      if (this.potentialNodes == null) {
         return this;
      } else {
         for(String item : items) {
            this.potentialNodes.remove(item);
         }

         return this;
      }
   }

   public PodSchedulingContextSpecFluent removeAllFromPotentialNodes(Collection items) {
      if (this.potentialNodes == null) {
         return this;
      } else {
         for(String item : items) {
            this.potentialNodes.remove(item);
         }

         return this;
      }
   }

   public List getPotentialNodes() {
      return this.potentialNodes;
   }

   public String getPotentialNode(int index) {
      return (String)this.potentialNodes.get(index);
   }

   public String getFirstPotentialNode() {
      return (String)this.potentialNodes.get(0);
   }

   public String getLastPotentialNode() {
      return (String)this.potentialNodes.get(this.potentialNodes.size() - 1);
   }

   public String getMatchingPotentialNode(Predicate predicate) {
      for(String item : this.potentialNodes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingPotentialNode(Predicate predicate) {
      for(String item : this.potentialNodes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSchedulingContextSpecFluent withPotentialNodes(List potentialNodes) {
      if (potentialNodes != null) {
         this.potentialNodes = new ArrayList();

         for(String item : potentialNodes) {
            this.addToPotentialNodes(item);
         }
      } else {
         this.potentialNodes = null;
      }

      return this;
   }

   public PodSchedulingContextSpecFluent withPotentialNodes(String... potentialNodes) {
      if (this.potentialNodes != null) {
         this.potentialNodes.clear();
         this._visitables.remove("potentialNodes");
      }

      if (potentialNodes != null) {
         for(String item : potentialNodes) {
            this.addToPotentialNodes(item);
         }
      }

      return this;
   }

   public boolean hasPotentialNodes() {
      return this.potentialNodes != null && !this.potentialNodes.isEmpty();
   }

   public String getSelectedNode() {
      return this.selectedNode;
   }

   public PodSchedulingContextSpecFluent withSelectedNode(String selectedNode) {
      this.selectedNode = selectedNode;
      return this;
   }

   public boolean hasSelectedNode() {
      return this.selectedNode != null;
   }

   public PodSchedulingContextSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodSchedulingContextSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodSchedulingContextSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodSchedulingContextSpecFluent removeFromAdditionalProperties(Map map) {
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

   public PodSchedulingContextSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PodSchedulingContextSpecFluent that = (PodSchedulingContextSpecFluent)o;
            if (!Objects.equals(this.potentialNodes, that.potentialNodes)) {
               return false;
            } else if (!Objects.equals(this.selectedNode, that.selectedNode)) {
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
      return Objects.hash(new Object[]{this.potentialNodes, this.selectedNode, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.potentialNodes != null && !this.potentialNodes.isEmpty()) {
         sb.append("potentialNodes:");
         sb.append(this.potentialNodes + ",");
      }

      if (this.selectedNode != null) {
         sb.append("selectedNode:");
         sb.append(this.selectedNode + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
