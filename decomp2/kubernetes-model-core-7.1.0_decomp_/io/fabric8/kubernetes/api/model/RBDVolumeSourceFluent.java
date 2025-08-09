package io.fabric8.kubernetes.api.model;

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

public class RBDVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private String image;
   private String keyring;
   private List monitors = new ArrayList();
   private String pool;
   private Boolean readOnly;
   private LocalObjectReferenceBuilder secretRef;
   private String user;
   private Map additionalProperties;

   public RBDVolumeSourceFluent() {
   }

   public RBDVolumeSourceFluent(RBDVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RBDVolumeSource instance) {
      instance = instance != null ? instance : new RBDVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withImage(instance.getImage());
         this.withKeyring(instance.getKeyring());
         this.withMonitors(instance.getMonitors());
         this.withPool(instance.getPool());
         this.withReadOnly(instance.getReadOnly());
         this.withSecretRef(instance.getSecretRef());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public RBDVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public String getImage() {
      return this.image;
   }

   public RBDVolumeSourceFluent withImage(String image) {
      this.image = image;
      return this;
   }

   public boolean hasImage() {
      return this.image != null;
   }

   public String getKeyring() {
      return this.keyring;
   }

   public RBDVolumeSourceFluent withKeyring(String keyring) {
      this.keyring = keyring;
      return this;
   }

   public boolean hasKeyring() {
      return this.keyring != null;
   }

   public RBDVolumeSourceFluent addToMonitors(int index, String item) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      this.monitors.add(index, item);
      return this;
   }

   public RBDVolumeSourceFluent setToMonitors(int index, String item) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      this.monitors.set(index, item);
      return this;
   }

   public RBDVolumeSourceFluent addToMonitors(String... items) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      for(String item : items) {
         this.monitors.add(item);
      }

      return this;
   }

   public RBDVolumeSourceFluent addAllToMonitors(Collection items) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      for(String item : items) {
         this.monitors.add(item);
      }

      return this;
   }

   public RBDVolumeSourceFluent removeFromMonitors(String... items) {
      if (this.monitors == null) {
         return this;
      } else {
         for(String item : items) {
            this.monitors.remove(item);
         }

         return this;
      }
   }

   public RBDVolumeSourceFluent removeAllFromMonitors(Collection items) {
      if (this.monitors == null) {
         return this;
      } else {
         for(String item : items) {
            this.monitors.remove(item);
         }

         return this;
      }
   }

   public List getMonitors() {
      return this.monitors;
   }

   public String getMonitor(int index) {
      return (String)this.monitors.get(index);
   }

   public String getFirstMonitor() {
      return (String)this.monitors.get(0);
   }

   public String getLastMonitor() {
      return (String)this.monitors.get(this.monitors.size() - 1);
   }

   public String getMatchingMonitor(Predicate predicate) {
      for(String item : this.monitors) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingMonitor(Predicate predicate) {
      for(String item : this.monitors) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RBDVolumeSourceFluent withMonitors(List monitors) {
      if (monitors != null) {
         this.monitors = new ArrayList();

         for(String item : monitors) {
            this.addToMonitors(item);
         }
      } else {
         this.monitors = null;
      }

      return this;
   }

   public RBDVolumeSourceFluent withMonitors(String... monitors) {
      if (this.monitors != null) {
         this.monitors.clear();
         this._visitables.remove("monitors");
      }

      if (monitors != null) {
         for(String item : monitors) {
            this.addToMonitors(item);
         }
      }

      return this;
   }

   public boolean hasMonitors() {
      return this.monitors != null && !this.monitors.isEmpty();
   }

   public String getPool() {
      return this.pool;
   }

   public RBDVolumeSourceFluent withPool(String pool) {
      this.pool = pool;
      return this;
   }

   public boolean hasPool() {
      return this.pool != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public RBDVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public LocalObjectReference buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public RBDVolumeSourceFluent withSecretRef(LocalObjectReference secretRef) {
      this._visitables.remove("secretRef");
      if (secretRef != null) {
         this.secretRef = new LocalObjectReferenceBuilder(secretRef);
         this._visitables.get("secretRef").add(this.secretRef);
      } else {
         this.secretRef = null;
         this._visitables.get("secretRef").remove(this.secretRef);
      }

      return this;
   }

   public boolean hasSecretRef() {
      return this.secretRef != null;
   }

   public RBDVolumeSourceFluent withNewSecretRef(String name) {
      return this.withSecretRef(new LocalObjectReference(name));
   }

   public SecretRefNested withNewSecretRef() {
      return new SecretRefNested((LocalObjectReference)null);
   }

   public SecretRefNested withNewSecretRefLike(LocalObjectReference item) {
      return new SecretRefNested(item);
   }

   public SecretRefNested editSecretRef() {
      return this.withNewSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse((Object)null));
   }

   public SecretRefNested editOrNewSecretRef() {
      return this.withNewSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse((new LocalObjectReferenceBuilder()).build()));
   }

   public SecretRefNested editOrNewSecretRefLike(LocalObjectReference item) {
      return this.withNewSecretRefLike((LocalObjectReference)Optional.ofNullable(this.buildSecretRef()).orElse(item));
   }

   public String getUser() {
      return this.user;
   }

   public RBDVolumeSourceFluent withUser(String user) {
      this.user = user;
      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public RBDVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RBDVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RBDVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RBDVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public RBDVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            RBDVolumeSourceFluent that = (RBDVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.image, that.image)) {
               return false;
            } else if (!Objects.equals(this.keyring, that.keyring)) {
               return false;
            } else if (!Objects.equals(this.monitors, that.monitors)) {
               return false;
            } else if (!Objects.equals(this.pool, that.pool)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretRef, that.secretRef)) {
               return false;
            } else if (!Objects.equals(this.user, that.user)) {
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
      return Objects.hash(new Object[]{this.fsType, this.image, this.keyring, this.monitors, this.pool, this.readOnly, this.secretRef, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.image != null) {
         sb.append("image:");
         sb.append(this.image + ",");
      }

      if (this.keyring != null) {
         sb.append("keyring:");
         sb.append(this.keyring + ",");
      }

      if (this.monitors != null && !this.monitors.isEmpty()) {
         sb.append("monitors:");
         sb.append(this.monitors + ",");
      }

      if (this.pool != null) {
         sb.append("pool:");
         sb.append(this.pool + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretRef != null) {
         sb.append("secretRef:");
         sb.append(this.secretRef + ",");
      }

      if (this.user != null) {
         sb.append("user:");
         sb.append(this.user + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public RBDVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class SecretRefNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;

      SecretRefNested(LocalObjectReference item) {
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return RBDVolumeSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
