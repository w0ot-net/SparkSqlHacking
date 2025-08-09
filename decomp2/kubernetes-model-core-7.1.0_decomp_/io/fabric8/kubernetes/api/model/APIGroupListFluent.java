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

public class APIGroupListFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList groups = new ArrayList();
   private String kind;
   private Map additionalProperties;

   public APIGroupListFluent() {
   }

   public APIGroupListFluent(APIGroupList instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIGroupList instance) {
      instance = instance != null ? instance : new APIGroupList();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withGroups(instance.getGroups());
         this.withKind(instance.getKind());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public APIGroupListFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public APIGroupListFluent addToGroups(int index, APIGroup item) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      APIGroupBuilder builder = new APIGroupBuilder(item);
      if (index >= 0 && index < this.groups.size()) {
         this._visitables.get("groups").add(index, builder);
         this.groups.add(index, builder);
      } else {
         this._visitables.get("groups").add(builder);
         this.groups.add(builder);
      }

      return this;
   }

   public APIGroupListFluent setToGroups(int index, APIGroup item) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      APIGroupBuilder builder = new APIGroupBuilder(item);
      if (index >= 0 && index < this.groups.size()) {
         this._visitables.get("groups").set(index, builder);
         this.groups.set(index, builder);
      } else {
         this._visitables.get("groups").add(builder);
         this.groups.add(builder);
      }

      return this;
   }

   public APIGroupListFluent addToGroups(APIGroup... items) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      for(APIGroup item : items) {
         APIGroupBuilder builder = new APIGroupBuilder(item);
         this._visitables.get("groups").add(builder);
         this.groups.add(builder);
      }

      return this;
   }

   public APIGroupListFluent addAllToGroups(Collection items) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      for(APIGroup item : items) {
         APIGroupBuilder builder = new APIGroupBuilder(item);
         this._visitables.get("groups").add(builder);
         this.groups.add(builder);
      }

      return this;
   }

   public APIGroupListFluent removeFromGroups(APIGroup... items) {
      if (this.groups == null) {
         return this;
      } else {
         for(APIGroup item : items) {
            APIGroupBuilder builder = new APIGroupBuilder(item);
            this._visitables.get("groups").remove(builder);
            this.groups.remove(builder);
         }

         return this;
      }
   }

   public APIGroupListFluent removeAllFromGroups(Collection items) {
      if (this.groups == null) {
         return this;
      } else {
         for(APIGroup item : items) {
            APIGroupBuilder builder = new APIGroupBuilder(item);
            this._visitables.get("groups").remove(builder);
            this.groups.remove(builder);
         }

         return this;
      }
   }

   public APIGroupListFluent removeMatchingFromGroups(Predicate predicate) {
      if (this.groups == null) {
         return this;
      } else {
         Iterator<APIGroupBuilder> each = this.groups.iterator();
         List visitables = this._visitables.get("groups");

         while(each.hasNext()) {
            APIGroupBuilder builder = (APIGroupBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildGroups() {
      return this.groups != null ? build(this.groups) : null;
   }

   public APIGroup buildGroup(int index) {
      return ((APIGroupBuilder)this.groups.get(index)).build();
   }

   public APIGroup buildFirstGroup() {
      return ((APIGroupBuilder)this.groups.get(0)).build();
   }

   public APIGroup buildLastGroup() {
      return ((APIGroupBuilder)this.groups.get(this.groups.size() - 1)).build();
   }

   public APIGroup buildMatchingGroup(Predicate predicate) {
      for(APIGroupBuilder item : this.groups) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingGroup(Predicate predicate) {
      for(APIGroupBuilder item : this.groups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public APIGroupListFluent withGroups(List groups) {
      if (this.groups != null) {
         this._visitables.get("groups").clear();
      }

      if (groups != null) {
         this.groups = new ArrayList();

         for(APIGroup item : groups) {
            this.addToGroups(item);
         }
      } else {
         this.groups = null;
      }

      return this;
   }

   public APIGroupListFluent withGroups(APIGroup... groups) {
      if (this.groups != null) {
         this.groups.clear();
         this._visitables.remove("groups");
      }

      if (groups != null) {
         for(APIGroup item : groups) {
            this.addToGroups(item);
         }
      }

      return this;
   }

   public boolean hasGroups() {
      return this.groups != null && !this.groups.isEmpty();
   }

   public GroupsNested addNewGroup() {
      return new GroupsNested(-1, (APIGroup)null);
   }

   public GroupsNested addNewGroupLike(APIGroup item) {
      return new GroupsNested(-1, item);
   }

   public GroupsNested setNewGroupLike(int index, APIGroup item) {
      return new GroupsNested(index, item);
   }

   public GroupsNested editGroup(int index) {
      if (this.groups.size() <= index) {
         throw new RuntimeException("Can't edit groups. Index exceeds size.");
      } else {
         return this.setNewGroupLike(index, this.buildGroup(index));
      }
   }

   public GroupsNested editFirstGroup() {
      if (this.groups.size() == 0) {
         throw new RuntimeException("Can't edit first groups. The list is empty.");
      } else {
         return this.setNewGroupLike(0, this.buildGroup(0));
      }
   }

   public GroupsNested editLastGroup() {
      int index = this.groups.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last groups. The list is empty.");
      } else {
         return this.setNewGroupLike(index, this.buildGroup(index));
      }
   }

   public GroupsNested editMatchingGroup(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.groups.size(); ++i) {
         if (predicate.test((APIGroupBuilder)this.groups.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching groups. No match found.");
      } else {
         return this.setNewGroupLike(index, this.buildGroup(index));
      }
   }

   public String getKind() {
      return this.kind;
   }

   public APIGroupListFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public APIGroupListFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIGroupListFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIGroupListFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIGroupListFluent removeFromAdditionalProperties(Map map) {
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

   public APIGroupListFluent withAdditionalProperties(Map additionalProperties) {
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
            APIGroupListFluent that = (APIGroupListFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.groups, that.groups)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.groups, this.kind, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.groups != null && !this.groups.isEmpty()) {
         sb.append("groups:");
         sb.append(this.groups + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class GroupsNested extends APIGroupFluent implements Nested {
      APIGroupBuilder builder;
      int index;

      GroupsNested(int index, APIGroup item) {
         this.index = index;
         this.builder = new APIGroupBuilder(this, item);
      }

      public Object and() {
         return APIGroupListFluent.this.setToGroups(this.index, this.builder.build());
      }

      public Object endGroup() {
         return this.and();
      }
   }
}
