package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Condition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class GatewayClassStatusFluent extends BaseFluent {
   private List conditions = new ArrayList();
   private ArrayList supportedFeatures = new ArrayList();
   private Map additionalProperties;

   public GatewayClassStatusFluent() {
   }

   public GatewayClassStatusFluent(GatewayClassStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewayClassStatus instance) {
      instance = instance != null ? instance : new GatewayClassStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withSupportedFeatures(instance.getSupportedFeatures());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GatewayClassStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public GatewayClassStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public GatewayClassStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public GatewayClassStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public GatewayClassStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public GatewayClassStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public List getConditions() {
      return this.conditions;
   }

   public Condition getCondition(int index) {
      return (Condition)this.conditions.get(index);
   }

   public Condition getFirstCondition() {
      return (Condition)this.conditions.get(0);
   }

   public Condition getLastCondition() {
      return (Condition)this.conditions.get(this.conditions.size() - 1);
   }

   public Condition getMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewayClassStatusFluent withConditions(List conditions) {
      if (conditions != null) {
         this.conditions = new ArrayList();

         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public GatewayClassStatusFluent withConditions(Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public GatewayClassStatusFluent addToSupportedFeatures(int index, SupportedFeature item) {
      if (this.supportedFeatures == null) {
         this.supportedFeatures = new ArrayList();
      }

      SupportedFeatureBuilder builder = new SupportedFeatureBuilder(item);
      if (index >= 0 && index < this.supportedFeatures.size()) {
         this._visitables.get("supportedFeatures").add(index, builder);
         this.supportedFeatures.add(index, builder);
      } else {
         this._visitables.get("supportedFeatures").add(builder);
         this.supportedFeatures.add(builder);
      }

      return this;
   }

   public GatewayClassStatusFluent setToSupportedFeatures(int index, SupportedFeature item) {
      if (this.supportedFeatures == null) {
         this.supportedFeatures = new ArrayList();
      }

      SupportedFeatureBuilder builder = new SupportedFeatureBuilder(item);
      if (index >= 0 && index < this.supportedFeatures.size()) {
         this._visitables.get("supportedFeatures").set(index, builder);
         this.supportedFeatures.set(index, builder);
      } else {
         this._visitables.get("supportedFeatures").add(builder);
         this.supportedFeatures.add(builder);
      }

      return this;
   }

   public GatewayClassStatusFluent addToSupportedFeatures(SupportedFeature... items) {
      if (this.supportedFeatures == null) {
         this.supportedFeatures = new ArrayList();
      }

      for(SupportedFeature item : items) {
         SupportedFeatureBuilder builder = new SupportedFeatureBuilder(item);
         this._visitables.get("supportedFeatures").add(builder);
         this.supportedFeatures.add(builder);
      }

      return this;
   }

   public GatewayClassStatusFluent addAllToSupportedFeatures(Collection items) {
      if (this.supportedFeatures == null) {
         this.supportedFeatures = new ArrayList();
      }

      for(SupportedFeature item : items) {
         SupportedFeatureBuilder builder = new SupportedFeatureBuilder(item);
         this._visitables.get("supportedFeatures").add(builder);
         this.supportedFeatures.add(builder);
      }

      return this;
   }

   public GatewayClassStatusFluent removeFromSupportedFeatures(SupportedFeature... items) {
      if (this.supportedFeatures == null) {
         return this;
      } else {
         for(SupportedFeature item : items) {
            SupportedFeatureBuilder builder = new SupportedFeatureBuilder(item);
            this._visitables.get("supportedFeatures").remove(builder);
            this.supportedFeatures.remove(builder);
         }

         return this;
      }
   }

   public GatewayClassStatusFluent removeAllFromSupportedFeatures(Collection items) {
      if (this.supportedFeatures == null) {
         return this;
      } else {
         for(SupportedFeature item : items) {
            SupportedFeatureBuilder builder = new SupportedFeatureBuilder(item);
            this._visitables.get("supportedFeatures").remove(builder);
            this.supportedFeatures.remove(builder);
         }

         return this;
      }
   }

   public GatewayClassStatusFluent removeMatchingFromSupportedFeatures(Predicate predicate) {
      if (this.supportedFeatures == null) {
         return this;
      } else {
         Iterator<SupportedFeatureBuilder> each = this.supportedFeatures.iterator();
         List visitables = this._visitables.get("supportedFeatures");

         while(each.hasNext()) {
            SupportedFeatureBuilder builder = (SupportedFeatureBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSupportedFeatures() {
      return this.supportedFeatures != null ? build(this.supportedFeatures) : null;
   }

   public SupportedFeature buildSupportedFeature(int index) {
      return ((SupportedFeatureBuilder)this.supportedFeatures.get(index)).build();
   }

   public SupportedFeature buildFirstSupportedFeature() {
      return ((SupportedFeatureBuilder)this.supportedFeatures.get(0)).build();
   }

   public SupportedFeature buildLastSupportedFeature() {
      return ((SupportedFeatureBuilder)this.supportedFeatures.get(this.supportedFeatures.size() - 1)).build();
   }

   public SupportedFeature buildMatchingSupportedFeature(Predicate predicate) {
      for(SupportedFeatureBuilder item : this.supportedFeatures) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSupportedFeature(Predicate predicate) {
      for(SupportedFeatureBuilder item : this.supportedFeatures) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewayClassStatusFluent withSupportedFeatures(List supportedFeatures) {
      if (this.supportedFeatures != null) {
         this._visitables.get("supportedFeatures").clear();
      }

      if (supportedFeatures != null) {
         this.supportedFeatures = new ArrayList();

         for(SupportedFeature item : supportedFeatures) {
            this.addToSupportedFeatures(item);
         }
      } else {
         this.supportedFeatures = null;
      }

      return this;
   }

   public GatewayClassStatusFluent withSupportedFeatures(SupportedFeature... supportedFeatures) {
      if (this.supportedFeatures != null) {
         this.supportedFeatures.clear();
         this._visitables.remove("supportedFeatures");
      }

      if (supportedFeatures != null) {
         for(SupportedFeature item : supportedFeatures) {
            this.addToSupportedFeatures(item);
         }
      }

      return this;
   }

   public boolean hasSupportedFeatures() {
      return this.supportedFeatures != null && !this.supportedFeatures.isEmpty();
   }

   public GatewayClassStatusFluent addNewSupportedFeature(String name) {
      return this.addToSupportedFeatures(new SupportedFeature(name));
   }

   public SupportedFeaturesNested addNewSupportedFeature() {
      return new SupportedFeaturesNested(-1, (SupportedFeature)null);
   }

   public SupportedFeaturesNested addNewSupportedFeatureLike(SupportedFeature item) {
      return new SupportedFeaturesNested(-1, item);
   }

   public SupportedFeaturesNested setNewSupportedFeatureLike(int index, SupportedFeature item) {
      return new SupportedFeaturesNested(index, item);
   }

   public SupportedFeaturesNested editSupportedFeature(int index) {
      if (this.supportedFeatures.size() <= index) {
         throw new RuntimeException("Can't edit supportedFeatures. Index exceeds size.");
      } else {
         return this.setNewSupportedFeatureLike(index, this.buildSupportedFeature(index));
      }
   }

   public SupportedFeaturesNested editFirstSupportedFeature() {
      if (this.supportedFeatures.size() == 0) {
         throw new RuntimeException("Can't edit first supportedFeatures. The list is empty.");
      } else {
         return this.setNewSupportedFeatureLike(0, this.buildSupportedFeature(0));
      }
   }

   public SupportedFeaturesNested editLastSupportedFeature() {
      int index = this.supportedFeatures.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last supportedFeatures. The list is empty.");
      } else {
         return this.setNewSupportedFeatureLike(index, this.buildSupportedFeature(index));
      }
   }

   public SupportedFeaturesNested editMatchingSupportedFeature(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.supportedFeatures.size(); ++i) {
         if (predicate.test((SupportedFeatureBuilder)this.supportedFeatures.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching supportedFeatures. No match found.");
      } else {
         return this.setNewSupportedFeatureLike(index, this.buildSupportedFeature(index));
      }
   }

   public GatewayClassStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewayClassStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewayClassStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewayClassStatusFluent removeFromAdditionalProperties(Map map) {
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

   public GatewayClassStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewayClassStatusFluent that = (GatewayClassStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.supportedFeatures, that.supportedFeatures)) {
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
      return Objects.hash(new Object[]{this.conditions, this.supportedFeatures, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.supportedFeatures != null && !this.supportedFeatures.isEmpty()) {
         sb.append("supportedFeatures:");
         sb.append(this.supportedFeatures + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SupportedFeaturesNested extends SupportedFeatureFluent implements Nested {
      SupportedFeatureBuilder builder;
      int index;

      SupportedFeaturesNested(int index, SupportedFeature item) {
         this.index = index;
         this.builder = new SupportedFeatureBuilder(this, item);
      }

      public Object and() {
         return GatewayClassStatusFluent.this.setToSupportedFeatures(this.index, this.builder.build());
      }

      public Object endSupportedFeature() {
         return this.and();
      }
   }
}
