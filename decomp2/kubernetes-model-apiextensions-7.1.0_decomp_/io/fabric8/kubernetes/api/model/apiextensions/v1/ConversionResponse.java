package io.fabric8.kubernetes.api.model.apiextensions.v1;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.internal.KubernetesDeserializerForList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"convertedObjects", "result", "uid"})
public class ConversionResponse implements Editable, KubernetesResource {
   @JsonProperty("convertedObjects")
   @JsonDeserialize(
      using = KubernetesDeserializerForList.class
   )
   @JsonInclude(Include.NON_EMPTY)
   private List convertedObjects = new ArrayList();
   @JsonProperty("result")
   private Status result;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ConversionResponse() {
   }

   public ConversionResponse(List convertedObjects, Status result, String uid) {
      this.convertedObjects = convertedObjects;
      this.result = result;
      this.uid = uid;
   }

   @JsonProperty("convertedObjects")
   @JsonInclude(Include.NON_EMPTY)
   public List getConvertedObjects() {
      return this.convertedObjects;
   }

   @JsonProperty("convertedObjects")
   @JsonDeserialize(
      using = KubernetesDeserializerForList.class
   )
   public void setConvertedObjects(List convertedObjects) {
      this.convertedObjects = convertedObjects;
   }

   @JsonProperty("result")
   public Status getResult() {
      return this.result;
   }

   @JsonProperty("result")
   public void setResult(Status result) {
      this.result = result;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonIgnore
   public ConversionResponseBuilder edit() {
      return new ConversionResponseBuilder(this);
   }

   @JsonIgnore
   public ConversionResponseBuilder toBuilder() {
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
      List var10000 = this.getConvertedObjects();
      return "ConversionResponse(convertedObjects=" + var10000 + ", result=" + this.getResult() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConversionResponse)) {
         return false;
      } else {
         ConversionResponse other = (ConversionResponse)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$convertedObjects = this.getConvertedObjects();
            Object other$convertedObjects = other.getConvertedObjects();
            if (this$convertedObjects == null) {
               if (other$convertedObjects != null) {
                  return false;
               }
            } else if (!this$convertedObjects.equals(other$convertedObjects)) {
               return false;
            }

            Object this$result = this.getResult();
            Object other$result = other.getResult();
            if (this$result == null) {
               if (other$result != null) {
                  return false;
               }
            } else if (!this$result.equals(other$result)) {
               return false;
            }

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
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
      return other instanceof ConversionResponse;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $convertedObjects = this.getConvertedObjects();
      result = result * 59 + ($convertedObjects == null ? 43 : $convertedObjects.hashCode());
      Object $result = this.getResult();
      result = result * 59 + ($result == null ? 43 : $result.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
