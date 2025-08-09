package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class WebhookClientConfigFluent extends BaseFluent {
   private String caBundle;
   private ServiceReferenceBuilder service;
   private String url;
   private Map additionalProperties;

   public WebhookClientConfigFluent() {
   }

   public WebhookClientConfigFluent(WebhookClientConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(WebhookClientConfig instance) {
      instance = instance != null ? instance : new WebhookClientConfig();
      if (instance != null) {
         this.withCaBundle(instance.getCaBundle());
         this.withService(instance.getService());
         this.withUrl(instance.getUrl());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getCaBundle() {
      return this.caBundle;
   }

   public WebhookClientConfigFluent withCaBundle(String caBundle) {
      this.caBundle = caBundle;
      return this;
   }

   public boolean hasCaBundle() {
      return this.caBundle != null;
   }

   public ServiceReference buildService() {
      return this.service != null ? this.service.build() : null;
   }

   public WebhookClientConfigFluent withService(ServiceReference service) {
      this._visitables.remove("service");
      if (service != null) {
         this.service = new ServiceReferenceBuilder(service);
         this._visitables.get("service").add(this.service);
      } else {
         this.service = null;
         this._visitables.get("service").remove(this.service);
      }

      return this;
   }

   public boolean hasService() {
      return this.service != null;
   }

   public WebhookClientConfigFluent withNewService(String name, String namespace, String path, Integer port) {
      return this.withService(new ServiceReference(name, namespace, path, port));
   }

   public ServiceNested withNewService() {
      return new ServiceNested((ServiceReference)null);
   }

   public ServiceNested withNewServiceLike(ServiceReference item) {
      return new ServiceNested(item);
   }

   public ServiceNested editService() {
      return this.withNewServiceLike((ServiceReference)Optional.ofNullable(this.buildService()).orElse((Object)null));
   }

   public ServiceNested editOrNewService() {
      return this.withNewServiceLike((ServiceReference)Optional.ofNullable(this.buildService()).orElse((new ServiceReferenceBuilder()).build()));
   }

   public ServiceNested editOrNewServiceLike(ServiceReference item) {
      return this.withNewServiceLike((ServiceReference)Optional.ofNullable(this.buildService()).orElse(item));
   }

   public String getUrl() {
      return this.url;
   }

   public WebhookClientConfigFluent withUrl(String url) {
      this.url = url;
      return this;
   }

   public boolean hasUrl() {
      return this.url != null;
   }

   public WebhookClientConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public WebhookClientConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public WebhookClientConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public WebhookClientConfigFluent removeFromAdditionalProperties(Map map) {
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

   public WebhookClientConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            WebhookClientConfigFluent that = (WebhookClientConfigFluent)o;
            if (!Objects.equals(this.caBundle, that.caBundle)) {
               return false;
            } else if (!Objects.equals(this.service, that.service)) {
               return false;
            } else if (!Objects.equals(this.url, that.url)) {
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
      return Objects.hash(new Object[]{this.caBundle, this.service, this.url, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.caBundle != null) {
         sb.append("caBundle:");
         sb.append(this.caBundle + ",");
      }

      if (this.service != null) {
         sb.append("service:");
         sb.append(this.service + ",");
      }

      if (this.url != null) {
         sb.append("url:");
         sb.append(this.url + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ServiceNested extends ServiceReferenceFluent implements Nested {
      ServiceReferenceBuilder builder;

      ServiceNested(ServiceReference item) {
         this.builder = new ServiceReferenceBuilder(this, item);
      }

      public Object and() {
         return WebhookClientConfigFluent.this.withService(this.builder.build());
      }

      public Object endService() {
         return this.and();
      }
   }
}
