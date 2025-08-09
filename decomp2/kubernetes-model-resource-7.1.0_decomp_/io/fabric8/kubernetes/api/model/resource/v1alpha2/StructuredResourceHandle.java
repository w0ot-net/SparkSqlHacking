package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"nodeName", "results", "vendorClaimParameters", "vendorClassParameters"})
public class StructuredResourceHandle implements Editable, KubernetesResource {
   @JsonProperty("nodeName")
   private String nodeName;
   @JsonProperty("results")
   @JsonInclude(Include.NON_EMPTY)
   private List results = new ArrayList();
   @JsonProperty("vendorClaimParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object vendorClaimParameters;
   @JsonProperty("vendorClassParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object vendorClassParameters;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StructuredResourceHandle() {
   }

   public StructuredResourceHandle(String nodeName, List results, Object vendorClaimParameters, Object vendorClassParameters) {
      this.nodeName = nodeName;
      this.results = results;
      this.vendorClaimParameters = vendorClaimParameters;
      this.vendorClassParameters = vendorClassParameters;
   }

   @JsonProperty("nodeName")
   public String getNodeName() {
      return this.nodeName;
   }

   @JsonProperty("nodeName")
   public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
   }

   @JsonProperty("results")
   @JsonInclude(Include.NON_EMPTY)
   public List getResults() {
      return this.results;
   }

   @JsonProperty("results")
   public void setResults(List results) {
      this.results = results;
   }

   @JsonProperty("vendorClaimParameters")
   public Object getVendorClaimParameters() {
      return this.vendorClaimParameters;
   }

   @JsonProperty("vendorClaimParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setVendorClaimParameters(Object vendorClaimParameters) {
      this.vendorClaimParameters = vendorClaimParameters;
   }

   @JsonProperty("vendorClassParameters")
   public Object getVendorClassParameters() {
      return this.vendorClassParameters;
   }

   @JsonProperty("vendorClassParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setVendorClassParameters(Object vendorClassParameters) {
      this.vendorClassParameters = vendorClassParameters;
   }

   @JsonIgnore
   public StructuredResourceHandleBuilder edit() {
      return new StructuredResourceHandleBuilder(this);
   }

   @JsonIgnore
   public StructuredResourceHandleBuilder toBuilder() {
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
      String var10000 = this.getNodeName();
      return "StructuredResourceHandle(nodeName=" + var10000 + ", results=" + this.getResults() + ", vendorClaimParameters=" + this.getVendorClaimParameters() + ", vendorClassParameters=" + this.getVendorClassParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StructuredResourceHandle)) {
         return false;
      } else {
         StructuredResourceHandle other = (StructuredResourceHandle)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nodeName = this.getNodeName();
            Object other$nodeName = other.getNodeName();
            if (this$nodeName == null) {
               if (other$nodeName != null) {
                  return false;
               }
            } else if (!this$nodeName.equals(other$nodeName)) {
               return false;
            }

            Object this$results = this.getResults();
            Object other$results = other.getResults();
            if (this$results == null) {
               if (other$results != null) {
                  return false;
               }
            } else if (!this$results.equals(other$results)) {
               return false;
            }

            Object this$vendorClaimParameters = this.getVendorClaimParameters();
            Object other$vendorClaimParameters = other.getVendorClaimParameters();
            if (this$vendorClaimParameters == null) {
               if (other$vendorClaimParameters != null) {
                  return false;
               }
            } else if (!this$vendorClaimParameters.equals(other$vendorClaimParameters)) {
               return false;
            }

            Object this$vendorClassParameters = this.getVendorClassParameters();
            Object other$vendorClassParameters = other.getVendorClassParameters();
            if (this$vendorClassParameters == null) {
               if (other$vendorClassParameters != null) {
                  return false;
               }
            } else if (!this$vendorClassParameters.equals(other$vendorClassParameters)) {
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
      return other instanceof StructuredResourceHandle;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nodeName = this.getNodeName();
      result = result * 59 + ($nodeName == null ? 43 : $nodeName.hashCode());
      Object $results = this.getResults();
      result = result * 59 + ($results == null ? 43 : $results.hashCode());
      Object $vendorClaimParameters = this.getVendorClaimParameters();
      result = result * 59 + ($vendorClaimParameters == null ? 43 : $vendorClaimParameters.hashCode());
      Object $vendorClassParameters = this.getVendorClassParameters();
      result = result * 59 + ($vendorClassParameters == null ? 43 : $vendorClassParameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
