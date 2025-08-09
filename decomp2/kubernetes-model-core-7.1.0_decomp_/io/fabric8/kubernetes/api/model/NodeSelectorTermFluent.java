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

public class NodeSelectorTermFluent extends BaseFluent {
   private ArrayList matchExpressions = new ArrayList();
   private ArrayList matchFields = new ArrayList();
   private Map additionalProperties;

   public NodeSelectorTermFluent() {
   }

   public NodeSelectorTermFluent(NodeSelectorTerm instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeSelectorTerm instance) {
      instance = instance != null ? instance : new NodeSelectorTerm();
      if (instance != null) {
         this.withMatchExpressions(instance.getMatchExpressions());
         this.withMatchFields(instance.getMatchFields());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeSelectorTermFluent addToMatchExpressions(int index, NodeSelectorRequirement item) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
      if (index >= 0 && index < this.matchExpressions.size()) {
         this._visitables.get("matchExpressions").add(index, builder);
         this.matchExpressions.add(index, builder);
      } else {
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent setToMatchExpressions(int index, NodeSelectorRequirement item) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
      if (index >= 0 && index < this.matchExpressions.size()) {
         this._visitables.get("matchExpressions").set(index, builder);
         this.matchExpressions.set(index, builder);
      } else {
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent addToMatchExpressions(NodeSelectorRequirement... items) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      for(NodeSelectorRequirement item : items) {
         NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent addAllToMatchExpressions(Collection items) {
      if (this.matchExpressions == null) {
         this.matchExpressions = new ArrayList();
      }

      for(NodeSelectorRequirement item : items) {
         NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
         this._visitables.get("matchExpressions").add(builder);
         this.matchExpressions.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent removeFromMatchExpressions(NodeSelectorRequirement... items) {
      if (this.matchExpressions == null) {
         return this;
      } else {
         for(NodeSelectorRequirement item : items) {
            NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
            this._visitables.get("matchExpressions").remove(builder);
            this.matchExpressions.remove(builder);
         }

         return this;
      }
   }

   public NodeSelectorTermFluent removeAllFromMatchExpressions(Collection items) {
      if (this.matchExpressions == null) {
         return this;
      } else {
         for(NodeSelectorRequirement item : items) {
            NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
            this._visitables.get("matchExpressions").remove(builder);
            this.matchExpressions.remove(builder);
         }

         return this;
      }
   }

   public NodeSelectorTermFluent removeMatchingFromMatchExpressions(Predicate predicate) {
      if (this.matchExpressions == null) {
         return this;
      } else {
         Iterator<NodeSelectorRequirementBuilder> each = this.matchExpressions.iterator();
         List visitables = this._visitables.get("matchExpressions");

         while(each.hasNext()) {
            NodeSelectorRequirementBuilder builder = (NodeSelectorRequirementBuilder)each.next();
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

   public NodeSelectorRequirement buildMatchExpression(int index) {
      return ((NodeSelectorRequirementBuilder)this.matchExpressions.get(index)).build();
   }

   public NodeSelectorRequirement buildFirstMatchExpression() {
      return ((NodeSelectorRequirementBuilder)this.matchExpressions.get(0)).build();
   }

   public NodeSelectorRequirement buildLastMatchExpression() {
      return ((NodeSelectorRequirementBuilder)this.matchExpressions.get(this.matchExpressions.size() - 1)).build();
   }

   public NodeSelectorRequirement buildMatchingMatchExpression(Predicate predicate) {
      for(NodeSelectorRequirementBuilder item : this.matchExpressions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatchExpression(Predicate predicate) {
      for(NodeSelectorRequirementBuilder item : this.matchExpressions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeSelectorTermFluent withMatchExpressions(List matchExpressions) {
      if (this.matchExpressions != null) {
         this._visitables.get("matchExpressions").clear();
      }

      if (matchExpressions != null) {
         this.matchExpressions = new ArrayList();

         for(NodeSelectorRequirement item : matchExpressions) {
            this.addToMatchExpressions(item);
         }
      } else {
         this.matchExpressions = null;
      }

      return this;
   }

   public NodeSelectorTermFluent withMatchExpressions(NodeSelectorRequirement... matchExpressions) {
      if (this.matchExpressions != null) {
         this.matchExpressions.clear();
         this._visitables.remove("matchExpressions");
      }

      if (matchExpressions != null) {
         for(NodeSelectorRequirement item : matchExpressions) {
            this.addToMatchExpressions(item);
         }
      }

      return this;
   }

   public boolean hasMatchExpressions() {
      return this.matchExpressions != null && !this.matchExpressions.isEmpty();
   }

   public MatchExpressionsNested addNewMatchExpression() {
      return new MatchExpressionsNested(-1, (NodeSelectorRequirement)null);
   }

   public MatchExpressionsNested addNewMatchExpressionLike(NodeSelectorRequirement item) {
      return new MatchExpressionsNested(-1, item);
   }

   public MatchExpressionsNested setNewMatchExpressionLike(int index, NodeSelectorRequirement item) {
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
         if (predicate.test((NodeSelectorRequirementBuilder)this.matchExpressions.get(i))) {
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

   public NodeSelectorTermFluent addToMatchFields(int index, NodeSelectorRequirement item) {
      if (this.matchFields == null) {
         this.matchFields = new ArrayList();
      }

      NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
      if (index >= 0 && index < this.matchFields.size()) {
         this._visitables.get("matchFields").add(index, builder);
         this.matchFields.add(index, builder);
      } else {
         this._visitables.get("matchFields").add(builder);
         this.matchFields.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent setToMatchFields(int index, NodeSelectorRequirement item) {
      if (this.matchFields == null) {
         this.matchFields = new ArrayList();
      }

      NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
      if (index >= 0 && index < this.matchFields.size()) {
         this._visitables.get("matchFields").set(index, builder);
         this.matchFields.set(index, builder);
      } else {
         this._visitables.get("matchFields").add(builder);
         this.matchFields.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent addToMatchFields(NodeSelectorRequirement... items) {
      if (this.matchFields == null) {
         this.matchFields = new ArrayList();
      }

      for(NodeSelectorRequirement item : items) {
         NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
         this._visitables.get("matchFields").add(builder);
         this.matchFields.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent addAllToMatchFields(Collection items) {
      if (this.matchFields == null) {
         this.matchFields = new ArrayList();
      }

      for(NodeSelectorRequirement item : items) {
         NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
         this._visitables.get("matchFields").add(builder);
         this.matchFields.add(builder);
      }

      return this;
   }

   public NodeSelectorTermFluent removeFromMatchFields(NodeSelectorRequirement... items) {
      if (this.matchFields == null) {
         return this;
      } else {
         for(NodeSelectorRequirement item : items) {
            NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
            this._visitables.get("matchFields").remove(builder);
            this.matchFields.remove(builder);
         }

         return this;
      }
   }

   public NodeSelectorTermFluent removeAllFromMatchFields(Collection items) {
      if (this.matchFields == null) {
         return this;
      } else {
         for(NodeSelectorRequirement item : items) {
            NodeSelectorRequirementBuilder builder = new NodeSelectorRequirementBuilder(item);
            this._visitables.get("matchFields").remove(builder);
            this.matchFields.remove(builder);
         }

         return this;
      }
   }

   public NodeSelectorTermFluent removeMatchingFromMatchFields(Predicate predicate) {
      if (this.matchFields == null) {
         return this;
      } else {
         Iterator<NodeSelectorRequirementBuilder> each = this.matchFields.iterator();
         List visitables = this._visitables.get("matchFields");

         while(each.hasNext()) {
            NodeSelectorRequirementBuilder builder = (NodeSelectorRequirementBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMatchFields() {
      return this.matchFields != null ? build(this.matchFields) : null;
   }

   public NodeSelectorRequirement buildMatchField(int index) {
      return ((NodeSelectorRequirementBuilder)this.matchFields.get(index)).build();
   }

   public NodeSelectorRequirement buildFirstMatchField() {
      return ((NodeSelectorRequirementBuilder)this.matchFields.get(0)).build();
   }

   public NodeSelectorRequirement buildLastMatchField() {
      return ((NodeSelectorRequirementBuilder)this.matchFields.get(this.matchFields.size() - 1)).build();
   }

   public NodeSelectorRequirement buildMatchingMatchField(Predicate predicate) {
      for(NodeSelectorRequirementBuilder item : this.matchFields) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatchField(Predicate predicate) {
      for(NodeSelectorRequirementBuilder item : this.matchFields) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeSelectorTermFluent withMatchFields(List matchFields) {
      if (this.matchFields != null) {
         this._visitables.get("matchFields").clear();
      }

      if (matchFields != null) {
         this.matchFields = new ArrayList();

         for(NodeSelectorRequirement item : matchFields) {
            this.addToMatchFields(item);
         }
      } else {
         this.matchFields = null;
      }

      return this;
   }

   public NodeSelectorTermFluent withMatchFields(NodeSelectorRequirement... matchFields) {
      if (this.matchFields != null) {
         this.matchFields.clear();
         this._visitables.remove("matchFields");
      }

      if (matchFields != null) {
         for(NodeSelectorRequirement item : matchFields) {
            this.addToMatchFields(item);
         }
      }

      return this;
   }

   public boolean hasMatchFields() {
      return this.matchFields != null && !this.matchFields.isEmpty();
   }

   public MatchFieldsNested addNewMatchField() {
      return new MatchFieldsNested(-1, (NodeSelectorRequirement)null);
   }

   public MatchFieldsNested addNewMatchFieldLike(NodeSelectorRequirement item) {
      return new MatchFieldsNested(-1, item);
   }

   public MatchFieldsNested setNewMatchFieldLike(int index, NodeSelectorRequirement item) {
      return new MatchFieldsNested(index, item);
   }

   public MatchFieldsNested editMatchField(int index) {
      if (this.matchFields.size() <= index) {
         throw new RuntimeException("Can't edit matchFields. Index exceeds size.");
      } else {
         return this.setNewMatchFieldLike(index, this.buildMatchField(index));
      }
   }

   public MatchFieldsNested editFirstMatchField() {
      if (this.matchFields.size() == 0) {
         throw new RuntimeException("Can't edit first matchFields. The list is empty.");
      } else {
         return this.setNewMatchFieldLike(0, this.buildMatchField(0));
      }
   }

   public MatchFieldsNested editLastMatchField() {
      int index = this.matchFields.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last matchFields. The list is empty.");
      } else {
         return this.setNewMatchFieldLike(index, this.buildMatchField(index));
      }
   }

   public MatchFieldsNested editMatchingMatchField(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.matchFields.size(); ++i) {
         if (predicate.test((NodeSelectorRequirementBuilder)this.matchFields.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching matchFields. No match found.");
      } else {
         return this.setNewMatchFieldLike(index, this.buildMatchField(index));
      }
   }

   public NodeSelectorTermFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeSelectorTermFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeSelectorTermFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeSelectorTermFluent removeFromAdditionalProperties(Map map) {
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

   public NodeSelectorTermFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeSelectorTermFluent that = (NodeSelectorTermFluent)o;
            if (!Objects.equals(this.matchExpressions, that.matchExpressions)) {
               return false;
            } else if (!Objects.equals(this.matchFields, that.matchFields)) {
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
      return Objects.hash(new Object[]{this.matchExpressions, this.matchFields, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.matchExpressions != null && !this.matchExpressions.isEmpty()) {
         sb.append("matchExpressions:");
         sb.append(this.matchExpressions + ",");
      }

      if (this.matchFields != null && !this.matchFields.isEmpty()) {
         sb.append("matchFields:");
         sb.append(this.matchFields + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MatchExpressionsNested extends NodeSelectorRequirementFluent implements Nested {
      NodeSelectorRequirementBuilder builder;
      int index;

      MatchExpressionsNested(int index, NodeSelectorRequirement item) {
         this.index = index;
         this.builder = new NodeSelectorRequirementBuilder(this, item);
      }

      public Object and() {
         return NodeSelectorTermFluent.this.setToMatchExpressions(this.index, this.builder.build());
      }

      public Object endMatchExpression() {
         return this.and();
      }
   }

   public class MatchFieldsNested extends NodeSelectorRequirementFluent implements Nested {
      NodeSelectorRequirementBuilder builder;
      int index;

      MatchFieldsNested(int index, NodeSelectorRequirement item) {
         this.index = index;
         this.builder = new NodeSelectorRequirementBuilder(this, item);
      }

      public Object and() {
         return NodeSelectorTermFluent.this.setToMatchFields(this.index, this.builder.build());
      }

      public Object endMatchField() {
         return this.and();
      }
   }
}
