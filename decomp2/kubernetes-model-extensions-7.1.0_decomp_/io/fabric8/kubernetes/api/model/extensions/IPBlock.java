package io.fabric8.kubernetes.api.model.extensions;

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
@JsonPropertyOrder({"cidr", "except"})
public class IPBlock implements Editable, KubernetesResource {
   @JsonProperty("cidr")
   private String cidr;
   @JsonProperty("except")
   @JsonInclude(Include.NON_EMPTY)
   private List except = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IPBlock() {
   }

   public IPBlock(String cidr, List except) {
      this.cidr = cidr;
      this.except = except;
   }

   @JsonProperty("cidr")
   public String getCidr() {
      return this.cidr;
   }

   @JsonProperty("cidr")
   public void setCidr(String cidr) {
      this.cidr = cidr;
   }

   @JsonProperty("except")
   @JsonInclude(Include.NON_EMPTY)
   public List getExcept() {
      return this.except;
   }

   @JsonProperty("except")
   public void setExcept(List except) {
      this.except = except;
   }

   @JsonIgnore
   public IPBlockBuilder edit() {
      return new IPBlockBuilder(this);
   }

   @JsonIgnore
   public IPBlockBuilder toBuilder() {
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
      String var10000 = this.getCidr();
      return "IPBlock(cidr=" + var10000 + ", except=" + this.getExcept() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IPBlock)) {
         return false;
      } else {
         IPBlock other = (IPBlock)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$cidr = this.getCidr();
            Object other$cidr = other.getCidr();
            if (this$cidr == null) {
               if (other$cidr != null) {
                  return false;
               }
            } else if (!this$cidr.equals(other$cidr)) {
               return false;
            }

            Object this$except = this.getExcept();
            Object other$except = other.getExcept();
            if (this$except == null) {
               if (other$except != null) {
                  return false;
               }
            } else if (!this$except.equals(other$except)) {
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
      return other instanceof IPBlock;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $cidr = this.getCidr();
      result = result * 59 + ($cidr == null ? 43 : $cidr.hashCode());
      Object $except = this.getExcept();
      result = result * 59 + ($except == null ? 43 : $except.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
