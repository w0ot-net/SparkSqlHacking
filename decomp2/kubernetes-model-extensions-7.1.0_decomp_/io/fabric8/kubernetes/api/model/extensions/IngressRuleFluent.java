package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IngressRuleFluent extends BaseFluent {
   private String host;
   private HTTPIngressRuleValueBuilder http;
   private Map additionalProperties;

   public IngressRuleFluent() {
   }

   public IngressRuleFluent(IngressRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressRule instance) {
      instance = instance != null ? instance : new IngressRule();
      if (instance != null) {
         this.withHost(instance.getHost());
         this.withHttp(instance.getHttp());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHost() {
      return this.host;
   }

   public IngressRuleFluent withHost(String host) {
      this.host = host;
      return this;
   }

   public boolean hasHost() {
      return this.host != null;
   }

   public HTTPIngressRuleValue buildHttp() {
      return this.http != null ? this.http.build() : null;
   }

   public IngressRuleFluent withHttp(HTTPIngressRuleValue http) {
      this._visitables.remove("http");
      if (http != null) {
         this.http = new HTTPIngressRuleValueBuilder(http);
         this._visitables.get("http").add(this.http);
      } else {
         this.http = null;
         this._visitables.get("http").remove(this.http);
      }

      return this;
   }

   public boolean hasHttp() {
      return this.http != null;
   }

   public HttpNested withNewHttp() {
      return new HttpNested((HTTPIngressRuleValue)null);
   }

   public HttpNested withNewHttpLike(HTTPIngressRuleValue item) {
      return new HttpNested(item);
   }

   public HttpNested editHttp() {
      return this.withNewHttpLike((HTTPIngressRuleValue)Optional.ofNullable(this.buildHttp()).orElse((Object)null));
   }

   public HttpNested editOrNewHttp() {
      return this.withNewHttpLike((HTTPIngressRuleValue)Optional.ofNullable(this.buildHttp()).orElse((new HTTPIngressRuleValueBuilder()).build()));
   }

   public HttpNested editOrNewHttpLike(HTTPIngressRuleValue item) {
      return this.withNewHttpLike((HTTPIngressRuleValue)Optional.ofNullable(this.buildHttp()).orElse(item));
   }

   public IngressRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressRuleFluent removeFromAdditionalProperties(Map map) {
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

   public IngressRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressRuleFluent that = (IngressRuleFluent)o;
            if (!Objects.equals(this.host, that.host)) {
               return false;
            } else if (!Objects.equals(this.http, that.http)) {
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
      return Objects.hash(new Object[]{this.host, this.http, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.host != null) {
         sb.append("host:");
         sb.append(this.host + ",");
      }

      if (this.http != null) {
         sb.append("http:");
         sb.append(this.http + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class HttpNested extends HTTPIngressRuleValueFluent implements Nested {
      HTTPIngressRuleValueBuilder builder;

      HttpNested(HTTPIngressRuleValue item) {
         this.builder = new HTTPIngressRuleValueBuilder(this, item);
      }

      public Object and() {
         return IngressRuleFluent.this.withHttp(this.builder.build());
      }

      public Object endHttp() {
         return this.and();
      }
   }
}
