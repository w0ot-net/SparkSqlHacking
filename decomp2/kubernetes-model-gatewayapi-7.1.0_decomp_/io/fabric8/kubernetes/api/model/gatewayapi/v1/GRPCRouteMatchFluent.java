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
import java.util.Optional;
import java.util.function.Predicate;

public class GRPCRouteMatchFluent extends BaseFluent {
   private ArrayList headers = new ArrayList();
   private GRPCMethodMatchBuilder method;
   private Map additionalProperties;

   public GRPCRouteMatchFluent() {
   }

   public GRPCRouteMatchFluent(GRPCRouteMatch instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GRPCRouteMatch instance) {
      instance = instance != null ? instance : new GRPCRouteMatch();
      if (instance != null) {
         this.withHeaders(instance.getHeaders());
         this.withMethod(instance.getMethod());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GRPCRouteMatchFluent addToHeaders(int index, GRPCHeaderMatch item) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      GRPCHeaderMatchBuilder builder = new GRPCHeaderMatchBuilder(item);
      if (index >= 0 && index < this.headers.size()) {
         this._visitables.get("headers").add(index, builder);
         this.headers.add(index, builder);
      } else {
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public GRPCRouteMatchFluent setToHeaders(int index, GRPCHeaderMatch item) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      GRPCHeaderMatchBuilder builder = new GRPCHeaderMatchBuilder(item);
      if (index >= 0 && index < this.headers.size()) {
         this._visitables.get("headers").set(index, builder);
         this.headers.set(index, builder);
      } else {
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public GRPCRouteMatchFluent addToHeaders(GRPCHeaderMatch... items) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      for(GRPCHeaderMatch item : items) {
         GRPCHeaderMatchBuilder builder = new GRPCHeaderMatchBuilder(item);
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public GRPCRouteMatchFluent addAllToHeaders(Collection items) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      for(GRPCHeaderMatch item : items) {
         GRPCHeaderMatchBuilder builder = new GRPCHeaderMatchBuilder(item);
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public GRPCRouteMatchFluent removeFromHeaders(GRPCHeaderMatch... items) {
      if (this.headers == null) {
         return this;
      } else {
         for(GRPCHeaderMatch item : items) {
            GRPCHeaderMatchBuilder builder = new GRPCHeaderMatchBuilder(item);
            this._visitables.get("headers").remove(builder);
            this.headers.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteMatchFluent removeAllFromHeaders(Collection items) {
      if (this.headers == null) {
         return this;
      } else {
         for(GRPCHeaderMatch item : items) {
            GRPCHeaderMatchBuilder builder = new GRPCHeaderMatchBuilder(item);
            this._visitables.get("headers").remove(builder);
            this.headers.remove(builder);
         }

         return this;
      }
   }

   public GRPCRouteMatchFluent removeMatchingFromHeaders(Predicate predicate) {
      if (this.headers == null) {
         return this;
      } else {
         Iterator<GRPCHeaderMatchBuilder> each = this.headers.iterator();
         List visitables = this._visitables.get("headers");

         while(each.hasNext()) {
            GRPCHeaderMatchBuilder builder = (GRPCHeaderMatchBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildHeaders() {
      return this.headers != null ? build(this.headers) : null;
   }

   public GRPCHeaderMatch buildHeader(int index) {
      return ((GRPCHeaderMatchBuilder)this.headers.get(index)).build();
   }

   public GRPCHeaderMatch buildFirstHeader() {
      return ((GRPCHeaderMatchBuilder)this.headers.get(0)).build();
   }

   public GRPCHeaderMatch buildLastHeader() {
      return ((GRPCHeaderMatchBuilder)this.headers.get(this.headers.size() - 1)).build();
   }

   public GRPCHeaderMatch buildMatchingHeader(Predicate predicate) {
      for(GRPCHeaderMatchBuilder item : this.headers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingHeader(Predicate predicate) {
      for(GRPCHeaderMatchBuilder item : this.headers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GRPCRouteMatchFluent withHeaders(List headers) {
      if (this.headers != null) {
         this._visitables.get("headers").clear();
      }

      if (headers != null) {
         this.headers = new ArrayList();

         for(GRPCHeaderMatch item : headers) {
            this.addToHeaders(item);
         }
      } else {
         this.headers = null;
      }

      return this;
   }

   public GRPCRouteMatchFluent withHeaders(GRPCHeaderMatch... headers) {
      if (this.headers != null) {
         this.headers.clear();
         this._visitables.remove("headers");
      }

      if (headers != null) {
         for(GRPCHeaderMatch item : headers) {
            this.addToHeaders(item);
         }
      }

      return this;
   }

   public boolean hasHeaders() {
      return this.headers != null && !this.headers.isEmpty();
   }

   public GRPCRouteMatchFluent addNewHeader(String name, String type, String value) {
      return this.addToHeaders(new GRPCHeaderMatch(name, type, value));
   }

   public HeadersNested addNewHeader() {
      return new HeadersNested(-1, (GRPCHeaderMatch)null);
   }

   public HeadersNested addNewHeaderLike(GRPCHeaderMatch item) {
      return new HeadersNested(-1, item);
   }

   public HeadersNested setNewHeaderLike(int index, GRPCHeaderMatch item) {
      return new HeadersNested(index, item);
   }

   public HeadersNested editHeader(int index) {
      if (this.headers.size() <= index) {
         throw new RuntimeException("Can't edit headers. Index exceeds size.");
      } else {
         return this.setNewHeaderLike(index, this.buildHeader(index));
      }
   }

   public HeadersNested editFirstHeader() {
      if (this.headers.size() == 0) {
         throw new RuntimeException("Can't edit first headers. The list is empty.");
      } else {
         return this.setNewHeaderLike(0, this.buildHeader(0));
      }
   }

   public HeadersNested editLastHeader() {
      int index = this.headers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last headers. The list is empty.");
      } else {
         return this.setNewHeaderLike(index, this.buildHeader(index));
      }
   }

   public HeadersNested editMatchingHeader(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.headers.size(); ++i) {
         if (predicate.test((GRPCHeaderMatchBuilder)this.headers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching headers. No match found.");
      } else {
         return this.setNewHeaderLike(index, this.buildHeader(index));
      }
   }

   public GRPCMethodMatch buildMethod() {
      return this.method != null ? this.method.build() : null;
   }

   public GRPCRouteMatchFluent withMethod(GRPCMethodMatch method) {
      this._visitables.remove("method");
      if (method != null) {
         this.method = new GRPCMethodMatchBuilder(method);
         this._visitables.get("method").add(this.method);
      } else {
         this.method = null;
         this._visitables.get("method").remove(this.method);
      }

      return this;
   }

   public boolean hasMethod() {
      return this.method != null;
   }

   public GRPCRouteMatchFluent withNewMethod(String method, String service, String type) {
      return this.withMethod(new GRPCMethodMatch(method, service, type));
   }

   public MethodNested withNewMethod() {
      return new MethodNested((GRPCMethodMatch)null);
   }

   public MethodNested withNewMethodLike(GRPCMethodMatch item) {
      return new MethodNested(item);
   }

   public MethodNested editMethod() {
      return this.withNewMethodLike((GRPCMethodMatch)Optional.ofNullable(this.buildMethod()).orElse((Object)null));
   }

   public MethodNested editOrNewMethod() {
      return this.withNewMethodLike((GRPCMethodMatch)Optional.ofNullable(this.buildMethod()).orElse((new GRPCMethodMatchBuilder()).build()));
   }

   public MethodNested editOrNewMethodLike(GRPCMethodMatch item) {
      return this.withNewMethodLike((GRPCMethodMatch)Optional.ofNullable(this.buildMethod()).orElse(item));
   }

   public GRPCRouteMatchFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GRPCRouteMatchFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GRPCRouteMatchFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GRPCRouteMatchFluent removeFromAdditionalProperties(Map map) {
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

   public GRPCRouteMatchFluent withAdditionalProperties(Map additionalProperties) {
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
            GRPCRouteMatchFluent that = (GRPCRouteMatchFluent)o;
            if (!Objects.equals(this.headers, that.headers)) {
               return false;
            } else if (!Objects.equals(this.method, that.method)) {
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
      return Objects.hash(new Object[]{this.headers, this.method, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.headers != null && !this.headers.isEmpty()) {
         sb.append("headers:");
         sb.append(this.headers + ",");
      }

      if (this.method != null) {
         sb.append("method:");
         sb.append(this.method + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class HeadersNested extends GRPCHeaderMatchFluent implements Nested {
      GRPCHeaderMatchBuilder builder;
      int index;

      HeadersNested(int index, GRPCHeaderMatch item) {
         this.index = index;
         this.builder = new GRPCHeaderMatchBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteMatchFluent.this.setToHeaders(this.index, this.builder.build());
      }

      public Object endHeader() {
         return this.and();
      }
   }

   public class MethodNested extends GRPCMethodMatchFluent implements Nested {
      GRPCMethodMatchBuilder builder;

      MethodNested(GRPCMethodMatch item) {
         this.builder = new GRPCMethodMatchBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteMatchFluent.this.withMethod(this.builder.build());
      }

      public Object endMethod() {
         return this.and();
      }
   }
}
