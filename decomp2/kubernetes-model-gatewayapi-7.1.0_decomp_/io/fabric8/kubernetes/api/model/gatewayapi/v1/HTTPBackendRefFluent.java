package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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

public class HTTPBackendRefFluent extends BaseFluent {
   private ArrayList filters = new ArrayList();
   private String group;
   private String kind;
   private String name;
   private String namespace;
   private Integer port;
   private Integer weight;
   private Map additionalProperties;

   public HTTPBackendRefFluent() {
   }

   public HTTPBackendRefFluent(HTTPBackendRef instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPBackendRef instance) {
      instance = instance != null ? instance : new HTTPBackendRef();
      if (instance != null) {
         this.withFilters(instance.getFilters());
         this.withGroup(instance.getGroup());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withPort(instance.getPort());
         this.withWeight(instance.getWeight());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HTTPBackendRefFluent addToFilters(int index, HTTPRouteFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").add(index, builder);
         this.filters.add(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPBackendRefFluent setToFilters(int index, HTTPRouteFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").set(index, builder);
         this.filters.set(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPBackendRefFluent addToFilters(HTTPRouteFilter... items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(HTTPRouteFilter item : items) {
         HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPBackendRefFluent addAllToFilters(Collection items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(HTTPRouteFilter item : items) {
         HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public HTTPBackendRefFluent removeFromFilters(HTTPRouteFilter... items) {
      if (this.filters == null) {
         return this;
      } else {
         for(HTTPRouteFilter item : items) {
            HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public HTTPBackendRefFluent removeAllFromFilters(Collection items) {
      if (this.filters == null) {
         return this;
      } else {
         for(HTTPRouteFilter item : items) {
            HTTPRouteFilterBuilder builder = new HTTPRouteFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public HTTPBackendRefFluent removeMatchingFromFilters(Predicate predicate) {
      if (this.filters == null) {
         return this;
      } else {
         Iterator<HTTPRouteFilterBuilder> each = this.filters.iterator();
         List visitables = this._visitables.get("filters");

         while(each.hasNext()) {
            HTTPRouteFilterBuilder builder = (HTTPRouteFilterBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildFilters() {
      return this.filters != null ? build(this.filters) : null;
   }

   public HTTPRouteFilter buildFilter(int index) {
      return ((HTTPRouteFilterBuilder)this.filters.get(index)).build();
   }

   public HTTPRouteFilter buildFirstFilter() {
      return ((HTTPRouteFilterBuilder)this.filters.get(0)).build();
   }

   public HTTPRouteFilter buildLastFilter() {
      return ((HTTPRouteFilterBuilder)this.filters.get(this.filters.size() - 1)).build();
   }

   public HTTPRouteFilter buildMatchingFilter(Predicate predicate) {
      for(HTTPRouteFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingFilter(Predicate predicate) {
      for(HTTPRouteFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPBackendRefFluent withFilters(List filters) {
      if (this.filters != null) {
         this._visitables.get("filters").clear();
      }

      if (filters != null) {
         this.filters = new ArrayList();

         for(HTTPRouteFilter item : filters) {
            this.addToFilters(item);
         }
      } else {
         this.filters = null;
      }

      return this;
   }

   public HTTPBackendRefFluent withFilters(HTTPRouteFilter... filters) {
      if (this.filters != null) {
         this.filters.clear();
         this._visitables.remove("filters");
      }

      if (filters != null) {
         for(HTTPRouteFilter item : filters) {
            this.addToFilters(item);
         }
      }

      return this;
   }

   public boolean hasFilters() {
      return this.filters != null && !this.filters.isEmpty();
   }

   public FiltersNested addNewFilter() {
      return new FiltersNested(-1, (HTTPRouteFilter)null);
   }

   public FiltersNested addNewFilterLike(HTTPRouteFilter item) {
      return new FiltersNested(-1, item);
   }

   public FiltersNested setNewFilterLike(int index, HTTPRouteFilter item) {
      return new FiltersNested(index, item);
   }

   public FiltersNested editFilter(int index) {
      if (this.filters.size() <= index) {
         throw new RuntimeException("Can't edit filters. Index exceeds size.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public FiltersNested editFirstFilter() {
      if (this.filters.size() == 0) {
         throw new RuntimeException("Can't edit first filters. The list is empty.");
      } else {
         return this.setNewFilterLike(0, this.buildFilter(0));
      }
   }

   public FiltersNested editLastFilter() {
      int index = this.filters.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last filters. The list is empty.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public FiltersNested editMatchingFilter(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.filters.size(); ++i) {
         if (predicate.test((HTTPRouteFilterBuilder)this.filters.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching filters. No match found.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public String getGroup() {
      return this.group;
   }

   public HTTPBackendRefFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public String getKind() {
      return this.kind;
   }

   public HTTPBackendRefFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public HTTPBackendRefFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public HTTPBackendRefFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public Integer getPort() {
      return this.port;
   }

   public HTTPBackendRefFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public Integer getWeight() {
      return this.weight;
   }

   public HTTPBackendRefFluent withWeight(Integer weight) {
      this.weight = weight;
      return this;
   }

   public boolean hasWeight() {
      return this.weight != null;
   }

   public HTTPBackendRefFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPBackendRefFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPBackendRefFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPBackendRefFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPBackendRefFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPBackendRefFluent that = (HTTPBackendRefFluent)o;
            if (!Objects.equals(this.filters, that.filters)) {
               return false;
            } else if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
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
      return Objects.hash(new Object[]{this.filters, this.group, this.kind, this.name, this.namespace, this.port, this.weight, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.filters != null && !this.filters.isEmpty()) {
         sb.append("filters:");
         sb.append(this.filters + ",");
      }

      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
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

   public class FiltersNested extends HTTPRouteFilterFluent implements Nested {
      HTTPRouteFilterBuilder builder;
      int index;

      FiltersNested(int index, HTTPRouteFilter item) {
         this.index = index;
         this.builder = new HTTPRouteFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPBackendRefFluent.this.setToFilters(this.index, this.builder.build());
      }

      public Object endFilter() {
         return this.and();
      }
   }
}
