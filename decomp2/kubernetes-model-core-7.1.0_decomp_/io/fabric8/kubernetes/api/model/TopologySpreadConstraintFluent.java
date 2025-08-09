package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class TopologySpreadConstraintFluent extends BaseFluent {
   private LabelSelectorBuilder labelSelector;
   private List matchLabelKeys = new ArrayList();
   private Integer maxSkew;
   private Integer minDomains;
   private String nodeAffinityPolicy;
   private String nodeTaintsPolicy;
   private String topologyKey;
   private String whenUnsatisfiable;
   private Map additionalProperties;

   public TopologySpreadConstraintFluent() {
   }

   public TopologySpreadConstraintFluent(TopologySpreadConstraint instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TopologySpreadConstraint instance) {
      instance = instance != null ? instance : new TopologySpreadConstraint();
      if (instance != null) {
         this.withLabelSelector(instance.getLabelSelector());
         this.withMatchLabelKeys(instance.getMatchLabelKeys());
         this.withMaxSkew(instance.getMaxSkew());
         this.withMinDomains(instance.getMinDomains());
         this.withNodeAffinityPolicy(instance.getNodeAffinityPolicy());
         this.withNodeTaintsPolicy(instance.getNodeTaintsPolicy());
         this.withTopologyKey(instance.getTopologyKey());
         this.withWhenUnsatisfiable(instance.getWhenUnsatisfiable());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LabelSelector buildLabelSelector() {
      return this.labelSelector != null ? this.labelSelector.build() : null;
   }

   public TopologySpreadConstraintFluent withLabelSelector(LabelSelector labelSelector) {
      this._visitables.remove("labelSelector");
      if (labelSelector != null) {
         this.labelSelector = new LabelSelectorBuilder(labelSelector);
         this._visitables.get("labelSelector").add(this.labelSelector);
      } else {
         this.labelSelector = null;
         this._visitables.get("labelSelector").remove(this.labelSelector);
      }

      return this;
   }

   public boolean hasLabelSelector() {
      return this.labelSelector != null;
   }

   public LabelSelectorNested withNewLabelSelector() {
      return new LabelSelectorNested((LabelSelector)null);
   }

   public LabelSelectorNested withNewLabelSelectorLike(LabelSelector item) {
      return new LabelSelectorNested(item);
   }

   public LabelSelectorNested editLabelSelector() {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse((Object)null));
   }

   public LabelSelectorNested editOrNewLabelSelector() {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public LabelSelectorNested editOrNewLabelSelectorLike(LabelSelector item) {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse(item));
   }

   public TopologySpreadConstraintFluent addToMatchLabelKeys(int index, String item) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      this.matchLabelKeys.add(index, item);
      return this;
   }

   public TopologySpreadConstraintFluent setToMatchLabelKeys(int index, String item) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      this.matchLabelKeys.set(index, item);
      return this;
   }

   public TopologySpreadConstraintFluent addToMatchLabelKeys(String... items) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      for(String item : items) {
         this.matchLabelKeys.add(item);
      }

      return this;
   }

   public TopologySpreadConstraintFluent addAllToMatchLabelKeys(Collection items) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      for(String item : items) {
         this.matchLabelKeys.add(item);
      }

      return this;
   }

   public TopologySpreadConstraintFluent removeFromMatchLabelKeys(String... items) {
      if (this.matchLabelKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.matchLabelKeys.remove(item);
         }

         return this;
      }
   }

   public TopologySpreadConstraintFluent removeAllFromMatchLabelKeys(Collection items) {
      if (this.matchLabelKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.matchLabelKeys.remove(item);
         }

         return this;
      }
   }

   public List getMatchLabelKeys() {
      return this.matchLabelKeys;
   }

   public String getMatchLabelKey(int index) {
      return (String)this.matchLabelKeys.get(index);
   }

   public String getFirstMatchLabelKey() {
      return (String)this.matchLabelKeys.get(0);
   }

   public String getLastMatchLabelKey() {
      return (String)this.matchLabelKeys.get(this.matchLabelKeys.size() - 1);
   }

   public String getMatchingMatchLabelKey(Predicate predicate) {
      for(String item : this.matchLabelKeys) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingMatchLabelKey(Predicate predicate) {
      for(String item : this.matchLabelKeys) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TopologySpreadConstraintFluent withMatchLabelKeys(List matchLabelKeys) {
      if (matchLabelKeys != null) {
         this.matchLabelKeys = new ArrayList();

         for(String item : matchLabelKeys) {
            this.addToMatchLabelKeys(item);
         }
      } else {
         this.matchLabelKeys = null;
      }

      return this;
   }

   public TopologySpreadConstraintFluent withMatchLabelKeys(String... matchLabelKeys) {
      if (this.matchLabelKeys != null) {
         this.matchLabelKeys.clear();
         this._visitables.remove("matchLabelKeys");
      }

      if (matchLabelKeys != null) {
         for(String item : matchLabelKeys) {
            this.addToMatchLabelKeys(item);
         }
      }

      return this;
   }

   public boolean hasMatchLabelKeys() {
      return this.matchLabelKeys != null && !this.matchLabelKeys.isEmpty();
   }

   public Integer getMaxSkew() {
      return this.maxSkew;
   }

   public TopologySpreadConstraintFluent withMaxSkew(Integer maxSkew) {
      this.maxSkew = maxSkew;
      return this;
   }

   public boolean hasMaxSkew() {
      return this.maxSkew != null;
   }

   public Integer getMinDomains() {
      return this.minDomains;
   }

   public TopologySpreadConstraintFluent withMinDomains(Integer minDomains) {
      this.minDomains = minDomains;
      return this;
   }

   public boolean hasMinDomains() {
      return this.minDomains != null;
   }

   public String getNodeAffinityPolicy() {
      return this.nodeAffinityPolicy;
   }

   public TopologySpreadConstraintFluent withNodeAffinityPolicy(String nodeAffinityPolicy) {
      this.nodeAffinityPolicy = nodeAffinityPolicy;
      return this;
   }

   public boolean hasNodeAffinityPolicy() {
      return this.nodeAffinityPolicy != null;
   }

   public String getNodeTaintsPolicy() {
      return this.nodeTaintsPolicy;
   }

   public TopologySpreadConstraintFluent withNodeTaintsPolicy(String nodeTaintsPolicy) {
      this.nodeTaintsPolicy = nodeTaintsPolicy;
      return this;
   }

   public boolean hasNodeTaintsPolicy() {
      return this.nodeTaintsPolicy != null;
   }

   public String getTopologyKey() {
      return this.topologyKey;
   }

   public TopologySpreadConstraintFluent withTopologyKey(String topologyKey) {
      this.topologyKey = topologyKey;
      return this;
   }

   public boolean hasTopologyKey() {
      return this.topologyKey != null;
   }

   public String getWhenUnsatisfiable() {
      return this.whenUnsatisfiable;
   }

   public TopologySpreadConstraintFluent withWhenUnsatisfiable(String whenUnsatisfiable) {
      this.whenUnsatisfiable = whenUnsatisfiable;
      return this;
   }

   public boolean hasWhenUnsatisfiable() {
      return this.whenUnsatisfiable != null;
   }

   public TopologySpreadConstraintFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TopologySpreadConstraintFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TopologySpreadConstraintFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TopologySpreadConstraintFluent removeFromAdditionalProperties(Map map) {
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

   public TopologySpreadConstraintFluent withAdditionalProperties(Map additionalProperties) {
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
            TopologySpreadConstraintFluent that = (TopologySpreadConstraintFluent)o;
            if (!Objects.equals(this.labelSelector, that.labelSelector)) {
               return false;
            } else if (!Objects.equals(this.matchLabelKeys, that.matchLabelKeys)) {
               return false;
            } else if (!Objects.equals(this.maxSkew, that.maxSkew)) {
               return false;
            } else if (!Objects.equals(this.minDomains, that.minDomains)) {
               return false;
            } else if (!Objects.equals(this.nodeAffinityPolicy, that.nodeAffinityPolicy)) {
               return false;
            } else if (!Objects.equals(this.nodeTaintsPolicy, that.nodeTaintsPolicy)) {
               return false;
            } else if (!Objects.equals(this.topologyKey, that.topologyKey)) {
               return false;
            } else if (!Objects.equals(this.whenUnsatisfiable, that.whenUnsatisfiable)) {
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
      return Objects.hash(new Object[]{this.labelSelector, this.matchLabelKeys, this.maxSkew, this.minDomains, this.nodeAffinityPolicy, this.nodeTaintsPolicy, this.topologyKey, this.whenUnsatisfiable, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.labelSelector != null) {
         sb.append("labelSelector:");
         sb.append(this.labelSelector + ",");
      }

      if (this.matchLabelKeys != null && !this.matchLabelKeys.isEmpty()) {
         sb.append("matchLabelKeys:");
         sb.append(this.matchLabelKeys + ",");
      }

      if (this.maxSkew != null) {
         sb.append("maxSkew:");
         sb.append(this.maxSkew + ",");
      }

      if (this.minDomains != null) {
         sb.append("minDomains:");
         sb.append(this.minDomains + ",");
      }

      if (this.nodeAffinityPolicy != null) {
         sb.append("nodeAffinityPolicy:");
         sb.append(this.nodeAffinityPolicy + ",");
      }

      if (this.nodeTaintsPolicy != null) {
         sb.append("nodeTaintsPolicy:");
         sb.append(this.nodeTaintsPolicy + ",");
      }

      if (this.topologyKey != null) {
         sb.append("topologyKey:");
         sb.append(this.topologyKey + ",");
      }

      if (this.whenUnsatisfiable != null) {
         sb.append("whenUnsatisfiable:");
         sb.append(this.whenUnsatisfiable + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class LabelSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      LabelSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return TopologySpreadConstraintFluent.this.withLabelSelector(this.builder.build());
      }

      public Object endLabelSelector() {
         return this.and();
      }
   }
}
