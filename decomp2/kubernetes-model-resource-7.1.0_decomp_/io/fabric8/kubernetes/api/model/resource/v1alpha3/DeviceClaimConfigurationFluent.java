package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class DeviceClaimConfigurationFluent extends BaseFluent {
   private OpaqueDeviceConfigurationBuilder opaque;
   private List requests = new ArrayList();
   private Map additionalProperties;

   public DeviceClaimConfigurationFluent() {
   }

   public DeviceClaimConfigurationFluent(DeviceClaimConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceClaimConfiguration instance) {
      instance = instance != null ? instance : new DeviceClaimConfiguration();
      if (instance != null) {
         this.withOpaque(instance.getOpaque());
         this.withRequests(instance.getRequests());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public OpaqueDeviceConfiguration buildOpaque() {
      return this.opaque != null ? this.opaque.build() : null;
   }

   public DeviceClaimConfigurationFluent withOpaque(OpaqueDeviceConfiguration opaque) {
      this._visitables.remove("opaque");
      if (opaque != null) {
         this.opaque = new OpaqueDeviceConfigurationBuilder(opaque);
         this._visitables.get("opaque").add(this.opaque);
      } else {
         this.opaque = null;
         this._visitables.get("opaque").remove(this.opaque);
      }

      return this;
   }

   public boolean hasOpaque() {
      return this.opaque != null;
   }

   public DeviceClaimConfigurationFluent withNewOpaque(String driver, Object parameters) {
      return this.withOpaque(new OpaqueDeviceConfiguration(driver, parameters));
   }

   public OpaqueNested withNewOpaque() {
      return new OpaqueNested((OpaqueDeviceConfiguration)null);
   }

   public OpaqueNested withNewOpaqueLike(OpaqueDeviceConfiguration item) {
      return new OpaqueNested(item);
   }

   public OpaqueNested editOpaque() {
      return this.withNewOpaqueLike((OpaqueDeviceConfiguration)Optional.ofNullable(this.buildOpaque()).orElse((Object)null));
   }

   public OpaqueNested editOrNewOpaque() {
      return this.withNewOpaqueLike((OpaqueDeviceConfiguration)Optional.ofNullable(this.buildOpaque()).orElse((new OpaqueDeviceConfigurationBuilder()).build()));
   }

   public OpaqueNested editOrNewOpaqueLike(OpaqueDeviceConfiguration item) {
      return this.withNewOpaqueLike((OpaqueDeviceConfiguration)Optional.ofNullable(this.buildOpaque()).orElse(item));
   }

   public DeviceClaimConfigurationFluent addToRequests(int index, String item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      this.requests.add(index, item);
      return this;
   }

   public DeviceClaimConfigurationFluent setToRequests(int index, String item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      this.requests.set(index, item);
      return this;
   }

   public DeviceClaimConfigurationFluent addToRequests(String... items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(String item : items) {
         this.requests.add(item);
      }

      return this;
   }

   public DeviceClaimConfigurationFluent addAllToRequests(Collection items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(String item : items) {
         this.requests.add(item);
      }

      return this;
   }

   public DeviceClaimConfigurationFluent removeFromRequests(String... items) {
      if (this.requests == null) {
         return this;
      } else {
         for(String item : items) {
            this.requests.remove(item);
         }

         return this;
      }
   }

   public DeviceClaimConfigurationFluent removeAllFromRequests(Collection items) {
      if (this.requests == null) {
         return this;
      } else {
         for(String item : items) {
            this.requests.remove(item);
         }

         return this;
      }
   }

   public List getRequests() {
      return this.requests;
   }

   public String getRequest(int index) {
      return (String)this.requests.get(index);
   }

   public String getFirstRequest() {
      return (String)this.requests.get(0);
   }

   public String getLastRequest() {
      return (String)this.requests.get(this.requests.size() - 1);
   }

   public String getMatchingRequest(Predicate predicate) {
      for(String item : this.requests) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingRequest(Predicate predicate) {
      for(String item : this.requests) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceClaimConfigurationFluent withRequests(List requests) {
      if (requests != null) {
         this.requests = new ArrayList();

         for(String item : requests) {
            this.addToRequests(item);
         }
      } else {
         this.requests = null;
      }

      return this;
   }

   public DeviceClaimConfigurationFluent withRequests(String... requests) {
      if (this.requests != null) {
         this.requests.clear();
         this._visitables.remove("requests");
      }

      if (requests != null) {
         for(String item : requests) {
            this.addToRequests(item);
         }
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null && !this.requests.isEmpty();
   }

   public DeviceClaimConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceClaimConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceClaimConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceClaimConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceClaimConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceClaimConfigurationFluent that = (DeviceClaimConfigurationFluent)o;
            if (!Objects.equals(this.opaque, that.opaque)) {
               return false;
            } else if (!Objects.equals(this.requests, that.requests)) {
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
      return Objects.hash(new Object[]{this.opaque, this.requests, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.opaque != null) {
         sb.append("opaque:");
         sb.append(this.opaque + ",");
      }

      if (this.requests != null && !this.requests.isEmpty()) {
         sb.append("requests:");
         sb.append(this.requests + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OpaqueNested extends OpaqueDeviceConfigurationFluent implements Nested {
      OpaqueDeviceConfigurationBuilder builder;

      OpaqueNested(OpaqueDeviceConfiguration item) {
         this.builder = new OpaqueDeviceConfigurationBuilder(this, item);
      }

      public Object and() {
         return DeviceClaimConfigurationFluent.this.withOpaque(this.builder.build());
      }

      public Object endOpaque() {
         return this.and();
      }
   }
}
