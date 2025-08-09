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

public class CephFSVolumeSourceFluent extends BaseFluent {
   private List monitors = new ArrayList();
   private String path;
   private Boolean readOnly;
   private String secretFile;
   private LocalObjectReferenceBuilder secretRef;
   private String user;
   private Map additionalProperties;

   public CephFSVolumeSourceFluent() {
   }

   public CephFSVolumeSourceFluent(CephFSVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CephFSVolumeSource instance) {
      instance = instance != null ? instance : new CephFSVolumeSource();
      if (instance != null) {
         this.withMonitors(instance.getMonitors());
         this.withPath(instance.getPath());
         this.withReadOnly(instance.getReadOnly());
         this.withSecretFile(instance.getSecretFile());
         this.withSecretRef(instance.getSecretRef());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CephFSVolumeSourceFluent addToMonitors(int index, String item) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      this.monitors.add(index, item);
      return this;
   }

   public CephFSVolumeSourceFluent setToMonitors(int index, String item) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      this.monitors.set(index, item);
      return this;
   }

   public CephFSVolumeSourceFluent addToMonitors(String... items) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      for(String item : items) {
         this.monitors.add(item);
      }

      return this;
   }

   public CephFSVolumeSourceFluent addAllToMonitors(Collection items) {
      if (this.monitors == null) {
         this.monitors = new ArrayList();
      }

      for(String item : items) {
         this.monitors.add(item);
      }

      return this;
   }

   public CephFSVolumeSourceFluent removeFromMonitors(String... items) {
      if (this.monitors == null) {
         return this;
      } else {
         for(String item : items) {
            this.monitors.remove(item);
         }

         return this;
      }
   }

   public CephFSVolumeSourceFluent removeAllFromMonitors(Collection items) {
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

   public CephFSVolumeSourceFluent withMonitors(List monitors) {
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

   public CephFSVolumeSourceFluent withMonitors(String... monitors) {
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

   public String getPath() {
      return this.path;
   }

   public CephFSVolumeSourceFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public CephFSVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getSecretFile() {
      return this.secretFile;
   }

   public CephFSVolumeSourceFluent withSecretFile(String secretFile) {
      this.secretFile = secretFile;
      return this;
   }

   public boolean hasSecretFile() {
      return this.secretFile != null;
   }

   public LocalObjectReference buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public CephFSVolumeSourceFluent withSecretRef(LocalObjectReference secretRef) {
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

   public CephFSVolumeSourceFluent withNewSecretRef(String name) {
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

   public CephFSVolumeSourceFluent withUser(String user) {
      this.user = user;
      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public CephFSVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CephFSVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CephFSVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CephFSVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public CephFSVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            CephFSVolumeSourceFluent that = (CephFSVolumeSourceFluent)o;
            if (!Objects.equals(this.monitors, that.monitors)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretFile, that.secretFile)) {
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
      return Objects.hash(new Object[]{this.monitors, this.path, this.readOnly, this.secretFile, this.secretRef, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.monitors != null && !this.monitors.isEmpty()) {
         sb.append("monitors:");
         sb.append(this.monitors + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretFile != null) {
         sb.append("secretFile:");
         sb.append(this.secretFile + ",");
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

   public CephFSVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class SecretRefNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;

      SecretRefNested(LocalObjectReference item) {
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return CephFSVolumeSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
