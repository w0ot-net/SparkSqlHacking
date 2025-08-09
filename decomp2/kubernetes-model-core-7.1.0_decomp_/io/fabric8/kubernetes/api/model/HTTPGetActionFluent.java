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
import java.util.Optional;
import java.util.function.Predicate;

public class HTTPGetActionFluent extends BaseFluent {
   private String host;
   private ArrayList httpHeaders = new ArrayList();
   private String path;
   private IntOrStringBuilder port;
   private String scheme;
   private Map additionalProperties;

   public HTTPGetActionFluent() {
   }

   public HTTPGetActionFluent(HTTPGetAction instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPGetAction instance) {
      instance = instance != null ? instance : new HTTPGetAction();
      if (instance != null) {
         this.withHost(instance.getHost());
         this.withHttpHeaders(instance.getHttpHeaders());
         this.withPath(instance.getPath());
         this.withPort(instance.getPort());
         this.withScheme(instance.getScheme());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHost() {
      return this.host;
   }

   public HTTPGetActionFluent withHost(String host) {
      this.host = host;
      return this;
   }

   public boolean hasHost() {
      return this.host != null;
   }

   public HTTPGetActionFluent addToHttpHeaders(int index, HTTPHeader item) {
      if (this.httpHeaders == null) {
         this.httpHeaders = new ArrayList();
      }

      HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
      if (index >= 0 && index < this.httpHeaders.size()) {
         this._visitables.get("httpHeaders").add(index, builder);
         this.httpHeaders.add(index, builder);
      } else {
         this._visitables.get("httpHeaders").add(builder);
         this.httpHeaders.add(builder);
      }

      return this;
   }

   public HTTPGetActionFluent setToHttpHeaders(int index, HTTPHeader item) {
      if (this.httpHeaders == null) {
         this.httpHeaders = new ArrayList();
      }

      HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
      if (index >= 0 && index < this.httpHeaders.size()) {
         this._visitables.get("httpHeaders").set(index, builder);
         this.httpHeaders.set(index, builder);
      } else {
         this._visitables.get("httpHeaders").add(builder);
         this.httpHeaders.add(builder);
      }

      return this;
   }

   public HTTPGetActionFluent addToHttpHeaders(HTTPHeader... items) {
      if (this.httpHeaders == null) {
         this.httpHeaders = new ArrayList();
      }

      for(HTTPHeader item : items) {
         HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
         this._visitables.get("httpHeaders").add(builder);
         this.httpHeaders.add(builder);
      }

      return this;
   }

   public HTTPGetActionFluent addAllToHttpHeaders(Collection items) {
      if (this.httpHeaders == null) {
         this.httpHeaders = new ArrayList();
      }

      for(HTTPHeader item : items) {
         HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
         this._visitables.get("httpHeaders").add(builder);
         this.httpHeaders.add(builder);
      }

      return this;
   }

   public HTTPGetActionFluent removeFromHttpHeaders(HTTPHeader... items) {
      if (this.httpHeaders == null) {
         return this;
      } else {
         for(HTTPHeader item : items) {
            HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
            this._visitables.get("httpHeaders").remove(builder);
            this.httpHeaders.remove(builder);
         }

         return this;
      }
   }

   public HTTPGetActionFluent removeAllFromHttpHeaders(Collection items) {
      if (this.httpHeaders == null) {
         return this;
      } else {
         for(HTTPHeader item : items) {
            HTTPHeaderBuilder builder = new HTTPHeaderBuilder(item);
            this._visitables.get("httpHeaders").remove(builder);
            this.httpHeaders.remove(builder);
         }

         return this;
      }
   }

   public HTTPGetActionFluent removeMatchingFromHttpHeaders(Predicate predicate) {
      if (this.httpHeaders == null) {
         return this;
      } else {
         Iterator<HTTPHeaderBuilder> each = this.httpHeaders.iterator();
         List visitables = this._visitables.get("httpHeaders");

         while(each.hasNext()) {
            HTTPHeaderBuilder builder = (HTTPHeaderBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildHttpHeaders() {
      return this.httpHeaders != null ? build(this.httpHeaders) : null;
   }

   public HTTPHeader buildHttpHeader(int index) {
      return ((HTTPHeaderBuilder)this.httpHeaders.get(index)).build();
   }

   public HTTPHeader buildFirstHttpHeader() {
      return ((HTTPHeaderBuilder)this.httpHeaders.get(0)).build();
   }

   public HTTPHeader buildLastHttpHeader() {
      return ((HTTPHeaderBuilder)this.httpHeaders.get(this.httpHeaders.size() - 1)).build();
   }

   public HTTPHeader buildMatchingHttpHeader(Predicate predicate) {
      for(HTTPHeaderBuilder item : this.httpHeaders) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingHttpHeader(Predicate predicate) {
      for(HTTPHeaderBuilder item : this.httpHeaders) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPGetActionFluent withHttpHeaders(List httpHeaders) {
      if (this.httpHeaders != null) {
         this._visitables.get("httpHeaders").clear();
      }

      if (httpHeaders != null) {
         this.httpHeaders = new ArrayList();

         for(HTTPHeader item : httpHeaders) {
            this.addToHttpHeaders(item);
         }
      } else {
         this.httpHeaders = null;
      }

      return this;
   }

   public HTTPGetActionFluent withHttpHeaders(HTTPHeader... httpHeaders) {
      if (this.httpHeaders != null) {
         this.httpHeaders.clear();
         this._visitables.remove("httpHeaders");
      }

      if (httpHeaders != null) {
         for(HTTPHeader item : httpHeaders) {
            this.addToHttpHeaders(item);
         }
      }

      return this;
   }

   public boolean hasHttpHeaders() {
      return this.httpHeaders != null && !this.httpHeaders.isEmpty();
   }

   public HTTPGetActionFluent addNewHttpHeader(String name, String value) {
      return this.addToHttpHeaders(new HTTPHeader(name, value));
   }

   public HttpHeadersNested addNewHttpHeader() {
      return new HttpHeadersNested(-1, (HTTPHeader)null);
   }

   public HttpHeadersNested addNewHttpHeaderLike(HTTPHeader item) {
      return new HttpHeadersNested(-1, item);
   }

   public HttpHeadersNested setNewHttpHeaderLike(int index, HTTPHeader item) {
      return new HttpHeadersNested(index, item);
   }

   public HttpHeadersNested editHttpHeader(int index) {
      if (this.httpHeaders.size() <= index) {
         throw new RuntimeException("Can't edit httpHeaders. Index exceeds size.");
      } else {
         return this.setNewHttpHeaderLike(index, this.buildHttpHeader(index));
      }
   }

   public HttpHeadersNested editFirstHttpHeader() {
      if (this.httpHeaders.size() == 0) {
         throw new RuntimeException("Can't edit first httpHeaders. The list is empty.");
      } else {
         return this.setNewHttpHeaderLike(0, this.buildHttpHeader(0));
      }
   }

   public HttpHeadersNested editLastHttpHeader() {
      int index = this.httpHeaders.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last httpHeaders. The list is empty.");
      } else {
         return this.setNewHttpHeaderLike(index, this.buildHttpHeader(index));
      }
   }

   public HttpHeadersNested editMatchingHttpHeader(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.httpHeaders.size(); ++i) {
         if (predicate.test((HTTPHeaderBuilder)this.httpHeaders.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching httpHeaders. No match found.");
      } else {
         return this.setNewHttpHeaderLike(index, this.buildHttpHeader(index));
      }
   }

   public String getPath() {
      return this.path;
   }

   public HTTPGetActionFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public IntOrString buildPort() {
      return this.port != null ? this.port.build() : null;
   }

   public HTTPGetActionFluent withPort(IntOrString port) {
      this._visitables.remove("port");
      if (port != null) {
         this.port = new IntOrStringBuilder(port);
         this._visitables.get("port").add(this.port);
      } else {
         this.port = null;
         this._visitables.get("port").remove(this.port);
      }

      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public HTTPGetActionFluent withNewPort(Object value) {
      return this.withPort(new IntOrString(value));
   }

   public PortNested withNewPort() {
      return new PortNested((IntOrString)null);
   }

   public PortNested withNewPortLike(IntOrString item) {
      return new PortNested(item);
   }

   public PortNested editPort() {
      return this.withNewPortLike((IntOrString)Optional.ofNullable(this.buildPort()).orElse((Object)null));
   }

   public PortNested editOrNewPort() {
      return this.withNewPortLike((IntOrString)Optional.ofNullable(this.buildPort()).orElse((new IntOrStringBuilder()).build()));
   }

   public PortNested editOrNewPortLike(IntOrString item) {
      return this.withNewPortLike((IntOrString)Optional.ofNullable(this.buildPort()).orElse(item));
   }

   public String getScheme() {
      return this.scheme;
   }

   public HTTPGetActionFluent withScheme(String scheme) {
      this.scheme = scheme;
      return this;
   }

   public boolean hasScheme() {
      return this.scheme != null;
   }

   public HTTPGetActionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPGetActionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPGetActionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPGetActionFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPGetActionFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPGetActionFluent that = (HTTPGetActionFluent)o;
            if (!Objects.equals(this.host, that.host)) {
               return false;
            } else if (!Objects.equals(this.httpHeaders, that.httpHeaders)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.scheme, that.scheme)) {
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
      return Objects.hash(new Object[]{this.host, this.httpHeaders, this.path, this.port, this.scheme, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.host != null) {
         sb.append("host:");
         sb.append(this.host + ",");
      }

      if (this.httpHeaders != null && !this.httpHeaders.isEmpty()) {
         sb.append("httpHeaders:");
         sb.append(this.httpHeaders + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.scheme != null) {
         sb.append("scheme:");
         sb.append(this.scheme + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class HttpHeadersNested extends HTTPHeaderFluent implements Nested {
      HTTPHeaderBuilder builder;
      int index;

      HttpHeadersNested(int index, HTTPHeader item) {
         this.index = index;
         this.builder = new HTTPHeaderBuilder(this, item);
      }

      public Object and() {
         return HTTPGetActionFluent.this.setToHttpHeaders(this.index, this.builder.build());
      }

      public Object endHttpHeader() {
         return this.and();
      }
   }

   public class PortNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      PortNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return HTTPGetActionFluent.this.withPort(this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
