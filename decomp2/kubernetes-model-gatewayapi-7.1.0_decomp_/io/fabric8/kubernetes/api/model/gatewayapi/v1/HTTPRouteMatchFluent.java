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

public class HTTPRouteMatchFluent extends BaseFluent {
   private ArrayList headers = new ArrayList();
   private String method;
   private HTTPPathMatchBuilder path;
   private ArrayList queryParams = new ArrayList();
   private Map additionalProperties;

   public HTTPRouteMatchFluent() {
   }

   public HTTPRouteMatchFluent(HTTPRouteMatch instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRouteMatch instance) {
      instance = instance != null ? instance : new HTTPRouteMatch();
      if (instance != null) {
         this.withHeaders(instance.getHeaders());
         this.withMethod(instance.getMethod());
         this.withPath(instance.getPath());
         this.withQueryParams(instance.getQueryParams());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HTTPRouteMatchFluent addToHeaders(int index, HTTPHeaderMatch item) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      HTTPHeaderMatchBuilder builder = new HTTPHeaderMatchBuilder(item);
      if (index >= 0 && index < this.headers.size()) {
         this._visitables.get("headers").add(index, builder);
         this.headers.add(index, builder);
      } else {
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent setToHeaders(int index, HTTPHeaderMatch item) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      HTTPHeaderMatchBuilder builder = new HTTPHeaderMatchBuilder(item);
      if (index >= 0 && index < this.headers.size()) {
         this._visitables.get("headers").set(index, builder);
         this.headers.set(index, builder);
      } else {
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent addToHeaders(HTTPHeaderMatch... items) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      for(HTTPHeaderMatch item : items) {
         HTTPHeaderMatchBuilder builder = new HTTPHeaderMatchBuilder(item);
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent addAllToHeaders(Collection items) {
      if (this.headers == null) {
         this.headers = new ArrayList();
      }

      for(HTTPHeaderMatch item : items) {
         HTTPHeaderMatchBuilder builder = new HTTPHeaderMatchBuilder(item);
         this._visitables.get("headers").add(builder);
         this.headers.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent removeFromHeaders(HTTPHeaderMatch... items) {
      if (this.headers == null) {
         return this;
      } else {
         for(HTTPHeaderMatch item : items) {
            HTTPHeaderMatchBuilder builder = new HTTPHeaderMatchBuilder(item);
            this._visitables.get("headers").remove(builder);
            this.headers.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteMatchFluent removeAllFromHeaders(Collection items) {
      if (this.headers == null) {
         return this;
      } else {
         for(HTTPHeaderMatch item : items) {
            HTTPHeaderMatchBuilder builder = new HTTPHeaderMatchBuilder(item);
            this._visitables.get("headers").remove(builder);
            this.headers.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteMatchFluent removeMatchingFromHeaders(Predicate predicate) {
      if (this.headers == null) {
         return this;
      } else {
         Iterator<HTTPHeaderMatchBuilder> each = this.headers.iterator();
         List visitables = this._visitables.get("headers");

         while(each.hasNext()) {
            HTTPHeaderMatchBuilder builder = (HTTPHeaderMatchBuilder)each.next();
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

   public HTTPHeaderMatch buildHeader(int index) {
      return ((HTTPHeaderMatchBuilder)this.headers.get(index)).build();
   }

   public HTTPHeaderMatch buildFirstHeader() {
      return ((HTTPHeaderMatchBuilder)this.headers.get(0)).build();
   }

   public HTTPHeaderMatch buildLastHeader() {
      return ((HTTPHeaderMatchBuilder)this.headers.get(this.headers.size() - 1)).build();
   }

   public HTTPHeaderMatch buildMatchingHeader(Predicate predicate) {
      for(HTTPHeaderMatchBuilder item : this.headers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingHeader(Predicate predicate) {
      for(HTTPHeaderMatchBuilder item : this.headers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPRouteMatchFluent withHeaders(List headers) {
      if (this.headers != null) {
         this._visitables.get("headers").clear();
      }

      if (headers != null) {
         this.headers = new ArrayList();

         for(HTTPHeaderMatch item : headers) {
            this.addToHeaders(item);
         }
      } else {
         this.headers = null;
      }

      return this;
   }

   public HTTPRouteMatchFluent withHeaders(HTTPHeaderMatch... headers) {
      if (this.headers != null) {
         this.headers.clear();
         this._visitables.remove("headers");
      }

      if (headers != null) {
         for(HTTPHeaderMatch item : headers) {
            this.addToHeaders(item);
         }
      }

      return this;
   }

   public boolean hasHeaders() {
      return this.headers != null && !this.headers.isEmpty();
   }

   public HTTPRouteMatchFluent addNewHeader(String name, String type, String value) {
      return this.addToHeaders(new HTTPHeaderMatch(name, type, value));
   }

   public HeadersNested addNewHeader() {
      return new HeadersNested(-1, (HTTPHeaderMatch)null);
   }

   public HeadersNested addNewHeaderLike(HTTPHeaderMatch item) {
      return new HeadersNested(-1, item);
   }

   public HeadersNested setNewHeaderLike(int index, HTTPHeaderMatch item) {
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
         if (predicate.test((HTTPHeaderMatchBuilder)this.headers.get(i))) {
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

   public String getMethod() {
      return this.method;
   }

   public HTTPRouteMatchFluent withMethod(String method) {
      this.method = method;
      return this;
   }

   public boolean hasMethod() {
      return this.method != null;
   }

   public HTTPPathMatch buildPath() {
      return this.path != null ? this.path.build() : null;
   }

   public HTTPRouteMatchFluent withPath(HTTPPathMatch path) {
      this._visitables.remove("path");
      if (path != null) {
         this.path = new HTTPPathMatchBuilder(path);
         this._visitables.get("path").add(this.path);
      } else {
         this.path = null;
         this._visitables.get("path").remove(this.path);
      }

      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public HTTPRouteMatchFluent withNewPath(String type, String value) {
      return this.withPath(new HTTPPathMatch(type, value));
   }

   public PathNested withNewPath() {
      return new PathNested((HTTPPathMatch)null);
   }

   public PathNested withNewPathLike(HTTPPathMatch item) {
      return new PathNested(item);
   }

   public PathNested editPath() {
      return this.withNewPathLike((HTTPPathMatch)Optional.ofNullable(this.buildPath()).orElse((Object)null));
   }

   public PathNested editOrNewPath() {
      return this.withNewPathLike((HTTPPathMatch)Optional.ofNullable(this.buildPath()).orElse((new HTTPPathMatchBuilder()).build()));
   }

   public PathNested editOrNewPathLike(HTTPPathMatch item) {
      return this.withNewPathLike((HTTPPathMatch)Optional.ofNullable(this.buildPath()).orElse(item));
   }

   public HTTPRouteMatchFluent addToQueryParams(int index, HTTPQueryParamMatch item) {
      if (this.queryParams == null) {
         this.queryParams = new ArrayList();
      }

      HTTPQueryParamMatchBuilder builder = new HTTPQueryParamMatchBuilder(item);
      if (index >= 0 && index < this.queryParams.size()) {
         this._visitables.get("queryParams").add(index, builder);
         this.queryParams.add(index, builder);
      } else {
         this._visitables.get("queryParams").add(builder);
         this.queryParams.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent setToQueryParams(int index, HTTPQueryParamMatch item) {
      if (this.queryParams == null) {
         this.queryParams = new ArrayList();
      }

      HTTPQueryParamMatchBuilder builder = new HTTPQueryParamMatchBuilder(item);
      if (index >= 0 && index < this.queryParams.size()) {
         this._visitables.get("queryParams").set(index, builder);
         this.queryParams.set(index, builder);
      } else {
         this._visitables.get("queryParams").add(builder);
         this.queryParams.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent addToQueryParams(HTTPQueryParamMatch... items) {
      if (this.queryParams == null) {
         this.queryParams = new ArrayList();
      }

      for(HTTPQueryParamMatch item : items) {
         HTTPQueryParamMatchBuilder builder = new HTTPQueryParamMatchBuilder(item);
         this._visitables.get("queryParams").add(builder);
         this.queryParams.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent addAllToQueryParams(Collection items) {
      if (this.queryParams == null) {
         this.queryParams = new ArrayList();
      }

      for(HTTPQueryParamMatch item : items) {
         HTTPQueryParamMatchBuilder builder = new HTTPQueryParamMatchBuilder(item);
         this._visitables.get("queryParams").add(builder);
         this.queryParams.add(builder);
      }

      return this;
   }

   public HTTPRouteMatchFluent removeFromQueryParams(HTTPQueryParamMatch... items) {
      if (this.queryParams == null) {
         return this;
      } else {
         for(HTTPQueryParamMatch item : items) {
            HTTPQueryParamMatchBuilder builder = new HTTPQueryParamMatchBuilder(item);
            this._visitables.get("queryParams").remove(builder);
            this.queryParams.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteMatchFluent removeAllFromQueryParams(Collection items) {
      if (this.queryParams == null) {
         return this;
      } else {
         for(HTTPQueryParamMatch item : items) {
            HTTPQueryParamMatchBuilder builder = new HTTPQueryParamMatchBuilder(item);
            this._visitables.get("queryParams").remove(builder);
            this.queryParams.remove(builder);
         }

         return this;
      }
   }

   public HTTPRouteMatchFluent removeMatchingFromQueryParams(Predicate predicate) {
      if (this.queryParams == null) {
         return this;
      } else {
         Iterator<HTTPQueryParamMatchBuilder> each = this.queryParams.iterator();
         List visitables = this._visitables.get("queryParams");

         while(each.hasNext()) {
            HTTPQueryParamMatchBuilder builder = (HTTPQueryParamMatchBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildQueryParams() {
      return this.queryParams != null ? build(this.queryParams) : null;
   }

   public HTTPQueryParamMatch buildQueryParam(int index) {
      return ((HTTPQueryParamMatchBuilder)this.queryParams.get(index)).build();
   }

   public HTTPQueryParamMatch buildFirstQueryParam() {
      return ((HTTPQueryParamMatchBuilder)this.queryParams.get(0)).build();
   }

   public HTTPQueryParamMatch buildLastQueryParam() {
      return ((HTTPQueryParamMatchBuilder)this.queryParams.get(this.queryParams.size() - 1)).build();
   }

   public HTTPQueryParamMatch buildMatchingQueryParam(Predicate predicate) {
      for(HTTPQueryParamMatchBuilder item : this.queryParams) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingQueryParam(Predicate predicate) {
      for(HTTPQueryParamMatchBuilder item : this.queryParams) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPRouteMatchFluent withQueryParams(List queryParams) {
      if (this.queryParams != null) {
         this._visitables.get("queryParams").clear();
      }

      if (queryParams != null) {
         this.queryParams = new ArrayList();

         for(HTTPQueryParamMatch item : queryParams) {
            this.addToQueryParams(item);
         }
      } else {
         this.queryParams = null;
      }

      return this;
   }

   public HTTPRouteMatchFluent withQueryParams(HTTPQueryParamMatch... queryParams) {
      if (this.queryParams != null) {
         this.queryParams.clear();
         this._visitables.remove("queryParams");
      }

      if (queryParams != null) {
         for(HTTPQueryParamMatch item : queryParams) {
            this.addToQueryParams(item);
         }
      }

      return this;
   }

   public boolean hasQueryParams() {
      return this.queryParams != null && !this.queryParams.isEmpty();
   }

   public HTTPRouteMatchFluent addNewQueryParam(String name, String type, String value) {
      return this.addToQueryParams(new HTTPQueryParamMatch(name, type, value));
   }

   public QueryParamsNested addNewQueryParam() {
      return new QueryParamsNested(-1, (HTTPQueryParamMatch)null);
   }

   public QueryParamsNested addNewQueryParamLike(HTTPQueryParamMatch item) {
      return new QueryParamsNested(-1, item);
   }

   public QueryParamsNested setNewQueryParamLike(int index, HTTPQueryParamMatch item) {
      return new QueryParamsNested(index, item);
   }

   public QueryParamsNested editQueryParam(int index) {
      if (this.queryParams.size() <= index) {
         throw new RuntimeException("Can't edit queryParams. Index exceeds size.");
      } else {
         return this.setNewQueryParamLike(index, this.buildQueryParam(index));
      }
   }

   public QueryParamsNested editFirstQueryParam() {
      if (this.queryParams.size() == 0) {
         throw new RuntimeException("Can't edit first queryParams. The list is empty.");
      } else {
         return this.setNewQueryParamLike(0, this.buildQueryParam(0));
      }
   }

   public QueryParamsNested editLastQueryParam() {
      int index = this.queryParams.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last queryParams. The list is empty.");
      } else {
         return this.setNewQueryParamLike(index, this.buildQueryParam(index));
      }
   }

   public QueryParamsNested editMatchingQueryParam(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.queryParams.size(); ++i) {
         if (predicate.test((HTTPQueryParamMatchBuilder)this.queryParams.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching queryParams. No match found.");
      } else {
         return this.setNewQueryParamLike(index, this.buildQueryParam(index));
      }
   }

   public HTTPRouteMatchFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRouteMatchFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRouteMatchFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRouteMatchFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPRouteMatchFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPRouteMatchFluent that = (HTTPRouteMatchFluent)o;
            if (!Objects.equals(this.headers, that.headers)) {
               return false;
            } else if (!Objects.equals(this.method, that.method)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.queryParams, that.queryParams)) {
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
      return Objects.hash(new Object[]{this.headers, this.method, this.path, this.queryParams, this.additionalProperties, super.hashCode()});
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

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.queryParams != null && !this.queryParams.isEmpty()) {
         sb.append("queryParams:");
         sb.append(this.queryParams + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class HeadersNested extends HTTPHeaderMatchFluent implements Nested {
      HTTPHeaderMatchBuilder builder;
      int index;

      HeadersNested(int index, HTTPHeaderMatch item) {
         this.index = index;
         this.builder = new HTTPHeaderMatchBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteMatchFluent.this.setToHeaders(this.index, this.builder.build());
      }

      public Object endHeader() {
         return this.and();
      }
   }

   public class PathNested extends HTTPPathMatchFluent implements Nested {
      HTTPPathMatchBuilder builder;

      PathNested(HTTPPathMatch item) {
         this.builder = new HTTPPathMatchBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteMatchFluent.this.withPath(this.builder.build());
      }

      public Object endPath() {
         return this.and();
      }
   }

   public class QueryParamsNested extends HTTPQueryParamMatchFluent implements Nested {
      HTTPQueryParamMatchBuilder builder;
      int index;

      QueryParamsNested(int index, HTTPQueryParamMatch item) {
         this.index = index;
         this.builder = new HTTPQueryParamMatchBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteMatchFluent.this.setToQueryParams(this.index, this.builder.build());
      }

      public Object endQueryParam() {
         return this.and();
      }
   }
}
