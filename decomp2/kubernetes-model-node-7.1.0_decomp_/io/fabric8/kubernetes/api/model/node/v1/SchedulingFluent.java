package io.fabric8.kubernetes.api.model.node.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Toleration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class SchedulingFluent extends BaseFluent {
   private Map nodeSelector;
   private List tolerations = new ArrayList();
   private Map additionalProperties;

   public SchedulingFluent() {
   }

   public SchedulingFluent(Scheduling instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Scheduling instance) {
      instance = instance != null ? instance : new Scheduling();
      if (instance != null) {
         this.withNodeSelector(instance.getNodeSelector());
         this.withTolerations(instance.getTolerations());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public SchedulingFluent addToNodeSelector(String key, String value) {
      if (this.nodeSelector == null && key != null && value != null) {
         this.nodeSelector = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.nodeSelector.put(key, value);
      }

      return this;
   }

   public SchedulingFluent addToNodeSelector(Map map) {
      if (this.nodeSelector == null && map != null) {
         this.nodeSelector = new LinkedHashMap();
      }

      if (map != null) {
         this.nodeSelector.putAll(map);
      }

      return this;
   }

   public SchedulingFluent removeFromNodeSelector(String key) {
      if (this.nodeSelector == null) {
         return this;
      } else {
         if (key != null && this.nodeSelector != null) {
            this.nodeSelector.remove(key);
         }

         return this;
      }
   }

   public SchedulingFluent removeFromNodeSelector(Map map) {
      if (this.nodeSelector == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.nodeSelector != null) {
                  this.nodeSelector.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getNodeSelector() {
      return this.nodeSelector;
   }

   public SchedulingFluent withNodeSelector(Map nodeSelector) {
      if (nodeSelector == null) {
         this.nodeSelector = null;
      } else {
         this.nodeSelector = new LinkedHashMap(nodeSelector);
      }

      return this;
   }

   public boolean hasNodeSelector() {
      return this.nodeSelector != null;
   }

   public SchedulingFluent addToTolerations(int index, Toleration item) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      this.tolerations.add(index, item);
      return this;
   }

   public SchedulingFluent setToTolerations(int index, Toleration item) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      this.tolerations.set(index, item);
      return this;
   }

   public SchedulingFluent addToTolerations(Toleration... items) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      for(Toleration item : items) {
         this.tolerations.add(item);
      }

      return this;
   }

   public SchedulingFluent addAllToTolerations(Collection items) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      for(Toleration item : items) {
         this.tolerations.add(item);
      }

      return this;
   }

   public SchedulingFluent removeFromTolerations(Toleration... items) {
      if (this.tolerations == null) {
         return this;
      } else {
         for(Toleration item : items) {
            this.tolerations.remove(item);
         }

         return this;
      }
   }

   public SchedulingFluent removeAllFromTolerations(Collection items) {
      if (this.tolerations == null) {
         return this;
      } else {
         for(Toleration item : items) {
            this.tolerations.remove(item);
         }

         return this;
      }
   }

   public List getTolerations() {
      return this.tolerations;
   }

   public Toleration getToleration(int index) {
      return (Toleration)this.tolerations.get(index);
   }

   public Toleration getFirstToleration() {
      return (Toleration)this.tolerations.get(0);
   }

   public Toleration getLastToleration() {
      return (Toleration)this.tolerations.get(this.tolerations.size() - 1);
   }

   public Toleration getMatchingToleration(Predicate predicate) {
      for(Toleration item : this.tolerations) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingToleration(Predicate predicate) {
      for(Toleration item : this.tolerations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SchedulingFluent withTolerations(List tolerations) {
      if (tolerations != null) {
         this.tolerations = new ArrayList();

         for(Toleration item : tolerations) {
            this.addToTolerations(item);
         }
      } else {
         this.tolerations = null;
      }

      return this;
   }

   public SchedulingFluent withTolerations(Toleration... tolerations) {
      if (this.tolerations != null) {
         this.tolerations.clear();
         this._visitables.remove("tolerations");
      }

      if (tolerations != null) {
         for(Toleration item : tolerations) {
            this.addToTolerations(item);
         }
      }

      return this;
   }

   public boolean hasTolerations() {
      return this.tolerations != null && !this.tolerations.isEmpty();
   }

   public SchedulingFluent addNewToleration(String effect, String key, String operator, Long tolerationSeconds, String value) {
      return this.addToTolerations(new Toleration(effect, key, operator, tolerationSeconds, value));
   }

   public SchedulingFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SchedulingFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SchedulingFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SchedulingFluent removeFromAdditionalProperties(Map map) {
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

   public SchedulingFluent withAdditionalProperties(Map additionalProperties) {
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
            SchedulingFluent that = (SchedulingFluent)o;
            if (!Objects.equals(this.nodeSelector, that.nodeSelector)) {
               return false;
            } else if (!Objects.equals(this.tolerations, that.tolerations)) {
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
      return Objects.hash(new Object[]{this.nodeSelector, this.tolerations, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nodeSelector != null && !this.nodeSelector.isEmpty()) {
         sb.append("nodeSelector:");
         sb.append(this.nodeSelector + ",");
      }

      if (this.tolerations != null && !this.tolerations.isEmpty()) {
         sb.append("tolerations:");
         sb.append(this.tolerations + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
