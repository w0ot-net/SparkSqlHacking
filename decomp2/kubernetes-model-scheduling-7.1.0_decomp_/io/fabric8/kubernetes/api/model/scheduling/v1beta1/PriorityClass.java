package io.fabric8.kubernetes.api.model.scheduling.v1beta1;

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
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "description", "globalDefault", "preemptionPolicy", "value"})
@Version("v1beta1")
@Group("scheduling.k8s.io")
public class PriorityClass implements Editable, HasMetadata {
   @JsonProperty("apiVersion")
   private String apiVersion = "scheduling.k8s.io/v1beta1";
   @JsonProperty("description")
   private String description;
   @JsonProperty("globalDefault")
   private Boolean globalDefault;
   @JsonProperty("kind")
   private String kind = "PriorityClass";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("preemptionPolicy")
   private String preemptionPolicy;
   @JsonProperty("value")
   private Integer value;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PriorityClass() {
   }

   public PriorityClass(String apiVersion, String description, Boolean globalDefault, String kind, ObjectMeta metadata, String preemptionPolicy, Integer value) {
      this.apiVersion = apiVersion;
      this.description = description;
      this.globalDefault = globalDefault;
      this.kind = kind;
      this.metadata = metadata;
      this.preemptionPolicy = preemptionPolicy;
      this.value = value;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("description")
   public String getDescription() {
      return this.description;
   }

   @JsonProperty("description")
   public void setDescription(String description) {
      this.description = description;
   }

   @JsonProperty("globalDefault")
   public Boolean getGlobalDefault() {
      return this.globalDefault;
   }

   @JsonProperty("globalDefault")
   public void setGlobalDefault(Boolean globalDefault) {
      this.globalDefault = globalDefault;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("preemptionPolicy")
   public String getPreemptionPolicy() {
      return this.preemptionPolicy;
   }

   @JsonProperty("preemptionPolicy")
   public void setPreemptionPolicy(String preemptionPolicy) {
      this.preemptionPolicy = preemptionPolicy;
   }

   @JsonProperty("value")
   public Integer getValue() {
      return this.value;
   }

   @JsonProperty("value")
   public void setValue(Integer value) {
      this.value = value;
   }

   @JsonIgnore
   public PriorityClassBuilder edit() {
      return new PriorityClassBuilder(this);
   }

   @JsonIgnore
   public PriorityClassBuilder toBuilder() {
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
      return "PriorityClass(apiVersion=" + var10000 + ", description=" + this.getDescription() + ", globalDefault=" + this.getGlobalDefault() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", preemptionPolicy=" + this.getPreemptionPolicy() + ", value=" + this.getValue() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PriorityClass)) {
         return false;
      } else {
         PriorityClass other = (PriorityClass)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$globalDefault = this.getGlobalDefault();
            Object other$globalDefault = other.getGlobalDefault();
            if (this$globalDefault == null) {
               if (other$globalDefault != null) {
                  return false;
               }
            } else if (!this$globalDefault.equals(other$globalDefault)) {
               return false;
            }

            Object this$value = this.getValue();
            Object other$value = other.getValue();
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$description = this.getDescription();
            Object other$description = other.getDescription();
            if (this$description == null) {
               if (other$description != null) {
                  return false;
               }
            } else if (!this$description.equals(other$description)) {
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

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$preemptionPolicy = this.getPreemptionPolicy();
            Object other$preemptionPolicy = other.getPreemptionPolicy();
            if (this$preemptionPolicy == null) {
               if (other$preemptionPolicy != null) {
                  return false;
               }
            } else if (!this$preemptionPolicy.equals(other$preemptionPolicy)) {
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
      return other instanceof PriorityClass;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $globalDefault = this.getGlobalDefault();
      result = result * 59 + ($globalDefault == null ? 43 : $globalDefault.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $description = this.getDescription();
      result = result * 59 + ($description == null ? 43 : $description.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $preemptionPolicy = this.getPreemptionPolicy();
      result = result * 59 + ($preemptionPolicy == null ? 43 : $preemptionPolicy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
