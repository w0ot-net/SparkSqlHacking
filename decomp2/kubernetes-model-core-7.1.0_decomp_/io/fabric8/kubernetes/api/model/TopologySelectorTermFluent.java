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

public class TopologySelectorTermFluent extends BaseFluent {
   private ArrayList matchLabelExpressions = new ArrayList();
   private Map additionalProperties;

   public TopologySelectorTermFluent() {
   }

   public TopologySelectorTermFluent(TopologySelectorTerm instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TopologySelectorTerm instance) {
      instance = instance != null ? instance : new TopologySelectorTerm();
      if (instance != null) {
         this.withMatchLabelExpressions(instance.getMatchLabelExpressions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TopologySelectorTermFluent addToMatchLabelExpressions(int index, TopologySelectorLabelRequirement item) {
      if (this.matchLabelExpressions == null) {
         this.matchLabelExpressions = new ArrayList();
      }

      TopologySelectorLabelRequirementBuilder builder = new TopologySelectorLabelRequirementBuilder(item);
      if (index >= 0 && index < this.matchLabelExpressions.size()) {
         this._visitables.get("matchLabelExpressions").add(index, builder);
         this.matchLabelExpressions.add(index, builder);
      } else {
         this._visitables.get("matchLabelExpressions").add(builder);
         this.matchLabelExpressions.add(builder);
      }

      return this;
   }

   public TopologySelectorTermFluent setToMatchLabelExpressions(int index, TopologySelectorLabelRequirement item) {
      if (this.matchLabelExpressions == null) {
         this.matchLabelExpressions = new ArrayList();
      }

      TopologySelectorLabelRequirementBuilder builder = new TopologySelectorLabelRequirementBuilder(item);
      if (index >= 0 && index < this.matchLabelExpressions.size()) {
         this._visitables.get("matchLabelExpressions").set(index, builder);
         this.matchLabelExpressions.set(index, builder);
      } else {
         this._visitables.get("matchLabelExpressions").add(builder);
         this.matchLabelExpressions.add(builder);
      }

      return this;
   }

   public TopologySelectorTermFluent addToMatchLabelExpressions(TopologySelectorLabelRequirement... items) {
      if (this.matchLabelExpressions == null) {
         this.matchLabelExpressions = new ArrayList();
      }

      for(TopologySelectorLabelRequirement item : items) {
         TopologySelectorLabelRequirementBuilder builder = new TopologySelectorLabelRequirementBuilder(item);
         this._visitables.get("matchLabelExpressions").add(builder);
         this.matchLabelExpressions.add(builder);
      }

      return this;
   }

   public TopologySelectorTermFluent addAllToMatchLabelExpressions(Collection items) {
      if (this.matchLabelExpressions == null) {
         this.matchLabelExpressions = new ArrayList();
      }

      for(TopologySelectorLabelRequirement item : items) {
         TopologySelectorLabelRequirementBuilder builder = new TopologySelectorLabelRequirementBuilder(item);
         this._visitables.get("matchLabelExpressions").add(builder);
         this.matchLabelExpressions.add(builder);
      }

      return this;
   }

   public TopologySelectorTermFluent removeFromMatchLabelExpressions(TopologySelectorLabelRequirement... items) {
      if (this.matchLabelExpressions == null) {
         return this;
      } else {
         for(TopologySelectorLabelRequirement item : items) {
            TopologySelectorLabelRequirementBuilder builder = new TopologySelectorLabelRequirementBuilder(item);
            this._visitables.get("matchLabelExpressions").remove(builder);
            this.matchLabelExpressions.remove(builder);
         }

         return this;
      }
   }

   public TopologySelectorTermFluent removeAllFromMatchLabelExpressions(Collection items) {
      if (this.matchLabelExpressions == null) {
         return this;
      } else {
         for(TopologySelectorLabelRequirement item : items) {
            TopologySelectorLabelRequirementBuilder builder = new TopologySelectorLabelRequirementBuilder(item);
            this._visitables.get("matchLabelExpressions").remove(builder);
            this.matchLabelExpressions.remove(builder);
         }

         return this;
      }
   }

   public TopologySelectorTermFluent removeMatchingFromMatchLabelExpressions(Predicate predicate) {
      if (this.matchLabelExpressions == null) {
         return this;
      } else {
         Iterator<TopologySelectorLabelRequirementBuilder> each = this.matchLabelExpressions.iterator();
         List visitables = this._visitables.get("matchLabelExpressions");

         while(each.hasNext()) {
            TopologySelectorLabelRequirementBuilder builder = (TopologySelectorLabelRequirementBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMatchLabelExpressions() {
      return this.matchLabelExpressions != null ? build(this.matchLabelExpressions) : null;
   }

   public TopologySelectorLabelRequirement buildMatchLabelExpression(int index) {
      return ((TopologySelectorLabelRequirementBuilder)this.matchLabelExpressions.get(index)).build();
   }

   public TopologySelectorLabelRequirement buildFirstMatchLabelExpression() {
      return ((TopologySelectorLabelRequirementBuilder)this.matchLabelExpressions.get(0)).build();
   }

   public TopologySelectorLabelRequirement buildLastMatchLabelExpression() {
      return ((TopologySelectorLabelRequirementBuilder)this.matchLabelExpressions.get(this.matchLabelExpressions.size() - 1)).build();
   }

   public TopologySelectorLabelRequirement buildMatchingMatchLabelExpression(Predicate predicate) {
      for(TopologySelectorLabelRequirementBuilder item : this.matchLabelExpressions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatchLabelExpression(Predicate predicate) {
      for(TopologySelectorLabelRequirementBuilder item : this.matchLabelExpressions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TopologySelectorTermFluent withMatchLabelExpressions(List matchLabelExpressions) {
      if (this.matchLabelExpressions != null) {
         this._visitables.get("matchLabelExpressions").clear();
      }

      if (matchLabelExpressions != null) {
         this.matchLabelExpressions = new ArrayList();

         for(TopologySelectorLabelRequirement item : matchLabelExpressions) {
            this.addToMatchLabelExpressions(item);
         }
      } else {
         this.matchLabelExpressions = null;
      }

      return this;
   }

   public TopologySelectorTermFluent withMatchLabelExpressions(TopologySelectorLabelRequirement... matchLabelExpressions) {
      if (this.matchLabelExpressions != null) {
         this.matchLabelExpressions.clear();
         this._visitables.remove("matchLabelExpressions");
      }

      if (matchLabelExpressions != null) {
         for(TopologySelectorLabelRequirement item : matchLabelExpressions) {
            this.addToMatchLabelExpressions(item);
         }
      }

      return this;
   }

   public boolean hasMatchLabelExpressions() {
      return this.matchLabelExpressions != null && !this.matchLabelExpressions.isEmpty();
   }

   public MatchLabelExpressionsNested addNewMatchLabelExpression() {
      return new MatchLabelExpressionsNested(-1, (TopologySelectorLabelRequirement)null);
   }

   public MatchLabelExpressionsNested addNewMatchLabelExpressionLike(TopologySelectorLabelRequirement item) {
      return new MatchLabelExpressionsNested(-1, item);
   }

   public MatchLabelExpressionsNested setNewMatchLabelExpressionLike(int index, TopologySelectorLabelRequirement item) {
      return new MatchLabelExpressionsNested(index, item);
   }

   public MatchLabelExpressionsNested editMatchLabelExpression(int index) {
      if (this.matchLabelExpressions.size() <= index) {
         throw new RuntimeException("Can't edit matchLabelExpressions. Index exceeds size.");
      } else {
         return this.setNewMatchLabelExpressionLike(index, this.buildMatchLabelExpression(index));
      }
   }

   public MatchLabelExpressionsNested editFirstMatchLabelExpression() {
      if (this.matchLabelExpressions.size() == 0) {
         throw new RuntimeException("Can't edit first matchLabelExpressions. The list is empty.");
      } else {
         return this.setNewMatchLabelExpressionLike(0, this.buildMatchLabelExpression(0));
      }
   }

   public MatchLabelExpressionsNested editLastMatchLabelExpression() {
      int index = this.matchLabelExpressions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last matchLabelExpressions. The list is empty.");
      } else {
         return this.setNewMatchLabelExpressionLike(index, this.buildMatchLabelExpression(index));
      }
   }

   public MatchLabelExpressionsNested editMatchingMatchLabelExpression(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.matchLabelExpressions.size(); ++i) {
         if (predicate.test((TopologySelectorLabelRequirementBuilder)this.matchLabelExpressions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching matchLabelExpressions. No match found.");
      } else {
         return this.setNewMatchLabelExpressionLike(index, this.buildMatchLabelExpression(index));
      }
   }

   public TopologySelectorTermFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TopologySelectorTermFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TopologySelectorTermFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TopologySelectorTermFluent removeFromAdditionalProperties(Map map) {
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

   public TopologySelectorTermFluent withAdditionalProperties(Map additionalProperties) {
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
            TopologySelectorTermFluent that = (TopologySelectorTermFluent)o;
            if (!Objects.equals(this.matchLabelExpressions, that.matchLabelExpressions)) {
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
      return Objects.hash(new Object[]{this.matchLabelExpressions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.matchLabelExpressions != null && !this.matchLabelExpressions.isEmpty()) {
         sb.append("matchLabelExpressions:");
         sb.append(this.matchLabelExpressions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MatchLabelExpressionsNested extends TopologySelectorLabelRequirementFluent implements Nested {
      TopologySelectorLabelRequirementBuilder builder;
      int index;

      MatchLabelExpressionsNested(int index, TopologySelectorLabelRequirement item) {
         this.index = index;
         this.builder = new TopologySelectorLabelRequirementBuilder(this, item);
      }

      public Object and() {
         return TopologySelectorTermFluent.this.setToMatchLabelExpressions(this.index, this.builder.build());
      }

      public Object endMatchLabelExpression() {
         return this.and();
      }
   }
}
