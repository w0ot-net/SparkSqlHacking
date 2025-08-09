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

public class ServiceAccountFluent extends BaseFluent {
   private String apiVersion;
   private Boolean automountServiceAccountToken;
   private ArrayList imagePullSecrets = new ArrayList();
   private String kind;
   private ObjectMetaBuilder metadata;
   private ArrayList secrets = new ArrayList();
   private Map additionalProperties;

   public ServiceAccountFluent() {
   }

   public ServiceAccountFluent(ServiceAccount instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServiceAccount instance) {
      instance = instance != null ? instance : new ServiceAccount();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withAutomountServiceAccountToken(instance.getAutomountServiceAccountToken());
         this.withImagePullSecrets(instance.getImagePullSecrets());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withSecrets(instance.getSecrets());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ServiceAccountFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public Boolean getAutomountServiceAccountToken() {
      return this.automountServiceAccountToken;
   }

   public ServiceAccountFluent withAutomountServiceAccountToken(Boolean automountServiceAccountToken) {
      this.automountServiceAccountToken = automountServiceAccountToken;
      return this;
   }

   public boolean hasAutomountServiceAccountToken() {
      return this.automountServiceAccountToken != null;
   }

   public ServiceAccountFluent addToImagePullSecrets(int index, LocalObjectReference item) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
      if (index >= 0 && index < this.imagePullSecrets.size()) {
         this._visitables.get("imagePullSecrets").add(index, builder);
         this.imagePullSecrets.add(index, builder);
      } else {
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent setToImagePullSecrets(int index, LocalObjectReference item) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
      if (index >= 0 && index < this.imagePullSecrets.size()) {
         this._visitables.get("imagePullSecrets").set(index, builder);
         this.imagePullSecrets.set(index, builder);
      } else {
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent addToImagePullSecrets(LocalObjectReference... items) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      for(LocalObjectReference item : items) {
         LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent addAllToImagePullSecrets(Collection items) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      for(LocalObjectReference item : items) {
         LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent removeFromImagePullSecrets(LocalObjectReference... items) {
      if (this.imagePullSecrets == null) {
         return this;
      } else {
         for(LocalObjectReference item : items) {
            LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
            this._visitables.get("imagePullSecrets").remove(builder);
            this.imagePullSecrets.remove(builder);
         }

         return this;
      }
   }

   public ServiceAccountFluent removeAllFromImagePullSecrets(Collection items) {
      if (this.imagePullSecrets == null) {
         return this;
      } else {
         for(LocalObjectReference item : items) {
            LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
            this._visitables.get("imagePullSecrets").remove(builder);
            this.imagePullSecrets.remove(builder);
         }

         return this;
      }
   }

   public ServiceAccountFluent removeMatchingFromImagePullSecrets(Predicate predicate) {
      if (this.imagePullSecrets == null) {
         return this;
      } else {
         Iterator<LocalObjectReferenceBuilder> each = this.imagePullSecrets.iterator();
         List visitables = this._visitables.get("imagePullSecrets");

         while(each.hasNext()) {
            LocalObjectReferenceBuilder builder = (LocalObjectReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildImagePullSecrets() {
      return this.imagePullSecrets != null ? build(this.imagePullSecrets) : null;
   }

   public LocalObjectReference buildImagePullSecret(int index) {
      return ((LocalObjectReferenceBuilder)this.imagePullSecrets.get(index)).build();
   }

   public LocalObjectReference buildFirstImagePullSecret() {
      return ((LocalObjectReferenceBuilder)this.imagePullSecrets.get(0)).build();
   }

   public LocalObjectReference buildLastImagePullSecret() {
      return ((LocalObjectReferenceBuilder)this.imagePullSecrets.get(this.imagePullSecrets.size() - 1)).build();
   }

   public LocalObjectReference buildMatchingImagePullSecret(Predicate predicate) {
      for(LocalObjectReferenceBuilder item : this.imagePullSecrets) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingImagePullSecret(Predicate predicate) {
      for(LocalObjectReferenceBuilder item : this.imagePullSecrets) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceAccountFluent withImagePullSecrets(List imagePullSecrets) {
      if (this.imagePullSecrets != null) {
         this._visitables.get("imagePullSecrets").clear();
      }

      if (imagePullSecrets != null) {
         this.imagePullSecrets = new ArrayList();

         for(LocalObjectReference item : imagePullSecrets) {
            this.addToImagePullSecrets(item);
         }
      } else {
         this.imagePullSecrets = null;
      }

      return this;
   }

   public ServiceAccountFluent withImagePullSecrets(LocalObjectReference... imagePullSecrets) {
      if (this.imagePullSecrets != null) {
         this.imagePullSecrets.clear();
         this._visitables.remove("imagePullSecrets");
      }

      if (imagePullSecrets != null) {
         for(LocalObjectReference item : imagePullSecrets) {
            this.addToImagePullSecrets(item);
         }
      }

      return this;
   }

   public boolean hasImagePullSecrets() {
      return this.imagePullSecrets != null && !this.imagePullSecrets.isEmpty();
   }

   public ServiceAccountFluent addNewImagePullSecret(String name) {
      return this.addToImagePullSecrets(new LocalObjectReference(name));
   }

   public ImagePullSecretsNested addNewImagePullSecret() {
      return new ImagePullSecretsNested(-1, (LocalObjectReference)null);
   }

   public ImagePullSecretsNested addNewImagePullSecretLike(LocalObjectReference item) {
      return new ImagePullSecretsNested(-1, item);
   }

   public ImagePullSecretsNested setNewImagePullSecretLike(int index, LocalObjectReference item) {
      return new ImagePullSecretsNested(index, item);
   }

   public ImagePullSecretsNested editImagePullSecret(int index) {
      if (this.imagePullSecrets.size() <= index) {
         throw new RuntimeException("Can't edit imagePullSecrets. Index exceeds size.");
      } else {
         return this.setNewImagePullSecretLike(index, this.buildImagePullSecret(index));
      }
   }

   public ImagePullSecretsNested editFirstImagePullSecret() {
      if (this.imagePullSecrets.size() == 0) {
         throw new RuntimeException("Can't edit first imagePullSecrets. The list is empty.");
      } else {
         return this.setNewImagePullSecretLike(0, this.buildImagePullSecret(0));
      }
   }

   public ImagePullSecretsNested editLastImagePullSecret() {
      int index = this.imagePullSecrets.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last imagePullSecrets. The list is empty.");
      } else {
         return this.setNewImagePullSecretLike(index, this.buildImagePullSecret(index));
      }
   }

   public ImagePullSecretsNested editMatchingImagePullSecret(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.imagePullSecrets.size(); ++i) {
         if (predicate.test((LocalObjectReferenceBuilder)this.imagePullSecrets.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching imagePullSecrets. No match found.");
      } else {
         return this.setNewImagePullSecretLike(index, this.buildImagePullSecret(index));
      }
   }

   public String getKind() {
      return this.kind;
   }

   public ServiceAccountFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public ServiceAccountFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public ServiceAccountFluent addToSecrets(int index, ObjectReference item) {
      if (this.secrets == null) {
         this.secrets = new ArrayList();
      }

      ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
      if (index >= 0 && index < this.secrets.size()) {
         this._visitables.get("secrets").add(index, builder);
         this.secrets.add(index, builder);
      } else {
         this._visitables.get("secrets").add(builder);
         this.secrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent setToSecrets(int index, ObjectReference item) {
      if (this.secrets == null) {
         this.secrets = new ArrayList();
      }

      ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
      if (index >= 0 && index < this.secrets.size()) {
         this._visitables.get("secrets").set(index, builder);
         this.secrets.set(index, builder);
      } else {
         this._visitables.get("secrets").add(builder);
         this.secrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent addToSecrets(ObjectReference... items) {
      if (this.secrets == null) {
         this.secrets = new ArrayList();
      }

      for(ObjectReference item : items) {
         ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
         this._visitables.get("secrets").add(builder);
         this.secrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent addAllToSecrets(Collection items) {
      if (this.secrets == null) {
         this.secrets = new ArrayList();
      }

      for(ObjectReference item : items) {
         ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
         this._visitables.get("secrets").add(builder);
         this.secrets.add(builder);
      }

      return this;
   }

   public ServiceAccountFluent removeFromSecrets(ObjectReference... items) {
      if (this.secrets == null) {
         return this;
      } else {
         for(ObjectReference item : items) {
            ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
            this._visitables.get("secrets").remove(builder);
            this.secrets.remove(builder);
         }

         return this;
      }
   }

   public ServiceAccountFluent removeAllFromSecrets(Collection items) {
      if (this.secrets == null) {
         return this;
      } else {
         for(ObjectReference item : items) {
            ObjectReferenceBuilder builder = new ObjectReferenceBuilder(item);
            this._visitables.get("secrets").remove(builder);
            this.secrets.remove(builder);
         }

         return this;
      }
   }

   public ServiceAccountFluent removeMatchingFromSecrets(Predicate predicate) {
      if (this.secrets == null) {
         return this;
      } else {
         Iterator<ObjectReferenceBuilder> each = this.secrets.iterator();
         List visitables = this._visitables.get("secrets");

         while(each.hasNext()) {
            ObjectReferenceBuilder builder = (ObjectReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSecrets() {
      return this.secrets != null ? build(this.secrets) : null;
   }

   public ObjectReference buildSecret(int index) {
      return ((ObjectReferenceBuilder)this.secrets.get(index)).build();
   }

   public ObjectReference buildFirstSecret() {
      return ((ObjectReferenceBuilder)this.secrets.get(0)).build();
   }

   public ObjectReference buildLastSecret() {
      return ((ObjectReferenceBuilder)this.secrets.get(this.secrets.size() - 1)).build();
   }

   public ObjectReference buildMatchingSecret(Predicate predicate) {
      for(ObjectReferenceBuilder item : this.secrets) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSecret(Predicate predicate) {
      for(ObjectReferenceBuilder item : this.secrets) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceAccountFluent withSecrets(List secrets) {
      if (this.secrets != null) {
         this._visitables.get("secrets").clear();
      }

      if (secrets != null) {
         this.secrets = new ArrayList();

         for(ObjectReference item : secrets) {
            this.addToSecrets(item);
         }
      } else {
         this.secrets = null;
      }

      return this;
   }

   public ServiceAccountFluent withSecrets(ObjectReference... secrets) {
      if (this.secrets != null) {
         this.secrets.clear();
         this._visitables.remove("secrets");
      }

      if (secrets != null) {
         for(ObjectReference item : secrets) {
            this.addToSecrets(item);
         }
      }

      return this;
   }

   public boolean hasSecrets() {
      return this.secrets != null && !this.secrets.isEmpty();
   }

   public SecretsNested addNewSecret() {
      return new SecretsNested(-1, (ObjectReference)null);
   }

   public SecretsNested addNewSecretLike(ObjectReference item) {
      return new SecretsNested(-1, item);
   }

   public SecretsNested setNewSecretLike(int index, ObjectReference item) {
      return new SecretsNested(index, item);
   }

   public SecretsNested editSecret(int index) {
      if (this.secrets.size() <= index) {
         throw new RuntimeException("Can't edit secrets. Index exceeds size.");
      } else {
         return this.setNewSecretLike(index, this.buildSecret(index));
      }
   }

   public SecretsNested editFirstSecret() {
      if (this.secrets.size() == 0) {
         throw new RuntimeException("Can't edit first secrets. The list is empty.");
      } else {
         return this.setNewSecretLike(0, this.buildSecret(0));
      }
   }

   public SecretsNested editLastSecret() {
      int index = this.secrets.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last secrets. The list is empty.");
      } else {
         return this.setNewSecretLike(index, this.buildSecret(index));
      }
   }

   public SecretsNested editMatchingSecret(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.secrets.size(); ++i) {
         if (predicate.test((ObjectReferenceBuilder)this.secrets.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching secrets. No match found.");
      } else {
         return this.setNewSecretLike(index, this.buildSecret(index));
      }
   }

   public ServiceAccountFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServiceAccountFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServiceAccountFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServiceAccountFluent removeFromAdditionalProperties(Map map) {
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

   public ServiceAccountFluent withAdditionalProperties(Map additionalProperties) {
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
            ServiceAccountFluent that = (ServiceAccountFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.automountServiceAccountToken, that.automountServiceAccountToken)) {
               return false;
            } else if (!Objects.equals(this.imagePullSecrets, that.imagePullSecrets)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.secrets, that.secrets)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.automountServiceAccountToken, this.imagePullSecrets, this.kind, this.metadata, this.secrets, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.automountServiceAccountToken != null) {
         sb.append("automountServiceAccountToken:");
         sb.append(this.automountServiceAccountToken + ",");
      }

      if (this.imagePullSecrets != null && !this.imagePullSecrets.isEmpty()) {
         sb.append("imagePullSecrets:");
         sb.append(this.imagePullSecrets + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.secrets != null && !this.secrets.isEmpty()) {
         sb.append("secrets:");
         sb.append(this.secrets + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ServiceAccountFluent withAutomountServiceAccountToken() {
      return this.withAutomountServiceAccountToken(true);
   }

   public class ImagePullSecretsNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;
      int index;

      ImagePullSecretsNested(int index, LocalObjectReference item) {
         this.index = index;
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return ServiceAccountFluent.this.setToImagePullSecrets(this.index, this.builder.build());
      }

      public Object endImagePullSecret() {
         return this.and();
      }
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return ServiceAccountFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class SecretsNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;
      int index;

      SecretsNested(int index, ObjectReference item) {
         this.index = index;
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return ServiceAccountFluent.this.setToSecrets(this.index, this.builder.build());
      }

      public Object endSecret() {
         return this.and();
      }
   }
}
