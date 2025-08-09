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
@JsonPropertyOrder({"defaultBackend", "ingressClassName", "rules", "tls"})
public class IngressSpec implements Editable, KubernetesResource {
   @JsonProperty("defaultBackend")
   private IngressBackend defaultBackend;
   @JsonProperty("ingressClassName")
   private String ingressClassName;
   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   private List rules = new ArrayList();
   @JsonProperty("tls")
   @JsonInclude(Include.NON_EMPTY)
   private List tls = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressSpec() {
   }

   public IngressSpec(IngressBackend defaultBackend, String ingressClassName, List rules, List tls) {
      this.defaultBackend = defaultBackend;
      this.ingressClassName = ingressClassName;
      this.rules = rules;
      this.tls = tls;
   }

   @JsonProperty("defaultBackend")
   public IngressBackend getDefaultBackend() {
      return this.defaultBackend;
   }

   @JsonProperty("defaultBackend")
   public void setDefaultBackend(IngressBackend defaultBackend) {
      this.defaultBackend = defaultBackend;
   }

   @JsonProperty("ingressClassName")
   public String getIngressClassName() {
      return this.ingressClassName;
   }

   @JsonProperty("ingressClassName")
   public void setIngressClassName(String ingressClassName) {
      this.ingressClassName = ingressClassName;
   }

   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   public List getRules() {
      return this.rules;
   }

   @JsonProperty("rules")
   public void setRules(List rules) {
      this.rules = rules;
   }

   @JsonProperty("tls")
   @JsonInclude(Include.NON_EMPTY)
   public List getTls() {
      return this.tls;
   }

   @JsonProperty("tls")
   public void setTls(List tls) {
      this.tls = tls;
   }

   @JsonIgnore
   public IngressSpecBuilder edit() {
      return new IngressSpecBuilder(this);
   }

   @JsonIgnore
   public IngressSpecBuilder toBuilder() {
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
      IngressBackend var10000 = this.getDefaultBackend();
      return "IngressSpec(defaultBackend=" + var10000 + ", ingressClassName=" + this.getIngressClassName() + ", rules=" + this.getRules() + ", tls=" + this.getTls() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressSpec)) {
         return false;
      } else {
         IngressSpec other = (IngressSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$defaultBackend = this.getDefaultBackend();
            Object other$defaultBackend = other.getDefaultBackend();
            if (this$defaultBackend == null) {
               if (other$defaultBackend != null) {
                  return false;
               }
            } else if (!this$defaultBackend.equals(other$defaultBackend)) {
               return false;
            }

            Object this$ingressClassName = this.getIngressClassName();
            Object other$ingressClassName = other.getIngressClassName();
            if (this$ingressClassName == null) {
               if (other$ingressClassName != null) {
                  return false;
               }
            } else if (!this$ingressClassName.equals(other$ingressClassName)) {
               return false;
            }

            Object this$rules = this.getRules();
            Object other$rules = other.getRules();
            if (this$rules == null) {
               if (other$rules != null) {
                  return false;
               }
            } else if (!this$rules.equals(other$rules)) {
               return false;
            }

            Object this$tls = this.getTls();
            Object other$tls = other.getTls();
            if (this$tls == null) {
               if (other$tls != null) {
                  return false;
               }
            } else if (!this$tls.equals(other$tls)) {
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
      return other instanceof IngressSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $defaultBackend = this.getDefaultBackend();
      result = result * 59 + ($defaultBackend == null ? 43 : $defaultBackend.hashCode());
      Object $ingressClassName = this.getIngressClassName();
      result = result * 59 + ($ingressClassName == null ? 43 : $ingressClassName.hashCode());
      Object $rules = this.getRules();
      result = result * 59 + ($rules == null ? 43 : $rules.hashCode());
      Object $tls = this.getTls();
      result = result * 59 + ($tls == null ? 43 : $tls.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
