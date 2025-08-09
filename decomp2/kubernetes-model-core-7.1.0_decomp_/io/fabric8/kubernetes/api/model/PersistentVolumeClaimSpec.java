package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"accessModes", "dataSource", "dataSourceRef", "resources", "selector", "storageClassName", "volumeAttributesClassName", "volumeMode", "volumeName"})
public class PersistentVolumeClaimSpec implements Editable, KubernetesResource {
   @JsonProperty("accessModes")
   @JsonInclude(Include.NON_EMPTY)
   private List accessModes = new ArrayList();
   @JsonProperty("dataSource")
   private TypedLocalObjectReference dataSource;
   @JsonProperty("dataSourceRef")
   private TypedObjectReference dataSourceRef;
   @JsonProperty("resources")
   private VolumeResourceRequirements resources;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("storageClassName")
   private String storageClassName;
   @JsonProperty("volumeAttributesClassName")
   private String volumeAttributesClassName;
   @JsonProperty("volumeMode")
   private String volumeMode;
   @JsonProperty("volumeName")
   private String volumeName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeClaimSpec() {
   }

   public PersistentVolumeClaimSpec(List accessModes, TypedLocalObjectReference dataSource, TypedObjectReference dataSourceRef, VolumeResourceRequirements resources, LabelSelector selector, String storageClassName, String volumeAttributesClassName, String volumeMode, String volumeName) {
      this.accessModes = accessModes;
      this.dataSource = dataSource;
      this.dataSourceRef = dataSourceRef;
      this.resources = resources;
      this.selector = selector;
      this.storageClassName = storageClassName;
      this.volumeAttributesClassName = volumeAttributesClassName;
      this.volumeMode = volumeMode;
      this.volumeName = volumeName;
   }

   @JsonProperty("accessModes")
   @JsonInclude(Include.NON_EMPTY)
   public List getAccessModes() {
      return this.accessModes;
   }

   @JsonProperty("accessModes")
   public void setAccessModes(List accessModes) {
      this.accessModes = accessModes;
   }

   @JsonProperty("dataSource")
   public TypedLocalObjectReference getDataSource() {
      return this.dataSource;
   }

   @JsonProperty("dataSource")
   public void setDataSource(TypedLocalObjectReference dataSource) {
      this.dataSource = dataSource;
   }

   @JsonProperty("dataSourceRef")
   public TypedObjectReference getDataSourceRef() {
      return this.dataSourceRef;
   }

   @JsonProperty("dataSourceRef")
   public void setDataSourceRef(TypedObjectReference dataSourceRef) {
      this.dataSourceRef = dataSourceRef;
   }

   @JsonProperty("resources")
   public VolumeResourceRequirements getResources() {
      return this.resources;
   }

   @JsonProperty("resources")
   public void setResources(VolumeResourceRequirements resources) {
      this.resources = resources;
   }

   @JsonProperty("selector")
   public LabelSelector getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(LabelSelector selector) {
      this.selector = selector;
   }

   @JsonProperty("storageClassName")
   public String getStorageClassName() {
      return this.storageClassName;
   }

   @JsonProperty("storageClassName")
   public void setStorageClassName(String storageClassName) {
      this.storageClassName = storageClassName;
   }

   @JsonProperty("volumeAttributesClassName")
   public String getVolumeAttributesClassName() {
      return this.volumeAttributesClassName;
   }

   @JsonProperty("volumeAttributesClassName")
   public void setVolumeAttributesClassName(String volumeAttributesClassName) {
      this.volumeAttributesClassName = volumeAttributesClassName;
   }

   @JsonProperty("volumeMode")
   public String getVolumeMode() {
      return this.volumeMode;
   }

   @JsonProperty("volumeMode")
   public void setVolumeMode(String volumeMode) {
      this.volumeMode = volumeMode;
   }

   @JsonProperty("volumeName")
   public String getVolumeName() {
      return this.volumeName;
   }

   @JsonProperty("volumeName")
   public void setVolumeName(String volumeName) {
      this.volumeName = volumeName;
   }

   @JsonIgnore
   public PersistentVolumeClaimSpecBuilder edit() {
      return new PersistentVolumeClaimSpecBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeClaimSpecBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      List var10000 = this.getAccessModes();
      return "PersistentVolumeClaimSpec(accessModes=" + var10000 + ", dataSource=" + this.getDataSource() + ", dataSourceRef=" + this.getDataSourceRef() + ", resources=" + this.getResources() + ", selector=" + this.getSelector() + ", storageClassName=" + this.getStorageClassName() + ", volumeAttributesClassName=" + this.getVolumeAttributesClassName() + ", volumeMode=" + this.getVolumeMode() + ", volumeName=" + this.getVolumeName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeClaimSpec)) {
         return false;
      } else {
         PersistentVolumeClaimSpec other = (PersistentVolumeClaimSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$accessModes = this.getAccessModes();
            Object other$accessModes = other.getAccessModes();
            if (this$accessModes == null) {
               if (other$accessModes != null) {
                  return false;
               }
            } else if (!this$accessModes.equals(other$accessModes)) {
               return false;
            }

            Object this$dataSource = this.getDataSource();
            Object other$dataSource = other.getDataSource();
            if (this$dataSource == null) {
               if (other$dataSource != null) {
                  return false;
               }
            } else if (!this$dataSource.equals(other$dataSource)) {
               return false;
            }

            Object this$dataSourceRef = this.getDataSourceRef();
            Object other$dataSourceRef = other.getDataSourceRef();
            if (this$dataSourceRef == null) {
               if (other$dataSourceRef != null) {
                  return false;
               }
            } else if (!this$dataSourceRef.equals(other$dataSourceRef)) {
               return false;
            }

            Object this$resources = this.getResources();
            Object other$resources = other.getResources();
            if (this$resources == null) {
               if (other$resources != null) {
                  return false;
               }
            } else if (!this$resources.equals(other$resources)) {
               return false;
            }

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
               return false;
            }

            Object this$storageClassName = this.getStorageClassName();
            Object other$storageClassName = other.getStorageClassName();
            if (this$storageClassName == null) {
               if (other$storageClassName != null) {
                  return false;
               }
            } else if (!this$storageClassName.equals(other$storageClassName)) {
               return false;
            }

            Object this$volumeAttributesClassName = this.getVolumeAttributesClassName();
            Object other$volumeAttributesClassName = other.getVolumeAttributesClassName();
            if (this$volumeAttributesClassName == null) {
               if (other$volumeAttributesClassName != null) {
                  return false;
               }
            } else if (!this$volumeAttributesClassName.equals(other$volumeAttributesClassName)) {
               return false;
            }

            Object this$volumeMode = this.getVolumeMode();
            Object other$volumeMode = other.getVolumeMode();
            if (this$volumeMode == null) {
               if (other$volumeMode != null) {
                  return false;
               }
            } else if (!this$volumeMode.equals(other$volumeMode)) {
               return false;
            }

            Object this$volumeName = this.getVolumeName();
            Object other$volumeName = other.getVolumeName();
            if (this$volumeName == null) {
               if (other$volumeName != null) {
                  return false;
               }
            } else if (!this$volumeName.equals(other$volumeName)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof PersistentVolumeClaimSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $accessModes = this.getAccessModes();
      result = result * 59 + ($accessModes == null ? 43 : $accessModes.hashCode());
      Object $dataSource = this.getDataSource();
      result = result * 59 + ($dataSource == null ? 43 : $dataSource.hashCode());
      Object $dataSourceRef = this.getDataSourceRef();
      result = result * 59 + ($dataSourceRef == null ? 43 : $dataSourceRef.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $storageClassName = this.getStorageClassName();
      result = result * 59 + ($storageClassName == null ? 43 : $storageClassName.hashCode());
      Object $volumeAttributesClassName = this.getVolumeAttributesClassName();
      result = result * 59 + ($volumeAttributesClassName == null ? 43 : $volumeAttributesClassName.hashCode());
      Object $volumeMode = this.getVolumeMode();
      result = result * 59 + ($volumeMode == null ? 43 : $volumeMode.hashCode());
      Object $volumeName = this.getVolumeName();
      result = result * 59 + ($volumeName == null ? 43 : $volumeName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
