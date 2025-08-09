package io.fabric8.kubernetes.api.model.networking.v1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hosts", "secretName"})
public class IngressTLS implements Editable, KubernetesResource {
   @JsonProperty("hosts")
   @JsonInclude(Include.NON_EMPTY)
   private List hosts = new ArrayList();
   @JsonProperty("secretName")
   private String secretName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressTLS() {
   }

   public IngressTLS(List hosts, String secretName) {
      this.hosts = hosts;
      this.secretName = secretName;
   }

   @JsonProperty("hosts")
   @JsonInclude(Include.NON_EMPTY)
   public List getHosts() {
      return this.hosts;
   }

   @JsonProperty("hosts")
   public void setHosts(List hosts) {
      this.hosts = hosts;
   }

   @JsonProperty("secretName")
   public String getSecretName() {
      return this.secretName;
   }

   @JsonProperty("secretName")
   public void setSecretName(String secretName) {
      this.secretName = secretName;
   }

   @JsonIgnore
   public IngressTLSBuilder edit() {
      return new IngressTLSBuilder(this);
   }

   @JsonIgnore
   public IngressTLSBuilder toBuilder() {
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
      List var10000 = this.getHosts();
      return "IngressTLS(hosts=" + var10000 + ", secretName=" + this.getSecretName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressTLS)) {
         return false;
      } else {
         IngressTLS other = (IngressTLS)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hosts = this.getHosts();
            Object other$hosts = other.getHosts();
            if (this$hosts == null) {
               if (other$hosts != null) {
                  return false;
               }
            } else if (!this$hosts.equals(other$hosts)) {
               return false;
            }

            Object this$secretName = this.getSecretName();
            Object other$secretName = other.getSecretName();
            if (this$secretName == null) {
               if (other$secretName != null) {
                  return false;
               }
            } else if (!this$secretName.equals(other$secretName)) {
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
      return other instanceof IngressTLS;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hosts = this.getHosts();
      result = result * 59 + ($hosts == null ? 43 : $hosts.hashCode());
      Object $secretName = this.getSecretName();
      result = result * 59 + ($secretName == null ? 43 : $secretName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
