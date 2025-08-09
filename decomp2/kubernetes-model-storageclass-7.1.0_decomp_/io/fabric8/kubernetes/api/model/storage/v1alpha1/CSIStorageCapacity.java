package io.fabric8.kubernetes.api.model.storage.v1alpha1;

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
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "capacity", "maximumVolumeSize", "nodeTopology", "storageClassName"})
@Version("v1alpha1")
@Group("storage.k8s.io")
public class CSIStorageCapacity implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "storage.k8s.io/v1alpha1";
   @JsonProperty("capacity")
   private Quantity capacity;
   @JsonProperty("kind")
   private String kind = "CSIStorageCapacity";
   @JsonProperty("maximumVolumeSize")
   private Quantity maximumVolumeSize;
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("nodeTopology")
   private LabelSelector nodeTopology;
   @JsonProperty("storageClassName")
   private String storageClassName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CSIStorageCapacity() {
   }

   public CSIStorageCapacity(String apiVersion, Quantity capacity, String kind, Quantity maximumVolumeSize, ObjectMeta metadata, LabelSelector nodeTopology, String storageClassName) {
      this.apiVersion = apiVersion;
      this.capacity = capacity;
      this.kind = kind;
      this.maximumVolumeSize = maximumVolumeSize;
      this.metadata = metadata;
      this.nodeTopology = nodeTopology;
      this.storageClassName = storageClassName;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("capacity")
   public Quantity getCapacity() {
      return this.capacity;
   }

   @JsonProperty("capacity")
   public void setCapacity(Quantity capacity) {
      this.capacity = capacity;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("maximumVolumeSize")
   public Quantity getMaximumVolumeSize() {
      return this.maximumVolumeSize;
   }

   @JsonProperty("maximumVolumeSize")
   public void setMaximumVolumeSize(Quantity maximumVolumeSize) {
      this.maximumVolumeSize = maximumVolumeSize;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("nodeTopology")
   public LabelSelector getNodeTopology() {
      return this.nodeTopology;
   }

   @JsonProperty("nodeTopology")
   public void setNodeTopology(LabelSelector nodeTopology) {
      this.nodeTopology = nodeTopology;
   }

   @JsonProperty("storageClassName")
   public String getStorageClassName() {
      return this.storageClassName;
   }

   @JsonProperty("storageClassName")
   public void setStorageClassName(String storageClassName) {
      this.storageClassName = storageClassName;
   }

   @JsonIgnore
   public CSIStorageCapacityBuilder edit() {
      return new CSIStorageCapacityBuilder(this);
   }

   @JsonIgnore
   public CSIStorageCapacityBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "CSIStorageCapacity(apiVersion=" + var10000 + ", capacity=" + this.getCapacity() + ", kind=" + this.getKind() + ", maximumVolumeSize=" + this.getMaximumVolumeSize() + ", metadata=" + this.getMetadata() + ", nodeTopology=" + this.getNodeTopology() + ", storageClassName=" + this.getStorageClassName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CSIStorageCapacity)) {
         return false;
      } else {
         CSIStorageCapacity other = (CSIStorageCapacity)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$capacity = this.getCapacity();
            Object other$capacity = other.getCapacity();
            if (this$capacity == null) {
               if (other$capacity != null) {
                  return false;
               }
            } else if (!this$capacity.equals(other$capacity)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$maximumVolumeSize = this.getMaximumVolumeSize();
            Object other$maximumVolumeSize = other.getMaximumVolumeSize();
            if (this$maximumVolumeSize == null) {
               if (other$maximumVolumeSize != null) {
                  return false;
               }
            } else if (!this$maximumVolumeSize.equals(other$maximumVolumeSize)) {
               return false;
            }

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$nodeTopology = this.getNodeTopology();
            Object other$nodeTopology = other.getNodeTopology();
            if (this$nodeTopology == null) {
               if (other$nodeTopology != null) {
                  return false;
               }
            } else if (!this$nodeTopology.equals(other$nodeTopology)) {
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
      return other instanceof CSIStorageCapacity;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $capacity = this.getCapacity();
      result = result * 59 + ($capacity == null ? 43 : $capacity.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $maximumVolumeSize = this.getMaximumVolumeSize();
      result = result * 59 + ($maximumVolumeSize == null ? 43 : $maximumVolumeSize.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $nodeTopology = this.getNodeTopology();
      result = result * 59 + ($nodeTopology == null ? 43 : $nodeTopology.hashCode());
      Object $storageClassName = this.getStorageClassName();
      result = result * 59 + ($storageClassName == null ? 43 : $storageClassName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
