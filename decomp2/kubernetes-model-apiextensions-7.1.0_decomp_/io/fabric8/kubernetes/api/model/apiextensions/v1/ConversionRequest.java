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
@JsonPropertyOrder({"desiredAPIVersion", "objects", "uid"})
public class ConversionRequest implements Editable, KubernetesResource {
   @JsonProperty("desiredAPIVersion")
   private String desiredAPIVersion;
   @JsonProperty("objects")
   @JsonDeserialize(
      using = KubernetesDeserializerForList.class
   )
   @JsonInclude(Include.NON_EMPTY)
   private List objects = new ArrayList();
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ConversionRequest() {
   }

   public ConversionRequest(String desiredAPIVersion, List objects, String uid) {
      this.desiredAPIVersion = desiredAPIVersion;
      this.objects = objects;
      this.uid = uid;
   }

   @JsonProperty("desiredAPIVersion")
   public String getDesiredAPIVersion() {
      return this.desiredAPIVersion;
   }

   @JsonProperty("desiredAPIVersion")
   public void setDesiredAPIVersion(String desiredAPIVersion) {
      this.desiredAPIVersion = desiredAPIVersion;
   }

   @JsonProperty("objects")
   @JsonInclude(Include.NON_EMPTY)
   public List getObjects() {
      return this.objects;
   }

   @JsonProperty("objects")
   @JsonDeserialize(
      using = KubernetesDeserializerForList.class
   )
   public void setObjects(List objects) {
      this.objects = objects;
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
   public ConversionRequestBuilder edit() {
      return new ConversionRequestBuilder(this);
   }

   @JsonIgnore
   public ConversionRequestBuilder toBuilder() {
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
      String var10000 = this.getDesiredAPIVersion();
      return "ConversionRequest(desiredAPIVersion=" + var10000 + ", objects=" + this.getObjects() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConversionRequest)) {
         return false;
      } else {
         ConversionRequest other = (ConversionRequest)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$desiredAPIVersion = this.getDesiredAPIVersion();
            Object other$desiredAPIVersion = other.getDesiredAPIVersion();
            if (this$desiredAPIVersion == null) {
               if (other$desiredAPIVersion != null) {
                  return false;
               }
            } else if (!this$desiredAPIVersion.equals(other$desiredAPIVersion)) {
               return false;
            }

            Object this$objects = this.getObjects();
            Object other$objects = other.getObjects();
            if (this$objects == null) {
               if (other$objects != null) {
                  return false;
               }
            } else if (!this$objects.equals(other$objects)) {
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
      return other instanceof ConversionRequest;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $desiredAPIVersion = this.getDesiredAPIVersion();
      result = result * 59 + ($desiredAPIVersion == null ? 43 : $desiredAPIVersion.hashCode());
      Object $objects = this.getObjects();
      result = result * 59 + ($objects == null ? 43 : $objects.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
