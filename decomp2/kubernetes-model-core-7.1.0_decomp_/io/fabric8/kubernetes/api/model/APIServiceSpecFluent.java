package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class APIServiceSpecFluent extends BaseFluent {
   private String caBundle;
   private String group;
   private Integer groupPriorityMinimum;
   private Boolean insecureSkipTLSVerify;
   private ServiceReferenceBuilder service;
   private String version;
   private Integer versionPriority;
   private Map additionalProperties;

   public APIServiceSpecFluent() {
   }

   public APIServiceSpecFluent(APIServiceSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIServiceSpec instance) {
      instance = instance != null ? instance : new APIServiceSpec();
      if (instance != null) {
         this.withCaBundle(instance.getCaBundle());
         this.withGroup(instance.getGroup());
         this.withGroupPriorityMinimum(instance.getGroupPriorityMinimum());
         this.withInsecureSkipTLSVerify(instance.getInsecureSkipTLSVerify());
         this.withService(instance.getService());
         this.withVersion(instance.getVersion());
         this.withVersionPriority(instance.getVersionPriority());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getCaBundle() {
      return this.caBundle;
   }

   public APIServiceSpecFluent withCaBundle(String caBundle) {
      this.caBundle = caBundle;
      return this;
   }

   public boolean hasCaBundle() {
      return this.caBundle != null;
   }

   public String getGroup() {
      return this.group;
   }

   public APIServiceSpecFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public Integer getGroupPriorityMinimum() {
      return this.groupPriorityMinimum;
   }

   public APIServiceSpecFluent withGroupPriorityMinimum(Integer groupPriorityMinimum) {
      this.groupPriorityMinimum = groupPriorityMinimum;
      return this;
   }

   public boolean hasGroupPriorityMinimum() {
      return this.groupPriorityMinimum != null;
   }

   public Boolean getInsecureSkipTLSVerify() {
      return this.insecureSkipTLSVerify;
   }

   public APIServiceSpecFluent withInsecureSkipTLSVerify(Boolean insecureSkipTLSVerify) {
      this.insecureSkipTLSVerify = insecureSkipTLSVerify;
      return this;
   }

   public boolean hasInsecureSkipTLSVerify() {
      return this.insecureSkipTLSVerify != null;
   }

   public ServiceReference buildService() {
      return this.service != null ? this.service.build() : null;
   }

   public APIServiceSpecFluent withService(ServiceReference service) {
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

   public APIServiceSpecFluent withNewService(String name, String namespace, Integer port) {
      return this.withService(new ServiceReference(name, namespace, port));
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

   public String getVersion() {
      return this.version;
   }

   public APIServiceSpecFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public Integer getVersionPriority() {
      return this.versionPriority;
   }

   public APIServiceSpecFluent withVersionPriority(Integer versionPriority) {
      this.versionPriority = versionPriority;
      return this;
   }

   public boolean hasVersionPriority() {
      return this.versionPriority != null;
   }

   public APIServiceSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIServiceSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIServiceSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIServiceSpecFluent removeFromAdditionalProperties(Map map) {
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

   public APIServiceSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            APIServiceSpecFluent that = (APIServiceSpecFluent)o;
            if (!Objects.equals(this.caBundle, that.caBundle)) {
               return false;
            } else if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.groupPriorityMinimum, that.groupPriorityMinimum)) {
               return false;
            } else if (!Objects.equals(this.insecureSkipTLSVerify, that.insecureSkipTLSVerify)) {
               return false;
            } else if (!Objects.equals(this.service, that.service)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
               return false;
            } else if (!Objects.equals(this.versionPriority, that.versionPriority)) {
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
      return Objects.hash(new Object[]{this.caBundle, this.group, this.groupPriorityMinimum, this.insecureSkipTLSVerify, this.service, this.version, this.versionPriority, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.caBundle != null) {
         sb.append("caBundle:");
         sb.append(this.caBundle + ",");
      }

      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.groupPriorityMinimum != null) {
         sb.append("groupPriorityMinimum:");
         sb.append(this.groupPriorityMinimum + ",");
      }

      if (this.insecureSkipTLSVerify != null) {
         sb.append("insecureSkipTLSVerify:");
         sb.append(this.insecureSkipTLSVerify + ",");
      }

      if (this.service != null) {
         sb.append("service:");
         sb.append(this.service + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.versionPriority != null) {
         sb.append("versionPriority:");
         sb.append(this.versionPriority + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public APIServiceSpecFluent withInsecureSkipTLSVerify() {
      return this.withInsecureSkipTLSVerify(true);
   }

   public class ServiceNested extends ServiceReferenceFluent implements Nested {
      ServiceReferenceBuilder builder;

      ServiceNested(ServiceReference item) {
         this.builder = new ServiceReferenceBuilder(this, item);
      }

      public Object and() {
         return APIServiceSpecFluent.this.withService(this.builder.build());
      }

      public Object endService() {
         return this.and();
      }
   }
}
