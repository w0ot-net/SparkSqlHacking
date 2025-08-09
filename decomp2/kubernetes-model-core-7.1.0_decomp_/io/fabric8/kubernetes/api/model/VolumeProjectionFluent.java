package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VolumeProjectionFluent extends BaseFluent {
   private ClusterTrustBundleProjectionBuilder clusterTrustBundle;
   private ConfigMapProjectionBuilder configMap;
   private DownwardAPIProjectionBuilder downwardAPI;
   private SecretProjectionBuilder secret;
   private ServiceAccountTokenProjectionBuilder serviceAccountToken;
   private Map additionalProperties;

   public VolumeProjectionFluent() {
   }

   public VolumeProjectionFluent(VolumeProjection instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeProjection instance) {
      instance = instance != null ? instance : new VolumeProjection();
      if (instance != null) {
         this.withClusterTrustBundle(instance.getClusterTrustBundle());
         this.withConfigMap(instance.getConfigMap());
         this.withDownwardAPI(instance.getDownwardAPI());
         this.withSecret(instance.getSecret());
         this.withServiceAccountToken(instance.getServiceAccountToken());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ClusterTrustBundleProjection buildClusterTrustBundle() {
      return this.clusterTrustBundle != null ? this.clusterTrustBundle.build() : null;
   }

   public VolumeProjectionFluent withClusterTrustBundle(ClusterTrustBundleProjection clusterTrustBundle) {
      this._visitables.remove("clusterTrustBundle");
      if (clusterTrustBundle != null) {
         this.clusterTrustBundle = new ClusterTrustBundleProjectionBuilder(clusterTrustBundle);
         this._visitables.get("clusterTrustBundle").add(this.clusterTrustBundle);
      } else {
         this.clusterTrustBundle = null;
         this._visitables.get("clusterTrustBundle").remove(this.clusterTrustBundle);
      }

      return this;
   }

   public boolean hasClusterTrustBundle() {
      return this.clusterTrustBundle != null;
   }

   public ClusterTrustBundleNested withNewClusterTrustBundle() {
      return new ClusterTrustBundleNested((ClusterTrustBundleProjection)null);
   }

   public ClusterTrustBundleNested withNewClusterTrustBundleLike(ClusterTrustBundleProjection item) {
      return new ClusterTrustBundleNested(item);
   }

   public ClusterTrustBundleNested editClusterTrustBundle() {
      return this.withNewClusterTrustBundleLike((ClusterTrustBundleProjection)Optional.ofNullable(this.buildClusterTrustBundle()).orElse((Object)null));
   }

   public ClusterTrustBundleNested editOrNewClusterTrustBundle() {
      return this.withNewClusterTrustBundleLike((ClusterTrustBundleProjection)Optional.ofNullable(this.buildClusterTrustBundle()).orElse((new ClusterTrustBundleProjectionBuilder()).build()));
   }

   public ClusterTrustBundleNested editOrNewClusterTrustBundleLike(ClusterTrustBundleProjection item) {
      return this.withNewClusterTrustBundleLike((ClusterTrustBundleProjection)Optional.ofNullable(this.buildClusterTrustBundle()).orElse(item));
   }

   public ConfigMapProjection buildConfigMap() {
      return this.configMap != null ? this.configMap.build() : null;
   }

   public VolumeProjectionFluent withConfigMap(ConfigMapProjection configMap) {
      this._visitables.remove("configMap");
      if (configMap != null) {
         this.configMap = new ConfigMapProjectionBuilder(configMap);
         this._visitables.get("configMap").add(this.configMap);
      } else {
         this.configMap = null;
         this._visitables.get("configMap").remove(this.configMap);
      }

      return this;
   }

   public boolean hasConfigMap() {
      return this.configMap != null;
   }

   public ConfigMapNested withNewConfigMap() {
      return new ConfigMapNested((ConfigMapProjection)null);
   }

   public ConfigMapNested withNewConfigMapLike(ConfigMapProjection item) {
      return new ConfigMapNested(item);
   }

   public ConfigMapNested editConfigMap() {
      return this.withNewConfigMapLike((ConfigMapProjection)Optional.ofNullable(this.buildConfigMap()).orElse((Object)null));
   }

   public ConfigMapNested editOrNewConfigMap() {
      return this.withNewConfigMapLike((ConfigMapProjection)Optional.ofNullable(this.buildConfigMap()).orElse((new ConfigMapProjectionBuilder()).build()));
   }

   public ConfigMapNested editOrNewConfigMapLike(ConfigMapProjection item) {
      return this.withNewConfigMapLike((ConfigMapProjection)Optional.ofNullable(this.buildConfigMap()).orElse(item));
   }

   public DownwardAPIProjection buildDownwardAPI() {
      return this.downwardAPI != null ? this.downwardAPI.build() : null;
   }

   public VolumeProjectionFluent withDownwardAPI(DownwardAPIProjection downwardAPI) {
      this._visitables.remove("downwardAPI");
      if (downwardAPI != null) {
         this.downwardAPI = new DownwardAPIProjectionBuilder(downwardAPI);
         this._visitables.get("downwardAPI").add(this.downwardAPI);
      } else {
         this.downwardAPI = null;
         this._visitables.get("downwardAPI").remove(this.downwardAPI);
      }

      return this;
   }

   public boolean hasDownwardAPI() {
      return this.downwardAPI != null;
   }

   public DownwardAPINested withNewDownwardAPI() {
      return new DownwardAPINested((DownwardAPIProjection)null);
   }

   public DownwardAPINested withNewDownwardAPILike(DownwardAPIProjection item) {
      return new DownwardAPINested(item);
   }

   public DownwardAPINested editDownwardAPI() {
      return this.withNewDownwardAPILike((DownwardAPIProjection)Optional.ofNullable(this.buildDownwardAPI()).orElse((Object)null));
   }

   public DownwardAPINested editOrNewDownwardAPI() {
      return this.withNewDownwardAPILike((DownwardAPIProjection)Optional.ofNullable(this.buildDownwardAPI()).orElse((new DownwardAPIProjectionBuilder()).build()));
   }

   public DownwardAPINested editOrNewDownwardAPILike(DownwardAPIProjection item) {
      return this.withNewDownwardAPILike((DownwardAPIProjection)Optional.ofNullable(this.buildDownwardAPI()).orElse(item));
   }

   public SecretProjection buildSecret() {
      return this.secret != null ? this.secret.build() : null;
   }

   public VolumeProjectionFluent withSecret(SecretProjection secret) {
      this._visitables.remove("secret");
      if (secret != null) {
         this.secret = new SecretProjectionBuilder(secret);
         this._visitables.get("secret").add(this.secret);
      } else {
         this.secret = null;
         this._visitables.get("secret").remove(this.secret);
      }

      return this;
   }

   public boolean hasSecret() {
      return this.secret != null;
   }

   public SecretNested withNewSecret() {
      return new SecretNested((SecretProjection)null);
   }

   public SecretNested withNewSecretLike(SecretProjection item) {
      return new SecretNested(item);
   }

   public SecretNested editSecret() {
      return this.withNewSecretLike((SecretProjection)Optional.ofNullable(this.buildSecret()).orElse((Object)null));
   }

   public SecretNested editOrNewSecret() {
      return this.withNewSecretLike((SecretProjection)Optional.ofNullable(this.buildSecret()).orElse((new SecretProjectionBuilder()).build()));
   }

   public SecretNested editOrNewSecretLike(SecretProjection item) {
      return this.withNewSecretLike((SecretProjection)Optional.ofNullable(this.buildSecret()).orElse(item));
   }

   public ServiceAccountTokenProjection buildServiceAccountToken() {
      return this.serviceAccountToken != null ? this.serviceAccountToken.build() : null;
   }

   public VolumeProjectionFluent withServiceAccountToken(ServiceAccountTokenProjection serviceAccountToken) {
      this._visitables.remove("serviceAccountToken");
      if (serviceAccountToken != null) {
         this.serviceAccountToken = new ServiceAccountTokenProjectionBuilder(serviceAccountToken);
         this._visitables.get("serviceAccountToken").add(this.serviceAccountToken);
      } else {
         this.serviceAccountToken = null;
         this._visitables.get("serviceAccountToken").remove(this.serviceAccountToken);
      }

      return this;
   }

   public boolean hasServiceAccountToken() {
      return this.serviceAccountToken != null;
   }

   public VolumeProjectionFluent withNewServiceAccountToken(String audience, Long expirationSeconds, String path) {
      return this.withServiceAccountToken(new ServiceAccountTokenProjection(audience, expirationSeconds, path));
   }

   public ServiceAccountTokenNested withNewServiceAccountToken() {
      return new ServiceAccountTokenNested((ServiceAccountTokenProjection)null);
   }

   public ServiceAccountTokenNested withNewServiceAccountTokenLike(ServiceAccountTokenProjection item) {
      return new ServiceAccountTokenNested(item);
   }

   public ServiceAccountTokenNested editServiceAccountToken() {
      return this.withNewServiceAccountTokenLike((ServiceAccountTokenProjection)Optional.ofNullable(this.buildServiceAccountToken()).orElse((Object)null));
   }

   public ServiceAccountTokenNested editOrNewServiceAccountToken() {
      return this.withNewServiceAccountTokenLike((ServiceAccountTokenProjection)Optional.ofNullable(this.buildServiceAccountToken()).orElse((new ServiceAccountTokenProjectionBuilder()).build()));
   }

   public ServiceAccountTokenNested editOrNewServiceAccountTokenLike(ServiceAccountTokenProjection item) {
      return this.withNewServiceAccountTokenLike((ServiceAccountTokenProjection)Optional.ofNullable(this.buildServiceAccountToken()).orElse(item));
   }

   public VolumeProjectionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeProjectionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeProjectionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeProjectionFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeProjectionFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeProjectionFluent that = (VolumeProjectionFluent)o;
            if (!Objects.equals(this.clusterTrustBundle, that.clusterTrustBundle)) {
               return false;
            } else if (!Objects.equals(this.configMap, that.configMap)) {
               return false;
            } else if (!Objects.equals(this.downwardAPI, that.downwardAPI)) {
               return false;
            } else if (!Objects.equals(this.secret, that.secret)) {
               return false;
            } else if (!Objects.equals(this.serviceAccountToken, that.serviceAccountToken)) {
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
      return Objects.hash(new Object[]{this.clusterTrustBundle, this.configMap, this.downwardAPI, this.secret, this.serviceAccountToken, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.clusterTrustBundle != null) {
         sb.append("clusterTrustBundle:");
         sb.append(this.clusterTrustBundle + ",");
      }

      if (this.configMap != null) {
         sb.append("configMap:");
         sb.append(this.configMap + ",");
      }

      if (this.downwardAPI != null) {
         sb.append("downwardAPI:");
         sb.append(this.downwardAPI + ",");
      }

      if (this.secret != null) {
         sb.append("secret:");
         sb.append(this.secret + ",");
      }

      if (this.serviceAccountToken != null) {
         sb.append("serviceAccountToken:");
         sb.append(this.serviceAccountToken + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClusterTrustBundleNested extends ClusterTrustBundleProjectionFluent implements Nested {
      ClusterTrustBundleProjectionBuilder builder;

      ClusterTrustBundleNested(ClusterTrustBundleProjection item) {
         this.builder = new ClusterTrustBundleProjectionBuilder(this, item);
      }

      public Object and() {
         return VolumeProjectionFluent.this.withClusterTrustBundle(this.builder.build());
      }

      public Object endClusterTrustBundle() {
         return this.and();
      }
   }

   public class ConfigMapNested extends ConfigMapProjectionFluent implements Nested {
      ConfigMapProjectionBuilder builder;

      ConfigMapNested(ConfigMapProjection item) {
         this.builder = new ConfigMapProjectionBuilder(this, item);
      }

      public Object and() {
         return VolumeProjectionFluent.this.withConfigMap(this.builder.build());
      }

      public Object endConfigMap() {
         return this.and();
      }
   }

   public class DownwardAPINested extends DownwardAPIProjectionFluent implements Nested {
      DownwardAPIProjectionBuilder builder;

      DownwardAPINested(DownwardAPIProjection item) {
         this.builder = new DownwardAPIProjectionBuilder(this, item);
      }

      public Object and() {
         return VolumeProjectionFluent.this.withDownwardAPI(this.builder.build());
      }

      public Object endDownwardAPI() {
         return this.and();
      }
   }

   public class SecretNested extends SecretProjectionFluent implements Nested {
      SecretProjectionBuilder builder;

      SecretNested(SecretProjection item) {
         this.builder = new SecretProjectionBuilder(this, item);
      }

      public Object and() {
         return VolumeProjectionFluent.this.withSecret(this.builder.build());
      }

      public Object endSecret() {
         return this.and();
      }
   }

   public class ServiceAccountTokenNested extends ServiceAccountTokenProjectionFluent implements Nested {
      ServiceAccountTokenProjectionBuilder builder;

      ServiceAccountTokenNested(ServiceAccountTokenProjection item) {
         this.builder = new ServiceAccountTokenProjectionBuilder(this, item);
      }

      public Object and() {
         return VolumeProjectionFluent.this.withServiceAccountToken(this.builder.build());
      }

      public Object endServiceAccountToken() {
         return this.and();
      }
   }
}
