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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"datasetName", "datasetUUID"})
public class FlockerVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("datasetName")
   private String datasetName;
   @JsonProperty("datasetUUID")
   private String datasetUUID;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public FlockerVolumeSource() {
   }

   public FlockerVolumeSource(String datasetName, String datasetUUID) {
      this.datasetName = datasetName;
      this.datasetUUID = datasetUUID;
   }

   @JsonProperty("datasetName")
   public String getDatasetName() {
      return this.datasetName;
   }

   @JsonProperty("datasetName")
   public void setDatasetName(String datasetName) {
      this.datasetName = datasetName;
   }

   @JsonProperty("datasetUUID")
   public String getDatasetUUID() {
      return this.datasetUUID;
   }

   @JsonProperty("datasetUUID")
   public void setDatasetUUID(String datasetUUID) {
      this.datasetUUID = datasetUUID;
   }

   @JsonIgnore
   public FlockerVolumeSourceBuilder edit() {
      return new FlockerVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public FlockerVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getDatasetName();
      return "FlockerVolumeSource(datasetName=" + var10000 + ", datasetUUID=" + this.getDatasetUUID() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FlockerVolumeSource)) {
         return false;
      } else {
         FlockerVolumeSource other = (FlockerVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$datasetName = this.getDatasetName();
            Object other$datasetName = other.getDatasetName();
            if (this$datasetName == null) {
               if (other$datasetName != null) {
                  return false;
               }
            } else if (!this$datasetName.equals(other$datasetName)) {
               return false;
            }

            Object this$datasetUUID = this.getDatasetUUID();
            Object other$datasetUUID = other.getDatasetUUID();
            if (this$datasetUUID == null) {
               if (other$datasetUUID != null) {
                  return false;
               }
            } else if (!this$datasetUUID.equals(other$datasetUUID)) {
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
      return other instanceof FlockerVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $datasetName = this.getDatasetName();
      result = result * 59 + ($datasetName == null ? 43 : $datasetName.hashCode());
      Object $datasetUUID = this.getDatasetUUID();
      result = result * 59 + ($datasetUUID == null ? 43 : $datasetUUID.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
