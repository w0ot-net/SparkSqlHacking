package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class LabelSelectorFluent extends BaseFluent {
   private ArrayList matchExpressions = new ArrayList();
   private Map matchLabels;
   private Map additionalProperties;

   public LabelSelectorFluent() {
   }

   public LabelSelectorFluent(LabelSelector instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LabelSelector instance) {
      instance = instance != null ? instance : new LabelSelector();
      if (instance != null) {
         this.withMatchExpressions(instance.getMatchExpressions());
         this.withMatchLabels(instance.getMatchLabels());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LabelSelectorFluent addToMatchExpressions(int index, LabelSelectorRequirement item) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      LabelSelectorRequirementBuilder builder = new LabelSelectorRequirementBuilder(item);
      if (index >= 0 && index < this.matchExpressions.size()) {
         this._visitables.get("matchExpressions").add(index, builder);
         this.matchExpressions.add(index, builder);
      } else {
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public LabelSelectorFluent setToMatchExpressions(int index, LabelSelectorRequirement item) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      LabelSelectorRequirementBuilder builder = new LabelSelectorRequirementBuilder(item);
      if (index >= 0 && index < this.matchExpressions.size()) {
         this._visitables.get("matchExpressions").set(index, builder);
         this.matchExpressions.set(index, builder);
      } else {
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public LabelSelectorFluent addToMatchExpressions(LabelSelectorRequirement... items) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      for(LabelSelectorRequirement item : items) {
         LabelSelectorRequirementBuilder builder = new LabelSelectorRequirementBuilder(item);
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public LabelSelectorFluent addAllToMatchExpressions(Collection items) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      for(LabelSelectorRequirement item : items) {
         LabelSelectorRequirementBuilder builder = new LabelSelectorRequirementBuilder(item);
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public LabelSelectorFluent removeFromMatchExpressions(LabelSelectorRequirement... items) {
      if (this.matchExpressions == null) {
         return this;
      } else {
         for(LabelSelectorRequirement item : items) {
            LabelSelectorRequirementBuilder builder = new LabelSelectorRequirementBuilder(item);
            this._visitables.get("matchExpressions").remove(builder);
            this.matchExpressions.remove(builder);
         }

         return this;
      }
   }

   public LabelSelectorFluent removeAllFromMatchExpressions(Collection items) {
      if (this.matchExpressions == null) {
         return this;
      } else {
         for(LabelSelectorRequirement item : items) {
            LabelSelectorRequirementBuilder builder = new LabelSelectorRequirementBuilder(item);
            this._visitables.get("matchExpressions").remove(builder);
            this.matchExpressions.remove(builder);
         }

         return this;
      }
   }

   public LabelSelectorFluent removeMatchingFromMatchExpressions(Predicate predicate) {
      if (this.matchExpressions == null) {
         return this;
      } else {
         Iterator<LabelSelectorRequirementBuilder> each = this.matchExpressions.iterator();
         List visitables = this._visitables.get("matchExpressions");

         while(each.hasNext()) {
            LabelSelectorRequirementBuilder builder = (LabelSelectorRequirementBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMatchExpressions() {
      return this.matchExpressions != null ? build(this.matchExpressions) : null;
   }

   public LabelSelectorRequirement buildMatchExpression(int index) {
      return ((LabelSelectorRequirementBuilder)this.matchExpressions.get(index)).build();
   }

   public LabelSelectorRequirement buildFirstMatchExpression() {
      return ((LabelSelectorRequirementBuilder)this.matchExpressions.get(0)).build();
   }

   public LabelSelectorRequirement buildLastMatchExpression() {
      return ((LabelSelectorRequirementBuilder)this.matchExpressions.get(this.matchExpressions.size() - 1)).build();
   }

   public LabelSelectorRequirement buildMatchingMatchExpression(Predicate predicate) {
      for(LabelSelectorRequirementBuilder item : this.matchExpressions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatchExpression(Predicate predicate) {
      for(LabelSelectorRequirementBuilder item : this.matchExpressions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public LabelSelectorFluent withMatchExpressions(List matchExpressions) {
      if (this.matchExpressions != null) {
         this._visitables.get("matchExpressions").clear();
      }

      if (matchExpressions != null) {
         this.matchExpressions = new ArrayList();

         for(LabelSelectorRequirement item : matchExpressions) {
            this.addToMatchExpressions(item);
         }
      } else {
         this.matchExpressions = null;
      }

      return this;
   }

   public LabelSelectorFluent withMatchExpressions(LabelSelectorRequirement... matchExpressions) {
      if (this.matchExpressions != null) {
         this.matchExpressions.clear();
         this._visitables.remove("matchExpressions");
      }

      if (matchExpressions != null) {
         for(LabelSelectorRequirement item : matchExpressions) {
            this.addToMatchExpressions(item);
         }
      }

      return this;
   }

   public boolean hasMatchExpressions() {
      return this.matchExpressions != null && !this.matchExpressions.isEmpty();
   }

   public MatchExpressionsNested addNewMatchExpression() {
      return new MatchExpressionsNested(-1, (LabelSelectorRequirement)null);
   }

   public MatchExpressionsNested addNewMatchExpressionLike(LabelSelectorRequirement item) {
      return new MatchExpressionsNested(-1, item);
   }

   public MatchExpressionsNested setNewMatchExpressionLike(int index, LabelSelectorRequirement item) {
      return new MatchExpressionsNested(index, item);
   }

   public MatchExpressionsNested editMatchExpression(int index) {
      if (this.matchExpressions.size() <= index) {
         throw new RuntimeException("Can't edit matchExpressions. Index exceeds size.");
      } else {
         return this.setNewMatchExpressionLike(index, this.buildMatchExpression(index));
      }
   }

   public MatchExpressionsNested editFirstMatchExpression() {
      if (this.matchExpressions.size() == 0) {
         throw new RuntimeException("Can't edit first matchExpressions. The list is empty.");
      } else {
         return this.setNewMatchExpressionLike(0, this.buildMatchExpression(0));
      }
   }

   public MatchExpressionsNested editLastMatchExpression() {
      int index = this.matchExpressions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last matchExpressions. The list is empty.");
      } else {
         return this.setNewMatchExpressionLike(index, this.buildMatchExpression(index));
      }
   }

   public MatchExpressionsNested editMatchingMatchExpression(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.matchExpressions.size(); ++i) {
         if (predicate.test((LabelSelectorRequirementBuilder)this.matchExpressions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching matchExpressions. No match found.");
      } else {
         return this.setNewMatchExpressionLike(index, this.buildMatchExpression(index));
      }
   }

   public LabelSelectorFluent addToMatchLabels(String key, String value) {
      if (this.matchLabels == null && key != null && value != null) {
         this.matchLabels = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.matchLabels.put(key, value);
      }

      return this;
   }

   public LabelSelectorFluent addToMatchLabels(Map map) {
      if (this.matchLabels == null && map != null) {
         this.matchLabels = new LinkedHashMap();
      }

      if (map != null) {
         this.matchLabels.putAll(map);
      }

      return this;
   }

   public LabelSelectorFluent removeFromMatchLabels(String key) {
      if (this.matchLabels == null) {
         return this;
      } else {
         if (key != null && this.matchLabels != null) {
            this.matchLabels.remove(key);
         }

         return this;
      }
   }

   public LabelSelectorFluent removeFromMatchLabels(Map map) {
      if (this.matchLabels == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.matchLabels != null) {
                  this.matchLabels.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getMatchLabels() {
      return this.matchLabels;
   }

   public LabelSelectorFluent withMatchLabels(Map matchLabels) {
      if (matchLabels == null) {
         this.matchLabels = null;
      } else {
         this.matchLabels = new LinkedHashMap(matchLabels);
      }

      return this;
   }

   public boolean hasMatchLabels() {
      return this.matchLabels != null;
   }

   public LabelSelectorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LabelSelectorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LabelSelectorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LabelSelectorFluent removeFromAdditionalProperties(Map map) {
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

   public LabelSelectorFluent withAdditionalProperties(Map additionalProperties) {
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
            LabelSelectorFluent that = (LabelSelectorFluent)o;
            if (!Objects.equals(this.matchExpressions, that.matchExpressions)) {
               return false;
            } else if (!Objects.equals(this.matchLabels, that.matchLabels)) {
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
      return Objects.hash(new Object[]{this.matchExpressions, this.matchLabels, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.matchExpressions != null && !this.matchExpressions.isEmpty()) {
         sb.append("matchExpressions:");
         sb.append(this.matchExpressions + ",");
      }

      if (this.matchLabels != null && !this.matchLabels.isEmpty()) {
         sb.append("matchLabels:");
         sb.append(this.matchLabels + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MatchExpressionsNested extends LabelSelectorRequirementFluent implements Nested {
      LabelSelectorRequirementBuilder builder;
      int index;

      MatchExpressionsNested(int index, LabelSelectorRequirement item) {
         this.index = index;
         this.builder = new LabelSelectorRequirementBuilder(this, item);
      }

      public Object and() {
         return LabelSelectorFluent.this.setToMatchExpressions(this.index, this.builder.build());
      }

      public Object endMatchExpression() {
         return this.and();
      }
   }
}
