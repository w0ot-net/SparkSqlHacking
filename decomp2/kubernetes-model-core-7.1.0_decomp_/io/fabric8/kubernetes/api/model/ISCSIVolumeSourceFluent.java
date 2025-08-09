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

public class ISCSIVolumeSourceFluent extends BaseFluent {
   private Boolean chapAuthDiscovery;
   private Boolean chapAuthSession;
   private String fsType;
   private String initiatorName;
   private String iqn;
   private String iscsiInterface;
   private Integer lun;
   private List portals = new ArrayList();
   private Boolean readOnly;
   private LocalObjectReferenceBuilder secretRef;
   private String targetPortal;
   private Map additionalProperties;

   public ISCSIVolumeSourceFluent() {
   }

   public ISCSIVolumeSourceFluent(ISCSIVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ISCSIVolumeSource instance) {
      instance = instance != null ? instance : new ISCSIVolumeSource();
      if (instance != null) {
         this.withChapAuthDiscovery(instance.getChapAuthDiscovery());
         this.withChapAuthSession(instance.getChapAuthSession());
         this.withFsType(instance.getFsType());
         this.withInitiatorName(instance.getInitiatorName());
         this.withIqn(instance.getIqn());
         this.withIscsiInterface(instance.getIscsiInterface());
         this.withLun(instance.getLun());
         this.withPortals(instance.getPortals());
         this.withReadOnly(instance.getReadOnly());
         this.withSecretRef(instance.getSecretRef());
         this.withTargetPortal(instance.getTargetPortal());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getChapAuthDiscovery() {
      return this.chapAuthDiscovery;
   }

   public ISCSIVolumeSourceFluent withChapAuthDiscovery(Boolean chapAuthDiscovery) {
      this.chapAuthDiscovery = chapAuthDiscovery;
      return this;
   }

   public boolean hasChapAuthDiscovery() {
      return this.chapAuthDiscovery != null;
   }

   public Boolean getChapAuthSession() {
      return this.chapAuthSession;
   }

   public ISCSIVolumeSourceFluent withChapAuthSession(Boolean chapAuthSession) {
      this.chapAuthSession = chapAuthSession;
      return this;
   }

   public boolean hasChapAuthSession() {
      return this.chapAuthSession != null;
   }

   public String getFsType() {
      return this.fsType;
   }

   public ISCSIVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public String getInitiatorName() {
      return this.initiatorName;
   }

   public ISCSIVolumeSourceFluent withInitiatorName(String initiatorName) {
      this.initiatorName = initiatorName;
      return this;
   }

   public boolean hasInitiatorName() {
      return this.initiatorName != null;
   }

   public String getIqn() {
      return this.iqn;
   }

   public ISCSIVolumeSourceFluent withIqn(String iqn) {
      this.iqn = iqn;
      return this;
   }

   public boolean hasIqn() {
      return this.iqn != null;
   }

   public String getIscsiInterface() {
      return this.iscsiInterface;
   }

   public ISCSIVolumeSourceFluent withIscsiInterface(String iscsiInterface) {
      this.iscsiInterface = iscsiInterface;
      return this;
   }

   public boolean hasIscsiInterface() {
      return this.iscsiInterface != null;
   }

   public Integer getLun() {
      return this.lun;
   }

   public ISCSIVolumeSourceFluent withLun(Integer lun) {
      this.lun = lun;
      return this;
   }

   public boolean hasLun() {
      return this.lun != null;
   }

   public ISCSIVolumeSourceFluent addToPortals(int index, String item) {
      if (this.portals == null) {
         this.portals = new ArrayList();
      }

      this.portals.add(index, item);
      return this;
   }

   public ISCSIVolumeSourceFluent setToPortals(int index, String item) {
      if (this.portals == null) {
         this.portals = new ArrayList();
      }

      this.portals.set(index, item);
      return this;
   }

   public ISCSIVolumeSourceFluent addToPortals(String... items) {
      if (this.portals == null) {
         this.portals = new ArrayList();
      }

      for(String item : items) {
         this.portals.add(item);
      }

      return this;
   }

   public ISCSIVolumeSourceFluent addAllToPortals(Collection items) {
      if (this.portals == null) {
         this.portals = new ArrayList();
      }

      for(String item : items) {
         this.portals.add(item);
      }

      return this;
   }

   public ISCSIVolumeSourceFluent removeFromPortals(String... items) {
      if (this.portals == null) {
         return this;
      } else {
         for(String item : items) {
            this.portals.remove(item);
         }

         return this;
      }
   }

   public ISCSIVolumeSourceFluent removeAllFromPortals(Collection items) {
      if (this.portals == null) {
         return this;
      } else {
         for(String item : items) {
            this.portals.remove(item);
         }

         return this;
      }
   }

   public List getPortals() {
      return this.portals;
   }

   public String getPortal(int index) {
      return (String)this.portals.get(index);
   }

   public String getFirstPortal() {
      return (String)this.portals.get(0);
   }

   public String getLastPortal() {
      return (String)this.portals.get(this.portals.size() - 1);
   }

   public String getMatchingPortal(Predicate predicate) {
      for(String item : this.portals) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingPortal(Predicate predicate) {
      for(String item : this.portals) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ISCSIVolumeSourceFluent withPortals(List portals) {
      if (portals != null) {
         this.portals = new ArrayList();

         for(String item : portals) {
            this.addToPortals(item);
         }
      } else {
         this.portals = null;
      }

      return this;
   }

   public ISCSIVolumeSourceFluent withPortals(String... portals) {
      if (this.portals != null) {
         this.portals.clear();
         this._visitables.remove("portals");
      }

      if (portals != null) {
         for(String item : portals) {
            this.addToPortals(item);
         }
      }

      return this;
   }

   public boolean hasPortals() {
      return this.portals != null && !this.portals.isEmpty();
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public ISCSIVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public LocalObjectReference buildSecretRef() {
      return this.secretRef != null ? this.secretRef.build() : null;
   }

   public ISCSIVolumeSourceFluent withSecretRef(LocalObjectReference secretRef) {
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

   public ISCSIVolumeSourceFluent withNewSecretRef(String name) {
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

   public String getTargetPortal() {
      return this.targetPortal;
   }

   public ISCSIVolumeSourceFluent withTargetPortal(String targetPortal) {
      this.targetPortal = targetPortal;
      return this;
   }

   public boolean hasTargetPortal() {
      return this.targetPortal != null;
   }

   public ISCSIVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ISCSIVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ISCSIVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ISCSIVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ISCSIVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ISCSIVolumeSourceFluent that = (ISCSIVolumeSourceFluent)o;
            if (!Objects.equals(this.chapAuthDiscovery, that.chapAuthDiscovery)) {
               return false;
            } else if (!Objects.equals(this.chapAuthSession, that.chapAuthSession)) {
               return false;
            } else if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.initiatorName, that.initiatorName)) {
               return false;
            } else if (!Objects.equals(this.iqn, that.iqn)) {
               return false;
            } else if (!Objects.equals(this.iscsiInterface, that.iscsiInterface)) {
               return false;
            } else if (!Objects.equals(this.lun, that.lun)) {
               return false;
            } else if (!Objects.equals(this.portals, that.portals)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.secretRef, that.secretRef)) {
               return false;
            } else if (!Objects.equals(this.targetPortal, that.targetPortal)) {
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
      return Objects.hash(new Object[]{this.chapAuthDiscovery, this.chapAuthSession, this.fsType, this.initiatorName, this.iqn, this.iscsiInterface, this.lun, this.portals, this.readOnly, this.secretRef, this.targetPortal, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.chapAuthDiscovery != null) {
         sb.append("chapAuthDiscovery:");
         sb.append(this.chapAuthDiscovery + ",");
      }

      if (this.chapAuthSession != null) {
         sb.append("chapAuthSession:");
         sb.append(this.chapAuthSession + ",");
      }

      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.initiatorName != null) {
         sb.append("initiatorName:");
         sb.append(this.initiatorName + ",");
      }

      if (this.iqn != null) {
         sb.append("iqn:");
         sb.append(this.iqn + ",");
      }

      if (this.iscsiInterface != null) {
         sb.append("iscsiInterface:");
         sb.append(this.iscsiInterface + ",");
      }

      if (this.lun != null) {
         sb.append("lun:");
         sb.append(this.lun + ",");
      }

      if (this.portals != null && !this.portals.isEmpty()) {
         sb.append("portals:");
         sb.append(this.portals + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.secretRef != null) {
         sb.append("secretRef:");
         sb.append(this.secretRef + ",");
      }

      if (this.targetPortal != null) {
         sb.append("targetPortal:");
         sb.append(this.targetPortal + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ISCSIVolumeSourceFluent withChapAuthDiscovery() {
      return this.withChapAuthDiscovery(true);
   }

   public ISCSIVolumeSourceFluent withChapAuthSession() {
      return this.withChapAuthSession(true);
   }

   public ISCSIVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }

   public class SecretRefNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;

      SecretRefNested(LocalObjectReference item) {
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return ISCSIVolumeSourceFluent.this.withSecretRef(this.builder.build());
      }

      public Object endSecretRef() {
         return this.and();
      }
   }
}
