package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hostname", "type", "uri"})
public class SubjectAltName implements Editable, KubernetesResource {
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("type")
   private String type;
   @JsonProperty("uri")
   private String uri;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SubjectAltName() {
   }

   public SubjectAltName(String hostname, String type, String uri) {
      this.hostname = hostname;
      this.type = type;
      this.uri = uri;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonProperty("uri")
   public String getUri() {
      return this.uri;
   }

   @JsonProperty("uri")
   public void setUri(String uri) {
      this.uri = uri;
   }

   @JsonIgnore
   public SubjectAltNameBuilder edit() {
      return new SubjectAltNameBuilder(this);
   }

   @JsonIgnore
   public SubjectAltNameBuilder toBuilder() {
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
      String var10000 = this.getHostname();
      return "SubjectAltName(hostname=" + var10000 + ", type=" + this.getType() + ", uri=" + this.getUri() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SubjectAltName)) {
         return false;
      } else {
         SubjectAltName other = (SubjectAltName)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hostname = this.getHostname();
            Object other$hostname = other.getHostname();
            if (this$hostname == null) {
               if (other$hostname != null) {
                  return false;
               }
            } else if (!this$hostname.equals(other$hostname)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$uri = this.getUri();
            Object other$uri = other.getUri();
            if (this$uri == null) {
               if (other$uri != null) {
                  return false;
               }
            } else if (!this$uri.equals(other$uri)) {
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
      return other instanceof SubjectAltName;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $uri = this.getUri();
      result = result * 59 + ($uri == null ? 43 : $uri.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
