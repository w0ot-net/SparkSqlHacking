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

public class ListenerStatusFluent extends BaseFluent {
   private Integer attachedRoutes;
   private List conditions = new ArrayList();
   private String name;
   private ArrayList supportedKinds = new ArrayList();
   private Map additionalProperties;

   public ListenerStatusFluent() {
   }

   public ListenerStatusFluent(ListenerStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ListenerStatus instance) {
      instance = instance != null ? instance : new ListenerStatus();
      if (instance != null) {
         this.withAttachedRoutes(instance.getAttachedRoutes());
         this.withConditions(instance.getConditions());
         this.withName(instance.getName());
         this.withSupportedKinds(instance.getSupportedKinds());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAttachedRoutes() {
      return this.attachedRoutes;
   }

   public ListenerStatusFluent withAttachedRoutes(Integer attachedRoutes) {
      this.attachedRoutes = attachedRoutes;
      return this;
   }

   public boolean hasAttachedRoutes() {
      return this.attachedRoutes != null;
   }

   public ListenerStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public ListenerStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public ListenerStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public ListenerStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public ListenerStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public ListenerStatusFluent removeAllFromConditions(Collection items) {
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

   public ListenerStatusFluent withConditions(List conditions) {
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

   public ListenerStatusFluent withConditions(Condition... conditions) {
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

   public String getName() {
      return this.name;
   }

   public ListenerStatusFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ListenerStatusFluent addToSupportedKinds(int index, RouteGroupKind item) {
      if (this.supportedKinds == null) {
         this.supportedKinds = new ArrayList();
      }

      RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
      if (index >= 0 && index < this.supportedKinds.size()) {
         this._visitables.get("supportedKinds").add(index, builder);
         this.supportedKinds.add(index, builder);
      } else {
         this._visitables.get("supportedKinds").add(builder);
         this.supportedKinds.add(builder);
      }

      return this;
   }

   public ListenerStatusFluent setToSupportedKinds(int index, RouteGroupKind item) {
      if (this.supportedKinds == null) {
         this.supportedKinds = new ArrayList();
      }

      RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
      if (index >= 0 && index < this.supportedKinds.size()) {
         this._visitables.get("supportedKinds").set(index, builder);
         this.supportedKinds.set(index, builder);
      } else {
         this._visitables.get("supportedKinds").add(builder);
         this.supportedKinds.add(builder);
      }

      return this;
   }

   public ListenerStatusFluent addToSupportedKinds(RouteGroupKind... items) {
      if (this.supportedKinds == null) {
         this.supportedKinds = new ArrayList();
      }

      for(RouteGroupKind item : items) {
         RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
         this._visitables.get("supportedKinds").add(builder);
         this.supportedKinds.add(builder);
      }

      return this;
   }

   public ListenerStatusFluent addAllToSupportedKinds(Collection items) {
      if (this.supportedKinds == null) {
         this.supportedKinds = new ArrayList();
      }

      for(RouteGroupKind item : items) {
         RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
         this._visitables.get("supportedKinds").add(builder);
         this.supportedKinds.add(builder);
      }

      return this;
   }

   public ListenerStatusFluent removeFromSupportedKinds(RouteGroupKind... items) {
      if (this.supportedKinds == null) {
         return this;
      } else {
         for(RouteGroupKind item : items) {
            RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
            this._visitables.get("supportedKinds").remove(builder);
            this.supportedKinds.remove(builder);
         }

         return this;
      }
   }

   public ListenerStatusFluent removeAllFromSupportedKinds(Collection items) {
      if (this.supportedKinds == null) {
         return this;
      } else {
         for(RouteGroupKind item : items) {
            RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
            this._visitables.get("supportedKinds").remove(builder);
            this.supportedKinds.remove(builder);
         }

         return this;
      }
   }

   public ListenerStatusFluent removeMatchingFromSupportedKinds(Predicate predicate) {
      if (this.supportedKinds == null) {
         return this;
      } else {
         Iterator<RouteGroupKindBuilder> each = this.supportedKinds.iterator();
         List visitables = this._visitables.get("supportedKinds");

         while(each.hasNext()) {
            RouteGroupKindBuilder builder = (RouteGroupKindBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSupportedKinds() {
      return this.supportedKinds != null ? build(this.supportedKinds) : null;
   }

   public RouteGroupKind buildSupportedKind(int index) {
      return ((RouteGroupKindBuilder)this.supportedKinds.get(index)).build();
   }

   public RouteGroupKind buildFirstSupportedKind() {
      return ((RouteGroupKindBuilder)this.supportedKinds.get(0)).build();
   }

   public RouteGroupKind buildLastSupportedKind() {
      return ((RouteGroupKindBuilder)this.supportedKinds.get(this.supportedKinds.size() - 1)).build();
   }

   public RouteGroupKind buildMatchingSupportedKind(Predicate predicate) {
      for(RouteGroupKindBuilder item : this.supportedKinds) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSupportedKind(Predicate predicate) {
      for(RouteGroupKindBuilder item : this.supportedKinds) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ListenerStatusFluent withSupportedKinds(List supportedKinds) {
      if (this.supportedKinds != null) {
         this._visitables.get("supportedKinds").clear();
      }

      if (supportedKinds != null) {
         this.supportedKinds = new ArrayList();

         for(RouteGroupKind item : supportedKinds) {
            this.addToSupportedKinds(item);
         }
      } else {
         this.supportedKinds = null;
      }

      return this;
   }

   public ListenerStatusFluent withSupportedKinds(RouteGroupKind... supportedKinds) {
      if (this.supportedKinds != null) {
         this.supportedKinds.clear();
         this._visitables.remove("supportedKinds");
      }

      if (supportedKinds != null) {
         for(RouteGroupKind item : supportedKinds) {
            this.addToSupportedKinds(item);
         }
      }

      return this;
   }

   public boolean hasSupportedKinds() {
      return this.supportedKinds != null && !this.supportedKinds.isEmpty();
   }

   public ListenerStatusFluent addNewSupportedKind(String group, String kind) {
      return this.addToSupportedKinds(new RouteGroupKind(group, kind));
   }

   public SupportedKindsNested addNewSupportedKind() {
      return new SupportedKindsNested(-1, (RouteGroupKind)null);
   }

   public SupportedKindsNested addNewSupportedKindLike(RouteGroupKind item) {
      return new SupportedKindsNested(-1, item);
   }

   public SupportedKindsNested setNewSupportedKindLike(int index, RouteGroupKind item) {
      return new SupportedKindsNested(index, item);
   }

   public SupportedKindsNested editSupportedKind(int index) {
      if (this.supportedKinds.size() <= index) {
         throw new RuntimeException("Can't edit supportedKinds. Index exceeds size.");
      } else {
         return this.setNewSupportedKindLike(index, this.buildSupportedKind(index));
      }
   }

   public SupportedKindsNested editFirstSupportedKind() {
      if (this.supportedKinds.size() == 0) {
         throw new RuntimeException("Can't edit first supportedKinds. The list is empty.");
      } else {
         return this.setNewSupportedKindLike(0, this.buildSupportedKind(0));
      }
   }

   public SupportedKindsNested editLastSupportedKind() {
      int index = this.supportedKinds.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last supportedKinds. The list is empty.");
      } else {
         return this.setNewSupportedKindLike(index, this.buildSupportedKind(index));
      }
   }

   public SupportedKindsNested editMatchingSupportedKind(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.supportedKinds.size(); ++i) {
         if (predicate.test((RouteGroupKindBuilder)this.supportedKinds.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching supportedKinds. No match found.");
      } else {
         return this.setNewSupportedKindLike(index, this.buildSupportedKind(index));
      }
   }

   public ListenerStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ListenerStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ListenerStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ListenerStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ListenerStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ListenerStatusFluent that = (ListenerStatusFluent)o;
            if (!Objects.equals(this.attachedRoutes, that.attachedRoutes)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.supportedKinds, that.supportedKinds)) {
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
      return Objects.hash(new Object[]{this.attachedRoutes, this.conditions, this.name, this.supportedKinds, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attachedRoutes != null) {
         sb.append("attachedRoutes:");
         sb.append(this.attachedRoutes + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.supportedKinds != null && !this.supportedKinds.isEmpty()) {
         sb.append("supportedKinds:");
         sb.append(this.supportedKinds + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SupportedKindsNested extends RouteGroupKindFluent implements Nested {
      RouteGroupKindBuilder builder;
      int index;

      SupportedKindsNested(int index, RouteGroupKind item) {
         this.index = index;
         this.builder = new RouteGroupKindBuilder(this, item);
      }

      public Object and() {
         return ListenerStatusFluent.this.setToSupportedKinds(this.index, this.builder.build());
      }

      public Object endSupportedKind() {
         return this.and();
      }
   }
}
