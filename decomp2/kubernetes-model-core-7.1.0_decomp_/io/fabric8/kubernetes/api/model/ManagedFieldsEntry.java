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
@JsonPropertyOrder({"apiVersion", "fieldsType", "fieldsV1", "manager", "operation", "subresource", "time"})
public class ManagedFieldsEntry implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion;
   @JsonProperty("fieldsType")
   private String fieldsType;
   @JsonProperty("fieldsV1")
   private FieldsV1 fieldsV1;
   @JsonProperty("manager")
   private String manager;
   @JsonProperty("operation")
   private String operation;
   @JsonProperty("subresource")
   private String subresource;
   @JsonProperty("time")
   private String time;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ManagedFieldsEntry() {
   }

   public ManagedFieldsEntry(String apiVersion, String fieldsType, FieldsV1 fieldsV1, String manager, String operation, String subresource, String time) {
      this.apiVersion = apiVersion;
      this.fieldsType = fieldsType;
      this.fieldsV1 = fieldsV1;
      this.manager = manager;
      this.operation = operation;
      this.subresource = subresource;
      this.time = time;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("fieldsType")
   public String getFieldsType() {
      return this.fieldsType;
   }

   @JsonProperty("fieldsType")
   public void setFieldsType(String fieldsType) {
      this.fieldsType = fieldsType;
   }

   @JsonProperty("fieldsV1")
   public FieldsV1 getFieldsV1() {
      return this.fieldsV1;
   }

   @JsonProperty("fieldsV1")
   public void setFieldsV1(FieldsV1 fieldsV1) {
      this.fieldsV1 = fieldsV1;
   }

   @JsonProperty("manager")
   public String getManager() {
      return this.manager;
   }

   @JsonProperty("manager")
   public void setManager(String manager) {
      this.manager = manager;
   }

   @JsonProperty("operation")
   public String getOperation() {
      return this.operation;
   }

   @JsonProperty("operation")
   public void setOperation(String operation) {
      this.operation = operation;
   }

   @JsonProperty("subresource")
   public String getSubresource() {
      return this.subresource;
   }

   @JsonProperty("subresource")
   public void setSubresource(String subresource) {
      this.subresource = subresource;
   }

   @JsonProperty("time")
   public String getTime() {
      return this.time;
   }

   @JsonProperty("time")
   public void setTime(String time) {
      this.time = time;
   }

   @JsonIgnore
   public ManagedFieldsEntryBuilder edit() {
      return new ManagedFieldsEntryBuilder(this);
   }

   @JsonIgnore
   public ManagedFieldsEntryBuilder toBuilder() {
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
      return "ManagedFieldsEntry(apiVersion=" + var10000 + ", fieldsType=" + this.getFieldsType() + ", fieldsV1=" + this.getFieldsV1() + ", manager=" + this.getManager() + ", operation=" + this.getOperation() + ", subresource=" + this.getSubresource() + ", time=" + this.getTime() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ManagedFieldsEntry)) {
         return false;
      } else {
         ManagedFieldsEntry other = (ManagedFieldsEntry)o;
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

            Object this$fieldsType = this.getFieldsType();
            Object other$fieldsType = other.getFieldsType();
            if (this$fieldsType == null) {
               if (other$fieldsType != null) {
                  return false;
               }
            } else if (!this$fieldsType.equals(other$fieldsType)) {
               return false;
            }

            Object this$fieldsV1 = this.getFieldsV1();
            Object other$fieldsV1 = other.getFieldsV1();
            if (this$fieldsV1 == null) {
               if (other$fieldsV1 != null) {
                  return false;
               }
            } else if (!this$fieldsV1.equals(other$fieldsV1)) {
               return false;
            }

            Object this$manager = this.getManager();
            Object other$manager = other.getManager();
            if (this$manager == null) {
               if (other$manager != null) {
                  return false;
               }
            } else if (!this$manager.equals(other$manager)) {
               return false;
            }

            Object this$operation = this.getOperation();
            Object other$operation = other.getOperation();
            if (this$operation == null) {
               if (other$operation != null) {
                  return false;
               }
            } else if (!this$operation.equals(other$operation)) {
               return false;
            }

            Object this$subresource = this.getSubresource();
            Object other$subresource = other.getSubresource();
            if (this$subresource == null) {
               if (other$subresource != null) {
                  return false;
               }
            } else if (!this$subresource.equals(other$subresource)) {
               return false;
            }

            Object this$time = this.getTime();
            Object other$time = other.getTime();
            if (this$time == null) {
               if (other$time != null) {
                  return false;
               }
            } else if (!this$time.equals(other$time)) {
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
      return other instanceof ManagedFieldsEntry;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $fieldsType = this.getFieldsType();
      result = result * 59 + ($fieldsType == null ? 43 : $fieldsType.hashCode());
      Object $fieldsV1 = this.getFieldsV1();
      result = result * 59 + ($fieldsV1 == null ? 43 : $fieldsV1.hashCode());
      Object $manager = this.getManager();
      result = result * 59 + ($manager == null ? 43 : $manager.hashCode());
      Object $operation = this.getOperation();
      result = result * 59 + ($operation == null ? 43 : $operation.hashCode());
      Object $subresource = this.getSubresource();
      result = result * 59 + ($subresource == null ? 43 : $subresource.hashCode());
      Object $time = this.getTime();
      result = result * 59 + ($time == null ? 43 : $time.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
