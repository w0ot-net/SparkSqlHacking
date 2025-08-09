package io.fabric8.kubernetes.api.model.certificates.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ClusterTrustBundleSpecFluent extends BaseFluent {
   private String signerName;
   private String trustBundle;
   private Map additionalProperties;

   public ClusterTrustBundleSpecFluent() {
   }

   public ClusterTrustBundleSpecFluent(ClusterTrustBundleSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ClusterTrustBundleSpec instance) {
      instance = instance != null ? instance : new ClusterTrustBundleSpec();
      if (instance != null) {
         this.withSignerName(instance.getSignerName());
         this.withTrustBundle(instance.getTrustBundle());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getSignerName() {
      return this.signerName;
   }

   public ClusterTrustBundleSpecFluent withSignerName(String signerName) {
      this.signerName = signerName;
      return this;
   }

   public boolean hasSignerName() {
      return this.signerName != null;
   }

   public String getTrustBundle() {
      return this.trustBundle;
   }

   public ClusterTrustBundleSpecFluent withTrustBundle(String trustBundle) {
      this.trustBundle = trustBundle;
      return this;
   }

   public boolean hasTrustBundle() {
      return this.trustBundle != null;
   }

   public ClusterTrustBundleSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ClusterTrustBundleSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ClusterTrustBundleSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ClusterTrustBundleSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ClusterTrustBundleSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ClusterTrustBundleSpecFluent that = (ClusterTrustBundleSpecFluent)o;
            if (!Objects.equals(this.signerName, that.signerName)) {
               return false;
            } else if (!Objects.equals(this.trustBundle, that.trustBundle)) {
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
      return Objects.hash(new Object[]{this.signerName, this.trustBundle, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.signerName != null) {
         sb.append("signerName:");
         sb.append(this.signerName + ",");
      }

      if (this.trustBundle != null) {
         sb.append("trustBundle:");
         sb.append(this.trustBundle + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
