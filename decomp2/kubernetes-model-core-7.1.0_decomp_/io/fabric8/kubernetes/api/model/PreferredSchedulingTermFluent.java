package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PreferredSchedulingTermFluent extends BaseFluent {
   private NodeSelectorTermBuilder preference;
   private Integer weight;
   private Map additionalProperties;

   public PreferredSchedulingTermFluent() {
   }

   public PreferredSchedulingTermFluent(PreferredSchedulingTerm instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PreferredSchedulingTerm instance) {
      instance = instance != null ? instance : new PreferredSchedulingTerm();
      if (instance != null) {
         this.withPreference(instance.getPreference());
         this.withWeight(instance.getWeight());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeSelectorTerm buildPreference() {
      return this.preference != null ? this.preference.build() : null;
   }

   public PreferredSchedulingTermFluent withPreference(NodeSelectorTerm preference) {
      this._visitables.remove("preference");
      if (preference != null) {
         this.preference = new NodeSelectorTermBuilder(preference);
         this._visitables.get("preference").add(this.preference);
      } else {
         this.preference = null;
         this._visitables.get("preference").remove(this.preference);
      }

      return this;
   }

   public boolean hasPreference() {
      return this.preference != null;
   }

   public PreferenceNested withNewPreference() {
      return new PreferenceNested((NodeSelectorTerm)null);
   }

   public PreferenceNested withNewPreferenceLike(NodeSelectorTerm item) {
      return new PreferenceNested(item);
   }

   public PreferenceNested editPreference() {
      return this.withNewPreferenceLike((NodeSelectorTerm)Optional.ofNullable(this.buildPreference()).orElse((Object)null));
   }

   public PreferenceNested editOrNewPreference() {
      return this.withNewPreferenceLike((NodeSelectorTerm)Optional.ofNullable(this.buildPreference()).orElse((new NodeSelectorTermBuilder()).build()));
   }

   public PreferenceNested editOrNewPreferenceLike(NodeSelectorTerm item) {
      return this.withNewPreferenceLike((NodeSelectorTerm)Optional.ofNullable(this.buildPreference()).orElse(item));
   }

   public Integer getWeight() {
      return this.weight;
   }

   public PreferredSchedulingTermFluent withWeight(Integer weight) {
      this.weight = weight;
      return this;
   }

   public boolean hasWeight() {
      return this.weight != null;
   }

   public PreferredSchedulingTermFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PreferredSchedulingTermFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PreferredSchedulingTermFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PreferredSchedulingTermFluent removeFromAdditionalProperties(Map map) {
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

   public PreferredSchedulingTermFluent withAdditionalProperties(Map additionalProperties) {
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
            PreferredSchedulingTermFluent that = (PreferredSchedulingTermFluent)o;
            if (!Objects.equals(this.preference, that.preference)) {
               return false;
            } else if (!Objects.equals(this.weight, that.weight)) {
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
      return Objects.hash(new Object[]{this.preference, this.weight, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.preference != null) {
         sb.append("preference:");
         sb.append(this.preference + ",");
      }

      if (this.weight != null) {
         sb.append("weight:");
         sb.append(this.weight + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PreferenceNested extends NodeSelectorTermFluent implements Nested {
      NodeSelectorTermBuilder builder;

      PreferenceNested(NodeSelectorTerm item) {
         this.builder = new NodeSelectorTermBuilder(this, item);
      }

      public Object and() {
         return PreferredSchedulingTermFluent.this.withPreference(this.builder.build());
      }

      public Object endPreference() {
         return this.and();
      }
   }
}
