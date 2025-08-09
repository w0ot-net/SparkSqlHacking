package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PodFailurePolicyRuleFluent extends BaseFluent {
   private String action;
   private PodFailurePolicyOnExitCodesRequirementBuilder onExitCodes;
   private ArrayList onPodConditions = new ArrayList();
   private Map additionalProperties;

   public PodFailurePolicyRuleFluent() {
   }

   public PodFailurePolicyRuleFluent(PodFailurePolicyRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodFailurePolicyRule instance) {
      instance = instance != null ? instance : new PodFailurePolicyRule();
      if (instance != null) {
         this.withAction(instance.getAction());
         this.withOnExitCodes(instance.getOnExitCodes());
         this.withOnPodConditions(instance.getOnPodConditions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAction() {
      return this.action;
   }

   public PodFailurePolicyRuleFluent withAction(String action) {
      this.action = action;
      return this;
   }

   public boolean hasAction() {
      return this.action != null;
   }

   public PodFailurePolicyOnExitCodesRequirement buildOnExitCodes() {
      return this.onExitCodes != null ? this.onExitCodes.build() : null;
   }

   public PodFailurePolicyRuleFluent withOnExitCodes(PodFailurePolicyOnExitCodesRequirement onExitCodes) {
      this._visitables.remove("onExitCodes");
      if (onExitCodes != null) {
         this.onExitCodes = new PodFailurePolicyOnExitCodesRequirementBuilder(onExitCodes);
         this._visitables.get("onExitCodes").add(this.onExitCodes);
      } else {
         this.onExitCodes = null;
         this._visitables.get("onExitCodes").remove(this.onExitCodes);
      }

      return this;
   }

   public boolean hasOnExitCodes() {
      return this.onExitCodes != null;
   }

   public OnExitCodesNested withNewOnExitCodes() {
      return new OnExitCodesNested((PodFailurePolicyOnExitCodesRequirement)null);
   }

   public OnExitCodesNested withNewOnExitCodesLike(PodFailurePolicyOnExitCodesRequirement item) {
      return new OnExitCodesNested(item);
   }

   public OnExitCodesNested editOnExitCodes() {
      return this.withNewOnExitCodesLike((PodFailurePolicyOnExitCodesRequirement)Optional.ofNullable(this.buildOnExitCodes()).orElse((Object)null));
   }

   public OnExitCodesNested editOrNewOnExitCodes() {
      return this.withNewOnExitCodesLike((PodFailurePolicyOnExitCodesRequirement)Optional.ofNullable(this.buildOnExitCodes()).orElse((new PodFailurePolicyOnExitCodesRequirementBuilder()).build()));
   }

   public OnExitCodesNested editOrNewOnExitCodesLike(PodFailurePolicyOnExitCodesRequirement item) {
      return this.withNewOnExitCodesLike((PodFailurePolicyOnExitCodesRequirement)Optional.ofNullable(this.buildOnExitCodes()).orElse(item));
   }

   public PodFailurePolicyRuleFluent addToOnPodConditions(int index, PodFailurePolicyOnPodConditionsPattern item) {
      if (this.onPodConditions == null) {
         this.onPodConditions = new ArrayList();
      }

      PodFailurePolicyOnPodConditionsPatternBuilder builder = new PodFailurePolicyOnPodConditionsPatternBuilder(item);
      if (index >= 0 && index < this.onPodConditions.size()) {
         this._visitables.get("onPodConditions").add(index, builder);
         this.onPodConditions.add(index, builder);
      } else {
         this._visitables.get("onPodConditions").add(builder);
         this.onPodConditions.add(builder);
      }

      return this;
   }

   public PodFailurePolicyRuleFluent setToOnPodConditions(int index, PodFailurePolicyOnPodConditionsPattern item) {
      if (this.onPodConditions == null) {
         this.onPodConditions = new ArrayList();
      }

      PodFailurePolicyOnPodConditionsPatternBuilder builder = new PodFailurePolicyOnPodConditionsPatternBuilder(item);
      if (index >= 0 && index < this.onPodConditions.size()) {
         this._visitables.get("onPodConditions").set(index, builder);
         this.onPodConditions.set(index, builder);
      } else {
         this._visitables.get("onPodConditions").add(builder);
         this.onPodConditions.add(builder);
      }

      return this;
   }

   public PodFailurePolicyRuleFluent addToOnPodConditions(PodFailurePolicyOnPodConditionsPattern... items) {
      if (this.onPodConditions == null) {
         this.onPodConditions = new ArrayList();
      }

      for(PodFailurePolicyOnPodConditionsPattern item : items) {
         PodFailurePolicyOnPodConditionsPatternBuilder builder = new PodFailurePolicyOnPodConditionsPatternBuilder(item);
         this._visitables.get("onPodConditions").add(builder);
         this.onPodConditions.add(builder);
      }

      return this;
   }

   public PodFailurePolicyRuleFluent addAllToOnPodConditions(Collection items) {
      if (this.onPodConditions == null) {
         this.onPodConditions = new ArrayList();
      }

      for(PodFailurePolicyOnPodConditionsPattern item : items) {
         PodFailurePolicyOnPodConditionsPatternBuilder builder = new PodFailurePolicyOnPodConditionsPatternBuilder(item);
         this._visitables.get("onPodConditions").add(builder);
         this.onPodConditions.add(builder);
      }

      return this;
   }

   public PodFailurePolicyRuleFluent removeFromOnPodConditions(PodFailurePolicyOnPodConditionsPattern... items) {
      if (this.onPodConditions == null) {
         return this;
      } else {
         for(PodFailurePolicyOnPodConditionsPattern item : items) {
            PodFailurePolicyOnPodConditionsPatternBuilder builder = new PodFailurePolicyOnPodConditionsPatternBuilder(item);
            this._visitables.get("onPodConditions").remove(builder);
            this.onPodConditions.remove(builder);
         }

         return this;
      }
   }

   public PodFailurePolicyRuleFluent removeAllFromOnPodConditions(Collection items) {
      if (this.onPodConditions == null) {
         return this;
      } else {
         for(PodFailurePolicyOnPodConditionsPattern item : items) {
            PodFailurePolicyOnPodConditionsPatternBuilder builder = new PodFailurePolicyOnPodConditionsPatternBuilder(item);
            this._visitables.get("onPodConditions").remove(builder);
            this.onPodConditions.remove(builder);
         }

         return this;
      }
   }

   public PodFailurePolicyRuleFluent removeMatchingFromOnPodConditions(Predicate predicate) {
      if (this.onPodConditions == null) {
         return this;
      } else {
         Iterator<PodFailurePolicyOnPodConditionsPatternBuilder> each = this.onPodConditions.iterator();
         List visitables = this._visitables.get("onPodConditions");

         while(each.hasNext()) {
            PodFailurePolicyOnPodConditionsPatternBuilder builder = (PodFailurePolicyOnPodConditionsPatternBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildOnPodConditions() {
      return this.onPodConditions != null ? build(this.onPodConditions) : null;
   }

   public PodFailurePolicyOnPodConditionsPattern buildOnPodCondition(int index) {
      return ((PodFailurePolicyOnPodConditionsPatternBuilder)this.onPodConditions.get(index)).build();
   }

   public PodFailurePolicyOnPodConditionsPattern buildFirstOnPodCondition() {
      return ((PodFailurePolicyOnPodConditionsPatternBuilder)this.onPodConditions.get(0)).build();
   }

   public PodFailurePolicyOnPodConditionsPattern buildLastOnPodCondition() {
      return ((PodFailurePolicyOnPodConditionsPatternBuilder)this.onPodConditions.get(this.onPodConditions.size() - 1)).build();
   }

   public PodFailurePolicyOnPodConditionsPattern buildMatchingOnPodCondition(Predicate predicate) {
      for(PodFailurePolicyOnPodConditionsPatternBuilder item : this.onPodConditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingOnPodCondition(Predicate predicate) {
      for(PodFailurePolicyOnPodConditionsPatternBuilder item : this.onPodConditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodFailurePolicyRuleFluent withOnPodConditions(List onPodConditions) {
      if (this.onPodConditions != null) {
         this._visitables.get("onPodConditions").clear();
      }

      if (onPodConditions != null) {
         this.onPodConditions = new ArrayList();

         for(PodFailurePolicyOnPodConditionsPattern item : onPodConditions) {
            this.addToOnPodConditions(item);
         }
      } else {
         this.onPodConditions = null;
      }

      return this;
   }

   public PodFailurePolicyRuleFluent withOnPodConditions(PodFailurePolicyOnPodConditionsPattern... onPodConditions) {
      if (this.onPodConditions != null) {
         this.onPodConditions.clear();
         this._visitables.remove("onPodConditions");
      }

      if (onPodConditions != null) {
         for(PodFailurePolicyOnPodConditionsPattern item : onPodConditions) {
            this.addToOnPodConditions(item);
         }
      }

      return this;
   }

   public boolean hasOnPodConditions() {
      return this.onPodConditions != null && !this.onPodConditions.isEmpty();
   }

   public PodFailurePolicyRuleFluent addNewOnPodCondition(String status, String type) {
      return this.addToOnPodConditions(new PodFailurePolicyOnPodConditionsPattern(status, type));
   }

   public OnPodConditionsNested addNewOnPodCondition() {
      return new OnPodConditionsNested(-1, (PodFailurePolicyOnPodConditionsPattern)null);
   }

   public OnPodConditionsNested addNewOnPodConditionLike(PodFailurePolicyOnPodConditionsPattern item) {
      return new OnPodConditionsNested(-1, item);
   }

   public OnPodConditionsNested setNewOnPodConditionLike(int index, PodFailurePolicyOnPodConditionsPattern item) {
      return new OnPodConditionsNested(index, item);
   }

   public OnPodConditionsNested editOnPodCondition(int index) {
      if (this.onPodConditions.size() <= index) {
         throw new RuntimeException("Can't edit onPodConditions. Index exceeds size.");
      } else {
         return this.setNewOnPodConditionLike(index, this.buildOnPodCondition(index));
      }
   }

   public OnPodConditionsNested editFirstOnPodCondition() {
      if (this.onPodConditions.size() == 0) {
         throw new RuntimeException("Can't edit first onPodConditions. The list is empty.");
      } else {
         return this.setNewOnPodConditionLike(0, this.buildOnPodCondition(0));
      }
   }

   public OnPodConditionsNested editLastOnPodCondition() {
      int index = this.onPodConditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last onPodConditions. The list is empty.");
      } else {
         return this.setNewOnPodConditionLike(index, this.buildOnPodCondition(index));
      }
   }

   public OnPodConditionsNested editMatchingOnPodCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.onPodConditions.size(); ++i) {
         if (predicate.test((PodFailurePolicyOnPodConditionsPatternBuilder)this.onPodConditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching onPodConditions. No match found.");
      } else {
         return this.setNewOnPodConditionLike(index, this.buildOnPodCondition(index));
      }
   }

   public PodFailurePolicyRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodFailurePolicyRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodFailurePolicyRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodFailurePolicyRuleFluent removeFromAdditionalProperties(Map map) {
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

   public PodFailurePolicyRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            PodFailurePolicyRuleFluent that = (PodFailurePolicyRuleFluent)o;
            if (!Objects.equals(this.action, that.action)) {
               return false;
            } else if (!Objects.equals(this.onExitCodes, that.onExitCodes)) {
               return false;
            } else if (!Objects.equals(this.onPodConditions, that.onPodConditions)) {
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
      return Objects.hash(new Object[]{this.action, this.onExitCodes, this.onPodConditions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.action != null) {
         sb.append("action:");
         sb.append(this.action + ",");
      }

      if (this.onExitCodes != null) {
         sb.append("onExitCodes:");
         sb.append(this.onExitCodes + ",");
      }

      if (this.onPodConditions != null && !this.onPodConditions.isEmpty()) {
         sb.append("onPodConditions:");
         sb.append(this.onPodConditions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OnExitCodesNested extends PodFailurePolicyOnExitCodesRequirementFluent implements Nested {
      PodFailurePolicyOnExitCodesRequirementBuilder builder;

      OnExitCodesNested(PodFailurePolicyOnExitCodesRequirement item) {
         this.builder = new PodFailurePolicyOnExitCodesRequirementBuilder(this, item);
      }

      public Object and() {
         return PodFailurePolicyRuleFluent.this.withOnExitCodes(this.builder.build());
      }

      public Object endOnExitCodes() {
         return this.and();
      }
   }

   public class OnPodConditionsNested extends PodFailurePolicyOnPodConditionsPatternFluent implements Nested {
      PodFailurePolicyOnPodConditionsPatternBuilder builder;
      int index;

      OnPodConditionsNested(int index, PodFailurePolicyOnPodConditionsPattern item) {
         this.index = index;
         this.builder = new PodFailurePolicyOnPodConditionsPatternBuilder(this, item);
      }

      public Object and() {
         return PodFailurePolicyRuleFluent.this.setToOnPodConditions(this.index, this.builder.build());
      }

      public Object endOnPodCondition() {
         return this.and();
      }
   }
}
